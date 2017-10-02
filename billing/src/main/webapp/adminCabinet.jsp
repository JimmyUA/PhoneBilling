
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin cabinet</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="cabinetGreeting">
        <p>Hello, <c:out value="${client.getLogin()}"/></p>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
    <div class="pull-right">

    </div>
</header>
<main>
    <h1>Hello Admin!</h1>
    <a href="<c:url value="/clientsList"/>">Check Clients list</a></br>
    <a href="<c:url value="/tariffs"/>">Tariffs</a>
</main>
    <footer>

</footer>
</body>
</html>
