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
                <!--首页 内容 -->
                <div class="gl_06" style="border:0">
                    <div id="gl_00">
                        <div class="cj_02">
                            <a href="../examResultManage/examScores.do?eid=${eid }&type=1" id="current" class="cj_l">查看成绩</a>
                        </div>
                        <div class="cj_01">
                            <a href="../examResultManage/importExamScore.do?eid=${eid }" class="cj_l">线下成绩录入</a>
                        </div>
                        <div class="cj_02">
                            <a href="../examScoreFile/exportExcel.do?eid=${eid }" class="cj_l">导出成绩</a>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="clr"></div>
                    <form name="form1" method="post" action="<%=basePath%>examScoreFile.do?method=importExcel" ENCTYPE="multipart/form-data">
                        <table class="table1 table table-striped table-bordered batchOperation" width="80%" cellspacing="0" cellpadding="0" align="center">
                            <tr>
                                <th>导入类型：</th>
                                <td>增加导入</td>
                            </tr>
                            <tr>
                                <th>选择上传EXCEL文件：</th>
                                <td>
                                    <input id="file" type="file" name="excelFile"/><font color="red">*</font>
                                </td>
                            </tr>
                            <tr>
                                <th>查看导入示例：</th>
                                <td>
                                    <a href=javascript:openwindowtocenter('modelExcel/outputExcel.jsp?file=score.xls','<%=Constants.systemName%>',600,600)>
                                        <img src="/image/choice.jpg" border="0"/>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center" style="padding-top: 30px;">
                                    <input type="hidden" name="eid" value="${eid }"/>
                                    <div class="btnContainer">
                                        <div class="clearfix">
                                            <a href="../examManage/toExamManageHome.do?pageNo=1" class="btn-blue-l">
                                                <span class="btn-blue-r">返&nbsp;&nbsp;回</span>
                                            </a>
                                            <a href="javascript:submitImport();" class="btn-orange-l">
                                                <span class="btn-orange-r">上&nbsp;&nbsp;传</span>
                                            </a>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <div class="clr"></div>
                    <div class="nr_right_b5">
                        <c:if test="${errorMsgList != null}">
                            <c:forEach var="errMsg" items="${errorMsgList}" varStatus="status">
                                <div style="margin-left:30px;margin-top:10px;background-color:yellow;overflow:auto;text-align:center">
                                    <font color="red"><bean:write name="errMsg"/></font>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${errorMsgList == null or errorMsgList == ''}">
                            <c:if test="${importFlagSuccess != null}">
                                <div style="margin-left:30px;margin-top:10px;background-color:yellow;overflow:auto;text-align:center">
                                    <font color="red"><bean:write name="importFlagSuccess"/></font>
                                </div>
                            </c:if>
                        </c:if>
                    </div>
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
<link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
<link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
<link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/common/home.js"></script>
<SCRIPT language="JavaScript" src="/js/widget/window.js"></SCRIPT>
<script type="text/javascript">
    function submitImport() {
        var file = document.getElementById('file').value;
        if (file == "" || file == null) {
            jAlert("请选择要上传的文件!", '提示');
        } else if (file != "" && file != null) {
            var pos = file.lastIndexOf(".");
            var lastname = file.substring(pos + 1, file.length);
            if (lastname == "xls" || lastname == "xlsx") {
                document.form1.submit();
            } else {
                jAlert("只能上传excel类型的文件，请重新上传!", '提示');
            }
        }
    }
</script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
    th {
        text-align: left;
    }
</style>
</body>
</html>
