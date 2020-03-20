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
    <title></title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
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
                <li class="selected"><a href="#tabs"><span>修改通知</span></a></li>
                <li><a href="../msgMessageArrangeList/searchArrangeUser.do"><span>回执统计</span></a></li>
                <li><a href="../msgMessageInfo/forPreviousTrainingNotice.do"><span>以往培训通知</span></a></li>
            </ul>
            <div class="clr"></div>
            <div class="homezoneall">
                <div class="homezonehead">
                    <span class="homezonetitle">修改通知</span>
                </div>
                <div class="homezonecontent">
                    <form action="" method="post" id="messageEditor" name="addForm">
                        <input type="hidden" name="filterFlag" value="false"/>
                        <input type="hidden" id="msgId" name="id" value="${msgMessageInfoForm.id}"/>
                        <input type='hidden' name='trainId' value='${msgMessageInfoForm.trainId }'/>
                        <div class="messageForm">
                            <div class="component">
                                <fieldset>
                                    <legend>标题(创建时间： ${msgMessageInfoForm.validDate})</legend>
                                    <input type="text" name="title" class="title" id="title" value='${msgMessageInfoForm.title}'/><font color='red'>*</font>
                                </fieldset>
                            </div>
                            <div class="component">
                                <fieldset class="senderSelect effectArea" id="studentList">
                                    <legend>学员选择——方式1：指定姓名</legend>
                                    <span class="orangeSimpleButton simpleButton userAddMenu button" style="margin-left:50px" id="selectStudent">人员选择</span>
                                    <span class="blueSimpleButton simpleButton withMsgnum showUsersPreview button" style="margin-left:50px;">人员查看
                                        <em class="j-nav-msgnum">0</em>
                                    </span>
                                    <div class="hiddenArea"></div>
                                    <div class="detailInfoForShow notShow">
                                        <table></table>
                                    </div>
                                </fieldset>
                                <fieldset class="senderSelect effectArea" id="orgSelect">
                                    <legend>学员选择——方式2：指定单位</legend>
                                    <span class="orangeSimpleButton simpleButton orgSelect button" style="margin-left:50px" id="selectOrgs">单位选择</span>
                                    <span class="blueSimpleButton simpleButton withMsgnum showOrgsPreview button" style="margin-left:50px;">单位查看
                                        <em class="j-nav-msgnum">0</em>
                                    </span>
                                    <div class="hiddenArea"></div>
                                    <div class="detailInfoForShow notShow">
                                        <table></table>
                                    </div>
                                </fieldset>
                                <fieldset class="senderSelect effectArea" id="secretaryList">
                                    <legend>联络员抄送</legend>
                                    <span class="orangeSimpleButton simpleButton userAddMenu button" style="margin-left:50px" id="secretarySelect">人员选择</span>
                                    <span class="blueSimpleButton simpleButton withMsgnum showUsersPreview button" style="margin-left:50px;">人员查看
                                        <em class="j-nav-msgnum">0</em>
                                    </span>
                                    <div class="hiddenArea"></div>
                                    <div class="detailInfoForShow notShow">
                                        <table></table>
                                    </div>
                                </fieldset>
                                <fieldset class="senderSelect effectArea" id="directorList">
                                    <legend>领导抄送</legend>
                                    <span class="orangeSimpleButton simpleButton userAddMenu button" style="margin-left:50px" id="selectDirector">人员选择</span>
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
                                    <input type="radio" name="isPublic" value="1156" <c:if test="${msgMessageInfoForm.isPublic == 1156}">checked</c:if> onclick="selectMessageType()"/>
                                    <span class="choice">需报名</span>
                                    <input type="radio" name="isPublic" value="1157" <c:if test="${msgMessageInfoForm.isPublic == 1157}">checked</c:if> onclick="selectMessageType()"/>
                                    <span class="choice">无需报名仅提醒</span>
                                </fieldset>
                            </div>
                            <div class="component" id="tipType" style="display:none">
                                <fieldset>
                                    <legend>通知方式</legend>
                                    <input type="checkbox" name="tipType" value="0" disabled checked/>
                                    <span class="choice">邮件(默认)</span>
                                    <input type="checkbox" name="tipType" value="1" onclick="changeInputType()" id="smsType" <c:if test="${msgMessageInfoForm.isSMS == 1}">checked</c:if> />
                                    <span class="choice">手机短信(70字内纯文本)</span>
                                </fieldset>
                            </div>
                            <div class="component" id="confirmType">
                                <fieldset>
                                    <legend>确认方式</legend>
                                    <input type="radio" name="confirmType" value="0" <c:if test="${msgMessageInfoForm.confirmType == 0}">checked</c:if> />
                                    <span class="choice">选择参加</span>
                                    <input type="radio" name="confirmType" value="1" <c:if test="${msgMessageInfoForm.confirmType == 1}">checked</c:if>  />
                                    <span class="choice">必须参加</span>
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
                                <span class="orangeSimpleButton simpleButton" onclick="window.location.href='../msgMessageInfo/foradd.do'">返&nbsp;&nbsp;回</span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
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

