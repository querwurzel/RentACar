package com.car.domain.dto;

import java.io.Serializable;

public class InvoiceTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String invoiceNumber;
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
}
