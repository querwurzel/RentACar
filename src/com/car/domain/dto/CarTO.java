package com.car.domain.dto;

import java.io.Serializable;

public class CarTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private String image;
	private Double dailyFee;

	public CarTO() {
		super();
	}

	public CarTO(Long id, String name) {
		this();
		this.id = id;
		this.name = name;
	}
	
	public CarTO(Long id, String name, String description, String image, Double dailyFee) {
		this(id, name);
		this.setDescription(description);
		this.setImage(image);
		this.setDailyFee(dailyFee);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
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

	public Double getDailyFee() {
		return this.dailyFee;
	}

	public void setDailyFee(Double dailyFee) {
		this.dailyFee = dailyFee;
	}
}
