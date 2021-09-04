package test;

import org.junit.jupiter.api.*;
import pages.*;

/**
 * @author EllenTex
 */

public class CasoDeUsoConducaoTest {
    private HomePage homePage;
    private LoginClientePage loginClientePage;
    private DetalhesLivroPage detalhesLivroPage;
    private CarrinhoPage carrinhoPage;
    private CheckoutPage checkoutPage;
    private PedidoRealizadoPage pedidoRealizadoPage;
    private LoginAdminPage loginAdminPage;
    private AreaAdminPage areaAdminPage;
    private PedidoClientePage pedidoClientePage;
    private String valorCartaoExistente = "10.00";
    private String numPedido;
    private String cupomPromocional = "LES2021";
    private String valorCupomSelecionado;


    @BeforeEach
    public void beforeEach(){
        homePage = new HomePage(null);

        loginClientePage = homePage.abrirPaginaLogin();
        loginClientePage.efetuarLogin("jorge@jorge.com", "Etdsand20*");

        detalhesLivroPage = homePage.conferirLivro(3);
        carrinhoPage = detalhesLivroPage.adicionarLivroCarrinho(1);
        carrinhoPage.continuarComprando();

        homePage.conferirLivro(5);
        detalhesLivroPage.adicionarLivroCarrinho(3);
        carrinhoPage.continuarComprando();

        detalhesLivroPage = homePage.conferirLivro(8);
        carrinhoPage = detalhesLivroPage.adicionarLivroCarrinho(5);
        checkoutPage = carrinhoPage.finalizarCompra();
        checkoutPage.selecionarEndCobranca();
    }

    @AfterEach
    public void afterEach(){
        homePage.quit();
    }

    @Test
    @DisplayName("1a Versão - Pedido com endereço existente, cartão (existente e novo) e visualização de status")
    //@RepeatedTest(0)
    public void primeiroCasoDeUsoConducao(){
        checkoutPage.selecionarEndEntregaExistente();
        checkoutPage.inserirCupomDesconto(cupomPromocional);
        valorCupomSelecionado = checkoutPage.selecionarCupomTroca();
        checkoutPage.pagarCartaoExistente(1,1, valorCartaoExistente);
        checkoutPage.pagarNovoCartao(2, "4127923050472262","Henrique Andrade Marino",1,"428", 5,10,checkoutPage.recuperarTotalPagar(valorCartaoExistente));
        pedidoRealizadoPage = checkoutPage.submeterFormulario();

        numPedido = pedidoRealizadoPage.recuperarNumeroPedidoCompra();

        pedidoClientePage = pedidoRealizadoPage.consultarPedidos();
        pedidoClientePage.consultarPedidoRealizado(numPedido);

        loginAdminPage = homePage.abrirPaginaLoginAdmin();

        loginAdminPage.acessarPaginaLogin();
        areaAdminPage = loginAdminPage.efetuarLogin("admin", "admin");

        areaAdminPage.acessarPedidosRecebidos();
        areaAdminPage.aprovarPedido(numPedido);

        areaAdminPage.alternarParaJaneladeOrigem();
        pedidoClientePage.atualizarTela();

        pedidoClientePage.alternarParaNovaJanelaAberta();
        areaAdminPage.liberarPedidoParaEntrega(numPedido);

        areaAdminPage.alternarParaJaneladeOrigem();
        pedidoClientePage.atualizarTela();

        pedidoClientePage.alternarParaNovaJanelaAberta();
        areaAdminPage.marcarPedidoComoEntregue(numPedido);

        areaAdminPage.alternarParaJaneladeOrigem();
        pedidoClientePage.atualizarTela();

        Assertions.assertTrue(pedidoClientePage.isPaginaConsultarPedido(numPedido));
        Assertions.assertTrue(pedidoClientePage.isStatusPedido("ENTREGUE"));
        Assertions.assertTrue(pedidoClientePage.isCupomPromocional(cupomPromocional));
        Assertions.assertTrue(pedidoClientePage.isCupomTroca(valorCupomSelecionado));
    }

