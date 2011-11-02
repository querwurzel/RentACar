package com.car.presentation;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import com.car.business.remote.CarService;
import com.car.business.remote.OrderService;
import com.car.domain.Car;
import com.car.domain.query.CarBasics;
import com.car.domain.query.CarTypeBasics;

@ManagedBean
@ViewScoped
public class CarHandler {

	@EJB
	private CarService carService;

	@EJB
	private OrderService orderService;

	private Integer duration;
	private Long carTypeId;
	private Long carId;

	private Car car;
	private Date rentedUntil;

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
	
	public Date getRentalDate() {
		return this.rentedUntil;
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
		this.rentedUntil = null;
	}

	public void selectCarTypeAsynchronous(AjaxBehaviorEvent event) {
		this.selectCarType(null);
	}

	public void selectCar(ActionEvent event) {
		// reset or retrieve car information
		this.car = (this.carId == null) ? null : this.carService.getCar(this.carId);
		this.rentedUntil = (this.carId == null) ? null : this.carService.getRentalDate(this.carId);
	}
	
	public void selectCarAsynchronous(AjaxBehaviorEvent event) {
		this.selectCar(null);
	}

	/**
	 * Proceeds to payment if a car was selected successfully.
	 */
	public String confirmCar() {
		// no car selected or car already rented
		if (this.car == null || this.rentedUntil != null)
			return "index";

		orderService.selectCar(this.car);

		return "payment";
	}
}
