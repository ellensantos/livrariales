package br.com.fatec.ellentex.livrariales.dominio;

import javax.persistence.*;
import java.util.List;

/**
 * @author EllenTex
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Livro extends Produto {
    private String titulo;
    private int anoLancamento;
    private double edicao;
    private String isbn;
    private int qtdePagina;
    private String sinopse;
    private String justificativaStatus;

    @ManyToOne
    private Autor autor;

    @ManyToOne
    private Editora editora;

    @ManyToOne
    private CategoriaInativacao categoriaInativacao;

    @ManyToOne
    private CategoriaAtivacao categoriaAtivacao;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<CategoriaLivro> categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    private Precificacao precificacao;

    @JoinColumn(unique = true)
    @OneToOne
    private Dimensao dimensao;

    public Livro() {}

    public Livro(String titulo, int anoLancamento, double edicao, String isbn, int qtdePagina, String sinopse,
                 String justificativaStatus, Autor autor, Editora editora, CategoriaInativacao categoriaInativacao,
                 CategoriaAtivacao categoriaAtivacao, List<CategoriaLivro> categoria, Precificacao precificacao,
                 Dimensao dimensao) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.edicao = edicao;
        this.isbn = isbn;
        this.qtdePagina = qtdePagina;
        this.sinopse = sinopse;
        this.justificativaStatus = justificativaStatus;
        this.autor = autor;
        this.editora = editora;
        this.categoriaInativacao = categoriaInativacao;
        this.categoriaAtivacao = categoriaAtivacao;
        this.categoria = categoria;
        this.precificacao = precificacao;
        this.dimensao = dimensao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public double getEdicao() {
        return edicao;
    }

    public void setEdicao(double edicao) {
        this.edicao = edicao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQtdePagina() {
        return qtdePagina;
    }

    public void setQtdePagina(int qtdePagina) {
        this.qtdePagina = qtdePagina;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getJustificativaStatus() {
        return justificativaStatus;
    }

    public void setJustificativaStatus(String justificativaStatus) {
        this.justificativaStatus = justificativaStatus;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public CategoriaInativacao getCategoriaInativacao() {
        return categoriaInativacao;
    }

    public void setCategoriaInativacao(CategoriaInativacao categoriaInativacao) {
        this.categoriaInativacao = categoriaInativacao;
    }

    public CategoriaAtivacao getCategoriaAtivacao() {
        return categoriaAtivacao;
    }

    public void setCategoriaAtivacao(CategoriaAtivacao categoriaAtivacao) {
        this.categoriaAtivacao = categoriaAtivacao;
    }

    public List<CategoriaLivro> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<CategoriaLivro> categoria) {
        this.categoria = categoria;
    }

    public Precificacao getPrecificacao() {
        return precificacao;
    }

    public void setPrecificacao(Precificacao precificacao) {
        this.precificacao = precificacao;
    }

    public Dimensao getDimensao() {
        return dimensao;
    }

    public void setDimensao(Dimensao dimensao) {
        this.dimensao = dimensao;
    }
}
