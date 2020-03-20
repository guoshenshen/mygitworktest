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
    <style type="text/css">
        .templateNavigation {
            background-color: #000;
            opacity: .6;
            display: block;
        }
        .templateNavigation div {
            width: 80%;
            float: left;
            margin-top: 20px;
            margin-left: 120px;
        }
        .templateNavigation div a {
            margin-right: 30px;
            opacity: 1;
        }
    </style>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/simpleModel.css" rel="stylesheet" type="text/css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <ul id="tabs">
                <li class="selected"><a href="#tabs"><span>培训证书</span></a></li>
                <li><a href="../mtMixTrainCertificate/grantTrainCertificateManage.do"><span>发证管理</span></a></li>
                <div class='clr'></div>
            </ul>
            <div id="mainbody">
                <div class="homezoneall" style="display:none">
                    <div class="homezonehead">
                        <span class="homezonetitle">证书样式上传</span>
                    </div>
                    <div class="homezonecontent">
                        <form action="../mtMixTrainCertificate/upload.do" method="post" id="form5" ENCTYPE="multipart/form-data" name="form5">
                            <table align="center" class="table1" width="80%" cellspacing="0" cellpadding="0">
                                <tr>
                                    <th align="left" width="30%">1.选择证书样式图片:</th>
                                </tr>
                                <tr>
                                    <td align="left" width="30%">
                                        <input type="file" name="picFile" id="picFile" size=30/>
                                    </td>
                                </tr>
                                <tr>
                                    <th align="left" width="30%">2.模板版式:</th>
                                </tr>
                                <tr>
                                    <td>
                                        <label><input type="radio" name="certificateType" value="0" checked/>横版</label>
                                        <label><input type="radio" name="certificateType" value="1"/>竖版 </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <span style="color:red;" id="lStyle">(横版的尺寸为944px*678px)</span>
                                        <span style="color:red;display:none;" id="vStyle">(竖版的尺寸为419px*573px)</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align='left'>
                                        <div class="btnContainer">
                                            <a href="javascript:void(0);" onclick="javascript:uploadpicture();" class="btn-blue-l">
                                                <span class="btn-blue-r">上&nbsp;传</span>
                                            </a>
                                        </div>
                                        <br/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
                <div class="homezoneall">
                    <div class="homezonehead">
                        <span class="homezonetitle">证书编辑</span>
                    </div>
                    <div class="homezonecontent">
                        <form action="../mtMixTrainCertificate/createTrainCertificate.do" method="post" id="form1" name="form1">
                            <table align="center" class="table1" width="90%" cellspacing="0" cellpadding="0">
                                <tr>
                                    <th align=right width="30%">1.请选择证书样式</th>
                                    <td width="60%">
                                        <div>
                                            <a class="btn-blue-l" href="javascript:void(0);" onclick="javascript:chooseTemplate();">
                                                <span class="btn-blue-r">模板选择</span>
                                            </a>
                                            <input type="hidden" name="certificateTemplateId" id="certificateTemplateId"
                                                   value="<c:if test="${trainCertificate != null}">${trainCertificate.certificateTemplateId}</c:if>"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <th align=right width="30%">2.请确认证书内容</th>
                                    <td>
                                        <textarea rows="5" cols="80" name="certificateContent" id="certificateContent">
                                            <c:if test="${trainCertificate == null}">
                                                您于<fmt:formatDate value="${train.startTime }" type="both" pattern="yyyy年MM月dd日"/>
                                                至<fmt:formatDate value="${train.endTime }" type="both" pattern="yyyy年MM月dd日"/>
                                                ，参加由${train.sponsorName}主办的“${train.trainName }”，经考核达到结业要求，特发此证。
                                            </c:if>
                                            <c:if test="${trainCertificate != null}">
                                                ${trainCertificate.certificateContent}
                                            </c:if>
                                        </textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th align=right width="30%">3.请确认单位名称</th>
                                    <td>
                                        <input type="text" name="certificateOrgName"
                                            value="
                                        <c:if test="${trainCertificate != null}">${trainCertificate.certificateOrgName}</c:if>
                                        <c:if test="${trainCertificate == null}">${train.sponsorName }</c:if>
                                         " id="certificateOrgName" class="textInput longInput"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th align=right width="30%">4.请选择证书日期</th>
                                    <td>
                                        <input name="certificateDate"
                                               value="<c:if test="${trainCertificate != null}">${trainCertificate.certificateDate}</c:if>"
                                               class="Wdate textInput shortInput rightMar" type="text"
                                               id="certificateDate" onFocus="WdatePicker({dateFmt:'yyyy年MM月dd日'})" size=15/>
                                    </td>
                                </tr>
                            </table>
                            <div style="margin:40px 0 40px 40%;">
                                <input type="button" name="trainCertificate" value="生成培训证书" onclick="javascript:onSubmit();"/>
                            </div>
                            <input type="hidden" name="operatorId" id="operatorId" value=${operator.operatorId}/>
                            <input type="hidden" name="orgId" id="orgId" value=${orgId}/>
                        </form>
                    </div>
                </div>
                <div class="homezoneall">
                    <div class="homezonehead">
                        <span class="homezonetitle">培训证书</span>
                    </div>
                    <div class="homezonecontent"
                         style="width:100%;height:70px; padding-left: 350px;padding-top: 12px;background-color: rgb(255, 250, 205);">
                        <div id="trainCertificate" style="margin:0px auto;text-align:center; padding-bottom:20px;">
                            <c:if test="${trainCertificate != null}">
                                <a class="btn-blue-l" href="javascript:void(0);" onclick="javascript:window.open('${trainCertificate.certificateUrl }')" style="margin-right:30px">
                                    <span class="btn-blue-r">预览</span>
                                </a>
                                <a class="btn-blue-l" href="javascript:void(0);" onclick="javascript:verifyDeleteTrainCertificate(${trainCertificate.id });">
                                    <span class="btn-blue-r">删除</span>
                                </a>
                            </c:if>
                        </div>
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

