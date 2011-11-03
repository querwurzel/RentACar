package com.car.business;

import java.util.Currency;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.security.DenyAll;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.car.domain.Car;
import com.car.domain.CarType;
import com.car.domain.CreditCard;
import com.car.domain.Customer;
import com.car.domain.Customer.CustomerRole;
import com.car.domain.Customer.Gender;
import com.car.domain.Invoice;
import com.car.domain.Rental;

/**
 * Session Bean implementation class Bootstrap
 */
@Startup
@DenyAll
@Singleton
public class Bootstrap {
	
	@PersistenceContext
	private EntityManager manager;

	@PostConstruct
	@SuppressWarnings("unused")
    private void init() {
    	Customer cust = new Customer();
		cust.setName("Name");
		cust.setSurname("Surname");
		cust.setStreet("Street");
		cust.setNumber("A");
		cust.setGender(Gender.MALE);
		cust.setDateOfBirth( new Date() );
		cust.setEmail("admin@localhost");
		cust.setPassword("admin");
		cust.setLocality("Locality");
		cust.setPostalCode("D-12345");
		cust.setRole(CustomerRole.CONSUMER);
		
		manager.persist(cust);
    	
    	CarType ct1 = new CarType("BMW");
    	CarType ct2 = new CarType("Mercedes");
    	CarType ct3 = new CarType("Audi");
    	
    	manager.persist( ct1 );
		manager.persist( ct2 );
		manager.persist( ct3 );
		
		Car c11 = new Car();
		c11.setCarType( ct1 );
		c11.setName("BMW 730d (F01)");
		c11.setDescription("Der BMW 730d, dessen V8-Diesel mit zwei Turboladern ein gewaltiges Drehmoment entwickelt, verspricht die Leistungscharakteristik einer Dampfmaschine.");
		c11.setImage("69f4af110693fcf5bd5472f7d1b58f6e.jpg");
		c11.setDailyFee(90.0);
		c11.setCurrency( Currency.getInstance("EUR") );
		
		Car c12 = new Car();
		c12.setCarType( ct1 );
		c12.setName("BMW 535i");
		c12.setDescription("Der 306 PS starke BMW 535i mit Twinturbo-Reihensechser markiert die obere Mitte des Motorenangebots für den optisch braven neuen Fünfer BMW.");
		c12.setImage("612e6aa528346c820efbff5e7f9304ff.jpg");
		c12.setDailyFee(49.9);
		c12.setCurrency( Currency.getInstance("EUR") );
		
		Car c21 = new Car();
		c21.setCarType( ct2 );
		c21.setName("Mercedes W203 Elegance");
		c21.setDescription("Im März 2000 löste die Modellreihe 203 bei Mercedes-Benz die Modellreihe 202 ab. Zunächst nur als Limousine, seit Januar 2001 auch als T-Modell.");
		c21.setImage("a140ebb3ce43c32127e435bcc7e73d1f.jpg");
		c21.setDailyFee(60.0);
		c21.setCurrency( Currency.getInstance("EUR") );
		
		manager.persist( c11 );
		manager.persist( c12 );
		
		CreditCard card = new CreditCard();
		card.setDateOfPayment( new Date() );
		card.setNumber(123456789L);
		card.setOwner("Name Surname");
		
		Invoice invoice = new Invoice();
		invoice.setDateOfPayment( new Date() );
		invoice.setInvoiceNumber(1000L);
		
		manager.persist(card);
		manager.persist(invoice);
		manager.flush();
		
		// past
		Rental r1 = new Rental();
		r1.setAmount(200.0);
		r1.setCurrency( Currency.getInstance("EUR") );
		r1.setPayment(card);
		r1.setCar(c11);
		r1.setCustomer(cust);
		r1.setDateRented( new Date(System.currentTimeMillis() - 30000000000L) );
		r1.setRentedUntil( new Date(System.currentTimeMillis() - 20000000000L) );
		
		// present
		Rental r2 = new Rental();
		r2.setAmount(100.0);
		r2.setCurrency( Currency.getInstance("EUR") );
		r2.setPayment(invoice);
		r2.setCar(c11);
		r2.setCustomer(cust);
		r2.setDateRented( new Date() );
		r2.setRentedUntil( new Date(System.currentTimeMillis() + 30000000000L) );
		
		manager.persist(r1);
		manager.persist(r2);
		manager.flush();
		
		Logger.getLogger(Bootstrap.class.getName()).log(Level.INFO, "Bootstrap: Success!");
    }
}
