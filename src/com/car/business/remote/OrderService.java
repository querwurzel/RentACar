package com.car.business.remote;

import javax.ejb.Remote;

@Remote
public interface OrderService {
	
	public void selectCar(Long carId);

}
