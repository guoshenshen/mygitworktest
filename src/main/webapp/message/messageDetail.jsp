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
<!-- InstanceBegin template="/Templates/admin2.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <!-- InstanceBeginEditable name="doctitle" -->
    <title><%=Constants.systemName%>
    </title>
    <!-- InstanceEndEditable -->

    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/nav/style_home.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/skinCss/administratorStyle.css"/>
    <script type="text/javascript" src="/js/common/tools.js"></script>
    <script type="text/javascript" src="/js/common/home.js"></script>
    <script type="text/javascript" src="/js/dojojs/dojo.js"></script>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/jquery-json.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/nav/menu.js"></script>
    <script type="text/javascript" src="/js/nav/roll.js"></script>
    <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
    <script language="javascript" src="/js/jquery.cookie-min.js"></script>
    <script type="text/javascript"> dojo.require("dojo.widget.*");</script>

    <script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/public/orgAndUsersSelect.js"></script>
    <style type="text/css">
        table.homecontenttable tr th {
            font-size: 13px;
        }
    </style>
</head>
<body>
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
                <c:if test="${msgInfo.isPublic ==1156 }">
                    <span class="choice">需报名</span>
                </c:if>
                <c:if test="${msgInfo.isPublic ==1157 }">
                    <span class="choice">无需报名仅提醒</span>
                </c:if>
            </fieldset>
        </div>
        <c:if test="${msgInfo.isPublic ==1157 }">
            <div class="component" id="tipType">
                <fieldset>
                    <legend>通知方式</legend>
                    <input type="checkbox" name="tipType" value="0" checked disabled/><span class="choice">邮件(默认)</span>
                    <input type="checkbox" name="tipType" value="1" onclick="changeInputType()" id="smsType"
                           <c:if test="${msgInfo.isSMS == 1}">checked</c:if> disabled/>
                    <span class="choice">手机短信	</span>
                </fieldset>
            </div>
        </c:if>

        <c:if test="${msgInfo.isPublic ==1156 }">
            <div class="component" id="confirmType">
                <fieldset>
                    <legend>确认方式</legend>
                    <c:if test="${msgInfo.confirmType == 0 }">
                        <span class="choice">选择参加</span>
                    </c:if>
                    <c:if test="${msgInfo.confirmType == 1 }">
                        <span class="choice">必须参加</span>
                   </c:if>
                </fieldset>
            </div>
        </c:if>

        <div class="component" id="commonContent">
            <fieldset>
                <legend>通知内容</legend>
                ${msgInfo.content }
            </fieldset>
            <c:if test="${msgInfo.attachmentPath != null}">
                <a href='${msgInfo.attachmentPath }'>附件下载</a>
            </c:if>
        </div>
    </div>
</div>

<script type="text/javascript">
    function resetCurrentBase() {
        return window;
    }
    ;$(function () {
        window.parent.iFrameHeight('messageDetailFrame', 20);
        $("#messageEditor").prepareForMessageReceiver({"msgId": $("#msgId").val()});
    })
</script>
</body>
</html>
