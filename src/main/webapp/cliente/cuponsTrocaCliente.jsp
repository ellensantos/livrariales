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
    <title>Cupons</title>
    <jsp:include page="../navCliente.jsp"></jsp:include>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/estilo.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container mt-4">    <!-- -->
    <div class="row">
        <jsp:include page="barraLateralCli.jsp"></jsp:include>
        <div class="col-lg-9 my-lg-0 my-1">
            <div id="main-content" class="bg-white border">
                <h3 style="color: #912F41; font-weight: bolder">Cupons de Troca</h3>
                <form action="Cliente" method="post">
                    <table class="table table-bordered table-striped" style="font-size: 13px">
                        <thead style="color: #912F41; font-weight: bolder">
                        <tr>
                            <th scope="col">CODIGO</th>
                            <th scope="col">VALOR</th>
                            <th scope="col">VALIDADE</th>
                            <th scope="col">STATUS</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="cupom" items="${usuarioLogado.usuario.cliente.cupom}" varStatus="cont">
                            <tr>
                                <th scope="row">${cupom.codigo}</th>
                                <th scope="row"><fmt:formatNumber value="${cupom.valor}" type="currency" /></th>
                                <fmt:formatDate value="${cupom.validade}" pattern="dd-MM-yyyy" var = "dataFormatada"/>
                                <td>${dataFormatada}</td>
                                <td>${cupom.status}</td>
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
<c:if test="${empty usuarioLogado.usuario.cliente.cupom}">
    <script>
        alert('Nenhum cupom de troca disponível');
    </script>
</c:if>

</html>
