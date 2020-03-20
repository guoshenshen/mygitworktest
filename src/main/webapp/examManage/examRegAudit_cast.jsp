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
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="contentbody">
                <div id="content_02">
                    <form id="form1" name="form1" method="post">
                        <input type="hidden" name="eid" value="${eid}"/>
                        <table class="table table-striped table-bordered batchOperation">
                            <tr class="tableTh">
                                <th>
                                    <input type="checkbox" id="selectAll" name="selectAll" onClick="_setSelected(this,'selectbox','form1')"/>
                                </th>
                                <th> 员工编号</th>
                                <th> 员工姓名</th>
                                <th> 审批状态</th>
                                <th> 申请理由</th>
                            </tr>
                            <c:if test="${examRegList != null}">
                                <c:forEach var="examUserReg" items="${examRegList}" varStatus="status">
                                    <tr>
                                        <td align="center">
                                            <input type="checkbox" name="selectbox" value="${examUserReg.id }"/>
                                        </td>
                                        <td align="center">
                                            ${examUserReg.employeeID }
                                        </td>
                                        <td align="center">
                                            ${examUserReg.employeeName }
                                        </td>
                                        <td align="center">
                                            <c:if test="${examUserReg.examStatus != null}">
                                                <c:if test="${examUserReg.examStatus == 1051}">
                                                    <a href="examReg.do?method=auditExamReg&examRegId=${examUserReg.id }&auditResult=1">通过</a> |
                                                    <a href="examReg.do?method=auditExamReg&examRegId=${examUserReg.id }&auditResult=0">不通过</a>
                                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                                </c:if>
                                                <c:if test="${examUserReg.examStatus == 1052}">
                                                    <font color="red">审批通过</font>&nbsp;&nbsp;&nbsp;&nbsp;
                                                </c:if>
                                                <c:if test="${examUserReg.examStatus == 1053}">
                                                    <font color="red">审批未通过</font>&nbsp;&nbsp;&nbsp;&nbsp;
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <td align="center" style="max-width: 200px;">
                                            ${examUserReg.examApplyReason }
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${examRegList == null or examRegList == '' or examRegList == []}">
                                <tr>
                                    <td colspan="5" align="left">暂无数据</td>
                                </tr>
                            </c:if>
                        </table>
                    </form>
                </div>
                <div id="content_03">
                    <div class="gl_03">
                        <a href="../examManage/toExamManageHome.do" class="btn-blue-l">
                            <span class="btn-blue-r">返&nbsp;&nbsp;回</span>
                        </a>
                        <a href="javascript:passAuditInBatch();" class="btn-orange-l">
                            <span class="btn-orange-r">批量审核通过</span>
                        </a>
                    </div>
                    <br/>
                    <div class="clr"></div>
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

<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>

<script language='javascript'>
    function viewApplyReason(id) {
        var url = "<%=basePath%>examReg.do?method=toSeeApplyReason&examRegId=" + id;
        window.open(url, "", "width=300,height=160,toolbar=no,scrollbars=yes,menubar=no,screenX=100,screenY=100");
    }
    function passAuditInBatch() {
        var tagcheck = 0;
        var formElement = document.getElementById("form1");
        var eles = formElement.elements;

        for (var j = 0; j < eles.length; j++) {
            if (eles[j].type == "checkbox" && eles[j].name == "selectbox" && eles[j].checked == true) {
                tagcheck = 1;
                break;
            }
        }
        if (tagcheck == 1) {
            document.form1.action = "<%=basePath%>examReg.do?method=auditExamRegByIds";
            document.getElementById('form1').submit();
        } else {
            jAlert("请选择审核项目！", '填写提示');
        }
    }
</script>
<script type="text/javascript" src="/js/common/select.js"></script>
<c:if test="${alertString != null}">
    <script type="text/javascript">
        jAlert("${alertString}", '');
    </script>
</c:if>
<link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>
<link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
<link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>

</body>
</html>
