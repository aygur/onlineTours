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
    <td>Помечен на удаление</td>
    <td>Операции</td>
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
            <td>${tourItem.deleted}</td>
            <c:if test="${sessionScope.get(\"role\") == \"admin\"}">
                <td><a href="/tour/edit?id=${tourItem.idtur}">Edit</a>
                    <form action="/tour/delete" method="post">
                        <input type="hidden" name="idtur" id="idtur" value="${tourItem.idtur}" >
                        <input type="hidden" name="deleted" id="deleted" value="${tourItem.deleted}" >
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </c:if>
        </tr>
</table>

<%@ include file="../template/body-footer.html" %>