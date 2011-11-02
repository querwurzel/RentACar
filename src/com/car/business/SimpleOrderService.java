package com.car.business;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;

import com.car.business.remote.CustomerService;
import com.car.business.remote.OrderService;
import com.car.domain.Car;
import com.car.domain.Customer.CustomerRole;
import com.car.domain.Payment;

/**
 * Session Bean implementation class SimpleOrderService
 */
@Stateful
@RolesAllowed(CustomerRole.CONSUMER)
public class SimpleOrderService implements OrderService {

	@EJB
	private CustomerService customerService;

	@Resource
	private SessionContext context;

	private Long carId;
	private Payment payment;
	
	public void selectCar(Car car) {
		
		Logger.getLogger(SimpleOrderService.class.getName()).log(Level.INFO, String.format("Car (id: %d) was selected for rental.", carId));
		
	}

	public void selectPayment(Payment payment) {
		// TODO Auto-generated method stub
	}

	public void abortOrder() {
		this.carId = null;
		this.payment = null;
		
		Logger.getLogger(SimpleOrderService.class.getName()).log(Level.INFO, String.format("Order aborted by customer (email: %s).", this.context.getCallerPrincipal()));
	}

	public void confirmOrder() {
		// TODO Auto-generated method stub
		
	}
}
