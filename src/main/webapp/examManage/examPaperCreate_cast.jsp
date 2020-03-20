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
            <!-- your content is here -->
            <!-- InstanceBeginEditable name="main" -->
            <div class="gl_11_2">
                <span class="gl_11_no" style="margin:-15px;">1、考试基本信息</span>
                <span class="gl_11_yes">2、指定试卷</span>
                <span class="gl_11_no" style="margin-left:15px;">3、安排人员</span>
                <span class="gl_11_no" style="margin-left:-30px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、发布通知</span>
            </div>

            <div id="content_01">
                <form name="paperTitleSearch" action="examPaper.do?method=examPaperSearch&firstSearch=true" method="post">
                    <input type="hidden" id="eid" value="${examInfo.id}"/>
                    <input type="radio" id="paperCreateType" name="paperCreateType" value="0" checked="checked"/>
                    <span style="font-size:13px;">指定试卷</span>
                    <div style="float:right;font-family:宋体,Arial;font-size:13px;padding-right:150px;position:relative;top:-9px">
                        试卷标题:<input id="searchKey" type="text" name="searchKey" value="${paperTitleSearch}"/>
                        <a href="javascript:paperSearch();" class="btn-blue-l" style="position:absolute;left:230px;top:-3px;">
                            <span class="btn-blue-r">查询</span>
                        </a>
                        <a href="javascript:reset();" style="position:absolute;left:280px;top:-3px;" class="btn-orange-l">
                            <span class="btn-orange-r">重填</span>
                        </a>
                    </div>
                </form>
            </div>
            <div id="content_02">
                <form action="" name="examPaperForm" method="post">
                    <table class="table table-striped table-bordered batchOperation">
                        <tr class="tableTh">
                            <th>选择</th>
                            <th>试卷名称</th>
                            <th>预览</th>
                            <th>试卷类型</th>
                        </tr>
                        <c:if test="${paperList != null}">
                            <c:forEach var="paper" items="${paperList}" varStatus="status">
                                <tr>
                                    <td align="center">
                                        <input type="radio" id="paperId" name="paperId" value="${paper.ID}"
                                               <c:if test="${paper.ID == examPaper.paperId}">checked</c:if> />
                                    </td>
                                    <td align="center">${paper.paperTitle}</td>
                                    <td align="center">&nbsp;<a href="javascript:previewPaper(${paper.id})">预览</a>&nbsp;
                                    </td>
                                    <td align="center">
                                        <c:if test="${paper.paperTypeId == 0}">指定试题试卷</c:if>
                                        <c:if test="${paper.paperTypeId == 1}">策略试卷</c:if>
                                        <c:if test="${paper.paperTypeId == 2}">自测试卷</c:if>
                                        &nbsp;
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${paperList == null or paperList== '' or paperList == []}">
                            <tr>
                                <td colspan="5">暂无数据</td>
                            </tr>
                        </c:if>
                    </table>
                </form>
            </div>

            <div id="content_03">
                <%--<form id="form2" name="form2" action="examPaper.do?method=toExamPaperCreate" method="post">
                    <div align="right">
                        <input type="hidden" id="pageNo" name="pageNo" value="1"/>
                        <logic:present name="pager"> 总数:
                            <bean:write name="pager" property="rowCount"/>
                        |
                        每页数:
                        <html:select name="pager" property="pageSize"
                                     onchange="document.getElementById('pageNo').value='1';document.getElementById('form2').submit();">
                        <logic:notEmpty name="pager" scope="request">
                            <html:options name="pager" property="pageSizeIndexs"/>
                        </logic:notEmpty>
                        </html:select>
                        </logic:present>

                        <logic:present name="pager"> [<a
                            href="javascript:document.getElementById('pageNo').value='<bean:write name="pager" property="firstPageNo" />';document.getElementById('form2').submit();"
                            class="nr_right_b53_l">首页</a>]
                        [<a
                            href="javascript:document.getElementById('pageNo').value='<bean:write name="pager" property="prePageNo" />';document.getElementById('form2').submit();"
                            class="nr_right_b53_l">上一页</a>]
                        [<a
                            href="javascript:document.getElementById('pageNo').value='<bean:write name="pager" property="nextPageNo" />';document.getElementById('form2').submit();"
                            class="nr_right_b53_l">下一页</a>]
                        [<a
                            href="javascript:document.getElementById('pageNo').value='<bean:write name="pager" property="lastPageNo" />';document.getElementById('form2').submit();"
                            class="nr_right_b53_l">尾页</a>]
                        转至
                        <html:select name="pager" property="pageNo"
                                     onchange="javascript:document.getElementById('pageNo').value=this.value;document.getElementById('form2').submit();">
                        <logic:notEmpty name="pager" scope="request">
                            <html:options name="pager" property="pageNoIndexs"/>
                        </logic:notEmpty>
                        </html:select>
                    </logic:present>
                </form>--%>
            </div>
            <div class="clr"></div>
            <div class="btnContainer">
                <div class="clearfix">
                    <a href="examInfo.do?method=toEditExamInfoById&eid=${eid}" class="btn-blue-l">
                        <span class="btn-blue-r">上一步</span>
                    </a>
                    <a href="javascript:examPaperInsert();" class="btn-orange-l">
                        <span class="btn-orange-r">下一步</span>
                    </a>
                </div>
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
<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
<script type="text/javascript" src="/js/widget/window.js"></script>
<script type="text/javascript" src="/js/widget/classify.js"></script>
<script type="text/javascript">
    function examPaperInsert() {
        var paperCreateType = document.getElementById("paperCreateType");
        if (paperCreateType.value == 0) {
            if (typeof(document.examPaperForm.paperId) == 'undefined') {
                //alert('创建试卷后再指定试卷！');
                jAlert('创建试卷后再指定试卷！', '填写提示');
                return;
            }

            var len = document.examPaperForm.paperId.length;
            var checked = false;
            if (len != undefined) {
                for (i = 0; i < len; i++) {
                    if (document.examPaperForm.paperId[i].checked == true) {
                        checked = true;
                        break;
                    }
                }
            } else {
                if (document.examPaperForm.paperId.checked == true) checked = true;
            }
            if (!checked) {
                //alert("必须选择一种试卷!");
                jAlert("必须选择一种试卷!", '填写提示');
                return;
            }
        }
        var url = "examPaper.do?method=insert&paperCreateType=" + paperCreateType.value;
        document.examPaperForm.action = url;
        document.examPaperForm.submit();
    }

    function paperSearch() {
        var searchKey = document.getElementById("searchKey").value;
        /*if(searchKey.length>0){
            document.paperTitleSearch.submit();
        }*/
        document.paperTitleSearch.submit();
    }

    function reset() {
        $("#searchKey").val("");
    }

    function previewPaper(paperId) {
        var pop_url = "paperManage.do?method=previewTestPaper&paperId=" + paperId;
        var pop = $("<div id='_pop_win'><h2>试卷预览"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='450' width='900' scrolling='auto' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {
                width: "900px",
                height: "480px",
                cursor: "default",
                left: ($left - 900) / 2 - 10,
                top: ($top - 400) / 2 - 10
            }
        });
    }
</script>
<!-- InstanceEndEditable -->
<link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>
<link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
<link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
<link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/common/home.js"></script>
<!-- InstanceBeginEditable name="head" --><!-- InstanceEndEditable -->
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
</body>
</html>
