package br.com.project.bean.view;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.com.framework.interfaces.crud.InterfaceCrud;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.bean.geral.ObjetoCampoConsulta;
import br.com.project.enums.CondicaoPesquisa;
import br.com.project.report.util.BeanReportView;
import br.com.project.util.all.UtilitarioRegex;

/**
 * Responsavel pela rotina de consulta e abstracao de metodos de CRUD e outros
 * padroes
 * 
 * @author leandro
 * 
 */
@Component
public abstract class BeanManagedViewAbstract extends BeanReportView {

	private static final long serialVersionUID = 1L;

	protected abstract Class<?> getClassImplement();

	protected abstract InterfaceCrud<?> getController();

	public ObjetoCampoConsulta objetoCampoConsultaSelecionado;

	public List<SelectItem> listaSelectItemsCampoPesquisa;

	public List<SelectItem> listaCondicaoPesquisa;

	public CondicaoPesquisa condicaoPesquisaSelecionado;

	public String valorPesquisa;

	public abstract String condicaoAndParaPesquisa() throws Exception;

	public ObjetoCampoConsulta getObjetoCampoConsultaSelecionado() {
		return objetoCampoConsultaSelecionado;
	}

	public void setObjetoCampoConsultaSelecionado(ObjetoCampoConsulta objetoCampoConsultaSelecionado) {

		if (objetoCampoConsultaSelecionado != null) {

			for (Field field : getClassImplement().getDeclaredFields()) {

				if (field.isAnnotationPresent(IdentificaCampoPesquisa.class)) {

					if (objetoCampoConsultaSelecionado.getCampoBanco().equalsIgnoreCase(field.getName())) {
						String descricaoCampo = field.getAnnotation(IdentificaCampoPesquisa.class).descricaoCampo();
						objetoCampoConsultaSelecionado.setDescricao(descricaoCampo);
						objetoCampoConsultaSelecionado.setTipoClass(field.getType().getCanonicalName());
						//objetoCampoConsultaSelecionado.setPrincipal(field.getAnnotation(IdentificaCampoPesquisa.class).principal());

						break;// se encontrar o objeto desejado o break para o processo
					}
				}

			}
		}

		this.objetoCampoConsultaSelecionado = objetoCampoConsultaSelecionado;
	}

	public List<SelectItem> getListaSelectItemsCampoPesquisa() {

		listaSelectItemsCampoPesquisa = new ArrayList<SelectItem>();

		List<ObjetoCampoConsulta> listTemporaria = new ArrayList<ObjetoCampoConsulta>();

		for (Field field : getClassImplement().getDeclaredFields()) {

			if (field.isAnnotationPresent(IdentificaCampoPesquisa.class)) {// identifica se existe essa anotação na
																			// classe de cidade

				String descricaoCampo = field.getAnnotation(IdentificaCampoPesquisa.class).descricaoCampo();
				String descricaoCampoConsulta = field.getAnnotation(IdentificaCampoPesquisa.class).campoConsulta();
				int isPrincipal = field.getAnnotation(IdentificaCampoPesquisa.class).principal();

				ObjetoCampoConsulta objetoCampoConsulta = new ObjetoCampoConsulta();
				objetoCampoConsulta.setDescricao(descricaoCampo);
				objetoCampoConsulta.setCampoBanco(descricaoCampoConsulta);
				objetoCampoConsulta.setTipoClass(field.getType().getCanonicalName());
				objetoCampoConsulta.setPrincipal(isPrincipal);

				listTemporaria.add(objetoCampoConsulta);
			}
		}

		ordenarReverse(listTemporaria);

		for (ObjetoCampoConsulta objetoCampoConsulta : listTemporaria) {
			// transforma a lista de objetoCampoConsulta em um selectItem
			listaSelectItemsCampoPesquisa.add(new SelectItem(objetoCampoConsulta));
		}

		return listaSelectItemsCampoPesquisa;
	}

