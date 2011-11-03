package com.car.presentation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UserHandler {

	private FacesContext context;

	@PostConstruct
	@SuppressWarnings("unused")
	private void init() {
		this.context = FacesContext.getCurrentInstance();
	}

	public String logout() {
		this.context.getExternalContext().invalidateSession();

		return "login";
	}
}
