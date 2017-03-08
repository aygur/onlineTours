<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dmitrii
  Date: 23.02.17
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../template/head.jsp" %>
<%@ include file="../template/body-head.jsp" %>


<form action="/client/edit" method="post">
    <c:set var="client" value="${client}" />
    <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" class="form-control" name="lastName"  id="lastName" value="${client.lastName}" placeholder="Last Name">
    </div>
    <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" class="form-control" name="firstName" id="firstName" value="${client.firstName}"  placeholder="First Name">
    </div>
    <div class="form-group">
        <label for="phone">Phone:</label>
        <input type="text" class="form-control" name="phone" id="phone" value="${client.phone}"  placeholder="Phone">
    </div>
    <div class="form-group">
        <label for="doc">Doc:</label>
        <input type="text" class="form-control" name="doc" id="doc" value="${client.doc}"  placeholder="Doc">
    </div>
    <div class="form-group">
        <label for="birthDate">BirthDate:</label>
        <input type="text" class="form-control" name="birthDate" id="birthDate" value="${client.birthDate}"  placeholder="BirthDate">
    </div>
    <div class="form-group">
        <label for="address">Address:</label>
        <input type="text" class="form-control" name="address" id="address" value="${client.address}"  placeholder="Address">
    </div>
    <div class="form-group">
        <label for="gender">Gender:</label>
        <input type="text" class="form-control" name="gender" id="gender" value="${client.gender}"  placeholder="Gender">
    </div>
    <%--<div class="form-group">--%>
        <%--<label for="login">Login:</label>--%>
        <%--<input type="text" class="form-control" name="login" id="login" value="${client.login}"  placeholder="Login">--%>
    <%--</div>--%>
    <%--<div class="form-group">--%>
        <%--<label for="password">Password:</label>--%>
        <%--<input type="text" class="form-control" name="password" id="password" value="${client.password}"  placeholder="Password">--%>
    <%--</div>--%>
    <%--<div class="form-group">--%>
        <%--<label for="email">Email:</label>--%>
        <%--<input type="email" class="form-control" name="email" id="email" value="${client.email}"  placeholder="Email">--%>
    <%--</div>--%>
    <%--<div class="form-group">--%>
        <%--<label for="role">Role:</label>--%>
        <%--<input type="text" class="form-control" name="role" id="role" value="${client.role}"  placeholder="role">--%>
    <%--</div>--%>


    <input type="hidden" name="id" id="id" value="${client.idclient}" >
    <input type="submit" value="Submit">
</form>

<%@ include file="../template/body-footer.html" %>