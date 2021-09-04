package br.com.fatec.ellentex.livrariales.util.filtro;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemReservado;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author EllenTex
 */
public class FiltroItemReservado {

    public TypedQuery<EntidadeDominio> criarQuery(ItemReservado itemReservado, EntityManager entityManager) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
        Root<ItemReservado> root = cQuery.from(ItemReservado.class);

        Predicate predicate = builder.and();

        if (itemReservado.getId() > 0) {
            predicate = builder.and(predicate, builder.equal(root.get("id"), itemReservado.getId()));
        } else {
            if (itemReservado.getProduto() != null && itemReservado.getProduto().getId() > 0) {
                predicate = builder.and(predicate, builder.equal(root.join("produto").get("id"), itemReservado.getProduto().getId()));
            }
        }

        cQuery.select(root).where(predicate);
        TypedQuery<EntidadeDominio> query = entityManager.createQuery(cQuery);
        return query;
    }
}
