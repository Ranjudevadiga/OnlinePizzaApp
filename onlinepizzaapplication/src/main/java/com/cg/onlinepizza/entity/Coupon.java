package com.cg.onlinepizza.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Coupon {
	@Id
	@GeneratedValue
	private int coupanId;
	private String coupanName;
	private String coupanType;
	private String coupanDescription;
	public int getCoupanId() {
		return coupanId;
	}
	public void setCoupanId(int coupanId) {
		this.coupanId = coupanId;
	}
	public String getCoupanName() {
		return coupanName;
	}
	public void setCoupanName(String coupanName) {
		this.coupanName = coupanName;
	}
	public String getCoupanType() {
		return coupanType;
	}
	public void setCoupanType(String coupanType) {
		this.coupanType = coupanType;
	}
	public String getCoupanDescription() {
		return coupanDescription;
	}
	public void setCoupanDescription(String coupanDescription) {
		this.coupanDescription = coupanDescription;
	}
	public Coupon(int coupanId, String coupanName, String coupanType, String coupanDescription) {
		super();
		this.coupanId = coupanId;
		this.coupanName = coupanName;
		this.coupanType = coupanType;
		this.coupanDescription = coupanDescription;
	}
	public Coupon() {
		super();
	}
	

}
