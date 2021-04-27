package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.project.bean.geral.ObjetoCampoConsulta;

@FacesConverter(forClass = ObjetoCampoConsulta.class)//obrigatório essa anotação
public class ObjetoCampoConsultaConverter implements Converter, Serializable {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return null;
	}

}
