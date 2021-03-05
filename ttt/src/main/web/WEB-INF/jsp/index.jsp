<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 05.03.21
  Time: 09:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Test</title>
  </head>

  <body>
  <c:forEach items="${list}" var="c">
      <h1>${c.email}</h1>
      <h1>${c.firstName}</h1>
  </c:forEach>
  </body>
</html>
