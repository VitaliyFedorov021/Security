<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div>
        <a class="navbar-brand" href="/">
            <img src="http://cdn.onlinewebfonts.com/svg/img_211558.png"
                 height="50" width="50"
            />
        </a>
    </div>
    <sec:authorize access="!isAuthenticated()">
        <a class="nav-link" href="/login">Sign in</a>
        <a class="nav-link" href="/categories">Categories</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a class="nav-link" href="/cart">
            <img src="https://cdn.iconscout.com/icon/free/png-256/shopping-cart-452-1163339.png"
                 height="50" width="50"
            />
        </a>
        <a class="nav-link" href="/categories">Categories</a>
        <a class="nav-link" href="/logout">Logout</a>
        <sec:authentication property="principal.username"/>
    </sec:authorize>

</nav>
</body>
</html>