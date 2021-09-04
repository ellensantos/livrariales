package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * @author EllenTex
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Fornecedor extends EntidadeDominio {
    private String nome;
    private String cnpj;
    @ManyToOne
    private Pais pais;

    public Fornecedor() {}

    public Fornecedor(String nome, String cnpj, Pais pais) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.pais = pais;
    }

    public Fornecedor(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
