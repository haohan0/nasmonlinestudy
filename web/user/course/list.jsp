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
        <div class="well">
            <table class="table">
                <thead>
                <c:forEach items="${pager.list}" var="item">
                    <p style="font-weight: bolder;font-size: x-large; font-family:Lucida Calligraphy, cursive, serif, sans-serif;color: #4d5b76">
                            ${item.cname}
                    </p>
                    <p style="font-size: large">
                            ${item.ccontent}
                    </p>
                </c:forEach>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
</html>
