<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 28.08.2022
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<body>
<div align="center">
    <h1><fmt:message key="index.addNewTour"/></h1>
    <form action="home" method="post">
        <input hidden name="command" value="createTour">
        <table style="with: 80%">
            <tr>
                <td><fmt:message key="index.ukrName"/></td>
                <td><input type="text" name="ukrName"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           required></td>
            </tr>
            <tr>
                <td><fmt:message key="index.engName"/></td>
                <td><input type="text" name="engName"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           required></td>
            </tr>
            <tr>
                <td><fmt:message key="index.byType"/></td>
                <td><input type="text" name="tourType"
                            style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                            required></td>
            </tr>
            <tr>
                <td><fmt:message key="index.byPrice"/></td>
                <td><input type="text" name="tourPrice"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           required></td>
            </tr>
            <tr>
                <td><fmt:message key="index.byNumberOfPersons"/></td>
                <td><input type="text" name="numberOfPersons"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           required></td>
            </tr>
            <tr>
                <td><fmt:message key="index.byHotelType"/></td>
                <td><input type="text" name="hotelType"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           required></td>
            </tr>
            <tr>
                <td><fmt:message key="index.hot"/></td>
                <td><input type="text" name="hot"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           required></td>
            </tr>
            <tr>
                <td><fmt:message key="index.discount"/></td>
                <td><input type="text" name="discount"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           required></td>
            </tr>
            <tr>
                <td><fmt:message key="index.tourDescription"/></td>
                <td><input type="text" name="description"
                           style="width: 500px;background-color: #ffffff;margin-bottom: 16px;"
                           required></td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="index.confirm"/>">
    </form>
</div>
</body>
</html>
