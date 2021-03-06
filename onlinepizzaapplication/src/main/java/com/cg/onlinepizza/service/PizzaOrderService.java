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
import com.cg.onlinepizza.utils.CouponDoesNotExistsException;
import com.cg.onlinepizza.utils.CouponDoesNotMatchException;
import com.cg.onlinepizza.utils.OrderCancelException;
import com.cg.onlinepizza.utils.OrderUpdateException;
import com.cg.onlinepizza.utils.PizzaIdNotFoundException;

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

	
	//Book Pizza Order
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
		order.setStatus("Ordered");
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

	//Update pizza order
	@Override
	public PizzaOrder updatePizzaOrder(PizzaOrderDTO orderDto) {
		LocalDate date=LocalDate.now();
		System.out.println(orderDto.getDateOfOrder());
		PizzaOrder order=pizzaOrderDao.getPizzaOrderById(orderDto.getBookingOrderId());
		if(order.getDateOfOrder().compareTo(date)==0) {
		
			if(pizzaOrderDao.getPizzaOrderById(orderDto.getBookingOrderId())!=null)
			{
				
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
		}else {
			throw new OrderUpdateException();
		}
		}
	
	
	//Cancel pizza order
	@Override
	public List<PizzaOrder> cancelPizzaOrder(int orderId) {
		
		LocalDate date=LocalDate.now();
		PizzaOrder order=pizzaOrderDao.getPizzaOrderById(orderId);
		if(order.getDateOfOrder().compareTo(date)==0) {
		pizzaOrderDao.deleteById(orderId);
		return pizzaOrderDao.findAll();
		}else {
			throw new OrderCancelException();
		}
		
	}
	
	
	
	//View a pizza order
	@Override
	public PizzaOrder viewPizzaOrder(int orderId) {
		PizzaOrder order= pizzaOrderDao.getPizzaOrderById(orderId);
		System.out.println(order.getPizza().getPizzaName());
		return order;
		
		
	}

	
	//Total calculation
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
	
	//View Orders of a particular customer
	@Override
	public List<PizzaOrder> viewOrderList(int customerId) {
		return pizzaOrderDao.getPizzaOrderByCustomerId(customerId);
	}

	//Sort pizza by price
	@Override
	public List<Pizza> viewPizzaByPrice(double min, double max) {
		List<Pizza> pizza=pizzaDao.sortByPrice(min, max);
		return pizza;
	}
	
	//View All Coupans
	@Override
	public List<Coupon> viewCoupons() {
		List<Coupon> couponList =couponDao.findAll();
		if(couponList.isEmpty()) 
			return null; 
		else 
			return couponList;
		}
	
	//Customer registration
	@Override
	public void addCustomer(Customer customer)  {
		 customerDao.save(customer);
		
	}
	
	//updating customer
	@Override
	public String updateCustomer(int customerId,Customer customer) {
		if(customerDao.existsById(customerId)){
			customerDao.save(customer);
			return "updated";
		}
		return "Id Not Found";
	}

	//deleting customer
	@Override
	public boolean deleteCustomer(Integer customerId) {
		customerDao.deleteById(customerId);
		return true;
	}

	//Viewing customer by Id
	@Override
	public Customer viewCustomerById(Integer customerId) {
			return customerDao.getCustomerId(customerId);
	}
	
	//View all the pizza's available
	@Override
	public List<Pizza> viewPizzaList() throws PizzaIdNotFoundException {
		List<Pizza> pizza = pizzaDao.findAll();
		return pizza;
	}

	//Login validation
	@Override
	public Customer validate(String email, String password) {
		Customer login=customerDao.validate(email,password);

		return login;
	}

	//View current order
	@Override
	public List<PizzaOrder> viewCurrentorder(int id) {
		
		return pizzaOrderDao.getCurrentOrders(id);
	}

	//Sort pizza by size
	@Override
	public List<Pizza> viewPizzaByType(String type) {
		List<Pizza> pizzaList=pizzaDao.sortByType(type);
		return pizzaList;
	}
		
	
}
