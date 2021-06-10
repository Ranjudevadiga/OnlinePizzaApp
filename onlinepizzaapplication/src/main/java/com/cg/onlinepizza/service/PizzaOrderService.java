package com.cg.onlinepizza.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dao.CouponDao;
import com.cg.onlinepizza.dao.CustomerDao;
import com.cg.onlinepizza.dao.PizzaDao;
import com.cg.onlinepizza.dao.PizzaOrderDao;
import com.cg.onlinepizza.dto.PizzaOrderDTO;
import com.cg.onlinepizza.entity.Coupon;
import com.cg.onlinepizza.entity.Customer;
import com.cg.onlinepizza.entity.Pizza;
import com.cg.onlinepizza.entity.PizzaOrder;
import com.cg.onlinepizza.utils.BookingIdNotAvailableException;
import com.cg.onlinepizza.utils.BookingOrderIdDoesNotExists;
import com.cg.onlinepizza.utils.CouponDoesNotExistsException;
import com.cg.onlinepizza.utils.CouponDoesNotMatchException;
import com.cg.onlinepizza.utils.PizzaNotFoundException;

@Service
public class PizzaOrderService implements IPizzaOrderService{
	@Autowired
	CouponDao couponDao;
	@Autowired
	PizzaDao pizzaDao;
	@Autowired
	PizzaOrderDao pizzaOrderDao;
	@Autowired
	CustomerDao customerDao;

	@Override
	public PizzaOrder bookPizzaOrder(PizzaOrderDTO orderDto) {
		
		Pizza pizza=pizzaDao.getPizzaId(orderDto.getPizzaId());
		if(couponDao.checkExists(orderDto.getCouponName())<1&&orderDto.getCouponName()!=null) {
			throw new CouponDoesNotExistsException();
		}
		Coupon coupon= couponDao.getByCouponName(orderDto.getCouponName());
		

		LocalDate todaysDate = LocalDate.now();
		PizzaOrder order=new PizzaOrder();
		order.setPizzaQuantity(orderDto.getPizzaQuantity());
		order.setTransactionMode(orderDto.getTransactionMode());
		order.setDateOfOrder(todaysDate);
		System.out.println(coupon);
		order.setPizzaSize(pizza.getPizzaSize());
		Customer customer=customerDao.getCustomerId(orderDto.getCustomerId());
		int qty=orderDto.getPizzaQuantity();
		double price=pizza.getPizzaCost();
		System.out.println(coupon);
		if(coupon==null) {
			order.setTotalCost(calculate(price,qty));
			order.setCoupon(coupon);
			order.setPizza(pizza);
			order.setCustomer(customer);
			return pizzaOrderDao.save(order);
		}
		else {
			if(pizza.getPizzaId()!=coupon.getPizza().getPizzaId())
			{
				throw new CouponDoesNotMatchException();
			}

			
			order.setTotalCost(caluculateTotal(pizza.getPizzaId(),orderDto.getCouponName(),price,qty));
			order.setCoupon(coupon);
			order.setPizza(pizza);
			order.setCustomer(customer);
			return pizzaOrderDao.save(order);
			
		}
		
	}

	@Override
	public PizzaOrder updatePizzaOrder(PizzaOrderDTO orderDto) {
		if(pizzaOrderDao.getPizzaOrderById(orderDto.getBookingOrderId())!=null)
		{
			
			PizzaOrder order=pizzaOrderDao.getPizzaOrderById(orderDto.getBookingOrderId());
			Coupon coupon= couponDao.getByCouponName(orderDto.getCouponName());
			Pizza pizza=pizzaDao.getPizzaId(orderDto.getPizzaId());
			order.setPizzaQuantity(orderDto.getPizzaQuantity());
			
			int qty=orderDto.getPizzaQuantity();
			double price=pizza.getPizzaCost();
			System.out.println(coupon);
			if(coupon==null) {
				order.setTotalCost(calculate(price,qty));
				
				return pizzaOrderDao.save(order);
				
			}
			else {
				
				order.setTotalCost(caluculateTotal(pizza.getPizzaId(),orderDto.getCouponName(),price,qty));
				return pizzaOrderDao.save(order);
				
			}
		}else {
			throw new BookingIdNotAvailableException();
		}

	
	}

	@Override
	public List<PizzaOrder> cancelPizzaOrder(int orderId) {
		pizzaOrderDao.deleteById(orderId);
		return pizzaOrderDao.findAll();
	}

	@Override
	public PizzaOrder viewPizzaOrder(int orderId) {
		return pizzaOrderDao.getPizzaOrderById(orderId);
		
	}

	

	@Override
	public double caluculateTotal(int pizzaId, String couponName, double price, int quantity) {
		Coupon coupon= couponDao.getByCouponName(couponName);
		System.out.println(couponDao.getByCouponName(couponName));
		int id=coupon.getPizza().getPizzaId();
		double gross=0;
		if(id==pizzaId)
		{
			double discount=coupon.getDiscountValue();
			double discountamount=discount/100;
			System.out.println(discountamount);
			double amount=quantity*price;
			double totalAmount=amount-(discountamount*amount);
			gross=totalAmount+(totalAmount*.18);
			if(gross<500)
			{
				return gross+50;
			}
			
		}
		return gross;
		
		
	}
	public double calculate(double price,int quantity) {
			double amount=quantity*price;
			double totalAmount=amount;
			double gross=totalAmount+(totalAmount*.18);
			if(gross<500)
			{
				return gross+50;
			}
			
		
		return gross;
		
	}

	@Override
	public List<PizzaOrder> viewListOrder(int customerId) {
		return pizzaOrderDao.getPizzaOrderByCustomerId(customerId);
	}

}
