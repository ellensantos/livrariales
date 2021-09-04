package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemEstoque;
import br.com.fatec.ellentex.livrariales.dominio.Produto;
import br.com.fatec.ellentex.livrariales.util.filtro.FiltroItemEstoque;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author EllenTex
 */
public class ItemEstoqueDAO extends AbstractDAO{

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

        ItemEstoque itemEstoque = (ItemEstoque) entidade;

        try{
            getEntityManager().clear();
            FiltroItemEstoque filtroItemEstoque = new FiltroItemEstoque();
            TypedQuery<EntidadeDominio> query = filtroItemEstoque.criarQuery(itemEstoque, getEntityManager());
            return query.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

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

        ItemEstoque itemEstoque = (ItemEstoque) entidade;

        try {

            if(itemEstoque.getId() <= 0){
                getEntityManager().persist(itemEstoque);
            }

            else{
                itemEstoque = getEntityManager().merge(itemEstoque);
            }

            Produto produto = getEntityManager().find(Produto.class, itemEstoque.getProduto().getId());
            produto.setItemEstoque(itemEstoque);
            System.out.println("PRODUTO ESTOQUE" + produto.getItemEstoque().getId());
            getEntityManager().merge(produto);

        }catch(Exception e){
            System.out.println("Erro ao salvar Item Estoque.");
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return itemEstoque;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {
        ItemEstoque itemEstoque = (ItemEstoque) entidade;

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        try{

            if(itemEstoque.getId() <= 0){
                itemEstoque = (ItemEstoque) salvar(itemEstoque);
            }

            itemEstoque = getEntityManager().merge(itemEstoque);

        }catch (Exception e){
            System.out.println("Erro ao ALTERAR Item Estoque!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }

        }

        return itemEstoque;
    }

    @Override
    public String excluir(EntidadeDominio entidade) {

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        ItemEstoque item = (ItemEstoque) entidade;

        try{
            List<EntidadeDominio> listaConsulta = consultar(item);
            ItemEstoque itemEncontrado = (ItemEstoque) listaConsulta.get(0);
            ItemEstoque i = getEntityManager().find(ItemEstoque.class,itemEncontrado.getId());

            int qtdeItemLiberar = item.getQuantidade();

            item = getEntityManager().merge(i);
            item.setQuantidade((item.getQuantidade()) - qtdeItemLiberar);
            getEntityManager().merge(item);

            ctrlTransacao = true;

        }catch (Exception e){
            getEntityManager().getTransaction().rollback();
            System.out.println("Falha ao dar baixa no estoque");
            e.printStackTrace();
        } finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return null;
    }

    @Override
    public List<EntidadeDominio> listar() {
        return null;
    }
}
