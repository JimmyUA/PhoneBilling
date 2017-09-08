<%@ page import="com.sergey.prykhodko.users.Client" %>
<%@ page contentType="text/html;charset=UTF-8; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <h1><c:out value="${requestScope.get('user').getFullName()}"/>, welcome in our billing</h1>
    <p><a href="clientCabinet.jsp">Enter personal cabinet</a></p>
</body>
</html>