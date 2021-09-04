package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.*;

/**
 * @author EllenTex
 */
@Entity
public class ItemEstoque extends EntidadeDominio {

    @ManyToOne//(cascade = CascadeType.PERSIST)
    @JoinColumn(unique = true)
    private Estoque estoque;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(unique = true)
    private Produto produto;
    private int quantidade;
    private double valorVenda;
/*    @Column(columnDefinition = "BOOLEAN")
    private boolean pendenciaValor;*/

    public ItemEstoque() {
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }


}
