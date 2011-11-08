package com.car.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: Rental
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Rental.QUERY_RENTEDUNTIL_BY_CAR, query = "SELECT MAX(r.rentedUntil) FROM Rental r WHERE r.car.id = ?1")
})
public class Rental implements Serializable {

	public static final String QUERY_RENTEDUNTIL_BY_CAR = "Rental.RentedUntil.FindByCar";
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dateRented;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date rentedUntil;
	
	@Column(nullable = false)
	private Double amount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Payment payment;
	
	@JoinColumn(nullable = false)
	private Customer customer;

	@JoinColumn(nullable = false)
	private Car car;

	public Rental() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
