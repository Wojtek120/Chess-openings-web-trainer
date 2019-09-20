<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 19.09.19
  Time: 00:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="user-opening-add.title"/></title>
</head>
<body>
<c:import url="header.jsp"/>
<section class="hero has-background-primary" id="newBookSection">
    <div class="hero-body">
        <div class="container">
            <h1 class="title has-text-white">
                <spring:message code="user-opening-add.title-on-webpage"/>
            </h1>
            <h2 class="subtitle has-text-white">
                <spring:message code="user-opening-add.subtitle-on-webpage"/>
            </h2>
        </div>
    </div>
</section>


<section class="has-background-light">
    <br>
    <div class="columns is-mobile is-centered" id="addNewBookContainer">
        <div class="tile is-parent is-6">
            <div class="tile is-child box">


                <form:form method="post" modelAttribute="userOpeningDto">

                    <div class="field">
                        <spring:message code="user-opening.name" var="nameOfOpening"/>
                        <label class="label">${nameOfOpening}</label>
                        <div class="control">
                            <form:input path="name" required="true" class="input" placeholder="${nameOfOpening}"/>
                        </div>
                        <p class="help is-danger">
                            <form:errors path="name"/>
                        </p>
                    </div>

                    <div class="field">
                        <label class="label"><spring:message code="user-opening.play-as"/></label>
                        <div class="control">
                            <div class="select">
                                <form:select path="playedAs" required="true" class="select">
                                    <form:option value="white"><spring:message
                                            code="user-opening-add.white"/></form:option>
                                    <form:option value="black"><spring:message
                                            code="user-opening-add.black"/></form:option>
                                </form:select>
                            </div>
                            <p class="help is-danger">
                                <form:errors path="playedAs"/>
                            </p>
                        </div>
                    </div>

                    <div class="field">
                        <div class="control">
                            <label class="checkbox"><spring:message code="user-opening.is-public"/>
                                <form:checkbox path="isPublic"/>
                            </label>
                        </div>
                        <p class="help is-danger">
                            <form:errors path="isPublic"/>
                        </p>
                    </div>


                    <div class="control">
                        <button class="button is-rounded is-link"><spring:message code="general.save"/></button>
                    </div>

                </form:form>
            </div>
        </div>
    </div>
    <br>
</section>

<c:import url="fotter.jsp"/>

</body>
</html>
