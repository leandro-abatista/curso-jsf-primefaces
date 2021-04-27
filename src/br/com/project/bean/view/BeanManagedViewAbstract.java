package br.com.project.bean.view;

import org.springframework.stereotype.Component;

import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.bean.geral.ObjetoCampoConsulta;
import br.com.project.report.util.BeanReportView;

@Component
public abstract class BeanManagedViewAbstract extends BeanReportView {

	private static final long serialVersionUID = 1L;
	
	protected abstract Class<?> getClassImplement();
	
	protected abstract InterfaceCrud<?> getController();
	
	public ObjetoCampoConsulta objetoCampoConsultaSelecionado;

	public ObjetoCampoConsulta getObjetoCampoConsultaSelecionado() {
		return objetoCampoConsultaSelecionado;
	}

	public void setObjetoCampoConsultaSelecionado(ObjetoCampoConsulta objetoCampoConsultaSelecionado) {
		this.objetoCampoConsultaSelecionado = objetoCampoConsultaSelecionado;
	}
	
}
