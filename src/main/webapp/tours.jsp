<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 11.08.2022
  Time: 16:44
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
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<body>
<h2 style="text-align: center"><a href="home?command=allTours"><fmt:message key="index.allTours"/></a></h2>

<div align="center">
    <nav>
        <i><fmt:message key="index.chooseBy"/>:</i>
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <form action="home" method="get">
                    <input hidden name="command" value="toursByHotelType">
                    <input type="hidden" name="page" value="${param.page}">
                    <%--<input hidden name="page" value="${param.page}">--%>
                    <select name="hotelType">
                        <option disabled><fmt:message key="index.byHotelType"/></option>
                        <option value="FIVE_STAR"><fmt:message key="index.fiveStar"/></option>
                        <option value="FOUR_STAR"><fmt:message key="index.fourStar"/></option>
                        <option value="THREE_STAR"><fmt:message key="index.threeStar"/></option>
                        <option value="TWO_STAR"><fmt:message key="index.twoStar"/></option>
                        <option value="ONE_STAR"><fmt:message key="index.oneStar"/></option>
                    </select>
                    <p><input type="submit" value="<fmt:message key="index.search"/>"/></p>
                </form>
            </li>
            <li class="page-item"><a class="page-link"
                                     href="home?command=toursByNumberOfPersons&page=${param.page}"><fmt:message
                    key="index.byNumberOfPersons"/></a></li>
            <li class="page-item"><a class="page-link" href="home?command=toursByPrice&page=${param.page}"><fmt:message
                    key="index.byPrice"/></a></li>
            <li class="page-item">
                <form action="home" method="get">
                    <input hidden name="command" value="toursByType">
                    <input type="hidden" name="page" value="${param.page}">
                    <select name="tourType">
                        <option disabled><fmt:message key="index.byType"/></option>
                        <option value="REST"><fmt:message key="index.rest"/></option>
                        <option value="SHOPPING"><fmt:message key="index.shopping"/></option>
                        <option value="EXCURSION"><fmt:message key="index.excursion"/></option>
                    </select>
                    <p><input type="submit" value="<fmt:message key="index.search"/>" <%--onclick="window.location='home?command=toursByType&tourType=${tourType}&page=${param.page}'"--%>/></p>
                </form>
            </li>
        </ul>
    </nav>
    <b><fmt:message key="index.showMe"/><a href="home?command=hotTours"><fmt:message key="index.hotTours"/></a></b>
    <table border="2" cellpadding="5">
        <tr>
            <th><fmt:message key="index.name"/></th>
            <th><fmt:message key="index.byType"/></th>
            <th><fmt:message key="index.priceUah"/></th>
            <th><fmt:message key="index.maxPersons"/></th>
            <th><fmt:message key="index.byHotelType"/></th>
            <th><fmt:message key="index.hot"/></th>
            <th><fmt:message key="index.possibleDiscount"/></th>
            <th><fmt:message key="index.dates"/></th>
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
                <td><c:out value="${tour.tourType}"/>
                </td>
                <td><c:out value="${tour.price}"/>
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
                <td><input type="button" value="<fmt:message key="index.book"/>" onclick="window.location='home?command=addTourIntoOrder&tourId=${tour.id}&userId=${user.id}'"></td>
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
</body>
</html>
