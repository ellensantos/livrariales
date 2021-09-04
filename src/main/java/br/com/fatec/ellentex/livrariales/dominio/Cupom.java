package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Date;

/**
 * @author EllenTex
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cupom extends EntidadeDominio {

    private String codigo;
    private double valor;
    private Date validade;

    public Cupom() {
    }

    public Cupom(String codigo, double valor, Date validade) {
        this.codigo = codigo;
        this.valor = valor;
        this.validade = validade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }
}
