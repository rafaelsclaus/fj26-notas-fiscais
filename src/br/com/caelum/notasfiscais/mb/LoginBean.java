package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Model
public class LoginBean implements Serializable{
	private Usuario usuario = new Usuario();

	@Inject
	private UsuarioDao dao;
	
	@Inject
	private UsuarioLogadoBean usuarioLogadoBean;
	
	@Inject
	Event<Usuario> eventoLogin;
	
	public String efetuaLogin() {

		boolean loginValido = dao.existe(this.usuario);
		if (loginValido) {
			eventoLogin.fire(usuario);
			usuarioLogadoBean.logar(usuario);
			return "produto?faces-redirect=true";
		}else {
			return logout();
		}
		
	}
	public String logout() {
		usuarioLogadoBean.deslogar();
		return "login?faces-redirect=true";
	}
	
	
	public Usuario getUsuario(){
		return this.usuario;
	}
}
