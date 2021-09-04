<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 03/04/2021
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Checkout</title>
    <%--<meta http-equiv="refresh" content="1">--%>
    <jsp:include page="navCheckout.jsp"></jsp:include>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/estiloHome.css">

    <script type="text/javascript" src="js/endereco.js"></script>
    <script type="text/javascript" src="js/cartao.js"></script>
    <script type="text/javascript" src="js/cupom.js"></script>
    <script type="text/javascript" src="js/moeda.js"></script>
    <script type="text/javascript" src="js/checkout.js"></script>
    <script type="text/javascript" src="js/frete.js"></script>
    <script type="text/javascript" src="js/sessao.js"></script>
</head>
<body>

<%--<c:if test="${empty carrinhoCompra.itens}">
    <jsp:forward page="carrinhoCompra.jsp"></jsp:forward>
</c:if>--%>

<form action="Pedido" method="post" id="formPedido">
    <section id="sidebarCheckOutItens">
        <div class="border-bottom pb-2 ml-2">
            <h4><i class="bi bi-card-checklist"></i> Itens</h4>
        </div>
        <div class="py-2 border-bottom ml-3">
            <table class="table table table-light" id="tabelaCheckout">
                <thead class="thead-light">
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Item</th>
                    <th scope="col">Preço</th>
                    <th scope="col">Qtde</th>
                    <th scope="col">Total</th>
                </tr>
                <tbody>
                <c:forEach var="item" items="${carrinhoCompra.itens}" varStatus="cont">
                    <tr>
                        <th scope="row"><img class="rounded mx-auto d-block" src="images/${item.produto.foto}" width="43px" height="65px"></th>
                        <th scope="row">${item.produto.titulo}</th>
                        <th scope="row"><fmt:formatNumber value="${item.produto.itemEstoque.valorVenda}" type="currency" /></th>
                        <th scope="row">${item.qtde}</th>
                        <th scope="row"><fmt:formatNumber value="${item.preco}" type="currency" /></th>
                    </tr>
                </c:forEach>
                </tbody>

                <tfoot>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row">Total</th>
                <th scope="row" id="totalPagar" style="color: #912F41"></th>
                <th scope="row"></th>
                </tfoot>

                <tfoot>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row">Subtotal</th>
                <th scope="row" style="color: #912F41"><fmt:formatNumber value="${carrinhoCompra.valorTotal}" type="currency" /></th>
                <th scope="row"></th>
                </tfoot>

                <tfoot>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row">Frete</th>
                <th scope="row" id="valorFreteExib" style="color: #912F41"></th>
                <th scope="row"></th>
                </tfoot>

                <tfoot>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row"></th>
                <th scope="row">Desconto</th>
                <th scope="row" id="valorDescontoExib" style="color: #912F41"></th>
                <th scope="row"></th>
                </tfoot>
                </thead>
            </table>
        </div>
        <br>
        <input type="text" id="valorTotalCarrinho" value="${carrinhoCompra.valorTotal}" hidden="true">
        <input type="text" id="valorTotalCompra" value="${carrinhoCompra.valorTotal}" hidden="true">
        <input type="text" id="valorFrete" value="0" hidden="true">

        <%-- CUPOM DESCONTO --%>
        <div class="col-lg-9 my-lg-0 my-2">
            <label for="cupomDesconto">Cupom Desconto</label>
            <div class="form-check-inline">
                <input type="text" class="form-control" value="" id="cupomDesconto" name="cupomDesconto">
                <div id="respErroCupomDesconto" style="display: none; color: red"> <i class="bi bi-x"></i></div>
                <div id="respSucessoCupomDesconto" style="display: none; color: green"> <i class="bi bi-check2"></i></div>
                <div class="col-lg-5 my-lg-0 my-2">
                <button type="button" class="btn btn-primary " id="validarCupomDesconto" >Validar Cupom</button>
                </div>
            </div>
        </div>

        <div class="col-lg-3 my-lg-0 my-2" id="cupomDescontoDetalhes" style="display: none">
            <label for="cupomDesconto">Desconto R$</label>
            <div class="form-check-inline">
                <input type="text" class="form-control" value="0" id="valorCupomDesconto" name="valorCupomDesconto" onchange="soma()" readonly>
            </div>
        </div>

    <%-- CUPOM DESCONTO --%>
        <br><br><br><br><br><br><br><br><br>

        <div align="right">
            <a href="carrinhoCompra.jsp" type="button" class="btn btn-warning"> <i class="bi bi-back"></i> Voltar</a>
            <button type="submit" id="operacao" class="btn btn-success" name="operacao"  value="SALVAR">Confirmar Pedido <i class="bi bi-bag-check"></i></button>
        </div>
    </section>

    <!-- ENDEREÇO COBRANÇA -->
    <section id="sidebarCheckOut">
        <div class="border-bottom pb-2 ml-2">
            <h4><i class="bi bi-upc"></i> Endereço de Cobrança</h4>
        </div>
        <br>
        <div id="selecEndCobrancaCad" style="background-color: #F0ECEC;">
            <div class="form-row">
                <div class="col-lg-8 my-lg-0 my-2"><br>
                    <label for="idEndCobrancaSelecionado" id="labelSelectEnd">Endereços cadastrados</label>
                </div>
                <div class="col-lg-8 my-lg-0 my-2">
                    <select class="form-control" id="idEndCobrancaSelecionado" name="idEndCobrancaSelecionado" required>
                        <option selected data-default value="">Selecione ...</option>
                        <c:forEach var="end" items="${usuarioLogado.usuario.cliente.endereco}" varStatus="cont">
                            <option value="${end.id}">${end.descricao}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-inline"><button class="btn-sm btn-primary" id="AddEndCobranca" type="button" onclick="addEndCheckOut(this)">Novo Endereço</button></div>
                <br>
            </div>
        </div>

        <!-- NOVO ENDEREÇO COBRANÇA -->
        <div class="form-row">
            <div class="col-lg-12 my-lg-0 my-2">
                <div id="novoEnderecoCobranca"></div>
            </div>
        </div>
    </section>
    <!-- ENDEREÇO COBRANÇA -->

    <!-- ENDEREÇO ENTREGA -->
    <section id="sidebarCheckOut">
        <div class="border-bottom pb-2 ml-2">
            <h4><i class="bi bi-geo-alt"></i> Endereço de Entrega</h4>
        </div>
        <br>
        <div id="selecEndEntregaCad" style="background-color: #F0ECEC;">
            <div class="form-row">
                <div class="col-lg-8 my-lg-0 my-2"><br>
                    <label>Endereços cadastrados</label>
                </div>
                <div class="col-lg-8 my-lg-0 my-2">
                    <select class="form-control" id="idEndEntregaSelecionado" name="idEndEntregaSelecionado" onchange="calcularFrete()" required>
                        <option selected data-default value="">Selecione ...</option>
                        <c:forEach var="end" items="${usuarioLogado.usuario.cliente.endereco}" varStatus="cont">
                            <option value="${end.id}">${end.descricao}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-inline"><button class="btn-sm btn-primary" id="AddEndEntrega" type="button" onclick="addEndCheckOut(this)">Novo Endereço</button></div>
                <br>
            </div>
        </div>

        <!-- NOVO ENDEREÇO ENTREGA -->
        <div class="form-row">
            <div class="col-lg-12 my-lg-0 my-2">
                <div id="novoEnderecoEntrega"></div>
            </div>
        </div>
    </section>
    <!-- ENDEREÇO ENTREGA -->


    <!-- BANDEIRA CARTÃO -->
    <input type="text" name="qtdeBandeiraCartao" id="qtdeBandeiraCartao" value="${bandeiraCartao.size()}" hidden="true">
    <c:forEach var="bandeiraCartao" items="${bandeiraCartao}" varStatus="c">
        <input type="text" class="form-control" name="idBandCartao${c.count}"
               id="idBandCartao${c.count}"
               value="${bandeiraCartao.id}" hidden="true">
        <input type="text" class="form-control" name="bandCartao${c.count}" id="bandCartao${c.count}"
               value="${bandeiraCartao.descricao}" hidden="true">
    </c:forEach>
    <!-- BANDEIRA CARTÃO -->

    <input id="valorPago" value="0" hidden="true">
    <input id="valorPendente" value="${carrinhoPedido.valorTotal}" hidden="true">
    <input type="text" id="qtdeCupomSelecionado" name="qtdeCupomSelecionado" value="0" hidden="true">
    <input type="text" id="valorTotalPagCupom" name="valorTotalPagCupom" value="0" hidden="true">
    <input type="text" id="qtdeCupomTroca" name="qtdeCupomTroca" value="${usuarioLogado.usuario.cliente.cupom.size()}" hidden="true">

    <!-- CUPOM DE TROCA -->
    <c:if test="${not empty usuarioLogado.usuario.cliente.cupom}">
        <section id="sidebarCheckOut">
            <div class="border-bottom pb-2 ml-2">
                <h4><i class="bi bi-credit-card"></i></i> Cupom de Troca</h4>
            </div>
            <br>
            <div id="selecaoCupom1" style="background-color: #F0ECEC;">
                <div class="form-row">

                    <div class="col-lg-8 my-lg-0 my-2"><br>
                        <label id="labelSelectCupom">Cupons de Troca Disponíveis</label>
                        <br>
                    </div>
                    <div class="col-lg-5 my-lg-0 my-2">
                        <c:forEach var="cupom" items="${usuarioLogado.usuario.cliente.cupom}" varStatus="i">
                            <c:if test="${cupom.status eq true}">
                                <div class="form-check-inline">
                                    <label class="form-check-label" for="cupomTroca${i.count}">
                                        <input type="checkbox" class="form-check-input" id="cupomTroca${i.count}" name="cupomTroca${i.count}" onchange="validarCheckCupom(this)" value=${cupom.id}>R$ ${cupom.valor}
                                        <input type="text" id="valorCupomTroca${i.count}" name="valorCupomTroca${i.count}" value=${cupom.valor} hidden="true">
                                        <input type="text" id="idCupomTroca${i.count}" name="idCupomTroca${i.count}" value=${cupom.id} hidden="true">
                                    </label>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                    <br>
                </div>
            <br>
            <div align="center">
                <button class="btn btn-secondary" type="button" id="desfazerCupomTroca" onclick="desbloquearCuponsTroca()"><i class="bi bi-arrow-clockwise"></i> desfazer seleção</button>
            </div>
            </div>
        </section>
    </c:if>
    <!-- CUPOM DE TROCA -->


    <!-- CUPOM DE TROCA -->
    <c:if test="${empty usuarioLogado.usuario.cliente.cupom}">
        <section id="sidebarCheckOut">
            <div class="border-bottom pb-2 ml-2">
                <h4><i class="bi bi-credit-card"></i></i> Cupom de Troca</h4>
            </div>
            <br>
            <div id="selecaoCupom1" style="background-color: #F0ECEC;">
                <div class="form-row">
                    <div class="col-lg-8 my-lg-0 my-2"><br>
                        <label id="labelSelectCupom">Nenhum Cupom de Troca Disponível</label>
                        <br>
                    </div>
                </div>
            </div>
        </section>
    </c:if>
    <!-- CUPOM DE TROCA -->



    <!-- CARTÃO -->
    <section id="sidebarCheckOut">
        <div class="border-bottom pb-2 ml-2">
            <h4><i class="bi bi-credit-card"></i></i> Forma de Pagamento</h4>
        </div>
        <br>
        <input type="text" id="qtdeCartaoPag" name="qtdeCartaoPag" value="1" hidden="true">

        <c:if test="${not empty usuarioLogado.usuario.cliente.cartao}">
            <input type="text" id="qtdeCartao" name="qtdeCartao" value="${usuarioLogado.usuario.cliente.cartao.size()}" hidden="true">

            <c:forEach var="cartao" items="${usuarioLogado.usuario.cliente.cartao}" varStatus="cont">
                <input type="text" id="idCartaoCliente${cont.count}" value="${cartao.id}" hidden="true">
                <input type="text" id="numCartaoCliente${cont.count}" value="${cartao.numero}" hidden="true">
            </c:forEach>

            <input type="text" id="qtdeCartaoPagCad" name="qtdeCartaoPagCad" value="1" hidden="true">
            <div id="novoCartaoCadastrado1">
                <div id="selecaoCartao1" style="background-color: #F0ECEC;">
                    <div class="form-row">
                        <div class="col-lg-8 my-lg-0 my-2"><br>
                            <label id="labelSelectCartao1">Cartões cadastrados</label>
                        </div>
                        <div class="col-lg-5 my-lg-0 my-2">
                            <select class="form-control" id="idCartaoSelecionado1" name="idCartaoSelecionado1" onchange="validarPrimeiroValorSelectCad()">
                                <option selected data-default value="">Selecione ...</option>
                                <c:forEach var="cartao" items="${usuarioLogado.usuario.cliente.cartao}" varStatus="cont">
                                    <option value="${cartao.id}">${cartao.numero}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-inline">
                            <label for="valorPag1">Valor R$ </label>
                            <div class="col-lg-1 my-lg-0 my-2">
                                <input type="text" class="form-control" id="valorPag1" name="valorPag1" onkeyup="mascaraMoeda(this)" onchange="validarValorCartao(this)" disabled>
                            </div>
                        </div>

                        <div class="col-lg-12 my-lg-0 my-2" id="msgValorPendente1">
                            <script>msgValorPendente("msgValorPendente1");</script>
                        </div>
                        <!-- O primeiro cartão cadastrado não é obrigatório preencher, se tiver em branco o valor será desconsiderado. -->
                        <br>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${empty usuarioLogado.usuario.cliente.cartao}">
            <input type="text" id="qtdeCartaoPagCad" name="qtdeCartaoPagCad" value="0" hidden="true">
            <input type="text" id="qtdeCartao" name="qtdeCartao" value="0" hidden="true">
            <div id="novoCartao1">
                <div class="card" id="cardCartaoCheckOut">
                    <div class="card-body" id="cardCartaoCheckOut">
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <input type="text" name="idCartao1" id="idCartao1" value="0" hidden="true">

                                <label for="numeroCartao1">Numero do Cartao</label>
                                <input type="text" class="form-control" name="numeroCartao1" id="numeroCartao1" pattern="[0-9]+$" minlength="16" maxlength="16" required>
                            </div>

                            <div class="col-md-5">
                                <label for="nomeCartao1">Nome Impresso no Cartao</label>
                                <input type="text" class="form-control" name="nomeCartao1" id="nomeCartao1" required>
                            </div>

                            <div class="col-md-4">
                                <label for="bandeiraCartao1">Bandeira do Cartao</label>
                                <select class="form-control" id="bandeiraCartao1" name="bandeiraCartao1" required>
                                </select>
                            </div>
                            <br/>

                            <div class="col-md-2">
                                <label for="cvv1">CVV</label>
                                <input type="text" class="form-control" name="cvv1" id="cvv1" minlength="3" maxlength="3" required>
                            </div>

                            <div class="col-md-2">
                                <label for="mesVencimento1">Mes</label>
                                <select class="form-control" id="mesVencimento1" name="mesVencimento1" required>
                                    <option selected data-default value=""></option>
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
                                <label for="anoVencimento1">Ano</label>
                                <select class="form-control" id="anoVencimento1" name="anoVencimento1" required>
                                    <option selected data-default value=""></option>
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
                        <br/>

                        <div class="row">
                            <div class="col-md-3">
                                <label for="valorPag1" style="color: red">Valor R$ </label>
                                <input type="text" class="form-control" id="valorPag1" name="valorPag1" onkeyup="mascaraMoeda(this)" onchange="validarValorCartao(this)" required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-8" id="msgValorPendente1">
                                <script>msgValorPendente("msgValorPendente1");</script>
                            </div>
                        </div>

                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <br/>
                                <input type="checkbox" class="form-check-input" id="salvarCartao1" name="salvarCartao1"> Salvar Cartao
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <script>popularSelectBandeiraCartao("bandeiraCartao1");</script>
            </div>
        </c:if>
        </div>

        <!-- NOVO CARTÃO CADASTRO -->
        <div id="novoCartaoCadastrado"></div>
        <!-- NOVO CARTÃO CADASTRO -->

        <!-- NOVO CARTÃO -->
        <div class="form-row">
            <div class="col-lg-12 my-lg-0 my-2">
                <div id="novoCartao"></div>
            </div>
        </div>
        <!-- NOVO CARTÃO -->
        <br>
        <div align="right"class="form-inline">
            <c:if test="${not empty usuarioLogado.usuario.cliente.cartao}">
                <button class="btn btn-primary" id="botaoAddCadastrado" type="button" onclick="addSelectCartaoCad()"><i class="bi bi-plus-circle"></i> Pagar com + um cartão cadastrado</button>
            </c:if>
            <button class="btn btn-success" name="botaoAddCartao" id="botaoAddCartao" type="button" onclick="addCartaoCheckOut()"><i class="bi bi-plus-circle"></i> Pagar com + um novo cartão</button>
        </div>
    </section>
    <!-- CARTÃO -->
</form>
</body>
</html>
