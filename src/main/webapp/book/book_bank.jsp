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

<h3>Банковский перевод</h3>

<table class="table table-hover">
    <thead>
    <td>Дата начала</td>
    <td>Дата окончания</td>
    <td>Тип тура</td>
    <td>Тип питания</td>
    <td>Стоимость</td>
    <td>Отель</td>
    <td>Расположение</td>
    <td>Date booking</td>
    <td>Операции</td>
    </thead>
    <tr>
        <td>${travelVoucher.tour.dateStart}</td>
        <td>${travelVoucher.tour.dateFinish}</td>
        <td>${travelVoucher.tour.tur_type}</td>
        <td>${travelVoucher.tour.menu_type}</td>
        <td>${travelVoucher.tour.cost}</td>
        <td>${travelVoucher.tour.hotel}</td>
        <td>${travelVoucher.tour.city}</td>
        <td>${travelVoucher.booking_date}</td>
        <form action="/bank" method="post">
            <h3>${error}</h3>
            <label>${one} + ${two} = </label>
            <input type="text" name="sum_user" id="sum_user" value="" placeholder="Введите сумму">
            <input type="hidden" name="idtur" id="idtur" value="${travelVoucher.idtravel_voucher}" >
            <input type="submit" value="Оплатить">
        </form>


    </tr>
</table>
<form action="/book_cancel" method="post">
    <input type="hidden" name="id" id="id" value="${travelVoucher.idtravel_voucher}" >
    <input type="submit" value="Отменить бронирование">
</form>

<%@ include file="../template/body-footer.html" %>