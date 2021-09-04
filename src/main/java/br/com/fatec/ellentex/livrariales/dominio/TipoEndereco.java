package br.com.fatec.ellentex.livrariales.dominio;

/**
 * @author EllenTex
 *
 */

import javax.persistence.Entity;

@Entity
public class TipoEndereco extends EntidadeDominio {

	private String descricao;

	public TipoEndereco(String descricao) {
		this.descricao = descricao;
	}

	public TipoEndereco() {	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
