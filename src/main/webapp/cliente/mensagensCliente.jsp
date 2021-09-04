<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 27/05/2021
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Mensagens</title>
    <jsp:include page="../navCliente.jsp"></jsp:include>
<%--    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/estilo.css"/>--%>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/estilo.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container mt-4">    <!-- -->
    <div class="row">
        <jsp:include page="barraLateralCli.jsp"></jsp:include>

        <div class="col-lg-9 my-lg-0 my-1">
            <div id="main-content" class="bg-white border">

                <h3 style="color: #912F41; font-weight: bolder">Mensagens Recebidas</h3>
                <form action="Cliente" method="post">
                    <table class="table table-bordered table-striped" style="font-size: 13px">
                        <thead style="color: #912F41; font-weight: bolder">
                        <tr>
                            <th scope="col">MENSAGEM</th>
                            <th scope="col">DATA</th>
                            <th scope="col">STATUS</th>
                            <th scope="col">OPERAÇÕES</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="mensagem" items="${usuarioLogado.usuario.cliente.mensagem}" varStatus="cont">
                            <tr>
                                <th scope="row">${mensagem.descricao}</th>
                                <fmt:formatDate value="${mensagem.data}" pattern="dd-MM-yyyy" var = "dataFormatada"/>
                                <td>${dataFormatada}</td>
                                <td></td>
                                <td><a></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
