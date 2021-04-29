package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "mensagem")
@SequenceGenerator(name = "mensagem_seq", sequenceName = "mensagem_seq", allocationSize = 1, initialValue = 1)
public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "men_cod", principal = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mensagem_seq")
	private Long men_cod;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Origem", campoConsulta = "usu_origem.ent_nomeFantasia", principal = 2)
	@ManyToOne(fetch = FetchType.EAGER)//muitas mensagens para um usuário
	@ForeignKey(name = "usu_origem_fk")
	@JoinColumn(name = "usu_origem")
	private Entidade usu_origem = new Entidade();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Destino", campoConsulta = "usu_origem.ent_nomeFantasia", principal = 3)
	@ManyToOne(fetch = FetchType.EAGER)//muitas mensagens para um usuário
	@ForeignKey(name = "usu_destino_fk")
	@JoinColumn(name = "usu_destino")
	private Entidade usu_destino = new Entidade();
	
	@Column(nullable = false)
	private Boolean men_lido = Boolean.FALSE;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Data", campoConsulta = "men_dataHora", principal = 4)
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date men_dataHora = new Date();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Assunto", campoConsulta = "men_assunto", principal = 5)
	@Column(nullable = false, length = 80)
	private String men_assunto;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Mensagem", campoConsulta = "men_descricao", principal = 6)
	@Column(nullable = false, length = 1000)
	private String men_descricao;
	
	private Boolean men_exigirResposta = false;
	
	@Version // verificar se o objeto utilizado na transição foi atualizado desde a ultima vez em que ele foi requisitado
	@Column(name = "versionNumero")
	private Integer versionNumero;

	public Long getMen_cod() {
		return men_cod;
	}

	public void setMen_cod(Long men_cod) {
		this.men_cod = men_cod;
	}

	public Entidade getUsu_origem() {
		return usu_origem;
	}

	public void setUsu_origem(Entidade usu_origem) {
		this.usu_origem = usu_origem;
	}

	public Entidade getUsu_destino() {
		return usu_destino;
	}

	public void setUsu_destino(Entidade usu_destino) {
		this.usu_destino = usu_destino;
	}

	public Boolean getMen_lido() {
		return men_lido;
	}

	public void setMen_lido(Boolean men_lido) {
		this.men_lido = men_lido;
	}

	public Date getMen_dataHora() {
		return men_dataHora;
	}

	public void setMen_dataHora(Date men_dataHora) {
		this.men_dataHora = men_dataHora;
	}

	public String getMen_assunto() {
		return men_assunto;
	}

	public void setMen_assunto(String men_assunto) {
		this.men_assunto = men_assunto;
	}

	public String getMen_descricao() {
		return men_descricao;
	}

	public void setMen_descricao(String men_descricao) {
		this.men_descricao = men_descricao;
	}

	public Boolean getMen_exigirResposta() {
		return men_exigirResposta;
	}

	public void setMen_exigirResposta(Boolean men_exigirResposta) {
		this.men_exigirResposta = men_exigirResposta;
	}

	public Integer getVersionNumero() {
		return versionNumero;
	}

	public void setVersionNumero(Integer versionNumero) {
		this.versionNumero = versionNumero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((men_cod == null) ? 0 : men_cod.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensagem other = (Mensagem) obj;
		if (men_cod == null) {
			if (other.men_cod != null)
				return false;
		} else if (!men_cod.equals(other.men_cod))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mensagem [men_cod=" + men_cod + ", men_assunto=" + men_assunto + ", men_descricao=" + men_descricao	+ "]";
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("men_cod", men_cod);
		map.put("men_lido", men_lido);
		map.put("men_assunto", men_assunto);
		map.put("men_descricao", men_descricao);
		
		return new JSONObject();
	}
}
