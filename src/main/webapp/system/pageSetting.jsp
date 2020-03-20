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
<body class="admin">
<div class="adminFrame">
	<div class="topbody"></div>
	<div class="mainbody" >
		<div id="trace" class="content"></div>
		<div class="mainContent content">
			<form  id="formForPageSetting">
				<div class="minContent">
					<p class="layer">
						<label style="font-weight: bold">每页显示的记录条数：</label>
						<select name="pageSize" style="margin-left:20px;width:100px">
						</select>
						<input id="flag" type="hidden" name="flag" value="${flag}"/>
						<input type="hidden" name="currentPageSize" value="${pageSize}" />
					</p>
					<p class="layer">
						<button class='btn btn-confirm' id="savePageSetting" type="button">保存</button>
					</p>
				</div>
			</form>
		</div>
	</div>
</div>
<div class="bottombody"></div>
<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/nav/amenu.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/aTool.js"></script>
<script type="text/javascript">
    $(function(){
        $("#savePageSetting").click(function(){
            $("#formForPageSetting").submit();
        })

		var currentPageSize=$("input[name=currentPageSize]").val();
        if($.trim(currentPageSize)==""){
            currentPageSize=25;
		}
		var defaultPageSetting=[5,10,25,50,100,500];
        var optionArray=new Array();
		for(var index=0;index<defaultPageSetting.length;index++){
			if(currentPageSize==defaultPageSetting[index]){
                optionArray.push("<option selected value='"+defaultPageSetting[index]+"'>");
			}
			else{
                optionArray.push("<option value='"+defaultPageSetting[index]+"'>");
			}
            optionArray.push(defaultPageSetting[index]);
			optionArray.push("</option>");
		}
		$("select[name=pageSize]").html(optionArray.join(""));


		$("#formForPageSetting").submit(function(){
            $(this).ajaxSubmit({
                traditional:true,
                url:"/system/pageSettingSave.do",
                type:"POST",
                dataType:"json",
                success:function(data){
                    if(data.status){
                        $.Ntip({
                            'content':"页面默认设置成功！"
                        })
                    }
                    else{
                        $.Ntip({
                            'content':"页面默认设置失败"
                        })
                    }
                },
                error:function(){
                    $.Ntip({
                        'content':"页面默认设置失败"
                    })
                }
            });
            return false;
		});

    })
</script>
</body>
</html>

