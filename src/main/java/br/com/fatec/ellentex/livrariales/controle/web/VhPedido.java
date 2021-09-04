package br.com.fatec.ellentex.livrariales.controle.web;

import br.com.fatec.ellentex.livrariales.dominio.*;
import br.com.fatec.ellentex.livrariales.negocio.AlterarStatusPedido;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author EllenTex
 */
public class VhPedido implements IViewHelper{
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request) {
        Pedido pedido = null;
        String operacao = request.getParameter("operacao");

        if(operacao.equals("SALVAR")){
            return definirPedido(request);
        }

        else if(operacao.equals("PEDIDO_DETALHES_CLIENTE") || operacao.equals("PEDIDO_DETALHES_ADMIN")){
            pedido = new Pedido();
            pedido.setId(Long.parseLong(request.getParameter("idPedido")));
        }

        else if(operacao.equals("LISTAR")){
            pedido = new Pedido();
        }

        else if(operacao.equals("LISTAR_PEDIDO_TROCA")){
            pedido = new PedidoTroca();
        }

        else if(operacao.equals("LISTAR_PEDIDO_COMPRA")){
            pedido = new PedidoCompra();
        }

        else if(operacao.equals("CONSULTAR_STATUS")) {
            pedido = new Pedido();
            String status = request.getParameter("statusPedido");
            pedido.setStatus(StatusPedido.getStatusPedido(status));
        }

        else if(operacao.equals("SALVAR_TROCA")){
            pedido = new PedidoTroca();
            UsuarioLogado usuarioLogado = (UsuarioLogado) request.getSession().getAttribute("usuarioLogado");
            Cliente cliente = ((UsuarioComum) usuarioLogado.getUsuario()).getCliente();

            long idItem = Long.parseLong(request.getParameter("idItem"));
            //long idPedido = Long.parseLong(request.getParameter("idPedido"));
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setId(idItem);
            pedido.setCliente(cliente);
            ((PedidoTroca) pedido).setItemPedido(itemPedido);
            pedido.setData(new Date());
            pedido.setStatus(StatusPedido.EM_TROCA);
        }

        else if(operacao.equals("ALTERAR")){
            String entradaItemEstoque = request.getParameter("entradaEstoque");
            pedido = new Pedido();
            long idPedido = Long.parseLong(request.getParameter("idPedido"));
            pedido.setId(idPedido);

            if(entradaItemEstoque != null && entradaItemEstoque.equals("sim")){
                pedido.setStatus(StatusPedido.REENTRAR_ESTOQUE);
            }

            AlterarStatusPedido alterarStatusPedido = new AlterarStatusPedido();
            pedido = (Pedido) alterarStatusPedido.processar(pedido);
        }

        return pedido;
    }

    private PedidoCompra definirPedido(HttpServletRequest request){
        Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinhoCompra");
        PedidoCompra pedidoCompra = new PedidoCompra();
        List<Pagamento> listaPagamento = new ArrayList<>();

        Cliente cliente = carrinho.getCliente();

        //ENDEREÇOS
        Endereco enderecoEntrega = definirEnderecoEntrega(request);
        Endereco enderecoCobranca = definirEnderecoCobranca(request);
        List<Endereco> listaEnderecoCliente = cliente.getEndereco();

        if(request.getParameter("salvarEndCobranca1") != null){
            //Salvar endereço Cobrança
            listaEnderecoCliente.add(enderecoCobranca);
        }

        if(request.getParameter("salvarEndEntrega1") != null){
            //Salvar endereço Entrega
            listaEnderecoCliente.add(enderecoEntrega);
        }

        cliente.setEndereco(listaEnderecoCliente);
        pedidoCompra.setEnderecoCobranca(enderecoCobranca);
        pedidoCompra.setEnderecoEntrega(enderecoEntrega);

        //PAGAMENTO CARTÃO
        int qtdeCartaoPag = Integer.parseInt(request.getParameter("qtdeCartaoPag"));
        int qtdeCartaoCliente = Integer.parseInt(request.getParameter("qtdeCartao"));

        //Cartão Cadastrado
        if(qtdeCartaoCliente > 0){
            for(int i = 1; i <= qtdeCartaoPag; i++){
                if(request.getParameter("idCartaoSelecionado" + i) != null){
                    //O select do cartão cadastrado existe
                    if(request.getParameter("valorPag" + i) != null){
                        //O campo valor foi preenchido
                        double valorPag = Double.parseDouble(request.getParameter("valorPag" + i).replace(",", "."));
                        Long idCartaoSelecionado = Long.parseLong(request.getParameter("idCartaoSelecionado" + i));
                        Cartao cartao = new Cartao();
                        cartao.setId(idCartaoSelecionado);
                        Pagamento pagamento = new Pagamento(cartao,valorPag);
                        listaPagamento.add(pagamento);

                    }
                }
            }
        }

        //Novos cartões
        List<Cartao> listaCartaoCliente = cliente.getCartao();

        for(int i = 0; i<= qtdeCartaoPag; i++){
            if(request.getParameter("numeroCartao" + i) != null){
                //O input novo cartão existe
                String numeroCartao = request.getParameter("numeroCartao" + i);
                String nomeImpresso = request.getParameter("nomeCartao" + i);
                String cvv = request.getParameter("cvv" + i);
                long idBandeira = (Long.parseLong(request.getParameter("bandeiraCartao" + i)));
                int mesVencimento = Integer.parseInt(request.getParameter("mesVencimento" + i));
                int anoVencimento = Integer.parseInt(request.getParameter("anoVencimento" + i));
                double valorPag = Double.parseDouble(request.getParameter("valorPag" + i).replace(",", "."));

                BandeiraCartao bandeiraCartao = new BandeiraCartao();
                bandeiraCartao.setId(idBandeira);

                Cartao cartao = new Cartao(numeroCartao,nomeImpresso, bandeiraCartao, cvv, mesVencimento, anoVencimento);
                Pagamento pagamento = new Pagamento(cartao,valorPag);
                listaPagamento.add(pagamento);

                if(request.getParameter("salvarCartao" + i) != null){
                    listaCartaoCliente.add(cartao);
                }
            }
        }

        cliente.setCartao(listaCartaoCliente);
        pedidoCompra.setPagamento(listaPagamento);


        //CUPOM DE TROCA
        if(cliente.getCupom() != null) {
            int qtdeCupomCliente = cliente.getCupom().size();
            List<CupomTroca> cupomTrocaPedido = new ArrayList<>();

            for(int i=1; i <= qtdeCupomCliente; i++){
                String flgCupom = request.getParameter("cupomTroca" + i);
                if(flgCupom != null){
                    CupomTroca cupom = new CupomTroca();
                    Long idCup = Long.parseLong(request.getParameter("idCupomTroca" + i));
                    cupom.setId(idCup);
                    cupomTrocaPedido.add(cupom);
                }
            }

            pedidoCompra.setCupomTroca(cupomTrocaPedido);
        }


        //CUPOM DE DESCONTO
        if(request.getParameter("cupomDesconto") != null && request.getParameter("cupomDesconto") != ""){
            CupomDesconto cupomDesconto = new CupomDesconto();
            cupomDesconto.setCodigo(request.getParameter("cupomDesconto"));
            pedidoCompra.setCupomDesconto(cupomDesconto);
        }

        //ITENS E VALOR TOTAL
        pedidoCompra.setQtdeItens(carrinho.getQtdeItens());
        pedidoCompra.setItens(carrinho.getItens());
        pedidoCompra.setValorTotal(carrinho.getValorTotal()); //somar Frete na RNG

        //DATA
        Date data = new Date();
        new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
        pedidoCompra.setData(data);

        //STATUS
        pedidoCompra.setStatus(StatusPedido.EM_PROCESSAMENTO);

        //CLIENTE
        pedidoCompra.setCliente(cliente);

        //FRETE NA RNG
        return pedidoCompra;
    }

    private Endereco definirEnderecoCobranca(HttpServletRequest request){
        long idEnderecoCobranca;
        String idEndCobranca = request.getParameter("idEndCobrancaSelecionado");
        Endereco endereco = new Endereco();

        //O cliente selecionou um endereço cadastrado.
        if(idEndCobranca != null && idEndCobranca != ""){
            idEnderecoCobranca = Long.parseLong(request.getParameter("idEndCobrancaSelecionado"));
            endereco.setId(idEnderecoCobranca);
        }

        else {

            String descricaoEnd = request.getParameter("descricaoEndCobranca1");
            String tipoResidencia = request.getParameter("tipoResidenciaEndCobranca1");
            String tipoLogradouro = request.getParameter("tipoLogradouroEndCobranca1");
            String logradouro = request.getParameter("logradouroEndCobranca1");
            int numero = (Integer.parseInt(request.getParameter("numeroEndCobranca1")));
            String bairro = request.getParameter("bairroEndCobranca1");
            String cep = request.getParameter("cepEndCobranca1");
            int idpais = (Integer.parseInt(request.getParameter("paisEndCobranca1")));
            int idestado = (Integer.parseInt(request.getParameter("estadoEndCobranca1")));
            int idcidade = (Integer.parseInt(request.getParameter("cidadeEndCobranca1")));
            String observacao = request.getParameter("observacaoEndCobranca1");

            Pais pais = new Pais();
            pais.setId(idpais);

            Estado estado = new Estado();
            estado.setId(idestado);

            Cidade cidade = new Cidade(estado);
            cidade.setId(idcidade);

            TipoEndereco tipoEndereco = new TipoEndereco();
            tipoEndereco.setId(2L);

            List<TipoEndereco> listaTipoEndereco = new ArrayList<>();
            listaTipoEndereco.add(tipoEndereco);

            endereco = new Endereco(descricaoEnd, tipoResidencia, tipoLogradouro, logradouro, numero,
                    bairro, cep, cidade, listaTipoEndereco, observacao);
        }

        return endereco;
    }

    private Endereco definirEnderecoEntrega(HttpServletRequest request){
        long idEnderecoEntrega;
        String idEndEntrega = request.getParameter("idEndEntregaSelecionado");
        Endereco endereco = new Endereco();

        if(idEndEntrega != null && idEndEntrega != ""){
            idEnderecoEntrega = Long.parseLong(request.getParameter("idEndEntregaSelecionado"));
            //O cliente selecionou um endereço cadastrado.
            endereco.setId(idEnderecoEntrega);
            System.out.println("selecionou um endereço entrega cadastrado!!");
        }

        else {

            String descricaoEnd = request.getParameter("descricaoEndEntrega1");
            String tipoResidencia = request.getParameter("tipoResidenciaEndEntrega1");
            String tipoLogradouro = request.getParameter("tipoLogradouroEndEntrega1");
            String logradouro = request.getParameter("logradouroEndEntrega1");
            int numero = (Integer.parseInt(request.getParameter("numeroEndEntrega1")));
            String bairro = request.getParameter("bairroEndEntrega1");
            String cep = request.getParameter("cepEndEntrega1");
            int idpais = (Integer.parseInt(request.getParameter("paisEndEntrega1")));
            int idestado = (Integer.parseInt(request.getParameter("estadoEndEntrega1")));
            int idcidade = (Integer.parseInt(request.getParameter("cidadeEndEntrega1")));
            String observacao = request.getParameter("observacaoEndEntrega1");

            Pais pais = new Pais();
            pais.setId(idpais);

            Estado estado = new Estado();
            estado.setId(idestado);

            Cidade cidade = new Cidade(estado);
            cidade.setId(idcidade);

            TipoEndereco tipoEndereco = new TipoEndereco();
            tipoEndereco.setId(1L);

            List<TipoEndereco> listaTipoEndereco = new ArrayList<>();
            listaTipoEndereco.add(tipoEndereco);

            endereco = new Endereco(descricaoEnd, tipoResidencia, tipoLogradouro, logradouro, numero,
                    bairro, cep, cidade, listaTipoEndereco, observacao);
        }

        return endereco;
    }


    @Override
    public void setView(java.lang.Object obj, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacao = request.getParameter("operacao");
        RequestDispatcher rd = null;
        String mensagemVH = null;

        if(operacao.equals("SALVAR")) {
            if (obj instanceof Pedido) {
                UsuarioLogado usuarioLogado = (UsuarioLogado) request.getSession().getAttribute("usuarioLogado");
                request.getSession().setAttribute("carrinhoCompra", new Carrinho(((UsuarioComum) usuarioLogado.getUsuario()).getCliente()));
                request.getSession().setAttribute("pedidoRealizado", obj);
            }
            response.sendRedirect("/ellentex-livrariales/cliente/pedidoRealizado.jsp");
        }

        else if(operacao.equals("ALTERAR")){
            if (obj instanceof Pedido) {
                mensagemVH = "sucesso";
                request.getSession().setAttribute("msg", mensagemVH);
            }
            else{
                request.getSession().setAttribute("msg", obj);
                response.sendRedirect("Pedido?operacao=LISTAR");
            }

            if(obj instanceof PedidoCompra)
                response.sendRedirect("Pedido?operacao=LISTAR_PEDIDO_COMPRA");

            else if(obj instanceof PedidoTroca)
                response.sendRedirect("Pedido?operacao=LISTAR_PEDIDO_TROCA");
        }

        else if(operacao.equals("PEDIDO_DETALHES_CLIENTE")){
            request.getSession().setAttribute("pedidoDetalhes", obj);
            //response.sendRedirect("/ellentex-livrariales/cliente/detalhesPedido.jsp");
            rd = request.getRequestDispatcher("/cliente/detalhesPedido.jsp");
            rd.forward(request, response);
        }

        else if(operacao.equals("PEDIDO_DETALHES_ADMIN")){
            request.getSession().setAttribute("pedidoDetalhes", obj);
            //response.sendRedirect("/ellentex-livrariales/admin/detalhesPedido.jsp");
            rd = request.getRequestDispatcher("/admin/detalhesPedido.jsp");
            rd.forward(request, response);
        }

        else if(operacao.equals("LISTAR") || operacao.equals("CONSULTAR_STATUS") || operacao.equals("LISTAR_PEDIDO_COMPRA") || operacao.equals("LISTAR_PEDIDO_TROCA")){
            request.getSession().setAttribute("listaPedidos", obj);
            rd = request.getRequestDispatcher("/admin/listarPedidos.jsp");
            rd.forward(request, response);
        }

        if(operacao.equals("SALVAR_TROCA")) {
            if (obj instanceof Pedido) {
                request.getSession().setAttribute("pedidoRealizado", obj);
            }
            response.sendRedirect("/ellentex-livrariales/cliente/pedidoRealizado.jsp");
        }

    }
}
