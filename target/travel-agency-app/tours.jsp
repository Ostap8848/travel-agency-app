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

    <input type="button" onclick="location.href='home?command=setLocale&locale=ukr';" value="Ua"/>
    <input type="button" onclick="location.href='home?command=setLocale&locale';" value="Eng"/>
    <p align="right">
        <input type="button" onclick="location.href='home?command=logout';" value="<fmt:message key="index.logout"/>"/>
    </p>
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
                    <select name="hotelType">
                        <option disabled><fmt:message key="index.byHotelType"/></option>
                        <option value="FIVE_STAR">FIVE_STAR</option>
                        <option value="FOUR_STAR">FOUR_STAR</option>
                        <option value="THREE_STAR">THREE_STAR</option>
                        <option value="TWO_STAR">TWO_STAR</option>
                        <option value="ONE_STAR">ONE_STAR</option>
                    </select>
                    <p><input type="submit" value="<fmt:message key="index.search"/>"/></p>
                </form>
            </li>
            <li class="page-item"><a class="page-link"
                                     href="home?command=toursByNumberOfPersons&page=${param.page}"><fmt:message
                    key="index.ByNumberOfPersons"/></a></li>
            <li class="page-item"><a class="page-link" href="home?command=toursByPrice&page=${param.page}"><fmt:message
                    key="index.byPrice"/></a></li>
            <li class="page-item">
                <form action="home" method="get">
                    <input hidden name="command" value="toursByType">
                    <select name="tourType">
                        <option disabled><fmt:message key="index.byType"/></option>
                        <option value="REST">REST</option>
                        <option value="SHOPPING">SHOPPING</option>
                        <option value="EXCURSION">EXCURSION</option>
                    </select>
                    <p><input type="submit" value="<fmt:message key="index.search"/>"/></p>
                </form>
            </li>
        </ul>
    </nav>
    <b><fmt:message key="index.showMe"/><a href="home?command=hotTours"><fmt:message key="index.hotTours"/></a></b>
    <table border="2" cellpadding="5">
        <tr>
            <th>UKR name</th>
            <th>ENG name</th>
            <th>Type</th>
            <th>Price, UAH</th>
            <th>Max Persons</th>
            <th>Hotel Type</th>
            <th>HOT</th>
            <th>Possible Discount, %</th>
            <th>Description</th>
        </tr>
        <c:forEach var="tour" items="${tours}">
            <tr>
                <td><c:out value="${tour.nameUkr}"/>
                </td>
                <td><c:out value="${tour.nameEng}"/>
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
                <td><input type="button" value="Select" onclick="window.location='makeOrder.jsp'"></td>
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
