package com.cg.onlinepizza.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cg.onlinepizza.entity.PizzaOrder;
@Repository
public interface PizzaOrderDao extends JpaRepository<PizzaOrder, Integer>{
	@Query(value="from PizzaOrder order where order.bookingOrderId=?1")
	public PizzaOrder getPizzaOrderById(int id);
	
	@Query(value="from PizzaOrder pizzaOrder where pizzaOrder.customer.customerId=?1")
	public List<PizzaOrder> getPizzaOrderByCustomerId(int id);
	
	
}
