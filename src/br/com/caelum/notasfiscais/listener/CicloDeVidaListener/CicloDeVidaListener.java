package br.com.caelum.notasfiscais.listener.CicloDeVidaListener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class CicloDeVidaListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent e) {
		System.out.println("Antes da fase: " + e.getPhaseId());

	}

	@Override
	public void beforePhase(PhaseEvent e) {
		System.out.println("Depois da fase: " + e.getPhaseId());

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
