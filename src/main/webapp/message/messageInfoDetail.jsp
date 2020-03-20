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
    <title>培训项目基本信息管理</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <ul id="tabs">
                <li class="selected"><a href="#tabs"><span>通知详情</span></a></li>
                <li><a href="../msgMessageArrangeList/searchArrangeUser.do"><span>回执统计</span></a></li>
                <li><a href="../msgMessageInfo/forPreviousTrainingNotice.do"><span>以往培训通知</span></a></li>
            </ul>
            <div class='clr'></div>
            <div class="homezoneall">
                <div class="homezonehead">
                    <span class="homezonetitle">通知明细</span>
                </div>
                <div class="homezonecontent">
                    <input type="hidden" id="msgId" name="id" value="${msgInfo.id}"/>
                    <div class="messageForm">
                        <div class="component">
                            <fieldset>
                                <legend>标题(创建时间： ${msgInfo.validDate})</legend>
                                <p>${msgInfo.title }</p>
                            </fieldset>
                        </div>
                        <div class="component">
                            <fieldset class="senderSelect effectArea readOnly" id="studentList">
                                <legend>学员选择——方式1：指定姓名</legend>
                                <span class="blueSimpleButton simpleButton withMsgnum showUsersPreview button" style="margin-left:50px;">人员查看
                                    <em class="j-nav-msgnum">0</em>
                                </span>
                                <div class="hiddenArea"></div>
                                <div class="detailInfoForShow notShow">
                                    <table></table>
                                </div>
                            </fieldset>
                            <fieldset class="senderSelect effectArea readOnly" id="orgSelect">
                                <legend>学员选择——方式2：指定单位</legend>
                                <span class="blueSimpleButton simpleButton withMsgnum showOrgsPreview button" style="margin-left:50px;">单位查看
                                    <em class="j-nav-msgnum">0</em>
                                </span>
                                <div class="hiddenArea"></div>
                                <div class="detailInfoForShow notShow">
                                    <table></table>
                                </div>
                            </fieldset>
                            <fieldset class="senderSelect effectArea readOnly" id="secretaryList">
                                <legend>联络员抄送</legend>
                                <span class="blueSimpleButton simpleButton withMsgnum showUsersPreview button" style="margin-left:50px;">人员查看
                                    <em class="j-nav-msgnum">0</em>
                                 </span>
                                <div class="hiddenArea"></div>
                                <div class="detailInfoForShow notShow">
                                    <table></table>
                                </div>
                            </fieldset>
                            <fieldset class="senderSelect effectArea readOnly" id="directorList">
                                <legend>领导抄送</legend>
                                <span class="blueSimpleButton simpleButton withMsgnum showUsersPreview button" style="margin-left:50px;">人员查看
                                    <em class="j-nav-msgnum">0</em>
                                </span>
                                <div class="hiddenArea"></div>
                                <div class="detailInfoForShow notShow">
                                    <table></table>
                                </div>
                            </fieldset>
                        </div>
                        <div class="component">
                            <fieldset>
                                <legend>通知类别</legend>
                                <c:if test="${msgInfo.isPublic == 1156 }" ><span class="choice">需报名</span></c:if>
                                <c:if test="${msgInfo.isPublic == 1157 }" ><span class="choice">无需报名仅提醒</span></c:if>
                            </fieldset>
                        </div>

                        <c:if test="${msgInfo.isPublic == 1157 }" >
                            <div class="component" id="tipType">
                                <fieldset>
                                    <legend>通知方式</legend>
                                    <input type="checkbox" name="tipType" value="0" checked disabled/><span class="choice">邮件(默认)</span>
                                    <input type="checkbox" name="tipType" value="1" onclick="changeInputType()" id="smsType" <c:if test="${msgInfo.isSMS == 1 }" >checked</c:if> disabled/>
                                    <span class="choice">手机短信	</span>
                                </fieldset>
                            </div>
                        </c:if>

                        <c:if test="${msgInfo.isPublic == 1156 }" >
                            <div class="component" id="confirmType">
                                <fieldset>
                                    <legend>确认方式</legend>
                                    <c:if test="${msgInfo.confirmType == 0 }" ><span class="choice">选择参加</span></c:if>
                                    <c:if test="${msgInfo.confirmType == 1 }" ><span class="choice">必须参加</span></c:if>
                                </fieldset>
                            </div>
                        </c:if>

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
                                ${msgInfo.content }
                            </fieldset>
                            <c:if test="${msgInfo.attachmentPath != null}"><a href='${msgInfo.attachmentPath }'>附件下载</a></c:if>
                        </div>

                        <div class="btnContainer">
                            <c:if test="${sendable == 1}">
                                <c:if test="${msgInfo.isPublic == 1156}">
                                    <span class="blueSimpleButton simpleButton" onclick="sendType();" style="width:100px">发送邮件</span>
                                </c:if>
                                <c:if test="${msgInfo.isPublic == 1157}">
                                    <c:if test="${msgInfo.isSMS != 0}">
                                        <span class="blueSimpleButton simpleButton" onclick="sendType();">发送邮件</span>
                                    </c:if>
                                    <c:if test="${msgInfo.isSMS == 1}">
                                        <span class="blueSimpleButton simpleButton" onclick="sendType();">发送短信</span>
                                    </c:if>
                                </c:if>
                            </c:if>
                            <span class="orangeSimpleButton simpleButton" onclick="window.location.href='../msgMessageInfo/foradd.do'">返&nbsp;&nbsp;回</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="clr"></div>
        </div>
    </div>
