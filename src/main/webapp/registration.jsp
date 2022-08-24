<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 11.08.2022
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>registration</title>
</head>
<body>
<div align="center">
    <h1><fmt:message key="index.register"/></h1>
    <form action="home" method="post">
        <input hidden name="command" value="register">
        <table style="with: 80%">
            <tr>
                <td>Login</td>
                <td> <input type="text" name="login"
                            style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                            pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$" required></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           minlength="8" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$" required></td>
            </tr>
            <tr>
                <td>Repeat Password</td>
                <td><input type="password" name="password"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           minlength="8" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$" required></td>
            </tr>
            <tr>
                <td>First Name</td>
                <td><input type="text" name="firstName"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           minlength="2"
                           required=""></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastName"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           minlength="2" required=""></td>
            </tr>
            <tr>
                <td>Instagram</td>
                <td><input type="text" name="instagram"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           pattern="\p{L}" minlength="2" required=""></td>
            </tr>
            <tr>
                <td>Phone Number</td>
                <td><input type="text" name="phoneNumber"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           pattern="((?:\+|00)[17](?: |\-)?|(?:\+|00)[1-9]\d{0,2}(?: |\-)?|(?:\+|00)1\-\d{3}(?: |\-)?)?(0\d|\([0-9]{3}\)|[1-9]{0,3})(?:((?: |\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\-)[0-9]{3}(?: |\-)[0-9]{4})|([0-9]{7}))"
                           required=""></td>
            </tr>
        </table>
        <input type="submit" value="Confirm">
    </form>
</div>
</body>
</html>


