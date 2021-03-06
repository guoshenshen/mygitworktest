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
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
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
            <div class="homezoneall" id="newsdefault">
                <div class="homezonehead">
                    <span class="homezonetitle">培训新闻</span>
                </div>
                <div class="homezonecontent">
                    <!-- InstanceBeginEditable name="main" -->
                    <br/><br/>

                    <form action="../mtTrainNews/toAddNews.do" method="post" id="form1" class="form-horizontal">
                        <%--这是验证是否表单重复提交，现在的基地项目中，还未对重复表单提交做控制，以后加上了再说--%>
                        <%--<input type="hidden" name="<%=org.apache.struts.taglib.html.Constants.TOKEN_KEY%>" value="<%=session.getAttribute(Globals.TRANSACTION_TOKEN_KEY) %>"/>--%>

                        <input type="hidden" name="filterFlag" value="false"/>
                        <div class="form-group">
                            <label for="newsTitle" class="col-sm-2 control-label">新闻标题：</label>
                            <div class="col-sm-8">
                                <input type="text" id="newsTitle" name="newsTitle" class="form-control" size="42" value="${newsTemplateList[0].newsTitle}"/>
                            </div>
                            <div class="col-sm-1"><font color="red">*</font></div>
                        </div>
                        <div class="form-group newshidden">
                            <label for="newsTitle" class="col-sm-2 control-label">新闻内容：</label>
                            <div class="col-sm-8">
                                <textarea id="newsContent" rows="5" cols="50" class="" name="newsContent">${newsTemplateList[0].newsContent}</textarea>
                            </div>
                        </div>
                    </form>

                    <div style="float:right;margin-right:50px;margin-bottom:10px">
                        <a href="../mtTrainNewsTemplate/queryNewsTemplate.do" class="btn btn-primary">使用内置模板</a>
                        <a href="javascript:void(0);" class="btn btn-primary" onclick="toAddNews();">保&nbsp;存</a>
                    </div>

                </div>
            </div>

            <div id="preview_news" style="display:none;cursor:default;">
                <div id='_pop_win'><h2>培训新闻预览<a href='javascript:void(0)' class='pop_close_btn'>X</a></h2></div>
                <p></p>
                <div id="preview_news_body" style="margin-left:3%;margin-right:3%">
                </div>
            </div>

            <div class="homezoneall" id="#newsnow">
                <div class="homezonehead">
                    <span class="homezonetitle">当前培训新闻</span>
                </div>
                <div class="homezonecontent">
                    <form id="form4" name="form4" method="post">
                        <table class="homecontenttable">
                            <tr class="tableTh">
                                <th align="center">
                                    新闻标题
                                </th>
                                <!-- <th>新闻摘要</th> -->
                                <th>
                                    推送到首页新闻动态栏目中
                                </th>
                                <th width="180px">
                                    操作
                                </th>
                            </tr>

                            <c:if test="${trainNewsList != null}">
                                <c:forEach var="trainNews" items="${trainNewsList}" >
                                    <tr>
                                        <td align="center">
                                                ${trainNews.newsTitle}<br/>
                                        </td>

                                        <td align="center" id="recommendNews_${trainNews.newsId }">
                                            <c:if test="${trainNews.status == 1092}">
                                                <c:if test="${trainNews.recommendTag == 1}">
                                                    <a href="javascript:recommendNews(${trainNews.newsId },1)">已推送</a>
                                                </c:if>
                                                <c:if test="${trainNews.recommendTag != 1}">
                                                    <a href="javascript:recommendNews(${trainNews.newsId },2)" onclick="return confirm('确定推送？')">未推送</a>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${trainNews.status != 1092}">
                                                未推送
                                            </c:if>
                                        </td>

                                        <td width="140px">
                                            <span id="status_${trainNews.newsId}">
                                                <c:if test="${trainNews.status !=1092}">
                                                    <a href="javascript:changeStatus(${trainNews.newsId },1091)">未发布</a>
                                                </c:if>
                                                <c:if test="${trainNews.status ==1092}">
                                                    <a href="javascript:changeStatus(${trainNews.newsId },1092)">已发布</a>
                                                </c:if>
                                            </span>
                                            | <a href="javascript:previewNews(${trainNews.newsId })">预览</a>
                                            | <a href="../mtTrainNews/forUpdateNews.do?newsId=${trainNews.newsId }">编辑</a>
                                            | <a href="../mtTrainNews/deleteNews.do?newsId=${trainNews.newsId }" onclick="return confirm('确定要删除吗?');">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </table>
                    </form>
                </div>
            </div>
            <div class="mainHidden"></div>
        </div>
        <div id="getContent" style="display:none"></div>
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

    function addStudyNote() {
        document.location.href = "studyNoteForSelfStudyAction.do?method=forAddStudyNote";
    }

    function toAddNews() {
        var newsName = document.getElementById('newsTitle').value;
        var newsContent = editor.getContent();
        if (newsContent.length > 65535) {
            jAlert('<font color=red>新闻内容字数过多，请重新编辑！</font>', '提示信息');
            return;
        }
        if (newsName.length == 0) {
            jAlert('<font color=red>请输入新闻标题！</font>', '提示信息');
        } else if (newsContent.length == 0) {
            jAlert('<font color=red>请输入新闻内容！</font>', '提示信息');
        } else if (newsName.length > 500) {
            jAlert('<font color=red>新闻标题字数过多，请重新编辑！</font>', '提示信息');
        } else {
            document.getElementById("form1").submit();
            $("tr[class='newshidden']").hide('slow');
        }
    }
    function importExcel() {
        var url = "<%=path%>/mixtraining/mixTrainingNewsImport.jsp";
        window.open(url, '参加人员', 'height=400, width=800, top=200, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
    }
    function searchNews() {
        var newsTitle = document.getElementById("newsName").value;
        window.open('<%=path%>/mtMixTrainNewsAction.do?method=queryNews&newsName=' + newsTitle, '_self');
    }
    function queryNews() {
        var cdiv = document.getElementById("newsDiv");
        if (cdiv.style.display == 'none') {
            cdiv.style.display = '';
        } else {
            cdiv.style.display = 'none';
        }
    }
    function previewNews(newsId) {
        $.ajax({
            url: '../mtTrainNews/viewNews.do',
            type: 'post',
            dataType: 'JSON',//JSON,html
            data: "newsId=" + newsId + "",
            success: function (data, evt) {
                //var nodeArr=data;
                $('#getContent').html(data.data).hide();
                var newsTitle, author, createTime, newsContent;
                var temp = "";
                newsTitle = $('#_newsTitle').html();
                author = $('#_operatorName').html();
                createTime = $('#_newsDate').html();
                newsContent = $('#_newContent').html();
                if (newsTitle == 'null') newsTitle = "";
                if (author == 'null') author = "";
                if (createTime == 'null') createTime = "";
                if (newsContent == 'null') newsContent = "";
                temp += "<center><font size='5' >" + newsTitle + "</font><br/><br/><font size='2' color='#777'>作者：" + author + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：" + createTime + "</font></center><div style='maigin-left:200px'><font size='3' color='#777'>" + newsContent + "</font></div>";
                temp += "";

                $('#preview_news_body').html(temp);
                $("a.pop_close_btn").click(function () {
                    $.unblockUI();
                });
                $.blockUI({
                    message: $('#preview_news'), css: {
                        width: '800px', height: '480px', background: '#F5F5F5', top: ($top - 480) / 2 - 10 + 'px',
                        left: ($left - 800) / 2 - 10 + 'px', textAlign: '""', overflow: 'scroll'
                    }
                });
            }
        });
    }
</script>
<script type="text/javascript">
    $(function () {
        var option = {
            initialStyle: 'p{line-height:1.5em;font-size:14px;font-family:宋体};span{font-size:14px;font-family:宋体;line-height:1.5em}'
        };
        editor = new UE.ui.Editor(option);
        editor.render('newsContent');
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
        $("#newsTitle").focus(function () {
            $that = $(this);
            $that.parent().parent().siblings().show('slow');
        });
    })
</script>
<script type="text/javascript">
    function recommendNews(newsId, flag) {
        $.ajax({
            url: '../mtTrainNews/recommendNewsAjax.do',
            data: {"newsId": newsId},
            dataType: "JSON",
            success: function (data) {
                if (data.success) {
                    var html = ""
                    if (flag == 1) {
                        html = '<a href="javascript:recommendNews(' + newsId + ',2)" onclick="return confirm(\'确定推送？\')">未推送</a>';
                    } else if (flag == 2) {
                        html = '<a href="javascript:recommendNews(' + newsId + ',1)">已推送</a>';
                    }
                    $("#recommendNews_" + newsId).empty();
                    $("#recommendNews_" + newsId).html(html);
                }
            }
        });
    }
    function changeStatus(newsId, flag) {
        $.ajax({
            url: '../mtTrainNews/changeStatus.do',
            data: {"newsId": newsId},
            dataType: "JSON",
            success: function (data) {
                if (data.success) {
                    if (flag == 1091) { //发布
                        $("#recommendNews_" + newsId).empty();
                        $("#recommendNews_" + newsId).html('<a href="javascript:recommendNews(' + newsId + ',2)" onclick="return confirm(\'确定推送？\')">未推送</a>');
                        $("#status_" + newsId).empty();
                        $("#status_" + newsId).html('<a href="javascript:changeStatus(' + newsId + ',1092)">已发布</a>')
                    } else if (flag == 1092) { //取消发布
                        $("#recommendNews_" + newsId).empty();
                        $("#recommendNews_" + newsId).html('未推送');
                        $("#status_" + newsId).empty();
                        $("#status_" + newsId).html('<a href="javascript:changeStatus(' + newsId + ',1091)">未发布</a>')
                    }
                }
            }
        });
    }
</script>
</body>
</html>