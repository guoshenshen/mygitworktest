<%@ page import="com.elearning.common.Constants" %>
<%@ page language="java" pageEncoding="utf-8" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";

    String courseID = (String) session.getAttribute("COURSEID");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>"></base>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="中国科学院,继续教育,线上课程">
    <meta http-equiv="description" content="中国科学院继续教育平台线上课程">
    <title>单一视频课件</title>
    <script id="allmobilize" charset="utf-8"
            src="http://ysp.cnic.cn:30000/c98b9d4a79156fd9bc4515637d3256ca/allmobilize.min.js"></script>
    <style type="text/css">
        body {
            text-align: center;
        }

        .jwlogo {
            display: none;
        }
    </style>
    <link rel="shortcut icon" type="image/x-icon"
          href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="css/jquery-UI/jquery.modal.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="css/jquery-UI/jquery.mCustomScrollbar.css"
          rel="stylesheet" type="text/css"/>
    <link href="css/courseStudy/courseToolBox.css" rel="stylesheet"
          type="text/css"/>
    <script src="js/jwplayer.js"></script>
    <script src="js/jwplayer.html5.js"></script>
    <script type="text/javascript" src="js/jquery-latest.js"></script>
    <script type="text/javascript" src="js/template/templateTool.js"></script>
    <script type="text/javascript" src="js/UI/jquery.modal.min.js"></script>
    <script type="text/javascript" src="js/UI/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/javascript" src="./js/basicUserFunc.js"></script>
    <script type="text/javascript" src="js/UI/jquery.alerts.js"></script>
    <link href="css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
    <link href="css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=basePath %>/js/jquery.uploadifive.js"></script>
    <script type="text/javascript">
        var position = 0;
        var totalTime = 0;

        $(function () {

            $("body").addClass("showBoxStatus");

            var $width = $(document).width();
            var $height = $(document).height();

            if (!$.support.leadingWhitespace) {
                $('#contain').css({'left': $width / 16, 'margin-top': $height / 16});
            } else {
                $('#contain').css({'margin-left': $width / 16, 'margin-top': $height / 16});
            }

            var firstCourse = $("#firstCourse").val();
            if (firstCourse == "yes") {

            } else {
                menuLoadingOptions.loadCourseToolBox(actionAfterToolBoxLoaded());
            }

        });

        function actionAfterToolBoxLoaded() {
            var result = false;
            var courseId = $("#coursePackageId").val();
            if (courseId == "" || courseId == null) {
                courseId = $("#courseId").val();
            }
            var text = "";
            var images = "";
            var trainId = $("#trainId").val();
            var info = $("#info").val();
            $.ajax({
                url: '/onlineCourse/getChapters.do?courseId=' + courseId,
                type: 'get',
                dataType: 'text',
                success: function (data, evt) {
                    var html = '<li class="section">';
                    data = JSON.parse(data);
                    if (data.status) {
                        if ($("#coursePackageId").val() == "") {
                            text = '<div class="courseTitle f-thide" id="courseTitle">${course.courseName}</div>' +
                                '<div class="courseQuality f-thide"><p class="courseInfo" id="evaluateNum">(${course.selectedTimes}人选学)</p></div>' +
                                '<p class="courseInfo f-thide" id="publisher">创建人:${course.maker}</p>';
                            images = '<a href="javascript:void(0);"><img src="${course.pictureURL}" onerror="imgError({type:0,target:this})"/></a>';
                        } else {
                            text = '<div class="courseTitle f-thide" id="courseTitle">${coursePackage.courseName}</div>' +
                                '<div class="courseQuality f-thide"><p class="courseInfo" id="evaluateNum">(${coursePackage.selectedTimes}人选学)</p></div>' +
                                '<p class="courseInfo f-thide" id="publisher">创建人:${coursePackage.maker}</p>';
                            images = '<a href="javascript:void(0);"><img src="${coursePackage.pictureURL}" onerror="imgError({type:0,target:this})"/></a>';
                        }
                        $(".courseDetail").html(text);
                        $(".courseImage").html(images);
                        var chapterList = data.data;
                        for (var i = 0; i < chapterList.length; i++) {
                            var chapter = chapterList[i];
                            var array = chapter.subset;
                            var oneCourse;
                            if (array.length > 0) {
                                oneCourse = array[0].courseId;
                            }
                            if (info == "admin") {
                                html += '<div class="sectionTitle" ><span class="sectionIndex"></span><a href="javascript:void(0);" onclick = "window.location.href=\'/courseStudy/previewStudy.do?courseID=' + oneCourse + '&info=admin\'"><span class="sectionName" title ="' + chapter.chapterName + '" >' + chapter.chapterName + '</span></a></div>';
                            } else {
                                html += '<div class="sectionTitle" ><span class="sectionIndex"></span><a href="javascript:void(0);" onclick = "window.location.href=\'/courseStudy/scormStudy.do?courseId=' + oneCourse + '&train_id=${trainId}&section_id=0\'"><span class="sectionName" title ="' + chapter.chapterName + '" >' + chapter.chapterName + '</span></a></div>';
                            }
                            html += '<ul class="courseList">';
                            for (var int = 0; int < array.length; int++) {
                                if (array.length > 1) {
                                    var course = array[int];
                                    if (info == "admin") {
                                        html += '<li class="courseItem" id = "courseItem_' + course.courseId + '"><span class="courseItemIcon"></span><a  href="javascript:void(0);" onclick = "window.location.href=\'/courseStudy/previewStudy.do?courseID=' + course.courseId + '&info=admin\'"><span class="courseItemTitle">' + course.courseName + '</span></a></li>';
                                    } else {
                                        html += '<li class="courseItem" id = "courseItem_' + course.courseId + '"><span class="courseItemIcon"></span><a href="javascript:void(0);" onclick = "window.location.href=\'/courseStudy/scormStudy.do?courseId=' + course.courseId + '&train_id=${trainId}&section_id=0\'"><span class="courseItemTitle">' + course.courseName + '</span></a></li>';
                                    }
                                }
                            }
                            html += '</ul>';
                        }
                        html += '</li>';
                        $(".sectionList").html(html);

                        var courseId = $("#courseId").val();
                        $("#courseItem_" + courseId).css("background-color", "#000");
                    }
                }
            });

        }

        function goScormStudy(courseId, trainId) {
            window.location.href = "/courseStudy/scormStudy.do?courseId=" + courseId + "&train_id=" + trainId + "&section_id=0";
        }

        function errorReminder(text) {
            $("#remindDIV p").html(text);
            if (!$.modal.isActive()) {
                $("#remindDIV").modal({
                    fadeDuration: 1000,
                    fadeDelay: 0.50
                });
            }
        }

        function isFromMobile() {
            var mobileAgent = new Array("iphone", "ipod", "ipad", "android", "mobile", "blackberry", "webos", "incognito", "webmate", "bada", "nokia", "lg", "ucweb", "skyfire");

            var browser = navigator.userAgent.toLowerCase();

            var isMobile = false;

            for (var i = 0; i < mobileAgent.length; i++) {
                if (browser.indexOf(mobileAgent[i]) != -1) {
                    isMobile = true;
                    break;
                }
            }
            return isMobile;
        }

        function getURL(urlPath) {
            var result = false;
            $.ajax({
                url: "<%=basePath%>/excelTool/isFileExistFromInternet.do",
                type: "POST",
                async: false,
                data: {urlPath: urlPath},
                success: function (data) {
                    if (data.data == "true") {
                        result = true;
                    } else {
                        result = false;
                    }
                }
            });
            return result;
        }

        window.onload = function () {
            var captionsCN = $("#captionsChinese").val();
            var captionsEN = $("#captionsEnglish").val();
            var $width = $(document).width();
            var $height = $(document).height();
            var isMobile = isFromMobile();//是否使用手机播放
            //是否已转化为多个尺寸
            var isMultiSize = 0;
            isMultiSize = $('#isMultiSize').val();

            var filePath = "";
            var filePath_720P = "", filePath_480P = "", filePath_360P = "";

            var available_count = 0;//视频版本个数
            var isMp4File = true; //判断是否是.mp4类型的文件，.flv的暂时未实现记忆播放功能
            if (isMobile) { //手机端播放
                filePath = '${httpAddress}/${orgDomainName}/${course.courseId}/sv${course.courseId}.mp4';
            } else if (isMultiSize == 0) { //PC端播放+视频格式唯一

                var sliceType = $("#sliceType").val();
                if (sliceType == 1) {
                    filePath = '${course.enterUrl}';
                } else {
                    filePath = '${httpAddress}/${orgDomainName}/${course.courseId}/${course.enterUrl}';
                }
                if (filePath.substring(filePath.length - 4) == ".flv") { //.flv类型的课程没有实现记忆播放的功能，因为seek方法时常不起作用
                    isMp4File = false;
                }
            } else {
                filePath_720P = '${httpAddress}/${orgDomainName}/${course.courseId}/hv${course.courseId}.mp4';
                filePath_480P = '${httpAddress}/${orgDomainName}/${course.courseId}/mv${course.courseId}.mp4';
                filePath_360P = '${httpAddress}/${orgDomainName}/${course.courseId}/sv${course.courseId}.mp4';
                if (getURL(filePath_720P))
                    available_count++;
                if (getURL(filePath_480P))
                    available_count++;
                if (getURL(filePath_360P))
                    available_count++;
            }
            var _jwplayer;
            if (available_count >= 2) { //PC+视频格式多个,大于等于2个
                switch (available_count) {
                    case 2:
                        var filePath1 = getURL(filePath_720P) == true ? filePath_720P : filePath_480P;
                        var filePath2 = getURL(filePath_360P) == true ? filePath_360P : filePath_480P;
                        _jwplayer = jwplayer('videodiv')
                            .setup(
                                {
                                    primary: 'flash',
                                    flashplayer: '/js/course/Player.swf',
                                    levels: [
                                        {bitrate: 1000, file: filePath1, label: "高清"},
                                        {bitrate: 600, file: filePath2, label: "标准"}
                                    ],
                                    width: $width / 8 * 7,
                                    height: $height / 8 * 7,
                                    //autostart:true,
                                    provider: "http",
                                    startparam: "start",
                                    tracks: [{
                                        file: captionsCN,
                                        kind: "captions",
                                        label: "Chinese",
                                        "default": true
                                    }, {
                                        file: captionsEN,
                                        label: "English",
                                        kind: "captions"

                                    }],
                                    captions: {back: false, color: 'ffffff', fontsize: 22}
                                });
                        break;
                    case 3:
                        _jwplayer = jwplayer('videodiv')
                            .setup(
                                {
                                    primary: 'flash',
                                    flashplayer: '/js/course/Player.swf',
                                    levels: [
                                        {bitrate: 1000, file: filePath_720P, label: "高清"},
                                        {bitrate: 600, file: filePath_480P, label: "标准"},
                                        {bitrate: 300, file: filePath_360P, label: "流畅"}
                                    ],
                                    width: $width / 8 * 7,
                                    height: $height / 8 * 7,
                                    //autostart:true,
                                    provider: "http",
                                    startparam: "start",
                                    tracks: [{
                                        file: captionsCN,
                                        kind: "captions",
                                        label: "Chinese",
                                        "default": true
                                    }, {
                                        file: captionsEN,
                                        label: "English",
                                        kind: "captions"

                                    }],
                                    captions: {back: false, color: 'ffffff', fontsize: 22}
                                });
                        break;
                    default:
                        _jwplayer = jwplayer('videodiv')
                            .setup(
                                {
                                    primary: 'flash',
                                    flashplayer: '/js/course/Player.swf',
                                    levels: [
                                        {bitrate: 1000, file: filePath_720P, label: "高清"},
                                        {bitrate: 600, file: filePath_480P, label: "标准"},
                                        {bitrate: 300, file: filePath_360P, label: "流畅"}
                                    ],
                                    width: $width / 8 * 7,
                                    height: $height / 8 * 7,
                                    //autostart:true,
                                    provider: "http",
                                    startparam: "start",
                                    tracks: [{
                                        file: captionsCN,
                                        kind: "captions",
                                        label: "Chinese",
                                        "default": true
                                    }, {
                                        file: captionsEN,
                                        label: "English",
                                        kind: "captions"
                                    }],
                                    captions: {back: false, color: 'ffffff', fontsize: 22}
                                });
                        break;
                }

            } else { //手机端或者视频格式唯一
                if (filePath == "")
                    filePath = '${httpAddress}/${orgDomainName}/${course.courseId}/${course.enterUrl}';
                if (!getURL(filePath)) {
                    if (getURL(filePath_360P))
                        filePath = filePath_360P;
                    if (getURL(filePath_480P))
                        filePath = filePath_480P;
                    if (getURL(filePath_720P))
                        filePath = filePath_720P;
                }
                _jwplayer = jwplayer('videodiv')
                    .setup(
                        {
                            primary: 'flash',
                            flashplayer: '/js/course/Player.swf',
                            playlist: [
                                {
                                    file: filePath,
                                    tracks: [{
                                        file: captionsCN,
                                        kind: "captions",
                                        label: "Chinese",
                                        "default": true
                                    }, {
                                        file: captionsEN,
                                        label: "English",
                                        kind: "captions"

                                    }]
                                }],
                            width: $width / 8 * 7,
                            height: $height / 8 * 7,
                            //autostart:true,
                            provider: "http",
                            startparam: "start",
                            tracks: [{
                                file: captionsCN,
                                kind: "captions",
                                label: "Chinese",
                                "default": true
                            }, {
                                file: captionsEN,
                                label: "English",
                                kind: "captions"

                            }],
                            captions: {back: false, color: 'ffffff', fontsize: 22}
                        });
            }

            var lastTime = $('#lastLearnTime').val();
            var totalCourseTime = $('#totalCourseTime').val();
            var info = $("#info").val();

            jwplayer('videodiv').onReady(function () {
                if (info != "admin") {
                    if (lastTime > 0 && isMp4File) { //.mp4格式，且之前观看过

                        if (totalCourseTime > 0 && (lastTime - totalCourseTime >= 0 || totalCourseTime - lastTime <= 1)) { //上次已完整观看课程，则重头播放
                            jwplayer('videodiv').seek(0);
                            //设置定时任务，定时发送学习进度到后台
                            setInterval("sendLearnTime()", 1000);
                        } else { //上次未完整观看课程，则提示是否继续上次播放位置播放
                            //console.log("3-0:"+jwplayer('videodiv').getState());
                            jConfirm('是否从上次观看位置继续观看？', '提示', function (flag) {
                                if (flag) {
                                    //console.log("3-1:"+jwplayer('videodiv').getState());
                                    jwplayer('videodiv').seek(lastTime);
                                    //console.log("3-2:"+jwplayer('videodiv').getState());
                                }
                                else {
                                    jwplayer('videodiv').seek(0);
                                }

                                //设置定时任务，定时发送学习进度到后台
                                setInterval("sendLearnTime()", 1000);
                            })
                        }
                    } else {
                        jwplayer('videodiv').seek(0);
                        //设置定时任务，定时发送学习进度到后台
                        setInterval("sendLearnTime()", 1000);
                    }
                }
            })
            jwplayer('videodiv').onPlaylistComplete(function () {
                var coursePackageId = $("#coursePackageId").val();
                if (coursePackageId != "") {
                    var courseId = $("#courseId").val();
                    var msg = "是否播放下一课件？";
                    jConfirm(msg, "提示", function (flag) {
                        if (flag) {
                            $.ajax({
                                url: "/courseStudy/nextPlayer.do",
                                type: "post",
                                data: {courseId: courseId},
                                dataType: "json",
                                success: function (data) {
                                    if (data.status ) {
                                        if (data.data != null) {
                                            window.location.href = '/courseStudy/scormStudy.do?courseId=' + data.data + '&train_id=${trainId}&section_id=0'
                                        } else {
                                            jAlert('已经是最后一个', '提示');
                                        }
                                    }
                                }

                            });
                        }
                    });

                }
            });
        }


        function sendLearnTime() {

            jwplayer('videodiv').onTime(function (e) {
                //jwplayer('videodiv').getDuration()格式如下：158.0176643... 处理时需要格式化为整数
                //注意：e.position = jwplayer('videodiv').getPosition(); e.duration = jwplayer('videodiv').getDuration();注意千万不能写错，否则不向下继续执行
                position = Math.floor(e.position);
                totalTime = Math.floor(e.duration);

            });
            if (position > 0 && totalTime > 0) {
                //保存学习进度到数据库中，使用ajax方法
                $.ajax({
                    url: "<%=basePath%>/courseStudy/updateCourseStudyLastLearnTime.do",
                    type: "post",
                    data: {'position': position, 'totalTime': totalTime, 'courseId':${course.courseId}},
                    dataType: "text",
                    success: function (data, evt) {
                    }
                })
            }

        }
    </script>
    <script type="text/javascript">
        function flashChecker() {
            var hasFlash = 0;　　　　 //是否安装了flash
            var flashVersion = 0;　　 //flash版本

            if (document.all) {
                var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
                if (swf) {
                    hasFlash = 1;
                    VSwf = swf.GetVariable("$version");
                    flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);
                }
            } else {
                if (navigator.plugins && navigator.plugins.length > 0) {
                    var swf = navigator.plugins["Shockwave Flash"];
                    if (swf) {
                        hasFlash = 1;
                        var words = swf.description.split(" ");
                        for (var i = 0; i < words.length; ++i) {
                            if (isNaN(parseInt(words[i]))) continue;
                            flashVersion = parseInt(words[i]);
                        }
                    }
                }
            }
            return {f: hasFlash, v: flashVersion};
        }
    </script>
</head>


<body class="courseToolPanelParent">

<div id="contain">
    <div id='videodiv'></div>
    <div id='remindDIV' style="display:none">
        <p></p>
    </div>
</div>
<input id="coursePackageId" type='hidden' name="coursePackageId" value="${coursePackage.courseId}"/>
<input id="firstCourse" type='hidden' name="firstCourse" value="${firstCourse}"/>
<input id="courseId" type='hidden' name="courseId" value="${course.courseId}"/>
<input id="sliceType" type='hidden' name="sliceType" value="${course.sliceType}"/>
<input id="trainId" type='hidden' name="trainId" value="${trainId}"/>
<input id="info" type='hidden' name="info" value="${INFO}"/>
<input id="captionsChinese" type='hidden' name="captionsChinese" value="${captionsChinese}"/>
<input id="captionsEnglish" type='hidden' name="captionsEnglish" value="${captionsEnglish}"/>
<input type="hidden" name="isMultiSize" id="isMultiSize"
       value=${course.isMultiSize }/>
<input type="hidden" name="lastLearnTime" id="lastLearnTime"
       value=${lastLearnTime }/>
<input type="hidden" name="totalCourseTime" id="totalCourseTime"
       value=${totalCourseTime }/>
</body>
</html>