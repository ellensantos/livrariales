<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 14/03/2021
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Cad Livro</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
</head>
<body>
<jsp:include page="barraLateralAdmin.jsp"></jsp:include>

<div class="container mt-4">
    <div class="col-lg-9 my-lg-0 my-2">
        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">PESQUISAR ITEM NO ESTOQUE</div>
            </div>

            <div class="d-flex flex-column">
                <form action="#" method="post">
                    <div class="card" id = "card-cadastro">
                        <div class="card-body" id = "card-cadastro">

                            <div class="card" id = "card-cadastro">
                                <div class="card-body" id = "card-cadastro">
                                    <div class="form-row">
                                        <div class="form-group col-md-3">
                                            <label for="tipoProduto">Tipo Produto</label>
                                            <select class="form-control" name="idPais">
                                                <option selected data-default value="1">Livro</option>
                                            </select>
                                        </div>

                                        <div class="form-group col-md-7">
                                            <label for="produto">Produto</label>
                                            <select class="form-control" name="idProduto">
                                                <option value="1">Rápido e Devagar</option>
                                                <option value="2">Harry Potter - Cálice de Fogo</option>
                                            </select>
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="qtdeProduto">Qtde</label>
                                            <input type="number" class="form-control" value="0" name="qtdeProduto" required>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="valorProduto">Valor Custo</label>
                                            <input type="number" class="form-control" value="0" name="valorProduto" required>
                                        </div>

                                    <div class="form-group col-md-5">
                                        <label for="fornecedor">Fornecedor</label>
                                        <select class="form-control" name="idFornecedor">
                                            <option value="1">Saraiva</option>
                                            <option value="2">Amazon</option>
                                        </select>
                                    </div>


                                        <div class="form-group col-md-4">
                                            <label for="tituloLivro">Data</label>
                                            <input type="text" class="form-control" name="tituloLivro" id="tituloLivro">
                                        </div>
                                    </div>






                        </div><!-- Card Bory Principal -->
                    </div> <!-- Card Bory Principal -->

                    <br>

                    <div align="right">
                        <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="PESQUISAR_CLIENTE">CONSULTAR</button>
                    </div>

                </form>
            </div>
        </div>  <!-- -->
    </div>  <!-- -->
</div>



</body>
</html>
