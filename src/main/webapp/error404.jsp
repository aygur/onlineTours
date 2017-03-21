<%--
  Created by IntelliJ IDEA.
  User: dmitrii
  Date: 23.02.17
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 404</title>
</head>
<body>
<center><h1>Error 404</h1></center>
<br>
<p>
    <h1>Тут вам не здесь!!!</h1>
</p>
<p>${exception}</p>
<p>
    ${url}
</p>
<br>
<a href="/dashboard">На главную</a>
</body>
</html>
