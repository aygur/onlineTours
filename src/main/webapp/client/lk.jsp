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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ include file="../template/head.jsp" %>
<%@ include file="../template/body-head.jsp" %>

<c:set var="client" value="${client}" />
    <div class="row">
        <div class="col-md-4">
        <h3>About you</h3>
        <a href="/client/edit"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
            Редактирование личных данных</a>
        <table class="table">
            <tbody>
                <tr>
                    <td>
                        Last Name
                    </td>
                    <td>
                        ${client.lastName}
                    </td>
                </tr>
                <tr>
                    <td>
                        First Name
                    </td>
                    <td>
                        ${client.firstName}
                    </td>
                </tr>

                <tr>
                    <td>
                        Phone
                    </td>
                    <td>
                        ${client.phone}
                    </td>
                </tr>
                <tr>
                    <td>
                        Doc
                    </td>
                    <td>
                        ${client.doc}
                    </td>
                </tr>
                <tr>
                    <td>
                        BirthDate
                    </td>
                    <td>
                        ${client.birthDate}
                    </td>
                </tr>
                <tr>
                    <td>
                        Address
                    </td>
                    <td>
                        ${client.address}
                    </td>
                </tr>
                <tr>
                    <td>
                        Gender
                    </td>
                    <td>
                        ${client.gender}
                    </td>
                </tr>
                <tr>
                    <td>
                        Login
                    </td>
                    <td>
                        ${client.login}
                    </td>
                </tr>
                <tr>
                    <td>
                        Password
                    </td>
                    <td>
                        ${client.password}
                    </td>
                </tr>
                <tr>
                    <td>
                        Email
                    </td>
                    <td>
                        ${client.email}
                    </td>
                </tr>
                <tr>
                    <td>
                        Role
                    </td>
                    <td>
                        ${client.role}
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
                <div class="col-lg-6">
                    <h3>Список тур. путевок</h3>

                    <table class="table table-hover">
                        <thead>
                        <td>№</td>
                        <td>Путевка</td>

                        <td>Дата начала</td>
                        <td>Дата окончания</td>
                        <td>Стоимость</td>
                        <td>payment_date</td>
                        <td>booking_date</td>
                        <td>Номер оплаты</td>
                        <td>Статус</td>
                        <td>Покупатель</td>
                        <td>Операции</td>
                        </thead>
                        <c:forEach items="${travelVouchers}" var="travelVoucher">
                            <tr>
                                <td>${travelVoucher.idtravel_voucher}</td>
                                <td><a href="/tour/details?id=${travelVoucher.tour.idtur}">${travelVoucher.tour.idtur}</a></td>
                                <td>${travelVoucher.tour.dateStart}</td>
                                <td>${travelVoucher.tour.dateFinish}</td>
                                <td>${travelVoucher.tour.cost}</td>
                                <td>${travelVoucher.payment_date}</td>
                                <td>${travelVoucher.booking_date}</td>
                                <td>${travelVoucher.payment_num}</td>
                                <td>${travelVoucher.voucherStatus.status}</td>
                                <td>
                                <c:if test="${travelVoucher.client.idclient == sessionScope.get(\"id\")}">
                                    <a href="/book_after?id=${travelVoucher.idtravel_voucher}">
                                        Оплатить или Отменить тур</a>
                                    </c:if>

                            </tr>
                        </c:forEach>
                    </table>
    </div>
</div>

<%@ include file="../template/body-footer.html" %>