package br.com.fatec.ellentex.livrariales.command;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

/**
 * @author EllenTex
 *
 */
public class ConsultarCommand extends AbstractCommand {

	@Override
	public java.lang.Object execute(EntidadeDominio entidade) {
		return fachada.consultar(entidade);
	}

}
