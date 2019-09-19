<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wojciech
  Date: 08.09.19
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

    <!---Chess rules--->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/chess.js/0.10.2/chess.js"></script>

    <!---Chess board--->
    <script src="https://unpkg.com/@chrisoakman/chessboardjs@1.0.0/dist/chessboard-1.0.0.min.js"
            integrity="sha384-8Vi8VHwn3vjQ9eUHUxex3JSN/NFqUg3QbPyX8kWyb93+8AC/pPWTzj+nHtbC5bxD"
            crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="https://unpkg.com/@chrisoakman/chessboardjs@1.0.0/dist/chessboard-1.0.0.min.css"
          integrity="sha384-q94+BZtLrkL1/ohfjR8c6L+A6qzNH9R2hBLwyoAfu3i/WCvQjzL2RQJ3uNHDISdU"
          crossorigin="anonymous">

    <title><spring:message code="main-chessboard.header"/><</title>

    <style>
        ul li {
            margin-left: 10%;
        }
    </style>
</head>
<body>

<c:import url="header.jsp"/>

<section class="hero has-background-primary" id="newBookSection">
    <div class="hero-body">
        <div class="container">
            <h1 class="title has-text-white">
                <spring:message code="main-chessboard.training-title"/>
            </h1>
            <h2 class="subtitle has-text-white">
                <spring:message code="main-chessboard.training-subtitle"/>
            </h2>
        </div>
    </div>
</section>

<section class="has-background-light">
    <br>


    <div class="columns is-mobile">
        <div class="column is-4  is-centered">
            <div class="columns is-mobile">
                <div class="column">
                    <button id="changeOrientationBtn">Change orientation</button>
                    <label>Status:</label>
                    <div id="status"></div>
                </div>
                <div class="column is-centered">
                    <h4><spring:message code="main-chessboard.moves.tree"/></h4>
                    <button id="saveTreeToDatabase"><spring:message code="main-chessboard.save-to-database"/></button>
                    <div id="openingTree">
                        <ul>
                            <li id="mainTree">

                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="column is-4  is-centered">
            <div id="myBoard" style="width: 400px"></div>
        </div>
        <div class="column is-4" style="text-align:left;">

            <h4><spring:message code="main-chessboard.moves"/></h4>
            <table class="table" id="pgnTable">
            </table>
        </div>
    </div>


    <%--        <label>FEN:</label>--%>
    <%--        <div id="fen"></div>--%>
    <%--        <label>PGN:</label>--%>
    <%--        <div id="pgn"></div>--%>

    <br>
</section>


<button class="loadRepository">Load</button>
<button id="trainBtn">Train</button>



<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

<script src="static/js/chessboard.js"></script>
</body>
</html>
