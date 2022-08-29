<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <base target="main" />
    <title>欢迎使用汇编语言指令学习系统</title>
    <link rel="stylesheet" href="<%=basePath%>css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>css/admin.css">
    <script src="<%=basePath%>js/jquery.js"></script>

</head>

<body style="background-color:#f2f9fd;">
    <div class="header bg-main">
        <div class="logo margin-big-left fadein-top">
            <h1><img src="css/images/1804.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
        </div>
        <div class="head-l">
            <!--javascript:是伪协议，表示url的内容通过javascript执行。void(0)表示不作任何操作，
                这样会防止链接跳转到其他页面。这么做往往是为了保留链接的样式，但不让链接执行实际操作 -->
            <a href="javascript:void(0);" class="button button-little bg-blue"><span class="icon-user"></span> ${user.usertruename}(${user.rolename})</a> &nbsp;&nbsp;
            <a class="button button-little bg-red" href="<%=basePath%>sys/user?cmd=logout" target="_self"><span class="icon-power-off"></span> 退出登录</a>
        </div>
    </div>
    <div class="leftnav">
        <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
        <c:forEach items="${list}" var="top">
            <!--判断是否是管理员，
                如果funid为-1,就是管理员界面 -->
            <c:if test="${top.funpid==\"-1\"}">
                <h2><span class="icon-briefcase"></span>${top.funname}</h2>
                <ul id="error-menu${top.funid}" class="nav nav-list collapse" style="display:block">
                    <!--  UserService、java中的getallUser()方法查询用户信息。
                          logServlet、java主要用来将查询到用户信息保存到Session对象中，用来进行输出代码。
                          使用forEace迭代标签结合EL表达式
                          将保存到session对象中的用户信息取出且输出到页面中方法代码-->
                    <c:forEach items="${list}" var="child">
                        <c:if test="${child.funpid==top.funid}">
                            <li>
                                <a href="<%=basePath%>${child.funurl}" target="right"><span class="icon-caret-right"></span>${child.funname}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </c:if>
        </c:forEach>
    </div>
    <script type="text/javascript">

        //是jquer等dom加载完后执行该方法
        $(function(){
            //单击执行方法
            $(".leftnav h2").click(function(){
                $(this).next().slideToggle(200);	
                $(this).toggleClass("on"); 
            })
            $(".leftnav ul li a").click(function(){
                $("#a_leader_txt").text($(this).text());
                $(".leftnav ul li a").removeClass("on");
                $(this).addClass("on");
            })
        });
    </script>
    <ul class="bread">
        <li><a href="javascript:void(0);" target="right" class="icon-home"> 首页</a></li>
        <li><a href="javascript:void(0);" target="right" id="a_leader_txt">网站信息</a></li>
    </ul>
    <div class="admin">
        <iframe scrolling="auto" rameborder="0" src="" name="right" width="100%" height="100%"></iframe>
    </div>
</body>
</html>