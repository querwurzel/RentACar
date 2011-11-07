package com.car.business;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.car.business.remote.CarService;
import com.car.domain.Car;
import com.car.domain.CarType;
import com.car.domain.Rental;
import com.car.domain.query.CarBasics;
import com.car.domain.query.CarTypeBasics;

/**
 * Session Bean implementation class CarService.
 */
@Stateless
@RolesAllowed("CUSTOMER")
public class SimpleCarService implements CarService {

	@PersistenceContext
	private EntityManager manager;
	
	public List<CarTypeBasics> getCarTypes() {
		return this.manager.createNamedQuery(CarType.QUERY_CARTYPE_BASICS, CarTypeBasics.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<CarBasics> getCars(Long carTypeId) {
		Query query = this.manager.createNamedQuery(Car.QUERY_CAR_BASICS_BY_CARTYPE, CarBasics.class);
		query.setParameter(1, carTypeId);

		return query.getResultList();
	}

	public Car getCar(Long carId) {
		return this.manager.find(Car.class, carId);
	}

	public Boolean isRented(Long carId) {
		Query query = this.manager.createNamedQuery(Rental.QUERY_RENTEDUNTIL_BY_CAR);
		query.setParameter(1, carId);
		
		Date rentedUntil = (Date)query.getSingleResult();
		
		return (rentedUntil == null) ? false : (rentedUntil.getTime() > System.currentTimeMillis());
	}

	public Boolean isRented(Car car) {
		return this.isRented( car.getId() );
	}
}
