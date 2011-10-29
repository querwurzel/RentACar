package com.car.presentation;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.car.business.remote.CarService;
import com.car.domain.Car;
import com.car.domain.query.CarBasics;
import com.car.domain.query.CarTypeBasics;

@ManagedBean
@ViewScoped
public class CarHandler {

	@EJB
	private CarService carService;

	private Long carTypeId;
	private Long carId;
	private Car car;

	public Long getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
		this.car = this.carService.getCar( this.getCarId() );
	}
	
	public Double getCarFee() {
		return (this.car != null) ? this.car.getDailyFee() : null;
	}
	
	
	
	public List<CarTypeBasics> getAllCarTypes() {
		return this.carService.getAllCarTypes();
	}
	
	public List<CarBasics> getAllCars() {
		return this.carService.getAllCars( this.getCarTypeId() );
	}
}
