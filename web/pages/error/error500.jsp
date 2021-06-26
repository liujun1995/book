<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>错误页面</title>
    <%--静态包含base标签css样式jquery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }
        body {
            text-align: center;
        }

        div {
            width: 300px;
            height: 300px;
            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -150px;
            margin-top: -150px;
            text-align: center;
        }
        a{
            color: red;
            text-decoration: none;
        }

    </style>
</head>
<body>
    <div>
        <h1>抱歉，您访问的后台程序出现了错误，正在抢修！</h1><br>
        <a href="index.jsp">返回首页</a>
    </div>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
