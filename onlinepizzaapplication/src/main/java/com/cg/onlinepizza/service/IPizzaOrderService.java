package com.cg.onlinepizza.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.cg.onlinepizza.dto.PizzaOrderDTO;
import com.cg.onlinepizza.entity.Pizza;
import com.cg.onlinepizza.entity.PizzaOrder;


public interface IPizzaOrderService {
	PizzaOrder bookPizzaOrder(PizzaOrderDTO orderDto);

	PizzaOrder updatePizzaOrder(PizzaOrderDTO pizzaOrderDto);

	List<PizzaOrder> cancelPizzaOrder(int orderId);

	PizzaOrder viewPizzaOrder(int orderId);
	
	List<PizzaOrder> viewOrderList(int customerId);
	
	double caluculateTotal(int pizzaId,String couponName,double price, int quantity);
	
	List<Pizza> viewPizzaByPrice(double min,double max);
}
