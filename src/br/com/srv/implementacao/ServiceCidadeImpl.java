package br.com.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryCidade;
import br.com.srv.interfaces.ServiceCidade;

@Service
public class ServiceCidadeImpl implements ServiceCidade {

	private static final long serialVersionUID = 1L;
	
	@Autowired//injeção de dependência
	private RepositoryCidade repositoryCidade;

}
