<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<form:form action="/sign_up" method="POST" class="needs-validation" modelAttribute="customer">
    <spring:bind path="email">
    <div class="form-group col-md-3">
        <label for="email">Email</label>
        <form:input type="text" id="email" path="email" placeholder="email@example.com" class="form-control"></form:input>
        <form:errors path="email"></form:errors>
        <br>
    </div>
    </spring:bind>
    <spring:bind path="firstName">
    <div class="form-group col-md-3">
        <label for="firstName">First name</label>
        <form:input type="text" id="firstName" path="firstName" placeholder="First name" class="form-control"></form:input>
        <form:errors path="firstName"></form:errors>
        <br>
    </div>
    </spring:bind>
    <spring:bind path="lastName">
    <div class="form-group col-md-3">
        <label for="lastName">Last name</label>
        <form:input type="text" id="lastName" path="lastName" placeholder="Last name" class="form-control"></form:input>
        <form:errors path="lastName"></form:errors>
    </div>
    </spring:bind>
    <spring:bind path="birthdayDate">
    <div class="form-group col-md-2">
    <label for="birthdayDate">Birthday</label>
            <form:input type="date" id="birthdayDate" path="birthdayDate" class="form-control" max="2009-01-01"></form:input>
            <form:errors path="birthdayDate"></form:errors>
        </div>
    </spring:bind>
        <spring:bind path="phoneNumber">
        <div class="form-group col-md-3">
        <label for="phoneNumber">Phone number:</label>
            <form:input type="number" id="phoneNumber" path="phoneNumber" placeholder="099 123 45 67" class="form-control" maxlength="10"></form:input>
        <form:errors path="phoneNumber"></form:errors>
        </div>
        </spring:bind>
    <div class="col-12 col-md-4">
    <label for="gender" class="form-label">Gender</label>
    <select class="form-select" id="gender" name="gender" required>
        <option>MALE</option>
        <option>FEMALE</option>
        <option>OTHER</option>
    </select>
</div>
<br>
    <spring:bind path="password">
    <div class="col-12 col-md-2">
        <label for="password">Password</label>
        <form:input path="password" type="password" class="form-control" placeholder="Enter your password" id="password"></form:input> <br>
        <p class="lead">Password must have at least 1 capital latin letter, 1 number and consists of greater than 6 symbols</p>
    <form:errors path="password"></form:errors>
    </div>
    </spring:bind>
    <div class="col-12">
        <button class="btn btn-primary" type="submit">Sign up</button>
    </div>
</form:form>

</body>
</html>
