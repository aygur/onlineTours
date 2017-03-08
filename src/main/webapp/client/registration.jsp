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
<link href="/resources/signin.css" rel="stylesheet">

<body>
<div class="container">
    <a href="${pageContext.request.contextPath}/login">Login</a><br>
    <p style="color: red">${requestScope.error}</p>
    <form action="${pageContext.request.contextPath}/registration" method="post" class="form-signin">
        <h2 class="form-signin-heading">Registration</h2>
        <label for="login" class="sr-only">Login</label>
        <input type="text" id="login" name="login" value="" class="form-control" placeholder="Login" required autofocus>
        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="password" value="" class="form-control" placeholder="Password" required>
        <label for="email" class="sr-only">Email</label>
        <input type="email" id="email" name="email" value="" class="form-control" placeholder="Email" required>
        <label for="lastName" class="sr-only">Last Name</label>
        <input type="text" id="lastName" name="lastName" value="" class="form-control" placeholder="Last Name" required>

        <label for="firstName" class="sr-only">First Name</label>
        <input type="text" id="firstName" name="firstName" value="" class="form-control" placeholder="First Name" required>
        <label for="phone" class="sr-only">Phone</label>
        <input type="text" id="phone" name="phone" value="" class="form-control" placeholder="Phone" required>
        <label for="doc" class="sr-only">Doc</label>
        <input type="text" id="doc" name="doc" value="" class="form-control" placeholder="Doc" required>
        <label for="birthDate" class="sr-only">BirthDate</label>
        <input type="date" id="birthDate" name="birthDate" value="" class="form-control" placeholder="BirthDate" required>

        <label for="address" class="sr-only">Address</label>
        <input type="text" id="address" name="address" value="" class="form-control" placeholder="Address" required>
        <label for="gender" class="sr-only">Gender</label>
        <select name="gender" id="gender" class="form-control">
            <option value="m" selected>Man</option>
            <option value="w">Woman</option>
        </select>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

</div> <!-- /container -->

</body>
</html>