<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript"> dojo.require("dojo.widget.*");</script>

<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<!-- InstanceBeginEditable name="head" -->
<script type="text/javascript" src="/js/ueditor-new/ueditor.config.js"></script>
<script type="text/javascript" src="/js/ueditor-new/ueditor.all.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/onlineTraining/mixtraining.js"></script>
<script type="text/javascript" src="/js/message/messageInfoadd.js"></script>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>

<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/public/orgAndUsersSelect.js"></script>
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
            window.open("<%=basePath%>msgMessageInfoAction.do?method=messagedetail&id=" + messageIdInfo.msgId);
        })


    })
</script>

<script type="text/javascript">
    $(function () {
        init();
        var option = {
            initialStyle: 'p{line-height:1.5em;font-size:14px;font-family:宋体};span{font-size:14px;font-family:宋体}'
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
            var $messageEditor = $("#messageEditor");
            $messageEditor.attr("action", "../msgMessageInfo/update.do");
            $messageEditor.submit();
        }
        return true;
    }

    function init() {
        var val = $('input:checkbox[name="tipType"]:checked').val();
        var messageTypeVal = $('input:radio[name="isPublic"]:checked').val();
        var smsContent = $("#sms_content").val();
        var remain = $("#total").val() - smsContent.length;
        if (remain < 0) {
            remain = 0;
        }
        if (messageTypeVal == 1157) {
            $("#tipType").show();
            $("#confirmType").hide();
            if ($('input[name=tipType]:eq(1)').prop("checked")) {
                $('#used').val(smsContent.length);
                $('#remain').val(remain);
                $("#smsContent").show();
            } else {
                $("#smsContent").hide();
            }
        } else {
            $("#tipType").hide();
            $("#confirmType").show();
            $("#smsContent").hide();
            $("#commonContent").show();
        }

    }

    function selectMessageType() {
        var messageTypeVal = $('input:radio[name="isPublic"]:checked').val();
        var val = $('input:checkbox[name="tipType"]:checked').val();
        var confirmTypeValue = $('input:radio[name="confirmType"]:checked').val();
        if (messageTypeVal == 1156) {
            //if(val==1){
            if ($('input[name=tipType]:eq(1)').prop("checked")) {
                $("#commonContent").show();
                $("#smsContent").hide();
                //$('input:checkbox[name="tipType"]').attr("checked",false);
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
        var smsContent = $("#sms_content").val();
        var remain = $("#total").val() - defaultContent.length;
        if (remain < 0) {
            remain = 0;
        }
        //if(val==1){
        if ($('input[name=tipType]:eq(1)').prop("checked")) {
            $('#smsContent').show();
            var str = document.getElementById("sms_content").value;
            str = str.replace(/<\/?[^>]*>/g, ''); //去除HTML tag
            str = str.replace(/[ | ]*\n/g, '\n'); //去除行尾空白
            str = str.replace(/&nbsp;/ig, '');//去掉&nbsp;
            $('#smsContent textarea').text(str);//jquery过滤html标签
            if (smsContent.length == 0) {
                $('#used').val(defaultContent.length);
                $('#remain').val(remain);
                $("#sms_content").html(defaultContent);
            }
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

        }
        else {
            used.value = message.value.length;
            remain.value = max - used.value;
        }

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

    function clearusers(idList, nameList) {
        document.getElementById(idList).value = "";
        document.getElementById(nameList).value = "";
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


    function selectusers(idTag, nameTag) {
        var pop_url = "<%=basePath%>systemAction.do?method=selectUsers&idTag=" + idTag + "&nameTag=" + nameTag;

        var pop = $("<div id='_pop_win'><h2>选择人员"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='500' width='700' scrolling='auto' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        focusForm = "#form";//定义弹出窗关闭后，光标的定位form，解决弹出窗关闭后不能输入的问题
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {width: "700px", height: "530px", cursor: "default", left: "20%", top: "15%"}
        });
    }

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

    $('#tabs li').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

</script>
</body>
</html>
