package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.LivroDAO;
import br.com.fatec.ellentex.livrariales.dominio.CategoriaInativacao;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Livro;

/**
 * @author EllenTex
 */
public class InativarLivroSemEstoque implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof Livro){
            System.out.println("Inativando livro sem estoque ... ");
            Livro livro = (Livro) entidade;
            LivroDAO daoLivro = new LivroDAO();
            CategoriaInativacao categoriaInativacao = new CategoriaInativacao();
            categoriaInativacao.setId(10L);
            livro.setCategoriaInativacao(categoriaInativacao);
            livro.setStatus(false);
            livro.setCategoriaAtivacao(null);
            daoLivro.alterar(livro);

        }
        return null;
    }
}
