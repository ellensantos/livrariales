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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Lista Estoque Livros</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
</head>
<body>
<jsp:include page="barraLateralAdmin.jsp"></jsp:include>

<c:if test="${sessionScope.msg eq 'sucesso'}">
    <div class="alert alert-success" role="alert" id="msgRetorno">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>SUCESSO!</strong> Operação bem sucedida!
    </div>
    ${sessionScope.remove(msg)}
</c:if>

<c:if test="${sessionScope.msg ne 'sucesso' and not empty sessionScope.msg}">
    <div class="alert alert-danger" role="alert" id="msgRetorno">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>FALHA!</strong> ${sessionScope.msg}
    </div>
    ${sessionScope.remove(msg)}
</c:if>


<div class="container mt-4">    <!-- -->
    <div class="col-lg-9 my-lg-0 my-1">

        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">ITENS DISPONÍVEIS EM ESTOQUE</div>
            </div>


            <form action="">
                <div class="form-row">
                    <div class="form-group col-md-11">
                        <input name="" id=""
                               placeholder="Pesquisar" type="search" class="form-control" disabled>

                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-info" name="operacao" id="operacao" value="" disabled>
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form>

            <div class="form-row">
                <table class="table table-bordered table-light" id="mytable">
                    <thead class="thead-dark" align="center">
                    <tr>
                        <th scope="col">Tipo Produto</th>
                        <th scope="col">Cod de Barras</th>
                        <th scope="col">Nome Produto</th>
                        <th scope="col">Quantidade</th>
                        <th scope="col">Valor Venda</th>
                    </tr>

                    <tbody>
                    <c:forEach var = "item" items="${listaItemEstoque}" varStatus="cont">
                        <tr>
                            <th scope="row">${item.produto['class'].simpleName}</th>
                            <th scope="row">${item.produto.codBarras}</th>
                            <th scope="row">${item.produto.titulo}</th>
                            <th scope="row">${item.quantidade}</th>
                            <th scope="row"><fmt:formatNumber value="${item.valorVenda}" type="currency" /></th>
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
</html>
