<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="${requestScope.lang}">
<head>
    <title>Registration</title>
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
                <dir><a href="registration"><fmt:message key="header.Registration"/></a></dir>
                <dir><a href="login"><fmt:message key="header.LogIn"/></a></dir>
            </ul>
        </nav>

    </header>

    <form align="center" class="signup-form"
          action="${pageContext.request.contextPath}/registration"
          method="post" >
        <div class="row">
            <input id="login" placeholder=<fmt:message key="form.Login"/> type='text' name='login'/>
        </div>

        <div class="row">
            <input placeholder=<fmt:message key="form.Email"/> type='text' name='email'/>
        </div>
        <div class="row">
            <input placeholder=<fmt:message key="form.Password"/> type='text' name='password'/>
        </div>
        <div class="row">
            <input placeholder=<fmt:message key="form.PasswordRepeat"/> type='text' name='password-repeat'/>
        </div>
        <div class="row">
            <input type='submit' name=<fmt:message key="bottom.submit"/>/>
        </div>
    </form>
</div>

<script>
    function settingsLang(lang) {
        document.cookie = "lang=" + lang + "; path=/; max-age=" + (365 * 24 * 60 * 60);
        location.reload();
    }
</script>
</body>
</html>
