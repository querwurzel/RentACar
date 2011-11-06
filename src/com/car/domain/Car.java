package com.car.domain;

import java.io.Serializable;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entity implementation class for Entity: Car
 */
@Entity
@NamedQueries({
	@NamedQuery(name = Car.QUERY_CAR_BASICS_BY_CARTYPE, query = "SELECT NEW com.car.domain.query.CarBasics(c.id, c.name) FROM Car c WHERE c.carType.id = ?1 ORDER BY c.name"),
})
public class Car implements Serializable {
	
	public static final String QUERY_CAR_BASICS_BY_CARTYPE = "Car.Basics.FindAllByCarType";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	private String description;

	private String image;
	
	@Column(nullable = false)
	private Double dailyFee;
	
	@Column(nullable = false)
	private String currency;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private CarType carType;

	private static final long serialVersionUID = 1L;

	public Car() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public Double getDailyFee() {
		return dailyFee;
	}

	public void setDailyFee(Double dailyFee) {
		this.dailyFee = dailyFee;
	}
	
	public Currency getCurrency() {
		return Currency.getInstance(this.currency);
	}

	public void setCurrency(Currency currency) {
		this.currency = currency.getCurrencyCode();
	}
}
