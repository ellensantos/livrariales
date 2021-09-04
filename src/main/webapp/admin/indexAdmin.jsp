<%--
  Created by IntelliJ IDEA.
  User: EllenTex
  Date: 14/03/2021
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>ADMIN</title>
    <jsp:include page="navAdmin.jsp"></jsp:include>
</head>
<body>
<jsp:include page="barraLateralAdmin.jsp"></jsp:include>
<jsp:useBean id="dataAtual" class="java.util.Date"/>

<div class="container mt-4">    <!-- -->
    <div class="col-lg-9 my-lg-0 my-1">
        <h2 style= "text-align:center;"><fmt:formatDate value="${dataAtual}" dateStyle="full"/></h2>
        <fmt:formatDate value="${dataAtual}" pattern="dd-MM-yyyy"/>

    </div>
</div>


</body>
</html>
