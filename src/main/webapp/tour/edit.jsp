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


<form action="/tour/edit" method="post">
    <c:set var="tour" value="${tour}" />
    <div class="form-group">
        <label for="dateStart">Date Start:</label>
        <input type="text" class="form-control" name="dateStart" id="dateStart" value="${tour.dateStart}" placeholder="Date Start">
    </div>
    <div class="form-group">
        <label for="dateFinish">Date Finish:</label>
        <input type="text" class="form-control" name="dateFinish" id="dateFinish" value="${tour.dateFinish}" placeholder="Date Finish">
    </div>
    <div class="form-group">
        <label for="tur_type">Tour type:</label>
        <input type="text" class="form-control" name="tur_type" id="tur_type" value="${tour.tur_type}"  placeholder="Tour type">
    </div>
    <div class="form-group">
        <label for="menu_type">Menu type:</label>
        <input type="text" class="form-control" name="menu_type" id="menu_type" value="${tour.menu_type}"  placeholder="Menu type">
    </div>
    <div class="form-group">
        <label for="cost">Cost:</label>
        <input type="text" class="form-control" name="cost" id="cost" value="${tour.cost}"  placeholder="Cost">
    </div>
    <div class="form-group">
        <label for="booking">Booking:</label>
        <input type="text" class="form-control" name="booking" id="booking" value="${tour.booking}"  placeholder="Booking">
    </div>
    <div class="form-group">
        <label for="hotel">Hotel:</label>
        <input type="text" class="form-control" name="hotel" id="hotel" value="${tour.hotel}"  placeholder="Hotel">
    </div>
    <div class="form-group">
        <label for="city">City:</label>
        <input type="text" class="form-control" name="city" id="city" value="${tour.city}"  placeholder="City">
    </div>
    <div class="form-group">
        <label for="deleted">Deleted:</label>
        <input type="text" class="form-control" name="deleted" id="deleted" value="${tour.deleted}"  placeholder="deleted">
    </div>

    <input type="hidden" name="id" id="id" value="${tour.idtur}" >
    <input type="submit" value="Submit">
</form>

<%@ include file="../template/body-footer.html" %>