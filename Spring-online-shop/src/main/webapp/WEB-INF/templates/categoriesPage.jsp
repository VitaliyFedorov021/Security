<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 25.01.2021
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Categories</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <table class="table table-bordered">
        <tr>
            <td>Name</td>
            <td>Quantity of products</td>
        </tr>
        <c:forEach items="${categories}" var="c">
            <tr>
                <td><a href="/products/${c.code}">${c.name}</a></td>
                <td>${c.quantityOfProducts}</td>
            </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
