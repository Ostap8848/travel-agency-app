<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 24.08.2022
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%--<%@ page isELIgnored="false" %>--%>
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
<div>
    <fmt:message key="index.loginLogin" />: <c:out value="${sessionScope.user.login}"/><br>
    <fmt:message key="index.firstName" />: <c:out value="${sessionScope.user.firstName}"/><br>
    <fmt:message key="index.lastName" />: <c:out value="${sessionScope.user.lastName}"/><br>
    Instagram: <c:out value="${sessionScope.user.instagram}"/><br>
    <fmt:message key="index.phoneNumber" />: <c:out value="${sessionScope.user.phoneNumber}"/><br>
    <fmt:message key="index.role" />:<c:out value="${sessionScope.user.role}"/><br>
</div>
<br>
<h2 style="text-align: center"><fmt:message key="index.myOrders"/></h2>
<table border="2" cellpadding="5">
    <tr>
        <th><fmt:message key="index.name"/></th>
        <th><fmt:message key="index.priceUah"/></th>
        <th><fmt:message key="index.dates"/></th>
        <th><fmt:message key="index.details"/></th>
        <th><fmt:message key="index.possibleDiscount"/></th>
        <th><fmt:message key="index.orderStatus"/></th>
    </tr>
    <c:forEach var="tour" items="${tours}">
        <tr>
            <td>
                <c:if test="${locale != 'ukr'}">
                    <c:out value="${tour.nameEng}"/>
                </c:if>
                <c:if test="${locale == 'ukr'}">
                    <c:out value="${tour.nameUkr}"/>
                </c:if>
            </td>
            <td><c:out value="${tour.price}"/>
            </td>
            <td><c:out value="${tour.description}"/>
            </td>
            <td><c:out value="${tour.tourType}"/>
            </td>

            <td><c:out value="${tour.numberOfPersons}"/>
            </td>
            <td><c:out value="${tour.hotelTypeByStars}"/>
            </td>
            <td><c:out value="${tour.isTourHot}"/>
            </td>
            <td><c:out value="${tour.discount}"/>
            </td>
            <td><c:out value="${tour.description}"/>
            </td>
        </tr>
    </c:forEach>
    <br>
</table>
</body>
</html>
