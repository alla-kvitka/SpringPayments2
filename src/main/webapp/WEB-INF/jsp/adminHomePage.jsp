<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="${requestScope.lang}">
<head>
    <title>Admin Home Page</title>
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
            <h1><a href="adminHomePage">Home</a></h1>
            <ul class="nav-ul">
                <dir></dir>
                <dir></dir>
                <dir><a href="adminAllCards"><fmt:message key="header.AllCards"/></a></dir>
                <dir><a href="adminAllUsers"><fmt:message key="header.AllUsers"/></a></dir>
                <dir><a href="logout"><fmt:message key="header.LogOut"/></a></dir>
            </ul>
        </nav>
    </header>
    <div>
        <h2>
            <p><img src="../../img/avatar.jpg" width="250" height="250" align="middle"></p>
        </h2>
    </div>
</div>
<script>
    function settingsLang(lang) {
        document.cookie = "lang=" + lang + "; path=/; max-age=" + (365 * 24 * 60 * 60);
        location.reload();
    }
</script>
</body>
</html>
