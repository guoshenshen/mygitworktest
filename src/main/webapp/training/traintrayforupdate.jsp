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
            <div id="contentbody" class="trainEditor">
                <div id="content_02">
                    <form id="form2" method="post" action="" class="gl_06" style="border:none;width:800px">
                        <c:if test="${currentTrainTipList != null && currentTrainTipList != '' }" >
                            <table class="table0" width="100%" cellspacing="0" cellpadding="0">
                                <tr class="tableTh" bgcolor="#f1f1f1">
                                    <td colspan="4" align="left" bgcolor="#c9e5f1" style="font-size:13px;">
                                        <strong>${trainTipName}</strong>提醒您:<font color=red>[&nbsp;${train.trainName}&nbsp;]今天需关注如下内容</font>
                                    </td>
                                </tr>
                                <c:forEach var="mtTipItem" items="currentTrainTipList">
                                    <tr>
                                        <td style="border:0px">
                                            <c:choose>
                                                <c:when test="${mtTipItem.status == 1 }">
                                                    <input type="checkbox" id="itemId_${mtTipItem.id}" name="selectAll" onclick="_setSelected(this,${mtTipItem.id})" checked/>
                                                </c:when>
                                                <c:when test="${mtTipItem.status == 0 }">
                                                    <input type="checkbox" id="itemId_${mtTipItem.id}" name="selectAll" onclick="_setSelected(this,${mtTipItem.id})"/>
                                                </c:when>
                                            </c:choose>
                                            <br/>
                                        </td>
                                        <td align="left" colspan='3' style="border:0px"><bean:write name="mtTipItem" property="itemName"/></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </form>

                    <form id="trainBasicInfoForm" class="form-horizontal" method="post" name="onlinetrainingbasicinfo">
                        <input type="hidden" name="trainId" id="trainId" value="${train.ID}"/>
                        <input type="hidden" name="filterFlag" value="false"/>
                        <input name="programNo" type='hidden' value="${train.programNo}"/>
                        <input name="topbandId" type='hidden' value="${train.topbandId}"/>
                        <input name="iconId" type='hidden' value="${train.iconId}"/>
                        <input name="isStationTrain" type="hidden" value="0"/>
                        <input name="executePlan" type="hidden" value=""/>
                        <input type='hidden' name="operatorId" id="operatorId" value="${train.operatorId }"/>
                        <input type="hidden" name="isReported" id="isReported" value="0"/>
                        <input type="hidden" name="approveStatus" id="approveStatus" value="${train.approveStatus }"/>
                        <!-- 审批状态 -->
                        <c:if test="${plan != null && plan != '' }" >
                            <input name="planType" type='hidden' value="${plan.planType}" id="planType"/>
                        </c:if>

                        <div class="form-group">
                            <p class="bg-primary" style="width: 780px;margin-left: 12%"><strong>基本信息</strong></p>
                        </div>

                        <div class="form-group">
                            <label for="trainName" class="col-sm-3 control-label">培训名称：</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="trainName" name="trainName" value="${train.trainName}">
                            </div>
                            <div class="col-sm-2"><font color=red>&nbsp;*</font></div>
                        </div>

                        <div class="form-group">
                            <label for="year" class="col-sm-3 control-label">培训年度：</label>
                            <div class="col-sm-2">${train.year}
                                <input type="hidden" class="form-control" id="year" name="year" value="${train.year}">
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                            <label for="trainTypeId" class="col-sm-2 control-label">培训类别：</label>
                            <div class="col-sm-2">
                                <select name="trainTypeId" id="trainTypeId" class="form-control">
                                    <option value="-1"> 请选择</option>
                                    <c:forEach var="trainType" items="${trainTypeList}">
                                        <option value="${trainType.code}" <c:if test="${train.trainTypeID == trainType.code }" >selected</c:if>> ${trainType.name} </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group">
                            <label for="startTime" class="col-sm-3 control-label">培训开始时间：</label>
                            <div class="col-sm-2">
                                <input name="startTime" id="startTime" class="Wdate form-control" type="text" onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})"
                                        value="<fmt:formatDate value='${train.startTime}' pattern='yyyy-MM-dd HH:mm'/>" style="height:35px;">
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                            <label for="endTime" class="col-sm-2 control-label">培训结束时间：</label>
                            <div class="col-sm-2">
                                <input name="endTime" id="endTime" class="Wdate form-control" type="text" onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})"
                                       value="<fmt:formatDate value='${train.endTime}' pattern='yyyy-MM-dd HH:mm'/>" style="height:35px;">
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group">
                            <label for="classHour" class="col-sm-3 control-label">学时：</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" id="classHour" name="classHour" value="${train.classHour}">
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                            <label for="days" class="col-sm-2 control-label">天数：</label>
                            <div class="col-sm-2">
                                <input name="days" id="days" class="form-control" type="text" value="${train.days}"/>
                            </div>
                            <div class="col-sm-1">天<font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group">
                            <label for="attendantCount" class="col-sm-3 control-label">学员计划参加人数：</label>
                            <div class="col-sm-2">
                                <input name="attendantCount" id="attendantCount" class="form-control" type="text" value="${train.attendantCount}"/>
                            </div>
                            <div class="col-sm-1">人<font color=red>&nbsp;*</font></div>
                            <label for="researchPostNum" class="col-sm-2 control-label">学员实际人数：</label>
                            <div class="col-sm-2">
                                <input name="researchPostNum" id="researchPostNum" class="form-control" type="text" value="${train.researchPostNum}"/>
                            </div>
                            <div class="col-sm-1">人</div>
                        </div>
                        <%-- <div class="form-group">
                            <label for="managePostNum" class="col-sm-3 control-label">管理岗人数：</label>
                            <div class="col-sm-2">
                              <input name="managePostNum" id="managePostNum" class="form-control" type="text" value="${train.managePostNum}"/>
                            </div>
                            <div class="col-sm-1">人</div>
                            <label for="supportPostNum" class="col-sm-2 control-label">支撑岗人数：</label>
                            <div class="col-sm-2">
                               <input name="supportPostNum" id="supportPostNum" class="form-control" type="text" value="${train.supportPostNum}" />
                            </div>
                            <div class="col-sm-1">人</div>
                        </div> --%>
                        <div class="form-group">
                            <label for="workerNum" class="col-sm-3 control-label">工作人员计划人数：</label>
                            <div class="col-sm-2">
                                <input name="workerNum" id="workerNum" class="form-control" type="text" value="${train.workerNum}"/>
                            </div>
                            <div class="col-sm-1">人</div>
                            <label for="outSideNum" class="col-sm-2 control-label">工作人员实际人数：</label>
                            <div class="col-sm-2">
                                <input name="outSideNum" id="outSideNum" class="form-control" type="text" value="${train.outSideNum}"/>
                            </div>
                            <div class="col-sm-1">人</div>
                        </div>
                        <div class="form-group">
                            <label for="feeLevel" class="col-sm-3 control-label">培训对象级别：</label>
                            <div class="col-sm-3">
                                <input type="radio" name="feeLevel" value="0" checked/>处级及以下
                                <input type="radio" name="feeLevel" value="1" />司局级
                                <input type="radio" name="feeLevel" value="2" />其他 &nbsp;&nbsp;&nbsp;<font color=red>&nbsp;*</font>
                            </div>
                            <div class="col-sm-2"><font color=red></font></div>
                        </div>
                        <div class="form-group">
                            <label for="feeChannel" class="col-sm-3 control-label">列支渠道：</label>
                            <div class="col-sm-2">
                                <input name="feeChannel" id="feeChannel" class="form-control" type="text" value="${train.feeChannel}"/>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                            <label for="fee" class="col-sm-2 control-label">经费预算(万元)：</label>
                            <div class="col-sm-2">
                                <input name="fee" id="fee" class="form-control" type="text" value="${train.fee}"/>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group">
                            <label for="expenseFee" class="col-sm-3 control-label">报销费用(万元)：</label>
                            <div class="col-sm-2">
                                <input name="expenseFee" id="expenseFee" class="form-control" type="text" value="${train.expenseFee}"/>
                            </div>
                            <div class="col-sm-1"></div>
                            <label for="fee" class="col-sm-2 control-label">辅助资料：</label>
                            <div class="col-sm-2">
                                <input name="auxiliary" id="auxiliary" class="form-control" type="text" value="${train.auxiliary}"/>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>
                        <div class="form-group">
                            <label for="isNoted" class="col-sm-3 control-label">是否精品培训班：</label>
                            <div class="col-sm-2">
                                <c:if test="${train.isNoted == 1 }" >
                                    <input type="radio" name="isNoted" value="1" checked/>是&nbsp;
                                    <input name="isNoted" type="radio" value="0"/>否
                                </c:if>
                                <c:if test="${train.isNoted == 0 }" >
                                    <input type="radio" name="isNoted" value="1"/>是
                                    <input name="isNoted" type="radio" value="0" checked/>否
                                </c:if>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                            <label for="upTenantId" class="col-sm-2 control-label">是否分院展示：</label>
                            <div class="col-sm-2">
                                <input type="radio" name="upTenantId" value="4" <c:if test="${train.upTenantId == 4 }" >checked</c:if> />是&nbsp;
                                <input name="upTenantId" type="radio" value="0" <c:if test="${train.upTenantId == 0 }" >checked</c:if> />否
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group">
                            <label for="ifBJ" class="col-sm-3 control-label">培训地点：</label>
                            <div class="col-sm-2">
                                <input type="radio" name="ifBJ" value="1" <c:if test="${train.ifBJ == 1 }" >checked</c:if>></input>
                                本地
                                <input type="radio" name="ifBJ" value="0" <c:if test="${train.ifBJ == 0 }" >checked</c:if>></input>
                                外埠
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                            <div class="col-sm-4"><input name="location" type="text" value="${train.location}" placeholder="具体地点" class="form-control"/></div>
                        </div>
                        <div class="form-group">
                            <label for="stationId" class="col-sm-3 control-label">适用岗位：</label>
                            <div class="col-sm-3">
                                <input type="checkbox" name="stationId" value="1" <c:if test="${train.stationId != null && fn:contains(train.stationId,'1')}" >checked</c:if> />科研类&nbsp;
                                <input type="checkbox" name="stationId" value="2" <c:if test="${train.stationId != null && fn:contains(train.stationId,'2')}" >checked</c:if> />撑类&nbsp;
                                <input type="checkbox" name="stationId" value="3" <c:if test="${train.stationId != null && fn:contains(train.stationId,'3')}" >checked</c:if> />管理类&nbsp;
                                &nbsp;&nbsp;&nbsp;<font color=red>&nbsp;&nbsp;&nbsp;&nbsp;*</font>
                            </div>
                            <label for="itemType" class="col-sm-2 control-label">项目类型：${train.itemType}</label>
                            <div class="col-sm-2">
                                <select name="itemType" id="itemType" class="form-control">
                                    <option value="-1"> 请选择</option>
                                    <option value="1" <c:if test="${train.item_type == 1 }" >selected</c:if>> 一类</option>
                                    <option value="2" <c:if test="${train.item_type == 2 }" >selected</c:if>> 二类</option>
                                    <option value="3" <c:if test="${train.item_type == 3 }" >selected</c:if>> 三类</option>
                                </select>
                            </div>
                            <div class="col-sm-1"></div>
                        </div>

                        <div class="form-group">
                            <label for="trainWay" class="col-sm-3 control-label">培训方式：</label>
                            <div class="col-sm-6">
                                <input type="radio" name="trainWay" value="1" <c:if test="${train.trainWay == 1 }" >checked</c:if>></input>线下培训
                                <input type="radio" name="trainWay" value="0" <c:if test="${train.trainWay == 0 }" >checked</c:if>></input>线上培训
                                <input type="radio" name="trainWay" value="2" <c:if test="${train.trainWay == 2 }" >checked</c:if>></input>混合培训（线下+线上）
                            </div>
                        </div>

                        <div class="form-group upReportOrgInfoContainer" style="display:none">
                            <label class="col-sm-3 control-label">上报机构：</label>
                            <div class="col-sm-7 upReportOrgInfo">
                            </div>
                        </div>

                        <%--<div class="form-group">
                            <label for="isAllowedUnderAdminReg" class="col-sm-3 control-label">是否允许下级管理员报名：</label>
                            <div class="col-sm-2">
                                <c:if test="${train.isAllowedUnderAdminReg == 1 }" >
                                    <input type="radio" name="isAllowedUnderAdminReg" value="1" checked/>是&nbsp;
                                    <input name="isAllowedUnderAdminReg" type="radio" value="0"/>否&nbsp;
                                </c:if>
                                <c:if test="${train.isAllowedUnderAdminReg == 0 }" >
                                    <input type="radio" name="isAllowedUnderAdminReg" value="1"/>是
                                    <input name="isAllowedUnderAdminReg" type="radio" value="0" checked/>否&nbsp;
                                </c:if>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>--%>

                        <div class="form-group">
                            <label for="attendants" class="col-sm-3 control-label">培训对象：</label>
                            <div class="col-sm-7">
                                <textarea class="form-control" name="attendants" id="attendants" rows="3" cols="40">${train.attendants}</textarea>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group">
                            <label for="trainGoal" class="col-sm-3 control-label">培训目的：</label>
                            <div class="col-sm-7">
                                <textarea class="form-control" name="trainGoal" id="trainGoal" rows="3" cols="40">${train.trainGoal}</textarea>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group">
                            <label for="trainingContent" class="col-sm-3 control-label">培训内容：</label>
                            <div class="col-sm-7">
                                <textarea class="form-control" name="trainingContent" id="trainingContent" rows="3" cols="40" onKeyDown="gbcount(document.getElementById('trainingContent'));"
                                          onKeyUp="gbcount(document.getElementById('trainingContent'));">${train.trainingContent}</textarea>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group">
                            <label for="keyWordsTag" class="col-sm-3 control-label">培训关键词：</label>
                            <div class="col-sm-7">
                                <input type='text' class="form-control" name="keyWordsTag" id="keyWordsTag" value="${train.keyWordsTag }" size='50' maxLength='50'/>
                            </div>
                            <div class="col-sm-2">(关键词间用 ; 隔开)</div>
                        </div>
                        <div class="form-group">
                            <label for="comment" class="col-sm-3 control-label">备注：</label>
                            <div class="col-sm-7">
                                <textarea class="areaInput" name="comment" id="comment" rows="3" cols="485" onKeyDown="gbcount(document.getElementById('comment'));"
                                          onKeyUp="gbcount(document.getElementById('comment'));" style="width:485px;">${train.comment}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <p class="bg-primary" style="width: 780px;margin-left: 12%"><strong>主办单位与联系人</strong></p>
                        </div>
                        <div class="form-group">
                            <label for="orgsname" class="col-sm-3 control-label">主办单位:</label>
                            <div class="col-sm-7">
                                <input name="orgsname" id="_orgsname" class="form-control" type="text" value="${train.orgName}" size="40" maxLength="64"/>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group">
                            <label for="orgsname" class="col-sm-3 control-label">联系人:</label>
                            <div class="col-sm-5">
                                <input name="organizerName" class="form-control" type="text" id="organizerName" value="${train.organizerName}" size="40" maxLength="64"/>
                            </div>
                            <div class="col-sm-2">
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group">
                            <label for="telephone" class="col-sm-3 control-label">联系人电话：</label>
                            <div class="col-sm-2">
                                <input id="telephone" name="telephone" class="form-control" type="text" value="${train.telephone}" size="20" maxLength="20"/>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                            <label for="organizerEmail" class="col-sm-2 control-label">联系人Email：</label>
                            <div class="col-sm-2">
                                <input name="organizerEmail" id="organizerEmail" class="form-control" type="text" value="${train.organizerEmail}"/>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group">
                            <p class="bg-primary" style="width: 780px;margin-left: 12%"><strong>报名与证书</strong></p>
                        </div>
                        <div class="form-group">
                            <label for="openScope" class="col-sm-3 control-label">培训公开范围：</label>
                            <div class="col-sm-7">
                                <c:if test="${openScopeList != null }" >
                                    <c:forEach var="openScope" items="${openScopeList}" >
                                        <input type="radio" name="openScope" value="${openScope.code }" onclick="register(${openScope.code})" <c:if test="${openScope.code == train.openScope}">checked</c:if> /> ${openScope.name}
                                    </c:forEach>
                                </c:if>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                        </div>
                        <div class="form-group" id="register" <c:if test="${train.openScope == 2201 }" >style="display:none"</c:if>>
                            <label for="telephone" class="col-sm-3 control-label">是否允许报名：</label>
                            <div class="col-sm-2">
                                <input name="isEnrolled" id="isEnrolledYes" <c:if test="${train.isStationTrain}">disabled</c:if> type="radio" value="1" onclick="isEnroll()" <c:if test="${train.isEnrolled}">checked</c:if> />是
                                <input name="isEnrolled" id="isEnrolledNo" type="radio" onclick="noEnroll()"  value="0" <c:if test="${!train.isEnrolled}">checked</c:if> />否
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                            <div id="isNeedCheck">
                                <label for="isNeedCheck" class="col-sm-2 control-label">报名是否需要审批：</label>
                                <div class="col-sm-2" class="needForEnroll">
                                    <input name="isNeedCheck" type="radio" value="1" <c:if test="${train.isNeedCheck}">checked</c:if>/>是
                                    <input name="isNeedCheck" type="radio" value="0" <c:if test="${!train.isNeedCheck}">checked</c:if>/>否
                                </div>
                                <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                            </div>
                        </div>
                        <div class="form-group" id="programrow" <c:if test="${train.openScope == 2201}">style="display:none"</c:if>>
                            <label for="programStartTime" class="col-sm-3 control-label">报名开始时间：</label>
                            <div class="col-sm-2">
                                <input name="programStartTime" id="programStartTime" class="Wdate form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false,readOnly:true})"
                                       value="<fmt:formatDate value='${train.programStartTime}' pattern='yyyy-MM-dd HH:mm'/>" style="height:35px"/>
                            </div>
                            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                            <div id="isNeeds">
                                <label for="programEndTime" class="col-sm-2 control-label">报名结束时间：</label>
                                <div class="col-sm-2">
                                    <input name="programEndTime" id="programEndTime" class="Wdate form-control" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false,readOnly:true})"
                                           value="<fmt:formatDate value='${train.programEndTime}' pattern='yyyy-MM-dd HH:mm'/>" style="height:35px"/>
                                </div>
                                <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12" style="text-align:center;">
                                <button class="btn btn-info" id="backButton" type="button">返回</button>
                                <button class="btn btn-info" id="tempStorage" type="button">暂存</button>
                                <button class="btn btn-info" id="submitStorage" type="button">提交</button>
                                <c:if test="${implementable}">
                                    <button class="btn btn-primary" id="submitTrainInfo" type="button">实施</button>
                                </c:if>
                            </div>
                        </div>
                        <!-- 未确定字段 -->
                        <%--<eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='creamProject' pageType='0'>
                            <select name="creamProject" id="creamProject" class="selectStyle" style="width: 148px;" onchange="getKeyWords();">
                                <option value="-1"> 请选择</option>
                                <logic:iterate id="creamProject" name="creamProjectList" type="cn.kepu.elearning.bean.pub.Ddictionary">
                                    <option value="${creamProject.code}"
                                            <logic:equal name="creamProject" property="code" value="${train.creamProject}">selected</logic:equal>> ${creamProject.name} </option>
                                </logic:iterate>
                            </select> <font color=red>&nbsp;*</font>
                        </eln:td>
                        <eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='trainMode' pageType='0'>
                            <select name="trainMode" id="trainMode" class="selectStyle" style="width: 148px;">
                                <option value="-1"> 请选择</option>
                                <logic:iterate id="trainMode" name="trainModeList" type="cn.kepu.elearning.bean.pub.Ddictionary">
                                    <option value="${trainMode.code}" <logic:equal name="trainMode" property="code" value="${train.trainMode}">selected</logic:equal>> ${trainMode.name} </option>
                                </logic:iterate>
                            </select> <font color=red>&nbsp;*</font>
                        </eln:td>--%>
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="bottombody"></div>

