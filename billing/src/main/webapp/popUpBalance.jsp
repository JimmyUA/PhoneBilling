<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pop-Up Balance</title>
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
    <div>
        <form method="post" name="popUpBalance" action="${pageContext.request.contextPath}/popUpBalance">
            <input type="text" required title="please enter your card number"
                placeholder="0000 0000 0000 0000" pattern="[\d]{16}">
            <input type="number" required title="enter CVV" placeholder="CVV"
                pattern="[\d]{3}">
            <input type="number" required title="enter expiration date" placeholder="mm"
                   pattern="[\d]{2}">/
            <input type="number" required title="enter expiration date" placeholder="yy"
                   pattern="[\d]{2}">

            <label>Enter amount</label>
            <input id="amount" name="amount" type="text" required title="enter amount">
            <input type="submit" value="POP UP" class="ui-button  submitBtn">
        </form>
    </div>
<footer>

</footer>
</body>
</html>
