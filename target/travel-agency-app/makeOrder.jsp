<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 17.08.2022
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<div class="header">

    <div class="container-fluid p-4 my-0 bg-light text-info">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
            <div class="col-md-1 text-end">
                <a href = "home?command=setLocale&locale=ukr&pageToTranslate=${param.command}" role="button"
                   class="btn btn-outline-primary me-2">Ua</a>
                <a href = "home?command=setLocale&locale&pageToTranslate=${param.command}" role="button"
                   class="btn btn-outline-primary me-2">Eng</a>
            </div>
            <div class="col-md-6 text-end">
                <h4 align="center"><a href="home?command=homePage">Travel Agency</a></h4>
            </div>
            <c:if test="${sessionScope.user == null}">
                <div class="col-md-15 text-end">
                    <a href = "home?command=loginForm" role="button"
                       class="btn btn-outline-primary me-2"><fmt:message key="index.login"/></a>
                    <a href = "home?command=registerForm" role="button" class="btn btn-primary"><fmt:message key="index.registration"/></a>
                </div>
            </c:if>
            <c:if test="${sessionScope.user != null}">

                <div class="col-md-15 text-end">
                    <a href="home?command=personalAccount"><fmt:message key="index.hello"/>
                            ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                    </a>
                    <a href="home?command=logout" role="button" class="btn btn-outline-primary mx-2"><fmt:message key="index.logout"/></a>
                </div>
            </c:if>
        </div>
    </div>
</div>
<html>
<head>
    <title>make order</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS (jsDelivr CDN) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Bootstrap Bundle JS (jsDelivr CDN) -->
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
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
