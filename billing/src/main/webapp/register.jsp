
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Registration</title>
    <link href="css/style.css" rel="stylesheet">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="js/registration.js"></script>
</head>
<body>
    <header>

    </header>

    <article>
        <div id="registrationForm" style="display: inline-flex;" class="formsWrapperDiv">
            <div class="page-canvas">
                <div class="signup-wrapper">
                    <h1 class="header">Join our billing!</h1>
                    <form class="signup-form" method="post" action="<c:url value="/registration"/>" >
                        <div>
                            <div class="field" data-fieldname="name">
                                <input id="full-name" name="full-name" autocomplete="off" value="" maxlength="50"
                                       placeholder="Full name" class="" title="Should contain 2 words"
                                       pattern="[A-Za-z\d]* [A-Za-z\d]*">
                            </div>
                            <div class="field" data-fieldname="login">
                                <input id="login" name="login" autocomplete="off" value=""  maxlength="20"
                                       placeholder="Chose login" class="" required pattern="^[A-Za-z0-9_]{3,25}$"
                                        title="Should be longer than 3 letters">
                            </div>
                            <div class="field" data-fieldname="e-mail">
                                <input id="e-mail" name="e-mail" autocomplete="off" value="" maxlength="50"
                                       placeholder="Enter your e-mail" class="" type="email" required
                                       title="Enter your e-mail address"
                                       pattern="^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$">
                            </div>
                            <div class="field" data-fieldname="password">
                                <input id="password" name="password" autocomplete="off" value="" maxlength="50"
                                       placeholder="Chose password" class="" type="password" required
                                       title="Password should have minimum 8 letters and minimum one number"
                                       pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$">
                            </div>
                            <div class="field" data-fieldname="confirmPassword">
                                <input id="confirmPassword" name="confirmPassword" autocomplete="off"
                                       value="" maxlength="50" placeholder="Confirm password" class=""
                                       type="password" required title="Passwords should be equals"
                                       onchange="validate()">
                            </div>
                                <button  id="registerButton" name="signupForm:submit" class="ui-button  submitBtn >
                                    <span class="ui-button-text>REGISTER</span>
                                </button>
                            <p id="validate-status"></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </article>

    <footer>

    </footer>

</body>
</html>
