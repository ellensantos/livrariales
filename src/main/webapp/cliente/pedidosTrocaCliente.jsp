<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 27/05/2021
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Pedidos</title>
    <jsp:include page="../navCliente.jsp"></jsp:include>
<%--    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/estilo.css"/>--%>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/estilo.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container mt-4">    <!-- -->
    <div class="row">
        <jsp:include page="barraLateralCli.jsp"></jsp:include>

        <div class="col-lg-9 my-lg-0 my-1">
            <div id="main-content" class="bg-white border">

                <h3 style="color: #912F41; font-weight: bolder">Histórico de Pedidos de Troca </h3>

                <form action="Cliente" method="post">
                    <table class="table table-bordered table-striped" style="font-size: 13px" id="tabelaPedidoCliente">
                        <thead style="color: #912F41; font-weight: bolder">
                        <tr>
                            <th scope="col">PEDIDO</th>
                            <th scope="col">DATA</th>

                            <th scope="col">STATUS</th>
                            <th scope="col">TIPO PEDIDO</th>
                            <th scope="col">TOTAL</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="pedido" items="${usuarioLogado.usuario.cliente.pedido}" varStatus="cont">
                            <c:if test="${pedido['class'].simpleName eq 'PedidoTroca'}">
                            <tr>
                                <th scope="row"><a href="/ellentex-livrariales/Pedido?operacao=PEDIDO_DETALHES_CLIENTE&idPedido=${pedido.id}">#${pedido.id}</a></th>
                                <fmt:formatDate value="${pedido.data}" pattern="dd-MM-yyyy" var = "dataFormatada"/>
                                <td>${dataFormatada}</td>


                                <td>${pedido.status}</td>
                                <td> Pedido de Troca</td>
                                <td><fmt:formatNumber value="${pedido.valorTotal}" type="currency" /></td>
                            </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
