package com.car.presentation;

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

	private Long carTypeId;
	private Long carId;
	private Car car;
	private Integer duration;

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
	}

	public Car getCar() {
		return car;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
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
		return (this.carTypeId == null) ? null : this.carService.getCars( this.carTypeId );
	}
	
	public void selectCarType(ActionEvent event) {
		// reset dependent attributes
		this.carId = null;
		this.car = null;
	}
	
	public void selectCar(ActionEvent event) {
		// reset or retrieve Car currently selected
		this.car = (this.carId == null) ? null : this.carService.getCar( this.carId );
	}
	
	public void selectCarTypeAsynchronous(AjaxBehaviorEvent event) {
		this.selectCarType(null);
	}
	
	public void selectCarAsynchronous(AjaxBehaviorEvent event) {
		this.selectCar(null);
	}
	
	/**
	 * Proceeds to payment if a car was selected successfully.
	 */
	public String confirmCar() {
		// TODO
		
		// abort if no car selected
		if (this.car == null)
			return "index";

		orderService.selectCar( this.getCarId() );

		return "payment";
	}
}
