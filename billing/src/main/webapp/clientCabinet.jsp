<%@ page import="com.sergey.prykhodko.model.users.User" %>
<%@ page contentType="text/html;charset=UTF-8; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Cabinet</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<header>
    <div class="cabinetGreeting">
        <p>Hello, <c:out value="${sessionScope.get('user').getLogin()}"/></p>
    </div>
    <div class="pull-right">

    </div>
</header>


<footer>

</footer>
</body>
</html>
