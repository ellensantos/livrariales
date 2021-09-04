<%--
  Created by IntelliJ IDEA.
  User: Your name
  Date: 28/02/2021
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Editar Endereço</title>
    <jsp:include page="../navCliente.jsp"></jsp:include>
    <script type="text/javascript" src ="js/endereco.js"></script>
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

${sessionScope.remove("msg")}
${requestScope.remove("msg")}

<div class="container mt-4">    <!-- -->
    <div class="row">
        <jsp:include page="barraLateralCli.jsp"></jsp:include>
        <div class="col-lg-9 my-lg-0 my-1">
            <div id="main-content" class="bg-white border">
                <div class="d-flex flex-column">
                    <form action="Cliente" method="post" onsubmit="return verificarCheckBox()">
                        <div class="card" id = "card-cadastro">
                            <div class="card-body" id = "card-cadastro">

                                <input type="text" name="qtdeEndereco" id="qtdeEndereco" value="${usuarioLogado.usuario.cliente.endereco.size()}" hidden="true">
                                <c:forEach var = "enderecos" items="${usuarioLogado.usuario.cliente.endereco}" varStatus="contador">
                                    <div id="novoEndereco${contador.count}">
                                        <label for="endereco${contador.count}"><b>ENDEREÇO ${contador.count}:</b></label>
                                        <div class="card" id = "card-cadastro">
                                            <div class="card-body" id="card-cadastro">

                                                <input type="text" name="idEndereco${contador.count}" id="idEndereco${contador.count}" value="${enderecos.id}" hidden="true">

                                                <label><b>Tipo de Endereço: &#160;&#160;</b></label>
                                                <div class="form-check-inline">
                                                    <label class="form-check-label" for="entrega${contador.count}" id="label-titulos">
                                                        <c:if test="${enderecos.tipoEndereco[0].id eq 1}">
                                                            <input type="checkbox" class="form-check-input" id="entrega${contador.count}" name="entrega${contador.count}" value="1" checked="true">Entrega
                                                        </c:if>

                                                        <c:if test="${enderecos.tipoEndereco[1].id eq 1}">
                                                            <input type="checkbox" class="form-check-input" id="entrega${contador.count}" name="entrega${contador.count}" value="1" checked="true">Entrega
                                                        </c:if>

                                                        <c:if test="${enderecos.tipoEndereco[0].id ne 1 and enderecos.tipoEndereco[1].id ne 1}">
                                                            <input type="checkbox" class="form-check-input" id="entrega${contador.count}" name="entrega${contador.count}" value="1">Entrega
                                                        </c:if>
                                                    </label>
                                                </div>
                                                <div class="form-check-inline">
                                                    <label class="form-check-label" for="cobranca${contador.count}" id= "label-titulos">
                                                        <c:if test="${enderecos.tipoEndereco[0].id eq 2}">
                                                            <input type="checkbox" class="form-check-input" id="cobranca${contador.count}" name="cobranca${contador.count}" value="2" checked="true">Cobrança
                                                        </c:if>

                                                        <c:if test="${enderecos.tipoEndereco[1].id eq 2}">
                                                            <input type="checkbox" class="form-check-input" id="cobranca${contador.count}" name="cobranca${contador.count}" value="2" checked="true">Cobrança
                                                        </c:if>

                                                        <c:if test="${enderecos.tipoEndereco[0].id ne 2 and enderecos.tipoEndereco[1].id ne 2}">
                                                            <input type="checkbox" class="form-check-input" id="cobranca${contador.count}" name="cobranca${contador.count}" value="2">Cobrança
                                                        </c:if>
                                                    </label>
                                                </div>
                                                <hr class="mb-1">
                                                <br/>

                                                <div class="form-row">
                                                    <div class="form-group col-md-6">
                                                        <label for="descricaoEnd${contador.count}" id= "label-titulos">Descrição do Endereço (Apelido)</label>
                                                        <input type="text" class="form-control" value="${enderecos.descricao}" id="descricaoEnd${contador.count}" name="descricaoEnd${contador.count}" required>
                                                    </div>
                                                    <div class="col-md-3">
                                                        <label for="tipoResidencia${contador.count}" id= "label-titulos">Tipo de Residência</label>
                                                        <select class="form-control" id="tipoResidencia${contador.count}" name="tipoResidencia${contador.count}" required>
                                                            <option value ="${enderecos.tipoResidencia}" selected hidden = "true">${enderecos.tipoResidencia}</option>
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
                                                        <label for="tipoLogradouro${contador.count}" id= "label-titulos">Tipo Logradouro</label>
                                                        <select class="form-control" id="tipoLogradouro${contador.count}" name="tipoLogradouro${contador.count}" required>
                                                            <option value="${enderecos.tipoLogradouro}" selected hidden = "true">${enderecos.tipoLogradouro}</option>
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
                                                            <option>Sitio</option>
                                                            <option>Travessa</option>
                                                            <option>Vale</option>
                                                            <option>Via</option>
                                                            <option>Viela</option>
                                                            <option>Vila</option>
                                                        </select>
                                                    </div>

                                                    <div class="col-md-4">
                                                        <label for="logradouro${contador.count}" id= "label-titulos">Logradouro</label>
                                                        <input type="text" class="form-control" value="${enderecos.logradouro}" id="logradouro${contador.count}" name="logradouro${contador.count}" required>
                                                    </div>

                                                    <div class="col-md-2">
                                                        <label for="numero${contador.count}" id= "label-titulos">Número</label>
                                                        <input type="text" class="form-control" value="${enderecos.numero}" id="numero${contador.count}" name="numero${contador.count}" min = "0" required>
                                                    </div>
                                                </div>

                                                <br/>

                                                <div class="form-row">
                                                    <div class="col-md-4">
                                                        <label for="bairro${contador.count}" id= "label-titulos">Bairro</label>
                                                        <input type="text" class="form-control" value="${enderecos.bairro}" id="bairro${contador.count}" name="bairro${contador.count}" required>
                                                    </div>

                                                    <div class="col-md-3">
                                                        <label for="cep${contador.count}" id="label-titulos">CEP</label>
                                                        <input type="text" class="form-control" value="${enderecos.cep}" id="cep${contador.count}" name="cep${contador.count}" pattern="[0-9]+$" maxlength="8" oninvalid = "setCustomValidity('Apenas números')" onchange="setCustomValidity('')" required>
                                                    </div>
                                                </div>

                                                <br/>
                                                <div class="form-row">
                                                    <div class="col-md-3">
                                                        <label for="pais${contador.count}" id= "label-titulos">País</label>
                                                        <select class="form-control" id="pais${contador.count}" name="pais${contador.count}" required>
                                                            <option selected data-default value="1">Brasil</option>
                                                        </select>
                                                    </div>

                                                    <div class="col-md-3">
                                                        <label for="estado${contador.count}" id= "label-titulos">Estado</label>
                                                        <select class="form-control" id="estado${contador.count}" name="estado${contador.count}" onclick="popularSelectEstado(this)" onchange="buscaCidades(this.value, this)" required>
                                                            <option selected data-default value="${enderecos.cidade.estado.id}" selected hidden = "true">${enderecos.cidade.estado.descricao}</option>
                                                        </select>
                                                    </div>

                                                    <div class="col-md-3">
                                                        <label for="cidade${contador.count}" id= "label-titulos">Cidade</label>
                                                        <select class="form-control" id="cidade${contador.count}" name="cidade${contador.count}" required>
                                                            <option selected data-default value="${enderecos.cidade.id}" selected hidden="true">${enderecos.cidade.descricao}</option>
                                                        </select>
                                                    </div>

                                                </div>
                                                <br/>
                                                <label for="observacao${contador.count}" id= "label-titulos">Observação</label>
                                                <textarea class="form-control" rows="3"   id="observacao${contador.count}" name="observacao${contador.count}">${enderecos.observacao}</textarea>

                                                <c:if test="${contador.count > 1}">
                                                    <br/>
                                                    <div align="center">
                                                        <button type="button" class="btn btn-danger" name = "remover${contador.count}" id = "remover${contador.count}" onclick="deletarEndereco(this)">Excluir Endereço ${contador.count} </button>
                                                    </div>
                                                    <script>popularSelectEstado("estado${contador.count}")</script>
                                                </c:if>

                                            </div> <!--Card Bory -->
                                        </div> <!-- Card  -->
                                    </div>
                                </c:forEach>

                                <div id="novoEndereco"></div> <!-- Novos Endereços -->

                                <br/>
                                <div align="right">
                                    <button type="button" class="btn btn-primary" onclick="adicionarEndereco()">Adicionar Endereço +</button>
                                </div>

                            </div>
                        </div> <!-- Card Bory Principal -->

                        <br>
                        <div align="right">
                            <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="SALVAR_ENDERECO">SALVAR</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<%--    Preencher Combobox dos Estados
    <script>popularSelectEstadoEdicao()</script>--%>

</div>
</body>
</html>
