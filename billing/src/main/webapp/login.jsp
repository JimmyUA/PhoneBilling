<%@page import="javax.naming.Context" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.SQLException" %>
<%@page import="java.sql.Connection" %>
<%@page import="javax.sql.DataSource" %>
<%@page import="javax.naming.InitialContext" %>
<%@ page contentType="text/html; charset=UTF-8; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:set var="errorMessage" value="${sessionScope.get('errorMessage')}" scope="page"/>
<c:set var="current" value="${param.language}" scope="session"/>

<c:if test="${not empty current}">
    <fmt:setLocale value="${current}" scope="session"/>
</c:if>
<fmt:setBundle basename="billing"/>

<head>
    <title>Billing</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<header>
    <form action="login.jsp" method="post">
        <select name="language">
            <option><fmt:message key="chose"/></option>
            <option value="en"><fmt:message key="english"/></option>
            <option value="ru"><fmt:message key="russian"/></option>
        </select>
        <input type="submit" value="<fmt:message key="chose"/>">
    </form>
</header>
<article class="loginArticle">
    <form id="loginForm" name="loginForm" method="post" action="<c:url value="/login"/>" class="loginForm"
          accept-charset="UTF-8">
        <input name="loginForm" value="loginForm" type="hidden">
        <input id="loginForm:hiddenLocalStorage" name="loginForm:hiddenLocalStorage" type="hidden">
        <div id="loginTable" style="display: inline-flex;" class="formsWrapperDiv">
            <div class="formsFloatDiv">
                <label for="loginForm:login"><img src="<c:url value="/drawable/login.png?ln=default"/>" alt="Login:"></label>
                <label for="loginForm:password"><img src="<c:url value="/drawable/key.png?ln=default"/>" alt="Password:"></label>
            </div>
            <div class="formsFloatDiv">
                <input id="loginForm:login" name="loginForm:login" class="formsInputElem formsInputMiddle" type="text">
                <input id="loginForm:password" name="loginForm:password" value=""
                       class="formsInputElem formsInputMiddle" type="password">
            </div>
        </div>
        <button id="loginForm:submit" name="loginForm:submit" class="ui-button  submitBtn">
            <span class="ui-button-text ui-c"><fmt:message key="login"/></span>
        </button>

        <div class="error">
            <c:out value="${errorMessage.toString()}"/>
        </div>
    </form>

    <div class="ifNotRegister">
        <p><fmt:message key="isRegister"/> <a href="register.jsp"> <fmt:message key="link"/> </a></p>
    </div>

</article>
<footer>

</footer>

</body>
</html>