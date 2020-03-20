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
    <title>培训作业管理</title>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/uploadify.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-UI/jquery.navigation.css"/>
    <link rel="stylesheet" type="text/css" href="/css/skinCss/icon.css"/>
    <link href="/css/jquery-UI/remodal.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/remodal-default-theme.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/css/skinCss/administratorStyle.css"/>
    <link rel="stylesheet" type="text/css" href="/css/public/material.css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="funcCon" class="trainApp"></div>
            <div id="assignInfo" class="adminStyle">
                <div class="homezoneall">
                    <div id="content_single">
                        <div class="headtitle">
                            <img hspace="4" align="absmiddle" src="/image/folder_table.png"/>
                            培训作业
                        </div>
                    </div>
                    <div id="assignContainer" class="assignContainer">
                        <div class="assignHiddenInfo" style="display:none">
                            <input type="hidden" name="resourceId" value="${resourceId}"/>
                            <input type="hidden" name="type" value="${type}"/>
                        </div>
                        <ul id="assignmentList" class="assignmentList">
                            <li class="assignmentItem firstAssignment">
                                <div id="addAssignment">
                                    <div class="addTag"></div>
                                    新建作业
                                </div>
                            </li>
                            <c:if test="${assignmentList != null}">
                                <c:forEach var="assignment" items="${assignmentList}" varStatus="status">
                                    <li class="assignmentItem moreAssignment">
                                        <div class="assignName">
                                            <font>${assignment.name}</font>
                                            <input type="hidden" name="assignId" value="${assignment.id}"/>
                                        </div>
                                        <div class="assignOptions">
                                            <span class="editTopic">编辑题目</span>
                                            <span class="replyStatistics">答题统计</span>
                                            <span class="deleteAssign">删除</span>
                                        </div>
                                        <div class="createTime">
                                            <fmt:formatDate value="${assignment.createDate }" pattern="yyyy-MM-dd"/>
                                        </div>
                                        <div style="clear:both"></div>
                                    </li>
                                </c:forEach>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>

<!-- 作业创建 -->
<div class="assignEditor normal noBorder" data-remodal-id="assignEditor" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close"></button>
    </div>
    <div class="wrapper common">
        <input type="text" class="editor" name="name" class=""/>
        <p class="tip">作业名称</p>
        <input type="hidden" name="id"/>
        <textarea class="editor description" name="description"></textarea>
        <p class="tip">作业描述</p>
        <textarea class="editor comment" name="comment"></textarea>
        <p class="tip">备注</p>
        <button data-remodal-action="cancel" class="remodal-cancel">取消</button>
        <button data-remodal-action="confirm" class="remodal-confirm">确定</button>
    </div>
</div>

<!-- 作业题目创建及管理 -->
<div class="assignTopicEditor normal noBorder" data-remodal-id="assignTopicEditor" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close"></button>
    </div>
    <div id="assignTopicContainer" class="wrapper common">
        <div id="addTopic" style="position:relative" class="showForChange">
            <div id="addTopicLine">
                <div class="addTag"></div>
                新建题目
            </div>
            <button data-remodal-action="confirm" class="remodal-confirm" style="position:absolute;top:10px;left:200px;">确定
            </button>
        </div>
        <div id="topicItemList">
            <div class="topicItem firstItem" style="display:none">
                <form>
                    <input type="hidden" name="id"/>
                    <p class="topicAttribute">
                        <span class="attributeName contentAttribute">内容</span>
                        <textarea class="content"></textarea>
                    </p>
                    <p class="topicAttribute">
                        <span class="attributeName">提交形式</span>
                        <input type="radio" name="form" value="0" checked/>文本输入
                        <input type="radio" name="form" value="1"/>附件上传
                    </p>
                    <p class="topicAttribute">
                        <span class="attributeName">是否必填</span>
                        <input type="radio" name="required" value="1" checked/>是
                        <input type="radio" name="required" value="0"/>否
                    </p>
                    <div class="actionButtonGroup">
                        <span class="simpleButton blueSimpleButton save" style="margin-bottom:10px;">确定</span>
                        <span class="simpleButton clear">清除</span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 答题统计管理界面 -->
