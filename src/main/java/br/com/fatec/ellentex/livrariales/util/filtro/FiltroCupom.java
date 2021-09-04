package br.com.fatec.ellentex.livrariales.util.filtro;

import br.com.fatec.ellentex.livrariales.dominio.Cupom;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author EllenTex
 */
public class FiltroCupom {


    public TypedQuery<EntidadeDominio> criarQuery(Cupom cupom, EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
        Root<Cupom> root = cQuery.from(Cupom.class);

        Predicate predicate = builder.and();

        //Predicate predicate = builder.equal((userjoin.get("email")), email);


        if(cupom.getId() > 0){
            predicate = builder.and(predicate, builder.equal(root.get("id"),cupom.getId()));
        }

        else {
            if(!cupom.getCodigo().isEmpty()){
                predicate = builder.and(predicate, builder.equal(root.get("codigo"), cupom.getCodigo()));
            }
        }

        cQuery.select(root).where(predicate);
        TypedQuery<EntidadeDominio> query = entityManager.createQuery(cQuery);

        return query;
    }


}
