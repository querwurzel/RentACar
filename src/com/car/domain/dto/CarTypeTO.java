package com.car.domain.dto;

import java.io.Serializable;

public class CarTypeTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public CarTypeTO() {
		super();
	}

	public CarTypeTO(Long id, String name) {
		this();
		this.id = id;
		this.name = name;
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
}
