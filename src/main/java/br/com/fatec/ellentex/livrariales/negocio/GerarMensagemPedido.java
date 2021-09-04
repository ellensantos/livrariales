package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Mensagem;
import br.com.fatec.ellentex.livrariales.dominio.PedidoTroca;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerarMensagemPedido implements IStrategy{
    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof PedidoTroca){
            System.out.println("Gerando mensagem de pedido de troca ... ");
            PedidoTroca pedido = (PedidoTroca) entidade;
            String msg = "O pedido de troca " + pedido.getId() + " foi autorizado.";
            Mensagem mensagem = new Mensagem(msg, new Date());
            List<Mensagem> listMensagem = pedido.getCliente().getMensagem();

            if(listMensagem == null)
                listMensagem = new ArrayList<>();

            listMensagem.add(mensagem);
            pedido.getCliente().setMensagem(listMensagem);
        }
        return null;
    }
}
