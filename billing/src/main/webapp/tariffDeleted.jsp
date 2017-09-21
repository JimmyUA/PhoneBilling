<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tariff deleted</title>
</head>
<body>
    <h2>Tariff with ID "<c:out value="${requestScope.get('tariffID')}"/>" is deleted</h2>
    <a href="<c:url value="adminCabinet.jsp"/>">Go back to cabinet</a>

</body>
</html>
