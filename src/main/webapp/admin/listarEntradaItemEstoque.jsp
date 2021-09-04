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
    <title>Lista Entrada Itens</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
</head>
<body>
<jsp:include page="barraLateralAdmin.jsp"></jsp:include>

<div class="container mt-4">    <!-- -->
    <div class="col-lg-9 my-lg-0 my-1">

        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">LISTAGEM DE ENTRADAS DE ITEM DE LIVRO</div>
            </div>


            <div class="form-row">
                <table class="table table-bordered table-light" id="mytable">
                    <thead class="thead-dark" align="center">
                    <tr>
                        <th scope="col">Data Entrada</th>
                        <th scope="col">Produto</th>
                        <th scope="col">Quantidade</th>
                        <th scope="col">Fornecedor</th>
                        <th scope="col">Valor de Custo</th>
                    </tr>

                    <tbody>
                    <c:forEach var = "item" items="${listaEntradaItem}" varStatus="cont">
                        <fmt:formatDate value="${item.dataEntrada}" pattern="dd-MM-yyyy" var = "dataFormatada"/>
                        <tr>
                            <th scope="row">${dataFormatada}</th>
                            <th scope="row">${item.livro.titulo}</th>
                            <th scope="row">${item.quantidade}</th>
                            <th scope="row">${item.fornecedor.nome}</th>
                            <th scope="row"><fmt:formatNumber value="${item.valorCusto}" type="currency" /></th>
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
