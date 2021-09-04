<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 14/03/2021
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Relatório</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
    <script type="text/javascript" src ="../js/periodo.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/chart.js@3.3.0/dist/chart.min.js"></script>
    <script type="text/javascript" src="../js/graficoLinhas.js"></script>
</head>
<body>
<jsp:include page="barraLateralAdmin.jsp"></jsp:include>

<div class="container mt-4">    <!-- -->
    <div class="h2" style= "text-align:center;">Relatórios de Livros</div><br>
    <div>
        <form action="Pesquisa" method="post" onsubmit=" return validaData()" class="form-inline">
            <label for="dataInicio">Data Início: </label>
            <input name= "dataInicio" id="dataInicio" class="form-control mr-sm-1" type="date" value="${param.dataInicio}" required>

            <label for="dataFim">Data Final: </label>
            <input id="dataFim" name="dataFim" class="form-control mr-sm-1" type="date" value="${param.dataFim}" required>
            <button type="submit" class="form-control btn-info" name="operacao" value="PESQUISA_PERIODO">Consultar</button>
        </form>
    </div>

    <br><br>

    <div class="col-lg-10 col-md-10 col-sm-10 offset-md-0 offset-sm-1" id="grafico" align="center">
        <!-- GRÁFICO LINHA -->
        <div>
            <canvas id="canvas"></canvas>
        </div>
    </div>

</div>


</body>
</html>
