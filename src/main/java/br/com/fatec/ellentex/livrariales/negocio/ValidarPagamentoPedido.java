package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.*;

import java.util.List;

/**
 * @author EllenTex
 */

public class ValidarPagamentoPedido implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof PedidoCompra) {
            System.out.println("Validando valor de pagamento do pedido ... ");
            PedidoCompra pedidoCompra = (PedidoCompra) entidade;
            if (pedidoCompra.getId() <= 0) {
                List<Pagamento> listPagamento = pedidoCompra.getPagamento();
                List<CupomTroca> listCupomTroca = pedidoCompra.getCupomTroca();
                double valorPago = 0;
                double valorPagoCupomTroca = 0;
                double valorDesconto = 0;

                if (listPagamento != null) {
                    for (Pagamento pag : listPagamento) {
                        valorPago += pag.getValor();
                    }
                }

                if (listCupomTroca != null) {
                    for (CupomTroca cup : listCupomTroca) {
                        //System.out.println("VALOR DO CUPOM " + cup.getValor());
                        valorPagoCupomTroca += cup.getValor();
                    }
                }

                if (pedidoCompra.getCupomDesconto() != null)
                    valorDesconto = pedidoCompra.getCupomDesconto().getValor();

/*                System.out.println("Valor Desconto " + valorDesconto);
                System.out.println("Valor Pag Cupom " + valorPagoCupomTroca);
                System.out.println("Valor Pag Cartao " + valorPago);*/

                if ((valorPago + valorPagoCupomTroca + valorDesconto) < pedidoCompra.getValorTotal()) {
                    pedidoCompra.setStatus(StatusPedido.RECUSADO);
                    System.out.println("Valor pago difere do valor total, pedido recusado.");
/*                    System.out.println("Valor do Pedido = " + pedidoCompra.getValorTotal());
                    System.out.println("Valor Pago = " + valorPago);*/
                }

            }
        }
        return null;
    }
}
