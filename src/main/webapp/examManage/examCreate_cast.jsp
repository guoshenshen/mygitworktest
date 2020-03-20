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
<!--[if IE 6 ]> <html id="ne_wrap" class="ne_ua_ie6 ne_ua_ielte8"> <![endif]-->
<!--[if IE 7 ]> <html id="ne_wrap" class="ne_ua_ie7 ne_ua_ielte8"> <![endif]-->
<!--[if IE 8 ]> <html id="ne_wrap" class="ne_ua_ie8 ne_ua_ielte8"> <![endif]-->
<!--[if IE 9 ]> <html id="ne_wrap" class="ne_ua_ie9"> <![endif]-->
<!--[if (gte IE 10)|!(IE)]><!-->
<html>
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
    <style type="text/css">

    </style>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <!-- InstanceBeginEditable name="main" -->
            <div class="gl_11_1">
                <span class="gl_11_yes" style="margin:-15px;">1、考试基本信息</span>
                <span class="gl_11_no">2、指定试卷</span>
                <span class="gl_11_no" style="margin-left:15px;">3、安排人员</span>
                <span class="gl_11_no" style="margin-left:-20px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、发布通知</span>
            </div>

            <div id="content_02">
                <form action="" id="ExamInfoForm" name="ExamInfoForm" method="post">
                    <input type="hidden" id="eid" value="${examInfo.ID}"/>
                    <input type="hidden" id="orgId" name="orgId" value="${orgId}"/>
                    <input type="hidden" name="filterFlag" value="false"/>
                    <table class="table0 table table-striped table-bordered batchOperation" width="80%" cellspacing="0" cellpadding="0" align="center">
                        <tr>
                            <th width="40%" align="left">
                                考试名称：
                            </th>
                            <td width="60%">
                                <input type="text" name="examTitle" size="60" value="${examInfo.examTitle}" maxlength="40"/>
                                <font color=red>&nbsp;*</font>
                            </td>
                        </tr>
                        <tr>
                            <th align="left">
                                开始时间：
                            </th>
                            <td>
                                <input name="startTime" id="startTime" class="Wdate" type="text"
                                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false,readOnly:true})" value="${examInfo.startTime}" size=25/>
                                <font color=red>&nbsp;*</font>
                            </td>
                        </tr>
                        <tr>
                            <th align="left">
                                结束时间：
                            </th>
                            <td>
                                <input name="endTime" id="endTime" class="Wdate" type="text"
                                       onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false,readOnly:true})" value="${examInfo.endTime}" size=25/>
                                <font color=red>&nbsp;*</font>
                            </td>
                        </tr>
                        <tr>
                            <th align="left">
                                考试总时间长(单位：小时)：
                            </th>
                            <td>
                                <input type="text" name="totalTime" width="10" value="${examInfo.totalTime}" maxlength="10"/>
                                <font color=red>&nbsp;*</font>
                            </td>
                        </tr>
                        <tr>
                            <th align="left">
                                考试类型：
                            </th>
                            <td>
                                <input type="radio" name="examType" value="0" <c:if test="${examInfo.examType == 0 }">checked</c:if> onclick="activeOther()"/>
                                线上
                                <input type="radio" name="examType" value="1" <c:if test="${examInfo.examType == 1 }">checked</c:if> onclick="disableOther()"/>
                                线下
                            </td>
                        </tr>
                        <tr>
                            <th align="left">
                                答卷方式：
                            </th>
                            <td>
                                <input type="radio" name="examStyle" value="0" <c:if test="${examInfo.examStyle == 0}">checked</c:if> />
                                开卷
                                <input type="radio" name="examStyle" value="1" <c:if test="${examInfo.examStyle == 1}">checked</c:if> />
                                闭卷
                            </td>
                        </tr>
                        <tr>
                            <th align="left">
                                试卷成绩占总成绩的比例(范围:<=1)：
                            </th>
                            <td>
                                <input type="text" name="proportion" width=10 value="${examInfo.proportion}" maxlength="10"/>
                                <font color=red>&nbsp;*</font>（例如试卷成绩为总成绩的70%,则比例为0.7）
                            </td>
                        </tr>
                        <tr>
                            <th align="left">
                                是否允许报名：
                            </th>
                            <td>
                                <input type="radio" id="isApply" name="isApply" value="1" <c:if test="${examInfo.isApply == 1 }">checked</c:if> onclick="isapply()"/>
                                是&nbsp;&nbsp;&nbsp;
                                <input type="radio" id="isApply" name="isApply" value="0" <c:if test="${examInfo.isApply == 0 }">checked</c:if> onclick="isnotapply()"/>
                                否
                            </td>
                        </tr>
                        <tr id="isNeedApprove1" <c:if test="${examInfo.isApply == 0 }">style="display:none"</c:if> >
                            <th align="left">
                                是否需要审批：
                            </th>
                            <td>
                                <input type="radio" name="isNeedApprove" id="isNeedApprove" value="1" <c:if test="${examInfo.isNeedApprove == 1 }">checked</c:if> />
                                是&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="isNeedApprove" id="isNeedApprove" value="0" <c:if test="${examInfo.isNeedApprove == 0 }">checked</c:if> />
                                否
                            </td>
                        </tr>
                        <tr>
                            <th align="left">
                                何时看到答案：
                            </th>
                            <td>
                                <input type="radio" id="ifDisplayAnswer" name="ifDisplayAnswer" value="0" <c:if test="${examInfo.ifDisplayAnswer == 0 }">checked</c:if> />
                                考试时间结束看答案&nbsp;&nbsp;&nbsp;
                                <input type="radio" id="ifDisplayAnswer" name="ifDisplayAnswer" value="1" <c:if test="${examInfo.ifDisplayAnswer == 1 }">checked</c:if> />
                                考试后立即看答案
                            </td>
                        </tr>
                        <tr>
                            <th align="left">
                                是否允许多次考试：
                            </th>
                            <td>
                                <input type="radio" id="ifRepeatExam" name="ifRepeatExam" value="1" <c:if test="${examInfo.ifRepeatExam == 1 }">checked</c:if> />
                                是&nbsp;&nbsp;&nbsp;
                                <input type="radio" id="ifRepeatExam" name="ifRepeatExam" value="0" <c:if test="${examInfo.ifRepeatExam == 0 }">checked</c:if> />
                                否
                            </td>
                        </tr>
                        <tr>
                            <th align="left">
                                考试说明：
                            </th>
                            <td style="width:60%">
                                <div id="examDescription" style="max-width:660px;">${examInfo.examDescription}</div>
                                <textarea name="examDescription" style="display:none">${examInfo.examDescription}</textarea>
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <div class="btnContainer">
                        <div class="clearfix" style="margin-left:45%">
                            <a href="../examManage/toExamManageHome.do?pageNo=1" class="btn-blue-l">
                                <span class="btn-blue-r">返&nbsp;&nbsp;回</span>
                            </a>
                            <a href="javascript:void(0);" onclick="return checkForm();" class="btn-orange-l">
                                <span class="btn-orange-r">下一步</span>
                            </a>
                        </div>
                    </div>
                </form>
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
<link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
<link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
<link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/common/home.js"></script>
<!-- InstanceBeginEditable name="head" -->
<script type="text/javascript" src="/js/CheckFunction.js"></script>
<!-- InstanceEndEditable -->
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/CheckFunction.js"></script>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/widget/classify.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/editor/wangEditor.min.js"></script>
<script type="text/javascript">
    $(function () {
        /*
        var option = {
           initialFrameWidth:590,
           initialFrameHeight:330
        };
        var editor = new UE.ui.Editor(option);
        editor.render('examDescription');
        */
        var WE = window.wangEditor;
        var Editor = new WE('#examDescription');
        Editor.customConfig.onchange = function (html) {
            $("textarea[name=examDescription]").val(html).html(html);
        };
        Editor.create();
    });


    function examInfoInsert() {
        document.ExamInfoForm.action = "examInfo.do?method=examInsert";
        document.getElementById('ExamInfoForm').submit();
    }

    function examInfoUpdate() {
        document.ExamInfoForm.action = "examInfo.do?method=examUpdate&eid=${examInfo.id}";
        document.getElementById('ExamInfoForm').submit();
    }

    function checkForm() {
        if (document.ExamInfoForm.examTitle.value == "") {
            //alert("考试名称不能为空!");
            jAlert("考试名称不能为空!", "考试信息填写提示");
            document.ExamInfoForm.examTitle.focus();
            return false;
        }
        if (document.ExamInfoForm.startTime.value == "") {
            //alert("考试开始时间不能为空!");
            jAlert("考试开始时间不能为空!", "考试信息填写提示");
            document.ExamInfoForm.startTime.focus();
            return false;
        }
        if (document.ExamInfoForm.endTime.value == "") {
            //alert("考试结束时间不能为空!");
            jAlert("考试结束时间不能为空!", "考试信息填写提示");
            document.ExamInfoForm.endTime.focus();
            return false;
        }
        if (compare_date(document.ExamInfoForm.startTime.value, document.ExamInfoForm.endTime.value)) {
            //alert("开始时间不能大于结束时间!");
            jAlert("开始时间不能大于结束时间!", "考试信息填写提示");
            return false;
        }
        if (document.ExamInfoForm.totalTime.value == "") {
            //alert("考试总时间长不能为空!");
            jAlert("考试总时间长不能为空!", "考试信息填写提示");
            document.ExamInfoForm.totalTime.focus();
            return false;
        } else if (!is_numeric(document.ExamInfoForm.totalTime, 1, "考试总时间长填入非数字错误!")) {
            return false;
        } else if (!is_greaterZero(document.ExamInfoForm.totalTime, 1, "考试总时间长填入大于0的实数!")) {
            return false;
        }
        if (document.ExamInfoForm.proportion.value == "") {
            //alert("试卷成绩与平常成绩的比例不能为空!");
            jAlert("试卷成绩与平常成绩的比例不能为空!", "考试信息填写提示");
            document.ExamInfoForm.proportion.focus();
            return false;
        } else if (!is_greaterZero(document.ExamInfoForm.proportion, 1, "成绩比例填入大于0的实数!")) {
            return false;
        } else if (!is_nogreater(document.ExamInfoForm.proportion, 1, 1, "成绩比例不能大于1!")) {
            return false;
        }
        if (document.getElementById("eid").value != "") {
            examInfoUpdate();
        } else {
            examInfoInsert();
        }
        return true;

    }

    function disableOther() {
        var objRadio = document.ExamInfoForm.getElementsByTagName("input");
        for (var i = 0; i < objRadio.length; i++) {
            if (objRadio[i].type == "radio") {
                if (objRadio[i].name == "examType" || objRadio[i].name == "examStyle") {
                } else {
                    objRadio[i].disabled = true;
                    if (objRadio[i].value == 0) {
                        objRadio[i].checked = true;
                    }

                }
            }
        }
    }

    function activeOther() {
        var objRadio = document.ExamInfoForm.getElementsByTagName("input");
        for (var i = 0; i < objRadio.length; i++) {
            if (objRadio[i].type == "radio") {
                if (objRadio[i].name == "examType" || objRadio[i].name == "examStyle") {
                } else {
                    objRadio[i].disabled = false;
                }
            }
        }
    }

    function checkAndFormat(element) {
        if (element.value == "") {
            element.value = element.defaultValue;
        } else {
            var date = element.value;
            if (isValid(date)) {
                var dateFormatType = getDateFormatType(date);

                if (0 != dateFormatType) {
                    element.value = formatDate(date, dateFormatType);
                } else {
                    //alert("格式非法");
                    jAlert("格式非法", "考试信息填写提示");
                }
            } else {
                document.getElementById(element.name).innerHTML = "<font color=\'red\'>格式非法，请检查是否有字母、汉字或者特殊字符</font>"
            }

        }
    }

    function getDateFormatType(date) {
        var RegExp1 = /\d{6} \d{2}(\:|\：)\d{2}(\:|\：)\d{2}/;
        var RegExp2 = /\d{4} \d{2}(\:|\：)\d{2}(\:|\：)\d{2}/;
        var RegExp3 = /\d{4}-\d{2}-\d{2} \d{2}(\:|\：)\d{2}(\:|\：)\d{2}/;
        var RegExp4 = /\d{4}-\d{2}-\d{2} \d{2}(\:|\：)\d{2}/;
        var RegExp5 = /\d{6} \d{2}(\:|\：)\d{2}/;

        if (RegExp1.test(date)) return 1;
        if (RegExp2.test(date)) return 2;
        if (RegExp3.test(date)) return 3;
        if (RegExp4.test(date)) return 4;
        if (RegExp5.test(date)) return 5;
        return 0;
    }

    function formatDate(date, dateType) {
        switch (dateType) {
            case 1:
                var date2 = date;
                var str = date2.substring(0, 4) + "-" + date2.substring(4, 6) + "-" + date2.substring(6, 8) + " " + date2.substring(9, 11) + ":" + date2.substring(12, 14) + ":" + date2.substring(15, 17);
                return str;
                break;
            case 2:
                var date2 = date;
                var str = "20" + date2.substring(0, 2) + "-" + date2.substring(2, 4) + "-" + date2.substring(4, 6) + " " + date2.substring(7, 9) + ":" + date2.substring(10, 12) + ":" + date2.substring(13, 15);
                return str;
                break;
            case 3:
                return date;
                break;
            case 4:
                return date + ":00";
                break;
            case 5:
                var date2 = date;
                var str = date2.substring(0, 4) + "-" + date2.substring(4, 6) + "-" + date2.substring(6, 8) + " " + date2.substring(9, 11) + ":" + date2.substring(12, 14) + ":00";
                return str;
                break;
        }
    }

    function isValid(date) {
        var patrn = /([0-9]| |:|：)/;
        return patrn.test(date);
    }

    function isapply() {
        //document.getElementById('isNeedApprove1').style("display","");
        document.getElementById('isNeedApprove1').style.display = "";
    }

    function isnotapply() {
        //document.getElementById('isNeedApprove1').style("display","none");
        document.getElementById('isNeedApprove1').style.display = "none";
    }
</script>
</body>
</html>
