<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>培训项目基本信息管理</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts-extend.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/remodal-default-theme.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/remodal.css"/>
    <link rel="stylesheet" type="text/css" href="/css/onlinetraining/mixtraining.css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>

    <style type="text/css">
        th {
            text-align: center; /*设置水平居中*/
            vertical-align: middle; /*设置垂直居中*/
        }
        td {
            text-align: center; /*设置水平居中*/
            vertical-align: middle; /*设置垂直居中*/
        }
    </style>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <ul id="tabs">
                <li><a href="../msgMessageInfo/foradd.do"><span>新建通知</span></a></li>
                <li><a href="../msgMessageArrangeList/searchArrangeUser.do"><span>回执统计</span></a></li>
                <li class="selected"><a href="#tabs"><span>以往培训通知</span></a></li>
                <div class="clr"></div>
            </ul>
            <div id="mainbody">
                <div class="homezoneall">
                    <div class="homezonehead">
                        <span class="homezonetitle">以往培训通知</span>
                    </div>
                    <div class="homezonecontent">
                        <div id="content_02">
                            <table id="oldMessageList" class="homecontenttable homezonecontentborder" rules="cols"
                                   align="center">
                                <tr class="tableTh">
                                    <th width=20%>培训名称</th>
                                    <th width=25%>通知标题</th>
                                    <th width=20%>时间</th>
                                    <th width=15%>是否已发布</th>
                                    <th width=15%>操作</th>
                                </tr>
                            </table>
                        </div>

                        <div class="condtion">
                            <ul class="pagination-admin" style="float:right"></ul>
                            <div style="clear:both"></div>
                        </div>

                        <div id="content_03">
                            <div class="clr"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clr"></div>
        </div>
    </div>
</div>
</div>
</div>
<div class="bottombody"></div>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>

<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/onlineTraining/mixtraining.js"></script>
<script type="text/javascript" src="/js/mixtraining/addressBookWindow.js"></script>
<script type="text/javascript" src="/js/public/jquery.excel.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/public/orgAndUsersSelect.js"></script>

<script type="text/javascript">
    $(function () {
        oldMessageList();
    });

    function oldMessageList() {
        //1.获取分页工具包
        var paginationTool = $.getPaginationConfig();
        //2.针对某分页任务，就该任务的逻辑覆盖分页工具包中的方法
        var extraPaginationConfig = {
            getUrlForPagination: function () {
                return "../msgMessageInfo/listOldMessage.do"
            }, //分页器url，访问该url，可获取分页及某页的记录

            //清空之前加载的记录（加载新的分页结果，需要执行该方法）
            actionForClearLoadedData: function () {
                $("#oldMessageList .infoRow").remove();
            },
            actionForSucessLoadingData: function (data) {
                var dataList = data.list;
                var dataLength = dataList.length;
                var htmlArray = new Array();

                for (var i = 0; i < dataLength; i++) {
                    var currentItem = dataList[i];
                    htmlArray.push("<tr class='infoRow'>");
                    htmlArray.push("<td align='center'>" + currentItem.trainName + "</td>");
                    htmlArray.push("<td align='center'>" + currentItem.title + "</td>");
                    htmlArray.push("<td align='center'>" + currentItem.validDate + "</td>");

                    htmlArray.push("<td align='center'>");
                    if (currentItem.status == "1091") {
                        htmlArray.push("未发布");
                    } else if (currentItem.status == "1092") {
                        htmlArray.push("已发布");
                    } else {
                        htmlArray.push("&nbsp;");
                    }
                    htmlArray.push("</td>");
                    htmlArray.push("<td align='center'>");
                    htmlArray.push("<a href='javascript:void(0);' class='showMessageInfo'><input type='hidden' name='msgId' value='" + currentItem.id + "'/>查看</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                    htmlArray.push("<a href='../msgMessageInfo/copyMsgInfo.do?id=" + currentItem.id + "'>引用</a>");
                    htmlArray.push("</td>");

                    htmlArray.push("</tr>");

                    $("#oldMessageList .infoRow").remove();
                    $("#oldMessageList").append(htmlArray.join(""));
                }
            }
            //将读取的分页记录组装成html并加载到页面
        };

        //将任务逻辑注入分页工具包
        $.extend(paginationTool, extraPaginationConfig);
        //恢复分页器默认设置
        paginationTool.resetSearchInfo();
        //向分页器传递查询条件
        $(".searchTrain .formInfo").each(function (index, that) {
            paginationTool.searchInfo[that.name] = that.value;
        });

        paginationTool.searchInfo["operatorName"] = $("#operatorName").val();
        paginationTool.searchInfo["title"] = $("#title").val();
        paginationTool.searchInfo["attendable"] = $("#attendable").val();

        //触发分页器
        paginationTool.actionForLoadingPagination();
    }

    $(function(){
        var pop_url=null;
        var messageIdInfo={
            msgId:null
        };
        $("body #oldMessageList").on("click",".showMessageInfo",function(){
            messageIdInfo.msgId=$("input[name=msgId]",$(this)).val();
            //window.open("../msgMessageInfo/messagedetail.do?id="+messageIdInfo.msgId);
            var pop_url="../msgMessageInfo/messagedetail.do?id="+messageIdInfo.msgId;
            var pop = $("<div id='_pop_win'><h2>查看培训<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
                + "<iframe id='pop_selectUser' height='540' width='698' scrolling='auto' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
                + "<div style='clear:both'></div>"
            );
            focusForm = "#form";//定义弹出窗关闭后，光标的定位form，解决弹出窗关闭后不能输入的问题
            pop.find("a.pop_close_btn").click(function () {
                $.unblockUI();
            });
            $.blockUI({
                message: pop,
                css: {
                    width: "700px",
                    height: "580px",
                    cursor: "default",
                    left: ($left - 700) / 2 - 10 + 'px',
                    top: ($top - 580) / 2 - 10 + 'px'
                }
            });
        });
    });
</script>
</body>
</html>