package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Mensagem extends EntidadeDominio {
    private String descricao;
    @Column(columnDefinition = "BOOLEAN")
    private boolean status;
    private Date data;

    public Mensagem() {
        this.status = true;
    }

    public Mensagem(String descricao, Date data) {
        this.status = true;
        this.descricao = descricao;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
