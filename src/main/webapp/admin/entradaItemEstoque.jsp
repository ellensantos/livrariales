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
<html>
<head>
    <title>Estoque</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
</head>
<body>
<jsp:include page="barraLateralAdmin.jsp"></jsp:include>

<div class="container mt-4">
    <div class="col-lg-9 my-lg-0 my-2">
        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">ENTRADA DE ITEM DE LIVRO NO ESTOQUE</div>
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

                                        <div class="form-group col-md-7">
                                            <label for="produto">Produto</label>

                                            <select class="form-control" name="idProduto" required>
                                                <option selected hidden="true" data-default value=""></option>
                                                <c:forEach var = "livro" items="${livros}" varStatus="cont">
                                                    <option value=${livro.id}>${livro.titulo}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="qtdeProduto">Qtde</label>
                                            <input type="number" class="form-control" min="0" name="qtdeProduto" required>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="valorCustoProduto">Valor Custo R$</label>
                                            <input type="number" class="form-control" min="0.1" step="0.1" name="valorCustoProduto" required>
                                        </div>

                                        <div class="form-group col-md-6">
                                            <label for="fornecedor">Fornecedor</label>
                                            <select class="form-control" name="idFornecedor" required>
                                                <option selected hidden="true" data-default value=""></option>
                                                <c:forEach var = "fornecedor" items="${fornecedores}" varStatus="cont">
                                                    <option value=${fornecedor.id}>${fornecedor.nome}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group col-md-3">
                                            <label for="dataEntrada">Data</label>
                                            <input type="date" class="form-control" name="dataEntrada" id="dataEntrada" required>
                                        </div>

                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="valorVendaProduto">Valor Venda R$</label>
                                            <input type="number" class="form-control" min="0.1" step="0.1" id="valorVendaProduto" name="valorVendaProduto" disabled>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <button type="button" class="btn btn-info btn-sm" onclick="habilitarValorManual()"  id="linkValorVendaManual">Inserir valor de Venda Manualmente </button>
                                    </div>

                                    <div class="form-row">
                                        <button type="button" class="btn btn-info btn-sm" onclick="habilitarValorAutomatico()"  id="linkValorVendaAutomatico" hidden>Desfazer</button>
                                    </div>

                                </div><!-- Card Bory Principal -->
                            </div> <!-- Card Bory Principal -->

                            <br>

                            <div align="right">
                                <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="SALVAR">SALVAR</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>  <!-- -->
</div>  <!-- -->
</body>

<script type="text/javascript" src="../js/estoque.js"></script>
</html>
