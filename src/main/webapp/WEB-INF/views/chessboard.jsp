<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

</head>
<body>
<div id="myBoard" style="width: 400px"></div>
<button id="changeOrientationBtn">Change orientation</button>
<label>Status:</label>
<div id="status"></div>
<label>FEN:</label>
<div id="fen"></div>
<label>PGN:</label>
<div id="pgn"></div>


<h4><spring:message code="main-chessboard.moves"/></h4>
<div>
    <table class="table" id="pgnTable">
    </table>
</div>

<h4><spring:message code="main-chessboard.moves.tree"/></h4>
<div id="openingTree">
    <ul>
        <li id="mainTree">

        </li>
    </ul>
</div>
<button id="saveTreeToDatabase" > <spring:message code="main-chessboard.save-to-database"/> </button>

<script src="static/js/chessboard.js"></script>
</body>
</html>
