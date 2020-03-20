<%@ page import="com.elearning.pojo.pub.Course" %>
<%@ page import="com.elearning.common.Constants" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String courseurl = (String) request.getAttribute("courseurl");
    session.setAttribute("courseurl", courseurl);

    String courseID = (String) request.getAttribute("COURSEID");
    session.setAttribute("COURSEID", courseID);

    String startTime = (String) request.getAttribute("STARTTIME");
    session.setAttribute("STARTTIME", startTime);
    //STARTTIME
    String streamServerAddress = (String) request.getAttribute("BASEURL");
    session.setAttribute("BASEURL", streamServerAddress);

    String appOrgName = (String) request.getAttribute("appOrgName");
    session.setAttribute("appOrgName", appOrgName);

    Course course = (Course) request.getAttribute("course");
    session.setAttribute("course", course);

    Integer lastLearnTime = (Integer) request.getAttribute("lastLearnTime");
    session.setAttribute("lastLearnTime", lastLearnTime);

    Integer totalCourseTime = (Integer) request.getAttribute("totalCourseTime");
    session.setAttribute("totalCourseTime", totalCourseTime);


    Course coursePackage = (Course) request.getAttribute("coursePackage");
    session.setAttribute("coursePackage", coursePackage);

    String firstCourse = (String) request.getAttribute("firstCourse");
    session.setAttribute("firstCourse", firstCourse);

    String info = (String) request.getAttribute("info");
    request.setAttribute("info", info);

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <title>classroom.jsp</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    </head>

    <c:if test="${preMode != 0}">
        <script type="text/javascript">
            var TimeNum = 300;
            var timeStr;
            function ChangeTime() {
                if (TimeNum > 0) {
                    TimeNum--;
                    timeStr = setTimeout("ChangeTime()", 10000);
                } else {
                    alert("预览到此为止，如想学习课件，请登录<%=request.getAttribute("tenantURL")%>");
                    window.close();
                }
            }
        </script>
        <frameset rows="0%,100%" name="LMSMain" id="LMSMain" FRAMEBORDER=no border="0">
            <frame id="controlNotebook" name="controlNotebook" src="controlNotebook.jsp" FRAMEBORDER=no border="0">
            <frame id="Content" name="Content" src="documentRoom.jsp"/>
        </frameset>
    </c:if>

    <c:if test="${preMode != 1}">
        <frameset rows="0%,0%,97%,0%" name="LMSMain" id="LMSMain" FRAMEBORDER=no border="1">
            <frame id="LMSFrame" name="LMSFresh" src="freshVideoCourse.jsp" noresize FRAMEBORDER=no border="0">
            <frame id="controlNotebook" name="controlNotebook" src="controlNotebook.jsp" FRAMEBORDER=no border="0">
            <frame id="Content" name="Content" src="documentRoom.jsp"/>
        </frameset>
    </c:if>
    <body>
    </body>
</html>
