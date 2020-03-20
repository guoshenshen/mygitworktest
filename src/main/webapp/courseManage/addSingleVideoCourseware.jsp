<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants"%>

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
	<link rel="stylesheet" type="text/css" href="../css/uploadify.css"/>
	<link rel="stylesheet" type="text/css" href="../css/uploadifive.css"/>
	<style type="text/css">
		#file_upload{
			padding-left: 56px;
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
		  <div id="mainbody">
			  <c:if test="${train != null} ">
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
						  <img class="barL" src="/image/arrows4l.png"/>
						  <div class='thirdMenu'>
							  <ul>
							  </ul>
						  </div>
						  <img class="barR" src="/image/arrows4r.png"/>
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
			  </c:if>
			  <div id="contentbody">
				  <div id="mainbody1" class="valuediv">
					  <!-- InstanceBeginEditable name="main" -->
					  <div class="gl_31_2"><span class="gl_11_no">1.基本信息</span><span class="gl_11_yes">2.课件上传</span><span class="gl_11_no">3.相关资料上传</span></div>
					  <br/>
					  <div class="content_02" align="center">
						  <div id="convertVideo" style="display:none"><img src="../image/convert.gif"/><br/><br/><span style="font-size:12px;color:red;">正在上传与转化，请稍候...</span></div>
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
										  <c:if test="${not empty PictureURL}"><img  class="coursePic" style="width:450px;height:240px;"  src="${PictureURL}" /></c:if>
									  </div>
								  </div>
							  </form>
						  </c:if>
					  </div>


					  <input type="hidden" name="courseDescription" id = "courseDescription" value="${courseDescription}"/>
					  <input type="hidden" name="courseUrl" id = "courseUrl" value="${courseUrl}"/>
					  <form id="form1"  action="../fileUpload/uploadSingleVideo.do?" class="form-horizontal report"  method="post"  enctype="multipart/form-data">
						  <div class="form-group">
							  <label class="col-sm-3 control-label" id = "courseVoideo">上传课件：</label>
							  <div class="col-sm-6" >

								  <c:if test="${train != null }">
									  <input type="hidden" name="trainId" value="${train.id }" />
								  </c:if>
								  <div id="fileQueue"></div>
								  <input type="file" name="file_upload" id="file_upload"  class="form-control"/>
								  <div id="file_upload_message"></div>
								  <input type="hidden" name="uploadCourseId" id="uploadCourseId" value='${courseId}'/>
								  <input type="hidden" name="uploadCourseTypeId" id="uploadCourseTypeId" value='${courseTypeId}'/>
								  <input type="hidden" name="operatorOrgId" id="operatorOrgId" value='${operatorOrgId}'/>
								  <input type="hidden" name="uploadAddress" id="uploadAddress" value='${uploadAddress}'/>
								  <input type="hidden" name="uploadFileName" id="uploadFileName" value=""/>
								  <input type="hidden" name="orgDomainName" id="orgDomainName" value='${orgDomainName }'/>
								  <input type="hidden" name="chapterId" value="${chapterId}"/>
								  <input type="hidden" name="uploadChapter" value="${uploadChapter}"/>
								  <input type="hidden" name="courseDescription" value="${courseDescription}"/>
							  </div>
						  </div>
					  </form>
					  <div id="file_upload_attention">
						  <table  class="table0" cellspacing="0" width="90%" align="center" cellpadding="0">
							  <tr>
								  <td align="left">
									  <span style="font-size:12px;color:#5A5A5A;"><strong>说明：</strong></span><br/>
									  <span style="font-size:12px;color:#5A5A5A;">1.缩略图可不上传，系统将自动截取课件/视频生成</span><br/>
									  <span style="font-size:12px;color:#5A5A5A;">2.为达到最佳预览效果，建议手动上传220px*135px的缩略图</span><br/>
									  <span id="openTypeDetail" style="font-size:12px;color:#5A5A5A;">3.支持视频格式：<strong>绝大多数的视频格式【<a href="javascript:void(0);" onclick='supportVideoType(1)' >点击查看</a>】</strong></span>
									  <span id="closeTypeDetail" style="font-size:12px;color:#5A5A5A;display:none;">3.支持视频格式：<strong>绝大多数的视频格式【<a href="javascript:void(0);" onclick='supportVideoType(0)' >点击关闭</a>】</strong></span>
									  <br/>
									  <div id="supportVideoType" style="background-color:#FFFACD;padding:10px 0 10px 10px;display:none;">微软视频：<strong> &nbsp;.wmv &nbsp;.avi &nbsp;.dat &nbsp;.asf</strong><br/>Real Player：<strong>&nbsp;.rm &nbsp;.rmvb &nbsp;.ram</strong><br/>MPEG视频：<strong>&nbsp;.mpg &nbsp;.mpeg</strong><br/>手机视频：<strong>&nbsp;.3gp</strong><br/>Apple视频： <strong>&nbsp;.mov</strong><br/>Sony视频： <strong>&nbsp;.mp4 &nbsp;.m4v</strong><br/>DV视频：<strong>&nbsp;.dvix &nbsp;.dv</strong><br/>其他常见视频： <strong>&nbsp;.dat &nbsp;.mkv &nbsp;.flv &nbsp;.vob &nbsp;.ram &nbsp;.qt &nbsp;.divx &nbsp;.cpk &nbsp;.fli &nbsp;.flc &nbsp;.mod</strong></div>
									  <span style="font-size:12px;color:#5A5A5A;">4.支持文档格式：<strong>.pdf&nbsp;&nbsp; .doc &nbsp;&nbsp;.docx &nbsp;&nbsp;.ppt &nbsp;&nbsp;.pptx&nbsp;&nbsp; .xls&nbsp;&nbsp; .xlsx &nbsp;&nbsp; .html</strong></span><br/>
									  <span style="font-size:12px;color:#5A5A5A;">5.点击选择视频或文档，选中要上传的视频或文档实现上传</span><br/>
									  <span style="font-size:12px;color:#5A5A5A;">6.为保证顺利上传，在上传过程中请勿刷新此页面</span><br/>

									  <div id="helpTips"></div>
								  </td>
							  </tr>
						  </table>
					  </div>
					  <table  class="table0" cellspacing="0" width="90%" align="center" cellpadding="0">
						  <tr>
							  <td>
								  <br/>
								  <div class="btnContainer">
									  <div>
										  <c:if test="${courseDescription != null}">
											  <c:if test="${train != null }" >
												  <button onclick="javascript:window.location.href = '../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id }';return false" class="btn btn-info">返&nbsp;&nbsp;回</button>
												  <button onclick="javascript:window.location.href = '../addLessonInfo/courseDescription.do?courseId=${courseId}&courseDescription=1';return false" class="btn btn-primary">下一步</button>
											  </c:if>
											  <c:if test="${train == null }">
												  <button  onclick = "javascript:window.location.href = '../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId} ';return false"  class="btn btn-info">返&nbsp;&nbsp;回</button>
												  <button   onclick="javascript:window.location.href = '../addLessonInfo/courseDescription.do?courseId=${courseId}&courseDescription=1';return false" class="btn btn-primary">下一步</button>
											  </c:if>
										  </c:if>
										  <c:if test="${courseDescription == null }">
											  <c:if test="${uploadChapter != null }">
												  <button onclick="javascript:window.location.href = '../addLessonInfo/intoSectionFragmentEditor.do?chapterId=${chapterId}';return false" class="btn btn-info">返&nbsp;&nbsp;回</button>
												  <button onclick="javascript:window.location.href = '../addLessonInfo/intoSectionFragmentEditor.do?chapterId=${chapterId}';return false;" class="btn btn-primary">完&nbsp;&nbsp;成</button>
											  </c:if>
											  <c:if test="${uploadChapter == null}">
												  <c:if test="${train != null }" >
													  <button onclick="javascript:window.location.href = '../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id }';return false" class="btn btn-info">返&nbsp;&nbsp;回</button>
													  <button onclick="javascript:window.location.href = '../courseType/otherFileUpload.do?serverId=${serverId}&fileSize=${fileSize}&courseID=${courseId}&courseTypeId=${courseTypeId}&trainId=${train.id }';return false" class="btn btn-primary">下一步</button>
												  </c:if>
												  <c:if test="${train == null }" >
													  <button  onclick = "javascript:window.location.href = '../addLessonInfo/updateCourse.do?courseId=${courseId}&courseTypeId=${courseTypeId} ';return false"  class="btn btn-info">返&nbsp;&nbsp;回</button>
													  <button   onclick="javascript:window.location.href = '../courseType/otherFileUpload.do?serverId=${serverId}&fileSize=${fileSize}&courseID=${courseId}&courseTypeId=${courseTypeId}';return false" class="btn btn-primary">下一步</button>
												  </c:if>
											  </c:if>
										  </c:if>
									  </div>
								  </div>
							  </td>
						  </tr>
					  </table>
				  </div>
			  </div>
		  </div>
	  </div>
  </div>
