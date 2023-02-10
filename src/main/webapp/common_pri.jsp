<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    <h2 class="leftH2"><span class="span1"></span>功能列表<span></span></h2>
    <nav>
        <ul class="list">
                <c:forEach items="${userinfo.priList}" var="pri">
                    <c:if test="${pri.p_url=='userList.jsp'}">
                        <li>
                            <a  style="text-decoration: none" href="${pageContext.request.contextPath}/findUserByPageHandler">${pri.p_name}</a>
                        </li>
                    </c:if>
                    <c:if test="${pri.p_url!='userList.jsp'}">
                        <li>
                            <a  style="text-decoration: none" href="${pri.p_url}">${pri.p_name}</a>
                        </li>
                    </c:if>
                </c:forEach>
        </ul>
        <script>

        </script>
    </nav>

