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

            <div id="funcCon" class="trainApp"></div>
            <div class="homezoneall">
                <div class="homezonecontent" style="margin-top:0px">
                    <div class="gl_06_title">
                        ${exam.examTitle} 考试信息
                    </div>

                    <div id="content_02">
                        <table class="table1" width="70%" cellspacing="0" cellpadding="0" align="center">
                            <tr>
                                <th width="55%" align="left">考试名称：</th>
                                <td>${exam.examTitle }</td>
                            </tr>
                            <tr>
                                <th align="left">开始时间：</th>
                                <td><fmt:formatDate value="${exam.startTime }" pattern="yyyy-MM-dd HH:mm"/></td>
                            </tr>
                            <tr>
                                <th align="left">结束时间：</th>
                                <td><fmt:formatDate value="${exam.endTime }" pattern="yyyy-MM-dd HH:mm"/></td>
                            </tr>
                            <tr>
                                <th align="left">考试总时间长：</th>
                                <td>${exam.totalTime }</td>
                            </tr>
                            <tr>
                                <th align="left">考试类型：</th>
                                <td>
                                    <c:if test="${exam.examType == 1}">
                                        <%=Constants.S_EXAMTYPE_OFFLINE %>
                                    </c:if>
                                    <c:if test="${exam.examType == 0}">
                                        <%=Constants.S_EXAMTYPE_ONLINE %>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th align="left">答卷方式：</th>
                                <td>
                                    <c:if test="${exam.examStyle == 0}">
                                        <%=Constants.S_EXAMSTYLE_OPEN %>
                                    </c:if>
                                    <c:if test="${exam.examStyle == 1}">
                                        <%=Constants.S_EXAMSTYLE_CLOSE %>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th align="left">试卷成绩与平常成绩的比例：</th>
                                <td>${exam.proportion }</td>
                            </tr>
                            <tr>
                                <th align="left">考试是否允许报名：</th>
                                <td>
                                    <c:if test="${exam.isApply == 0}">
                                        否
                                    </c:if>
                                    <c:if test="${exam.isApply == 1}">
                                        是
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th align="left">试卷是否需要审批：</th>
                                <td>
                                    <c:if test="${exam.isNeedApprove == 0}">
                                        否
                                    </c:if>
                                    <c:if test="${exam.isNeedApprove == 1}">
                                        是
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th align="left">考试说明：</th>
                                <%--<td><bean:write name="exam" property="examDescription" filter="false"/></td>--%>
                                <td>${exam.examDescription }</td>
                            </tr>
                        </table>
                    </div>
                    <div class="btnContainer">
                        <div class="clearfix">
                            <a href="../examManage/toExamManageHome.do?examForTrainFlag=1"
                               class="btn-orange-l"><span class="btn-orange-r">返&nbsp;&nbsp;回</span></a>
                        </div>
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

<link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
<link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/common/home.js"></script>

<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>

<style type="text/css">
    td {
        text-align: left;
    }
</style>

</body>
</html>
