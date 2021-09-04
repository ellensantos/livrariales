package br.com.fatec.ellentex.livrariales.dominio;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author EllenTex
 *
 */

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Cartao extends FormaPagamento {

    private String numero;
    private String nomeImpresso;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    private BandeiraCartao bandeiraCartao;

    private String cvv;

    int mesVencimento;
    int anoVencimento;

    public Cartao (){}

    public Cartao(String numero, String nomeImpresso, BandeiraCartao bandeiraCartao, String cvv, int mesVencimento, int anoVencimento) {
        this.numero = numero;
        this.nomeImpresso = nomeImpresso;
        this.bandeiraCartao = bandeiraCartao;
        this.cvv = cvv;
        this.mesVencimento = mesVencimento;
        this.anoVencimento = anoVencimento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }

    public BandeiraCartao getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getMesVencimento() {
        return mesVencimento;
    }

    public void setMesVencimento(int mesVencimento) {
        this.mesVencimento = mesVencimento;
    }

    public int getAnoVencimento() {
        return anoVencimento;
    }

    public void setAnoVencimento(int anoVencimento) {
        this.anoVencimento = anoVencimento;
    }
}
