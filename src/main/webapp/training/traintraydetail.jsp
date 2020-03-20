<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page
        import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher" %>
<%--<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>--%>
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
        EosOperator user = (EosOperator) session
                .getAttribute(Constants.USERINFO_KEY);

    } catch (Exception ex) {
        //do nothing
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
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui.css"/>
</head>
<body class="admin" <c:if test="${mode }">style="padding-bottom:0px;"</c:if>>
<div class="adminFrame">
    <c:if test="${!mode }">
        <div class="topbody"></div>
    </c:if>
    <div class="mainbody">
        <c:if test="${!mode }">
            <div id="trace" class="content"></div>
            <div class="mainContent content">
                <div id="funcCon" class="trainApp">
        </c:if>
            </div>
            <!-- InstanceBeginEditable name="main" -->
            <div class="homezoneall">
                <!-- InstanceBeginEditable name="main" -->
                <div id="content_02">

                    <form id="form1" method="post" action="../onlineTraining/saveTrain.do" name="onlinetrainingbasicinfo" class="gl_06" style="border:none;">
                        <input type="hidden" name="trainID" id="trainID" value="${trainID}"/>

                        <table class="table1" width="100%" cellspacing="0" cellpadding="0">
                            <input name="trainWay" type="hidden" value="${trainWay }"/>
                            <input name="isStationTrain" type="hidden" value="${isStationTrain }"/>
                            <input name="isPlaned" type="hidden" value="0"/>
                            <input id="trainPlanID" type="hidden" name="trainPlanID" value="-1" readonly="readonly"/>
                            <input name="programNo" type='hidden' value="${train.programNo}"/>
                            <tr bgcolor="#f1f1f1">
                                <td colspan="4" align="left" bgcolor="#c9e5f1" style="font-size:13px;">
                                    <strong>基本信息</strong></td>
                            </tr>
                            <tr>
                                <th>培训名称：</th>
                                <td>${train.trainName}</td>
                                <th>培训年度：</th>
                                <td>${train.year}</td>
                            </tr>
                            <tr>
                                <th>培训编号：</th>
                                <td>${train.trainID}</td>
                                <th>是否分院展示：</th>
                                <td>
                                    <c:if test="${train.upTenantId == 4}">是</c:if>
                                    <c:if test="${train.upTenantId == 0}">否</c:if>
                                </td>
                            </tr>
                            <tr>
                                <th>培训类别：</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${train.trainWay == 0 }">线上培训</c:when>
                                        <c:when test="${train.trainWay == 1 }">线下培训</c:when>
                                        <c:when test="${train.trainWay == 2 }">混合培训</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th>培训开始时间：</th>
                                <td><fmt:formatDate value="${train.startTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                                <th>培训结束时间：</th>
                                <td><fmt:formatDate value="${train.endTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                            </tr>
                            <tr>
                                <th>学时：</th>
                                <td>${train.classHour}小时</td>
                                <th>天数：</th>
                                <td>${train.days}天</td>
                            </tr>
                            <tr>
                                <th>学员计划参加人数：</th>
                                <td>${train.attendantCount}</td>
                                <th>学员实际人数：</th>
                                <td>${train.researchPostNum}</td>
                            </tr>
                            <tr>
                                <th>工作人员计划人数：</th>
                                <td>${train.workerNum}</td>
                                <th>工作人员实际人数：</th>
                                <td>${train.outSideNum}</td>
                            </tr>
                            <tr>
                                <th>报销费用：</th>
                                <td>${train.expenseFee}</td>
                                <th>辅助资料：</th>
                                <td>${train.auxiliary}</td>
                            </tr>
                            <tr>
                                <th>列支渠道：</th>
                                <td>${train.feeChannel}</td>
                                <th>所需经费(万元)：</th>
                                <td>${train.fee}</td>
                            </tr>
                            <tr>
                                <th>培训对象级别：</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${train.feeLevel == 0 }">处级及以下</c:when>
                                        <c:when test="${train.feeLevel == 1 }">司局级</c:when>
                                        <c:when test="${train.feeLevel == 2 }">省部级</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th>是否精品培训班：</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${train.isNoted == 1 }">是</c:when>
                                        <c:otherwise>否</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th>培训地点：</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${train.ifBJ == 1 }">本地</c:when>
                                        <c:when test="${train.ifBJ == 0 }">外埠</c:when>
                                    </c:choose>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    ${train.location}
                                </td>
                            </tr>
                            <tr>
                                <th>适用岗位：</th>
                                <td>
                                    <c:if test="${train.stationId != null }" >
                                        <c:forEach var="tdv" items="${fn:split(train.stationId , ';')}">
                                            <c:if  test='${ tdv == 1}'>科研类</c:if>
                                            <c:if  test='${ tdv == 2}'>支撑类</c:if>
                                            <c:if  test='${ tdv == 3}'>管理类</c:if>
                                            <c:if  test='${ tdv == 4}'>成果转移转化类</c:if>
                                            <c:if  test='${ tdv == 5}'>领导类</c:if>
                                        </c:forEach>
                                    </c:if>
                                </td>

                                <th>项目类型:</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${train.item_type == 1 }">一类</c:when>
                                        <c:when test="${train.item_type == 2 }">二类</c:when>
                                        <c:when test="${train.item_type == 3 }">三类</c:when>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <th>培训方式：</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${train.trainWay == 0 }">线上培训</c:when>
                                        <c:when test="${train.trainWay == 1 }">线下培训</c:when>
                                        <c:when test="${train.trainWay == 2 }">混合培训</c:when>
                                    </c:choose>
                                </td>
                            </tr>

                            <tr>
                                <th>培训对象：</th>
                                <td>${train.attendants}</td>
                            </tr>
                            <tr>
                                <th>培训目的：</th>
                                <td>${train.trainGoal}</td>
                            </tr>
                            <tr>
                                <th>培训内容：</th>
                                <td>${train.trainingContent}</td>
                            </tr>
                            <tr>
                                <th>培训关键词：</th>
                                <td>${train.keyWordsTag}</td>
                            </tr>
                            <tr>
                                <th>备注：</th>
                                <td>${train.comment}</td>
                            </tr>
                            <tr bgcolor="#f1f1f1">
                                <td colspan="4" align="left" bgcolor="#c9e5f1" style="font-size:13px;">
                                    <strong>主办单位与联系人</strong></td>
                            </tr>

                            <tr>
                                <th>主办单位：</th>
                                <td>${train.orgName}</td>
                            </tr>
                            <tr>
                                <th>联系人：</th>
                                <td>${train.organizerName}</td>
                            </tr>
                            <tr>
                                <th>联系人电话：</th>
                                <td>${train.telephone}</td>
                                <th>联系人Email：</th>
                                <td>${train.organizerEmail}</td>
                            </tr>

                            <tr bgcolor="#f1f1f1">
                                <td colspan="4" bgcolor="#c9e5f1" style="font-size:13px;"><strong>报名与证书</strong></td>
                            </tr>

                            <tr>
                                <th style="font-weight:bold;" align="right"> 是否允许报名</th>
                                <td align="left">
                                    <c:choose>
                                        <c:when test="${train.isEnrolled}">是</c:when>
                                        <c:otherwise>否</c:otherwise>
                                    </c:choose>
                                </td>
                                <c:if test="${train.isEnrolled}" >
                                    <th>报名是否需要审批：</th>
                                    <td>
                                        <c:choose>
                                            <c:when test="${train.isNeedCheck}">是</c:when>
                                            <c:otherwise>否</c:otherwise>
                                        </c:choose>
                                    </td>
                                </c:if>
                            </tr>

                            <c:if test="${train.isEnrolled}" >
                                <tr id="programrow" style="display:none">
                                    <td align="right"> 报名开始时间:<br/><br/></td>
                                    <td align="left">
                                        <fmt:formatDate value="${train.programStartTime }" pattern="yyyy-MM-dd HH:mm"/><br/><br/>
                                    </td>
                                    <td align="right"> 报名结束时间:<br/><br/></td>
                                    <td align="left">
                                        <fmt:formatDate value="${train.programEndTime }" pattern="yyyy-MM-dd HH:mm"/><br/><br/>
                                    </td>
                                </tr>
                            </c:if>
                        </table>

                        <c:if test="${!mode }">
                            <div class="nextstepblock">
                                <input name="按钮" type="button" value="返回编辑" onclick="window.location.href='../onlineTraining/forupdate.do'"/>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<c:if test="${!mode }">
    <div class="bottombody"></div>
</c:if>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/widget/window.js"></script>
<script type="text/javascript" src="/js/widget/classify.js"></script>
<script language="javascript" type="text/javascript" src="/js/CheckFunction.js"></script>
<script type="text/javascript">
    dojo.require("dojo.widget.*");
    if (window.parent) {
        window.parent.iFrameHeight('trainDetailIframe', 20);
    }
</script>
<script type="text/javascript" src="/js/common/home.js"></script>
</body>
</html>