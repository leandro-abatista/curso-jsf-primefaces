package br.com.project.bean.view;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.bean.geral.EntidadeAtualizarSenhaBean;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	@Autowired
	private EntidadeController entidadeController;

	private EntidadeAtualizarSenhaBean entidadeAtualizarSenhaBean = new EntidadeAtualizarSenhaBean();// sempre
																										// instanciar

	@Autowired
	private ContextBean contextBean;

	/**
	 * Retorna da classe ContextBean
	 * 
	 * @return contextBean
	 */
	public String getUsuarioLogadoSecurity() {
		return contextBean.getAuthentication().getName();
	}

	public Date getUltimoAcesso() throws Exception {
		return contextBean.getEntidadeLogada().getEnt_ultimoAcesso();

	}
	
	public void updateSenha() throws Exception {
		Entidade entidadeLogada = contextBean.getEntidadeLogada();
		
		/*o primeiro if verifica se a senha atual que foi digitada, é a mesma que o usuário está logado no sistema
		 * se for diferente, uma mensagem é enviada para o usuário*/
		if (!entidadeAtualizarSenhaBean.getSenhaAtual().equals(entidadeLogada.getEnt_senha())) {
			addMsg("A senha atual não é válida!");
			return;
		} else
			
		if (entidadeAtualizarSenhaBean.getSenhaAtual().equals(entidadeAtualizarSenhaBean.getNovaSenha())) {
			addMsg("A senha atual não pode ser igual a nova senha!");
			return;
		} else
			
		if (!entidadeAtualizarSenhaBean.getNovaSenha().equals(entidadeAtualizarSenhaBean.getConfirmarSenha())) {
			addMsg("A nova senha e confirmar nova senha, não conferem!");
			return;
		} else {
			
			entidadeLogada.setEnt_senha(entidadeAtualizarSenhaBean.getNovaSenha());
			entidadeController.saveOrUpdate(entidadeLogada);
			entidadeLogada = entidadeController.findByPorId(Entidade.class, entidadeLogada.getEnt_codigo());
			
			if (entidadeLogada.getEnt_senha().equals(entidadeAtualizarSenhaBean.getNovaSenha())) {
				sucesso();
			} else {
				addMsg("A nova senha não foi atualizada!");
				error();
			}
		}
	}

	@Override
	protected Class<Entidade> getClassImplement() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return null;
	}

	public EntidadeAtualizarSenhaBean getEntidadeAtualizarSenhaBean() {
		return entidadeAtualizarSenhaBean;
	}

	public void setEntidadeAtualizarSenhaBean(EntidadeAtualizarSenhaBean entidadeAtualizarSenhaBean) {
		this.entidadeAtualizarSenhaBean = entidadeAtualizarSenhaBean;
	}

}
