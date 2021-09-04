<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 15/03/2021
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Inserir Imagem</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#myModal").modal({
                backdrop: 'static',
                keyboard: false
            });
            $("#myModal").modal('show');
        });
    </script>
</head>
<body>
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Inserir imagem do produto</h5>
                <%--<a href="cadastrarLivro.jsp" data-dismiss="modal">Fechar</a>
                <a href="formUploadImagemProd.jsp">Inserir Imagem</a>
                    <button type="submit" class="close" data-dismiss="modal">&times;</button>--%>
                <form action="cadastrarLivro.jsp">
                    <a href=cadastrarLivro.jsp><button class="close">&times;</button></a>
                </form>

            </div>
            <div class="modal-body">
                <form action="EnviarArquivo" method="post" enctype="multipart/form-data">
                    <input type="file" name="file" id="file" onchange="capturarNomeArquivo()"/>
                    <input type="text" name="nomeArquivo" id="nomeArquivo" hidden="true">
                    <div align="right"><button id="enviarImagem" type="submit" class="btn btn-success">Enviar</button></div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src ="../js/upload.js"></script>
</html>