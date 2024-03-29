package br.com.dao.implementacao;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Entidade;
import br.com.repository.interfaces.RepositoryEntidade;

@Repository
public class DaoEntidade extends ImplementacaoCrud<Entidade> implements RepositoryEntidade {

	private static final long serialVersionUID = 1L;

	@Override
	public Date getUltimoAcessoEntidadeLogada(String login) {
		
		SqlRowSet sqlRowSet = super.getJdbcTemplate()
				.queryForRowSet("select ent_ultimoacesso from entidade where ent_inativo is false and ent_login = ?", new Object[] { login });
		// se tiver usu�rio, ele retorna a data e hora de ultimo acesso, se n�o retorna null
		return sqlRowSet.next() ? sqlRowSet.getDate("ent_ultimoacesso") : null;// if tern�rio
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		
		String sql = "update entidade set ent_ultimoacesso = current_timestamp where ent_inativo is false and ent_login = ?";
		
		super.getSimpleJdbcTemplate().update(sql, login);
	}

	@Override
	public boolean existeUsuario(String ent_login) {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select count(1) >= 1 from entidade where ent_login = ")
					 .append("'")
					 .append(ent_login)
					 .append("'");
		
		return super.getJdbcTemplate().queryForObject(stringBuilder.toString(), Boolean.class);//ele retorna true ou false
	}

}
