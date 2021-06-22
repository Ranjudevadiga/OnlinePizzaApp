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
	
	
	@ExceptionHandler(value=OrderUpdateException.class)
	public ResponseEntity<Object> exception( OrderUpdateException exception){
		return new ResponseEntity<>("Sorry You cant Update the Older Order",HttpStatus.NOT_FOUND);
		}
	
	
	@ExceptionHandler(value=PriceException.class)
	public ResponseEntity<Object> exception( PriceException exception){
		return new ResponseEntity<>("Minimum price should be less than maximum price",HttpStatus.NOT_FOUND);
		}
	
	
	@ExceptionHandler(value=PizzaNotFoundInRangeException.class)
	public ResponseEntity<Object> exception( PizzaNotFoundInRangeException exception){
		return new ResponseEntity<>("No Pizza found in selected price range",HttpStatus.NOT_FOUND);
		}
	
	
	
	@ExceptionHandler(value=IDNotFoundException.class)
	public ResponseEntity<Object> exception( IDNotFoundException exception){
		return new ResponseEntity<>("Customer ID not found",HttpStatus.NOT_FOUND);
		}
	
	
	@ExceptionHandler(value=ListIsEmptyException.class)
	public ResponseEntity<Object> exception( ListIsEmptyException exception){
		return new ResponseEntity<>("List is empty",HttpStatus.NOT_FOUND);
		}
	
	
	@ExceptionHandler(value=PizzaIdNotFoundException.class)
	public ResponseEntity<Object> exception( PizzaIdNotFoundException exception){
		return new ResponseEntity<>("Pizza ID not found",HttpStatus.NOT_FOUND);
		}
	
	
	@ExceptionHandler(value=EmailAlreadyExistsException.class)
	public ResponseEntity<Object> exception( EmailAlreadyExistsException exception){
		return new ResponseEntity<>("Email already exists",HttpStatus.NOT_FOUND);
		}
	
	
	@ExceptionHandler(value=PizzaTypeNotFoundException.class)
	public ResponseEntity<Object> exception( PizzaTypeNotFoundException exception){
		return new ResponseEntity<>("No PizzaFound in selected type",HttpStatus.NOT_FOUND);
		}
	
	
	@ExceptionHandler(value=InvalidUserException.class)
	public ResponseEntity<Object> exception( InvalidUserException exception){
		return new ResponseEntity<>("Invalis credentials",HttpStatus.NOT_FOUND);
		}
	@ExceptionHandler(value=OrderCancelException.class)
	public ResponseEntity<Object> exception( OrderCancelException exception){
		return new ResponseEntity<>("Sorry You cant Cancel the Older Order",HttpStatus.NOT_FOUND);
		}

}
