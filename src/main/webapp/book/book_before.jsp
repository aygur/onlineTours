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

<h3>Подтверждение бронирование тура:</h3>

<table class="table table-hover">
    <thead>
    <td>Дата начала</td>
    <td>Дата окончания</td>
    <td>Тип тура</td>
    <td>Тип питания</td>
    <td>Стоимость</td>
    <td>Отель</td>
    <td>Расположение</td>
    <td>Забронирован</td>
    </thead>
    <tr>
        <td>${tourItem.dateStart}</td>
        <td>${tourItem.dateFinish}</td>
        <td>${tourItem.tur_type}</td>
        <td>${tourItem.menu_type}</td>
        <td>${tourItem.cost}</td>
        <td>${tourItem.hotel}</td>
        <td>${tourItem.city}</td>
        <td>${tourItem.booking}</td>

    </tr>
</table>
<c:choose>
    <c:when test="${tourItem.booking == 0}">
        <form action="/book_before" method="post">
            <input type="hidden" name="idtur" id="idtur" value="${tourItem.idtur}" >
            <input type="submit" value="Да, бронирою тур">
        </form>
    </c:when>
    <c:otherwise>
        <p>Данный тур уже забронирован</p>
    </c:otherwise>
</c:choose>


<%@ include file="../template/body-footer.html" %>