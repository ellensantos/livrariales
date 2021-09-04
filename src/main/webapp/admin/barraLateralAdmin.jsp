<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 13/03/2021
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Bootstrap CSS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.0/font/bootstrap-icons.css">
<link rel="stylesheet" type="text/css" href="../css/estiloAdmin.css"/>
<link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"/>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap CSS -->

<div class="col-lg-3 my-lg-0 my-md-1">
    <div id="sidebar" class="bg-purple">
        <div class="h4"><i class="bi bi-list"></i> MENU PRINCIPAL</div>
        <ul>

            <li>
                <div class="fas fa-box-open pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div id="tituloBarraLateral" style="color: #912F41"><i class="bi bi-caret-down-fill" ></i> PEDIDOS DE COMPRA</div>
                    <a id="pedidoRecebido" href="${pageContext.request.contextPath}/admin/Pedido?operacao=CONSULTAR_STATUS&statusPedido=EM_PROCESSAMENTO" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Recebidos</div></a>
                    <a id="pedidoAprovado" href="${pageContext.request.contextPath}/admin/Pedido?operacao=CONSULTAR_STATUS&statusPedido=APROVADO" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Aprovados</div></a>
                    <a id="pedidoEmTransporte" href="${pageContext.request.contextPath}/admin/Pedido?operacao=CONSULTAR_STATUS&statusPedido=EM_TRANSPORTE" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Em Transporte</div></a>
                    <a id="pedidoEntregue" href="${pageContext.request.contextPath}/admin/Pedido?operacao=CONSULTAR_STATUS&statusPedido=ENTREGUE" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Entregue</div></a>
                    <a id="pedidoRecusado" href="${pageContext.request.contextPath}/admin/Pedido?operacao=CONSULTAR_STATUS&statusPedido=RECUSADO" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Recusados</div></a>
                    <a id="pedidoReprovado" href="${pageContext.request.contextPath}/admin/Pedido?operacao=CONSULTAR_STATUS&statusPedido=REPROVADO" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Reprovados</div></a>
                    <a id="listarPedidoCompra" href="${pageContext.request.contextPath}/admin/Pedido?operacao=LISTAR_PEDIDO_COMPRA" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Listar Todos os Pedidos</div></a>
                </div>
            </li>

            <li>
                <div class="fas fa-box-open pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div id="tituloBarraLateral" style="color: #912F41"><i class="bi bi-caret-down-fill"></i> PEDIDOS DE TROCA</div>
                    <a id="pedidoEmTroca" href="${pageContext.request.contextPath}/admin/Pedido?operacao=CONSULTAR_STATUS&statusPedido=EM_TROCA" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Recebidos</div></a>
                    <a id="pedidoTrocaAutorizada" href="${pageContext.request.contextPath}/admin/Pedido?operacao=CONSULTAR_STATUS&statusPedido=TROCA_AUTORIZADA" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Autorizados</div></a>
                    <a id="pedidoProdutoRecebido" href="${pageContext.request.contextPath}/admin/Pedido?operacao=CONSULTAR_STATUS&statusPedido=PRODUTO_RECEBIDO" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Produto Recebido</div></a>
                    <a id="listarPedidoTroca" href="${pageContext.request.contextPath}/admin/Pedido?operacao=LISTAR_PEDIDO_TROCA" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Listar Todos os Pedidos</div></a>
                </div>
            </li>

            <li>
                <div class="fas fa-box-open pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div id="tituloBarraLateral" style="color: #912F41"><i class="bi bi-caret-down-fill"></i> CLIENTES</div>
                    <a id="listarClientes" href="${pageContext.request.contextPath}/admin/Cliente?operacao=LISTAR_CLIENTES" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Listar Clientes</div></a>
                    <a id="pesquisarCliente" href="${pageContext.request.contextPath}/admin/pesquisarCliente.jsp"><div class="link"><i class="bi bi-chevron-double-right"></i> Pesquisar Cliente</div></a>
                </div>
            </li>

            <li>
                <div class="fas fa-box-open pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div id="tituloBarraLateral" style="color: #912F41"><i class="bi bi-caret-down-fill"></i> LIVROS</div>
                    <a id="cadastrarLivro" href="${pageContext.request.contextPath}/admin/Livro?operacao=CADASTRAR_LIVRO" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Cadastrar Livro</div></a>
                    <a id="pesquisarLivro" href="${pageContext.request.contextPath}/admin/Livro?operacao=PESQUISAR_LIVRO" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Pesquisar Livro</div></a>
                    <a id="listarLivros" href="${pageContext.request.contextPath}/admin/Livro?operacao=LISTAR_LIVROS" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Listar Todos os Livros</div></a>
                </div>
            </li>

            <li>
                <div class="fas fa-box-open pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div id="tituloBarraLateral" style="color: #912F41"><i class="bi bi-caret-down-fill"></i> ESTOQUE</div>
                    <a class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Pesquisar Items #</div></a>
                    <a id="entradaEstoque" href="${pageContext.request.contextPath}/admin/ItemLivro?operacao=ENTRADA_ESTOQUE" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Entrada de Item</div></a>
                    <a id="listarEstoqueLivro" href="${pageContext.request.contextPath}/admin/Estoque?operacao=LISTAR_LIVROS" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Listar Todos os Itens</div></a>
                    <a href="${pageContext.request.contextPath}/admin/ItemLivro?operacao=CONSULTAR_PENDENTES" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Valor de Custo Pendentes</div></a>
                    <a href="${pageContext.request.contextPath}/admin/ItemLivro?operacao=CONSULTAR" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Listagem de Entradas no Estoque</div></a>
                </div>
            </li>
            <li>
                <div class="fas fa-box pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div id="tituloBarraLateral" style="color: #912F41"> <i class="bi bi-caret-down-fill"></i> RELATÓRIOS</div>
                    <a id="livroMaisVendido" href="relatorioLivro.jsp" class="text-decoration-none d-flex align-items-start"><div class="link"><i class="bi bi-chevron-double-right"></i> Mais Vendidos</div></a>
                </div>
            </li>

        </ul>
    </div>
</div>