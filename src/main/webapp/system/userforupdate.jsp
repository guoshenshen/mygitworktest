<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/xx1.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="培训;在线培训;网络培训" name="keywords" />
<title></title>
<link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css" />
<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/css/bootstrap/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css" />
<link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
<link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
</head>
<body class="admin iframeAdmin" id="distributeRole">
<div class="mainContent">
	<form id="userForm"  method="post" class="form-horizontal" role="form" style="margin: 0 auto;width:720px;box-shadow: #c7c7c7 0px 0px 2px;padding: 20px;border: 1px solid #e5e5e5;" >
		<div class="col-sm-12">
			<div class="form-group col-sm-6">
				<div class="form-group">
				    <label class="col-sm-6 control-label"><span style="color:red">*  </span>姓名</label>
				    <div class="col-sm-6">
				      <input type="text" class="form-control" id="operatorName" name="operatorName" value='${user.operatorName}' placeholder="用户名称" />
				    </div>
			    </div>
			    <div class="form-group">
				    <label class="col-sm-6 control-label"><span style="color:red">*  </span>账号</label>
				    <div class="col-sm-6">
				      <input type="text" class="form-control" id="userId" name="userId" value='${user.userId}' placeholder="登录账号" />
				    </div>
			    </div>
			 </div>
			 <div class="form-group col-sm-5" style="text-align:right">
				<img id="headPic" style='width:90px;height:90px;'   />
				<input type="hidden" value='${user.address}' name='address' />
			 </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-3 control-label">单位（部门）</label>
		    <div class="col-sm-9 effectArea clearBefore" id="orgId">
		    	<button class="btn btn-info singleOrgSelect" type="button">单位选择</button>
			    <button class="btn btn-primary showOrgsPreview" type="button">单位查看</button>
			    <div class="hiddenArea">
					<c:if test="${not empty user.orgId}">
						<input type="hidden" name="orgId"  value="${user.orgId}" />
					</c:if>
			    </div>
				<div class="detailInfoForShow notShow"><table></table></div>
		    </div>
		 </div>
		  <div class="form-group">
		    <label class="col-sm-3 control-label">用户编号</label>
		    <div class="col-sm-3">
		     	<input type="text" name="empCode" id="empCode" class="form-control" value="${user.empCode}"  placeholder="用户编号" />
		    </div>
		    <label class="col-sm-2 control-label">邮箱</label>
		    <div class="col-sm-3">
		      <input type="text" name="oemail" id="email" value="${user.oemail}" class="form-control" placeholder="邮箱（办公邮箱最佳）" />
		    </div> 
		 </div>
		 <div class="form-group">
		 	<label class="col-sm-3 control-label">是否有学时要求</label>
		   	<div class="col-sm-3">
			<c:if test="${empty user.isCtnEduRequire}">
		    	<label class="radio-inline">
		   			<input type="radio" name="isCtnEduRequire" value="1" checked="checked"/>是
		   		</label>
		   		<label class="radio-inline">
		   			<input type="radio" name="isCtnEduRequire" value="0"/>  否
		    	</label>
			</c:if>
			<c:if test="${not empty user.isCtnEduRequire}">
				<label class="radio-inline">
		        <input type="radio" name="isCtnEduRequire" value="1" <c:if test="${user.isCtnEduRequire!=0}">checked="checked"</c:if>/>是
		      </label>
		      <label class="radio-inline">
		        <input type="radio" name="isCtnEduRequire" value="0" <c:if test="${user.isCtnEduRequire==0}">checked="checked"</c:if>/>否
        	  </label>
			</c:if>
		    </div>
		 
		 
		    <label class="col-sm-2 control-label">性别</label>
		    <div class="col-sm-3">
				<c:if test="${empty user.gender}">
					<label class="radio-inline">
						<input type="radio" name="gender" value="1"/>男
					</label>
					<label class="radio-inline">
						<input type="radio" name="gender" value="2"/>女
					</label>
				</c:if>
				<c:if test="${not empty user.gender}">
					<label class="radio-inline">
						<input type="radio" name="gender" value="1" <c:if test="${user.gender!=2}">checked="checked"</c:if>/>男
					</label>
					<label class="radio-inline">
						<input type="radio" name="gender" value="2" <c:if test="${user.gender==2}">checked="checked"</c:if>/>女
					</label>
				</c:if>
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-3 control-label">座机</label>
		    <div class="col-sm-3">
		      <input type=text class="form-control" name="otel1" value="${user.otel1}" placeholder="座机" />
		    </div>
		    
		    <label class="col-sm-2 control-label">手机</label>
		    <div class="col-sm-3">
		      <input type=text class="form-control" name="mobileNo" value="${user.mobileNo}" placeholder="手机" />
		    </div>
		 </div>
		 
		 <div class="form-group">
		    <label class="col-sm-3 control-label">出生日期</label>
		    <div class="col-sm-3" >
				<input type="text" style="height: 34px;" class="Wdate form-control" id="birthDate" name="birthDate" value='${user.birthDate}' />
		    </div>
		    <label class="col-sm-2 control-label">入职时间</label>
		    <div class="col-sm-3">
		      <input type="text" style="height: 34px;"  class="Wdate form-control" id="regDate" name="regDate"  value='${user.regDate}' />
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-3 control-label">岗位名称</label>
		    <div class="col-sm-3">
		      <input type="text"  class="form-control" name="position" value='${user.position}'  />
		    </div>
		    <label class="col-sm-2 control-label">学历</label>
		    <div class="col-sm-3">
		      <input type="text"  class="form-control" name="degree" value='${user.degree}'  />
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-3 control-label">职务</label>
		    <div class="col-sm-3">
		     <input type="text"  class="form-control" name="job" value='${user.job}'  />
		    </div>
		    <label class="col-sm-2 control-label">职称</label>
		    <div class="col-sm-3">
		     <input type="text"  class="form-control" name="title" value='${user.title}'  />
		    </div>
		 </div>
		 <div class="form-group">
		    <label class="col-sm-3 control-label">账户创建时间</label>
		    <div class="col-sm-3">
		      <input type="text"  class="form-control"  style="font-size:10px;" value="${user.createTime}"   readonly='true' />
		    </div>
		    <label class="col-sm-2 control-label">最后修改时间</label>
		    <div class="col-sm-3">
		       <input type="text"  class="form-control"  style="font-size:10px;" value='${user.lastModifyTime}' readonly='true' />
		    </div>
		    <logic:notEmpty name="user" property="operatorId"><input type="hidden" name = "operatorId" value = '${user.operatorId}' /></logic:notEmpty>
		 </div> 
		 <div class="form-group" style="text-align: center;">
		  	<button class="goback btn btn-info" type="button">返回</button>
	        <button class="submit btn btn-primary" type="submit">完成</button>
		 </div>
	</form>
