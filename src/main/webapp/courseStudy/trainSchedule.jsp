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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><%=Constants.systemName%>
    </title>
    <META content="教育培训系统" name="keywords">
    <meta name="description" content=""/>
    <link href="/js/nav/xy_style.css" rel="stylesheet" type="text/css"/>
    <link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link id="rsmRcmbook" href="/css/resourceManage/rsmRcmbook.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/jquery-json.js"></script>
    <script type="text/javascript" src="/js/nav/roll.js"></script>
    <script type="text/javascript">
        $(function () {
            var url = "/image/picturebase/" + $("#head2 input").val() + ".jpg";
            // $("#head2").css("background-image","url("+url+")");
            rowspan("#_table1", 1);
            rowspan("#_table2", 1);
        })
    </script>
</head>
<body>
<div class="homezoneall">
    <div id="content_02">
        <div id="item_table">
            <c:if test="${train.trainWay == 0}">
                <span style="font-size:12px;color:red;position:absolute;left:80px;margin-bottom:10px;margin-top:-20px;">学时统计: ${progressFlag } 学时</span>
                <c:if test="${onlineItemList != null}">
                    <!-- <div style="text-align:center;font-size:13px;font-weight:bold;background-color:#8398D7;height:30px;line-height:30px;color:#F2F2F2">线上学习安排</div>-->
                    <table class="homecontenttable" id="_table1">
                        <tr class="tableTh">
                            <th>起止日期</th>
                            <th>课程</th>
                            <th>课程学时</th>
                            <th>主讲人</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="item" items="${onlineItemList}" varStatus="status">
                            <tr>
                                <td align='center' width="25%">
                                    ${item.scheduleStartTime }
                                    ~
                                    ${item.scheduleEndTime }
                                </td>
                                <td align='center' width="25%">
                                    ${item.courseName }
                                </td>
                                <td align='center' width="15%">
                                    <c:if test="${item.studyProgress >= 80.00 }">${item.classHour }</c:if>
                                    <c:if test="${item.studyProgress < 80.00 }">0</c:if>
                                </td>
                                <td align='center' width="15%">
                                    ${item.teacherName}
                                </td>
                                <td align='center' width="20%">
                                    <c:if test="${joinTcourseStudy}">
                                        <a href="javascript:void(0);"
                                           onclick="window.open('/courseStudy/scormStudy.do?courseID=${item.courseId}','课件预览','height=700,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')">学习</a>
                                    </c:if>
                                    <c:if test="${!joinTcourseStudy}">
                                        <a href="javascript:void(0);"
                                           onclick="window.open('/courseStudy/previewStudy.do?courseID=${item.courseId}','课件预览','height=700,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')">预览</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </c:if>

            <c:if test="${train.trainWay == 2}">
                <span style="font-size:12px;color:red;position:absolute;left:80px;margin-bottom:10px;margin-top:-20px;">学时统计: ${progressFlag } 学时</span>
                <c:if test="${onlineItemList != null}">
                    <div class="title"><strong>&nbsp;&nbsp;&nbsp;线上学习安排</strong></div>
                    <table class="homecontenttable" id="_table1">
                        <tr class="tableTh">
                            <th>起止日期</th>
                            <th>课程</th>
                            <th>课程学时</th>
                            <th>主讲人</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach var="item" items="${onlineItemList}" varStatus="status">
                            <tr>
                                <td align='center' width="25%">
                                    ${item.scheduleStartTime}
                                    ~
                                    ${item.scheduleEndTime}
                                </td>
                                <td align='center' width="25%">
                                    ${item.courseName}
                                </td>
                                <td align='center' width="15%">
                                    <c:if test="${item.studyProgress >= 80.00 }">${item.classHour }</c:if>
                                    <c:if test="${item.studyProgress < 80.00 }">0</c:if>
                                </td>
                                <td align='center' width="15%">
                                    ${item.teacherName}
                                </td>
                                <td align='center' width="20%">
                                    <c:if test="${joinTrainFlag}">
                                        <a href="javascript:void(0);"
                                           onclick="window.open('/courseStudy/scormStudy.do?courseID=${item.courseId}','课件预览','height=700,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=courseStudy=no')">学习</a>
                                    </c:if>
                                    <c:if test="${!joinTrainFlag}">
                                        <a href="javascript:void(0);"
                                           onclick="window.open('/courseStudy/previewStudy.do?courseID=${item.courseId}','课件预览','height=700,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')">预览</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <br/><br/>
            </c:if>
        </div>
    </div>
</div>
<div class="clr"></div>
<br/>
</body>
</html>