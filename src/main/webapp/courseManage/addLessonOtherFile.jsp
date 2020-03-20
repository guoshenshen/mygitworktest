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
    <link id="styleId" href="<%=Constants.SKINDIR%>/css/style.css" rel="stylesheet" type="text/css" />
    <link id="style_gl_Id"  href="<%=Constants.SKINDIR%>/css/skinCss/style_gl.css" rel="stylesheet" type="text/css" />
    <link href="../css/skinCss/Normalize.css" rel="stylesheet" type="text/css" />
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
    <link href="../css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="../css/uploadify.css"/>
    <link rel="stylesheet" type="text/css" href="../css/uploadifive.css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody"  id="courseRelatedMaterialEditor" >
        <div id="trace" class="content"></div>
        <div class="mainContent content">

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
                    </div></div>

                <ul id="tabs">
                    <li ><a href="mtMixTrainScheduleAction.do?method=listSchedule"><span>培训日程</span></a></li>
                    <li > <a href="mtMixTrainScheduleAction.do?method=listScheduleTeacher"><span>教师信息</span></a></li>
                    <li class="selected"> <a href="#tabs"><span>课程信息</span></a></li>
                    <div class='clr'></div>
                </ul>
            </c:if>
            <div id="contentbody">
                <!-- InstanceBeginEditable name="main" -->
                <div class="gl_31_3">
                    <span class="gl_11_no">1.基本信息</span>
                    <span class="gl_11_no">2.课件上传</span>
                    <span class="gl_11_yes">3.相关资料上传</span>
                </div>
                <div class="content_02">
                    <form id="form5" name="form5" class="form-horizontal report"  action="<%=basePath%>fileUpload.do?method=deleteLecture" method="post">
                        <table  class="table0 table table-striped table-bordered batchOperation" rules="cols" cellspacing="0" width="90%" align="center" cellpadding="0" id= "selectLectureList" style="display: none;">
                        </table>
                        <input type="hidden" name="uploadChapter"  value = "${uploadChapter}"/>
                        <input type="hidden" name="chapterId"  value = "${chapterId}"/>
                    </form>
                    <div class="btnContainer" id="deleteLectureList" style = "display: none;">
                        <div class="clearfix" style="; margin-left: 80px;margin-top: 10px;">
                            <button onclick="javascript:deleteLectureList();" class="btn btn-info">批量删除</button>
                        </div>
                    </div>
                    <form action="" id="form6" name="form6">
                        <table  class="table0 table table-striped table-bordered batchOperation" cellspacing="0" width="90%" align="center" cellpadding="0" id = "selectRelatedCourseList" style = "display: none;">

                        </table>
                        <input type="hidden" name="uploadChapter"  value = "${uploadChapter}"/>
                        <input type="hidden" name="chapterId"  value = "${chapterId}"/>
                    </form>

                    <div class="btnContainer" id = "deleteRelatedCourseList" style = "display: none;">
                        <div class="clearfix" style="margin-left: 80px;margin-top: 10px;">
                            <button onclick="javascript:deleteRelatedCourseList();" class="btn btn-info">批量删除</button>
                        </div>
                    </div>

                    <form name="courseDoc" action="<%=basePath%>fileUpload.do?method=uploadLecture" class="form-horizontal report"  enctype="multipart/form-data" method="post" id="courseDoc">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">资料名称：</label>
                            <div class="col-sm-6">
                                <input type="text" name="lectrueName" id="lectrueName" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">从本机选择资料：</label>
                            <div class="col-sm-6">
                                <input type="file" name="relatedMaterialUploader" id="relatedMaterialUploader"  class="form-control" />
                                <div id="fileQueue"></div>
                                <p id="uploadInfo" style="text-align: left;line-height:2em;"></p>
                                <span style="font-size:12px;color:#5A5A5A;"><strong>说明：</strong></span><br/>
                                <span style="font-size:12px;color:#5A5A5A;">1.如有本课程相关资料，请上传</span><br/>
                                <span style="font-size:12px;color:#5A5A5A;">2.支持文件大小：<b>100MB</b></span><br/>
                                <span style="font-size:12px;color:#5A5A5A;">3.点击选择文件，选中要上传的文件然后点击上传按钮即可实现上传</span><br/>
                                <span style="font-size:12px;color:#5A5A5A;">4.为保证顺利上传，在上传文件过程中请勿刷新此页面</span><br/>
                            </div>
                            <input type="hidden" name="courseId" value="${courseId }" />
                            <input type="hidden" name="courseTypeId" value="${courseTypeId}" />
                            <input type="hidden" name="hidFileID" id="hidFileID" value="" />
                            <input type="hidden" name="uploadChapter" id="uploadChapter" value="${uploadChapter}" />
                            <input type="hidden" name = "chapterId" id = "chapterId" value = "${chapterId}"/>
                            <c:if test="${train != null}">
                                <input type="hidden" id="trainId" name="trainId" value="${train.id }"/>
                            </c:if>
                        </div>
                        <c:if test="${uploadsuccess != null }">
                            <tr>
                                <td colspan="2" align="center">
                                    --><font color=red>${uploadsuccess}</font>
                                </td>
                            </tr>
                        </c:if>
                    </form>

                    <div class="form-group" style="padding-top: 40px;padding-bottom: 20px">
                        <label class="col-sm-3 control-label">指定相关课程：</label>
                        <div class="col-sm-3"style="width:12.8%">
                            <button class="btn btn-info" type="button"  id = "courseSearch" >选择</button>
                        </div>
                    </div>
                </div>


                <div id="_courseCenter"  style="display:none;cursor:default;overflow:hidden;">
                    <div id='_pop_win'><h2>添加课程<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
                    <div id="add_courseSelect">
                    </div>
                </div>

                <div id="mySelectCourse"  style="display:none;cursor:default;overflow:hidden;">
                    <div ><h2>已指定课程列表<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
                    <div id="isMySelectCourse"></div>
                </div>
                <div class="clr"></div><br/>
                <div class="btnContainer">
                    <div>
                        <c:if test="${uploadChapter != null }">
                            <c:if test="${train!= null }">
                                <button
                                        onclick="window.history.back();" class="btn btn-info">返&nbsp;回
                                </button>
                                <button
                                        onclick="javascript:location.href='../courseCourseType/listOffLineAllCourse.do';" class="btn btn-primary">确&nbsp;定
                                </button>

                            </c:if>
                            <c:if test="${train == null }">
                                <button
                                        onclick="window.history.back();" class="btn btn-info">返&nbsp;回
                                </button>
                                <button
                                        onclick="javascript:location.href='../courseCourseType/listOffLineAllCourse.do';"class="btn btn-primary">确&nbsp;定
                                </button>
                            </c:if>

                        </c:if>
                        <c:if test="${uploadChapter == null }">
                            <c:if test="${train != null }">
                                <button
                                        onclick="javascript:location.href='addLessonInfo.do?method=foruploadcourseware&info=1&courseTypeId=${courseTypeId}&trainId=${train.id }';" class="btn btn-info">返&nbsp;回
                                </button>
                                <button
                                        onclick="javascript:location.href='../courseCourseType/listOffLineAllCourse.do';" class="btn btn-info">确&nbsp;定
                                </button>

                            </c:if>
                            <c:if test="${train == null}">
                                <button
                                        onclick="window.history.back();" class="btn btn-info">返&nbsp;回
                                </button>
                                <button
                                        onclick="javascript:location.href='../courseCourseType/listOffLineAllCourse.do';" class="btn btn-primary">确&nbsp;定
                                </button>
                            </c:if>
                        </c:if>
                    </div>
                </div><br/><br/>
                <div class="clr"></div>
            </div><!-- content_02 -->
        </div>
        <input type="hidden" id="firstWatch" name = "firstWatch" value="${firstWatch}"/>
        <div id="chooseUscLectureFileOpenScope" style="display:none;cursor:default;overflow:hidden;">
            <div id='_pop_win'><h2 >选择发布范围<a href='javascript:void(0);' class='pop_close_btn' style="float: right;padding-right: 10px">X</a></h2></div>
            <div style="text-align:left;padding-left:20px;">
                <p><label><input type="radio" name="uscLectureFileOpenScope" value="2201" checked/>不公开</label></p>
                <p id="unitScope"><label><input type="radio" name="uscLectureFileOpenScope" value="2202"/>机构内公开</label></p>
                <p id="orgScope"><label><input type="radio" name="uscLectureFileOpenScope" value="2203"/>本单位公开</label></p>
                <p><label><input type="radio" name="uscLectureFileOpenScope" value="2204"/>全院公开</label></p>
                <p><label><input type="radio" name="uscLectureFileOpenScope" value="2205"/>完全公开</label></p>

                <input type="button" value="确定" onclick="javascript:forUpdateOpenStatus()" />
                <input type="hidden" id="selectedUscLectureFileId" value=""/>

            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>
