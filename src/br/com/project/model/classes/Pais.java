package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "pais")
@SequenceGenerator(name = "pais_seq", sequenceName = "pais_seq", initialValue = 1, allocationSize = 1) // gera a chave
																										// id sequencial
																										// no banco de
																										// dados
public class Pais implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "pais_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_seq")
	private Long pais_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "pais_nome", principal = 1)
	@Column(nullable = false, length = 50)
	private String pais_nome;

	@Column(nullable = false, length = 5)
	private String pais_sigla;

	public Long getPais_codigo() {
		return pais_codigo;
	}

	public void setPais_codigo(Long pais_codigo) {
		this.pais_codigo = pais_codigo;
	}

	public String getPais_nome() {
		return pais_nome;
	}

	public void setPais_nome(String pais_nome) {
		this.pais_nome = pais_nome;
	}

	public String getPais_sigla() {
		return pais_sigla;
	}

	public void setPais_sigla(String pais_sigla) {
		this.pais_sigla = pais_sigla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pais_codigo == null) ? 0 : pais_codigo.hashCode());
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
		Pais other = (Pais) obj;
		if (pais_codigo == null) {
			if (other.pais_codigo != null)
				return false;
		} else if (!pais_codigo.equals(other.pais_codigo))
			return false;
		return true;
	}

}
