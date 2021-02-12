<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 08.02.2021
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Confirm order</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
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
    <form action="/confirmorder?command=SaveDeliveryAddress" method="post">
        <div class="form-group col-md-3">
            <label for="street">Street</label>
            <input type="text" id="street" name="street" placeholder="my-street.st" required class="form-control">
            <br>
        </div>

        <div class="form-group col-md-2">
            <label for="postalcode">Postal code</label>
            <input type="text" id="postalcode" name="postalCode" placeholder="12345" maxlength="5" required class="form-control">
            <br>
        </div>

        <div class="form-group col-md-3">
            <label for="country">Country</label>
            <input type="text" id="country" name="country" required class="form-control">
        </div>

        <div class="form-group col-md-3">
            <label for="town">Town</label>
            <input type="text" id="town" name="town" placeholder="Kharkiv" required class="form-control">
            <br>
        </div>

        <div class="form-group col-md-3">
            <label for="region">Region</label>
            <input type="text" id="region" name="region" placeholder="Kharkiv" required class="form-control">
            <br>
        </div>
        <input type="submit" class="btn btn-primary" value="Submit">
    </form>

</body>
</html>
