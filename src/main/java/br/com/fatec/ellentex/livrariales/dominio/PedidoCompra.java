package br.com.fatec.ellentex.livrariales.dominio;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * @author EllenTex
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class PedidoCompra extends Pedido{

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Pagamento> pagamento;

    @ManyToOne
    private CupomDesconto cupomDesconto;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<CupomTroca> cupomTroca;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<ItemPedido> itens;

    @ManyToOne
    private  Endereco enderecoEntrega;

    @ManyToOne
    private  Endereco enderecoCobranca;
    private int qtdeItens;
    private double frete;

    public PedidoCompra() {
        qtdeItens = 0;
    }

    public List<Pagamento> getPagamento() {
        return pagamento;
    }

    public void setPagamento(List<Pagamento> pagamento) {
        this.pagamento = pagamento;
    }

    public CupomDesconto getCupomDesconto() {
        return cupomDesconto;
    }

    public void setCupomDesconto(CupomDesconto cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
    }

    public List<CupomTroca> getCupomTroca() {
        return cupomTroca;
    }

    public void setCupomTroca(List<CupomTroca> cupomTroca) {
        this.cupomTroca = cupomTroca;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Endereco getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(Endereco enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    public int getQtdeItens() {
        return qtdeItens;
    }

    public void setQtdeItens(int qtdeItens) {
        this.qtdeItens = qtdeItens;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }
}
