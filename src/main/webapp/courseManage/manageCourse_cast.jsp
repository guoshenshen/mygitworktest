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
	<link href="../css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
	<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />

	<link href="../css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		th {
			text-align:center; /*设置水平居中*/
			vertical-align:middle;/*设置垂直居中*/
		}
		p {
			margin: 0px;
		}
	</style>
</head>
<body class="admin">
<div class="adminFrame">
	<div class="topbody"></div>
	<div class="mainbody" >
		<input id="defaultPageSize" type="hidden" value="${defaultPageSize}" />
		<div id="trace" class="content"></div>
		<div class="mainContent content" id = "courseFrame">
			<div id="contentbody">
				<div id="content_search">
					<div class="headtitle">
						<a
								href="javascript:void(0);"> <span
								style="font-size:14px;color: red" id = "courseManage" onclick="defaultCourse()">课程管理</span>
						</a>
						<c:if test="${courseUploadPlatform != 1}">
							&nbsp;|&nbsp;<a
							href="javascript:void(0);"> <span
								style="font-size:14px;" id = "sharedCourse" onclick="shareCourse()">共享课程池</span>
							</a>
							&nbsp;|&nbsp;<a
							href="javascript:void(0);"> <span
								style="font-size:14px;" id = "myFavorites" onclick="collectCourse()">我的收藏夹</span>
							</a>
						</c:if>

					</div>
					<!-- headtitle -->
				</div>


				<div class="searchCourse" id = "searchCourse">
					<form id="form1" name="form1" action="../courseCourseType/listOffLineAllCourse.do" method="post">
						<div class="form-inline condition" style="text-align: right;">
							<div class="form-group">
								<label class=" control-label">发布状态</label>

								<select name="coursePublishStatusInput" id="coursePublishStatusInput" class="form-control formInfo">
									<option value="0" selected="selected">全部</option>
									<c:if test="${pubishStatusList != null }" >
										<c:forEach var="pubishStatusId" items="${pubishStatusList}" >

											<option value="${pubishStatusId.code}"
													<c:if test="${pubishStatusId.code == coursePublishStatusInput}">
														selected
													</c:if>>
													${pubishStatusId.name}</option>
										</c:forEach>
									</c:if>
								</select>
								<input type="text" class="form-control formInfo" name="courseNameInput" placeholder="课程名称..."
									   id="courseNameInput" value="${courseNameInput}" />
								<c:if test="${courseUploadPlatform != 1}" >
									<input type="text" class="form-control formInfo"  placeholder="关键词..."
										   name="courseKeyWordsInput" id="courseKeyWordsInput"
										   value="${courseKeyWordsInput }" />
								</c:if>
								<input type="text" class="form-control formInfo" name="courseCreatorInput"  placeholder="讲师..."
									   id="courseCreatorInput" value="${courseCreatorInput }" />
								<button  type="button" id = "serchCourse" class="btn btn-primary" style="top:15px;">查询</button>
								<input type="hidden" id="conditionString" name="conditionString"
									   value="${conditionString}" />
							</div>
						</div>
					</form>
				</div>
				<!--content_single-->
				<div class="clr"></div>
				<div id="content_02">
					<form id="form3" name="form3" action="" method="post" >
						<table id="courseList"	class="table table-striped table-bordered batchOperation" rules="cols" width="100%" >
							<tr >
								<th width="4%"><input type="checkbox" name="selectAll"
													  onclick="_setSelected(this,'selectbox')" /></th>
								<th width="14%">课程名称</th>
								<th width="7%">主讲人</th>
								<th width="7%">专业领域</th>
								<th width="7%">关键词</th>
								<th width="11%">所属机构</th>

								<c:if test="${courseUploadPlatform != 1}" >
									<th width="6%" class = "coursedisplayYes">创建时间</th>
									<th width="6%"	class = "coursedisplayYes">发布状态</th>
									<th width="6%" class = "coursedisplayYes">公开范围</th>
									<th width="6%" class = "coursedisplayYes">发布时间</th>
									<th width="6%" class = "coursedisplayYes">课件状态</th>
									<th  width=8% style="display: none;" class = "coursedisplayNo">共享时间</th>
									<th width=7% style="display: none;" class = "coursedisplayNo">学习人次</th>
									<th width=8% style="display: none;" class = "coursedisplayNo  myFavoritesYes">学习概况</th>
									<th width=8% style="display: none;" class = "coursedisplayNo  myFavoritesNo">本机构员工学习与否</th>
									<th width=7% style="display: none;" class = "coursedisplayNo">收藏状态</th>
									<th width="20%">操作</th>
								</c:if>
								<c:if test="${courseUploadPlatform == 1 }" >
									<th width="9%">创建时间</th>
									<th width="8%">公开范围</th>
									<th width="9%">发布时间</th>
									<th width="8%">课件状态</th>
									<th  width=8% style="display: none;">共享时间</th>
									<th width=7% style="display: none;">学习人次</th>
									<th width=8% style="display: none;">本机构员工学习与否</th>
									<th width=7% style="display: none;">收藏状态</th>
									<th width="16%">操作</th>
								</c:if>
							</tr>
						</table>
					</form>
				</div>

				<div class="condtion">
					<ul class="pagination-admin" style="float:right"></ul>
					<div style="clear:both"></div>
				</div>

				<div class="condition" style="margin-top:20px;" id = "handleCourse">
					<c:if test="${ courseUploadPlatform != 1 }" >
						<button onclick="javascript:_add();" class="btn btn-info" >新建课程</button>
						<button id = "coursewareExport" class="btn btn-info" onclick="coursewareExport(this)">课件信息导出</button>
						<!-- <button id="configure"  class="btn btn-info">信息配置</button> -->
						<button  id = "deletionByQuery" onclick="deletionByQuery()" class="btn btn-danger">批量删除</button>
						<c:if test="${zipFilePath != null }">
							<button onclick="window.open('${zipFilePath}')" class="btn btn-info"><span
									class="btn btn-info">导出下载</span>
							</button>
						</c:if>
					</c:if>
					<c:if test="${ courseUploadPlatform == 1}" >
						<c:if test="${isCanUpdatable == 1}" >
							<button onclick="javascript:_add();" class="btn btn-info">新建课程</button>
							<button id = "deletionByQuery"  onclick="deletionByQuery()" class="btn btn-danger">批量删除</button>
						</c:if>
					</c:if>
				</div>
				<div id="content_03">
					<!-- gl_03 -->
					<div align="right">
						<form id="form2" name="form2" action="../courseCourseType/listOffLineAllCourse.do" method="post">
							<input type="hidden" id="pageNo" name="pageNo" value="1" />
                            <input type="hidden" name="courseProperty" value="${courseProperty}" />
							<input type="hidden" name="porpertyValue" value="${porpertyValue}" />
                            <input type="hidden" name="conditionString" value="${conditionString }" />
                            <input type="hidden" name="courseUnderScope" value="${courseUnderScope}" />
                            <input type="hidden" name="postId" value="${postId}" />
                            <input type="hidden" name="expertAreaId" value="${expertAreaId}" />
                            <input type="hidden" name="courseNameInput" value="${courseNameInput }" />
                            <input type="hidden" name="courseKeyWordsInput" value="${courseKeyWordsInput }" />
                            <input type="hidden" name="courseOrgNameInput" value="${courseOrgNameInput}" />
                            <input type="hidden" name="courseCreatorInput" value="${courseCreatorInput }" />
                            <input type="hidden" name="coursePublishStatusInput" value="${coursePublishStatusInput}" />
						</form>
					</div>
					<div class="clr"></div>
				</div>

			</div>

		</div>

		<!-- mainbody -->
		<div id="chooseCourseOpenScope"
			 style="display:none;cursor:default;overflow:hidden;">
			<div id='_pop_win'>
				<h2>
					选择发布范围<a href='javascript:void(0);' class='pop_close_btn' style="float: right;padding-right: 10px">X</a>
				</h2>
			</div>
			<div style="text-align:left;padding-left:20px;">
				<c:if test="${courseUploadPlatform != 1}" >
					<p>
						<label><input type="radio" name="courseOpenScope"
									  value="2201" />不公开</label>
					</p>
					<p id="unitScope">
						<label><input type="radio" name="courseOpenScope"
									  value="2202" />机构内公开</label>
					</p>
				</c:if>
				<p id="orgScope">
					<label><input type="radio" name="courseOpenScope"
								  value="2203" />本单位公开</label>
				</p>
				<p>
					<label><input type="radio" name="courseOpenScope"
								  value="2204" />全院公开</label>
				</p>
				<p>
					<label><input type="radio" name="courseOpenScope"
								  value="2205" />完全公开</label>
				</p>

				<c:if test="${courseUploadPlatform != 1}">

					<input type="button" value="确定"
						   onclick="javascript:forUpdateOpenStatus()" style="float: right;
    						margin-right: 60px;"/>
				</c:if>
				<c:if test="${courseUploadPlatform == 1}" >
					<input type="button" value="确定"
						   onclick="javascript:forPublishCourseForCourseUploadPlatform()" />
				</c:if>
				<input type="hidden" id="selectedCourseId" value="" /> <input
					type="hidden" id="selectedCourseOrgId" value="" /> <input
					type="hidden" id="selectedCourseTenantOrgId" value="" />
			</div>
		</div>
	</div>
