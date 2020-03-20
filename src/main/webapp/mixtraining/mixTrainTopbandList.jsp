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
    <link rel="stylesheet" type="text/css" href="/css/uploadify.css"/>
    <link rel="stylesheet" type="text/css" href="/css/uploadifive.css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <ul id="tabs" style="display:none">
                <li><a href="mtMixTrainIconListAction.do"><span>图标选择</span></a></li>
                <li class="selected"><a href="#tabs"><span>标题背景</span></a></li>
                <div class="clr"></div>
            </ul>
            <div id="mainbody">
                <div class="homezoneall">

                    <div class="homezonehead">
                        <span class="homezonetitle">图标选择</span>
                    </div>
                    <div class="homezonecontent" id="trainMaterialEditor">
                        <div class="content_02" align="center">
                            <div style="display:none">
                                <img src="/image/convert.gif"/><br/><br/>
                                <span style="font-size:12px;color:red;">正在上传与转化，请稍候...</span>
                            </div>
                            <form class="form-horizontal report" id="trainPicEditor">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" id="trainImage">上传培训图片缩略图：</label>
                                    <div class="col-sm-3">
                                        <input type="hidden" name="trainId" value="${train.ID}"/>
                                        <input type="file" name="trainPic" id="trainPicUploader"/>
                                        <p id="imgUploadInfo" style="text-align: left;line-height:2em;"></p>
                                        <div id="imgQueue"></div>
                                    </div>
                                    <div class="col-sm-3 trainPicContainer">
                                        <c:if test="${train.imgUrl != null}">
                                            <img class="trainPic" style="width:270px;height:140px;" src="${train.imgUrl}"/>
                                        </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="homezonehead">
                        <span class="homezonetitle">标题背景选择</span>
                    </div>
                    <div class="homezonecontent" id="bgMaterialEditor">
                        <div class="content_02" align="center">
                            <div style="display:none">
                                <img src="/image/convert.gif"/><br/><br/>
                                <span style="font-size:12px;color:red;">正在上传与转化，请稍候...</span></div>
                            <form class="form-horizontal report" id="bgPicEditor">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" id="bgImage">上传培训图片缩略图：</label>
                                    <div class="col-sm-3">
                                        <input type="hidden" name="trainId" value="${train.ID }"/>
                                        <input type="file" name="bgPic" id="bgPicUploader"/>
                                        <p id="imgUploadInfo" style="text-align: left;line-height:2em;"></p>
                                        <div id="imgQueue1"></div>
                                    </div>
                                    <div class="col-sm-3 bgPicContainer">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="homezoneall">
                    <div class="homezonehead">
                        <span class="homezonetitle">我的背景图片</span>
                    </div>
                    <div class="homezonecontent">
                        <table align="center" class="table1" width="80%" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="25%">&nbsp;&nbsp;</td>
                                <td width="25%">&nbsp;&nbsp;</td>
                                <td width="25%">&nbsp;&nbsp;</td>
                                <td width="25%">&nbsp;&nbsp;</td>
                            </tr>
                            <c:if test="${myList != null }">
                                <tr>
                                    <c:forEach var="it" items="${myList}" varStatus="status">
                                        ${(status.count-1)%4==0?"<tr>":""}
                                        <td>
                                            <img height="50px" width="200px" src='${it.pictureUrl }'/>
                                            <br/>
                                            <a href='../mtMixTrainTopband/selectTopband.do?id=${it.id}'>使用</a>&nbsp;&nbsp;
                                            <a href='../mtMixTrainTopband/deleteSelfTopband.do?id=${it.id}'>删除</a>
                                        </td>
                                        ${status.count%4==0?"</tr>":""}
                                    </c:forEach>
                                </tr>
                            </c:if>
                            <c:if test="${message!=null }">
                                <tr>
                                    <td colspan=4 align=center bgcolor='yellow'>
                                        <font color='red'>
                                                ${message}
                                        </font>
                                    </td>
                                </tr>
                            </c:if>
                        </table>
                    </div>
                </div>
                <div class="homezoneall">
                    <div class="homezonehead">
                        <span class="homezonetitle">通用背景图片</span>
                    </div>
                    <div class="homezonecontent">
                        <table align="center" class="table1" width="80%" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="25%">&nbsp;&nbsp;</td>
                                <td width="25%">&nbsp;&nbsp;</td>
                                <td width="25%">&nbsp;&nbsp;</td>
                                <td width="25%">&nbsp;&nbsp;</td>
                            </tr>
                            <tr>
                                <c:forEach var="it1" items="${shareList}" varStatus="status1">
                                    ${(status1.count-1)%4==0 ? "<tr>" : "" }
                                    <td width="25%">
                                        <img height="50px" width="200px" src='${it1.pictureUrl }'/>
                                        <br/>
                                        <a href='../mtMixTrainTopband/selectTopband.do?id=${it1.id}'>使用</a>
                                    </td>
                                    ${(status1.count)%4==0 ? "</tr>" : "" }
                                </c:forEach>
                            </tr>
                        </table>
                    </div>
                </div>
                <!-- InstanceEndEditable -->
                <div class="clr"></div>
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
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript"> dojo.require("dojo.widget.*");</script>

