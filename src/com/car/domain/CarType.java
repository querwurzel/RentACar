package com.car.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entity implementation class for Entity: CarType
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = CarType.QUERY_CARTYPE_BASICS, query = "SELECT NEW com.car.domain.query.CarTypeBasics(ct.id, ct.name) FROM CarType ct ORDER BY ct.name")
})
public class CarType implements Serializable {
	
	public static final String QUERY_CARTYPE_BASICS = "CarType.AllCarTypeBasics";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	private static final long serialVersionUID = 1L;

	public CarType() {
		super();
	}
	
	public CarType(String name) {
		this();
		this.setName(name);
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
