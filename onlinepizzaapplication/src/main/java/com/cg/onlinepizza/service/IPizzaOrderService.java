package com.cg.onlinepizza.service;

import java.util.List;

import com.cg.onlinepizza.dto.PizzaOrderDTO;
import com.cg.onlinepizza.entity.PizzaOrder;


public interface IPizzaOrderService {
	PizzaOrder bookPizzaOrder(PizzaOrderDTO orderDto);

	PizzaOrder updatePizzaOrder(PizzaOrderDTO pizzaOrderDto);

	List<PizzaOrder> cancelPizzaOrder(int orderId);

	PizzaOrder viewPizzaOrder(int orderId);
	
	List<PizzaOrder> viewListOrder(int customerId);

	double caluculateTotal(int pizzaId,String couponName,double price, int quantity);
}
