<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

		<title>任务功能列表</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/theme.css">
		<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	</head>

	<body class="content1">

		<div class="container-fluid">
			<div class="row-fluid">
				<form class="form-inline" method="post"
					action="<%=basePath%>sys/task?cmd=list">
					<input class="input-xlarge" placeholder="题号..." name="Task_content"
						type="text" value="${param.Task_content}">
					<input class="btn icon-search" type="submit" value="查询" />
					<a class="btn btn-primary"
						href="<%=basePath%>sys/task/add.jsp"> <i
						class="icon-plus"></i> 新增任务</a>
				</form>

				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>
									任务ID
								</th>
								<th>
									任务标题
								</th>
								<th>
									任务内容
								</th>
								<th>
									任务状态
								</th>
								<th style="width: 90px;">
									编辑
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pager.list}" var="item">
								<tr>
									<td>
										${item.taskid}
									</td>
									<td>
										${item.taskname}
									</td>
									<td>
										${item.taskcontent}
									</td>
									<td>
										<c:choose>
											<c:when test="${item.taskstate==\"1\"}">
												可用		
											</c:when>
											<c:otherwise>不可用</c:otherwise>
										</c:choose>
									</td>
									<td>
										<a href="<%=basePath%>sys/task?cmd=toedit&id=${item.taskid}">编辑</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="pagination pagination-right">
						<ul>
							<li>
								<a>共计：${pager.pagectrl.pagecount}页/${pager.pagectrl.rscount}条记录</a>
							</li>
							
							<li>
								<c:if test="${pager.pagectrl.currentindex==1}" var="fp">
									<a style="disabled:true">上一页</a>
								</c:if>
								<c:if test="${!fp}">
									<a href="<%=basePath%>sys/task?cmd=list&index=${pager.pagectrl.currentindex-1}">上一页</a>
								</c:if>
							</li>						
							
							<c:forEach begin="${pager.pagectrl.minpage}" step="1" end="${pager.pagectrl.maxpage}" var="index">
							<li>
								<c:if test="${pager.pagectrl.currentindex==index}" var="t">
									<a style="color:red;background-color:#bbb">${index}</a>
								</c:if>
								<c:if test="${!t}">
								<a href="<%=basePath%>sys/task?cmd=list&index=${index}">${index}</a>
								</c:if>
							</li>
							</c:forEach>
							
							<li>
								<c:if test="${pager.pagectrl.currentindex==pager.pagectrl.pagecount}" var="fp">
									<a style="disabled:true">下一页</a>
								</c:if>
								<c:if test="${!fp}">
									<a href="<%=basePath%>sys/task?cmd=list&index=${pager.pagectrl.currentindex+1}">下一页</a>
								</c:if>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
