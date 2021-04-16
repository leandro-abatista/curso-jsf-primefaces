package br.com.project.geral.controller;

import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionControllerImpl implements SessionController {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, HttpSession> hashMap = new HashMap<String, HttpSession>();

	@Override
	public void addSession(String keyLoginUser, HttpSession httpSession) {
		hashMap.put(keyLoginUser, httpSession);
	}

	@Override
	public void invalidateSession(String keyLoginUser) {
		/*Aqui retorna o usu�rio logado na sess�o caso exista*/
		HttpSession sessionUsu�rio = hashMap.get(keyLoginUser);
		
		if (sessionUsu�rio != null) {//remove sess�o do usu�rio passado como par�metro
			try {
				sessionUsu�rio.invalidate();//invalida a sess�o do usu�rio
			} catch (Exception e) {
				e.printStackTrace();
				e.getMessage();
			}
		} else {
			System.out.println("N�o existe usu�rio!!!");
		}
		
		/*ap�s invalidar a sess�o, remove a sess�o do usu�rio*/
		hashMap.remove(keyLoginUser);
	}

}
