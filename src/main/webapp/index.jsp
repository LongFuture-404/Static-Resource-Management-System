<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title> 静态资源后台管理系统主页</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="js/jquery.js"></script>
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

</section>
<!--主体内容-->
<section class="publicMian">
    <div class="left">

    </div>
    <div class="right">
        <img class="wColck" src="img/clock.jpg" alt=""/>
        <div class="wFont">
            <h2>${userinfo.name}</h2>
            <p>欢迎来到 静态资源后台管理系统!</p>
            <span id="hours"></span>
        </div>
    </div>
</section>
<script src="js/time.js"></script>
<footer class="footer">
</footer>

<script>
    $(function () {
        $(".left").load("common_pri.jsp");
    }) ;
</script>
</body>
</html>