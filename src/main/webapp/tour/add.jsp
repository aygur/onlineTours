<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<c:set var="now" value="<%=new java.util.Date()%>" />
<div class="row">
    <div class="col-md-6">
        <form action="/tour/add" method="post">

    <div class="form-group">
        <label for="dateStart">Date Start:</label>
        <input type="date" class="form-control" name="dateStart" id="dateStart"
               value="<fmt:formatDate pattern="yyyy-MM-dd"
                            value="${now}"/>" placeholder="Date Start">
    </div>
    <div class="form-group">
        <label for="dateFinish">Date Finish:</label>
        <input type="date" class="form-control" name="dateFinish" id="dateFinish" value="" placeholder="Date Finish">
    </div>
    <div class="form-group">
        <label for="tur_type">Tour type:</label>
        <input type="text" class="form-control" name="tur_type" id="tur_type" value=""  placeholder="Tour type">
    </div>
    <div class="form-group">
        <label for="menu_type">Menu type:</label>
        <input type="text" class="form-control" name="menu_type" id="menu_type" value=""  placeholder="Menu type">
    </div>
    <div class="form-group">
        <label for="cost">Cost:</label>
        <input type="text" class="form-control" name="cost" id="cost" value=""  placeholder="Cost">
    </div>
    <div class="form-group">
        <label for="booking">Booking:</label>
        <input type="text" class="form-control" name="booking" id="booking" value=""  placeholder="Booking">
    </div>
    <div class="form-group">
        <label for="hotel">Hotel:</label>
        <input type="text" class="form-control" name="hotel" id="hotel" value=""  placeholder="Hotel">
    </div>
    <div class="form-group">
        <label for="city">City:</label>
        <input type="text" class="form-control" name="city" id="city" value=""  placeholder="City">
    </div>
    <div class="form-group">
        <label for="deleted">Deleted:</label>
        <input type="text" class="form-control" name="deleted" id="deleted" value=""  placeholder="deleted">
    </div>
    <input type="submit" value="Submit">
</form>
    </div>
</div>
<%@ include file="../template/body-footer.html" %>