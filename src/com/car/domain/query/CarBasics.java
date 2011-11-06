package com.car.domain.query;

import java.io.Serializable;

public class CarBasics implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Long id;
	private final String name;

	public CarBasics(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
