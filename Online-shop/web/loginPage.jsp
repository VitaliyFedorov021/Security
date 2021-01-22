<%--
  Created by IntelliJ IDEA.
  User: vitalij
  Date: 15.01.2021
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div>
    <a href="/"><button type="button" class="btn btn-primary">HOME</button></a>
</div>
<form action="/login?command=Login" method="post">
    <div class="form-group col-md-4">
        <label for="email1">Login(email):</label>
        <input type="text" id="email1" name="email" class="form-control" placeholder="test@example.com" required><br>
    </div>
    <div class="form-group col-md-4">
        <label for="pass1">Password:</label>
        <input type="password" class="form-control" name="password" placeholder="Enter your password" id="pass1" required> <br>
    </div>
    <br>${message}<br>
    <input type="submit" value="login" class="btn btn-primary">
</form>

<div>
    <p class="lead">
        Have no account?
    </p>
    <a href="/signup?command=SignUpPage"><button type="button" class="btn btn-primary">Create account</button></a>
</div>

</body>
</html>
