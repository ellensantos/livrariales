package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.ItemEstoqueDAO;
import br.com.fatec.ellentex.livrariales.dominio.*;

/**
 * @author EllenTex
 */
public class ReentradaItemEstoque implements IStrategy{

    @Override
    public Object processar(EntidadeDominio entidade) {
        if(entidade instanceof PedidoTroca){
            System.out.println("Reentrada do item no estoque ... ");
            PedidoTroca pedidoTroca = (PedidoTroca) entidade;

/*            System.out.println(pedidoTroca);
            System.out.println("ITEM =" + pedidoTroca.getItemPedido());

            if(pedidoTroca.getCupomTroca() != null){
                System.out.println("CUPOM DENTRO DA REENTRADA " + pedidoTroca.getCupomTroca());
                System.out.println(pedidoTroca.getCupomTroca().getCodigo());
                System.out.println(pedidoTroca.getCupomTroca().getStatus());
            }*/


            ItemEstoqueDAO dao = new ItemEstoqueDAO();
            ItemPedido itemPedido = pedidoTroca.getItemPedido();

            ItemEstoque i = new ItemEstoque();
            i.setProduto(itemPedido.getProduto());
            ItemEstoque itemEstoqueEncontrado = (ItemEstoque) dao.consultar(i).get(0);
            itemEstoqueEncontrado.setQuantidade(itemEstoqueEncontrado.getQuantidade() + itemPedido.getQtde());
            itemPedido.getProduto().setItemEstoque(itemEstoqueEncontrado);

            pedidoTroca.setItemPedido(itemPedido);
            pedidoTroca.setStatus(StatusPedido.PRODUTO_RECEBIDO);

           //System.out.println("QUANTIDADE = " + pedidoTroca.getItemPedido().getProduto().getItemEstoque().getQuantidade());
        }

        /*
        if(entidade instanceof PedidoCompra){
            PedidoCompra pedidoCompra = (PedidoCompra) entidade;
            ItemEstoqueDAO dao = new ItemEstoqueDAO();
            List<ItemPedido> itemPedidos = pedidoCompra.getItens();

            for(ItemPedido item : itemPedidos){
                ItemEstoque i = new ItemEstoque();
                i.setProduto(item.getProduto());
                ItemEstoque itemEstoqueEncontrado = (ItemEstoque) dao.consultar(i).get(0);
                itemEstoqueEncontrado.setQuantidade(itemEstoqueEncontrado.getQuantidade() + item.getQtde());
                item.getProduto().setItemEstoque(itemEstoqueEncontrado);
            }

            pedidoCompra.setItens(itemPedidos);
        }
*/



        return null;
    }
}
