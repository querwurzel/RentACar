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
		c11.setName("BMW 740d");
		c11.setCurrency( Currency.getInstance("EUR") );
		c11.setDailyFee(90.0);
		
		Car c12 = new Car();
		c12.setCarType( ct1 );
		c12.setName("BMW 525d");
		c12.setCurrency( Currency.getInstance("EUR") );
		c12.setDailyFee(49.9);
		
		manager.persist( c11 );
		manager.persist( c12 );
    }

}
