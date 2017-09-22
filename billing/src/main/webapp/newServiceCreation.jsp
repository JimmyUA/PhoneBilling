<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service creation</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<h1>Service Creation</h1>
<div id="tariffCreationForm" style="display: inline-flex;" class="formsWrapperDiv">
    <div class="page-canvas">
        <div class="signup-wrapper">
            <form class="signup-form" method="post" action="<c:url value="/createService"/>" >
                <div>
                    <div class="field" data-fieldname="name">
                        <input id="serviceName" name="serviceName" autocomplete="off" value="" maxlength="50"
                               placeholder="Enter service name" class="" title="Chose any name">
                    </div>
                    <div class="field" data-fieldname="name">
                        <input id="serviceCharge" name="serviceCharge" autocomplete="off" value="" maxlength="50"
                               placeholder="Enter charge per month" class="" title="Enter charge in format 0.000">
                    </div>

                    <button  id="createServiceButton" formmethod="post" name="createServiceButton" class="ui-button  submitBtn >
                                    <span class="ui-button-text>CREATE</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
