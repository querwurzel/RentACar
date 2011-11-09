package com.car.presentation;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.car.business.remote.RentalService;

@ManagedBean
@SessionScoped
public class UserHandler {

	@EJB
	private RentalService rentalService;

	public RentalService getRentalService() {
		return this.rentalService;
	}

	/**
	 * Invalidates current user session and redirects to the login page.
	 */
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "login";
	}
}
