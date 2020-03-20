<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <meta name="description" content="中国科协系统智能学习平台培训项目--${train.trainName}"/>
    <meta name="keywords" content="中国科协;干部教育;培训;${train.trainName}"/>
    <%--<script id="allmobilize" charset="utf-8"
            src="http://ysp.cnic.cn:30000/c98b9d4a79156fd9bc4515637d3256ca/allmobilize.min.js"></script>--%>
    <title>${train.trainName}</title>

    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/js/jianyueStyle/style_gl.css" rel="stylesheet" type="text/css"/>
    <link id="rsmRcmbook" href="/css/resourceManage/rsmRcmbook.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css"/>
    <link href="/css/trainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/newTrainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/calendar.js"></script>
    <script type="text/javascript" src="/js/jquery-json.js"></script>
    <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/nav/roll.js"></script>
    <script type="text/javascript" src="/js/nav/trainClassMenu.js"></script>
    <script type="text/javascript" src="/js/login/login.js"></script>
    <script type="text/javascript" src="/js/nav/umenu.js"></script>
</head>
<body>
<div id="backgroundImg"><img src=''/></div>
<div id='bufferMask'></div>
<div id="pagebody">
    <div id="headerbody">
        <div id="head2">
            <input type="hidden" value="${train.topbandId }"/>
            <input type="hidden" id="orgId" value="${train.orgId }"/>
        </div>
        <div id="head-content">
            <p id="title">${train.trainName }</p>
            <p id="time">时间：
                <fmt:formatDate value="${train.startTime}" pattern="yyyy-MM-dd HH:mm"/>
                ~
                <fmt:formatDate value="${train.endTime}" pattern="yyyy-MM-dd HH:mm"/>
            </p>
            <p id="location">
                <c:if test="${train.location != null }" >
                    地点：${train.location }
                </c:if>
            </p>
        </div>
        <div id="menubar">
            <div class='pagebodybufferMask'></div>
            <span id="joinTrainFlag" style="display:none;">${joinTrainFlag }</span>
            <div id="trainClassMenu" class="enterTrain">
                <ul></ul>
            </div>
            <input value="${train.ID }" id="_trainId" type="hidden"/>
            <input value="${userId}" id="_userId" type="hidden"/>
            <input value="${joinTrainFlag }" id="_userJoinedFlag" type="hidden"/>
            <input value="${isTrainAdmin }" id="_isTrainAdmin" type="hidden"/>
        </div>
    </div>

    <div id="mainbody">
        <div class='pagebodybufferMask'></div>
        <div id="trainInfo" class="mainContent">
            <div id="sponsor" class="animate component">
                <div class="Tag"></div>
                <div class="sponsor_left"><h3>${train.trainName }</h3>
                    <div class="sponsor_left_table">
                        <table cellspacing="0" width="95%" align="center" cellpadding="0">
                            <tr>
                                <td width="30%">主办：${train.orgName}</td>
                            </tr>
                            <tr>
                                <td width="20%" align="left">联系人：${train.organizerName}</td>
                            </tr>
                            <tr>
                                <td width="20%" align="left">联系人Email：${train.organizerEmail}</td>
                            </tr>
                            <tr>
                                <td width="20%" align="left">联系人电话：${train.telephone}</td>
                            </tr>
                            <tr>
                                <c:if test="${applyTrainStatus != 8 }" >
                                    <c:if test="${applyTrainStatus != 7 }" >
                                        <td width="20%" align="left">
                                            报名时间：<fmt:formatDate value="${train.programStartTime}" pattern="yyyy-MM-dd HH:mm"/>~
                                            <fmt:formatDate value="${train.programEndTime}" pattern="yyyy-MM-dd HH:mm"/>
                                        </td>
                                    </c:if>
                                </c:if>
                            </tr>
                            <tr>
                                <td width="20%" align="left">培训方式：
                                    <c:choose>
                                        <c:when test="${train.trainWay == 0 }">线上培训</c:when>
                                        <c:when test="${train.trainWay == 1 }">线下培训</c:when>
                                        <c:when test="${train.trainWay == 2 }">混合培训(线上+线下)</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="sponsor_right">
                    <c:if test="${applyTrainStatus == 1 }" ><div class="train_signupBtn">未审核</div></c:if>
                    <c:if test="${applyTrainStatus == 2 }" ><div class="train_signupBtn">审核通过</div></c:if>
                    <c:if test="${applyTrainStatus == 3 }" ><div class="train_signupBtn">审核未通过</div></c:if>
                    <c:if test="${applyTrainStatus == 4 }" >
                        <c:if test="${userId == -1 }" >
                            <div class="train_signupBtn">我要报名<input type="hidden" value="-1"/></div>
                        </c:if>
                        <c:if test="${userId != -1 }" >
                            <div class="train_signupBtn">我要报名<input type="hidden" value="${train.ID }"/></div>
                        </c:if>
                    </c:if>
                    <c:if test="${applyTrainStatus == 5 }" ><div class="train_unsignupBtn">报名尚未开始</div></c:if>
                    <c:if test="${applyTrainStatus == 6 }" ><div class="train_unsignupBtn">报名已截止</div></c:if>
                    <c:if test="${applyTrainStatus == 7 }" ><div class="train_unsignupBtn">尚未设置报名时间</div></c:if>
                    <c:if test="${applyTrainStatus == 8 }" ><div class="train_unsignupBtn">不允许报名</div></c:if>
                </div>
            </div>
            <div class="clr"></div>

            <c:if test="${train.trainGoal != null }" >
                <div id="target" class="animate component">
                    <div class="Tag"></div>
                    <p>${train.trainGoal}</p>
                </div>
            </c:if>

            <c:if test="${train.trainingContent != null }" >
                <div id="content" class="animate component">
                    <div class="Tag"></div>
                    <p>${train.trainingContent}</p>
                </div>
            </c:if>

            <c:if test="${train.attendants != null }" >
                <div id="object" class="animate component">
                    <div class="Tag"></div>
                    <p>${train.attendants}</p>
                </div>
            </c:if>

            <c:if test="${train.comment != null }" >
                <div id="comment" class="animate component">
                    <div class="Tag"></div>
                    <p>${train.comment}</p>
                </div>
            </c:if>
            <div class="clr"></div>

            <c:if test="${onlineItemList != null }" >
                <div id="taining_time">
                    <h3>线上学习</h3>
                    <div id="taining_time_table">
                        <table class="homecontenttable" id="_table1"><input value="${train.trainWay }" type="hidden"/>
                            <c:forEach var="item" items="onlineItemList" varStatus="status">
                                <tr>
                                    <td align='center' width="25%">
                                        <%--<fmt:formatDate value="${item.scheduleStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                        &nbsp;~&nbsp;
                                        <fmt:formatDate value="${item.scheduleEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                                        ${item.scheduleStartTime}&nbsp;~&nbsp;${item.scheduleEndTime}
                                    </td>
                                    <td align='left' width="40%">
                                        ${item.courseName}
                                        <input value="${item.courseId }" type="hidden"/>
                                    </td>
                                    <td align='left' width="35%">
                                        ${item.teacherName}
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${offlineItemList != null }" >
                <div id="taining_time">
                    <h3>线下讲座</h3>
                    <div id="taining_time_table">
                        <table class="homecontenttable" id="_table2">
                            <c:forEach var="item" items="offlineItemList" varStatus="status">
                                <tr>
                                    <td align='left' width="25%">
                                        <%--<fmt:formatDate value="${item.scheduleDate}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;
                                        <fmt:formatDate value="${item.scheduleStartTime}" pattern="yyyy-MM-dd HH:mm"/>~
                                        <fmt:formatDate value="${item.scheduleEndTime}" pattern="yyyy-MM-dd HH:mm"/>--%>
                                        ${item.scheduleDate}&nbsp;&nbsp;${item.scheduleStartTime}~${item.scheduleEndTime}
                                    </td>
                                    <td align='left' width="40%">${item.courseName}</td>
                                    <td align='left' width="35%">${item.teacherName}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <c:if test="${joinTrainFlag}" >
        <div class="fuc">
            <h3>公告动态</h3>
            <ul>
                <c:if test="${trainNewsList != null}" >
                    <c:forEach var="news" items="trainNewsList" varStatus="status" begin="0" end="3">
                        <li>
                            <a href="mtMixTrainNewsAction.do?method=queryForNews&trainId=${trainForm.train_id }" title="${news.newsTitle}">
                                <c:choose>
                                    <c:when test='${fn:length(news.newsTitle)>12}'>
                                        ${fn:substring(news.newsTitle,0,12) }...
                                    </c:when>
                                    <c:otherwise>
                                        ${news.newsTitle}
                                    </c:otherwise>
                                </c:choose>
                                <span style="right:5%;position:absolute">&nbsp;&nbsp;
                                    <fmt:formatDate value="${news.createTime }" pattern="yyyy-MM-dd"/>
                                </span>
                            </a>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
    </c:if>

    <c:if test="${joinTrainFlag}" >
        <div class="fuc">
            <h3>调查与考试</h3>
            <ul>
                <c:if test="${myTnNeedsList != null}" >
                    <c:forEach var="need" items="myTnNeedsList" varStatus="status" begin="0" end="1">
                        <li><a href="tnNeedsAction.do?method=myTnNeeds&trainId=${trainForm.train_id }">${need.theme}</a>
                        </li>
                    </c:forEach>
                </c:if>
                <c:if test="${myExamList != null}" >
                    <c:forEach var="exam" items="myExamList" varStatus="status" begin="0" end="1">
                        <li><a href="myExam.do?method=toMyExamHome&trainId=${trainForm.train_id }">${exam.examTitle}</a>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
    </c:if>
    <div>
        <c:if test="${hasCertificate != null}" >
            <div style="margin-bottom:20px;">
                <a href="javascript:void(0);" onclick="javascript:downloadCertificate();">
                    <img src="/image/zhengshuxiazai.png"/>
                </a>
            </div>
        </c:if>
        <div class="clr"></div>
        <div id="applyTrain" style="display:none;cursor:default;overflow:hidden;">
            <div id='_pop_win'><h2>培训报名<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
            <div id="applyTrainContent">
                <table class="applyTrainStyle">
                    <tr>
                        <td align=right>培训班名称:</td>
                        <td>${train.trainName }</td>
                    </tr>
                    <tr>
                        <td align=right>培训主办:</td>
                        <td>${train.sponsorName }</td>
                    </tr>
                    <tr>
                        <td align=right>培训时间:</td>
                        <td>
                            <fmt:formatDate value="${train.startTime}" pattern="yyyy-MM-dd HH:mm"/> ~
                            <fmt:formatDate value="${train.endTime}" pattern="yyyy-MM-dd HH:mm"/>
                        </td>
                    </tr>
                    <tr>
                        <td align=right>培训地点:</td>
                        <td>${train.location }</td>
                    </tr>
                    <tr>
                        <td align=right width=25%>对本次培训的建议或期待:</td>
                        <td colspan=2 align=left width=60% align="right"><textarea rows="10" cols="48"></textarea></td>
                    </tr>
                </table>
                <div style="padding:20px 0 5px 0;">
                    <input type="button" name="confirmApplyTrain" id="confirmApplyTrain" onclick="confirmApplyTrain(${train.ID })" value="确定报名"/>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="foot"></div>
