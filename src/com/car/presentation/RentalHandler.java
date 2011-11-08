package com.car.presentation;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.car.business.remote.RentalService;
import com.car.domain.Car;
import com.car.domain.Payment;
import com.car.domain.Rental;

@ManagedBean
@SessionScoped
public class RentalHandler {

	@ManagedProperty(value = "#{userHandler.rentalService}")
	private RentalService rentalService;
	
	private Rental rental;

	@PostConstruct
	private void reset() {
		this.rental = new Rental();
	}

	public void setRentalService(RentalService rentalService) {
		this.rentalService = rentalService;
	}
	
	public Rental getRental() {
		return rental;
	}

	/**
	 * First step in the rental workflow: Fills in car and duration into current rental.
	 * Redirects to the next part.
	 * 
	 * @param car
	 * @param duration
	 */
	public String setCar(Car car, Integer duration) {
		this.rental = this.rentalService.commitCar(this.rental, car, duration);

		return "payment";
	}

	/**
	 * Second step in the rental workflow. Fills in payment type into current rental.
	 * Redirects to the next part.
	 * 
	 * @param payment
	 */
	public String setPayment(Payment payment) {
		this.rental = this.rentalService.commitPayment(this.rental, payment);

		return "checkout";
	}

	/**
	 * Last step in the the rental workflow. Persists current rental.
	 * Redirects to index page if rental successful.
	 */
	public String confirmRental() {
		try {
			this.rentalService.commitRental(this.rental);

			this.reset();

			FacesContext.getCurrentInstance().addMessage("rental", new FacesMessage("Car successfully rented. Thank you."));

			return "index";
		} catch (EJBException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Car currently rented!"));

			return "checkout";
		}
	}

	/**
	 * Aborts current rental and redirects to index page.
	 */
	public String cancelRental() {
		this.reset();

		return "index";
	}
}
