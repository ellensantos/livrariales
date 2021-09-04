package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

/**
 * @author EllenTex
 *
 */
public interface IStrategy {

	Object processar(EntidadeDominio entidade);

}
