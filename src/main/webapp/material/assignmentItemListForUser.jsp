<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String userRolesInString = "";
    try {
        EosOperator user = (EosOperator) session.getAttribute(Constants.USERINFO_KEY);
    } catch (Exception ex) {
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <title><%=Constants.systemName%>
    </title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link id="rsmRcmbook" href="/css/resourceManage/rsmRcmbook.css" rel="stylesheet" type="text/css"/>
    <link href="/css/tip.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/trainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/newTrainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/jquery-json.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/nav/trainClassMenu.js"></script>
    <script type="text/javascript" src="/js/nav/roll.js"></script>
    <script type="text/javascript" src="/js/widget/window.js"></script>
    <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
    <script type="text/javascript" src="/js/nav/umenu.js"></script>
    <style type="text/css">
        .waitInfo {
            font-family: Microsoft YaHei;
            font-size: 18px;
            text-align: center;
            color: #919191;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            var trainId = $('#_trainId').val();
            var userId = $('#_userId').val();
            var userJoinedFlag = $('#_userJoinedFlag').val();
            var courseId = "";
            var str = "";
            var newDiv = $('<div class="tTip"></div>');
        })
    </script>
</head>
<body>
<div id="simpleNavBar"></div>
<div id="backgroundImg"><img src=''/></div>
<div id='bufferMask'></div>
<div id="pagebody">
    <div id="headerbody">
        <div id="head2">
            <input type="hidden" value="${train.topbandId }"/>
        </div>
        <div id="head-content" class="outstanding">
            <p id="title">${train.trainName }</p>
            <p id="time">时间：
                <fmt:formatDate value="${train.startTime }" pattern="yyyy-MM-dd HH:mm"/>
                ~
                <fmt:formatDate value="${train.endTime }" pattern="yyyy-MM-dd HH:mm"/>
            </p>
            <p id="location">
                <c:if test="${train.location != null}">地点：${train.location }</c:if>
            </p>
        </div>
        <div id="menubar">
            <div class='pagebodybufferMask'></div>
            <span id="joinTrainFlag" style="display:none;">${joinTrainFlag }</span>
            <div id="trainClassMenu">
                <ul></ul>
            </div>
        </div>
    </div>
    <div id="mainbody">
        <div class='pagebodybufferMask'></div>
        <div class="mainContent">
            <c:if test="${assignmentItemList == null}">
                <div class="emptyInfo">暂无作业...</div>
            </c:if>
            <div id="item_table">
                <input value="${train.ID }" id="_trainId" type="hidden"/>
                <input value="${operatorId }" id="operatorId" type="hidden"/>
                <input value="${rootUrl }" id="rootUrl" type="hidden"/>
                <c:if test="${assignmentItemList != null}">
                    <table class="homecontenttable" id="_table1">
                        <tr class="tableTh">
                            <th>作业名称</th>
                            <th>创建时间</th>
                            <th>创建人</th>
                            <th>状态</th>
                            <th>提交时间</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="item" items="${assignmentItemList}" varStatus="status">
                            <tr>
                                <td align='center' width="25%">
                                    ${item.assignName }
                                </td>
                                <td align='center' width="15%">
                                    <fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td align='center' width="15%">
                                    ${item.assignCreatorName }
                                </td>
                                <td align='center' width="12%">
                                    <c:if test="${item.status == 1}"><font style="font-weight:bold;">已提交</font></c:if>
                                    <c:if test="${item.status == 0}"><font style="font-weight:bold;">未提交</font></c:if>
                                    <c:if test="${item.status == -1}"><font style="font-weight:bold;">作业被打回</font></c:if>
                                    <c:if test="${item.status == 2}"><font style="font-weight:bold;">教师已查阅</font></c:if>
                                </td>
                                <td align='center' width="15%">
                                    <c:if test="${item.submitTime != null}">
                                        <fmt:formatDate value="${item.submitTime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                                    </c:if>
                                    <c:if test="${item.submitTime == null}">
                                        --
                                    </c:if>
                                </td>
                                <td align='center' width="18%">
                                    <a href="javascript:void(0);" onclick="javascript:viewAssignment('${item.assignId}')">查看作业</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
            <br/><br/>
        </div>
    </div>
    <div id="bottombody">
        <div class='pagebodybufferMask'></div>
        <%=Constants.systemBottom %>
    </div>
</div>
<script type="text/javascript">
    function viewAssignment(assignmentId) {
        var operatorId = $('#operatorId').val();
        var rootUrl = $('#rootUrl').val();
        var assignmentUrl = "../assignment/intoAssignmentForUser.do?operatorId=" + operatorId + "&assignId=" + assignmentId;
        window.open(assignmentUrl);
    }
</script>
</body>

</html>