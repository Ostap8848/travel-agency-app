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
<fmt:setBundle basename="messagesEng"/>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<body>
<h2 style="text-align: center"><fmt:message key="caption.allTours"/></h2>
<div align="center">
   <nav>
      <ul class="pagination justify-content-center">
         <li class="page-item"><a class="page-link" href="home?command=toursByHotelType&page=${param.page}"><fmt:message key="caption.byHotelType"/></a></li>
         <li class="page-item"><a class="page-link" href="home?command=toursByNumberOfPersons&page=${param.page}"><fmt:message key="caption.ByNumberOfPersons"/></a></li>
      </ul>
   </nav>
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
            <li class="page-item"><a class="page-link" href="home?command=${param.command}&page=${param.page-1}"><fmt:message key="caption.previous"/></a></li>
         </c:if>

         <c:forEach var="page" items="${pages}">

            <li class="page-item"><a class="page-link" href="home?command=${param.command}&page=${page}">${page}</a></li>

         </c:forEach>
         <c:set var="size" scope="page" value="${requestScope.pages}"/>

         <c:if test="${param.page+1 <= size.size()}">
            <li class="page-item"><a class="page-link" href="home?command=${param.command}&page=${param.page+1}"><fmt:message key="caption.next"/></a></li>
         </c:if>
      </ul>
   </nav>
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
   </div>
</body>
</html>
