package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.*;
import br.com.fatec.ellentex.livrariales.util.filtro.FiltroItemLivro;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class ItemLivroDAO extends AbstractDAO {

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        ItemLivro itemLivro = (ItemLivro) entidade;

        try{
            getEntityManager().clear();
            FiltroItemLivro filtroItemLivro = new FiltroItemLivro();
            TypedQuery<EntidadeDominio> query = filtroItemLivro.criarQuery(itemLivro, getEntityManager());

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

        ItemLivro itemLivro = (ItemLivro) entidade;
        Estoque estoque =  new Estoque();
        List<ItemEstoque> listItem = new ArrayList<>();
        EstoqueDAO estoqueDAO = new EstoqueDAO();

        try{

            getEntityManager().persist(itemLivro);

            listItem.add(itemLivro.getItemEstoque());
            estoque.setId(itemLivro.getItemEstoque().getEstoque().getId());
            estoque.setItens(listItem);
            estoqueDAO.salvar(estoque);


            /*Livro livro = getEntityManager().find(Livro.class, itemLivro.getLivro().getId());
                livro.setItemEstoque(itemLivro.getItemEstoque());
                getEntityManager().merge(livro);*/

            ctrlTransacao = true;

        }catch (Exception e){
            System.out.println("Erro ao salvar ItemLivro!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                System.out.println("COMITANDO EM ITEM LIVRO");
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }

        }
        return itemLivro;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {
        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        ItemLivro itemLivro = (ItemLivro) entidade;
        ItemEstoqueDAO itemEstoqueDAO = new ItemEstoqueDAO();


        try{

            itemEstoqueDAO.alterar(itemLivro.getItemEstoque());
            itemLivro = getEntityManager().merge(itemLivro);

            ctrlTransacao = true;

        }catch (Exception e){
            System.out.println("Erro ao alterar ItemLivro!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }

        }
        return itemLivro;
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
            Root<ItemLivro> root = cQuery.from(ItemLivro.class);
            CriteriaQuery<EntidadeDominio> all = cQuery.select(root).orderBy(builder.desc(root.get("dataEntrada")));
            TypedQuery<EntidadeDominio> query = getEntityManager().createQuery(all);
            return query.getResultList();

        } catch (Exception e) {
            return null;
        }

    }
}
