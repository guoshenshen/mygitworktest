<%@ page import="com.elearning.pojo.pub.Course" %>
<%@ page import="com.elearning.common.Constants" %>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String orgDomainName=(String)request.getAttribute("orgDomainName");
    session.setAttribute("orgDomainName",orgDomainName);

    String courseID = (String)request.getAttribute("COURSEID");
    session.setAttribute("COURSEID",courseID);

    String startTime = (String)request.getAttribute("STARTTIME");
    session.setAttribute("STARTTIME",startTime);
    //STARTTIME
    String streamServerAddress=(String)request.getAttribute("streamServerAddress");
    session.setAttribute("streamServerAddress",streamServerAddress);

    String httpAddress=(String)request.getAttribute("httpAddress");
    session.setAttribute("httpAddress",httpAddress);

    Course course = (Course)request.getAttribute("course");
    session.setAttribute("course",course);

    Integer lastLearnTime = (Integer)request.getAttribute("lastLearnTime");
    session.setAttribute("lastLearnTime",lastLearnTime);

    Integer totalCourseTime = (Integer)request.getAttribute("totalCourseTime");
    session.setAttribute("totalCourseTime",totalCourseTime);

    Course coursePackage  = (Course)request.getAttribute("coursePackage");
    session.setAttribute("coursePackage", coursePackage);

    String firstCourse = (String)request.getAttribute("firstCourse");
    session.setAttribute("firstCourse", firstCourse);


    String info = (String)request.getAttribute("info");
    request.setAttribute("info", info);

    String sliceType = (String)request.getAttribute("sliceType");
    request.setAttribute("sliceType", sliceType);


    String captionsChinese = (String)request.getAttribute("captionsChinese");
    session.setAttribute("captionsChinese", captionsChinese);

    String captionsEnglish = (String)request.getAttribute("captionsEnglish");
    session.setAttribute("captionsEnglish", captionsEnglish);

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
    <head>
        <title>classroom.jsp</title>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="中国科学院,继续教育,线上课程">
        <meta http-equiv="description" content="中国科学院继续教育平台线上课程">
        <meta http-equiv="Access-Control-Allow-Origin" content="*">
        <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
        <script type="text/javascript" src="../js/jquery-latest.js"></script>
        <script type="text/javascript" src="../js/jquery-json.js"></script>
        <script language="javascript" src="../js/jquery.cookie-min.js"></script>
        <script type="text/javascript" src="../js/jquery.blockUI.js"></script>
    </head>
    <c:if test="${preMode != 0 }" >
        <script type="text/javascript">
            var TimeNum=300;
            var timeStr;
            function ChangeTime(){
                if(TimeNum > 0){
                    TimeNum--;
                    timeStr=setTimeout("ChangeTime()",10000);
                }else{
                    alert("预览到此为止，如想学习课件，请与我们联系。联系电话：010-58813710 联系email：train@cnic.cn");	window.close();
                }
            }
        </script>
        <script type="text/javascript">
            function flashChecker() {
                var hasFlash = 0;　　　　 //是否安装了flash
                var flashVersion = 0;　　 //flash版本

                if(document.all) {
                    var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
                    if(swf) {
                        hasFlash = 1;
                        VSwf = swf.GetVariable("$version");
                        flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);
                    }
                } else {
                    if(navigator.plugins && navigator.plugins.length > 0) {
                        var swf = navigator.plugins["Shockwave Flash"];
                        if(swf) {
                            hasFlash = 1;
                            var words = swf.description.split(" ");
                            for(var i = 0; i < words.length; ++i) {
                                if(isNaN(parseInt(words[i]))) continue;
                                flashVersion = parseInt(words[i]);
                            }
                        }
                    }
                }
                return { f: hasFlash, v: flashVersion };
            }
        </script>
        <frameset rows="0%,100%" name="LMSMain"  id="LMSMain" FRAMEBORDER=no border="0">
            <frame id="controlNotebook" name="controlNotebook" src="controlNotebook.jsp" FRAMEBORDER=no border="0">
            <c:if test="${not empty actionType }" >
                <frame id="Content" name="Content" src="${courseVideo}?actionType=${actionType}" />
            </c:if>
            <c:if test="${empty actionType}" >
                <frame id="Content" name="Content" src="${courseVideo}">
            </c:if>
        </frameset>
    </c:if>
    <c:if test="${preMode != 1}"   >
        <frameset rows="0%,0%,100%,0%" name="LMSMain" id="LMSMain" FRAMEBORDER=no border="1" id="iframeSet">
            <frame id="LMSFrame" name="LMSFresh" src="freshVideoCourse.jsp" noresize FRAMEBORDER=no border="0">
            <frame id="controlNotebook" name="controlNotebook" src="controlNotebook.jsp" FRAMEBORDER=no border="0">
            <c:if test="${not empty actionType }" >
                <frame id="Content" name="Content" src="${courseVideo}?actionType=${actionType}" />
            </c:if>
            <c:if test="${empty actionType }" >
                <frame id="Content" name="Content" src="videoCourseWare_h5.jsp">
            </c:if>
        </frameset>
    </c:if>

    <script type="text/javascript">


    </script>
</html>
