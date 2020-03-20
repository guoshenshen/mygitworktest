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
    <link id="style_td_Id" href="/js/jianyueStyle/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/js/jianyueStyle/style_gl.css" rel="stylesheet" type="text/css"/>
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
        $(document).ready(function () {
            $('#stopUse').find('li:eq(1)').find('a').css({'color': '#FF7800', 'font-weight': 'bold'})
        });
        $(function () {
            var url = "";
            if ($("#head2 input").val() != ""){
                url = "../image/picturebase/" + $("#head2 input").val() + ".jpg";
            } else{
                url = "../image/picturebase/4.jpg";
            }
            var trainWay = $('#_table1 input').val();
            var trainId = $('#_trainId').val();
            var userId = $('#_userId').val();
            var userJoinedFlag = $('#_userJoinedFlag').val();
            var courseId = "";
            var str = "";
            var newDiv = $("<div class='tTip'></div>");
        });
        function controlScheduleDownload(fileName, filePath) {
            var fileUrl = '/mixtraining/downloadScheduleItemFile.jsp?fileName=' + fileName + '&filePath=' + filePath;
            var userId = $('#_userId').val();
            var userJoinedFlag = $('#_userJoinedFlag').val();
            if (userId == 0) {
                alert("请登录系统后再下载，谢谢！");
            } else if (userJoinedFlag == 'false') {
                alert("管理员尚未对您的培训学时进行确认，不能下载，谢谢！");
            } else {
                window.open(fileUrl);
            }
        }
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
            <c:if test="${reviseOnlineItemList == null}">
                <c:if test="${reviseOfflineItemList == null}">
                    <div class="emptyInfo">暂无培训日程...</div>
                </c:if>
            </c:if>
            <div id="item_table">
                <input value="${train.ID }" id="_trainId" type="hidden"/>
                <input value="${userId }" id="_userId" type="hidden"/>
                <input value="${joinTrainFlag }" id="_userJoinedFlag" type="hidden"/>
                <c:if test="${train.trainWay == 0}">
                    <c:if test="${reviseOnlineItemList != null}">
                        <span style="font-size:12px;color:red;position:absolute;left:80px;margin-bottom:10px;margin-top:-20px;">学时统计: ${progressFlag } 学时</span>
                        <span style="font-size:12px;color:red;position:absolute;right:80px;margin-bottom:10px;margin-top:-20px;">若无法正常学习课件，请点击<a href="download/VGAPlayer.zip">此处</a>手工下载控件安装</span>
                        <table class="homecontenttable" id="_table1"><input value="${train.trainWay }" type="hidden"/>
                            <tr class="tableTh">
                                <th>时间</th>
                                <th>课程</th>
                                <th>课程学时</th>
                                <th>主讲人</th>
                                <th>学习进度</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach var="itemList" items="${reviseOnlineItemList}" varStatus="status">
                                <logic:iterate id="item" name="itemList" indexId="index">
                                    <tr>
                                        <td align='center' width="18%">
                                            ${item.scheduleStartTime }~${item.scheduleEndTime }
                                        </td>
                                        <td align='center' width="32%">
                                            ${item.courseName }
                                            <input value="${item.courseId }" type="hidden"/>
                                        </td>
                                        <td align='center' width="15%">
                                                ${item.classHour }
                                        </td>
                                        <td align='center' width="15%">
                                            ${item.teacherName }
                                        </td>
                                        <td align='center' width="10%">
                                            ${item.studyProgress }%
                                        </td>
                                        <td align='center' width="10%">
                                            <c:if test="${item.courseId != null}">
                                                <c:if test="${joinTrainFlag }">
                                                    <a href="javascript:void(0);"
                                                       onclick="window.open('../courseStudy/scormStudy.do?courseId=${item.courseId}&train_id=${train.id }')">学习</a>
                                                </c:if>
                                                <c:if test="${!joinTrainFlag }">
                                                    <c:if test="${userId == 0}">
                                                        <a href="javascript:void(0);"
                                                           onclick="window.open('../courseStudy/previewScheduleCourse.do?courseID=${item.courseId}')">预览</a>
                                                    </c:if>
                                                    <c:if test="${userId != 0}">
                                                        <a href="javascript:void(0);"
                                                           onclick="window.open('../courseStudy/previewStudy.do?courseID=${item.courseId}')">预览</a>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${item.courseId == null}">--</c:if>
                                        </td>
                                    </tr>
                                </logic:iterate>
                            </c:forEach>
                        </table>
                    </c:if>
                </c:if>
                <c:if test="${train.trainWay == 2}">
                    <c:if test="${reviseOnlineItemList != null}">
                        <span style="font-size:12px;color:red;position:absolute;left:80px;margin-bottom:10px;margin-top:-20px;">学时统计: ${progressFlag } 学时</span>
                        <div class="title"><strong>&nbsp;&nbsp;&nbsp;线上学习安排</strong>
                            <span style="font-size:12px;color:red;position:absolute;right:80px">若无法正常学习课件，请点击<a
                                href="download/VGAPlayer.zip">此处</a>手工下载控件安装
                            </span>
                        </div>
                        <table class="homecontenttable" id="_table1"><input value="${train.trainWay }" type="hidden"/>
                            <tr class="tableTh">
                                <th><span class="pagebodybufferMask"></span><span>时间</span></th>
                                <th><span class="pagebodybufferMask"></span><span>课程</span></th>
                                <th><span class="pagebodybufferMask"></span><span>课程学时</span></th>
                                <th><span class="pagebodybufferMask"></span><span>主讲人</span></th>
                                <th><span class="pagebodybufferMask"></span><span>学习进度</span></th>
                                <th><span class="pagebodybufferMask"></span><span>操作</span></th>
                            </tr>
                            <c:forEach var="itemList" items="${reviseOnlineItemList}" varStatus="status1">
                                <c:forEach var="item" items="${itemList}" varStatus="status2">
                                    <tr>
                                        <td align='center' width="18%">
                                            ${item.scheduleStartTime} ~ ${item.scheduleEndTime}
                                        </td>
                                        <td align='center' width="32%">
                                            ${item.courseName}
                                            <input value="${item.courseId }" type="hidden"/>
                                        </td>
                                        <td align='center' width="15%">
                                                ${item.classHour }
                                        </td>
                                        <td align='center' width="10%">
                                            ${item.teacherName }
                                        </td>
                                        <td align='center' width="10%">
                                            ${item.studyProgress}%
                                        </td>
                                        <td align='center' width="15%">
                                            <c:if test="${item.courseId != null}">
                                                <c:if test="${joinTrainFlag}">
                                                    <a href="javascript:void(0);"
                                                       onclick="window.open('../courseStudy/scormStudy.do?courseId=${item.courseId}&train_id=${train.ID }')">学习</a>
                                                </c:if>
                                                <c:if test="${!joinTrainFlag }">
                                                    <c:if test="${userId == 0}">
                                                        <a href="javascript:void(0);"
                                                           onclick="window.open('../courseStudy/previewScheduleCourse.do?courseID=${item.courseId}')">预览</a>
                                                    </c:if>
                                                    <c:if test="${userId != 0}">
                                                        <a href="javascript:void(0);"
                                                           onclick="window.open('/courseStudy/previewStudy.do?courseID=${item.courseId}')">预览</a>
                                                    </c:if>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${item.courseId == null}">--</c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </table>
                    </c:if>
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
<div id="toMyCourseStudyInfo" style="display:none;cursor:default;overflow:hidden;">
    <div id='_pop_win'><h2>课程信息<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
    <div id="myCourseStudyInfo"></div>
</div>

<script type="text/javascript">
    function watchCourseStudyDetail(courseId, trainId) {
        var $left = $(window).width();
        var $top = $(window).height();
        var iframeContent = "<iframe height='480' scrolling='auto' width='100%' class='pop_iframe' src='selectCourse.do?method=CourseDetail&courseId=" + courseId + "&trainId=" + trainId + "'> </iframe>"
        $("#myCourseStudyInfo").html(iframeContent);
        $.blockUI({
            message: $("#toMyCourseStudyInfo"),
            css: {width: '900px', height: '600px', left: ($left - 900) / 2 - 10, top: ($top - 600) / 2 - 10}
        });
        $("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
    }
</script>
</body>

</html>