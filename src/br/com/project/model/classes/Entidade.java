package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.acessos.Permissao;
import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "entidade")
@SequenceGenerator(name = "entidade_seq", sequenceName = "entidade_seq", initialValue = 3, allocationSize = 1)
public class Entidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "ent_codigo", principal = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entidade_seq")
	private Long ent_codigo;

	@Column(length = 50)
	private String ent_login = null;
	
	@Column(length = 100)
	private String ent_senha;
	
	private boolean ent_inativo = false;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Nome Fantasia", campoConsulta = "ent_nomeFantasia", principal = 2)
	@Column(length = 100)
	private String ent_nomeFantasia;
	
	@Temporal(TemporalType.TIMESTAMP)//essa anotação carrega hora e data
	private Date ent_ultimoAcesso;
	
	@Column(length = 50)
	private String tipoEntidade = "";
	
	@CollectionOfElements
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@JoinTable(name = "entidadeacesso", 
			   uniqueConstraints = {@UniqueConstraint(name = "unique_acesso_entidade_key", 
			   columnNames = {"ent_codigo", "esa_codigo"})}, 
			   joinColumns = {@JoinColumn(name = "ent_codigo")})
	@Column(name = "esa_codigo", length = 50)
	private Set<String> acessos = new HashSet<String>();
	
	@Column(length = 150)
	private String ent_email;
	
	@Version // verificar se o objeto utilizado na transição foi atualizado desde a ultima vez em que ele foi requisitado
	@Column(name = "versionNumero")
	private Integer versionNumero;
	
	/*===================================Getters e Setters====================================================================================*/
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
	
	public String getEnt_nomeFantasia() {
		return ent_nomeFantasia;
	}

	public void setEnt_nomeFantasia(String ent_nomeFantasia) {
		this.ent_nomeFantasia = ent_nomeFantasia;
	}
	
	public String getTipoEntidade() {
		return tipoEntidade;
	}
	
	public void setTipoEntidade(String tipoEntidade) {
		this.tipoEntidade = tipoEntidade;
	}
	
	public Set<String> getAcessos() {
		return acessos;
	}

	public void setAcessos(Set<String> acessos) {
		this.acessos = acessos;
	}
	
	public Integer getVersionNumero() {
		return versionNumero;
	}

	public void setVersionNumero(Integer versionNumero) {
		this.versionNumero = versionNumero;
	}
	
	public String getEnt_email() {
		return ent_email;
	}

	public void setEnt_email(String ent_email) {
		this.ent_email = ent_email;
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
	
	/**
	 * Jason do primefaces
	 * @return
	 */
	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("ent_codigo", ent_codigo);
		map.put("ent_login", ent_login);
		map.put("ent_nomeFantasia", ent_nomeFantasia);
		
		return new JSONObject(map);
	}
	
	public Set<Permissao> getAcessosPermissao(){
		Set<Permissao> permissoes = new HashSet<Permissao>();
		
		for (String acesso : acessos) {
			for (Permissao permissao : Permissao.values()) {
				if (acesso.equalsIgnoreCase(permissao.name())) {
					permissoes.add(permissao);
				}
			}
		}
		return permissoes;
	}
	
}
