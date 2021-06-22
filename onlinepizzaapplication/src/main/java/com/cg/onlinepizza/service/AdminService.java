package com.cg.onlinepizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dao.CouponDao;
import com.cg.onlinepizza.dao.CustomerDao;
import com.cg.onlinepizza.dao.PizzaDao;
import com.cg.onlinepizza.dao.PizzaOrderDao;
import com.cg.onlinepizza.dto.CouponDto;
import com.cg.onlinepizza.entity.Coupon;
import com.cg.onlinepizza.entity.Customer;
import com.cg.onlinepizza.entity.Pizza;
import com.cg.onlinepizza.entity.PizzaOrder;
import com.cg.onlinepizza.utils.OrderNotAcceptedException;
import com.cg.onlinepizza.utils.PizzaIdNotFoundException;


@Service
public class AdminService implements IAdminService{
	@Autowired
	CouponDao couponDao;
	@Autowired
	PizzaDao pizzaDao;
	@Autowired
	CustomerDao customerDao;
	@Autowired
	PizzaOrderDao pizzaOrderDao;
	
	
	//Add Coupans
	@Override
	public void addCoupans(CouponDto couponDto) {
		Coupon coupon=new Coupon();
		coupon.setCouponId(couponDto.getCouponId());
		coupon.setCouponName(couponDto.getCouponName());
		coupon.setCouponDescription(couponDto.getCouponDescription());
		coupon.setDiscountValue(couponDto.getDiscountValue());
		int id= couponDto.getPizzaId();
		Pizza pizza = pizzaDao.getPizzaId(id);
		coupon.setPizza(pizza);
		couponDao.save(coupon);
	}
	
	//Updates Coupans
	@Override
	public String editCoupans(CouponDto couponDto, int coupanId) {
		if(couponDao.existsById(coupanId)){
			Coupon coupon=new Coupon();
			coupon.setCouponName(couponDto.getCouponName());
			coupon.setCouponDescription(couponDto.getCouponDescription());
			coupon.setDiscountValue(couponDto.getDiscountValue());
			int id= couponDto.getPizzaId();
			Pizza pizza = pizzaDao.getPizzaId(id);
			coupon.setPizza(pizza);
			couponDao.save(coupon);
			return "coupan updated successfully";
		}
		else
			return null;
	}

	//Deletes Coupans
	@Override
	public String deleteCoupans(int coupanId) {
		if(couponDao.existsById(coupanId)) {
			couponDao.deleteById(coupanId);
		return "Coupon Deleted";
	}
		else
			return null;
	}

	//View all coupans 
	@Override
	public List<Coupon> viewCoupons() {
		List<Coupon> couponList =couponDao.findAll();
		if(couponList.isEmpty()) 
			return null; 
		else 
			return couponList;
	
	}
	
	
	//View all customers
	@Override
		public List<Customer> viewCustomer() {
			List<Customer> customers = customerDao.findAll();
				return customers;
	}
		
		
		
	//Pizza table insertion
	@Override
	public void addPizza(Pizza pizza) {
		pizzaDao.save(pizza);
	}
		

	
	//Updating Pizza Information
	@Override
	public String updatePizza(int pizzaId, Pizza pizza) {
		if(pizzaDao.existsById(pizzaId)){
			pizzaDao.save(pizza);
			return "Pizza Updated";
		}
		return "Pizza Id not valid";
				
	}
		

	
	//Deleting Pizza
	@Override
	public String deletePizza(int pizzaId) {
			pizzaDao.deleteById(pizzaId);
				return "Pizza Cancelled";
			
		}


	
	//To view all the available Pizza's
	@Override
	public List<Pizza> viewPizzaList() throws PizzaIdNotFoundException {
		List<Pizza> pizza = pizzaDao.findAll();
		return pizza;
	}

	
	
	@Override
	public List<PizzaOrder> viewPizzaOrders() {
	List<PizzaOrder> order=pizzaOrderDao.getAllOrder();
	return order;
	}


	
	
	@Override
	public PizzaOrder acceptOrder(int orderId) {
		PizzaOrder order=pizzaOrderDao.getPizzaOrderById(orderId);
		order.setStatus("Accepted");
		pizzaOrderDao.save(order);
		return order;
	}


	
	@Override
	public PizzaOrder cancelOrder(int orderId) {
		PizzaOrder order=pizzaOrderDao.getPizzaOrderById(orderId);
		order.setStatus("Cancelled");
		pizzaOrderDao.save(order);
		return order;
	}


	
	@Override
	public PizzaOrder delivered(int orderId) {
		PizzaOrder order=pizzaOrderDao.getPizzaOrderById(orderId);
		System.out.println(order.getStatus());
		if(order.getStatus().equals("Accepted")) {
		order.setStatus("Delivered");
		pizzaOrderDao.save(order);
		return order;
		}else {
			throw new OrderNotAcceptedException();
		}
	}

	@Override
	public List<PizzaOrder> viewCurrentOrders() {
		return pizzaOrderDao.getAdminCurrentOrders();
		
	}

}
