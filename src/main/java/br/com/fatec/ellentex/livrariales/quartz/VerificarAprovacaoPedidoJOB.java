package br.com.fatec.ellentex.livrariales.quartz;

import br.com.fatec.ellentex.livrariales.aplicacao.ParametrosSistema;
import br.com.fatec.ellentex.livrariales.dao.PedidoCompraDAO;
import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.PedidoCompra;
import br.com.fatec.ellentex.livrariales.dominio.StatusPedido;
import br.com.fatec.ellentex.livrariales.negocio.GerenciarStatusPedido;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class VerificarAprovacaoPedidoJOB implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("Executando a verificação de pedidos pendentes ... ");
        PedidoCompraDAO pedidoCompraDAO = new PedidoCompraDAO();
        PedidoCompra pedidoCompra = new PedidoCompra();
        pedidoCompra.setStatus(StatusPedido.EM_PROCESSAMENTO);
        List<EntidadeDominio> listaPedidosPendentes = pedidoCompraDAO.consultar(pedidoCompra);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dataAtual = new Date();
        df.format(dataAtual);

        if(!listaPedidosPendentes.isEmpty()) {
            for (EntidadeDominio pedido : listaPedidosPendentes) {
                PedidoCompra pedCompra = (PedidoCompra) pedido;
                long minutosDiferenca = (dataAtual.getTime() - pedCompra.getData().getTime()) / 60000;

                if(minutosDiferenca >= ParametrosSistema.MINUTOS_APROVACAO_PEDIDO){
                    System.out.println("Liberando itens do pedido: " + pedCompra.getId());
                    pedCompra.setStatus(StatusPedido.REPROVADO);
                    GerenciarStatusPedido gerenciarStatusPedido = new GerenciarStatusPedido();
                    gerenciarStatusPedido.processar(pedCompra);
                    pedidoCompraDAO.alterar(pedCompra);
                }
            }
        }
    }
}
