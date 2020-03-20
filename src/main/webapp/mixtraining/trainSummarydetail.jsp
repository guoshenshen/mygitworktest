<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <title>培训项目基本信息管理</title>

    <link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>

    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/common/home.js"></script>
    <script type="text/javascript" src="/js/common/tools.js"></script>
</head>
<body style="background-color:white">
<div id="mainbody" class="valuediv" style="width:100%" align='center'>
    <div id="content_02">
        <table class="homecontenttable">
            <tr>
                <th width='20%'>总结名称</th>
                <td width='80%' align="left">&nbsp;${trainSummaryForm.summaryName}</td>
            </tr>
            <tr>
                <th>用户姓名</th>
                <td align="left">&nbsp;${trainSummaryForm.operatorName}</td>
            </tr>
            <tr>
                <th>提交日期</th>
                <td align="left">
                    &nbsp;<fmt:formatDate value="${trainSummaryForm.submitDate }" pattern="yyyy-MM-dd HH:mm"/>
                </td>
            </tr>
            <tr>
                <th>总结内容</th>
                <td colspan="3" align="left">&nbsp;${trainSummaryForm.conclusion}
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
 