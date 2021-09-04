<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 14/03/2021
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Cad Livro</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>

</head>
<body>
<jsp:include page="barraLateralAdmin.jsp"></jsp:include>

<div class="container mt-4">
    <div class="col-lg-9 my-lg-0 my-2">
        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">PESQUISA DE LIVRO</div>
            </div>

            <div class="d-flex flex-column">
                <form action="Livro" method="post">
                    <div class="card" id = "card-cadastro">
                        <div class="card-body" id = "card-cadastro">

                            <input type="text" name="qtdeCategoriaLivro" id="qtdeCategoriaLivro" value="${categoriasLivro.size()}" hidden="true">
                            <c:forEach var = "categoria" items="${categoriasLivro}" varStatus="cont">
                                <input type="text" class="form-control" name="idCategoriaLivro${cont.count}" id="idCategoriaLivro${cont.count}" value="${categoria.id}" hidden="true">
                                <input type="text" class="form-control" name="categoriaLivro${cont.count}" id="categoriaLivro${cont.count}" value="${categoria.descricao}" hidden="true">
                            </c:forEach>

                            <label for="dadosLivro"><b>DADOS LIVRO:</b></label>
                            <div class="card" id = "card-cadastro">
                                <div class="card-body" id = "card-cadastro">
                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <!-- <label for="statusLivro">Status</label>-->
                                             <select class="form-control" name="statusLivro" id="statusLivro" hidden="true">
                                                 <option value="1">Ativo</option>
                                             </select>
                                         </div>

                                         <div class="form-group col-md-5">
                                             <!-- <label for="statusLivro">Justificativa Status</label> -->
                                             <input type="tex" class="form-control" name="justificativaStatus" id="justificativaStatus" hidden="true">
                                         </div>

                                         <div class="form-group col-md-2">
                                             <!-- <label>Ativação</label> -->
                                            <input type="text" name="idCategoriaAtivacao" value="0" hidden="true">
                                        </div>

                                        <div class="form-group col-md-2">
                                            <!-- <label>Inativação</label> -->
                                            <input type="text" name="idCategoriaInativacao" value="0" hidden="true">
                                        </div>

                                    </div>

                                        <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label for="tituloLivro">Título</label>
                                            <input type="text" class="form-control" name="tituloLivro" id="tituloLivro">
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label for="isbn">ISBN</label>
                                            <input type="text" class="form-control" name="isbn" placeholder="Apenas números" maxlength="13" pattern="[0-9]+([,\.][0-9]+)?">
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="qtdePaginas">Qtde Páginas</label>
                                            <input type="number" class="form-control" step="1" min="0" value="0" name="qtdePaginas">
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="anoLancamento">Lançamento</label>
                                            <input type="number" class="form-control" name="anoLancamento" step="1" value ="0" min="0" max="2030" placeholder="YYYY">
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="numeroEdicao">Edição</label>
                                            <input type="number" min="0" step="0.5" max="1000" value="0" class="form-control" name="numeroEdicao"/>
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label for="autor">Autor</label>
                                            <select class="form-control" name="idAutor" required>
                                                <option selected data-default value="0"></option>
                                                <c:forEach var="autor" items="${autores}">
                                                    <option value="${autor.id}">${autor.nome}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label for="editora">Editora</label>
                                            <select class="form-control" name="idEditora" required>
                                                <option selected data-default value="0"></option>
                                                <c:forEach var="editora" items="${editoras}">
                                                    <option value="${editora.id}">${editora.nome}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <br>

                                    <br>
                                    <div class="form-row">
                                        <label for="sinopseLivro">Sinopse:</label>
                                        <textarea rows="3" maxlength="500" class="form-control" name="sinopse" id="sinopseLivro" placeholder="Máximo 500 caracteres"></textarea>
                                    </div><br>

                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label for="categoria">Categoria 1</label>
                                            <select class="form-control" name="categoria1">
                                                <option selected data-default value=""></option>
                                                <c:forEach var="categoriaLivro" items="${categoriasLivro}">
                                                    <option value="${categoriaLivro.id}">${categoriaLivro.descricao}</option>
                                                </c:forEach>
                                            </select>
                                        </div>


                                        <label for="qtdeCategoria" style="display:none;">Qtde Categoria</label>
                                        <input type="text" style="display:none;" class="form-control" value="1" id="qtdeCategoria" name="qtdeCategoria">
                                    </div>



                                <div id="novaCategoria"></div> <!-- Novas Categorias style="display:none;"-->


                                <div align="right">
                                    <button type="button" class="btn btn-primary" name="operacao" onclick='adicionarCategoria()'>Adicionar Categoria +</button>
                                </div>

                                </div>

                            </div>


                        <br>

                        <label for="dimensoesLivro"><b>DIMENSÕES:</b></label>
                        <div class="card" id = "card-cadastro">
                            <div class="card-body" id = "card-cadastro">
                                <div class="form-row">
                                    <div class="form-group col-md-2">
                                        <label for="altura">Altura</label>
                                        <input type="number" min="0" step="0.01" value="0" class="form-control" name="altura" placeholder="cm">
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="largura">Largura</label>
                                        <input type="number" min="0" step="0.01" value="0" class="form-control" name="largura" placeholder="cm">
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="comprimento">Comprimento</label>
                                        <input type="number" min="0" step="0.01" value="0" class="form-control" name="comprimento" placeholder="cm">
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="peso">Peso</label>
                                        <input type="number" min="0" step="0.01" value="0" class="form-control" name="peso" placeholder="Kg">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>

                        <label for="idPrecificacao"><b>PRECIFICAÇÃO:</b></label>
                        <div class="card" id = "card-cadastro">
                            <div class="card-body" id = "card-cadastro">
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="idPrecificacao">Grupo de Precificação</label>
                                        <select class="form-control" name="idPrecificacao">
                                            <option selected data-default value="0"></option>
                                            <c:forEach var="precificacao" items="${precificacoes}">
                                                <option value="${precificacao.id}">${precificacao.descricao} - Lucro = ${precificacao.margemLucro}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="codigoDeBarras">Código de Barras</label>
                                        <input type="text" class="form-control" maxlength="13" name="codigoDeBarras" id="codigoDeBarras" placeholder="Apenas números" pattern="[0-9]+([,\.][0-9]+)?">
                                    </div>

                                 <%--   <div class="form-group col-md-2">
                                        <label for="valorLivro">Custo (R$)</label>
                                        <input type="number" min="0" class="form-control" step="0.10" name="valorLivro" placeholder="R$" required>
                                    </div>--%>
                                </div>
                            </div>
                        </div>
                    </div><!-- Card Bory Principal -->
            </div> <!-- Card Bory Principal -->

            <br>

            <div align="right">
                <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="CONSULTAR">PESQUISAR</button>
            </div>
            </form>
        </div>
    </div>  <!-- -->
</div>  <!-- -->
</div>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src ="../js/livro.js"></script>

</body>
</html>
