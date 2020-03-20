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
    <title>培训项目基本信息管理</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.alerts-extend.css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/mixtraining/addrbook.css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        th {
            text-align:center; /*设置水平居中*/
            vertical-align:middle;/*设置垂直居中*/
        }
        td {
            text-align:center; /*设置水平居中*/
            vertical-align:middle;/*设置垂直居中*/
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
                <li title="当前培训班下由您或其他管理员创建的通讯录" <c:if test="${trainId != -1}">style="background:#e9e9e9;"</c:if>>
                    <a href="javascript:void(0);" onclick="javascript:reloadWindow({});">
                        <span>当前通讯录</span>
                    </a>
                </li>
                <li title="您创建的全部通讯录" <c:if test="${trainId == -1}">style="background:#e9e9e9;"</c:if>>
                    <a href="javascript:void(0);" onclick="javascript:reloadWindow({'trainId':-1});">
                        <span>通讯录全集</span>
                    </a>
                </li>
            </ul>
            <div style='display:none'>
                <input type='hidden' name="trainId" id="trainId" value="${trainId }" />
            </div>
            <div class="clr"></div>
            <div class="homezoneall">
                <div class="condition">
                    <form method="post" id="form" name="form" action="../addrBook/queryTrainBook.do" class="form-inline" style="text-align: right"
                          onSubmit="return searchCheck()">
                        <div class="form-group">
                            <input type="hidden" name="trainId" value="${trainId}"/>
                            <input type="text" class="form-control" type="text" name="keyWords" id="keyWords" placeholder="通讯录|用户名…" value="${keyWords}">
                            <button class="btn btn-primary" type="submit">查询</button>
                        </div>
                    </form>
                </div>

                <!-- 当前培训项目中创建的通讯录 -->
                <div id="currentTrainAddrBook" class="homezonecontent" <c:if test="${trainId == -1 }">style="display:none"</c:if>>
                    <form action="../mtMixTrainNews/toAddNews.do" method="post" id="form1">
                        <table class="homecontenttable table table-striped table-bordered batchOperation">
                            <tr class="tableTh">
                                <th>通讯录名称</th>
                                <th>通讯录类型</th>
                                <th>最新修改时间</th>
                                <th width="140px">操作</th>
                            </tr>
                            <c:if test="${trainAddrBookList != null }">
                                <c:forEach var="trainAddrBook" items="${trainAddrBookList}">
                                    <tr>
                                        <td align="center">
                                            <a href="javascript:showAddrBook(${trainAddrBook.addrbookId })">${trainAddrBook.addrbookName }</a>
                                        </td>
                                        <td align="center">${trainAddrBook.bookRole }</td>
                                        <td align="center">
                                            <%--<fmt:formatDate value="${trainAddrBook.addrbookDate }" pattern="yyyy-MM-dd"/>--%>
                                            ${trainAddrBook.addrbookDate }
                                        </td>
                                        <td width="140px">
                                            <a href="javascript:void(0);" onclick="javascript:copyAddrbook({'addrbookId':'${trainAddrBook.addrbookId}','trainId':${trainId }});">复制</a>
                                            | <a href="javascript:void(0);" onclick="javascript:deleteAddrbook({'addrbookId':'${trainAddrBook.addrbookId}'});">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </table>
                    </form>
                    <br/>
                </div>
                <!-- 其他培训项目中创建的通讯录 -->
                <div id="otherTrainAddrBook" class="homezonecontent" <c:if test="${trainId != -1 }">style="display:none"</c:if>>
                    <table class="homecontenttable table table-striped table-bordered batchOperation" width="100%" cellspacing="0" cellpadding="0">
                        <tr class="tableTh">
                            <th align="center">通讯录名称</th>
                            <th align="center">通讯录类型</th>
                            <th>最新修改时间</th>
                            <th width="140px">操作</th>
                        </tr>
                        <c:if test="${otherAddrBookList != null}">
                            <c:forEach var="addrBook" items="${otherAddrBookList}">
                                <tr>
                                    <td align="center">
                                        <a href="javascript:void(0);" onclick="javascript:showAddrBook(${addrBook.addrbookId })">${addrBook.addrbookName }</a>
                                    </td>
                                    <td align="center">${addrBook.bookRole }</td>
                                    <td align="center">
                                        ${addrBook.addrbookDate }
                                        <%--<fmt:formatDate value="${addrBook.addrbookDate }" pattern="yyyy-MM-dd"/>--%>
                                    </td>
                                    <td width="140px">
                                        <a href="javascript:void(0);" onclick="javascript:forEditAddrBook({'addrbookId':'${addrBook.addrbookId}','trainId':'-1'})">编辑</a>
                                        | <a href="javascript:void(0);" onclick="javascript:copyAddrbook({'addrbookId':'${addrBook.addrbookId}','trainId':${trainId }});">复制</a>
                                        | <a href="javascript:void(0);" onclick="javascript:deleteAddrbook({'addrbookId':'${addrBook.addrbookId}','trainId':'-1'});">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </div>
                <div class="condition">
                    <button type="button" class="btn btn-primary" id="createAddrbookButton">新建通讯录</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>
<div class="mainHidden"></div>

<div class="remodal normal noBorder addressBookEditor" data-remodal-id="addressBookEditor" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc' style="width:730px;">
    <div class="box">
        <button data-remodal-action="close" class="remodal-close"></button>
    </div>
    <div class="wrapper common" style="padding:0px;">
        <iframe class="bookInfo" src="" style="min-height:400px;overflow-y:scroll;max-height:600px;"></iframe>
    </div>
</div>

<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript">
    dojo.require("dojo.event.*");
</script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<script type="text/javascript" src="/js/mixtraining/addressBookWindow.js"></script>
<script type="text/javascript" src="/js/mixtraining/addressBookHome.js"></script>
<script type="text/javascript">

    function reloadWindow(param) {
        openLink("../addrBook/queryTrainBook.do", param);
    }

    $(function () {
        var trainId = $("#trainId").val();
        addressBookHomeArea.reloadWindow = reloadWindow;

        $("#createAddrbookButton").click(function () {
            forEditAddrBook({"trainId": trainId});
        })
    });

    function searchCheck() {
        if (document.form.elements[0].value == "") {
            alert("请输入查询关键词！");
            document.form1.elements[0].focus();
            return false;
        }
        return true;
    }
</script>
</body>
</html>