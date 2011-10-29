package com.car.presentation;

import java.util.Currency;
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
	private String name;
	private String description;
	private String image;
	private Double dailyFee;
	private String currency;

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
		
		// fetch missing car data
		Car car = this.carService.getCar( this.getCarId() );

		if (car != null) {
			this.name = car.getName();
			this.description = car.getDescription();
			this.image = car.getImage();
			this.dailyFee = car.getDailyFee();
			this.currency = car.getCurrency().getCurrencyCode();
		}
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	public Double getDailyFee() {
		return dailyFee;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	
	
	
	
	public List<CarTypeBasics> getAllCarTypes() {
		return this.carService.getAllCarTypes();
	}
	
	public List<CarBasics> getAllCars() {
		return this.carService.getAllCars( this.getCarTypeId() );
	}
}
