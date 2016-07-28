package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@ViewScoped
@Named
public class ListaNotasFiscaisBean {
	
	@Inject
	private NotaFiscalDao dao;
	
	public List<NotaFiscal> getNotasFiscais() {
		return dao.listaTodos();
	}
	
	
}
