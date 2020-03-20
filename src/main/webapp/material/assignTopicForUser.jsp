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
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta content="CASMOOC;继续教育;中科院;网络培训" name="keywords"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <title>
        <c:if test="${assignment == null}"><%=Constants.systemName%></c:if>
        <c:if test="${assignment != null}">${assignment.name}</c:if>
    </title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link rel="stylesheet" type="text/css" href="/css/uploadify.css"/>
    <link rel="stylesheet" type="text/css" href="/css/uploadifive.css"/>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css"/>
    <link href="/css/template/testPaper.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="top">
    <div id="top-title">
        <div id="badge" onclick="javascript:window.open('<%=basePath %>')"></div>
        <div id="top-title-name">
            <c:if test="${assignment != null}">${assignment.name}</c:if>
        </div>
    </div>
</div>
<div id="center">
    <div id="assignTopicHiddenInfo" style="display:none">
        <c:if test="${assignUser != null}">
            <input type="hidden" name="assignId" value="${assignUser.assignId}"/>
            <input type="hidden" name="operatorId" value="${assignUser.operatorid}"/>
            <input type="hidden" name="status" value="${assignUser.status}"/>
            <input type="hidden" name="type" value="${assignment.type}"/>
            <input type="hidden" name="resourceId" value="${assignment.resourceId}"/>
        </c:if>
    </div>

    <div id="main" style="float:none">
        <div id="survey-info">
            <div id="survey-info-content">
                <c:if test="${assignment.description != null}">${assignment.description}</c:if>
            </div>
        </div>
        <form id="paperForm">
            <div id="survey-content">
                <c:if test="${assignTopicFormList != null}">
                    <c:set var="questionCount" scope="page" value="0" target=""/>
                    <c:forEach var="assignTopicForm" items="${assignTopicFormList}" varStatus="status">
                        <div class="question">
                            <c:set var="questionCount" scope="page" value="${questionCount+1}"/>
                            <div class="question-name">
                                <div class="question-name-item">${questionCount}、${assignTopicForm.topicContent}
                                    <c:if test="${assignTopicForm.required}">
                                        <img src="/image/survey/vip.jpg"/>
                                    </c:if>
                                </div>
                            </div>
                            <div class="question-sep"></div>
                            <div style="margin-top:30px;margin-left:40px" class="topicContent">
                                <input type="hidden" name="topicId" value="${assignTopicForm.topicId}"/>
                                <input type="hidden" name="required" value="${assignTopicForm.required}"/>
                                <c:if test="${!assignTopicForm.form}">
                                    <textarea rows="6" cols="80" name="${assignTopicForm.topicId}" class="topicId answer">
                                            ${assignTopicForm.content}
                                    </textarea>
                                </c:if>
                                <c:if test="${assignTopicForm.form}">
                                    <input type="hidden" class="answer" name="answer" value="${assignTopicForm.content}"/>
                                    <span class="blueSimpleButton simpleButton uploadAccessory">上传附件</span>
                                    <span class="orangeSimpleButton simpleButton clearAccessory" style="display:none">清除附件</span>
                                    <span class="simpleButton downloadAccessory" style="display:none">下载附件</span>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </form>
        <div style="margin-left:15px;clear:both" id="buttonLayer">
            <c:if test="${previewTag != null}">
                <a data-remodal-target="modal" href="javascript:void(0);" class='newsTitle'>
                    <img src="/image/survey/submit.jpg" border="0"/>
                </a>
            </c:if>
            <c:if test="${previewTag == null}">
                <a href="javascript:void(0);" id="submitAssign"><img src="/image/survey/submit.jpg" border="0"/></a>
            </c:if>
            <a href="javascript:void(0);" id="resetAssign"><img src="/image/survey/reset.jpg" border="0"/></a>
        </div>
    </div>
</div>
<div id="simplefoot" style="float:none"></div>
<div class='remodal' data-remodal-id='modal' role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <button data-remodal-action='close' class='remodal-close' aria-label="Close" style="left:auto;right:0;"></button>
    <div style="font-size:14pt;font-weight:bold;">
        预览问卷不支持问卷提交！
    </div>
</div>
<div class='remodal' data-remodal-id='modal_reset' role='dialog' aria-labelledby='modal1Title'
     aria-describedby='modal1Desc'>
    <button data-remodal-action='close' class='remodal-close' aria-label="Close" style="left:auto;right:0;"></button>
    <div style="font-size:14pt;font-weight:bold;">
        您所填写的内容已重置！
    </div>
</div>
<!-- 附件上传器 -->
<div class="uploader" data-remodal-id="uploaderPanel" role="dialog" aria-labelledby='modal1Title'
     aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close"></button>
    </div>
    <div id="uploadInfoContainer">
        <input type="file" name="file_upload" id="file_upload"/>
        <p id="uploadInfo">仅支持word文档上传</p>
        <div id="fileQueue"></div>
    </div>
</div>

