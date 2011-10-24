package com.car.business.remote;

import javax.ejb.Remote;

@Remote
public interface CustomerService {

	public void createCustomer();

	public Boolean emailExists(String email);

}
