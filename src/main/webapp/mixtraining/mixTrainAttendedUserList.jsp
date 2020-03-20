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
<!-- InstanceBegin template="/Templates/xx1.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <!-- InstanceBeginEditable name="doctitle" -->
    <title><%=Constants.systemName%>
    </title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/common/tools.js"></script>
    <link id="rsmRcmbook" href="/css/resourceManage/rsmRcmbook.css" rel="stylesheet" type="text/css"/>
    <link href="/css/trainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/newTrainClass.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/skin.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/studentStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        #showMode {
            float: left;
            width: 200px;
            margin: 10px 20px 30px;
        }
        .mode1 .tab1, .mode2 .tab2 {
            display: block;
        }
        .mode1 .tab2, .mode2 .tab1, .tab1, .tab2 {
            display: none;
            clear: both;
        }
        .tab2 .follow-modal-body-wrapper {
            width: 990px;
            margin: 0 auto;
            display: block;
        }
        th {
            text-align:center; /*设置水平居中*/
            vertical-align:middle;/*设置垂直居中*/
        }
        td {
            text-align:center; /*设置水平居中*/
            vertical-align:middle;/*设置垂直居中*/
        }
    </style>
    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/nav/trainClassMenu.js"></script>
    <script type="text/javascript" src="/js/UI/jquery.infobox.js"></script>
    <script type="text/javascript" src="/js/basicUserFunc.js"></script>
    <script type="text/javascript" src="/js/nav/umenu.js"></script>
</head>
<body>
<div id="backgroundImg"><img src=''/></div>
<div id='bufferMask'></div>
<div id="simpleNavBar"></div>
<div id="pagebody">
    <div id="headerbody">
        <input id="defaultPageSize" type="hidden" value="${defaultPageSize}" />
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
            <div class="searchTrain"><!-- ../onlineTraining/searchTrain.do -->
                <div class="form-inline condition" style="text-align: right;">
                    <div class="form-group">
                        <input class="form-control formInfo" type="text" name="userNameInput" id="userNameInput" placeholder="姓名…" value="${userNameInput}" />
                        <button  id = "searchTrain" class="btn btn-primary" style="top:15px;" type="button" onclick="searchTrain()">查询</button>
                    </div>
                </div>
            </div>
            <div class='pagebodybufferMask'></div>
            <span id="joinTrainFlag" style="display:none;">${joinTrainFlag }</span>
            <div id="trainClassMenu">
                <ul></ul>
            </div>
        </div>
    </div>
    <div id="mainbody">
        <div class='pagebodybufferMask'></div>
        <div class="mainContent"></div>

        <%--<div id="userlist">
            <div id="showMode">
                <input type="radio" checked="checked" name="tab" value="0" />列表模式
                <input type="radio" name="tab" value="1" />头像模式
            </div>
            <div class="tab1">
                <table class="homecontenttable"></table>
            </div>
            <div class="tab2 hoverContainer">
                <div class="follow-modal-body-wrapper"></div>
            </div>
        </div>--%>

        <div class="condtion">
            <ul class="pagination-admin" style="float:right"></ul>
            <div style="clear:both"></div>
        </div>

    </div>
    <div id="bottombody">
        <div class='pagebodybufferMask'></div>
        <%=Constants.systemBottom %>
    </div>
</div>

<script type="text/javascript">

    //查询按钮
    function searchTrain() {
        alert("马上要查询了！");
        userList();
    }

    $(function () {
        userList();
    });

    function userList(){
        //1.获取分页工具包
        var paginationTool=$.getPaginationConfig();
        var userListArray = new Array();
        //2.针对某分页任务，就该任务的逻辑覆盖分页工具包中的方法
        var extraPaginationConfig= {
            getUrlForPagination:function(){return "../mtMixTrainUserTrainInfo/getAttendedListForPaginationTool.do"}, //分页器url，访问该url，可获取分页及某页的记录
            //清空之前加载的记录（加载新的分页结果，需要执行该方法）
            actionForClearLoadedData:function(){
                //$("#userList .infoRow").remove();
                $("#mainbody").remove();
            },
            actionForSucessLoadingData:function(data){
                var dataList = data.list;
                var dataLength = dataList.length;
                if(dataLength>0){
                    $(".mainContent").append('<div  id="userlist"><div id="showMode"><input type="radio" checked="checked" name="tab" value="0" />列表模式<input type="radio" name="tab" value="1" />头像模式</div><div class="tab1"><table class="homecontenttable" ></table></div><div class="tab2 hoverContainer"><div class="follow-modal-body-wrapper"></div></div></div>');
                    for(var i=0;i < dataLength;i++){
                        userListArray.push(dataList[i]);
                    }
                    $("#userlist table").showMemberList(userListArray);
                    $("#userlist").addClass("mode1");
                } else{
                    $(".mainContent").append('<div class="emptyInfo">暂无参会人员...</div>');
                }
            }
            //将读取的分页记录组装成html并加载到页面
        };

        //将任务逻辑注入分页工具包
        $.extend(paginationTool,extraPaginationConfig);
        //恢复分页器默认设置
        paginationTool.resetSearchInfo();

        paginationTool.searchInfo.userNameInput = $("#userNameInput").val();

        //触发分页器
        paginationTool.actionForLoadingPagination();

        $("#trainListFrame").on("click","#searchTrain",function(){
            //paginationTool.resetSearchInfo();
            paginationTool.searchInfo.userNameInput = $("#userNameInput").val();
            paginationTool.actionForLoadingPagination();
        })

        $(".mainContent").on("click","input[name=tab]",function(){
            if(this.value=="0"){
                $("#userlist table").showMemberList(userListArray);
                $("#userlist").removeClass("mode2").addClass("mode1");
            } else{
                $("#userlist .tab2 .follow-modal-body-wrapper").showMemberCardList(userListArray);
                $("#userlist").removeClass("mode1").addClass("mode2");
            }
        });

        //鼠标放在某学员头像上,显示该学员名片
        $.hoverUserCard();
    }
</script>
</body>
</html>
