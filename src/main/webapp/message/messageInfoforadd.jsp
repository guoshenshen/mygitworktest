<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title><%=Constants.systemName%>
    </title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts-extend.css"/>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/pagination.css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <%--<link rel="stylesheet" type="text/css" href="/css/pagination.css"/>--%>
    <style type="">
        legend {
            width: auto;
            border-bottom: 0px solid #e5e5e5
        }
    </style>
    <style type="text/css">
        th {
            text-align:center; /*设置水平居中*/
            vertical-align:middle;/*设置垂直居中*/
        }
        td {
            text-align:center; /*设置水平居中*/
            vertical-align:middle;/*设置垂直居中*/
        }
    </style>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <ul id="tabs">
                <li class="selected"><a href="#tabs"><span>新建通知</span></a></li>
                <li><a href="../msgMessageArrangeList/searchArrangeUser.do"><span>回执统计</span></a></li>
                <li><a href="../msgMessageInfo/forPreviousTrainingNotice.do"><span>以往培训通知</span></a></li>
            </ul>
            <div class="clr"></div>
            <div id="scheduleTab">
                <div id="mainbody">
                    <div class="homezoneall">
                        <div class="homezonehead">
                            <span class="homezonetitle">新建通知</span>
                        </div>
                        <div class="homezonecontent">
                            <form action="../msgMessageInfo/add.do" method="post" id="messageEditor" name="addForm">
                                <%--<input type="hidden" name="<%=org.apache.struts.taglib.html.Constants.TOKEN_KEY%>" value="<%=session.getAttribute(Globals.TRANSACTION_TOKEN_KEY) %>"/>--%>
                                <input type="hidden" name="filterFlag" value="false"/>
                                <input type='hidden' name='trainId' value='${msgMessageInfoForm.trainId }'/>
                                <input type='hidden' name='addrbookId'/>
                                <input type='hidden' name='roleId'/>
                                <c:if test="${oldMsgId!=null}">
                                    <input type="hidden" id="msgId" value="${oldMsgId}"/>
                                </c:if>
                                <c:if test="${oldMsgId==null}">
                                    <input type="hidden" id="msgId" value="-1"/>
                                </c:if>
                                <div class="messageForm">
                                    <div class="component">
                                        <fieldset>
                                            <legend>标题</legend>
                                            <input type="text" name="title" class="title" id="title" value='${msgMessageInfoForm.title}'/><font color='red'>*</font>
                                        </fieldset>
                                    </div>
                                    <div class="component">
                                        <fieldset class="senderSelect effectArea" id="studentList">
                                            <legend>学员选择——方式1：指定姓名</legend>
                                            <span class="orangeSimpleButton simpleButton userAddMenu button" style="margin-left:50px">人员选择</span>
                                            <span class="blueSimpleButton simpleButton withMsgnum showUsersPreview button" style="margin-left:50px;">人员查看<em class="j-nav-msgnum">0</em></span>
                                            <div class="hiddenArea"></div>
                                            <div class="detailInfoForShow notShow">
                                                <table></table>
                                            </div>
                                        </fieldset>
                                        <fieldset class="senderSelect effectArea" id="orgSelect">
                                            <legend>学员选择——方式2：指定单位</legend>
                                            <span class="orangeSimpleButton simpleButton orgSelect button" style="margin-left:50px">单位选择</span>
                                            <span class="blueSimpleButton simpleButton withMsgnum showOrgsPreview button" style="margin-left:50px;">单位查看<em class="j-nav-msgnum">0</em></span>
                                            <div class="hiddenArea"></div>
                                            <div class="detailInfoForShow notShow">
                                                <table></table>
                                            </div>
                                        </fieldset>
                                        <fieldset class="senderSelect effectArea" id="secretaryList">
                                            <legend>联络员抄送</legend>
                                            <span class="orangeSimpleButton simpleButton userAddMenu button" style="margin-left:50px">人员选择</span>
                                            <span class="blueSimpleButton simpleButton withMsgnum showUsersPreview button" style="margin-left:50px;">人员查看<em class="j-nav-msgnum">0</em></span>
                                            <div class="hiddenArea"></div>
                                            <div class="detailInfoForShow notShow">
                                                <table></table>
                                            </div>
                                        </fieldset>
                                        <fieldset class="senderSelect effectArea" id="directorList">
                                            <legend>领导抄送</legend>
                                            <span class="orangeSimpleButton simpleButton userAddMenu button" style="margin-left:50px">人员选择</span>
                                            <span class="blueSimpleButton simpleButton withMsgnum showUsersPreview button" style="margin-left:50px;">人员查看<em class="j-nav-msgnum">0</em></span>
                                            <div class="hiddenArea"></div>
                                            <div class="detailInfoForShow notShow">
                                                <table></table>
                                            </div>
                                        </fieldset>
                                    </div>
                                    <div class="component">
                                        <fieldset>
                                            <legend>通知类别</legend>
                                            <input type="radio" name="isPublic" value="1156" checked onclick="selectMessageType()"/><span class="choice">需报名</span>
                                            <input type="radio" name="isPublic" value="1157" onclick="selectMessageType()"/><span class="choice">无需报名仅提醒</span>
                                        </fieldset>
                                    </div>
                                    <div class="component" id="tipType" style="display:none">
                                        <fieldset>
                                            <legend>通知方式</legend>
                                            <input type="checkbox" name="tipType" value="0" disabled checked/><span class="choice">邮件(默认)</span>
                                            <input type="checkbox" name="tipType" value="1" onclick="changeInputType()" id="smsType"/><span class="choice">手机短信(70字内纯文本)</span>
                                        </fieldset>
                                    </div>
                                    <div class="component" id="confirmType">
                                        <fieldset>
                                            <legend>确认方式</legend>
                                            <input type="radio" name="confirmType" value="0" checked/><span class="choice">选择参加	</span>
                                            <input type="radio" name="confirmType" value="1"/><span class="choice">必须参加  </span>
                                        </fieldset>
                                    </div>
                                    <div class="component" id="smsContent" style="display:none">
                                        <fieldset>
                                            <legend>短信内容</legend>
                                            <textarea name="smscontent" rows="2" cols="65" id="sms_content"
                                                      onKeyDown="gbcount(document.getElementById('sms_content'),document.getElementById('total'),document.getElementById('used'),document.getElementById('remain'));"
                                                      onKeyUp="gbcount(document.getElementById('sms_content'),document.getElementById('total'),document.getElementById('used'),document.getElementById('remain'));">
                                                ${msgMessageInfoForm.smscontent}
                                            </textarea>
                                            <br/>
                                            最多字数：<input disabled style="border:none; background:#fff;" maxLength="4" name="total" id="total" size="3" value="70" class="inputtext"/>
                                            已用字数：<input disabled style="border:none; background:#fff;" maxLength="4" name="used" id="used" size="3" value="0" class="inputtext"/>
                                            剩余字数：<input disabled style="border:none; background:#fff;" maxLength="4" name="remain" id="remain" size="3" value="70" class="inputtext"/>
                                        </fieldset>
                                    </div>
                                    <div class="component" id="commonContent">
                                        <fieldset>
                                            <legend>通知内容</legend>
                                            <textarea name="content" id="contentArea" rows="10" cols="50"> ${msgMessageInfoForm.content}</textarea>
                                        </fieldset>
                                    </div>
                                    <div class="btnContainer">
                                        <span class="blueSimpleButton simpleButton" onclick="return checkForm();">提&nbsp;&nbsp;交</span>
                                    </div>
                                </div>
                            </form>
                            <br/>
                        </div>
                    </div>

                    <!-- 本次培训相关通知  -->
                    <div class="homezoneall">
                        <div class="homezonehead">
                            <span class="homezonetitle">当前培训通知</span>
                        </div>
                        <div class="homezonecontent">
                            <table class="homecontenttable table table-striped table-bordered batchOperation" rules="cols" align="center">
                                <tr class="tableTh">
                                    <th with=25%>通知标题</th>
                                    <th with=15%>通知类别</th>
                                    <th with=15%>时间</th>
                                    <th with=10%>是否已发布</th>
                                    <th with=10%>推送到首页通知公告栏目中</th>
                                    <th with=15%>操作</th>
                                </tr>
                                <c:if test="${thisTrainMsgInfoFormList!=null && thisTrainMsgInfoFormList!=''}">
                                    <c:forEach var="thisTrainMsgForm" items="${thisTrainMsgInfoFormList}" >
                                        <tr id="msg_${thisTrainMsgForm.id }">
                                            <td align="center">${thisTrainMsgForm.title}</td>
                                            <td align="center">${thisTrainMsgForm.publicName}</td>
                                            <td align="center">${thisTrainMsgForm.validDate}</td>
                                            <td align="center" id="status_${thisTrainMsgForm.id}">
                                                <c:if test="${thisTrainMsgForm.status == 1091}">
                                                    <a href="javascript:changeStatus('${thisTrainMsgForm.id}',1091)">未发布</a>
                                                </c:if>
                                                <c:if test="${thisTrainMsgForm.status == 1092}">
                                                    <a href="javascript:changeStatus('${thisTrainMsgForm.id}',1092)">已发布</a>
                                                </c:if>
                                            </td>
                                            <td align="center" id="recommendTag_${thisTrainMsgForm.id}">
                                                <c:if test="${thisTrainMsgForm.recommendTag == 1}">
                                                    <a href="javascript:recommendTag('${thisTrainMsgForm.id}',1)">已推送</a>
                                                </c:if>
                                                <c:if test="${thisTrainMsgForm.recommendTag != 1}">
                                                    <a href="javascript:recommendTag('${thisTrainMsgForm.id}',0)">未推送</a>
                                                </c:if>
                                            </td>
                                            <td align="center">
                                                <c:if test="${thisTrainMsgForm.status == 1091}">
                                                    <a href="javascript:if(confirm('确定要删除吗?')) delMsg('${thisTrainMsgForm.id}')">删除</a>&nbsp;&nbsp;
                                                    <a href='../msgMessageInfo/forupdate.do?id=${thisTrainMsgForm.id}'>修改</a>&nbsp;&nbsp;
                                                </c:if>
                                                <a href='../msgMessageInfo/detail.do?id=${thisTrainMsgForm.id}'>查看</a>&nbsp;&nbsp;
                                                <a href='../msgMessageInfo/copyMsgInfo.do?id=${thisTrainMsgForm.id}'>复制</a>&nbsp;&nbsp;
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clr"></div>
        </div>
    </div>