<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/nav/amenu.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js"></script>
<script type="text/javascript" src="../js/aTool.js"></script>
<script type="text/javascript" src="../js/swfobject.js"></script>
<script type="text/javascript" src="../js/jquery.uploadify.js"></script>
<script type="text/javascript" src="../js/jquery.uploadifive.js"></script>
<script type="text/javascript" src="../js/course/courseEditor.js"></script>
<script type="text/javascript" src="../js/jquery.blockUI.js"></script>
<script type="text/javascript" src="../js/dojojs/dojo.js"></script>
<script type="text/javascript" src="../js/UI/jquery.alerts.js"></script>
<script type="text/javascript" src="../js/UI/remodal.js"></script>
<script type="text/javascript">

    $(function(){

        addRelatedCourseList();
        addLectureList();
    })

    function addLectureList(){
        var courseId = ${courseId};
        var packageCourseId = $("#firstWatch").val();
        $.ajax({
            method:"POST",
            data:{"courseID":courseId},
            url:"../courseType/addLectureList.do",
            dataType:"json",
            success:function(data){
                var html = ""
                if(data.status  ){
                    var dataList = data.data;
                    if(dataList.length == 0){
                        $("#selectLectureList").hide();
                        $("#deleteLectureList").hide();
                    }else{
                        $("#selectLectureList").show();
                        $("#deleteLectureList").show();
                        html += '<tbody> <tr><th  width="20%"> <input type="checkbox" id="selectAllFile" name="selectAllFile" onclick="_setSelected(this,\'courseFileFormList\')" /></th><th width="50%" align="center">资料列表</th><th width="30%">公开范围</th></tr>';
                        for(var i = 0; i < dataList.length; i++){
                            var course = dataList[i];
                            html += '<tr class = "infoRow"><td align="left"><input type="checkbox" name="courseFileFormList" value="'+course.lectureId+'" /></td><td>';
                            html += '<a href="'+course.lectureURL+'" target="_blank">'+course.lectureName+'</a></td>';
                            var openScope = "";
                            if(course.openScope == 2201 || course.openScope == 0){
                                openScope = "不公开";
                                course.openScope = 2201;
                            }
                            if(course.openScope == 2202){
                                openScope = "机构内公开";
                            }
                            if(course.openScope == 2203){
                                openScope = "本单位公开";
                            }
                            if(course.openScope == 2204){
                                openScope = "全院公开";
                            }
                            if(course.openScope == 2205){
                                openScope = "完全公开";
                            }
                            html += '<td align="left"><div id="openScope_'+course.lectureId+'"><a href = "javascript:updateOpenScope('+course.lectureId+','+course.openScope +')">'+openScope+'</a>';
                            html += '</div></td></tr>'
                        }
                        html += '</tbody>'
                        $("#selectLectureList").html(html);
                    }
                }
            }
        })

    }
    function deleteLectureList(){
        var courseIdArray=new Array();
        $("#selectLectureList tr.infoRow").each(function(){
            var $checkbox=$("input[type=checkbox]",$(this));
            if($checkbox.is(':checked')){
                courseIdArray.push($checkbox.val());
            }
        })
        if(courseIdArray.length==0){
            $.Ntip({
                'content':"请先选择要删除的相关资料"
            });
            return;
        }else{
            $.Nconfirm({
                'confirmQuestion':"确认删除资料课程？",
                'onConfirm':function(){
                    $.ajax({
                        method:"POST",
                        data:{"relevantCourseList":courseIdArray},
                        url:"../courseType/deleteLectureList.do",
                        traditional:true,
                        dataType:"json",
                        success:function(data){
                            if(data.status ){
                                addLectureList();
                            }
                        }
                    });
                }
            });


        }
    }






    function addRelatedCourseList(){
        var courseId = ${courseId};
        var packageCourseId = $("#firstWatch").val();
        $.ajax({
            method:"POST",
            data:{"courseID":courseId},
            url:"../courseType/addRelatedCourseList.do",
            dataType:"json",
            success:function(data){
                var html = ""
                if(data.status  ){
                    var dataList = data.data;
                    if(dataList.length == 0){
                        $("#selectRelatedCourseList").hide();
                        $("#deleteRelatedCourseList").hide();
                    }else{
                        $("#selectRelatedCourseList").show();
                        $("#deleteRelatedCourseList").show();
                        html += '<tr><th><input type="checkbox" id="selectAllCourse" name="selectAllCourse"   onclick="_setSelectedRelevantCourse(this,\'relevantCourseList\')"/>';
                        html += '</th><th width="80%">相关课程列表</th></tr>';
                        for(var i = 0; i < dataList.length; i++){
                            var course = dataList[i];
                            html += '<tr class = "infoRow"><td align="left"><input type="checkbox" name="relevantCourseList" value="'+course.id+'" /></td>';
                            if(packageCourseId != "" && packageCourseId != null){
                                html += '<td><button onclick="window.open(\'/courseStudy/previewStudy.do?courseID='+packageCourseId+'\');return false">'+course.courseName+'</button>'
                            }else{
                                html += '<td><button onclick="window.open(\'/courseStudy/previewStudy.do?courseID='+course.courseId+'\');return false">'+course.courseName+'</button>'
                            }
                            html += '</td></tr>';
                        }
                        $("#selectRelatedCourseList").html(html);
                    }
                }
            }
        })
    }
    function deleteRelatedCourseList(){
        var courseId = ${courseId};
        var courseIdArray=new Array();
        $("#selectRelatedCourseList tr.infoRow").each(function(){
            var $checkbox=$("input[type=checkbox]",$(this));
            if($checkbox.is(':checked')){
                courseIdArray.push($checkbox.val());
            }
        })
        if(courseIdArray.length==0){
            $.Ntip({
                'content':"请先选择要删除的相关课程"
            });
            return;
        }else{
            $.ajax({
                method:"POST",
                data:{"relevantCourseList":courseIdArray},
                url:"../courseCourseType/ajaxdeleteRelevantCourse.do",
                traditional:true,
                dataType:"json",
                success:function(data){
                    if(data.status ){
                        addRelatedCourseList();
                    }
                }
            })
        }
    }


    function checkValues()
    {
        if (courseWare.coursezipfile.value == "" )
        {
            alert( "You must enter a value for all items" );
            return false;
        }

        courseWare.theZipFile.value = courseWare.coursezipfile.value;
        //alert( courseWare.theZipFile.value );
        return true;
    }




    function showFile() {
        document.getElementById('search_files').style.display="block";
        document.getElementById('add_files').style.display="none";


    }

    //删除资料时，先判断是否选中
    function _delete(){

        var length = $('input:checkbox[name="courseFileFormList"]:checked').length;
        //alert(length);
        if(length>0){
            document.form5.action="<%=path%>/fileUpload.do?method=deleteLecture&courseId="+${courseId};
            document.form5.submit();
        }else
            $.Ntip({
                'content':"请先选择要删除的相关资料"
            });

    }

    function _delete_relevantCourse(){

        var length = $('input:checkbox[name="relevantCourseList"]:checked').length;
        //alert(length);
        if(length>0){
            document.form6.action="<%=path%>/courseCoursetype.do?method=deleteRelevantCourse&courseId="+${courseId};
            document.form6.submit();
        }else
        //alert("请先选择要删除的相关课程");
            jAlert("请先选择要删除的相关课程",'提示');

    }

    function _setSelected(id,type){
        var chechTemp = id.checked;
        with(document.form5){
            for ( var j=0; j<elements.length; j++ ){
                if ( elements[j].type == "checkbox" && elements[j].name == type) {
                    if(chechTemp){
                        document.form5.elements[j].checked = true;
                    }else{
                        document.form5.elements[j].checked = false;
                    }
                }
            }
        }
    }

    function _setSelectedRelevantCourse(id,type){
        var chechTemp = id.checked;
        with(document.form6){
            for ( var j=0; j<elements.length; j++ ){
                if ( elements[j].type == "checkbox" && elements[j].name == type) {
                    if(chechTemp){
                        document.form6.elements[j].checked = true;
                    }else{
                        document.form6.elements[j].checked = false;
                    }
                }
            }
        }
    }

    function checkDocValues()
    {
        if(document.getElementById("lectrueName").value == "")
        {
            //alert("资料名称不能为空!");
            //document.getElementById("lectrueName").focus();
            jAlert("资料名称不能为空!",'提示',function(){
                document.getElementById("lectrueName").focus();
            });
            return false;
        }
        if(document.getElementById("lectrueFile").value == "")
        {
            //alert("资料上传地址不能为空!");
            //document.getElementById("lectrueFile").focus();
            jAlert("资料上传地址不能为空!",'提示',function(){
                document.getElementById("lectrueFile").focus();
            });
            return false;
        }
        document.getElementById("courseDoc").submit();
    }

    function updateOpenScope(lectureId,openScope){
        $('#selectedUscLectureFileId').val(lectureId);
        $("input[name=courseOpenScope]:radio").each(function(){
            if(this.value==openScope)this.checked=true;
        })
        $.blockUI({ message: $("#chooseUscLectureFileOpenScope"), css: { width: '400px',height:'252px'} });
        $("a.pop_close_btn").click(function(){$.unblockUI();});
    }

    function forUpdateOpenStatus(){
        var selectedUscLectureFileId=$('#selectedUscLectureFileId').val();
        var openScope=$('input[name=uscLectureFileOpenScope]:checked').val();
        $.ajax({
            method:"POST",
            data:{lectureId:selectedUscLectureFileId,openScope:openScope},
            url:"../courseCourseType/updateUscLectureFileOpenScope.do",
            traditional:true,
            dataType:"json",
            success:function(data){
                if(data.status  ){
                    jAlert("设置公开范围成功！",'提示');
                    var insertContent="<a href=javascript:updateOpenScope("+selectedUscLectureFileId+","+openScope+")>"+data.data+"</a>";
                    $("#openScope_"+selectedUscLectureFileId).html(insertContent);
                }
            }
        })
        $.unblockUI();
    }




    //选择课程
    function _selectCourse(trainWay){
        var temp= 730;
        var iframeContent="<iframe height='"+temp+"' scrolling='auto' width='100%' class='pop_iframe' src='courseCoursetype.do?method=listAllCourseToSetRelativity&fromRelaCourse=true&step=0'> </iframe>"
        $("#add_courseSelect").html(iframeContent);
        $.blockUI({ message: $("#_courseCenter"), css: {width:'90%',top:'5%',left:'5%'} });
        $("a.pop_close_btn").click(function(){$.unblockUI();});
    }

    //查看已选
    function manageMySelectCourse(step,trainWay){
        var selectedCourseIdStr="";
        if(step==0)
            selectedCourseIdStr=$("#rele_courseIds").val();
        else if(step==-1)
            selectedCourseIdStr=top.$('#editOnLineItem').find('#editItem_courseId').val();
        if(selectedCourseIdStr!=""){
            var temp= 730;
            var iframeContent="<iframe height='"+temp+"' scrolling='auto' width='100%' class='pop_iframe' src='courseCoursetype.do?method=listMySelectCourseToSchedule&step="+step+"&trainWay="+trainWay+"&courseIdStr="+selectedCourseIdStr+"&relevantCoursePageFlag=1"+"'> </iframe>"
            $("#isMySelectCourse").html(iframeContent);
            $.blockUI({ message: $("#mySelectCourse"), css: {width:'90%',top:'5%',left:'5%'} });
            $("a.pop_close_btn").click(function(){$.unblockUI();});
        }else
        //alert("当前没有选择任何课程，请先选择课程！");
            jAlert("当前没有选择任何课程，请先选择课程！",'提示');
    }

    //保存指定
    function saveMySelectCourse(trainWay){
        var uploadChapter = $("#uploadChapter").val();
        if(trainWay==0 && $('#onLineCourseName').val()!=""){
            $.post("courseCoursetype.do?method=setRelevantCourse",
                {rele_courseIds:$("#rele_courseIds").val(),
                    courseID:${courseId},
                },
                function(data){
                    jAlert("<font color=red>指定相关课程成功！</font>","提示",function(){location.href="<%=basePath%>courseType.do?method=otherFileUpload&courseID=${courseId}&courseTypeId=${courseTypeId}";});
                }
            );
        }
        else{
            //alert("当前没有选择任何课程，请先选择课程！");
            jAlert("当前没有选择任何课程，请先选择课程！",'提示');
            //jAlert('<font color=red>课程不能为空！</font>','提示');
        }

    }


    //清空选择
    function _concelSelectCourse(trainWay){
        if(trainWay==0){
            if($('#onLineCourseName').val() != ""){
                $('#onLineCourseName').val("");
                $('#rele_courseIds').val("");
                //alert("清除成功！");
                jAlert("清除成功！",'提示');
            }else{
                //alert("当前未选择课程");
                jAlert("当前未选择课程",'提示');
            }
        }
        //$.post("courseCoursetype.do?method=clearMySelectCourseSession");
        // alert("清除成功！");
    }


    function saveMySelectCourse(trainWay){

        var courseIdArray=new Array();
        $("#courseSelectTable tr.infoRow").each(function(){
            var $checkbox=$("input[type=checkbox]",$(this));
            if($checkbox.is(':checked')){
                courseIdArray.push($checkbox.val());
            }
        })
        if(courseIdArray.length==0){
            $.Ntip({
                'content':"您尚未指定需要关联的课程"
            });
            return;
        }
        else{
            $.ajax({
                method:"POST",
                data:{"rele_courseIds":courseIdArray,"courseID":${courseId}},
                url:"../courseCourseType/ajaxSetRelevantCourse.do",
                traditional:true,
                dataType:"json",
                success:function(result){
                    if(result.status == 0 ){
                        $.Ntip({'content':"绑定成功！"});
                        $.closeCourseModal();
                        $("#selectRelatedCourseList").show();
                        $("#deleteRelatedCourseList").show();
                        addRelatedCourseList()
                    }
                }
            })
        }
    }
    $("#courseSearch").on("click",function(){
        var param = {
            actionForSubmit:function(courseArrayObject){
                var courseIdArray=new Array();
                $("#courseSelectTable tr.infoRow").each(function(){
                    var $checkbox=$("input[type=checkbox]",$(this));
                    if($checkbox.is(':checked')){
                        courseIdArray.push($checkbox.val());
                    }
                })
                if(courseIdArray.length==0){
                    $.Ntip({
                        'content':"您尚未指定需要关联的课程"
                    });
                    return;
                }
                else{
                    $.ajax({
                        method:"POST",
                        data:{"rele_courseIds":courseIdArray,"courseID":${courseId}},
                        url:"../courseCourseType/ajaxSetRelevantCourse.do",
                        traditional:true,
                        dataType:"json",
                        success:function(result){
                            if(result.status == 0){
                                $.Ntip({'content':"绑定成功！"});
                                $.closeCourseModal();
                                $("#selectRelatedCourseList").show();
                                $("#deleteRelatedCourseList").show();
                                addRelatedCourseList()
                            }
                        }
                    })
                }
            }
        }
        $.showSelectableCourse(param);
    });


</script>

</body>
</html>
