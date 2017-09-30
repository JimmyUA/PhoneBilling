<%@ page import="com.sergey.prykhodko.model.users.User" %>

<%@ page contentType="text/html;charset=UTF-8; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="client" value="${sessionScope.get('user')}" scope="page"/>
<html>

<head>
    <title>Cabinet</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="cabinetGreeting">
        <p>Hello, <c:out value="${sessionScope.get('user').getLogin()}"/></p>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
    <div class="pull-right">

    </div>
</header>

<p>Login: <c:out value="${client.getLogin()}"/>
    <c:if test="${client.isActive()}">
        - Active.
    </c:if></p>
<p>Full name: <c:out value="${client.getFullName()}"/></p>
<p>E-mail ID: <c:out value="${client.getEmail()}"/></p>
<p>Tariff Plan : <c:out value="${client.getTariffPlanId()}"/></p>

<footer>

</footer>
</body>
</html>
