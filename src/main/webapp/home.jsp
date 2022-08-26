<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 10.08.2022
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%--<%@ page isELIgnored="false" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Home page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS (jsDelivr CDN) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- Bootstrap Bundle JS (jsDelivr CDN) -->
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
<%--<jsp:include page="header/header.jspf"/>--%>
<div class="container-fluid p-4 my-0 bg-light text-info">
    <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <div class="col-md-1 text-end">
                <a href = "home?command=setLocale&locale=ukr&pageToTranslate=${param.command}" role="button"
                   class="btn btn-outline-primary me-2">Ua</a>
                <a href = "home?command=setLocale&locale&pageToTranslate=${param.command}" role="button"
                   class="btn btn-outline-primary me-2">Eng</a>
        </div>
        <div class="col-md-6 text-end">
            <h4 align="center">Travel Agency</h4>
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


<h4 align="center"><fmt:message key="index.description"/></h4>
<h4 align="center"><fmt:message key="index.openWorld"/> <a href="home?command=allTours"><fmt:message key="index.rightNow"/></a> </h4>
<br>
<h4 align="center"><b>Instagram</b></h4>
<h4 align="center">Travel_with_Us</h4>
<br>
<h4 align="center"><b>E-mail</b></h4>
<h4 align="center">tour_tour_stryy@gmail.com</h4>
<br>
<h4 align="center"><b><fmt:message key="index.phoneNumber"/></b></h4>
<h4 align="center">+380671113007</h4>
<br>
<h4 align="center"><b><fmt:message key="index.address"/></b></h4>
<h4 align="center"><fmt:message key="index.exactAddress"/></h4>

</body>
</html>
