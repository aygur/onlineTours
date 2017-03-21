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

<div class="container-fluid">
    <div class="row">
        <div class="col-md-8">
<h3>Список тур. путевок</h3>
            <table class="table table-hover">
                <thead>
                <td>№</td>
                <td>Путевка</td>

                <td>Дата начала</td>
                <td>Дата окончания</td>
                <td>Тип тура</td>
                <td>Тип питания</td>
                <td>Стоимость</td>
                <td>payment_date</td>
                <td>booking_date</td>
                <td>Номер оплаты</td>
                <td>Статус</td>
                <td>Покупатель</td>
                </thead>
                <tbody>
                <c:forEach items="${travelVouchers}" var="travelVoucher">
                    <tr>
                        <td>${travelVoucher.idtravel_voucher}</td>
                        <td><a href="/tour/details?id=${travelVoucher.tour.idtur}">${travelVoucher.tour.idtur}</a></td>
                        <td>${travelVoucher.tour.dateStart}</td>
                        <td>${travelVoucher.tour.dateFinish}</td>
                        <td>${travelVoucher.tour.tur_type}</td>
                        <td>${travelVoucher.tour.menu_type}</td>
                        <td>${travelVoucher.tour.cost}</td>
                        <td>${travelVoucher.payment_date}</td>
                        <td>${travelVoucher.booking_date}</td>
                        <td>${travelVoucher.payment_num}</td>
                        <td>${travelVoucher.voucherStatus.status}</td>
                        <td>
                            <a href="/client/details?id=${travelVoucher.client.idclient}">
                                ${travelVoucher.client.firstName} ${travelVoucher.client.lastName}</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


        </div>
    </div>

</div>

<%@ include file="../template/body-footer.html" %>