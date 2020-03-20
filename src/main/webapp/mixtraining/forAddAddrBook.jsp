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
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts-extend.css"/>
    <link href="/css/mixtraining/addrbook.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/common/tools.js"></script>
    <script type="text/javascript" src="/js/common/home.js"></script>
    <script type="text/javascript" src="/js/dojojs/dojo.js"></script>
    <script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/jquery-json.js"></script>
    <%--<script type="text/javascript" src="/js/nav/menu.js"></script>--%>
    <script type="text/javascript" src="/js/nav/roll.js"></script>
    <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
    <script language="javascript" src="/js/jquery.cookie-min.js"></script>
    <script type="text/javascript" src="/js/jquery.form.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
    <script type="text/javascript">
        $(function () {

            var mixTrainUserInfo = window.parent.addressBookWindow.operatorIdArray;
            var flag = window.parent.addressBookWindow.flag;

            var alertInfoOption = {
                alertTitle: '提示信息',
                confirmTitle: '',
                dialogStyle: 'blueStyle'
            }

            $.alerts.dialogClass = alertInfoOption.dialogStyle;

            $("#addrbook").change(function () {
                var value = $(this).children('option:selected').val();
                if (value == "-1") {
                    $("tr.foraddNewBook").show();
                } else {
                    $("tr.foraddNewBook").hide();
                }
            });


            $.ajax({
                data: {mixTrainUserInfo: mixTrainUserInfo, flag: flag},
                type: "post",
                url: "../addrBook/getUserInfoByMixTrainUserInfo.do",
                dataType: "json",
                traditional: true,
                success: function (data) {
                    var tbodyArray = new Array();
                    var resultData = data.data;
                    for (var i in resultData) {
                        tbodyArray.push("<tr id=tr_" + resultData[i].operatorId + ">");
                        tbodyArray.push("<td><input name='importPersonId' type='hidden' value=" + resultData[i].operatorId + ">");
                        tbodyArray.push("<a  href='javascript:void(0);' onclick=javascript:$('#tr_" + resultData[i].operatorId + "').remove(); >删除</a>");
                        tbodyArray.push("</td>");
                        tbodyArray.push("<td>" + resultData[i].operatorName + "</td>");
                        tbodyArray.push("<td>" + resultData[i].orgName + "</td>");
                        tbodyArray.push("<td>" + resultData[i].userId + "</td>");
                        tbodyArray.push("<td>" + resultData[i].oemail + "</td>");
                        tbodyArray.push("<td>" + resultData[i].otel + "</td>");
                        tbodyArray.push("</tr>");
                    }
                    $("#personnelInfoForAdd").append(tbodyArray.join(""));
                }
            });

            $("#generateAddrBook").submit(function () {
                var options = {
                    url: '../addrBook/generateTrainBook.do',
                    dataType: 'json',
                    type: 'post',
                    success: function (data) {
                        var resultInfo = "";
                        resultInfo += data.msg + "<br/>";
                        jAlert(resultInfo, alertInfoOption.alertTitle, function () {
                            if (data.status) {
                                window.parent.closePop();
                            }
                        });
                    },
                    error: function (data) {
                        jAlert("通讯录创建出现异常,请联系技术支持人员", alertInfoOption.alertTitle);
                    }
                }
                $(this).ajaxSubmit(options);
                return false;
            })
        });

    </script>
</head>
<body>
<form id='generateAddrBook'>
    <table class='table0' width='90%' align='center' cellspacing='0' cellpadding='0'>
        <c:if test="${addrBooks != null }" >
            <tr class='tableTh'>
                <th width='30%' class='ctext'>选择现有通讯录</th>
                <td class='ctext1'>
                    <div style='float:left;'>
                        <select id='addrbook' name='addrbook'>
                            <option value='-1'>------</option>
                            <c:forEach var="addrBook" items="${addrBooks}">
                                <option value='${addrBook.addrbookId}'>${addrBook.addrbookName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </td>
            </tr>
        </c:if>
        <tr class='foraddNewBook'>
            <th class='ctext'>新建通讯录</th>
            <td>
                <div style='float:left;'><input type='text' id='new_addr' name='new_addr'/></div>
            </td>
        </tr>
        <tr class='foraddNewBook'>
            <th class='ctext'>通讯录类型</th>
            <td class='ctext1'>
                部门学员通讯录<input checked='checked' type='radio' id='addrbookRole1' name='addrbookRole' value='0'/>&nbsp;
                部门培训联络员通讯录<input type='radio' id='addrbookRole2' name='addrbookRole' value='1'/>&nbsp;&nbsp;
                部门领导通讯录<input type='radio' id='addrbookRole3' name='addrbookRole' value='2'/>&nbsp;
            </td>
        </tr>
        <tr class='foraddNewBook'>
            <th class='ctext'>公开范围</th>
            <td class='ctext1'>
                <c:if test="${AddrBook == null }" >
                    不公开<input type='radio' checked='checked' name='openScope' value='2201'/>&nbsp;
                    公开<input type='radio' name='openScope' value='2204'/>&nbsp;
                </c:if>
            </td>
        </tr>
        <tr>
            <th class="ctext">创建时间</th>
            <td class="ctext1">
                ${addrbookDate}<input type="hidden" id="addrbookDate" value="${addrbookDate}"/>
            </td>
        </tr>
    </table>
    <br/>
    <div style='float:right;font-weight:bold;color:#777;font-size:13px;'>
        <a href="javascript:void(0);" onclick='javascript:window.parent.closePop();' id='AddBook' class='btn-blue-l' style='margin-right: 20px;'><span class='btn-blue-r'>取消</span></a>
        <a href="javascript:void(0);" onclick="javascript:$('#generateAddrBook').submit();" class='btn-blue-l' style='margin-right: 20px;'><span class='btn-blue-r'>确定</span></a>
    </div>
    <br/><br/>
    <div style="padding:10px;width:97%;float:left">
        <div class="addrPersonListTitle">&nbsp;&nbsp;添加人员确认</div>
        <div style="float:left;width:100%;margin-top:10px">
            <table class="homecontenttable homezonecontentborder" rules="cols" width="100%" cellspacing="0" cellpadding="0" id="personnelInfoForAdd">
                <tr class='tableTh'>
                    <th width='5%'></th>
                    <th width='15%'>用户姓名</th>
                    <th width='15%'>单位名称</th>
                    <th width='10%'>员工登陆名</th>
                    <th width='15%'>电子邮箱</th>
                    <th width='10%'>电话</th>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>
