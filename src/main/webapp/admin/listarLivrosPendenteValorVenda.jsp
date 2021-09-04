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
    <title>Lista Livros</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
</head>
<body>
<jsp:include page="barraLateralAdmin.jsp"></jsp:include>

<div class="container mt-4">    <!-- -->
    <div class="col-lg-9 my-lg-0 my-1">

        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">ITENS VALOR DE VENDA PENDENTE</div>
            </div>

            <div class="form-row">
                <table class="table table-bordered table-light" id="mytable">
                    <thead class="thead-dark" align="center">
                    <tr>
                        <th scope="col">Livro</th>
                        <th scope="col">Data Entrada</th>
                        <th scope="col">Valor de Venda Atual</th>
                        <th scope="col">Valor de Venda a ser Aprovado</th>
                        <th scope="col">Operações</th>
                    </tr>
                    <tbody>

                <c:if test="${empty listaItemLivroPendente}">
                    <script>
                        alert('Nenhuma pendência encontrada');
                    </script>
                </c:if>

                    <c:forEach var = "itemLivro" items="${listaItemLivroPendente}" varStatus="cont">
                        <tr>
                            <th scope="row">${itemLivro.livro.titulo}</th>
                            <fmt:formatDate value="${itemLivro.dataEntrada}" pattern="dd-MM-yyyy" var = "dataFormatada"/>
                            <th scope="row">${dataFormatada}</th>
                            <th scope="row">R$${itemLivro.itemEstoque.valorVenda}0</th>
                            <th scope="row">R$${itemLivro.valorVenda}0</th>
                            <th scope="row">
                                <a href="/ellentex-livrariales/admin/ItemLivro?operacao=NEGAR_VALOR&idItemLivro=${itemLivro.id}">
                                <button class="btn btn-danger btn-sm"><i class="bi bi-bag-check-fill"></i> NEGAR</button></a>
                                <a href="/ellentex-livrariales/admin/ItemLivro?operacao=APROVAR_VALOR&idItemLivro=${itemLivro.id}">
                                <button class="btn btn-success btn-sm"><i class="bi bi-bag-check-fill"></i> APROVAR</button></a>
                            </th>

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
