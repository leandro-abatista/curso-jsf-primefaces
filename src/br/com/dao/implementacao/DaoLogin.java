package br.com.dao.implementacao;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.repository.interfaces.RepositoryLogin;

@Repository
public class DaoLogin extends ImplementacaoCrud<Object> implements RepositoryLogin {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean autentico(String login, String senha) throws Exception {
		
		/*SQL normal*/
		String sql = "select count(1) as autentica from entidade where ent_login = ? and ent_senha = ?";
		/*SQL do spring | Passa como parâmetros o sql e o object em forma de array, 
		 * o login na primeira posição e senha na segunda posição*/
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql, new Object[] {login, senha});
		/*O resultado é o seguinte: se sqlRowSet retornar algum resultado e o autentica for maior que 0, ok, se não retorna false*/
		return sqlRowSet.next() ? sqlRowSet.getInt("autentica") > 0 : false;
	}

}
