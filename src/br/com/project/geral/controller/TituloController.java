package br.com.project.geral.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.model.classes.Titulo;

@Controller
public class TituloController extends ImplementacaoCrud<Titulo> implements InterfaceCrud<Titulo> {

	private static final long serialVersionUID = 1L;
	
	@RequestMapping("**/gerarGraficoInicial")
	public @ResponseBody String gerarGraficoInicial(@RequestParam(value = "dias") int dias) {
		
		List<Map<String, Object>> titulosUltimosDias = getTitulosUltimosDias(dias);
		
		/*pegando o total de linhas*/
		int totalDeLinhas = titulosUltimosDias.size() + 1;
		
		boolean semDados = false;
		if (totalDeLinhas <= 1) {
			semDados = true;
		}
		
		String[] dados = new String[totalDeLinhas];
		int cont = 0;
		
		if (semDados) {
			dados[cont ++] = "[\"" + "Sem Dados" + "\"," + "\"" + 0 + "\"," + "\"" + 0 + "\"]";
		} else {
			dados[cont] = "[\"" + "Tipo" + "\"," + "\"" + "Quantidade" + "\"," + "\"" + "Média" + "\"]";
			cont++;
			
			for (Map<String, Object> objeto : titulosUltimosDias) {
				dados[cont] = "[\"" + objeto.get("situacao") + "\"," + "\"" + objeto.get("quantidade") + "\"," + "\"" + objeto.get("media_valor") + "\"]";
				cont++;
			}
		}
		
		
		return Arrays.toString(dados);
	}

	private List<Map<String, Object>> getTitulosUltimosDias(int dias) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) "); 
				sql.append("as quantidade, "); 
				sql.append("tit_origem as situacao, "); 
				sql.append("trunc(avg(coalesce(tit_valor,0.00)), 2) as media_valor "); 
				sql.append("from titulo "); 
				sql.append("where cast(tit_datahora as date) >= (current_date - " + dias + ") "); 
				sql.append("and cast(tit_datahora as date) <= current_date "); 
				sql.append("group by tit_origem "); 
				sql.append(" "); 
				sql.append("union ");/*unindo as tabelas*/ 
				sql.append(" "); 
				sql.append("select count(1) ");
				sql.append("as quantidade, "); 
				sql.append("case when tit_baixado then 'BAIXADO' else 'EM ABERTO' end as situacao, "); 
				sql.append("trunc(avg(coalesce(tit_valor,0.00)), 2) as media_valor "); 
				sql.append("from titulo "); 
				sql.append("where cast(tit_datahora as date) >= (current_date - " + dias + ") "); 
				sql.append("and cast(tit_datahora as date) <= current_date "); 
				sql.append("group by tit_baixado "); 
				sql.append(" "); 
				sql.append("union "); /*unindo as tabelas*/ 
				sql.append(" "); 
				sql.append("select count(1) "); 
				sql.append("as quantidade, "); 
				sql.append("tit_tipo as situacao, "); 
				sql.append("trunc(avg(coalesce(tit_valor,0.00)), 2) as media_valor "); 
				sql.append("from titulo "); 
				sql.append("where cast(tit_datahora as date) >= (current_date - " + dias + ") "); 
				sql.append("and cast(tit_datahora as date) <= current_date "); 
				sql.append("group by tit_tipo "); 
				sql.append(" "); 
				sql.append("order by quantidade, media_valor ");
		
		
		return super.getSimpleJdbcTemplate().queryForList(sql.toString());/*retorna uma lista de objetos*/
	}
	
}
