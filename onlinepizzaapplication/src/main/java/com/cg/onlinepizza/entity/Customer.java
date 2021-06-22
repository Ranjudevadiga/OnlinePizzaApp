package com.cg.onlinepizza.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


/**
 * 
 * @author Shashank
 *
 */
@Entity
public class Customer {
	@Id
	@GeneratedValue
	@Column
	private int customerId;
	@Column(name="customer_Name")
	@NotBlank(message="Name cannot not be empty")
	private String customerName;
	@Column(name="role")
	@NotBlank(message="Role cannot not be empty")
	private String role;
	@Column(name="customer_Mobile")
	@Digits(integer=10,fraction=0)
	private Long customerMobile;
	@Column(name="customer_Email")
	@Email(message="Must be an email")
	private String customerEmail;
	@Column(name="customer_Address")
	@NotBlank(message="Address cannot not be empty")
	private String customerAddress;
	@Column(name="Password")
	@NotBlank(message="Password cannot not be empty")
	private String password;
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<CustomerOrder> order;
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<PizzaOrder> pizzaOrder;
	
	
	/*public List<CustomerOrder> getOrder() {
		return order;
	}
	public void setOrder(List<CustomerOrder> order) {
		this.order = order;
	}*/
	public Customer() {
		super();
	}
	
	/**
	 * 
	 * @param customerId
	 * @param customerName
	 * @param customerMobile
	 * @param customerEmail
	 * @param customerAddress
	 * @param password
	 * @param role
	 */
	public Customer(int customerId, String customerName, Long customerMobile, String customerEmail,
			String customerAddress, String password,String role) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerMobile = customerMobile;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.password = password;
		this.role=role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
