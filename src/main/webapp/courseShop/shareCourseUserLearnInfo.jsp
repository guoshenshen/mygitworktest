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
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/admin4.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta content="培训;在线培训;网络培训" name="keywords" />
	<!-- InstanceBeginEditable name="doctitle" -->
	<title><%=Constants.systemName%></title>
	<!-- InstanceEndEditable -->
	<link id="styleId" href="<%=Constants.SKINDIR%>/css/style.css" rel="stylesheet" type="text/css" />

	<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
	<link id="style_td_Id"   href="<%=Constants.SKINDIR%>/css/skinCss/style_td.css" rel="stylesheet" type="text/css" />
	<link id="style_gl_Id"  href="<%=Constants.SKINDIR%>/css/skinCss/style_gl.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/jquery-latest.js"></script>

	<script type="text/javascript">
        function getNames(type){
            var $this="";
            if(type==0)
                $this=$("#shareCourseToOrgOperatorName");
            else
                $this=$("#shareCourseToTenantName");
            var styleValue=$this.css("display");
            if(styleValue=='none')
                $this.css('display','block');
            else
                $this.css('display','none');
        }
        function updateCollectStatus(courseId,collectId){
            $.ajax({
                url:'../courseCourseType/updateCourseCollectStatus.do',
                type:'post',
                data:{courseId:courseId,collectId:collectId},
                success:function(data,evt){
                    top.$.unblockUI();
                    top.location.reload();
                }
            })
        }
	</script>
</head>
<body style="background-color: white">
<div id="mainbody" class="valuediv" style="width: 80%">
	<div id="content_02"
		 style="margin-top: 10px; font-size: 12px; font-family: 微软雅黑">
		<p style="color: red; font-size: 14px; margin-left: -20px;">
			*本机构学习此课程概况：
		</p>
		<div>
			<p>
				<strong>本机构是否已引用：</strong>
				<c:if test="${courseForm.usedByOrgFlag == 0 || courseForm.usedByOrgFlag == null }">否</c:if>
				<c:if test="${courseForm.usedByOrgFlag == 1}">是
				<div>
			<p>
				<strong>本机构选修或学习的人员信息：</strong>
				<c:if test="${courseForm.shareCourseToOrgLearnerNum > 0}">
					<!-- <a href="javascript:void(0);" onclick="javascript: getNames(0);">共${courseForm.shareCourseToOrgLearnerNum}个</a> -->
					<span style="font-size:14px;color:#317EF3;">共${courseForm.shareCourseToOrgLearnerNum}人</span>
				</c:if>
				<c:if test="${courseForm.shareCourseToOrgLearnerNum == 0}"><span style="font-size:14px;color:#317EF3;">共${courseForm.shareCourseToOrgLearnerNum }人</span></c:if>
			</p>
		</div>
		<div id="shareCourseToOrgOperatorName"
			 style="background-color: #4AA1DC; color: white; display: none; padding-left: 20px;">
				${courseForm.shareCourseToOrgAllOperatorNames }
		</div>
		<div>
			<p>
				<strong>本机构选修或学习的部门信息：</strong>${courseForm.shareCourseToOrgAllPosts
					}
			</p>
		</div>
		</c:if>
		</p>
	</div>
	<p style="color: red; font-size: 14px; margin-left: -20px;">
		*课程总体概况：
	</p>
	<div>
		<p>
			<strong>被选修或学习的总次数：</strong><span style="font-size:14px;color:#317EF3;">共${courseForm.totalLearnedUser }次</span>
		</p>
	</div>
	<div>
		<p>
			<strong>被引用的机构总数：</strong>
			<c:if test="${courseForm.shareCourseToTenantNum == 0}" ><span style="font-size:14px;color:#317EF3;">共${courseForm.shareCourseToTenantNum}个</span></c:if>
			<c:if test="${courseForm.shareCourseToTenantNum > 0}">
				<!--  <a href="javascript:void(0);" onclick="javascript: getNames(1);">共${courseForm.shareCourseToTenantNum}个</a> -->
				<span style="font-size:14px;color:#317EF3;">共${courseForm.shareCourseToTenantNum}个</span>
			</c:if>
		</p>
	</div>
	<div id="shareCourseToTenantName"
		 style="background-color: #4AA1DC; color: white; display: none; padding-left: 20px;">
		${courseForm.shareCourseToAllTenantNames }
	</div>
</div>
<c:if test="${courseForm.shareCourseCollectStatus == 2911}"  >
	<a href="javascript:void(0);" onclick="javascript:updateCollectStatus(${courseForm.courseId},${courseForm.shareCourseCollectStatus })"><div  style="background:url('../image/buchong.jpg') no-repeat center;height:40px;line-height:40px;font-family:Microsoft YaHei;font-size:24px;color:white;text-align:center;width:180px;margin-left: auto;margin-right: auto;">我要收藏</div></a>
</c:if>
<c:if test="${courseForm.shareCourseCollectStatus == 2912}" >
	<a href="javascript:void(0);" onclick="javascript:updateCollectStatus(${courseForm.courseId},${courseForm.shareCourseCollectStatus })"><div  style="background:url('../image/buchong.jpg') no-repeat center;height:40px;line-height:40px;font-family:Microsoft YaHei;font-size:24px;color:white;text-align:center;width:180px;margin-left: auto;margin-right: auto;">取消收藏</div></a>
</c:if>

</div>
</body>
</html>
