<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 26/05/2021
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Relatório</title>

    <jsp:include page="nav.jsp"></jsp:include>

    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/estiloHome.css">
    <script type="text/javascript" src ="js/periodo.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/chart.js@3.3.0/dist/chart.min.js"></script>
    <script type="text/javascript" src="js/graficoLinhas.js"></script>

</head>
<body>
<section id="sidebarHome">

    <div class="border-bottom pb-2 ml-2">
        <h4 id="burgundyHome">Pesquisa</h4>
    </div>
    <div class="py-2 border-bottom ml-3">
        <h6 class="font-weight-bold">Mais Vendidos</h6>
        <div id="orangeHome"><span class="fa fa-minus"></span></div>
        <form action="Pesquisa" method="post" onsubmit=" return validaData()"><br>
            <label for="datainicio">Data Início</label> <div> <input class="form-control" type="date" name="dataInicio" id="dataInicio" value="${param.dataInicio}" onkeydown="return false" required> </div>
            <br>
            <label for="datainicio">Data Fim</label> <div> <input class="form-control" type="date" name="dataFim" id="dataFim" value="${param.dataFim}" onkeydown="return false" required> </div>
            <br><button class="form-control btn-info" type="submit" name="operacao" value="PESQUISA_PERIODO">Consultar </button>
        </form>
    </div>

    <br>
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
        <form action="#" method="post">
            <div class="row">
                <div class="col-lg-10 col-md-10 col-sm-10 offset-md-0 offset-sm-1" id="grafico">
                    <!-- GRÁFICO LINHA -->
                    <c:set var="dataInicio" value="${fn:replace(param.dataInicio,'-' ,'/').split('/')}" />
                    <c:set var="dataFim" value="${fn:replace(param.dataFim,'-' ,'/').split('/')}" />
                    <h3>Livros Vendidos - Período: ${dataInicio[2]}/${dataInicio[1]}/${dataInicio[0]} até ${dataFim[2]}/${dataFim[1]}/${dataFim[0]} </h3>
                    <div>
                        <canvas id="canvas"></canvas>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>
</body>
</html>
