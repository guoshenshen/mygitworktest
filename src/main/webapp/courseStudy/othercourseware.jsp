<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator" %>
<%@ page import="com.elearning.common.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    String userRolesInString = "";
    try{
        EosOperator user = (EosOperator) session.getAttribute(Constants.USERINFO_KEY);

    }catch(Exception ex){
        //do nothing
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%-- <html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/admin4.dwt" codeOutsideHTMLIsLocked="false" --> --%>
<html>
    <base href="<%=basePath%>"></base>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta content="培训;在线培训;网络培训" name="keywords" />
        <title><%=Constants.systemName%></title>
        <style type="text/css">
            body {
                text-align: center;
            }
            .jwlogo{
                display:none;
            }
        </style>
        <link id="styleId" href="css/skinCss/style.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery-UI/jquery.modal.min.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery-UI/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
        <link href="css/courseStudy/courseToolBox.css" rel="stylesheet" type="text/css" />
        <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
        <link id="style_td_Id"  href="css/skinCss/style_td.css" rel="stylesheet" type="text/css" />
        <link id="style_gl_Id" href="css/skinCss/style_gl.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="css/pagination.css"/>
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/>
        <link rel="stylesheet" type="text/css" href="css/uploadify.css"/>
        <link href="css/pop_window.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/template/templateTool.js"></script>
        <script type="text/javascript" src="js/flexpaper_flash.js"></script>
        <script type="text/javascript" src="../js/jquery.js"></script>
        <script type="text/javascript" src="./js/basicUserFunc.js"></script>
        <script type="text/javascript" src="js/UI/jquery.modal.min.js"></script>
        <script type="text/javascript" src="js/UI/jquery.mCustomScrollbar.concat.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $("body").addClass("showBoxStatus");
                var firstCourse = $("#firstCourse").val();
                if(firstCourse != "yes" ){
                    menuLoadingOptions.loadCourseToolBox(actionAfterToolBoxLoaded());
                }
            })
            function actionAfterToolBoxLoaded(){
                var result = false;
                var courseId = $("#coursePackageId").val();
                if(courseId == "" || courseId == null ){
                    courseId = $("#courseId").val();
                }
                var text = "";
                var images = "";
                var trainId = $("#trainId").val();
                var info = $("#info").val();
                $.ajax({
                    url:'/onlineCourse/getChapters.do?courseId='+courseId,
                    type:'get',
                    dataType:'text',
                    success:function(data,evt) {
                        var html = '<li class="section">';
                        data = JSON.parse(data);
                        if(data.status) {
                            if($("#coursePackageId").val() == ""){
                                text = '<div class="courseTitle f-thide" id="courseTitle">${course.courseName}</div>'+
                                    '<div class="courseQuality f-thide"><p class="courseInfo" id="evaluateNum">(${course.selectedTimes}人选学)</p></div>'+
                                    '<p class="courseInfo f-thide" id="publisher">创建人:${course.maker}</p>';

                                images = '<a href="javascript:void(0);"><img src="${course.pictureURL}" onerror="imgError({type:0,target:this})"/></a>';

                            }else{
                                text = '<div class="courseTitle f-thide" id="courseTitle">${coursePackage.courseName}</div>'+
                                    '<div class="courseQuality f-thide"><p class="courseInfo" id="evaluateNum">(${coursePackage.selectedTimes}人选学)</p></div>'+
                                    '<p class="courseInfo f-thide" id="publisher">创建人:${coursePackage.maker}</p>';
                                images = '<a href="javascript:void(0);"><img src="${coursePackage.pictureURL}" onerror="imgError({type:0,target:this})"/></a>';
                            }
                            $(".courseDetail").html(text);
                            $(".courseImage").html(images);
                            var chapterList = data.data;
                            for ( var i = 0; i < chapterList.length; i++) {
                                var chapter = chapterList[i];
                                var array = chapter.subset;
                                var oneCourse ;
                                if(array.length > 0){
                                    oneCourse = array[0].courseId;
                                }
                                if(info == "admin"){
                                    html += '<div class="sectionTitle" ><span class="sectionIndex"></span><a href="javascript:void(0);" onclick = "window.location.href=\'/courseStudy/previewStudy.do?courseID='+oneCourse+'&info=admin\'"><span class="sectionName" title ="'+chapter.chapterName+'" >'+chapter.chapterName+'</span></a></div>';
                                }else{
                                    html += '<div class="sectionTitle" ><span class="sectionIndex"></span><a href="javascript:void(0);" onclick = "window.location.href=\'/courseStudy/scormStudy.do?courseId='+oneCourse+'&train_id=${trainId}&section_id=0\'"><span class="sectionName" title ="'+chapter.chapterName+'" >'+chapter.chapterName+'</span></a></div>';
                                }
                                html += '<ul class="courseList">'
                                for ( var int = 0; int < array.length; int++) {
                                    if(array.length > 1){
                                        var course = array[int];
                                        if(info == "admin"){

                                            html += '<li class="courseItem"  id = "courseItem_'+course.courseId+'"><span class="courseItemIcon"></span><a  href="javascript:void(0);" onclick = "window.location.href=\'/courseStudy/previewStudy.do?courseID='+course.courseId+'&info=admin\'"><span class="courseItemTitle">'+course.courseName+'</span></a></li>'
                                        }else{
                                            html += '<li class="courseItem"  id = "courseItem_'+course.courseId+'"><span class="courseItemIcon"></span><a href="javascript:void(0);" onclick = "window.location.href=\'/courseStudy/scormStudy.do?courseId='+course.courseId+'&train_id=${trainId}&section_id=0\'"><span class="courseItemTitle">'+course.courseName+'</span></a></li>'
                                        }
                                    }
                                }
                                html += '</ul>';
                            }
                            html += '</li>';
                            $(".sectionList").html(html);
                            var courseId = $("#courseId").val();
                            $("#courseItem_"+courseId).css("background-color","#000");

                        }
                    }
                });
            }
        </script>
        <style type="text/css" media="screen">
            html, body	{ height:100%;background-color:#E7E7E7 }
            body { margin:0; padding:0; overflow:auto; }
            #flashContent,#courseInfo { display:none; }
        </style>
    </head>
    <body class="courseToolPanelParent">
    <iframe src="${courseurl}" style="width:100%;height:100%;">
    </iframe>
    <input id="coursePackageId" type='hidden' name="coursePackageId" value="${coursePackage.courseId}" />
    <input id="firstCourse" type='hidden' name="firstCourse" value="${firstCourse}" />
    <input id="courseId" type='hidden' name="courseId" value="${course.courseId}" />
    <input id="trainId" type='hidden' name="trainId" value="${trainId}" />
    <input id="info" type='hidden' name="info" value="${INFO}" />
    </body>
</html>

