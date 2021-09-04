package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.CategoriaLivro;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author EllenTex
 */
public class CategoriaLivroDAO extends AbstractDAO{

    public CategoriaLivroDAO() {
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
            Root<CategoriaLivro> root = cQuery.from(CategoriaLivro.class);
            CriteriaQuery<EntidadeDominio> all = cQuery.select(root).orderBy(builder.asc(root.get("descricao")));
            TypedQuery<EntidadeDominio> query = getEntityManager().createQuery(all);
            return query.getResultList();

        }catch (Exception e) {
            return null;
        }


    }
}
