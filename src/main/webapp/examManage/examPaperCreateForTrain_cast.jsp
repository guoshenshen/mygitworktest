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
    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
    <link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
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

            <!-- your content is here -->
            <div id="funcCon" class="trainApp"></div>
            <div class="homezoneall">
                <div class="homezonecontent">
                    <input id="defaultPageSize" type="hidden" value="${defaultPageSize}" />
                    <input id="paperId2" type="hidden" value="${examPaper.paperID}" />

                    <!-- InstanceBeginEditable name="main" -->
                    <div class="gl_31_1" style="width:450px">
                        <span class="gl_11_no">1、考试基本信息</span>
                        <span class="gl_11_yes">2、指定试卷</span>
                        <span class="gl_11_no" style="margin-left:20px;">3、发布通知</span>　　
                    </div>
                    <div id="content_01">
                        <form name="paperTitleSearch" action="examPaper.do?method=examPaperSearch&examForTrainFlag=1&firstSearch=true" method="post">
                            <input type="hidden" id="eid" value="${examInfo.ID}"/>
                            <input type="radio" id="paperCreateType" name="paperCreateType" value="0" checked="checked"/>
                            <span style="font-size:13px;">指定试卷</span>
                            <div style="float:right;font-family:宋体,Arial;font-size:13px;padding-right:150px;position:relative;top:-9px">
                                试卷标题:<input id="searchKey" type="text" name="searchKey" value="${paperTitleSearch}"/>
                                <button type="button" class="btn btn-primary" id="searchPaperID" style="letter-spacing:5px;text-indent:5px">查询</button>
                                <button type="button" class="btn btn-warning" onclick="reset()" style="letter-spacing:5px;text-indent:5px">重填</button>
                            </div>
                        </form>
                    </div>

                    <div id="content_02">
                        <form action="" name="examPaperForm" method="post">
                            <table id="paperTableId" class="table table-striped table-bordered batchOperation">
                                <tr class="tableTh">
                                    <th>选择</th>
                                    <th>试卷名称</th>
                                    <th>预览</th>
                                    <th>试卷类型</th>
                                </tr>
                            </table>
                        </form>

                        <div class="condtion">
                            <ul class="pagination-admin" style="float:right"></ul>
                            <div style="clear:both"></div>
                        </div>

                    </div>
                    <div class="clr"></div>
                </div>

                <div class="condition" style="float:none;">
                    <button class="btn btn-info" type="button"
                            onclick="window.location.href='../examInfo/toEditExamInfoById.do?examForTrainFlag=1&eid=${eid}'">返回
                    </button>
                    <button class="btn btn-primary" type="button" onclick="javascript:examPaperInsert();">提交</button>
                </div>
            </div>
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
<script type="text/javascript" src="/js/aTool.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
<script type="text/javascript" src="/js/widget/window.js"></script>
<script type="text/javascript" src="/js/widget/classify.js"></script>
<script language='javascript'>
    $(function(){
        paperList();
    });
    function paperList(){

        //1.获取分页工具包
        var paginationTool=$.getPaginationConfig();

        //2.针对某分页任务，就该任务的逻辑覆盖分页工具包中的方法
        var extraPaginationConfig= {
            getUrlForPagination:function(){return "../examPaper/searchPaperList.do"}, //分页器url，访问该url，可获取分页及某页的记录

            //清空之前加载的记录（加载新的分页结果，需要执行该方法）
            actionForClearLoadedData:function(){
                $("#paperTableId .infoRow").remove();
            },
            actionForSucessLoadingData:function(data){
                var dataList = data.list;
                var dataLength = dataList.length;
                var htmlArray = new Array();

                var paperId2 = $("#paperId2").val();

                if(dataList != null && dataLength > 0){
                    for(var i = 0; i < dataLength ; i++){
                        var paper = dataList[i];
                        htmlArray.push("<tr class='infoRow' id='tr_"+paper.id+"' >");

                        htmlArray.push("<td>");
                        htmlArray.push("<input type='radio' id='paperId' name='paperId' value='"+paper.id+"'");
                        if(paperId2 != null){
                            if(paper.id == paperId2){
                                htmlArray.push("checked");
                            }
                        }

                        htmlArray.push("/>");
                        htmlArray.push("</td>");

                        htmlArray.push("<td>");
                        htmlArray.push(paper.paperTitle);
                        htmlArray.push("</td>");

                        htmlArray.push("<td>");
                        htmlArray.push("&nbsp;<a href='javascript:previewPaper("+paper.id+")'>预览</a>&nbsp;");
                        htmlArray.push("</td>");

                        htmlArray.push("<td>");
                        if(paper.paperTypeId == 0){
                            htmlArray.push("指定试题试卷");
                        }else if (paper.paperTypeId == 1){
                            htmlArray.push("策略试卷");
                        }else if (paper.paperTypeId == 2){
                            htmlArray.push("自测试卷");
                        }
                        htmlArray.push("</td>");

                        htmlArray.push("</tr>");

                        $("#paperTableId .infoRow").remove();
                        $("#paperTableId").append(htmlArray.join(""));
                    }
                } else{
                    /*当返回结果没有数据的时候,这个方法就进不来,所以这个else基本不用判断了*/
                    htmlArray.push("<tr class='infoRow'>");
                    htmlArray.push("<td colspan='5' align='left'>暂无数据</td>");
                    htmlArray.push("</tr>");

                    $("#paperTableId .infoRow").remove();
                    $("#paperTableId").append(htmlArray.join(""));
                }
            }
            //将读取的分页记录组装成html并加载到页面
        };

        //将任务逻辑注入分页工具包
        $.extend(paginationTool,extraPaginationConfig);
        //恢复分页器默认设置
        paginationTool.resetSearchInfo();

        //向分页器传递查询条件
        paginationTool.searchInfo.searchKey = $("#searchKey").val();

        //触发分页器
        paginationTool.actionForLoadingPagination();

        $(".mainbody").on("click","#searchPaperID",function(){
            paginationTool.searchInfo.searchKey = $("#searchKey").val();
            paginationTool.actionForLoadingPagination();
        })
    }
</script>

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

        var url = "../examPaper/insert.do?examForTrainFlag=1&paperCreateType=" + paperCreateType.value;
        document.examPaperForm.action = url;
        document.examPaperForm.submit();
    }

    function paperSearch() {
        //var searchKey = document.getElementById("searchKey").value;
        //document.paperTitleSearch.submit();

        paginationTool.searchInfo.searchKey = $("#searchKey").val();
        paginationTool.actionForLoadingPagination();
    }

    function reset() {
        $("#searchKey").val("");
    }

    function previewPaper(paperId) {
        var pop_url = "../paperManage/previewTestPaper.do?paperId=" + paperId;
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
<script type="text/javascript" src="/js/common/home.js"></script>
<!-- InstanceBeginEditable name="head" --><!-- InstanceEndEditable -->
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>

</body>
</html>
