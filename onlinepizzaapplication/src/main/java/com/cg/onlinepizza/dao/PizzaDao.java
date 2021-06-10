package com.cg.onlinepizza.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.onlinepizza.entity.Pizza;
@Repository
public interface PizzaDao extends JpaRepository<Pizza, Integer>{
	@Query(value="from Pizza pizza where pizza.pizzaId=?1")
	public Pizza getPizzaId(int id);

}
