package com.car.domain.dto;

import java.io.Serializable;

public class CreditCardTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String owner;
	private Long number;

	public CreditCardTO() {
		super();
	}

	public CreditCardTO(String owner, Long number) {
		this();
		this.setOwner(owner);
		this.setNumber(number);
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Long getNumber() {
		return this.number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
}
