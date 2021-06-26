<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>结算页面</title>
    <%--静态包含base标签css样式jquery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">结算</span>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">
    <c:if test="${empty requestScope.orderId}">
        <h1>您的购物车为空，无法生成订单！</h1>
    </c:if>
    <C:if test="${not empty requestScope.orderId}">
        <h1>你的订单已结算，订单号为${requestScope.orderId}</h1>
    </C:if>
</div>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>