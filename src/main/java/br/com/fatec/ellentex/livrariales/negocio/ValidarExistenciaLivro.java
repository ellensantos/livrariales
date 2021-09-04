package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.LivroDAO;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Livro;

import java.util.List;

/**
 * @author EllenTex
 */
public class ValidarExistenciaLivro implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {
        if (entidade instanceof Livro) {
            Livro livro = (Livro) entidade;
            System.out.println("Validando existência do Livro ... ");
            if(livro.getId() > 0){
                return null;
            }
            LivroDAO dao = new LivroDAO();

            Livro livConsulta = new Livro();
            livConsulta.setIsbn(livro.getIsbn());
            List<EntidadeDominio> listaLivros = dao.consultar(livConsulta);

            if (!listaLivros.isEmpty()) {
                return "ISBN já cadastrado! ";
            }
        }

        return null;
    }
}
