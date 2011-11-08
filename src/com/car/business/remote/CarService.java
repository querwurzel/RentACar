package com.car.business.remote;

import java.util.List;

import javax.ejb.Remote;

import com.car.domain.Car;
import com.car.domain.dto.CarBasics;
import com.car.domain.dto.CarTypeBasics;

@Remote
public interface CarService {

	public List<CarTypeBasics> getCarTypes();

	public List<CarBasics> getCars(Long carTypeId);

	public Car getCar(Long carId);

	public Boolean isRented(Long carId);
	
	public Boolean isRented(Car car);

}