<div class="assignUserEditor userSelectPanel" data-remodal-id="assignUserEditor" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div id="assignUserContainer" class="userListFrame userSelectStatus" style="border-bottom:none">
        <button data-remodal-action="close" class="remodal-close"></button>
        <div class="box">
            <div class="btn-wrapper;">
                <div class="tools">
                    <span class="selectAll"><input type="checkbox">全选</span>
                    <span class="search">
						<input id="search-info" name="search" value="" type="text" placeholder="姓名|单位|邮箱">
						<a href="javascript:void(0);" class="search-icon">
                            <img src="/image/search01.png" alt="搜索">
                        </a>
					</span>
                    <span class="search-info-tip">&nbsp;</span>
                </div>
            </div>
            <div class="tabContainer">
                <form>
                    <input type="radio" name="userTab" checked="checked" value="0"/>作业派送
                    <input type="radio" name="userTab" class="notFirst" value="1"/>作业查看
                </form>
            </div>
        </div>
        <div class="userSelectTab userList-wrapper forUserSelect list-wrapper ">
            <table class="usersTable standardUserListTable standardTable" id="attendedUsers">
                <thead>
                <tr class="title">
                    <th class="check"></th>
                    <th>姓名</th>
                    <th>所属单位</th>
                    <th>邮箱</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <div class="userAnswerTab userList-wrapper forUserAnswer  list-wrapper">
            <table class="usersTable standardUserListTable standardTable" id="usersReply">
                <thead>
                <tr class="title">
                    <th class="check"></th>
                    <th>姓名</th>
                    <th>所属单位</th>
                    <th>邮箱</th>
                    <th>提交状态</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <ul class="vertical-menu float_vertical_menu">
            <li class="red" title="邮件派送" width="33.4%" id="mailDispatch">
                <span class="mail-icon"></span>
                <a href="javascript:void(0);"><span>邮件派送</span></a>
            </li>
            <li class="orange forUserAnswer" title="批量下载" width="33.4%" id="downloadAssign">
                <span class="download-icon"></span>
                <a href="javascript:void(0);"><span>批量下载</span></a>
            </li>
            <li class="grey forUserAnswer" title="删除" width="33.4%" id="deleteAnswer">
                <span class="delete-icon"></span>
                <a href="javascript:void(0);"><span>批量删除</span></a>
            </li>
        </ul>
    </div>
</div>

<!-- 编辑邮件信息完成作业邮件派送 -->
<div class="dispatchEmailEditor normal" data-remodal-id="dispatchEmailEditor" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close"></button>
    </div>
    <div class="wrapper common">
        <input type="text" class="editor" name="mailTitle" class=""/>
        <p class="tip">邮件标题</p>
        <textarea class="editor description" name="mailContent"></textarea>
        <p class="tip">邮件内容</p>
        <button data-remodal-action="cancel" class="remodal-cancel">取消</button>
        <button data-remodal-action="confirm" class="remodal-confirm">确定</button>
    </div>
</div>

<!-- 查看学员提交的作业情况-->
<div class="answerEditor" data-remodal-id="answerEditor" role='dialog' aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <div class="box">
        <button data-remodal-action="close" class="remodal-close"></button>
    </div>
    <div class="wrapper">
        <ul class='answerList'>
        </ul>
        <div class='feedback'>
            <form id='feedbackForAnswer'>
                <div class='layer'>
                    <span class='title'>审核意见：</span>
                    <input name='status' type='radio' value='-1'/>驳回
                    <input type='radio' value='2' checked='checked' name='status'/>通过
                </div>
                <div class='layer'>
                    <span class='title'>审核说明：</span>
                    <textarea class='advice'></textarea>
                </div>
                <div class='layer'>
                    <span class='simpleButton'>评价作业</span>
                </div>
                <input type='hidden' name='operatorId' value=''/>
                <input type='hidden' name='assignId' value=''/>
            </form>
        </div>
    </div>
</div>

<div class="uploader" data-remodal-id="uploader" role="dialog" aria-labelledby='modal1Title' aria-describedby='modal1Desc'>
    <button data-remodal-action="close" class="remodal-close"></button>
    <div id="uploadInfoContainer" class="box">
        <input type="file" name="file_upload" id="file_upload"/>
        <p id="uploadInfo"></p>
        <div id="fileQueue"></div>
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
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript"> dojo.require("dojo.widget.*");</script>
<script type="text/javascript" src="/js/widget/window.js"></script>
<script type="text/javascript" src="/js/swfobject.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/basicUserFunc.js"></script>
<script type="text/javascript" src="/js/jquery.form.js"></script>
<script type="text/javascript" src="/js/material/assignment.js"></script>
<script type="text/javascript">
    $(function () {
        var h = document.documentElement.clientHeight || document.body.clientHeight;
        var originHeight = $(".list-wrapper").css("max-height");
        var resetHeight = parseInt(originHeight) > h * 0.75 ? h * 0.75 : originHeight;
        $(".list-wrapper").css("max-height", resetHeight);
    })
</script>
</body>
</html>