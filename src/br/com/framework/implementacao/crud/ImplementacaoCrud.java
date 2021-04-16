package br.com.framework.implementacao.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
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
	private JdbcTemplateImpl jdbcTemplateImpl;
	
	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplateImpl;
	
	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsertImpl;
	
	@Autowired
	private SimpleJdbcClassImpl simpleJdbcClassImpl;
	

	public JdbcTemplateImpl getJdbcTemplateImpl() {
		return jdbcTemplateImpl;
	}

	public SimpleJdbcTemplateImpl getSimpleJdbcTemplateImpl() {
		return simpleJdbcTemplateImpl;
	}

	public SimpleJdbcInsertImpl getSimpleJdbcInsertImpl() {
		return simpleJdbcInsertImpl;
	}

	public SimpleJdbcClassImpl getSimpleJdbcClassImpl() {
		return simpleJdbcClassImpl;
	}

	@Override
	public void save(T obj) throws Exception {
		validarSessionfactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	@Override
	public void persist(T obj) throws Exception {
		validarSessionfactory();
		sessionFactory.getCurrentSession().persist(obj);
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		validarSessionfactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();
	}

	@Override
	public void update(T obj) throws Exception {
		validarSessionfactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();
	}

	@Override
	public void delete(T obj) throws Exception {
		validarSessionfactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T merge(T obj) throws Exception {
		validarSessionfactory();
		obj = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(Class<T> entidade) throws Exception {
		validarSessionfactory();
		
		StringBuilder  query = new StringBuilder(); 
		query.append(" select distinct(entity)")
			 .append(entidade.getSimpleName())
			 .append(" entity ");
		
		List<T> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();
		
		return lista;
	}

	@Override
	public Object findById(Class<T> entidade, Long id) throws Exception {
		validarSessionfactory();
		Object obj = sessionFactory.getCurrentSession().load(getClass(), id);
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findByPorId(Class<T> entidade, Long id) throws Exception {
		validarSessionfactory();
		T obj = (T) sessionFactory.getCurrentSession().load(getClass(), id);
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

	@Override
	public T carregar(Class<T> class1, Long long1) throws Exception {
		return null;
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

	@Override
	public Long totalRegistro(String tabela) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) from ").append(tabela);//retorna um long
		return jdbcTemplateImpl.queryForLong(sql.toString());//faz a conversão para string
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		validarSessionfactory();
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(query.toString());
		return queryReturn;
	}

	/**
	 * Realiza consulta no banco de dados, iniciando o carregamento a partir do
	 * registro passado como parâmetro -> iniciaNoRegistro e obtendo maximo de
	 * resultados passados em -> maximoResultado
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception {
		validarSessionfactory();
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(query)
				.setFirstResult(iniciaNoRegistro)
				.setMaxResults(maximoResultado).list();
		return lista;
	}
	
	private void validarSessionfactory() {
		
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
	}
	
	@SuppressWarnings("unused")
	private void validarTransaction() {
		
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {//se não tiver transação ativa
			sessionFactory.getCurrentSession().getTransaction();//vai iniciar uma transação no hibernate
		}
	}
	
	@SuppressWarnings("unused")
	private void commitProcessoAjax() {
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	
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

	@Override
	public JdbcTemplate getJdbcTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getListSQLDinamicArray(String sql) throws Exception {
		validarSessionfactory();
		List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public T findUniqueByQueryDinamic(String query) throws Exception {
		validarSessionfactory();
		
		T obj = (T) sessionFactory.getCurrentSession().createQuery(query.toString()).uniqueResult();

		return obj;
	}

	public T findUniqueByProperty(Class<T> entidade, Object valor, String atributo, String condicao) throws Exception {
		validarSessionfactory();
		
		StringBuilder query = new StringBuilder();
		query.append(" select entity from ")
			 .append(entidade.getSimpleName())
			 .append(" entity where entity.")
			 .append(atributo)
			 .append(" = '")
			 .append(valor)
			 .append("' ")
			 .append(condicao);

		T obj = (T) this.findUniqueByQueryDinamic(query.toString());
		
		return obj;
	}
	
	public T findUninqueByProperty(Class<T> entidade, Object valor, String atributo) throws Exception {
		validarSessionfactory();
		StringBuilder query = new StringBuilder();

		query.append("select entity from ")
		     .append(entidade.getSimpleName())
			 .append(" entity where entity.")
			 .append(atributo)
		     .append(" = '")
		     .append(valor)
		     .append("'");

		T obj = (T) this.findUniqueByQueryDinamic(query.toString());

		return obj;

	}
}
