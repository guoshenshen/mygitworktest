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
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/uploadify.css"/>
    <link rel="stylesheet" type="text/css" href="/css/uploadifive.css"/>
    <link rel="stylesheet" type="text/css" href="/css/skinCss/skin.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.navigation.css"/>
    <link rel="stylesheet" type="text/css" href="/css/skinCss/icon.css"/>
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/public/material.css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <div id="albumInfo" class="adminStyle">
                <div class="homezoneall">
                    <div id="content_single">
                        <div class="headtitle">
                            <img hspace="4" align="absmiddle" src="/image/folder_table.png"/>
                            班级相册
                        </div>
                    </div>
                    <div id="albumContainer" class="albumContainer">
                        <div class="albumHiddenInfo" style="display:none">
                            <input type="hidden" name="resourceId" value="${resourceId}"/>
                            <input type="hidden" name="type" value="${type}"/>
                        </div>
                        <ul id="albumList" class="albumList">
                            <li class="albumItem">
                                <div class="leftRotate photoSkin"></div>
                                <div class="rightRotate photoSkin"></div>
                                <div class="noneRotate photoSkin">
                                    <img src="/image/material/albumBackground.jpg"/>
                                    <span class="albumTag">新建相册</span>
                                    <input type="hidden" name="albumId" value="${album.id}"/>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>
<div class="albumEditor noBorder" data-remodal-id="albumEditor" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close"></button>
    </div>
    <div class="wrapper common">
        <input type="text" name="albumName" class="albumName"/>
        <p class="tip">相册名称</p>
        <input type="hidden" name="albumId"/>
        <textarea class="albumDescription" name="albumDescription"></textarea>
        <p class="tip">相册描述</p>
        <button data-remodal-action="cancel" class="remodal-cancel">取消</button>
        <button data-remodal-action="confirm" class="remodal-confirm">确定</button>
    </div>
</div>

<div class="uploader" data-remodal-id="uploaderPanel" role="dialog" aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <button data-remodal-action="close" class="remodal-close"></button>
    <div id="uploadInfoContainer">
        <input type="file" name="file_upload" id="file_upload"/>
        <p id="uploadInfo"></p>
        <div id="fileQueue"></div>
    </div>
</div>


<div class="photoContainer noBorder" data-remodal-id="photoContainer" role='dialog' aria-labelledby='modal1Title'
     aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close"></button>
    </div>
    <div class="wrapper">
        <div id="photoDisplayer">
            <div id="currentPhoto" class="notCursor noPhoto">
                <img src="/image/material/empty.jpg"/>
                <div id="currentPhotoTitle">
                    <div class="bufferMask"></div>
                    <span class="photoTitle"></span>
                    <span class="photoContent"></span>
                    <input type="text" class="editPhotoTitle"/>
                    <textarea class="editPhotoContent"></textarea>
                    <input type="hidden" name="photoId" value=""/>
                    <input type="hidden" name="albumId" value=""/>
                </div>
                <div id="currentPhotoPower"></div>
                <span class="save" title="保存">保存</span>
                <span class="edit" title="编辑"></span>
                <span class="unfavourite" title="设为封面"></span>
            </div>
            <div id="otherPhotoList">
                <div id="photoBar">
                </div>
            </div>
        </div>
        <ul class="vertical-menu float_vertical_menu">
            <li class="green" title="图片上传" width="33.4%" id="uploadPhoto">
                <span class="upload-icon"></span>
                <a href="javascript:void(0);"><span>图片上传</span></a>
            </li>
            <li class="red" title="图片下载" width="33.4%" id="downloadPhoto">
                <span class="download-icon"></span>
                <a href="javascript:void(0);"><span>图片下载</span></a>
            </li>
        </ul>
        <span class="toLeft"></span>
        <span class="toRight"></span>
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

<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript"> dojo.require("dojo.widget.*");</script>
<script type="text/javascript" src="/js/widget/window.js"></script>
<script type="text/javascript" src="/js/swfobject.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.js"></script>
<script type="text/javascript" src="/js/jquery.uploadifive.min.js"></script>

<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/material/album.js"></script>
</body>
</html>