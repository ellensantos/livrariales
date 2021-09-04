<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 14/03/2021
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pesquisar Cliente</title>

    <jsp:include page="navAdmin.jsp"></jsp:include>


</head>
<body>
<jsp:include page="barraLateralAdmin.jsp"></jsp:include>
<div class="container mt-4">

    <!-- -->
    <div class="col-lg-9 my-lg-0 my-2">
        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">PESQUISA DE CLIENTE</div>
            </div>

            <div class="d-flex flex-column">
                <form action="Cliente" method="post">
                    <div class="card" id = "card-cadastro">
                        <div class="card-body" id = "card-cadastro">

                            <label for="dadosPessoais"><b>DADOS PESSOAIS:</b></label>
                            <div class="card" id = "card-cadastro">
                                <div class="card-body" id = "card-cadastro">
                                    <div class="form-row">
                                        <div class="form-group col-md-5">
                                            <label for="nomeCliente" id = "label-titulos">Nome Completo</label>
                                            <input type= "text"	class="form-control" name="nomeCliente">
                                        </div>

                                        <div class="col">
                                            <label for="dataNascimento" id= "label-titulos">Data de Nascimento</label>
                                            <input type="date" class="form-control" name="dataNascimento" value = "AAAA-MM-DD">
                                        </div>

                                        <div class="col">
                                            <label for="generoCliente" id= "label-titulos">Gênero</label>
                                            <select class="form-control" name="generoCliente">
                                                <option selected data-default value=""></option>
                                                <option>Feminino</option>
                                                <option>Masculino</option>
                                                <option>Outro</option>
                                            </select>
                                        </div>

                                        <div class="col">
                                            <label for="cpfCliente" id= "label-titulos">CPF</label>
                                            <input type="text" class="form-control" name="cpfCliente">
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
                                            <select class="form-control" name="tipoTelefone">
                                                <option selected data-default value=""></option>
                                                <option>Celular</option>
                                                <option>Residencial</option>
                                                <option>Comercial</option>
                                            </select>
                                        </div>

                                        <div class="col-md-2">
                                            <label for="dddTelefone" id= "label-titulos">DDD</label>
                                            <input type="text" class="form-control" name="dddTelefone" maxlength="2" pattern="[0-9]+$" value = "0" oninvalid = "setCustomValidity('Valor mínimo é 0')" onchange="setCustomValidity('')">
                                        </div>

                                        <div class="col-md-4">
                                            <label for="numTelefone" id= "label-titulos">Número de Telefone</label>
                                            <input type="text" class="form-control" name="numTelefone" maxlength="9" pattern="[0-9]+$" oninvalid = "setCustomValidity('Apenas números')" onchange="setCustomValidity('')">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br/>

                            <!-- Email e Senha -->
                            <hr class="mb-4">
                            <label for="contato" id= "label-titulos"><b>LOGIN:</b></label>
                            <div class="card" id = "card-cadastro">
                                <div class="card-body" id = "card-cadastro">
                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="email" id= "label-titulos">E-mail</label>
                                            <input type="email" class="form-control" name="email">
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <br/>
                            <input type="text" style="display:none;" class="form-control" value="0" id="qtdeEndereco" name="qtdeEndereco">
                            <br/>


                        </div>
                    </div> <!-- Card Bory Principal -->

                    <br>

                    <div align="right">
                        <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="PESQUISAR_CLIENTE">PESQUISAR</button>
                    </div>

                </form>
            </div>
        </div>  <!-- -->
    </div>  <!-- -->
</div>




</body>



</html>
