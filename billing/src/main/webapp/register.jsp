
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="css/login.css" rel="stylesheet">
</head>
<body>
    <header>

    </header>

    <article>
        <div id="registrationForm" style="display: inline-flex;" class="formsWrapperDiv">
            <div class="page-canvas">
                <div class="signup-wrapper">
                    <h1 class="header">Join our billing!</h1>
                    <form class="signup-form" method="post" action="" >
                        <div>
                            <div class="field" data-fieldname="name">
                                <input id="full-name" autocomplete="off" value="" maxlength="50" placeholder="First name and second name" class="">
                            </div>
                            <div class="field" data-fieldname="login">
                                <input id="login" autocomplete="off" value=""  maxlength="20" placeholder="Chose login" class="">
                            </div>
                            <div class="field" data-fieldname="e-mail">
                                <input id="e-mail" autocomplete="off" value="" maxlength="50" placeholder="Enter your e-mail" class="">
                            </div>
                            <div class="field" data-fieldname="e-mail">
                                <input id="password" autocomplete="off" value="" maxlength="50" placeholder="Chose password" class="" type="password">
                            </div>
                            <div class="field" data-fieldname="e-mail">
                                <input id="confirm password" autocomplete="off" value="" maxlength="50" placeholder="Confirm password" class="" type="password">
                            </div>
                                <button  id="registerButton" name="signupForm:submit" class="ui-button  submitBtn">
                                    <span class="ui-button-text ui-c">REGISTER</span>
                                </button>
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
