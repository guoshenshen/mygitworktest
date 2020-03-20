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
    <title><%=Constants.systemName%>
    </title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.navigation.css"/>
    <link rel="stylesheet" type="text/css" href="/css/skinCss/administratorStyle.css"/>

    <style type="text/css">
        .code {
            display: none;
            z-index: 9999;
            position: fixed;
            right: 40%;
            top: 30%;
        }
        .orgList {
            display: none;
            z-index: 9999;
            position: fixed;
            right: 35%;
            top: 30%;
            width: 600px;
            height: 500px;
            overflow-x: auto;
            overflow-y: auto;
            background: #fff;
        }
        th {
            text-align: center;
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
            <div class="homezoneall">
                <div class="homezonecontent" style="margin-top:0px">
                    <div id="content_single">
                        <div class="headtitle">
                            <img hspace="4" align="absmiddle" src="/image/folder_table.png"/>
                            培训签到
                        </div>
                        <div class="condition">

                        </div>
                        <div class="cls"></div>
                    </div>
                    <div class="homezonecontent ">
                        <form id="form1" name="form1" method="post">
                            <table class="homecontenttable" id="surveyList">
                                <tr class="tableTh">
                                    <th width="13%">签到名称</th>
                                    <th width="13%">签到开始时间</th>
                                    <th width="13%">签到结束时间</th>
                                    <th width="8%">签到时长</th>
                                    <th width="8%">签到人数</th>
                                    <th width="7%">状态</th>
                                    <th width="14%">上课开始时间</th>
                                    <th width="14%">上课结束时间</th>
                                    <th width="10%">操作</th>
                                </tr>
                            </table>
                        </form>
                    </div>

                    <!--首页 内容 end -->
                    <div class="clr"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>
<div class="code">
    <div id='_pop_win'><h2>签到二维码<a href='javascript:void(0);' onclick="codeClose()" class='pop_close_btn'>X</a></h2>
    </div>
    <img id="codeImage" src="http://mobile.caskj.cn/qrcodeimage/1904091118348340.jpg" alt="培训签到二维码" width=400px
         height=400px/>
</div>
<div class="orgList">
    <div id='_pop_win'><h2>签到人员名单<a href='javascript:void(0);' onclick="closeOrg()" class='pop_close_btn'>X</a></h2>
    </div>
    <div>
        <table class="homecontenttable" id="orgTable" cellspacing="0">
            <tr class="tableTh">
                <th width="18%">用户姓名</th>
                <th width="20%">单位</th>
                <th width="17%">账号</th>
                <th width="20%">邮箱</th>
                <th width="15%">签到时间</th>
            </tr>
        </table>
    </div>
</div>


<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/ueditor-new/ueditor.config.js"></script>
<script type="text/javascript" src="/js/ueditor-new/ueditor.all.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/survey/surveyUserList.js"></script>
<script type="text/javascript" src="/js/basicUserFunc.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript">
    $(function () {
        var trainId = ${trainId};
        $.ajax({
            type: "post",
            data: {"trainId": trainId},
            url: "../mtMixTrainCertificate/findTrainSign.do",
            dataType: "json",
            success: function (data) {
                if (data.status) {
                    var html = "";
                    var arraytrain = data.data;
                    for (var int = 0; int < arraytrain.length; int++) {
                        var train = arraytrain[int];
                        var state = "";
                        var codeName = "";
                        if (train.state == 1) {
                            state = "不可用";
                        } else {
                            state = "可用";
                        }
                        if (train.ewmType == 1) {
                            codeName = "静态二维码";
                        } else {
                            codeName = "动态二维码";
                        }
                        html += '<tr><td>' + train.name + '</td><td>' + train.startS + '</td><td>' + train.endS + '</td><td>' + train.hour + '小时</td>';
                        if (train.signCount > 0) {
                            html += '<td><a href="javascript:void(0)" onclick="orgList(\'' + train.id + '\')" >' + train.signCount + '人</a></td>';
                        } else {
                            html += '<td>' + train.signCount + '人</td>'
                        }
                        html += '<td>' + state + '</td>';
                        html += '<td>' + train.startClassS + '</td><td>' + train.endClassS + '</td><td><a href="javascript:void(0)" onclick="clikeCode(\'' + train.id + '\',\'' + train.trainId + '\',\'' + train.ewmType + '\')">' + codeName + '</a></td></tr>';
                    }
                    $("#surveyList").append(html);
                }
            }
        })
    });
    var interval = "";
    var qcode = "";

    function codeClose() {
        $(".code").hide();
        clearInterval(interval);
        clearInterval(qcode);
    };

    function clikeCode(id, trainId, ewmType) {
        var url = "";
        if (ewmType == 1) {
            url = getEwm(id);
            $("#codeImage").attr("src", url);
        } else {
            qcode = setInterval(function () {
                $.ajax({
                    type: "get",
                    async: true,
                    url: "http://159.226.28.50/castelearning/qRCode.do?trainId=" + trainId + "&id=" + id + "&type=1",
                    dataType: "jsonp",
                    success: function (data) {
                    }
                });
            }, 30000);
            interval = setInterval(function () {
                url = getEwm(id);
                $("#codeImage").attr("src", url);
            }, 1000);
        }
        $(".code").show();
        $(".orgList").hide();
    }

    function getEwm(id) {
        var ewm = "";
        $.ajax({
            type: "post",
            data: {"id": id},
            async: false,
            url: "../mtMixTrainCertificate/findTrainSignEwm.do",
            dataType: "json",
            success: function (data) {
                if (data.status) {
                    ewm = data.data;
                };
            }
        });
        return ewm;
    }

    function orgList(id) {
        $(".orgTr").remove();
        $(".orgList").show();
        $(".code").hide();
        getOrgList(id);
    }

    function closeOrg() {
        $(".orgList").hide();
    }

    function getOrgList(id) {
        $.ajax({
            type: "post",
            data: {"id": id},
            async: false,
            url: "../mtMixTrainCertificate/getOrgList.do",
            dataType: "json",
            success: function (data) {
                if (data.status) {
                    var orgList = data.data;
                    var html = "";
                    for (var int = 0; int < orgList.length; int++) {
                        var org = orgList[int];
                        html += '<tr class = "orgTr"><td>' + org.operatorName + '</td><td>' + org.orgName + '</td><td>' + org.userName + '</td><td>' + org.email + '</td><td>' + org.timeS + '</td></tr>';
                    }
                    $("#orgTable").append(html);
                };
            }
        });
    };
</script>
</body>
</html>