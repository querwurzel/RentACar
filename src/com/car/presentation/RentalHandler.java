package com.car.presentation;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.car.business.remote.CarService;
import com.car.business.remote.CustomerService;
import com.car.business.remote.RentalService;

@ManagedBean
@ViewScoped
public class RentalHandler {
	
	@EJB
	private RentalService rentalService;
	
	@EJB
	private CustomerService customerService;
	
	@EJB
	private CarService carService;
	
	
	
	public String abortRental() {
		this.rentalService.abortRental();
		
		return "index";
	}
}
