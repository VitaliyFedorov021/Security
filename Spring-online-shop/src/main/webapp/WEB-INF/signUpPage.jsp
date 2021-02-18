<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 15.01.2021
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div>
    <a href="/"><button type="button" class="btn btn-primary">HOME</button></a>
</div>
<form action="/sign_up" method="POST" name="customer" class="needs-validation" novalidate>
    <div class="form-group col-md-3">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="email@example.com" required class="form-control">
        <br>
    </div>

    <div class="form-group col-md-3">
        <label for="firstName">First name</label>
        <input type="text" id="firstName" name="firstName" placeholder="First name" required class="form-control">
        <br>
    </div>
    <div class="form-group col-md-3">
        <label for="lastName">Last name</label>
        <input type="text" id="lastName" name="lastName" placeholder="Last name" required class="form-control">
    </div>
    <div class="col-12 col-md-4">
        <label for="gender" class="form-label">Gender</label>
        <select class="form-select" id="gender" name="gender" required>
            <option>Male</option>
            <option>Female</option>
            <option>Other</option>
        </select>
    </div>
    <div class="form-group col-md-2">
        <label for="birthdayDate">Birthday</label>
        <input type="date" id="birthdayDate" name="birthdayDate" required class="form-control" max="2009-01-01">
    </div>
    <div class="form-group col-md-3">
        <label for="phoneNumber">Phone number:</label>
        <input type="number" id="phoneNumber" name="phoneNumber" placeholder="099 123 45 67" required class="form-control" maxlength="10">
    </div>
    <br>
    <div class="col-12 col-md-2">
        <label for="password">Password</label>
        <input type="password" class="form-control" name="password" placeholder="Enter your password" required id="password"> <br>
        <p class="lead">Password must have at least 1 capital latin letter, 1 number and consists of greater than 6 symbols</p>
    </div>
    <div class="col-12">
        <c:forEach var="e" items="${message}">
            <h1 class="text-danger">${e}</h1>
        </c:forEach>
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit">Sign up</button>
    </div>
</form>

</body>
</html>
