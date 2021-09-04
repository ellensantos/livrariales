package br.com.fatec.ellentex.livrariales.util.filtro;

import br.com.fatec.ellentex.livrariales.dominio.Cliente;
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
public class FiltroCliente{

    public FiltroCliente() {}

    public TypedQuery<EntidadeDominio> criarQuery(Cliente cliente, EntityManager entityManager) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
        Root<Cliente> root = cQuery.from(Cliente.class);

        Predicate predicate = builder.and();

        //Predicate predicate = builder.equal((userjoin.get("email")), email);


        if(cliente.getId() > 0){
            predicate = builder.and(predicate, builder.equal(root.get("id"),cliente.getId()));
        }

        else {
            if(cliente.getUsuario() != null && (!cliente.getUsuario().getEmail().isEmpty())){
                predicate = builder.and(predicate, builder.equal(root.join("usuario").get("email"), cliente.getUsuario().getEmail()));
            }

            if (cliente.getCpf() != null && (!cliente.getCpf().isEmpty())) {
                predicate = builder.and(predicate, builder.like(root.get("cpf"), "%" + cliente.getCpf() + "%"));
            }

            if (cliente.getNome() != null && (!cliente.getNome().isEmpty())) {
                predicate = builder.and(predicate, builder.like(root.get("nome"), "%" + cliente.getNome() + "%"));
            }
            if (cliente.getDtNascimento() != null && (!cliente.getDtNascimento().equals(""))) {
                predicate = builder.and(predicate, builder.equal(root.get("dtNascimento"), cliente.getDtNascimento()));
            }
            if (cliente.getGenero() != null && (!cliente.getGenero().isEmpty())) {
                predicate = builder.and(predicate, builder.like(root.get("genero"), "%" + cliente.getGenero() + "%"));
            }

            if (cliente.getTelefone() != null && cliente.getTelefone().getDdd() != 0) {
                predicate = builder.and(predicate, builder.like(root.join("telefone").get("ddd"), "%" + cliente.getTelefone().getDdd() + "%"));
            }

            if (cliente.getTelefone() != null && cliente.getTelefone().getTelefone() != null && (!cliente.getTelefone().getTelefone().isEmpty())) {
                predicate = builder.and(predicate, builder.like(root.join("telefone").get("telefone"), "%" + cliente.getTelefone().getTelefone() + "%"));
            }

            if (cliente.getUsuario() != null && cliente.getUsuario().getEmail() != null && (!cliente.getUsuario().getEmail().isEmpty())) {
                predicate = builder.and(predicate, builder.like(root.join("usuario").get("email"), "%" + cliente.getUsuario().getEmail() + "%"));
            }
        }

        cQuery.select(root).where(predicate);
        TypedQuery<EntidadeDominio> query = entityManager.createQuery(cQuery);

        //return query.getResultList();

        return query;
    }


}
