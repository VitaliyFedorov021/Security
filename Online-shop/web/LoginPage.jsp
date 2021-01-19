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
<form action="/controller/" method="post">
    <div class="form-group col-md-4">
        <label for="email1">Login(email):</label>
        <input type="text" id="email1" name="email" class="form-control" placeholder="test@example.com" required><br>
    </div>
    <div class="form-group col-md-4">
        <label for="pass1">Password:</label>
        <input type="password" class="form-control" name="password" placeholder="Enter your password" required id="pass1"> <br>
    </div>
    <br>${msg}
    <br><br>
    <input type="submit" value="Send" class="btn btn-primary"> <input type="reset" value="Reset" class="btn btn-secondary">
    <input type="hidden" name="command" value="LOGIN">
</form>

<%--<div>--%>
<%--    <p class="lead">--%>
<%--        Have no account?--%>
<%--    </p>--%>
<%--    <a href="controller/signup?command=SignUpPage"><button type="button" class="btn btn-primary">Create account</button></a>--%>
<%--</div>--%>

</body>
</html>
