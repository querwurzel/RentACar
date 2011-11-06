package com.car.business.remote;

import javax.ejb.Remote;

import com.car.domain.Car;
import com.car.domain.Payment;
import com.car.domain.Rental;

@Remote
public interface RentalService {

	public Rental commitCar(Rental rental, Car car, Integer duration);
	
	public Rental commitPayment(Rental rental, Payment payment);
	
	public Rental commitRental(Rental rental);

}
