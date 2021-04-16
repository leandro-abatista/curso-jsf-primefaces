package br.com.srv.implementacao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryEntidade;
import br.com.srv.interfaces.ServiceEntidade;

@Service
public class ServiceEntidadeImpl implements ServiceEntidade {

	private static final long serialVersionUID = 1L;
	
	@Autowired//injeção de dependência
	private RepositoryEntidade repositoryEntidade;

	@Override
	public Date getUltimoAcessoEntidadeLogada(String login) {
		return repositoryEntidade.getUltimoAcessoEntidadeLogada(login);
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		repositoryEntidade.updateUltimoAcessoUser(login);
	}

	@Override
	public boolean existeUsuario(String ent_login) {
		return repositoryEntidade.existeUsuario(ent_login);
	}

}
