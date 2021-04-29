package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Audited
@Entity
public class Entidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ent_codigo;

	private String ent_login = null;
	private String ent_senha;
	private boolean ent_inativo = false;
	
	@Temporal(TemporalType.TIMESTAMP)//essa anota��o carrega hora e data
	private Date ent_ultimoAcesso;
	
	public Long getEnt_codigo() {
		return ent_codigo;
	}
	
	public void setEnt_codigo(Long ent_codigo) {
		this.ent_codigo = ent_codigo;
	}

	public String getEnt_login() {
		return ent_login;
	}

	public void setEnt_login(String ent_login) {
		this.ent_login = ent_login;
	}

	public String getEnt_senha() {
		return ent_senha;
	}

	public void setEnt_senha(String ent_senha) {
		this.ent_senha = ent_senha;
	}

	public boolean isEnt_inativo() {
		return ent_inativo;
	}

	public void setEnt_inativo(boolean ent_inativo) {
		this.ent_inativo = ent_inativo;
	}

	public Date getEnt_ultimoAcesso() {
		return ent_ultimoAcesso;
	}

	public void setEnt_ultimoAcesso(Date ent_ultimoAcesso) {
		this.ent_ultimoAcesso = ent_ultimoAcesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ent_codigo == null) ? 0 : ent_codigo.hashCode());
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
		Entidade other = (Entidade) obj;
		if (ent_codigo == null) {
			if (other.ent_codigo != null)
				return false;
		} else if (!ent_codigo.equals(other.ent_codigo))
			return false;
		return true;
	}
	
	

}
