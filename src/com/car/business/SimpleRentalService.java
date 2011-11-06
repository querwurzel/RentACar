package com.car.business;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.car.business.remote.CarService;
import com.car.business.remote.CustomerService;
import com.car.business.remote.RentalService;
import com.car.domain.Car;
import com.car.domain.Customer.CustomerRole;
import com.car.domain.Invoice;
import com.car.domain.Payment;
import com.car.domain.Rental;

/**
 * Session Bean implementation class SimpleOrderService
 */

@Stateless
@RolesAllowed(CustomerRole.CONSUMER)
public class SimpleRentalService implements RentalService {

	@EJB
	private CustomerService customerService;
	
	@EJB
	private CarService carService;

	@PersistenceContext
	private EntityManager manager;

	public Rental commitCar(Rental rental, Car car, Integer duration) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, duration);
		
		rental.setCar(car);
		rental.setAmount( car.getDailyFee() * duration );
		rental.setCurrency( car.getCurrency() );
		rental.setRentedUntil( cal.getTime() );
		
		Logger.getLogger(SimpleRentalService.class.getName()).log(Level.INFO, String.format("SimpleRentalService: Assigned car to rental: '%s'", car.getName()));
		
		return rental;
	}

	public Rental commitPayment(Rental rental, Payment payment) {
		// default date of payment
		payment.setDateOfPayment( new Date() );

		if (payment instanceof Invoice) {
			// date of payment for invoice: 14 days
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, 14);
			
			payment.setDateOfPayment( cal.getTime() );
			
			// use nanoTime() as unique invoice number
			((Invoice) payment).setInvoiceNumber( System.nanoTime() );
		}
		
		rental.setPayment(payment);
		
		Logger.getLogger(SimpleRentalService.class.getName()).log(Level.INFO, String.format("SimpleRentalService: Assigned payment to rental: '%s'", payment.toString()));

		return rental;
	}

	public Rental commitRental(Rental rental) {
		// check car availability again
		if (this.carService.isRented( rental.getCar().getId() )) {
			Logger.getLogger(SimpleRentalService.class.getName()).log(Level.WARNING, "SimpleRentalService: Car currently rented, rental aborted!");
			throw new EJBException();
		}
		
		rental.setCustomer( this.customerService.getCurrentCustomer() );
		rental.setDateRented( new Date() );

		this.manager.persist(rental);
		
		Logger.getLogger(SimpleRentalService.class.getName()).log(Level.INFO, String.format("SimpleRentalService: Committed rental for customer: '%s' -> '%s'", rental.getCustomer().getEmail(), rental.getCar().getName()));
	
		return rental;
	}
}
