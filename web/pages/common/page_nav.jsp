<%--
  Created by IntelliJ IDEA.
  User: 刘俊
  Date: 2021/6/17
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页条的开始--%>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--页码输出的开始--%>
    <c:choose>
        <%--情况一：总页码pageTotaal小于等于5，页码范围是1-总页码--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${i==requestScope.page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${i != requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <c:when test="${requestScope.page.pageTotal>5}">
            <%--小情况一：总页码pageTotaal大于5，当前页码为1、2、3时--%>
            <c:choose>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--小情况二，当前页码为最后3个，页码范围为总页码-4到总页码--%>
                <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--小情况三，当前页码为4、5、6、7，页码范围为当前页码减2到当前页码加2--%>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                        <c:if test="${i==requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

        </c:when>

    </c:choose>



    <%--页码输出的结束--%>


    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    <%--得到地址栏中pageNo的值--%>
    到第<input value="<c:choose >
                            <c:when test="${param.pageNo<1}">
                                ${1}
                            </c:when>
                            <c:when test="${param.pageNo>requestScope.page.pageTotal}">
                                ${requestScope.page.pageTotal}
                            </c:when>
                            <c:otherwise>
                                ${param.pageNo}
                            </c:otherwise>
                        </c:choose>"
             name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {
                let pageNo = $("#pn_input").val();
                if (Math.round(pageNo) === pageNo) {
                    if (pageNo < 1) {
                        pageNo = 1;
                    }
                    if (pageNo >${requestScope.page.pageTotal}) {
                        pageNo =${requestScope.page.pageTotal};
                    }
                    //javaScript中提供了一个location对象，可以获得地址栏中的地址
                    //pageScope.basePath是head中动态获取到的
                    location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
                }
            })
        });
    </script>
</div>
<%--分页条的结束--%>
