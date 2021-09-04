package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;

@Entity
public class BandeiraCartao extends EntidadeDominio {

    String descricao;

    public BandeiraCartao(){}

    public BandeiraCartao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
