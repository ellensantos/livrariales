package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;

/**
 * @author EllenTex
 */
@Entity
public class CategoriaInativacao extends Categoria {

    public CategoriaInativacao(String descricao) {
        super(descricao);
    }

    public CategoriaInativacao() {
        super();
    }
}
