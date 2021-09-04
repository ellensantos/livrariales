package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.CartaoDAO;
import br.com.fatec.ellentex.livrariales.dominio.*;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;

import java.time.LocalDate;
import java.util.List;

/**
 * @author EllenTex
 */
public class ValidarDadosPagamentoCartao implements IStrategy{

    @Override
    public java.lang.Object processar(EntidadeDominio entidade) {

        if (entidade instanceof PedidoCompra){
            PedidoCompra pedidoCompra = (PedidoCompra) entidade;

            if(pedidoCompra.getPagamento() != null  && pedidoCompra.getId() <= 0){
                System.out.println("Validando dados pagamento do cartão ...");
                List<Pagamento> pagamentoPedido = pedidoCompra.getPagamento();
                LocalDate localDate = LocalDate.now();
                int anoAtual = localDate.getYear();
                int mesAtual = localDate.getMonthValue();

                for(Pagamento pag: pagamentoPedido){
                    Cartao cartao = (Cartao) pag.getFormaPagamento();

                    if(cartao.getId() > 0){
                        CartaoDAO daoCartao = new CartaoDAO();
                        cartao = (Cartao) daoCartao.consultar(cartao).get(0);
                    }
                    if(cartao.getAnoVencimento() < anoAtual){
                        pedidoCompra.setStatus(StatusPedido.RECUSADO); //cartão vencido
                        System.out.println("Cartão vencido");
                    }
                    else if (cartao.getAnoVencimento() == anoAtual && cartao.getMesVencimento() <= mesAtual){
                        pedidoCompra.setStatus(StatusPedido.RECUSADO); //cartão vencido
                        System.out.println("Cartão vencido");
                    }
                }
            }
        }
        return null;
    }
}
