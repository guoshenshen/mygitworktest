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
    <title><%=Constants.systemName%>
    </title>

    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="style_td_Id" id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link id="rsmRcmbook" href="/css/resourceManage/rsmRcmbook.css" rel="stylesheet" type="text/css"/>
    <link href="/css/trainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/newTrainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/common/tools.js"></script>
    <script type="text/javascript" src="/js/dojojs/dojo.js"></script>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/jquery-json.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/UI/remodal.min.js"></script>
    <script type="text/javascript" src="/js/nav/trainClassMenu.js"></script>
    <script type="text/javascript" src="/js/nav/roll.js"></script>
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet" type="text/css"/>
    <script language='javascript'>
        function previewResult(tnId) {
            var url = "tnNeedsAction.do?method=myTnNeedsResult&tnId=" + tnId + "&flag=xy";
            window.open(url, '查看结果', 'height=400, width=800, top=200, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
        }
        $(document).ready(function () {
            $('#stopUse').find('li:eq(2)').find('a').css({'color': '#FF7800', 'font-weight': 'bold'})
        })
        $(function () {
            var url = ""
            if ($("#head2 input").val() != "")
                url = "/image/picturebase/" + $("#head2 input").val() + ".jpg";
            else
                url = "/image/picturebase/4.jpg";
        });

        $(function () {
            var options = {};
            var browserType = searchBrowserTypeAndVersion();
            if (!browserType) {
                $(".newsTitle").click(function () {
                    options.newsId = $(this).find(".newsId").val();
                    options.trainId = $(this).find(".trainId").val();
                    openLink("mtMixTrainNewsAction.do?method=getNewsDetail", options, {"target": "_blank"})
                });
            } else {
                showTrainNewsDetailInfoWin(options);
                $(".newsTitle").click(function () {
                    options.newsId = $(this).find(".newsId").val();
                })
            }
        })
    </script>
</head>
<body>
<div id="backgroundImg"><img src=''/></div>
<div id='bufferMask'></div>
<div id="pagebody">
    <div id="headerbody">
        <div id="head2">
            <input type="hidden" value="${train.topbandId }"/>
            <input type="hidden" value="${train.ID }" id="_trainId"/>
        </div>
        <div id="head-content" class="outstanding">
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
        <div id="content_02" class="mainContent">
            <c:if test="${trainNewsList == null}">
                <div class="emptyInfo">暂无新闻动态...</div>
            </c:if>
            <form id="form1" name="form1" method="post">
                <ul class="notice-list">
                    <c:if test="${trainNewsList != null}">
                        <c:forEach var="trainNews" items="${trainNewsList}" varStatus="status">
                            <li>
                                <p>
                                    <a data-remodal-target="modal" href="javascript:void(0);" class='newsTitle'>
                                        <input type='hidden' class="newsId" value="${trainNews.newsId}"/>
                                        <input type='hidden' class="trainId" value="${trainNews.trainId}"/>
                                        ${trainNews.newsTitle }
                                    </a>
                                </p>
                                <span style="color:#5B5B5B">[<fmt:formatDate value="${trainNews.createTime }" pattern="yyyy-MM-dd"/>]</span>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </form>
        </div>
        <div class="clr"></div>
    </div>
    <div id="bottombody">
        <div class='pagebodybufferMask'></div>
        <%=Constants.systemBottom %>
    </div>
</div>
<div class='remodal' data-remodal-id='modal' role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <button data-remodal-action='close' class='remodal-close' aria-label="Close"></button>
    <div>
        <div id="notice-content">
            <div class="notice-header">
                <p class="notice-title" style="text-align:center">${mtMixTrainnews.newsTitle}</p>
                <p class="notice-detail">
                    <span class='notice-time'></span>&nbsp;&nbsp;|
                    【<span class="notice-operation">
                    <a href="javascript:void(0);" onclick="javascript:notShowNews()">关闭</a></span>】
                </p>
            </div>
            <div class="notice-body" id="noticeContent" style="font-size:13px">
                ${mtMixTrainnews.newsContent}
            </div>
        </div>
    </div>
</div>
<div id="foot"></div>
<div id="simpleNavBar" style="position:fixed;top:0px;"></div>
<script type="text/javascript" src="/js/nav/umenu.js"></script>
</body>
</html>