</div>

<div class="bottombody"></div>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>

<script type="text/javascript" src="/js/ueditor-new/ueditor.config.js"></script>
<script type="text/javascript" src="/js/ueditor-new/ueditor.all.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>

<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/basicFunc.js"></script>
<script type="text/javascript" src="/js/UI/tooltipster.bundle.js"></script>

<script type="text/javascript">
    dojo.require("dojo.event.*");
</script>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<script type="text/javascript" src="/js/onlineTraining/mixtraining.js"></script>
<script type="text/javascript" src="/js/message/messageInfoadd.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/public/orgAndUsersSelect.js"></script>

<script type="text/javascript">
    $(function () {
        var option = {
            initialStyle: 'p{line-height:1.5em;font-size:14px;font-family:宋体};span{font-size:14px;font-family:宋体;line-height:1.5em}'
        };

        var editor = new UE.ui.Editor(option);
        editor.render('contentArea');

        editor.addInputRule(function (root) {
            root.traversal(function (node) {
                if (node.type == 'element') {

                    switch (node.tagName) {
                        case 'span':
                            node.setStyle({'font-size': '14px', "font-family": "宋体", "line-height": "1.5em"});
                        case 'p':
                            node.setStyle({'font-size': '14px', "font-family": "宋体", "line-height": "1.5em"});
                        case 'div':
                            node.setStyle({'font-size': '14px', "font-family": "宋体"});
                        case 'li':
                            node.setStyle({'font-size': '14px', "font-family": "宋体"});
                        case 'table':
                            node.setStyle({'font-size': '14px', "font-family": "宋体"});
                    }
                }
            })
        });
    })

    function checkForm() {
        if (document.addForm.title.value == "") {
            jAlert("通知标题不能为空!", '提示', function () {
                document.addForm.title.focus();
            });
            return false;
        } else {
            $("#messageEditor").submit();
        }
        return true;
    }

    function selectMessageType() {
        var messageTypeVal = $('input:radio[name="isPublic"]:checked').val();
        var val = $('input:checkbox[name="tipType"]:checked').val();
        var confirmTypeValue = $('input:radio[name="confirmType"]:checked').val();
        var i = 0;
        if (messageTypeVal == 1156) {
            //if(val==1){
            if ($('input[name=tipType]:eq(1)').prop("checked")) {
                $("#commonContent").show();
                $("#smsContent").hide();
                //$('input:checkbox[name="tipType"]').val("");
                $('input:checkbox[name=tipType]:eq(0)').attr("checked", true);
                $('input:checkbox[name=tipType]:eq(1)').attr("checked", false);
            }
            $("#tipType").hide();
            $("#confirmType").show();
        }
        if (messageTypeVal == 1157) {
            $("#tipType").show();
            $("#confirmType").hide();
            $('input[name=confirmType]:eq(0)').attr("checked", true);
        }
    }

    function changeInputType() {
        var val = $('input:checkbox[name="tipType"]:checked').val();
        var messageTypeVal = $('input:radio[name="isPublic"]:checked').val();
        var defaultContent = $("#title").val();
        var remain = $("#total").val() - defaultContent.length;
        //alert($('input[name=tipType]:eq(1)').attr("checked"));
        if ($('input[name=tipType]:eq(1)').prop("checked")) {
            if (remain < 0) {
                remain = 0;
            }
            $('#used').val(defaultContent.length);
            $('#remain').val(remain);
            $('#smsContent').show();
            $("#sms_content").html(defaultContent);
            //$("#commonContent").hide();
            $("#smsContent").show();
        } else {
            //$("#commonContent").show();
            $("#smsContent").hide();
        }
    }

    function gbcount(message, total, used, remain) {
        var max;
        max = total.value;
        if (message.value.length > max) {
            message.value = message.value.substring(0, max);
            used.value = max;
            remain.value = 0;
        } else {
            used.value = message.value.length;
            remain.value = max - used.value;
        }
    }

    function selectdepts(userType) {
        var pop_url = "<%=path%>/pub/checkboxOrgTree.jsp?userType=" + userType;
        var pop = $("<div id='_pop_win'><h2>选择机构名称"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='320' scrolling='auto' width='100%' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {width: "320px", height: "360px", cursor: "default", left: "30%", top: "18%"}
        });
    }


    function selectusers(id_tag, name_tag) {
        var pop_url = "<%=basePath%>systemAction.do?method=selectUsers&idTag=" + id_tag + "&nameTag=" + name_tag;
        /*pop window外观定义来自css/pop_window.css*/
        var pop = $("<div id='_pop_win'><h2>选择人员<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe id='pop_selectUser' height='540' width='700' scrolling='auto' class='pop_iframe' style='margin-right:50px;' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        focusForm = "#form";//定义弹出窗关闭后，光标的定位form，解决弹出窗关闭后不能输入的问题
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {
                width: "700px",
                height: "580px",
                cursor: "default",
                left: ($left - 700) / 2 - 10 + 'px',
                top: ($top - 580) / 2 - 10 + 'px'
            }
        });
    }


    $('#tabs li').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    function userDetail(operID) {
        var pop_url = "<%=basePath%>eosorgTEmployeeAction.do?method=detail&userid=" + operID;
        var pop = $("<div id='_pop_win_sub'><h2>人员详细信息"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='400' scrolling='no' width='100%' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $("#_pop_win").unblock();
        });
        $("#_pop_win").block({
            message: pop,
            css: {width: "500px", height: "430px", cursor: "default", left: "30%", top: "10%"}
        });
    }
