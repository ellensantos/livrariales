package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;

/**
 * @author EllenTex
 */
@Entity
public class Dimensao extends EntidadeDominio {
    private double altura;
    private double largura;
    private double comprimento;
    private double peso;

    public Dimensao(double altura, double largura, double comprimento, double peso) {
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.peso = peso;
    }

    public Dimensao() {}

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
