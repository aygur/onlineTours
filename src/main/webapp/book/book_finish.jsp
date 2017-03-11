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

<h3>Туристический ваучер</h3>
<p>Туристический ваучер оплачен, желаем Вам приятного отдыха</p>
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
    <td>Дата оплаты</td>
    <td>Статус</td>
    <td>Покупатель</td>
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
        <td>${travelVoucher.payment_num}</td>
        <td>${travelVoucher.voucherStatus.status}</td>
        <td>${travelVoucher.client.firstName} ${travelVoucher.client.lastName}</td>

    </tr>
</table>

<%@ include file="../template/body-footer.html" %>