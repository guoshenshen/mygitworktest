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
    <link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
    <link href="/css/trainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/basicStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css"/>
    <link href="/css/newTrainClass.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/common/tools.js"></script>
    <script type="text/javascript" src="/js/dojojs/dojo.js"></script>
    <script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/nav/trainClassMenu.js"></script>
    <script type="text/javascript">
        var alertInfoOption = {
            alertTitle: '提示信息',
            confirmTitle: '提示信息',
            dialogStyle: 'blueStyle'
        }
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
            document.form1.action = "trainSummaryAction.do?method=foradd&train_id=" + trainId;
            document.form1.submit();
        }
        //保存总结
        function saveSummary() {
            var options = {
                "summaryName": $("#ownTrainSummaryBody #summaryName").val(),
                "conclusion": $("#ownTrainSummaryBody #conclusion").val(),
                "summary_id": $("#ownTrainSummaryBody #summary_id").val(),
                "train_id": $("#_trainId").val()
            };
            if (options.train_id == null || $.trim(options.train_id).length == 0) {
                jAlert("当前页面信息有误，您的总结无法提交", alertInfoOption.alertTitle);
                return;
            }
            if (options.summaryName == null || $.trim(options.summaryName).length < 5) {
                jAlert("请输入总结标题，标题不得小于5个字", alertInfoOption.alertTitle);
                return;
            }
            if (options.conclusion == null || $.trim(options.conclusion).length < 10 || $.trim(options.conclusion).length > 500) {
                jAlert("请输入总结内容，内容不得为空，字数请控制在10-500字之间", alertInfoOption.alertTitle);
                return;
            }
            $.ajax({
                data: options,
                url: "../trainSummary/saveOrupdateSubmit.do",
                dataType: "json",
                type: 'post',
                traditional: true,
                success: function (data) {
                    if (data.status) {
                        $("#ownTrainSummaryBody #summary_id").val(data.data.id);
                        $("#ownTrainSummaryBody .summaryTime span").html(data.data.submitDate);
                        var summaryName = data.data.summaryName;
                        var conclusion = data.data.conclusion;
                        $(".summaryTitle:first").html(summaryName);
                        $(".summaryContent:first").html(conclusion);
                        addDecorationForSummary();
                        $("#summaryName").val($(".summaryTitle:first").html());
                        $("#conclusion").val($(".summaryContent:first").html());
                    }
                    else {
                        jAlert("更新失败，请稍后尝试", alertInfoOption.alertTitle);
                    }
                },
                error: function (data) {
                    jAlert("更新失败，请稍后尝试", alertInfoOption.alertTitle);
                }
            })
        }
        //重新编辑总结
        function forEditSummary() {
            var summaryId = $("#ownTrainSummaryBody #summary_id").val();
            $("#ownTrainSummaryBody #summary_id").val("");
            addDecorationForSummary();
            $("#ownTrainSummaryBody #summary_id").val(summaryId);
            $("#summaryName").html($(".summaryTitle:first").html());
            $("#conclusion").html($(".summaryContent:first").html());
        }
        //删除总结
        function deleteSummary() {
            var summaryId = $("#ownTrainSummaryBody #summary_id").val();
            $.ajax({
                data: {"id": summaryId},
                url: "../trainSummary/deleteSummary.do",
                dataType: "json",
                type: 'post',
                traditional: true,
                success: function (data) {
                    if (data.status) {
                        $("#ownTrainSummaryBody #summary_id").val("");
                        $("#summaryName").val("");
                        $("#conclusion").val("");
                        $(".summaryTitle:first").html("");
                        $(".summaryContent:first").html("");
                        addDecorationForSummary();
                    } else {
                        jAlert("删除失败，请稍后尝试", alertInfoOption.alertTitle);
                    }

                },
                error: function (data) {
                    jAlert("删除失败，请稍后尝试", alertInfoOption.alertTitle);
                }
            })
        }
        $(function () {
            $.alerts.dialogClass = alertInfoOption.dialogStyle;
            var trainId = $("#_trainId").val();
            $("#myConclusion").click(function () {
                window.location.href = "../trainSummary/getTrainingSummary.do?train_id=" + trainId;
            })

            $("#otherConclusion").click(function () {
                window.location.href = "../trainSummary/getTrainingSummary.do?train_id=" + trainId + "&summaryFlag=otherSummary";
            })

            addDecorationForSummary();

            //绑定事件到编辑、删除、保存按钮
            $("#ownTrainSummaryBody .deleteTag").click(function () {
                jConfirm("确定删除总结?", alertInfoOption.confirmTitle, function (response) {
                    if (response == true) {
                        deleteSummary();
                    }
                });
            });
            $("#ownTrainSummaryBody .editTag").click(function () {
                forEditSummary();
            });
            $("#ownTrainSummaryBody .saveTag").click(function () {
                saveSummary();
            });
        });

        $(function () {
            var url = ""
            if ($("#head2 input").val() != "")
                url = "/image/picturebase/" + $("#head2 input").val() + ".jpg";
            else
                url = "/image/picturebase/4.jpg";
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
            $.ajax({
                url: 'rsmRcmbookdiscussAction.do?method=addsummarydiscuss',
                data: {summaryId: trainSummaryId, content: content, isReply: 1, discussId: discussId},
                type: "POST",
                dataType: "text",
                success: function (data, evt) {
                    document.location.reload();
                }
            })
        }
        //为学员创建自身总结添加样式
        function addDecorationForSummary() {
            var $mySummaryId = $("#ownTrainSummaryBody #summary_id");
            if ($mySummaryId.length == 0 || $.trim($mySummaryId.val()).length == 0) {
                $("#ownTrainSummaryBody").addClass("editStatus");
            } else {
                $("#ownTrainSummaryBody").removeClass("editStatus");
            }
            $("body #bufferMask").css("height", $("body").css("height"));
        }
    </script>
</head>
<body>
<div id="backgroundImg"><img src=''/></div>
<div id='bufferMask'></div>
<div id="simpleNavBar"></div>
<div id="pagebody">
    <div id="headerbody">
        <div id="head2">
            <input type="hidden" name="train_id" id="_trainId" value="${train_id}"/>
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
                <c:if test="${train.location != null}">
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
            <div class="sectionHeader">
                <h2 class="section-header-title currentHeader" id="myConclusion">我的总结</h2>
                <h2 class="section-header-title" id="otherConclusion">其他学员总结</h2>
            </div>
            <div style="clear:both"></div>
            <div id="ownTrainSummaryBody" class="summaryBody">
                <div class="summaryTitleHead">我的总结</div>
                <input type="hidden" id="summary_id" value="${ownTrainSummary.id}"/>
                <a class=" editTag showArea" title="编辑"></a>
                <a class=" deleteTag showArea" title="删除"></a>
                <a class=" saveTag editArea" title="保存"></a>
                <h2 class="summaryTitle showArea">${ownTrainSummary.summaryName}</h2>
                <h2 class="summaryTitle editArea">
                    <textarea name="summaryName" id="summaryName" rows="1" cols="80" placeholder="请输入总结标题..."></textarea>
                </h2>
                <div class="summaryTime showArea">
                    <span>
                        <fmt:formatDate value="${ownTrainSummary.submitDate }" pattern="yyyy-MM-dd HH:mm"/>
                    </span>
                </div>
                <div class="summaryContent showArea">${ownTrainSummary.conclusion}</div>
                <div class="summaryContent editArea">
                    <textarea name="conclusion" id="conclusion" rows="10" cols="90" placeholder="请输入培训总结内容..."></textarea>
                </div>
                <div class="summaryAttachment showArea">
                    <c:if test="${ownTrainSummary.attachmentPath != null}">
                        <button class="insert_mini" onclick='javascript:window.open(${ownTrainSummary.attachmentPath})'>
                            附件下载
                        </button>
                    </c:if>
                </div>
            </div>
            <div style="height:40px"></div>
            <div id="otherTrainSummaryBody">
                <div class="summaryTitleHead"> 评论：</div>
                <div id="otherTrainSummaryContent">
                    <c:if test="${rsmRcmbookdiscussList != null}">
                        <c:forEach var="rsmRcmbookdiscuss" items="${rsmRcmbookdiscussList}" varStatus="status">
                            <ul style="padding-right:20px">
                                <span class="discussTitle_left">${status.count+1}. ${rsmRcmbookdiscuss.content}</span>
                                <span class="discussTitle_right">
                                    <a href="javascript:displayReplyArea(${rsmRcmbookdiscuss.discussId});" id="displayReplyArea_${rsmRcmbookdiscuss.discussId}">回复
                                    </a>&nbsp;&nbsp;评论者：${rsmRcmbookdiscuss.operatorName}&nbsp;&nbsp;时间：
                                    <fmt:formatDate value="${rsmRcmbookdiscuss.discussTime }" pattern="yyyy-MM-dd HH:mm"/>
                                </span>
                                <div id="replyArea_${rsmRcmbookdiscuss.discussId}" style="display:none">
                                    <textarea rows="5" cols="3" style="width:900px" name="reply_${rsmRcmbookdiscuss.discussId}" id="reply_${rsmRcmbookdiscuss.discussId}"></textarea>
                                    <div id="spanContentBtn">
                                        <a href="javascript:replyDebate(${ownTrainSummary.id},${rsmRcmbookdiscuss.discussId});" class="grayBtn">回复</a>
                                    </div>
                                </div>
                                <li style="margin-bottom:20px;border-bottom:#363636 1px dashed;width:940px;margin-top:35px">
                                    <div style="margin-left:12px">
                                        <c:forEach var="reply" items="${rsmRcmbookdiscuss.discussReplies }" varStatus="ind">
                                            <p>${reply.content}</p>
                                            <p style="color:#FF7800;">
                                                回答：${reply.operatorName}&nbsp;&nbsp;时间：时间：
                                                <fmt:formatDate value="${reply.discussTime }" pattern="yyyy-MM-dd HH:mm"/>
                                            </p>
                                        </c:forEach>
                                    </div>
                                </li>
                            </ul>
                        </c:forEach>
                    </c:if>
                </div>
                <div style="clear:both"></div>
            </div>
        </div>
    </div>
    <div id="bottombody">
        <div class='pagebodybufferMask'></div>
        <%=Constants.systemBottom %>
    </div>
</div>
<script type="text/javascript" src="/js/nav/smenu.js"></script>
</body>
</html>