<div class='remodal' data-remodal-id='assign_sumbit' role='dialog' aria-labelledby='modal1Title'
     aria-describedby='modal1Desc'>
    <button data-remodal-action='close' class='remodal-close' aria-label="Close" style="left:auto;right:0;"></button>
    <div style="margin-bottom:20px;font-size:14pt;font-weight:bold;">确定提交作业吗？</div>
    <button data-remodal-action="confirm" class="remodal-confirm">确定</button>
    <button data-remodal-action="cancel" class="remodal-cancel">取消</button>
</div>
<div class='remodal' data-remodal-id='assign_submit_result' role='dialog' aria-labelledby='modal1Title'
     aria-describedby='modal1Desc'>
    <div class="sucess_submit submitInfo" style="display:none">
        <div class="icon success"></div>
        作业成功提交
    </div>
    <div class="error_submit submitInfo" style="display:none">
        <div class="icon error"></div>
        作业提交出现问题
    </div>
</div>

<div class='remodal assign_status' data-remodal-id='assign_status' role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <h2 class='tips'></h2>
    <button data-remodal-action="confirm" class="remodal-confirm">确定</button>
</div>
<script type="text/javascript" src="<%=basePath %>/js/common/tools.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/common/home.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery-latest.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/JSCommonTools.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/nav/smenu.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery.uploadify.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery.uploadifive.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/jquery-json.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/UI/remodal.min.js"></script>
<script type="text/javascript">
    var $uploader = $("[data-remodal-id=uploaderPanel]").remodal($.defaultRemodalOption());

    var $assign_sumbit = $('[data-remodal-id=assign_sumbit]').remodal($.defaultRemodalOption());

    var $assign_submit_result = $('[data-remodal-id=assign_submit_result]').remodal($.defaultRemodalOption());

    var $currentTopic = null;

    var $assign_status = $('[data-remodal-id=assign_status]').remodal($.defaultRemodalOption());

    function generateAssignInfo() {
        var $operatorId = $("#assignTopicHiddenInfo input[name=operatorId]");
        var $assignId = $("#assignTopicHiddenInfo  input[name=assignId]");
        var topicIds = new Array();
        var answers = new Array();
        var canSubmit = true;
        $("#paperForm .topicContent").each(function (index, that) {
            var required = $("input[name=required]", $(that));
            var topicId = $("input[name=topicId]", $(that));
            var answer = $(".answer", $(that));
            if (required.val() == "true" && $.trim(answer.val()) == "") {
                var num = index + 1;
                $.tips("第" + num + "题为必填内容，请填写内容后提交", "系统提示");
                canSubmit = false;
                return false;
            }
            topicIds.push(topicId.val());
            answers.push(answer.val());
        });
        if (canSubmit) {
            var assignInfoForSubmit = {
                url: "../assignment/submitAssignment.do",
                dataType: "json",
                type: "post",
                data: {
                    "assignId": $assignId.val(),
                    "operatorId": $operatorId.val(),
                    "topicIds": topicIds,
                    "answers": answers
                },
                traditional: true,
                success: function (data) {
                    if (data.status) {
                        $(".sucess_submit").show();
                        setTimeout(function () {
                            var type = $("#assignTopicHiddenInfo input[name=type]").val();
                            var resourceId = $("#assignTopicHiddenInfo input[name=resourceId]").val();
                            if (type) {
                                window.location.href = "../onlineTraining/viewTrain4Admin.do?trainId=" + resourceId;
                            }
                        }, 3000);
                    } else {
                        if ($.trim(data.msg) != "") {
                            $(".error_submit").text(data.msg);
                        }
                        $(".error_submit").show();
                        setTimeout(function () {
                            $assign_submit_result.close();
                        }, 3000);
                    }
                },
                error: function (data) {
                    $(".error_submit").removeClass("notShow");
                    setTimeout(function () {
                        $assign_submit_result.close();
                    }, 3000);
                },
                complete: function (data) {
                    $assign_submit_result.open();
                }
            }

            return assignInfoForSubmit;
        } else {
            return null;
        }
    }
    $(function () {

        var operatorId = $("#assignTopicHiddenInfo input[name=operatorId]").val();

        if (operatorId) {
            var currentStatus = 0;
            if ($("#assignTopicHiddenInfo input[name=status]").length > 0) {
                currentStatus = $("#assignTopicHiddenInfo input[name=status]").val();
            }

            var submitOption = null;
            $(document).on('confirmation', '[data-remodal-id=assign_sumbit]', function () {
                $.ajax(submitOption);
            });

            $(document).on("opening", '[data-remodal-id=assign_status]', function () {
                var tipInfo = "";
                switch (currentStatus) {
                    case "-1":
                        tipInfo = "未通过管理员审核,请重新提交！";
                        break;
                    case "1":
                        tipInfo = "您已提交过作业，重新提交将覆盖已提交内容";
                        break;
                    case "2":
                        tipInfo = "作业已通过管理员审核，无需提交";
                        break;
                }
                $(".assign_status .tips").html(tipInfo);
            });

            if (currentStatus != 0) {
                $assign_status.open();
            }
            if (currentStatus == 2) {
                $("#buttonLayer").remove();
            }
            $(".topicContent").each(function (index, that) {
                var $answer = $("input[name=answer]", $(that));
                if ($answer.length > 0 && $.trim($answer.val()).length > 0) {
                    $(".clearAccessory", $(that)).css("display", "inline-block");
                    $(".downloadAccessory", $(that)).css("display", "inline-block");
                }
            })
            $("#submitAssign").click(function () {
                submitOption = generateAssignInfo();
                if (submitOption != null) {
                    $assign_sumbit.open();
                }
            });
            $("#resetAssign").click(function () {
                $.confirm("确定清空当前作业全部内容？", "系统提示", function (status) {
                    if (status) {
                        $(".topicContent .answer").each(function () {
                            this.value = "";
                        })
                        $(".clearAccessory").css("display", "none");
                        $(".downloadAccessory").css("display", "none");
                    } else {
                        //do nothing
                    }
                })
            })

            $(".uploadAccessory").click(function () {
                $currentTopic = $(this).parents(".topicContent");

                $uploader.open();
            })

            $(".clearAccessory").click(function () {
                var $selectedTopic = $(this).parents(".topicContent");
                $(this).css("display", "none");
                $(".downloadAccessory", $selectedTopic).css("display", "none");
                $("input[name=answer]", $selectedTopic).val("");
            })

            $(".downloadAccessory").click(function () {
                var $selectedTopic = $(this).parents(".topicContent");
                var answer = $("input[name=answer]", $selectedTopic).val();
                if ($.trim(answer) == "") {
                    $.tips("您尚未上传任何附件！", "系统提示");
                } else {
                    window.open(answer);
                }
            })


            var uploadAction = {
                errorTip: function () {
                    $.tips("无法上传您选择的文件到服务器,请确保文件为word文档、大小不超过10M", "系统提示", function () {
                        $("#uploadInfo").html("仅支持word文档上传");
                        $("#fileQueue").empty();
                    });

                },
                onUploadStart: function (file) {
                    $("#uploadInfo").html("");
                },
                onDialogClose: function (queueData) {
                    if (queueData.filesErrored > 0) {
                        uploadAction.errorTip();
                    }
                },
                onUploadSuccess: function (file, data, response) {
                    var resultData = $.parseJSON(data);
                    var result = resultData.result;
                    var uploadResultInfo = "";
                    if (result == "true") {
                        $("input[name=answer]", $currentTopic).val(resultData.answer);
                        var uploadResultInfo = "您的附件上传成功";
                        $(".clearAccessory", $currentTopic).css("display", "inline-block");
                        $(".downloadAccessory", $currentTopic).css("display", "inline-block");
                        setTimeout(function () {
                            $uploader.close();
                            $("#uploadInfo").html("");
                        }, 1000)
                    } else {
                        if ($.trim(data.cause) != "") {
                            uploadResultInfo = data.cause;
                        }
                    }
                    $("#uploadInfo").html(uploadResultInfo);
                    $("#fileQueue").empty();
                }
            }

            var getCurrentUploadUrl = function () {
                var currentTopicId = $("input[name=topicId]", $currentTopic).val();
                return 'assignAction.do?method=uploadAssignTopic&operatorId=' + operatorId + "&topicId=" + currentTopicId;
            }

            $(document).on("opening", "[data-remodal-id=uploaderPanel]", function () {
                if ($.supportHTML5()) {
                    $('#file_upload').uploadifive({
                        'uploadScript': getCurrentUploadUrl(),
                        'buttonText': '附件上传',
                        'queueID': 'fileQueue',
                        'multi': false,
                        'fileTypeExts': '*.txt;*.docx;*.doc;',
                        'fileSizeLimit': "10MB",
                        'onError': function (file, fileType, data) {
                            uploadAction.errorTip();
                        },
                        'onDialogClose': uploadAction.onDialogClose,
                        'onUploadComplete': uploadAction.onUploadSuccess,
                    });
                }
                else {
                    //绑定动作到相片上传操作
                    $("#file_upload").uploadify({
                        'overrideEvents': ['onDialogClose'],
                        'method': 'get',
                        'swf': './js/uploadify.swf',
                        'fileTypeExts': '*.docx;*.doc;',
                        'uploader': 'assignAction.do?method=uploadAssignTopic',
                        'cancelImg': 'image/cancel.png',
                        'fileSizeLimit': "10MB",
                        'auto': true,
                        'multi': false,
                        "formData": {
                            'operatorId': operatorId,
                            "topicId": $("input[name=topicId]", $currentTopic).val()
                        },
                        'buttonText': '附件上传',
                        'onUploadStart': uploadAction.onUploadStart,
                        'queueID': 'fileQueue',
                        'successTimeout': 6000,
                        'onDialogClose': uploadAction.onDialogClose,
                        'onUploadSuccess': uploadAction.onUploadSuccess,
                    });
                }
            })
        } else {
            $(".assign_status .remodal-confirm").css("display", "none");
            $(document).on("opening", '[data-remodal-id=assign_status]', function () {
                var tipInfo = "您无权访问该作业信息";
                $(".assign_status .tips").html(tipInfo);
                setTimeout(function () {
                    window.location.href = "<%=basePath%>";
                }, 3000)
            });
            $assign_status.open();
        }
    })
</script>
</body>
</html>