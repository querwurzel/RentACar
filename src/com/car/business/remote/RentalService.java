package com.car.business.remote;

import javax.ejb.Remote;

import com.car.domain.dto.CreditCardTO;
import com.car.domain.dto.InvoiceTO;
import com.car.domain.dto.RentalTO;

@Remote
public interface RentalService {

	public RentalTO getCurrentRental();

	public void commitCar(Long car, Integer duration);

	public void commitInvoice(InvoiceTO to);

	public void commitCreditCard(CreditCardTO to);

	public void commitRental();

	public void cancelRental();

	public void terminate();

}
