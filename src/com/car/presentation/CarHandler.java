package com.car.presentation;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import com.car.business.remote.CarService;
import com.car.business.remote.RentalService;
import com.car.domain.dto.CarTO;
import com.car.domain.dto.CarTypeTO;

@ManagedBean
@ViewScoped
public class CarHandler {

	@EJB
	private CarService carService;

	@ManagedProperty(value = "#{userHandler.rentalService}")
	private RentalService rentalService;

	private Integer duration;
	private Long carTypeId;
	private Long carId;

	private CarTO car;
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

	public CarTO getCar() {
		return this.car;
	}
	
	public Boolean getIsRented() {
		return this.isRented;
	}
	
	/**
	 * Setter for RentalService, required for dependency injection.
	 */
	public void setRentalService(RentalService rentalService) {
		this.rentalService = rentalService;
	}
	
	/**
	 * Returns all carTypes available from CarService.
	 */
	public List<CarTypeTO> getCarTypes() {
		return this.carService.getCarTypes();
	}
	
	/**
	 * Returns all cars available for the current carType from CarService.
	 */
	public List<CarTO> getCars() {
		return this.carService.getCars(this.carTypeId);
	}

	/**
	 * ActionListener Event
	 * Resets dependent attributes for prior car selection.
	 */
	public void selectCarType(ActionEvent event) {
		// reset dependent attributes
		this.carId = null;
		this.car = null;
		this.isRented = null;
	}

	/**
	 * Asynchronous Event for AJAX.
	 * @see CarHandler#selectCarType(ActionEvent)
	 */
	public void selectCarTypeAsynchronous(AjaxBehaviorEvent event) {
		this.selectCarType(null);
	}
	
	/**
	 * ActionListener Event
	 * Retrieves car information from CarService if a car has been selected.
	 */
	public void selectCar(ActionEvent event) {
		// reset or retrieve car information
		this.car = (this.carId == null) ? null : this.carService.getCar(this.carId);
		this.isRented = (this.carId == null) ? null : this.carService.isRented(this.carId);
	}
	
	/**
	 * Asynchronous Event for AJAX.
	 * @see CarHandler#selectCar(ActionEvent)
	 */
	public void selectCarAsynchronous(AjaxBehaviorEvent event) {
		this.selectCar(null);
	}

	/**
	 * Starts rental workflow. Assigns selected to new rental.
	 * Redirects to the next step.
	 */
	public String confirmCar() {
		// check if car selected
		if (this.carId == null)
			return "index";

		this.rentalService.commitCar(this.carId, this.duration);

		return "payment";
	}
}