    @Test
    @DisplayName("2a versão - Pedido, status, novo cartão e endereço no perfil")
    public void segundoCasoDeUsoConducao(){
        String descricaoEndereco = "End 2 - Vinculado no Checkout";
        String logradouro = "Rua 30";
        String numeroCartao = "4127923050472262";
        String nomeCartao = "Henrique Andrade Marino";

        checkoutPage.definirNovoEnderecoEntrega(descricaoEndereco, 2, 1,
                logradouro, "34", "Bairro 30","08722431",0,1,1,descricaoEndereco);
        checkoutPage.salvarNovoEnderecoEntrega();

        checkoutPage.inserirCupomDesconto(cupomPromocional);

        valorCupomSelecionado = checkoutPage.selecionarCupomTroca();

        checkoutPage.pagarNovoCartao(2, numeroCartao,nomeCartao,1,"428", 5,10,checkoutPage.recuperarTotalPagar(valorCartaoExistente));
        checkoutPage.salvarNovoCartaoCadastro();

        checkoutPage.pagarCartaoExistente(1,1, valorCartaoExistente);

        pedidoRealizadoPage = checkoutPage.submeterFormulario();

        numPedido = pedidoRealizadoPage.recuperarNumeroPedidoCompra();

        pedidoClientePage = pedidoRealizadoPage.consultarPedidos();
        pedidoClientePage.consultarPedidoRealizado(numPedido);

        loginAdminPage = homePage.abrirPaginaLoginAdmin();

        loginAdminPage.acessarPaginaLogin();
        areaAdminPage = loginAdminPage.efetuarLogin("admin", "admin");

        areaAdminPage.acessarPedidosRecebidos();
        areaAdminPage.aprovarPedido(numPedido);

        areaAdminPage.alternarParaJaneladeOrigem();
        pedidoClientePage.atualizarTela();

        pedidoClientePage.alternarParaNovaJanelaAberta();
        areaAdminPage.liberarPedidoParaEntrega(numPedido);

        areaAdminPage.alternarParaJaneladeOrigem();
        pedidoClientePage.atualizarTela();

        pedidoClientePage.alternarParaNovaJanelaAberta();
        areaAdminPage.marcarPedidoComoEntregue(numPedido);

        areaAdminPage.alternarParaJaneladeOrigem();
        pedidoClientePage.atualizarTela();

        boolean isEntregue = pedidoClientePage.isStatusPedido("ENTREGUE");
        boolean isCupomPromocional = pedidoClientePage.isCupomPromocional(cupomPromocional);
        boolean isCupomTroca = pedidoClientePage.isCupomTroca(valorCupomSelecionado);
        AreaClientePage areaClientePage = homePage.abrirPaginaAreaCliente();
        DadosEnderecoPage dadosEnderecoPage = areaClientePage.abrirPaginaDadosEndereco();
        boolean flgEndereco = dadosEnderecoPage.validarExistencia(descricaoEndereco, logradouro);
        DadosCartaoPage dadosCartaoPage = areaClientePage.abrirPaginaDadosPagamento();
        boolean flgCartao = dadosCartaoPage.validarExistencia(numeroCartao, nomeCartao);

        Assertions.assertTrue(isEntregue);
        Assertions.assertTrue(isCupomPromocional);
        Assertions.assertTrue(isCupomTroca);
        Assertions.assertTrue(flgEndereco);
        Assertions.assertTrue(flgCartao);
    }

    @Test
    @DisplayName("Pedido, status e solicitação de troca do Item")
    public void solicitacaoTrocaItemPedido(){

        checkoutPage.selecionarEndEntregaExistente();
        checkoutPage.inserirCupomDesconto(cupomPromocional);
        valorCupomSelecionado = checkoutPage.selecionarCupomTroca();
        checkoutPage.pagarCartaoExistente(1,1, valorCartaoExistente);
        checkoutPage.pagarNovoCartao(2, "4127923050472262","Henrique Andrade Marino",1,"428", 5,10,checkoutPage.recuperarTotalPagar(valorCartaoExistente));
        pedidoRealizadoPage = checkoutPage.submeterFormulario();

        numPedido = pedidoRealizadoPage.recuperarNumeroPedidoCompra();

        pedidoClientePage = pedidoRealizadoPage.consultarPedidos();
        pedidoClientePage.consultarPedidoRealizado(numPedido);

        loginAdminPage = homePage.abrirPaginaLoginAdmin();

        loginAdminPage.acessarPaginaLogin();
        areaAdminPage = loginAdminPage.efetuarLogin("admin", "admin");

        areaAdminPage.acessarPedidosRecebidos();
        areaAdminPage.aprovarPedido(numPedido);

        areaAdminPage.alternarParaJaneladeOrigem();
        pedidoClientePage.atualizarTela();

        pedidoClientePage.alternarParaNovaJanelaAberta();
        areaAdminPage.liberarPedidoParaEntrega(numPedido);

        areaAdminPage.alternarParaJaneladeOrigem();
        pedidoClientePage.atualizarTela();

        pedidoClientePage.alternarParaNovaJanelaAberta();
        areaAdminPage.marcarPedidoComoEntregue(numPedido);

        areaAdminPage.alternarParaJaneladeOrigem();
        pedidoClientePage.atualizarTela();

        pedidoClientePage.solicitarTroca();
        String numeroPedidoTroca = pedidoRealizadoPage.recuperarNumeroPedidoTroca();
        pedidoClientePage = pedidoRealizadoPage.consultarPedidos();
        pedidoClientePage.consultarPedidoRealizado(numeroPedidoTroca);

        pedidoClientePage.alternarParaNovaJanelaAberta();
        areaAdminPage.autorizarTroca(numeroPedidoTroca);
        areaAdminPage.alternarParaJaneladeOrigem();

        pedidoClientePage.atualizarTela();
        pedidoClientePage.alternarParaNovaJanelaAberta();
        areaAdminPage.marcarPedidoComoRecebidoReentrada(numeroPedidoTroca);
        areaAdminPage.alternarParaJaneladeOrigem();
        pedidoClientePage.atualizarTela();

        Assertions.assertTrue(pedidoClientePage.isPaginaConsultarPedido(numPedido));
        Assertions.assertFalse(pedidoClientePage.isCupomLiberado());
        Assertions.assertTrue(pedidoClientePage.isStatusPedido("PRODUTO_RECEBIDO"));
    }


}