<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<!-- InstanceBeginEditable name="head" -->
<SCRIPT language="JavaScript" src="/js/widget/window.js"></SCRIPT>
<script type="text/javascript" src="/js/jquery.uploadify.js"></script>
<script type="text/javascript" src="/js/jquery.uploadifive.js"></script>
<script type="text/javascript">
    function uploadpicture() {
        var fileName = document.getElementById("picFile").value;
        if (fileName == "") {
            alert("上传标题背景图片地址不能为空!");
            document.getElementById("picFile").focus();
            return;
        }
        document.getElementById('form5').submit();
    }

    $('#tabs li').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });
</script>
<script type="text/javascript">
    $(function () {

        var supportH5 = $.supportHTML5();
        //培训图片上传
        var $trainMaterialEditor = $("#trainMaterialEditor");
        if ($trainMaterialEditor.length > 0) {
            var errorTip = function () {
                $.tips("无法设置培训封面图片，请确保您选择的是图片文件、大小不超过1M", "系统提示", function () {
                    $("#trainPicEditor #uploadInfo").html("仅支持图片上传");
                    $("#trainPicEditor #imgQueue").empty();
                });
            };
            var uploadSuccess = function (file, data, response) {
                var trainId = $("#trainPicEditor input[name=trainId]").val();
                var resultData = $.parseJSON(data);
                var result = resultData.result;
                var uploadResultInfo = "";
                if (result == "true") {
                    uploadResultInfo = "";
                    $.ajax({
                        data: {"imgUrl": resultData.savePath, "trainId": trainId},
                        url: "onlineTraining.do?method=updateImgUrl",
                        method: "post",
                        dataType: "json",
                        success: function (data) {
                            if (data.status) {
                                uploadResultInfo = "您的培训图片更新成功";
                            } else {
                                uploadResultInfo = "培训图片更新失败";
                            }
                            //$("input[name=mainPicUrl]",".seriesEditorModal").val(resultData.savePath);
                            $("#trainPicEditor #imgUploadInfo").html(uploadResultInfo);
                            setTimeout(function () {
                                $("#trainPicEditor #imgUploadInfo").empty();
                                $("#trainPicEditor #imgQueue").empty();
                            }, 3000);
                            var $coursePic = $("#trainPicEditor .trainPic");
                            if ($coursePic.length == 0) {
                                $("#trainPicEditor .trainPicContainer").html('<img  class="trainPic" style="width:270px;height:140px;"   />');
                            }
                            $("#trainPicEditor .trainPic").attr("src", resultData.savePath);
                        },
                        error: function () {
                            uploadResultInfo = "培训图片更新失败";
                            $("#trainPicEditor #imgUploadInfo").html(uploadResultInfo);
                            setTimeout(function () {
                                $("#trainPicEditor #imgUploadInfo").empty();
                            }, 3000);
                        }
                    })
                } else {
                    if ($.trim(data.cause) != "") {
                        uploadResultInfo = data.cause;
                    }
                }
                $("#trainPicEditor #imgUploadInfo").html(uploadResultInfo);
                $("#trainPicEditor #fileQueue").empty();
            };
            var dialogClose = function (queueData) {
                if (queueData.filesErrored > 0) {
                    uploadAction.errorTip();
                }
            };
            var onUploadStart = function (file) {
                $("#trainPicEditor #uploadInfo").html("");
            };
            if (supportH5) {
                $("#trainPicEditor #trainPicUploader").uploadifive({
                    'uploadScript': "filesToolAction.do?method=uploadPic&picWidth=270&picHeight=154&uploadType=4",
                    'buttonText': '图片上传',
                    'queueID': 'imgQueue',
                    'multi': false,
                    'fileType': 'image/*',
                    'fileSizeLimit': "1MB",
                    'onError': errorTip,
                    'onDialogClose': dialogClose,
                    'onUploadComplete': uploadSuccess
                });
            } else {
                //绑定动作到相片上传操作
                $("#trainPicEditor #trainPicUploader").uploadify({
                    'overrideEvents': ['onDialogClose'],
                    'method': 'get',
                    'swf': './js/uploadify.swf',
                    'fileTypeExts': '*.jpg;*.png;*.bmp;*.jpeg',
                    'uploader': 'filesToolAction.do?method=uploadPic&uploadType=4&picWidth=270&picHeight=154',
                    'cancelImg': 'image/cancel.png',
                    'fileSizeLimit': "1MB",
                    'auto': true,
                    'multi': false,
                    "formData": {'picWidth': 450, 'picHeight': 240},
                    'buttonText': '图片上传',
                    'onUploadStart': onUploadStart,
                    'queueID': 'imgQueue',
                    'successTimeout': 6000,
                    'onDialogClose': dialogClose,
                    'onUploadSuccess': uploadSuccess
                });
            }
        }

        //培训背景图片上传
        var $bgMaterialEditor = $("#bgMaterialEditor");
        if ($bgMaterialEditor.length > 0) {
            var errorTip = function () {
                $.tips("无法设置培训封面图片，请确保您选择的是图片文件、大小不超过1M", "系统提示", function () {
                    $("#bgPicEditor #uploadInfo").html("仅支持图片上传");
                    $("#bgPicEditor #imgQueue").empty();
                });
            };
            var uploadSuccess = function (file, data, response) {
                var trainId = $("#bgPicEditor input[name=trainId]").val();
                var resultData = $.parseJSON(data);
                var result = resultData.result;
                var uploadResultInfo = "";
                if (result == "true") {
                    uploadResultInfo = "";
                    location.href = "mtMixTrainTopbandAction.do?method=savePicture&pictureUrl=" + resultData.savePath;
                } else {
                    if ($.trim(data.cause) != "") {
                        uploadResultInfo = data.cause;
                    }
                }
                $("#bgPicEditor #imgUploadInfo").html(uploadResultInfo);
                $("#bgPicEditor #fileQueue").empty();
            };
            var dialogClose = function (queueData) {
                if (queueData.filesErrored > 0) {
                    uploadAction.errorTip();
                }
            };
            var onUploadStart = function (file) {
                $("#bgPicEditor #uploadInfo").html("");
            };
            if (supportH5) {
                $("#bgPicEditor #bgPicUploader").uploadifive({
                    'uploadScript': "filesToolAction.do?method=uploadPic&picWidth=960&picHeight=245&uploadType=4",
                    'buttonText': '图片上传',
                    'queueID': 'imgQueue1',
                    'multi': false,
                    'fileType': 'image/*',
                    'fileSizeLimit': "1MB",
                    'onError': errorTip,
                    'onDialogClose': dialogClose,
                    'onUploadComplete': uploadSuccess
                });
            } else {
                //绑定动作到相片上传操作
                $("#trainPicturePicEditor #trainPicturePicUploader").uploadify({
                    'overrideEvents': ['onDialogClose'],
                    'method': 'get',
                    'swf': './js/uploadify.swf',
                    'fileTypeExts': '*.jpg;*.png;*.bmp;*.jpeg',
                    'uploader': 'filesToolAction.do?method=uploadPic&uploadType=4&picWidth=960&picHeight=245',
                    'cancelImg': 'image/cancel.png',
                    'fileSizeLimit': "1MB",
                    'auto': true,
                    'multi': false,
                    "formData": {'picWidth': 450, 'picHeight': 240},
                    'buttonText': '图片上传',
                    'onUploadStart': onUploadStart,
                    'queueID': 'imgQueue1',
                    'successTimeout': 6000,
                    'onDialogClose': dialogClose,
                    'onUploadSuccess': uploadSuccess
                });
            }
        }
    });
</script>
</body>
</html>