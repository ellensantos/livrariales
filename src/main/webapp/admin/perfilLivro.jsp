<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 20/03/2021
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Detalhes Livro</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
</head>
<body>

<jsp:include page="barraLateralAdmin.jsp"></jsp:include>

<div class="container mt-4">
    <div class="col-lg-9 my-lg-0 my-2">
        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">LIVRO</div>
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


                            <div class="card" id = "card-cadastro">
                                <div class="card-body" id = "card-cadastro">
                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label for="statusLivro">Status do Livro</label>
                                            <c:if test="${livroPerfil[0].status eq true}">
                                                <input type="text" class="form-control" value="Ativo" disabled>
                                                <input type="text" value="1" name="statusLivro" id="statusLivro" hidden="true"></input>
                                            </c:if>

                                            <c:if test="${livroPerfil[0].status eq false}">
                                                <input type="text" class="form-control" value="Inativo" disabled>
                                                <input type="text" value="0" name="statusLivro" id="statusLivro" hidden="true"></input>
                                            </c:if>


                                            <input type="text" value="${livroPerfil[0].id}" name="idLivroPerfil" id="idLivroPerfil" hidden="true">
                                            <input type="text" value="${livroPerfil[0].dimensao.id}" name="idDimensaoLivroPerfil" id="idDimensaoLivroPerfil" hidden="true">
                                        </div>


                                    <div class="form-group col-md-4">
                                        <label>Foto Atual:</label>
                                        <input type="text" class="form-control" name="nomeImagemProduto" id="nomeImagemProduto" value="${livroPerfil[0].foto}" readonly="readonly" ">
                                    </div>
                                    </div>





                                    <div class="form-row">
                                        <div class="form-group col-md-5">
                                            <!-- <label for="statusLivro">Justificativa Status</label> -->
                                            <input type="tex" class="form-control" name="justificativaStatus" id="justificativaStatus" value="${livroPerfil[0].justificativaStatus}" hidden="true">
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
                                            <input type="text" class="form-control" name="tituloLivro" id="tituloLivro" value="${livroPerfil[0].titulo}" required>
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label for="isbn">ISBN</label>
                                            <input type="text" class="form-control" name="isbn" placeholder="Apenas números" minlength="13" maxlength="13" pattern="[0-9]+([,\.][0-9]+)?" value="${livroPerfil[0].isbn}" required>
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="qtdePaginas">Qtde Páginas</label>
                                            <input type="number" class="form-control" step="1" min="1" name="qtdePaginas" value="${livroPerfil[0].qtdePagina}" required>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="anoLancamento">Lançamento</label>
                                            <input type="number" class="form-control" name="anoLancamento" step="1" min="1000" max="2030" placeholder="YYYY" value="${livroPerfil[0].anoLancamento}" required>
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="numeroEdicao">Edição</label>
                                            <input type="number" min="0" step="0.5" max="1000" class="form-control" name="numeroEdicao" value="${livroPerfil[0].edicao}" required/>
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label for="autor">Autor</label>
                                            <select class="form-control" name="idAutor" required>
                                                <option selected hidden="true" data-default value="${livroPerfil[0].autor.id}">${livroPerfil[0].autor.nome}</option>
                                                <c:forEach var="autor" items="${autores}">
                                                    <option value="${autor.id}">${autor.nome}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label for="editora">Editora</label>
                                            <select class="form-control" name="idEditora" required>
                                                <option selected hidden="true" data-default value="${livroPerfil[0].editora.id}">${livroPerfil[0].editora.nome}</option>
                                                <c:forEach var="editora" items="${editoras}">
                                                    <option value="${editora.id}">${editora.nome}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <br>


                                    <div class="form-row">
                                        <label for="sinopseLivro">Sinopse:</label>
                                        <textarea rows="3" maxlength="500" class="form-control" name="sinopse" id="sinopseLivro" placeholder="Máximo 500 caracteres" required>${livroPerfil[0].sinopse}</textarea>
                                    </div><br>


                                    <!-- <label for="qtdeCategoria">Qtde Categoria</label> -->
                                    <input type="text" class="form-control" value="${livroPerfil[0].categoria.size()}" id="qtdeCategoria" name="qtdeCategoria" hidden="true">

                                    <c:forEach var="categ" items="${livroPerfil[0].categoria}" varStatus="contador">
                                        <div id="novaCategoria${contador.count}">
                                            <div class="form-row">
                                                <div class="form-group col-md-4">
                                                    <label for="categoria">Categoria</label>
                                                    <select class="form-control" name="categoria${contador.count}" onchange="validarCategorias(this)" required>
                                                        <option selected hidden="true" data-default value="${categ.id}">${categ.descricao}</option>
                                                        <c:forEach var="categoriaLivro" items="${categoriasLivro}">
                                                            <option value="${categoriaLivro.id}">${categoriaLivro.descricao}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <c:if test="${contador.count > 1}">
                                                <button type="button" class="btn btn-danger btn-sm" name = "remover${contador.count}" id="remover${contador.count}" onclick="deletarCategoria(this)">Excluir Categoria ${contador.count}</button>
                                            </c:if>
                                        </div>
                                    </c:forEach>

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

                                        <input name="idDimensao" id ="idDimensao" value="${livroPerfil[0].dimensao.id}" hidden="true">


                                        <div class="form-group col-md-2">
                                            <label for="altura">Altura</label>
                                            <input type="number" min="0" step="0.01" class="form-control" name="altura" placeholder="cm" value="${livroPerfil[0].dimensao.altura}" required>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label for="largura">Largura</label>
                                            <input type="number" min="0" step="0.01" class="form-control" name="largura" placeholder="cm" value="${livroPerfil[0].dimensao.largura}" required>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label for="comprimento">Comprimento</label>
                                            <input type="number" min="0" step="0.01" class="form-control" name="comprimento" placeholder="cm" value="${livroPerfil[0].dimensao.comprimento}"required>
                                        </div>
                                        <div class="form-group col-md-2">
                                            <label for="peso">Peso</label>
                                            <input type="number" min="0" step="0.01" class="form-control" name="peso" placeholder="Kg" value="${livroPerfil[0].dimensao.peso}"required>
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
                                            <select class="form-control" name="idPrecificacao" required>
                                                <option selected hidden="true" data-default value="${livroPerfil[0].precificacao.id}">${livroPerfil[0].precificacao.descricao} - Livro = ${livroPerfil[0].precificacao.margemLucro}</option>
                                                <c:forEach var="precificacao" items="${precificacoes}">
                                                    <option value="${precificacao.id}">${precificacao.descricao} - Lucro = ${precificacao.margemLucro} </option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label for="codigoDeBarras">Código de Barras</label>
                                            <input type="text" class="form-control" minlength="13" maxlength="13" name="codigoDeBarras" id="codigoDeBarras" placeholder="Apenas números" pattern="[0-9]+([,\.][0-9]+)?" value="${livroPerfil[0].codBarras}" required>
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
                        <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="ALTERAR">ALTERAR</button>
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
