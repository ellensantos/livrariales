package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author EllenTex
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class PedidoTroca extends Pedido{

    @ManyToOne
    private ItemPedido itemPedido;
    @OneToOne
    private CupomTroca cupomTroca;

    public PedidoTroca() {
    }


    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public CupomTroca getCupomTroca() {
        return cupomTroca;
    }

    public void setCupomTroca(CupomTroca cupomTroca) {
        this.cupomTroca = cupomTroca;
    }
}
