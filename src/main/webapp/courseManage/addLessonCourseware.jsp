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
    boolean hasUpload = false;
    if((!"".equals(request.getAttribute("serverFilePath")) && request.getAttribute("serverFilePath") != null )||
            (!"".equals(request.getAttribute("courseUrl"))&& request.getAttribute("courseUrl") != null) ){
        hasUpload = true;
    }
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
    <link rel="stylesheet" type="text/css" href="../css/uploadify.css"/>
    <link rel="stylesheet" type="text/css" href="../css/uploadifive.css"/>
    <style>
        .form-control{
            padding:1px 0px;
        }
        .btn btn-info{
            margin-left:258px;
        }
        .condtion{
            margin-top:15px;
            margin-bottom:15px;
        }
    </style>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody" >
        <div id="trace" class="content"></div>
        <div class="mainContent content" id="courseMaterialEditor">
            <div id="pagebody">
                <div id="location"></div>
                <div id="mainbody" class="valuediv">
                    <c:if test="${train != null }">
                        <div id="funcCon">
                            <div id="conTop">
                                <span class="funcTitle" style="cursor:pointer;" onclick="window.location.href='onlineTraining.do?method=fordetail'">${train.trainName}</span>
                                <div id="funcCheck">
                                    <div id="selectBox">
                                        <div id="selectImg">
                                            <div id="selectText">功能管理</div>
                                        </div>
                                        <ul id="selectMenu">

                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div id="conBottom">
                                <img class="barL" src="./image/arrows4l.png"/>
                                <div class='thirdMenu'>
                                    <ul>
                                    </ul>
                                </div>
                                <img class="barR" src="./image/arrows4r.png"/>
                                <div style="clear:both;"></div>
                                <div class="scrollBarWapper">
                                    <div class="scrollBar">
                                        <div class="barM">
                                            <div class="bar">
                                                <div class="l"></div>
                                                <div class="r"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <ul id="tabs">
                            <li ><a href="mtMixTrainScheduleAction.do?method=listSchedule"><span>培训日程</span></a></li>
                            <li > <a href="mtMixTrainScheduleAction.do?method=listScheduleTeacher"><span>教师信息</span></a></li>
                            <li class="selected"> <a href="#tabs"><span>课程信息</span></a></li>
                            <div class='clr'></div>
                        </ul>
                    </c:if>
                    <div id="contentbody">
                        <div id="mainbody1" class="valuediv">
                            <!-- InstanceBeginEditable name="main" -->

                            <div class="gl_31_2"><span class="gl_11_no">1.基本信息</span><span class="gl_11_yes">2.课件上传</span><span class="gl_11_no">3.相关资料上传</span></div>



                            <form name="courseWare" class="form-horizontal report"   id="courseWare" action="../fileUpload/uploadCourse.do"  enctype="multipart/form-data" method="post">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">课件上传：</label>
                                    <div class="col-sm-6"  >
                                        <input name="coursezipfile" type="file" id="coursezipfile" value="${courseAddress}" class="form-control"     size="50" contenteditable="false" s/>
                                        <span id="spanButtonPlaceholder"></span>( 最大10 MB )
                                        <input type=hidden name="theManifest" />
                                        <input type="hidden" name="theZipFile" />
                                        <input type="hidden" name="courseId" id = "courseId" value="${courseId}" />
                                        <input type="hidden" name="coureName" value="${courseName }" />
                                        <input type="hidden" name="courseTypeId" value="${courseTypeId}" />
                                        <input type="hidden" name="hidFileID" id="hicourseStudyalue" value="" />
                                        <input type="hidden" name="uploadChapter" value="${uploadChapter}" />
                                        <input type="hidden" name="chapterId" id="chapterId" value='${chapterId}'/>
                                        <button  id="btnSubmit1" class="btn-orange-l" onclick="checkValues();return false">上传</button>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">课件存储地址：</label>
                                    <div class="col-sm-6"  >
                                        <input name="courseUrl" type=text id="courseUrl" value="${courseUrl} " class="textInput"    size="48"  />
                                        <button onclick="changeUrl(); return false" class="btn-orange-l">确&nbsp;定</button>
                                        <c:if test="${courseUrl != null }">
                                            <button  id="btnSubmit" class="btn-orange-l" onclick="javascript:openwindowtocenter('/courseStudy/previewStudy.do?courseID=${courseId}','<%=Constants.systemName%>',600,500); return false">预览</button>
                                        </c:if>
                                        <font color='red' name = "uploadCourseSuccess"></font>
                                    </div>
                                </div>

                            </form>
                            <c:if test="${isFirstLevelDirectory != null }">
                                <form class="form-horizontal report" id="coursePicEditor">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label" id="courseImage">上传课程缩略图：</label>
                                        <div class="col-sm-3" >
                                            <input type="hidden" name="courseId" value="${courseId}"  />
                                            <input type="file" name="coursePic" id="coursePicUploader" />
                                            <p id="imgUploadInfo" style="text-align: left;line-height:2em;"></p>
                                            <div id="imgQueue"></div>
                                        </div>
                                        <div class="col-sm-3 coursePicContainer">
                                            <c:if test="${not empty PictureURL}">
                                                <img  class="coursePic"style="width:450px;height:240px;"
                                                      src="${PictureURL}" />
                                            </c:if>
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                        </div>
                        <br/>
                        <div  style="text-align:center" class="condtion">
                            <c:if test="${uploadChapter != null }">
                                <button onclick="javascript:window.location.href =
                                        '../addLessonInfo/intoSectionFragmentEditor.do?chapterId=${chapterId}';
                                        return false" class="btn btn-info">
                                    返&nbsp;&nbsp;回
                                </button>
                                <button onclick="javascript:window.location.href =
                                        '../addLessonInfo/intoSectionFragmentEditor.do?chapterId=${chapterId}';
                                        return false" class="btn btn-info">
                                    完&nbsp;&nbsp;成
                                </button>
                            </c:if>
                            <c:if test="${uploadChapter == null }">
                                <button onclick="javascript:window.location.href =
                                        '../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id }';
                                        return false" class="btn btn-info">
                                    返&nbsp;&nbsp;回
                                </button>
                                <button onclick="javascript:window.location.href =
                                        '../courseType/otherFileUpload.do?serverId=${serverId}&fileSize=${fileSize}&courseID=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id }';
                                        return false" class="btn btn-info">
                                    下一步
                                </button>
                                <input type="hidden" id="train_Id" value="${train.id}"/>
                            </c:if>
                        </div>
                    </div>
                    </div>
                    <div class="clr"/>
                </div><!-- content body -->
            </div><!-- mainbody -->
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
<script type="text/javascript" src="../js/jquery.uploadify.js"></script>
<script type="text/javascript" src="../js/jquery.uploadifive.js"></script>
<script type="text/javascript" src="../js/course/courseEditor.js"></script>
<script type="text/javascript" src="../js/dojojs/dojo.js"></script>
<script type="text/javascript" >
    dojo.require("dojo.widget.*");
