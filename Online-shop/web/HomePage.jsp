<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 15.01.2021
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome!</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div>
    <a href="controller/?command=HOME_PAGE"><button type="button" class="btn btn-primary">HOME</button></a>
</div>
<c:if test="${customer == null}">
    <h1 class="display-1">Welcome to our shop!</h1>   <div><a href="controller/login?command=LOGIN_PAGE"><button type="button" class="btn btn-primary">Sign in</button></a></div>
</c:if>
<c:if test="${customer != null}">
    <h1 class="display-1">Welcome to our shop!</h1>   <div><a href="controller/logout?command=LOGOUT"><button type="button" class="btn btn-primary">Log out</button></a></div>
</c:if>
</body>
</html>
