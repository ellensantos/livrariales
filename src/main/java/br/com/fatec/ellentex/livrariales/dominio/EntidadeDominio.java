package br.com.fatec.ellentex.livrariales.dominio;

import br.com.fatec.ellentex.livrariales.aplicacao.IEntidade;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadeDominio implements IEntidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
