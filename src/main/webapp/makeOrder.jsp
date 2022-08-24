<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 17.08.2022
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-fluid p-4 my-0 bg-light text-info">
    <div class="row">
        <div class="col-10">
            <h3 align="center">Travel Agency</h3>
        </div>
        <div class="col-1">
            <p align="right"><button type="button" class="btn btn-outline-primary"><a href = "login.jsp">Login</a></button></p>
        </div>
        <div class="col-1">
            <p align="right"><button type="button" class="btn btn-outline-primary"><a href = "registration.jsp">Registration</a></button></p>
        </div>
    </div>
</div>
<div align="center">
    <h1><fmt:message key="index.register"/></h1>
    <form action="home" method="post">
        <input hidden name="command" value="register">
        <table style="with: 80%">
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
                           minlength="8" required></td>
            </tr>
            <tr>
                <td>Repeat Password</td>
                <td><input type="password" name="password"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           minlength="8" required></td>
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
                           minlength="2" required=""></td>
            </tr>
            <tr>
                <td>Phone Number</td>
                <td><input type="text" name="phoneNumber"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           required=""></td>
            </tr>
        </table>
        <input type="submit" value="Confirm">
    </form>
</div>
</body>
</html>
