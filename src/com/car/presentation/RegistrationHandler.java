package com.car.presentation;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.car.business.remote.CustomerService;

@ManagedBean
@RequestScoped
public class RegistrationHandler {
	
	@EJB
	private CustomerService customerService;
	
	private Character gender;
	private String surname;
	private String name;
	private String email;
	private Date dateOfBirth;
	private String street;
	private String number;
	private String locality;
	private String postalCode;

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * 
	 * @return navigation action
	 */
	public String registerCustomer() {
		
		System.out.println("calling customer service");
		
		customerService.createCustomer();
		
		System.out.println("called customer service");
		
		//TODO: return "login";
		return "register";
	}
}
