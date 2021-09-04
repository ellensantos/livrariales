package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.*;

/**
 * @author EllenTex
 */
public class DefinirRankingCliente implements IStrategy {

    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof PedidoCompra){
            System.out.println("Definindo Ranking ... ");
            PedidoCompra pedido = (PedidoCompra) entidade;
            Ranking ranking = new Ranking();
            Cliente cliente = pedido.getCliente();
            int qtdePedidoCompra = 0;

            if(pedido.getCliente().getRanking() == null){
                qtdePedidoCompra = 1;
                pedido.getCliente().setRanking(ranking);
            }
            else{
                for(Pedido ped : pedido.getCliente().getPedido()){
                    if(ped instanceof PedidoCompra){
                       if(ped.getStatus() != StatusPedido.RECUSADO && ped.getStatus() != StatusPedido.REPROVADO)
                        qtdePedidoCompra += 1;
                    }
                }
            }

            Ranking rank = pedido.getCliente().getRanking();
            rank.setCategoria(CategoriaRanking.getCategoria(qtdePedidoCompra));
            pedido.getCliente().setRanking(rank);
        }
        return null;
    }
}
