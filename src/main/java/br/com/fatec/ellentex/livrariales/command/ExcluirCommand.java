package br.com.fatec.ellentex.livrariales.command;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

/**
 * @author EllenTex
 *
 */
public class ExcluirCommand extends AbstractCommand {

	@Override
	public java.lang.Object execute(EntidadeDominio entidade) {
		return fachada.excluir(entidade);
	}

}

