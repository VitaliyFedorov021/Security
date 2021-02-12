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
<form action="/sign_up?command=SignUp" method="post" class="needs-validation" novalidate>
    <div class="form-group col-md-3">
        <label for="email1">Email</label>
        <input type="email" id="email1" name="email" placeholder="email@example.com" required class="form-control">
        <br>
    </div>

    <div class="form-group col-md-3">
        <label for="first_name">First name</label>
        <input type="text" id="first_name" name="first_name" placeholder="First name" required class="form-control">
        <br>
    </div>
    <div class="form-group col-md-3">
        <label for="last_name">Last name</label>
        <input type="text" id="last_name" name="last_name" placeholder="Last name" required class="form-control">
    </div>
    <div class="col-12 col-md-4">
        <label for="Gender" class="form-label">Gender</label>
        <select class="form-select" id="Gender" name="gender" required>
            <option>Male</option>
            <option>Female</option>
            <option>Other</option>
        </select>
    </div>
    <div class="form-group col-md-2">
        <label for="date">Birthday</label>
        <input type="date" id="date" name="birthday_date" required class="form-control" max="2009-01-01">
    </div>
    <div class="form-group col-md-3">
        <label for="number">Phone number:</label>
        <input type="number" id="number" name="phone_number" placeholder="099 123 45 67" required class="form-control" maxlength="10">
    </div>
    <input type="hidden" name="command" value="SignUpCommand">
    <br>
    <div class="col-12 col-md-2">
        <label for="pass1">Password</label>
        <input type="password" class="form-control" name="password" placeholder="Enter your password" required id="pass1"> <br>
        <p class="lead">Password must have at least 1 capital latin letter, 1 number and consists of greater than 6 symbols</p>
    </div>
    <div class="col-12">
        <c:forEach var="e" items="${message}">
            <h1 class="text-danger">${e.message}</h1>
        </c:forEach>
    </div>

    <div class="col-12">
        <button class="btn btn-primary" type="submit">Sign up</button>
    </div>
</form>

</body>
</html>
