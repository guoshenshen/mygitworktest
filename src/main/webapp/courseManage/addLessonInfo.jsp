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
	<link id="styleId" href="<%=Constants.SKINDIR%>/css/style.css" rel="stylesheet" type="text/css" />
	<link id="style_td_Id"   href="../css/skinCss/style_td.css" rel="stylesheet" type="text/css" />
	<link id="style_gl_Id"  href="../css/skinCss/style_gl.css" rel="stylesheet" type="text/css" />
	<link href="../css/skinCss/Normalize.css" rel="stylesheet" type="text/css" />
	<link href="../css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
	<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="../css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />

	<style>
		.admin .note-editor .btn{
			letter-spacing:0px;
			text-indent:0px;
			padding:3px 5px;
		}
		.note-insert>.btn:first-child,.note-view , .btn-group>.btn:last-child:not(:first-child),
			.btn-group>.dropdown-toggle:not(:first-child) , .note-btn-group btn-group note-view>.btn:first-child , .note-icon-caret{
			display:none;
		}


	</style>
</head>

<body class="admin">
	<div class="adminFrame">
		<div class="topbody"></div>
		<div class="mainbody">
			<div id="trace" class="content"></div>
			<div class="mainContent content" id="courseEditor">
				<br />
				<div class="gl_31_1">
					<span class="gl_11_yes">1.基本信息</span><span class="gl_11_no">2.课件上传</span><span
						class="gl_11_no">3.相关资料上传</span>
				</div>

				<div id="content_02">
					<form id="form1" action="../addLessonInfo/insertCourse.do"  class="form-horizontal report"   role="form" style="width: 1054px;margin: 0 auto;" method="post">
						<input type="hidden" id="courseUploadPlatform" value="${courseUploadPlatform}" />

						<div class="form-group">
							<label class="col-sm-3 control-label">课程名称：</label>
							<div class="col-sm-7" >
								<input type="text" id="courseName" name="courseName" value='' class="form-control"  />
							</div>
							<font color=red>&nbsp;*</font>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">创建日期：</label>
							<div class="col-sm-2"  >
								<input name="createDate" id="createDate" class="Wdate"  style="width:180px; height:35px;"
									readonly="readonly" type="text" value='${createDate}' /> 
							</div>
							<font color=red>*</font>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">创建人：</label>
							<div class="col-sm-7">
								<input type="text" id="maker" name="maker" value='${maker }' class="form-control"
									size="20" maxlength="40"/>
							</div>
							<font color=red>&nbsp;*</font>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">主讲人：</label>
							<div class="col-sm-3">
							<c:if test="${not empty teacher }">
								<input type="text"  id="creator" name="creator"  class="form-control" value="${teacher.teacherName}" />
								<input type="text" id = "teacherId" name = "teacherId" value = "0" style="display: none;"/>
							</c:if>
							<c:if test="${ empty teacher }">
								<input type="text" id="creator" name="creator" class="form-control"
									value='' size="20" maxlength="200" />
									<input type="text" id = "teacherId" name = "teacherId" value = "1" style="display: none;"/>
							</c:if>
							</div>
							<div class="col-sm-3"style="width:12.8%">
								<button class="btn btn-info" type="button" id="searchTeacher">选择</button>
							</div>
							<font color=red>&nbsp;*</font>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">是否按章节添加：</label>
							<div class="col-sm-7">
								<input type="radio"  name="isCoursePackage"  value="1" />是
								<input type="radio" name="isCoursePackage" value="0" checked />否 <font color=red>*</font>
							</div>
						</div>
						<div class="form-group" id="addTeacer">
							<label class="col-sm-3 control-label">是否纳入教师库：</label>
							<div class="col-sm-7">
								<input type="radio"  name="isTeacher" value="1" checked />是
								<input type="radio" name="isTeacher" value="0" />否<font color=red>&nbsp;*</font>
							</div>
						</div>
						<div class="form-group" id= "courseDescription">
							<label class="col-sm-3 control-label">是否添加课程简介：</label>
							<div class="col-sm-7">
								<input type="radio"  name="courseDescription" value="1"  />是 <input
									type="radio" name="courseDescription" value="0" checked/>否<font color=red>&nbsp;*</font>
							</div>
						</div>
						
						
						<div id="classHourDiv" class="form-group">
							<label class="col-sm-3 control-label">学时：</label>
							<div class="col-sm-3">
								<input type="text" id="classHour" name="classHour" class="form-control"
									value='' maxlength="3"/>
							</div>
							<font color=red>&nbsp;*</font>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" >课件类别：</label>
							<div class="col-sm-3" >
								<select property="kindId" name="kindId" id="kindId"  class="form-control"  style="width:180px;float:left;">
									<option value="0" >请选择课件类型</option>
									<c:if test="${ not empty  kinds}">
										<c:forEach var="kind" items="${kinds}">
											<option
												value="${kind.key}"
												<c:if  test='${ kind.key == course.kindId}'>selected</c:if>>
												${kind.value}
											</option>
										</c:forEach>
									</c:if>
								</select>
								<font color=red>&nbsp;*</font>
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-sm-3 control-label">科目分类：</label>
							<div class="col-sm-3">
								<select property="category" name="category" id="category" class="form-control"  style="width:180px;float:left;">
									<option value="0" selected="selected">请选择科目分类</option>
									<c:if test="${not empty categoryList}">
										<c:forEach var="categoryId" items="${categoryList}">
											<option value="${categoryId.code}">
												${categoryId.name}</option>
										</c:forEach>
									</c:if>
								</select> <font color=red>&nbsp;*</font>
							</div>


						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label" >资源分类：</label>
							<div class="col-sm-3">
								<select property="classfication" name="classfication"   class="form-control"  style="width:180px;float:left;"	id="classfication">
									<option value="0" selected="selected" >请选择资源分类</option>
									<c:if test="${not empty resourceList}">
										<c:forEach var="resourceId" items="${resourceList}">
											<option value="${resourceId.code}">
													${resourceId.name}</option>
										</c:forEach>
									</c:if>
								</select> <font color=red>&nbsp;*</font>
							</div>
						</div>
		
						<div class="form-group">
							<label class="col-sm-3 control-label">是否公开课：</label>
							<div class="col-sm-7">
								<input type="radio" name="isOpenCourse"  value="1"></input>
								是&nbsp;&nbsp;&nbsp; <input type="radio" name="isOpenCourse"
									value="0" checked></input> 否 <font color=red>&nbsp;*</font>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">是否推荐：</label>
							<div class="col-sm-7">
								<input type="radio"  name="isNoted"  value="1" />是 <input
									 type="radio" name="isNoted" value="0" checked />否 <font
									color=red>*</font>
							</div>
						</div>
						<div class="form-group">
							<c:if test="${courseUploadPlatform == 1} " >
								<label class="col-sm-3 control-label">课程所属单位：</label>
								<div class="col-sm-7">
									<select property="orgId" name="orgId" id="orgId">
										<option value="0" selected="selected">请选择课程所属单位</option>
										<c:if test="${not empty casOrgList}">
											<c:forEach var="casOrgId" items="${casOrgList}">
												<option value="${casOrgId.orgId}">
													${casOrgId.orgName}</option>

											</c:forEach>
										</c:if>
									</select> <font color=red>&nbsp;*</font>
								</div>
							</c:if>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">课程领域：</label>
							<div  class="territoryEditor">
								<div class="col-sm-2 territoryDIV">
									<select class="territory form-control" ></select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">制作单位：</label>
							<div class="col-sm-3">
								<input type="text" id="produceOrgName" name="produceOrgName" class="form-control"
									value='' size="30" maxlength="500"></input>
							</div>
							<label class="col-sm-2 control-label"style="width:9.6%">资助单位：</label>
							<div class="col-sm-3" >
								<input type="text" id="fundingOrgName" name="fundingOrgName" class="form-control"
									value='' size="30" maxlength="500"></input>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-3 control-label">关键词：</label>
							<div class="col-sm-7" style="width:59.333333%">
								<input type="text" name="keyWords" id="keyWords" size="60" class="form-control"
									value='' /> &nbsp;&nbsp;(关键词之间用 ; 隔开)
							</div>
							 <font color=red>&nbsp;*</font>
						</div>

						<div class="form-group">
								<label class="col-sm-3 control-label">主要内容：</label>

							<div class="col-sm-7">

							<textarea  name="mainContent" id="mainContent" rows="10"									
											cols="80" ></textarea>

							</div>
						</div>
						
						
				
						<div class="form-group">
							<label class="col-sm-3 control-label">备注：</label>
							<div class="col-sm-7">
								<textarea name="remark" rows="5" cols="70" class="form-control"
									onKeyDown="gbcount(this,400);" onKeyUp="gbcount(this,400);"></textarea>
							</div>
						</div>
						
					
						
						
						<div class="form-group" style="text-align: center;">
										<button 
								onclick="javascript:window.location.href='../courseCourseType/listOffLineAllCourse.do';return false" class="btn btn-info">返&nbsp;&nbsp;回
							</button>
									<button id="insertOrUpdateBtn"
										onclick="insertOrUpdate(); return false" class="btn btn-primary">下一步
									</button>
						 </div>
					</form>
				</div>
			</div>
		</div>
	</div>

