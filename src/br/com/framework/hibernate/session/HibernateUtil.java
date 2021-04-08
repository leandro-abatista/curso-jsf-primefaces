package br.com.framework.hibernate.session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

import br.com.framework.implementacao.crud.VariavelConexaoUtil;

/**
 * Classe respons�vel por estabelecer conex�o com o hibernate
 * @author Leandro
 *
 */
@ApplicationScoped//Serve para a aplica��o toda
public class HibernateUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/postgres";
	
	private static SessionFactory sessionFactory = buildSessionFactory();
	
	/**
	 * RESPONS�VEL POR LER O ARQUIVO DE CONFIGURA��O HIBERNATE.CFG.XML
	 * @return SessionFactory
	 */
	private static SessionFactory buildSessionFactory() {
		
		try {
			
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}
			
			return sessionFactory;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conex�o session factory!!");
		}
	}
	
	/**
	 * Retorna um sessionFactory corrente
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	/**
	 * Retorna a sess�o do Sessionfactory
	 * @return Sess�o Corrente
	 */
	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	/**
	 * Abri uma nova sess�o
	 * @return session
	 */
	public static Session openSession() {
		
		if (sessionFactory == null) {
			buildSessionFactory();
		}
		
		return sessionFactory.openSession();
	}
	
	/**
	 * Obt�m a conex�o do provedor de conex�es configurado
	 * @return ConnectionSQL
	 * @throws SQLException
	 */
	public static Connection getConnectionProvider() throws SQLException {
		
		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
	}
	
	/**
	 * Retorna a conex�o no InitialContext: java:/comp/env/jdbc/postgres
	 * @return Connection
	 * @throws NamingException
	 * @throws Exception
	 */
	public static Connection getConnection() throws NamingException, Exception {
		
		InitialContext context = new InitialContext();
		DataSource dataSource = (DataSource) context.lookup(JAVA_COMP_ENV_JDBC_DATA_SOURCE);
		
		return dataSource.getConnection();
	}
	
	public DataSource getDataSourceJndi() throws NamingException {
		
		InitialContext context = new InitialContext();
		
		return (DataSource) context.lookup(VariavelConexaoUtil.JAVA_COMP_ENV_JDBC_DATA_SOURCE);
	}
}
