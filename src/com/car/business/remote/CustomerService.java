package com.car.business.remote;

import javax.ejb.Remote;

import com.car.domain.Customer;

@Remote
public interface CustomerService {

	public void createCustomer();

	public Boolean customerExists(Customer customer);
	
}
