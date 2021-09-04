package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.BandeiraCartao;
import br.com.fatec.ellentex.livrariales.dominio.Cartao;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class CartaoDAO extends AbstractDAO{

    public CartaoDAO() {
        getEntityManager();
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

        //Implementado apenas para buscar o cartão pelo ID.
        Cartao cartao = (Cartao) entidade;
        List<EntidadeDominio> listaCartao = new ArrayList<>();

        try {
            cartao = getEntityManager().find(Cartao.class, cartao.getId());
            listaCartao.add(cartao);

        }catch (Exception e) {

        }finally {
            if(ctrlTransacao){
                getEntityManager().close();
            }
        }

        return listaCartao;
    }

    @Override
    public EntidadeDominio salvar(EntidadeDominio entidade) {

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        Cartao cartao = (Cartao) entidade;

        //Tratar se endereço já existe
        try{
            //Recuperar o objeto Bandeira do cartão pois apenas setei o ID no VH
            //EMF não está atualizando o contexto
            BandeiraCartao bandeiraCartao = getEntityManager().find(BandeiraCartao.class, cartao.getBandeiraCartao().getId());
            cartao.setBandeiraCartao(bandeiraCartao);
            getEntityManager().persist(cartao);

        }catch (Exception e){
            System.out.println("Erro ao salvar cartão!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return cartao;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        Cartao cartao = (Cartao) entidade;

        try{
            if (cartao.getId() <= 0) {
                cartao = (Cartao) salvar(cartao);
            }

            cartao = getEntityManager().merge(cartao);

        }catch (Exception e){
            System.out.println("Erro ao salvar o Cartão!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return cartao;
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
