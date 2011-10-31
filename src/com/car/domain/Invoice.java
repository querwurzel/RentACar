package com.car.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Invoice
 */
@Entity
public class Invoice extends Payment implements Serializable {

	@Column(nullable = false, unique = true, updatable = false)
	private Long invoiceNumber;

	private static final long serialVersionUID = 1L;

	public Invoice() {
		super();
	}

	public Long getInvoiceNumber() {
		return this.invoiceNumber;
	}

	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
}
