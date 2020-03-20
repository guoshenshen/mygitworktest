<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants"%>
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
	<link href="../css/skinCss/Normalize.css" rel="stylesheet" type="text/css" />
	<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="../css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
	<link href="../css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
</head>
<body class="admin" id="roleFrame">
<div class="adminFrame">
	<div class="topbody"></div>
	<div class="mainbody" >
		<div id="trace" class="content"></div>
		<div class="mainContent content">
			<div class="condition">
				<div class="form-inline" style="text-align: right;">
					<div class="form-group">
						<input class="form-control" type="text" name="roleName" id="roleName"  placeholder="角色名称…"	value="${roleName}" />
						<button id="searchRole" class="btn btn-primary">查询</button>
					</div>
				</div>
			</div>
			<table class="table table-striped table-bordered batchOperation" id="roleList" rules="cols"  width="100%" cellspacing="0" cellpadding="0" >
				<tr>
					<th><input type="checkbox" id="selectAll" name="selectAll" /></th>
					<th>角色名称</th>
					<th>管理范围</th>
					<th>创建时间</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</table>
			<div class="condition" style="text-align:left;">
				<button class="addRole btn btn-info">新建角色</button>
				<button class="deleteRole btn btn-danger">批量删除</button>
				<ul class="pagination-admin" style="float:right"></ul>
			</div>
		</div>
	</div>
</div>
<div class="bottombody"></div>
<div class="notShow">
	<input type="hidden" value="${login_user.operatorId}" id="currentOperatorId" />
</div>
<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/nav/amenu.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js"></script>
<script type="text/javascript" src="../js/aTool.js"></script>
<script type="text/javascript" src="../js/systemManage/systemManage.js"></script>
</body>
</html>
