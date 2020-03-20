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
    <title><%=Constants.systemName%>
    </title>

    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link id="rsmRcmbook" href="/css/resourceManage/rsmRcmbook.css" rel="stylesheet" type="text/css"/>
    <link href="/css/trainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/basicStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/newTrainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/common/tools.js"></script>
    <script type="text/javascript" src="/js/dojojs/dojo.js"></script>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <%--<script type="text/javascript" src="/js/jquery-ui-1.10.4.custom.min.js"></script>--%>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script language='javascript'>

        function _setSelected(id, type) {
            var chechTemp = id.checked;
            with (document.form1) {
                for (var j = 0; j < elements.length; j++) {
                    if (elements[j].type == "checkbox" && elements[j].name == type) {
                        if (chechTemp) {
                            document.form1.elements[j].checked = true;
                        } else {
                            document.form1.elements[j].checked = false;
                        }
                    }
                }
            }
        }
        function _add() {
            var trainId = $("#_trainId").val();
            document.form1.action = "trainSummaryAction.do?method=foradd&train_id=" + train_id;
            document.form1.submit();
        }
        $(function () {
            var trainId = $("#_trainId").val();
            $("#myConclusion").click(function () {
                window.location.href = "../trainSummary/getTrainingSummary.do?train_id=" + trainId;
            });
            $("#otherConclusion").click(function () {
                window.location.href = "../trainSummary/getTrainingSummary.do?train_id=" + trainId + "&summaryFlag=otherSummary";
            })
        });

        function displayReplyArea(discussId) {
            if ($("#replyArea_" + discussId).css("display") == "none") {
                $("#replyArea_" + discussId).css("display", "block");

            } else {
                $("#replyArea_" + discussId).css("display", "none");
                $("#reply_" + discussId).val("");
            }
        }

        function replyDebate(trainSummaryId, discussId) {
            var content = $("#reply_" + discussId).val();
            var train_id = $("#_trainId").val();
            var summaryFlag = "otherSummary";
            var summary_id = $("#summaryDivId").val();

            $.ajax({
                url: 'rsmRcmbookdiscussAction.do?method=addsummarydiscuss',
                data: {summaryId: trainSummaryId, content: content, isReply: 1, discussId: discussId},
                type: "POST",
                dataType: "text",
                success: function (data, evt) {
                    $.ajax({
                        url: 'trainSummaryDebateAction.do?method=detail',
                        type: 'post',
                        data: {train_id: train_id, summaryFlag: summaryFlag, trainSummaryId: summary_id},
                        dataType: 'text',
                        success: function (data, evt) {
                            $("#summaryDebate_" + summary_id).html($(data).find("#summaryDebate_" + summary_id).html());
                            $("#summaryMainContent_" + summary_id).show();
                            $("#content_" + summary_id).val("");
                        }
                    })
                }
            })
        }

        function submitDebate() {
            var summary_id = $("#summaryDivId").val();
            var content = $("#content_" + summary_id).val();
            var train_id = $("#_trainId").val();
            var summaryFlag = "otherSummary";
            $.ajax({
                url: 'rsmRcmbookdiscussAction.do?method=addsummarydiscuss',
                data: {summaryId: summary_id, content: content, isReply: 0},
                type: "POST",
                dataType: "text",
                success: function (data, evt) {
                    $.ajax({
                        url: 'trainSummaryDebateAction.do?method=detail',
                        type: 'post',
                        data: {train_id: train_id, summaryFlag: summaryFlag, trainSummaryId: summary_id},
                        dataType: 'text',
                        success: function (data, evt) {
                            $("#summaryDebate_" + summary_id).html($(data).find("#summaryDebate_" + summary_id).html());
                            $("#summaryMainContent_" + summary_id).show();
                            $("#content_" + summary_id).val("");
                        }
                    })
                }
            })
        }

        $(function () {
            $(".commentList .summaryBody").each(function (index, that) {
                if (index % 2 == 0) {
                    $(that).addClass("left");
                } else {
                    $(that).addClass("right");
                }
            });

            $(".commentSearch .searchTag").click(function () {
                var userName = $(".commentSearch #userName").val();
                var trainId = $("#_trainId").val();
                openLink("../trainSummary/getTrainingSummary.do?summaryFlag=otherSummary", {
                    "userName": userName,
                    "train_id": trainId
                })
            })
        })
    </script>
    <script type="text/javascript" src="/js/nav/trainClassMenu.js"></script>
