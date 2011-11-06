package com.car.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.car.domain.CreditCard;
import com.car.domain.Invoice;

@ManagedBean
@ViewScoped
public class PaymentHandler {

	@ManagedProperty(value = "#{rentalHandler}")
	private RentalHandler rentalHandler;

	// credit card fields
	private String owner;
	private Long number;

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Long getNumber() {
		return this.number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public void setRentalHandler(RentalHandler rentalHandler) {
		this.rentalHandler = rentalHandler;
	}

	public String confirmInvoice() {
		return this.rentalHandler.setPayment(new Invoice());
	}

	public String confirmCreditCard() {
		return this.rentalHandler.setPayment(new CreditCard(this.owner, this.number));
	}
}
