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
	<link href="../css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css" />
	<link href="../css/jquery-UI/remodal.css" rel="stylesheet" type="text/css" />
	<link href="../css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
	<link href="../css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link href="../css/jquery-UI/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
</head>
<body class="admin" id="editRole">
<div class="adminFrame">
	<div class="topbody"></div>
	<div class="mainbody" >
		<div id="trace" class="content"></div>
		<div class="mainContent content">
			<div id="contentbody">
				<!-- InstanceBeginEditable name="main" -->

				<form id="roleForm"   class="form-horizontal" role="form" style="width: 800px;margin: 0 auto;">

					<div class="form-group">
						<c:if test="${not empty role.ID}"><input type="hidden" name="ID" value="${role.ID}" /></c:if>
						<label class="col-sm-4 control-label">角色编号:</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" maxlength="20" style="width:350px;"
								   id="roleCode"
								   name="roleCode" value='${role.roleCode}'>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label">角色名称:</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" maxlength="20" style="width:350px;"
								   id="roleName" name="roleName" value='${role.roleName}'>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-4 control-label">是否虚拟机构管理员角色:</label>
						<div class="col-sm-6">
							<c:if test="${role.isVirRole==1}">
								<input type="text" class="form-control" maxlength="20" style="width:350px;" disabled
									   value='是'><input type="hidden" name="isVirRole" value='1' />
							</c:if>
							<c:if test="${role.isVirRole==0}">
								<input type="text" class="form-control" maxlength="20" style="width:350px;" disabled
									   value='否'><input type="hidden" name="isVirRole" value='0' />
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">管理范围:</label>
						<div class="col-sm-6 effectArea"  id="orgId">
							<button class="btn btn-info singleOrgSelect" type="button">单位选择</button>
							<button class="btn btn-primary showOrgsPreview" type="button">单位查看</button>
							<div class="hiddenArea">
								<c:if test="${not empty role.orgId}">
									<input type="hidden" name="orgId"  value="${role.orgId}" />
								</c:if>
							</div>
							<div class="detailInfoForShow notShow"><table></table></div>
							<font color=red>&nbsp;&nbsp;(若为空，则管理范围为管理员所属单位)</font>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">备注:</label>
						<div class="col-sm-6">
							<textarea name="comment" class="form-control" rows="3" onKeyDown="gbcount(this,400);" onKeyUp="gbcount(this,400);">${role.comment}</textarea>
						</div>
					</div>
					<div class="form-group" style="text-align: center;">
						<button class="goback btn btn-info" type="button">返回</button>
						<button class="submit btn btn-primary" type="button">完成</button>
					</div>
				</form>

			</div>
		</div>
	</div>
</div>
<div class="bottombody"></div>
<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/nav/amenu.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js"></script>
<script type="text/javascript" src="../js/UI/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="../js/UI/jquery.ztree.exhide.min.js"></script>
<script type="text/javascript" src="../js/public/orgSelect.js"></script>
<script type="text/javascript" src="../js/UI/remodal.min.js"></script>
<script type="text/javascript" src="../js/public/orgAndUsersSelect.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/aTool.js"></script>
<script type="text/javascript" src="../js/systemManage/systemManage.js"></script>
</body>
</html>