</div>
</div>
<div class="bottombody"></div>
<input type="hidden" id="token" name="token" value="${token}"/>
<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/nav/amenu.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js"></script>
<script type="text/javascript" src="../js/aTool.js"></script>
<script type="text/javascript" src="../js/course/courseEditor.js"></script>
<script type="text/javascript" src="../js/jquery.uploadify.js"></script>
<script type="text/javascript" src="../js/jquery.uploadifive.js"></script>
<script type="text/javascript">

    $(function(){
        var courseDescription = $("#courseDescription").val();
        if(courseDescription == 1){
            $("#courseImage").text("上传课程包缩略图：");
            $("#courseVoideo").text("上传课程简介：");
        }


        $("#file_upload").uploadify({
            'formData'       :{'uploadCourseId':'','operatorOrgId':'','uploadCourseTypeId':'','orgDomainName':''},
            'method'         :'get',
            'swf'            : '../js/uploadify.swf',
            'fileTypeExts'   :'*.json;*.JSON;*.mts;*.swf;*.SWF;*.doc;*.docx;*.ppt;*.pptx;*.xls;*.xlsx;*.html;*.HTML;*.DOC;*.DOCX;*.PPTX;*.XLSX;*.PDF;*.wmv;*.avi;*.dat;*.asf;*.rm; *.rmvb;*.ram;*.mpg;*.mpeg;*.3gp;*.mov;*.mp4;*.m4v;*.dvix;*.dv;*.dat;*.mkv;*.flv;*.vob;*.ram;*.qt;*.divx;*.cpk;*.fli;*.flc;*.mod',
            'uploader'       : $('#uploadAddress').val()+'/uploadVideo',
            'cancelImg'      : 'image/cancel.png',
            'auto'           : false,
            'multi'          : true,
            'buttonText'     : '选择视频或文档',
            'onUploadStart'  : function(file){
                $("#file_upload").uploadify("settings", "formData",{'uploadCourseId':$('#uploadCourseId').val(),
                    'operatorOrgId':$('#operatorOrgId').val(),
                    'uploadCourseTypeId':$('#uploadCourseTypeId').val(),
                    'orgDomainName':$('#orgDomainName').val()});
                var docType="swf,Swf,doc,docx,ppt,pptx,xls,html,pdf,HTML,DOC,DOCX,PPTX,XLSX,PDF";
                var fileType=file.name.substr(file.name.indexOf(".")+1);
                if(docType.indexOf(fileType)>-1){
                    $('#convertVideo').css('display','block');
                }
            },
            'queueID'        : 'fileQueue',
            'onSelect'   :function(){
                judgeCourseExist($('#uploadCourseId').val());
            },
            'successTimeout' :600,
            'onUploadSuccess':function(file,data,response){
                $('#uploadFileName').val(file.name);
                $('#form1').submit();
                $('.btnContainer').css('display','block');
            }
        });

    });


    function insertOrUpdate(courseId,courseTypeId){
        window.location.href = "../addLessonInfo/updateCourse.do?courseId="+courseId+"&courseTypeId="+courseTypeId+"";
    }

    function judgeCourseExist(id){
        // $.ajax({
        //     url:'fileUpload.do?method=checkIsExistCourse',
        //     type:'post',
        //     dataType:'text',
        //     data:{courseId:id},
        //     success:function(data,evt){
        //         if(data=='true'){
        //             $.Nconfirm({
        //                 'confirmQuestion':"已有课件，是否覆盖？",
        //                 'onConfirm':function(){
        //                     $('#file_upload').uploadify('upload');
        //                 },
        //                 'onDeny':function(){
        //                     $('#file_upload').uploadify('cancel','*');
        //                 }
        //             })
        //         }else
        //             $('#file_upload').uploadify('upload');
        //     }
        // })

		var courseUrl = $("#courseUrl").val();
        if(courseUrl != ''){
            $.Nconfirm({
                'confirmQuestion':"已有课件，是否覆盖？",
                'onConfirm':function(){
                    $('#file_upload').uploadify('upload');
                },
                'onDeny':function(){
                    $('#file_upload').uploadify('cancel','*');
                }
            })
        }else{
            $('#file_upload').uploadify('upload');
		}

    }

    function supportVideoType(type){
        if(type==1){
            $('#supportVideoType').show();
            $('#openTypeDetail').hide();
            $('#closeTypeDetail').show();

        }else{
            $('#supportVideoType').hide();
            $('#openTypeDetail').show();
            $('#closeTypeDetail').hide();
        }

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
