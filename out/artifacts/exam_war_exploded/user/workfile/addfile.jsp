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

    <title>增加试题功能</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/theme.css">
    <script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
<script>
    $('#a_leader_txt', parent.document).html('添加任务功能');
</script>
<div class="container-fluid">
    <div class="row-fluid">
        <form method="post" enctype=”multipart/form-data” action="<%=basePath%>user/file?cmd=upfile&id=${id}&username=${user.username}">
            <div class="btn-toolbar">
                <input type="submit" class="btn btn-primary" value="提交">
                <a href="<%=basePath%>user/task?cmd=userlist&username=${user.username}" class="btn">取消</a>

            </div>
            <div class="well">
                <div class="tab-pane active in">
                    <label>
                        文件命名格式“学号+专业班级+姓名！”
                    </label>
                    <input type="file" name="file1" value="选择文件">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
