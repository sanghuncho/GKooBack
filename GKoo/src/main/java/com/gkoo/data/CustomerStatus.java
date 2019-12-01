package com.gkoo.data;

/**
 *
 * @author sanghuncho
 *
 * @since  08.11.2019
 *
 */
public class CustomerStatus {
	private String userid;
	private int insuranceAmount;
	private int depositeAmount;
	private int pointAmount;
	
	public CustomerStatus(String userid, int insuranceAmount, int depositeAmount, int pointAmount){
		this.userid = userid;
		this.insuranceAmount = insuranceAmount;
		this.depositeAmount = depositeAmount;
		this.pointAmount = pointAmount;
	}
	
	public CustomerStatus() {}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
	    this.userid = userid;
	}

	public int getInsuranceAmount() {
		return insuranceAmount;
	}
	
	public void setInsuranceAmount(int insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public int getDepositeAmount() {
		return this.depositeAmount;
	}
	
	public void setDepositeAmount(int depositeAmount) {
		this.depositeAmount = depositeAmount;
	}

	public int getPointAmount() {
		return this.pointAmount;
	}
	
	public void setPointAmount(int pointAmount) {
		this.pointAmount = pointAmount;
	}
}