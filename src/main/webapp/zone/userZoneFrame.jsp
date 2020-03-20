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
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/contentStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/jquery.infobox.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/studentZone.css" rel="stylesheet" type="text/css"/>
</head>
<body class="student studentZone">
<div class="mainContent">
    <div id="headbody" class="zoneHead">
        <div class="zone-headContent">
            <a class="zone-headPic u-userCardTrigger">
                <c:if test="${userinfo.headPic != null}">
                    <img src="${userinfo.headPic}" class="headPic infobox_headPic" onerror="imgError({type:-1,target:this});"/>
                </c:if>
                <c:if test="${userinfo.headPic == null}">
                    <c:if test="${itemType != 0}">
                        <c:if test="${userinfo.gender == 2}">
                            <img src="/image/headPic/female1.jpg" class="headPic infobox_headPic"/>
                        </c:if>
                        <c:if test="${userinfo.gender != 2}">
                            <img src="/image/headPic/male1.jpg" class="headPic infobox_headPic"/>
                        </c:if>
                    </c:if>
                    <c:if test="${itemType == 0}">
                        <img src="/image/headPic/defaultOrg.png" class="headPic orgPic infobox_headPic"/>
                    </c:if>
                </c:if>
                <input type="hidden" class="infobox_itemType" value="${itemType}">
                <input type="hidden" class="infobox_userName" value="${userinfo.operatorName}">
                <input type="hidden" class="infobox_userId" value="${userinfo.operatorId}">
            </a>
            <div class="zone-userInfo">
                <span class="username">${userinfo.operatorName}</span>
                <p class="orgName">${userinfo.orgName}</p>
                <input type="hidden" name="operatorId" value="${userinfo.operatorId}"/>
                <input type="hidden" name="currentOperatorId" value="${currentOperatorId}"/>
                <input type="hidden" name="orgId" value="${userinfo.orgId}"/>
                <input type="hidden" name="itemType" value="${itemType}"/>
            </div>
            <span class="zone-fans">
                <span class="concernNum" data-remodal-target="modal"
                    onclick="javascript:showMemberList({'memberType':'0','operatorId':'${userinfo.operatorId}'});">关注
                    <font class="focusPeopleCount">${countOfFollows}</font>人
                </span>
                | <span class="fansNum" data-remodal-target="modal"
                    onclick="javascript:showMemberList({'memberType':'1','operatorId':'${userinfo.operatorId}'});">粉丝
                    <font class="fansCount">${countOfFans}</font>人
                  </span>
                | <span class="concernOrgNum" data-remodal-target="modal"
                    onclick="javascript:showMemberList({'memberType':'2','operatorId':'${userinfo.operatorId}'});">关注机构
                    <font class="focusOrgCount">${countOfFollowOrgs}</font>个
                  </span>
            </span>
        </div>
    </div>
    <div id="mainbody" class="zoneContent">
        <div class="j-userTab">
            <c:if test="${itemType != 0}">
                <a class="tabTitle Tab" id="userBasicInfo" href="javascript:void(0);">基本信息</a>
                <a class="tabTitle Tab userItem notShow" id="courseJoined" href="javascript:void(0);">已选学课程</a>
                <a class="tabTitle Tab userItem notShow" id="trainJoined" href="javascript:void(0);">已参与培训</a>
            </c:if>
            <c:if test="${itemType == 0}">
                <a class="tabTitle Tab orgItem notShow" id="coursePublish" href="javascript:void(0);">已发布课程</a>
                <a class="tabTitle Tab orgItem notShow" id="trainPublish" href="javascript:void(0);">已发布培训</a>
            </c:if>
        </div>
        <div class="j-infoBox">

        </div>
    </div>
</div>
<div id="DetailFoot">
</div>
<div id="foot"></div>
<div id="simpleNavBar" style="position:fixed;top:0px;">
</div>
<!--弹出框-->

<div class="remodal hoverContainer" data-remodal-id="modal" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <button data-remodal-action="close" class="remodal-close"></button>
    <div class="concernAndFans">
        <div class="box">
            <div class="btn-wrapper">
                <a class="concern-list btn ga-click" href="javascript:void(0);"
                   onclick="javascript:showMemberList({'memberType':'0','operatorId':'${userinfo.operatorId}'});">
                    <div>关注<font class="focusPeopleCount">${countOfFollows}</font>人</div>
                </a>
                <a class="fans-list btn ga-click" href="javascript:void(0);"
                   onclick="javascript:showMemberList({'memberType':'1','operatorId':'${userinfo.operatorId}'});">
                    <div>粉丝<font class="fansCount">${countOfFans}</font>人</div>
                </a>
                <a class="concernOrgs-list btn ga-click" href="javascript:void(0);"
                   onclick="javascript:showMemberList({'memberType':'2','operatorId':'${userinfo.operatorId}'});">
                    <div>关注机构<font class="focusOrgCount">${countOfFollowOrgs}</font>个</div>
                </a>
                <div class="search">
                    <input id="search-info" name="search" value="" type="text" placeholder="关注/粉丝"/>
                    <a href="javascript:void(0);" onclick="javascript:searchFollowsOrFansByName();" class="search-icon">
                        <img src="/image/search01.png" alt="搜索"></a>
                </div>
            </div>
        </div>
        <div class="follow-modal-body-wrapper">
            <div class="scrollable-wrapper">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/UI/jquery.infobox.js"></script>
<script type="text/javascript" src="/js/nav/umenu.js"></script>
<script type="text/javascript" src="/js/UI/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/UI/basicWidget.js"></script>
<script type="text/javascript" src="/js/UI/jquery.infobox.js"></script>
<script type="text/javascript" src="/js/basicUserFunc.js"></script>
<script type="text/javascript" src="/js/public/resource.js"></script>
<script type="text/javascript" src="/js/user/userZone.js"></script>
<script type='text/javascript'>
    var loginData = {
        nextActionUrl: "../interaction/intoUserZone.do?itemType=" + $(".zone-userInfo input[name=itemType]").val() + "&operatorId=" + $(".zone-userInfo input[name=operatorId]").val()
    };

    var userZoneFrameArea = {
        canClick: true,
    };

    function lazyLoadHeadPic() {
        $("img.lazy").lazyload({
            threshold: 10,
            effect: "fadeIn"
        });
    };

    $(function () {
        lazyLoadHeadPic();

        //绑定动作到课程选学、培训参与、课程发布、培训发布页面
        bindActionForTab();

        //鼠标放在某学员头像上,显示该学员名片
        $.hoverUserCard();

        //绑定删除粉丝/关注动作
        removeFansOrConcern();

    })
</script>
</body>
</html>