</div>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/UI/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="/js/UI/jquery.ztree.exhide.min.js"></script>
<script type="text/javascript" src="/js/public/orgSelect.js"></script>
<script type="text/javascript" src="/js/public/orgAndUsersSelect.js"></script>		
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript">
	$(function(){

		$("#headPic").error(function(){
			var $gender=$("input[name=gender]:checked");
			var gender="1";
			var defaultPic="/image/headPic/male1.jpg";
			if($gender.length>0){
				gender=$gender.val();
			}
			else{
				
			}
			if(gender=="2"){
				defaultPic="/image/headPic/female1.jpg";
			}
			menuimgError(this,defaultPic);
		}).attr("src",$("input[name=address]").val());

		$("#userForm").on("click",".goback",function(){
			history.back();
		})

		if(window.parent&&typeof window.parent.iframeResize=="function"){
			window.parent.iframeResize();
		}

		$("#orgId").loadDefaultOrgList();

		$(".Wdate").click(function(){
			WdatePicker({readOnly:true});
		})

		var canclick=true;
		$("#userForm")
		.on("click",".goback",function(){
			window.location.href='eosorgTEmployeeListAction.do';
		})
		.on("click",".submit",function(){	
			$("#userForm").submit();
		})
		.submit(function(){
			if(canclick){
				canclick=false;
				$(this).ajaxSubmit({
					traditional:true,
					url:"/eosorgTEmployee/updateUser.do",
					type:"POST",
					dataType:"json",
					success:function(data){
						canclick=true;
						var content="";
						if(data.status){
							content="信息修改成功!";
						}
						else{
							if($.trim(data.cause)!=""){
								content=$.trim(data.cause);
							}
							else{
								cotent="修改信息失败!";
							}
						}
						$.Ntip({
							'content':content,
							'onClose':function(){
								if(data.status){
									$("#distributeRole .goback").click();
								}		
							}
						})
					},
					error:function(){
						canclick=true;
					}
				});	
			}
			return false;
		})
	})
	</script>
</body>
</html>

