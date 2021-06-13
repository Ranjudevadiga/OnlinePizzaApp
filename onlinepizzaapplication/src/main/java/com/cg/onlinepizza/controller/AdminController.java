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
import com.cg.onlinepizza.dto.CouponDto;
import com.cg.onlinepizza.entity.Coupon;
import com.cg.onlinepizza.service.CouponService;
import com.cg.onlinepizza.utils.CouponDoesNotExistsException;
import com.cg.onlinepizza.utils.ListEmptyException;

@CrossOrigin(origins="http://localhost:8081")
@RestController
@RequestMapping("/Admin")
public class AdminController {
	@Autowired
	CouponService couponService;
	@Autowired
	CouponDao couponDao;
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
}
