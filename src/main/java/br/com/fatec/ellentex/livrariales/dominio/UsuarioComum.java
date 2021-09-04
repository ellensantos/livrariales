package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author EllenTex
 *
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class UsuarioComum extends Usuario {

	@OneToOne
	private Cliente cliente;

	public UsuarioComum() {
	}

	public UsuarioComum(String email, String senha) {
		super(email, senha);
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
