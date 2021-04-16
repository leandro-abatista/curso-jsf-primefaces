package br.com.framework.interfaces.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional// Essa anotação identifica que ela realiza transação com o banco de dados
public interface InterfaceCrud<T> extends Serializable {
	
	/*Por padrão, na classe de interface, os métodos são public e por isso não precisa colocar*/
	
	/*salva os dados*/
	void save(T obj) throws Exception;
	
	void persist(T obj) throws Exception;
	
	/*salva ou atualiza os dados*/
	void saveOrUpdate(T obj) throws Exception;
	
	/*atualiza os dados*/
	void update(T obj) throws Exception;
	
	/*deleta os dados*/
	void delete(T obj) throws Exception;
	
	/*salva qualquer objeto e retorna qualquer objeto
	 * ele recebe o obejto e retorna o objeto salvo*/
	T merge(T obj) throws Exception;
	
	/*carrega uma lista de dados d eum objeto qualquer*/
	List<T> findList(Class<T> obj) throws Exception;
	
	Object findById(Class<T> obj, Long id) throws Exception;
	
	T findByPorId(Class<T> obj, Long id) throws Exception;
	
	List<T> findListByProperty(Class<T> entidade, Object atributo, Object valor) throws Exception;
	
	List<T> findListByQueryDinamica(String s) throws Exception;
	
	List<T> findListByListDeIds(Class<T> obj, List<Long> cods) throws Exception;
	
	/*executa update com hql*/
	void executeUpdateQueryDinamica(String s) throws Exception;
	
	/*executa update com sql*/
	void executeUpdateSQLDinamica(String s) throws Exception;
	
	/*limpa a sessão do hibernate*/
	void clearSession() throws Exception;
	
	/*retira um objeto da sessão do hibernate*/
	void evict(Object obj) throws Exception;
	
	Session getSession() throws Exception;
	
	List<?> getListSQLDinamica(String sql) throws Exception;
	
	T carregar(Class<T> class1, Long long1) throws Exception;

	Class<T> getClass(Class<T> entidade) throws Exception;

	List<T> findListByLike(Class<T> entidade, String atributoClass, String valor) throws Exception;

	List<T> findByPropertyId(Class<T> entidade, Long id, Object atributo) throws Exception;
	
	JdbcTemplate getJdbcTemplate();
	
	SimpleJdbcTemplate getSimpleJdbcTemplate();

	SimpleJdbcInsert getSimpleJdbcInsert();
	
	T findUninqueByProperty(Class<T> entidade, Object valor, String atributo, String condicao) throws Exception;
	
	Long totalRegistro(String tabela) throws Exception;
	
	Query obterQuery(String query) throws Exception;
	
	/*carregamento dinâmico com JSF e Primefaces*/
	List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception; 
	
	List<Object[]> getListSQLDinamicArray(String sql) throws Exception;
}
