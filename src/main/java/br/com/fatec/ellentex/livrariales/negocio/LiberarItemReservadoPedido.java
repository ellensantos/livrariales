package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.ItemReservadoDAO;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemPedido;
import br.com.fatec.ellentex.livrariales.dominio.ItemReservado;
import br.com.fatec.ellentex.livrariales.dominio.PedidoCompra;

import java.util.List;

/**
 * @author EllenTex
 */
public class LiberarItemReservadoPedido implements IStrategy{

    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof PedidoCompra){
            System.out.println("Liberando item do pedido reservado  ... ");
            PedidoCompra pedidoCompra = (PedidoCompra) entidade;
            List<ItemPedido> listaItemPedido = pedidoCompra.getItens();
            ItemReservado itemReservado = new ItemReservado();
            ItemReservadoDAO dao = new ItemReservadoDAO();

            for(ItemPedido item  : listaItemPedido){
                //listaProduto.add(item.getProduto());
                itemReservado.setQtde(item.getQtde());
                itemReservado.setProduto(item.getProduto());
                dao.excluir(itemReservado);
            }
        }
        return null;
    }
}
