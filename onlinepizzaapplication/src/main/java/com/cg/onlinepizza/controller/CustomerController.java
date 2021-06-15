package com.cg.onlinepizza.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlinepizza.dao.CouponDao;
import com.cg.onlinepizza.dao.CustomerDao;
import com.cg.onlinepizza.dao.PizzaDao;
import com.cg.onlinepizza.dao.PizzaOrderDao;
import com.cg.onlinepizza.dto.PizzaOrderDTO;
import com.cg.onlinepizza.entity.Coupon;
import com.cg.onlinepizza.entity.Customer;
import com.cg.onlinepizza.entity.Pizza;
import com.cg.onlinepizza.entity.PizzaOrder;
import com.cg.onlinepizza.service.PizzaOrderService;
import com.cg.onlinepizza.utils.BookingOrderIdDoesNotExists;
import com.cg.onlinepizza.utils.IDNotFoundException;
import com.cg.onlinepizza.utils.InvalidUserException;
import com.cg.onlinepizza.utils.ListEmptyException;
import com.cg.onlinepizza.utils.PizzaNotFoundException;
import com.cg.onlinepizza.utils.PizzaNotFoundInRangeException;
import com.cg.onlinepizza.utils.PizzaTypeNotFoundException;
import com.cg.onlinepizza.utils.PriceException;
import com.cg.onlinepizza.utils.EmailAlreadyExistsException;
@CrossOrigin(origins = "http://localhost:3002")
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
	
	  @Autowired
		CustomerDao customerDao;
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
		return new ResponseEntity<>("Order Updated", HttpStatus.OK);
	}
	@GetMapping("/viewCustomerOrders/{id}")
	public ResponseEntity <List<PizzaOrder>> viewCustomerOrders(@PathVariable int id) {
		List<PizzaOrder> order=pizzaOrderService.viewOrderList(id);
		if(order.size()<=0) throw new ListEmptyException();
		return new ResponseEntity<List<PizzaOrder>>(order, HttpStatus.OK);
	}
	
	@GetMapping("/viewPizzaBySorting/{min}/{max}")
	public ResponseEntity <List<Pizza>> viewPizzaByCost(@PathVariable double min,@PathVariable double max) throws PizzaNotFoundInRangeException {
		if(min<max) {
			List<Pizza> order=pizzaOrderService.viewPizzaByPrice(min, max);
			if(order.size()<=0) throw new PizzaNotFoundInRangeException();
			return new ResponseEntity<List<Pizza>>(order, HttpStatus.OK);
		}else {
			throw new PriceException();
		}
	}
	
	@GetMapping("/viewPizzaByType")
	public ResponseEntity <List<Pizza>> viewPizzaByType(@RequestBody Pizza pizza) throws PizzaTypeNotFoundException {
			List<Pizza> pizzaList=pizzaOrderService.viewPizzaByType(pizza);
			if(pizzaList.size()<=0) throw new PizzaTypeNotFoundException();
			return new ResponseEntity<List<Pizza>>(pizzaList, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/viewCoupons")
	public ResponseEntity<List <Coupon>> viewCoupons(){
		List<Coupon> couponList = pizzaOrderService.viewCoupons();
		if(couponList.isEmpty())
			throw new ListEmptyException();
		else	
			return new ResponseEntity<>(couponList, HttpStatus.OK);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> addcustomer(@Valid @RequestBody Customer customer) throws EmailAlreadyExistsException{
		List<Customer> customerlist=customerDao.findAll();
		for(Customer cust : customerlist) {
		if(cust.getCustomerEmail().equals(customer.getCustomerEmail())) {
			throw new EmailAlreadyExistsException();	
		  } 
	  }
		pizzaOrderService.addCustomer(customer);
		return new ResponseEntity<String>("customer added", HttpStatus.OK);
	}
	
	@GetMapping("/getbyid/{Id}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable Integer Id) throws IDNotFoundException{
		if(customerDao.existsById(Id)) {
				return new ResponseEntity<Customer>(pizzaOrderService.viewCustomerById(Id),HttpStatus.OK);
		}
		else
			throw new IDNotFoundException();
	}

	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("Id") Integer Id) throws IDNotFoundException{
		if(!customerDao.findById(Id).isPresent()) {
			throw new IDNotFoundException();
		}
		
		pizzaOrderService.deleteCustomer(Id);
		return new ResponseEntity<String>("deleted...", HttpStatus.OK);
	}
	
	@PutMapping("/updateCustomer")
	public ResponseEntity<String> updateCustomer(@Valid @RequestParam int customerId, @RequestBody Customer customer) throws IDNotFoundException{
		if(customerDao.existsById(customer.getCustomerId())) {
			String str=pizzaOrderService.updateCustomer(customerId,customer);
			return new ResponseEntity<String>(str,HttpStatus.OK);
		}
		else
			throw new IDNotFoundException();
	}
	
	@GetMapping("/getallpizza")
	public ResponseEntity<List<Pizza>> getAllPizza(){
		List<Pizza> pizzaList=pizzaOrderService.viewPizzaList();
		return new ResponseEntity<List<Pizza>>(pizzaList,HttpStatus.OK);
	}
	
	@PostMapping("/validate")
	public ResponseEntity<Customer> validate(@RequestBody Customer login)
	{
		if(customerDao.validate(login.getCustomerEmail(),login.getPassword())==null)
	{
		throw new InvalidUserException();
	}
		Customer customer=pizzaOrderService.validate(login.getCustomerEmail(),login.getPassword());
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@GetMapping("/viewCurrentOrders/{id}")
	public ResponseEntity <List<PizzaOrder>> viewCurrentOrders(@PathVariable int id) {
		List<PizzaOrder> order=pizzaOrderService.viewCurrentorder(id);
		if(order.size()<=0) throw new ListEmptyException();
		return new ResponseEntity<List<PizzaOrder>>(order, HttpStatus.OK);
	}

} 
