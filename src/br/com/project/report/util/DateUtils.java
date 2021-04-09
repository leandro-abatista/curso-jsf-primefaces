package br.com.project.report.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Esse método retorna a data atual (data atual do sistema)
	 * @return
	 */
	public static String getDateAtualReportName() {
		DateFormat dataformatada = new SimpleDateFormat("dd-MM-yyyy");
		return dataformatada.format(Calendar.getInstance().getTime());
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String formatDateSQL(Date data) {
		StringBuffer retorno = new StringBuffer();
		DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");//esse formato de salvar no banco de dados
		retorno.append("'");
		retorno.append(dataFormat.format(data));
		retorno.append("'");
		return retorno.toString();
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static String formatDateSQLSimple(Date data) {
		StringBuffer retorno = new StringBuffer();
		DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		retorno.append(dataFormat.format(data));
		return retorno.toString();
	}

}
