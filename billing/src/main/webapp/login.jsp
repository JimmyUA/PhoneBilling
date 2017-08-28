<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Billing</title>
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<header>

</header>
<article class="loginArticle">
    <form id="loginForm" name="loginForm" method="post" action="" class="loginForm">
        <input name="loginForm" value="loginForm" type="hidden">
        <input id="loginForm:hiddenLocalStorage" name="loginForm:hiddenLocalStorage" type="hidden">
        <div id="loginTable" style="display: inline-flex;" class="formsWrapperDiv">
            <div class="formsFloatDiv">
                <label for="loginForm:login"><img src="/drawable/login.png?ln=default" alt="Login:"></label>
                <label for="loginForm:password"><img src="/drawable/key.png?ln=default" alt="Password:"></label>
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

    <div class="ifNotRegister">
        <p>Not registered yet? <a href="register.jsp"> Click here!</a></p>
    </div>
    <h1>Data in my Connection Pooled Database</h1>
    <br>
    <%
        InitialContext initialContext = new InitialContext();
        Context context = (Context) initialContext.lookup("java:comp/env");
        //The JDBC Data source that we just created
        DataSource ds = (DataSource) context.lookup("jdbc/billing");
        Connection connection = ds.getConnection();

        if (connection == null)
        {
            throw new SQLException("Error establishing connection!");
        }
        String query = "SELECT * FROM clients";

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        while (rs.next())
        {
            out.print(rs.getString("name") + "< br >");
        }
    %>

</article>
<footer>

</footer>

</body>
</html>