<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 15.01.2021
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Welcome!</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<sec:authorize access="!isAuthenticated()">
    <h1 class="display-1">Welcome to our shop!</h1>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <h1 class="display-1">Welcome to our shop, <sec:authentication property="principal.customer.firstName"/>
        <sec:authentication property="principal.customer.lastName"/>!
    </h1>
</sec:authorize>
</body>
</html>
