<%--
  Created by IntelliJ IDEA.
  User: Your name
  Date: 01/03/2021
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- Bootstrap CSS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.0/font/bootstrap-icons.css">
<link rel="stylesheet" type="text/css" href="css/estilo.css"/>
<link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"/>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.bundle.js"></script>
<script src="js/bootstrap.js"></script>
<!-- Bootstrap CSS -->

<!--JS MENSAGENS-->
<script type="text/javascript" src ="js/mensagens.js"></script>

<nav class="navbar navbar-expand-sm navbar-light bg-light">
    <div class="container" id="principal">
        <div class="collapse navbar-collapse" id="mynav">

            <ul class="navbar-nav">
                <a class="navbar-brand" href="/ellentex-livrariales/index.jsp" style="color: #912F41"> <b><i class="bi bi-book-half"></i> LIVRARIALES</b></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>

                <form class="form-inline">
                    <input class="form-control mr-sm-1" type="search" placeholder="Buscar">
                    <button class="btn btn-primary btn-sm" type="submit" >Buscar</button>
                </form>

                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>


                <a class="navbar-brand" href="carrinhoCompra.jsp"><i class="bi bi-cart3" style="font-size: 3rem; color: #912F41;"></i></a>

                <form class="form-inline">
                    <c:if test="${not empty usuarioLogado}">
                        <c:if test="${carrinhoCompra.qtdeItens > 0}">
                            <a class="navbar-brand"><div id="sessao" style="font-size: 13px; color: #912F41;">Sessão: </div></a>
                            <a class="navbar-brand"><div id="tempoSessao" style="font-size: 13px; color: #912F41;"></div></a>
                        </c:if>
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary btn dropdown-toggle" id="usuarioLogadoNav"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <c:set var="nome" value="${fn:split(usuarioLogado.usuario.cliente.nome, ' ')}" />
                                Olá ${nome[0]}
                            </button>

                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="Cliente?operacao=CONSULTAR_DADOS">Dados Pessoais</a>
                                <a class="dropdown-item" href="Cliente?operacao=CONSULTAR_MENSAGENS">Mensagens</a>
                                <a class="dropdown-item" href="Cliente?operacao=CONSULTAR_PEDIDOS">Pedidos</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="Logout?operacao=DESLOGAR">Deslogar</a>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${empty usuarioLogado}">
                        <a class="navbar-brand">
                            <a href="formLogin.jsp">Entre ou Cadastre-se</a>
                        </a>
                    </c:if>
                </form>
            </ul>
        </div>
    </div>
</nav>