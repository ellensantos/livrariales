package br.com.fatec.ellentex.livrariales.dominio;

/**
 * @author EllenTex
 *
 */

import javax.persistence.Entity;

@Entity
public class Estado extends EntidadeDominio {

	private String descricao;
	private String sigla;

	public Estado(String descricao, String sigla) {
		super();
		this.descricao = descricao;
		this.sigla = sigla;
	}

	public Estado() {
		super();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
