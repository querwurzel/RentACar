package com.car.domain;

import java.io.Serializable;
import java.util.Currency;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Order
 * 
 */
@Entity
@Table(name = "\"ORDER\"")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, updatable = false)
	private Long orderNumber;

	@Column(nullable = false, scale = 3)
	private Double amount;

	@Column(nullable = false)
	private Currency currency;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false, updatable = false)
	private Date dateCreated;

	@JoinColumn(nullable = false)
	private PaymentType payment;

	private static final long serialVersionUID = 1L;

	public Order() {
		super();
		
		this.dateCreated = new Date();
	}

	public Long getId() {
		return this.id;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public PaymentType getPayment() {
		return payment;
	}

	public void setPayment(PaymentType payment) {
		this.payment = payment;
	}
}
