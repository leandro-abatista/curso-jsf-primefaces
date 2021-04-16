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
		/*Aqui retorna o usuário logado na sessão caso exista*/
		HttpSession sessionUsuário = hashMap.get(keyLoginUser);
		
		if (sessionUsuário != null) {//remove sessão do usuário passado como parâmetro
			try {
				sessionUsuário.invalidate();//invalida a sessão do usuário
			} catch (Exception e) {
				e.printStackTrace();
				e.getMessage();
			}
		} else {
			System.out.println("Não existe usuário!!!");
		}
		
		/*após invalidar a sessão, remove a sessão do usuário*/
		hashMap.remove(keyLoginUser);
	}

}
