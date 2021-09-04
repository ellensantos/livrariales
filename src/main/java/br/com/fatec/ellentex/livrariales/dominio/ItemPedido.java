package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author EllenTex
 */

@Entity
public class ItemPedido extends EntidadeDominio {
    @ManyToOne
    private Produto produto;
    private int qtde;
    private double preco;
    @ManyToOne
    private PedidoTroca pedidoTroca;

    public ItemPedido() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public PedidoTroca getPedidoTroca() {
        return pedidoTroca;
    }

    public void setPedidoTroca(PedidoTroca pedidoTroca) {
        this.pedidoTroca = pedidoTroca;
    }
}
