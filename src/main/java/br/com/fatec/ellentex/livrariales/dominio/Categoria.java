package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.MappedSuperclass;

/**
 * @author EllenTex
 */
@MappedSuperclass
public abstract class Categoria extends EntidadeDominio {
    private String descricao;

    public Categoria(String descricao) {
        this.descricao = descricao;
    }

    public Categoria() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
