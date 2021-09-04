package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.Dimensao;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import java.util.List;

/**
 * @author EllenTex
 */
public class DimensaoDAO extends AbstractDAO{

    public DimensaoDAO() {
        getEntityManager();
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        Dimensao dimensao = (Dimensao) entidade;

        try{
            getEntityManager().persist(dimensao);
        }catch (Exception e){
            System.out.println("Erro ao salvar a Dimensão!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return dimensao;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }

        else{
            getEntityManager().getTransaction().begin();
        }

        Dimensao dimensao = (Dimensao) entidade;

        try{

            dimensao = getEntityManager().merge(dimensao);

        }catch (Exception e){
            System.out.println("Erro ao alterar a Dimensão!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
            }
        }

        return dimensao;
    }

    @Override
    public String excluir(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public List<EntidadeDominio> listar() {
        return null;
    }
}
