package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.project.bean.geral.ObjetoCampoConsulta;

/**
 * Responsavel por efetuar a conversão nos combos de campos de consulta
 * @author leandro
 *
 */
@FacesConverter(forClass = ObjetoCampoConsulta.class)//obrigatório essa anotação
public class ObjetoCampoConsultaConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		if (value != null && !value.isEmpty()) {//aqui retorna para o lado do servidor
			String[] valores = value.split("\\*");
			ObjetoCampoConsulta objetoCampoConsulta = new ObjetoCampoConsulta();
			objetoCampoConsulta.setCampoBanco(valores[0]);
			objetoCampoConsulta.setTipoClass(valores[1]);
			
			return objetoCampoConsulta;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if (value != null) {
			
			ObjetoCampoConsulta c = (ObjetoCampoConsulta) value;
			
			return c.getCampoBanco() + "*" + c.getTipoClass();//aqui retorna para o combo
		} else {
			return "Não foi possível estabelecer o valor!";
		}
	}

}
