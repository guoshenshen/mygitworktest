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
            <input id="defaultPageSize" type="hidden" value="${defaultPageSize}" />
            <div class="homezoneall">
                <div class="homezonecontent">
                    <!-- InstanceBeginEditable name="main" -->
                    <!--首页 内容 -->
                    <div class="gl_06" style="border:0">
                        <div id="gl_00" style="width:95%">
                            <div class="cj_01">
                                <a href="../examResultManage/examScores.do?examForTrainFlag=1&eid=${eid }&type=1" id="current" class="cj_l">
                                    查看成绩
                                </a>
                            </div>
                            <div class="cj_02">
                                <a href="../examResultManage/importExamScore.do?examForTrainFlag=1&eid=${eid}" class="cj_l">线下成绩录入</a>
                            </div>
                            <div class="cj_02">
                                <a href="../examScoreFile/exportExcel.do?examForTrainFlag=1&eid=${eid}" class="cj_l">导出成绩</a>
                            </div>
                        </div>
                        <div class="condition">
                            <%--  ../examResultManage/examScoresSearch.do?examForTrainFlag=1&eid=${eid }&type=1  --%>
                            <form id="form5" method="post" action="">
                                编号：<input id="empCode" type="text" name="empCode" size="15" value="${empCode}" />&nbsp;&nbsp;
                                姓名：<input id="empName" type="text" name="empName" size="15" value="${empName}" />
                                <button type="button" class="btn btn-primary" id="searchID" style="letter-spacing:5px;text-indent:5px">查询</button>
                                <%--<a href="javascript:document.getElementById('form5').submit();" class="btn-orange-l location">
                                    <span class="btn-orange-r">查询</span>
                                </a>--%>
                            </form>
                        </div>
                        <br/>
                        <div class="clr"></div>
                        <div id="cj_03"></div>

                        <c:if test="${examInfo.ifRepeatExam == 1}">
                            <form id="form1" name="form1" method="post" action="../examResultManage/modifyUsualScore.do">
                                <input type="hidden" name="eid" value="${eid}"/>
                                <table class="table table-striped table-bordered batchOperation" id="tableId">
                                    <tr class="tableTh">
                                        <th>考试名称</th>
                                        <th>编号</th>
                                        <th>姓名</th>
                                        <th>所属单位</th>
                                        <th>第一次试卷成绩(<font color=red>${percentAge}</font>)</th>
                                        <th>第一次考试提交时间</th>
                                        <th>最高试卷成绩(<font color=red>${percentAge}</font>)</th>
                                        <th>最高分考试提交时间</th>
                                        <th>
                                            平时成绩
                                            <c:if test="${usualPercentAge != null}">
                                                (<font color=red>${usualPercentAge}</font>)
                                            </c:if>
                                        </th>
                                        <th>总分</th>
                                    </tr>
                                </table>
                                <c:if test="${examScoresList != null}">
                                    <c:forEach var="exam" items="${examScoresList}" varStatus="status">
                                        <tr>
                                            <td align="center">${exam.examName }</td>
                                            <td align="center">${exam.empCode }</td>
                                            <td align="center">${exam.empName }</td>
                                            <td align="center">${exam.orgName }</td>
                                            <td align="center">${exam.firstPaperScore }</td>
                                            <td align="center">${exam.firstSubmitDate }</td>
                                            <td align="center">${exam.paperScore }</td>
                                            <td align="center">${exam.submitDate }</td>
                                            <c:if test="${percentAge != 1.0}">
                                                <td align="center">
                                                    <input style="width:40px;" type="text" name="usualScore" id="usualScore${exam.id }" value="${exam.usualScore }"
                                                           onblur="usual(this.value,${exam.id });"/>
                                                    <input type="hidden" name="usualScore${exam.id }" id="us${exam.id}"/>
                                                </td>
                                            </c:if>
                                            <c:if test="${percentAge == 1.0}">
                                                <td></td>
                                            </c:if>
                                            <td align="center">${exam.totalScore }</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${examScoresList == null or examScoresList == '' or examScoresList == []}">
                                    <tr>
                                        <td colspan="10" align="left">暂无数据</td>
                                    </tr>
                                </c:if>
                            </form>
                            <div class="condtion">
                                <ul class="pagination-admin" style="float:right"></ul>
                                <div style="clear:both"></div>
                            </div>
                        </c:if>

                        <c:if test="${examInfo.ifRepeatExam != 1}">
                            <form id="form1" name="form1" method="post" action="../examResultManage/modifyUsualScore.do?examForTrainFlag=1">
                                <input type="hidden" name="eid" value="${eid}"/>
                                <table class="homecontenttable homezonecontentborder" id="tableId">
                                    <tr class="tableTh">
                                        <th>考试名称</th>
                                        <th>编号</th>
                                        <th>姓名</th>
                                        <th>所属单位</th>
                                        <th>试卷成绩(<font color=red>${percentAge}</font>)</th>
                                        <th>
                                            平时成绩
                                            <logic:present name="usualPercentAge">
                                                (<font color=red>${usualPercentAge}</font>)
                                            </logic:present>
                                        </th>
                                        <th>提交时间</th>
                                        <th>总分</th>
                                    </tr>
                                </table>
                                <c:if test="${examScoresList != null}">
                                    <c:forEach var="exam" items="${examScoresList}" varStatus="status">
                                        <tr>
                                            <td align="center">${exam.examName }</td>
                                            <td align="center">${exam.empCode }</td>
                                            <td align="center">${exam.empName }</td>
                                            <td align="center">${exam.orgName }</td>
                                            <td align="center">${exam.paperScore }</td>

                                            <c:if test="${percentAge != 1.0 }">
                                                <td align="center">
                                                    <input style="width:40px;" type="text" name="usualScore" id="usualScore${exam.id }" value="${exam.usualScore }"
                                                           onblur="usual(this.value,${exam.id });"/>
                                                    <input type="hidden" name="usualScore${exam.id }" id="us${exam.id}"/>
                                                </td>
                                            </c:if>
                                            <c:if test="${percentAge == 1.0 }">
                                                <td></td>
                                            </c:if>

                                            <td align="center">${exam.submitDate }</td>
                                            <td align="center">${exam.totalScore }</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${examScoresList == null or examScoresList == '' or examScoresList == []}">
                                    <tr>
                                        <td colspan="8" align="left">暂无数据</td>
                                    </tr>
                                </c:if>
                            </form>
                            <div class="condtion">
                                <ul class="pagination-admin" style="float:right"></ul>
                                <div style="clear:both"></div>
                            </div>
                        </c:if>

                        <div class="clr"></div>
                        <div id="content_03">
                            <div class="gl_03">
                                <a href="../examManage/toExamManageHome.do?examForTrainFlag=1&pageNo=1" class="btn-blue-l" style="margin-left:10px">
                                    <span class="btn-blue-r">返&nbsp;&nbsp;回</span>
                                </a>
                                <c:if test="${percentAge != 1.0}">
                                    <a href="javascript:document.getElementById('form1').submit();" class="btn-orange-l">
                                        <span class="btn-orange-r">保&nbsp;&nbsp;存</span>
                                    </a>
                                </c:if>
                            </div>
                        </div>
                        <div class="clr"></div>
                    </div>
                    <div align='center'></div>
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
<link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
<link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/common/home.js"></script>
<SCRIPT language="JavaScript" src="/js/widget/window.js"></SCRIPT>
<%--
<script language='javascript'>
    $(function(){
        examList();
    });
    function examList(){
        //1.获取分页工具包
        var paginationTool=$.getPaginationConfig();

        //2.针对某分页任务，就该任务的逻辑覆盖分页工具包中的方法
        var extraPaginationConfig= {
            //分页器url，访问该url，可获取分页及某页的记录
            getUrlForPagination:function(){return "../examResultManage/searchExamScoreList.do"},

            //清空之前加载的记录（加载新的分页结果，需要执行该方法）
            actionForClearLoadedData:function(){
                $("#tableId .infoRow").remove();
            },
            actionForSucessLoadingData:function(data){
                var dataList = data.list;
                var dataLength = dataList.length;
                var htmlArray = new Array();

                if(dataList != null && dataLength > 0){
                    for(var i = 0; i < dataLength ; i++){
                        var exam = dataList[i];
                        htmlArray.push("<tr class='infoRow' id='tr_"+exam.id+"' >");

                        htmlArray.push("<td>");
                        htmlArray.push("</td>");

                        htmlArray.push("</tr>");

                        $("#tableId .infoRow").remove();
                        $("#tableId").append(htmlArray.join(""));
                    }
                } else{


                    $("#tableId .infoRow").remove();
                    $("#tableId").append(htmlArray.join(""));
                }
            }
            //将读取的分页记录组装成html并加载到页面
        };

        //将任务逻辑注入分页工具包
        $.extend(paginationTool,extraPaginationConfig);
        //恢复分页器默认设置
        paginationTool.resetSearchInfo();

        //向分页器传递查询条件
        paginationTool.searchInfo.empCode = $("#empCode").val();
        paginationTool.searchInfo.empName = $("#empName").val();

        //触发分页器
        paginationTool.actionForLoadingPagination();

        $(".mainbody").on("click","#searchID",function(){
            paginationTool.searchInfo.empCode = $("#empCode").val();
            paginationTool.searchInfo.empName = $("#empName").val();
            paginationTool.actionForLoadingPagination();
        })
    }
</script>--%>

<script type="text/javascript">
    function usual(usualscore, id) {
        var usualScoreId = 'us' + id;
        document.getElementById(usualScoreId).value = usualscore;
    }
</script>
<!-- InstanceEndEditable -->
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
</body>
</html>
