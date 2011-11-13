package com.car.business;

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

	/**
	 * Bootstrap used for development. Do not use on productive systems!
	 */
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
		cust.setRole("CUSTOMER");
		
		manager.persist(cust);
    	
    	CarType ct1 = new CarType("BMW");
    	CarType ct2 = new CarType("Mercedes");
    	CarType ct3 = new CarType("Audi");
    	CarType ct4 = new CarType("Maybach");
    	CarType ct5 = new CarType("Volvo");
    	
    	manager.persist(ct1);
		manager.persist(ct2);
		manager.persist(ct3);
		manager.persist(ct4);
		manager.persist(ct5);

		// BMW
		Car c11 = new Car();
		c11.setCarType(ct1);
		c11.setName("BMW 730d (F01)");
		c11.setDescription("Der BMW 730d, dessen V8-Diesel mit zwei Turboladern ein gewaltiges Drehmoment entwickelt, verspricht die Leistungscharakteristik einer Dampfmaschine.");
		c11.setImage("69f4af110693fcf5bd5472f7d1b58f6e.jpg");
		c11.setDailyFee(90.0);

		Car c12 = new Car();
		c12.setCarType(ct1);
		c12.setName("BMW 535i");
		c12.setDescription("Der 306 PS starke BMW 535i mit Twinturbo-Reihensechser markiert die obere Mitte des Motorenangebots für den optisch braven neuen Fünfer BMW.");
		c12.setImage("612e6aa528346c820efbff5e7f9304ff.jpg");
		c12.setDailyFee(49.9);

		// Mercedes
		Car c21 = new Car();
		c21.setCarType(ct2);
		c21.setName("Mercedes W203 Elegance");
		c21.setDescription("Im März 2000 löste die Modellreihe 203 bei Mercedes-Benz die Modellreihe 202 ab. Zunächst nur als Limousine, seit Januar 2001 auch als T-Modell.");
		c21.setImage("a140ebb3ce43c32127e435bcc7e73d1f.jpg");
		c21.setDailyFee(60.0);

		// Audi
		Car c31 = new Car();
		c31.setCarType(ct3);
		c31.setName("Audi S5 (8T) Coupe");
		c31.setDescription("Mit seinem dynamischen S-Sportfahrwerk zaubert das Audi S5 Coupé auf die Straße, was sein Design verspricht: Eine neue Form des Coupé-Fahrens.");
		c31.setImage("bfd1950119ae25f4d9245c21aa561dda.jpg");
		c31.setDailyFee(59.0);

		Car c32 = new Car();
		c32.setCarType(ct3);
		c32.setName("Audi A4 Allroad Quattro");
		c32.setDescription("Völlig identisch zum Avant fallen naturgemäß Innenraum und Platzverhältnisse aus. Mit 490 Litern Kofferraum (maximal: 1430 Liter) ist auch der Allroad kein klassischer Lademeister, sondern knapp geschnittener Edellaster.");
		c32.setImage("6e06bc7cd235acec85fb2eecfbb7a9ae.jpg");
		c32.setDailyFee(44.50);

		Car c33 = new Car();
		c33.setCarType(ct3);
		c33.setName("Audi RS 5 Coupé");
		c33.setDescription("Ein Anblick, von dem Sportwagenfans nicht genug bekommen können. Und eine Motorisierung, die unwiederstehliche Kraft enthält: der Audi RS 5 Coupe!");
		c33.setImage("1c0e96e9dbacb585ee0f09c0ff9dca06.jpg");
		c33.setDailyFee(49.0);

		// Maybach
		Car c41 = new Car();
		c41.setCarType(ct4);
		c41.setName("Maybach 57 S");
		c41.setDescription("Mit größter Liebe zum Detail gestaltet, in einer modernen Manufaktur gefertigt und mit der Automobiltechnik des 21. Jahrhunderts ausgestattet, setzt der Maybach 57 S die Tradition der berühmten Maybach Automobile fort.");
		c41.setImage("43cf700402e98596ab361cd90505753e.jpg");
		c41.setDailyFee(119.0);
		
		manager.persist(c11);
		manager.persist(c12);
		manager.persist(c21);
		manager.persist(c31);
		manager.persist(c32);
		manager.persist(c33);
		manager.persist(c41);
		
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
		
		// past rental
		Rental r1 = new Rental();
		r1.setAmount(200.0);
		r1.setPayment(card);
		r1.setCar(c11);
		r1.setCustomer(cust);
		r1.setDateRented( new Date(System.currentTimeMillis() - 30000000000L) );
		r1.setRentedUntil( new Date(System.currentTimeMillis() - 20000000000L) );
		
		// present rental
		Rental r2 = new Rental();
		r2.setAmount(100.0);
		r2.setPayment(invoice);
		r2.setCar(c11);
		r2.setCustomer(cust);
		r2.setDateRented( new Date() );
		r2.setRentedUntil( new Date(System.currentTimeMillis() + 30000000000L) );
		
		manager.persist(r1);
		manager.persist(r2);
		
		Logger.getLogger(Bootstrap.class.getName()).log(Level.INFO, "Bootstrap: Success!");
    }
}
