package com.cg.onlinepizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.onlinepizza.dao.CouponDao;
import com.cg.onlinepizza.dao.PizzaDao;
import com.cg.onlinepizza.dto.CouponDto;
import com.cg.onlinepizza.entity.Coupon;
import com.cg.onlinepizza.entity.Pizza;

@Service
public class CouponService implements ICouponService{
	@Autowired
	CouponDao dao;
	@Autowired
	PizzaDao pizzaDao;

	@Override
	public void addCoupans(CouponDto couponDto) {
		Coupon coupon=new Coupon();
		coupon.setCouponId(couponDto.getCouponId());
		coupon.setCouponName(couponDto.getCouponName());
		coupon.setCouponDescription(couponDto.getCouponDescription());
		coupon.setDiscountValue(couponDto.getDiscountValue());
		int id= couponDto.getPizzaId();
		Pizza pizza = pizzaDao.getPizzaId(id);
		coupon.setPizza(pizza);
		dao.save(coupon);
	}

	@Override
	public String editCoupans(CouponDto couponDto, int coupanId) {
		if(dao.existsById(coupanId)){
			Coupon coupon=new Coupon();
			coupon.setCouponName(couponDto.getCouponName());
			coupon.setCouponDescription(couponDto.getCouponDescription());
			coupon.setDiscountValue(couponDto.getDiscountValue());
			int id= couponDto.getPizzaId();
			Pizza pizza = pizzaDao.getPizzaId(id);
			coupon.setPizza(pizza);
			dao.save(coupon);
			return "coupan updated successfully";
		}
		else
			return null;
	}

	@Override
	public String deleteCoupans(int coupanId) {
		if(dao.existsById(coupanId)) {
			dao.deleteById(coupanId);
		return "Coupon Deleted";
	}
		else
			return null;
	}

	@Override
	public List<Coupon> viewCoupons() {
		List<Coupon> couponList =dao.findAll();
		if(couponList.isEmpty()) 
			return null; 
		else 
			return couponList;
	
	}

}
