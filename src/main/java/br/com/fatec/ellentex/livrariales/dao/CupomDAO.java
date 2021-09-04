package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.Cupom;
import br.com.fatec.ellentex.livrariales.dominio.CupomDesconto;
import br.com.fatec.ellentex.livrariales.dominio.CupomTroca;
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
public class CupomDAO extends AbstractDAO{

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

        Cupom cupom = null;

        if(entidade instanceof CupomTroca){
            cupom = (CupomTroca) entidade;
        }else if (entidade instanceof CupomDesconto){
            cupom = (CupomDesconto) entidade;
        }

        try{
            getEntityManager().clear();
            FiltroCupom filtroCupom = new FiltroCupom();
            TypedQuery<EntidadeDominio> query = filtroCupom.criarQuery(cupom,getEntityManager());
            return query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(ctrlTransacao) {
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
            Root<Cupom> root = cQuery.from(Cupom.class);
            CriteriaQuery<EntidadeDominio> all = cQuery.select(root);
            TypedQuery<EntidadeDominio> query = getEntityManager().createQuery(all);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        }
    }
}
