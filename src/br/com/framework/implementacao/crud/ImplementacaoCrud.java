package br.com.framework.implementacao.crud;

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
		
	}

	@Override
	public void persist(T obj) throws Exception {
		
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		
	}

	@Override
	public void update(T obj) throws Exception {
		
	}

	@Override
	public void delete(T obj) throws Exception {
		
	}

	@Override
	public T merge(T obj) throws Exception {
		return null;
	}

	@Override
	public List<T> findList(Class<T> obj) throws Exception {
		return null;
	}

	@Override
	public Object findById(Class<T> obj, Long id) throws Exception {
		return null;
	}

	@Override
	public T findByPorId(Class<T> obj, Long id) throws Exception {
		return null;
	}

	@Override
	public List<T> findListByProperty(Class<T> entidade, Object atributo, Object valor) throws Exception {
		return null;
	}

	@Override
	public List<T> findListByQueryDinamica(String s) throws Exception {
		return null;
	}

	@Override
	public List<T> findListByListDeIds(Class<T> obj, List<Long> cods) throws Exception {
		return null;
	}

	@Override
	public void executeUpdateQueryDinamica(String s) throws Exception {
		
	}

	@Override
	public void executeUpdateSQLDinamica(String s) throws Exception {
		
	}

	@Override
	public void clearSession() throws Exception {
		
	}

	@Override
	public void evict(Object obj) throws Exception {
		
	}

	@Override
	public Session getSession() throws Exception {
		return null;
	}

	@Override
	public List<?> getListSQLDinamica(String sql) throws Exception {
		return null;
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
	public T findUninqueByProperty(Class<T> entidade, Object valor, String atributo, String condicao) throws Exception {
		return null;
	}

	@Override
	public Long totalRegistro(String tabela) throws Exception {
		return null;
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		return null;
	}

	@Override
	public List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception {
		return null;
	}
	
	private void validarSessionfactory() {
		
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
	}
	
	private void validarTransaction() {
		
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {//se não tiver transação ativa
			sessionFactory.getCurrentSession().getTransaction();//vai iniciar uma transação no hibernate
		}
	}
	
	private void commitProcessoAjax() {
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	
	private void rollBackProcessoAjax() {
		sessionFactory.getCurrentSession().getTransaction().rollback();
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

}
