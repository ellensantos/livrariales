package br.com.fatec.ellentex.livrariales.hibernate;

import java.util.Date;

public class LivroPeriodo {

    private String titulo;
    private Date data;
    private int qtde;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
}
