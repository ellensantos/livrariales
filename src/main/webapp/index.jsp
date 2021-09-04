<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 30/03/2021
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Home Page</title>
    <jsp:include page="nav.jsp"></jsp:include>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="css/estiloHome.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script type="text/javascript" src ="js/periodo.js"></script>
    <script type="text/javascript" src="js/sessao.js"></script>
</head>
<body>
<section id="sidebarHome">
<%--    <div class="border-bottom pb-2 ml-2">
        <h4 id="burgundyHome">Mais Vendidos</h4>
    </div>
    <div class="py-2 border-bottom ml-3">
&lt;%&ndash;        <h6 class="font-weight-bold">Mais Vendidos</h6>&ndash;%&gt;
        <div id="orangeHome"><span class="fa fa-minus"></span></div>
        <form action="Pesquisa" method="post" onsubmit=" return validaData()"><br>
            <label for="datainicio">Data Início</label> <div> <input class="form-control" type="date" name="dataInicio" id="dataInicio" required> </div>
            <br>
            <label for="datainicio">Data Fim</label> <div> <input class="form-control" type="date" name="dataFim" id="dataFim" required> </div>
            <br><button type="submit" class="form-control btn-info" name="operacao" value="PESQUISA_PERIODO">Consultar </button>
        </form>
    </div>

    <br>--%>
    <div class="border-bottom pb-2 ml-2">
        <h4 id="burgundyHome">Filtro</h4>
    </div>
    <div class="py-2 border-bottom ml-3">
        <h6 class="font-weight-bold">Categorias</h6>
        <div id="orangeHome"><span class="fa fa-minus"></span></div>
        <br>
        <form>
            <div class="form-group"> <input type="checkbox" id="administracao" disabled> <label for="administracao">Administração</label> </div>
            <div class="form-group"> <input type="checkbox" id="romance" disabled> <label for="romance">Romance</label> </div>
            <div class="form-group"> <input type="checkbox" id="autoajuda" disabled> <label for="autoajuda">Autoajuda</label> </div>
        </form>
    </div>
    <div class="py-2 ml-3">
        <h6 class="font-weight-bold">Promoções</h6>
        <div id="orangeHome"><span class="fa fa-minus"></span></div>
        <br>
        <form>
            <div class="form-group"> <input type="checkbox" id="25off" disabled> <label for="25off">25% de desconto</label> </div>
            <div class="form-group"> <input type="checkbox" id="5off" disabled> <label for="5off" id="off">5% de desconto</label> </div>
        </form>
    </div>

</section>

<!-- products section -->
<section id="products">
    <div class="container">
        <form action="AddCart" method="post">
            <div class="row">
                <%--<c:if test="${not empty livros.itemEstoque and livros.itemEstoque.quantidade > 0}">
                </c:if>--%>
                <c:forEach var = "livros" items="${livrosCatalogo}" varStatus="cont">

                        <c:if test="${cont.count % 4 eq 0}">
                            <br>
                            <div class="row">
                        </c:if>

                        <div class="col-lg-3 col-md-6 col-sm-10 offset-md-0 offset-sm-1">
                            <div class="card" id="cardProdutos">
                                <img class="rounded mx-auto d-block" src="images/${livros.foto}" width="87px" height="129px">
                                <div class="card-body" id="card-bodyProdutos">
                                    <div align="center" id="tituloLivroHome">${livros.titulo}</div>
                                    <div class="d-flex flex-row my-2">

                                        <c:if test="${not empty livros.itemReservado}">
                                            <c:if test="${not empty livros.itemEstoque and (livros.itemEstoque.quantidade - livros.itemReservado.qtde) > 0 and livros.status ne 'false'}">
                                                <div class="text-muted"><fmt:formatNumber value="${livros.itemEstoque.valorVenda}" type="currency" /></div>
                                            </c:if>

                                            <c:if test="${empty livros.itemEstoque or (livros.itemEstoque.quantidade - livros.itemReservado.qtde) eq 0 or livros.status ne 'true'}">
                                                <div class="text-muted">Produto Indisponível</div>
                                            </c:if>
                                        </c:if>

                                        <c:if test="${empty livros.itemReservado}">
                                            <c:if test="${not empty livros.itemEstoque and livros.itemEstoque.quantidade > 0 and livros.status ne 'false'}">
                                                <div class="text-muted"><fmt:formatNumber value="${livros.itemEstoque.valorVenda}" type="currency" /></div>
                                            </c:if>

                                            <c:if test="${empty livros.itemEstoque or livros.itemEstoque.quantidade eq 0 or livros.status ne 'true'}">
                                                <div class="text-muted">Produto Indisponível</div>
                                            </c:if>
                                        </c:if>
                                    </div> <a id="conferirLivro${cont.count}" class="btn btn-primary btn-sm w-100  my-2" href="detalhesLivroCompra.jsp?idProduto=${livros.id}">Conferir</a>
                                </div>
                            </div>
                        </div>
                        <c:if test="${cont.count % 4 eq 0}">
                            </div>
                        </c:if>
                </c:forEach>
            </div>
        </form>
    </div>
</section>
</body>

</html>
