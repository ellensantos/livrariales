<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 30/03/2021
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Detalhes</title>

    <jsp:include page="nav.jsp"></jsp:include>

    <link rel="stylesheet" href="css/estiloHome.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script type="text/javascript" src ="js/periodo.js"></script>
    <script type="text/javascript" src="js/sessao.js"></script>
</head>
<body>
 <c:forEach var = "livros" items="${livrosCatalogo}" varStatus="cont">
    <c:if test="${livros.id eq param.idProduto}">
        <!-- products section -->
        <section id="products">
            <div class="container">
                <form action="Carrinho">
                    <div class="row" id="detalhesLivro">
                        <div class="col-lg-3 col-md-6 col-sm-10 offset-md-0 offset-sm-1">
                            <div class="card" id="cardProdutos">
                                <img class="rounded mx-auto d-block" src="images/${livros.foto}" width="261px" height="387px">
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 col-sm-10 offset-md-0 offset-sm-1">
                            <div class="card-box" id = "cardPagamento">
                                <div align="center">
                                    <br><br>
                                    <div class="text-muted">Cod de Barras: ${livros.codBarras}</div>
                                    <h3 id="nomeLivro">${livros.titulo}</h3>
                                    <br>
                                    Vendido e Entregue por: LIVRARIALES
                                    <br><br>

                                    <c:if test="${not empty livros.itemReservado}">
                                        <c:if test="${not empty livros.itemEstoque and (livros.itemEstoque.quantidade - livros.itemReservado.qtde) > 0}">
                                            <b style="font-size: x-large"><fmt:formatNumber value="${livros.itemEstoque.valorVenda}" type="currency" /></b><br>
                                            <div class="text-muted">em 1x no crédito</div>

                                            <c:if test="${(livros.itemEstoque.quantidade - livros.itemReservado.qtde) <= 10}">
                                                <br>
                                                <div class="text-muted">últimas ${(livros.itemEstoque.quantidade - livros.itemReservado.qtde)} unidades disponíveis</div>
                                            </c:if>
                                        </c:if>
                                    </c:if>

                                    <c:if test="${empty livros.itemReservado}">
                                        <c:if test="${not empty livros.itemEstoque and livros.itemEstoque.quantidade > 0}">
                                            <b style="font-size: x-large"><fmt:formatNumber value="${livros.itemEstoque.valorVenda}" type="currency" /></b><br>
                                            <div class="text-muted">em 1x no crédito</div>

                                            <c:if test="${livros.itemEstoque.quantidade - livros.itemReservado.qtde <= 10}">
                                                <br>
                                                <div class="text-muted">últimas ${livros.itemEstoque.quantidade} unidades disponíveis</div>
                                            </c:if>
                                        </c:if>
                                    </c:if>

                                    <c:if test="${empty livros.itemReservado}">
                                        <c:if test="${empty livros.itemEstoque or livros.itemEstoque.quantidade <= 0}">
                                            <b style="font-size: x-large">Produto Indisponível</b><br>
                                        </c:if>
                                    </c:if>

                                    <c:if test="${not empty livros.itemReservado}">
                                        <c:if test="${livros.itemEstoque.quantidade - livros.itemReservado.qtde == 0}">
                                            <b style="font-size: x-large">Produto Indisponível</b><br>
                                        </c:if>
                                    </c:if>


                                    <br><br><br>
                                    <c:if test="${empty livros.itemReservado}">
                                        <c:if test="${not empty livros.itemEstoque and livros.itemEstoque.quantidade > 0}">


                                            Quantidade<br><br>
                                            <div class="col-md-12">
                                                <button class="btn btn-info btn-sm" type="button" name="delLivroSelecionado00" id="delLivroSelecionado00" onclick="subtrairUnidade(this)"><i class="fa fa-minus-circle"aria-hidden="true"></i></button> &nbsp;
                                                <input class="form-group" type="text" value="1" name="qtdeLivroSelecionado00" id="qtdeLivroSelecionado00" style="width:8%; text-align: center;" readonly> &nbsp;
                                                <button class="btn btn-info btn-sm" type="button" name="addLivroSelecionado00" id="addLivroSelecionado00" onclick="somarUnidade(this)"><i class="fa fa-plus-circle" aria-hidden="true"></i></button>
                                            </div>
                                            <br><br>
                                            <input type="text" name="idProduto" value="${livros.id}" hidden="true">
                                            <button type="submit" class="btn btn-warning btn w-50 my-2" name="operacao" value="ADD_ITEM_CARRINHO">COMPRAR</button>
                                        </c:if>
                                    </c:if>

                                    <c:if test="${not empty livros.itemReservado}">
                                        <c:if test="${not empty livros.itemEstoque and (livros.itemEstoque.quantidade - livros.itemReservado.qtde) > 0}">

                                            Quantidade<br><br>
                                            <div class="col-md-12">
                                                <button class="btn btn-info btn-sm" type="button" name="delLivroSelecionado00" id="delLivroSelecionado00" onclick="subtrairUnidade(this)"><i class="fa fa-minus-circle"aria-hidden="true"></i></button> &nbsp;
                                                <input class="form-group" type="text" value="1" name="qtdeLivroSelecionado00" id="qtdeLivroSelecionado00" style="width:8%; text-align: center;">
                                                <button class="btn btn-info btn-sm" type="button" name="addLivroSelecionado00" id="addLivroSelecionado00" onclick="somarUnidade(this)"><i class="fa fa-plus-circle" aria-hidden="true"></i></button>
                                            </div>
                                            <br><br>
                                            <input type="text" name="idProduto" value="${livros.id}" hidden="true">
                                            <button type="submit" class="btn btn-warning btn w-50 my-2" name="operacao" value="ADD_ITEM_CARRINHO">COMPRAR</button>
                                        </c:if>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                    </div>

                    <br><br><br><br><br><br><br><br><br><br>

                    <div class="row">
                        <div class="col-lg-3 col-md-6 col-sm-10 offset-md-0 offset-sm-1">
                            <table class="table table-hover" id="tabelaDetalhesLivro">
                                <thead class="thead-light" align="center">

                                <tr>
                                    <th scope="row" class="tituloTabelaLiv"> <h4>Características</h4></th>
                                    <th scope="row" class="tituloTabelaLiv"></th>
                                    <th scope="row" class="tituloTabelaLiv"></th>
                                    <th scope="row" class="tituloTabelaLiv"></th>
                                    <th scope="row" class="tituloTabelaLiv"></th>
                                    <th scope="row" class="tituloTabelaLiv"></th>
                                </tr>

                                <tbody>
                                <tr>
                                    <th scope="row">TÍTULO:</th>
                                    <td colspan="5">${livros.titulo}</td>
                                </tr>
                                <tr>
                                    <th scope="row">AUTOR: </th>
                                    <td colspan="5">${livros.autor.nome}</td>
                                </tr>

                                <tr>
                                    <th scope="row">ANO: </th>
                                    <td colspan="5">${livros.anoLancamento}</td>
                                </tr>

                                <tr>
                                    <th scope="row"><b></b>EDIÇÃO: </th>
                                    <td colspan="5">${livros.edicao}</td>
                                </tr>

                                <tr>
                                    <th scope="row">ISBN: </th>
                                    <td colspan="5">${livros.isbn}</td>
                                </tr>

                                <tr>
                                    <th scope="row">PÁGINAS: </th>
                                    <td colspan="5">${livros.qtdePagina}</td>
                                </tr>

                                <tr>
                                    <th scope="row">SINOPSE: </th>
                                    <td colspan="5">${livros.sinopse}</td>
                                </tr>

                                <tr>
                                    <th scope="row">DIMENSÕES:</th>
                                    <td colspan="5">${livros.dimensao.altura} A x ${livros.dimensao.largura} L x ${livros.dimensao.comprimento} C (Altura x Largura x Comprimento)</td>
                                </tr>

                                <tr>
                                    <th scope="row">PESO: </th>
                                    <td colspan="5">${livros.dimensao.peso} kg</td>
                                </tr>
                                </tbody>
                                </thead>
                            </table>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </c:if>
</c:forEach>
</body>
<script type="text/javascript" src ="js/livro.js"></script>
</html>
