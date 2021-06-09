package com.cg.onlinepizza.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order {
	@Id
	@GeneratedValue
	
	private int orderId;
	private String orderType;
	private String orderDescription;
	private int size;
	private int quantity;
	private String transactionMode;
	
	private int customerId;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public Order(int orderId, String orderType, String orderDescription, int size, int quantity, String transactionMode,
			int customerId) {
		super();
		this.orderId = orderId;
		this.orderType = orderType;
		this.orderDescription = orderDescription;
		this.size = size;
		this.quantity = quantity;
		this.transactionMode = transactionMode;
		this.customerId = customerId;
	}
	public Order() {
		super();
	}

}
