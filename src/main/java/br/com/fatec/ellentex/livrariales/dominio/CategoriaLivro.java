package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;

/**
 * @author EllenTex
 */
@Entity
public class CategoriaLivro extends Categoria {

    public CategoriaLivro(String descricao) {
        super(descricao);
    }

    public CategoriaLivro() {
        super();
    }
}
