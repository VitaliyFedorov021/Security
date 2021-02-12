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
</head>
<body>
<c:if test="${customer == null}">
    <h1 class="display-1">Welcome to our shop!</h1>   <div><a href="/login/page?command=LoginPage"><button type="button" class="btn btn-primary">Sign in</button></a></div>
</c:if>
<c:if test="${customer != null}">
    <h1 class="display-1">Welcome to our shop, ${customer.firstName} ${customer.lastName}!</h1>
    <div><a href="/cart?command=CartPage"><button type="button" class="btn btn-primary">Show cart</button></a></div>
    <div>
        <a href="/logout?command=Logout"><button type="button" class="btn btn-primary">Logout</button></a>
    </div>
</c:if>
<div>
    <a href="/categories?command=ShowCategories"><button type="button" class="btn btn-primary">Show categories</button></a>
</div>
</body>
</html>