</div>
<div class="bottombody"></div>
<div class='remodal messageDetail' data-remodal-id='messageDetail' role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc' style="max-width:90%;height:95%;padding: 0px;">
    <button data-remodal-action='close' class='remodal-close' aria-label="Close"></button>
    <div style="overflow-y:auto;width: 100%;height: 94%;margin-top: 40px;">
        <iframe frameBorder="0" scrolling="no" width="100%" id="messageDetailFrame" class='pop_iframe' src=''></iframe>
    </div>
</div>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>

<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript"> dojo.require("dojo.widget.*");</script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/public/orgAndUsersSelect.js"></script>

<!-- InstanceBeginEditable name="head" -->

<script type="text/javascript">

    function checkForm() {
        if (document.addForm.title.value == "") {
            //alert("通知标题不能为空!");
            //document.addForm.title.focus();
            jAlert("通知标题不能为空!", '提示', function () {
                document.addForm.title.focus();
            });
            return false;
        } else {
            document.getElementById('form').action = "msgMessageInfoAction.do?method=update";
            document.getElementById('form').submit();
        }
        return true;
    }

    function checkType() {
        if (document.getElementById("sendtype1").checked != true
            && document.getElementById("sendtype2").checked != true) {
            //alert("请选择发布方式!");
            jAlert('<font color=red>请选择发布方式!</font>', '提示', function () {
            })
            return false;
        } else {
            sendType();
        }

        return true;

    }

    function sendType() {
        showBufferMask();
        window.location.href = "../msgMessageInfo/sendMessageByType.do?msgId=" +${msgInfo.id};
    }

    function getReceiverList(idTag, nameTag) {
        var pop_url = "<%=basePath%>AddrBookAction.do?method=selectAddrBook&idTag=" + idTag + "&nameTag=" + nameTag;
        /*pop window外观定义来自css/pop_window.css*/
        var pop = $("<div id='_pop_win'><h2>选择通讯录人员"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='380' width='400' scrolling='no' class='pop_iframe' style='margin-right:50px;' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {width: "400px", height: "400px", cursor: "default", left: "20%", top: "15%"}
        });
    }

    function selectusers(idTag, nameTag) {
        var pop_url = "<%=basePath%>systemAction.do?method=selectUsers&idTag=" + idTag + "&nameTag=" + nameTag;

        var pop = $("<div id='_pop_win'><h2>选择人员"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='480' width='670' scrolling='no' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {width: "670px", height: "500px", cursor: "default", left: "20%", top: "15%"}
        });
    }

    $('#tabs li').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    function getMessageInfo(id) {
        var pop_url = "<%=basePath%>msgMessageInfoAction.do?method=messagedetail&id=" + id;
        /*pop window外观定义来自css/pop_window.css*/
        var pop = $("<div id='_pop_win'><h2>通知查看"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='480' width='800' scrolling='yes' class='pop_iframe' style='margin-right:50px;' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {width: "800px", height: "500px", cursor: "default", left: "10%", top: "10%"}
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
        $("#messageEditor").prepareForMessageReceiver({"msgId": $("#msgId").val()});
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
            window.open("<%=basePath%>msgMessageInfoAction.do?method=messagedetail&id=" + messageIdInfo.msgId);
        })

    })
</script>
</body>
</html>