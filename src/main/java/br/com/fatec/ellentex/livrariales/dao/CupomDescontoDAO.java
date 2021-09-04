package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.CupomDesconto;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.util.filtro.FiltroCupom;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author EllenTex
 */
public class CupomDescontoDAO extends AbstractDAO{


    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {


        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        CupomDesconto cupomDesconto = (CupomDesconto) entidade;

        try{
            getEntityManager().clear();

            FiltroCupom filtroCupom = new FiltroCupom();
            TypedQuery<EntidadeDominio> query = filtroCupom.criarQuery(cupomDesconto,getEntityManager());

            return query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().close();
            }
        }

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

        try {
            getEntityManager().clear();
            CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<EntidadeDominio> cQuery = builder.createQuery(EntidadeDominio.class);
            Root<CupomDesconto> root = cQuery.from(CupomDesconto.class);
            CriteriaQuery<EntidadeDominio> all = cQuery.select(root);
            TypedQuery<EntidadeDominio> query = getEntityManager().createQuery(all);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        }
    }
}
