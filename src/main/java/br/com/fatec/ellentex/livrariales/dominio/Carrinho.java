package br.com.fatec.ellentex.livrariales.dominio;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class Carrinho extends EntidadeDominio {

    private List<ItemPedido> itens;
    private Cliente cliente;
    private int qtdeItens;
    private double valorTotal;
    private List<ItemPedido> itensRemovidos;
    private int segDesbloqueioItens;


    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.qtdeItens = 0;
        this.itensRemovidos = new ArrayList<>();
        this.segDesbloqueioItens = -1;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getQtdeItens() {
        return qtdeItens;
    }

    public void setQtdeItens(int qtdeItens) {
        this.qtdeItens = qtdeItens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }


    public List<ItemPedido> getItensRemovidos() {
        return itensRemovidos;
    }

    public void setItensRemovidos(List<ItemPedido> itensRemovidos) {
        this.itensRemovidos = itensRemovidos;
    }

    public int getSegDesbloqueioItens() {
        return segDesbloqueioItens;
    }

    public void setSegDesbloqueioItens(int segDesbloqueioItens) {
        this.segDesbloqueioItens = segDesbloqueioItens;
    }
}
