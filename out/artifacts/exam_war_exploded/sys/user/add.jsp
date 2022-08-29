<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>新增用户</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/theme.css">
		<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	</head>

	<body class="content1">
		<script>
    		$('#a_leader_txt', parent.document).html('新增用户');
		</script>
		<div class="container-fluid">
			<div class="row-fluid">
				<form method="post" action="<%=basePath%>sys/user?cmd=add">
					<div class="btn-toolbar">
						<input type="submit" class="btn btn-primary" value="保存 ">
						<a href="<%=basePath%>sys/user?cmd=list" class="btn">取消</a>

					</div>

					<div class="well">
						<div class="tab-pane active in">
							<!-- 选择角色 并对应相应的权限roleid
								roleid=-1时，为管理员，拥有管理员权限
								roleid=1，为学生，拥有学生权限
								roleid=2，为试卷管理者，有试卷管理权限
							-->
							<label>
								用户角色：
							</label>
							<select name="roleid">
								<option value="1">
									学生
								</option>
								<option value="-1">
									超级管理员
								</option>
								<option value="2">
									试题管理员
								</option>
							</select>
							<label>
								用户账号：
							</label>
							<input type="text" name="username" maxlength="10">
							<label>
								用户密码：
							</label>
							<input type="password" name="userpwd" maxlength="100">
							<label>
								用户姓名：
							</label>
							<input type="text" name="usertruename" maxlength="10">
							
							<label>
								用户状态：
							</label>
							<select name="userstate">
								<option value="1">
									正常
								</option>
								<option value="0">
									锁定
								</option>
							</select>
							<div style="color: #ff0000">
								${msg}
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
