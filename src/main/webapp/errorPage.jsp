<%--
  Created by IntelliJ IDEA.
  User: Ostap
  Date: 23.08.2022
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>

<head>
    <title>Command exception</title>
</head>

<body style="background-image: url('${pageContext.request.contextPath}/pics/command_exception.jpg')">
<p style="background: none; font-weight: bold; text-align: center">
    Request from ${pageContext.errorData.requestURI} is failed <br/>
    Status code: ${pageContext.errorData.statusCode} <br/>
    Exception: ${pageContext.errorData.throwable} <br/>
    Exception Message: ${pageContext.exception.message} <br/>
</p>
</body>

</html>
