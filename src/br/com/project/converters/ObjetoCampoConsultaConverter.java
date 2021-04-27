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
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		if (value != null && !value.isEmpty()) {//aqui retorna para o lado do servidor
			String[] valores = value.split("\\*");
			ObjetoCampoConsulta objetoCampoConsulta = new ObjetoCampoConsulta();
			objetoCampoConsulta.setCampoBanco(valores[0]);
			objetoCampoConsulta.setTipoClass(valores[1]);
			
			return objetoCampoConsulta;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		
		if (objeto != null) {
			ObjetoCampoConsulta c = (ObjetoCampoConsulta) objeto;
			
			return c.getCampoBanco() + "*" + c.getTipoClass();//aqui retorna para o combo
		}
		return null;
	}

}
