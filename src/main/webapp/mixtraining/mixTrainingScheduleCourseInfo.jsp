<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String userRolesInString = "";
    try {
        EosOperator user = (EosOperator) session.getAttribute(Constants.USERINFO_KEY);
    } catch (Exception ex) {
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
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="培训;在线培训;网络培训" name="keywords"/>
    <title>培训项目基本信息管理</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/pagination.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery.ui.theme.css"/>

</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <ul id="tabs">
                <li><a href="../mtMixTrainSchedule/listSchedule.do"><span>培训日程</span></a></li>
                <li><a href="../mtMixTrainSchedule/listScheduleTeacher.do"><span>教师信息</span></a></li>
                <li class="selected"><a href="#tabs"><span>课程信息</span></a></li>
                <div class='clr'></div>
            </ul>

            <div id="mainbody">
                <div class="homezoneall">
                    <div class="homezonehead">
                        <span class="homezonetitle"><font color=red>线下讲座课程信息</font></span>
                    </div>
                    <div class="homezonecontent" id="otherAllSchedule">
                        <!-- 培训日程中的课程信息tab -->
                        <div id="courseTab">
                            <div id="editCourseArea" style="display:none">
                                <input type="hidden" id="courseTab_courseId" value=""/>
                                <input type="hidden" id="courseTab_courseTypeId" value=""/>
                                <div class="demo">
                                    <div id="accordion">
                                        <h3><a href="javascript:void(0);">课程基本信息</a></h3>
                                        <div>
                                            <form id="form6" action="" method="post">
                                                <table class="table1" cellspacing="0" width="90%" align="center" cellpadding="0">
                                                    <tr>
                                                        <th>课程名称：</th>
                                                        <td><input type="text" name="courseName" id="scheduleCourseName" value="" size="20" maxlength="100"/>
                                                            <font color=red>&nbsp;*</font>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>创建日期：</th>
                                                        <td>
                                                            <input name="createDate" id="scheduleCreateDate" class="Wdate" type="text" onclick="WdatePicker({isShowClear:false,readOnly:true})" value=''/>
                                                            <font color='red'>*</font>
                                                        </td>

                                                        <th>创建人：</th>
                                                        <td>
                                                            <input type="text" name="maker" id="maker" value='' size="20" maxlength="40"/>
                                                            <font color=red>&nbsp;*</font>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>作者：</th>
                                                        <td>
                                                            <input type="text" name="creator" id="creator" value='' size="20" maxlength="30"/>
                                                            <font color=red>&nbsp;*</font>
                                                        </td>

                                                        <th>学时：</th>
                                                        <td>
                                                            <input type="text" name="classHour" id="classHour" value="" size="5" maxlength="3"/>
                                                            <font color=red>&nbsp;*</font>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>
                                                            课件类别：
                                                        </th>
                                                        <td>
                                                            <select property="typeId" name="typeId" id="typeId"></select>
                                                            <font color=red>&nbsp;*</font>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>
                                                            是否公开课：
                                                        </th>
                                                        <td>
                                                            <input type="radio" name="isOpenCourse" value="1"/>是&nbsp;&nbsp;&nbsp;
                                                            <input type="radio" name="isOpenCourse" value="0"/>否
                                                            <font color=red>&nbsp;*</font>
                                                        </td>

                                                        <th>专业领域</th>
                                                        <td>
                                                            <select property="expertAreaId" name="expertAreaId" id="expertAreaId">
                                                                <option value="" selected>全部</option>
                                                                <option value="1">工程类</option>
                                                                <option value="2">生物与生命科学类</option>
                                                                <option value="3">计算机类</option>
                                                                <option value="4">医学类</option>
                                                                <option value="5">物理类</option>
                                                                <option value="6">化学类</option>
                                                                <option value="7">教育类</option>
                                                                <option value="8">金融类</option>
                                                                <option value="9">动植物类</option>
                                                                <option value="10">电子类</option>
                                                                <option value="11">经济类</option>
                                                                <option value="12">自然科学</option>
                                                                <option value="13">社会科学</option>
                                                                <option value="14">新闻传播</option>
                                                                <option value="15">文物博物</option>
                                                                <option value="16">艺术类</option>
                                                                <option value="17">其他</option>
                                                            </select><font color=red>&nbsp;*</font>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>关键词：</th>
                                                        <td colspan=3>
                                                            <input type="text" name="keyWords" id="keyWords" value="" size="50"/>&nbsp;&nbsp;(关键词之间用 ; 隔开)
                                                            <font color=red>&nbsp;*</font>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>课程适用岗位类型：</th>
                                                        <td colspan=3>
                                                            <input type="checkbox" name="suitpost" value=1/> 科研类
                                                            <input type="checkbox" name="suitpost" value=2/> 支撑类
                                                            <input type="checkbox" name="suitpost" value=3/> 管理类
                                                            <input type="checkbox" name="suitpost" value=4/>成果转移转化类
                                                            <input type="checkbox" name="suitpost" value=5/> 领导类
                                                            <font color=red>&nbsp;*</font>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>适用对象：</th>
                                                        <td colspan="6">
                                                            <textarea name="suitableObject" id="suitableObject" rows="2" cols="85" onKeyDown="gbcount(this,400);" onKeyUp="gbcount(this,400);"></textarea>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>主要内容：</th>
                                                        <td colspan="6">
                                                            <textarea name="mainContent" id="mainContent" rows="5" cols="85" onKeyDown="gbcount(this,400);" onKeyUp="gbcount(this,400);"></textarea>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <th>备注：</th>
                                                        <td colspan="6">
                                                            <textarea name="remark" id="remark" rows="3" cols="85" onKeyDown="gbcount(this,400);" onKeyUp="gbcount(this,400);"></textarea>
                                                        </td>
                                                    </tr>
                                                </table>
                                                <div style="text-align:center; margin:20px;">
                                                    <input type="submit" value="下一步" onclick='javascript:insertOrUpdate();'/>
                                                </div>
                                            </form>
                                        </div>
                                        <h3><a href="javascript:void(0);">上传课件</a></h3>
                                        <div>
                                            <table class="table1" cellspacing="0" width="90%" align="right" cellpadding="0">
                                                <!-- ===========使用applet组件上传=====2011年8月17日========== -->
                                                <tr>
                                                    <td>
                                                        <jsp:plugin type='applet' code='cn/cnic/applet/FtpUploadApplet.class' archive='ftpUploadApplet.jar' codebase='.' height='90' width='120'>
                                                            <jsp:fallback>加载applet上传组件失败</jsp:fallback>
                                                        </jsp:plugin>
                                                    </td>
                                                    <td>
                                                        <table border="0" width="100%" cellspacing="0" cellpadding="3" style="border-collapse: collapse" bordercolor="#c9e5f1" bgcolor="#feffff" align="center">
                                                            <tr>
                                                                <td>输入课件入口地址</td>
                                                                <td>
                                                                    <form name="courseWare" id="courseWare" action="../fileUpload.do?method=uploadScheduleSingleCourse" enctype="multipart/form-data" method="post">
                                                                        <input name='courseUrl' id='courseUrl' type='text' size='48' value='${courseUrl}'/>
                                                                        <input type='hidden' name='coureId' id="url_courseId" value=''/>
                                                                        <input type='hidden' name='courseTypeId' id="url_courseTypeId" value=''/>
                                                                        <input type='submit' value='确定' id='btnSubmit' onclick='_submitCourseUrl();'/>
                                                                        <div id="previewSchedueCourse" style="display:none">
                                                                            <font color='red'><span id="uploadScheduleCourseUrl"></span></font>
                                                                            <a href="javascript:void(0);">预览</a>
                                                                        </div>
                                                                    </form>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>上传课件缩略图</td>
                                                                <td>
                                                                    <form name="coursePic" id="coursePic" action="fileUpload.do?method=uploadScheduleCoursePicture" enctype="multipart/form-data" method="post">
                                                                        <input name="pictureFile" id="pictureFile" type="file" id="pictureFile" size="48" contenteditable="false"/>
                                                                        <input type=hidden name="theManifest"/>
                                                                        <input type="hidden" name="theZipFile"/>
                                                                        <input type="hidden" name="coureId" id="pic_courseId" value=""/>
                                                                        <input type="hidden" name="courseUrl1" id="courseUrl1"/>
                                                                        <input type="hidden" name="courseTypeId" id="pic_courseTypeId" value=""/>
                                                                        <input type="hidden" name="hidFileID" id="hidFileID" value=""/>
                                                                        <input type="submit" value="确定" id="btnSubmit" onclick="_submitCoursePic();"/>
                                                                        <br/>
                                                                    </form>
                                                                    <font color='red'><span id="uploadScheduleCoursePic"></span></font>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                            <div style="text-align:center; margin:20px;">
                                                <input name="" type="button" value="上一步" onclick="jumpToCourseBasicPanel();"/>
                                                <input type="button" value="下一步" onclick="jumpToCourseOtherFilePanel();"/>
                                            </div>
                                        </div>
                                        <h3><a href="javascript:void(0);">上传辅助资料</a></h3>
                                        <div>
                                            <div id="panel_uploadOtherFile">
                                                <table class="table0" cellspacing="0" width="90%" align="center" cellpadding="0">
                                                    <tr>
                                                        <th>
                                                            <input type="checkbox" id="selectAllFile" name="selectAllFile" onclick="_setSelected();"/>
                                                        </th>
                                                        <th width="80%">
                                                            资料名称
                                                        </th>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <form id="form7" name="form7" action="" method="post">
                                                                <c:if test="${LectureList != null}">
                                                                    <c:forEach var="LectureList" items="${LectureList}">
                                                                        <tr>
                                                                            <td align="center">
                                                                                <input type="checkbox" name="courseFileFormList" value="${LectureList.lectureId}"/>
                                                                            </td>
                                                                            <td>
                                                                                <input type="hidden" name="coureId" id="deleteCoureFileCourseId" value=""/>
                                                                            </td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </c:if>
                                                                <div style="display:none"><input type="submit" id="toDeleteScheduleCourseFile" name="toDeleteScheduleCourseFile"/></div>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                            <table class="table0" cellspacing="0" width="90%" align="center" cellpadding="0">
                                                <tr>
                                                    <td colspan="2" height="20px;">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        （如有本课程相关资料，请上传。文件大小限制在100 MB以内。）
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        <form name="courseDoc" action="<%=basePath%>fileUpload.do?method=uploadScheduleCourseLecture" enctype="multipart/form-data" method="post" id="courseDoc">
                                                            资料名称：
                                                            <input type="text" name="lectrueName" id="lectrueName"/>
                                                            <br/>
                                                            从本机选择资料：
                                                            <input id="lectrueFile" name="lectrueFile" type="file" id="lectrueFile"/>
                                                            <span id="spanButtonPlaceholder"></span> (限100 MB以内 )
                                                            <input type="hidden" name="coureId" id="doc_courseId" value=""/>
                                                            <input type="hidden" name="courseTypeId" id="doc_courseTypeId" value=""/>
                                                            <input type="hidden" name="hidFileID" id="hidFileID" value=""/>
                                                            <input type="submit" name="uploadbutton" value="上传" onclick="_submitOtherFile();"/>
                                                            <br/><font color=red><span id="uploadScheduleCourseOtherFile"></span></font>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </table>
                                            <div style="text-align:center; margin:20px;">
                                                <input name="" type="button" value="上一步" onclick="jumpToSingleCoursePanel();"/>
                                                <input type="button" value="完成" onclick="finshScheduleCourse();"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="scheduleCourse" class="homezonecontent homezonecontentborder">
                                <table class="homecontenttable" rules="cols" width="100%" cellspacing="0" cellpadding="0">
                                    <tr class="tableTh">
                                        <th width="16%"> 课程名称</th>
                                        <th width="10%"> 主讲人</th>
                                        <th width="10%"> 适用岗位</th>
                                        <th width="10%"> 专业领域</th>
                                        <th width="10%"> 关键词</th>
                                        <th width="14%"> 所属机构</th>
                                        <th width="10%">状态</th>
                                    </tr>
                                    <c:if test="${courseFormList != null}">
                                        <c:forEach var="courseForm" items="${courseFormList}">
                                            <tr>
                                                <td align='center'>
                                                    ${courseForm.courseName }
                                                </td>
                                                <td align='center'>
                                                    ${courseForm.creator }
                                                </td>
                                                <td align='center'>
                                                    ${courseForm.courseStations }
                                                </td>
                                                <td align='center'>
                                                    ${courseForm.expertAreas }
                                                </td>
                                                <td align='center'>
                                                    ${courseForm.keyWords }
                                                </td>
                                                <td align='center'>
                                                    ${courseForm.orgName }
                                                </td>
                                                <td align='center'>
                                                    <c:if test="${courseForm.updatable == 1}">
                                                        <a href='javascript:updatePubStatus(${courseForm.course_id})'>
                                                            <div id="pubstatus_${courseForm.course_id}">
                                                                ${courseForm.pubStatus }
                                                            </div>
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${courseForm.updatable == 0}">
                                                        <div id="pubstatus_${courseForm.course_id}">
                                                            ${courseForm.pubStatus }
                                                        </div>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>

<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<!-- InstanceBeginEditable name="head" --><!-- InstanceEndEditable -->
<script>
    $(function () {
            $('#tabs li').click(function () {
                $(this).addClass('selected').siblings().removeClass('selected');
            });
        }
    );
</script>
<script type="text/javascript">
    function detail(cId, ctypeId, trainId) {
        document.location.href = "addLessonInfo.do?method=forUpdateCourse&courseId=" + cId + "&courseTypeId=" + ctypeId + "&trainId=" + trainId;
    }

    function _detail(cid) {
        $.ajax({
            type: 'post',
            url: 'courseCoursetype.do?method=scheduleCoursedetail',
            data: "course_id=" + cid + "",
            dataType: "text",
            success: function (data) {
                var nodeArr = $.parseJSON(data);
                var temp = "", courseType = "", expertArea = "", isOpenCourse = "", suitPost = "", courseTypeId = "";
                var otherFiletemp = "<form id='form7' name='form7' action='' method='post'><table class='table0' cellspacing='0' width='90%' align='center' cellpadding='0'><tr><th><input type='checkbox' id='selectAllFile' name='selectAllFile' onclick='_setSelected()'/></th><th width='80%'>资料名称</th></tr>";
                var courseUrl = "";
                var count = 0;
                $.each(nodeArr, function (i, value) {
                    count = nodeArr[i].count;
                    if (count == -1) {
                        temp += "<option value=" + nodeArr[i].courseTypeId + ">" + nodeArr[i].courseTypeName + "</option>";
                    } else if (count == 0) {
                        courseType = nodeArr[i].courseTypeName;
                        expertArea = nodeArr[i].expertArea;
                        isOpenCourse = nodeArr[i].courseIsOpen;
                        suitPost = nodeArr[i].suitPost;
                        courseTypeId = nodeArr[i].courseTypeId;
                        courseUrl = nodeArr[i].courseUrl;
                        $("#scheduleCourseName").val(nodeArr[i].courseName == 'null' ? "" : nodeArr[i].courseName);
                        $("#scheduleCreateDate").val(nodeArr[i].courseCreate == 'null' ? "" : nodeArr[i].courseCreate);
                        $("#maker").val(nodeArr[i].maker == 'null' ? "" : nodeArr[i].maker);
                        $("#creator").val(nodeArr[i].creator == 'null' ? "" : nodeArr[i].creator);
                        $("#maker").val(nodeArr[i].maker == 'null' ? "" : nodeArr[i].maker);
                        $("#classHour").val(nodeArr[i].courseHour == 'null' ? "" : nodeArr[i].courseHour);
                        $("#keyWords").val(nodeArr[i].keyWords == 'null' ? "" : nodeArr[i].keyWords);
                        $("#suitableObject").val(nodeArr[i].suitableObject == 'null' ? "" : nodeArr[i].suitableObject);
                        $("#mainContent").val(nodeArr[i].mainContent == 'null' ? "" : nodeArr[i].mainContent);
                        $("#remark").val(nodeArr[i].remark == 'null' ? "" : nodeArr[i].remark);
                    } else {
                        otherFiletemp += "<tr><td align='center'><input type='checkbox' name='courseFileFormList' value='" + nodeArr[i].lectureId + "'/></td><td align='center'>" + nodeArr[i].lectureName + "</td></tr>";
                    }
                })
                otherFiletemp += "</table><div style='margin-left:100px'><input type='submit' value='批量删除' onclick='_delete();'></input></div></form>";
                $("#panel_uploadOtherFile").html(otherFiletemp);
                $("#typeId").html(temp);
                count = $("#typeId option").length;
                for (var i = 0; i < count; i++) {
                    if ($("#typeId").get(0).options[i].text == courseType) {
                        $("#typeId").get(0).options[i].selected = true;
                        break;
                    }
                }
                count = $("#expertAreaId option").length;
                for (var j = 0; j < count; j++) {
                    if ($("#expertAreaId").get(0).options[j].text == expertArea) {
                        $("#expertAreaId").get(0).options[j].selected = true;
                        break;
                    }
                }
                $("input:[name=isOpenCourse]:radio").each(function () {
                    if (this.value == isOpenCourse) this.checked = true;
                })
                $("input:[name=suitpost]:checkbox").each(function () {
                    if (suitPost.indexOf(this.value) > -1) this.checked = true;
                })
                $("#form6").attr("action", "addLessonInfo.do?method=editScheduleCourse&courseId=" + cid + "");
                $("#editCourseArea").css("display", "block");
                $("#courseTab_courseId").val(cid);
                $("#courseTab_courseTypeId").val(courseTypeId);
                $("#url_courseId").val($("#courseTab_courseId").val());
                $("#url_courseTypeId").val($("#courseTab_courseTypeId").val());
                $("#pic_courseId").val($("#courseTab_courseId").val());
                $("#pic_courseTypeId").val($("#courseTab_courseTypeId").val());
                $("#doc_courseId").val($("#courseTab_courseId").val());
                $("#doc_courseTypeId").val($("#courseTab_courseTypeId").val());
                $("#deleteCoureFileCourseId").val($("#courseTab_courseId").val());
                $("#courseUrl").val(courseUrl == 'null' ? "" : courseUrl);
            }
        })
    }

    function finshScheduleCourse() {
        $("#accordion").accordion("option", "active", 0);
        $("#editCourseArea").css("display", "none");
        $.post('mtMixTrainScheduleAction.do?method=listScheduleCourse&timestamp=' + new Date().getTime(), function (data) {
            $('#scheduleCourse').html($(data).find('#scheduleCourse').html());
        });
    }

    function insertOrUpdate() {
        var options = {
            beforeSubmit: showRequest,
            success: showResponse
        };
        $('#form6').ajaxForm(options);
    }

    function showResponse(data) {
        _detail(data);
        $("#accordion").accordion("option", "active", 1);
    }

    function jumpToCourseBasicPanel() {
        $("#accordion").accordion("option", "active", 0);
    }

    function jumpToCourseOtherFilePanel() {
        $("#accordion").accordion("option", "active", 2);
    }

    function jumpToSingleCoursePanel() {
        $("#accordion").accordion("option", "active", 1);
    }

    function showRequest() {
        if (document.getElementById("scheduleCourseName").value == "") {
            alert("课程名称不能为空!");
            document.getElementById("scheduleCourseName").focus();
            return false;
        }
        if (document.getElementById("scheduleCreateDate").value == "") {
            alert("创建时间不能为空!");
            document.getElementById("scheduleCreateDate").focus();
            return false;
        }
        var length = document.getElementsByName("suitpost").length;
        var suitpost_flag = 0;
        for (var i = 0; i < length; i++) {
            if (document.getElementsByName("suitpost")[i].checked == true)
                suitpost_flag = 1;
        }
        if (suitpost_flag == 0) {
            alert("课程适用岗位类型不能为空!");
            return false;
        }
        var length = document.getElementById("typeId").options.length;
        var options_flag = 0;
        for (var i = 0; i < length; i++) {
            if (document.getElementById("typeId").options[i].selected && document.getElementById("typeId").options[i].value > 1)
                options_flag = 1;
        }
        if (options_flag == 0) {
            alert("请选择课件类别!");
            return false;
        }
        var length = document.getElementsByName("isOpenCourse").length;
        var isOpenCourse_flag = 0;
        for (var i = 0; i < length; i++) {
            if (document.getElementsByName("isOpenCourse")[i].checked == true)
                isOpenCourse_flag = 1;
        }
        if (isOpenCourse_flag == 0) {
            alert("请选择是否公开课!");
            return false;
        }
        if (document.getElementById("classHour").value == "") {
            alert("学时不能为空!");
            document.getElementById("classHour").focus();
            return false;
        }
        //if(!is_greaterZero(document.getElementById("classHour"),1,"学时只能是大于0的实数")) return false;
        return true;
    }

    function _submitCourseUrl() {
        var options = {
            beforeSubmit: checkValues,
            success: function (data) {
                var courseId = $("#courseTab_courseId").val();
                $("#previewSchedueCourse").css("display", "block").find("a").on('click', function () {
                    window.open('courseStudy.do?method=previewStudy&courseID=' + courseId, '课件预览', 'height=100,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no');
                });
                $("#uploadScheduleCourseUrl").html(data);
            }
        }
        $("#courseWare").ajaxForm(options);

    }

    function checkValues() {
        var defaultValue = $("#courseTab_courseId").val();
        var newValue = document.getElementById("courseUrl").value;
        if (defaultValue.indexOf("http://") > -1) {
            alert("默认课件地址正确,不必修改!");
            document.getElementById("courseUrl").value = defaultValue;
            return false;
        }
        if (document.getElementById("courseUrl").value == "") {
            alert("课件地址不能为空!");
            document.getElementById("courseUrl").focus();
            return false;
        }
    }

    function _submitCoursePic() {
        var options = {
            beforeSubmit: checkPicValues,
            success: function (data) {
                var courseId = $("#courseTab_courseId").val();
                $("#accordion").accordion("option", "active", 1);
                $("#uploadScheduleCoursePic").html(data);
            }
        }
        $("#coursePic").ajaxForm(options);
    }

    function checkPicValues() {
        if (document.getElementById("pictureFile").value == "") {
            alert("缩略图上传地址不能为空!");
            document.getElementById("pictureFile").focus();
            return false;
        } else {
            var picName = document.getElementById("pictureFile").value;
            var pickind = picName.substring(picName.lastIndexOf(".") + 1).toLowerCase();
            if (pickind != "jpg" && pickind != "jpeg" && pickind != "gif" && pickind != "png" && pickind != "bmp") {
                alert("上传图片只能是jpg,jpeg,gif,png,bmp格式!");
                document.getElementById("pictureFile").focus();
                return false;
            }
        }
        if (document.getElementById("courseUrl") != null)
            document.getElementById("courseUrl1").value = document.getElementById("courseUrl").value;
    }

    function _submitOtherFile() {
        var options = {
            beforeSubmit: checkDocValues,
            success: function (data) {
                var nodeArr = $.parseJSON(data);
                var temp = "<form id='form7' name='form7' action='' method='post'><table class='table0' cellspacing='0' width='90%' align='center' cellpadding='0'><tr><th><input type='checkbox' id='selectAllFile' name='selectAllFile' onclick='_setSelected()'/></th><th width='80%'>资料名称</th></tr>";
                $.each(nodeArr, function (i, value) {
                    temp += "<tr><td align='center'><input type='checkbox' name='courseFileFormList' value='" + nodeArr[i].lectureId + "'/></td><td align='center'>" + nodeArr[i].lectureName + "</td></tr>";
                })
                temp += "</table><div style='margin-left:100px'><input type='submit' value='批量删除' onclick='_delete();'></input></div></form>";
                $("#panel_uploadOtherFile").html(temp);
            }
        }
        $("#courseDoc").ajaxForm(options);
    }

    function checkDocValues() {
        if (document.getElementById("lectrueName").value == "") {
            alert("资料名称不能为空!");
            document.getElementById("lectrueName").focus();
            return false;
        }
        if (document.getElementById("lectrueFile").value == "") {
            alert("资料上传地址不能为空!");
            document.getElementById("lectrueFile").focus();
            return false;
        }
    }

    function _delete() {
        var _courseId = $("#courseTab_courseId").val();
        $("#form7").attr("action", "fileUpload.do?method=deleteScheduleLecture&courseId=" + _courseId + "");
        var options = {
            success: function (data) {
                var nodeArr = $.parseJSON(data);
                var temp = "<form id='form7' name='form7' action='' method='post'><table class='table0' cellspacing='0' width='90%' align='center' cellpadding='0'><tr><th><input type='checkbox' id='selectAllFile' name='selectAllFile' onclick='_setSelected()'/></th><th width='80%'>资料名称</th></tr>";
                $.each(nodeArr, function (i, value) {
                    temp += "<tr><td align='center'><input type='checkbox' name='courseFileFormList' value='" + nodeArr[i].lectureId + "'/></td><td align='center'>" + nodeArr[i].lectureName + "</td></tr>";
                })
                temp += "</table><div style='margin-left:100px'><input type='submit' value='批量删除' onclick='_delete();'></input></div></form>";
                $("#panel_uploadOtherFile").html(temp);
            }
        }
        $("#form7").ajaxForm(options);
    }

    function _setSelected() {
        $("#selectAllFile").click(function () {
            if ($(this).attr("checked")) {
                $("input:[name=courseFileFormList]:checkbox").each(function () {
                    $(this).attr("checked", true);
                })
            } else {
                $("input:[name=courseFileFormList]:checkbox").each(function () {
                    $(this).attr("checked", false);
                })
            }
        })
    }
</script>
</body>
</html>