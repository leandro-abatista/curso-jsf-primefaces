package br.com.framework.implementacao.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.interfaces.crud.InterfaceCrud;

@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {

	private static final long serialVersionUID = 1L;
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Autowired
	private JdbcTemplateImpl jdbcTemplate;

	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;

	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsert;
	
	@Autowired
	protected SimpleJdbcClassImpl simpleJdbcClassImpl;
	

	// =========================================TEMPLATES DE OPERA��ES DE CRUD EM JDBC SPRING==============================================================
	public JdbcTemplateImpl getJdbcTemplate() {
		return jdbcTemplate;
	}

	public SimpleJdbcTemplateImpl getSimpleJdbcTemplate() {
		return simpleJdbcTemplate;
	}

	public SimpleJdbcInsertImpl getSimpleJdbcInsert() {
		return simpleJdbcInsert;
	}
	
	public SimpleJdbcClassImpl getSimpleJdbcClassImpl() {
		return simpleJdbcClassImpl;
	}
	
	// =========================================TEMPLATES DE OPERA��ES DE CRUD EM JDBC SPRING==============================================================

	@Override
	public void save(T obj) throws Exception {
		validarSessionfactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	@Override
	public void persist(T entidade) throws Exception {
		validarSessionfactory();
		sessionFactory.getCurrentSession().persist(entidade);
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T entidade) throws Exception {
		validarSessionfactory();
		sessionFactory.getCurrentSession().saveOrUpdate(entidade);
		executeFlushSession();
	}

	@Override
	public void update(T entidade) throws Exception {
		validarSessionfactory();
		sessionFactory.getCurrentSession().update(entidade);
		executeFlushSession();
	}

	@Override
	public void delete(T entidade) throws Exception {
		validarSessionfactory();
		sessionFactory.getCurrentSession().delete(entidade);
		executeFlushSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T entidade) throws Exception {
		validarSessionfactory();
		entidade = (T) sessionFactory.getCurrentSession().merge(entidade);
		executeFlushSession();
		return entidade;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(Class<T> entidade) throws Exception {
		validarSessionfactory();
		
		StringBuilder  query = new StringBuilder(); 
		query.append(" select distinct(entity) from ")
			 .append(entidade.getSimpleName())
			 .append(" entity ");
		
		List<T> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();
		
		return lista;
	}

	@Override
	public Object findById(Class<T> entidade, Long id) throws Exception {
		validarSessionfactory();
		Object obj = sessionFactory.getCurrentSession().load(entidade, id);
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByPorId(Class<T> entidade, Long id) throws Exception {
		validarSessionfactory();
		//T obj = (T) sessionFactory.getCurrentSession().load(getClass(), id);
		T obj = (T) sessionFactory.getCurrentSession().load(entidade, id);
		return obj;
	}

	@Override
	public List<T> findListByProperty(Class<T> entidade, Object atributo, Object valor) throws Exception {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDinamica(String s) throws Exception {
		validarSessionfactory();
		
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(s).list();
		
		return lista;
	}

	@Override
	public List<T> findListByListDeIds(Class<T> obj, List<Long> cods) throws Exception {
		return null;
	}

	@Override
	public void executeUpdateQueryDinamica(String s) throws Exception {
		validarSessionfactory();
		
		sessionFactory.getCurrentSession().createQuery(s).executeUpdate();
		
		executeFlushSession();
	}

	@Override
	public void executeUpdateSQLDinamica(String s) throws Exception {
		validarSessionfactory();
		
		sessionFactory.getCurrentSession().createSQLQuery(s).executeUpdate();
		
		executeFlushSession();
	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();
	}

	@Override
	public void evict(Object obj) throws Exception {
		validarSessionfactory();
		sessionFactory.getCurrentSession().evict(obj);
		executeFlushSession();
	}

	/**
	 * Retorna sess�o corrente
	 */
	@Override
	public Session getSession() throws Exception {
		validarSessionfactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<?> getListSQLDinamica(String sql) throws Exception {
		validarSessionfactory();
		
		List<?> lista = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		
		return lista;
	}

	/**
	 * TestUnit Ok
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T carregar(Class<T> classe, Long codigo) throws Exception {
		validarSessionfactory();
		
		T t = (T) sessionFactory.getCurrentSession().get(classe, codigo);
		
		return t;
	}

	@Override
	public Class<T> getClass(Class<T> entidade) throws Exception {
		return null;
	}

	@Override
	public List<T> findListByLike(Class<T> entidade, String atributoClass, String valor) throws Exception {
		return null;
	}

	@Override
	public List<T> findByPropertyId(Class<T> entidade, Long id, Object atributo) throws Exception {
		return null;
	}

	/**
	 * Retorna o total de registro da tabela passada como parametro Return Long
	 */
	@Override
	public Long totalRegistro(String tabela) throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		
		sqlBuilder.append(" select count(1) from ").append(tabela);//retorna um long
		
		return jdbcTemplate.queryForLong(sqlBuilder.toString());//faz a convers�o para string
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		validarSessionfactory();
		
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(query.toString());
		
		return queryReturn;
	}

	/**
	 * Realiza consulta no banco de dados, iniciando o carregamento a partir do
	 * registro passado como par�metro -> iniciaNoRegistro e obtendo maximo de
	 * resultados passados em -> maximoResultado
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception {
		validarSessionfactory();
		
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.
				getCurrentSession().
				createQuery(query.toString()).
				setFirstResult(iniciaNoRegistro).
				setMaxResults(maximoResultado).
				list();
		return lista;
	}
	
	private void validarSessionfactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
	}
	
	@SuppressWarnings("unused")
	private void validarTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {//se n�o tiver transa��o ativa
			sessionFactory.getCurrentSession().getTransaction();//vai iniciar uma transa��o no hibernate
		}
	}
	
	@SuppressWarnings("unused")
	private void commitProcessoAjax() {
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	
	/**
	 * Retorna o total de registro da tabela passada como parametro Return Long
	 */
	@SuppressWarnings("unused")
	private void rollBackProcessoAjax() {
		sessionFactory.getCurrentSession().getTransaction().rollback();
	}
	
	/**
	 * Executa instantaneamente o SQL no banco de dados
	 */
	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getListSQLDinamicArray(String sql) throws Exception {
		validarSessionfactory();
		
		List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public T findUniqueByQueryDinamica(String query) throws Exception {
		validarSessionfactory();
		
		T obj = (T) sessionFactory.getCurrentSession().createQuery(query.toString()).uniqueResult();

		return obj;
	}

	public T findUniqueByProperty(Class<T> entidade, Object valor, String atributo, String condicao) throws Exception {
		validarSessionfactory();
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(" select entity from ")
			 .append(entidade.getSimpleName())
			 .append(" entity where entity.")
			 .append(atributo)
			 .append(" = '")
			 .append(valor)
			 .append("' ")
			 .append(condicao);

		T obj = (T) this.findUniqueByQueryDinamica(queryBuilder.toString());
		
		return obj;
	}
	
	public T findUninqueByProperty(Class<T> entidade, Object valor, String atributo) throws Exception {
		validarSessionfactory();
		StringBuilder query = new StringBuilder();

		query.append(" select entity from ")
		     .append(entidade.getSimpleName())
			 .append(" entity where entity.")
			 .append(atributo)
		     .append(" = '")
		     .append(valor)
		     .append("'");

		T obj = (T) this.findUniqueByQueryDinamica(query.toString());

		return obj;

	}
}
