package com.car.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.car.business.remote.RentalService;
import com.car.domain.CreditCard;
import com.car.domain.Invoice;

@ManagedBean
@ViewScoped
public class PaymentHandler {

	@ManagedProperty(value = "#{rentalHandler}")
	private RentalHandler rentalHandler;

	@ManagedProperty(value = "#{userHandler.rentalService}")
	private RentalService rentalService;
	
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

	/**
	 * Setter for RentalHandler, required for dependency injection.
	 */
	public void setRentalHandler(RentalHandler rentalHandler) {
		this.rentalHandler = rentalHandler;
	}

	/**
	 * Sets invoice as payment type for current rental.
	 * Redirects to the next step.
	 */
	public String confirmInvoice() {
		return this.rentalHandler.setPayment(new Invoice());
	}

	/**
	 * Sets credit card as payment type for current rental.
	 * Redirects to the next step. 
	 */
	public String confirmCreditCard() {
		return this.rentalHandler.setPayment(new CreditCard(this.owner, this.number));
	}

	public void setRentalService(RentalService rentalService) {
		this.rentalService = rentalService;
	}
}
