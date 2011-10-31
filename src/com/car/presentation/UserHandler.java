package com.car.presentation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class UserHandler {
	
	private FacesContext context;
	
	private String user;
	
	@PostConstruct
	private void init() {
		this.context = FacesContext.getCurrentInstance();
		
		ExternalContext exContext = this.context.getExternalContext();
		
		this.user = exContext.getRemoteUser();
	}
	
	public String getUser() {
		return user;
	}
	
	public String logout() {
		ExternalContext exContext = this.context.getExternalContext();
		
		exContext.invalidateSession();
		
		return "login";
	}

}
