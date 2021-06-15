package com.cg.onlinepizza.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 * 
 * @author Mahalasa
 *
 */
@Entity
public class Coupon {
	@Id
	@GeneratedValue
	private int couponId;
	@Column
	private String couponName;
	@Column
	private int  discountValue;
	@Column
	private String couponDescription;
	@ManyToOne
	@JoinColumn(name="pizzaId",referencedColumnName = "pizzaId")
	private Pizza pizza;
	@OneToMany(mappedBy = "coupon",cascade = CascadeType.ALL)
	private List<PizzaOrder> pizzaorder;
	/*public List<PizzaOrder> getPizzaorder() {
		return pizzaorder;
	}*/
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
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public Coupon() {
		super();
	}
	
	/**
	 * 
	 * @param couponId
	 * @param couponName
	 * @param discountValue
	 * @param couponDescription
	 * @param pizza
	 */
	
	public Coupon(int couponId, String couponName, int discountValue, String couponDescription, Pizza pizza) {
		super();
		this.couponId = couponId;
		this.couponName = couponName;
		this.discountValue = discountValue;
		this.couponDescription = couponDescription;
		this.pizza = pizza;
	}
	
	

}
