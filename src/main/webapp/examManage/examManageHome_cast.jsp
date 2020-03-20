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
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css"/>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css"/>
    <link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="/js/jquery-latest.js"></script>
    <script type="text/javascript" src="/js/nav/amenu.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/JSCommonTools.js"></script>
    <script type="text/javascript" src="/js/aTool.js"></script>

    <link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen"/>
    <script language="javascript" src="/js/jquery.cookie-min.js"></script>
    <link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
    <link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="/js/common/select.js"></script>
    <link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css"/>

    <link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css"/>
    <link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css"/>
    <link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/common/tools.js"></script>
    <script type="text/javascript" src="/js/common/home.js"></script>
    <script type="text/javascript" src="/js/dojojs/dojo.js"></script>
    <script type="text/javascript" src="/js/jquery-json.js"></script>
    <script type="text/javascript" src="/js/nav/roll.js"></script>
    <script language="javascript" src="/js/jquery.cookie-min.js"></script>
    <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
    <script type="text/javascript" src="/js/widget/classify.js"></script>

    <script language="javascript" src="/js/jquery.cookie-min.js"></script>
    <link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
    <link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
</head>
<body class="admin">
<div class="adminFrame">
    <div class="topbody"></div>
    <div class="mainbody">
        <div id="trace" class="content"></div>
        <div class="mainContent content">
            <div id="content_single">
                <div class="headtitle">
                    <img hspace="4" align="absmiddle" src="/image/folder_table.png"/>
                    考试管理
                </div>
                <div class="condition">
                    <form method="post" id="form5" name="form5" action="<%=basePath%>examManage.do?method=toExamManageHome&research=true">
                        考试名称：
                        <input id="key" type="text" name="examTitleContent" value="${examTitleContent}" size="15" style="height:16px"/>&nbsp;&nbsp;
                        考试类型：
                        <select name="examType">
                            <option value="2">所有</option>
                            <option value="0" <c:if test="${examType == 0}">selected</c:if> >线上</option>
                            <option value="1" <c:if test="${examType == 1}">selected</c:if> >线下</option>
                        </select>
                        <a href="javascript:document.getElementById('form5').submit()" class="btn-orange-l location"><span class="btn-orange-r">查询</span></a>
                    </form>
                </div>
            </div>
            <div class="cls"></div>
            <div id="content_02">
                <form id="form1" name="form1" method="post">
                    <table class="table table-striped table-bordered batchOperation" rules="cols" width="100%" cellspacing="0" cellpadding="0">
                        <tr class="tableTh">
                            <th>
                                <input type="checkbox" id="selectAll" name="selectAll" onClick="_setSelected(this,'selectbox','form1')"/>
                            </th>
                            <th> 考试名称</th>
                            <th> 曝光时间</th>
                            <th> 答卷管理</th>
                            <th> 成绩管理</th>
                            <th> 报名审批</th>
                            <th> 状态</th>
                            <th> 操作</th>
                        </tr>
                        <c:if test="${examList != null}">
                            <c:forEach var="exam" items="${examList}" varStatus="status">
                                <tr>
                                    <td align="center">
                                        <c:if test="${exam.examType == 0}">
                                            <c:if test="${exam.isPublish == 0}">
                                                <input type="checkbox" name="selectbox" value="<bean:write name="exam" property='id'/>"/>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${exam.examType == 1}">
                                            <input type="checkbox" name="selectbox" value="${exam.id}"/>
                                        </c:if>
                                    </td>
                                    <td width="130px" align="center">
                                        <a href="examManage.do?method=viewExamInfoAdmin&eid=${exam.id}" title="${exam.examTitle}">
                                            <c:set var="examTitle" value="${exam.examTitle}"/>
                                            <c:choose>
                                                <c:when test="${fn:length(examTitle)>10}">
                                                    <c:out value="${fn:substring(examTitle,0,9)}...">...</c:out>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${examTitle}"></c:out>
                                                </c:otherwise>
                                            </c:choose>
                                        </a>
                                    </td>
                                    <td align="center">
                                        <fmt:formatDate value="${exam.startTime }" pattern="yyyy年MM月dd日"/><br/>
                                        ~
                                        <fmt:formatDate value="${exam.endTime }" pattern="yyyy年MM月dd日"/>
                                    </td>
                                    <td align="center">&nbsp;
                                        <a href="examResultManage.do?method=toOnlinePaperReading&eid=${exam.id}&pageNo=1">阅卷</a>
                                    </td>
                                    <td align="center">
                                        <a href="examResultManage.do?method=examScores&eid=${exam.id }">
                                            成绩管理
                                        </a>&nbsp;(${exam.joinCount }人参加 ${exam.finishCount }人完成)
                                    </td>
                                    <td align="center">
                                        <a href="examReg.do?method=toExamRegAudit&eid=${exam.id}&pageNo=1">报名审批</a>
                                        <c:if test="${exam.regCount != null}">
                                            <c:if test="${exam.regCount == 0}">
                                                (<font color="red">${exam.regCount}</font>人)
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <td align="center">
                                        <c:if test="${exam.examType == 0}">
                                            <c:if test="${exam.isPublish == 0}">
                                                未发布
                                            </c:if>
                                            <c:if test="${exam.isPublish == 1}">
                                                <font color="red">已发布</font>
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <td align="center">
                                        <c:if test="${exam.examType == 0}">
                                            <c:if test="${exam.isPublish == 0}">
                                                <a href="examInfo.do?method=toEditExamInfoById&eid=${exam.id}">修改</a>&nbsp;
                                                <c:if test="${exam.paperId != null}">
                                                    | <a href="javascript:void(0);" onclick="publishExam(${exam.id},1)">发布</a>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${exam.isPublish == 1}">
                                                <c:if test="${exam.paperId != null}">
                                                    <a href="javascript:previewPaper(${exam.paperId})">预览试卷</a> |
                                                    <a href="javascript:void(0);" onclick="publishExam(${exam.id},0)">取消发布</a>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${exam.examType == 1}">
                                            <a href="examInfo.do?method=toEditExamInfoById&eid=${exam.id}">修改</a>&nbsp;
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${examList == null}">
                            <tr>
                                <td colspan="8" align="left">暂无数据</td>
                            </tr>
                        </c:if>
                    </table>
                </form>
            </div>
            <div id="content_03">
                <div class="gl_03">
                    <a href="<%=basePath%>examManage.do?method=toCreateExam" class="btn-blue-l">
                        <span class="btn btn-primary">新建考试</span>
                    </a>
                    <a href="javascript:deleteExam();" class="btn-orange-l"><span class="btn btn-danger">批量删除</span></a>
                </div>
                <div class="clr"></div>
            </div>
        </div>
    </div>
