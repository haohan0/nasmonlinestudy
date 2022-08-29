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
<div class="container-fluid">
    <div class="row-fluid">
        <form class="form-inline" method="post"
              action="<%=basePath%>sys/student?cmd=stulist">
            <input class="input-xlarge" placeholder="用户名..." name="username"
                   type="text" value="${param.username}">
            <input class="btn icon-search" type="submit" value="查询" />
        </form>

        <div class="well">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        角色ID
                    </th>
                    <th>
                        用户名
                    </th>
                    <!--<th>
                        用户密码
                    </th>
                    --><th>
                    用户真实名字
                </th>
                    <th>
                        用户状态
                    </th>
                    <th style="width: 90px;">
                        任务情况
                    </th>
                    <th style="width: 90px;">
                        成绩信息
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pager.list}" var="item">
                    <tr>
                        <td>
                                ${item.roleid}
                        </td>
                        <td>
                                ${item.username}
                        </td>
                        <!--<td>
										${item.userpwd}
									</td>
									--><td>
                            ${item.usertruename}
                    </td>
                        <td>
                            <c:choose>
                                <c:when test="${item.userstate==\"1\"}">
                                    正常
                                </c:when>
                                <c:otherwise>锁定</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="<%=basePath%>/user/file?cmd=taskworklist&id=${item.username}">任务详情</a>
                        </td>
                        <td>
                            <a href="<%=basePath%>sys/student?cmd=gradelist&id=${item.username}">成绩详情</a>
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
                            <a href="<%=basePath%>sys/student?cmd=stulist&index=${pager.pagectrl.currentindex-1}">上一页</a>
                        </c:if>
                    </li>

                    <c:forEach begin="${pager.pagectrl.minpage}" step="1" end="${pager.pagectrl.maxpage}" var="index">
                        <li>
                            <c:if test="${pager.pagectrl.currentindex==index}" var="t">
                                <a style="color:red;background-color:#bbb">${index}</a>
                            </c:if>
                            <c:if test="${!t}">
                                <a href="<%=basePath%>sys/student?cmd=stulist&index=${index}">${index}</a>
                            </c:if>
                        </li>
                    </c:forEach>

                    <li>
                        <c:if test="${pager.pagectrl.currentindex==pager.pagectrl.pagecount}" var="fp">
                            <a style="disabled:true">下一页</a>
                        </c:if>
                        <c:if test="${!fp}">
                            <a href="<%=basePath%>sys/student?cmd=stulist&index=${pager.pagectrl.currentindex+1}">下一页</a>
                        </c:if>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
