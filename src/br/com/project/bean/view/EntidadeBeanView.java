package br.com.project.bean.view;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.project.bean.geral.BeanManagedViewAbstract;

@Controller
@Scope(value = "session")
@ManagedBean(name = "entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired//inje��o de depend�ncia
	private ContextBean contextBean;

	public String getUsuarioLogadoSecurity() {
		return contextBean.getAuthentication().getName();
	}
	
	public Date getUltimoAcesso() throws Exception {
		return contextBean.getEntidadeLogada().getEnt_ultimoAcesso();
	}

}
