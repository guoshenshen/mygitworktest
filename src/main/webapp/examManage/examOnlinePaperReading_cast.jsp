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
<!--[if IE 6 ]> <html id="ne_wrap" class="ne_ua_ie6 ne_ua_ielte8"> <![endif]-->
<!--[if IE 7 ]> <html id="ne_wrap" class="ne_ua_ie7 ne_ua_ielte8"> <![endif]-->
<!--[if IE 8 ]> <html id="ne_wrap" class="ne_ua_ie8 ne_ua_ielte8"> <![endif]-->
<!--[if IE 9 ]> <html id="ne_wrap" class="ne_ua_ie9"> <![endif]-->
<!--[if (gte IE 10)|!(IE)]><!-->
<html>
<!--<![endif]-->
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <title></title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <!-- InstanceBeginEditable name="main" -->
            <div id="content_02">
                <form id="form1" name="form1" method="post">
                    <table class="table table-striped table-bordered batchOperation" rules="cols" width="100%" cellspacing="0" cellpadding="0">
                        <tr class="tableTh">
                            <th> 员工编号</th>
                            <th> 员工姓名</th>
                            <th> 部&nbsp;&nbsp;&nbsp;&nbsp;门</th>
                            <th> 考试状态</th>
                            <th> 员工提交时间</th>
                            <th> 批改状态</th>
                            <th> 进行批改</th>
                        </tr>
                        <c:if test="${onlineReadingList != null}">
                            <c:forEach var="ExamOnlinePaperReadingForm" items="${onlineReadingList}" varStatus="status">
                                <tr>
                                    <td align="center">
                                        ${ExamOnlinePaperReadingForm.empCode }
                                    </td>
                                    <td align="center">
                                        ${ExamOnlinePaperReadingForm.employeeName }
                                    </td>
                                    <td align="center">
                                        ${ExamOnlinePaperReadingForm.orgName }
                                    </td>
                                    <td align="center">
                                        ${ExamOnlinePaperReadingForm.examPartStatus }
                                    </td>
                                    <td align="center">
                                        ${ExamOnlinePaperReadingForm.submitDate }
                                    </td>
                                    <c:if test="${ExamOnlinePaperReadingForm.readingStatus == Constants.S_PAPERAUDIT_NONEED}">
                                        <td colspan="2">
                                            ${ExamOnlinePaperReadingForm.readingStatus }
                                        </td>
                                    </c:if>
                                    <c:if test="${ExamOnlinePaperReadingForm.readingStatus == Constants.S_PAPERAUDIT_NONEED}">
                                        <td align="center">
                                            ${ExamOnlinePaperReadingForm.readingStatus }
                                        </td>
                                        <td align="center">
                                            <a href="examResultManage.do?method=toViewUserPaperByOperatorIdAndExamId&eid=${ExamOnlinePaperReadingForm.examId}&operatorId=${ExamOnlinePaperReadingForm.employeeID}&replyId=${ExamOnlinePaperReadingForm.replyId}">
                                                批改
                                            </a>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${onlineReadingList == null or onlineReadingList == '' or onlineReadingList == [] }">
                            <tr>
                                <td colspan="7" align="left">暂无数据</td>
                            </tr>
                        </c:if>
                    </table>
                </form>
                <br/>
                <div class="btnContainer">
                    <div class="clearfix">
                        <a href="../examManage/toExamManageHome.do" class="btn-orange-l">
                            <span class="btn-orange-r">返&nbsp;回</span>
                        </a>
                    </div>
                </div>
                <div class="clr"></div>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<c:if test="${alertString != null}">
    <script type="text/javascript">
        jAlert("${alertString}", '');
    </script>
</c:if>
<link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
<link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
<link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/common/home.js"></script>
<!-- InstanceBeginEditable name="head" --><!-- InstanceEndEditable -->
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>

<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
</body>
</html>
