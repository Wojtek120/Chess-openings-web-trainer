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

    <div class="columns">
        <div class="column is-1"></div>
        <div class="column">
            <c:url var="addOpening" value="/user/opening/add"/>
            <a class="button is-primary" href="${addOpening}">
                <h3><spring:message code="user-opening-add.title-on-webpage"/></h3>
            </a>
        </div>
        <div class="column is-1"></div>
    </div>

    <div class="columns">
        <div class="column is-1"></div>
        <div class="column is-mobile is-centered">

            <table align="center" class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth">
                <thead>
                <tr>
                    <th align="center" ><spring:message code="user-opening.name"/></th>
                    <th align="center"><spring:message code="user-opening.play-as"/></th>
                    <th align="center"><spring:message code="user-opening.is-public"/></th>
                    <th colspan="2" align="center"><spring:message code="general.action"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${openings}" var="opening">
                    <tr>
                        <td align="center">${opening.name}</td>
                        <td align="center">${opening.playedAs}</td>
                        <td align="center">${opening.isPublic}</td>
                        <td align="center">
                            <c:url var="train" value="/user/opening/train/${opening.id}"/>
                            <a href="${train}">
                                <button class="button is-small is-rounded is-details"><spring:message
                                        code="user-opening-list.train"/></button>
                            </a>
                        </td>
                        <td align="center">
                            <c:url var="delete" value="/user/opening/delete/${opening.id}"/>
                            <form action="${delete}" method="post">
                                <button class="button is-small is-rounded is-danger"><spring:message
                                        code="general.delete"/></button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="column is-1"></div>
    </div>
    <br>
</section>
<c:import url="fotter.jsp"/>
</body>
</html>
