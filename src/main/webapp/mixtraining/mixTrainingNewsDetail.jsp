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
    <title></title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css"/>
    <link href="/css/login/login.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <style>
        .student {
            background: #EEEEEE;
        }

        .content {
            width: 1205px;
            /*margin:80px auto 20px;*/
            margin: 0 auto;
            background: #fff;
        }

        .Headlines {
            width: 96%;
            height: 140px;
            padding-top: 20px;
            border-bottom: 1px solid #bbb;
            margin: auto;
            margin-bottom: 20px;
        }

        .Headlines span {
            width: 800px;
            padding: 0px 20px;
            font-size: 24px;
            display: block;
            text-align: center;
            /* white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            */
        }

        .Headlines .time {
            font-size: 14px;
            margin-top: 40px;
        }

        .banner-left {
            width: 820px;
            float: left;
            margin-left: 10px;
            padding-right: 20px;
            padding-left: 20px;
        }

        .banner-left p {
            font-size: 16px;
            line-height: 32px;
            font-family: "Microsoft YaHei";
            text-align: justify;
        }
        .banner-right {
            width: 340px;
            float: right;
            margin-right: 10px;
        }
        .right_MoreNews {
            width: 100%;
            height: 225px;

        }
        .rm-title {
            height: 20px;
            border-left: 8px solid #27649c;
            text-align: left;
        }
        .rm_tit_g {
            font-size: 18px;
            color: #000;
            font-weight: 600;
            padding: 0 10px;
            background: #fff;
            cursor: pointer;
            text-align: left;
        }
        .right-ul {
        }
        .right-ul li {
            height: 36px;
            line-height: 36px;
            padding-left: 10px;
            font-size: 16px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .right-ul li a {
            color: black;
        }
        .lit-Carousel .pic {
            width: 320px;
            margin: 0px auto;
            height: 200px;
            background: green;
            margin-top: 20px;
        }
        /* ---------轮播css---- */
        .example {
            width: 300px;
            height: 190px;
            font-size: 40px;
            text-align: center;
            margin: 20px auto;
            background-color: #464576;
        }
        .carousel-item {
            line-height: 0px;
            color: #fff;
            font-family: Arial Black
        }
        ul,
        ol,
        li,
        div {
            margin: 0;
            padding: 0;
        }
        ul,
        ol {
            list-style: none;
        }
        .ft-carousel {
            position: relative;
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
        .ft-carousel .carousel-inner {
            position: absolute;
            left: 0;
            top: 0;
            height: 100%;
        }
        .ft-carousel .carousel-inner .carousel-item {
            float: left;
            height: 100%;
        }
        .ft-carousel .carousel-item img {
            width: 100%;
            height: 100%;
        }
        .ft-carousel .carousel-indicators {
            position: absolute;
            left: 96px;
            bottom: 10px;
            width: 100%;
            text-align: center;
            font-size: 0;
        }
        .ft-carousel .carousel-indicators span {
            display: inline-block;
            width: 10px;
            height: 10px;
            background-color: #fff;
            margin: 0 4px;
            border-radius: 50%;
            cursor: pointer;
        }
        .ft-carousel .carousel-indicators span.active {
            background-color: #de3a3a;
        }
        .ft-carousel .carousel-btn {
            position: absolute;
            top: 50%;
            width: 50px;
            height: 45px;

            cursor: pointer;
        }
        .ft-carousel .carousel-prev-btn {
            left: -10px;
        }
        .ft-carousel .carousel-next-btn {
            right: -10px;
        }
        .fa-chevron-left, .fa-chevron-right {
            color: white;
            font-size: 18px;
        }
    </style>
</head>
<body class="student">
<div class="mainContent">
    <div class="content">
        <div class="Headlines">
            <c:if test="${mtMixTrainnews != null}">
                <span>${mtMixTrainnews.newsTitle }</span>
                <span class="time">发布日期：<fmt:formatDate type="both" value="${mtMixTrainnews.createTime }"/></span>
            </c:if>
            <c:if test="${msgMessageInfo != null}">
                <span>${msgMessageInfo.title }</span>
                <span class="time">发布日期：<fmt:formatDate type="both" value="${msgMessageInfo.validDate }"/></span>
            </c:if>
        </div>
        <div class="banner">
            <div class="banner-left">
                <c:if test="${mtMixTrainnews != null}">
                    ${mtMixTrainnews.newsContent }
                </c:if>
                <c:if test="${msgMessageInfo != null}">
                    ${msgMessageInfo.content }
                </c:if>
            </div>
            <div class="banner-right">
                <div class="right_MoreNews">
                    <div class="rm-title">
                        <span class="rm_tit_g">
                            <c:if test="${mtMixTrainnews != null}">最新新闻</c:if>
                            <c:if test="${msgMessageInfo != null}">最新通知</c:if>
                        </span>
                    </div>
                    <ul class="right-ul" style="margin-top:18px;padding-top:5px">
                        <c:if test="${mtMixTrainnews != null}">
                            <c:forEach var="news" items="${newestNewsList}" varStatus="status">
                                <li>
                                    <a href="/elearning/mtMixTrainNewsAction.do?method=getNewsDetailFromMainPage&newsId=${news.newsId }&newsType=0" target="_blank">
                                        ${news.newsTitle }
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                        <c:if test="${msgMessageInfo != null}">
                            <c:forEach var="message" items="${newestMessageList}" varStatus="status">
                                <li>
                                    <a href="/elearning/msgMessageInfoAction.do?method=getNoticeDetailFromMainPage&msgId=${message.id }" target="_blank">
                                        ${message.title }
                                    </a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>

                <!--  轮播部分      -->
                <div class="example">
                    <div class="ft-carousel" id="carousel_1">
                        <ul class="carousel-inner">
                            <li class="carousel-item"><img src="/image/a1.jpg"/></li>
                            <li class="carousel-item"><img src="/image/a2.jpg"/></li>
                            <li class="carousel-item"><img src="/image/a3.jpg"/></li>
                            <li class="carousel-item"><img src="/image/a4.jpg"/></li>
                            <li class="carousel-item"><img src="/image/a5.jpg"/></li>
                            <li class="carousel-item"><img src="/image/a6.jpg"/></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div style="clear:both"></div>
        </div>
    </div>
</div>
<div id="foot"></div>
<div id="simpleNavBar" style="position:fixed;top:0px;">
</div>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/UI/jquery.infobox.js"></script>
<script type="text/javascript">
    var loginData = {
        nextActionUrl: "xxx"
    }
</script>
<script type="text/javascript" src="/js/nav/umenu.js"></script>
<script type="text/javascript" src="/js/UI/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/UI/basicWidget.js"></script>
<script type="text/javascript" src="/js/UI/jquery.infobox.js"></script>
<script type="text/javascript" src="/js/basicUserFunc.js"></script>
<script type="text/javascript" src="/js/ft-carousel.min.js"></script>
<script type="text/javascript">
    $("#carousel_1").FtCarousel();
</script>
</body>
</html>

