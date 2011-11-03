package com.car.business;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.car.business.remote.CarService;
import com.car.business.remote.CustomerService;
import com.car.business.remote.RentalService;
import com.car.domain.Car;
import com.car.domain.Customer.CustomerRole;
import com.car.domain.Payment;
import com.car.domain.Rental;

/**
 * Session Bean implementation class SimpleOrderService
 */
@Stateful
@RolesAllowed(CustomerRole.CONSUMER)
public class SimpleRentalService implements RentalService {

	@EJB
	private CustomerService customerService;
	
	@EJB
	private CarService carService;

	@PersistenceContext
	private EntityManager manager;
	
	private Rental rental;
	
	@PostConstruct
	private void resetRental() {
		this.rental = new Rental();
		this.rental.setCustomer( this.customerService.getCurrentCustomer() );
	}
	
	public void selectCar(Car car, Integer duration) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, duration);
		
		this.rental.setCar(car);
		this.rental.setAmount( car.getDailyFee() * duration );
		this.rental.setCurrency( car.getCurrency() );
		this.rental.setRentedUntil( cal.getTime() );
	}

	public void selectPayment(Payment payment) {
		// set payment
		
	}

	public void abortRental() {
		this.resetRental();
	}

	public void confirmRental() throws RuntimeException {
		// check car availability again
		if (this.carService.isRented( this.rental.getCar().getId() )) {
			Logger.getLogger(SimpleRentalService.class.getName()).log(Level.WARNING, "SimpleRentalService: Tried to rent a car curently rented.");
			throw new RuntimeException("Car currently rented.");
		}
		
		this.rental.setDateRented( new Date() );

		this.manager.persist(this.rental);
		
		Logger.getLogger(SimpleRentalService.class.getName()).log(Level.INFO, String.format("SimpleRentalService: Customer (email: %s) just rented Car (id: %s).", this.rental.getCustomer().getEmail(), this.rental.getCar().getId()));
	}
}
