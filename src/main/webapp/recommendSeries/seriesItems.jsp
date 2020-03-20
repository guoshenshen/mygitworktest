<%@ page language="java" import="java.util.*,java.io.*"
         pageEncoding="utf-8"%>
<%@ page import="com.elearning.common.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String staticServerPath = Constants.STATICSERVER_URL;
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="中国科学院;继续教育;网络课程;空间" />
    <link rel="shortcut icon" type="image/x-icon"
          href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
    <link href="/css/skinCss/Normalize.css" rel="stylesheet"
          type="text/css" />
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css" />
    <!--<link href="./css/skinCss/student.css" rel="stylesheet" type="text/css" />-->
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet"
          type="text/css" />
    <link href="/css/skinCss/contentStyle.css" rel="stylesheet"
          type="text/css" />
    <link href="/css/skinCss/topicTemplate.css"
          rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/jquery.infobox.css" rel="stylesheet"
          type="text/css" />
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css" />
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css" />
    <link href="/css/skinCss/portalStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type='text/javascript'>
        function imgError(imgObj){
            if(imgObj.className.indexOf("orgPic")>-1){
                imgObj.src="./image/headPic/defaultOrg.png";
            }
            else{
                imgObj.src="./image/headPic/male1.jpg";
            }
        }
    </script>
    <style>
        .seriesFrame div.section .series-pic {
            position: relative;
        }

        div.section .series-pic:hover button.studySeries {
            display: block;
            color: #fff;
        }

        div.section .series-pic:hover button.btn-danger {
            display: block;
            color: #fff;
            background-color: #c9302c;
            border-color: #ac2925;
        }

        div.section .series-pic button.studySeries {
            position: absolute;
            bottom: 10px;
            right: 20px;
            display: none;
        }

        .btn-success {
            color: #fff;
            background-color: #5cb85c;
            border-color: #4cae4c;
        }

        .btn-danger:hover,.btn-danger:focus,.btn-danger.focus,.btn-danger:active,.btn-danger.active,.open>.dropdown-toggle.btn-danger
        {
            color: #fff;
            background-color: #c9302c;
            border-color: #ac2925;
        }

        .btn:active,.btn.active {
            background-image: none;
            outline: 0;
            -webkit-box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
            box-shadow: inset 0 3px 5px rgba(0, 0, 0, .125);
        }

        .btn:hover,.btn:focus,.btn.focus {
            color: #333;
            text-decoration: none;
        }

        .btn {
            display: inline-block;
            padding: 6px 12px;
            margin-bottom: 0;
            font-size: 14px;
            font-weight: 400;
            line-height: 1.42857143;
            text-align: center;
            white-space: nowrap;
            vertical-align: middle;
            -ms-touch-action: manipulation;
            touch-action: manipulation;
            cursor: pointer;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            background-image: none;
            border: 1px solid transparent;
            border-radius: 4px;
        }

        button,select {
            text-transform: none;
        }

        button {
            overflow: visible;
        }

        button,input,optgroup,select,textarea {
            margin: 0;
            font: inherit;
            color: inherit;
        }

        .mainContainer .bannerContainer {
            margin-top: 90px !important;
        }
        .courseSelectFrame{
            padding: 35px 35px 60px;
        }
        .form-control {
            display: block;
            width: 100%;
            height: 34px;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        }
        span.Button {
            cursor: pointer;
            float: left;
            display: block;
            min-width: 84px;
            font-size: 16px;
            letter-spacing: 5px;
            text-align: center;
            font-family: 微软雅黑;
            color: rgb(0, 0, 0);
            font-weight: normal;
            box-shadow: rgb(187, 187, 187) 0px 1px 2px;
            padding: 10px 16px;
            border-radius: 4px;
        }
        /* .redButton {
            background-color: #ca483b;
        } */
        span{
            margin:0;
        }
    </style>
    <title>系列专题</title>
</head>
<body id="seriesItemsPage" class="student">
<img id="background-image" />
<div id="filter-backgournd"></div>
<div id="mainbody" class="seriesFrame">
    <!-- <div id="simplefoot" style="float:none"></div> -->
    <div id="hiddenInfo" class="hiddenInfo" style="display:none">
        <div id="seriesInfo">
            <input type="hidden" name="seriesId" value="${recommendSeries.id}" />
        </div>
        <div id="bannerInfo">
            <c:if test="${not empty topicBanners}">
                <c:forEach var="banner" items="${topicBanners}" >
                    <div class="topicBannerInfo">
                        <input type="hidden" name="picUrl" value="${banner.bannerPicUrl}" />
                        <input type="hidden" name="description"
                               value="${banner.description}" /> <input type="hidden"
                                                                       name="title" value="${banner.title}" /> <input type="hidden"
                                                                                                                      name="bannerClass" value="${banner.bannerClass}" /> <input
                            type="hidden" name="templateClass"
                            value="${banner.templateClass}" />
                    </div>
                </c:forEach>
            </c:if>
        </div>
        <div id="tabInfo">
            <c:if test="${not empty resourceTypeList}">
                <c:forEach var="resourceType" items="${resourceTypeList}" >
                    <input type='hidden' name="${resourceType.name}"
                           value="${resourceType.typeCode}" />
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
<script type="text/javascript" src="/js/nav/umenu.js"></script>
<script type="text/javascript" src="/js/recommend/seriesCore.js"></script>
<script type="text/javascript" src="/js/recommend/seriesItems.js"></script>
<script type="text/javascript" src="/js/UI/scrollpagination-extend.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/UI/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/UI/jquery.infobox.js"></script>
<script type="text/javascript" src="/js/UI/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="/js/basicUserFunc.js"></script>
<script type="text/javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/public/constants.js"></script>
<script type="text/javascript" src="/js/public/resource.js"></script>
<!-- <script type="text/javascript" src="./js/nav/smenu.js"></script> -->
<script type="text/javascript" src="/js/bootstrap.min.js"></script>

