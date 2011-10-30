package com.car.presentation;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.EmailValidator;

import com.car.business.remote.CustomerService;
import com.car.domain.Customer;
import com.car.domain.Customer.Gender;

@ManagedBean
@RequestScoped
public class RegistrationHandler {
	
	@EJB
	private CustomerService customerService;
	
	private Gender gender;
	private String surname;
	private String name;
	private String email;
	private Date dateOfBirth;
	private String street;
	private Integer number;
	private String locality;
	private String postalCode;
	private String password;

	@PostConstruct
	public void init() {
		this.postalCode = "D-";
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	/**
	 * Checks if email address is valid and not yet registered.
	 * 
	 * @throws ValidatorException if invalid format or already registered
	 */
	public void validateEmail(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		String email = (String)value;
		EmailValidator validator = EmailValidator.getInstance();
		
		if (!validator.isValid(email))
			throw new ValidatorException( new FacesMessage("EMail address invalid!") );
		
		if (this.customerService.emailExists(email))
			throw new ValidatorException( new FacesMessage("EMail address already used by another customer!") );
	}
	
	/**
	 * Registers new customer.
	 * Returns to login page afterwards.
	 * 
	 * @return navigation action
	 */
	public String registerCustomer() {
		Customer customer = new Customer();
		customer.setGender( this.getGender() );
		customer.setSurname( this.getSurname() );
		customer.setName( this.getName() );
		customer.setDateOfBirth( this.getDateOfBirth() );
		customer.setEmail( this.getEmail() );
		customer.setAddress( String.format("%s %s", this.getStreet(), this.getNumber()) );
		customer.setLocality( this.getLocality() );
		customer.setPostalCode( this.getPostalCode() );
		customer.setPassword( this.getPassword() );
		
		customerService.createCustomer(customer);
		
		return "login";
	}
	
	// TODO: refactor!
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext(); 
		//externalContext.getSessionMap().remove("SomeSessionBean");

		HttpSession session = (HttpSession)externalContext.getSession(true);
		session.invalidate(); 
		
		return "login";
	}
}
