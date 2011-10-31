package com.car.presentation;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.car.business.remote.OrderService;
import com.car.domain.Payment;

@ManagedBean
@ViewScoped
public class PaymentHandler {
	
	@EJB
	private OrderService orderService;
	
	private Payment payment;
	
	
	
	public String selectPayment() {
		// TODO: not yet finished
		return "checkout";
	}
	
	public String abortPayment() {
		this.orderService.abortOrder();
		
		return "index";
	}
}
