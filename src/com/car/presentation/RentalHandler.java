package com.car.presentation;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.car.business.remote.RentalService;
import com.car.domain.Car;
import com.car.domain.Payment;
import com.car.domain.Rental;

@ManagedBean
@SessionScoped
public class RentalHandler {

	@EJB
	private RentalService rentalService;

	private Rental rental;

	@PostConstruct
	private void reset() {
		this.rental = new Rental();
	}

	public Rental getRental() {
		return rental;
	}

	public String setCar(Car car, Integer duration) {
		this.rental = this.rentalService.commitCar(this.rental, car, duration);

		return "payment";
	}

	public String setPayment(Payment payment) {
		this.rental = this.rentalService.commitPayment(this.rental, payment);

		return "checkout";
	}

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

	public String cancelRental() {
		this.reset();

		return "index";
	}
}
