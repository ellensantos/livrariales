package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.*;
import java.util.List;

/**
 * @author EllenTex
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Produto extends EntidadeDominio {
    private String foto;
    private double valor;
    @Column(columnDefinition = "BOOLEAN")
    private boolean status;
    private String codBarras;
    private String tipo;
    @ManyToMany
    private List<Fornecedor> fornecedor;

    @OneToOne(fetch = FetchType.EAGER)
    private ItemEstoque itemEstoque;

    @OneToOne
    private ItemReservado itemReservado;

    public Produto(String foto, double valor, boolean status, String codBarras, String tipo, List<Fornecedor> fornecedor) {
        this.foto = foto;
        this.valor = valor;
        this.status = status;
        this.codBarras = codBarras;
        this.tipo = tipo;
        this.fornecedor = fornecedor;
    }

    public Produto() {
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Fornecedor> getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(List<Fornecedor> fornecedor) {
        this.fornecedor = fornecedor;
    }

    public ItemEstoque getItemEstoque() {
        return itemEstoque;
    }

    public void setItemEstoque(ItemEstoque itemEstoque) {
        this.itemEstoque = itemEstoque;
    }

    public ItemReservado getItemReservado() {
        return itemReservado;
    }

    public void setItemReservado(ItemReservado itemReservado) {
        this.itemReservado = itemReservado;
    }
}


