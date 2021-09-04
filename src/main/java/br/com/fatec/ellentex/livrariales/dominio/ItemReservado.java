package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author EllenTex
 */

@Entity
public class ItemReservado extends EntidadeDominio {
    @OneToOne
    private Produto produto;
    private int qtde;

    public ItemReservado() {
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
