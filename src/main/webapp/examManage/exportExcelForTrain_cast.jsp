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
            <!-- your content is here -->
            <div id="funcCon" class="trainApp"></div>
            <div class="homezoneall">
                <div class="homezonecontent">
                    <!--首页 内容 -->
                    <div class="gl_06" style="border:0">
                        <div id="gl_00">
                            <div class="cj_02">
                                <a href="../examResultManage/examScores.do?examForTrainFlag=1&eid=${eid }&type=1" id="current" class="cj_l">查看成绩</a>
                            </div>
                            <div class="cj_02">
                                <a href="../examResultManage/importExamScore.do?examForTrainFlag=1&eid=${eid }" class="cj_l">线下成绩录入</a>
                            </div>
                            <div class="cj_01">
                                <a href="../examScoreFile/exportExcel.do?examForTrainFlag=1&eid=${eid }" class="cj_l">导出成绩</a>
                            </div>
                        </div>
                        <br/>
                        <br/>
                        <div class="clr"></div>
                        <div align="center">
                            导出成绩：
                            <a class="gl_td_l" href="${zipFilePath}">
                                <img src="/image/excel.jpg" border="0"/>点击下载
                            </a>
                        </div>
                        <div class="clr"></div>
                        <div class="nr_right_b5"></div>
                        <div class="clr"></div>
                        <br/>
                        <div class="btnContaexamForTrainFlag" class=" clearfix" style="width:8%"></div>
                        <a href="javascript:void(0);" onclick="window.location.href='../examManage/toExamManageHome.do?examForTrainFlag=1&pageNo=1'" class="btn-blue-l">
                            <span class="btn-blue-r">返&nbsp;&nbsp;回</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="clr"></div>
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
<link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
<link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/common/home.js"></script>
<!-- InstanceBeginEditable name="head" -->
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/widget/window.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
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
</body>
</html>
