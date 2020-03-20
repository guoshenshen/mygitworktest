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
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta name="description" content="中国科学院继续教育平台"/>
    <meta name="keywords" content="中国科学院;在线教育;继续教育网"/>
    <script id="allmobilize" charset="utf-8"
            src="http://ysp.cnic.cn:30000/c98b9d4a79156fd9bc4515637d3256ca/allmobilize.min.js"></script>
    <title>登录-<%=Constants.systemName%>
    </title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"></link>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css"></link>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <c:if test="${nextActionUrl != null}">
        <script type="text/javascript">
            var loginData = {
                nextActionUrl: '${nextActionUrl}'
            }
        </script>
    </c:if>
    <script type="text/javascript" src="/js/login/login.js"></script>
    <script type="text/javascript" src="/js/UI/remodal.min.js"></script>
    <script type="text/javascript">
        var loginArea = {
            bannerRotateInfo: {
                index: -1,
                bannerArray: [
                    {bannerPic: "http://www.casmooc.cn/uploadFile/skin/discuss.jpg"},
                    {bannerPic: "http://www.casmooc.cn/uploadFile/skin/design.jpg"},
                    {bannerPic: "http://www.casmooc.cn/uploadFile/skin/bangong1.jpg"}
                ],
                rotateInterval: null
            }
        }
    </script>
</head>
<body id="loginBody">
<img id="background-image" src="http://www.casmooc.cn/uploadFile/skin/bangong1.jpg" style="display: inline;"/>
<div class="login" id="J-login">
    <div class="message"><%=Constants.systemName%></div>
    <div id="darkbannerwrap"></div>
    <form class="noNeedAfterValidate">
        <input name="action" value="login" type="hidden"/>
        <input name="userId" id="userId" placeholder="用户名" required="" type="text"/>
        <hr class="hr15"/>
        <input name="password" id="password" placeholder="密码" required="" type="password"/>
        <hr class="hr15"/>
        <input name="verifyCode" id="verifyCode" placeholder="验证码" required="" type="text" style="width:70%;"/>
        <img src='/image/banner-load1.gif' id='verifyCodeImg' title='点击刷新验证码'></img>
        <hr class="hr15"/>
        <input value="登录" style="width:100%;" type="button" id="J-login-btn"/>
        <hr class="hr20"/>
    </form>
    <div id="loginUserInfo" class="unOrigin" style="display: none;">
        <div class="userHead"></div>
        <div class="userInfo"></div>
    </div>
    <p id="login_info" class="login_info" style="margin:0px;text-align:center;position:absolute;bottom:20px;width:80%;color: #469ec4;"></p>
</div>

<c:if test="${loginAbnormal != null}">
    <div class="normal authLoginTips" data-remodal-id="authLoginTips" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
        <div class="tipInfo"><p></p></div>
        <div id="abnormalInfo" style="display:none">
            <c:if test="${message != null}">
                <input name="message" value="${message}" type="hidden"/>
            </c:if>

            <c:if test="${type != null}">
                <input name="type" value="${type}" type="hidden"/>
            </c:if>

            <c:if test="${authenId != null}">
                <input name="authenId" value="${authenId}" type="hidden"/>
            </c:if>

            <c:if test="${dynamicKey != null}">
                <input name="dynamicKey" value="${dynamicKey}" type="hidden"/>
            </c:if>

            <c:if test="${operatorName != null}">
                <input name="operatorName" value="${operatorName}" type="hidden"/>
            </c:if>

            <c:if test="${email != null}">
                <input name="email" value="${email}" type="hidden"/>
            </c:if>

            <c:if test="${userList != null}">
                <ul class="userList">
                    <c:forEach var="user" items="${userList}" varStatus="status">
                        <li class="userinfo">
                            <div class="box" title="点击用户名片完成认证绑定">
                                <a href="javascript:void(0);" class="zone-headPic">
                                    <input type="hidden" name="operatorId" value="${user.operatorId}"/>
                                    <input type="hidden" name="userId" value="${user.userId}"/>
                                    <input type="hidden" name="operatorName" value="${user.operatorName}"/>
                                    <img src="${user.address}" class="headPic"/>
                                </a>
                                <div class="personal-info">
                                    <div class="nickname">${user.operatorName}</div>
                                    <div class="info">
                                        <span class="member-type">${user.orgName}</span>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
        <button data-remodal-action="cancel" class="remodal-cancel" style="display:none">取消</button>
        <button data-remodal-action="confirm" class="remodal-confirm" style="display:none"></button>
    </div>
    <div class="normal authLoginConfirm" data-remodal-id="authLoginConfirm" role='dialog' aria-labelledby='modal1Title'
         aria-describedby='modal1Desc' data-remodal-options="hashTracking: false, closeOnOutsideClick: false">
        <div class="tipInfo"><p>账户绑定成功！是否使用认证账户邮箱替换CASMOOC账户相应信息？</p></div>
        <button data-remodal-action="cancel" class="remodal-cancel">取消</button>
        <button class="remodal-confirm">确定</button>
    </div>
    <script type="text/javascript" src="/js/login/authLogin.js"></script>
</c:if>

<script type="text/javascript">
    function picRotate() {
        var bannerRotateInfo = loginArea.bannerRotateInfo;
        var bannerArray = bannerRotateInfo.bannerArray;
        var currentIndex = (bannerRotateInfo.index + 1) % bannerArray.length;
        bannerRotateInfo.index = currentIndex;
        $("#background-image").animate({opacity: "0"}, 500, function () {
            $("#background-image").attr("src", bannerArray[currentIndex].bannerPic);
        });
    }

    $(function () {
        $("#verifyCodeImg").attr("src", "./pub/verifyCode.jsp?version=" + Math.random());
        bindActionForLogin();
        $("#background-image").load(function () {
            $(this).animate({opacity: "1"}, 1000);
        });
        var bannerRotateInfo = loginArea.bannerRotateInfo;
        bannerRotateInfo.roateInterval = setInterval(function () {
            picRotate();
        }, 10000);
    })
</script>
</body>
</html>