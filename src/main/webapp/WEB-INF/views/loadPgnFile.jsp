<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 17.09.19
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title><spring:message code="load-multiple-pgn.header"/></title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>
<body>
<h1><spring:message code="load-multiple-pgn.header"/></h1>

<input type="file" id="loadPgnBtn" name="loadPgnBtn"/>
<input type="hidden" id="openingString" name="openingString"/>


<c:url var="saveOpeningsUrl" value="/admin/load"/>
<form method="post" action="${saveOpeningsUrl}">
    <input type="hidden" name="openingPgnString" id="openingPgnString" value=""/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="ok">
</form>

<script src="/static/js/loadPgnFile.js"></script>
</body>
</html>
