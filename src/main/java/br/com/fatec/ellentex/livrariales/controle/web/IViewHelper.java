package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author EllenTex
 *
 */
public interface IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest request);

	// A partir de uma requisi��o, retornar uma entidade
	public void setView(java.lang.Object obj, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}
