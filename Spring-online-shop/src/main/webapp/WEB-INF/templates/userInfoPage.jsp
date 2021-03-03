<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 02.03.2021
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Info</title>
</head>
<body>
    <div>
        <h3>Email: <security:authentication property="principal.customer.email"/></h3>
        <h3>First name: <security:authentication property="principal.customer.firstName"/></h3>
        <h3>Last name: <security:authentication property="principal.customer.lastName"/></h3>
        <h3>Phone number: <security:authentication property="principal.customer.phoneNumber"/></h3>
        <h3>Birthday date: <security:authentication property="principal.customer.birthdayDate"/></h3>
    </div>
    <div>
        <c:if test="${carts.size() == 0}">
            <c:out value="You have no previous orders"/>
        </c:if>
        <c:if test="${carts.size() != 0}">
        <h1>Previous orders</h1>
        <c:forEach items="${carts}" var="c">
            <div><h2>${c.placedDate}</h2></div>
        <table class="table table-bordered">
            <tr>
                <td>Code</td>
                <td>Name</td>
                <td>Product price</td>
                <td>Quantity</td>
                <td>Price</td>
            </tr>
                <c:forEach items="${c.cartEntries}" var="cartEntry">
                    <tr>
                        <td>${cartEntry.product.code}</td>
                        <td>${cartEntry.product.name}</td>
                        <td>${cartEntry.product.price}</td>
                        <td>${cartEntry.quantity}</td>
                        <td>${cartEntry.totalPrice}</td>
                    </tr>
                </c:forEach>
            <div>
                <h3 class="display-3">Total price: ${c.totalPrice}</h3>
            </div>
        </table>
        </c:forEach>
        </c:if>
    </div>
</body>
</html>
