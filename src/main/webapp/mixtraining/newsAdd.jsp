<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="content_01">
                <div class="headtitle"><img hspace="4" align="absMiddle" src="/image/folder_table.png"/>新建新闻</div>
            </div>
            <form action="../mtTrainNews/toAddNews.do" method="post" id="form1" name="addForm">
                <input type="hidden" name="filterFlag" value="false"/>
                <input type="hidden" name="newsFlag" value="1"/>
                <table class="table1" width="80%" cellspacing="0" cellpadding="0" align="center">
                    <tr>
                        <th width="15%" align="left">新闻标题:</th>
                        <td width="85%" align="left">
                            <input type="text" id="newsTitle" name="newsTitle" size="50" maxlength="64"/>
                            <font color='red'>*</font>
                        </td>
                    </tr>
                    <tr>
                        <th width="15%" align="left">新闻类型:</th>
                        <td>
                            <input type="radio" name="newsType" id="radio1" value="0" checked/>新闻动态
                            <input type="radio" name="newsType" id="radio3" value="3"/>图片新闻
                        </td>
                    </tr>
                    <tr id="content">
                        <th align="left">新闻内容:</th>
                        <td align="left"><textarea name="newsContent" rows="10" cols="65" id="contentArea"></textarea>
                        </td>
                    </tr>
                </table>
            </form>
            <div class="condition" align="center">
                <button onclick="goBack()" class="btn btn-primary">返回</button>
                <button onclick="previewNews();" class="btn btn-info">预览新闻</button>
                <button onclick="checkForm();" class="btn btn-success">确定</button>
            </div>
        </div>
    </div>
</div>
</div>
<div class="bottombody"></div>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript"> dojo.require("dojo.widget.*");</script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/ueditor-new/ueditor.config.js"></script>
<script type="text/javascript" src="/js/ueditor-new/ueditor.all.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
    var editor = "";
    $(function () {
        $("input[name=newsType]").click(function () {
            var value = $(this).val();
            if (value != "2") {
                $("select[name=subNewsType]").css("display", "none");
            } else {
                $("select[name=subNewsType]").css("display", "inline");
            }
        })
    });

    function checkForm() {
        var newsName = document.getElementById('newsTitle').value;
        var newsContent = editor.getContent();
        if (newsContent.length > 65535) {
            //jAlert('<font color=red>新闻内容字数过多，请重新编辑！</font>','提示信息');
            //alert("新闻内容字数过多，请重新编辑！");
            jAlert("新闻内容字数过多，请重新编辑！", '提示');
            return;
        }
        if (newsName.length == 0) {
            //alert("请输入新闻标题!");
            jAlert("请输入新闻标题!", '提示');
        } else if (newsContent.length == 0) {
            //alert("请输入新闻内容!");
            jAlert("请输入新闻内容!", '提示');
        } else if (newsName.length > 500) {
            //alert("新闻标题字数过多，请重新编辑!");
            jAlert("新闻标题字数过多，请重新编辑!", '提示');
        } else {
            $("#form1").attr("action", "../mtTrainNews/toAddNews.do");
            document.getElementById("form1").submit();
        }
    }
    function previewNews() {
        $("#form1").attr("action", "../mtTrainNews/preview.do");
        $("#form1").attr("target", "_blank");
        $("#form1").submit();
    }

    $(function () {
        var option = {
            initialStyle: 'p{line-height:1.5em;font-size:14px;font-family:宋体};span{font-size:14px;font-family:宋体;line-height:1.5em}'
        };

        editor = new UE.ui.Editor(option);
        editor.render('contentArea');

        editor.addInputRule(function (root) {

            root.traversal(function (node) {
                if (node.type == 'element') {
                    switch (node.tagName) {
                        case 'span':
                            node.setStyle({'font-size': '14px', "font-family": "宋体", "line-height": "1.5em"});
                        case 'p':
                            node.setStyle({'font-size': '14px', "font-family": "宋体", "line-height": "1.5em"});
                        case 'div':
                            node.setStyle({'font-size': '14px', "font-family": "宋体"});
                        case 'li':
                            node.setStyle({'font-size': '14px', "font-family": "宋体"});
                        case 'table':
                            node.setStyle({'font-size': '14px', "font-family": "宋体"});
                    }
                }
            })
        });
    });
    function goBack() {
        history.back();
    }
</script>
</body>
</html>
