package com.car.business;

import java.util.Currency;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.car.domain.Car;
import com.car.domain.CarType;

/**
 * Session Bean implementation class Bootstrap
 */
@Startup
@Singleton
public class Bootstrap {
	
	@PersistenceContext
	private EntityManager manager;

    @PostConstruct
    private void init() {
    	CarType ct1 = new CarType("BMW");
    	CarType ct2 = new CarType("Mercedes");
    	
    	manager.persist( ct1 );
		manager.persist( ct2 );
		manager.flush();
		
		Car c11 = new Car();
		c11.setCarType( ct1 );
		c11.setName("BMW 730d (F01)");
		c11.setDescription("Der BMW 730d, dessen V8-Diesel mit zwei Turboladern ein gewaltiges Drehmoment entwickelt, verspricht die Leistungscharakteristik einer Dampfmaschine.");
		c11.setImage("69f4af110693fcf5bd5472f7d1b58f6e.jpg");
		c11.setCurrency( Currency.getInstance("EUR") );
		c11.setDailyFee(90.0);
		
		Car c12 = new Car();
		c12.setCarType( ct1 );
		c12.setName("BMW 535i");
		c12.setDescription("Der 306 PS starke BMW 535i mit Twinturbo-Reihensechser markiert die obere Mitte des Motorenangebots für den optisch braven neuen Fünfer BMW.");
		c12.setImage("612e6aa528346c820efbff5e7f9304ff.jpg");
		c12.setCurrency( Currency.getInstance("EUR") );
		c12.setDailyFee(49.9);
		
		manager.persist( c11 );
		manager.persist( c12 );
    }

}
