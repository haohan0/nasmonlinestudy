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
    $('#a_leader_txt', parent.document).html('添加成绩功能');
</script>
<div class="container-fluid">
    <div class="row-fluid">
        <form method="post" action="<%=basePath%>sys/grade?cmd=add">
            <div class="btn-toolbar">
                <input type="submit" class="btn btn-primary" value="保存 ">
                <a href="<%=basePath%>sys/student?cmd=gradelist&id=${param.username}"   class="btn">取消</a>

            </div>

            <div class="well">
                <div class="tab-pane active in">
                    <label>
                        用户名：
                    </label>
                    <input type="text" name="username" value="${param.username}" maxlength="100" readonly="readonly">
                    <label>
                        平时成绩：
                    </label>
                    <input type="text" name="gradeday" maxlength="10">
                    <label>
                        任务成绩：
                    </label>
                    <input type="text" name="gradetask" maxlength="10">
                    <div style="color: red">
                        ${msg}
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

