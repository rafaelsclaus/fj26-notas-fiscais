package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transational;



@Named @RequestScoped
public class ProdutoBean{
	private Produto produto = new Produto();
	private List<Produto> produtos;
	
	@Inject
	private ProdutoDao dao;
	
	public List<Produto> getProdutos(){
		if (produtos == null) {
			System.out.println("Carregando produtos...");
			produtos = dao.listaTodos();
		}
		return produtos;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Transational
	public void grava(){
		System.out.println("Sera que vai passar por aqui?");
		
		if(produto.getId() == null){
			dao.adiciona(produto);
		}else{
			dao.atualiza(produto);
		}
		this.produtos = dao.listaTodos();
		this.produto = new Produto();
	}
	
	@Transational
	public void remove(Produto produto){
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}
	

}
