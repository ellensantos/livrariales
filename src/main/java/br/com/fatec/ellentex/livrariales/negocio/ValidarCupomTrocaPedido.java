package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.CupomDAO;
import br.com.fatec.ellentex.livrariales.dominio.CupomTroca;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.PedidoCompra;
import br.com.fatec.ellentex.livrariales.dominio.StatusPedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author EllenTex
 */
public class ValidarCupomTrocaPedido implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof PedidoCompra){
            System.out.println("Validando cupons de troca ... ");
            PedidoCompra pedidoCompra = (PedidoCompra) entidade;
            List<CupomTroca> listaCupomTrocaPedido = pedidoCompra.getCupomTroca();
            List<CupomTroca> novalist = new ArrayList<>();
            CupomDAO dao = new CupomDAO();
            Date dataPedido = pedidoCompra.getData();

            if(listaCupomTrocaPedido != null) {
                for (CupomTroca cupom : listaCupomTrocaPedido) {
                    CupomTroca c = (CupomTroca) dao.consultar(cupom).get(0);
                    novalist.add(c);
                    //System.out.println("CUPOM USADO NO PEDIDO = " + c.getId() +  "-" + c.getValor());
                }

                pedidoCompra.setCupomTroca(novalist);

                if(pedidoCompra.getId() <= 0) {
                    for (CupomTroca cup : pedidoCompra.getCupomTroca()) {
                        if (dataPedido.after(cup.getValidade()) || !cup.getStatus()) {
                            pedidoCompra.setStatus(StatusPedido.RECUSADO);
                            System.out.println("Cupom de troca inválido,compra recusada ...");
                        }
                    }
                }
            }
        }

        return null;
    }
}
