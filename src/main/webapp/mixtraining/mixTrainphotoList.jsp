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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${train.trainName}</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link id="rsmRcmbook" href="/css/resourceManage/rsmRcmbook.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css"/>
    <link href="/css/trainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/newTrainClass.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.navigation.css"/>
    <link rel="stylesheet" type="text/css" href="/css/skinCss/icon.css"/>
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/public/material.css"/>
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="/js/common/tools.js"></script>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/compatible/json2.min.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/nav/trainClassMenu.js"></script>
    <script type="text/javascript" src="/js/nav/umenu.js"></script>
    <script type="text/javascript" src="/js/UI/remodal.min.js"></script>
    <script type="text/javascript" src="/js/material/album.js"></script>
    <script type="text/javascript" src="/js/login/login.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#stopUse').find('li:eq(6)').find('a').css({'color': '#FF7800', 'font-weight': 'bold'})
        })
    </script>
</head>
<body>
<div id="backgroundImg"><img src=''/></div>
<div id='bufferMask'></div>
<div id="pagebody">
    <div id="headerbody">
        <div id="head2">
            <input type="hidden" value="${train.topbandId}"/>
            <input type="hidden" id="_trainId" value="${train.ID}"/>
            <input type="hidden" id="trainName" value="${train.trainName}"/>
            <input value="${operator.operatorId}" id="_userId" type="hidden"/>
        </div>
        <div id="head-content">
            <p id="title">${train.trainName }</p>
            <p id="time">时间：
                <fmt:formatDate value="${train.startTime }" pattern="yyyy-MM-dd HH:mm"/>
                ~
                <fmt:formatDate value="${train.endTime }" pattern="yyyy-MM-dd HH:mm"/>
            </p>
            <p id="location">
                <c:if test="${train.location != null}">
                    地点：${train.location }
                </c:if>
            </p>
        </div>
        <div id="menubar">
            <div class='pagebodybufferMask'></div>
            <span id="joinTrainFlag" style="display:none;">${joinTrainFlag }</span>
            <div id="trainClassMenu">
                <ul></ul>
            </div>
        </div>
    </div>
    <div id="mainbody">
        <div class='pagebodybufferMask'></div>
        <div class="mainContent">
            <div class="emptyInfo" style="display:none;">暂无培训照片...</div>
            <div id="albumInfo" class="userStyle">
                <div id="albumContainer" class="albumContainer">
                    <div class="albumHiddenInfo" style="display:none">
                        <input type="hidden" name="resourceId" value="${resourceId}"/>
                        <input type="hidden" name="type" value="${type}"/>
                    </div>
                    <ul id="albumList" class="albumList">
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div id="simplefoot"></div>
</div>
<div id="simpleNavBar" style="position:fixed;top:0px;"></div>
<div class="photoContainer noBorder" data-remodal-id="photoContainer" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close"></button>
    </div>
    <div class="wrapper">
        <div id="photoDisplayer">
            <div id="currentPhoto" class="notCursor noPhoto">
                <img src=""/>
                <div id="currentPhotoTitle">
                    <div class="bufferMask"></div>
                    <span class="photoTitle"></span>
                    <span class="photoContent"></span>
                    <input type="hidden" name="photoId" value=""/>
                    <input type="hidden" name="albumId" value=""/>
                </div>
                <div id="currentPhotoPower"></div>
            </div>
            <div id="otherPhotoList">
                <div id="photoBar">
                </div>
            </div>
        </div>
        <ul class="vertical-menu float_vertical_menu">
            <li class="red" title="图片下载" width="33.4%" id="downloadPhoto">
                <span class="download-icon"></span>
                <a href="javascript:void(0);"><span>图片下载</span></a>
            </li>
        </ul>
        <span class="toLeft"></span>
        <span class="toRight"></span>
    </div>
</div>
<script type="text/javascript">
    albumArea.functionAfterAlbumLoaded = function () {
        if ($("#albumList li").length == 0) {
            $(".mainContent .emptyInfo").show();
        }
        else {
            $(".userStyle #albumContainer").show();
        }
    }
</script>
</body>
</html>
