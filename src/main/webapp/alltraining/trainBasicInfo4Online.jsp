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
    <meta name="description" content="中科院继续教育网培训项目--${train.trainName}"/>
    <meta name="keywords" content="中国科学院;继续教育;培训;${train.trainName}"/>
    <script id="allmobilize" charset="utf-8"
            src="http://ysp.cnic.cn:30000/c98b9d4a79156fd9bc4515637d3256ca/allmobilize.min.js"></script>
    <title>${train.trainName}</title>

    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/js/jianyueStyle/style_gl.css" rel="stylesheet" type="text/css"/>
    <link id="rsmRcmbook" href="/css/resourceManage/rsmRcmbook.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css"/>
    <link href="/css/trainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/newTrainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/css/courseStudy/styles.css">
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/calendar.js"></script>
    <script type="text/javascript" src="/js/jquery-json.js"></script>
    <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/nav/roll.js"></script>
    <script type="text/javascript" src="/js/nav/trainClassMenu.js"></script>
</head>
<body class="student">
<div id='bufferMask' style="background: white;"></div>
<div id="pagebody">
    <div id="headerbody">
        <div id="head2">
            <input type="hidden" value="${train.topbandId }"/>
            <input type="hidden" id="orgId" value="${train.orgId }"/>
        </div>
        <div id="head-content">
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
            <div id="onlineTrainMenu" class="enterTrain">
                <ul>
                    <li class="currentMenu indexTab"><a href="javascript:showIndex()" class="menu">培训首页</a></li>
                    <c:if test="${applyTrainStatus == 2}">
                        <li class="courseTab"><a href="javascript:showCourse(${train.ID });" class="menu">培训课程</a></li>
                        <li class="newsTab"><a href="javascript:showNews(${train.ID });" class="menu">新闻动态</a></li>
                        <c:if test="${hasExam == 1 }">
                            <li class="hasExam"><a href="javascript:showExam(${train.ID });" class="menu">考试</a></li>
                        </c:if>
                        <c:if test="${hasExam != 1 }">
                            <li class="summaryTab"><a href="javascript:showSummary(${train.ID });" class="menu">总结</a></li>
                        </c:if>
                    </c:if>
                </ul>
            </div>
            <input value="${train.ID }" id="_trainId" type="hidden"/>
            <input value="${userId}" id="_userId" type="hidden"/>
            <input value="${joinTrainFlag }" id="_userJoinedFlag" type="hidden"/>
            <input value="${isTrainAdmin }" id="_isTrainAdmin" type="hidden"/>
        </div>
    </div>

    <div id="mainbody" style="background:#e1e1e1">
        <div class='pagebodybufferMask'></div>
        <!-- #########培训首页############## -->
        <div id="trainIndex">
            <div id="trainInfo" class="mainContentIndex" style="padding-top:0px;padding-bottom: 29px; ">
                <div id="sponsor" class="animateOnline component">
                    <div class="TagOnline2" style="left: -50px;width: 6px; height: 50px; vertical-align: middle;top: 50px;border-radius: 0px;"></div>
                    <div class="TagOnline1">
                        <div class="fontDiv"><font class="circularText">主办</font></div>
                    </div>
                    <div class="sponsor_left_table">
                        <table cellspacing="0" width="95%" align="center" cellpadding="0">
                            <tr><td><h3>${train.trainName }</h3></td></tr>
                            <tr><td width="30%">主办：${train.orgName}</td></tr>
                            <tr><td width="20%" align="left">联系人：${train.organizerName}</td></tr>
                            <tr><td width="20%" align="left">联系人Email：${train.organizerEmail}</td></tr>
                            <tr><td width="20%" align="left">联系人电话：${train.telephone}</td></tr>
                            <tr>
                                <c:if test="${applyTrainStatus != 8}">
                                    <c:if test="${applyTrainStatus != 7}">
                                        <td width="20%" align="left">报名时间：
                                            <fmt:formatDate value="${train.programStartTime }" pattern="yyyy-MM-dd HH:mm"/>
                                            ~
                                            <fmt:formatDate value="${train.programEndTime }" pattern="yyyy-MM-dd HH:mm"/>
                                        </td>
                                    </c:if>
                                </c:if>
                            </tr>
                            <tr>
                                <td width="20%" align="left">培训方式：线上培训</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="sponsor_right" style="display: none;">
                    <c:if test="${applyTrainStatus == 1}">
                        <div class="train_signupBtnOnline">未审核</div>
                    </c:if>
                    <c:if test="${applyTrainStatus == 2}">
                        <div class="train_signupBtnOnline">审核通过</div>
                    </c:if>
                    <c:if test="${applyTrainStatus == 3}">
                        <div class="train_signupBtnOnline">审核未通过</div>
                    </c:if>
                    <c:if test="${applyTrainStatus == 4}">
                        <c:if test="${userId == -1}">
                            <div class="train_signupBtnOnline train_signupBtn">我要报名<input type="hidden" value="-1"/></div>
                        </c:if>
                        <c:if test="${userId != -1}">
                            <div class="train_signupBtnOnline">我要报名<input type="hidden" value="${train.ID }"/></div>
                        </c:if>
                    </c:if>
                    <c:if test="${applyTrainStatus == 5 }" >
                        <div class="train_signupBtnOnline">报名尚未开始</div>
                    </c:if>
                    <c:if test="${applyTrainStatus == 6 }" >
                        <div class="train_signupBtnOnline">报名已截止</div>
                    </c:if>
                    <c:if test="${applyTrainStatus == 7 }" >
                        <div class="train_signupBtnOnline">尚未设置报名时间</div>
                    </c:if>
                    <c:if test="${applyTrainStatus == 8 }" >
                        <div class="train_signupBtnOnline">不允许报名</div>
                    </c:if>
                </div>
            </div>
            <div class="clr"></div>
            <div id="trainInfo" class="mainContentIndex" style="padding-top:0px;">
                <div id="sponsor" class="animateOnline component">
                    <div class="TagOnline2" style="left: -50px;width: 6px; height: 50px; vertical-align: middle;top: 0px;border-radius: 0px;"></div>
                    <div class="TagOnline2">
                        <div class="fontDiv"><font class="circularText">目标</font></div>
                    </div>
                    <div class="tagContent"><p>${train.trainGoal}</p></div>
                </div>
            </div>
            <div class="clr"></div>
            <div id="trainInfo" class="mainContentIndex" style="padding-top:0px;">
                <div id="sponsor" class="animateOnline component">
                    <div class="TagOnline2" style="left: -50px;width: 6px; height: 50px; vertical-align: middle;top: 0px;border-radius: 0px;"></div>
                    <div class="TagOnline3">
                        <div class="fontDiv"><font class="circularText">内容</font></div>
                    </div>
                    <div class="tagContent"><p>${train.trainingContent}</p></div>
                </div>
            </div>
            <div class="clr"></div>
            <div id="trainInfo" class="mainContentIndex" style="padding-top:0px;">
                <div id="sponsor" class="animateOnline component">
                    <div class="TagOnline2" style="left: -50px;width: 6px; height: 50px; vertical-align: middle;top: 0px;border-radius: 0px;"></div>
                    <div class="TagOnline4">
                        <div class="fontDiv"><font class="circularText">对象</font></div>
                    </div>
                    <div class="tagContent"><p>${train.attendants}</p></div>
                </div>
            </div>
            <div class="clr"></div>
        </div>
        <!-- #########必修课程########### -->
        <div id="onlinCourse" style="display: none">
            <div class="clr"></div>
            <div id="trainInfo" class="mainContentOnline" style="padding-top:0px; margin-top: -10px;">
                <div class="courseTime_l">
                    <h3 style="margin-top:0px;text-align: center;padding-left: 0px;">已完成学时</h3>
                    <h4 id="learnedHours"></h4>
                </div>
                <div class="courseTime_l">
                    <h3 style="margin-top:0px;text-align: center;padding-left: 0px;">还需完成学时</h3>
                    <h4 id="neededHours"></h4>
                </div>
            </div>
            <div id="trainInfo" class="mainContentTitle" style="pad">
                <div style="display: inline;background: blue;font-size: 17px;left: 41px;position: absolute;right: px; width: -3px;">
                    |
                </div>
                <div class="courseTitle">学时课程列表：</div>
            </div>
            <div id="trainInfo" class="mainContentOnline" style="padding-top:0px; margin-left:50px;">
                <div style="padding-top:0px; margin-left:50px;" id="sortDiv"></div>
            </div>
            <div id="trainInfo" class="mainContentOnline onlineCourseList" style="padding-top:0px; margin-left:50px;top: -7px;">
                <div class="onlineTrainCourse">
                    <div style="text-align:center;font-size:20px;color:gray;padding-top:20px;">暂无课程</div>
                </div>
            </div>
        </div>
        <!-- #########新闻动态############## -->
        <div id="trainInfo" class="mainContentOnline" style="padding-top:0px; margin-top: -10px;margin: 0px;width: 100%;top: -18px;">
            <div id="trainNews" style="display: none">
                <div class="clr"></div>
                <ul class="notice-list" style="background-color: white;min-height:420px;">
                </ul>
            </div>
        </div>
        <!-- #########培训总结########### -->
        <div id="trainSummary" style="display: none">
            <div class="clr"></div>
            <div id="trainInfo" class="mainContentOnline" style="background: white;padding: 40px;width: 900px;margin-left: 52px;">
                <div id="" class="" style="margin-left: 120px;line-height: 32px;">
                    <h1 id="requirement">本培训班需要完成40学时后可提交总结</h1>
                    <div>
                        <textarea name="summaryName" id="summaryName" rows="1" cols="80" placeholder="请输入总结标题..." style="height: 30px;line-height: 30px;margin-top: 11px;margin-bottom: 11px;"></textarea>
                    </div>
                    <span id="subject"></span>
                    <div style="padding-bottom: 20px;">
                        <textarea name="summaryContent" id="summaryContent" rows="20" cols="80" placeholder="请输入培训总结内容..." style="font-size:14px;"></textarea>
                    </div>
                    <input id="summaryId" type="hidden"/>
                    <button id="sub" style="background: hsla(211, 87%, 49%, 0.91);float: right;color: white;height: 30px;width: 70px;border: solid;margin-right: 192px;margin-top: 0px;" onClick="submitSummary()">提交总结</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<div id="simplefoot">
