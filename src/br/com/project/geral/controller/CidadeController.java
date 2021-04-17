package br.com.project.geral.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.model.classes.Cidade;
import br.com.repository.interfaces.RepositoryCidade;
import br.com.srv.interfaces.ServiceCidade;

@Controller
public class CidadeController extends ImplementacaoCrud<Cidade> implements InterfaceCrud<Cidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired//injeção de dependência
	private ServiceCidade serviceCidade;
	
	@Autowired//injeção de dependência
	private RepositoryCidade repositoryCidade;

}
