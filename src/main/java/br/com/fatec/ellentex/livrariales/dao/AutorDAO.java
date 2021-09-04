package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.Autor;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author EllenTex
 */
public class AutorDAO extends AbstractDAO {

    public AutorDAO() {
        getEntityManager();
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public String excluir(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public List<EntidadeDominio> listar() {

        try{

            getEntityManager().clear();
            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
            Root<Autor> root = cQuery.from(Autor.class);
            CriteriaQuery<EntidadeDominio> all = cQuery.select(root);

            TypedQuery<EntidadeDominio> query = getEntityManager().createQuery(all);

            return query.getResultList();

        }catch (Exception e) {
            return null;
        }


    }
}
