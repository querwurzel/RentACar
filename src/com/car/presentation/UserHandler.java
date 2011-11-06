package com.car.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class UserHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "login";
	}
}
