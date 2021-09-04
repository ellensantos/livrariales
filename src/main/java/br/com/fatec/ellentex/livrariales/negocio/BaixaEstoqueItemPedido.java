package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.ItemEstoqueDAO;
import br.com.fatec.ellentex.livrariales.dominio.*;

import java.util.List;

/**
 * @author EllenTex
 */
public class BaixaEstoqueItemPedido implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof PedidoCompra){
            System.out.println("Dando baixa no estoque ... ");

            PedidoCompra pedidoCompra = (PedidoCompra) entidade;
            ItemEstoqueDAO dao = new ItemEstoqueDAO();
            List<ItemPedido> itemPedidos = pedidoCompra.getItens();

            for(ItemPedido item : itemPedidos){
                ItemEstoque i = new ItemEstoque();
                i.setProduto(item.getProduto());
                ItemEstoque itemEstoqueEncontrado = (ItemEstoque) dao.consultar(i).get(0);
                itemEstoqueEncontrado.setQuantidade(itemEstoqueEncontrado.getQuantidade() - item.getQtde());
                item.getProduto().setItemEstoque(itemEstoqueEncontrado);

                if(i.getProduto() instanceof Livro){
                    if(itemEstoqueEncontrado.getQuantidade() == 0){
                        InativarLivroSemEstoque inativarLivroSemEstoque = new InativarLivroSemEstoque();
                        inativarLivroSemEstoque.processar(i.getProduto());
                    }
                }
            }
            pedidoCompra.setItens(itemPedidos);
        }
        return null;
    }
}
