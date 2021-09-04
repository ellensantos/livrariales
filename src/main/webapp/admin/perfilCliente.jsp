<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <jsp:include page="navAdmin.jsp"></jsp:include>


</head>
<body>
<div class="container mt-4">
    <jsp:include page="barraLateralAdmin.jsp"></jsp:include>
    <!-- -->
    <div class="col-lg-9 my-lg-0 my-2">
        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">PERFIL CLIENTE</div>
            </div>

            <div class="d-flex flex-column">

                <div class="card" id = "card-cadastro">
                    <div class="card-body" id = "card-cadastro">
                        <label for="dadosPessoais"><b>DADOS PESSOAIS:</b></label>
                        <div class="card" id = "card-cadastro">
                            <div class="card-body" id = "card-cadastro">
                                <div class="form-row">
                                    <div class="form-group col-md-5">
                                        <label for="nomeCliente" id = "label-titulos">Nome Completo</label>
                                        <input type= "text"	class="form-control" name="nomeCliente" value="${clientePerfil[0].nome}" disabled>
                                    </div>

                                    <div class="col-md-3">
                                        <label for="dataNascimento" id= "label-titulos">Data de Nascimento</label>
                                        <fmt:formatDate value="${clientePerfil[0].dtNascimento}" pattern="yyyy-MM-dd" var = "dataFormatada"/>
                                        <input type="date" value="${dataFormatada}" class="form-control" name="dataNascimento" disabled>

                                    </div>

                                    <div class="col">
                                        <label for="generoCliente" id= "label-titulos">Gênero</label>
                                        <select class="form-control" name="generoCliente" disabled>
                                            <option>${clientePerfil[0].genero}</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="form-group col-md-5">
                                        <label for="cpfCliente" id= "label-titulos">CPF</label>
                                        <input type="text" class="form-control" name="cpfCliente" value="${clientePerfil[0].cpf}" disabled>
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
                                        <select class="form-control" name="tipoTelefone" disabled>
                                            <option>${clientePerfil[0].telefone.tipoTelefone}</option>
                                        </select>
                                    </div>

                                    <div class="col-md-2">
                                        <label for="dddTelefone" id= "label-titulos">DDD</label>
                                        <input type="text" class="form-control" name="dddTelefone" value="${clientePerfil[0].telefone.ddd}" disabled>
                                    </div>

                                    <div class="col-md-4">
                                        <label for="numTelefone" id= "label-titulos">Número de Telefone</label>
                                        <input type="text" class="form-control" name="numTelefone" value="${clientePerfil[0].telefone.telefone}" disabled>
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
                                        <input type="email" class="form-control" name="email" value="${clientePerfil[0].usuario.email}" disabled>
                                    </div>


                                    <div class="col-md-5">
                                        <label for="senha" id= "label-titulos">Senha</label>
                                        <input type="password" class="form-control"value="${clientePerfil[0].usuario.senha}" disabled>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br/>

                        <!-- Endereços -->

                        <input type="text" name="qtdeEndereco" id="qtdeEndereco" value="${clientePerfil[0].endereco.size()}" hidden="true">

                        <c:forEach var = "enderecos" items="${clientePerfil[0].endereco}" varStatus="contador">
                            <div id="novoEndereco${contador.count}">
                                <label for="endereco${contador.count}"><b>ENDEREÇO ${contador.count}:</b></label>
                                <div class="card" id = "card-cadastro">
                                    <div class="card-body" id="card-cadastro">

                                        <%--Position 1 = ${(enderecos.tipoEndereco[0].id)}
                                        Position 2 = ${(enderecos.tipoEndereco[1].id)}--%>

                                        <br>

                                        <label><b>Tipo de Endereço: &#160;&#160;</b></label>
                                        <div class="form-check-inline">
                                            <label class="form-check-label" for="entrega${contador.count}" id="label-titulos">

                                                <c:choose>
                                                    <c:when test="${enderecos.tipoEndereco[0].id eq 1}">
                                                        <input type="checkbox" class="form-check-input" id="entrega${contador.count}" name="entrega${contador.count}" value="1" checked="true" disabled>Entrega
                                                    </c:when>

                                                    <c:when test="${enderecos.tipoEndereco[1].id eq 1}">
                                                        <input type="checkbox" class="form-check-input" id="entrega${contador.count}" name="entrega${contador.count}" value="1" checked="true" disabled>Entrega
                                                    </c:when>


                                                    <c:otherwise>
                                                        <input type="checkbox" class="form-check-input" id="entrega${contador.count}" name="entrega${contador.count}" value="1" disabled>Entrega
                                                    </c:otherwise>
                                                </c:choose>


                                            </label>
                                        </div>
                                        <div class="form-check-inline">
                                            <label class="form-check-label" for="cobranca${contador.count}" id= "label-titulos">


                                                <c:choose>

                                                    <c:when test="${enderecos.tipoEndereco[0].id eq 2}">
                                                        <input type="checkbox" class="form-check-input" id="cobranca${contador.count}" name="cobranca${contador.count}" value="2" checked="true" disabled>Cobrança
                                                    </c:when>

                                                    <c:when test="${enderecos.tipoEndereco[1].id eq 2}">
                                                        <input type="checkbox" class="form-check-input" id="cobranca${contador.count}" name="cobranca${contador.count}" value="2" checked="true" disabled>Cobrança
                                                    </c:when>


                                                    <c:otherwise>
                                                        <input type="checkbox" class="form-check-input" id="cobranca${contador.count}" name="cobranca${contador.count}" value="2" disabled>Cobrança
                                                    </c:otherwise>
                                                </c:choose>
                                            </label>

                                        </div>
                                        <hr class="mb-1">
                                        <br/>

                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label for="descricaoEnd${contador.count}" id= "label-titulos">Descrição do Endereço (Apelido)</label>
                                                <input type="text" class="form-control" value="${enderecos.descricao}" id="descricaoEnd${contador.count}" name="descricaoEnd${contador.count}" disabled>
                                            </div>
                                            <div class="col-md-3">
                                                <label for="tipoResidencia${contador.count}" id= "label-titulos">Tipo de Residência</label>
                                                <select class="form-control" id="tipoResidencia${contador.count}" name="tipoResidencia${contador.count}" disabled>
                                                    <option>${enderecos.tipoResidencia}</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-row">
                                            <div class="col-md-3">
                                                <label for="tipoLogradouro${contador.count}" id= "label-titulos">Tipo Logradouro</label>
                                                <select class="form-control" id="tipoLogradouro${contador.count}" name="tipoLogradouro${contador.count}" disabled>
                                                    <option >${enderecos.tipoLogradouro}</option>
                                                </select>
                                            </div>

                                            <div class="col-md-4">
                                                <label for="logradouro${contador.count}" id= "label-titulos">Logradouro</label>
                                                <input type="text" class="form-control" value="${enderecos.logradouro}" id="logradouro${contador.count}" name="logradouro${contador.count}" disabled>
                                            </div>

                                            <div class="col-md-2">
                                                <label for="numero${contador.count}" id= "label-titulos">Número</label>
                                                <input type="text" class="form-control" value="${enderecos.numero}" id="numero${contador.count}" name="numero${contador.count}" min = "0" disabled>
                                            </div>
                                        </div>

                                        <br/>

                                        <div class="form-row">
                                            <div class="col-md-4">
                                                <label for="bairro${contador.count}" id= "label-titulos">Bairro</label>
                                                <input type="text" class="form-control" value="${enderecos.bairro}" id="bairro${contador.count}" name="bairro${contador.count}" disabled>
                                            </div>

                                            <div class="col-md-3">
                                                <label for="cep${contador.count}" id="label-titulos">CEP</label>
                                                <input type="text" class="form-control" value="${enderecos.cep}" id="cep${contador.count}" name="cep${contador.count}" disabled>
                                            </div>
                                        </div>

                                        <br/>
                                        <div class="form-row">
                                            <div class="col-md-3">
                                                <label for="pais${contador.count}" id= "label-titulos">País</label>
                                                <select class="form-control" id="pais${contador.count}" name="pais${contador.count}" disabled>
                                                    <option>Brasil</option>
                                                </select>
                                            </div>

                                            <div class="col-md-3">
                                                <label for="estado${contador.count}" id= "label-titulos">Estado</label>
                                                <select class="form-control" id="estado${contador.count}" name="estado${contador.count}" disabled>
                                                    <option>${enderecos.cidade.estado.descricao}</option>
                                                </select>
                                            </div>

                                            <div class="col-md-3">
                                                <label for="cidade${contador.count}" id= "label-titulos">Cidade</label>
                                                <select class="form-control" id="cidade${contador.count}" name="cidade${contador.count}" disabled>
                                                    <option>${enderecos.cidade.descricao}</option>
                                                </select>
                                            </div>

                                        </div>
                                        <br/>
                                        <label for="observacao${contador.count}" id= "label-titulos">Observação</label>
                                        <textarea class="form-control" rows="3"   id="observacao${contador.count}" name="observacao${contador.count}" disabled>${enderecos.observacao}</textarea>
                                    </div>
                                </div> <!-- Card Bory Endereços 1 -->
                                <br/>
                            </div>
                        </c:forEach>
                        <br/>


                        <input type="text" name="qtdeCartao" id="qtdeCartao" value="${clientePerfil[0].cartao.size()}" hidden="false">

                        <c:if test= "${ empty clientePerfil[0].cartao}">
                            <label id= "label-titulos"><b>*NENHUM CARTÃO CADASTRADO*</b></label><br>
                        </c:if>

                        <c:forEach var = "cartoes" items="${clientePerfil[0].cartao}" varStatus="contador">
                            <label for="cartao${contador.count}" id= "label-titulos"><b>CARTAO ${contador.count}:</b></label>
                            <div class="card" id = "card-cadastro">
                                <div class="card-body" id = "card-cadastro">
                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label for="numeroCartao${contador.count}" id= "label-titulos">Numero do Cartao</label>
                                            <input type="text" class="form-control" name="numeroCartao${contador.count}" id="numeroCartao${contador.count}" value="${cartoes.numero}" disabled>
                                        </div>


                                        <div class="col-md-6">
                                            <label for="nomeCartao${contador.count}" id= "label-titulos">Nome Impresso no Cartao</label>
                                            <input type="text" class="form-control" name="nomeCartao${contador.count}" id="nomeCartao${contador.count}" value="${cartoes.nomeImpresso}" disabled>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="col-md-4">
                                            <label for="bandeiraCartao${contador.count}" id= "label-titulos">Bandeira do Cartao</label>
                                            <select class="form-control" id="bandeiraCartao${contador.count}" name="bandeiraCartao${contador.count}" disabled>
                                                <option>${cartoes.bandeiraCartao.descricao}</option>

                                            </select>

                                        </div>

                                        <div class="col-md-2">
                                            <label for="cvv${contador.count}" id= "label-titulos">CVV</label>
                                            <input type="text" class="form-control" name="cvv${contador.count}" id="cvv${contador.count}" minlength="3" maxlength="3" value="${cartoes.cvv}" disabled>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <br>
                        </c:forEach>

                        <c:if test= "${empty clientePerfil[0].pedido}">
                            <label id= "label-titulos"><b>*NENHUM PEDIDO REALIZADO*</b></label><br>
                        </c:if>

                        <c:if test= "${not empty clientePerfil[0].pedido}">
                            <table class="table table-bordered table-striped" style="font-size: 13px">
                                <thead style="color: #912F41; font-weight: bolder">
                                <tr>
                                    <th scope="col">PEDIDO</th>
                                    <th scope="col">DATA</th>
                                    <th scope="col">QTDE PRODUTO</th>
                                    <th scope="col">STATUS</th>
                                    <th scope="col">TIPO PEDIDO</th>
                                    <th scope="col">TOTAL</th>

                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="pedido" items="${clientePerfil[0].pedido}" varStatus="cont">
                                    <tr>
                                        <th scope="row"><a href="/ellentex-livrariales/Pedido?operacao=PEDIDO_DETALHES_CLIENTE&idPedido=${pedido.id}">#${pedido.id}</a></th>
                                        <fmt:formatDate value="${pedido.data}" pattern="dd-MM-yyyy" var = "dataFormatada"/>
                                        <td>${dataFormatada}</td>

                                        <c:if test="${pedido['class'].simpleName eq 'PedidoCompra'}">
                                            <td>${pedido.qtdeItens}</td>
                                        </c:if>

                                        <c:if test="${pedido['class'].simpleName eq 'PedidoTroca'}">
                                            <td>-</td>
                                        </c:if>

                                        <td>${pedido.status}</td>
                                        <td>
                                            <c:if test="${pedido['class'].simpleName eq 'PedidoCompra'}">
                                                Pedido de Compra
                                            </c:if>
                                            <c:if test="${pedido['class'].simpleName eq 'PedidoTroca'}">
                                                Pedido de Troca
                                            </c:if>

                                        </td>
                                        <td><fmt:formatNumber value="${pedido.valorTotal}" type="currency" /></td>
                                    </tr>

                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>


                    </div>
                </div> <!-- Card Bory Principal -->

                <br>

                <div align="right">
                    <a href="Cliente?operacao=LISTAR_CLIENTES"><button class="btn btn-success">VOLTAR</button></a>
                </div>


            </div>
        </div>  <!-- -->
    </div>  <!-- -->
</div>




</body>

</html>