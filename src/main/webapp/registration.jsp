<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 11.08.2022
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
</head>
<body>
<div align="center">
    <h1>Register Form</h1>
    <form action="registration.jsp" method="post">
        <table style="with: 80%">
            <tr>
                <td>Login</td>
                <td> <input type="text" name="login"></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td>First Name</td>
                <td><input type="text" name="firstName"></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastName"></td>
            </tr>
            <tr>
                <td>Instagram</td>
                <td><input type="text" name="instagram"></td>
            </tr>
            <tr>
                <td>Phone Number</td>
                <td><input type="text" name="phoneNumber"></td>
            </tr>
        </table>
        <input type="submit" name="Confirm">
    </form>
</div>
</body>
</html>
