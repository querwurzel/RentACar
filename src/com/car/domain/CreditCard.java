package com.car.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: CreditCard
 * 
 */
@Entity
public class CreditCard extends PaymentType implements Serializable {

	@Column(nullable = false)
	private Long number;

	@Column(nullable = false)
	private String owner;

	private static final long serialVersionUID = 1L;

	public CreditCard() {
		super();
	}

	public Long getNumber() {
		return this.number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
