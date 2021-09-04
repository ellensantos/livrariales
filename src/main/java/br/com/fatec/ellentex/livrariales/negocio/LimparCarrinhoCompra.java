package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.ItemReservadoDAO;
import br.com.fatec.ellentex.livrariales.dominio.Carrinho;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.ItemPedido;
import br.com.fatec.ellentex.livrariales.dominio.ItemReservado;

import java.util.ArrayList;
import java.util.List;

/**
 * @author EllenTex
 */
public class LimparCarrinhoCompra implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof Carrinho){
            System.out.println("Limpando carrinho de compra ... ");
            Carrinho carrinho = (Carrinho) entidade;
            ItemReservadoDAO dao = new ItemReservadoDAO();
            ItemReservado itemReservado = new ItemReservado();
            List<ItemPedido> itensPedido = carrinho.getItens();

            if(carrinho.getQtdeItens() > 0) {
                for (ItemPedido item : itensPedido) {
                    itemReservado.setQtde(item.getQtde());
                    itemReservado.setProduto(item.getProduto());
                    dao.excluir(itemReservado);
                }

                carrinho.setItens(new ArrayList<>());
                carrinho.setQtdeItens(0);
            }
        }

        return null;
    }
}
