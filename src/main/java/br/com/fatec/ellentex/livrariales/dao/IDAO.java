package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import java.util.List;

/**
 * @author EllenTex
 *
 */
public interface IDAO {

	public List<EntidadeDominio> consultar(EntidadeDominio entidade);

	public EntidadeDominio salvar(EntidadeDominio entidade);
	
	public EntidadeDominio alterar(EntidadeDominio entidade);
	
	public String excluir(EntidadeDominio entidade);
	
	public List<EntidadeDominio> listar();
}
