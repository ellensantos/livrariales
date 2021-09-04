<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 03/03/2021
  Time: 00:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Editar Cliente</title>
    <jsp:include page="../navCliente.jsp"></jsp:include>
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

                <div class="d-flex flex-column">
                    <form action="Cliente" method="post">
                        <div class="card" id = "card-cadastro">
                            <div class="card-body" id = "card-cadastro">
                                <label for="dadosPessoais" id ="titulo"><b>DADOS PESSOAIS:</b></label>
                                <div class="card" id = "card-cadastro">
                                    <div class="card-body" id = "card-cadastro">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="nomeCliente" id = "label-titulos">Nome Completo</label>
                                                <input type= "text" class="form-control" name="nomeCliente" value= "${usuarioLogado.usuario.cliente.nome}" required>
                                            </div>

                                            <div class="col">
                                                <label for="dataNascimento" id= "label-titulos">Data de Nascimento</label>
                                                <fmt:formatDate value="${usuarioLogado.usuario.cliente.dtNascimento}" pattern="yyyy-MM-dd" var = "dataFormatada"/>
                                                <input type="date" value= "${dataFormatada}" class="form-control" name="dataNascimento" onkeydown="return false" required>
                                            </div>


                                            <div class="col">
                                                <label for="generoCliente" id= "label-titulos">Gênero</label>
                                                <select class="form-control" name="generoCliente" required>
                                                    <option value ="${usuarioLogado.usuario.cliente.genero}" selected hidden = "true">${usuarioLogado.usuario.cliente.genero}</option>
                                                    <option>Feminino</option>
                                                    <option>Masculino</option>
                                                    <option>Outro</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group col-md-5">
                                                <label for="cpfCliente" id= "label-titulos">CPF</label>
                                                <input type="text" value="${usuarioLogado.usuario.cliente.cpf}" class="form-control" name="cpfCliente" pattern="[0-9]+$" maxlength="11" oninvalid = "setCustomValidity('Apenas números')" onchange="setCustomValidity('')" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br/>

                                <!-- Contato -->
                                <hr class="mb-4">
                                <label for="contato"><b>CONTATO:</b></label>
                                <div class="card" id = "card-cadastro">
                                    <div class="card-body" id = "card-cadastro">
                                        <div class="form-row">
                                            <div class="form-group col-md-3">
                                                <label for="tipoTelefone" id= "label-titulos">Tipo de Telefone</label>
                                                <select class="form-control" name="tipoTelefone" required>
                                                    <option value="${usuarioLogado.usuario.cliente.telefone.tipoTelefone}" selected hidden = "true">${usuarioLogado.usuario.cliente.telefone.tipoTelefone}</option>
                                                    <option>Celular</option>
                                                    <option>Residencial</option>
                                                    <option>Comercial</option>
                                                </select>
                                            </div>

                                            <div class="col-md-2">
                                                <label for="dddTelefone" id= "label-titulos">DDD</label>
                                                <input type="text" class="form-control" name="dddTelefone" maxlength="2" pattern="[0-9]+$" value="${usuarioLogado.usuario.cliente.telefone.ddd}" required>
                                            </div>

                                            <div class="col-md-4">
                                                <label for="numTelefone" id= "label-titulos">Número de Telefone</label>
                                                <input type="text" class="form-control" name="numTelefone" maxlength="9" pattern="[0-9]+$" value="${usuarioLogado.usuario.cliente.telefone.telefone}" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br/>

                                <div align="right">
                                    <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="SALVAR_DADOS_PESSOAIS">SALVAR</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src ="js/endereco.js"></script>
</html>
