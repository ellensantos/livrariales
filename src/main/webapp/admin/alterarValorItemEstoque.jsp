<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 14/03/2021
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Alterar Valor de Venda</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
</head>
<body>
<jsp:include page="barraLateralAdmin.jsp"></jsp:include>

<c:forEach var = "item" items="${listaItemEstoque}" varStatus="cont">
    <c:if test="${item.id eq param.idItemEstoque}">

<div class="container mt-4">
    <div class="col-lg-9 my-lg-0 my-2">
        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">ALTERAR VALOR DE VENDA</div>
            </div>

            <div class="d-flex flex-column">
                <form action="ItemLivro" method="post">
                    <div class="card" id = "card-cadastro">
                        <div class="card-body" id = "card-cadastro">

                            <div class="card" id = "card-cadastro">
                                <div class="card-body" id = "card-cadastro">
                                    <div class="form-row">
                                        <div class="form-group col-md-3">
                                            <label for="tipoProduto">Tipo Produto</label>
                                            <select class="form-control" name="tipoProduto" disabled>
                                                <option selected data-default value="1">Livro</option>
                                            </select>
                                        </div>

                                        <input type="text" class="form-control" name="idProduto" value="${item.produto.id}">

                                        <div class="form-group col-md-2">
                                            <label for="id">Código</label>
                                            <input type="text" class="form-control" name="idItemEstoque" value="${item.id}" readonly>
                                        </div>

                                        <div class="form-group col-md-7">
                                            <label for="produto">Produto</label>
                                            <input type="text" class="form-control" name="nomeLivro" value="${item.produto.titulo}" disabled>
                                        </div>

                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-3">
                                            <label for="codDeBarras">Código de Barras</label>
                                            <input type="text" class="form-control" name="codDeBarras" value="${item.produto.codBarras}" disabled>
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="valorVendaProduto">Valor Atual R$</label>
                                            <input type="number" class="form-control" id="valorVendaProduto" name="valorVendaProduto" value="${item.valorVenda}" disabled>
                                        </div>

                                        <div class="form-group col-md-3">
                                            <label for="novoValorVendaProduto">Novo Valor Venda R$</label>
                                            <input type="number" class="form-control" name="novoValorVendaProduto"  id="novoValorVendaProduto" min="0.1" step="0.1" required>
                                        </div>
                                    </div>
                                </div><!-- Card Bory Principal -->
                            </div> <!-- Card Bory Principal -->

                            <br>

                            <div align="right">
                                <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="ALTERAR">SALVAR</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>  <!-- -->
</div>  <!-- -->
</c:if>
</c:forEach>
</body>

<script type="text/javascript" src="../js/estoque.js"></script>
</html>
