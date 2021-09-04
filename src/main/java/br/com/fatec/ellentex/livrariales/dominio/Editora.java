package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;

/**
 * @author EllenTex
 */

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Editora extends Fornecedor{
    private Date dataRegistro;

    public Editora(){}

    public Editora(String nome, String cnpj, Pais pais, Date dataRegistro) {
        super(nome, cnpj, pais);
        this.dataRegistro = dataRegistro;
    }

    public Editora(String nome, String cnpj, Pais pais) {
        super(nome, cnpj, pais);
    }


    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
