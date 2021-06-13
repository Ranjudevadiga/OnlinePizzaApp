package com.cg.onlinepizza.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CouponDto {
	private int couponId;
	@NotBlank(message="coupon name cant be empty")
	private String couponName;
	@NotNull(message="discount value cant be empty")
	private int  discountValue;
	@NotBlank(message="descrption cant be empty")
	private String couponDescription;
	@NotNull(message="pizzaid cant be empty")
	private int pizzaId; 
	
	public CouponDto() {
		super();
	}
	public CouponDto(int couponId, String couponName, int discountValue, String couponDescription, int pizzaId) {
		super();
		this.couponId = couponId;
		this.couponName = couponName;
		this.discountValue = discountValue;
		this.couponDescription = couponDescription;
		this.pizzaId = pizzaId;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public int getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(int discountValue) {
		this.discountValue = discountValue;
	}
	public String getCouponDescription() {
		return couponDescription;
	}
	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}
	public int getPizzaId() {
		return pizzaId;
	}
	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}
	
	

}
