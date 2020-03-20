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
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <!-- InstanceBeginEditable name="doctitle" -->
    <title><%=Constants.systemName%>
    </title>
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
    <script type="text/javascript" src="/js/public/orgAndUsersSelect.js"></script>

    <script type="text/javascript">
        $(function () {
            if ($("#createTime #addrbookDate").length == 0) {
                var currentDateStr = new Date().format("yyyy-M-d");
                $("#createTime").append("<input type='hidden' name='addrbookDate'></input>" + currentDateStr);
                $("#createTime input[name=addrbookDate]").val(currentDateStr);
            }

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
                            alert("表格信息导出失败");
                        }
                    }
                };
                $("#personnelInfoForAdd").exportTableInCurrentView(options);
            });

            $("#forCreateAddressBook").click(function () {
                var options = {};
                if ($("#generateAddrBook #addrbook").length != 0) {
                    options.addrbook = $("#generateAddrBook #addrbook").val();
                }
                options.new_addr = $("#generateAddrBook #new_addr").val();
                options.trainId = $("#generateAddrBook #trainId").val();
                options.addrbookRole = $("#generateAddrBook input[name=addrbookRole]:checked").val();
                options.openScope = $("#generateAddrBook input[name=openScope]:checked").val();
                updateUsersFromAddressBook(options);
            });

            if (editAddressBookArea != null) {
                editAddressBookArea.alreadyUsers = new Array();
                $("#personnelInfoForAdd .operatorId").each(function (index, such) {
                    editAddressBookArea.alreadyUsers.push(parseInt($(such).val()));
                })
            }
        })
    </script>
</head>
<body class="admin" style="padding-bottom:10px;">
<form id='generateAddrBook'>
    <table class='table table-striped table-bordered addrBookInfoTable' width='90%' align='center' cellspacing='0' cellpadding='0'>
        <tr class='tableTh'>
            <th width='30%' class='ctext'>通讯录名称</th>
            <td class='ctext1'>
                <div style='float:left;'>
                    <c:if test="${AddrBook != null}">
                        <input type="text" id="new_addr" name="new_addr" style='width:300px' value='${AddrBook.addrbookName}'/>
                    </c:if>
                    <c:if test="${AddrBook == null}">
                        <input type="text" id="new_addr" name="new_addr" style='width:300px' value='${train.trainName}通讯录'/>
                    </c:if>
                    <c:if test="${AddrBook != null}">
                        <input type='hidden' id='addrbook' name='addrbook' value="${AddrBook.addrbookId}"></input>
                    </c:if>
                </div>
            </td>
        </tr>

        <tr>
            <th class='ctext'>通讯录类型</th>
            <td class='ctext1'>
                <c:if test="${AddrBook != null }">
                    <input <c:if test="${AddrBook.addrbookRole == 0 }">checked='checked'</c:if> type='radio' name='addrbookRole' value='0'>部门学员通讯录</input>&nbsp;
                    <input <c:if test="${AddrBook.addrbookRole == 1 }">checked='checked'</c:if> type='radio' name='addrbookRole' value='1'>部门培训联络员通讯录</input>&nbsp;
                    <input <c:if test="${AddrBook.addrbookRole == 2 }">checked='checked'</c:if> type='radio' name='addrbookRole' value='2'>部门领导通讯录</input>&nbsp;
                </c:if>
                <c:if test="${AddrBook == null }">
                    <input type='radio' checked='checked' name='addrbookRole' value='0'>部门学员通讯录</input>&nbsp;
                    <input type='radio' name='addrbookRole' value='1'>部门培训联络员通讯录</input>&nbsp;
                    <input type='radio' name='addrbookRole' value='2'>部门领导通讯录</input>&nbsp;
                </c:if>
            </td>
        </tr>
        <tr>
            <th class='ctext'>公开范围</th>
            <td class='ctext1'>
                <c:if test="${AddrBook != null}">
                    <input <c:if test="${AddrBook.openScope == 2201}">checked='checked'</c:if> type='radio' id='addrbookRole' name='openScope' value='2201'>不公开</input>&nbsp;
                    <input <c:if test="${AddrBook.openScope == 2204}">checked='checked'</c:if> type='radio' id='addrbookRole' name='openScope' value='2204'>公开</input>&nbsp;
                </c:if>
                <c:if test="${AddrBook == null}">
                    <input type='radio' checked='checked' name='openScope' value='2201'>不公开</input>&nbsp;
                    <input type='radio' name='openScope' value='2204'>公开</input>&nbsp;
                </c:if>
            </td>
        </tr>
        <tr>
            <th class="ctext">
                <c:if test="${AddrBook != null}">最新修改时间</c:if>
                <c:if test="${AddrBook == null}">创建时间</c:if>
            </th>
            <td class="ctext1" id='createTime'>
                <c:if test="${AddrBook != null}">
                    <input type='hidden' name='addrbookDate' id='addrbookDate' value="<fmt:formatDate value="${AddrBook.addrbookDate}" pattern="yyyy-MM-dd"/>"/>
                    <fmt:formatDate value="${AddrBook.addrbookDate}" pattern="yyyy-MM-dd"/>
                    <%--${AddrBook.addrbookDate}--%>
                </c:if>
            </td>
        </tr>
    </table>
</form>
<div class="condition  senderSelect effectArea " id="selectedUserId">
    <button type="button" class="btn btn-info userAddMenu" id="forAddPersonToAddressBook">人员添加</button>
    <button type="button" class="btn btn-info" id="forCreateAddressBook">完成</button>
    <button type="button" class="btn btn-info" id="forExportAddressBook">导出</button>
    <button type="button" class='btn btn-success' id='downloadButton' style="display:none">下载</button>
    <div class="hiddenArea"></div>
    <div class="detailInfoForShow notShow">
        <table></table>
    </div>
</div>
<div style="padding:10px;">
    <div class="addrPersonListTitle">&nbsp;&nbsp;通讯录人员</div>
    <div style="width:100%;margin-top:10px">
        <table class="table table-striped table-bordered" id="personnelInfoForAdd">
            <tr class='tableTh'>
                <th width='10%'>序号</th>
                <th width='10%'>用户姓名</th>
                <th width='20%'>单位名称</th>
                <th width='20%'>电子邮箱</th>
                <th width='20%'>电话</th>
                <th class="notExport" width='10%'>操作</th>
            </tr>
            <c:if test="${arrangeUserList != null}">
                <c:forEach var="addressBookUser" items="${arrangeUserList}" varStatus="statu">
                    <tr>
                        <td>${statu.count }<input type='hidden' class='index' value='${statu.count}'/></td>
                        <td>
                            ${addressBookUser.operatorName }
                            <input type='hidden' class='operatorId' value='${addressBookUser.operatorId}' id="operatorId_${addressBookUser.operatorId}"/>
                        </td>
                        <td title="${addressBookUser.orgName}">${addressBookUser.orgName}</td>
                        <td title="${addressBookUser.email}">${addressBookUser.email}</td>
                        <td title="${addressBookUser.tel}">${addressBookUser.tel}</td>
                        <td class="notExport">
                            <a href="javascript:void(0);" onclick="javascript:removeUsersFromAddressBook('${addressBookUser.operatorId}')">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
</div>
<script type="text/javascript">
    function resetCurrentBase() {
        return window.parent;
    }
</script>

</body>
</html>