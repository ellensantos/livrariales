package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Estoque;
import br.com.fatec.ellentex.livrariales.dominio.ItemEstoque;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class EstoqueDAO extends AbstractDAO{
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

        Estoque estoque = (Estoque) entidade;
        ItemEstoqueDAO daoItemEstoque = new ItemEstoqueDAO();

        try {
            //Executar RNG para o estoque
            List<ItemEstoque> listaItens = estoque.getItens();
            List<ItemEstoque> novosItens = new ArrayList<ItemEstoque>();

            for(ItemEstoque item: listaItens){
                System.out.println("ADD os Itens de Estoque");
                novosItens.add((ItemEstoque) daoItemEstoque.salvar(item));
            }

            if(estoque.getId() < 0){
                getEntityManager().persist(estoque);
            }

            estoque = getEntityManager().merge(estoque);

            for(ItemEstoque item: novosItens){
                item.setEstoque(estoque);
                getEntityManager().merge(item);
            }


        }catch(Exception e){
            System.out.println("Erro ao salvar estoque. ");
            e.printStackTrace();
        }finally {
            if(ctrlTransacao){
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return estoque;
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
        return null;
    }
}
