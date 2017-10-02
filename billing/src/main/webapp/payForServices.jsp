<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="client" value="${sessionScope.get('user')}" scope="page"/>
<c:set var="unpaidInvoices" value="${sessionScope.get('unpaidInvoices')}" scope="page"/>
<html>
<head>
    <title>Pay for Services</title>
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
<p>Your current balance <c:out value="${client.checkBalance()}"/> $</p>
<p>Unpaid invoices</p>
<c:if test="${unpaidInvoices.size() == 0}">
    <p>All invoices are paid</p>
</c:if>
<c:forEach var="invoice" items="${unpaidInvoices}">
    <p>Invoice <c:out value="${invoice.getNumber()}"/> amount: <c:out value="${invoice.getAmount()}"/>$ Due Date
        <c:out value="${invoice.getDueDate()}"/></p>
</c:forEach>

<form name="payAllInvoices" method="post" action="<c:url value="/payForServices"/>">
    <input type="submit" value="PAY ALL" class="ui-button  submitBtn">
</form>

<footer>

</footer>
</body>
</html>
