<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 18.09.19
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
</head>
<body>

<nav class="navbar" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <img src="static/img/book.png" height="42" width="80" vspace="5">
    </div>

    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <a class="navbar-item" href="">
                <h4><spring:message code="header.train"/></h4>

            </a>

            <c:url var="addOpening" value="/user/opening/add"/>
            <a class="navbar-item" href="${addOpening}">
                <h4><spring:message code="header.repositories"/></h4>
            </a>

        </div>

        <div class="navbar-end">
            <div class="navbar-item">
                <a href="https://github.com/Wojtek120">
                    <img src="static/img/GitHub.png">
                </a>
            </div>
        </div>
    </div>
</nav>

</body>
</html>
