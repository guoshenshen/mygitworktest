<%@ page import="com.elearning.common.Constants" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    String courseID = (String) session.getAttribute("COURSEID");

    String actionType=request.getParameter("actionType");
    if(actionType!=null){
        request.setAttribute("actionType",actionType);
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>"></base>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="中国科学院,继续教育,线上课程">
        <meta http-equiv="description" content="中国科学院继续教育平台线上课程">
        <title>单一视频课件</title>
        <link href="css/video-js.min.css" rel="stylesheet">
            <%--<link href="https://cdnjs.cloudflare.com/ajax/libs/video.js/7.3.0/video-js.min.css" rel="stylesheet">--%>
        <link href="css/courseStudy/videojs.markers.css" rel="stylesheet">
        <link rel="stylesheet" href="http://vjs.zencdn.net/5.8.8/video-js.css" />
        <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
        <link rel="stylesheet" type="text/css"  href="./css/bootstrap.min.css" />
        <link href="css/jquery-UI/jquery.modal.min.css" rel="stylesheet"  type="text/css" />
        <link href="./css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="css/jquery-UI/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
        <link href="css/courseStudy/courseToolBox.css" rel="stylesheet" type="text/css" />
        <script src="http://vjs.zencdn.net/ie8/1.1.2/videojs-ie8.min.js"></script>
        <script src="http://api.html5media.info/1.1.4/html5media.min.js"></script>
        <!--videjs样式部分-->
            <%--<link href="http://vjs.zencdn.net/6.10/video-js.min.css" rel="stylesheet">--%>
        <!--videjs的js部分-->
        <script src="http://vjs.zencdn.net/6.10/video.min.js"></script>
        <style type="text/css">
            video::cue{
                background-color:transparent;
                color:#fff;
                font-size:40px;
            }
            .vjs-paused .vjs-big-play-button,
            .vjs-paused.vjs-has-started .vjs-big-play-button {
                display: block;
            }
            .video-js .vjs-big-play-button{
                font-size: 2.5em;
                line-height: 2.3em;
                height: 2.5em;
                width: 2.5em;
                -webkit-border-radius: 2.5em;
                -moz-border-radius: 2.5em;
                border-radius: 2.5em;
                background-color: #73859f;
                background-color: rgba(115,133,159,.5);
                border-width: 0.15em;
                margin-top: -1.25em;
                margin-left: -1.75em;
            }
            .vjs-big-play-button .vjs-icon-placeholder {
                font-size: 1.63em;
            }
            .vjs-loading-spinner {
                font-size: 2.5em;
                width: 2em;
                height: 2em;
                border-radius: 1em;
                margin-top: -1em;
                margin-left: -1.5em;
            }
            .video-js .vjs-time-control{display:block;}
            .video-js .vjs-remaining-time{display: none;}
        </style>
    </head>
    <body class="courseToolPanelParent">
    <div class = "wap">
        <div id="contain">
            <div id='videodiv'></div>
            <div id='remindDIV' style="display:none">
                <p></p>
            </div>
        </div>
        <div id="video_div" style="position:relative;">
            <video id="example_video" class="video-js vjs-big-play-centered vjs-default-skin" controls crossorigin="anonymous" >
                <source src="http://159.226.28.53/course/cloud/1493376332596/sv1493376332596.mp4" type="video/mp4" />
                <!-- <track label="中文字幕" kind="captions" chapters srclang="zh" src="courseStudy/test_cn.vtt" default> -->
                <%--<c:if test="${captionsChinese != ''}"  >
                    <track label="中文字幕"     kind="subtitles"    srclang="cn" src="${captionsChinese}" default />
                </c:if >
                <c:if test="${captionsEnglish != '' }"  >
                    <track label="英文字幕"     kind="subtitles"    srclang="en" src="${captionsEnglish}" />
                </c:if>--%>
            </video>


            <c:if test="${actionType.equals('setMarker')}">
                <div id="markerTool" title="设置视频关键帧">
                    <div class="mask">
                    </div>
                    <label class="col-sm-1" id="marker_timeInfo" style=" font-size: 14px;line-height: 2em;"></label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control markerParam" id="marker_text" name="text" placeholder="" />
                        <input type="hidden" id="marker_time" class="markerParam" name="time" />
                        <input type="hidden" id="marker_id" class="markerParam" name="id" />
                        <input type="hidden" id="marker_courseId" class="markerParam" name="courseId" value="${course.courseId}" />
                    </div>
                    <i class="fa fa-check" id="submitMarker" style="float:left;" title="设置"></i>
                    <i class="fa fa-caret-square-o-left" id="toPrevMarker" title="上一关键帧"></i>
                    <i class="fa fa-caret-square-o-right" id="toNextMarker" title="下一关键帧"></i>
                    <i class="fa fa-trash" id="deleteMarker" title="删除"></i>
                    <i class="fa fa-cog" id="setMarker" title="设置"></i>
                </div>
            </c:if>

        </div>
    </div>
    <input id="coursePackageId" type='hidden' name="coursePackageId" value="${coursePackage.courseId}" />
    <input id="firstCourse" type='hidden' name="firstCourse" value="${firstCourse}" />
    <input id="courseId" type='hidden' name="courseId" value="${course.courseId}" />
    <input id="sliceType" type='hidden' name="sliceType" value="${course.sliceType}" />
    <input id="trainId" type='hidden' name="trainId" value="${trainId}" />
    <input id="info" type='hidden' name="info" value="${INFO}" />
    <input id="captionsChinese" type='hidden' name="captionsChinese" value="${captionsChinese}" />
    <input id="captionsEnglish" type='hidden' name="captionsEnglish" value="${captionsEnglish}" />
    <input type="hidden" name="isMultiSize" id="isMultiSize"
           value=${course.isMultiSize } />
    <input type="hidden" name="lastLearnTime" id="lastLearnTime"
           value=${lastLearnTime } />
    <input type="hidden" name="totalCourseTime" id="totalCourseTime"
           value=${totalCourseTime } />
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/video.js/7.3.0/video.min.js"></script>
    <script type="text/javascript" src="js/video/video.min.js"></script>
    <script type="text/javascript" src="js/video/videojs-markers.js"></script>
    <script type="text/javascript" src="js/jquery-latest.js"></script>
    <script type="text/javascript" src="js/template/templateTool.js"></script>
    <script type="text/javascript" src="js/UI/jquery.modal.min.js"></script>
    <script type="text/javascript" src="js/UI/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/javascript" src="./js/basicUserFunc.js"></script>
    <script type="text/javascript" src="js/UI/jquery.alerts.js"></script>
    <script type="text/javascript">

        var player;
        var totalTime;
        var position = 0;
        $(function(){

            var isMobile = isFromMobile();//是否使用手机播放
            //是否已转化为多个尺寸
            var isMultiSize = 0;


            var $width = $(document).width();
            var $height = $(document).height();
            $("#video_div").width($width / 8 * 7 );
            $("#video_div").height($height / 8 * 7 );
            $("#example_video").width($width / 8 * 7 );
            $("#example_video").height($height / 8 * 7 );
            $('#video_div').css({'margin-left':$width / 16});

            isMultiSize = $('#isMultiSize').val();

            var filePath = "";
            var filePath_720P = "", filePath_480P = "", filePath_360P = "";

            var available_count = 0 ;//视频版本个数
            var isMp4File = true; //判断是否是.mp4类型的文件，.flv的暂时未实现记忆播放功能
            if (isMobile) { //手机端播放
                filePath = '${httpAddress}/${orgDomainName}/${course.courseId}/sv${course.courseId}.mp4';
            } else if (isMultiSize==0) { //PC端播放+视频格式唯一

                var sliceType = $("#sliceType").val();
                if(sliceType == 1){
                    filePath = '${course.enterUrl}';
                }else{
                    filePath = '${httpAddress}/${orgDomainName}/${course.courseId}/${course.enterUrl}';
                }
                //alert(filePath.substring(filePath.length-4));
                if(filePath.substring(filePath.length-4) == ".flv"){ //.flv类型的课程没有实现记忆播放的功能，因为seek方法时常不起作用
                    isMp4File = false;
                }

                //filePath = 'http://159.226.28.53:8080/course/cloud/1488851196744/v1488851196744.flv';
            } else {
                filePath_720P = '${httpAddress}/${orgDomainName}/${course.courseId}/hv${course.courseId}.mp4';
                filePath_480P = '${httpAddress}/${orgDomainName}/${course.courseId}/mv${course.courseId}.mp4';
                filePath_360P = '${httpAddress}/${orgDomainName}/${course.courseId}/sv${course.courseId}.mp4';
                //filePath_720P = 'http://159.226.28.53:8080/course/cloud/1493376332596/hv1493376332596.mp4';
                //filePath_480P = 'http://159.226.28.53:8080/course/cloud/1493376332596/mv1493376332596.mp4';
                //filePath_360P = 'http://159.226.28.53:8080/course/cloud/1493376332596/sv1493376332596.mp4';
                if(getURL(filePath_720P))
                    available_count++;
                if(getURL(filePath_480P))
                    available_count++;
                if(getURL(filePath_360P))
                    available_count++;
            }
            if (available_count>=2) { //PC+视频格式多个,大于等于2个
                switch(available_count){
                    case 2:
                        filePath_720P = getURL(filePath_720P)==true?filePath_720P:filePath_480P;
                        filePath_480P = getURL(filePath_360P)==true?filePath_360P:filePath_480P;
                        panelMenu(2,filePath_360P,filePath_480P,filePath_720P);
                        break;
                    case 3:
                        panelMenu(3,filePath_360P,filePath_480P,filePath_720P);
                        break;
                    default:
                        panelMenu(3,filePath_360P,filePath_480P,filePath_720P);
                        break;
                }

            } else { //手机端或者视频格式唯一
                if(filePath=="")
                    filePath = '${httpAddress}/${orgDomainName}/${course.courseId}/${course.enterUrl}';
                if(!getURL(filePath)){
                    if(getURL(filePath_360P))
                        filePath = filePath_360P;
                    if(getURL(filePath_480P))
                        filePath = filePath_480P;
                    if(getURL(filePath_720P))
                        filePath = filePath_720P;
                }
                videojs("example_video").src([{type: "video/mp4", src: filePath }]);
                init();
            }
        });

        function panelMenu(count,filePath_360P,filePath_480P,filePath_720P){
            videojs("example_video").src([{type: "video/mp4", src: filePath_480P }]);
            init();
            var video = '';
            if(count == 2){
                video =  '<li class="vjs-menu-item" tabindex="-1" role="menuitemcheckbox"  onclick="changeVideo(2,\''+filePath_480P+'\')">标清 </li>'
                    + '<li class="vjs-menu-item" tabindex="-1" role="menuitemcheckbox"  onclick="changeVideo(3,\''+filePath_720P+'\')">高清 </li>'  ;

            }else{
                video =  '<li class="vjs-menu-item" tabindex="-1" role="menuitemcheckbox"  onclick="changeVideo(1,\''+filePath_360P+'\')">流畅</li>'
                    + '<li class="vjs-menu-item" tabindex="-1" role="menuitemcheckbox"  onclick="changeVideo(2,\''+filePath_480P+'\')">标清 </li>'
                    + '<li class="vjs-menu-item" tabindex="-1" role="menuitemcheckbox"  onclick="changeVideo(3,\''+filePath_720P+'\')">高清 </li>' ;
            }

            var videoPanelMenu = $(".vjs-fullscreen-control");
            videoPanelMenu.before('<div class="vjs-subs-caps-button  vjs-menu-button vjs-menu-button-popup vjs-control vjs-button"  aria-live="polite" aria-expanded="false" aria-haspopup="true">'
                + '<div class="vjs-menu" role="presentation">'
                + '<ul class="vjs-menu-content" role="menu">'
                + video
                + '</ul></div>'
                +'  <button class="vjs-subs-caps-button vjs-control vjs-button" type="button" aria-live="polite" title="清晰度切换" aria-disabled="false">'
                +'      <span aria-hidden="true" class="vjs-icon-placeholder"></span><span class="vjs-control-text">清晰度切换</span>'
                +'  </button>'
                +'</div>'
            );

            var obj={tag:false,ctime:0};
            window.obj=obj;
            var myPlayer=videojs.getPlayers()['example_video'];
            myPlayer.on("example_video", function(){

                if(window.obj.tag){
                    videojs("example_video").currentTime(window.obj.ctime);
                    videojs("example_video").play();
                    window.obj.tag=false;
                }
                //视频打点
                var ctime_=videojs("example_video").currentTime().currentTime();
                if(ctime_==60){
                    videojs("example_video").currentTime(ctime_+1);
                    //do something
                }
            });

        }
        function changeVideo(type,filePath){

            var ctime=videojs("example_video").currentTime();

            if(type==1){
                videojs("example_video").src([{type: "video/mp4", src: filePath }]);
                videojs("example_video").play();
            }
            if(type==2){
                videojs("example_video").src([{type: "video/mp4", src: filePath }]);
                videojs("example_video").play();

            }
            if(type==3){
                videojs("example_video").src([{type: "video/mp4",src: filePath }]);
                videojs("example_video").play();
            }
            window.obj.ctime=ctime;
            window.obj.tag=true;
        }
        function init(){
            var captionsCN = $("#captionsChinese").val();
            var captionsEN = $("#captionsEnglish").val();
            //节点
            player = videojs('#example_video',{"example_option":true,"isFullscreen":true, "width": $(window).width()},function(){
                var html = '<track label="中文字幕"     kind="subtitles" srclang="de" src="'+captionsCN+'" default><track label="英文字幕"  kind="subtitles"  srclang="de" src="'+captionsEN+'" >';
                $("#example_video_html5_api").html(html);
            });  //实例化对象

            player.on('loadedmetadata',function(){
                totalTime =  Math.floor(player.duration());
                console.log(parseFloat(totalTime));
            });

            var lastTime = $('#lastLearnTime').val();
            var totalCourseTime = $('#totalCourseTime').val();
            var info = $("#info").val();
            if(info != "admin"){
                if(lastTime>0){ //.mp4格式，且之前观看过

                    if(totalCourseTime>0 && (lastTime-totalCourseTime>=0||totalCourseTime-lastTime<=1)){ //上次已完整观看课程，则重头播放
                        player.currentTime(0);
                        //设置定时任务，定时发送学习进度到后台
                        setInterval("sendLearnTime()", 1000);
                    }else{ //上次未完整观看课程，则提示是否继续上次播放位置播放
                        //console.log("3-0:"+jwplayer('videodiv').getState());
                        jConfirm('是否从上次观看位置继续观看？','提示',function(flag){
                            if(flag){
                                player.currentTime(lastTime);
                            }
                            else{
                                player.currentTime(0);
                            }

                            //设置定时任务，定时发送学习进度到后台
                            setInterval("sendLearnTime()", 1000);
                        });
                    }
                }else{
                    player.currentTime(0);
                    //设置定时任务，定时发送学习进度到后台
                    setInterval("sendLearnTime()", 1000);
                }
            }
        }
        var updateCourseId = ${course.courseId};
        function sendLearnTime(){
            position = Math.floor(player.currentTime());
            if(player.ended()){
                var coursePackageId = $("#coursePackageId").val();
                if(coursePackageId != "" ){
                    var courseId = $("#courseId").val();
                    var msg = "是否播放下一课件？";
                    jConfirm(msg,"提示",function(flag){
                        if(flag){
                            $.ajax({
                                url:"/courseStudy/nextPlayer.do",
                                type:"post",
                                data:{courseId:courseId},
                                dataType:"json",
                                success:function(data){
                                    if(data.status ){
                                        if(data.data != null){
                                            window.location.href='/courseStudy/scormStudy.do?courseId='+data.data+'&train_id=${trainId}&section_id=0';
                                        }else{
                                            jAlert('已经是最后一个','提示');
                                        }
                                    }
                                }

                            });
                        };
                    });

                }
            }
            if(position>0 && totalTime>0){
                //保存学习进度到数据库中，使用ajax方法
                $.ajax({
                    url:"<%=basePath%>/courseStudy/updateCourseStudyLastLearnTime.do",
                    type:"post",
                    data:{'position':position,'totalTime':totalTime,'courseId':updateCourseId},
                    dataType:"text",
                    success:function(data,evt){
                    }
                })
            }


        }
        //右边的章节
        $(function(){

            $("body").addClass("showBoxStatus");

            var $width=$(document).width();
            var $height=$(document).height();


            if (!$.support.leadingWhitespace) {
                $('#contain').css({'left':$width/16,'margin-top':$height/16});
            }else{
                $('#contain').css({'margin-left':$width/16,'margin-top':$height/16});
            }


            var firstCourse = $("#firstCourse").val();
            if(firstCourse == "yes" ){

            }else{
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
                            html += '<ul class="courseList">';
                            for ( var int = 0; int < array.length; int++) {
                                if( array.length > 1 ){
                                    var course = array[int];
                                    if(info == "admin"){
                                        html += '<li class="courseItem" id = "courseItem_'+course.courseId+'"><span class="courseItemIcon"></span><a  href="javascript:void(0);" onclick = "window.location.href=\'/courseStudy/previewStudy.do?courseID='+course.courseId+'&info=admin\'"><span class="courseItemTitle">'+course.courseName+'</span></a></li>';
                                    }else{
                                        html += '<li class="courseItem" id = "courseItem_'+course.courseId+'"><span class="courseItemIcon"></span><a href="javascript:void(0);" onclick = "window.location.href=\'/courseStudy/scormStudy.do?courseId='+course.courseId+'&train_id=${trainId}&section_id=0\'"><span class="courseItemTitle">'+course.courseName+'</span></a></li>';
                                    };
                                };
                            }
                            html += '</ul>';
                        }
                        html += '</li>';
                        $(".sectionList").html(html);
                        var courseId = $("#courseId").val();
                        $("#courseItem_"+courseId).css("background-color","#000");
                    };
                }
            });

        }

        function goScormStudy(courseId,trainId){
            window.location.href = "/courseStudy/scormStudy.do?courseId="+courseId+"&train_id="+trainId+"&section_id=0";
        }


        function errorReminder(text){
            $("#remindDIV p").html(text);
            if(!$.modal.isActive()){
                $("#remindDIV").modal({
                    fadeDuration: 1000,
                    fadeDelay: 0.50
                });
            }
        }
        //是否用手机观看
        function isFromMobile(){
            var mobileAgent = new Array("iphone", "ipod", "ipad", "android", "mobile", "blackberry", "webos", "incognito", "webmate", "bada", "nokia", "lg", "ucweb", "skyfire");

            var browser = navigator.userAgent.toLowerCase();

            var isMobile = false;

            for (var i = 0; i < mobileAgent.length; i++) {
                if (browser.indexOf(mobileAgent[i]) != -1) {
                    isMobile = true;

                    //alert(mobileAgent[i]);
                    break;
                };
            }
            return isMobile;
        }

        function getURL(urlPath) {
            var result = false;
            $.ajax({
                url: "<%=basePath%>/excelTool/isFileExistFromInternet.do",
                type: "POST",
                async: false,
                data:{urlPath:urlPath},
                success:function(data){
                    //alert(data);
                    if(data.data=="true"){
                        result = true;
                    }else{
                        result = false;
                    }
                }
            });
            //  alert(result);
            return result;
        }
        function flashChecker() {
            var hasFlash = 0;//是否安装了flash
            var flashVersion = 0;//flash版本

            if(document.all) {
                var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
                if(swf) {
                    hasFlash = 1;
                    VSwf = swf.GetVariable("$version");
                    flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);
                }
            } else {
                if(navigator.plugins && navigator.plugins.length > 0) {
                    var swf = navigator.plugins["Shockwave Flash"];
                    if(swf) {
                        hasFlash = 1;
                        var words = swf.description.split(" ");
                        for(var i = 0; i < words.length; ++i) {
                            if(isNaN(parseInt(words[i]))) continue;
                            flashVersion = parseInt(words[i]);
                        }
                    }
                }
            }
            return { f: hasFlash, v: flashVersion };
        }

        function openFlash(){

        }
        $(window).load(function(){
            var fls = flashChecker();
            var s = "";
            if(!fls.f) {
                if(confirm("您的浏览器未启用Flash，建议您打开Flash以便正常观看课件。")) {
                    window.location.href = "http://get.adobe.com/cn/flashplayer/";
                }
            }
        });
    </script>
    <script type="text/javascript" src="js/course/videoController.js"></script>
    <script type="text/javascript">

        $(function(){
            var courseId=$("input[name=courseId]").val();
            $.getVideoMarkerInfo({"courseId":courseId}).then(function(data){
                var markerArray=new Array();
                var length=data.length;
                if(length>0){
                    for(var j=0;j<length;j++){
                        markerArray.push({"time":data[j].time,"text":data[j].text});
                    }
                    var markOptions=$.setVideoMarker({
                        markers:markerArray
                    });
                    player.markers(markOptions);
                    $.generateMarkerMap({"markOptions":data});
                }
            },function(data){
                var markOptions=$.setVideoMarker({});
                player.markers(markOptions);
            });
        })
    </script>
    <logic:equal name="actionType" value="setMarker">
        <script type="text/javascript">
            $(function(){
                $.triggerEditVideoMarker({"player":player});
            });
        </script>
    </logic:equal>
    </body>
</html>