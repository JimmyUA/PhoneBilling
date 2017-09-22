<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Service</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
    <h2>New Service "<c:out value="${requestScope.get('serviceName')}"/>" is created</h2>
    <a href="<c:url value="/tariffs"/>">Go back to tariff creation</a>

</body>
</html>
