package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dao.PedidoDAO;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Pedido;
import br.com.fatec.ellentex.livrariales.dominio.StatusPedido;

import java.util.HashMap;
import java.util.Map;

/**
 * @author EllenTex
 */
public class AlterarStatusPedido implements IStrategy{
    //Mapa que guarda o próximo status do pedido de acordo com a lógica de negócio
    private Map<StatusPedido, StatusPedido> mapStatusPedido;

    @Override
    public Object processar(EntidadeDominio entidade) {


        if(entidade instanceof Pedido){
            System.out.println("Alterando Status do Pedido ... ");

            definirStatusPedido();
            Pedido pedido = (Pedido) entidade;

            StatusPedido statusAtual = pedido.getStatus();
            PedidoDAO dao = new PedidoDAO();
            pedido = (Pedido) dao.consultar(pedido).get(0);

            if(statusAtual != StatusPedido.REENTRAR_ESTOQUE){
                pedido.setStatus(mapStatusPedido.get(pedido.getStatus())); //Próximo Status
            }else{
                pedido.setStatus(statusAtual);
            }
            return pedido;
        }
        return null;
    }

    private void definirStatusPedido(){
        mapStatusPedido = new HashMap<>();
        mapStatusPedido.put(StatusPedido.EM_PROCESSAMENTO, StatusPedido.APROVADO);
        mapStatusPedido.put(StatusPedido.APROVADO, StatusPedido.EM_TRANSPORTE);
        mapStatusPedido.put(StatusPedido.EM_TRANSPORTE, StatusPedido.ENTREGUE);
        mapStatusPedido.put(StatusPedido.ENTREGUE, StatusPedido.EM_TROCA);
        mapStatusPedido.put(StatusPedido.EM_TROCA, StatusPedido.TROCA_AUTORIZADA);
        mapStatusPedido.put(StatusPedido.TROCA_AUTORIZADA, StatusPedido.PRODUTO_RECEBIDO);
    }
}
