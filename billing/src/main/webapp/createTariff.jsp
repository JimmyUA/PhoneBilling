
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="services" value="${requestScope.get('services')}" scope="page"/>
<html>
    <head>
        <link href="css/style.css" rel="stylesheet">
        <title>Create Tariff</title>
    </head>
    <body>
        <h1>Tariff Creation</h1>
        <div id="tariffCreationForm" style="display: inline-flex;" class="formsWrapperDiv">
            <div class="page-canvas">
                <div class="signup-wrapper">
                    <form class="signup-form" method="post" action="<c:url value="/createTariff"/>" >
                        <div>
                            <div class="field" data-fieldname="name">
                                <input id="tariffName" name="tariffName" autocomplete="off" value="" maxlength="50"
                                       placeholder="Enter tariff name" class="" title="Chose any name">
                            </div>

                            <fieldset>
                                <legend>Chose services to add to tariff plan</legend>
                                <c:forEach var="service" items="${services}">
                                    <label>
                                        <input type="checkbox" name="services" value="${service.getID()}"/>
                                    </label>
                                    <c:out value="${service.getName()}"/> <br />
                                </c:forEach>
                            </fieldset>


                            <button  id="createTariffButton" formmethod="post" name="createTariffButton" class="ui-button  submitBtn >
                                    <span class="ui-button-text>CREATE</span>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
