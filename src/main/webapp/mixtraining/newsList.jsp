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
    <script language='javascript'>
        function _setSelected(id, type) {
            var chechTemp = id.checked;
            with (document.form1) {
                for (var j = 0; j < elements.length; j++) {
                    if (elements[j].type == "checkbox" && elements[j].name == type) {
                        if (chechTemp) {
                            document.form1.elements[j].checked = true;
                        } else {
                            document.form1.elements[j].checked = false;
                        }
                    }
                }
            }
        }
        function previewNews(newsId) {
            $.ajax({
                url: 'mtMixTrainNewsAction.do?method=viewNews',
                type: 'post',
                dataType: 'html',
                data: "newsId=" + newsId + "",
                success: function (data, evt) {
                    //alert(data);
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
                    temp += "<center><font size='5' >" + newsTitle + "</font><br/><br/><font size='2' color='#777'>作者：" + author + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：" + createTime + "</font></center><div style='maigin-left:200px'><font size='3' color='#777'>" + newsContent + "</font></div>";
                    temp += "";

                    $('#preview_news_body').html(temp);
                    $("a.pop_close_btn").click(function () {
                        $.unblockUI();
                    });
                    $.blockUI({
                        message: $('#preview_news'), css: {
                            width: '750px', height: '480px', top: ($top - 480) / 2 - 10 + 'px',
                            left: ($left - 750) / 2 - 10 + 'px', textAlign: '""', overflow: 'scroll'
                        }
                    });
                }
            });
        }
        function isPublic(newsId) {
            if (confirm("确发布吗?")) {
                window.location.href = "mtMixTrainNewsAction.do?method=updateStatus&newsId=" + newsId;
            }
        }
    </script>
</head>
<style>
    .tableTh th {
        text-align: center;
    }
    .table {
        text-align: center;
    }
    .gl_td_l {
        color: red;
    }
