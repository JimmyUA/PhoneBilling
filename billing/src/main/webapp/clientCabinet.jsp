<%@ page import="com.sergey.prykhodko.model.users.Client" %>

<%@ page contentType="text/html;charset=UTF-8; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="client" value="${sessionScope.get('user')}" scope="page"/>
<c:set var="tariff" value="${client.getTariffPlan()}" scope="page"/>
<html>

<head>
    <title>Cabinet</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="cabinetGreeting">
        <p>Hello, <c:out value="${client.getLogin()}"/></p>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
    <div class="pull-right">

    </div>
</header>

    <c:if test="${client.isActive()}">
        - Active.
    </c:if>
<p>Full name: <c:out value="${client.getFullName()}"/></p>
<p>E-mail ID: <c:out value="${client.getEmail()}"/></p>
<p>Tariff Plan : <c:out value="${tariff.getName()}"/></p>
<p>Balance : <c:out value="${client.getAccount().getBalance()}"/></p>

<footer>

</footer>
</body>
</html>
