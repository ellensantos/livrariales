<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 15/03/2021
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Pedido</title>
    <jsp:include page="../navCliente.jsp"></jsp:include>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/estilo.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src ="../js/livro.js"></script>
    <script type="text/javascript" src ="../js/atualizarCliente.js"></script>
</head>
<body>
<div class="container mt-4">    <!-- -->
    <c:if test="${not empty sessionScope.pedidoRealizado}">
        <c:if test="${pedidoRealizado['class'].simpleName eq 'PedidoCompra'}">
        <div class="form-row">
            <h3 id="numeroPedido">Pedido de Compra #${pedidoRealizado.id} recebido</h3>
        </div>
        <hr class="mb-4">

        <div class="form-row">
            <h5 id="msgPedido">Seu pedido de compra foi recebido. <a href="/ellentex-livrariales/Cliente?operacao=CONSULTAR_PEDIDOS">Consultar Pedidos.</a></h5>
        </div>
        </c:if>

        <c:if test="${sessionScope.pedidoRealizado['class'].simpleName eq 'PedidoTroca'}">
            <div class="form-row">
                <h3 id="numeroPedido">Pedido de Troca #${pedidoRealizado.id} recebido</h3>
            </div>
            <hr class="mb-4">

            <div class="form-row">
                <h5 id="msgPedido">Seu pedido de troca foi recebido. <a href="/ellentex-livrariales/Cliente?operacao=CONSULTAR_PEDIDOS">Consultar Pedidos.</a></h5>
            </div>
        </c:if>
    </c:if>

<c:if test="${empty sessionScope.pedidoRealizado}">
    <div class="form-row">
        <h3>Falha ao receber o pedido</h3>
    </div>
    <hr class="mb-4">

    <div class="form-row">
        <h5>Sistema indisponível, tente novamente mais tarde. <a href="/ellentex-livrariales/index.jsp">Dê uma olhada nos livros disponíveis.</a></h5>
    </div>
</c:if>


</div>

</body>

</html>
