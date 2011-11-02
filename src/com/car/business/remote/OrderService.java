package com.car.business.remote;

import javax.ejb.Remote;

import com.car.domain.Car;
import com.car.domain.Payment;

@Remote
public interface OrderService {

	public void selectCar(Car car);

	public void selectPayment(Payment payment);

	public void abortOrder();

	public void confirmOrder();

}
