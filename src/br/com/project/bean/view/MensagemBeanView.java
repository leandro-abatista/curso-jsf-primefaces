package br.com.project.bean.view;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.geral.controller.MensagemController;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.Mensagem;

@Controller
@Scope(value = "session")
@ManagedBean(name = "mensagemBeanView")
public class MensagemBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private Mensagem objetoSelecionado = new Mensagem();
	
	@Autowired
	private ContextBean contextBean;
	
	@Autowired
	private EntidadeController entidadeController;
	
	@Autowired
	private MensagemController mensagemController;
	
	@Override
	public String novo() throws Exception {
		objetoSelecionado = new Mensagem();
		//carrega o usu�rio logado e seta o usu�rio de origem no objeto selecionado
		objetoSelecionado.setUsu_origem(contextBean.getEntidadeLogada());
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		
		if (objetoSelecionado.getUsu_destino().getEnt_codigo().equals(objetoSelecionado.getUsu_origem().getEnt_codigo())) {
			addMsg("Usu�rio de 'destino' n�o pode ser igual ao usu�rio de 'origem'!");
			
			return;
		}
		
		mensagemController.merge(objetoSelecionado);
		novo();
		addMsg("Mensagem enviada com sucesso!");
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "**/buscarUsuarioDestinoMsg")
	public void buscarUsuarioDestinoMsg(HttpServletResponse httpServletResponse, 
										@RequestParam(value = "codEntidade") Long codEntidade) throws Exception {
		
		Entidade entidade = entidadeController.findByPorId(Entidade.class, codEntidade);
		
		if (entidade != null) {
			objetoSelecionado.setUsu_destino(entidade);
			httpServletResponse.getWriter().write(entidade.getJson().toString());
		}
	}

	@Override
	protected Class<?> getClassImplement() {
		return null;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return null;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Mensagem getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Mensagem objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	
}
