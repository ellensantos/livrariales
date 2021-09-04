<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cad Cliente</title>
    <script type="text/javascript" src ="js/mensagens.js"></script>
    <jsp:include page="nav.jsp"></jsp:include>

</head>


<body onload='popularSelectEstado("estado1")'>

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
        <strong>FALHA!</strong> ${sessionScope.msg}
    </div>
    ${sessionScope.remove("msg")}
</c:if>

<div class="container mt-4">
    <!-- -->
    <div class="col-lg-9 my-lg-0 my-2">
        <div id="card-principal-cadastro" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">CADASTRO DE CLIENTE</div>
            </div>

            <div class="d-flex flex-column">
                <form action="CadCliente" method="post" onsubmit=" return verificarCheckBox()">
                    <div class="card" id = "card-cadastro">
                        <div class="card-body" id = "card-cadastro">
                            <label for="dadosPessoais"><b>DADOS PESSOAIS:</b></label>
                            <div class="card" id = "card-cadastro">
                                <div class="card-body" id = "card-cadastro">
                                    <div class="form-row">
                                        <div class="form-group col-md-5">
                                            <label for="nomeCliente" id = "label-titulos">Nome Completo</label>
                                            <input type= "text"	class="form-control" name="nomeCliente" required>
                                        </div>

                                        <div class="col">
                                            <label for="dataNascimento" id= "label-titulos">Data de Nascimento</label>
                                            <input type="date" class="form-control" name="dataNascimento" value = "AAAA-MM-DD" min="1900-01-01" max="2021-01-01" required>
                                        </div>

                                        <div class="col">
                                            <label for="generoCliente" id= "label-titulos">Gênero</label>
                                            <select class="form-control" name="generoCliente" required>
                                                <option selected data-default value=""></option>
                                                <option>Feminino</option>
                                                <option>Masculino</option>
                                                <option>Outro</option>
                                            </select>
                                        </div>

                                        <div class="col">
                                            <label for="cpfCliente" id= "label-titulos">CPF</label>
                                            <input type="text" class="form-control" name="cpfCliente" pattern="[0-9]+$" maxlength="11" onchange="setCustomValidity('')" required>
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
                                                <option selected data-default value=""></option>
                                                <option>Celular</option>
                                                <option>Residencial</option>
                                                <option>Comercial</option>
                                            </select>
                                        </div>

                                        <div class="col-md-2">
                                            <label for="dddTelefone" id= "label-titulos">DDD</label>
                                            <input type="text" class="form-control" name="dddTelefone" maxlength="2" pattern="[0-9]+$" oninvalid = "setCustomValidity('Apenas numeros')" onchange="setCustomValidity('')" required>
                                        </div>

                                        <div class="col-md-4">
                                            <label for="numTelefone" id= "label-titulos">Número de Telefone</label>
                                            <input type="text" class="form-control" name="numTelefone" maxlength="9" pattern="[0-9]+$" oninvalid = "setCustomValidity('Apenas numeros')" onchange="setCustomValidity('')" required>
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
                                            <input type="email" class="form-control" name="email" required>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-4">
                                            <label for="senha" id= "label-titulos">Senha</label>
                                            <input type="password" class="form-control" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15})"
                                                   title="Mínimo 8 caracteres, letras maiúsculas e minúsculas, números e um caracter especial!" name="senha" id="senha" required>
                                        </div>

                                        <div class="col-md-4">
                                            <label for="confirmaSenha" id= "label-titulos">Confirmar Senha</label>
                                            <input type="password" class="form-control" onblur="validarSenha()" pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15})"
                                                   title="Mínimo 8 caracteres, letras maiúsculas e minúsculas, números e caracter especial!" name="confirmaSenha" id="confirmaSenha" required>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br/>

                            <!-- Endereço -->
                            <hr class="mb-4">
                            <label for="endereco1"><b>ENDEREÇO 1:</b></label>
                            <div class="card" id = "card-cadastro">
                                <div class="card-body" id = "card-cadastro">

                                    <label><b>Tipo de Endereço: &#160;&#160;</b></label>
                                    <div class="form-check-inline">
                                        <label class="form-check-label" for="entrega1" id= "label-titulos">
                                            <input type="checkbox" class="form-check-input" id="entrega1" name="entrega1" value="1">Entrega
                                        </label>
                                    </div>
                                    <div class="form-check-inline">
                                        <label class="form-check-label" for="cobranca1" id= "label-titulos">
                                            <input type="checkbox" class="form-check-input" id="cobranca1" name="cobranca1" value="2">Cobrança
                                        </label>
                                    </div>
                                    <hr class="mb-1">
                                    <br/>

                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="descricaoEnd1" id= "label-titulos">Descrição do Endereço (Apelido)</label>
                                            <input type="text" class="form-control" id="descricaoEnd1" name="descricaoEnd1" required>
                                        </div>
                                        <div class="col-md-3">
                                            <label for="tipoResidencia1" id= "label-titulos">Tipo de Residência</label>
                                            <select class="form-control" id="tipoResidencia1" name="tipoResidencia1" required>
                                                <option selected data-default value=""></option>
                                                <option>Apartamento</option>
                                                <option>Bangalo</option>
                                                <option>Casa</option>
                                                <option>Edicula</option>
                                                <option>Flat</option>
                                                <option>Kitnet</option>
                                                <option>Loft</option>
                                                <option>Sobrado</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="col-md-3">
                                            <label for="tipoLogradouro1" id= "label-titulos">Tipo Logradouro</label>
                                            <select class="form-control" id="tipoLogradouro1" name="tipoLogradouro1" required>
                                                <option selected data-default value=""></option>
                                                <option>Aeroporto</option>
                                                <option>Alameda</option>
                                                <option>Avenida</option>
                                                <option>Condomínio</option>
                                                <option>Distrito</option>
                                                <option>Estrada</option>
                                                <option>Fazenda</option>
                                                <option>Feira</option>
                                                <option>Jardim</option>
                                                <option>Ladeira</option>
                                                <option>Loteamento</option>
                                                <option>Morro</option>
                                                <option>Parque</option>
                                                <option>Praça</option>
                                                <option>Quadra</option>
                                                <option>Recanto</option>
                                                <option>Residencial</option>
                                                <option>Rodovia</option>
                                                <option>Rua</option>
                                                <option>Sítio</option>
                                                <option>Travessa</option>
                                                <option>Vale</option>
                                                <option>Via</option>
                                                <option>Viela</option>
                                                <option>Vila</option>
                                            </select>
                                        </div>

                                        <div class="col-md-7">
                                            <label for="logradouro1" id= "label-titulos">Logradouro</label>
                                            <input type="text" class="form-control" id="logradouro1" name="logradouro1" required>
                                        </div>

                                        <div class="col-md-2">
                                            <label for="numero1" id= "label-titulos">Número</label>
                                            <input type="number" class="form-control" id="numero1" name="numero1" min = "0" required>
                                        </div>
                                    </div>
                                    <br/>

                                    <div class="form-row">
                                        <div class="col-md-4">
                                            <label for="bairro1" id= "label-titulos">Bairro</label>
                                            <input type="text" class="form-control" id="bairro1" name="bairro1" required>
                                        </div>

                                        <div class="col-md-3">
                                            <label for="cep1" id= "label-titulos">CEP</label>
                                            <input type="text" class="form-control" id="cep1" name="cep1" pattern="[0-9]+$" maxlength="8" oninvalid = "setCustomValidity('Apenas Números')" onchange="setCustomValidity('')" required>
                                        </div>
                                    </div>

                                    <br/>
                                    <div class="form-row">
                                        <div class="col-md-3">
                                            <label for="pais1" id= "label-titulos">País</label>
                                            <select class="form-control" id="pais1" name="pais1" required>
                                                <option selected data-default value="1">Brasil</option>
                                            </select>
                                        </div>

                                        <div class="col-md-3">
                                            <label for="estado1" id= "label-titulos">Estado</label>
                                            <select class="form-control" id="estado1" name="estado1" onchange="buscaCidades(this.value, this)" required>
                                                <option selected data-default value=""></option>
                                            </select>
                                        </div>

                                        <div class="col-md-3">
                                            <label for="cidade1" id= "label-titulos">Cidade</label>
                                            <select class="form-control" id="cidade1" name="cidade1" required>
                                                <option selected data-default value=""></option>
                                            </select>
                                        </div>

                                        <!-- Quantidade de Endereços -->
                                        <div class="col-md-2">
                                            <label for="qtdeEndereco" style="display:none;">Qtde Endereço</label>
                                            <input type="text" style="display:none;" class="form-control" value="1" id="qtdeEndereco" name="qtdeEndereco">
                                        </div>
                                    </div>		<!--style="display:none;" -->
                                    <br/>
                                    <label for="observacao1" id= "label-titulos">Observação</label>
                                    <textarea class="form-control" rows="3"   id="observacao1" name="observacao1"></textarea>
                                </div>
                            </div> <!-- Card Bory Endereço 1 -->

                            <div id="novoEndereco"></div> <!-- Novos Endereços -->

                            <br/>
                            <div align="right">
                                <button type="button" id="adicionarNovoEndereco" class="btn btn-primary" onclick='adicionarEndereco()'>Adicionar Endereço +</button>
                            </div>

                        </div>
                    </div> <!-- Card Bory Principal -->
                    <br>
                    <div align="right">
                        <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="SALVAR">CADASTRAR</button>
                    </div>
                </form>
            </div>
        </div>  <!-- -->
    </div>  <!-- -->
</div>
</body>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src ="js/endereco.js"></script>
<script type="text/javascript" src ="js/validarSenha.js"></script>

</html>