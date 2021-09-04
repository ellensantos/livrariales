package br.com.fatec.ellentex.livrariales.negocio;

import br.com.fatec.ellentex.livrariales.dominio.EntidadeDominio;
import br.com.fatec.ellentex.livrariales.dominio.Pedido;
import br.com.fatec.ellentex.livrariales.dominio.StatusPedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EllenTex
 */

public class GerenciarStatusPedido implements IStrategy{
    Map<StatusPedido, List<IStrategy>> mapRng;

    @Override
    public Object processar(EntidadeDominio entidade) {

        if(entidade instanceof Pedido){
            Pedido pedido = (Pedido) entidade;
            definirMapa();

            //É uma alteração
            //if(pedido.getId() > 0){
                List<IStrategy> rngStatus = mapRng.get(pedido.getStatus());
                if(rngStatus != null) {
                    for (IStrategy strategy : rngStatus) {
                        strategy.processar(pedido);
                    }
                }
            //}
        }

        return null;
    }

    private void definirMapa(){
        mapRng = new HashMap<>();
        List<IStrategy> rngPedidoEmProcessamento = new ArrayList<>();
        List<IStrategy> rngPedidoAprovado = new ArrayList<>();
        List<IStrategy> rngProdutoRecebido = new ArrayList<>();
        List<IStrategy> rngReentradaEstoque = new ArrayList<>();
        List<IStrategy> rngPedidoRecusado = new ArrayList<>();
        List<IStrategy> rngPedidoReprovado = new ArrayList<>();
        List<IStrategy> rngPedidoTrocaAutorizada = new ArrayList<>();

        //EM_PROCESSAMENTO
        IStrategy desativarCupomTroca = new DesativarCupomTroca();
        rngPedidoEmProcessamento.add(desativarCupomTroca);

        //APROVADO
        IStrategy baixaEstoqueItemPedido = new BaixaEstoqueItemPedido();
        IStrategy liberarItemReservadoPedido = new LiberarItemReservadoPedido();
        IStrategy gerarCupomDiferenca = new GerarCupomDiferenca();
        IStrategy definirRankingCliente = new DefinirRankingCliente();
        rngPedidoAprovado.add(baixaEstoqueItemPedido);
        rngPedidoAprovado.add(liberarItemReservadoPedido);
        rngPedidoAprovado.add(gerarCupomDiferenca);
        rngPedidoAprovado.add(definirRankingCliente);

        //PRODUTO RECEBIDO
        IStrategy gerarCupomTroca = new GerarCupomTroca();
        rngProdutoRecebido.add(gerarCupomTroca);

        //REENTRADA ESTOQUE
        IStrategy reentradaItemEstoque = new ReentradaItemEstoque();
        rngReentradaEstoque.add(gerarCupomTroca);
        rngReentradaEstoque.add(reentradaItemEstoque);

        //RECUSADO
        IStrategy gerarCupomCompraRecusavaOuReprovada = new GerarCupomCompraRecusavaOuReprovada();
        rngPedidoRecusado.add(liberarItemReservadoPedido);
        rngPedidoRecusado.add(gerarCupomCompraRecusavaOuReprovada);

        //REPROVADO
        rngPedidoReprovado.add(liberarItemReservadoPedido);
        //rngPedidoReprovado.add(gerarCupomTroca);
        rngPedidoReprovado.add(gerarCupomCompraRecusavaOuReprovada);

        //TROCA AUTORIZADA
        IStrategy gerarMensagemPedido = new GerarMensagemPedido();
        rngPedidoTrocaAutorizada.add(gerarMensagemPedido);

        //Maps RNGS
        mapRng.put(StatusPedido.EM_PROCESSAMENTO, rngPedidoEmProcessamento);
        mapRng.put(StatusPedido.APROVADO, rngPedidoAprovado);
        mapRng.put(StatusPedido.PRODUTO_RECEBIDO, rngProdutoRecebido);
        mapRng.put(StatusPedido.REENTRAR_ESTOQUE, rngReentradaEstoque);
        mapRng.put(StatusPedido.RECUSADO, rngPedidoRecusado);
        mapRng.put(StatusPedido.REPROVADO, rngPedidoReprovado);
        mapRng.put(StatusPedido.TROCA_AUTORIZADA, rngPedidoTrocaAutorizada);
    }
}
