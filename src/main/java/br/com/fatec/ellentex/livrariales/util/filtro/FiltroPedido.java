package br.com.fatec.ellentex.livrariales.util.filtro;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author EllenTex
 */
public class FiltroPedido {

    public TypedQuery<EntidadeDominio> criarQuery(Pedido pedido, EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
        Root<Pedido> root = cQuery.from(Pedido.class);

        Predicate predicate = builder.and();

        if(pedido.getId() > 0){
            predicate = builder.and(predicate, builder.equal(root.get("id"), pedido.getId()));
        }

        else {


            if (pedido.getCliente() != null) {
                predicate = builder.and(predicate, builder.equal(root.join("cliente").get("id"), pedido.getCliente().getId()));
            }

            if(pedido.getStatus() != null){
                predicate = builder.and(predicate, builder.equal(root.get("status"), pedido.getStatus()));
            }
        }

        cQuery.select(root).where(predicate).orderBy(builder.asc(root.get("id")));
        TypedQuery<EntidadeDominio> query = entityManager.createQuery(cQuery);

        return query;
    }
}
