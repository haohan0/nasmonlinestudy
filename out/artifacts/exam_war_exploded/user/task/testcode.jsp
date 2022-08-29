<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2022/4/24
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<style>
</style>
<head>
</head>
<body>
	<div class="leftnav">
		<h  style="font-weight: bolder;font-size: x-large; font-family:Lucida Calligraphy, cursive, serif, sans-serif;color: #4d5b76">任务描述</h>
		<p>${item.taskname}</p>
		<h  style="font-weight: bolder;font-size: x-large; font-family:Lucida Calligraphy, cursive, serif, sans-serif;color: #4d5b76">编码要求</h>
		<p>${item.taskcontent}</p>
	</div>
	<div class="admin">
			<iframe  scrolling="auto"  scr rameborder="0" src="https://www.dooccn.com/nasm/" width="100%" height="100%"></iframe>
	</div>
</body>
</html>
