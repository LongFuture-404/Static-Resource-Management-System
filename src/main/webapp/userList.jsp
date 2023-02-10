<%@ page import="com.example.realproject.entity.Users" %>
<%@ page import="com.example.realproject.entity.permissions"%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    //获取session中的用户
    Users userinfo = (Users)session.getAttribute("userinfo");
    //获取用户的权限
    List<permissions> priList = userinfo.getPriList();
    //判定用户权限中是否包含用户管理模块权限

    for(permissions pri:priList){
        if(pri.getP_name().equals("用户管理")){
            //获取这个模块权限对应的按钮级的权限
            List<permissions> son = pri.getPriList();
            //有了这个按钮级别的权限-做个标志
            //遍历
            for(permissions sonPri:son){
                //判定是否存在该按钮级别权限
                if(sonPri.getP_name().equals("用户管理_查询")){
                    //设置
                    pageContext.setAttribute("user_select",true);
                    pageContext.setAttribute("user_select_url",sonPri.getP_url());
                }
                if(sonPri.getP_name().equals("用户管理_编辑")){
                    //设置
                    pageContext.setAttribute("user_edit",true);
                }
                if(sonPri.getP_name().equals("用户管理_添加")){
                    //设置
                    pageContext.setAttribute("user_insert",true);
                }
                if(sonPri.getP_name().equals("用户管理_详情")){
                    //设置
                    pageContext.setAttribute("user_details",true);
                }
                if(sonPri.getP_name().equals("用户管理_删除")){
                    //设置
                    pageContext.setAttribute("user_delete",true);
//                    pageContext.setAttribute("user_delete_url",sonPri.getP_url());
                }
            }
            //结束
            continue ;
        }
    }
%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title> 静态资源后台管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="bootcss/bootstrap.min.css">
    <script src="bootjs/bootstrap.min.js"></script>
    <style>
        *{
            max-width: 1540px;
            font-size: 10px;
        }
    </style>
    <script>
        function deleteUser(phone){
            if(confirm("你确定要删除吗？")) {
                location.href = "${pageContext.request.contextPath}/delUsers.Handler?phone=" + phone;
            }
        }
    </script>
</head>
<body>
<!--头部-->
<header style="width: 100%;background-color:grey" class="publicHeader">
    <h1> 静态资源后台管理系统</h1>
    <div class="publicHeaderR">
        <div style="width: 50px;height: 50px;border-radius: 50%;overflow: hidden;margin-left: -100px;float: left">
            <td><img style="width: 65px;height: 65px;" src="${pageContext.request.contextPath}/imageLoad?phone=${userinfo.phone}"></td>
        </div>
        <span style="float: left" ><a href="login.jsp">退出</a></span>
    </div>
</header>
<!--时间-->
    <section class="publicTime">
        <span id="time">2019年1月1日 11:11  星期一</span>
        <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
    </section>
<!--主体内容-->
    <section class="publicMian" style="display: flex; flex-direction: column">
        <div style="height: 635px;" class="left"></div>
        <div class="right" style="position: absolute; left: 168px; top: 80px;">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div style="display: flex" class="search">
                <c:if test="${user_select}">
                    <form action="${user_select_url}" method="post">
                        <span>查询条件：</span>
                        <input type="text" name="name" placeholder="请输入关键字"/>
                        <input style="position: relative;top:5px; line-height:35px;width: 100px;height: 40px" type="submit" value="查询"/>
                    </form>
                </c:if>
                <c:if test="${user_insert}">
                    <a style="position: relative;left: 50% ;width: 120px" href="userAdd.jsp" >新增用户</a>
                </c:if>
            </div>
            <!--用户-->
            <table class="providerTable">
                <tr class="firstTr">
                    <th width="10%">用户编号</th>
                    <th width="15%">用户名称</th>
                    <th width="4%">头像</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="12%">电话</th>
                    <th width="10%">类型</th>
                    <th width="8%">状态</th>
                    <th width="15%">操作</th>
                </tr>
                <c:forEach items="${pb.list}" var="users" varStatus="s">
                    <tr>
                        <td>${users.uid}</td>
                        <td>${users.name}</td>
                        <td><img style="width: 100px;height: 58px;" src="${pageContext.request.contextPath}/imageLoad?phone=${users.phone}"></td>
                        <td>
                            <c:if test="${users.sex==1}">
                                男
                            </c:if>
                            <c:if test="${users.sex==0}">
                                女
                            </c:if>
                        </td>
                        <td>${users.normalBrithday}</td>
                        <td>${users.phone}</td>
                        <td>
                            <c:if test="${users.r_id==1}">
                                超级管理员
                            </c:if>
                            <c:if test="${users.r_id==2}">
                                普通管理员
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${users.state==1}">
                                启用
                            </c:if>
                            <c:if test="${users.state==0}">
                                锁死
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${user_details}">
                                <a href="${pageContext.request.contextPath}/findUserHandler?phone=${users.phone}&for=1"><img src="img/read.png" alt="查看" title="查看"/></a>
                            </c:if>
                            <c:if test="${user_edit}">
                                <a href="${pageContext.request.contextPath}/findUserHandler?phone=${users.phone}&for=2" ><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                            </c:if>
                            <c:if test="${user_delete}">
                                <a href="javascript:deleteUser(${users.phone})" class="removeUser"><img src="img/schu.png" alt="删除" title="删除"/></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <strong style="position: relative;left: 30px;font-size: 15px">${add_msg}</strong>
