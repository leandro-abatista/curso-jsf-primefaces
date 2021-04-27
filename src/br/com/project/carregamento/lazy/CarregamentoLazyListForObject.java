package br.com.project.carregamento.lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.framework.controller.crud.Controller;
import br.com.project.listener.ContextLoaderListenerCaixakiUtils;

public class CarregamentoLazyListForObject<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;
	
	private List<T> list = new ArrayList<T>();
	
	private Integer totalRegistroConsulta = 0;
	
	private String query = null;
	
	private Controller controller = (Controller) ContextLoaderListenerCaixakiUtils.getBean(Controller.class);
	
	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		// TODO Auto-generated method stub
		return super.load(first, pageSize, sortField, sortOrder, filters);
	}

}
