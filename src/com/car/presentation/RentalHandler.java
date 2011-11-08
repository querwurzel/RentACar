package com.car.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.car.business.remote.RentalService;
import com.car.domain.dto.RentalTO;

@ManagedBean
@ViewScoped
public class RentalHandler {

	@ManagedProperty(value = "#{userHandler.rentalService}")
	private RentalService rentalService;

	public RentalTO getRental() {
		return this.rentalService.getCurrentRental();
	}

	/**
	 * Setter for RentalService, required for dependency injection.
	 */
	public void setRentalService(RentalService rentalService) {
		this.rentalService = rentalService;
	}

	/**
	 * Confirms rental to rentalService. Last step in the the rental workflow.
	 * Redirects to index page.
	 */
	public String confirmRental() {
		this.rentalService.commitRental();

		FacesContext.getCurrentInstance().addMessage("rental", new FacesMessage("Car successfully rented. Thank you."));

		return "index";
	}

	/**
	 * Aborts current rental and redirects to index page.
	 */
	public String cancelRental() {
		this.rentalService.cancelRental();

		return "index";
	}
}
