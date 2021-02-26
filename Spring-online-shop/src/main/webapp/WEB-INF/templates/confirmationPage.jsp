<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 08.02.2021
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order confirmation</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <h1 class="header">Your order was successful! Thank you for shopping with us.</h1>
    <h3>${cart.code}</h3>
    <h3>${cart.placedDate}</h3>
    <h3>${cart.deliveryAddress.country}</h3>
    <h3>${cart.deliveryAddress.town}</h3>
    <h3>${cart.deliveryAddress.region}</h3>
    <h3>${cart.deliveryAddress.street}</h3>
    </div>
    <h1>Your order:</h1>
    <table class="table table-bordered" align="right">
        <tr>
            <td>Code</td>
            <td>Name</td>
        </tr>
        <c:forEach var="cartEntry" items="${cart.cartEntries}">
            <tr>
                <td>${cartEntry.product.code}</td>
                <td>${cartEntry.product.name}</td>
            </tr>
        </c:forEach>
        <div>
            <p align="right" class="display-3">Total price: ${cart.totalPrice}</p>
        </div>
    </table>

</body>
</html>
