package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;

/**
 * @author EllenTex
 */
@Entity
public class Autor extends Pessoa{

    private String biografia;

    public Autor(String nome, String biografia) {
        super(nome);
        this.biografia = biografia;
    }

    public Autor(){}

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}
