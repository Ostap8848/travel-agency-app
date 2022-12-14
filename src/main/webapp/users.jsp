<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 11.08.2022
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="WEB-INF/tld/mytag.tld" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<div class="header">

    <div class="container-fluid p-4 my-0 bg-light text-info">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
            <div class="col-md-1 text-end">
                <a href = "home?command=setLocale&locale=ukr&pageToTranslate=${param.command}" role="button"
                   class="btn btn-outline-primary me-2">Ukr</a>
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
<h2 style="text-align: center"><a href="home?command=allUsers"><fmt:message key="index.allUsers"/></a></h2>
<head>
    <title>Title</title>
</head>
<div align="center">
<table border="2" cellpadding="5">
    <tr>
        <th><fmt:message key="index.firstName"/></th>
        <th><fmt:message key="index.lastName"/></th>
        <th><fmt:message key="index.loginLogin"/></th>
        <th>Instagram</th>
        <th><fmt:message key="index.phoneNumber"/></th>
        <th><fmt:message key="index.role"/></th>
        <th><fmt:message key="index.blocked"/></th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.firstName}"/>
            </td>
            <td><c:out value="${user.lastName}"/>
            </td>
            <td><c:out value="${user.login}"/>
            </td>
            <td><c:out value="${user.instagram}"/>
            </td>
            <td><c:out value="${user.phoneNumber}"/>
            </td>
            <td><c:out value="${user.role}"/>
            </td>
            <td><c:out value="${user.isBlocked}"/>
            </td>
            <td><input type="button"
                       <c:if test="${user.isBlocked == false}">value="<fmt:message key="index.block"/>" onclick="window.location='home?command=blockUser&userId=${user.id}'"</c:if>
                       <c:if test="${user.isBlocked == true}">value="<fmt:message key="index.unblock"/>" onclick="window.location='home?command=unblockUser&userId=${user.id}'"</c:if>/>
            </td>
        </tr>
    </c:forEach>
    <br>
</table>
<nav>
    <ul class="pagination justify-content-center">
        <c:if test="${param.page-1 >= 1}">
            <li class="page-item"><a class="page-link"
                                     href="home?command=${param.command}&page=${param.page-1}"><fmt:message
                    key="index.previous"/></a></li>
        </c:if>

        <c:forEach var="page" items="${pages}">

            <li class="page-item"><a class="page-link" href="home?command=${param.command}&page=${page}">${page}</a>
            </li>

        </c:forEach>
        <c:set var="size" scope="page" value="${requestScope.pages}"/>

        <c:if test="${param.page+1 <= size.size()}">
            <li class="page-item"><a class="page-link"
                                     href="home?command=${param.command}&page=${param.page+1}"><fmt:message
                    key="index.next"/></a></li>
        </c:if>
    </ul>
</nav>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</div>
<br>
<h4 align="center"><my:today/></h4>
</body>
</html>
