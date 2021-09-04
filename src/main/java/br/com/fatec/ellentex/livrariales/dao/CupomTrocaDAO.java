package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.CupomTroca;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author EllenTex
 */
public class CupomTrocaDAO extends AbstractDAO{
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
        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        CupomTroca cupomTroca = (CupomTroca) entidade;

        try{

            if(cupomTroca.getId() <= 0) {
                getEntityManager().persist(cupomTroca);
            }

            cupomTroca = getEntityManager().merge(cupomTroca);

        }catch (Exception e){
            System.out.println("Erro ao ALTERAR Cupom Troca!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return cupomTroca;
    }

    @Override
    public String excluir(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public List<EntidadeDominio> listar() {
        try {
            getEntityManager().clear();
            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
            Root<CupomTroca> root = cQuery.from(CupomTroca.class);
            CriteriaQuery<EntidadeDominio> all = cQuery.select(root);
            TypedQuery<EntidadeDominio> query = getEntityManager().createQuery(all);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        }
    }
}
