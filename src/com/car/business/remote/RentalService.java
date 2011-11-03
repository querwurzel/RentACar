package com.car.business.remote;

import javax.ejb.Remote;

import com.car.domain.Car;
import com.car.domain.Payment;

@Remote
public interface RentalService {

	public void selectCar(Car car, Integer duration);

	public void selectPayment(Payment payment);

	public void abortRental();

	public void confirmRental() throws RuntimeException;

}
