package com.cg.onlinepizza.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.onlinepizza.entity.Customer;


@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>{
	@Query(value="from Customer customer where customer.customerId=?1")
	public Customer getCustomerId(int id);
}