</style>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <input id="defaultPageSize" type="hidden" value="${defaultPageSize}"/>
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="contentbody">
                <!-- InstanceBeginEditable name="main" -->
                <!--首页 内容 -->
                <div id="content_single">
                    <div class="headtitle">
                        <img hspace="4" align="absMiddle" src="/image/folder_table.png"/>新闻管理
                    </div>
                    <form id="form6" name="form6" action="<%=path%>/mtTrainNews/queryNews.do?newsFlag=1" method="post">
                        <div class="condition" align="right">
                            <div class="condition" style="text-align:right;">
                                <input type="text" name="newsName" id="newsName" value="${newsName}" placeholder="新闻标题或内容"/>&nbsp;&nbsp;
                                <button class="btn btn-primary" type="submit" id=search>查询</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div id="content_02">
                    <form id="form1" name="form1" method="post">
                        <table class="table table-bordered table-striped">
                            <tr class="tableTh">
                                <th width="5%">
                                    <input type="checkbox" id="selectAll" name="selectAll" onClick="_setSelected(this,'newsId')"/>
                                </th>
                                <th width="30%">
                                    新闻标题
                                </th>
                                <c:if test="${tenantId == 1000}">
                                    <th width="15%">新闻类型</th>
                                </c:if>
                                <th width="10%">
                                    创建时间
                                </th>
                                <c:if test="${tenantId != 1}">
                                    <th width="10%">发布状态</th>
                                </c:if>
                                <th width="30%">操作</th>
                            </tr>
                            <c:if test="${newsList != null}">
                                <c:forEach var="trainNews" items="${newsList}" varStatus="status">
                                    <tr>
                                        <td align="center">
                                            <c:if test="${trainNews.status == 1092}"></c:if>
                                            <c:if test="${tenantId == 1}">
                                                <input type="checkbox" value="${trainNews.newsId}" name="newsId"/>
                                            </c:if>
                                            <c:if test="${tenantId != 1}">
                                                <c:if test="${trainNews.status != 1092}">
                                                    <input type="checkbox" value="${trainNews.newsId}" name="newsId"/>
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <td align="center">
                                            ${trainNews.newsTitle }<br/>
                                        </td>
                                        <c:if test="${tenantId == 1000}">
                                            <td align="center">
                                                <c:if test="${trainNews.newsType == 0}">新闻动态</c:if>
                                                <c:if test="${trainNews.newsType == 1}">政策与工作流程</c:if>
                                                <br/>
                                            </td>
                                        </c:if>
                                        <td align="center">
                                            <fmt:formatDate value="${trainNews.createTime }" pattern="yyyy-MM-dd"/>
                                            <br/>
                                        </td>
                                        <c:if test="${tenantId != 1}">
                                            <td align="center">
                                                <c:if test="${trainNews.status == 1091}">
                                                    <a class="gl_td_l" id="${trainNews.newsId }" href="javascript:void(0)">未发布</a>
                                                </c:if>
                                                <c:if test="${trainNews.status == 1092}">
                                                    <a class="gl_td_l" id="${trainNews.newsId }" href="javascript:void(0)">已发布</a>
                                                </c:if>
                                            </td>
                                        </c:if>
                                        <c:if test="${tenantId != 1}">
                                            <td>
                                                <c:if test="${trainNews.status == 1091}">
                                                    <a href="../mtTrainNews/getNewsDetailFromMainPage.do?newsId=${trainNews.newsId}&newsType=0" target="_blank">预览</a>
                                                    | <a href="../mtTrainNews/forUpdateNews.do?newsId=${trainNews.newsId}&newsFlag=1">编辑</a> |
                                                    <a href="../mtTrainNews/quoteNews.do?newsId=${trainNews.newsId}&newsFlag=1">复制</a> |
                                                    <a href="javascript:deleteNews(${trainNews.newsId})">删除</a>
                                                    | <a href="javascript:void(0)" onclick="changeStatus(this)">发布</a>
                                                </c:if>
                                                <c:if test="${trainNews.status == 1092}">
                                                    <a href="mtMixTrainNewsAction.do?method=getNewsDetailFromMainPage&newsId=${trainNews.newsId}&newsType=0" target="_blank">预览</a>
                                                    | <a href="mtMixTrainNewsAction.do?method=quoteNews&newsId=${trainNews.newsId}&newsFlag=1">复制</a>
                                                    |<a href="mtMixTrainNewsAction.do?method=deleteNews&newsId=${trainNews.newsId}&newsFlag=1" onclick="return confirm('确定要删除吗?');">删除</a>
                                                    | <a href="javascript:void(0)" onclick="changeStatus(this)">撤销发布</a>
                                                </c:if>
                                            </td>
                                        </c:if>
                                        <c:if test="${tenantId == 1}">
                                            <td>
                                                <a href="mtMixTrainNewsAction.do?method=getNewsDetailFromMainPage&newsId=${trainNews.newsId}&newsType=0" target="_blank">预览</a>
                                                | <a href="mtMixTrainNewsAction.do?method=forUpdateNews&newsId=${trainNews.newsId}&newsFlag=1">编辑</a>
                                                | <a href="mtMixTrainNewsAction.do?method=quoteNews&newsId=${trainNews.newsId}&newsFlag=1">复制</a>
                                                | <a href="mtMixTrainNewsAction.do?method=deleteNews&newsId=${trainNews.newsId}&newsFlag=1" onclick="return confirm('确定要删除吗?');">删除</a>
                                            </td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </table>
                    </form>
                </div>
                <div class="condition" style="text-align:left;">
                    <button class="addNews btn btn-primary">新建新闻</button>
                    <button class="batchDelete btn btn-danger">批量删除</button>
                    <ul class="pagination-admin" style="float:right"></ul>
                </div>
                <%--<div align="right">
                    <form id="form3" name="form3" action="mtMixTrainNewsAction.do?method=queryNews&newsFlag=1" method="post">
                        <input type="hidden" id="pageNo" name="pageNo" value="1"/>
                        <logic:present name="newsPager"> 总数:
                            <bean:write name="newsPager" property="rowCount"/>
                            |
                            每页数:
                            <html:select name="newsPager" property="pageSize" onchange="document.getElementById('pageNo').value='1';document.getElementById('form3').submit();">
                                <logic:notEmpty name="newsPager" scope="request">
                                    <html:options name="newsPager" property="pageSizeIndexs"/>
                                </logic:notEmpty>
                            </html:select>
                        </logic:present>
                        <logic:present name="newsPager"> [<a
                            href="javascript:document.getElementById('pageNo').value='<bean:write name="newsPager"
                                                                                                  property="firstPageNo"/>';document.getElementById('form3').submit();"
                            class="nr_right_b53_l">首页</a>]
                            [<a
                            href="javascript:document.getElementById('pageNo').value='<bean:write name="newsPager"
                                                                                                  property="prePageNo"/>';document.getElementById('form3').submit();"
                            class="nr_right_b53_l">上一页</a>]
                            [<a
                            href="javascript:document.getElementById('pageNo').value='<bean:write name="newsPager"
                                                                                                  property="nextPageNo"/>';document.getElementById('form3').submit();"
                            class="nr_right_b53_l">下一页</a>]
                            [<a
                            href="javascript:document.getElementById('pageNo').value='<bean:write name="newsPager"
                                                                                                  property="lastPageNo"/>';document.getElementById('form3').submit();"
                            class="nr_right_b53_l">尾页</a>]
                            转至
                            <html:select name="newsPager" property="pageNo"
                                         onchange="javascript:document.getElementById('pageNo').value=this.value;document.getElementById('form3').submit();">
                                <logic:notEmpty name="newsPager" scope="request">
                                    <html:options name="newsPager" property="pageNoIndexs"/>
                                </logic:notEmpty>
                            </html:select>
                        </logic:present>
                    </form>
                </div>--%>
                <div class="clr"></div>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript">
    $("#contentbody")
        .on("click", ".addNews", function () {
            window.location.href = "../mtTrainNews/foraddNews.do";
        }).on("click", ".batchDelete", function () {
        var idd;
        with (document.form1) {
            for (var j = 0; j < elements.length; j++) {
                if (elements[j].type == "checkbox" && elements[j].name == "newsId") {
                    if (elements[j].checked) {
                        idd = 0;
                    }
                }
            }
        }
        if (idd != 0) {
            $.Ntip({
                'content': "请选择要删除的新闻",
            })
        } else {
            $.Nconfirm({
                'confirmQuestion': "确定要删除所选新闻吗？",
                'onConfirm': function () {
                    document.form1.action = "mtMixTrainNewsAction.do?method=deleteBatch";
                    document.form1.submit();
                },
                'onDeny': function () {
                }
            })
        }
    }).on("click", ".gl_td_l", function () {
        var $currentLink = $(this);
        var newsId = $currentLink.attr("id");
        $.ajax({
            url: "mtMixTrainNewsAction.do?method=changeStatus",
            type: "POST",
            data: {"newsId": newsId},
            dataType: "json",
            traditional: true,
            success: function (data) {
                if (data.status) {
                    if (data.flag == 1092) {
                        $currentLink.html("已发布");
                        var htmlArray = new Array();
                        // <a href="mtMixTrainNewsAction.do?method=quoteNews&newsId=${trainNews.newsId}&newsFlag=1">复制</a> | <a href="mtMixTrainNewsAction.do?method=deleteNews&newsId=${trainNews.newsId}&newsFlag=1" onclick="return confirm('确定要删除吗?');">删除</a>  |
                        htmlArray.push("<a href=\"mtMixTrainNewsAction.do?method=getNewsDetailFromMainPage&newsId=" + newsId + "&newsType=0\" target=\"_blank\">预览</a> ");
                        htmlArray.push("| <a href=\"mtMixTrainNewsAction.do?method=quoteNews&newsId=" + newsId + "&newsFlag=1\">复制</a>");
                        htmlArray.push("| <a href=\"javascript:deleteNews(" + newsId + ")\">删除</a>  |<a href=\"javascript:void(0)\" onclick=\"changeStatus(this)\">撤销发布</a>  ");
                        $($currentLink).parent().next().html(htmlArray);
                        $("input[name=newsId]", $currentLink.parents("tr")).remove();
                        $.Ntip({
                            'content': "发布成功",
                            'onClose': function () {
                            }
                        });
                    } else {
                        $currentLink.html("未发布");
                        var htmlArray = new Array();
                        htmlArray.push("<a href=\"mtMixTrainNewsAction.do?method=getNewsDetailFromMainPage&newsId=" + newsId + "&newsType=0\" target=\"_blank\">预览</a> ");
                        htmlArray.push("| <a href=\"mtMixTrainNewsAction.do?method=forUpdateNews&newsId=" + newsId + "&newsFlag=1\">编辑</a> | <a href=\"mtMixTrainNewsAction.do?method=quoteNews&newsId=" + newsId + "&newsFlag=1\">复制</a> ");
                        htmlArray.push("| <a  href=\"javascript:deleteNews(" + newsId + ")\">删除</a>  | <a href=\"javascript:void(0)\" onclick=\"changeStatus(this)\">发布</a> ");
                        $($currentLink).parent().next().html(htmlArray);
                        $($currentLink).parents().find("td:first").html("<input type=\"checkbox\" value=" + newsId + " name=\"newsId\" />");
                        $.Ntip({
                            'content': "撤销发布成功",
                            'onClose': function () {

                            }
                        });
                    }
                }
                else {
                    $.Ntip({
                        'content': "新闻发布失败",
                    })
                }
            }
        });


    })

    function changeStatus(a) {
        var $currentLink = $(a);
        var $publicButton = $currentLink.parent().prev().children(":eq(0)");
        $publicButton.click();
    }

    function deleteNews(newsId) {
        $.Nconfirm({
            'confirmQuestion': "确定要删除吗?",
            'onConfirm': function () {
                window.location.href = "mtMixTrainNewsAction.do?method=deleteNews&newsId=" + newsId + "&newsFlag=1";
            },
        })
    }

    /*function changeStatus(newsId){
        var _link = $("#newsId");
                $.ajax({
                url:"mtMixTrainNewsAction.do?method=changeStatus",
                type:"POST",
                 data:{"newsId":newsId},
                 dataType:"json",
                 success:function(data){
                    if(data.status){
                        if(data.flag==1092){
                            $.Ntip({
                                'content':$(_link),
                                'onClose':function(){
                                }
                            });
                        }
                    }
                    else{
                        $.Ntip({
                            'content':"新闻发布失败",
                        })
                    }
                }
            });
    }
        */


</script>
</body>
</html>
