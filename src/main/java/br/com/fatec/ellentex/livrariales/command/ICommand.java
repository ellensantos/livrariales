package br.com.fatec.ellentex.livrariales.command;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

/**
 * @author EllenTex
 */
public interface ICommand {
    public java.lang.Object execute(EntidadeDominio entidade);
}
