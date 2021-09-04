package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author EllenTex
 */

@Entity
public class Pagamento extends EntidadeDominio {

    @OneToOne
    private FormaPagamento formaPagamento;
    private double valor;

    public Pagamento() {
    }

    public Pagamento(FormaPagamento formaPagamento, double valor) {
        this.formaPagamento = formaPagamento;
        this.valor = valor;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
}
