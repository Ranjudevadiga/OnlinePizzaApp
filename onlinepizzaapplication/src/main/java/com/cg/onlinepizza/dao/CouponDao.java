package com.cg.onlinepizza.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.onlinepizza.entity.Coupon;
@Repository
public interface CouponDao extends JpaRepository<Coupon, Integer> {
	@Query(value="from Coupon coupon where coupon.couponName=?1")
	public Coupon getByCouponName(String name);
	
	@Query(value="select count(coupon) from Coupon coupon where coupon.couponName=?1")

	public int checkExists(String name);

}
