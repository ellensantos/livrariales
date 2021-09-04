package br.com.fatec.ellentex.livrariales.controle;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import java.util.List;

/**
 * @author EllenTex
 *
 */
public interface IFachada {

	public java.lang.Object salvar(EntidadeDominio entidade);

	public String excluir(EntidadeDominio entidade);

	public java.lang.Object alterar(EntidadeDominio entidade);

	public List<EntidadeDominio> consultar(EntidadeDominio entidade);

}
