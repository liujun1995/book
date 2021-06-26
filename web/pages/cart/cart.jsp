<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <base href="http://localhost:8080/book/">
    <%--静态包含base标签css样式jquery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            //给删除绑定单击事件
            $("a.deleteItem").click(function () {
                let itemName = $(this).attr("itemName");
                let confirmBoolean = confirm("你确定要删除" + itemName + "吗？");
                if (confirmBoolean === false) {
                    return false;
                }
            });

            $("a.clearCart").click(function () {
                return confirm("你确定要清空购物车吗？");
            });
            //给输入框绑定失去焦点事件
            $(".updateCount").change(function () {
                //获取商品名称
                let name = $(this).parent().parent().find("td:first").text();
                //获取商品数量
                let count = this.value;
                if (confirm("你确定要将"+name+"修改数量为"+count+"吗？")){
                    let bookId = $(this).attr("bookId");
                    location.href = "${pageScope.basePath}cartServlet?action=updateCount&count="+count+"&id="+bookId;
                }else {
                    //defaultValue属性是表单项DOM对象的属性，表示表示默认value属性值
                    this.value = this.defaultValue;
                }

            });


        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>

        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <%--如果购物车空，输出以下的内容--%>
        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5"><a href="index.jsp"> 亲，当前购物车为空，快去选一些中意的书籍吧！！！</a></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </c:if>
        <%--如果购物车非空，输出以下的内容--%>
        <c:if test="${not empty sessionScope.cart.items}">
            <%--遍历session中的cart对象中的Map,Map中的每个Node的key为商品的id，value为id所对应的商品--%>
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>
                    <td>
                        <input style="width: 80px;" class="updateCount" bookId="${entry.value.id}" type="text" value="${entry.value.count}">
                    </td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a class="deleteItem" itemName="${entry.value.name}"
                           href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>

    </table>
    <%--如果购物车非空，输出以下的内容--%>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a class="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>
</div>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>