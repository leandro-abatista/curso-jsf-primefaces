package br.com.project.model.classes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "titulo")
@SequenceGenerator(name = "titulo_seq", sequenceName = "titulo_seq", initialValue = 1, allocationSize = 1)
public class Titulo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "tit_codigo", principal = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "titulo_seq")
	private Long tit_codigo;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Origem", campoConsulta = "tit_origem")
	@Column(length = 100)
	private String tit_origem;/*Comissão para o funcionário -> origem*/
	
	@IdentificaCampoPesquisa(descricaoCampo = "Valor R$", campoConsulta = "tit_valor")
	@Column(scale = 4, precision = 15)
	private BigDecimal tit_valor = BigDecimal.ZERO;//inicia como  zero valor
	
	@IdentificaCampoPesquisa(descricaoCampo = "Valor Pago R$", campoConsulta = "tit_valorPago")
	@Column(scale = 4, precision = 15)
	private BigDecimal tit_valorPago = BigDecimal.ZERO;//inicia como  zero valor
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)//não atualiza essa coluna de data e hora do registro
	private Date tit_dataHora = new Date();
	
	private Boolean tit_baixado = Boolean.FALSE;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Tipo", campoConsulta = "tit_tipo")
	@Column(length = 50)
	private String tit_tipo;//Receber ou pagar
	
	/*Um usuário abre muitos titulos*/
	@IdentificaCampoPesquisa(descricaoCampo = "Usuário Abertura", campoConsulta = "ent_codigoAbertura.ent_nomeFantasia", principal = 2)
	@Basic
	@ManyToOne//Muitos titulos para um usuário
	@ForeignKey(name = "ent_codigoabertura_fk")
	@JoinColumn(nullable = false, name = "ent_codigoabertura", updatable = false)
	private Entidade ent_codigoAbertura = new Entidade();
	
	/*Responsável por pagar a conta*/
	@IdentificaCampoPesquisa(descricaoCampo = "Usuário Responsável", campoConsulta = "ent_codigo.ent_nomeFantasia", principal = 3)
	@Basic
	@ManyToOne
	@ForeignKey(name = "ent_codigo_fk")
	@JoinColumn(nullable = false, name = "ent_codigo")
	private Entidade ent_codigo = new Entidade();
	
	@Version // verificar se o objeto utilizado na transição foi atualizado desde a ultima vez em que ele foi requisitado
	@Column(name = "versionNumero")
	private Integer versionNumero;

	public Long getTit_codigo() {
		return tit_codigo;
	}

	public void setTit_codigo(Long tit_codigo) {
		this.tit_codigo = tit_codigo;
	}

	public String getTit_origem() {
		return tit_origem;
	}

	public void setTit_origem(String tit_origem) {
		this.tit_origem = tit_origem;
	}

	public BigDecimal getTit_valor() {
		return tit_valor;
	}

	public void setTit_valor(BigDecimal tit_valor) {
		this.tit_valor = tit_valor;
	}

	public BigDecimal getTit_valorPago() {
		return tit_valorPago;
	}

	public void setTit_valorPago(BigDecimal tit_valorPago) {
		this.tit_valorPago = tit_valorPago;
	}

	public Date getTit_dataHora() {
		return tit_dataHora;
	}

	public void setTit_dataHora(Date tit_dataHora) {
		this.tit_dataHora = tit_dataHora;
	}
	
	public Boolean getTit_baixado() {
		return tit_baixado;
	}

	public void setTit_baixado(Boolean tit_baixado) {
		this.tit_baixado = tit_baixado;
	}

	public String getTit_tipo() {
		return tit_tipo;
	}

	public void setTit_tipo(String tit_tipo) {
		this.tit_tipo = tit_tipo;
	}

	public Entidade getEnt_codigoAbertura() {
		return ent_codigoAbertura;
	}

	public void setEnt_codigoAbertura(Entidade ent_codigoAbertura) {
		this.ent_codigoAbertura = ent_codigoAbertura;
	}

	public Entidade getEnt_codigo() {
		return ent_codigo;
	}

	public void setEnt_codigo(Entidade ent_codigo) {
		this.ent_codigo = ent_codigo;
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
		result = prime * result + ((tit_codigo == null) ? 0 : tit_codigo.hashCode());
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
		Titulo other = (Titulo) obj;
		if (tit_codigo == null) {
			if (other.tit_codigo != null)
				return false;
		} else if (!tit_codigo.equals(other.tit_codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Titulo [tit_codigo=" + tit_codigo + ", tit_origem=" + tit_origem + ", tit_valor=" + tit_valor
				+ ", tit_valorPago=" + tit_valorPago + ", tit_dataHora=" + tit_dataHora + ", tit_tipo=" + tit_tipo
				+ ", ent_codigoAbertura=" + ent_codigoAbertura + ", ent_codigo=" + ent_codigo + "]";
	}

}
