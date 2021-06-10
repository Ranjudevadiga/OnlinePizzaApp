package com.cg.onlinepizza.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Customer {
	@Id
	@GeneratedValue
	@Column
	private int customerId;
	@Column(name="customer_Name")
	private String customerName;
	@Column(name="customer_Mobile")
	private Long customerMobile;
	@Column(name="customer_Email")
	private String customerEmail;
	@Column(name="customer_Address")
	private String customerAddress;
	@Column(name="Username")
	private String userName;
	@Column(name="Password")
	private String password;
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<CustomerOrder> order;
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<PizzaOrder> pizzaOrder;
	
	public List<CustomerOrder> getOrder() {
		return order;
	}
	public void setOrder(List<CustomerOrder> order) {
		this.order = order;
	}
	public Customer() {
		super();
	}
	public Customer(int customerId, String customerName, Long customerMobile, String customerEmail,
			String customerAddress, String userName, String password) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerMobile = customerMobile;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.userName = userName;
		this.password = password;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(Long customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
