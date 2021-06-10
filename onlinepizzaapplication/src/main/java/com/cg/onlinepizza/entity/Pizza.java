package com.cg.onlinepizza.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pizza {
	@Id
	@GeneratedValue
	@Column
	private int pizzaId;
	@Column(name="Pizza_Type")
	private String pizzaType;
	private String pizzaSize;
	@Column(name="Pizza_Name")
	private String pizzaName;
	@Column(name="Pizza_Description")
	private String pizzaDescription;
	@Column(name="Pizza_Cost")
	private double pizzaCost;
	@OneToMany(mappedBy = "pizza")
	private List<Coupon> coupon;
	@OneToMany(mappedBy = "pizza",cascade=CascadeType.ALL)
	private List<PizzaOrder> pizzaOrder;
	/*public List<PizzaOrder> getPizzaOrder() {
		return pizzaOrder;
	}*/
	public void setPizzaOrder(List<PizzaOrder> pizzaOrder) {
		this.pizzaOrder = pizzaOrder;
	}
	/*public List<Coupon> getCoupon() {
		return coupon;
	}*/
	public void setCoupon(List<Coupon> coupon) {
		this.coupon = coupon;
	}
	public int getPizzaId() {
		return pizzaId;
	}
	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}
	public String getPizzaType() {
		return pizzaType;
	}
	public void setPizzaType(String pizzaType) {
		this.pizzaType = pizzaType;
	}
	public String getPizzaName() {
		return pizzaName;
	}
	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}
	public String getPizzaDescription() {
		return pizzaDescription;
	}
	public void setPizzaDescription(String pizzaDescription) {
		this.pizzaDescription = pizzaDescription;
	}
	public double getPizzaCost() {
		return pizzaCost;
	}
	public void setPizzaCost(double pizzaCost) {
		this.pizzaCost = pizzaCost;
	}
	public String getPizzaSize() {
		return pizzaSize;
	}
	public void setPizzaSize(String pizzaSize) {
		this.pizzaSize = pizzaSize;
	}
	public Pizza(int pizzaId, String pizzaType, String pizzaName, String pizzaDescription, double pizzaCost,String pizzaSize) {
		super();
		this.pizzaId = pizzaId;
		this.pizzaType = pizzaType;
		this.pizzaName = pizzaName;
		this.pizzaDescription = pizzaDescription;
		this.pizzaCost = pizzaCost;
		this.pizzaSize=pizzaSize;
	}
	public Pizza() {
		super();
	}

}
