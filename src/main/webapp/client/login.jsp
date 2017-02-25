<%--
  Created by IntelliJ IDEA.
  User: dmitrii
  Date: 23.02.17
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="/template/head.jsp" ></jsp:include>
<link href="${pageContext.request.contextPath}/statics/signin.css" rel="stylesheet">


<body>
<div class="container">
    <a href="/registration">Registration</a><br>
    <p style="color: red">${requestScope.error}</p>
    <form action="${pageContext.request.contextPath}/login" method="post" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="login" class="sr-only">Login</label>
        <input type="text" id="login" name="login" value="" class="form-control" placeholder="Login" required autofocus>
        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="password" value="" class="form-control" placeholder="Password" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

</div> <!-- /container -->

</body>
</html>