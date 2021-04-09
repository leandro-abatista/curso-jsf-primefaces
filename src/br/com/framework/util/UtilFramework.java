package br.com.framework.util;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Classe respons�vel por controlar os usu�rios executando os m�todos de insert, update, delete
 * @author Leandro
 *
 */
@Component
public class UtilFramework implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

	public synchronized static ThreadLocal<Long> getThreadLocal(){
		return threadLocal;
	}
}
