package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.datamodel.DataModelNotasFiscais;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@ViewScoped
@Named
public class ListaNotasFiscaisBean implements Serializable{
	
	@Inject
	private NotaFiscalDao dao;
	
	@Inject
	private DataModelNotasFiscais dataModel;
	
	public List<NotaFiscal> getNotasFiscais() {
		return dao.listaTodos();
	}
	
	public DataModelNotasFiscais getDataModel() {
		return dataModel;
	}
}
