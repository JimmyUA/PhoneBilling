<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 17.09.2017
  Time: 0:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="client" value="${sessionScope.get('user')}" scope="page"/>
<p>Login: <c:out value="${client.getLogin()}"/></p>
