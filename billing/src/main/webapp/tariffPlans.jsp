<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 19.09.2017
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="tariffs" value="${requestScope.get('tariffs')}" scope="page"/>
<html>
<head>
    <title>Tariff Plans</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
    <h1>Tariffs</h1>
    <c:forEach var="tariff" items="${tariffs}">
        <tr id="tariffsTable" class="table">
            <td><c:out value="${tariff.getName()}"/>|</td>
            <td>${tariff.getChargeForMonth()} |</td>
        </tr>
        <c:forEach var="service" items="${tariff.getServices()}">
            </br>
            <td><c:out value="${service.getName()}"/>|</td>
            <td>${service.getChargePerMonth()} |</td>
        </c:forEach>
        <br>
    </c:forEach>
    <form action="<c:url value="/createTariff"/>" method="get">
        <input type="submit" id="createTariff" name="createTariff"
           class="ui-button  submitBtn" value="Create new Tariff">
    </form>
</body>
</html>
