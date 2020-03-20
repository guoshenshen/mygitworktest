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
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <div class="homezoneall">
                <div class="homezonehead">
                    <span class="homezonetitle">培训成效</span>
                </div>
                <div class="homezonecontent">
                    <br/><br/>
                    <form action="../mtMixTrainEffect/add.do" method="post" id="form1">
                        <input type="hidden" name="filterFlag" value="false"/>
                        <table width="60%" align="center">
                            <tr>
                                <th width="20%" class="ctext">姓名：</th>
                                <td width="30%" class="ctext">${trainSummaryForm.userName}</td>
                                <th width="20%" class="ctext">最后保存时间：</th>
                                <td width="30%" class="ctext"></td>
                            </tr>
                            <tr>
                                <td colspan='4' align='center'>
                                    <textarea id="conclusion" rows="30" cols="100" class="areaInput" name="conclusion"></textarea>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <div style="float:right;margin-right:170px;margin-bottom:10px;margin-top:20px;">
                        <a href="javascript:void(0);" class="btn-blue-l" onclick="toAddTrainEffect();" style="margin-left:10px;">
                            <span class="btn-blue-r">保&nbsp;存</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>
<div id='getContent'></div>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript">
    dojo.require("dojo.event.*");
</script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<!-- InstanceBeginEditable name="head" --><!-- InstanceEndEditable -->
<script type="text/javascript" src="/js/ueditor-new/ueditor.config.js"></script>
<script type="text/javascript" src="/js/ueditor-new/ueditor.all.js"></script>
<script type="text/javascript">
    var editor = "";
    function toAddTrainEffect1() {
        var trainEffect = editor.getContent();
        if (trainEffect.length > 65535) {
            jAlert('<font color=red>培训成效内容字数过多，请重新编辑！</font>', '提示信息');
            return;
        } else if (trainEffect.length == 0) {
            jAlert('<font color=red>请输入内容！</font>', '提示信息');
        } else {
            document.getElementById("form1").submit();
        }
    }
    function toAddTrainEffect() {
        document.getElementById("form1").submit();
    }
</script>
<script type="text/javascript">
    $(function () {
        var option = {
            initialStyle: 'p{line-height:1.5em;font-size:14px;font-family:宋体};span{font-size:14px;font-family:宋体;line-height:1.5em}'
        };

        editor = new UE.ui.Editor();

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
    })
</script>
</body>
</html>