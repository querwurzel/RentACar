package com.car.business;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.car.business.remote.CustomerService;
import com.car.domain.Customer;
import com.car.domain.dto.CustomerTO;

/**
 * Session Bean implementation class CustomerService
 */
@Stateless
public class SimpleCustomerService implements CustomerService {

	@PersistenceContext
	private EntityManager manager;

	/**
	 * Registers new Customer entity.
	 */
	public void registerCustomer(CustomerTO dto) {
		Customer customer = new Customer();
		customer.setGender( dto.getGender() );
		customer.setSurname( dto.getSurname() );
		customer.setName( dto.getName() );
		customer.setDateOfBirth( dto.getDateOfBirth() );
		customer.setEmail( dto.getEmail() );
		customer.setStreet( dto.getStreet() );
		customer.setNumber( dto.getNumber() );
		customer.setLocality( dto.getLocality() );
		customer.setPostalCode( dto.getPostalCode() );
		customer.setPassword( dto.getPassword() );
		customer.setRole("CUSTOMER");

		this.manager.persist(customer);

		Logger.getLogger(SimpleCustomerService.class.getName()).log(Level.INFO, String.format("SimpleCustomerService: New customer registrated (email: %s).", customer.getEmail()));
	}

	/**
	 * Checks if an email address is already assigned to a customer.
	 */
	public Boolean emailExists(String email) {
		Query query = this.manager.createNamedQuery(Customer.QUERY_EMAIL, Long.class);
		query.setParameter(1, email);

		return (Long)query.getSingleResult() > 0;
	}
}
