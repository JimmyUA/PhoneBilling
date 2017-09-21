
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin cabinet</title>
</head>
<body>
    <h1>Hello Admin!</h1>
    <a href="<c:url value="/clientsList"/>">Check Clients list</a></br>
    <a href="<c:url value="/tariffs"/>">Tariffs</a>
</body>
</html>