<div id="simpleNavBar" style="position:fixed;top:0px;"></div>
<script type="text/javascript">
    var trainBasicOptions = {
        trainId: $("#menubar #_trainId").val()
    };

    var loginData = {
        nextActionUrl: "onlineTraining/viewTrain4Admin.do?trainId=" + trainBasicOptions.trainId
    };

    $(function () {
        if ($(".train_signupBtn input").val() != null) {
            $(".train_signupBtn").css("cursor", "pointer").bind('click', function () {
                var trainId = $(this).find('input').val();
                if (trainId == -1) {
                    jAlert('<font color=red>请先登陆系统,然后再报名！</font>','登陆提示',
                        function () {
                            var targetUrl = "onlineTraining/viewTrain4Admin.do?trainId=" + trainBasicOptions.trainId;
                            resetTargetUrlAfterLogon(targetUrl);
                            showLoginWin();
                        }
                    )
                } else {
                    applyTrain(trainId);
                }
            })
        }
        $(".score_star").each(function () {
            var score = $(this).find('input').val();
            if (score == 5) {
                var width = 80;
            } else {
                var width = score * 16;
            }
            $(this).find("li.current-rating").width(width);
        });
    })

    function applyTrain(trainId) {
        $.post("onlineTraining.do?method=ifRegisterable", {trainId: trainId},
            function (data) {
                if (data == 0) {
                    alert('本报名只对特定人员开放，目前您无报名权限。请联系相关培训管理人员!');
                } else if (data == 1) {
                    $.blockUI({
                        message: $("#applyTrain"),
                        css: {width: '600px', height: '482px', left: ($left - 600) / 2 - 10, top: ($top - 482) / 2 - 10}
                    });
                    $("a.pop_close_btn").click(function () {
                        $.unblockUI();
                    });
                }
            })
    }
    function confirmApplyTrain(trainId) {
        var applyContent = $('#applyTrain textarea').val();
        $.post('selectCourse.do?method=trainRegister', {trainId: trainId, remark: applyContent}, function (data) {
            jAlert('<font color=red>' + data + '</font>', '提示信息', function () {
                $.unblockUI();
                window.location.reload();
            });
        })
    }
    function downloadCertificate() {
        var userJoinedFlag = $('#_userJoinedFlag').val();
        var trainId = $('#_trainId').val();
        var userId = $('#_userId').val();
        var orgId = $('#orgId').val();
        var isTrainAdmin = $('#_isTrainAdmin').val();

        if (userId == -1){
            alert("请登录系统后下载，谢谢！");
        } else {
            if (userJoinedFlag == 'false'){
                alert("您没有参加此培训，不能下载证书，抱歉！");
            } else {
                if (isTrainAdmin == 1) {
                    alert("培训管理员没有培训证书，谢谢！");
                } else {
                    var certificateUrl = './mixtraining/downloadMyCertificate.jsp?trainId=' + trainId + '&file=' + userId + '&orgId=' + orgId;
                    window.open(certificateUrl);
                }
            }
        }
    }
</script>
</body>
</html>