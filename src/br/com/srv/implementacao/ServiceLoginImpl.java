package br.com.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryLogin;
import br.com.srv.interfaces.ServiceLogin;

@Service
public class ServiceLoginImpl implements ServiceLogin {

	private static final long serialVersionUID = 1L;
	
	@Autowired//inje��o de depend�ncia
	private RepositoryLogin repositoryLogin;

	@Override
	public boolean autentico(String login, String senha) throws Exception {
		
		return repositoryLogin.autentico(login, senha);
	}

}
