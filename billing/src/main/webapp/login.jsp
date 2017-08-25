<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Billing</title>
    <link href="css/login.css" rel="stylesheet">
</head>
<body>
	<header>

	</header>
        <article>
            <form id="loginForm" name="loginForm" method="post" action="" class="loginForm">
                <input name="loginForm" value="loginForm" type="hidden">
                <input id="loginForm:hiddenLocalStorage" name="loginForm:hiddenLocalStorage" type="hidden">
                <div id="loginTable" style="display: inline-flex;" class="formsWrapperDiv">
                    <div class="formsFloatDiv">
                        <img src="<c:url value="/drawable/login.png?ln=default"/>" alt="Login:">
                        <img src="<c:url value="/drawable/key.png?ln=default"/>" alt="Password:">
                    </div>
                    <div class="formsFloatDiv">
                        <input id="loginForm:login" name="loginForm:login" class="formsInputElem formsInputMiddle" type="text">
                        <input id="loginForm:password" name="loginForm:password" value="" class="formsInputElem formsInputMiddle" type="password">
                    </div>
            </div>
                <button id="loginForm:submit" name="loginForm:submit" class="ui-button  submitBtn">
                    <span class="ui-button-text ui-c">Login</span>
                </button>
            </form>
        </article>
    <footer>

    </footer>
</body>
</html>