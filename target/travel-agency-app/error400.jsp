<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 29.08.2022
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>

<head>
    <title>Error page 404</title>
</head>

<body style="background-image: url('${pageContext.request.contextPath}/pics/error_404.jpg')">
<p style="background: white; font-weight: bold; text-align: center">
    Request from ${pageContext.errorData.requestURI} is failed <br/>
    Servlet name or type ${pageContext.errorData.servletName} <br/>
    Status code: ${pageContext.errorData.statusCode} <br/>
    Exception: ${pageContext.errorData.throwable} <br/>
    Exception Message: ${pageContext.exception.message} <br/>
</p>
</body>

</html>
