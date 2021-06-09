package com.cg.onlinepizza.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



@Entity
public class CustomerOrder {
	@Id
	@GeneratedValue
	private int orderId;
	private String pizzaName;
	private String pizzaDescription;
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer  customer;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="bookingOrderId")
	private PizzaOrder pizzaOrder;
	public CustomerOrder(int orderId, String pizzaName, String pizzaDescription, Customer customer,
			PizzaOrder pizzaOrder) {
		super();
		this.orderId = orderId;
		this.pizzaName = pizzaName;
		this.pizzaDescription = pizzaDescription;
		this.customer = customer;
		this.pizzaOrder = pizzaOrder;
	}
	public PizzaOrder getPizzaOrder() {
		return pizzaOrder;
	}
	public void setPizzaOrder(PizzaOrder pizzaOrder) {
		this.pizzaOrder = pizzaOrder;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getPizzaName() {
		return pizzaName;
	}
	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}
	public String getOrderDescription() {
		return pizzaDescription;
	}
	public void setOrderDescription(String pizzaDescription) {
		this.pizzaDescription = pizzaDescription;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public CustomerOrder() {
		super();
	}
	
}