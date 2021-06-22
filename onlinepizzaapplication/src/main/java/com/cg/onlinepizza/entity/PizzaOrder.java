package com.cg.onlinepizza.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;


/**
 * 
 * @author Ranjith
 *
 */
@Entity
public class PizzaOrder {
	@Id
	@GeneratedValue
	private int bookingOrderId;
	private LocalDate dateOfOrder;
	private double totalCost;
	private String pizzaSize;
	private String status;
	private int pizzaQuantity;
	private String transactionMode;

	
	
	public PizzaOrder(int bookingOrderId, LocalDate dateOfOrder, double totalCost, String pizzaSize, int pizzaQuantity,
			String transactionMode, Pizza pizza, Coupon coupon,Customer customer,String status) {
		super();
		this.bookingOrderId = bookingOrderId;
		this.dateOfOrder = dateOfOrder;
		this.totalCost = totalCost;
		this.pizzaSize = pizzaSize;
		this.pizzaQuantity = pizzaQuantity;
		this.transactionMode = transactionMode;
		this.pizza = pizza;
		this.coupon = coupon;
		this.customer=customer;
		this.status=status;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPizzaSize() {
		return pizzaSize;
	}
	public void setPizzaSize(String pizzaSize) {
		this.pizzaSize = pizzaSize;
	}
	public int getPizzaQuantity() {
		return pizzaQuantity;
	}
	public void setPizzaQuantity(int pizzaQuantity) {
		this.pizzaQuantity = pizzaQuantity;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	@ManyToOne
	@JoinColumn(name="pizzaId",referencedColumnName = "pizzaId")
	private Pizza pizza;
	
	@ManyToOne
	@JoinColumn(name="couponId",referencedColumnName = "couponId")
	private Coupon coupon;
	
	@ManyToOne
	@JoinColumn(name="customerId",referencedColumnName = "customerId")
	private Customer customer;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public PizzaOrder() {
		super();
	}
	
	public int getBookingOrderId() {
		return bookingOrderId;
	}
	public void setBookingOrderId(int bookingOrderId) {
		this.bookingOrderId = bookingOrderId;
	}
	public LocalDate getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(LocalDate dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
}
