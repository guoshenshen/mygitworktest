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
<link href="../css/skinCss/Normalize.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-UI/remodal.css" rel="stylesheet" type="text/css" />
<link href="../css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
<link href="../css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery-UI/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
</head>
<body class="admin">
<div class="adminFrame">
<div class="topbody"></div>
<div class="mainbody" >
  <div id="trace" class="content"></div>
  <div class="mainContent content">
  	<div id="loadingInfo">
		<input type="hidden" name="orgId" value="${orgId}" />
		<input type="hidden" name="targetUrl" value="${targetUrl}" />
	</div>
	<div class="guidePanel treeContainer" >
		<div id="orgTree">
		</div>
	</div>	
	<div class="mainPanel">
		<iframe src="" id="mainInfoFrame" class="mainInfoFrame" scrolling="no"></iframe>
	</div>
	<div style="clear:both"></div>
  </div>
</div>
</div>
<div class="bottombody"></div>


<div class="remodal normal noBorder normalModal"  data-remodal-id="normalModal" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
  <div class="box">
	  	<button data-remodal-action="close" class="remodal-close" ></button>
  </div>
  <div class="wrapper common" >
  </div>
</div>

<div class="treeFrameWindow"  data-remodal-id="treeFrameWindow" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc" style="min-height:380px;">
<button data-remodal-action="close" class="remodal-close"></button>
<div class="box"></div>
<div class="wrapper full" style="height: 500px;">
<iframe src="" style="width: 100%;height:100%" id="treeWindowIframe" >
</iframe>
</div>
</div>

<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/nav/amenu.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js"></script>
<script type="text/javascript" src="../js/UI/remodal.min.js"></script>
<script type="text/javascript" src="../js/UI/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="../js/UI/jquery.ztree.exhide.min.js"></script>
<script type="text/javascript" src="../js/public/orgSelect.js"></script>
<script type="text/javascript" src="/js/public/orgAndUsersSelect.js"></script>
<script type="text/javascript" src="../js/aTool.js"></script>
<script type="text/javascript">


function iframeResize(){
	iFrameHeight("mainInfoFrame",0);
}


function loadRootInfo(){
	var rootOrgId=$("#loadingInfo input[name=orgId]").val();
	var targetUrl=$("#loadingInfo input[name=targetUrl]").val();
	$("#orgTree").loadOrgTreeWithSearch({"orgId":rootOrgId},{
 		check:{enable:false},
 		callback:{
			onClick:function(event, treeId, treeNode){
				$("#mainInfoFrame").attr("src",targetUrl+"orgId="+treeNode.orgId);
			}
		}	
 	})	
}


$(function(){
	var $treeFrameModal=$('[data-remodal-id=treeFrameWindow]').remodal($.defaultRemodalOption());
 	
 	$.extend({
		settingsForTreeFrameWindow:function(params){
			if(typeof params.opening=="function")$(document).on("opening",".treeFrameWindow",params.opening);
			if(typeof params.closing=="function")$(document).on("closing",".treeFrameWindow",params.closing);
			if(typeof params.opened=="function")$(document).on("opened",".treeFrameWindow",params.opened);
			if(typeof params.closed=="function")$(document).on("closed",".treeFrameWindow",params.closed);
		},

		openTreeFrameWindow:function(){
			$treeFrameModal.open();
		}
		
 	})

	loadRootInfo();
	
	

})
</script>
</body>
</html>
