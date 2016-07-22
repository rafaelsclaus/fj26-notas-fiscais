package br.com.caelum.notasfiscais.util;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped 
public class JPAUtil implements Serializable{
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("notas");

	@PreDestroy
	public void fechaFactory() {
		emf.close();
	}
	
	@Produces @RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close(@Disposes EntityManager manager){
		manager.close();
	}
}