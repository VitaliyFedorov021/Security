<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 26.01.2021
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
    <script>
        <%@include file="addToCart.js"%>
    </script>
</head>
<body>
<div>
    <a href="/">
        <button type="button" class="btn btn-primary">HOME</button>
    </a>
</div>
<table class="table table-bordered">
    <tr>
        <td>Code</td>
        <td>Name</td>
        <td>Price</td>
    </tr>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.code}</td>
            <td><a href="/product/${p.code}">${p.name}</a></td>
            <td>${p.price}<br>
                <sec:authorize access="isAuthenticated()">
                    <div>
                        <button type="button" value="${p.code}" id="productCode" name="productCode" class="btn btn-cart">Add to cart</button>
                        <input type="hidden" id="quantity" name="quantity" value="1">
                    </div>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
