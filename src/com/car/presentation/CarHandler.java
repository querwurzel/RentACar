package com.car.presentation;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import com.car.business.remote.CarService;
import com.car.domain.Car;
import com.car.domain.query.CarBasics;
import com.car.domain.query.CarTypeBasics;

@ManagedBean
@SessionScoped
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
	
	/**
	 * Resets all car-specific fields if current carType changed.
	 */
	public void selectCarType(ActionEvent event) {
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
	public void selectCar(ActionEvent event) {
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
	
	public void selectCarTypeAsynchronous(AjaxBehaviorEvent event) {
		this.selectCarType(null);
	}
	
	public void selectCarAsynchronous(AjaxBehaviorEvent event) {
		this.selectCar(null);
	}
	
	@Deprecated
	public void onCarTypeSelect(ValueChangeEvent event) {
		System.out.println("onCarTypeSelect fired");
		
		this.setCarId(null);
		this.setName(null);
		this.setDescription(null);
		this.setImage(null);
		this.setDailyFee(null);
		this.setCurrency(null);
	}
	
	@Deprecated
	public void onCarSelect(ValueChangeEvent event) {
		System.out.println("onCarSelect fired");
		
		Long id = (Long)event.getNewValue();
		
		Car car = this.carService.getCar( id );

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
