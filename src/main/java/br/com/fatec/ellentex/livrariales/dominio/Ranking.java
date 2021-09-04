package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author EllenTex
 *
 */

@Entity
public class Ranking extends EntidadeDominio {
    @Enumerated(EnumType.STRING)
    private CategoriaRanking categoria;

    public Ranking() {

    }

    public Ranking(CategoriaRanking categoria) {
        this.categoria = CategoriaRanking.BRONZE;
    }

    public CategoriaRanking getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaRanking categoria) {
        this.categoria = categoria;
    }
}
