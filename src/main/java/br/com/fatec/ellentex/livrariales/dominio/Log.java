package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author EllenTex
 */

@Entity
public class Log extends EntidadeDominio {

    private Date dtOperacao;
    @Transient
    private UsuarioLogado usuarioLogado;
    @ManyToOne
    private Usuario usuarioResponsavel;
    @Transient
    private Object entidade;
    private String nomeEntidade;
    private String operacao;
    private long idEntidade;

    public Log() {
    }

    public Log(UsuarioLogado usuarioLogado, Object entidade, String operacao) {
        this.usuarioLogado = usuarioLogado;
        this.entidade = entidade;
        this.operacao = operacao;
    }

    public Date getDtOperacao() {
        return dtOperacao;
    }

    public void setDtOperacao(Date dtOperacao) {
        this.dtOperacao = dtOperacao;
    }

    public UsuarioLogado getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(UsuarioLogado usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public Object getEntidade() {
        return entidade;
    }

    public void setEntidade(Object entidade) {
        this.entidade = entidade;
    }

    public String getNomeEntidade() {
        return nomeEntidade;
    }

    public void setNomeEntidade(String nomeEntidade) {
        this.nomeEntidade = nomeEntidade;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public long getIdEntidade() {
        return idEntidade;
    }

    public void setIdEntidade(long idEntidade) {
        this.idEntidade = idEntidade;
    }

}
