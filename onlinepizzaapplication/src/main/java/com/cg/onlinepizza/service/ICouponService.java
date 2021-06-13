package com.cg.onlinepizza.service;

import java.util.List;

import com.cg.onlinepizza.dto.CouponDto;
import com.cg.onlinepizza.entity.Coupon;

public interface ICouponService {
	void addCoupans(CouponDto couponDto);

	String editCoupans(CouponDto couponDto, int couponId);//throws InvalidCoupanOperationException;

	String deleteCoupans(int couponId); //throws CoupanIdNotFoundException;

	List<Coupon> viewCoupons();
}
