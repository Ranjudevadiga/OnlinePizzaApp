package com.cg.onlinepizza.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinepizza.dao.CouponDao;
import com.cg.onlinepizza.dao.PizzaDao;
import com.cg.onlinepizza.dao.PizzaOrderDao;
import com.cg.onlinepizza.dto.PizzaOrderDTO;
import com.cg.onlinepizza.entity.PizzaOrder;
import com.cg.onlinepizza.service.PizzaOrderService;
import com.cg.onlinepizza.utils.BookingOrderIdDoesNotExists;
import com.cg.onlinepizza.utils.ListEmptyException;
import com.cg.onlinepizza.utils.PizzaNotFoundException;

@RestController
@RequestMapping("/Customer")
@Validated
public class CustomerController {
	@Autowired
	PizzaOrderService pizzaOrderService;
	@Autowired
	CouponDao couponDao;
	@Autowired
	PizzaDao pizzaDao;
	@Autowired
	PizzaOrderDao orderDao; 
	@PostMapping("/order")
	public ResponseEntity<PizzaOrder> bookPizzaOrder(@Valid @RequestBody PizzaOrderDTO pizzaOrderDto){
		if(!pizzaDao.findById(pizzaOrderDto.getPizzaId()).isPresent()) {
			throw new PizzaNotFoundException();
		}else {
			PizzaOrder order=pizzaOrderService.bookPizzaOrder(pizzaOrderDto);
			return new ResponseEntity<PizzaOrder>(order, HttpStatus.OK);
		}
	}
	@DeleteMapping("/cancel/{id}")
	public ResponseEntity<String> cancelPizza(@PathVariable int id) {
		if(!orderDao.findById(id).isPresent())
		{
			throw new BookingOrderIdDoesNotExists();
		}

		pizzaOrderService.cancelPizzaOrder(id);
		return new ResponseEntity<>("Successfully cancelled", HttpStatus.OK);
	}
	@GetMapping("/viewPizzaOrder/{id}")
	public ResponseEntity <PizzaOrder> getPizzaOrderById(@PathVariable int id) {
		if(!orderDao.findById(id).isPresent())
		{
			throw new BookingOrderIdDoesNotExists();
		}
		PizzaOrder order=pizzaOrderService.viewPizzaOrder(id);
		return new ResponseEntity<PizzaOrder>(order, HttpStatus.OK);
	}
	@PutMapping("/updateOrder")
	public ResponseEntity<String> updatePolicy( @RequestBody PizzaOrderDTO orderDto) {
		pizzaOrderService.updatePizzaOrder(orderDto);
		return new ResponseEntity<>("Policy Updated", HttpStatus.OK);
	}
	@GetMapping("/viewCustomerOrders/{id}")
	public ResponseEntity <List<PizzaOrder>> viewCustomerOrders(@PathVariable int id) {
		List<PizzaOrder> order=pizzaOrderService.viewListOrder(id);
		if(order.size()<=0) throw new ListEmptyException();
		return new ResponseEntity<List<PizzaOrder>>(order, HttpStatus.OK);
	}


}
