<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="中国科学院;继续教育;网络课程;空间"  />
    <title>${course.courseName}</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
    <link href="../css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="../css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css" />
    <link href="../css/jquery-UI/remodal.css" rel="stylesheet" type="text/css" />
    <link href="../css/skinCss/skin.css" rel="stylesheet" type="text/css" />
    <link href="../css/skinCss/basicStyle.css" rel="stylesheet" type="text/css" />
    <link href="../css/skinCss/contentStyle.css" rel="stylesheet" type="text/css" />
    <link href="../css/jquery-UI/jquery.infobox.css" rel="stylesheet" type="text/css"/>
    <link href="../css/login/login.css" rel="stylesheet" type="text/css"/>
    <link href="../css/skinCss/portalStyle.css" rel="stylesheet" type="text/css"/>
    <link href="../css/skinCss/studentStyle.css" rel="stylesheet" type="text/css" />
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/jquery-UI/ft-carousel.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .jwlogo{
            display:none;
        }
        li , a{
            list-style: none;
            text-decoration: none;
            color: black;
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
        }

        .directory{
            width: 900px;
            margin:0px auto;
        }
        .ul{
            width: 880px;
            margin: 0px auto;
        }
        .ul li{
            line-height: 40px;
            background: white;
            font-size: 14;
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
            padding-left: 10px;
            padding-right:10px;
        }
        .course:hover{
            border-bottom:1px solid #55B929;

        }
        .course a:hover{
            color:#55B929;
        }

        .ul .title{
            background: #FAFAFA;
            font-weight: bold;
            width: 100%;
            height: 40px;
        }
        .title div {
            width: 80%;
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
        }
        .time{
            text-align: right;
            float: right;
        }
        .fa-caret-square-o-right{
            display:inline-block;
            color:#B3B3B3;
            margin-right:4px;
        }
        .example {width: 265px;height: 200px;font-size: 40px;text-align: center;margin: 0px auto;background-color: #464576;}
        .carousel-item{line-height: 20px;color: #fff;font-family:  Arial Black}
        ul,
        ol,
        li,
        div {
            margin: 0;
            padding: 0;
        }

        ul,
        ol {
            list-style: none;
        }


        .fa-chevron-left , .fa-chevron-right{
            color:white;
        }

        .courseTitle{

            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
            margin: 0px auto;
            font-size: 15px;
            color: #fff;
            height: 20px;
            margin-top: -19px;
            width: 150px;
        }

        .ft-carousel .carousel-indicators{
            display:none;
        }

    </style>
    <script type='text/javascript'>

        function imgError(imgObj){
            if(imgObj.id=='coursePicUrl'){
                imgObj.src="./image/lazyload/courseDefault_1.jpg";
            }
            else if(imgObj.id=='sponsorPicUrl'){
                imgObj.src="./image/headPic/defaultOrg.png";
            }
        }
    </script>
</head>
<body class="student">
<input type="hidden" id="isVideoCourse" value="${isVideoCourse}"/>
<input type="hidden" id="mobilePlayAddress" value="${course.mobilePlayAddress}"/>
<div  class="mainContent"  id="portalStyle">
    <div id="pagebody" class="courseMain" style="padding-bottom:20px;margin-bottom:0px;">
        <div id="contentInfo" class="contentPart">
            <div id="courseDetailInfo" class="notShow">
                <input id="courseId" type='hidden' name="courseId" value="${course.courseId}" />
                <input type='hidden' name="courseName" value="${course.courseName}" />
                <input type="hidden" name="userID"  id="userID" value="${operatorId}"></input>
                <input type="hidden" name="train_ID"  id="train_ID" value="${trainId}"></input>
                <input type="hidden" name="pubStatus"  id="pubStatus" value="${course.pubStatus}"></input>
                <input type="hidden" name="courseID"  id="courseID" value="${bookId}"></input>
                <input type="hidden" name="section_id" value="0"></input>
                <input type="hidden" name="courseStudyFlag" id="courseStudyFlag" value="${courseStudyFlag}"></input>
                <input type="hidden" name="enterUrl" value="${course.enterUrl}"></input>
                <input type="hidden" name="isCanGenerateSelfPaper" id="isCanGenerateSelfPaper" value="0"></input>
                <input type="hidden" name="isCoursePackage" id="isCoursePackage" value="${course.isCoursePackage}"/>
            </div>


            <div id="courseCarryInfo" class="notShow">
                <input id="courseId" type='hidden' name="courseId" value="${carryCourse.courseId}" />
                <input type='hidden' name="courseName" value="${carryCourse.courseName}" />
                <input type="hidden" name="userID"  id="userID" value="${operatorId}"></input>
                <input type="hidden" name="train_ID"  id="train_ID" value="${trainId}"></input>
                <input type="hidden" name="pubStatus"  id="pubStatus" value="${carryCourse.pubStatus}"></input>
                <input type="hidden" name="courseID"  id="courseID" value="${bookId}"></input>
                <input type="hidden" name="section_id" value="0"></input>
                <input type="hidden" name="courseStudyFlag" id="courseStudyFlag" value="${courseStudyFlag}"></input>
                <input type="hidden" name="enterUrl" value="${carryCourse.enterUrl}"></input>
                <input type="hidden" name="isCanGenerateSelfPaper" id="isCanGenerateSelfPaper" value="0"></input>
            </div>

            <div id="firsetCourseInfo" class="notShow">
                <input id="courseId" type='hidden' name="courseId" value="${firstWatch.courseId}" />
                <input type='hidden' name="courseName" value="${firstWatch.courseName}" />
                <input type="hidden" name="userID"  id="userID" value="${operatorId}"></input>
                <input type="hidden" name="train_ID"  id="train_ID" value="${trainId}"></input>
                <input type="hidden" name="pubStatus"  id="pubStatus" value="${firstWatch.pubStatus}"></input>
                <input type="hidden" name="courseID"  id="courseID" value="${bookId}"></input>
                <input type="hidden" name="section_id" value="0"></input>
                <input type="hidden" name="courseStudyFlag" id="courseStudyFlag" value="${courseStudyFlag}"></input>
                <input type="hidden" name="enterUrl" value="${firstWatch.enterUrl}"></input>
                <input type="hidden" name="isCanGenerateSelfPaper" id="isCanGenerateSelfPaper" value="0"></input>
            </div>



            <div id="currentOperatorInfo" class="notShow">
                <c:if test="${currentOperator != null }">
                    <input type='hidden' name="operatorId" value="${currentOperator.operatorId}" />
                    <input type='hidden' name="operatorName" value="${currentOperator.operatorName}" />
                    <input type='hidden' name="headPic" value="${headPic}" />
                    <input type='hidden' name="gender" value="${gender}" />
                </c:if>
            </div>
            <div class="courseBasicInfo">
                <div class="coursePic mediaBase" id="courseImgDiv">
                    <div class="courseTag" style="z-index: 2">
                        <span title='点击查看相同领域课程' onclick="javascript:window.open('onlineStudy.do?method=intoFrame&resourceType=${course.expertAreaId}','_blank')">
                            ${course.expertAreas}
                        </span>
                    </div>
                    <c:if test="${isVideoCourse == 1}" >
                        <div id='videodiv' style="z-index: 1"></div>
                        <c:if test="${isPackage == 0 }">
                            <div class="courseBottomTag">您可以试看3分钟</div>
                        </c:if>
                    </c:if>

                    <c:if test="${isVideoCourse == 0 }">
                        <img src="${course.pictureUrl}" id="coursePicUrl" onerror="imgError({type:0,target:this})">
                    </c:if >

                </div>
                <div class="courseDetail">
                    <div class="courseAttr title" title="${course.courseName}">${course.courseName}</div>
                    <div class="courseAttr">
                        <span class="mini">作者：${course.creator }</span>
                        <span class="mini">选学人次:${totalLearnedMan}</span>
                        <span class="mini">学时：${course.classHour }小时</span>
                    </div>
                    <div class="extraInfo">
                        <c:if test="${course.orgName != null  }" ><div class="courseAttr"><span>创建单位：${course.orgName }</span></div></c:if>
                        <c:if test="${course.produceOrgName != null}"><div class="courseAttr"><span>制作单位：${course.produceOrgName }</span></div></c:if>
                        <c:if test="${course.fundingOrgName != null }" ><div class="courseAttr"><span>资助单位：${course.fundingOrgName }</span></div></c:if>
                    </div>
                    <c:if test="${course.keyWords != null }" ><div class="courseAttr"><span class="keytags">标签：${course.keyWords }</span></div></c:if>
                    <div class="coursetip">
                        （如无法观看课件,请重装Adobe Flash插件并重启浏览器;&nbsp;&nbsp;对于老版本三分屏课件,请<a href="download/VGAPlayer.zip">点击此处</a>下载安装VGAPlayer控件）
                    </div>
                    <div class="learnButton">
                        <span class="Button notShow">&nbsp;&nbsp;</span>
                    </div>
                </div>
                <div style="clear:both"></div>
            </div>
        </div>
        <c:if test="${ studyProgress!=null }">
            <div style="height:15px;"></div>
            <div id="progressInfo" class="contentPart">
                <div class="barAll" >
                    <div class='barFinished progress-bar-striped'>
                        <em class="tip"></em>
                    </div>
                </div>
                <span class="Button orangeButton">继续学习</span>
                <input type='hidden' name='studyProgress' value='${studyProgress}' />
            </div>
        </c:if>
        <div style="height:15px;"></div>
        <div id="studyEffect">
            <div class="leftpart">

                <div id="commentMainBody" class="leftInfo contentPart">
                    <div class="partTitle">
                        <c:if test="${course.mainContent != null }" >
                            <a id="contentTab" href="javascript:goContent()">课程内容</a>
                        </c:if>
                        <a id="discussTab" href="javascript:goComment()">课程评论</a>
                        <c:if test="${course.isCoursePackage == 1 }" >
                            <a id="catalogTitle" href="javascript:goCataLog()">目录</a>
                        </c:if>
                        <a id = "relatedData" href = "javascript:goData()" style = "display: none;">相关资料</a>
                        <a id="selfExamTab" style = "display: none;">课程自测考试</a>

                    </div>
                    <div id="comment" class="commentContainer container">
                        <div id="discussEditorContainer" class="discussEditorContainer hoverContainer"></div>
                        <div id="discussReplyContainer" class="discussReplyContainer hoverContainer"></div>
                    </div>
                    <div id = "data" style="display:none" ></div>
                    <div id="catalog" class="commentContainer container" style="display:none">
                    </div>
                    <c:if test="${course.mainContent != null }" >
                        <div class="courseContent container">${course.mainContent}</div>
                    </c:if>

                    <div class="courseSelfExam container">
                        <div>
                            <c:if test="${maxSelfTestScore == null}">
                                <p style="line-height:40px;">您尚未进行课程自测考试</p>
                            </c:if >
                            <c:if test="${maxSelfTestScore != null }">
                                <p style="line-height:40px;">您目前的自测最高分为：<span style="font-weight:bold;color:#008EFF;">${maxSelfTestScore}</span> 分</p>
                            </c:if>
                            <p>
                                <a href="javascript:applyTestExam(${bookId});" style="cursor:pointer;float:left;font-weight:bold;color:#008EFF;" class="btn-orange-l">
                                    <span class="btn-orange-r">进入自测考试</span>
                                </a>
                            </p>
                        </div>
                    </div>
                </div>
                <div style="clear:both"></div>
            </div>
            <div id="sponsor" class="rightpart rightInfo  contentPart">
                <div class="partTitle">
                    <h2>发布信息</h2>
                </div>
                <div class="sponsorInfo ">
                    <div class="sponsorInfoAttr">
                        <c:if test="${publishOrg != null }">
                            <div class="discuss-operator u-userCardTrigger">
                                <img id="sponsorPicUrl" class="lazy headPic orgPic infobox_headPic" src="
							<c:if test="${publishOrg.logo != null }" >${publishOrg.logo}</c:if>
							<c:if test="${publishOrg.logo == null }" >../image/headPic/defaultOrg.png</c:if>
							" onerror="imgError({type:'7',target:this});"  />
                                <input type='hidden' class="infobox_userId" value='${publishOrg.orgID}'/>
                                <input type='hidden' class="infobox_userName" value='${publishOrg.orgName}'/>
                                <input type='hidden' class="infobox_itemType" value='0'/>
                            </div>
                            <span>${publishOrg.orgName}</span>
                        </c:if>
                        <span>发布时间：${course.shareDateStr}</span>
                    </div>
                    <div style="clear:both"></div>
                </div>
            </div>
            <div style="height:15px;" class=" rightpart"></div>
            <div id="relatedCourseList" class="rightpart rightInfo  contentPart" style="display:none">
                <div class="partTitle">
                    <h2></h2>
                </div>
                <div style="width:265px;height:180px;" class="ft-carousel">
                    <ul class="carousel-inner">
                    </ul>
                </div>
            </div>
            <div style="height:15px;" class=" rightpart"></div>
            <div id="score" class="rightpart rightInfo  contentPart">
                <div class="partTitle">
                    <h2>课程评价</h2>
                </div>
                <div class="ratingContainer"></div>
                <div class="overallEvaluation "></div>
            </div>
            <div style="height:15px;" class="rightpart"></div>
            <div id="studentOfCourse" class="rightpart rightInfo contentPart hoverContainer">
                <div class="partTitle">
                    <h2>选学成员</h2>
                </div>
                <div class="userHeadList mini">
                    <div class='beforeLoading'>加载中...</div>
                </div>
                <div class="userCount"></div>
            </div>
            <div style="clear:both"></div>
        </div>
    </div>
    <div id="foot">
    </div>

    <!--弹出框-->
    <div class="remodal" id="courseOffLineWin" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
        <button data-remodal-action="close" class="remodal-close" ></button>
        <div class="scrollable-wrapper">
            <input type = "hidden" id="courseInfo" value = "${info}"/>
            <div class="info"></div>
        </div>
    </div>


    <div class="remodal normal" id="courseSelectWindow" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
        <div class="box">
            <button data-remodal-action="close" class="remodal-close" ></button>
        </div>
        <div class="courseSelectFrame" >
            <div class="scrollable-wrapper">
                <table>
                    <tr >
                        <th >课程名称</th>
                        <td  colspan="3">${course.courseName}</td>
                    </tr>
                    <tr>
                        <th >创建人</th>
                        <td >${course.maker}</td>
                        <th>创建日期</th>
                        <td>${course.createDateStr}</td>
                    </tr>
                    <tr>
                        <th >作者</th>
                        <td >${course.creator}</td>
                        <th >学时</th>
                        <td >${course.classHour}小时</td>
                    </tr>
                    <tr >
                        <th >课件类型</th>
                        <td >
                            <c:if test="${course.sliceType eq '1'}">单一网址课件</c:if>
                            <c:if test="${course.sliceType eq '2'}">SCORM课件</c:if>
                            <c:if test="${course.sliceType eq '3'}">视频、文档课件</c:if>
                        </td>
                        <th >所属领域</th>
                        <td>${course.expertAreas}</td>
                    </tr>
                </table>
                <br/>
                <div class="btnContainer">
                    <div class="tips"></div>
                    <div style="float:right">
                        <span class="redButton Button"  class="btn-blue-l">确认选课</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden"  id  = "imagePic" value = "${imagePic}" >
    <div class="remodal" id="previewTimeUp" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
        <button data-remodal-action="close" class="remodal-close" ></button>
        <div class="scrollable-wrapper">
            <div class="info" id="previewTimeUpReminder"></div>
        </div>
    </div>
</div>
<div id="simpleNavBar" style="position:fixed;top:0px;"></div>
<script type="text/javascript">
    function getCrumbInfo(){
        return "10";
    }
</script>
<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js" ></script>
<script type="text/javascript" src="../js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="../js/nav/umenu.js"></script>
<script type="text/javascript" src="../js/UI/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="../js/UI/basicWidget.js"></script>
<script type="text/javascript" src="../js/UI/jquery.infobox.js"></script>
<script type="text/javascript" src="../js/public/jquery.discuss.js"></script>
<script type="text/javascript" src="../js/UI/remodal.min.js"></script>
<script type="text/javascript" src="../js/course/courseDetailInfo.js"></script>
<script type="text/javascript" src="../js/ft-carousel.min.js"></script>
<script type="text/javascript" src="/js/basicUserFunc.js"></script>
<script type="text/javascript" src="/js/login/login.js"></script>
<script src="../js/jwplayer.js"></script>
<script src="../js/jwplayer.html5.js"></script>
<script type="text/javascript">
</script>
<script type="text/javascript">
    $(function(){

        relatedCourse();
        relatedData();
        var courseId = $('#courseID').val();
        var info = $("#courseInfo").val();
        if(info == "admin"){
            $("#discussTab").hide();
            $("#comment").hide();
            $("#catalog").hide();
        }
        var pic =$("#imagePic").val();
        if(pic !=null && pic != ""){
            $("#headSculpture").attr("src",pic);
        }
        var isVideoCourse = $('#isVideoCourse').val();
        //var mobilePlayAddress = $('#mobilePlayAddress').val();
        if(isVideoCourse == 1){
            $("#courseImgDiv").hover(function(){
                $(".courseBottomTag").css("top","190px");
            },function(){
                $(".courseBottomTag").css("top","210px");
            });
            //filePath = mobilePlayAddress;
            var _jwplayer;
            _jwplayer = jwplayer('videodiv')
                .setup(
                    {
                        primary: 'flash',
                        controlbar:"none",
                        flashplayer : '/js/course/Player.swf',
                        playlist : [ {
                            image : '${course.pictureUrl}',
                            file : '${course.mobilePlayAddress}'
                        } ],
                        width : 450,
                        height : 240,
                        autostart:false,
                        provider: "http",
                        startparam: "start"
                    });
            var isCoursePackage = $("#isCoursePackage").val();
            if(isCoursePackage != 1){
                //试看时长，单位：s,默认试看3分钟
                var previewTime = 60*3;
                jwplayer('videodiv').onTime(function(e) {
                    //jwplayer('videodiv').getDuration()格式如下：158.0176643... 处理时需要格式化为整数
                    //注意：e.position = jwplayer('videodiv').getPosition(); e.duration = jwplayer('videodiv').getDuration();注意千万不能写错，否则不向下继续执行
                    position = Math.floor(e.position);
                    totalTime = Math.floor(e.duration);
                    if(totalTime>0){
                        if(totalTime - previewTime <= 0){
                            previewTime = totalTime;
                        }
                        if(position - previewTime >= 0){
                            jwplayer('videodiv').stop();
                            jwplayer('videodiv').setFullscreen(false);
                            $("#previewTimeUp").remodal({}).open();
                        }
                    }
                });
            }

        }



        var courseStudyFlag = $('#courseStudyFlag').val();

        var operatorId = $('#userID').val();
/*        if(operatorId==0){
            $("#selfExamTab").hide();
        }else{
            var classifySign = 1;//表示课程自测
            $.ajax({
                url:'paperManage.do?method=toCreateSelfTestPaper',
                type:'post',
                data:{classifyId:courseId,classifySign:classifySign},
                dataType:'text',
                success:function(data,evt){
                    if(data!=null&&data!=""){
                        $("#selfExamTab").hide();
                        $("#isCanGenerateSelfPaper").val(0);
                    }else{
                        $("#isCanGenerateSelfPaper").val(1);
                        if(courseStudyFlag == 0){
                            $("#selfExamTab").hide();
                        }
                    }
                }
            })
        }*/

        initComment();
    })
    function goCataLog(){

        $("#comment").hide();
        $("#catalog").show();
        $("#data").hide();
    }
    function goComment() {
        $("#comment").show();
        $("#data").hide();
        $("#catalog").hide();
    }

    function goContent() {
        $("#comment").hide();
        $("#catalog").hide();
        $("#data").hide();
    }
    function goData(){
        $("#comment").hide();
        $("#catalog").hide();
        $("#data").show();
    }
    // 初始化目录
    function initComment() {
        var info = $("#courseInfo").val();
        $.ajax({
            url:'/onlineCourse/getChapters.do?courseId=' + $("#courseId").val(),
            type:'get',
            dataType:'text',
            success:function(data,evt) {
                var html = '<div class="directory">';
                data = JSON.parse(data);
                if(data.status) {
                    var chapterList = data.data;
                    for ( var i = 0; i < chapterList.length; i++) {
                        var chapter = chapterList[i];
                        var array = chapter.subset;
                        var oneCourse ;
                        if(array.length > 1){
                            oneCourse = array[0].courseId;
                            if(info == "admin"){
                                html += '<ul class="ul"><li class="title"><div><a href="javascript:void(0)" onclick = "previewStudy('+oneCourse+')">'+chapter.chapterName+'</a></div></li>';
                            }else{
                                html += '<ul class="ul"><li class="title"><div><a href="javascript:void(0)" onclick = "secondCourse('+oneCourse+')"  >'+chapter.chapterName+'</a></div></li>';

                            }
                            for ( var int = 0; int < array.length; int++) {
                                if(array.length > 1){
                                    var course = array[int];
                                    if(info == "admin"){
                                        html += '<li class="course"><a href="javascript:void(0)" onclick = "previewStudy('+course.courseId+')"><span>'+course.courseName+'</span><span class="time" ><i class="fa fa-caret-square-o-right"></i>'+course.classHour+'小时</span></a></li>'
                                    }else{
                                        html += '<li class="course"><a href="javascript:void(0)" onclick = "secondCourse('+course.courseId+')"  ><span>'+course.courseName+'</span><span class="time" ><i class="fa fa-caret-square-o-right"></i>'+course.classHour+'小时'+course.studyProgress+'%</span></a></li>'
                                    }
                                }
                            }
                        }else if(array.length == 1 ){
                            oneCourse = array[0].courseId;
                            if(info == "admin"){
                                /* html += '<ul class="ul"><li class="title"><div><a href="courseStudy.do?method=previewStudy&courseID='+oneCourse+'&info=admin" target="courseStudyWindow" >'+chapter.chapterName+'</a></div></li>'; */
                                html += '<ul class="ul"><li class="title"><a href="javascript:void(0)" onclick = "previewStudy('+oneCourse+')">'+chapter.chapterName+'<span class="time" ><i class="fa fa-caret-square-o-right"></i>'+array[0].classHour+'小时</span></a></li>';
                            }else{
                                html += '<ul class="ul"><li class="title"><a href="javascript:void(0)" onclick = "secondCourse('+oneCourse+')"  >'+chapter.chapterName+'<span class="time" ><i class="fa fa-caret-square-o-right"></i>'+array[0].classHour+'小时'+array[0].studyProgress+'%</span></a></li>';

                            }
                        }else{
                            html += '<ul class="ul"><li class="title"><div><span style="color:red;">'+chapter.chapterName+'(未添加课程)</span></div></li>';
                        }
                        html += '</ul>';
                    }
                    html += '</div>';
                    $("#catalog").html(html);
                }
            }
        });
    }
    function secondCourse(courseId){
        var courseStudyFlag = $("#courseStudyFlag").val();
        if( courseStudyFlag == 0 ){
            $.Ntip({'content':"请选学后播放"	});
        }else{
            openLink("/courseStudy/scormStudy.do",{"courseId":courseId,"train_id":${trainId},"section_id":0},{'target':'courseStudyWindow'});
        }
    }


    function previewStudy(courseId){
        openLink("/courseStudy/previewStudy.do",{"courseID":courseId,"info":"admin"},{'target':'courseStudyWindow'});
    }


    function relatedCourse(){
        var courseId = $('#courseID').val();
        var info = $("#courseInfo").val();
        $.ajax({
            url:'/addLessonInfo/getCourseRelevantCourse.do',
            type:'post',
            data:{originalCourseId:courseId},
            dataType:'json',
            success:function(data){
                if(data.status){
                    var dataList=data.data;
                    var html = '';
                    if(dataList.length == 0){
                    }else{
                        $("#relatedCourse").show();
                        html += '<ul class="carousel-inner" >'
                        for ( var int = 0; int < dataList.length; int++) {
                            var course = dataList[int];
                            if(info == "admin"){
                                html += '<li class="carousel-item" style = "width :265px">'
                                html += '<a href = "/onlineCourse/detail.do?bookId='+course.courseId+'&info=admin" target="view_frame" ><img src="'+course.pictureUrl+'"  onerror="imgError({type:0,target:this})" />';
                                html += '<div class = "courseTitle"  title = "'+course.courseName+'">'+course.courseName+'</div>'
                                html += '</a></li>';
                            }else{
                                html += '<li class="carousel-item" style = "width :265px">';
                                html += '<a href = "/onlineCourse/detail.do?bookId='+course.courseId+'" target="view_frame"><img src="'+course.pictureUrl+'"  onerror="imgError({type:0,target:this})"/>';

                            }

                        }
                        html += '</ul>'
                        $("#carousel_3").html(html);
                        $("#carousel_3").FtCarousel({
                            index: 0,
                            auto: true,
                            time: 3000,
                            indicators: false,
                            buttons: true
                        });
                    }
                }
            }
        })
    }

    function relatedData(){
        var courseId = $('#courseID').val();
        $.ajax({
            url:'/onlineCourse/getLectureFile.do',
            type:'post',
            data:{courseId:courseId},
            dataType:'json',
            success:function(data){
                if(data.status){
                    var dataList = data.data;
                    var html = '<div class="directory">';
                    if(dataList.length > 0 ){
                        $("#relatedData").show();
                        for(var int = 0; int < dataList.length; int++){
                            var relate = dataList[int];
                            html += '<ul class="ul"><li class="title"><div><a href = "'+relate.lectureUrl+'" target="_blank">'+relate.lectureName+'</a></div></li></ul>'
                        }
                    }
                    html += '</div>'
                    $("#data").html(html);
                }
            }
        })
    }

</script>
</body>
</html>