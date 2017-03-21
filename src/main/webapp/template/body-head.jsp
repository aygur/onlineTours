<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<body>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/dashboard">Online tours</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/dashboard">Tours</a></li>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="/clients">Clients</a></li>
                    <li><a href="/vouchers">Vouchers</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Дополнительно<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/tour/add">Добавить Тур</a></li>
                        </ul>
                    </li>
                </security:authorize>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                        <a href="/LKClient">Hello <security:authentication property="principal.username" />!</a>
                </li>
                    <%--<li><a href="/logout">Logout</a></li>--%>
                <li><a href="/logout">Logout</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">
    <div>
        <p>${errors}</p>
    </div>