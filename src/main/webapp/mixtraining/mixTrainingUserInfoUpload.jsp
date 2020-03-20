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
    <link rel="stylesheet" type="text/css" href="/css/pagination.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <ul id="tabs">
                <%--<li><a href="../msgMessageInfo/foradd.do"><span>新建通知</span></a></li>
                <li><a href="../msgMessageArrangeList/listArrangeUser.do"><span>回执统计</span></a></li>
                <li class="selected"><a href="#tabs"><span>实际参会人员</span></a></li>
                <div class="clr"></div>--%>
                <li class="selected"><a href="#tabs"><span>其他人员导入</span></a></li>
                <div class="clr"></div>
            </ul>
            <div id="mainbody">
                <!-- InstanceBeginEditable name="main" -->
                <div class="homezoneall">
                    <div class="homezonehead">
                        <span class="homezonetitle">其他人员导入</span>
                    </div>
                    <div class="homezonecontent">
                        <form id='form5' action="<%=path%>/mtMixTrainSectionFileMgt.do?method=addUserInfoFile&research=false" enctype="multipart/form-data" method="post">
                            <table class="table1" width="80%" align="center" cellspacing="0" cellpadding="0">
                                <tr>
                                    <th>
                                        选择文件：
                                    </th>
                                    <td>
                                        <input type="file" name="picFile" id="picFile" size=30/><font color='red'>&nbsp;* 请按示例格式导入人员培训信息</font>
                                    </td>
                                </tr>
                                <tr>
                                    <th>
                                        查看导入示例：
                                    </th>
                                    <td>
                                        <a href=javascript:openwindowtocenter('../modelExcel/outputExcel.jsp?file=offlineuserinfo.xls','<%=Constants.systemName%>',600,600)>
                                            <img src="/image/choice.jpg" border="0" style="width:35px;height:19px"/>
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <div class="button" onclick="javascript:uploadExcel();" name="button">
                                            <span>上传</span>
                                        </div>
                                        <div class="button" onclick="window.location.href = '../mtMixTrainUserTrainInfo/browseTrainUserInfo.do'" name="button">
                                            <span>返回</span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td align='center'>

                                    </td>
                                </tr>
                                <logic:notEmpty name='importResult'>
                                    <tr>
                                        <td colspan=8 align=center bgcolor='yellow'>
                                            <font color='red'>
                                                    ${importResult}
                                            </font>
                                        </td>
                                    </tr>
                                </logic:notEmpty>
                            </table>
                        </form>
                    </div>
                    <!--首页 内容 end -->
                    <!-- InstanceEndEditable --> </div>
                <div class="clr"></div>
            </div>
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
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script language='javascript'>
    function uploadExcel() {
        var fileName = document.getElementById("picFile").value;
        if (fileName == "") {
            alert("导入文件名称不能为空!");
            document.getElementById("picFile").focus();
            return;
        }
        if (fileName.substring(fileName.lastIndexOf(".") + 1) != "xls") {
            alert("请导入.xl格式的excel文件!");
            document.getElementById("picFile").focus();
            return;
        }
        showBufferMask();
        document.getElementById('form5').submit();
    }

    $('#tabs li').click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

</script>
</body>
</html>