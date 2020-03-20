<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants"%>
<%@ page import="com.elearning.util.PropertiesUtil" %>
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
    String newFileName = ""+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_by_"
            +((EosOperator)session.getAttribute(Constants.USERINFO_KEY)).getOperatorId();//new upload file name
    boolean hasUpload = false;
    if((!"".equals(request.getAttribute("serverFilePath")) && request.getAttribute("serverFilePath") != null )||
            (!"".equals(request.getAttribute("courseUrl"))&& request.getAttribute("courseUrl") != null) ){
        hasUpload = true;
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
        .gl_31_2 {
            margin:0 auto;
            width:700px;
            height:20px;
            background: url(../image/images_gl/3-2.jpg) no-repeat;
            padding-top:3px;
            padding-left:10px;
            font-family: "宋体";
            font-size: 12px;
            color: #1a5e7e;
            line-height: 150%;
            text-decoration:none;
            margin-bottom:25px;
            margin-top:15px;
        }
        .gl_11_no
        {
            color:#999999;
            font-size:12px;
            font-weight:normal;
            display:-moz-inline-box;
            display:inline-block;
            width:135px;
            margin:0;
            text-align:center;
            padding:0;
        }

        .gl_11_yes
        {
            color:#333333;
            font-size:14px;
            font-weight:bold;
            display:-moz-inline-box;
            display:inline-block;
            width:135px;
            margin:0;
            text-align:center;
            padding:0;
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
                <div id="topbody">
                    <div id="navbar">
                        <div id="rolebar">
                            <div id="_juese"></div>
                            <ul>
                            </ul>
                        </div>
                    </div>
                    <div id='org_Logo'></div>
                    <div id="menubody">
                        <ul id="menu1"></ul>
                    </div>
                </div>
                <div id="mainbody">
                    <c:if test="${train != null }">
                        <div id="funcCon">
                            <div id="conTop">
                                <span class="funcTitle" style="cursor:pointer;" onclick="window.location.href=
                                                        'onlineTraining.do?method=fordetail'">${train.trainName}</span>
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
                            </div></div>

                        <ul id="tabs">
                            <li ><a href="mtMixTrainScheduleAction.do?method=listSchedule"><span>培训日程</span></a></li>
                            <li > <a href="mtMixTrainScheduleAction.do?method=listScheduleTeacher"><span>教师信息</span></a></li>
                            <li class="selected"> <a href="#tabs"><span>课程信息</span></a></li>
                        </ul>
                        <div class='clr'></div>
                    </c:if>
                    <div id="contentbody">
                        <div id="mainbody1" class="valuediv">
                            <div class="gl_31_2"><span class="gl_11_no">1.基本信息</span><span class="gl_11_yes">
                                2.课件上传</span><span class="gl_11_no">3.相关资料上传</span></div>
                            <div class="content_02">
                                <table  class="table0" cellspacing="0" width="90%" align="center" cellpadding="0" id="courseUploader">
                                    <tr>
                                        <td align="center">
                                            <br/>
                                            <div id="fileQueue"></div>
                                            <input type="file" name="course" id="file_upload"/>
                                            <div id="file_upload_message">
                                            </div>
                                        </td>
                                    </tr>
                                </table><!-- 上传控件 -->
                                <div id="file_upload_attention">
                                    <table  class="table0" cellspacing="0" width="90%" align="center" cellpadding="0">
                                        <tr>
                                            <td>
                                                <span style="font-size:12px;color:#5A5A5A;"><strong>说明：</strong></span><br/>
                                                <span style="font-size:12px;color:#5A5A5A;">1.支持压缩格式：*.zip&nbsp;&nbsp;
                                                    <a href="javascript:void(0);" onclick="howToZipFile()">如何打包？</a></span><br/>
                                                <span style="font-size:12px;color:#5A5A5A;">2.支持文件大小：1GB</span><br/>
                                                <span style="font-size:12px;color:red;">3.文件压缩包内部的文件名称应该避免中文字符，否则课件无法正常播放</span><br/>
                                                <span style="font-size:12px;color:#5A5A5A;">4.点击选择课件，选中要上传的压缩包即可实现上传</span><br/>
                                                <span style="font-size:12px;color:#5A5A5A;">5.为保证顺利上传，在上传课件过程中请勿刷新此页面</span><br/>
                                                <span style="font-size:12px;color:#5A5A5A;">6.如果已有课件，可以直接点击右下角链接跳过这一步</span><br/>
                                                <div id="helpTips"></div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <br/>
                                                <div class="btnContainer">
                                                    <div class="clearfix">
                                                        <c:if test="${ courseUploadPlatform != 1 }" >
                                                            <c:if test="${ uploadChapter != null}">
                                                                <button onclick="javascript:window.location.href =
                                                                        '../addLessonInfo/intoSectionFragmentEditor.do?chapterId=${chapterId}'
                                                                        ;return false" class="btn btn-info">返&nbsp;&nbsp;回</button>
                                                                <button onclick="javascript:window.location.href =
                                                                        '../addLessonInfo/intoSectionFragmentEditor.do?chapterId=${chapterId}';
                                                                        return false" class="btn btn-primary">完&nbsp;&nbsp;成</button>
                                                            </c:if>
                                                            <c:if test="${uploadChapter == null }">
                                                                <c:if test="${train != null }">
                                                                    <button onclick="javascript:window.location.href =
                                                                            '../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id }';
                                                                            return false" class="btn btn-info">返&nbsp;&nbsp;回</button>
                                                                    <button onclick="javascript:window.location.href =
                                                                            '../courseType/otherFileUpload.do?serverId=${serverId}&fileSize=${fileSize}&courseID=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id }';
                                                                            return false" class="btn btn-primary">下一步</button>
                                                                </c:if>
                                                                <c:if test="${train == null }">
                                                                    <button  onclick = "javascript:window.location.href =
                                                                            '../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId} ';
                                                                            return false"  class="btn btn-info">返&nbsp;&nbsp;回</button>
                                                                    <button   onclick="javascript:window.location.href =
                                                                            '../courseType/otherFileUpload.do?serverId=${serverId}&fileSize=${fileSize}&courseID=${courseId}&courseTypeId=${courseTypeId}';
                                                                            return false" class="btn btn-primary">下一步</button>
                                                                </c:if>
                                                            </c:if>

                                                        </c:if>
                                                        <c:if test="${courseUploadPlatform == 1}" >
                                                            <a href="javascript:location.href='<%=basePath%>addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId}'"
                                                               class="btn-blue-l"><span class="btn-blue-r">返&nbsp;&nbsp;回</span></a>
                                                            <!-- 课件上传平台，不负责上传相关资料 -->
                                                            <a href="javascript:location.href='<%=basePath%>courseCourseType/listOffLineAllCourse.do';"
                                                               class="btn-orange-l"><span class="btn btn-primary">提交</span></a>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" valign="middle">
                                                <img alt="点此" src="<%=basePath%>image/lookright.gif" title="点击右边链接"/>
                                                <div style="float: right; margin-right: 20px;margin-left: 10px;font-size: 18px;font-weight: bold;">
                                                    <a href="javascript:void(0);" onclick="showCourseInfo();return false;"
                                                       title="已有课件，点击这里直接输入课件访问地址进行验证">
                                                        已有课件？点此直接输入访问地址
                                                    </a>
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="courseInfoDiv" style="display:none" >
                                    <form name="courseWare" id="courseWare" class="form-horizontal report"
                                          action="<%=path%>/fileUpload/uploadSingleCourse.do"
                                          method="post">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label">输入课件入口地址：</label>
                                            <div class="col-sm-6" >
                                                <input type="hidden" name="serverFilePath" id="serverFilePath" type="text" size="32" value="${serverFilePath}"/>
                                                <input type="hidden" name="courseId"  value="${courseId }" />
                                                <input type="hidden" name="courseTypeId" value="${courseTypeId}" />
                                                <input type="hidden" name="orgDomainName" id="orgDomainName" value='${orgDomainName }'/>
                                                <input type="hidden" name="uploadChapter" value="${uploadChapter}" />
                                                <input type="hidden" name="chapterId" id="chapterId" value='${chapterId}'/>

                                                <input class="textInput" name='courseUrl' id='courseUrl'   type='text' size='48'  value='${customPath}' />
                                                <button onclick='javascript:checkValues("${customPath}");return false ' class="btn-orange-l">确&nbsp;定</button>
                                                <c:if test='${not empty courseUrl}'>
                                                    <button  onclick="javascript:openwindowtocenter('${courseUrl}','中国科学院信息化人才培训平台',600,500);
                                                    return false;" class="btn-orange-l" id="previewCourse">预&nbsp;览</button>
                                                </c:if>

                                                <div style="float: left;margin-left: 15px;">
                                                    <font color='red'>${uploadCourseSuccess}</font>
                                                </div>
                                            </div></div>
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
                                    <tr>
                                        <td colspan="2">
                                            <span style="font-size:12px;color:#5A5A5A;"><strong>说明：</strong></span><br/>
                                            <span style="font-size:12px;color:#5A5A5A;">
                                                1.课件入口地址示例：若上传压缩包中访问入口文件
                                                是test文件夹下的index.html文件，则输入地址：test/index.html
                                            </span>
                                            <br/>
                                            <span style="font-size:12px;color:red;">
                                                2.课件入口地址应该避免输入中文，否则课件无法正常播放
                                            </span>
                                            <br/>
                                            <span style="font-size:12px;color:#5A5A5A;">
                                                3.若已有课件或通过其他方式上传的课件，则需要输入课件访问的完整路径，如：http://www.kepu.net.cn/gb/index.html
                                            </span>
                                            <br/>
                                            <span style="font-size:12px;color:#5A5A5A;">
                                                4.为达到最佳预览效果，建议上传220px*135px的课件缩略图
                                            </span>
                                            <br/>
                                        </td>
                                    </tr>

                                    <table  class="table0" cellspacing="0" width="90%" align="center" cellpadding="0">
                                        <tr>
                                            <td>
                                                <br/>
                                                <div class="btnContainer">
                                                    <div class="clearfix">
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
                                            </td>
                                        </tr>
                                        <%if(!hasUpload){%>
                                        <tr>
                                            <td align="right" valign="middle">
                                                <img alt="点此" src="<%=basePath%>image/lookright.gif" title="点击右边链接"/>
                                                <div style="float: right; margin-right: 20px;margin-left: 10px;font-size: 18px;font-weight: bold;">
                                                    <a href="javascript:void(0);" onclick="showUploadInfo();return false;" title="返回重新上传课件">
                                                        没有上传课件？点此返回上传课件
                                                    </a>
                                                </div>
                                            </td>
                                        </tr>
                                        <%} %>
                                    </table>
                                </div><!-- courseInfoDiv -->
                            </div><!-- content2 -->
                        </div>
                    </div><!-- contentbody -->
                </div><!-- mainbody -->

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
<script type="text/javascript" src="../js/jquery.uploadify.js"></script>
<script type="text/javascript" src="../js/jquery.uploadifive.js"></script>
<script type="text/javascript" src="../js/course/courseEditor.js"></script>
<%
    if(hasUpload){
%>
<script type="text/javascript">
    $("#file_upload_message").html("<font color=green>课件已上传</font>");
    $("#file_upload_attention").css("display","none");
    $("#courseInfoDiv").css("display","block");
</script>
<%
    }
%>
<script type="text/javascript">
    function howToZipFile(){
        if($("#helpTips").html() == null || $("#helpTips").html() == ""){
            $("#helpTips").html("<b>提示</b>：<br/>-如果安装了WinRAR，可以选中要上传的文件夹--右击--添加到压缩文件--压缩文件格式<font color='red'>选择ZIP</font>--确定即可。<br/>-没有安装WinRAR，可以下载7-zip免费压缩工具进行压缩，<a href='http://sourceforge.net/projects/sevenzip/files/7-Zip/9.20/7z920.exe/download' target='_blank'>点此下载7-ZIP</a>");
        }else{
            $("#helpTips").html("");
        }
    }
    function showCourseInfo(){
        $("#tips").css('display','none');
        $("#file_upload_attention").css("display","none");
        $("#courseInfoDiv").css("display","block");
    }
    function showUploadInfo(){
        $("#tips").css('display','block');
        $("#file_upload_attention").css("display","block");
        $("#courseInfoDiv").css("display","none");
    }
</script>
<script type="text/javascript">
    function insertOrUpdate(courseId,courseTypeId){
        window.location.href = "../addLessonInfo/updateCourse.do?courseId="+courseId+"&courseTypeId="+courseTypeId+"";
    }

    function checkValues(value)
    {
        var defaultValue=value;
        var newValue=document.getElementById("courseUrl").value;

        if(document.getElementById("courseUrl").value == "")
        {
            $.Ntip({'content':"课件地址不能为空!",})
            document.getElementById("courseUrl").focus();
            return ;
        }
        var trainId=$('#train_Id').val();
        if(trainId!=-1)
            document.getElementById("courseWare").action='../fileUpload/uploadSingleCourse.do?trainId=${train.id }';
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

        var trainId=$('#train_Id').val();
        if(trainId!=-1)
            document.getElementById("coursePic").action='fileUpload.do?method=uploadPicture&trainId=${train.id}';
        return document.getElementById("coursePic").submit();

    }





    $(function() {

        $("#courseUploader #file_upload").uploadify({
            'fileTypeDesc' : 'ZIP压缩文件',
            'fileTypeExts' : '*.zip',
            'method'        : 'post',
            'auto'          :  true,
            'multi'         :  false,
            'uploadLimit'   :  1,
            'buttonText'    : '选择课件',
            'fileObjName'   : 'course',
            'formData'      : {'newFileName' : '<%=newFileName%>','orgDomainName':$('#orgDomainName').val()},
            'swf'           : '../js/uploadify.swf',
            'uploader'      : '<%=PropertiesUtil.getProperty("elearning-fs.address")+"/course_uploadZipCourse.action"%>',
            'progressData'  : 'percentage',
            'onUploadStart' : function() {
                <%if(hasUpload){%>
                $.Nconfirm({
                    'confirmQuestion':"确定重新上传？重新上传后会覆盖原有课件信息",
                    'onConfirm':function(){
                        $("#file_upload_message").html('<font color=green>课件正在重新上传，请稍等...</font>');
                        $("#file_upload_attention").css("display","block");
                        $("#courseInfoDiv").css("display","none");
                    },
                    'onDeny':function(){
                        $('#file_upload').uploadify('cancel', '*');
                    }
                })

                <%}else{%>
                $("#file_upload_message").html('<font color=green>课件正在上传，请稍等...</font>');
                <%}%>
            },
            'onCancel' : function(file) {
                $("#file_upload_message").html('<font color=red>课件上传被取消了</font>');
            },
            'onUploadError' : function(file, errorCode, errorMsg, errorString) {
                $("#file_upload_message").html('<font color=red>上传课件出错</font>');
            },
            'onUploadSuccess' : function(file, val, response) {
                $("#tips").css('display','none');
                $("#file_upload_message").css('display','block');
                if(response){
                    if(val == '-3'){
                        $("#file_upload_message").html("<font color=red>上传失败，失败原因：服务器端未正常解压，请<a href='addLessonInfo.do?method=foruploadcourseware&courseTypeId=${courseTypeId}'>点此刷新页面并重新上传</a>。<br/>如果问题还未解决，请联系系统管理员</font>");
                    }else if(val == '-2'){
                        $("#file_upload_message").html("<font color=red>上传失败，失败原因：服务器端未正确保存上传课件，请<a href='addLessonInfo.do?method=foruploadcourseware&courseTypeId=${courseTypeId}'>点此刷新页面并重新上传</a>。<br/>如果问题还未解决，请联系系统管理员</font>");
                    }else if(val == '-1'){
                        $("#file_upload_message").html("<font color=red>上传失败，失败原因：上传文件类型不正确，请<a href='addLessonInfo.do?method=foruploadcourseware&courseTypeId=${courseTypeId}'>点此刷新页面并重新上传</a>。<br/>如果问题还未解决，请联系系统管理员</font>");
                    }else{
                        $.ajax({
                            type : "POST",
                            dataType: "json",
                            url : "<%=path%>/fileUpload/uploadSingleCourseFileSuccess.do?folder=<%=newFileName%>&courseId=${courseId}",
                            success : function(data){
                                if(data.statusCode == 2 ){
                                    $("#file_upload_message").html("<font color=red>课件信息保存失败，失败原因：保存信息传递参数不正确，请<a href='addLessonInfo.do?method=foruploadcourseware&courseTypeId=${courseTypeId}'>点此刷新页面并重新上传</a>。<br/>如果问题还未解决，请联系系统管理员</font>");
                                }else if(data.statusCode == 1){
                                    $("#file_upload_message").html("<font color=red>课件信息保存失败，失败原因：未正确保存到本地数据库，请<a href='addLessonInfo.do?method=foruploadcourseware&courseTypeId=${courseTypeId}'>点此刷新页面并重新上传</a>。<br/>如果问题还未解决，请联系系统管理员</font>");
                                }else if(data.status){
                                    $("#file_upload_message").html('<font color=green>课件上传成功</font>');
                                    $("#file_upload_attention").css("display","none");
                                    $("#courseInfoDiv").css("display","block");
                                    $("#serverFilePath").prop("value",data.data.serverFilePath);
                                    if(data.data.courseUrl != ""){
                                        $("#previewCourse").attr("href","javascript:openwindowtocenter('../" + data.data.courseUrl + "','中国科学院信息化人才培训平台',600,500)");
                                    }
                                }
                            }
                        });
                    }
                }else{
                    $("#file_upload_message").html('<font color=red>上传失败，失败原因：服务器没有响应</font>');
                }
            }
        });
    });


</script>




</body>
</html>
