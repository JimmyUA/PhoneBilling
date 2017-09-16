<%@page import="com.sergey.prykhodko.model.users.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.Integer"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="clients" value="${requestScope.get('clients')}" scope="page"/>
<c:set var="page" value="${sessionScope.get('pageNumber')}"/>
<c:set var="lastPage" value="${sessionScope.get('lastPage')}"/>
<html>
<head>
    <title>Clients</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>


    <h1>Clients</h1>
    <c:forEach var="client" items="${clients}">
    <tr id="clientsTable" class="table">
        <td><c:out value="${client.getFullName()}" />|</td>
        <td>${client.getLogin()}  |</td>
        <td>${client.getEmail()}  </td>
        <td>
            <form name="clientActivation" action="/activate" method="post">
                <input name="clientID" type="hidden" value="${client.getId()}"/>
            <c:if test="${client.isActive() eq false}">
                <input name="activateDeactivateButton" type="submit" value="ACTIVATE" class="ui-button  submitBtn"/>
            </c:if><c:if test="${client.isActive() eq true}">
                <input name="activateDeactivateButton" type="submit" value="DEACTIVATE" class="ui-button  submitBtn"/>
            </c:if>
            </form>
        </td>
    </tr>
        <br>
        </c:forEach>

    <div>
        <form name="pagination" action="/clientsList" method="get">

            <c:if test="${sessionScope.get('pageNumber') > 1}">
                <input name="previous" type="submit" value="PREVIOUS" class="ui-button  submitBtn"/>
            </c:if>

            <p>${sessionScope.get("pageNumber").toString()}</p>

            <c:if test="${lastPage != page}">
                <input name="next" type="submit" value="NEXT" class="ui-button  submitBtn"/>
            </c:if>
        </form>
    </div>


</body>
</html>