<div id="_preview" style="display:none;cursor:default;overflow:hidden;">
    <div id='_pop_win'><h2>预览证书<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2></div>
    <br/>
    <div id="_preview_table" style="height:500px;"><img src=""/></div>
</div>

<div id="showTemplatePreview" class="filling-container;" style="display:none">
    <div style="background:url(/image/popupbg.png) 0 0 no-repeat;height:36px;">
        <a href="javascript:void(0);" onclick="javascript:closeTemplatePreviewWin();" class="pop_close_btn" style="top: 6px;position:absolute;right:7px;display: block;">X</a>
    </div>
    <div id="buffer" style="float:left;width:50%;height:50%;background:url(/image/buffer2.gif);margin-left: 200px;margin-top:60px;background-repeat:no-repeat"></div>
    <div id="templateFolder" style="float:left;width:100%;height:80%;display:none;font-size:15px;position:relative;">
        <div id="currentTemplate" style="float:left;width:100%;height:90%">
            <a><img style="height:100%;width:100%;max-width:600px;max-height:360px;cursor:pointer;" alt=""/></a>
        </div>
        <div id="templateNavigator" style="width:100%;height:20%;float:left;position:absolute;bottom:0px;" class="templateNavigation">
            <div>
                <a class="btn-blue-l" href="javascript:void(0);" onclick="javascript:lastTemplate();">
                    <span class="btn-blue-r">上一张</span>
                </a>
                <a class="btn-blue-l" href="javascript:void(0);" onclick="javascript:confirmTemplate();">
                    <span class="btn-blue-r">确定</span>
                </a>
                <a class="btn-blue-l" href="javascript:void(0);" onclick="#;"><span class="btn-blue-r">删除</span></a>
                <a class="btn-blue-l" href="javascript:void(0);" onclick="javascript:nextTemplate();">
                    <span class="btn-blue-r">下一张</span>
                </a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script language="javascript" src="/js/jquery.simplemodal.1.4.4.min.js"></script>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<!-- InstanceBeginEditable name="head" -->
<script type="text/javascript" src="/js/widget/window.js"></script>
<script type="text/javascript">
    var $modal = null;
    var certificateTemplates = new Array();
    var currentTemplateIndex = 0;

    //证书模板对象
    function certificateTemplate(id, url, name, certificateTemplateType) {
        this.id = id;
        this.url = url;
        this.name = name;
        //0:横版;1:竖版
        this.certificateTemplateType = certificateTemplateType;
    }
</script>

