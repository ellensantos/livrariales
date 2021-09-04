package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.CupomTroca;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.PedidoCompra;

public class DesativarCupomTroca implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof PedidoCompra){
            System.out.println("Desativando cupons de troca ... ");
            PedidoCompra pedido = (PedidoCompra) entidade;
            if(pedido.getCupomTroca() != null){
                for (CupomTroca cupomPedido: pedido.getCupomTroca()){
                    for(CupomTroca cupomCliente : pedido.getCliente().getCupom()){
                        if(cupomPedido.getId() == cupomCliente.getId()){
                            cupomCliente.setStatus(false);
                        }
                    }
                }
            }
        }
        return null;
    }
}