<div id="foot"></div>
<div id="simpleNavBar" style="position:fixed;top:0px;"></div>
<div class="remodal normal" id="studySeriesModal" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close" ></button>
    </div>
    <div class="courseSelectFrame" >
        <div class="scrollable-wrapper">
            <table>
                <tr>
                    <th>专题名称</th>
                    <td  colspan="3">
                        <!-- <input type="text" style="background:#fff" value='' name ="title" class="form-control" disabled="disabled"/> -->
                        <span id="title"></span>
                        <input name="seriesId" value="" type="hidden" />
                    </td>
                </tr>
                <tr>
                    <th>组织单位</th>
                    <td  colspan="3">
                        <input type="hidden" style="background:#fff" id="orgName" value="${org.orgName }" name="orgName" class="form-control" disabled="disabled"/>
                        <span id="orgNameSpan">${org.orgName }</span>
                        <input type="hidden" id="orgId" name="orgId" value="${org.orgId }"/>
                    </td>
                </tr>
                <tr>
                    <th>专题详情</th>
                    <td  colspan="3">
                        <!-- <textarea class="form-control" name="detail" style="background:#fff;height:100px" disabled="disabled"></textarea> -->
                        <span id="detail"></span>
                    </td>
                </tr>
            </table>
            <br/>
            <div class="btnContainer">
                <div class="tips"></div>
                <div style="float:right">
                    <span class="redButton Button" id="seriesOK" style="margin:0;padding:8px 14px 8px 15px">确认选学</span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type='text/javascript'>
    var loginData={
        nextActionUrl:"/recommendSeries/intoSeriesItemFrame.do?seriesId="+$("#seriesInfo input[name=seriesId]").val()
    }
    var $studySeriesModal=$("#studySeriesModal").remodal();
    /**
     声明子专题focus变量
     **/
    window.subseries = new Object();
    window.subseries.focusSeriesId = null;
    window.subseries.focusSeriesStudy = null;
    window.subseries.$focusButton = null;
    $(function(){
        seriesItemsArea.loadStructure("./htmlStructure/topicTemplate.html","defaultTemplate",fillDataInfoForStructure);
    })
    /**
     判断是否登陆...
     **/
    function isLogon(){
        var operator = "<%=session.getAttribute(Constants.USERINFO_KEY)%>";
        if(operator=="null"){
            return false;
        }
        else{
            return true;
        }

    }
    function resourceAccess(){
        if(isLogon()==false){
            $.Nconfirm({
                'confirmQuestion':"请您先登录学习平台！",
                'onConfirm':function(){
                    $(".logonAction").click();
                    return false;
                },
                'onDeny':function(){
                    return false;
                }

            });
            return false;
        }
        else{
            return true;
        }
    }
    $("#seriesOK").on('click',function(){
        if(window.subseries.focusSeriesStudy=="选学"){
            $.ajax({
                method:"POST",
                data:{"seriesId":window.subseries.focusSeriesId,"isStudy":1},
                url:"recommendSeriesAction.do?method=studySeries",
                dataType:"json",
                success:function(data){
                    if(data.status){
                        window.subseries.$focusButton.html("退选").removeClass("btn-success").addClass("btn-danger");
                    }else{
                    }
                }
            });
        }else{
            $.ajax({
                method:"POST",
                data:{"seriesId":window.subseries.focusSeriesId,"isStudy":0},
                url:"recommendSeriesAction.do?method=studySeries",
                dataType:"json",
                success:function(data){
                    if(data.status){
                        window.subseries.$focusButton.html("选学").removeClass("btn-danger").addClass("btn-success");
                    }else{
                    }
                }
            });
        }
        $studySeriesModal.close();

    });
    function studySeries(_this,event){
        if(resourceAccess()==true){
            var $_this = $(_this);
            var seriesId = $_this.val();
            var isStudy = $_this.html();
            window.subseries.$focusButton = $(_this);
            window.subseries.focusSeriesId = seriesId;
            window.subseries.focusSeriesStudy = isStudy;
            $("#studySeriesModal").remodal().open();
            if(isStudy=="选学"){
                $("#seriesOK").html("确认选学").removeClass("btn-danger").addClass("btn-success");;
            }
            else{
                $("#seriesOK").html("确认退选").removeClass("btn-success").addClass("btn-danger");;
            }
            $.ajax({
                method:"POST",
                data:{"seriesId":window.subseries.focusSeriesId},
                url:"seriesManageAction.do?method=findSeriesById",
                dataType:"json",
                success:function(data){
                    var series = data.series;
                    if(series.sponsorInfo!=null && series.sponsorInfo!= ""){
                        $("input[name='orgName']").val(series.sponsorInfo);
                    }
                    /* $("#studySeriesModal input[name='title']").val(series.title);
                    $("#studySeriesModal input[name='seriesId']").val(series.id);
                    $("#studySeriesModal textarea[name='detail']").val(series.detail); */
                    $("#studySeriesModal #title").html(series.title);
                    $("#studySeriesModal input[name='seriesId']").val(series.id);
                    $("#studySeriesModal #detail").html(series.detail);
                    $("#studySeriesModal #orgNameSpan").html($("#studySeriesModal input[name='orgName']").val());
                }

            });
        }
        event.stopPropagation();
        //return false;
    }

</script>
</html>