</div>
<div class="bottombody"></div>


<div class="normal noBorder remodal courseConfigureModal" data-remodal-id="courseConfigureModal" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc" tabindex="-1" >
	<div class="box"><button data-remodal-action="close" class="remodal-close"></button></div>
	<div class="wrapper common">
		<div class="territoryEditor">
			<span>所属领域</span>
			<select class="territory"></select>
		</div>
		<button data-remodal-action="cancel" class="remodal-cancel">取消</button>
		<button class="remodal-confirm">确定</button>
	</div>
</div>



<div class="remodal normal noBorder courseModal "  data-remodal-id="courseModal" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc' style="padding-bottom:0px;">
	<div class="box">
		<button data-remodal-action="close" class="remodal-close" ></button>
	</div>
	<div class="wrapper common" >
		<iframe  width='800' style="min-height:500px;" scrolling='auto' class='pop_iframe' src=''> </iframe>
	</div>
</div>

<div style="display: none"><h2 >共享课程选修与学习<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
	<div id="shareCourseLearnInfo" style="text-align:left;padding:10px;font-family:微软雅黑"></div>
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
<script type="text/javascript" src="../js/course/courseEditor.js"></script>
<script type="text/javascript" src="../js/public/jquery.excel.js"></script>

<script type="text/javascript">
	var resultName = "";
	$(function(){
        courseList();
        var $courseWin=$('[data-remodal-id=courseModal]').remodal($.defaultRemodalOption());
    	/*课程名称的点击*/
        $("#courseList").on("click",".courseName",function(){
            var course_id = $("input[name='selectbox']",$(this)).val();
            $(".courseModal .wrapper.common iframe").attr("src","<%=basePath%>courseCourseType/detail.do?courseId="+course_id);
            $courseWin.open();
        })
		/*本机构员工学习与否的点击*/
        $("#courseList").on("click","#myFavoritesLook",function(){
            var course_id = $("input[name=myFavoritesLook]",$(this)).val();
            $(".courseModal .wrapper.common iframe").attr("src","<%=basePath%>courseCourseType/findUserLearnCourseInfo.do?courseId="+course_id);
            $courseWin.open();
        })

        $("#searchCourse").on("click","#serchCourse",function(){
                courseList();
            })
	})

	function shareCourse(){
	    resultName = "shareCourse";
        courseList();
	}
	/*共享课程*/
	function shareCourse(){
	    resultName = "shareCourse";
        $("#sharedCourse").css("color","red");
        $("#sharedCourse").css("text-decoration","nono");
        $("#courseManage").css("color","inherit");
        $("#myFavorites").css("color","inherit");
        courseList();
	}
	/*收藏夹*/
	function collectCourse(){
	    resultName = "collectCourse";
        $("#myFavorites").css("color","red");
        $("#myFavorites").css("text-decoration","nono");
        $("#courseManage").css("color","inherit");
        $("#sharedCourse").css("color","inherit");
        $("#handleCourse").hide();
        courseList();
	}

	/*课程管理*/
	function defaultCourse(){
		resultName = "";
        $("#courseManage").css("color","red");
        $("#courseManage").css("text-decoration","nono");
        $("#sharedCourse").css("color","inherit");
        $("#myFavorites").css("color","inherit");
        courseList();
	}
	/*查询按钮*/
	/*function serchCourse() {
        courseList();
    }*/


    function courseList(){
        var courseListPagination=$.getPaginationConfig();
        var courseListPaginationConfig={
            getUrlForPagination:function(){return "../courseCourseType/listCourses.do"},
            actionForClearLoadedData:function(){
                $("#courseList .infoRow").remove();
            },
            actionForSucessLoadingData:function(data){
                var dataList=data.list;
                var dataLength=dataList.length;
                var htmlArray=new Array();
                for(var i = 0; i < dataLength ; i++){
                    var currentItem=dataList[i];
                    htmlArray.push("<tr class='infoRow' id='tr_"+currentItem.courseId+"' >");
                    htmlArray.push("<td><input id='checkbox_"+currentItem.courseId+"' type='checkbox' value='"+currentItem.courseId+"' name='selectbox' /></td>");
                    htmlArray.push("<td align='left' class = 'coursedisplayYes'><a href='javascript:void(0);' class='courseName' >"
                        +"<input  type='hidden' value='"+currentItem.courseId+"' name='selectbox' />"
                        +currentItem.courseName+"</a></td>");
                    htmlArray.push("<td align='left' class = 'coursedisplayNo'>"+currentItem.courseName+"</td>");
                    htmlArray.push("<td align='center'>"+currentItem.creator+"</td>");
                    htmlArray.push("<td align='center' >"+currentItem.expertAreas+"</td>");
                    htmlArray.push("<td align='center' >"+currentItem.keyWords+"</td>");
                    htmlArray.push("<td align='center' >"+currentItem.orgName+"</td>");
                    htmlArray.push("<td align='center' class = 'coursedisplayYes' >"+currentItem.createDateStr+"</td>");

                    htmlArray.push("<td align='center' class = 'coursedisplayYes'>"
                        +"<a href='javascript:void(0);' id = 'pubstatus_"+currentItem.courseId+"' class='pubstatus operable' onclick='updatePubStatus(this)'>"
                        +currentItem.pubStatus+"</a></td>");

                    var openScope = null;
                    if(currentItem.openScope == 2201){
                        openScope = "不公开";
                    }
                    if(currentItem.openScope == 2202){
                        openScope = "机构内公开";
                    }
                    if(currentItem.openScope == 2203){
                        openScope = "本单位公开";
                    }
                    if(currentItem.openScope == 2204){
                        openScope = "全院公开";
                    }
                    if(currentItem.openScope == 2205){
                        openScope = "完全公开";
                    }
                    htmlArray.push("<td align='center' class = 'coursedisplayYes'>"
                        +"<div id='openScope_"+currentItem.courseId+"' class='scopeChangeDIV'>"
                        +	  "<input type='hidden' name='updatable' value='"+currentItem.updatable +"' />"
                        +	  "<input type='hidden' name='course_id' value='"+currentItem.courseId+"' />"
                        +	  "<input type='hidden' name='openScope' value='"+currentItem.openScope +"' />"
                        +	  "<input type='hidden' name='orgId' value='"+currentItem.orgId +"' />"
                        +	  "<input type='hidden' name='tenantOrgId' value='"+currentItem.tenantId +"' />"
                        +    " <a href='javascript:void(0);' class='scopeChangeLink openScopeName operable' onclick='updateOpenStatus(this)'>"+openScope+"</a>"
                        +    "<p class='onlyShown openScopeName'></p> "
                        +"</div></td>	");
                    if(currentItem.updatable == 1){
                        $(".operable",$("tr")).show();
                    }else{
                        $(".onlyShown",$("tr")).show();
                    }
                    var shareDateStr = currentItem.shareDateStr;
                    if(shareDateStr == ""){
                        shareDateStr = "未发布";
                    }
                    htmlArray.push("<td align='center' class = 'coursedisplayYes'>"+shareDateStr+"</td>");
                    if(currentItem.enterUrl == ""){
                        htmlArray.push("<td align='center' class = 'coursedisplayYes' ><img src='../image/redStatus.png' style='vertical-align:top;'/>不可用</td>");
                    }else{
                        htmlArray.push("<td align='center' class = 'coursedisplayYes' ><img id='useable_"+currentItem.courseId+"'  src='../image/greenStatus.png' style='vertical-align:top;'/>可使用</td>");
                    }
                    htmlArray.push("<td align='center'  class = 'coursedisplayNo' style = 'display: none;'>"+shareDateStr+"</td>");
                    htmlArray.push("<td align='center' class = 'coursedisplayNo' style = 'display: none;'>"+currentItem.selectedTimes+"</td>");
                    if(currentItem.usedByOrgFlag == 1){
                        htmlArray.push("<td align='center' class = 'coursedisplayNo  myFavoritesNo' style = 'display: none;' >"
                            +"是</td>");
                    }else{

                        htmlArray.push("<td align='center' class = 'coursedisplayNo  myFavoritesNo' style = 'display: none;' >"
                            +"否</td>");
                    }

                    htmlArray.push("<td align='center' class = 'coursedisplayNo   myFavoritesYes' style = 'display: none;' >"
                        +"<a href='javascript:void(0);' id = 'myFavoritesLook'>"
                        +"<input style =  'display: none;' value='"+currentItem.courseId+"' name='myFavoritesLook' />"
                        +"查看</a></td>");

                    if(currentItem.shareCourseCollectStatus == 2911){
                        htmlArray.push("<td align='center' class = 'coursedisplayNo updateCollectStatus'  style = 'display: none;'>"
                            +"<a href='javascript:void(0);' id = 'updateCollectStatus_"+currentItem.courseId+"' onclick='updateCollectStatus(this)' >"
                            +"<input style =  'display: none;' value='"+currentItem.shareCourseCollectStatus+"' name='shareCourseCollectStatusNo' />"
                            +"<input style =  'display: none;' value='"+currentItem.courseId+"' name='shareCourseCollectStatusId' />"
                            +"未收藏</a></td>");
                    }
                    if(currentItem.shareCourseCollectStatus == 2912){
                        htmlArray.push("<td align='center' class = 'coursedisplayNo updateCollectStatus' style = 'display: none;'>"
                            +"<a href='javascript:void(0);' id = 'updateCollectStatus_"+currentItem.courseId+"' onclick='updateCollectStatus(this)'>	"
                            +"<input style =  'display: none;' value='"+currentItem.shareCourseCollectStatus+"' name='shareCourseCollectStatusNo' />"
                            +"<input style =  'display: none;' value='"+currentItem.courseId+"' name='shareCourseCollectStatusId' />"
                            +"已收藏</a></td>");
                    }
                    if(currentItem.updatable == 1){
                        var selfSetUp = " |<a id = 'selfSetUp' href='addLessonInfo.do?method=forUpdateSelfTestMark&courseId="+currentItem.courseId+"'>自测设置</a>"
                    }else{
                        var selfSetUp = "|<a  id = 'selfChecking' href='myExam.do?method=checkOutSelfTestExam&courseId="+currentItem.courseId+"&searchFlag=1'>自测查看</a>"
                    }
                    htmlArray.push("<td align='center' class = 'coursedisplayYes'><a id='edit_"+currentItem.courseId+"' href='../addLessonInfo/updateCourse.do?"
                        +"courseId="+currentItem.courseId+"&courseTypeId="+currentItem.courseTypeId+"'>"
                        +"编辑</a> <span id='edit_line_"+currentItem.courseId+"' class='operable'>|</span>"

                        +"<a href='javascript:void(0);' id= 'clickPreview' onclick='clickPreview(this)'>"
                        +"<input style =  'display: none;' value='"+currentItem.courseId+"' name='courseId' />"
                        +"<input style =  'display: none;' value='"+currentItem.enterUrl+"' name='enterUrl' />"
                        +"预览</a>"
                        +"|<a href='rsmRcmbookdiscussManageListAction.do?bookId="+currentItem.courseId+"&discussType=1703'>评论查看</a>"
                        + selfSetUp
                        +" </td>");
                    if(currentItem.enterUrl != "" && currentItem.enterUrl != null ){
                        htmlArray.push("<td align='center' class = 'coursedisplayNo' style = 'display: none;'>"
                            +" <a href='javascript:void(0);' onclick='window.open(\"/courseStudy/previewStudy.do?courseID="+currentItem.courseId+"&info=admin\")'>预览</a>"
                            +" | <a href='javascript:void(0);' onclick = 'window.open(\"/onlineCourse/detail.do?bookId="+currentItem.courseId+"&info=admin\")'>详细</a>"
                            +"</td>");
                    }else{
                        htmlArray.push("<td align='center' class = 'coursedisplayNo' style = 'display: none;'>预览"
                            +" | <a href='javascript:void(0);' onclick = 'window.open(\"/onlineCourse/detail.do?bookId="+currentItem.courseId+"&info=admin\")'>详细</a>"
                            +"</td>");
                    }

                    htmlArray.push("<input id = 'returnCourse_id' style='display:none' value = '"+currentItem.courseId+"'></input>");
                    htmlArray.push("</tr>");
                }
                $("#courseList .infoRow").remove();
                $("#courseList").append(htmlArray.join(""));

                if(resultName == "" || resultName == null){
                    $(".coursedisplayNo").hide();
                    $(".coursedisplayYes").show();
                    $("#handleCourse").show();
                }
                if(resultName == "shareCourse"){
                    $(".coursedisplayYes").hide();
                    $(".coursedisplayNo").show();
                    $("#handleCourse").hide();
                }
                if(resultName == "collectCourse"){
                    $(".coursedisplayYes").hide();
                    $(".coursedisplayNo").show();
                    $("#handleCourse").hide();
                }
            }
        };
        $.extend(courseListPagination,courseListPaginationConfig);
		courseListPagination.resetSearchInfo();
		$(".searchCourse .formInfo").each(function(index,that){
			courseListPagination.searchInfo[that.name]=that.value;
		});
		courseListPagination.searchInfo["resultName"] = resultName;
		courseListPagination.actionForLoadingPagination();

    }



	/*发布按钮*/
	function updatePubStatus(obj) {
        var course_id=obj.id.replace("pubstatus_","");;
        var pubStatus=$(obj).text();
        var pubStatusId=null;
        if($("#useable_"+course_id).length==0){
            $.tips("课程内容不可使用，请重新编辑课程！","系统提示",function(){
            });
            return;
        };
        var afterStatus="";
        if(pubStatus=="已发布"){
            pubStatusId=1091;
            afterStatus='未发布';
        }
        else{
            pubStatusId=1092;
            afterStatus='已发布';
        }
        $.ajax({
            url:'../courseCourseType/updateCoursePubStatus.do',
            method:"post",
            dataType:"json",
            data:{
                "courseId":course_id,
                "pubStatusId":pubStatusId
            },
            success:function(data){
                if( data.status ){
                    $("#pubstatus_"+course_id).html(afterStatus);
                    $("a.scopeChangeLink","#openScope_"+course_id).html("不公开");
                    $("input[name=openScope]","#openScope_"+course_id).val(2201);
                }
                else{
                    $.tips("课程发布状态设置失败","系统提示");
                }
            },
            error:function(){
                $.tips("课程发布状态设置失败","系统提示");
            }
        });
    }


    /*公开范围按钮*/
    function updateOpenStatus(obj){
        var $parentDIV=$(obj).parents(".scopeChangeDIV");
        var course_id=$("input[name=course_id]",$parentDIV).val();
        var orgId=$("input[name=orgId]",$parentDIV).val();
        var pubStatus=$("#pubstatus_"+course_id).html();
        var tenantOrgId=$("input[name=tenantOrgId]",$parentDIV).val();
        var openScope=$("input[name=openScope]",$parentDIV).val();
        if($("#useable_"+course_id).length==0){
            $.tips("课程内容不可使用，请重新编辑课程！","系统提示",function(){});
            return;
        }
        if(pubStatus=="未发布"){
            $.tips("此课程还未发布，不能公开，请先发布！",'系统提示');
            return;
        }
        if(orgId!=tenantOrgId)
            $("#unitScope").css("display","block");
        if(orgId == tenantOrgId && tenantOrgId == 1)
            $("#orgScope").css("display","none");
        $('#selectedCourseId').val(course_id);
        $('#selectedCourseOrgId').val(orgId);
        $('#selectedCourseTenantOrgId').val(tenantOrgId);
        $("input[name=courseOpenScope]:radio").each(function(){
            if(this.value==openScope){
                this.checked=true;
            }
        })
        $.blockUI({ message: $("#chooseCourseOpenScope"), css: { width: '400px',height:'252px'} });
        $("a.pop_close_btn").click(function(){$.unblockUI();});
	}

	/*预览按钮*/
	function clickPreview(obj){
        var enterUrl = $("input[name='enterUrl']",$(obj)).val();
        var courseId = $("input[name='courseId']",$(obj)).val();
        /*if(enterUrl == ""){
            $.Ntip({'content':"该课程不可使用",})
        }else{
            window.open("/onlineCourse/detail.do?bookId="+courseId+"&info=admin")
        }*/
        window.open("/onlineCourse/detail.do?bookId="+courseId+"&info=admin")
	}


	/*修改公开范围的方法*/
    function forUpdateOpenStatus(){
        var selectedCourseId=$('#selectedCourseId').val();
        var openScope=$('input[name=courseOpenScope]:checked').val();
        var openScopeInfo=$('input[name=courseOpenScope]:checked').parents("label").text();
        var $currentCourseDIV=$("#openScope_"+selectedCourseId);
        $.ajax({
            url:'../courseCourseType/updateCourseOpenScope.do',
            method:"post",
            dataType:"json",
            data:{courseId:selectedCourseId,openScope:openScope},
            success:function(data){
                if( data.status ){
                    $("a.scopeChangeLink",$currentCourseDIV).text(openScopeInfo);
                    $("input[name=openScope]",$currentCourseDIV).val(openScope);
                }
                else{
                    $.tips("课程公开范围设置失败","系统提示");
                }
            },
            error:function(data){
                $.tips("课程公开范围设置失败，请稍后重试","系统提示");
            },
            complete:function(data){
                $.unblockUI();
            }
        })
    }

    /*收藏按钮*/
    function updateCollectStatus(obj) {
        var courseId = $("input[name='shareCourseCollectStatusId']",$(obj)).val();
        var collectId = $("input[name='shareCourseCollectStatusNo']",$(obj)).val();
        var collectFlag="";
        var msg = "";
        if(collectId==2912)
            msg="确定取消对该课程的收藏吗?";
        else if(collectId==2911)
            msg="确定收藏该课程吗？";
        jConfirm(msg,"提示",function(flag){
            if(flag){
                $.ajax({
                    url:"../courseCourseType/updateCourseCollectStatus.do",
                    type:"post",
                    data:{courseId:courseId,collectId:collectId},
                    success:function(data,evt){
                        if(data.status ){
                            if(collectId == 2911){
                                $("#updateCollectStatus_"+courseId).html(
                                    "<input style =  'display: none;' value='"+2912+"' name='shareCourseCollectStatusNo' />"
                                    +"<input style =  'display: none;' value='"+courseId+"' name='shareCourseCollectStatusId' />已收藏");
                                jAlert('收藏成功！','提示');
                            }else{
                                $("#updateCollectStatus_"+courseId).html("<input style =  'display: none;' value='"+2911+"' name='shareCourseCollectStatusNo' />"
                                    +"<input style =  'display: none;' value='"+courseId+"' name='shareCourseCollectStatusId' />未收藏");
                                jAlert('取消成功！','提示');
                            }
						}else{
                            jAlert('收藏失败！','提示');
						}
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        jAlert('收藏失败！','提示');
                    }

                });

            }
        });
    }

	/*课件信息导出按钮*/
	function coursewareExport(obj){


        var optionsForExport={};
        optionsForExport.success=function(data){
            if(data.result=='true'){
                $("#coursewareExport").click(function(){
                    window.open(data.downloadPath);
                });
                $("#coursewareExport").show();
            }
            else{
                $.Ntip({
                    "content":"证书清单导出失败"
                })
            }
        };

        $("#courseList").exportBigTable(
            {
                optionForExcel:optionsForExport,
                errorTip:function(){
                    $.Ntip({
                        "content":"证书导出失败"
                    })
                }

            });

/*        var roleIdArray=new Array();
        $(".infoRow input[type=checkbox]:checked").each(function(index,that){
            roleIdArray.push(that.value);
        });

        if(roleIdArray.length==0){
            $.Ntip({
                'content':"请选择要导出的课程。",
            })
        }else{
            $.Nconfirm({
                'confirmQuestion':"确认导出课程？",
                'onConfirm':function(){
                    $.ajax({
                        url:"../courseCourseType/exportCourseInfo.do",
                        type:"POST",
                        data:{"selectbox":roleIdArray},
                        dataType:"json",
                        traditional:true,
                        success:function(data){
                            if(data.status == 0 ){
                                $.Ntip({
                                    'content':"课程导出成功",
                                    'onClose':function(){
                                        window.open(data.data);
                                        courseList();
                                    }
                                })
                            }
                            else{
                                $.Ntip({
                                    'content':"课程导出失败",
                                })
                            }
                        }
                    });
                }
            });
        }*/

	}

    /**
	 * 全部选中按钮
     * @param id
     * @param type
     * @private
     */
    function _setSelected(id,type){
        var chechTemp = id.checked;
        with(document.form3){
            for ( var j=0; j<elements.length; j++ ){
                if ( elements[j].type == "checkbox" && elements[j].name == type) {
                    if(chechTemp){
                        document.form3.elements[j].checked = true;
                    }else{
                        document.form3.elements[j].checked = false;
                    }
                }
            }
        }
    }

    /**
	 * 批量删除
     */
    function deletionByQuery(){
        var roleIdArray=new Array();
        var errorMessage = "课件删除成功";
        $(".infoRow input[type=checkbox]:checked").each(function(index,that){
            roleIdArray.push(that.value);
        });
        if(roleIdArray.length==0){
            $.Ntip({
                'content':"请选择要删除课件",
            })
        }else{
            $.Nconfirm({
                'confirmQuestion':"确认删除已选择的课件吗？",
                'onConfirm':function(){
                    $.ajax({
                        url:"../courseCourseType/deleteCourse.do",
                        contentType: "application/x-www-form-urlencoded; charset=utf-8",
                        type:"POST",
                        data:{"selectbox":roleIdArray},
                        dataType:"json",
                        traditional:true,
                        success:function(data){
                            if(data.status ){
                                $.Ntip({
                                    'content':data.msg,
                                    'onClose':function(){
                                        courseList();
                                    }
                                })
                            }else if(data.status == 1){
                                $.Ntip({
                                    'content': data.msg,
                                    'onClose':function(){
                                        courseList();
                                    }
                                })
                            }else{
                                $.Ntip({
                                    'content':"课件删除失败",
                                })
                            }
                        }
                    });
                }
            });
        }
	}
	function _add(){
        document.form1.action="../courseType/addNewCourse.do";
        document.form1.submit();
	}
</script>
</body>
</html>
