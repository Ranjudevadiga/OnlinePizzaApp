package com.cg.onlinepizza.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.onlinepizza.entity.Customer;

/**
 * 
 * @author Shashank
 *
 */

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{
	@Query(value="from Customer customer where customer.customerId=?1")
	public Customer getCustomerId(int id);
	
	@Query(value="from Customer login where login.customerEmail=?1 and login.password=?2")
	public Customer validate(String email,String password);
}
