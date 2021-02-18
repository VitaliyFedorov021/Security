<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 01.02.2021
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div>
    <a href="/"><button type="button" class="btn btn-primary">HOME</button></a>
</div>
<c:choose>
    <c:when test="${cart.cartEntries.size() == 0}">
        <h3>Your cart is empty, add products here</h3><br>
        <a href="/categories?command=ShowCategories">Show categories</a>
    </c:when>
    <c:otherwise>
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
                        <form method="post" action="/change_quantity">
                            <input type="number" id="quantity" name="quantity" min="1" max="15" value="${cartEntry.quantity}">
                            <input type="hidden" name="productCode" value="${cartEntry.product.code}">
                            <input type="submit" class="btn btn-info" value="change">
                        </form>
                    </td>
                    <td>${cartEntry.totalPrice}</td>
                    <td>
                        <form action="/delete_product" method="post">
                            <input type="hidden" name="productCode" value="${cartEntry.product.code}">
                            <input type="submit" class="btn btn-delete" value="delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <div>
                <h3 class="display-3">Total price: ${cart.totalPrice}</h3>
            </div>
        </table>
        <a href="/place_order"><button class="btn btn-primary" type="button">Place order</button></a>
    </c:otherwise>
</c:choose>
</body>
</html>
