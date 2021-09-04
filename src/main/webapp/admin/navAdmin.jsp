<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 13/03/2021
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Bootstrap CSS -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.0/font/bootstrap-icons.css">
<link rel="stylesheet" type="text/css" href="../css/estiloAdmin.css"/>
<link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"/>
<link rel="stylesheet" href="../css/bootstrap.min.css" >
<script type="text/javascript"src="../js/mensagens.js"></script>
<jsp:useBean id="dataAtual" class="java.util.Date"/>

<!-- Bootstrap CSS -->

<nav class="navbar navbar-expand-lg navbar-light bg-purple">
    <div class="container" id="principal">
        <div class="collapse navbar-collapse" id="mynav">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <a class="navbar-brand" href="indexAdmin.jsp" style="color: #912F41"> <b><i class="bi bi-book-half"></i> LIVRARIALES - PAINEL ADMINISTRATIVO <i class="bi bi-gear"></i></b></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"></a>
                <a class="navbar-brand"><fmt:formatDate value="${dataAtual}" pattern="dd-MM-yyyy"/></a>

                <div id = "divmenuadmin" align="right">
                    <form action="Logout" class="form-inline">
                        <c:if test="${not empty usuarioLogado}">
                            <a class="navbar-brand" id="usuarioLogadoNav">USER LOGADO: ${usuarioLogado.usuario.email}</a>
                            <a class="navbar-brand"></a>
                            <a class="navbar-brand"></a>
                            <a class="navbar-brand"></a>
                            <a class="navbar-brand">
                                <button class="btn btn-danger" type="submit" name="operacao" id="operacao" value="DESLOGAR">DESLOGAR</button>
                            </a>
                        </c:if>
                    </form>
                </div>
            </ul>
        </div>
    </div>
</nav>