package com.gkoo.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author sanghuncho
 *
 * @since  08.11.2019
 *
 */
public class CustomerStatus {
	private String customerId;
	private int insuranceAmount;
	private int depositeAmount;
	private int pointAmount;
	
	
	public CustomerStatus(String customerId, int insuranceAmount, int depositeAmount, int pointAmount){
		this.customerId = customerId;
		this.insuranceAmount = insuranceAmount;
		this.depositeAmount = depositeAmount;
		this.pointAmount = pointAmount;
	}
	
	public CustomerStatus() {}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String id) {
		customerId = id;
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