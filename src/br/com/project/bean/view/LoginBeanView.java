package br.com.project.bean.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.SessionController;
import br.com.srv.interfaces.ServiceLogin;

@Controller // spring
@Scope(value = "request")
@ManagedBean(name = "loginBeanView")
public class LoginBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	@Autowired//injeção de dependência
	private SessionController sessionController;
	
	@Autowired//injeção de dependência
	private ServiceLogin serviceLogin;

	private String username;
	private String password;
	
	/**
	 * Método para invalidar a sessão
	 */
	@RequestMapping(value = "**/invalidar_session", method = RequestMethod.POST)
	public void invalidarSessionMetodo(HttpServletRequest httpServletRequest) throws Exception {
		
		String usuarioLogadoSessao = null;
		
		if (httpServletRequest.getUserPrincipal() != null) {
			usuarioLogadoSessao = httpServletRequest.getUserPrincipal().getName();
		}
		/*O trim -> tira os espaços*/
		if (usuarioLogadoSessao == null || (usuarioLogadoSessao != null && usuarioLogadoSessao.trim().isEmpty())) {
			usuarioLogadoSessao = httpServletRequest.getRemoteUser();
		}
		
		if (usuarioLogadoSessao != null && usuarioLogadoSessao.isEmpty()) {
			sessionController.invalidateSession(usuarioLogadoSessao);
		}
	}

	public void invalidar(ActionEvent evento) throws Exception {
		//System.out.println("Chamou o invalidar");
		RequestContext requestContext = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		boolean loggedIn = true;//por padrão é true
		
		if (serviceLogin.autentico(getUsername(), getPassword())) {
			sessionController.invalidateSession(getUsername());
			loggedIn = true;//se passar true aqui porque deu certo
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Acesso Negado!, Login e/ou Senha incorretos!", "");
		}
		
		if (message != null) {
			FacesContext.getCurrentInstance().addMessage("msg", message);
		}
		
		requestContext.addCallbackParam("loggedIn", loggedIn);//posso retornar uma mensagem ou redirecionar pra outra página
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	protected Class<?> getClassImplement() {
		return null;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return null;
	}

}
