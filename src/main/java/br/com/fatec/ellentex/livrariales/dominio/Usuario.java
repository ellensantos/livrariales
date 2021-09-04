package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author EllenTex
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends EntidadeDominio {

	private String email;
	private String senha;
	@Column(columnDefinition = "BOOLEAN")
	private boolean status;

	public Usuario() {
	}

	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
