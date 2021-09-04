package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;

/**
 * @author EllenTex
 *
 */

@Entity
public class Telefone extends EntidadeDominio {

	private String tipoTelefone;
	private int ddd;
	private String telefone;
	
	public Telefone() {}

	public Telefone(int ddd, String telefone, String tipoTelefone) {
		super();
		this.setTipoTelefone(tipoTelefone);
		this.ddd = ddd;
		this.telefone = telefone;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

}
