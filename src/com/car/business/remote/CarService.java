package com.car.business.remote;

import java.util.List;

import javax.ejb.Remote;

import com.car.domain.dto.CarTO;
import com.car.domain.dto.CarTypeTO;

@Remote
public interface CarService {

	public List<CarTypeTO> getCarTypes();

	public List<CarTO> getCars(Long carTypeId);

	public CarTO getCar(Long carId);

	public Boolean isRented(Long carId);

}
