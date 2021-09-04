package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;

/**
 * @author EllenTex
 */
@Entity
public class CategoriaAtivacao extends Categoria {

    public CategoriaAtivacao(String descricao) {
        super(descricao);
    }

    public CategoriaAtivacao() {
        super();
    }
}
