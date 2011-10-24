package com.car.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		System.out.println("would create new customer");
		
	}
	
	public Boolean emailExists(String email) {
		Query query = manager.createNamedQuery(Customer.EMAIL_EXISTS, Long.class);
		query.setParameter (1, email);
		
		// exists if count() > 0
		return (Long)query.getSingleResult() > 0;
	}
}
