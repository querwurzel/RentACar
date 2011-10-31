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
	private String name;
	private String description;
	private String image;
	private Double dailyFee;
	private String currency;
	
	/**
	 * Resets all car-specific fields if current carType changed.
	 */
	public void updateCarType(ActionEvent event) {
		this.setCarId(null);
		this.setName(null);
		this.setDescription(null);
		this.setImage(null);
		this.setDailyFee(null);
		this.setCurrency(null);
	}
	
	/**
	 * Resolves all car-specific fields for the current car.
	 * If no car was found all fields will be nulled.
	 */
	public void updateCar(ActionEvent event) {
		Car car = this.carService.getCar( this.getCarId() );

		if (car == null) {
			this.setName(null);
			this.setDescription(null);
			this.setImage(null);
			this.setDailyFee(null);
			this.setCurrency(null);
		} else {
			this.setName( car.getName() );
			this.setDescription( car.getDescription() );
			this.setImage( car.getImage() );
			this.setDailyFee( car.getDailyFee() );
			this.setCurrency( car.getCurrency().getCurrencyCode() );
		}
	}
	
	public void updateCarTypeAsynchronous(AjaxBehaviorEvent event) {
		this.updateCarType(null);
	}
	
	public void updateCarAsynchronous(AjaxBehaviorEvent event) {
		this.updateCar(null);
	}
	
	public String selectCar() {
		if (this.getCarId() == null)
			return "index";

		orderService.selectCar( this.getCarId() );

		return "payment";
	}
	
	/**
	 * Returns all carTypes available.
	 */
	public List<CarTypeBasics> getAllCarTypes() {
		return this.carService.getAllCarTypes();
	}
	
	/**
	 * Returns all cars available for the current carType.
	 */
	public List<CarBasics> getAllCars() {
		return this.carService.getAllCars( this.getCarTypeId() );
	}

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

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	private void setImage(String image) {
		this.image = image;
	}

	public Double getDailyFee() {
		return dailyFee;
	}

	private void setDailyFee(Double dailyFee) {
		this.dailyFee = dailyFee;
	}

	public String getCurrency() {
		return currency;
	}

	private void setCurrency(String currency) {
		this.currency = currency;
	}
}
