package br.com.project.geral.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.srv.interfaces.ServiceEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired//injeção de dependência
	private ServiceEntidade serviceEntidade;
	
	public void updateUltimoAcessoUser(String name) {
		serviceEntidade.updateUltimoAcessoUser(name);
	}
	
	public Date getUltimoAcessoEntidadeLogada(String login) {
		return serviceEntidade.getUltimoAcessoEntidadeLogada(login);
	}

}
