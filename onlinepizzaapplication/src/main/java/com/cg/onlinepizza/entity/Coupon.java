package com.cg.onlinepizza.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Coupon {
	@Id
	@GeneratedValue
	private int couponId;
	private String couponName;
	private String couponType;
	private String couponDescription;
	@ManyToOne
	@JoinColumn(name="pizzaId")
	private Pizza pizza;
	@OneToMany(mappedBy = "coupon",cascade = CascadeType.ALL)
	private List<PizzaOrder> pizzaorder;
	public List<PizzaOrder> getPizzaorder() {
		return pizzaorder;
	}
	public void setPizzaorder(List<PizzaOrder> pizzaorder) {
		this.pizzaorder = pizzaorder;
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
	public String getCouponType() {
		return couponType;
	}
	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}
	public String getCouponDescription() {
		return couponDescription;
	}
	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}
	public Coupon() {
		super();
	}
	
	public Coupon(int couponId, String couponName, String couponType, String couponDescription, Pizza pizza) {
		super();
		this.couponId = couponId;
		this.couponName = couponName;
		this.couponType = couponType;
		this.couponDescription = couponDescription;
		this.pizza = pizza;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	

}
