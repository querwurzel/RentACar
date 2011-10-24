package com.car.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Rental
 * 
 */
@Entity
public class Rental implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, updatable = false)
	private Date dateRented;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date rentedUntil;

	@Temporal(TemporalType.DATE)
	private Date dateReturned;

	@Temporal(TemporalType.DATE)
	private Date lastUpdated;

	@JoinColumn(nullable = false)
	private Customer customer;

	@JoinColumn(nullable = false)
	private Car car;

	@JoinColumn(nullable = false)
	private Order order;

	private static final long serialVersionUID = 1L;

	public Rental() {
		super();
		
		this.dateRented = new Date();
	}

	public Long getId() {
		return this.id;
	}

	public Date getDateRented() {
		return this.dateRented;
	}

	public Date getRentedUntil() {
		return this.rentedUntil;
	}

	public void setRentedUntil(Date rentedUntil) {
		this.rentedUntil = rentedUntil;
	}

	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Date getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
