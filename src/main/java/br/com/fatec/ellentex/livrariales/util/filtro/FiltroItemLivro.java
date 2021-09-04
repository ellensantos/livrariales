package br.com.fatec.ellentex.livrariales.util.filtro;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemLivro;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author EllenTex
 */
public class FiltroItemLivro {

    public TypedQuery<EntidadeDominio> criarQuery(ItemLivro itemLivro, EntityManager entityManager) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
        Root<ItemLivro> root = cQuery.from(ItemLivro.class);

        Predicate predicate = builder.and();

        if (itemLivro.getId() > 0) {
            predicate = builder.and(predicate, builder.equal(root.get("id"), itemLivro.getId()));
        }

        else {
            if (itemLivro.getStatus() != null) {
                predicate = builder.and(predicate, builder.equal(root.get("status"), itemLivro.getStatus()));
            }

            if(itemLivro.getItemEstoque() != null){
                predicate = builder.and(predicate, builder.equal(root.get("itemEstoque"), itemLivro.getItemEstoque().getId()));
            }
        }

        cQuery.select(root).where(predicate);
        TypedQuery<EntidadeDominio> query = entityManager.createQuery(cQuery);

        return query;
    }
}
