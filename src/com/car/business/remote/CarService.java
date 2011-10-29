package com.car.business.remote;

import java.util.List;

import javax.ejb.Remote;

import com.car.domain.Car;
import com.car.domain.query.CarBasics;
import com.car.domain.query.CarTypeBasics;

@Remote
public interface CarService {

	public List<CarTypeBasics> getAllCarTypes();

	public List<CarBasics> getAllCars(Long carTypeId);

	public Car getCar(Long carId);

}
