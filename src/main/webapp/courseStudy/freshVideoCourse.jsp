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
        <title>Freshcourse.jsp</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
        <script type="text/javascript" src="../js/jquery-latest.js"></script>
        <script type="text/javascript" src="../js/jquery-json.js"></script>
        <script language="javascript" src="../js/jquery.cookie-min.js"></script>
        <script type="text/javascript" src="../js/jquery.blockUI.js"></script>
        <script language ="JAVASCRIPT" type="text/javascript">
            var videoClassRoomArea={};
            videoClassRoomArea.failRefreshTime=0;
            $(function(){
                var isSessionValid = $('#isSessionValid').val();
                var info = $("#info").val();
                if(isSessionValid){
                    window.parent.location.reload();
                }
                if(info != 'admin'){
                    setInterval("sendClassHourRefreshCommand()", 30000);
                }
            });

            //定时发送更新学时记录信息
            function sendClassHourRefreshCommand(){
                $.ajax({
                    url:"<%=path%>/updateOtherCourse/updateStudy.do",
                    dataType:"html",
                    type:"post",
                    success:function(data){
                        videoClassRoomArea.failRefreshTime=0;
                    },
                    error:function(data){
                        videoClassRoomArea.failRefreshTime++;
                        console.log(videoClassRoomArea.failRefreshTime);
                        if(videoClassRoomArea.failRefreshTime>=10){
                            videoClassRoomArea.failRefreshTime=0;
                            self.parent.frames["Content"].errorReminder("因网络异常,您的学时未能正常记录,请检查网络并重新登录");
                        }
                    }
                })
            }
        </script>
    </head>

    <body>
    <input id="isSessionValid" type="hidden" name="isSessionValid" value="${isSessionValid }"/>
    <input id="info" type="hidden" name="info" value="${INFO}"/><br>
    </body>
</html>
