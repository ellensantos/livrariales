package br.com.fatec.ellentex.livrariales.util.filtro;

import br.com.fatec.ellentex.livrariales.dominio.CategoriaLivro;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Livro;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class FiltroLivro {

    public FiltroLivro() {}

    public TypedQuery<EntidadeDominio> criarQuery(Livro livro, EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
        Root<Livro> root = cQuery.from(Livro.class);

        List<CategoriaLivro> listaCategoria = new ArrayList<CategoriaLivro>();
        listaCategoria = livro.getCategoria();

        Predicate predicate = builder.and();

        //Predicate predicate = builder.equal((userjoin.get("email")), email);


        if(livro.getId() > 0){
            predicate = builder.and(predicate, builder.equal(root.get("id"),livro.getId()));
        }

        else {
            if (livro.getTitulo() != null && (!livro.getTitulo().isEmpty())) {
                predicate = builder.and(predicate, builder.like(root.get("titulo"), "%" + livro.getTitulo() + "%"));
            }
            if (livro.getIsbn() != null && (!livro.getIsbn().isEmpty())) {
                predicate = builder.and(predicate, builder.like(root.get("isbn"), "%" + livro.getIsbn() + "%"));
            }
            if (livro.getQtdePagina() > 0 ) {
                predicate = builder.and(predicate, builder.equal(root.get("qtdePagina"), livro.getQtdePagina()));
            }
            if (livro.getAnoLancamento() > 0) {
                predicate = builder.and(predicate, builder.equal(root.get("anoLancamento"), livro.getAnoLancamento()));
            }
            if (livro.getEdicao() > 0) {
                predicate = builder.and(predicate, builder.equal(root.get("edicao"), livro.getEdicao()));
            }
            if (livro.getAutor() != null && livro.getAutor().getId() > 0) {
                predicate = builder.and(predicate, builder.equal(root.get("autor_id"), livro.getAutor().getId()));
            }
            if (livro.getEditora() != null && livro.getEditora().getId() > 0) {
                predicate = builder.and(predicate, builder.equal(root.get("editora_id"), livro.getEditora().getId()));
            }
            if (livro.getSinopse() != null && (!livro.getSinopse().isEmpty())) {
                predicate = builder.and(predicate, builder.like(root.get("sinopse"), "%" + livro.getSinopse() + "%"));
            }

            if(listaCategoria != null) {
                for (CategoriaLivro categ : listaCategoria) {
                    predicate = builder.and(predicate, builder.equal(root.join("categorialivro").get("id"), categ.getId()));
                }
            }

            if(livro.getDimensao() != null && livro.getDimensao().getAltura() > 0){
                predicate = builder.and(predicate, builder.equal(root.join("dimensao").get("altura"),livro.getDimensao().getAltura()));
            }
            if(livro.getDimensao() != null && livro.getDimensao().getLargura() > 0){
                predicate = builder.and(predicate, builder.equal(root.join("dimensao").get("largura"),livro.getDimensao().getLargura()));
            }
            if(livro.getDimensao() != null && livro.getDimensao().getComprimento() > 0){
                predicate = builder.and(predicate, builder.equal(root.join("dimensao").get("comprimento"),livro.getDimensao().getComprimento()));
            }
            if(livro.getDimensao() != null && livro.getDimensao().getPeso() > 0){
                predicate = builder.and(predicate, builder.equal(root.join("dimensao").get("peso"),livro.getDimensao().getPeso()));
            }
            if(livro.getPrecificacao() != null && livro.getPrecificacao().getId() > 0){
                predicate = builder.and(predicate, builder.equal(root.join("precificacao").get("id"),livro.getPrecificacao().getId()));
            }
            if(livro.getCodBarras() != null && (!livro.getCodBarras().isEmpty())){
            predicate = builder.and(predicate, builder.like(root.get("codBarras"),"%" + livro.getCodBarras() + "%"));
            }
        }

        cQuery.select(root).where(predicate);
        TypedQuery<EntidadeDominio> query = entityManager.createQuery(cQuery);

        //return query.getResultList();

        return query;
    }


}
