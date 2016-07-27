package br.com.caelum.notasfiscais.listener.Autorizador;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.mb.UsuarioLogadoBean;

public class Autorizador implements PhaseListener {

	@Inject
	private UsuarioLogadoBean usuarioLogado;

	@Inject
	private NavigationHandler navigationHandler;
	
	@Inject
	private FacesContext context;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		
		
		if ("/login.xhtml".equals(context.getViewRoot().getViewId())) {
			return;
		}
		
		// Verificacao
		if (!usuarioLogado.isLogado()) {
			
			navigationHandler.handleNavigation(context, null, "login?faces-redirect=true");
			
			//Efetua a reenderizacao da tela
			context.renderResponse();
		}
		
		if ("/index.xhtml".equals(context.getViewRoot().getViewId())) {
			return ;
		}
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
