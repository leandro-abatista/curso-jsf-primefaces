package teste.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.junit.Ignore;
import org.junit.Test;

import br.com.project.report.util.DateUtils;

public class Testes {
	
	@Test
	@Ignore
	public void testeDataAtualCorrente() {
		System.out.println(DateUtils.getDateAtualReportName());
	}
	
	@Test
	public void testeData() {
		try {
			assertEquals("09-04-2021", DateUtils.getDateAtualReportName());
			assertEquals("'2021-04-09'", DateUtils.formatDateSQL(Calendar.getInstance().getTime()));
			assertEquals("2021-04-09", DateUtils.formatDateSQLSimple(Calendar.getInstance().getTime()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
