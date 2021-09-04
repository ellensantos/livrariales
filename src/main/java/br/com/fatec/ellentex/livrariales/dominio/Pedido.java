package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.*;
import java.util.Date;

/**
 * @author EllenTex
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pedido extends EntidadeDominio {

    private Date data;
    @ManyToOne
    private Cliente cliente;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private double valorTotal;

    public Pedido() {
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
