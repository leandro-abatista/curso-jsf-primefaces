package br.com.project.geral.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;
import br.com.srv.interfaces.ServiceEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired//injeção de dependência
	private ServiceEntidade serviceEntidade;
	
	@SuppressWarnings("unchecked")
	public Entidade findUserLogado(String usuarioLogado) throws Exception {
		
		List<Entidade> entidades = (List<Entidade>) 
				super.findUniqueByProperty(Entidade.class, usuarioLogado, "ent_login", " and entity.ent_inativo is false");
		
		if (entidades != null && !entidades.isEmpty()) {
			return entidades.get(0);//retorna o primeiro objeto. Na lista começa com 0 a primeira posição.
		}
		
		return null;
		
	}
	
	public Date getUltimoAcessoEntidadeLogada(String login) {
		return serviceEntidade.getUltimoAcessoEntidadeLogada(login);
	}

	public void updateUltimoAcessoUser(String name) {
		serviceEntidade.updateUltimoAcessoUser(name);
	}

}