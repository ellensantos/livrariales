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

<c:if test="${sessionScope.msg eq 'sucesso'}">
    <div class="alert alert-success" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>SUCESSO!</strong> Operação bem sucedida!
    </div>
    ${sessionScope.remove("msg")}
</c:if>

<c:if test="${sessionScope.msg ne 'sucesso' and not empty sessionScope.msg}">
    <div class="alert alert-danger" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>FALHA!</strong> ${sessionScope.msg}
    </div>
    ${sessionScope.remove("msg")}
</c:if>

<div class="container mt-4">
    <div class="col-lg-9 my-lg-0 my-2">
        <div id="main-content" class="bg-white border">
            <div class="d-flex flex-column">
                <div class="h2" style= "text-align:center;">CADASTRO DE LIVRO</div>
            </div>

            <div class="d-flex flex-column">
                <form action="Livro" method="post" onsubmit="return validarFotoLivro()">
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
                                            <label for="capaLivro">FOTO LIVRO: </label>
                                            <a href="formUploadImagemProd.jsp" id="capaLivro">Inserir Imagem</a>
                                            <c:if test="${msgUploadArquivo eq 'Arquivo carregado com sucesso'}">

                                                <div class="form-check-inline">
                                                    <label class="form-check-label" for="validacaoimagemProduto" id= "label-titulos">
                                                        <input type="checkbox" class="form-check-input" id="validacaoImagemProduto" name="validacaoImagemProduto" checked="true" disabled>Status Imagem
                                                    </label>
                                                </div>

                                                <div class="form-row">
                                                    <label>Foto Atual:</label>
                                                    <input type="text" class="form-control" name="nomeImagemProduto" id="nomeImagemProduto" value="${nomeImagemProduto}" readonly>
                                                </div>

                                            </c:if>

                                            <c:if test="${msgUploadArquivo ne 'Arquivo carregado com sucesso'}">
                                                <div class="form-check-inline">
                                                    <label class="form-check-label" for="validacaoimagemProduto" id= "label-titulos">
                                                        <input type="checkbox" class="form-check-input" id="validacaoImagemProduto" name="validacaoImagemProduto" disabled >Status Imagem
                                                    </label>
                                                </div>
                                            </c:if>
                                        </div>
                                        <div class="form-group col-md-7">
                                            ${msgUploadArquivo}.
                                        </div>
                                    </div>


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
                                            <input type="text" class="form-control" name="tituloLivro" id="tituloLivro" required>
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label for="isbn">ISBN</label>
                                            <input type="text" class="form-control" name="isbn" placeholder="Apenas números" minlength="13" maxlength="13" pattern="[0-9]+([,\.][0-9]+)?" required>
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="qtdePaginas">Qtde Páginas</label>
                                            <input type="number" class="form-control" step="1" min="1" name="qtdePaginas" required>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="anoLancamento">Lançamento</label>
                                            <input type="number" class="form-control" name="anoLancamento" step="1" min="1000" max="2030" placeholder="YYYY" required>
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="numeroEdicao">Edição</label>
                                            <input type="number" min="0" step="0.5" max="1000" class="form-control" name="numeroEdicao" required/>
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label for="autor">Autor</label>
                                            <select class="form-control" name="idAutor" required>
                                                <option selected hidden="true" data-default value=""></option>
                                                <c:forEach var="autor" items="${autores}">
                                                    <option value="${autor.id}">${autor.nome}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label for="editora">Editora</label>
                                            <select class="form-control" name="idEditora" required>
                                                <option selected hidden="true" data-default value="" ></option>
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
                                        <textarea rows="3" maxlength="500" class="form-control" name="sinopse" id="sinopseLivro" placeholder="Máximo 500 caracteres" required></textarea>
                                    </div><br>

                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label for="categoria">Categoria 1</label>
                                            <select class="form-control" name="categoria1" id="categoria1" onchange="validarCategorias(this)" required>
                                                <option selected hidden="true" data-default value=""></option>
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
                                    <input name="idDimensao" id ="idDimensao" value="0" hidden="true">
                                    <div class="form-group col-md-2">
                                        <label for="altura">Altura</label>
                                        <input type="number" min="0" step="0.01" class="form-control" name="altura" placeholder="cm" required>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="largura">Largura</label>
                                        <input type="number" min="0" step="0.01" class="form-control" name="largura" placeholder="cm" required>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="comprimento">Comprimento</label>
                                        <input type="number" min="0" step="0.01" class="form-control" name="comprimento" placeholder="cm" required>
                                    </div>
                                    <div class="form-group col-md-2">
                                        <label for="peso">Peso</label>
                                        <input type="number" min="0" step="0.01" class="form-control" name="peso" placeholder="Kg" required>
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
                                            <option selected hidden="true" data-default value=""></option>
                                            <c:forEach var="precificacao" items="${precificacoes}">
                                                <option value="${precificacao.id}">${precificacao.descricao} - Lucro = ${precificacao.margemLucro}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="codigoDeBarras">Código de Barras</label>
                                        <input type="text" class="form-control" minlength="13" maxlength="13" name="codigoDeBarras" id="codigoDeBarras" placeholder="Apenas números" pattern="[0-9]+([,\.][0-9]+)?" required>
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
                <button type="submit" class="btn btn-success" name="operacao" id="operacao" value="SALVAR">CADASTRAR</button>
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
