package com.car.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class UserHandler {

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "login";
	}
}
