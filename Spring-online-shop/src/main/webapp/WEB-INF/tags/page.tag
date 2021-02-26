<%@ taglib prefix="cus" tagdir="/WEB-INF/tags"%>
<%@ attribute name="page" required="true" %>
<html>
    <body>
    <div>
        <cus:header/>
    </div>
        <cus:master page="${page}"/>
    <br>
    <div>
        <cus:footer/>
    </div>
    </body>
</html>