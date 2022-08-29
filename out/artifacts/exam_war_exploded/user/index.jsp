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
		<h1><img src="css/images/1804.jpg" class="radius-circle rotate-hover" height="50" alt="" />汇编指令学习网</h1>
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
	<div class="leftnav-content">
		<h2><a href="<%=basePath%>/user/course?cmd=userlist" target="right"><span class="icon-briefcase"></span>课程简介</a></h2>
		<h2><a href="<%=basePath%>/user/instruct?cmd=userlist" target="right"><span class="icon-briefcase"></span>汇编指令</a></h2>
		<h2><a href="<%=basePath%>/user/task?cmd=userlist&username=${user.username}" target="right"><span class="icon-briefcase"></span>任务栏</a></h2>
		<h2><a href="<%=basePath%>/user/grade?cmd=stulist&username=${user.username}" target="right"><span class="icon-briefcase"></span>成绩</a></h2>
	</div>

</div>
<script type="text/javascript">
	//是jquer等dom加载完后执行该方法
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