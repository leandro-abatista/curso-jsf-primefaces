package br.com.project.bean.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.geral.controller.TituloController;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.Titulo;
import br.com.project.util.all.Messagens;

@Controller
@Scope("view")
@ManagedBean(name = "tituloBeanView")
public class TituloBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ContextBean contextBean;
	
	@Autowired
	private TituloController tituloController;
	
	@Autowired
	private EntidadeController entidadeController;
	
	private Titulo objetoSelecionado = new Titulo();//inicia uma instância de título

	private CarregamentoLazyListForObject<Titulo> list = new CarregamentoLazyListForObject<Titulo>();
	
	private String urlFind = "/cadastro/find_titulo.jsf?faces-redirect=true";
	
	private String url = "/cadastro/cad_titulo.jsf?faces-redirect=true";
	
	@PostConstruct
	public void init() throws Exception {
		objetoSelecionado.setEnt_codigoAbertura(contextBean.getEntidadeLogada());
	}
	
	public List<Entidade> pesquisarPagador(String nome) throws Exception{
		return entidadeController.pesquisarPorNome(nome);
	}

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("rel_titulo");
		super.setNomeRelatorioSaida("rel_titulo");
		super.setListDataBeanCollectionReport(tituloController.findList(getClassImplement()));
		return super.getArquivoReport();
	}
	
	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		/* e depois é que salva e retorna o objeto selecionado */
		objetoSelecionado = tituloController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Titulo();
		sucesso();
	}
	
	@Override
	public void saveEdit() throws Exception {
		objetoSelecionado = tituloController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Titulo();
		Messagens.msgSeverityInf("Registro atualizado com sucesso!");
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();//limpa a lista
		return url;
	}
	
	@Override
	public void setarVariaveisNulas() throws Exception {
		list.clean();
		objetoSelecionado = new Titulo();
	}
	
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getTit_codigo() != null && objetoSelecionado.getTit_codigo() > 0) {
			tituloController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Titulo();
			Messagens.msgSeverityInf("Registro excluído com sucesso!");
		}
	}
	
	@Override
	protected Class<Titulo> getClassImplement() {
		return Titulo.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return tituloController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}
	
	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new Titulo();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		return urlFind;
	}

	public Titulo getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Titulo objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public CarregamentoLazyListForObject<Titulo> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Titulo> list) {
		this.list = list;
	}

}
