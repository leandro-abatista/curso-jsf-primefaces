package br.com.framework.implementacao.crud;

import java.io.Serializable;

/**
 * Nome do caminho do JNDI postgres Tomcat 9
 * @author Leandro
 *
 */
public class VariavelConexaoUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
}
