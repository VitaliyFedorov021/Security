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
</head>
<body>
<form method="post" action="/login">
    <div class="form-group col-md-4">
        <label for="username">Login(email):</label>
        <input type="text" id="username" name="username" class="form-control" placeholder="test@example.com" required><br>
    </div>
    <div class="form-group col-md-4">
        <label for="password">Password:</label>
        <input type="password" class="form-control" name="password" placeholder="Enter your password" id="password" required> <br>
    </div>
    <br><h3 class="text-danger">${message}</h3><br>
    <button type="submit" class="btn btn-primary">Sign in</button>
</form>

<div>
    <p class="lead">
        Have no account?
    </p>
    <a href="/sign_up"><button type="button" class="btn btn-primary">Create account</button></a>
</div>

</body>
</html>
