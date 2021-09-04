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
    <title>Lista Livros</title>
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
                <div class="h2" style= "text-align:center;">LIVROS CADASTRADOS</div>
            </div>
            <form action="Livro">
                <div class="form-row">
                    <div class="form-group col-md-11">
                        <input name="consultaTituloLivro" id="consultaTituloLivro"
                               placeholder="Pesquisar pelo Titulo" type="search" class="form-control">
                    </div>

                    <div class="col">
                        <button type="submit" class="btn btn-info" name="operacao" id="operacao" value="PESQUISAR_LIVRO_TITULO">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form>

            <div class="form-row">
                <c:if test="${empty listaLivrosConsultados}">
                    <div class="h4" align="center">Nenhum Livro Encontrado</div>
                </c:if>

                <table class="table table-bordered table-light" id="mytable">
                    <thead class="thead-dark" align="center">
                    <tr>
                        <th scope="col">Código</th>
                        <th scope="col">Título</th>
                        <th scope="col">ISBN</th>
                        <th scope="col">Status</th>
                        <th scope="row">Categ Inativação</th>
                        <th scope="col">Cód de Barras</th>
                        <th scope="col">Perfil</th>
                        <th scope="col">Operações</th>

                    </tr>

                    <tbody>
                    <c:forEach var = "livro" items="${listaLivrosConsultados}" varStatus="cont">
                        <tr>
                            <th scope="row">${livro.id}</th>
                            <th scope="row">${livro.titulo}</th>
                            <th scope="row">${livro.isbn}</th>
                            <th scope="row">${livro.status}</th>
                            <c:if test="${not empty livro.categoriaInativacao}">
                                <th scope="row">${livro.categoriaInativacao.descricao}</th>
                            </c:if>
                            <c:if test="${empty livro.categoriaInativacao}">
                                <th scope="row">-</th>
                            </c:if>
                            <th scope="row">${livro.codBarras}</th>
                            <th scope="row"><a href="/ellentex-livrariales/admin/Livro?operacao=PERFIL_LIVRO&idLivro=${livro.id}">Detalhes</a>

                                <c:if test="${livro.status eq true}">
                            <th scope="row"><a href="/ellentex-livrariales/admin/Livro?operacao=ATIVAR_LIVRO&idLivro=${livro.id}">
                                <button class="btn btn-success btn-sm" disabled><i class="bi bi-unlock"></i>ativar</button></a>
                                <a href="/ellentex-livrariales/admin/Livro?operacao=DESATIVAR_LIVRO&idLivro=${livro.id}">
                                    <button class="btn btn-warning btn-sm"><i class="bi bi-lock"></i>bloquear</button></a></th>
                            </c:if>
                            <c:if test="${livro.status eq false}">
                                <th scope="row"><a href="/ellentex-livrariales/admin/Livro?operacao=ATIVAR_LIVRO&idLivro=${livro.id}">
                                    <button class="btn btn-success btn-sm"><i class="bi bi-unlock"></i>ativar</button></a>
                                    <a href="/ellentex-livrariales/admin/Livro?operacao=DESATIVAR_LIVRO&idLivro=${livro.id}">
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
</div>




</body>
</html>
