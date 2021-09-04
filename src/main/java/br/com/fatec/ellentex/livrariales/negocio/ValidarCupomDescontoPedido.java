package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.CupomDAO;
import br.com.fatec.ellentex.livrariales.dominio.CupomDesconto;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.PedidoCompra;
import br.com.fatec.ellentex.livrariales.dominio.StatusPedido;

import java.util.Date;
import java.util.List;

/**
 * @author EllenTex
 */
public class ValidarCupomDescontoPedido implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if (entidade instanceof PedidoCompra){
            System.out.println("Validando cupom de desconto ... ");
            PedidoCompra pedidoCompra = (PedidoCompra) entidade;
            if(pedidoCompra.getId() <= 0) {
                // System.out.println("CUPOM RECEBIDO PARA VALIDAR " + cupomDesconto);

                if (pedidoCompra.getCupomDesconto() != null && pedidoCompra.getCupomDesconto().getCodigo() != null) {
                    //O código foi preenchido
                    CupomDAO dao = new CupomDAO();
                    List<EntidadeDominio> listaCupomDesconto = dao.consultar(pedidoCompra.getCupomDesconto());
                    Date dataPedido = pedidoCompra.getData();

                    for (EntidadeDominio ent : listaCupomDesconto) {
                        CupomDesconto cupom = (CupomDesconto) ent;
                        if (cupom.getCodigo().equals(pedidoCompra.getCupomDesconto().getCodigo())) {
                            //Cupom com código igual
                            pedidoCompra.getCupomDesconto().setId(cupom.getId());
                            pedidoCompra.getCupomDesconto().setValor(cupom.getValor());

                            if (!dataPedido.after(cupom.getValidade())) {
                                return null;
                            }
                        }
                    }
                    //Cupom inválido

                    System.out.println("Cupom desconto inválido, compra recusada ... ");
                    pedidoCompra.setStatus(StatusPedido.RECUSADO);

                }
            }
        }
        return null;
    }
}
