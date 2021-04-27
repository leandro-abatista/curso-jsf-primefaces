package br.com.project.bean.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Component;

import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.bean.geral.ObjetoCampoConsulta;
import br.com.project.report.util.BeanReportView;

@Component
public abstract class BeanManagedViewAbstract extends BeanReportView {

	private static final long serialVersionUID = 1L;
	
	protected abstract Class<?> getClassImplement();
	
	protected abstract InterfaceCrud<?> getController();
	
	public ObjetoCampoConsulta objetoCampoConsultaSelecionado;
	
	public List<SelectItem> listaSelectItemsCampoPesquisa;

	public ObjetoCampoConsulta getObjetoCampoConsultaSelecionado() {
		return objetoCampoConsultaSelecionado;
	}

	public void setObjetoCampoConsultaSelecionado(ObjetoCampoConsulta objetoCampoConsultaSelecionado) {
		
		if (objetoCampoConsultaSelecionado != null) {
			
			for (Field field : getClassImplement().getDeclaredFields()) {
				
				if (field.isAnnotationPresent(IdentificaCampoPesquisa.class)) {
					
					if (objetoCampoConsultaSelecionado.getCampoBanco().equalsIgnoreCase(field.getName())) {
						String descricaoCampo = field.getAnnotation(IdentificaCampoPesquisa.class).descricaoCampo();
						objetoCampoConsultaSelecionado.setDescricao(descricaoCampo);
						objetoCampoConsultaSelecionado.setTipoClass(field.getType().getCanonicalName());
						objetoCampoConsultaSelecionado.setPrincipal(field.getAnnotation(IdentificaCampoPesquisa.class).principal());
						
						break;//se encontrar o objeto desejado o break para o processo
					}
				}
				
			}
		}
		
		this.objetoCampoConsultaSelecionado = objetoCampoConsultaSelecionado;
	}
	
	public List<SelectItem> getListaSelectItemsCampoPesquisa() {
		
		listaSelectItemsCampoPesquisa = new ArrayList<SelectItem>();
		
		List<ObjetoCampoConsulta> listTemporaria = new ArrayList<ObjetoCampoConsulta>();
		
		for (Field field : getClassImplement().getDeclaredFields()) {
			
			if (field.isAnnotationPresent(IdentificaCampoPesquisa.class)) {//identifica se existe essa anotação na classe de cidade
				
				String descricaoCampo = field.getAnnotation(IdentificaCampoPesquisa.class).descricaoCampo();
				String descricaoCampoPesquisa = field.getAnnotation(IdentificaCampoPesquisa.class).campoConsulta();
				int isPrincipal = field.getAnnotation(IdentificaCampoPesquisa.class).principal();	
				
				ObjetoCampoConsulta objetoCampoConsulta = new ObjetoCampoConsulta();
				objetoCampoConsulta.setDescricao(descricaoCampo);
				objetoCampoConsulta.setCampoBanco(descricaoCampoPesquisa);
				objetoCampoConsulta.setTipoClass(field.getType().getCanonicalName());
				objetoCampoConsulta.setPrincipal(isPrincipal);
				
				listTemporaria.add(objetoCampoConsulta);
			}
		}
		
		orderReverse(listTemporaria);
		
		for (ObjetoCampoConsulta objetoCampoConsulta : listTemporaria) {
			//transforma a lista de objetoCampoConsulta em um selectItem
			listaSelectItemsCampoPesquisa.add(new SelectItem(objetoCampoConsulta));
		}
		
		return listaSelectItemsCampoPesquisa;
	}

	private void orderReverse(List<ObjetoCampoConsulta> listTemporaria) {
		Collections.sort(listTemporaria, new Comparator<ObjetoCampoConsulta>() {

			@Override
			public int compare(ObjetoCampoConsulta objeto1, ObjetoCampoConsulta objeto2) {
				
				return objeto1.getPrincipal().compareTo(objeto2.getPrincipal());
			}
		});
		
	}
	
	
	
}
