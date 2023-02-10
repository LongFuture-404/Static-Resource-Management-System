<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title> 静态资源后台管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<!--头部-->
<header class="publicHeader">
    <h1> 静态资源后台管理系统</h1>
    <div class="publicHeaderR">
        <div style="width: 50px;height: 50px;border-radius: 50%;overflow: hidden;margin-left: -100px;float: left">
            <td><img style="width: 65px;height: 65px;" src="${pageContext.request.contextPath}/imageLoad?phone=${userinfo.phone}"></td>
        </div>
        <span style="float: left" ><a href="login.html">退出</a></span>
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
            <span>图片管理页面 >> 图片详情</span>
        </div>
        <div class="providerView">
            <div class="providerViewLeftbox">
                <p><strong>图片编号：</strong><span>XXXXXXXXXXXXXXX</span></p>
            <p><strong>文件名称：</strong><span>逍遥见灵儿</span></p>
            <p><strong>文件大小：</strong><span>10.5MB</span></p>
            <p><strong>文件类型：</strong><span>.mp4</span></p>
            <p><strong>图片分类：</strong><span>国漫</span></p>
            <p><strong>审核状态：</strong><span>审核通过</span></p>
            <p><strong>上传时间：</strong><span>2019/11/12</span></p>
            <p><strong>浏览数量：</strong><span>9999</span></p>
            </div>
            <div class="providerViewRightbox">
                <a href="pro">审核通过</a>
                <a href="pro">审核未通过</a>
                <a href="videoList.jsp">返回</a>
                <div style="border: #bd644e solid ;width: 300px;height: 300px;margin-top: 20px;">
                    
                </div>
            </div>
           
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