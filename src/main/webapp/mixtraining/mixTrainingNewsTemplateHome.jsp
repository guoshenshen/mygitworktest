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

            <div class="homezoneall">
                <div class="homezonehead">
                    <span class="homezonetitle">培训新闻</span>
                </div>
                <div class="homezonecontent">
                    <!-- InstanceBeginEditable name="main" -->
                    <br/><br/>
                    <form action="../mtTrainNews/toAddNews.do" method="post" id="form1">
                        <input type="hidden" name="filterFlag" value="false"/>
                        <table width="85%" align="center">
                            <tr>
                                <th width="20%" class="ctext">新闻标题：</th>
                                <td><input type="text" id="newsTitle" name="newsTitle" class="textInput" size="42" value="${newsTemplateList[0].newsTitle}"></input><font color="red">*</font>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <th width="20%" class="ctext">新闻内容：</th>
                                <td><textarea id="newsContent" rows="15" cols="50" class="areaInput" name="newsContent">${newsTemplateList[0].newsContent}</textarea></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </form>
                    <div style="float:right;margin-right:50px;margin-bottom:10px">
                        <a href="../mtTrainNews/queryNews.do" class="btn-blue-l"><span class="btn-blue-r">返&nbsp;回</span></a>
                        <a href="../mtTrainNewsTemplate/queryNewsTemplate.do" class="btn-orange-l"><span class="btn-orange-r">使用内置模板</span></a>
                        <a href="javascript:void(0);" class="btn-blue-l" onclick="toAddNews();" style="margin-left:10px"><span class="btn-blue-r">保&nbsp;存</span></a>
                    </div>
                </div>
            </div>
            <div id="preview_news" style="display:none;cursor:default;">
                <div id='_pop_win'><h2>培训新闻预览<a href='javascript:void(0)' class='pop_close_btn'>X</a></h2></div>
                <p></p>
                <div id="preview_news_body" style="margin-left:3%;margin-right:3%">
                </div>
            </div>

            <!--首页 内容 end -->
            <!-- InstanceEndEditable -->
            <div class="homezoneall">
                <div class="homezonehead">
                    <span class="homezonetitle">当前培训新闻</span>
                </div>
                <div class="homezonecontent">
                    <form id="form4" name="form4" method="post">
                        <table class="homecontenttable">
                            <tr class="tableTh">
                                <th align="center">新闻标题</th>
                                <th>推送到首页新闻动态栏目中</th>
                                <th width="140px">操作</th>
                            </tr>
                            <c:if test="${trainNewsList != null}">
                                <c:forEach var="trainNews" items="${trainNewsList}" >
                                    <tr>
                                        <td align="center">
                                                ${trainNews.newsTitle}<br/>
                                        </td>
                                        <td align="center">
                                            <c:if test="${trainNews.recommendTag == 1}">
                                                <a href="../mtTrainNews/recommendNews.do?newsId=${trainNews.newsId}">已推送</a>
                                            </c:if>
                                            <c:if test="${trainNews.recommendTag != 1}">
                                                <a href="../mtTrainNews/recommendNews.do?newsId=${trainNews.newsId}" onclick="return confirm('确定推送？')">未推送</a>
                                            </c:if>
                                        </td>
                                        <td width="140px">
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
        </div>
    </div>
</div>
<div class="bottombody"></div>
<div id="getContent"></div>
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
    function addStudyNote() {
        alert("");
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
            url: 'mtMixTrainNewsAction.do?method=viewNews',
            type: 'post',
            dataType: 'html',
            data: "newsId=" + newsId + "",
            success: function (data, evt) {
                //var nodeArr=data;
                $('#getContent').html(data).hide();
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
                temp += "<center><font size='5'>" + newsTitle + "</font><br/><br/><font size='2' color='#777'>作者：" + author + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：" + createTime + "</font></center><div style='maigin-left:200px'><font size='3' color='#777'>" + newsContent + "</font></div>";
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
        editor = new UE.ui.Editor();
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
    })
</script>
</body>
</html>