<script type="text/javascript">
    function uploadpicture() {
        var fileName = document.getElementById("picFile").value;
        if (fileName == "") {
            alert("上传证书背景图片地址不能为空!");
            document.getElementById("picFile").focus();
            return;
        }
        document.getElementById('form5').submit();
    }

    $('#tabs li').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    $(function () {
        $('input[name=certificateType]').bind('click', function () {
            var flag = $('input[name=certificateType]:checked').val();
            if (flag == 0) {
                $('#lStyle').css('display', "block");
                $('#vStyle').css('display', "none");
            } else {
                $('#lStyle').css('display', "none");
                $('#vStyle').css('display', "block");
            }
        });
        $("#templateFolder").bind("mouseover", function () {
            $("#templateNavigator").show();
        });
        $("#templateFolder").bind("mouseout", function () {
            $("#templateNavigator").hide();
        });
    })

    function showTemplate() {
        $("#showTemplatePreview #currentTemplate img").attr("src", certificateTemplates[currentTemplateIndex].url);
        $("#showTemplatePreview #currentTemplate img").attr("alt", certificateTemplates[currentTemplateIndex].name);
        $("#showTemplatePreview #currentTemplate img").attr("title", certificateTemplates[currentTemplateIndex].name);
        var templateDetail = "javascript:window.open('" + certificateTemplates[currentTemplateIndex].url + "')";
        $("#showTemplatePreview #currentTemplate a").attr("onclick", templateDetail);
    }

    function lastTemplate() {
        //显示前一张培训证书
        if (certificateTemplates.length > 0) {
            currentTemplateIndex = currentTemplateIndex - 1 + certificateTemplates.length;
            currentTemplateIndex = (currentTemplateIndex) % (certificateTemplates.length);
            showTemplate();
        }
    }

    function nextTemplate() {
        //显示后一张培训证书
        if (certificateTemplates.length > 0) {
            currentTemplateIndex++;
            currentTemplateIndex = (currentTemplateIndex) % (certificateTemplates.length);
            showTemplate();
        }
    }

    function confirmTemplate() {
        //确定培训证书模板
        if (certificateTemplates.length > 0) {
            $("#certificateTemplateId").val(certificateTemplates[currentTemplateIndex].id);
        }
        closeTemplatePreviewWin();
    }

    //选择证书模板
    function chooseTemplate() {
        $modal = $("#showTemplatePreview").modal({
            "opacity": 60,
            "overlayClose": false,
            "containerId": "window-frame-base",
            "close": false
        });
        $("#window-frame-base").css("width", "600px");
        $("#window-frame-base").css("height", "400px");
        certificateTemplates = new Array();
        if (certificateTemplates.length == 0) {
            var orgIdData = $("#orgId").val();
            var operatorIdData = $("#operatorId").val();
            $.ajax({
                type: "post",
                url: "mtMixTrainCertificateAction.do?method=getCertificateTemplate",
                dataType: "json",
                success: function (data) {
                    for (var i in data) {
                        var certificate = new certificateTemplate(data[i].id, data[i].url, data[i].name, data[i].certificateTemplateType);
                        certificateTemplates.push(certificate);
                    }
                    if (data.length > 0) {
                        $("#showTemplatePreview #buffer").hide();
                        showTemplate();
                        $("#showTemplatePreview #templateFolder").show();
                    }
                },
                data: {"orgId": orgIdData, "operatorId": operatorIdData}
            })
        }
    }

    function closeTemplatePreviewWin() {
        $modal.close();
    }

    function onSubmit() {
        var submitFlag = true;
        var coverFlag = submitFlag;
        //var flag=$('input[name=certificateTemplateId]:checked').val();
        var flag = $('input[name=certificateTemplateId]').val();
        if (flag == "" || flag == null) {
            alert("请选择证书模板！");
            submitFlag = false;
            return false;
        }

        if ($('#certificateDate').val() == "") {
            alert("请选择培训证书日期！");
            submitFlag = false;
            return false;
        }
        if ($('#trainCertificate').html().trim() != "") {
            coverFlag = confirm("此培训已有培训证书，是否重新生成，替换它？");
        }

        if (submitFlag && coverFlag) {
            $('#form1').submit();
        }
    }

    function verifyDeleteCertificateTemplate(ctid) {
        if (confirm("确认要删除此培训证书背景吗?")) {
            document.location.href = "mtMixTrainCertificateAction.do?method=deleteCertificateBg&ctId=" + ctid;
        }
    }

    function verifyDeleteTrainCertificate(cid) {
        if (confirm("确认要删除此培训证书吗?")) {
            $.ajax({
                url: "mtMixTrainCertificateAction.do?method=getGrantTrainCertificateStatus",
                type: "post",
                dataType: "text",
                data: {"certificateType": 0},
                success: function (data, evt) {
                    if (data == "") {
                        document.location.href = "mtMixTrainCertificateAction.do?method=deleteTrainCertificate&cId=" + cid;
                    } else{
                        alert(data);
                    }
                }
            })
        }
    }
</script>
<script type="text/javascript">
    function previewImage(cid, type) {
        var picUrl = "";
        if (type == 0){
            picUrl = $('#b_' + cid).attr('src');
        } else{
            picUrl = $('#c_' + cid).attr('src');
        }
        $.ajax({
            url: "mtMixTrainCertificateAction.do?method=getCertificateStyle",
            type: "post",
            dataType: "text",
            data: {cId: cid, cType: type},
            success: function (data, evt) {
                if (data == 0) {
                    $('#_preview_table img').attr({src: picUrl, width: '80%', height: '80%'});
                } else if (data == 1)
                    $('#_preview_table img').attr({src: picUrl, width: '50%', height: '80%'});
                $.blockUI({message: $('#_preview'), css: {width: '90%', height: '90%', top: '5%', left: '5%'}});
                $("a.pop_close_btn").click(function () {
                    $.unblockUI();
                });
            }
        })
    }
</script>
</body>
</html>