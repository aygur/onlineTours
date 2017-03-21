<%--
  Created by IntelliJ IDEA.
  User: dmitrii
  Date: 23.02.17
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<html>
<jsp:include page="/template/head.jsp" ></jsp:include>
<link href="/resources/signin.css" rel="stylesheet">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
<div class="container">
    <a href="/registration">Registration</a><br>
    <%--<p style="color: red">${requestScope.error}</p>--%>
    <c:url value="/j_spring_security_check" var="loginUrl"/>
    <form action="${loginUrl}" method="post" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="j_username" class="sr-only">Login</label>
        <input type="text" id="j_username" name="j_username" value="" class="form-control" placeholder="Login" required autofocus>
        <label for="j_password" class="sr-only">Password</label>
        <input type="password" id="j_password" name="j_password" value="" class="form-control" placeholder="Password" required>
        <div class="checkbox">
                <input id="remember_me" name="_spring_security_remember_me"
                       type="checkbox"/>
                <label for="remember_me" class="inline">Remember me</label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

</div> <!-- /container -->

</body>
</html>