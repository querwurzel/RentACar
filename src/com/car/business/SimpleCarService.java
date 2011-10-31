package com.car.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.car.business.remote.CarService;
import com.car.domain.Car;
import com.car.domain.CarType;
import com.car.domain.Customer.CustomerRole;
import com.car.domain.query.CarBasics;
import com.car.domain.query.CarTypeBasics;

/**
 * Session Bean implementation class CarService.
 */
@Stateless
@RolesAllowed(CustomerRole.CONSUMER)
public class SimpleCarService implements CarService {

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<CarTypeBasics> getAllCarTypes() {
		Query query = this.manager.createNamedQuery(CarType.QUERY_CARTYPE_BASICS, CarTypeBasics.class);
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<CarBasics> getAllCars(Long carTypeId) {
		if (carTypeId == null)
			return new ArrayList<CarBasics>();
		
		Query query = this.manager.createNamedQuery(Car.QUERY_CAR_BASICS_BY_CARTYPE, CarBasics.class);
		query.setParameter(1, carTypeId);
		
		return query.getResultList();
	}
	
	public Car getCar(Long carId) {
		return (carId == null) ? null : this.manager.find(Car.class, carId);
	}
}