<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript">
    function setCookie(c_name, value, expiredays) {
        var exdate = new Date();
        exdate.setDate(exdate.getDate() + expiredays);
        document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())+";path=/";
    }

    var trainName = $("#trainName").val();
    setCookie("trainName", trainName);
    var approveStatus = $("#approveStatus").val();
    setCookie("approveStatus", approveStatus);
</script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>

<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/ueditor-new/ueditor.config.js"></script>
<script type="text/javascript" src="/js/ueditor-new/ueditor.all.js"></script>
<script type="text/javascript">
    var djConfig = {isDebug: false};
</script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css"/>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<link href="/css/pop_window.css" rel="stylesheet" type="text/css"/>
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/widget/window.js"></script>
<script type="text/javascript" src="/js/widget/classify.js"></script>
<script language="javascript" type="text/javascript" src="/js/CheckFunction.js"></script>
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<script type="text/javascript" src="/js/verify.js"></script>
<script type="text/javascript" src="/js/onlineTraining/trainInfoEdit.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript">
    dojo.require("dojo.widget.*");
</script>
<script type="text/javascript">

    dojo.require("dojo.lang.*");
    dojo.require("dojo.widget.*");
    $(function () {
        var option = {
            initialFrameWidth: 480,
            initialFrameHeight: 250,
            initialStyle: 'p{line-height:20px;font-size:12px;font-family:宋体};span{font-size:12px;font-family:宋体;line-height:20px}'
        };
        editor = new UE.ui.Editor(option);
        editor.addInputRule(function (root) {
            root.traversal(function (node) {
                if (node.type == 'element') {
                    switch (node.tagName) {
                        case 'span':
                            node.setStyle({'font-size': '12px', "font-family": "宋体", "line-height": "20px"});
                        case 'p':
                            node.setStyle({'font-size': '12px', "font-family": "宋体", "line-height": "20px"});
                        case 'div':
                            node.setStyle({'font-size': '12px', "font-family": "宋体"});
                        case 'li':
                            node.setStyle({'font-size': '12px', "font-family": "宋体"});
                        case 'table':
                            node.setStyle({'font-size': '12px', "font-family": "宋体"});
                    }
                }
            })
        });
        editor.render('comment');
    });

    function radioIsSelect(radioname) {
        var radiogroup = document.getElementsByName(radioname);
        for (var i = 0; i < radiogroup.length; i++) {
            if (radiogroup[i].checked == true) {
                return true;
            }
        }
        return false;
    }

    function getSubTrainTypeList(trainTypeId) {
        dojo.io.bind({
            url: 'onlineTraining.do?method=getSubTrainType',
            content: {trainTypeId: trainTypeId},
            method: 'POST',
            handler: getSubTrainTypeCallback
        });
    }

    function getSubTrainTypeCallback(type, data, evt) {
        if (type == 'error') {
            alert('读取服务器数据时出错');
        } else {
            document.getElementById("subTrainTypeID").length = 0;   //初始化下拉列表，清空下拉数据
            if (data != null && $.parseJSON(data).length > 0) {
                var nodeArr = $.parseJSON(data);
                for (var i = 0; i < nodeArr.length; i++) {
                    document.getElementById("subTrainTypeID").options[document.getElementById("subTrainTypeID").length] = new Option(nodeArr[i].subTrainTypeName, nodeArr[i].code);   //建立option
                }
            }
        }
    }

    function switchTrainWay() {
        var way = document.getElementById('trainWay').value;
        if (way == 0) {
            document.getElementById('isStationTrainRow').style.display = "block";
        } else {
            document.getElementById('isStationTrainRow').style.display = "none";
            document.getElementById('isStationTrain1').checked = false;
            document.getElementById('isStationTrain0').checked = true;
        }
    }


    function isplan() {
        document.getElementById('isplanrow').style.display = "block";
    }

    function noplan() {
        document.getElementById('isplanrow').style.display = "none";
        document.getElementById('trainPlanID').value = "-1";
        document.getElementById('trainPlanName').value = "";
        var pop_url = "<%=basePath%>orgPlanAction.do?method=foradd&fortrain=1";
        var pop = $("<div id='_pop_win'><h2>创建培训计划"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='500' width='800' scrolling='auto' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {
                width: "800px",
                height: "540px",
                cursor: "default",
                left: ($left - 800) / 2 - 10,
                top: ($top - 560) / 2 - 10
            }
        });
    }

    function register(openScope) {
        if (openScope == 2201) {
            //不公开
            document.getElementById('register').style.display = "none";
            document.getElementById('programrow').style.display = "none";
            noEnroll();

        } else {
            document.getElementById('register').style.display = "";
            var is_allow = document.getElementsByName("isEnrolled")[0].checked;
            if (is_allow) {
                //允许报名
                document.getElementById('isNeedCheck').style.display = "block";
                document.getElementById('programrow').style.display = "block";
                document.getElementById('isNeeds').style.display = "block";
            } else {
                //不允许报名
                noEnroll();
            }

        }
    }

    function selectdepts() {
        var pop_url = "<%=basePath%>pub/checkboxOrgTree.html";
        var pop = $("<div id='_pop_win'><h2>选择机构名称"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='400' scrolling='auto' width='100%' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {
                width: "320px",
                height: "550px",
                cursor: "default",
                left: ($left - 320) / 2 - 10,
                top: ($top - 550) / 2 - 10
            }
        });
    }

    function isEnroll() {

        document.getElementById('isNeedCheck').style.display = "block";
        document.getElementById('programrow').style.display = "";
        document.getElementById('isNeeds').style.display = "block";
        //document.getElementById("programStartTime").value = "${train.programStartTime}";
        document.getElementById("programStartTime").value = "<fmt:formatDate value='${train.programStartTime}' pattern='yyyy-MM-dd HH:mm'/>";
        //document.getElementById("programEndTime").value = "${train.programEndTime}";
        document.getElementById("programEndTime").value = "<fmt:formatDate value='${train.programEndTime}' pattern='yyyy-MM-dd HH:mm'/>";
    }

    function noEnroll(tear) {

        if (tear == 0) {
            document.getElementById('programrow').style.display = "none";
        } else {
            document.getElementById('isNeedCheck').style.display = "none";
            document.getElementById('programrow').style.display = "none";
            document.getElementById('isNeeds').style.display = "none";
        }
    }

    function noEnrollValue() {

        document.getElementById('isNeedCheck').style.display = "none";
        document.getElementById('programrow').style.display = "none";
        document.getElementById('isNeeds').style.display = "none";

        var isCheck = document.getElementsByName('isNeedCheck');
        for (var i = 0; i < isCheck.length; i++) {
            if (isCheck[i].type == "radio") {

                if (isCheck[i].checked) {
                    isCheck[i].checked = false;
                }
            }
        }
        if (dojo.widget.byId("programStartTime").inputNode.value != null){
            dojo.widget.byId("programStartTime").inputNode.value = "";
        }
        document.getElementsByName("programStartTime")[0].value = "";


        if (dojo.widget.byId("programEndTime").inputNode.value != null) {
            dojo.widget.byId("programEndTime").inputNode.value = "";
        }
        document.getElementsByName("programEndTime")[0].value = "";
    }

    function isEnrollValue() {
        document.getElementById('isNeedCheck').style.display = "block";
        document.getElementById('programrow').style.display = "";
        document.getElementById('isNeeds').style.display = "block";
    }


    function trainPlan() {
        var pop_url = "<%=basePath%>orgPlanAction.do?method=queryOrgPlanList";
        var pop = $("<div id='_pop_win'><h2>相关培训计划"
            + "<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe height='500' width='800' scrolling='auto' class='pop_iframe' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {
                width: "800px",
                height: "540px",
                cursor: "default",
                left: ($left - 800) / 2 - 10,
                top: ($top - 560) / 2 - 10
            }
        });
    }

    function judgeRoot(tag) {
        if (tag == 1){
            window.location.href = "<%=basePath%>sponsortraining/sponsorTrainingHome.jsp";
        } else{
            window.location.href = "<%=basePath%>onlineTraining.do?method=searchTrain&research=true&trainWay=0";
        }
    }

    function judgeModify(tag) {
        if (tag == 1) {

        }else{
            window.location.href = "<%=basePath%>onlineTraining.do?method=addTrainForAll";
        }
    }

    function toObtainTrainPlans(id, name) {
        if (confirm("确定要按此计划来实施培训吗?\n如果选'确定'，本页相关字段内容将以此计划内容来填充!")) {
            document.getElementById("trainPlanID").value = id;
            document.getElementById("trainPlanName").value = name;
            //alert(id+","+name+","+openScope);
            document.getElementById("form1").action = "<%=basePath%>onlineTraining.do?method=loadTrainByPlan";
            document.getElementById("form1").submit();
        }
    }

    function checkTrainOrg() {
        if (document.getElementsByName('orgId')[0].value == -1){
            alert("请选择'培训创建部门'！");
        } else{
            document.getElementById('form1').submit();
        }
    }

    function stationtrain() {
        document.getElementById('isEnrolledYes').checked = "false";
        document.getElementById('isEnrolledYes').disabled = "true";
        document.getElementById('isEnrolledNo').checked = "checked";
        noEnroll();
    }

    function stationtrainno() {
        document.getElementById('isEnrolledYes').disabled = false;
    }

    function cleardepts() {
        document.getElementById("orgsname").value = "";
        document.getElementById("orgsid").value = "";
    }

    function clearusers() {
        document.getElementById("organizerName").value = "";
        document.getElementById("organizerId").value = "";

    }

    function toObtainTrainPlans(id, name) {
        if (confirm("确定要按此计划来实施培训吗?\n如果选'确定'，本页相关字段内容将以此计划内容来填充!")) {
            document.getElementById("trainPlanID").value = id;
            document.getElementById("trainPlanName").value = name;
            document.getElementById("form1").action = "<%=basePath%>onlineTraining.do?method=loadTrainByPlan";
            document.getElementById("form1").submit();
        }
    }

