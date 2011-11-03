package com.car.presentation;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import com.car.business.remote.CarService;
import com.car.business.remote.RentalService;
import com.car.domain.Car;
import com.car.domain.query.CarBasics;
import com.car.domain.query.CarTypeBasics;

@ManagedBean
@ViewScoped
public class CarHandler {

	@EJB
	private CarService carService;

	@EJB
	private RentalService rentalService;

	private Integer duration;
	private Long carTypeId;
	private Long carId;

	private Car car;
	private Boolean isRented;

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public Long getCarTypeId() {
		return this.carTypeId;
	}

	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}

	public Long getCarId() {
		return this.carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Car getCar() {
		return this.car;
	}
	
	public Boolean getIsRented() {
		return this.isRented;
	}
	
	/**
	 * Returns all carTypes available.
	 */
	public List<CarTypeBasics> getCarTypes() {
		return this.carService.getCarTypes();
	}
	
	/**
	 * Returns all cars available for the current carType.
	 */
	public List<CarBasics> getCars() {
		return this.carService.getCars(this.carTypeId);
	}

	public void selectCarType(ActionEvent event) {
		// reset dependent attributes
		this.carId = null;
		this.car = null;
		this.isRented = null;
	}

	public void selectCarTypeAsynchronous(AjaxBehaviorEvent event) {
		this.selectCarType(null);
	}

	public void selectCar(ActionEvent event) {
		// reset or retrieve car information
		this.car = (this.carId == null) ? null : this.carService.getCar(this.carId);
		this.isRented = (this.carId == null) ? null : this.carService.isRented(this.carId);
	}
	
	public void selectCarAsynchronous(AjaxBehaviorEvent event) {
		this.selectCar(null);
	}

	/**
	 * Proceeds to payment if a car was selected successfully.
	 */
	public String confirmCar() {
		// no car selected or car already rented
		if (this.car == null || this.isRented)
			return "index";

		rentalService.selectCar(this.car, this.duration);

		return "payment";
	}
}
