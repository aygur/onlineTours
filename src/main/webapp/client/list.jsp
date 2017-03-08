
<%--
  Created by IntelliJ IDEA.
  User: dmitrii
  Date: 23.02.17
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../template/head.jsp" %>
<%@ include file="../template/body-head.jsp" %>

<!-- Main component for a primary marketing message or call to action -->
<h3>Список клиентов</h3>
<table class="table table-hover">
    <thead>
    <td>Last name</td>
    <td>First name</td>
    <td>Phone</td>
    <td>Document</td>
    <td>Date of birth</td>
    <td>Address</td>
    <td>Gender</td>
    <td>Login</td>
    <td>Password</td>
    <td>Email</td>
    <td>Role</td>
    <td>Blocked</td>
    <td>Операции</td>
    </thead>
    <c:forEach items="${clients}" var="client">
        <tr>
            <td>${client.lastName}</td>
            <td>${client.firstName}</td>
            <td>${client.phone}</td>
            <td>${client.doc}</td>
            <td>${client.birthDate}</td>
            <td>${client.address}</td>
            <td>${client.gender}</td>
            <td>${client.login}</td>
            <td>${client.password}</td>
            <td>${client.email}</td>
            <td>${client.role}</td>
            <td>${client.blocked}</td>

            <td><a href="/client/edit?id=${client.idclient}">Edit</a>

                <form action="/client/block" method="post">
                <input type="hidden" name="id" id="id" value="${client.idclient}" >
                <input type="hidden" name="block" id="block" value="${client.blocked}" >
                <input type="submit" value="Block">
                </form>
            </td>

        </tr>
    </c:forEach>
</table>

<%@ include file="../template/body-footer.html" %>