</head>
<body>
<div id="backgroundImg"><img src=''/></div>
<div id='bufferMask'></div>
<div id="simpleNavBar"></div>
<div id="pagebody">
    <div id="headerbody">
        <div id="head2">
            <input type="hidden" value="${train.topbandId }"/>
            <input type="hidden" id="_trainId" value="${train.ID}"/>
        </div>
        <div id="head-content">
            <p id="title">${train.trainName }</p>
            <p id="time">时间：
                <fmt:formatDate value="${train.startTime }" pattern="yyyy-MM-dd HH:mm"/>
                ~
                <fmt:formatDate value="${train.endTime }" pattern="yyyy-MM-dd HH:mm"/>
            </p>
            <p id="location">
                <c:if test="${train.location != null }">
                    地点：${train.location }
                </c:if>
            </p>
        </div>
        <div id="menubar">
            <div class='pagebodybufferMask'></div>
            <span id="joinTrainFlag" style="display:none;">${joinTrainFlag}</span>
            <div id="trainClassMenu">
                <ul></ul>
            </div>
        </div>
    </div>
    <div id="mainbody">
        <div class='pagebodybufferMask'></div>
        <div class="mainContent">
            <form id="form1" name="form1" action="trainSummaryAction.do?method=delete" method="post"></form>
            <c:if test="${userJoinTrainFlag}">`
                <div class="sectionHeader">
                    <h2 class="section-header-title" id="myConclusion">我的总结</h2>
                    <h2 class="section-header-title currentHeader" id="otherConclusion">其他学员总结</h2>
                </div>
                <div style="clear:both"></div>
            </c:if>
            <c:if test="${trainSummaryFormList != null}">
                <div class="commentSearch">
                    姓名：<input type="text" name="userName" id="userName" value="${userName}" size=15 style="font-size:16px"/>
                    <a href="javascript:void(0);" class="searchTag" title="查询"></a>
                </div>
            </c:if>
            <c:if test="${trainSummaryFormList == null}">
                <div class="emptyInfo">暂无总结...</div>
            </c:if>

            <div class="commentList">
                <c:if test="${trainSummaryFormList != null}">
                    <c:forEach var="trainSummary" items="${trainSummaryFormList}" varStatus="status">
                        <div class="summaryBody">
                            <div class="summaryTitleHead">${trainSummary.operatorName}</div>
                            <input type="hidden" id="summary_id" value="${trainSummary.id}"/>
                            <h2 class="summaryTitle">${trainSummary.summaryName}</h2>
                            <div class="summaryTime">
                                <span><fmt:formatDate value="${trainSummary.submitDate }" pattern="yyyy-MM-dd HH:mm"/></span>
                            </div>
                            <div class="summaryContent">${trainSummary.conclusion}</div>
                            <div class="summaryAttachment">
                                <c:if test="${trainSummary.attachmentPath != null}">
                                    <button class="insert_mini" onclick='javascript:window.open(${trainSummary.attachmentPath})'>附件下载
                                    </button>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <div style="clear:both"></div>
            </div>
            <input type="hidden" name="trainId" id="_trainId" value="${train.ID}"/>
            <input type="hidden" name="summaryFlag" id="summaryFlag" value="${summaryFlag}"/>
            <input type="hidden" name="summaryDivId" id="summaryDivId" value=""/>
        </div>
    </div>
    <div id="bottombody">
        <div class='pagebodybufferMask'></div>
        <%=Constants.systemBottom %>
    </div>
</div>
<script type="text/javascript" src="/js/nav/umenu.js"></script>
</body>
</html>
