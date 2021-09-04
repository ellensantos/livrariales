package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.*;
import java.util.Date;

/**
 * @author EllenTex
 */
@Entity
public class ItemLivro extends EntidadeDominio {
    @ManyToOne(fetch = FetchType.EAGER)
    private Livro livro;
    @ManyToOne
    private Fornecedor fornecedor;
    private int quantidade;
    private Date dataEntrada;
    private double valorCusto;
    private double valorVenda;
    @Enumerated(EnumType.STRING)
    private StatusItemLivro status;

    @ManyToOne(fetch = FetchType.EAGER)
    private ItemEstoque itemEstoque;

    public ItemLivro() {
    }

    public ItemLivro(Livro livro, Fornecedor fornecedor, int quantidade, Date dataEntrada, double valorCusto, double valorVenda) {
        this.livro = livro;
        this.fornecedor = fornecedor;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
        this.valorCusto = valorCusto;
        this.valorVenda = valorVenda;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro produto) {
        this.livro = produto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public StatusItemLivro getStatus() {
        return status;
    }

    public void setStatus(StatusItemLivro status) {
        this.status = status;
    }

    public ItemEstoque getItemEstoque() {
        return itemEstoque;
    }

    public void setItemEstoque(ItemEstoque itemEstoque) {
        this.itemEstoque = itemEstoque;
    }
}
