package com.car.business.remote;

import javax.ejb.Remote;

import com.car.domain.dto.CustomerTO;

@Remote
public interface CustomerService {

	public void registerCustomer(CustomerTO dto);

	public Boolean emailExists(String email);

}
