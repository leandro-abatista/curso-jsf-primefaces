package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Entidade;

@FacesConverter(forClass = Entidade.class, value = "entidadeConverter")
public class EntidadeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * M�todo respons�vel por receber o c�digo do objeto
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		
		if (codigo != null && !codigo.isEmpty()) {
			return (Entidade) HibernateUtil.getCurrentSession().get(Entidade.class, new Long(codigo));
		}
		return codigo;// se tiver tudo ok, retorna o c�digo
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		
		if (objeto != null) {
			Entidade entidade = (Entidade) objeto;
			return entidade.getEnt_codigo() != null && entidade.getEnt_codigo() > 0 ? entidade.getEnt_codigo().toString() : null;
		}
		return null;
	}

}
