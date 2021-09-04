package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemReservado;
import br.com.fatec.ellentex.livrariales.dominio.Produto;
import br.com.fatec.ellentex.livrariales.util.filtro.FiltroItemReservado;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author EllenTex
 */
public class ItemReservadoDAO extends AbstractDAO{
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

        ItemReservado itemReservado = (ItemReservado) entidade;

        try{
            getEntityManager().clear();
            FiltroItemReservado filtroItemReservado = new FiltroItemReservado();
            TypedQuery<EntidadeDominio> query = filtroItemReservado.criarQuery(itemReservado, getEntityManager());
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

        ItemReservado itemReservado = (ItemReservado) entidade;

        try{

            List<EntidadeDominio> listaConsulta = consultar(itemReservado);

            if(!consultar(itemReservado).isEmpty()){
                System.out.println("JÁ tem reserva para o produto");
                ItemReservado itemEncontrado = (ItemReservado) listaConsulta.get(0);
                ItemReservado i = getEntityManager().find(ItemReservado.class,itemEncontrado.getId());
                i.setQtde(i.getQtde() + itemReservado.getQtde());
                itemReservado = getEntityManager().merge(i);
            }

            else{
                getEntityManager().persist(itemReservado);
            }

            //Atualizando produto
            Produto prod = getEntityManager().find(Produto.class, itemReservado.getProduto().getId());
            prod.setItemReservado(itemReservado);
            getEntityManager().merge(prod);

            ctrlTransacao = true;

        }catch (Exception e){
            getEntityManager().getTransaction().rollback();
            System.out.println("Falha ao reservar item");
            e.printStackTrace();
        } finally {
            if(ctrlTransacao){
                System.out.println("COMITANDO!!!!");
                getEntityManager().getTransaction().commit();
                getEntityManager().close();
            }
        }

        return itemReservado;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public String excluir(EntidadeDominio entidade) {

        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        ItemReservado itemReservado = (ItemReservado) entidade;

        try{
            List<EntidadeDominio> listaConsulta = consultar(itemReservado);
            ItemReservado itemEncontrado = (ItemReservado) listaConsulta.get(0);
            ItemReservado i = getEntityManager().find(ItemReservado.class,itemEncontrado.getId());

            int qtdeItemLiberar = itemReservado.getQtde();
            itemReservado = getEntityManager().merge(i);
            itemReservado.setQtde((itemReservado.getQtde()) - qtdeItemLiberar);
            getEntityManager().merge(itemReservado);

            ctrlTransacao = true;

        }catch (Exception e){
            getEntityManager().getTransaction().rollback();
            System.out.println("Falha ao excluir reserva item");
            e.printStackTrace();
        } finally {
            if(ctrlTransacao){
                System.out.println("COMITANDO!!!!");
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
