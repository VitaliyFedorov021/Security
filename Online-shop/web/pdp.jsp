<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 26.01.2021
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Product ${product.code}
    </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div>
    <a href="/">
        <button type="button" class="btn btn-primary">HOME</button>
    </a>
</div>
    <h2 class="display-2">${product.name}</h2>
    <h1 class="display-1">Description:</h1>
    <p>${product.description}</p>
    <br>
    <h1>Price:</h1>
    <h3 class="display-3">${product.price}$</h3>
</body>
</html>