<div class="bottombody"></div>
<div class="remodal normal noBorder normalModal teacherSearch"  data-remodal-id="teacherSearch" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
  <div class="box">
  		<div class="tools">
 			<span class="search">
				<input id="search-info" name="search" type="text" placeholder="教师名称" value="">
				<a href="javascript:void(0);" class="search-icon"><img src="../image/search01.png" alt="搜索"></a>	
			</span>
		</div>
	  	<button data-remodal-action="close" class="remodal-close" ></button>
  </div>
  <div class="wrapper noPadding" style="max-height:600px;overflow-y:scroll;" >
	  	<table class="table table-striped table-bordered batchOperation" id="teacherSelectTable">
	  		<tbody>
	  			<tr class="tableTh">
	  				<th></th>
	  				<th>姓名</th>
	  				<th>性别</th>
	  				<th>工作单位</th>
	  				<th>email</th>
	  			</tr>
	  		</tbody>
	  	</table>
	  	<div class="condtion">
			<ul class="pagination-admin" style="float:right"></ul>
			<div style="clear:both"></div>
		</div>
	  	<div class="condition" style="text-align: right;">
	  		<button class="btn btn-success" id="submitTeacherSelect">提交</button>
	  	</div>
  </div>
</div>
	<script type="text/javascript" src="../js/jquery-latest.js"></script>
	<script type="text/javascript" src="../js/nav/amenu.js"></script>
	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/JSCommonTools.js"></script>
	<script type="text/javascript" src="../js/aTool.js"></script>
	<script type="text/javascript" src="../js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="../js/UI/remodal.js"></script>
	<script type="text/javascript" src="../js/UI/remodal.min.js"></script>
	<script type="text/javascript" src="../js/UI/jquery.alerts.js"></script>

	<script language="javascript" type="text/javascript" src="../js/CheckFunction.js"></script>
	<script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../js/course/courseEditor.js"></script>
	<script type="text/javascript" src="../js/ueditor-new/ueditor.config.js"></script>
	<script type="text/javascript" src="../js/ueditor-new/ueditor.all.js"></script>
	<script type="text/javascript">
		function judegeTeacher() {
			var teacherName = document.getElementById("creator").value;
			$.ajax({
						url : '../tchrBaseInfo/judgeTeacher.do',
						data : {
							teacherName : teacherName
						},
						type : "POST",
						dataType : "json",
						async : false,
						success : function(data) {
							if (data.status  ) {
							    jConfirm('<font color=red>本机构教师库已有同名的主讲人，请确认两者是否属于同一个人，若是，本次操作仅将课件添加到主讲人的相关课件列表中;若否，请修改主讲人名称！</font>','提示',function(flag){
									if(flag){
										document.getElementById("form1").submit();
										disableLinkA("insertOrUpdateBtn");
									} else {
										document.getElementById("creator").focus();
									}
								});
							} else {
								document.getElementById("form1").submit();
								disableLinkA("insertOrUpdateBtn");
							}
						}
					})

		}




		$(function(){
			$("#courseDescription").hide();
			$.ajax({
				url:"../addLessonInfo/getOrgRelatedTerritory.do",
				method:"POST",
				dataType:"json",
				success:function(data){
					if(data.status ){
						$("#courseEditor #resourceCourseType").remove();
						var courseType=data.courseType;
						if(courseType!=null){
							$("#courseEditor").append("<input type='hidden' id='resourceCourseType' value='"+courseType.courseTypeID+"' />");
						}
						$.loadTerritory();
					}
					else{
						$.loadTerritory();
					}
				},
				error:function(){

				}
			})
		}

		)





		function insertOrUpdate() {

            if (document.getElementById("courseName").value == "") {
                //alert("课程名称不能为空!");
                jAlert("课程名称不能为空!", '提示');
                document.getElementById("courseName").focus();
                return;
            }
            if (document.getElementById("createDate").value == "") {
                //alert("创建时间不能为空!");
                jAlert("创建时间不能为空!", '提示');
                document.getElementById("createDate").focus();
                return;
            }
            if (document.getElementById("maker").value == "") {
                //alert("创建人不能为空！");
                jAlert("创建人不能为空！", '提示');
                document.getElementById("maker").focus();
                return;
            }
            if (document.getElementById("creator").value == "") {
                //alert("主讲人不能为空！");
                jAlert("主讲人不能为空！", '提示');
                document.getElementById("creator").focus();
                return;
            }
            if ($("input[name='isCoursePackage']:checked").val() == 0 && document.getElementById("classHour").value == "") {
                //alert("学时不能为空!");
                jAlert("学时不能为空!", '提示');
                document.getElementById("classHour").focus();
                return;
            }
            if ($("input[name='isCoursePackage']:checked").val() == 0 && document.getElementById("classHour").value != ""
                && !is_greaterZero(document.getElementById("classHour"), 1,
                    "学时只能是大于0的实数")){
                return;
			}

            var length = document.getElementById("kindId").options.length;
            var options_flag = 0;
            for ( var i = 0; i < length; i++) {
                if (document.getElementById("kindId").options[i].selected
                    && document.getElementById("kindId").options[i].value > 0)
                    options_flag = 1;
            }
            if (options_flag == 0) {
                //alert("请选择课件类别!");
                jAlert("请选择课件类别!", '提示');
                return;
            }

            if ($("#category").val() == 0) {
                //alert("请选择科目分类！");
                jAlert("请选择科目分类！", '提示');
                return;
            }

            if ($("#classfication").val() == 0) {
                //alert("请选择资源分类！");
                jAlert("请选择资源分类！", '提示');
                return;
            }

            var $selectTerritory = $(".selectTerritory");
            if ($selectTerritory.length == 0 || $selectTerritory.val() == "-1") {
                jAlert("请选择课程领域", "系统提示");
                return;
            } else {
                $selectTerritory.attr("name", "selectTerritory");
            }

/*
            var courseUploadPlatform = $('#courseUploadPlatform').val();
            //alert(courseUploadPlatform);
            if (courseUploadPlatform == 1) {
                //alert($("#orgId").val());
                if ($("#orgId").val() == 0) {
                    jAlert("请选择课程所属单位！", '提示');
                    return;
                }
            }
*/
            if (document.getElementById("keyWords").value == "") {
                //alert("关键词不能为空");
                jAlert("关键词不能为空,完善关键词有助于优化课件资源检索", '提示');
                document.getElementById("keyWords").focus();
                return;
            }
            var teacherId = $("#teacherId").val();
            if(teacherId == 1 ){
                judegeTeacher();
            }else{
                document.getElementById("form1").submit();
                disableLinkA("insertOrUpdateBtn");
            }

		}

		$(document).ready(function() {
			$("#newTypeId").hide();
		})

		function judgeNewType(s) {
			if (s == 99)
				$("#newTypeId").show();
			else
				$("#newTypeId").hide();
		}

		function disableLinkA(id) {
			$("#" + id).attr("disabled", true);
			$("#" + id).click(function() {
			});
			//注意：设置href=""不起作用
			$("#" + id).attr("href", "javascript:;");
		}


		$(function() {
			var option = {

				initialFrameWidth : 590,
				initialFrameHeight : 200

			};
			var editor = new UE.ui.Editor(option);
			editor.render('mainContent');
		})
	</script>
	<script>
		$(function() {
			$("input[name='isCoursePackage']").on("click",function(){
				if ($("input[name='isCoursePackage']:checked").val() == 0) {
					$("#classHourDiv").show();
					$("#courseDescription").hide();
				} else {
					$("#courseDescription").show();
					$("#classHourDiv").hide();
				}
			});
		})
	</script>
</body>
</html>
