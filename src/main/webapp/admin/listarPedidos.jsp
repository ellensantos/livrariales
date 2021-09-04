<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 12/03/2021
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Lista Pedidos</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
</head>
<body>
<c:if test="${sessionScope.msg eq 'sucesso'}">
    <div class="alert alert-success" role="alert" id="msgRetorno">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>SUCESSO!</strong> Operação bem sucedida!
    </div>
    ${sessionScope.remove("msg")}
</c:if>

<c:if test="${sessionScope.msg ne 'sucesso' and not empty sessionScope.msg}">
    <div class="alert alert-danger" role="alert" id="msgRetorno">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>FALHA!</strong> ${sessionScope.msg} . Tente novamente.
    </div>
    ${sessionScope.remove("msg")}
</c:if>

<jsp:include page="barraLateralAdmin.jsp"></jsp:include>

<div class="container mt-4">    <!-- -->
    <div class="col-lg-9 my-lg-0 my-1">
        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">LISTA DE PEDIDOS</div>
            </div>
            <form action="#">
                <div align="center">
                <div class="form-row">
                    <div class="col">

                        <a href="/ellentex-livrariales/admin/Pedido?operacao=LISTAR_PEDIDO_COMPRA">
                            <button type="button" class="btn btn-success btn-sm"><i class="bi bi-arrow-clockwise"></i> Pedidos de Compra</button>
                        </a>

                        <a href="/ellentex-livrariales/admin/Pedido?operacao=LISTAR_PEDIDO_TROCA">
                            <button type="button" class="btn btn-warning btn-sm"><i class="bi bi-arrow-clockwise"></i> Pedidos de Troca</button>
                        </a>
                    </div>
                </div>
                </div>
            </form>
            <br>

            <div class="form-row">
                <table class="table table-bordered table-light" id="mytable">
                    <thead class="thead-dark" align="center">
                    <tr>
                        <th scope="col">Numero</th>
                        <th scope="col">Data</th>
                        <th scope="col">Itens</th>
                        <th scope="col">Valor Total</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Status</th>
                        <th scope="col">Operações</th>
                    </tr>
                    <tbody>
                <c:if test="${empty listaPedidos}">
                    <script>
                        alert('Nenhum pedido encontrado');
                    </script>
                </c:if>
                    <c:forEach var="pedido" items="${listaPedidos}" varStatus="cont">
                        <tr>
                            <th scope="row"><a href="/ellentex-livrariales/admin/Pedido?operacao=PEDIDO_DETALHES_ADMIN&idPedido=${pedido.id}">#${pedido.id}</a></th>
                            <fmt:formatDate value="${pedido.data}" pattern="dd-MM-yyyy" var = "dataFormatada"/>
                            <td>${dataFormatada}</td>
                            <c:if test="${pedido['class'].simpleName eq 'PedidoCompra'}">
                                <td>${pedido.qtdeItens}</td>
                            </c:if>
                            <c:if test="${pedido['class'].simpleName eq 'PedidoTroca'}">
                                <td>1</td>
                            </c:if>
                            <td><fmt:formatNumber value="${pedido.valorTotal}" type="currency" /></td>
                            <td>
                                <c:if test="${pedido['class'].simpleName eq 'PedidoCompra'}">
                                    Pedido de Compra
                                </c:if>
                                <c:if test="${pedido['class'].simpleName eq 'PedidoTroca'}">
                                    Pedido de Troca
                                </c:if>
                            </td>
                            <td>${pedido.status}</td>

                                <%--             <c:if test="${pedido.status eq 'EM_PROCESSAMENTO'}">
                                                 <th scope="row"><a href="/ellentex-livrariales/admin/Pedido?operacao=ALTERAR&idPedido=${pedido.id}">Aprovar</a></th>
                                             </c:if>--%>

                                <%--                            <c:if test="${pedido.status eq 'RECUSADO' or pedido.status eq 'ENTREGUE' or pedido.status eq 'PRODUTO_RECEBIDO' or pedido.status eq 'EM_PROCESSAMENTO'}">--%>
                            <c:if test="${pedido.status eq 'RECUSADO' or pedido.status eq 'ENTREGUE' or pedido.status eq 'PRODUTO_RECEBIDO'}">
                                <th scope="row">-</th>
                            </c:if>

                            <c:if test="${pedido.status eq 'EM_PROCESSAMENTO'}">
                                <th scope="row"><a id="aprovarPedido${pedido.id}" href="/ellentex-livrariales/admin/Pedido?operacao=ALTERAR&idPedido=${pedido.id}">
                                    <button class="btn btn-primary btn-sm"><i class="bi bi-check-lg"></i> Aprovar</button></a>
                                </th>
                            </c:if>

                            <c:if test="${pedido.status eq 'APROVADO'}">
                                <th scope="row"><a id="liberarEntregaPedido${pedido.id}" href="/ellentex-livrariales/admin/Pedido?operacao=ALTERAR&idPedido=${pedido.id}">
                                    <button class="btn btn-primary btn-sm"><i class="bi bi-truck"></i> Liberar para Entrega</button></a>
                                </th>
                            </c:if>

                            <c:if test="${pedido.status eq 'EM_TRANSPORTE'}">
                                <th scope="row"><a id="entreguePedido${pedido.id}" href="/ellentex-livrariales/admin/Pedido?operacao=ALTERAR&idPedido=${pedido.id}">
                                    <button class="btn btn-primary btn-sm"><i class="bi bi-bag-check-fill"></i> Marcar como Entregue</button></a>
                                </th>
                            </c:if>

                            <c:if test="${pedido.status eq 'EM_TROCA'}">
                                <th scope="row"><a id="autorizarTroca${pedido.id}" href="/ellentex-livrariales/admin/Pedido?operacao=ALTERAR&idPedido=${pedido.id}">
                                    <button class="btn btn-primary btn-sm"><i class="bi bi-arrow-clockwise"></i> Autorizar Troca</button></a>
                                </th>
                            </c:if>

                            <c:if test="${pedido.status eq 'TROCA_AUTORIZADA'}">
                                <th scope="row"><a id="produtoRecebido${pedido.id}" href="/ellentex-livrariales/admin/Pedido?operacao=ALTERAR&idPedido=${pedido.id}" id="informarRecebTroca" onclick="confirmarReentradaEstoque()">
                                    <button class="btn btn-primary btn-sm"><i class="bi bi-bag-check-fill"></i> Produto Recebido</button></a>
                                </th>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                    </thead>
                </table>
            </div>
            <br/>
        </div>
    </div>
</div>
</div>
</body>
<script src="../js/pedido.js"></script>
</html>
