<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 27/04/2021
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Detalhes</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
</head>
<body>
<div class="container mt-4">    <!-- -->
    <div class="row">
        <div class="col-lg-9 my-lg-0 my-1" class="bg-white border">
            <div id="main-content">
                <fmt:formatDate value="${pedidoDetalhes[0].data}" pattern="dd-MM-yyyy" var = "dataFormatada"/>

                <form action="Pedido" method="post">
                    <div class="card" id = "card-cadastro">
                        <div class="card-body" id = "card-cadastro">
                            <c:if test="${pedidoDetalhes[0]['class'].simpleName eq 'PedidoCompra'}">
                                <h3 id="statusPedido" style="color: #912F41; font-weight: bolder">Pedido de Compra #${pedidoDetalhes[0].id} - ${pedidoDetalhes[0].status}</h3> ${dataFormatada}
                                <br><br>
                                <table class="table table-striped table-light"style="font-size: 13px">
                                    <thead style="color: #912F41; font-weight: bolder">
                                    <tr>
                                        <th scope="col">PRODUTO</th>
                                        <th scope="col">CÓDIGO DE BARRAS</th>
                                        <th scope="col">PREÇO</th>
                                        <th scope="col">QUANTIDADE</th>
                                        <th scope="col">TOTAL</th>
                                        <th scope="col">TROCA</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${pedidoDetalhes[0].itens}" varStatus="cont">
                                        <tr>
                                            <th scope="row">${item.produto.titulo}</th>
                                            <th scope="row">${item.produto.codBarras}</th>
                                            <th scope="row"><fmt:formatNumber value="${item.preco / item.qtde}" type="currency" /></th>
                                            <th scope="row">${item.qtde}</th>
                                            <th scope="row"><fmt:formatNumber value="${item.preco}" type="currency" /></th>

                                            <th scope="col">
                                                <c:if test="${empty item.pedidoTroca and pedidoDetalhes[0].status eq 'ENTREGUE'}">
                                                    <a href="/ellentex-livrariales/Pedido?operacao=SALVAR_TROCA&idItem=${item.id}&idPedido${pedidoDetalhes[0].id}" onclick="return confirm('Deseja mesmo solicitar a troca deste item?')">Solicitar Troca
                                                    </a>
                                                </c:if>

                                                <c:if test="${not empty item.pedidoTroca}">
                                                    Pedido: #${item.pedidoTroca.id}
                                                </c:if>
                                            </th>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tfoot>

                                    <tfoot>
                                    <td colspan="4">SubTotal</td>
                                    <th scope="row"><fmt:formatNumber value="${pedidoDetalhes[0].valorTotal - pedidoDetalhes[0].frete}" type="currency" /></th>
                                    <th scope="col"></th>
                                    </tfoot>

                                    <tfoot>
                                    <td colspan="4">Frete (Livrariales Transportadora)</td>
                                    <th scope="row"><fmt:formatNumber value="${pedidoDetalhes[0].frete}" type="currency" /></th>
                                    <th scope="col"></th>
                                    </tfoot>

                                    <tfoot>
                                    <td colspan="4" style="color: #912F41; font-weight: bolder">Total com Descontos</td>
                                    <th scope="row" style="color: #912F41; font-weight: bolder"><fmt:formatNumber value="${pedidoDetalhes[0].valorTotal}" type="currency" /></th>
                                    <th scope="col"></th>
                                    </tfoot>
                                </table>

                                <%--DADOS DE ENTREGA--%>
                                <h4 style="font-weight: bolder" align="center">ENDEREÇO DE ENTREGA</h4>
                                <div class="border-bottom pb-2 ml-2"></div>
                                <table class="table table-striped table-light"style="font-size: 13px" id="tabelaDadosEntrega">
                                    <thead style="color: #912F41; font-weight: bolder">
                                    <tr>
                                        <th scope="col" colspan="5">DESCRIÇÃO: ${pedidoDetalhes[0].enderecoEntrega.descricao}</th>
                                        <th scope="col" ></th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                        <th scope="col"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th scope="row">LOGRADOURO: ${pedidoDetalhes[0].enderecoEntrega.logradouro}</th>
                                        <th scope="row" colspan="4">NUM: ${pedidoDetalhes[0].enderecoEntrega.numero}</th>
                                        <th scope="row"></th>
                                        <th scope="row"></th>
                                        <th scope="row"></th>
                                    </tr>

                                    <tr>
                                        <th scope="row">BAIRRO: ${pedidoDetalhes[0].enderecoEntrega.bairro}</th>
                                        <th scope="row">CEP: ${pedidoDetalhes[0].enderecoEntrega.cep}</th>
                                        <th scope="row">ESTADO: ${pedidoDetalhes[0].enderecoEntrega.cidade.estado.descricao}</th>
                                        <th scope="row">CIDADE: ${pedidoDetalhes[0].enderecoEntrega.cidade.descricao}</th>
                                        <th scope="row"></th>
                                    </tr>
                                    </tbody>
                                </table>
                                <%--DADOS DE ENTREGA--%>

                                <br>


                                <%--DADOS DE PAGAMENTO--%>
                                <h4 style="font-weight: bolder" align="center">DETALHES DE PAGAMENTO</h4>
                                <div class="border-bottom pb-2 ml-2"></div>
                                <%--CARTÃO--%>
                                <c:if test="${not empty pedidoDetalhes[0].pagamento}">
                                    <table class="table table-striped table-light"style="font-size: 13px" >
                                        <thead style="color: #912F41; font-weight: bolder">
                                        <tr>
                                            <th scope="col">CARTÃO</th>
                                            <th scope="col">NOME IMPRESSO</th>
                                            <th scope="col">VALIDADE</th>
                                            <th scope="col">BANDEIRA</th>
                                            <th scope="col">VALOR</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="pagamento" items="${pedidoDetalhes[0].pagamento}" varStatus="cont">
                                            <tr>
                                                <th scope="row">${pagamento.formaPagamento.numero}</th>
                                                <th scope="row">${pagamento.formaPagamento.nomeImpresso}</th>
                                                <th scope="row">${pagamento.formaPagamento.mesVencimento}/${pagamento.formaPagamento.anoVencimento}</th>
                                                <th scope="row">${pagamento.formaPagamento.bandeiraCartao.descricao}</th>
                                                <th scope="row"><fmt:formatNumber value="${pagamento.valor}" type="currency" /></th>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>

                                <%--CUPONS--%>

                                <c:if test="${not empty pedidoDetalhes[0].cupomTroca}">
                                    <table class="table table-striped table-light"style="font-size: 13px" id="tabelaCupomTroca">
                                        <thead style="color: #912F41; font-weight: bolder">
                                        <tr>
                                            <th scope="col">CUPOM DE TROCA</th>
                                            <th scope="col">CÓDIGO</th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                            <th scope="col">VALOR</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="cupTroca" items="${pedidoDetalhes[0].cupomTroca}" varStatus="cont">
                                            <tr>
                                                <th scope="row">${cont.count}</th>
                                                <th scope="row">${cupTroca.codigo}</th>
                                                <th scope="row"></th>
                                                <th scope="row"></th>
                                                <th scope="row"><fmt:formatNumber value="${cupTroca.valor}" type="currency" /></th>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>

                                <c:if test="${not empty pedidoDetalhes[0].cupomDesconto}">
                                    <table class="table table-striped table-light"style="font-size: 13px" id="tabelaCupomDesconto">
                                        <thead style="color: #912F41; font-weight: bolder">
                                        <tr>
                                            <th scope="col"colspan="5">CUPOM DE DESCONTO</th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <th scope="row">${pedidoDetalhes[0].cupomDesconto.codigo}</th>
                                            <th scope="row" colspan="4"><fmt:formatNumber value="${pedidoDetalhes[0].cupomDesconto.valor}" type="currency" /></th>
                                            <th scope="row"></th>
                                            <th scope="row"></th>
                                            <th scope="row"></th>
                                        </tr>
                                        </tbody>
                                    </table>
                                </c:if>
                            </c:if>





                            <c:if test="${pedidoDetalhes[0]['class'].simpleName eq 'PedidoTroca'}">
                                <h3 style="color: #912F41; font-weight: bolder" id="statusPedido">Pedido de Troca #${pedidoDetalhes[0].id} - ${pedidoDetalhes[0].status}</h3> ${dataFormatada}
                                <br><br>
                                <table class="table table-striped table-light"style="font-size: 13px">
                                    <thead style="color: #912F41; font-weight: bolder">
                                    <tr>
                                        <th scope="col">PRODUTO</th>
                                        <th scope="col">CÓDIGO DE BARRAS</th>
                                        <th scope="col">PREÇO</th>
                                        <th scope="col">QUANTIDADE</th>
                                        <th scope="col">TOTAL</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <tr>
                                        <th scope="row">${pedidoDetalhes[0].itemPedido.produto.titulo}</th>
                                        <th scope="row">${pedidoDetalhes[0].itemPedido.produto.codBarras}</th>
                                        <th scope="row">R$ ${pedidoDetalhes[0].valorTotal / pedidoDetalhes[0].itemPedido.qtde}0</th>
                                        <th scope="row">${pedidoDetalhes[0].itemPedido.qtde}</th>
                                        <th scope="row"><fmt:formatNumber value="${pedidoDetalhes[0].valorTotal}" type="currency" /></th>
                                    </tr>

                                    </tbody>

                                    <tfoot>
                                    <td colspan="4">Cupom de Troca</td>
                                    <c:if test="${not empty pedidoDetalhes[0].cupomTroca}">
                                        <th scope="row">${pedidoDetalhes[0].cupomTroca.codigo}</th>
                                    </c:if>
                                    <c:if test="${empty pedidoDetalhes[0].cupomTroca}">
                                        <th scope="row">Não liberado</th>
                                    </c:if>
                                    </tfoot>

                                    <tfoot>
                                    <td colspan="4">Total</td>
                                    <th scope="row"><fmt:formatNumber value="${pedidoDetalhes[0].valorTotal}" type="currency" /></th>
                                    </tfoot>
                                </table>
                            </c:if>
                        </div>
                    </div>
                    <a href="Pedido?operacao=LISTAR_PEDIDOS">Retornar para a listagem de Pedidos</a>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
