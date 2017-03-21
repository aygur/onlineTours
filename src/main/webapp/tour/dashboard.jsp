<%--
  Created by IntelliJ IDEA.
  User: dmitrii
  Date: 25.02.17
  Time: 0:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../template/head.jsp" %>
<%@ include file="../template/body-head.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-7">

    <table class="table table-hover">
        <thead>
        <td>Дата начала</td>
        <td>Дата окончания</td>
        <td>Тип тура</td>
        <td>Тип питания</td>
        <td>Стоимость</td>
        <td>Отель</td>
        <td>Расположение</td>
        <td>Операции</td>
        </thead>
        <c:forEach items="${tourList}" var="tourItem">
            <tr>
                <td>${tourItem.dateStart}</td>
                <td>${tourItem.dateFinish}</td>
                <td>${tourItem.tur_type}</td>
                <td>${tourItem.menu_type}</td>
                <td>${tourItem.cost}</td>
                <td>${tourItem.hotel}</td>
                <td>${tourItem.city}</td>
                <td>
                <a href="/tour/details?id=${tourItem.idtur}">Details</a>
                <a href="/book_before?idtur=${tourItem.idtur}">Booking</a>
                </td>

            </tr>
        </c:forEach>
    </table>


        </div>
    </div>

</div>

<%@ include file="../template/body-footer.html" %>