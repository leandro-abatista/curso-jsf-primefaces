package br.com.project.bean.geral;

import java.io.Serializable;

public class EntidadeAtualizarSenhaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String senhaAtual;
	private String novaSenha;
	private String confirmarSenha;

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

}
