package com.car.domain.dto;

import java.io.Serializable;
import java.util.Date;

public class RentalTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date rentedUntil;
	private Double amount;
	private String payment;
	private String car;

	public Date getRentedUntil() {
		return this.rentedUntil;
	}

	public void setRentedUntil(Date rentedUntil) {
		this.rentedUntil = rentedUntil;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPayment() {
		return this.payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getCar() {
		return this.car;
	}

	public void setCar(String car) {
		this.car = car;
	}
}
