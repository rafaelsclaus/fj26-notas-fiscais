package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transational;

@Named
@ConversationScoped
public class ConversationNotaFiscalBeam implements Serializable{

	private NotaFiscal notaFiscal = new NotaFiscal();
	
	@Inject
	private Conversation conversation;
	
	@Inject
	private NotaFiscalDao notaFiscalDao;
	
	private Item item = new Item();
	private Long idProduto;
	
	@Inject
	private ProdutoDao produtoDao;
	

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	@Transational
	public String gravar() {
		this.notaFiscalDao.adiciona(notaFiscal);
		conversation.end();
		this.notaFiscal = new NotaFiscal();
		return "notaFiscalCabecalho?faces-redirect=true";
	}
	
	public String avancar(){
		if (conversation.isTransient()) {
			conversation.begin();
		}
		return "notaFiscalItens?faces-redirect=true";
	}
	
	public NotaFiscal getNotaFiscal(){
		return notaFiscal;
	}
	@Transational
	public void guardaItem(){
		Produto produto = produtoDao.buscaPorId(idProduto);
		
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		
		notaFiscal.getItens().add(item);
		item.setNotaFiscal(notaFiscal);
		
		item = new Item();
		idProduto = null;
		
	}
}
