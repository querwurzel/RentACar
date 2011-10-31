package com.car.business;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;

import com.car.business.remote.OrderService;

/**
 * Session Bean implementation class SimpleOrderService
 */
@Stateful
@RolesAllowed({"CONSUMER"})
public class SimpleOrderService implements OrderService {

	private Long carId;
	
	public void selectCar(Long carId) {
		Logger.getLogger(SimpleOrderService.class.getName()).log(Level.INFO, String.format("Car (id: %d) was selected for rental.", carId));
		
		this.carId = carId;
	}


}
