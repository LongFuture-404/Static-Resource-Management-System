<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
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
            font-size: 10px;
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
    <div class="left" style="height: 635px;"></div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <form action="${pageContext.request.contextPath}/updateUsers.Handler" method="post" enctype="multipart/form-data">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <input type="hidden" name="uid" value="${users.uid}"/>
                </div>
                <div>
                    <label for="name">用户名称：</label>
                    <input type="text" name="name" id="name" value="${users.name}"/>
                    <span >*</span>
                </div>
                <div>
                    <label style="position:relative;width: 66px;left: 16%;box-shadow:1px 1px 1px #0a0a0a" class="btn btn-default" for="avatar">上传头像</label>
                    <input type="file" name="avatar" id="avatar" style="display:none;"/>
                    <img style="width: 100px;height: 58px;position: relative;left: 210px;" src="${pageContext.request.contextPath}/imageLoad?phone=${users.phone}">
                </div>
                <div>
                    <label >用户性别：</label>
                    <select name="sex">
                    <c:if test="${users.sex==1}">
                        <option value="1" selected>男</option>
                        <option value="0" >女</option>
                    </c:if>
                    <c:if test="${users.sex==0}">
                        <option value="1" >男</option>
                        <option value="0" selected>女</option>女
                    </c:if>
                    <c:if test="${users.sex!=1 and users.sex!=0}">
                        <option value="1" >男</option>
                        <option value="0" >女</option>
                    </c:if>
                    </select>
                </div>
                <div>
                    <label for="brithday">出生日期：</label>
                    <input type="text" name="brithday" id="brithday" value="${users.normalBrithday}"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="phone">用户电话：</label>
                    <input type="text" name="phone" id="phone" value="${users.phone}"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="address">用户地址：</label>
                    <input type="text" name="address" id="address" value="${users.address}"/>
                </div>
                <div>
                    <label >用户类别：</label>
                    <c:if test="${users.r_id==1}">
                        <input type="radio" name="r_id" value="1" checked/>超级管理员
                        <input type="radio" name="r_id" value="2" />普通管理员
                    </c:if>
                    <c:if test="${users.r_id==2}">
                        <input type="radio" name="r_id" value="1" />超级管理员
                        <input type="radio" name="r_id" value="2" checked/>普通管理员
                    </c:if>
                    <c:if test="${users.r_id!=1 and users.r_id!=2}">
                        <input type="radio" name="r_id" value="1" />超级管理员
                        <input type="radio" name="r_id" value="2" />普通管理员
                    </c:if>
<%--                    <input type="radio" name="userlei" checked/>VIP玩家--%>
<%--                    <input type="radio" name="userlei"/>普通玩家--%>
                </div>
                <div>
                    <label >用户状态：</label>
                    <c:if test="${users.state==1}">
                        <input type="radio" name="state" value="1" checked/>启用
                        <input type="radio" name="state" value="0"/>停用
                    </c:if>
                    <c:if test="${users.state==0}">
                        <input type="radio" name="state" value="1" />启用
                        <input type="radio" name="state" value="0" checked/>停用
                    </c:if>
                </div>

                <div class="providerAddBtn">
                    <input type="submit" value="保存" onclick="history.back(-1)"/>
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
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
<script type="text/javascript">
    $("#phone").blur(function(){
        const phoneInput = document.getElementById('phone').value;
        $.post("${pageContext.request.contextPath}/selectByPhone", {phone: phoneInput}).then(response => {
            if (response==='') {
                console.log(true)
            } else {
                setTimeout("alert('该手机号未修改或已存在')",750)
            }
        })
    })
</script>
</body>
</html>