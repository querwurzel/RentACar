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
import com.car.domain.dto.CarTO;
import com.car.domain.dto.CarTypeTO;

/**
 * Session Bean implementation class CarService.
 */
@Stateless
@RolesAllowed("CUSTOMER")
public class SimpleCarService implements CarService {

	@PersistenceContext
	private EntityManager manager;
	
	/**
	 * Returns all available car types.
	 */
	public List<CarTypeTO> getCarTypes() {
		return this.manager.createNamedQuery(CarType.QUERY_CARTYPE_DTO, CarTypeTO.class).getResultList();
	}

	/**
	 * Returns all available cars for the specific car type.
	 */
	@SuppressWarnings("unchecked")
	public List<CarTO> getCars(Long carTypeId) {
		Query query = this.manager.createNamedQuery(Car.QUERY_CAR_DTO_BY_CARTYPE);
		query.setParameter(1, carTypeId);

		return query.getResultList();
	}

	/**
	 * Returns specific car entity.
	 */
	public CarTO getCar(Long carId) {
		Query query = this.manager.createNamedQuery(Car.QUERY_CAR_DTO_BY_ID);
		query.setParameter(1, carId);
		
		return (CarTO)query.getSingleResult();
	}

	/**
	 * Checks if a car is currently rented.
	 * @return true if rented
	 */
	public Boolean isRented(Long carId) {
		Query query = this.manager.createNamedQuery(Rental.QUERY_RENTEDUNTIL_BY_CAR);
		query.setParameter(1, carId);

		Date rentedUntil = (Date)query.getSingleResult();

		return (rentedUntil == null) ? false : rentedUntil.getTime() > System.currentTimeMillis();
	}
}
