package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.CidadeController;
import br.com.project.model.classes.Cidade;

@Controller//do spring
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";
	
	private Cidade objetoSelecionado = new Cidade();
	
	private List<Cidade> list = new ArrayList<Cidade>();
	
	@Autowired//injeção de dependência
	private CidadeController cidadeController;
	
	@Override
	public String save() throws Exception {
		//O merge faz o retorno do objeto salvo
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		novo();
		return "";
	}
	
	/**
	 * Método para pegar todas as cidades cadastradas no banco de dados
	 * @returna uma lista de cidades
	 * @throws Exception
	 */
	public List<Cidade> getList() throws Exception {
		list = cidadeController.findList(Cidade.class);
		return list;
	}
	
	/**
	 * retorna um novo objeto
	 */
	@Override
	public String novo() throws Exception {
		objetoSelecionado = new Cidade();
		return url;
	}
	
	@Override
	public String editar() throws Exception {
		
		return url;
	}
	
	@Override
	public void excluir() throws Exception {
		cidadeController.delete(objetoSelecionado);
		list.remove(objetoSelecionado);
		novo();
		
	}
	
	public Cidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Cidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
}