</script>
<script type="text/javascript">

    function insertOrUpdate(courseId,courseTypeId){
        window.location.href = "../addLessonInfo/updateCourse.do?courseId="+courseId+"&courseTypeId="+courseTypeId+"";
    }

    function changeUrl()
    {
        var courseId = $('#courseId').val();
        var enterUrl = $("#courseUrl").val();
        if(enterUrl == "")
        {
            $.Ntip({'content':"课件存储地址不能为空!",})
            document.getElementById("courseUrl").focus();
            return ;
        }
        $.ajax({
            url:'../fileUpload/updateCourseUrl.do',
            method:"post",
            dataType:"json",
            data:{courseId:courseId,enterUrl:enterUrl},
            success:function(data){
                if( data.status ){
                    $("font[name='uploadCourseSuccess']").html(data.msg);
                }
                else{
                    $.tips(data.msg,"系统提示");
                }
            }
        })
    }
    function checkValues()
    {
        if(document.getElementById("coursezipfile").value == "")
        {
            $.Ntip({'content':"课件上传地址不能为空!",})
            document.getElementById("coursezipfile").focus();
            return ;
        }
        return document.getElementById("courseWare").submit();
    }

    function checkPicValues()
    {
        if(document.getElementById("pictureFile").value == "")
        {
            $.Ntip({'content':"缩略图上传地址不能为空!",})
            document.getElementById("pictureFile").focus();
            return ;
        }else{
            var picName = document.getElementById("pictureFile").value;
            var pickind = picName.substring(picName.lastIndexOf(".")+1).toLowerCase();
            if(pickind != "jpg" && pickind != "jpeg" && pickind != "gif" && pickind != "png" && pickind != "bmp")
            {
                $.Ntip({'content':"上传图片只能是jpg,jpeg,gif,png,bmp格式!",})
                document.getElementById("pictureFile").focus();
                return ;
            }
        }
        if(document.getElementById("courseUrl")!=null)
            document.getElementById("courseUrl1").value=document.getElementById("courseUrl").value;
        return document.getElementById("coursePic").submit();

    }





</script>
</body>
</html>
