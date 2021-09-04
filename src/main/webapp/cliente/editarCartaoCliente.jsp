<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 06/03/2021
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cartão</title>
    <jsp:include page="../navCliente.jsp"></jsp:include>
<%--    <link href="css/bootstrap.min.css" rel="stylesheet">--%>
<%--    <link rel="stylesheet" type="text/css" href="css/estilo.css"/>--%>
    <%--<link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/estilo.css" rel="stylesheet" type="text/css"/>--%>
    <script type="text/javascript" src ="js/cartao.js"></script>

</head>
<body>
<c:if test="${sessionScope.msg eq 'sucesso'}">
    <div class="alert alert-success" role="alert" id="msgRetorno">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>SUCESSO!</strong> Operação bem sucedida!
    </div>
    ${sessionScope.remove("msg")}
</c:if>

<c:if test="${sessionScope.msg ne 'sucesso' and not empty sessionScope.msg}">
    <div class="alert alert-danger" role="alert" id="msgRetorno">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>FALHA!</strong> ${sessionScope.msg} . Tente novamente.
    </div>
    ${sessionScope.remove("msg")}
</c:if>
<div class="container mt-4">    <!-- -->
    <div class="row">
        <jsp:include page="barraLateralCli.jsp"></jsp:include>
        <div class="col-lg-9 my-lg-0 my-1">
            <div id="main-content" class="bg-white border">
                <form action="Cliente" method="post">
                    <div class="card" id = "card-cadastro">
                        <div class="card-body" id = "card-cadastro">

                            <input type="text" name="qtdeBandeiraCartao" id="qtdeBandeiraCartao" value="${bandeiraCartao.size()}" hidden="true">
                            <c:forEach var = "bandeiraCartao" items="${bandeiraCartao}" varStatus="cont">
                                <input type="text" class="form-control" name="idBandCartao${cont.count}" id="idBandCartao${cont.count}" value="${bandeiraCartao.id}" hidden="true">
                                <input type="text" class="form-control" name="bandCartao${cont.count}" id="bandCartao${cont.count}" value="${bandeiraCartao.descricao}" hidden="true">
                            </c:forEach>

                            <input type="text" name="qtdeCartao" id="qtdeCartao" value="${usuarioLogado.usuario.cliente.cartao.size()}" hidden="true">

                            <c:forEach var = "cartoes" items="${usuarioLogado.usuario.cliente.cartao}" varStatus="contador">
                                <div id="novoCartao${contador.count}">
                                    <label for="cartao${contador.count}" id= "label-titulos"><b>CARTAO:</b></label>
                                    <div class="card" id = "card-cadastro">
                                        <div class="card-body" id = "card-cadastro">
                                            <div class="form-row">
                                                <div class="form-group col-md-4">
                                                    <input type="text" name="idCartao${contador.count}" id="idCartao${contador.count}" value="${cartoes.id}" hidden="true">

                                                    <label for="numeroCartao${contador.count}" id= "label-titulos">Numero do Cartao</label>
                                                    <input type="text" class="form-control" name="numeroCartao${contador.count}" id="numeroCartao${contador.count}" pattern="[0-9]+$" minlength="16" maxlength="16" value="${cartoes.numero}" required>
                                                </div>

                                                <div class="col-md-6">
                                                    <label for="nomeCartao${contador.count}" id= "label-titulos">Nome Impresso no Cartao</label>
                                                    <input type="text" class="form-control" name="nomeCartao${contador.count}" id="nomeCartao${contador.count}" value="${cartoes.nomeImpresso}" required>
                                                </div>
                                            </div>

                                            <div class="form-row">
                                                <div class="col-md-4">
                                                    <label for="bandeiraCartao${contador.count}" id= "label-titulos">Bandeira do Cartao</label>
                                                    <select class="form-control" id="bandeiraCartao${contador.count}" name="bandeiraCartao${contador.count}" required>
                                                        <option value="${cartoes.bandeiraCartao.id}" selected hidden = "true">${cartoes.bandeiraCartao.descricao}</option>
                                                    </select>
                                                </div>

                                                <div class="col-md-2">
                                                    <label for="cvv${contador.count}" id= "label-titulos">CVV</label>
                                                    <input type="text" class="form-control" name="cvv${contador.count}" id="cvv${contador.count}" minlength="3" maxlength="3" value="${cartoes.cvv}" required>
                                                </div>

                                                <div class="col-md-2">
                                                    <label for="mesVencimento${contador.count}">Mes</label>
                                                    <select class="form-control" id="mesVencimento${contador.count}" name="mesVencimento${contador.count}" required>
                                                        <option value="${cartoes.mesVencimento}" selected hidden = "true">${cartoes.mesVencimento}</option>
                                                        <option>1</option>
                                                        <option>2</option>
                                                        <option>3</option>
                                                        <option>4</option>
                                                        <option>5</option>
                                                        <option>6</option>
                                                        <option>7</option>
                                                        <option>8</option>
                                                        <option>9</option>
                                                        <option>10</option>
                                                        <option>11</option>
                                                        <option>12</option>
                                                    </select>
                                                </div>

                                                <div class="col-md-2">
                                                    <label for="anoVencimento${contador.count}">Ano</label>
                                                    <select class="form-control" id="anoVencimento${contador.count}" name="anoVencimento${contador.count}" required>
                                                        <option value="${cartoes.anoVencimento}" selected hidden = "true">${cartoes.anoVencimento}</option>
                                                        <option>2015</option>
                                                        <option>2016</option>
                                                        <option>2017</option>
                                                        <option>2018</option>
                                                        <option>2019</option>
                                                        <option>2020</option>
                                                        <option>2021</option>
                                                        <option>2022</option>
                                                        <option>2023</option>
                                                        <option>2024</option>
                                                        <option>2025</option>
                                                        <option>2026</option>
                                                        <option>2027</option>
                                                        <option>2028</option>
                                                        <option>2029</option>
                                                        <option>2030</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <c:if test="${contador.count >= 1}">
                                                <br/>
                                                <div align="center">
                                                    <button type="button" class="btn btn-danger" name = "remover${contador.count}" id = "remover${contador.count}" onclick="deletarCartao(this)">Excluir Cartao ${contador.count} </button>
                                                </div>

                                            </c:if>

                                            <script>popularSelectBandeiraCartao("bandeiraCartao${contador.count}");</script>
                                        </div>
                                    </div>
                                </div>
                                <br>
                            </c:forEach>


                            <div id="novoCartao"></div> <!-- Novos Cartões -->

                            <br/>
                            <div align="right">
                                <button type="button" class="btn btn-primary" name="operacao" onclick='adicionarCartao()'>Adicionar Cartão +</button>
                            </div>

                        </div>
                    </div>


                    <br/>

                    <div align="right">
                        <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="SALVAR_CARTAO">SALVAR</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<c:if test="${empty usuarioLogado.usuario.cliente.cartao}">
    <script>
        alert('Nenhum cartão cadastrado até o momento');
    </script>
</c:if>
</html>
