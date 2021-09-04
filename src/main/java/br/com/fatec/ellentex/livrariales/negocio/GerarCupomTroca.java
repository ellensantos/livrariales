package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author EllenTex
 */
public class GerarCupomTroca implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof PedidoTroca){
            System.out.println("Gerando cupom de troca ... ");
            CupomTroca cupomTroca = new CupomTroca();

            LocalDateTime localDateTime = LocalDateTime.now();
            String codigo = "TRC" + localDateTime.getDayOfMonth() + localDateTime.getMonth() + localDateTime.getHour() + localDateTime.getMinute() + localDateTime.getSecond();
            Date data = new Date();
            data.setDate(data.getDay() + 30);

            cupomTroca.setCodigo(codigo);
            cupomTroca.setValidade(data);
            cupomTroca.setStatus(true);

            PedidoTroca pedido = (PedidoTroca) entidade;
            pedido.setCupomTroca(cupomTroca);
            cupomTroca.setValor(pedido.getValorTotal());

/*            if(entidade instanceof PedidoTroca){
                PedidoTroca pedido = (PedidoTroca) entidade;
                pedido.setCupomTroca(cupomTroca);
                cupomTroca.setValor(pedido.getValorTotal());
            }*/

/*            else if(entidade instanceof PedidoCompra){
                //Reativando e desasociando os cupons para compra recusada
                PedidoCompra pedido = (PedidoCompra) entidade;

                if(pedido.getStatus().equals(StatusPedido.RECUSADO)) {
   *//*                 if (pedido.getCupomTroca() != null) {
                        for (CupomTroca cup : pedido.getCupomTroca()) {
                            cup.setStatus(true);
                        }
                    }*//*

                    pedido.setCupomTroca(null);
                }
            }*/
        }

        return null;
    }
}
