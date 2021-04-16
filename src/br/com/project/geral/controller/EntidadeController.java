package br.com.project.geral.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;
import br.com.srv.interfaces.ServiceEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired//inje��o de depend�ncia
	private ServiceEntidade serviceEntidade;
	
	public Entidade findUserLogado(String usuarioLogado) throws Exception {
		return super.findUniqueByProperty(Entidade.class, usuarioLogado, " ent_login ", " and entity.ent_inativo is false");
	}
	
	public Date getUltimoAcessoEntidadeLogada(String login) {
		return serviceEntidade.getUltimoAcessoEntidadeLogada(login);
	}

	public void updateUltimoAcessoUser(String name) {
		serviceEntidade.updateUltimoAcessoUser(name);
	}

}