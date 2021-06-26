<%--
  Created by IntelliJ IDEA.
  User: 刘俊
  Date: 2021/6/14
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath", basePath);
%>

<base href="<%=basePath%>">
<!--以下所有路径都要以http://127.0.0.1:8080/book/此路径为基准进行编写，该路径代表web-->
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>