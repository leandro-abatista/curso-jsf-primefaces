package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Cidade;
import br.com.project.model.classes.Entidade;
import br.com.project.util.all.Messagens;

@Controller
@Scope("session")
@ManagedBean(name = "funcionarioBeanView")
public class FuncionarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private Entidade objetoSelecionado = new Entidade();

	@Autowired
	private ContextBean contextBean;
	
	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();
	
	private String urlFind = "/cadastro/find_funcionario.jsf?faces-redirect=true";
	
	private String url = "/cadastro/cad_funcionario.jsf?faces-redirect=true";

	@Autowired
	private EntidadeController entidadeController;
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("rel_funcionario");
		super.setNomeRelatorioSaida("rel_funcionario");
		super.setListDataBeanCollectionReport(entidadeController.findList(getClassImplement()));
		return super.getArquivoReport();
	}
	
	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		/*primeiro seta o acesso do usuário*/
		if (!objetoSelecionado.getAcessos().contains("USER")) {
			objetoSelecionado.getAcessos().add("USER");
		}
		
		if (entidadeController.existeCpf(objetoSelecionado.getEnt_cpf())) {
			
			Messagens.msgSeverityInf("CPF já estar cadastrado!");
			return;
		} 
			
		/* e depois é que salva e retorna o objeto selecionado */
		objetoSelecionado = entidadeController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Entidade();
		sucesso();
		
		
	}
	
	@Override
	public void saveEdit() throws Exception {
		objetoSelecionado = entidadeController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Entidade();
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
		objetoSelecionado = new Entidade();
	}
	
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getEnt_codigo() != null && objetoSelecionado.getEnt_codigo() > 0) {
			entidadeController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Entidade();
			Messagens.msgSeverityInf("Registro excluído com sucesso!");
		}
	}
	
	@Override
	protected Class<Entidade> getClassImplement() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return " and entity.tipoEntidade = 'FUNCIONARIO' ";
	}
	
	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new Entidade();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		return urlFind;
	}

	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public CarregamentoLazyListForObject<Entidade> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Entidade> list) {
		this.list = list;
	}

}
