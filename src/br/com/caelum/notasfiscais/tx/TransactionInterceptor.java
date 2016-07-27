package br.com.caelum.notasfiscais.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;


@Interceptor
@Transational
public class TransactionInterceptor implements Serializable {
	
	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object intercept(InvocationContext ctx) throws Exception{
		
		System.out.println("Abrindo a Transacao");
		
		manager.getTransaction().begin();
		
		// Passando para o JSF tratar a requisicao e pegando o
		// retorno da logica
		
		Object resultado = ctx.proceed();
		System.out.println("Comitando a transacao");
		
		return resultado;
	}
}
