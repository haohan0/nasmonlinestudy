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

  <title>增加指令功能</title>
  <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath%>css/theme.css">
  <script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
<script>
  $('#a_leader_txt', parent.document).html('添加指令功能');
</script>
<div class="container-fluid">
  <div class="row-fluid">
    <form method="post" action="<%=basePath%>sys/instruct?cmd=add">
      <div class="btn-toolbar">
        <input type="submit" class="btn btn-primary" value="保存 ">
        <a href="<%=basePath%>sys/instruct?cmd=list" class="btn">取消</a>

      </div>

      <div class="well">
        <div class="tab-pane active in">
          <label>
            指令名：
          </label>
          <input type="text" name="aname" maxlength="100">

          <label>
            指令格式：
          </label>
          <input type="text" name="acontent" maxlength="10">
          <label>
            指令事例：
          </label>
          <input type="text" name="aexam" maxlength="10">
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
