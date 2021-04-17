package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.model.classes.Estado;

@Controller
public class EstadoController extends ImplementacaoCrud<Estado> implements InterfaceCrud<Estado> {

	private static final long serialVersionUID = 1L;
	
	public List<SelectItem> getListaEstado() throws Exception{
		List<SelectItem> lista = new ArrayList<SelectItem>();
		
		List<Estado> estados = super.findListByQueryDinamica(" from Estado");
		
		for (Estado estado : estados) {
			lista.add(new SelectItem(estado, estado.getEst_nome()));//mostra o nome do estado na lista
		}
		return lista;
	}

}
