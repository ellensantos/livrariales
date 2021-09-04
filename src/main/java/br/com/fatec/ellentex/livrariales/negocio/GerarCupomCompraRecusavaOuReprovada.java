package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.CupomTroca;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.PedidoCompra;

import java.time.LocalDateTime;

/**
 * @author EllenTex
 */
public class GerarCupomCompraRecusavaOuReprovada implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {
        if(entidade instanceof PedidoCompra){
            PedidoCompra pedido =(PedidoCompra) entidade;

            if(pedido.getCupomTroca() != null){
                for(CupomTroca cup : pedido.getCupomTroca()){
                    CupomTroca cupomTroca = new CupomTroca();
                    LocalDateTime localDateTime = LocalDateTime.now();
                    String codigo = "REC" + localDateTime.getDayOfMonth() + localDateTime.getMonth() + localDateTime.getHour() + localDateTime.getMinute() + localDateTime.getSecond();
                    cupomTroca.setCodigo(codigo);
                    cupomTroca.setValidade(cup.getValidade());
                    cupomTroca.setStatus(true);
                    cupomTroca.setValor(cup.getValor());
                    pedido.getCliente().getCupom().add(cupomTroca);
                }
            }
        }

        return null;
    }
}
