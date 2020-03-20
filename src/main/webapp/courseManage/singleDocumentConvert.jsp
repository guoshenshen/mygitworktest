<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher"%>
<%--<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>--%>
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
            <div class="homezoneall">
                <div class="gl_31_2"><span class="gl_11_no">1.基本信息</span><span class="gl_11_yes">2.课件上传</span><span class="gl_11_no">3.相关资料上传</span></div>

                <div class="content_02" align="center">
                    <input type="hidden" name="uploadCourseId" id="uploadCourseId" value='${courseId}'/>
                    <div id="convertVideo"><img src="../image/convert.gif"/><br/><br/><span>上传已完成,正在转化为swf格式...</span></div>
                    <br/>
                    <div id="previewVideo"><font color=red>转化成功！</font>
                        <a href="javascript:void(0);" onclick="window.open('courseStudy.do?method=previewDocument&courseID=${courseId}','课件预览','height=700,width=900,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no')">预览</a>
                    </div>
                    <div><br/>
                        <span style="font-size:12px;color:#5A5A5A;">[提示：<strong>您可以点击"下一步"继续操作，转码自动进行，课程管理首页可查看其转化状态</strong>]</span><br/>
                    </div>
                    <div class="clr"></div>
                    <div style="text-align:right; margin:20px;">
                        <c:if test="${courseDescription != null }">
                            <c:if test="${train != null }">
                                <button onclick="javascript:window.location.href = '../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id }';return false" class="btn btn-info">返&nbsp;&nbsp;回</button>
                                <button onclick="javascript:window.location.href = '../addLessonInfo/courseDescription.do?courseId=${courseId}&courseDescription=1';return false" class="btn btn-info">下一步</button>
                            </c:if>
                            <c:if test="${train == null }">
                                <button  onclick = "javascript:window.location.href = '../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId} ';return false"  class="btn btn-info">返&nbsp;&nbsp;回</button>
                                <button   onclick="javascript:window.location.href = '../addLessonInfo/courseDescription.do?courseId=${courseId}&courseDescription=1';return false" class="btn btn-info">下一步</button>
                            </c:if>
                        </c:if>
                        <c:if test="${courseDescription == null }">
                            <c:if test="${uploadChapter != null }">
                                <button onclick="javascript:window.location.href = '../addLessonInfo/intoSectionFragmentEditor.do?chapterId=${chapterId}';return false" class="btn btn-info">返&nbsp;&nbsp;回</button>
                                <button onclick="javascript:window.location.href = '../addLessonInfo/intoSectionFragmentEditor.do?chapterId=${chapterId}';return false" class="btn btn-info">完&nbsp;&nbsp;成</button>
                            </c:if>
                            <c:if test="${uploadChapter == null }">
                                <c:if test="${train != null }">
                                    <button onclick="javascript:window.location.href = '../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id }';return false" class="btn btn-info">返&nbsp;&nbsp;回</button>
                                    <button onclick="javascript:window.location.href = '../courseType/otherFileUpload.do?serverId=${serverId}&fileSize=${fileSize}&courseID=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id }';return false" class="btn btn-info">下一步</button>
                                </c:if>
                                <c:if test="${train == null }">
                                    <button  onclick = "javascript:window.location.href = '../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId} ';return false"  class="btn btn-info">返&nbsp;&nbsp;回</button>
                                    <button   onclick="javascript:window.location.href = '../courseType/otherFileUpload.do?serverId=${serverId}&fileSize=${fileSize}&courseID=${courseId}&courseTypeId=${courseTypeId}';return false" class="btn btn-info">下一步</button>
                                </c:if>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="token" name="token" value="${token}"/>
<div class="bottombody"></div>
<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/nav/amenu.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js"></script>
<script type="text/javascript" src="../js/aTool.js"></script>
<script type="text/javascript">
    $(function(){
        $('#previewVideo').css('display','none');
        getLatestVideoConvertMessage();
    })


    function insertOrUpdate(courseId,courseTypeId){
        window.location.href = "../addLessonInfo/updateCourse.do?courseId="+courseId+"&courseTypeId="+courseTypeId+"";
    }
    function getLatestVideoConvertMessage(){
        $.ajax({
            url:'../fileUpload/getConvertSingleVideoStatus.do',
            type:'post',
            dataType:'text',
            data:{uploadCourseId:$('#uploadCourseId').val()},
            success:function(data){
                var message=data.msg;
                $('#convertVideo span').html(message).css('color','red');
                if(message.indexOf("完")>-1){
                    $('#previewVideo').css('display','block');
                    $('#convertVideo').css("display",'none');
                }else
                    setTimeout("getLatestVideoConvertMessage()",5000);
            }
        })
    }



</script>
</body>
</html>
