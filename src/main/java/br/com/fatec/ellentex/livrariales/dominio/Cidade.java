package br.com.fatec.ellentex.livrariales.dominio;

/**
 * @author EllenTex
 *
 */

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Cidade extends EntidadeDominio {

	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER)
	private Estado estado;

	public Cidade() {
		super();
	}

	public Cidade(Estado estado) {
		super();
		this.estado = estado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