</script>
<script type="text/javascript">
    function _setSelected(_this, itemId) {
        var id = _this.id;
        var checkTemp = _this.checked;
        if (checkTemp) {
            $("#" + id).checked = true;
            $.ajax({
                url: 'onlineTraining.do?method=updateTrainTipItemStatus',
                type: "post",
                dataType: "text",
                data: "itemid=" + itemId + "&flag=true",
                success: function () {
                }
            })
        } else {
            $("#" + id).checked = false;
            $.ajax({
                url: 'onlineTraining.do?method=updateTrainTipItemStatus',
                type: "post",
                dataType: "text",
                data: "itemid=" + itemId + "&flag=false",
                success: function () {
                }
            });
        }
        ;
    };

</script>
<script>
    if (document.getElementById("isEnrolledNo").checked) {
        noEnroll();
    } else {
        isEnroll();
    }
</script>
<script type='text/javascript'>
    ////////////////添加联系人///////////////////////////////////
    function chooseOrganizer(id_tag, name_tag) {
        var pop_url = "orgPlanAction.do?method=forAddOrganizer";
        var pop = $("<div id='_pop_win'><h2>选择人员<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
            + "<iframe id='pop_selectUser' height='540px' width='800px' scrolling='auto' class='pop_iframe' style='margin-right:50px;' src='" + pop_url + "'> </iframe></div>"
            + "<div style='clear:both'></div>"
        );
        focusForm = "#form";//定义弹出窗关闭后，光标的定位form，解决弹出窗关闭后不能输入的问题
        pop.find("a.pop_close_btn").click(function () {
            $.unblockUI();
        });
        $.blockUI({
            message: pop,
            css: {
                width: "800px",
                height: "580px",
                cursor: "default",
                left: ($left - 800) / 2 - 10 + 'px',
                top: ($top - 580) / 2 - 10 + 'px'
            }
        });
    }

    function addBaseInfoForOrganizer(operatorId) {
        $.ajax({
            type: "POST",
            data: {"userid": operatorId},
            url: "eosorgTEmployeeAction.do?method=JSONDetail",
            dataType: "JSON",
            success: function (data) {
                addOrganizerBasicInfo(data);
            }
        });
    }


    function addOrganizerBasicInfo(organizerInfo) {
        if (organizerInfo != null) {
            $("#telephone").val(organizerInfo.otel1);
            $("#organizerEmail").val(organizerInfo.oemail);
        }
    }

    function organizerSelected(organizerInfo) {
        $("#organizerName").val(organizerInfo.operatorName);
        $("#operatorId").val(organizerInfo.operatorId);
        $("#orgsname").val(organizerInfo.orgName);
        $("#orgsid").val(organizerInfo.orgId);
    }

    function closeWin() {
        $.unblockUI();
    }

</script>
</body>
</html>