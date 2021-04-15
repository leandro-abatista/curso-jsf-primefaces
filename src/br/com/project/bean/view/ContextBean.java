package br.com.project.bean.view;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.project.model.classes.Entidade;

@Scope(value = "session")
@Component(value = "contextBean")//com essa anotação, essa classe pode ser usada em qualquer parte do sistema
public class ContextBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Retorna todas informações do usuário logado
	 * 
	 * @return Authentication
	 */
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public Entidade getEntidadeLogada() throws Exception {
		
	}

}
