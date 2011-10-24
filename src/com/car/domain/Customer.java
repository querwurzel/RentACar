package com.car.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Customer
 * 
 */
@Entity
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Character gender;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dateOfBirth;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String locality;

	@Column(nullable = false, length = 7)
	private String postalCode;

	@Column(nullable = false, length = 32)
	private String password;

	private static final long serialVersionUID = 1L;

	public Customer() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public Character getGender() {
		return this.gender;
	}

	public void Character(Character gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocality() {
		return this.locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
