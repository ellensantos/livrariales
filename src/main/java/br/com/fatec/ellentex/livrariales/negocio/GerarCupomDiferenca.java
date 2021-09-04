package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.Cliente;
import br.com.fatec.ellentex.livrariales.dominio.CupomTroca;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.PedidoCompra;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerarCupomDiferenca implements IStrategy{

    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof PedidoCompra){
            System.out.println("Gerando cupom de diferença ... ");
            PedidoCompra pedidoCompra = (PedidoCompra) entidade;
            List<CupomTroca> cupons = pedidoCompra.getCupomTroca();
            Cliente cliente = pedidoCompra.getCliente();
            List<CupomTroca> listCupomCliente = cliente.getCupom();

            double valorPagoCupom = 0;

            if(cupons != null){
                for(CupomTroca cup : cupons){
                    valorPagoCupom += cup.getValor();
                }
            }

            if(valorPagoCupom > pedidoCompra.getValorTotal()){
                //Gerar Cupom
                CupomTroca cupomTroca = new CupomTroca();

                LocalDateTime localDateTime = LocalDateTime.now();
                String codigo = "DIF" + localDateTime.getDayOfMonth() + localDateTime.getMonth() + localDateTime.getHour() + localDateTime.getMinute() + localDateTime.getSecond();
                Date data = new Date();
                data.setDate(data.getDay() + 30);

                cupomTroca.setCodigo(codigo);
                cupomTroca.setValidade(data);
                cupomTroca.setStatus(true);
                cupomTroca.setValor(valorPagoCupom - pedidoCompra.getValorTotal());

                if(listCupomCliente == null) {
                    listCupomCliente = new ArrayList<>();
                }

                listCupomCliente.add(cupomTroca);
            }
            pedidoCompra.getCliente().setCupom(listCupomCliente);
        }
        return null;
    }
}
