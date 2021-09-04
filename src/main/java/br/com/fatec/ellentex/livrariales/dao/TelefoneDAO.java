package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Telefone;

import java.util.List;

/**
 * @author EllenTex
 */
public class TelefoneDAO extends AbstractDAO{

    public TelefoneDAO() {
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

        Telefone telefone = (Telefone) entidade;

        try{
            getEntityManager().persist(telefone);
        }catch (Exception e){
            System.out.println("Erro ao salvar o Telefone!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return telefone;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {
        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        Telefone telefone = (Telefone) entidade;

        try{
            telefone = getEntityManager().merge(telefone);

        }catch (Exception e){
            System.out.println("Erro ao ALTERAR Telefone!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return telefone;
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
