<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 04.08.2022
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>



</head>
<body>

<c:forEach var="tour" items="${requestScope.tours}">
    ${tour.getUkrName()} <br/>
</c:forEach>

</body>
</html>
