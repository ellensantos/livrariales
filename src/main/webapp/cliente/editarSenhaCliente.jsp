<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 02/03/2021
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Editar Senha</title>
    <jsp:include page="../navCliente.jsp"></jsp:include>
<%--    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/estilo.css"/>--%>
<%--    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/estilo.css" rel="stylesheet" type="text/css"/>--%>
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
                            <label for="contato" id= "label-titulos"><b>LOGIN:</b></label>
                            <div class="card" id = "card-cadastro">
                                <div class="card-body" id = "card-cadastro">
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="email" id= "label-titulos">E-mail</label>
                                            <input type="email" class="form-control" value="${usuarioLogado.usuario.email}" name="email" disabled="disabled" required>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-4">
                                            <label for="senha" id= "label-titulos">Nova Senha</label>
                                            <input type="password" class="form-control" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15})"
                                                   title="Mínimo 8 caracteres, letras maiúsculas e minúsculas, números e caracter especial" name="senha" id="senha" required>
                                        </div>

                                        <div class="col-md-4">
                                            <label for="confirmaSenha" id= "label-titulos">Confirmar Senha</label>
                                            <input type="password" class="form-control" onblur="validarSenha()" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15})"
                                                   title="Mínimo 8 caracteres, letras maiúsculas e minúsculas, números e caracter especial" name="confirmaSenha" id="confirmaSenha" required>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>

                    <div align="right">
                        <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="SALVAR_SENHA">SALVAR</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src ="js/validarSenha.js"></script>
</html>
