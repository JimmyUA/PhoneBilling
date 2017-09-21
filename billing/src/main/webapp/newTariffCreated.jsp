<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Tariff</title>
</head>
<body>
    <h2>New Tariff "<c:out value="${requestScope.get('tariffName')}"/>" is created</h2>
    <a href="<c:url value="adminCabinet.jsp"/>">Go back to cabinet</a>
</body>
</html>
