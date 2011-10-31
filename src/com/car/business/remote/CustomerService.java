package com.car.business.remote;

import javax.ejb.Remote;

import com.car.domain.Customer;

@Remote
public interface CustomerService {

	public void registerCustomer(Customer customer);

	public Boolean emailExists(String email);

	public Customer getCurrentCustomer();

}
