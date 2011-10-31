package com.car.business;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.car.business.remote.CustomerService;
import com.car.domain.Customer;
import com.car.domain.Customer.CustomerRole;

/**
 * Session Bean implementation class CustomerService
 */
@Stateless
public class SimpleCustomerService implements CustomerService {

	@PersistenceContext
	private EntityManager manager;

	@Resource
	private SessionContext context;

	public void registerCustomer(Customer customer) {
		customer.setRole(CustomerRole.CONSUMER);

		this.manager.persist(customer);
		
		Logger.getLogger(SimpleCustomerService.class.getName()).log(Level.INFO, String.format("SimpleCustomerService: Registrated new customer (email: %s).", customer.getName()));
	}

	public Boolean emailExists(String email) {
		Query query = this.manager.createNamedQuery(Customer.QUERY_EMAIL, String.class);
		query.setParameter(1, email);

		Logger.getLogger(SimpleCustomerService.class.getName()).log(Level.INFO, String.format("SimpleCustomerService: Querying email '%s'.", email));

		return query.getResultList().size() > 0;
	}

	@RolesAllowed(CustomerRole.CONSUMER)
	public Customer getCurrentCustomer() {
		Query query = this.manager.createNamedQuery(Customer.QUERY_CUSTOMER_BY_EMAIL, Customer.class);
		query.setParameter(1, context.getCallerPrincipal().getName());

		return (Customer)query.getSingleResult();
	}
}
