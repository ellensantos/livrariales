<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 15/03/2021
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Ativar Livro</title>
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
                <h5 class="modal-title">Ativação de Livro</h5>

                    <a href="/ellentex-livrariales/admin/Livro?operacao=LISTAR_LIVROS"><button class="close">&times;</button></a>


            </div>
            <div class="modal-body">
                <form action="Livro" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Categoria Ativação</label>
                            <select class="form-control" name="idCategoriaAtivacao" required>
                                <option selected hidden="true" data-default value=""></option>
                                <c:forEach var="categAtivacao" items="${categoriasAtivacao}">
                                    <option value="${categAtivacao.id}">${categAtivacao.descricao}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                            <label>Justificativa Ativação</label>
                            <textarea class="form-control" rows="3" maxlength="500" name="justificativaStatus" id="justificativaStatus" required></textarea>
                    </div><br>
                    <div align="right"><button type="submit" class="btn btn-success" name="operacao" value="HABILITAR_LIVRO">SALVAR</button></div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src ="../js/upload.js"></script>
</html>