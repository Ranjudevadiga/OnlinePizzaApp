package com.cg.onlinepizza.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PizzaOrder {
	@Id
	@GeneratedValue
	private int bookingOrderId;
	private LocalDate dateOfOrder;
	private double totalCost;
	private Pizza pizza;
	private Order order;
	private Coupon coupon;
	public PizzaOrder() {
		super();
	}
	public PizzaOrder(int bookingOrderId, LocalDate dateOfOrder, double totalCost, Pizza pizza, Order order,
			Coupon coupon) {
		super();
		this.bookingOrderId = bookingOrderId;
		this.dateOfOrder = dateOfOrder;
		this.totalCost = totalCost;
		this.pizza = pizza;
		this.order = order;
		this.coupon = coupon;
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
}
