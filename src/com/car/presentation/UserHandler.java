package com.car.presentation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class UserHandler {

	private FacesContext context;

	@PostConstruct
	private void init() {
		this.context = FacesContext.getCurrentInstance();
	}

	public String logout() {
		this.context.getExternalContext().invalidateSession();

		// TODO: might be needed some days
		// externalContext.getSessionMap().remove("SomeSessionBean");

		return "login";
	}
}
