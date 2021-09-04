package br.com.fatec.ellentex.livrariales.util.filtro;

import br.com.fatec.ellentex.livrariales.dominio.CupomTroca;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FiltroCupomTroca {
    public TypedQuery<EntidadeDominio> criarQuery(CupomTroca cupomTroca, EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
        Root<CupomTroca> root = cQuery.from(CupomTroca.class);

        Predicate predicate = builder.and();

        //Predicate predicate = builder.equal((userjoin.get("email")), email);


        if(cupomTroca.getId() > 0){
            predicate = builder.and(predicate, builder.equal(root.get("id"),cupomTroca.getId()));
        }

        cQuery.select(root).where(predicate);
        TypedQuery<EntidadeDominio> query = entityManager.createQuery(cQuery);

        return query;
    }
}