</script>

<script type="text/javascript">
    var $messageDetailModal = $(".remodal.messageDetail").remodal();

    ;$(function () {
        var pop_url = null;
        var messageIdInfo = {
            msgId: null
        };
        $("#messageEditor").prepareForMessageReceiver({"msgId": $("#messageEditor #msgId").val()});
        $(document).on('opening', '[data-remodal-id=messageDetail]', function () {
            var pop_url = "<%=basePath%>msgMessageInfoAction.do?method=messagedetail&id=" + messageIdInfo.msgId;
            $(".remodal.messageDetail iframe").attr("src", pop_url);
        });
        $(document).on('closed', '[data-remodal-id=messageDetail]', function () {
            $(".remodal.messageDetail iframe").attr("src", "");
        });

        $(".showMessageInfo").click(function () {
            messageIdInfo.msgId = $("input[name=msgId]", $(this)).val();
            //$messageDetailModal.open();
            <%-- window.open("<%=basePath%>msgMessageInfoAction.do?method=messagedetail&id="+messageIdInfo.msgId); --%>
            var pop_url = "<%=basePath%>msgMessageInfoAction.do?method=messagedetail&id=" + messageIdInfo.msgId;
            /*pop window外观定义来自css/pop_window.css*/
            var pop = $("<div id='_pop_win'><h2>查看培训<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
                + "<iframe id='pop_selectUser' height='540' width='698' scrolling='auto' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
                + "<div style='clear:both'></div>"
            );
            focusForm = "#form";//定义弹出窗关闭后，光标的定位form，解决弹出窗关闭后不能输入的问题
            pop.find("a.pop_close_btn").click(function () {
                $.unblockUI();
            });
            $.blockUI({
                message: pop,
                css: {
                    width: "700px",
                    height: "580px",
                    cursor: "default",
                    left: ($left - 700) / 2 - 10 + 'px',
                    top: ($top - 580) / 2 - 10 + 'px'
                }
            });

        })
    })
