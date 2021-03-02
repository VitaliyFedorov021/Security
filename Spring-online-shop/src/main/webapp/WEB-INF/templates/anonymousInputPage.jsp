<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 01.03.2021
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<security:authorize access="!isAuthenticated()">
<html>
<head>
    <title>Personal data</title>
</head>
<body>
<form:form action="/personal_data" method="POST" class="needs-validation" modelAttribute="customer">
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
    <spring:bind path="phoneNumber">
        <div class="form-group col-md-3">
            <label for="phoneNumber">Phone number:</label>
            <form:input type="number" id="phoneNumber" path="phoneNumber" placeholder="099 123 45 67" class="form-control" maxlength="10"></form:input>
            <form:errors path="phoneNumber"></form:errors>
        </div>
    </spring:bind>

    <button type="submit" class="btn btn-primary">Submit</button>
</form:form>

</body>
</html>
</security:authorize>