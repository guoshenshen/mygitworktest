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
<!-- InstanceBegin template="/Templates/admin4.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <!-- InstanceBeginEditable name="doctitle" -->
    <title><%=Constants.systemName%>
    </title>
    <!-- InstanceEndEditable -->
    <link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>

    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts-extend.css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/mixtraining/addrbook.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/jquery-json.js"></script>
    <script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/UI/remodal.min.js"></script>
    <script type="text/javascript" src="/js/jquery.form.js"></script>
    <script type="text/javascript" src="/js/calendar.js"></script>
    <script type="text/javascript" src="/js/basicFunc.js"></script>
    <script type="text/javascript" src="/js/mixtraining/editAddressBook.js"></script>
    <script type="text/javascript" src="/js/public/jquery.excel.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#forExportAddressBook").click(function () {
                var options = {};
                options.beforeSend = function (data) {
                    if ($("#downloadButton").length > 0) {
                        $("#downloadButton").hide();
                    }
                };
                options.success = function (data) {
                    if (data.result == 'true') {
                        if ($("#downloadButton").length == 0) {
                            var downloadButton = "<button class='btn btn-success'  id='downloadButton'>下载</button>";
                            $(downloadButton).insertAfter($("#forExportAddressBook"));
                        } else {
                            $("#downloadButton").show();
                        }
                        $("#downloadButton").click(function () {
                            window.open(data.downloadPath);
                        });
                    } else {
                        try {
                            jAlert("表格信息导出失败", '提示信息');
                        } catch (e) {
                            jAlert("表格信息导出失败");
                        }
                    }
                };
                $("#personnelInfoForAdd").exportTableInCurrentView(options);
            })
        })
    </script>
</head>
<body class="admin" style="padding-bottom:10px;">
<br/>
<table class='table table-striped table-bordered  addrBookInfoTable ' width='90%' align='center' cellspacing='0' cellpadding='0'>
    <tr class='tableTh'>
        <th width='30%' class='ctext'>通讯录名称</th>
        <td class='ctext1'>
            <div style='float:left;'>${addressBookInfo.addrbookName}</div>
        </td>
    </tr>

    <tr>
        <th class='ctext'>通讯录类型</th>
        <td class='ctext1'>
            <c:if test="${addressBookInfo.addrbookRole == 0 }">部门学员通讯录</c:if>
            <c:if test="${addressBookInfo.addrbookRole == 1 }">部门培训联络员通讯录</c:if>
            <c:if test="${addressBookInfo.addrbookRole == 2 }">部门领导通讯录</c:if>
        </td>
    </tr>
    <tr>
        <th class="ctext">创建时间</th>
        <td class="ctext1">${addressBookDate}</td>
    </tr>
</table>
<div class="condition  senderSelect effectArea " id="selectedUserId">
    <button type="button" class="btn btn-info" id="forExportAddressBook" style="margin-right:10px;">人员导出</button>
    <button type="button" class='btn btn-success' id='downloadButton' style="display:none">下载</button>
</div>
<div class="addrPersonListTitle">&nbsp;&nbsp;通讯录人员</div>
<div style="width:100%;margin-top:10px">
    <table class="table table-striped table-bordered" rules="cols" width="100%" cellspacing="0" cellpadding="0" id="personnelInfoForAdd">
        <tr class='tableTh'>
            <th width='5%'></th>
            <th width='9%'>用户姓名</th>
            <th width='15%'>单位名称</th>
            <th width='16%'>员工登陆名</th>
            <th width='20%'>电子邮箱</th>
            <th width='10%'>电话</th>
        </tr>
        <c:forEach var="addressBookUser" items="${addressBookUsers }" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${addressBookUser.operatorName }</td>
                <td>${addressBookUser.orgName }</td>
                <td>${addressBookUser.userId }</td>
                <td>${addressBookUser.email }</td>
                <td>${addressBookUser.tel }</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
