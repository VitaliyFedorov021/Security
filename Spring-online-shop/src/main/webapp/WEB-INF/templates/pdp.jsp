<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 26.01.2021
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        Product ${product.code}
    </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
<script>
    <%@include file="../addToCart.js"%>
</script>
    <h2 class="display-2">${product.name}</h2>
    <h1 class="display-1">Description:</h1>
    <p>${product.description}</p>
    <br>
    <h1>Price:</h1>
    <h3 class="display-3">${product.price}$</h3>
    <sec:authorize access="isAuthenticated()">
        <button type="button" value="${product.code}" id="productCode" name="productCode" class="btn btn-cart">Add to cart</button>
        <div class="form-group col-md-1">
            <label for="quantity">Quantity</label>
            <input type="number" id="quantity" name="quantity" min="1" max="15" value="1">
        </div>
    </sec:authorize>
</body>
</html>
