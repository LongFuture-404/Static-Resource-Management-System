<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title> 静态资源后台管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <style>
        *{
         font-size: 15px;
        }
        p{
            text-align: center;
            width: 100px;
            display: flex;
            flex-direction: column;
        }
        .providerView{

            width: 1300px;
            position: relative;
            display: flex;
            flex-direction:row;
            gap: 50px;
            justify-content: normal;
        }
        .providerView Strong{
            width: 100px;
        }

        .back{
            position: relative;
            top: 40%;
            left: 90%;
            background-color: #0c63e4;
            line-height: 50px;
            text-align: center;
            width: 100px;
            height: 50px;
        }
        .backButton{
            font-size: 20px;
            color: blanchedalmond;
        }
    </style>
</head>
<body>
<!--头部-->
<header class="publicHeader">
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
<section class="publicMian ">
    <div class="left"></div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户信息查看页面</span>
        </div>
        <div class="providerView">
            <p><strong>用户编号：</strong><span>${users.uid}</span></p>
            <p><strong>用户名称：</strong><span>${users.name}</span></p>
            <p><strong>头像：</strong><span>
                <img style="width: 150px;height: 100px;position: relative;left: -30px;top: -10px" src="${pageContext.request.contextPath}/imageLoad?phone=${users.phone}">
            </span></p>
            <p><strong>用户性别：</strong>
                <span>
                <c:if test="${users.sex==1}">男</c:if>
                <c:if test="${users.sex==0}">女</c:if>
                </span></p>
            <p><strong>出生日期：</strong><span>${users.normalBrithday}</span></p>
            <p><strong>用户电话：</strong><span>${users.phone}</span></p>
            <p><strong>用户地址：</strong><span>${users.address}</span></p>
            <p><strong>用户类别：</strong><span>
                <c:if test="${users.r_id==1}">超级管理员</c:if>
                <c:if test="${users.r_id==2}">普通管理员</c:if></span></p>
            <p><strong>用户状态：</strong><span>
                <c:if test="${users.state==1}">启用</c:if>
                <c:if test="${users.state==0}">停用</c:if>
            </span></p>
        </div>
        <div class="back">
            <a class="backButton" href="userList.jsp">返回</a>
        </div>
    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>
<script src="js/jquery.js"></script>
<script>
    $(function () {
        $(".left").load("common_pri.jsp");
    }) ;
</script>
</body>
</html>