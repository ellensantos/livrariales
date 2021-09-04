package br.com.fatec.ellentex.livrariales.dominio;

/**
 * @author EllenTex
 *
 */

import javax.persistence.Entity;

@Entity
public class Pais extends EntidadeDominio {

	private String descricao;

	public Pais() {
		super();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
