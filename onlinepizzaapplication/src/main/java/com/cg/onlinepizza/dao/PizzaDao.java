package com.cg.onlinepizza.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.onlinepizza.entity.Pizza;
@Repository
public interface PizzaDao extends JpaRepository<Pizza, Integer>{
	@Query(value="from Pizza pizza where pizza.pizzaId=?1")
	public Pizza getPizzaId(int id);
	
	@Query(value="from Pizza pizza where pizza.pizzaCost between ?1 and ?2")
	public List<Pizza> sortByPrice(double min,double max);

	@Query(value="from Pizza pizza where pizza.pizzaType=?1")
	public List<Pizza> sortByType(String type);

}
