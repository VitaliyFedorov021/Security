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
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<form action="controller/" method="post" class="row g-3 needs-validation" novalidate>
    <div class="form-group col-md-4">
        <label for="email1">Email</label>
        <input type="email" id="email1" placeholder="email@example.com" required class="form-control">

    </div>
    <div class="form-group col-md-4">
        <label for="pass1">Password:</label>
        <input type="password" class="form-control" name="password" placeholder="Enter your password" required id="pass1"> <br>
        <p class="lead">Password must have at least 1 capital latin letter, 1 number and consists of greater than 6 symbols</p>
    </div>
    <input type="hidden" name="command" value="WriteUser">
    <input type="submit" value="Send" class="btn btn-primary"> <input type="reset" value="Reset" class="btn btn-secondary">
</form>

</body>
</html>
