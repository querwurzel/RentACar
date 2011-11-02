package com.car.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Rental
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Rental.QUERY_RENTEDUNTIL_BY_CAR, query = "SELECT MAX(r.rentedUntil) FROM Rental r WHERE r.car.id = ?1")
})
public class Rental implements Serializable {

	public static final String QUERY_RENTEDUNTIL_BY_CAR = "Rental.RentedUntil.FindByCar";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, updatable = false)
	private Date dateRented;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date rentedUntil;

	@JoinColumn(nullable = false)
	private Customer customer;

	@JoinColumn(nullable = false)
	private Car car;

	@JoinColumn(nullable = false)
	private Order order;

	private static final long serialVersionUID = 1L;

	public Rental() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public Date getDateRented() {
		return this.dateRented;
	}
	
	public void setDateRented(Date dateRented) {
		this.dateRented = dateRented;
	}

	public Date getRentedUntil() {
		return this.rentedUntil;
	}

	public void setRentedUntil(Date rentedUntil) {
		this.rentedUntil = rentedUntil;
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