<%--                <tr>--%>
<%--                    <td>W10000001</td>--%>
<%--                    <td>火影忍者</td>--%>
<%--                    <td>--%>
<%--                        <div style="width: 46px;height: 46px;border-radius: 50%;overflow: hidden;float: left;">--%>
<%--                           <img style="width: 100%" src="img/head_img.jpeg" alt="头像" >--%>
<%--                        </div>--%>
<%--                    </td>--%>
<%--                    <td>男</td>--%>
<%--                    <td>18</td>--%>
<%--                    <td>159XXXX9999</td>--%>
<%--                    <td>VIP玩家</td>--%>
<%--                    <td>启用</td>--%>
<%--                    <td>--%>
<%--                        <c:if test="${user_details}">--%>
<%--                            <a href="userView.jsp"><img src="img/read.png" alt="查看" title="查看"/></a>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${user_edit}">--%>
<%--                            <a href="userUpdate.jsp"><img src="img/xiugai.png" alt="修改" title="修改"/></a>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${user_delete}">--%>
<%--                            <a href="#" class="removeUser"><img src="img/schu.png" alt="删除" title="删除"/></a>--%>
<%--                        </c:if>--%>
<%--                    </td>--%>
<%--                </tr>--%>
            </table>
        </div>
        <div style="display: flex; position: absolute;left: 200px;top: 90%">
            <form>
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${pb.currentPage==1}">
                        <li class="disabled page-item">
                            </c:if>
                            <c:if test="${pb.currentPage!=1}">
                        <li class="page-item">
                            </c:if>
                            <a class="page-link" href="${pageContext.request.contextPath}/findUserByPageHandler?currentPage=${pb.currentPage-1}&rows=7&name=${condition.name[0]}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span></a>
                        </li>
                        <c:forEach begin="1" end="${pb.totalPage}" var="i">
                            <li class="page-item">
                                <c:if test="${pb.currentPage==i}">
                                    <a class="page-link active" href="${pageContext.request.contextPath}/findUserByPageHandler?currentPage=${i}&rows=7&name=${condition.name[0]}">${i}</a>
                                </c:if>
                                <c:if test="${pb.currentPage!=i}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/findUserByPageHandler?currentPage=${i}&rows=7&name=${condition.name[0]}">${i}</a>
                                </c:if>
                            </li>
                        </c:forEach>
                        <c:if test="${pb.currentPage==pb.totalPage}">
                        <li class="disabled page-item">
                            </c:if>
                            <c:if test="${pb.currentPage!=pb.totalPage}">
                        <li class="page-item">
                            </c:if>
                            <a class="page-link" href="${pageContext.request.contextPath}/findUserByPageHandler?currentPage=${pb.currentPage+1}&rows=7&name=${condition.name[0]}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span></a>
                        </li>
                        <span style="font-size: 25px;margin-left: 5px;">共${pb.totalCount}条记录，共${pb.totalPage}页</span>
                    </ul>
                </nav>
            </form>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

    <footer class="footer">
    </footer>

<script src="js/jquery.js"></script>
<!--<script src="js/js.js"></script>-->
<!--<script src="js/time.js"></script>-->
<script>
    $(function () {
        $(".left").load("common_pri.jsp");
    }) ;
</script>
</body>
</html>