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
    <title>Error 403</title>
</head>
<body>
<center><h1>Error 403</h1></center>
<br>
<p>
<center><h1>Доступ запрещен</h1></center>
</p>
<p>${exception}</p>
<p>
    ${url}
</p>
<br>
<a href="/dashboard">На главную</a>
</body>
</html>
