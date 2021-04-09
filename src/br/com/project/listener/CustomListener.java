package br.com.project.listener;

import java.io.Serializable;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Service;

import br.com.framework.util.UtilFramework;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.InformacaoRevisao;

@Service
public class CustomListener implements RevisionListener, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void newRevision(Object revisionEntity) {

		InformacaoRevisao informacaoRevisao = (InformacaoRevisao) revisionEntity;
		Long codigoUsuario = UtilFramework.getThreadLocal().get();//carrega o codigo do usuário
		
		Entidade entidade = new Entidade();
		if (codigoUsuario != null && codigoUsuario != 0L) {
			entidade.setEnt_codigo(codigoUsuario);
			informacaoRevisao.setEntidade(entidade);
		}
	}

}
