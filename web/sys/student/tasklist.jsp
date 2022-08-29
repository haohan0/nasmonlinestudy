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

    <title>用户管理</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/theme.css">
    <script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
</head>

<body class="content1">
<script>
    $('#a_leader_txt', parent.document).html('学生任务信息');
</script>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="well">
            <table class="table">
                <thead>
                <tr>
                    <th>
                    任务编号
                    </th>
                    <th>
                        任务名
                    </th>
                    <th>
                        完成情况
                    </th>
                    <th>
                        文件名
                    </th>
                    <th>
                        下载
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pager2.list}" var="item">
                    <tr>
                        <td>
                                ${item.taskid}
                        </td>
                        <td>
                                ${item.taskname}
                        </td>
                        <c:set var="state" value="0"></c:set>
                        <c:set var="filename" value="0"></c:set>
                        <c:set var="filepath" value="0"></c:set>
                        <c:forEach items="${pager.list}" var="work">
                            <c:if test="${work.taskid==item.taskid}">
                                <c:set var="state" value="1"></c:set>
                                <c:set var="filename" value="${work.filename}"></c:set>
                                <c:set var="filepath" value="${work.filepath}"></c:set>
                            </c:if>
                        </c:forEach>
                        <td>
                            <c:choose>
                                <c:when test="${state==\"1\"}">
                                    已完成
                                </c:when>
                                <c:otherwise>
                                    未完成
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${filename==\"0\"}">
                                    无
                                </c:when>
                                <c:otherwise>
                                    ${filename}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="<%=basePath%>user/file?cmd=findfile&filename=${filename}&filepath=${filepath}">下载</a>
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
                            <a href="<%=basePath%>sys/student?cmd=tasklist&index=${pager.pagectrl.currentindex-1}">上一页</a>
                        </c:if>
                    </li>

                    <c:forEach begin="${pager.pagectrl.minpage}" step="1" end="${pager.pagectrl.maxpage}" var="index">
                        <li>
                            <c:if test="${pager.pagectrl.currentindex==index}" var="t">
                                <a style="color:red;background-color:#bbb">${index}</a>
                            </c:if>
                            <c:if test="${!t}">
                                <a href="<%=basePath%>sys/student?cmd=tasklist&index=${index}">${index}</a>
                            </c:if>
                        </li>
                    </c:forEach>

                    <li>
                        <c:if test="${pager.pagectrl.currentindex==pager.pagectrl.pagecount}" var="fp">
                            <a style="disabled:true">下一页</a>
                        </c:if>
                        <c:if test="${!fp}">
                            <a href="<%=basePath%>sys/student?cmd=tasklist&index=${pager.pagectrl.currentindex+1}">下一页</a>
                        </c:if>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