</div>
</div>
<div id="simpleNavBar" style="position:fixed;top:0px;"></div>
<script type="text/javascript" src="/js/nav/umenu.js"></script>
<script type="text/javascript" src="/js/login/login.js"></script>
<script type="text/javascript" src="/js/basicUserFunc.js"></script>
<script type="text/javascript">
    var trainBasicOptions = {
        trainId: $("#menubar #_trainId").val()
    };

    var loginData = {
        nextActionUrl: "onlineTraining.do?method=viewTrain4Admin&trainId=" + trainBasicOptions.trainId
    };

    $(function () {
        if ($(".train_signupBtnOnline input").val() != null) {
            $(".train_signupBtnOnline").css("cursor", "pointer").bind('click', function () {
                var trainId = $(this).find('input').val();
                if (trainId == -1) {
                    jAlert('<font color=red>请先登陆系统,然后再报名！</font>', '登陆提示',
                        function () {
                            var targetUrl = "onlineTraining.do?method=viewTrain4Admin&trainId=" + trainBasicOptions.trainId;
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
    });

    function applyTrain(trainId) {
        $.post("onlineTraining.do?method=ifRegisterable", {trainId: trainId},
            function (data) {
                if (data == 0) {
                    alert('本报名只对特定人员开放，目前您无报名权限。请联系相关培训管理人员!');
                } else if (data == 1) {
                    confirmApplyTrain(trainId);
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
                    var certificateUrl = '/mixtraining/downloadMyCertificate.jsp?trainId=' + trainId + '&file=' + userId + '&orgId=' + orgId;
                    window.open(certificateUrl);
                }
            }
        }
    }
</script>
<script type="text/javascript">
    function showCourse(trainId) {
        $(".currentMenu").removeClass("currentMenu");
        $(".courseTab").addClass("currentMenu");
        var trainId = trainId;
        $.ajax({
            method: "POST",
            data: {"trainId": trainId},
            url: "../mtMixTrainSchedule/loadOnlineTrainScheduleItem.do",
            dataType: "json",
            success: function (data) {
                var sortList = data.data.sortList;
                var sortListLength = sortList.length;
                var courseList = data.data.courseList;
                var courseListLength = courseList.length;
                var sortHtmlArray = new Array();
                var courseHtmlArray = new Array();
                for (var i = 0; i < sortListLength; i++) {
                    var sortString = sortList[i];
                    if (sortString != "") {
                        sortHtmlArray.push("<span class='sortBtn courseSort_" + i + "'><a href='javascript:sortCourse(" + i + ");'>" + sortString + "</a></span>");
                    }
                    for (var j = 0; j < courseListLength; j++) {
                        var course = courseList[j];
                        if (sortString == course.sortLable) {
                            courseHtmlArray.push("<div class='onlineTrainCourse sort_" + i + "'>");
                            courseHtmlArray.push("<span class='pn_img'>");
                            courseHtmlArray.push("<img src='" + course.pictureURL + "' onerror='imgError({type:0,target:this})'/>");
                            courseHtmlArray.push("</span>");
                            courseHtmlArray.push("<div class='pn_422'>");
                            courseHtmlArray.push("<ul>");
                            courseHtmlArray.push("<li class='tit'>" + course.courseName + "</li>");
                            courseHtmlArray.push("<li><span>" + course.teacherName + "</span></li>");
                            courseHtmlArray.push("<li><span>课程学时：" + course.classHour + "</span></li>");
                            courseHtmlArray.push("<li><span class='linebg'>");
                            if (course.studyProgress == null || course.studyProgress == "") {
                                courseHtmlArray.push("<span class='line_now' style='width:0%'></span></span>");
                            } else {
                                courseHtmlArray.push("<span class='line_now' style='width:" + course.studyProgress + "%'></span></span>");
                            }
                            courseHtmlArray.push("</ul>");
                            courseHtmlArray.push("</div>");
                            courseHtmlArray.push("<span class='pn_btn'>");
                            courseHtmlArray.push("<a href='../courseStudy/scormStudy.do?courseId=" + course.courseId + "&train_id=" + trainId + "' target='view_window'>我要学习</a>");
                            courseHtmlArray.push("</span>");

                            courseHtmlArray.push("</div>");

                        }
                    }
                }

                var learnedHours = data.learnedHours;
                var trainNeededHours = data.trainNeededHours;
                var learnedHoursHtmlArray = new Array();
                var neededHoursHtmlArray = new Array();
                if (Math.floor(learnedHours) != 0) {
                    learnedHoursHtmlArray.push(Math.floor(learnedHours));
                    learnedHoursHtmlArray.push("小时");
                }
                if ((learnedHours - Math.floor(learnedHours)) != 0) {
                }
                learnedHoursHtmlArray.push(((learnedHours - Math.floor(learnedHours)) * 60).toFixed(0));
                learnedHoursHtmlArray.push("分钟");
                $("#learnedHours").html("");
                $("#learnedHours").html(learnedHoursHtmlArray.join(""));

                var lastHours = trainNeededHours - learnedHours;
                if (lastHours > 0) {
                    neededHoursHtmlArray.push(Math.floor(lastHours));
                    neededHoursHtmlArray.push("小时");
                    var minute = (lastHours - Math.floor(lastHours)) * 60
                    neededHoursHtmlArray.push(Math.floor(minute));
                    neededHoursHtmlArray.push("分钟");
                } else {
                    neededHoursHtmlArray.push("已完成");
                }
                $("#neededHours").html("");
                $("#neededHours").html(neededHoursHtmlArray.join(""));

                $("#sortDiv").html("");
                $("#sortDiv").html(sortHtmlArray.join(""));

                $(".onlineCourseList").html("");
                $(".onlineCourseList").html(courseHtmlArray.join(""));
            }

        });
        hideAll();
        $("#onlinCourse").show();
    }

    function sortCourse(sortId) {
        $(".onlineTrainCourse").hide();
        $(".sort_" + sortId).show();
        $(".sortBtnClick").removeClass("sortBtnClick");
        $(".courseSort_" + sortId).addClass("sortBtnClick");
    }

    function showIndex() {
        $(".currentMenu").removeClass("currentMenu");
        $(".indexTab").addClass("currentMenu");
        hideAll();
        $("#trainIndex").show();
    }

    function showSummary(trainId) {
        $(".currentMenu").removeClass("currentMenu");
        $(".summaryTab").addClass("currentMenu");
        hideAll();
        $.ajax({
            method: "POST",
            data: {"trainId": trainId},
            url: "../trainSummary/showTrainSummary.do",
            dataType: "json",
            success: function (data) {
                var neededHourse = data.data.neededHourse;
                var learnedHours = data.data.learnedHours;
                var subject = data.data.subject;
                var summary = data.data.summary;
                var ifEnd = data.data.ifEnd;

                if (subject == 0) {
                    $("#subject").html("暂未设置题目");
                } else {
                    $("#subject").html(subject);
                }
                //当有学时要求时,未达到要求不显示填写框和提交按钮
                if (neededHourse >= 0) {
                    $("#requirement").html("本培训班需要完成" + neededHourse + "学时后可提交总结");
                    if (learnedHours < neededHourse) {
                        $("#summaryName").hide();
                        $("#summaryContent").hide();
                        $("#sub").hide();
                    }
                }
                //当已有总结时，标题和内容为只读，按钮为修改。
                if (summary != 0) {
                    $("#summaryName").html(summary.summaryName);
                    $("#summaryContent").html(summary.conclusion);
                    $("#summaryName").attr({readonly: "ture"});
                    $("#summaryContent").attr({readonly: "ture"});
                    $("#summaryId").val(summary.id);
                    $("#sub").html("修改");
                } else {
                    $("#summaryId").val("0");
                }
                //培训班已结束时，总结不可修改不可提交
                if (ifEnd == 1) {
                    $("#summaryName").attr({readonly: "ture"});
                    $("#summaryContent").attr({readonly: "ture"});
                    $("#sub").remove();
                }

            }
        });
        $("#trainSummary").show();
    }

    function submitSummary() {
        var trainId = $("#_trainId").val();
        var summary_id = $("#summaryId").val();
        var summaryName = $("#summaryName").val();
        var conclusion = $("#summaryContent").val();
        if (summaryName == null || summaryName == "") {
            alert("标题不能为空");
            $("#summaryName").focus();
            return;
        }
        if (conclusion == null || conclusion == "") {
            alert("总结内容不能为空");
            $("#summaryContent").focus();
            return;
        }
        if ($("#sub").html() == "提交总结") {
            $.ajax({
                method: "POST",
                data: {
                    "train_id": trainId,
                    "summary_id": summary_id,
                    "summaryName": summaryName,
                    "conclusion": conclusion
                },
                url: "trainSummaryAction.do?method=saveOrupdateSubmit",
                dataType: "json",
                success: function (data) {
                    if (data.result) {
                        alert("提交成功");
                    } else {
                        alert("失败，请保存内容重新登录后提交。");
                    }
                }

            });
            $("#summaryName").attr({readonly: "ture"});
            $("#summaryContent").attr({readonly: "ture"});
            $("#sub").html("修改");
        } else {
            $("#summaryName").removeAttr("readonly");
            $("#summaryContent").removeAttr("readonly");
            $("#sub").html("提交总结");
        }
    }

    function showNews(trainId) {
        $(".currentMenu").removeClass("currentMenu");
        $(".newsTab").addClass("currentMenu");
        hideAll();
        $("#trainNews").show();
        $.ajax({
            method: "POST",
            data: {"trainId": trainId},
            url: "../mtTrainNews/findTrainNews.do",
            dataType: "json",
            success: function (data) {
                var newsList = data.data;
                var listLength = newsList.length;
                var newsHtmlArray = new Array();
                for (var i = 0; i < listLength; i++) {
                    var news = newsList[i];
                    newsHtmlArray.push("<li>");
                    newsHtmlArray.push("<p>");
                    newsHtmlArray.push("<a href='javascript:openNews(" + news.newsId + ")' style=\"color:#0d7bfe;\">" + news.newsTitle + "</a>");
                    newsHtmlArray.push("</p>");
                    newsHtmlArray.push("<span style=\"color:#5B5B5B\">[" + news.createTime + "]</span>");
                    newsHtmlArray.push("</li>");
                }
                if (listLength == 0) {
                    newsHtmlArray.push("<div style=\"text-align:center;font-size:20px;color:gray;padding-top:20px;\">暂无新闻</div>");
                }
                $(".notice-list").html(newsHtmlArray.join(""));
            }
        });
    }
    function openNews(newsId) {
        var src = "../mtTrainNews/getNewsDetailFromMainPage.do?newsId="+newsId;
        window.open(src);

    }
    function hideAll() {
        $("#trainIndex").hide();
        $("#onlinCourse").hide();
        $("#trainNews").hide();
        $("#trainSummary").hide();
    }
</script>
</body>
</html>