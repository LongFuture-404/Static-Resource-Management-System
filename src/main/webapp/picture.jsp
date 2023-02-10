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
        if(pri.getP_name().equals("图片管理")){
            //获取这个模块权限对应的按钮级的权限
            List<permissions> son = pri.getPriList();
            //有了这个按钮级别的权限-做个标志
            //遍历
            for(permissions sonPri:son){
                //判定是否存在该按钮级别权限
                if(sonPri.getP_name().equals("图片管理_查询")){
                    //设置
                    pageContext.setAttribute("picture_select",true);
                    pageContext.setAttribute("picture_select_url",sonPri.getP_url());
                }
                if(sonPri.getP_name().equals("图片管理_编辑")){
                    //设置
                    pageContext.setAttribute("picture_edit",true);
                }
                if(sonPri.getP_name().equals("图片管理_添加")){
                    //设置
                    pageContext.setAttribute("picture_insert",true);
                }
                if(sonPri.getP_name().equals("图片管理_详情")){
                    //设置
                    pageContext.setAttribute("picture_details",true);
                }
                if(sonPri.getP_name().equals("图片管理_删除")){
                    //设置
                    pageContext.setAttribute("picture_delete",true);
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
            <span>图片管理页面</span>
        </div>
        <div class="search" style="display:flex;">
            <c:if test="${picture_select}">
                <form action="${picture_select_url}" method="post">
                    <span>搜索条件：</span>
                    <input type="text" name="name" placeholder="请输入关键字"/>
                    <input type="button" value="搜索"/>
                </form>
            </c:if>
            <c:if test="${picture_insert}">
                <a style="position: relative;left: 48%" href="pictureAdd.jsp">添加图片</a>
            </c:if>
        </div>
        <!--图片操作表格-->
        <table class="providerTable" cellpadding="0" cellspacing="0">
            <tr class="firstTr">
                <th width="10%">图片编号</th>
                <th width="15%">图片名称</th>
                <th width="10%">图片分类</th>
                <th width="10%">审核状态</th>
                <th width="10%">上传时间</th>
                <th width="10%">作者</th>
                <th width="30%">操作</th>
            </tr>

            <tr>
                <td>pic_index_001</td>
                <td>心之所向</td>
                <td>动漫</td>
                <td>审核通过</td>
                <td>2020/1/1</td>
                <td>殷桃小丸子</td>
                <td>
                    <c:if test="${picture_details}">
                        <a href="pictureView.jsp"><img src="img/read.png" alt="查看" title="查看"/></a>
                    </c:if>
                    <c:if test="${picture_edit}">
                        <a href="pictureUpdate.jsp"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                    </c:if>
                    <c:if test="${picture_delete}">
                        <a href="#" class="removeProvider"><img src="img/schu.png" alt="删除" title="删除"/></a>
                    </c:if>
                </td>
            </tr>
        </table>

    </div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
   <div class="removerChid">
       <h2>提示</h2>
       <div class="removeMain" >
           <p>你确定要删除该图片资源吗？</p>
           <a href="#" id="yes">确定</a>
           <a href="#" id="no">取消</a>
       </div>
   </div>
</div>


<footer class="footer">
</footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>
<script>
    $(function () {
        $(".left").load("common_pri.jsp");
    }) ;
</script>
</body>
</html>