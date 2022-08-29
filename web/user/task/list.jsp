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
                <tr>
                    <th>
                        任务id
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
                        查看
                    </th>
                    <th>
                        提交
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pager.list}" var="item" varStatus="work">
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
                        <c:set var="sum" value="0"></c:set>
                        <c:forEach items="${pager2.list}" var="taskstate">
                            <c:if test="${taskstate.taskid==item.taskid}">
                                <c:set var="sum" value="1"></c:set>
                            </c:if>
                        </c:forEach>
                        <td>
                            <c:choose>
                                <c:when test="${sum==\"1\"}">
                                    已完成
                                </c:when>
                                <c:otherwise>
                                    未完成
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="<%=basePath%>user/task?cmd=tasklist&id=${item.taskid}">前往编译</a>
                        </td>
                        <td>
                            <a href="<%=basePath%>user/file?cmd=toadd&id=${item.taskid}">提交文件</a>
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
                            <a href="<%=basePath%>user/task?cmd=userlist&index=${pager.pagectrl.currentindex-1}">上一页</a>
                        </c:if>
                    </li>

                    <c:forEach begin="${pager.pagectrl.minpage}" step="1" end="${pager.pagectrl.maxpage}" var="index">
                        <li>
                            <c:if test="${pager.pagectrl.currentindex==index}" var="t">
                                <a style="color:red;background-color:#bbb">${index}</a>
                            </c:if>
                            <c:if test="${!t}">
                                <a href="<%=basePath%>user/task?cmd=userlist&index=${index}">${index}</a>
                            </c:if>
                        </li>
                    </c:forEach>

                    <li>
                        <c:if test="${pager.pagectrl.currentindex==pager.pagectrl.pagecount}" var="fp">
                            <a style="disabled:true">下一页</a>
                        </c:if>
                        <c:if test="${!fp}">
                            <a href="<%=basePath%>user/task?cmd=userlist&index=${pager.pagectrl.currentindex+1}">下一页</a>
                        </c:if>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
