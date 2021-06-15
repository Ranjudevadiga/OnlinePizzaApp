package com.cg.onlinepizza.service;

import java.util.List;

import com.cg.onlinepizza.dto.CouponDto;
import com.cg.onlinepizza.entity.Coupon;
import com.cg.onlinepizza.entity.Customer;
import com.cg.onlinepizza.entity.Pizza;
import com.cg.onlinepizza.entity.PizzaOrder;

public interface IAdminService {
	void addCoupans(CouponDto couponDto);

	String editCoupans(CouponDto couponDto, int couponId);//throws InvalidCoupanOperationException;

	String deleteCoupans(int couponId); //throws CoupanIdNotFoundException;

	List<Coupon> viewCoupons();
	
	public List<Customer> viewCustomer();
	public void addPizza(Pizza pizza);
	public String updatePizza(int pizzaId, Pizza pizza);
	public String deletePizza(int pizzaId);
	public List<Pizza> viewPizzaList();
	public List<PizzaOrder> viewPizzaOrders();
	public PizzaOrder acceptOrder(int orderId);
	public PizzaOrder cancelOrder(int orderId);
	public PizzaOrder delivered(int orderId); 
}
