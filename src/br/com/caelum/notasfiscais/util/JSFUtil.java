package br.com.caelum.notasfiscais.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

public class JSFUtil {

	@Produces @RequestScoped
	public FacesContext FacesContext(){
		return FacesContext.getCurrentInstance();
	}
	
	
	@Produces @RequestScoped
	public NavigationHandler navigationHandler(){
		return FacesContext().getApplication().getNavigationHandler();
	}
	
	
	
}