	private void ordenarReverse(List<ObjetoCampoConsulta> listTemporaria) {
		Collections.sort(listTemporaria, new Comparator<ObjetoCampoConsulta>() {

			@Override
			public int compare(ObjetoCampoConsulta objeto1, ObjetoCampoConsulta objeto2) {

				return objeto1.isPrincipal().compareTo(objeto2.isPrincipal());
			}
		});

	}

	public List<SelectItem> getListaCondicaoPesquisa() {

		listaCondicaoPesquisa = new ArrayList<SelectItem>();

		for (CondicaoPesquisa condicaoPesquisa : CondicaoPesquisa.values()) {
			listaCondicaoPesquisa.add(new SelectItem(condicaoPesquisa, condicaoPesquisa.toString()));
		}

		return listaCondicaoPesquisa;
	}

	public CondicaoPesquisa getCondicaoPesquisaSelecionado() {
		return condicaoPesquisaSelecionado;
	}

	public void setCondicaoPesquisaSelecionado(CondicaoPesquisa condicaoPesquisaSelecionado) {
		this.condicaoPesquisaSelecionado = condicaoPesquisaSelecionado;
	}

	public String getValorPesquisa() {
		return valorPesquisa != null ? new UtilitarioRegex().retiraAcentos(valorPesquisa.trim()) : "";// condição ternária
	}

	public void setValorPesquisa(String valorPesquisa) {
		this.valorPesquisa = valorPesquisa;
	}

	/**
	 * Método que define o sql do filtro
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getSqlLazyQuery() throws Exception {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" select entity from ");
		sqlBuilder.append(getQueryConsulta());
		sqlBuilder.append(" order by entity.");
		sqlBuilder.append(objetoCampoConsultaSelecionado.getCampoBanco());

		return sqlBuilder.toString();
	}

	/**
	 * Query de consulta
	 * 
	 * @return query
	 * @throws Exception
	 */
	private Object getQueryConsulta() throws Exception {

		valorPesquisa = new UtilitarioRegex().retiraAcentos(valorPesquisa);// retira os acentos no valor digitado pelo
																			// usuário

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(getClassImplement().getSimpleName());
		sqlBuilder.append(" entity where ");
		sqlBuilder.append("retira_acentos(upper(cast(entity.");
		sqlBuilder.append(objetoCampoConsultaSelecionado.getCampoBanco());
		sqlBuilder.append(" as character varying(150)))) ");
		//sqlBuilder.append(" as text))) ");

		if (condicaoPesquisaSelecionado.name().equals(CondicaoPesquisa.CONTEM.name())) {

			sqlBuilder.append(" like retira_acentos(upper('%");
			sqlBuilder.append(valorPesquisa);
			sqlBuilder.append("%'))");

		} else

		if (condicaoPesquisaSelecionado.name().equals(CondicaoPesquisa.IGUAL_A.name())) {

			sqlBuilder.append(" = retira_acentos(upper('");
			sqlBuilder.append(valorPesquisa);
			sqlBuilder.append("'))");

		} else

		if (condicaoPesquisaSelecionado.name().equals(CondicaoPesquisa.INICIA_COM.name())) {

			sqlBuilder.append(" like retira_acentos(upper('");
			sqlBuilder.append(valorPesquisa);
			sqlBuilder.append("%'))");

		} else

		if (condicaoPesquisaSelecionado.name().equals(CondicaoPesquisa.TERMINA_COM.name())) {

			sqlBuilder.append(" like retira_acentos(upper('%");
			sqlBuilder.append(valorPesquisa);
			sqlBuilder.append("'))");

		}

		sqlBuilder.append(" ");
		sqlBuilder.append(condicaoAndParaPesquisa());

		return sqlBuilder.toString();
	}

	/**
	 * Método que retorna o total de registros da consulta
	 * 
	 * @return
	 * @throws Exception
	 */
	protected int totalRegistroConsulta() throws Exception {
		
		Query query = getController().obterQuery(" select count(entity) from " + getQueryConsulta());
		Number result = (Number) query.uniqueResult();
		
		return result.intValue();
		/*
		String sql = (" select count(1) from " + getQueryConsulta()).toLowerCase();
		
		return getController().getJdbcTemplate().queryForInt(sql);
		*/
	}

}
