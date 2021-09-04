package br.com.fatec.ellentex.livrariales.util.filtro;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FiltroUsuario {

    public TypedQuery<EntidadeDominio> criarQuery(Usuario usuario, EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
        Root<Usuario> root = cQuery.from(Usuario.class);

        Predicate predicate = builder.and();

        if(usuario.getId() > 0){
            predicate = builder.and(predicate, builder.equal(root.get("id"), usuario.getId()));
        }

        else if(usuario.getEmail() != null) {
            predicate = builder.and(predicate, builder.equal(root.get("email"), usuario.getEmail()));
        }

        cQuery.select(root).where(predicate);
        TypedQuery<EntidadeDominio> query = entityManager.createQuery(cQuery);

        return query;
    }
}
