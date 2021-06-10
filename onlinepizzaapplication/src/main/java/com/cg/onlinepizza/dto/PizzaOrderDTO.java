package com.cg.onlinepizza.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

public class PizzaOrderDTO {
	
	private int bookingOrderId;
	private LocalDate dateOfOrder;
	private double totalCost;
	private String pizzaSize;
	@Range(min=1,message="Quantity cannot be empty")
	private int pizzaQuantity;
	@NotBlank(message="Transaction Mode could not be empty")

	private String transactionMode;
	private String couponName;
	private int pizzaId;
	private int customerId;
	public PizzaOrderDTO() {
		super();
		}
	public PizzaOrderDTO(int bookingOrderId, LocalDate dateOfOrder, double totalCost, String pizzaSize,
			int pizzaQuantity, String transactionMode, String couponName, int pizzaId,int customerId) {
		super();
		this.bookingOrderId = bookingOrderId;
		this.dateOfOrder = dateOfOrder;
		this.totalCost = totalCost;
		this.pizzaSize = pizzaSize;
		this.pizzaQuantity = pizzaQuantity;
		this.transactionMode = transactionMode;
		this.couponName = couponName;
		this.pizzaId = pizzaId;
		this.customerId=customerId;
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
	
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public int getPizzaId() {
		return pizzaId;
	}
	public void setPizzaId(int pizzaId) {
		this.pizzaId = pizzaId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

}
