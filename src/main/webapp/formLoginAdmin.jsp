<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap CSS -->
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.0/font/bootstrap-icons.css">
  <link rel="stylesheet" type="text/css" href="css/estilo.css"/>
  <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"/>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap CSS -->

  <title>Login</title>

</head>
<body  class="text-center">
  <br /> <br /> <br />
  <div class="d-flex justify-content-center">

    <main class="form-signin">
      <form action="LoginAdmin" method="post">
        <a class="navbar-brand" href="index.jsp" style="font-size: 20px"> <b><i class="bi bi-book-half"></i> LIVRARIALES</b></a>
        <h5 class="h5 mb-1 fw-normal">Administrador</h5><br>
        <input type="text" id="email" name="email" class="form-control" placeholder="Usuario" required autofocus>
        <input type="password" id="senha" name="senha" class="form-control" placeholder="Senha" required>

        <c:if test="${not empty msg and msg ne 'sucesso'}">
          <h7>${msg}</h7>
          ${sessionScope.remove("msg")}
          <br>
        </c:if>

        <br/>
        <button class="w-100 btn btn-danger" type="submit" name="operacao" id="operacao" value="LOGAR">Login</button>
        <hr class="mb-4">
        <h6>Não tem cadastro? </h6>Contate o Suporte
        <p class="mt-5 mb-3 text-muted">&copy; LES - 2021</p>
      </form>
    </main>
  </div>
</body>
</html>
