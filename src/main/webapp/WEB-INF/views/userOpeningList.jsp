<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 19.09.19
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="user-opening-list.list-of-openings"/></title>
</head>
<body>


<c:import url="header.jsp"/>
<section class="hero has-background-primary" id="newBookSection">
    <div class="hero-body">
        <div class="container">
            <h1 class="title has-text-white">
                <spring:message code="user-opening-list.list-of-openings"/>
            </h1>
            <h2 class="subtitle has-text-white">
                <spring:message code="user-opening-list.list-of-openings-subtitle"/>
            </h2>
        </div>
    </div>
</section>


<section class="has-background-light">
    <br>

    <c:url var="addOpening" value="/user/opening/add"/>
    <a href="${addOpening}">
        <h3><spring:message code="user-opening-add.title-on-webpage" /></h3>
    </a>
    <br>

    <div class="columns is-mobile is-centered">

        <table class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth">
            <thead>
            <tr>
                <th><spring:message code="user-opening.name"/></th>
                <th><spring:message code="user-opening.play-as"/></th>
                <th><spring:message code="user-opening.is-public"/></th>
                <th><spring:message code="general.action"/></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${openings}" var="opening">
                <tr>
                    <td>${opening.name}</td>
                    <td>${opening.playedAs}</td>
                    <td>${opening.isPublic}</td>
                    <td>
                        <c:url var="train" value="/user/opening/train/${opening.id}"/>
                        <a href="${train}">
                            <button class="button is-small is-rounded is-details"><spring:message
                                    code="user-opening-list.train"/></button>
                        </a>

                        <c:url var="delete" value="${opening.id}"/>
                        <a href="${delete}">
                            <button class="button is-small is-rounded is-danger"><spring:message
                                    code="general.delete"/></button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <br>
</section>
</body>
</html>
