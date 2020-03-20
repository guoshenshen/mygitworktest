<%@ page import="com.elearning.common.Constants" %>
<%@ page language="java" pageEncoding="utf-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
    <head>
        <title>contolnotebook.jsp</title>
        <script language ="JAVASCRIPT" >
        </script>
        <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
        <style type="text/css">
            <!--
            body {
                margin-top: 0px;
                margin-bottom: 0px;
            }
            -->
        </style>
    </head>

    <body>

    <img src="../image/images_xy/021.jpg" width="85" height="22" onClick="window.top.document.getElementById('LMSMain').setAttribute('rows','0%,3%,97%,0%')">
    <img src="../image/images_xy/022.jpg" width="85" height="22" onClick="window.top.document.getElementById('LMSMain').setAttribute('rows','0%,3%,77%,20%')">



    </body>
</html>