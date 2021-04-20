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
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		list.clear();//limpa a lista
		//O merge faz o retorno do objeto salvo
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		list.add(objetoSelecionado);//coloca na lista somente o objeto selecionado
		objetoSelecionado = new Cidade();//inicia um novo objeto e limpa
		sucesso();//mensagem
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
	public void saveEdit() throws Exception {
		//faz algum processamento
		saveNotReturn();
	}
	
	@Override
	public String editar() throws Exception {
		list.clear();//limpa a lista
		return url;
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Cidade) cidadeController.getSession().get(Cidade.class, objetoSelecionado.getCid_codigo());
		cidadeController.delete(objetoSelecionado);
		list.remove(objetoSelecionado);
		novo();
		sucesso();
	}
	
	public Cidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Cidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
}
