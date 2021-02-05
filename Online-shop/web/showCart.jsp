<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 01.02.2021
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<h1>Your order:</h1>
<table class="table table-bordered">
    <tr>
        <td>Code</td>
        <td>Name</td>
        <td>Product price</td>
        <td>Quantity</td>
        <td>Price</td>
        <td></td>
    </tr>
    <c:forEach var="cartEntry" items="${cart.cartEntries}">
        <tr>
            <td>${cartEntry.product.code}</td>
            <td>${cartEntry.product.name}</td>
            <td>${cartEntry.product.price}</td>
            <td>
                <form method="post" action="/quantity?command=ChangeQuantity">
                    <input type="number" id="quantity" name="quantity" min="1" max="15" value="${cartEntry.quantity}">
                    <input type="hidden" name="code" value="${cartEntry.product.code}">
                    <input type="submit" class="btn btn-info" value="change">
                </form>
            </td>
            <td>${cartEntry.totalPrice}</td>
            <td>
                <form action="/delete?command=Delete" method="post">
                    <input type="hidden" name="code" value="${cartEntry.product.code}">
                    <input type="submit" class="btn btn-delete" value="delete">
                </form>
            </td>
        </tr>
    </c:forEach>
    <div>
        <h3 class="display-3">Total price: ${cart.totalPrice}</h3>
    </div>
</table>
</body>
</html>
