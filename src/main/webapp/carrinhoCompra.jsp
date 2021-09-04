<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 15/03/2021
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Carrinho</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/sessao.js"></script>
    <jsp:include page="nav.jsp"></jsp:include>
</head>
<body id="body">
<c:if test="${not empty sessionScope.erro}">
    <script>
        alert('Quantidade indisponível para compra!');
    </script>
    ${sessionScope.remove("erro")}
</c:if>
<div class="container mt-4">    <!-- -->

    <c:if test="${empty carrinhoCompra or carrinhoCompra.qtdeItens eq 0}">
        <div class="form-row">
            <h3>Seu Carrinho</h3>
        </div>
        <hr class="mb-4">
        <div class="form-row">
            <h5>Seu carrinho de compras ainda está vazio. <a href="/ellentex-livrariales/index.jsp">Dê uma olhada nos livros disponíveis.</a></h5>
        </div>
        <c:if test="${not empty carrinhoCompra and not empty carrinhoCompra.itensRemovidos}">
            <hr class="mb-4">
            <div class="form-row">
                <h4>Itens Expirados</h4>
            </div>
            <table class="table table table-light" id="tabelaCarrinhoItensRemovidos">
                <thead class="thead-light">
                <tr>
                    <th scope="col">Livro</th>
                    <th scope="col">Item</th>
                    <th scope="col">Preço</th>
                    <th scope="col" >Qtde</th>
                    <th scope="col">Total</th>
                    <th scope="col"></th>
                </tr>

                <tbody>
                <c:forEach var = "item" items="${carrinhoCompra.itensRemovidos}" varStatus="cont">
                    <tr>
                        <th scope="row"><a href="/ellentex-livrariales/detalhesLivroCompra.jsp?idProduto=${item.produto.id}"><img width="50" class="rounded mx-auto d-block" src="images/${item.produto.foto}" width="43px" height="65px"></a></th>
                        <th scope="row">${item.produto.titulo}</th>
                        <th scope="row"><fmt:formatNumber value="${item.produto.itemEstoque.valorVenda}" type="currency" /></th>
                        <th scope="row">${item.qtde}</th>
                        <th scope="row"><fmt:formatNumber value="${item.preco}" type="currency" /></th>
                    </tr>
                </c:forEach>
                </tbody>
                </thead>
            </table>
        </c:if>
    </c:if>

    <c:if test="${not empty carrinhoCompra and carrinhoCompra.qtdeItens > 0}">
        <div class="form-row">
            <h3>Seu Carrinho</h3>

            <table class="table table table-light" id="tabelaCarrinho">
                <thead class="thead-light">
                <tr>
                    <th scope="col">Livro</th>
                    <th scope="col">Item</th>
                    <th scope="col">Preço</th>
                    <th scope="col" >Qtde</th>
                    <th scope="col">Total</th>
                    <th scope="col"></th>
                </tr>
                <tbody>
                <c:forEach var = "item" items="${carrinhoCompra.itens}" varStatus="cont">
                    <tr>
                        <th scope="row"><a href="/ellentex-livrariales/detalhesLivroCompra.jsp?idProduto=${item.produto.id}"><img width="50" class="rounded mx-auto d-block" src="images/${item.produto.foto}" width="43px" height="65px"></a></th>
                        <th scope="row">${item.produto.titulo}</th>
                        <th scope="row"><fmt:formatNumber value="${item.produto.itemEstoque.valorVenda}" type="currency" /></th>
                        <th scope="row">
<%--                            <div class="col-md-12">--%>
                                <a href="/ellentex-livrariales/Carrinho?operacao=DEL_UNIDADE_ITEM&idProduto=${item.produto.id}">
                                    <i class="bi bi-dash-circle-fill"></i>
                                </a>

                                <input class="form-group" type="text" value="${item.qtde}" name="qtdeLivroSelecionado${item.produto.id}" id="qtdeLivroSelecionado${item.produto.id}" style="width:12%; text-align: center; font-size: 13px" readonly="readonly"> &nbsp;
                                <a href="/ellentex-livrariales/Carrinho?operacao=ADD_UNIDADE_ITEM&idProduto=${item.produto.id}">
                                    <i class="bi bi-plus-circle-fill"></i>
                                </a>
<%--                            </div>--%>
                        </th>
                        <th scope="row"><fmt:formatNumber value="${item.preco}" type="currency" /></th>
                        <th scope="col"><a href="/ellentex-livrariales/Carrinho?operacao=REMOVER_ITEM_CARRINHO&idProduto=${item.produto.id}"><i class="bi bi-trash"></i></a></th>
                    </tr>
                </c:forEach>
                </tbody>

                <tfoot>
                <th scope="row"><a href="/ellentex-livrariales/Carrinho?operacao=CHECKOUT"><button id="btnFinalizarCompra" class="btn btn-warning btn"><i class="bi bi-cart3"></i> Finalizar Compra </button></a></th>
                <th scope="row"><a href="/ellentex-livrariales/index.jsp"><button id="btnContinuarComprando" class="btn btn-success btn"><i class="bi bi-bag-plus"></i> Continuar Comprando</button></a></th>
                <th scope="row"></th>
                <th scope="row"><a href="carrinhoCompra.jsp"><button class="btn btn-info btn-sm">Atualizar <i class="bi bi-arrow-repeat"></i></button></a></th>
                <th scope="row" style="color: #912F41" ><fmt:formatNumber value="${carrinhoCompra.valorTotal}" type="currency" /></th>
                <th scope="row" ></th>
                </tfoot>

                </thead>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>
