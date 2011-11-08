package com.car.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.car.business.remote.RentalService;
import com.car.domain.dto.CreditCardTO;
import com.car.domain.dto.InvoiceTO;

@ManagedBean
@ViewScoped
public class PaymentHandler {

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
	 * Setter for RentalService, required for dependency injection.
	 */
	public void setRentalService(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	/**
	 * Sets invoice as payment type for current rental.
	 * Redirects to the next step.
	 */
	public String confirmInvoice() {
		this.rentalService.commitInvoice( new InvoiceTO() );

		return "checkout";
	}

	/**
	 * Sets credit card as payment type for current rental.
	 * Redirects to the next step. 
	 */
	public String confirmCreditCard() {
		this.rentalService.commitCreditCard( new CreditCardTO(this.owner, this.number) );

		return "checkout";
	}
}