</script>
<script type="text/javascript">
    function delMsg(id) {
        $.ajax({
            url: '../msgMessageInfo/deleteAjax.do',
            data: {"id": id},
            datatype: 'JSON',
            success: function (data) {
                if (data.success) {
                    $("#msg_" + id).remove();
                }
            }
        });
    }

    function recommendTag(id, flag) {
        $.ajax({
            url: '../msgMessageInfo/changeRecommendMsg.do',
            data: {"id": id},
            datatype: 'JSON',
            success: function (data) {
                if (data.success) {
                    if (flag == 1) { // 已推送变未推送
                        $("#recommendTag_" + id).html("<a href='javascript:recommendTag(\"" + id + "\",0)'>未推送</a>");
                    } else {
                        $("#recommendTag_" + id).html("<a href='javascript:recommendTag(\"" + id + "\",1)'>已推送</a>");
                        $("#status_" + id).html("<a href='javascript:changeStatus(\"" + id + "\",1092)'>已发布</a>");
                    }
                }
            }
        });
    }

    function changeStatus(id, flag) {
        $.ajax({
            url: '../msgMessageInfo/changeStatus.do',
            data: {"id": id},
            datatype: 'JSON',
            success: function (data) {
                if (data.success) {
                    if (flag == 1091) { //未发布变已发布
                        $("#status_" + id).html("<a href='javascript:changeStatus(\"" + id + "\",1092)'>已发布</a>");
                    } else {
                        $("#status_" + id).html("<a href='javascript:changeStatus(\"" + id + "\",1091)'>未发布</a>");
                    }
                }
            }
        });
    }
</script>
</body>
</html>