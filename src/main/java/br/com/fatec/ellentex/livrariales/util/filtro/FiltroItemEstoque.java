package br.com.fatec.ellentex.livrariales.util.filtro;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemEstoque;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author EllenTex
 */
public class FiltroItemEstoque {

    public TypedQuery<EntidadeDominio> criarQuery(ItemEstoque itemEstoque, EntityManager entityManager) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
        Root<ItemEstoque> root = cQuery.from(ItemEstoque.class);

        Predicate predicate = builder.and();

        if (itemEstoque.getId() > 0) {
            predicate = builder.and(predicate, builder.equal(root.get("id"), itemEstoque.getId()));
        } else {
            if (itemEstoque.getProduto() != null && itemEstoque.getProduto().getId() > 0) {
                predicate = builder.and(predicate, builder.equal(root.join("produto").get("id"), itemEstoque.getProduto().getId()));
            }
        }

        cQuery.select(root).where(predicate);
        TypedQuery<EntidadeDominio> query = entityManager.createQuery(cQuery);

        return query;
    }
}
