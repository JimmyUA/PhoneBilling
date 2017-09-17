<%@ page import="com.sergey.prykhodko.model.users.Client" %>
<%@ page contentType="text/html;charset=UTF-8; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="client" value="${sessionScope.get('user')}" scope="page"/>
<p>Login: <c:out value="${client.login}"/>
<c:if test="${client.status}">
     - Active.
</c:if></p>
<p>Full name: <c:out value="${client.fullName()}"/></p>
<p>E-mail ID: <c:out value="${client.email()}"/></p>
