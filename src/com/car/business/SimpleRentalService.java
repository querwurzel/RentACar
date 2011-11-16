package com.car.business;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.car.business.remote.CarService;
import com.car.business.remote.RentalService;
import com.car.domain.Car;
import com.car.domain.CreditCard;
import com.car.domain.Customer;
import com.car.domain.Invoice;
import com.car.domain.Rental;
import com.car.domain.dto.CreditCardTO;
import com.car.domain.dto.InvoiceTO;
import com.car.domain.dto.RentalTO;

/**
 * Session Bean implementation class SimpleOrderService
 */
@Stateful
@StatefulTimeout(value = 30)
@RolesAllowed("CUSTOMER")
public class SimpleRentalService implements RentalService {

	@PersistenceContext
	private EntityManager manager;

	@Resource
	private SessionContext context;

	@EJB
	private CarService carService;
	
	private Rental rental;
	
	@PostConstruct
	private void reset() {
		this.rental = new Rental();
	}

	/**
	 * Adds car for a specific duration to a new rental.
	 * Takes care of car related information.
	 */
	public void commitCar(Long carId, Integer duration) {
		// set rentedUntil
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, duration);
		this.rental.setRentedUntil( cal.getTime() );

		// set car relatives
		Car car = this.getCar(carId);
		this.rental.setCar(car);
		this.rental.setAmount( car.getDailyFee() * duration );
		
		Logger.getLogger(SimpleRentalService.class.getName()).log(Level.INFO, String.format("SimpleRentalService: Assigned car to rental: '%s'", car.getName()));
	}

	/**
	 * Adds invoice as payment type to current rental.
	 * Takes care of invoice related information. 
	 */
	public void commitInvoice(InvoiceTO to) {
		Invoice invoice = new Invoice();
		
		// date of payment for invoice: 14 days
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 14);
		invoice.setDateOfPayment(cal.getTime());
		
		// use nanoTime() as unique invoice number
		invoice.setInvoiceNumber( System.nanoTime() );
		
		this.rental.setPayment(invoice);
		
		Logger.getLogger(SimpleRentalService.class.getName()).log(Level.INFO, String.format("SimpleRentalService: Assigned payment to rental: '%s'", invoice.toString()));
	}

	/**
	 * Adds credit card as payment type to current rental.
	 * Takes care of credit card related information. 
	 */
	public void commitCreditCard(CreditCardTO to) {
		CreditCard card = new CreditCard(to.getOwner(), to.getNumber());
		
		// date of payment for credit cards: now
		card.setDateOfPayment( new Date() );

		this.rental.setPayment(card);
		
		Logger.getLogger(SimpleRentalService.class.getName()).log(Level.INFO, String.format("SimpleRentalService: Assigned payment to rental: '%s'", card.toString()));
	}

	/**
	 * Returns current rental for checkout.
	 */
	public RentalTO getCurrentRental() {
		RentalTO to = new RentalTO();
		to.setAmount( this.rental.getAmount() );
		to.setRentedUntil( this.rental.getRentedUntil() );

		if (this.rental.getCar() != null)
			to.setCar( this.rental.getCar().getName() );

		if (this.rental.getPayment() != null)
			to.setPayment( this.rental.getPayment().toString() );

		return to;
	}
	
	/**
	 * Finishes rental by persistence.
	 * Takes care of customer and rental related information.
	 * 
	 * @throws EJBException if car is currently rented
	 */
	public void commitRental() {
		// check car availability again
		if (this.carService.isRented( rental.getCar().getId() )) {
			Logger.getLogger(SimpleRentalService.class.getName()).log(Level.WARNING, "SimpleRentalService: Car currently rented, cannot book rental!");
			throw new EJBException();
		}
		
		this.rental.setCustomer( this.getCustomer( context.getCallerPrincipal().getName() ) );
		this.rental.setDateRented( new Date() );

		this.manager.persist(rental);
		
		this.reset();
		
		Logger.getLogger(SimpleRentalService.class.getName()).log(Level.INFO, String.format("SimpleRentalService: Committed rental for customer: '%s' -> '%s'", rental.getCustomer().getEmail(), rental.getCar().getName()));
	}

	public void cancelRental() {
		this.reset();

		Logger.getLogger(SimpleRentalService.class.getName()).log(Level.INFO, "SimpleRentalService: Cancelled upcoming rental.");
	}
	
	/**
	 * Retrieves customer entity by email.
	 */
	private Customer getCustomer(String email) {
		Query query = this.manager.createNamedQuery(Customer.QUERY_CUSTOMER_BY_EMAIL);
		query.setParameter(1, email);

		return (Customer)query.getSingleResult();
	}

	/**
	 * Retrieves car entity by id.
	 */
	private Car getCar(Long carId) {
		return this.manager.find(Car.class, carId);
	}
}
