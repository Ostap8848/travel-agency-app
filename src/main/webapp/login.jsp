<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 11.08.2022
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<div class="header">

    <a href = "home?command=setLocale&locale=ukr&pageToTranslate=${param.command}" role="button"
       class="btn btn-outline-primary me-2">Ua</a>
    <a href = "home?command=setLocale&locale&pageToTranslate=${param.command}" role="button"
       class="btn btn-outline-primary me-2">Eng</a>

</div>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2 align="center"><fmt:message key="index.pleaseLogin"/></h2>
<div align="center">
<form action="home" method="post">
    <input hidden name="command" value="login">
    <table style="with: 50%">

        <tr>
            <td>Login</td>
            <td> <input type="text" name="login"
                        style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                        required></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"
                       style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                       minlength="8"  required></td>
        </tr>
    </table>
    <input type="submit" value="<fmt:message key="index.login"/>" /></form>
</div>
</body>
</html>
