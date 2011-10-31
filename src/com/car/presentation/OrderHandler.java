package com.car.presentation;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.car.business.remote.CarService;
import com.car.business.remote.CustomerService;
import com.car.business.remote.OrderService;

@ManagedBean
@ViewScoped
public class OrderHandler {
	
	@EJB
	private OrderService orderService;
	
	@EJB
	private CustomerService customerService;
	
	@EJB
	private CarService carService;
	
	
	
	public String abortOrder() {
		this.orderService.abortOrder();
		
		return "index";
	}
}
