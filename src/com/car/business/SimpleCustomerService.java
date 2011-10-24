package com.car.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.car.business.remote.CustomerService;
import com.car.domain.Customer;

/**
 * Session Bean implementation class CustomerService
 */
@Stateless
public class SimpleCustomerService implements CustomerService {

	@PersistenceContext
	private EntityManager manager;

	/**
	 * Default constructor.
	 */
	public SimpleCustomerService() {
		// TODO Auto-generated constructor stub
	}

	public void createCustomer() {

		System.out.println( "would create new customer ");
		
	}
	
	public Boolean customerExists(Customer customer) {
		return manager.find(Customer.class, customer.getId()) != null;
	}

}
