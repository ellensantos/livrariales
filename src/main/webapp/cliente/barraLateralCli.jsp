<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 03/03/2021
  Time: 00:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/estilo.css"/>--%>

</head>
<body>

<div class="col-lg-3 my-lg-0 my-md-1">
    <div id="sidebar" class="bg-purple">
        <div class="h4">Cliente <i class="bi bi-trophy"></i> ${usuarioLogado.usuario.cliente.ranking.categoria}</div>
        <ul>
            <li> <a href="${pageContext.request.contextPath}/Cliente?operacao=ALTERAR_DADOS_PESSOAIS" class="text-decoration-none d-flex align-items-start">
                <div class="fas fa-box pt-5 me-3"></div>
                <div class="d-flex flex-column">
                    <div class="link">Meus Dados</div>
                    <div class="link-desc">Atualizar dados pessoais e de contato</div>
                </div>
            </a> </li>
            <li> <a href="${pageContext.request.contextPath}/Cliente?operacao=ALTERAR_ENDERECO" class="text-decoration-none d-flex align-items-start">
                <div class="fas fa-box-open pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div class="link">Endereços</div>
                    <div class="link-desc">Atualizar e visualizar endereços</div>
                </div>
            </a> </li>
            <li> <a href="${pageContext.request.contextPath}/Cliente?operacao=ALTERAR_SENHA" class="text-decoration-none d-flex align-items-start">
                <div class="far fa-address-book pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div class="link">Dados de Logon</div>
                    <div class="link-desc">Alterar senha</div>
                </div>
            </a> </li>
            <li> <a href="${pageContext.request.contextPath}/Cliente?operacao=ALTERAR_CARTAO" class="text-decoration-none d-flex align-items-start">
                <div class="far fa-user pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div class="link">Formas de Pagamentos</div>
                    <div class="link-desc">Atualizar cartões cadastrados</div>
                </div>
            </a> </li>

            <li> <a href="${pageContext.request.contextPath}/Cliente?operacao=CONSULTAR_CUPONS" class="text-decoration-none d-flex align-items-start">
                <div class="far fa-user pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div class="link">Cupons de Troca</div>
                    <div class="link-desc">Consultar cupons de troca</div>
                </div>
            </a> </li>

            <li> <a id="consultarPedidos" href="${pageContext.request.contextPath}/Cliente?operacao=CONSULTAR_PEDIDOS" class="text-decoration-none d-flex align-items-start">
                <div class="fas fa-headset pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div class="link">Pedidos</div>
                    <div class="link-desc">Consultar pedidos</div>
                </div>
            </a> </li>
            <li> <a href="${pageContext.request.contextPath}/Cliente?operacao=CONSULTAR_MENSAGENS" class="text-decoration-none d-flex align-items-start">
                <div class="fas fa-headset pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div class="link">Mensagens</div>
                    <div class="link-desc">Consultar mensagens</div>
                </div>
            </a> </li>
            <li> <a id="desativarConta" href="${pageContext.request.contextPath}/Cliente?operacao=EXCLUIR" onclick="return confirm('Deseja mesmo desativar a conta?')" class="text-decoration-none d-flex align-items-start">
                <div class="fas fa-headset pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div class="link">Desativar Conta</div>
                    <div class="link-desc"></div>
                </div>
            </a> </li>
            <li> <a href="#" class="text-decoration-none d-flex align-items-start">
                <div class="fas fa-headset pt-2 me-3"></div>
                <div class="d-flex flex-column">
                    <div class="link">Ajuda e Suporte</div>
                    <div class="link-desc">Contato para ajuda e suporte</div>
                </div>
            </a> </li>

        </ul>
    </div>
</div>

</body>
</html>
