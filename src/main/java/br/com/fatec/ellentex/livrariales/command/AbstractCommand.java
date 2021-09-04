package br.com.fatec.ellentex.livrariales.command;

import br.com.fatec.ellentex.livrariales.controle.Fachada;

/**
 * @author EllenTex
 *
 */
public abstract class AbstractCommand implements ICommand {
	
	protected Fachada fachada = new Fachada();


}

