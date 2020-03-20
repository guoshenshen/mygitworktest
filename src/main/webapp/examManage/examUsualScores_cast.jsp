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
            <div id="contentbody">
                <!-- InstanceBeginEditable name="main" -->
                <div class="gl_06" style="border:0">
                    <div id="gl_00" style="width:95%">
                        <div class="cj_01">
                            <a href="../examResultManage/examScores.do?eid=${eid }&type=1" id="current" class="cj_l">查看成绩</a>
                        </div>
                        <div class="cj_02">
                            <a href="../examResultManage/importExamScore.do?eid=${eid }" class="cj_l">线下成绩录入</a>
                        </div>
                        <div class="cj_02">
                            <a href="../examScoreFile/exportExcel.do&eid=${eid }" class="cj_l">导出成绩</a>
                        </div>
                    </div>
                    <div class="condition">
                        <form id="form5" method="post" action="../examResultManage/examScoresSearch.do?eid=${eid }&type=1">
                            员工编号：<input id="key" type="text" name="empCode" size="15"/>&nbsp;&nbsp;
                            员工姓名：<input id="key" type="text" name="empName" size="15"/>
                            <a href="javascript:document.getElementById('form5').submit();" class="btn-orange-l location">
                                <span class="btn-orange-r">查询</span>
                            </a>
                        </form>
                    </div>
                    <br/>
                    <div class="clr"></div>
                    <div id="cj_03"></div>

                    <c:if test="${examInfo.ifRepeatExam == 1}">
                        <form id="form1" name="form1" method="post" action="../examResultManage/modifyUsualScore.do">
                            <input type="hidden" name="eid" value="${eid}"/>
                            <table class="table table-striped table-bordered batchOperation">
                                <tr class="tableTh">
                                    <th>考试名称</th>
                                    <th>员工编号</th>
                                    <th>员工姓名</th>
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

                                            <c:if test="${percentAge != 1.0 }">
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
                            </table>
                        </form>
                    </c:if>


                    <c:if test="${examInfo.ifRepeatExam != 1}">
                        <form id="form1" name="form1" method="post" action="../examResultManage/modifyUsualScore.do">
                            <input type="hidden" name="eid" value="${eid}"/>
                            <table class="homecontenttable homezonecontentborder">
                                <tr class="tableTh">
                                    <th>考试名称</th>
                                    <th>员工编号</th>
                                    <th>员工姓名</th>
                                    <th>所属单位</th>
                                    <th>试卷成绩(<font color=red>${percentAge}</font>)</th>
                                    <th>
                                        平时成绩
                                        <c:if test="${usualPercentAge != null}">
                                            (<font color=red>${usualPercentAge}</font>)
                                        </c:if>
                                    </th>
                                    <th>提交时间</th>
                                    <th>总分</th>
                                </tr>

                                <c:if test="${examScoresList != null}">
                                    <c:forEach var="exam" items="${examScoresList}" varStatus="status">
                                        <tr>
                                            <td align="center">${exam.examName }</td>
                                            <td align="center">${exam.empCode }</td>
                                            <td align="center">${exam.empName }</td>
                                            <td align="center">${exam.orgName }</td>
                                            <td align="center">${exam.paperScore }</td>

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
                            </table>
                        </form>
                    </c:if>

                    <div class="clr"></div>
                    <div id="content_03">
                        <div class="gl_03">
                            <a href="../examManage/toExamManageHome.do" class="btn-blue-l" style="margin-left:10px">
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
                <div align='center'>
                </div>
            <!--首页 内容 end -->
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
<link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
<link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
<link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/common/home.js"></script>
<script language="JavaScript" src="/js/widget/window.js"></script>
<script type="text/javascript">
    function usual(usualscore, id) {
        var usualScoreId = 'us' + id;
        document.getElementById(usualScoreId).value = usualscore;
    }
</script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
</body>
</html>
