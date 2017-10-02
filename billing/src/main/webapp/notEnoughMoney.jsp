<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Not Enough Money</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<header>

</header>
    <c:out value="${requestScope.get('errorMessage')}"/>
<a href="<c:url value="/popUpBalance"/>">Pop_up balance</a>
<a href="<c:url value="/clientCabinet.jsp"/>">Go back to the cabinet</a>
<footer>

</footer>
</body>
</html>
