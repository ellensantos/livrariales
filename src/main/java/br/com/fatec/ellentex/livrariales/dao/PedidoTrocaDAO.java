package br.com.fatec.ellentex.livrariales.dao;

import br.com.fatec.ellentex.livrariales.dominio.CupomTroca;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemPedido;
import br.com.fatec.ellentex.livrariales.dominio.PedidoTroca;
import br.com.fatec.ellentex.livrariales.util.filtro.FiltroPedidoTroca;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class PedidoTrocaDAO extends AbstractDAO{
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        PedidoTroca pedidoTroca = (PedidoTroca) entidade;

        try {
            getEntityManager().clear();

            FiltroPedidoTroca filtroPedido = new FiltroPedidoTroca();
            TypedQuery<EntidadeDominio> query = filtroPedido.criarQuery(pedidoTroca, getEntityManager());

            return query.getResultList();

        } catch (Exception e) {
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

        PedidoTroca pedidoTroca = (PedidoTroca) entidade;

        //DAOS
        ClienteDAO clienteDAO = new ClienteDAO();

        try{
            pedidoTroca.setItemPedido(getEntityManager().find(ItemPedido.class, pedidoTroca.getItemPedido().getId()));
            pedidoTroca.setCliente(getEntityManager().merge(pedidoTroca.getCliente()));
            pedidoTroca.setValorTotal(pedidoTroca.getItemPedido().getPreco());
            getEntityManager().persist(pedidoTroca);

            //PEDIDO - ITEM PEDIDO
            ItemPedido itemPedido = getEntityManager().merge(pedidoTroca.getItemPedido());
            itemPedido.setPedidoTroca(pedidoTroca);

            //PEDIDO - CLIENTE
            pedidoTroca.getCliente().getPedido().add(pedidoTroca);
            clienteDAO.alterar(pedidoTroca.getCliente());

            //ctrlTransacao = true;
            //Vai comitar em cliente

        }catch (Exception e){
            System.out.println("Erro ao Salvar Pedido Troca!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

        return pedidoTroca;
    }

    @Override
    public EntidadeDominio alterar(EntidadeDominio entidade) {
        if(getEntityManager().getTransaction().isActive()){
            ctrlTransacao = false;
        }
        else{
            getEntityManager().getTransaction().begin();
        }

        PedidoTroca pedidoTroca = (PedidoTroca) entidade;
        CupomTrocaDAO daoCupom = new CupomTrocaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ItemEstoqueDAO itemEstoqueDAO = new ItemEstoqueDAO();

        try{

            System.out.println("STS PEDIDO NO DAO = " + pedidoTroca.getStatus());
            //pedidoTroca = getEntityManager().merge(pedidoTroca);

            //Alterar o cupom se ele não for nulo e se ainda não foi salvo.
            if(pedidoTroca.getCupomTroca()!= null && pedidoTroca.getCupomTroca().getId()<=0){
                List<CupomTroca> listaCupom;
                listaCupom = pedidoTroca.getCliente().getCupom();

                if(listaCupom == null)
                    listaCupom = new ArrayList<>();

                pedidoTroca.setCupomTroca((CupomTroca) daoCupom.alterar(pedidoTroca.getCupomTroca()));
                listaCupom.add(pedidoTroca.getCupomTroca());

               // pedidoTroca = getEntityManager().merge(pedidoTroca);

                pedidoTroca = getEntityManager().merge(pedidoTroca);

                //Alterar ItemEstoque
                itemEstoqueDAO.alterar(pedidoTroca.getItemPedido().getProduto().getItemEstoque());

                //CUPOM - CLIENTE
                pedidoTroca.getCliente().setCupom(listaCupom);

            }

            //Vai comitar em cliente
            clienteDAO.alterar(pedidoTroca.getCliente());

        }catch (Exception e){
            System.out.println("Erro ao ALTERAR Pedido!");
            getEntityManager().getTransaction().rollback();
            e.printStackTrace();
        }

        return pedidoTroca;
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
