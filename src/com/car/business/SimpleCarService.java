package com.car.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.car.business.remote.CarService;
import com.car.domain.Car;
import com.car.domain.CarType;
import com.car.domain.query.CarBasics;
import com.car.domain.query.CarTypeBasics;

/**
 * Session Bean implementation class CarService.
 */
@Stateless
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
		
		Query query = this.manager.createNamedQuery(Car.QUERY_CAR_BASICS_BY_ID, CarBasics.class);
		query.setParameter(1, carTypeId);
		
		return query.getResultList();
	}

	@Deprecated
	public CarType getCarType(Long carTypeId) {
		if (carTypeId == null)
			return null;

		return this.manager.find(CarType.class, carTypeId);
	}
	
	public Car getCar(Long carId) {
		if (carId == null)
			return null;
		
		return this.manager.find(Car.class, carId);
	}

}
