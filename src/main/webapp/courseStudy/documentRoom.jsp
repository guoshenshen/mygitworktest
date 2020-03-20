<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.common.Constants" %>
<%@ page import="com.elearning.pojo.pub.EosOperator" %>
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
        <!-- InstanceBeginEditable name="doctitle" -->
        <title><%=Constants.systemName%></title>
        <!-- InstanceEndEditable -->
        <style type="text/css">
            body {
                text-align: center;
            }
            .jwlogo{
                display:none;
            }
        </style>
        <link id="styleId" href="css/skinCss/style.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery-UI/jquery.modal.min.css" rel="stylesheet"
              type="text/css" />
        <link href="css/jquery-UI/jquery.mCustomScrollbar.css"
              rel="stylesheet" type="text/css" />
        <link href="css/courseStudy/courseToolBox.css" rel="stylesheet"
              type="text/css" />
        <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
        <link id="style_td_Id"  href="css/skinCss/style_td.css" rel="stylesheet" type="text/css" />
        <link id="style_gl_Id" href="css/skinCss/style_gl.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="css/pagination.css"/>
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/>
        <link rel="stylesheet" type="text/css" href="css/uploadify.css"/>
        <link href="css/pop_window.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/template/templateTool.js"></script>
        <script type="text/javascript" src="js/flexpaper_flash.js"></script>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="./js/basicUserFunc.js"></script>
        <script type="text/javascript" src="js/UI/jquery.modal.min.js"></script>
        <script type="text/javascript" src="js/UI/jquery.mCustomScrollbar.concat.min.js"></script>
        <script type="text/javascript">
            $(function(){
                var $width=$(window).width();
                var $height=$(window).height();
                $('#viewerPlaceHolder').css('width',$width/2+$width/4).css('height',$height-5).css('display','block');

                var fp = new FlexPaperViewer(
                    '/js/course/FlexPaperViewer',
                    'viewerPlaceHolder', { config : {
                            COURSEID:$('div .courseId').text(),
                            BASEURL:$('div .baseUrl').text(),
                            APPORGNAME:$('div .appOrgName').text(),
                            Scale : 0.6,
                            ZoomTransition : 'easeOut',
                            ZoomTime : 0.5,
                            ZoomInterval : 0.2,
                            FitPageOnLoad : false,
                            FitWidthOnLoad : "true",
                            FullScreenAsMaxWindow : false,
                            ProgressiveLoading : false,
                            MinZoomSize : 0.2,
                            MaxZoomSize : 5,
                            SearchMatchAll : false,
                            InitViewMode : 'Portrait',
                            PrintPaperAsBitmap : false,

                            ViewModeToolsVisible : true,
                            ZoomToolsVisible : true,
                            NavToolsVisible : true,
                            CursorToolsVisible : "false",
                            SearchToolsVisible : "false",
                            localeChain: "zh_CN"
                        }});

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
                                images = '<a href="javascript:void(0);"><img src="${coursePackage.pictureURL}"  onerror="imgError({type:0,target:this})"/></a>';
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
        <!-- InstanceEndEditable -->
    </head>
    <body class="courseToolPanelParent">


    <div id="courseInfo">
        <span class="baseUrl">${BASEURL}</span>
        <span class="courseId">${COURSEID}</span>
        <span class="appOrgName">${appOrgName}</span>
    </div>
    <div align="center">
        <div id="viewerPlaceHolder"></div>

    </div>
    <input type="hidden" name="lastLearnTime" id="lastLearnTime"
           value=${lastLearnTime } />
    <input type="hidden" name="totalCourseTime" id="totalCourseTime"
           value=${totalCourseTime } />
    <input id="coursePackageId" type='hidden' name="coursePackageId" value="${coursePackage.courseId}" />
    <input id="firstCourse" type='hidden' name="firstCourse" value="${firstCourse}" />
    <input id="courseId" type='hidden' name="courseId" value="${course.courseId}" />
    <input id="trainId" type='hidden' name="trainId" value="${trainId}" />
    <input id="info" type='hidden' name="info" value="${INFO}" />
    <!-- FlexPaperViewer.swf是破解版的，可以去掉水印，但是很多监听触发事件没办法使用，所以采用了直接修改Flexpaper源码的问题解决记忆播放问题-->

    </body>
    <!-- InstanceEnd -->
</html>
<script type="text/javascript">

    //记忆播放功能：2018-03-22 author:xiongying
    //(1)此方法非常重要,不可删除或修改！在FlexPaperViewer.swf中被调用，用来从数据库中获取上次观看页面，然后控制"跳转到相应的页面"
    function getStartPageNumFromDB() {
        var lastTime = $('#lastLearnTime').val();
        //alert(lastTime);
        if(lastTime < 1){
            lastTime = 1;
        }
        console.log("lastTime:"+lastTime);
        return lastTime;
    }

    //(2)此方法非常重要,不可删除或修改！在FlexPaperViewer.swf中被调用，用来将当前页面保存到数据库中
    function saveCurrentPageIntoDB(currentPageNum,totalPageNum){
        var info = $("#info").val();

        if(info != "admin"){
            //成功返回1，否则返回0
            $.ajax({
                url:"<%=basePath%>/courseStudy/updateCourseStudyLastLearnTime.do",
                type:"post",
                data:{'position':currentPageNum,'totalTime':totalPageNum,'courseId':${course.courseId}},
                dataType:"text",
                success:function(data,evt){
                    return 1;
                },
                error:function(data,evt){
                    return 0;
                }
            })
        }

    }

</script>

