package com.cg.onlinepizza.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.cg.onlinepizza.dto.CouponDto;
import com.cg.onlinepizza.entity.Coupon;
import com.cg.onlinepizza.entity.Customer;
import com.cg.onlinepizza.entity.Pizza;
import com.cg.onlinepizza.entity.PizzaOrder;
import com.cg.onlinepizza.service.AdminService;
import com.cg.onlinepizza.service.PizzaOrderService;
import com.cg.onlinepizza.utils.CouponDoesNotExistsException;
import com.cg.onlinepizza.utils.ListEmptyException;
import com.cg.onlinepizza.utils.ListIsEmptyException;
import com.cg.onlinepizza.utils.PizzaIdNotFoundException;

@CrossOrigin(origins = "http://localhost:3002")
@RestController
@RequestMapping("/Admin")
public class AdminController {
	@Autowired
	AdminService couponService;
	@Autowired
	CouponDao couponDao;
	
	 @Autowired
		CustomerDao customerDao;
	    @Autowired
	   	PizzaDao pizzaDao;
	    @Autowired
	  	AdminService adminservice;
	    @Autowired
	    PizzaOrderService pizzaOrderService;
	@PostMapping("/addCoupon")
	public ResponseEntity<String> addCoupon(@Valid @RequestBody CouponDto couponDto){
		couponService.addCoupans(couponDto);
		return new ResponseEntity<>("coupon inserted",HttpStatus.OK);
	}
	
	@GetMapping("/viewCoupons")
	public ResponseEntity<List <Coupon>> viewCoupons(){
		List<Coupon> couponList = couponService.viewCoupons();
		if(couponList.isEmpty())
			throw new ListEmptyException();
		else	
			return new ResponseEntity<>(couponList, HttpStatus.OK);
	}
	@GetMapping("/viewOrders")
	public ResponseEntity<List <PizzaOrder>> viewOrders(){
		List<PizzaOrder> order = adminservice.viewPizzaOrders();
		if(order.isEmpty())
			throw new ListEmptyException();
		else	
			return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecoupons")
	public ResponseEntity<String> deleteCoupons(@RequestParam int id){
		if(couponDao.existsById(id)) {
		couponService.deleteCoupans(id);
		return new ResponseEntity<>("Coupon Deleted", HttpStatus.OK);
		}
		else
			throw new CouponDoesNotExistsException();
	}
	
	@PutMapping("/editcoupon") 
	public ResponseEntity<String> editCoupons(@Valid @RequestParam int couponId,@RequestBody CouponDto couponDto){
		if(couponDao.existsById(couponId)) {
		couponService.editCoupans(couponDto, couponId);
		return new ResponseEntity<>("Successfully updated", HttpStatus.OK); 
	}
		else
			throw new CouponDoesNotExistsException();

}
	 @PostMapping("/addpizza")	
		public ResponseEntity<String> addPizza(@Valid @RequestBody Pizza pizza){
	    	adminservice.addPizza(pizza);
			return new ResponseEntity<String>("Pizza Added",HttpStatus.OK);
		}
	    
		@GetMapping("/getallpizza")
		public ResponseEntity<List<Pizza>> getAllPizza(){
			List<Pizza> pizzaList=adminservice.viewPizzaList();
			return new ResponseEntity<List<Pizza>>(pizzaList,HttpStatus.OK);
		}
		
		@DeleteMapping("/deletepizza/{pizzaId}")
		public ResponseEntity<String> deletePizza(@PathVariable("pizzaId")int pizza_id) throws PizzaIdNotFoundException{
			if(!pizzaDao.findById(pizza_id).isPresent()) {
				throw new PizzaIdNotFoundException();
			}
			String str=adminservice.deletePizza(pizza_id);
			return new ResponseEntity<String>(str,HttpStatus.OK);
		}
		
	    
		@GetMapping("/getallcustomer")
	    public ResponseEntity<List<Customer>> viewCustomer()  throws ListIsEmptyException{
	    	List<Customer> customers = customerDao.findAll();
	    	if(customers.isEmpty()) {
	    		throw new ListIsEmptyException();
	    	}
	    	else
	    		return new ResponseEntity<List<Customer>>(adminservice.viewCustomer(),HttpStatus.OK);
	    }
	    
	    
	    @PutMapping("/updatepizza/{pizzaId}")
		public ResponseEntity<String> updatePizza(@Valid @PathVariable int pizzaId,@RequestBody Pizza pizza) throws PizzaIdNotFoundException{
	    	if(pizzaDao.existsById(pizza.getPizzaId())) {
	    		String str=adminservice.updatePizza(pizzaId, pizza);
	    		return new ResponseEntity<String>(str,HttpStatus.OK);
	    	}
	    	else throw new PizzaIdNotFoundException();
	    }
	    
	    @PutMapping("/cancelOrder/{id}")
		public ResponseEntity<PizzaOrder> cancelOrder(@Valid @PathVariable int id,@RequestBody Pizza pizza) {
	    	PizzaOrder order=adminservice.cancelOrder(id);
	    		return new ResponseEntity<PizzaOrder>(order,HttpStatus.OK);
	    }
	    
	    
	    @PutMapping("/acceptOrder/{id}")
		public ResponseEntity<PizzaOrder> acceptOrder(@Valid @PathVariable int id,@RequestBody Pizza pizza) {
	    	PizzaOrder order=adminservice.acceptOrder(id);
	    		return new ResponseEntity<PizzaOrder>(order,HttpStatus.OK);
	    }
	    
	    @PutMapping("/deliver/{id}")
		public ResponseEntity<PizzaOrder> deliverOrder(@Valid @PathVariable int id,@RequestBody Pizza pizza) {
	    	PizzaOrder order=adminservice.delivered(id);
	    		return new ResponseEntity<PizzaOrder>(order,HttpStatus.OK);
	    }
	    
	   
	    
	    
}
