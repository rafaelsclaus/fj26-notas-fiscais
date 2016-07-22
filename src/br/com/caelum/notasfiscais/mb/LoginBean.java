package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@RequestScoped
@Named
public class LoginBean implements Serializable{
	private Usuario usuario = new Usuario();

	@Inject
	private UsuarioDao dao;
	
	@Inject
	private UsuarioLogadoBean usuarioLogadoBean;
	
	public String efetuaLogin() {

		boolean loginValido = dao.existe(this.usuario);
		if (loginValido) {
			usuarioLogadoBean.logar(usuario);
			return "produto?faces-redirect=true";
		}else {
			usuarioLogadoBean.deslogar();
			return "login?faces-redirect=true";
		}
		
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}
}
