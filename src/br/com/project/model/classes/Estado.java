package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "estado")
@SequenceGenerator(name = "estado_seq", sequenceName = "estado_seq", initialValue = 1, allocationSize = 1)
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "C�digo", campoConsulta = "est_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_seq")
	private Long est_id;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "est_nome", principal = 1)
	@Column(nullable = false, length = 70)
	private String est_nome;
	
	@Column(nullable = false, length = 2)
	private String est_uf;
	
	@NotAudited
	@OneToMany(mappedBy = "estado", orphanRemoval = false)//S�o muitas cidades para um estado
	@Cascade(value = {CascadeType.SAVE_UPDATE, 
					 CascadeType.PERSIST,
					 CascadeType.MERGE,
					 CascadeType.REFRESH})
	private List<Cidade> cidades = new ArrayList<Cidade>();
	
	@Basic
	@JoinColumn(name = "pais")
	@ManyToOne(fetch = FetchType.EAGER)//muitos estados para um pa�s
	@ForeignKey(name = "pais_fk")
	private Pais pais = new Pais();
	
	@Version // verificar se o objeto utilizado na transi��o foi atualizado desde a ultima vez em que ele foi requisitado
	@Column(name = "versionNumero")
	private Integer versionNumero;

	public Long getEst_id() {
		return est_id;
	}

	public void setEst_id(Long est_id) {
		this.est_id = est_id;
	}

	public String getEst_nome() {
		return est_nome;
	}

	public void setEst_nome(String est_nome) {
		this.est_nome = est_nome;
	}

	public String getEst_uf() {
		return est_uf;
	}

	public void setEst_uf(String est_uf) {
		this.est_uf = est_uf;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
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
		result = prime * result + ((est_id == null) ? 0 : est_id.hashCode());
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
		Estado other = (Estado) obj;
		if (est_id == null) {
			if (other.est_id != null)
				return false;
		} else if (!est_id.equals(other.est_id))
			return false;
		return true;
	}
	
}
