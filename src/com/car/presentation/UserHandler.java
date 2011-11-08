package com.car.presentation;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.car.business.remote.RentalService;

@ManagedBean
@SessionScoped
@WebListener
public class UserHandler implements HttpSessionListener {

	@EJB
	private RentalService rentalService;

	public RentalService getRentalService() {
		return rentalService;
	}
	
	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se) {
    	Logger.getLogger(UserHandler.class.getName()).log(Level.INFO, "Customer entered RentACar. Welcome!");
    }

	/**
	 * Ensures that exit() is called.
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se) {
    	Logger.getLogger(UserHandler.class.getName()).log(Level.INFO, "Customer left RentACar. Goodbye!");

    	this.exit();
    }
    
	/**
	 * Ensures that an ejbRemove() call is made to the stateful session bean.
	 */
    private void exit() {
    	System.out.println("Session killed!"); // TODO: replace with ejbRemove() call
    }

	/**
	 * Invalidates current user session and redirects to the login page.
	 */
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "login";
	}
}
