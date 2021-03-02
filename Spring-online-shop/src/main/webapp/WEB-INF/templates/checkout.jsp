<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 08.02.2021
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Confirm order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <div>
        <h4>Name: ${customer.firstName}
                  ${customer.lastName}</h4>
        <h4>Phone: ${customer.phoneNumber}</h4>
    </div>
    <h1>Your order:</h1>
    <table class="table table-bordered" align="right">
        <tr>
            <td>Code</td>
            <td>Name</td>
            <td>Product price</td>
            <td>Quantity</td>
            <td>Price</td>
        </tr>
        <c:forEach var="cartEntry" items="${cart.cartEntries}">
            <tr>
                <td>${cartEntry.product.code}</td>
                <td>${cartEntry.product.name}</td>
                <td>${cartEntry.product.price}</td>
                <td>${cartEntry.quantity}</td>
                <td>${cartEntry.totalPrice}</td>
            </tr>
        </c:forEach>
        <div>
            <p align="right" class="display-3">Total price: ${cart.totalPrice}</p>
        </div>
    </table>
    <h2>Enter your address to place order</h2>
    <form:form action="/confirm_order" method="post" class="needs-validation" modelAttribute="address">
        <spring:bind path="street">
        <div class="form-group col-md-3">
            <label for="street">Street</label>
            <form:input type="text" id="street" path="street" placeholder="my-street.st" class="form-control"></form:input>
            <form:errors path="street"></form:errors>
            <br>
        </div>
        </spring:bind>
        <spring:bind path="postalCode">
        <div class="form-group col-md-2">
            <label for="postalCode">Postal code</label>
            <form:input type="text" id="postalCode" path="postalCode" placeholder="12345" maxlength="5" class="form-control"></form:input>
            <form:errors path="postalCode"></form:errors>
            <br>
        </div>
        </spring:bind>
        <spring:bind path="country">
        <div class="form-group col-md-3">
            <label for="country">Country</label>
            <form:input type="text" id="country" path="country" class="form-control"></form:input>
            <form:errors path="country"></form:errors>
        </div>
        </spring:bind>
        <spring:bind path="town">
        <div class="form-group col-md-3">
            <label for="town">Town</label>
            <form:input type="text" id="town" path="town" placeholder="Kharkiv" class="form-control"></form:input>
            <form:errors path="town"></form:errors>
            <br>
        </div>
        </spring:bind>
        <spring:bind path="region">
        <div class="form-group col-md-3">
            <label for="region">Region</label>
            <form:input type="text" id="region" path="region" placeholder="Kharkiv" class="form-control"></form:input>
            <form:errors path="region"></form:errors>
            <br>
        </div>
        </spring:bind>
            <button type="submit" class="btn btn-primary">Confirm</button>
    </form:form>

</body>
</html>