</div>
<div class="bottombody"></div>
<script language='javascript'>
    function publishExam(eid, type) {
        if (type == 1) {
            jConfirm('确定发布考试？', '提示', function (r) {
                if (r) {
                    window.location.href = "examInfo.do?method=publishExam&eid=" + eid;
                }

            });
        } else {
            jConfirm('取消发布考试？', '提示', function (r) {
                if (r) {
                    window.location.href = "examInfo.do?method=publishExam&eid=" + eid;
                }
            });
        }
    }

    function deleteExam() {
        var tagcheck = 0;
        var formElement = document.getElementById("form1");
        var eles = formElement.elements;

        for (var j = 0; j < eles.length; j++) {
            if (eles[j].type == "checkbox" && eles[j].name == "selectbox") {
                if (eles[j].checked) {
                    tagcheck = 1;
                    break;
                }
            }
        }

        if (tagcheck == 1) {
            jConfirm('确认删除选定的考试？', '提示', function (r) {
                if (r) {
                    document.form1.action = 'examManage.do?method=delExamInfoByIds';
                    document.getElementById('form1').submit();
                } else {
                    return;
                }
            });
        } else {
            jAlert("请选择要操作的考试！", '填写提示');
        }
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

<logic:notEmpty name="alertString" scope="request">
    <script type="text/javascript">
        //alert("${alertString}");
        jAlert("${alertString}", '');
    </script>
</logic:notEmpty>

</body>
</html>
