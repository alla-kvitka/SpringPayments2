<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="${requestScope.lang}">
<head>
    <title>Payments for you </title>
    <style>
        <%@include file="/css/index.css" %>
    </style>
</head>
<body>
<div>
    <%@ include file="/WEB-INF/jsp/jspf/select.jspf" %>
</div>
<div class="line"></div>
<div class="wrapper">
    <header role="banner">
        <nav role="navigation">
            <h1><a href="index">Payments</a></h1>
            <ul class="nav-ul">
                <dir></dir>
                <dir></dir>
                <dir><a href="registration"><fmt:message key="header.Registration"/></a></dir>
                <dir><a href="login"><fmt:message key="table.UserLogin"/></a></dir>
            </ul>
        </nav>
    </header>
    <section class="sec-intro" role="section">
        <h1>Be Innovative!</h1>

        <img src="../../img/main.jpg" alt=""/>
    </section>

    <footerindex>
        <p class="copy" align="left">&copy; Alla Kvitka 2021</p>
    </footerindex>
    <div class="line"></div>
</div>
<script>
    function settingsLang(lang) {
        document.cookie = "lang=" + lang + "; path=/; max-age=" + (365 * 24 * 60 * 60);
        location.reload();
    }
</script>
</body>
</html>