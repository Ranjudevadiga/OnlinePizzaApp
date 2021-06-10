package com.cg.onlinepizza.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlers {
	@ExceptionHandler(value=PizzaNotFoundException.class)
	public ResponseEntity<Object> exception( PizzaNotFoundException exception){
		return new ResponseEntity<>("Pizza Not Found",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=CouponDoesNotExistsException.class)
	public ResponseEntity<Object> exception( CouponDoesNotExistsException exception){
		return new ResponseEntity<>("Coupon Not Found",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=CouponDoesNotMatchException.class)
	public ResponseEntity<Object> exception( CouponDoesNotMatchException exception){
		return new ResponseEntity<>("Coupon Does Not Match With Selected Pizza",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=BookingOrderIdDoesNotExists.class)
	public ResponseEntity<Object> exception( BookingOrderIdDoesNotExists exception){
		return new ResponseEntity<>("You have not purchased this Pizza ",HttpStatus.NOT_FOUND);
		}
	
	@ExceptionHandler(value=ListEmptyException.class)
	public ResponseEntity<Object> exception( ListEmptyException exception){
		return new ResponseEntity<>("You have not purchased anything",HttpStatus.NOT_FOUND);
		}
	@ExceptionHandler(value=BookingIdNotAvailableException.class)
	public ResponseEntity<Object> exception( BookingIdNotAvailableException exception){
		return new ResponseEntity<>("Booking Id Not Available",HttpStatus.NOT_FOUND);
		}

}
