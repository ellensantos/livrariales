<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 12/03/2021
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Lista de Clientes</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
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

<jsp:include page="barraLateralAdmin.jsp"></jsp:include>
<div class="container mt-4">    <!-- -->
    <div class="col-lg-9 my-lg-0 my-1">

        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">CLIENTES CADASTRADOS</div>
            </div>
            <form action="Cliente">
                <div class="form-row">
                    <div class="form-group col-md-11">
                        <input name="consultaNomeCliente" id="consultaNomeCliente"
                               placeholder="Pesquisar pelo Nome" type="search" class="form-control">

                    </div>
                    <div class="col">
                        <button type="submit" class="btn btn-info" name="operacao" id="operacao" value="PESQUISAR_CLIENTE_NOME">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form>

            <div class="form-row">
                <c:if test="${empty listaCliente}">
                    <div class="h4" style="text-align: center;">Nenhum Cliente Encontrado</div>
                </c:if>
                <table class="table table-bordered table-light" id="mytable">
                    <thead class="thead-dark" align="center">
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Nome</th>
                        <th scope="col">CPF</th>
                        <th scope="col">Status</th>
                        <th scope="col">Operações</th>
                        <th scope="col">Conta</th>
                    </tr>
                    <tbody>
                    <c:forEach var = "cliente" items="${listaCliente}" varStatus="cont">
                        <tr>
                            <th scope="row">${cliente.id}</th>
                            <th scope="row">${cliente.nome}</th>
                            <th scope="row">${cliente.cpf}</th>
                            <th scope="row">${cliente.usuario.status}</th>
                            <th scope="row"><a href="/ellentex-livrariales/admin/Cliente?operacao=PERFIL_CLIENTE&idCliente=${cliente.id}">Detalhes</a></th>

                            <c:if test="${cliente.usuario.status eq true}">
                            <th scope="row">
                                <a href="/ellentex-livrariales/admin/Cliente?operacao=ATIVAR_CLIENTE&idCliente=${cliente.id}">
                                    <button class="btn btn-success btn-sm" disabled><i class="bi bi-unlock"></i>ativar</button></a>
                                <a href="/ellentex-livrariales/admin/Cliente?operacao=DESATIVAR_CLIENTE&idCliente=${cliente.id}">
                                    <button class="btn btn-warning btn-sm"><i class="bi bi-lock"></i>bloquear</button></a></th>
                            </c:if>

                            <c:if test="${cliente.usuario.status eq false}">
                                <th scope="row">
                                    <a href="/ellentex-livrariales/admin/Cliente?operacao=ATIVAR_CLIENTE&idCliente=${cliente.id}">
                                        <button class="btn btn-success btn-sm"><i class="bi bi-unlock"></i>ativar</button></a>
                                    <a href="/ellentex-livrariales/admin/Cliente?operacao=DESATIVAR_CLIENTE&idCliente=${cliente.id}">
                                        <button class="btn btn-warning btn-sm" disabled><i class="bi bi-lock"></i>bloquear</button></a></th>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                    </thead>
                </table>
            </div>
            <br/>
        </div>
    </div>
</div>
</body>
</html>
