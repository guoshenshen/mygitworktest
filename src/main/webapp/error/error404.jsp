<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userRolesInString = "";
	try{
		EosOperator user = (EosOperator)session.getAttribute(Constants.USERINFO_KEY);
	}catch(Exception ex){
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
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="培训;在线培训;网络培训" name="keywords" />
<title></title>
<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" /> 
<link href="../css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-UI/remodal.css" rel="stylesheet" type="text/css" />
<link href="../css/login/login.css" rel="stylesheet" type="text/css"/>
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="../css/skinCss/studentStyle.css" rel="stylesheet" type="text/css" />
<link href="../css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.error-center {
    float: left;
    position: relative;
    width: 100%;
    height: 622px;
    margin-top: 30px;
    margin-bottom: 30px;
    background: #EEEEEE;
}

.error-center .error-center-backImg {
    width: 100%;
    height: 100%;
    position: absolute;
}
</style>
</head>
<body class="student">
<div class="mainContent">
	<div class="error-center">
		<img class="error-center-backImg" src="./image/error.png" />
		<div class="error-prompt">
			<p>对不起，您访问的页面个不见了!</p>
		<p>您可以选择......</p>
		</div>
		<button class="error-return">返回首页</button>
	</div>
</div>
<div id="foot"></div>
<div id="simpleNavBar" style="position:fixed;top:0px;">
</div>

<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js" ></script>
<script type="text/javascript" src="../js/UI/remodal.min.js"></script>
<script type="text/javascript" src="../js/UI/jquery.infobox.js"></script>
<script type="text/javascript">
var loginData={
	nextActionUrl:"onlineStudy.do?method=intoFrame"
}
</script>
<script type="text/javascript" src="../js/nav/umenu.js"></script>
<script type="text/javascript" src="../js/UI/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="../js/UI/basicWidget.js"></script>
<script type="text/javascript" src="../js/UI/jquery.infobox.js"></script>
<script type="text/javascript" src="../js/basicUserFunc.js"></script>
</body>
</html>
