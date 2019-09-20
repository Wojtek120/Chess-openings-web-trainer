<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 20.09.19
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <title><spring:message code="fotter" </title>--%>
</head>
<body>


    <footer class="footer"><div class="container">
        <div class="level">
            <div class="level-left">
                <div class="level-item"><a class="title is-5" href="#"><spring:message code="header-by"/> </a></div>
            </div>
            <p class="subtitle is-6"><spring:message code="header.made-with"/></p>
        </div>
        <hr><div class="columns">
        <div class="column">
            <a href="https://github.com/Wojtek120">
                <img src="/static/img/GitHub_Logo.png" style="height: 32px">
            </a>
            <a href="https://github.com/Wojtek120">
                <img src="/static/img/GitHub.png">
            </a>
        </div>
        <div class="column has-text-centered has-text-right-tablet">
            <div class="level-right"><a class="level-item" href="https://github.com/jhlywa/chess.js/blob/master/README.md">chess.js</a><a class="level-item" href="https://chessboardjs.com/">chessboard.js</a><a class="level-item" href="https://bulma.io">Bulma</a></div>
        </div>
    </div>
    </div>
    </footer>

</body>
</html>
