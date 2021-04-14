package br.com.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository//repository do spring, interface de conexao com o banco de dados
public interface RepositoryLogin extends Serializable {
	
	/*Passando como par�metros o login e a senha vindos da tela*/
	boolean autentico(String login, String senha) throws Exception;
}
