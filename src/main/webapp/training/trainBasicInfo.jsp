<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userRolesInString = "";
	try{
		EosOperator user = (EosOperator)session.getAttribute(Constants.USERINFO_KEY);
	}catch(Exception ex){
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
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="培训;在线培训;网络培训" name="keywords" />
<title></title>
<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
<link href="../css/skinCss/Normalize.css" rel="stylesheet" type="text/css" />
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="../css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
<link href="../css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link id="styleId" href="<%=Constants.SKINDIR%>/css/style.css" rel="stylesheet" type="text/css" />
<link id="style_td_Id"  href="<%=Constants.SKINDIR%>/css/skinCss/style_td.css" rel="stylesheet" type="text/css" />
<link id="style_gl_Id" href="<%=Constants.SKINDIR%>/css/skinCss/style_gl.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css"/>
<link href="../css/pop_window.css" rel="stylesheet" type="text/css" />
</head>
<body class="admin">
<div class="adminFrame">
<div class="topbody"></div>
<div class="mainbody" >
  <div id="trace" class="content"></div>
  <div class="mainContent content trainEditor">
  	<!-- your content is here -->
  	<div>
    <!-- InstanceBeginEditable name="main" -->

    <form id="trainBasicInfoForm" class="form-horizontal" method="post" action="../onlineTraining/add.do" name="onlinetrainingbasicinfo">
    	<input type="hidden" name="filterFlag" value="false"/>
    	<input name="programNo" type='hidden' value="${train.programNo}"/>
        <input name="trainId" type='hidden' value="${train.ID}" />
        <input name="isStationTrain" type="hidden" value="0"/>
        <input type='hidden' name='orgId' id='orgId' value='${train.orgId}' />
        <input type='hidden' name="operatorId" id="operatorId" value="${train.operatorId }" />
        <input type='hidden' name="orgName" id="orgName" value="${train.orgName }" />
        <input name="isIssued" type="hidden" value="0" />
        <input name="isReported" id="isReported" value="0" type="hidden"/>
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
		    <div class="col-sm-2">
		      <input type="text" class="form-control" id="year" name="year" value="${sessionScope.year}">
		    </div>
		    <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
		    <label for="trainTypeId" class="col-sm-2 control-label">培训类别：</label>
			<div class="col-sm-2" >
				<select property="trainTypeId" name="trainTypeId" id="trainTypeId"  class="form-control">
					<option value="-1" >请选择</option>
					<c:if test="${ not empty  trainTypeList}">
						<c:forEach var="trainType" items="${trainTypeList}">
							<option value="${trainType.code}" <c:if  test='${ trainType.code == trainTypeId}'>selected</c:if>>
									${trainType.name}
							</option>
						</c:forEach>
					</c:if>
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
		       <input name="researchPostNum" id="researchPostNum" class="form-control" type="text" value="${train.researchPostNum}" />
		    </div>
		    <div class="col-sm-1">人</div>
    	</div>
    	<div class="form-group">
    		<label for="workerNum" class="col-sm-3 control-label">工作人员计划人数：</label>
    		<div class="col-sm-2">
		       <input name="workerNum" id="workerNum" class="form-control" type="text" value="${train.workerNum}" />
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
			<div class="col-sm-2"></div>
		</div>

		<div class="form-group">
    		<label for="feeChannel" class="col-sm-3 control-label">列支渠道：</label>
    		<div class="col-sm-2">
		      <input name="feeChannel" id="feeChannel" class="form-control" type="text" value="${train.feeChannel}"/>
		    </div>
		    <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
		    <label for="fee" class="col-sm-2 control-label">经费预算(万元)：</label>
    		<div class="col-sm-2">
		       <input name="fee" id="fee" class="form-control" type="text" value="${train.fee}" />
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
		       <input name="auxiliary" id="auxiliary" class="form-control" type="text" value="${train.auxiliary}" />
		    </div>
    	</div>
		<div class="form-group">
			<label for="isNoted" class="col-sm-3 control-label">是否精品培训班：</label>
    		<div class="col-sm-2">
		      <input type="radio" name="isNoted" value="1" checked/>是&nbsp;&nbsp;&nbsp;
           	  <input type="radio" name="isNoted" value="0"  />否&nbsp;<font color=red>&nbsp;*</font>
		    </div>
			<div class="col-sm-1"></div>
			<label for="upTenantId" class="col-sm-2 control-label">是否分院展示：</label>
    		<div class="col-sm-2">
		       <input type="radio" name="upTenantId" value="4" checked/>是&nbsp;&nbsp;&nbsp;
  			   <input type="radio" name="upTenantId" value="0" />否&nbsp;<font color=red>&nbsp;*</font>
		    </div>
    	</div>
		<div class="form-group">
			<label for="ifBJ" class="col-sm-3 control-label">培训地点：</label>
			<div class="col-sm-2">
				<input type="radio" name="ifBJ" value="1" checked></input>本地
				<input type="radio" name="ifBJ" value="0" ></input>外埠
			</div>
            <div class="col-sm-1"></div>
			<div class="col-sm-4">
				<input name="location"  type="text" value="${train.location}" placeholder="具体地点" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label for="stationId" class="col-sm-3 control-label">适用岗位：</label>
			<div class="col-sm-3">
				<input type="checkbox" name="stationId" value="1" />
				科研类
				<input type="checkbox" name="stationId" value="2" />
				支撑类
				<input type="checkbox" name="stationId" value="3" />
				管理类&nbsp;<font color=red>&nbsp;*</font>
			</div>
			<label for="itemType" class="col-sm-2 control-label">项目类型：</label>
			<div class="col-sm-2">
				<select name="itemType" id="itemType" class="form-control">
					<option value="-1" > 请选择 </option>
					<option value="1" > 一类 </option>
					<option value="2" > 二类 </option>
					<option value="3" > 三类 </option>
				</select>
			</div>
			<div class="col-sm-1"></div>
		</div>
		<div class="form-group">
			<label for="trainWay" class="col-sm-3 control-label">培训方式：</label>
			<div class="col-sm-6">
				<input type="radio" name="trainWay" value="0" ></input>
				线上培训
				<input type="radio" name="trainWay" value="1" checked></input>
				线下培训
				<input type="radio" name="trainWay" value="2" ></input>
				混合培训（线下+线上）
			</div>
		</div>
		<div class="form-group upReportOrgInfoContainer" style="display:none">
			<label class="col-sm-3 control-label">上报机构：</label>
			<div class="col-sm-7 upReportOrgInfo">
			</div>
		</div>

        <%--
        <div class="form-group">
            <label for="isAllowedUnderAdminReg" class="col-sm-3 control-label">是否允许下级管理员报名：</label>
            <div class="col-sm-2">
                <input type="radio" name="isAllowedUnderAdminReg" value="1" />是&nbsp;&nbsp;&nbsp;
                <input name="isAllowedUnderAdminReg" type="radio" value="0"  checked/>否&nbsp;<font color=red>&nbsp;*</font>
            </div>
        </div>
        --%>

		<div class="form-group">
			<label for="attendants" class="col-sm-3 control-label">培训对象：</label>
			<div class="col-sm-7">
				<textarea class="form-control" name="attendants" id="attendants" rows="3" cols="40"
						  onKeyDown="gbcount(document.getElementById('attendants'));" onKeyUp="gbcount(document.getElementById('attendants'));" >${train.attendants}</textarea>
			</div>
			<div class="col-sm-1"><font color=red>&nbsp;*</font></div>
		</div>

		<div class="form-group">
			<label for="trainGoal" class="col-sm-3 control-label">培训目的：</label>
			<div class="col-sm-7">
				<textarea class="form-control" name="trainGoal" id="trainGoal" rows="3" cols="40"
				onKeyDown="gbcount(document.getElementById('trainGoal'));" onKeyUp="gbcount(document.getElementById('trainGoal'));" >${train.trainGoal}</textarea>
			</div>
			<div class="col-sm-1"><font color=red>&nbsp;*</font></div>
		</div>

        <div class="form-group">
            <label for="trainGoal" class="col-sm-3 control-label">培训内容：</label>
            <div class="col-sm-7">
                <textarea class="form-control" name="trainingContent" id="trainingContent" rows="3" cols="40"  onKeyDown="gbcount(document.getElementById('trainingContent'));" onKeyUp="gbcount(document.getElementById('trainingContent'));" >${train.trainingContent}</textarea>
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
				<textarea class="areaInput" name="comment"  id="comment" rows="3" cols="485"  onKeyDown="gbcount(document.getElementById('comment'));" onKeyUp="gbcount(document.getElementById('comment'));" style="width:485px;">${train.comment}</textarea>
            </div>
        </div>
        <div class="form-group">
            <p class="bg-primary" style="width: 780px;margin-left: 12%"><strong>主办单位与联系人</strong></p>
        </div>

        <div class="form-group">
            <label for="orgName" class="col-sm-3 control-label">主办单位:</label>
            <div class="col-sm-7">
                <input name="orgsname" id="_orgsname" class="form-control" type="text" value="${train.sponsorName}" size="40" maxLength="64" />
            </div>
            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
        </div>

        <div class="form-group">
            <label for="organizerName" class="col-sm-3 control-label">联系人:</label>
            <div class="col-sm-5">
                <input name="organizerName" class="form-control" type="text" id="organizerName" value="${train.organizerName}" size="40" maxLength="64"/>
            </div>
            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
            <div class="col-sm-2">
                <a href="javascript:chooseOrganizer()" class="btn btn-primary" style="background-color: #3071a9;color:#fff;display:none">选择</a>
            </div>
        </div>

        <div class="form-group">
            <label for="telephone" class="col-sm-3 control-label">联系人电话：</label>
            <div class="col-sm-2">
                <input id="telephone" name="telephone" class="form-control" type="text" value="${train.telephone}" size="20" maxLength="20"/>
            </div>
            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
            <label for="organizerEmail" class="col-sm-2 control-label">联系人Email：</label>
            <div class="col-sm-2">
                <input name="organizerEmail"  id="organizerEmail" class="form-control" type="text" value="${train.organizerEmail}"/>
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
						<input type="radio" name="openScope" value="${openScope.code }" onclick="register(${openScope.code})"
							<c:if test="${openScope.code == train.openScope}">
                                checked
							</c:if>
						/> ${openScope.name}
					</c:forEach>
				</c:if>
            </div>
            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
        </div>
        <div class="form-group" id="register" style="display:none">
            <label for="isEnrolled" class="col-sm-3 control-label">是否允许报名：</label>
			<div class="col-sm-2">
				<input name="isEnrolled" id="isEnrolledYes" <c:if test="${train.isStationTrain}">disabled</c:if> type="radio" value="1" onclick="isEnroll()" <c:if test="${train.isEnrolled}">checked</c:if> />是
				<input name="isEnrolled" id="isEnrolledNo" type="radio" onclick="noEnroll()"  value="0" <c:if test="${!train.isEnrolled}">checked</c:if> />否
			</div>
            <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
            <div id="isNeedCheck" style="display: none">
                <label for="isNeedCheck" class="col-sm-2 control-label">报名是否需要审批：</label>
                <div class="col-sm-2">
                    <%--<input name="isNeedCheck" type="radio" value="1" <c:if test="${1 == train.openScope}">selected</c:if>/>是
					<input name="isNeedCheck" type="radio" value="0" <c:if test="${0 == train.openScope}">selected</c:if>/>否--%>
                    <input name="isNeedCheck" type="radio" value="1" <c:if test="${train.isNeedCheck}">selected</c:if>/>是
                    <input name="isNeedCheck" type="radio" value="0" <c:if test="${!train.isNeedCheck}">selected</c:if>/>否
                </div>
                <div class="col-sm-1"><font color=red>&nbsp;*</font></div>
            </div>
        </div>
        <div class="form-group" id="programrow" style="display:none">
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
				<c:if test="${trainID == -1 }">
					<button class="btn btn-info" id="backButton" type="button" onclick="window.location.href='../onlineTraining/searchTrain.do'">返回</button>
				</c:if>
				<c:if test="${trainID != -1 }">
					<button class="btn btn-info" id="backButton" type="button" onclick="window.location.href='../onlineTraining/searchTrain.do?research=false&trainWay=${train.trainWay}'">返回</button>
				</c:if>
                <button class="btn btn-info" id="addtempStorage" type="button">暂存</button>
                <button class="btn btn-info" id="addsubmitStorage" type="button">提交</button>
            </div>
        </div>

        <!-- 未确定字段 -->
        <%--
        <eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='creamProject' pageType='0'>
            <select name="creamProject" id="creamProject" class="selectStyle" style="width: 148px;" onchange="getKeyWords();">
                <option value="-1" > 请选择 </option>
                <logic:iterate id="creamProject" name="creamProjectList"
                               type="cn.kepu.elearning.bean.pub.Ddictionary">
                    <option value="${creamProject.code}" <logic:equal name="creamProject" property="code" value="${train.creamProject}">selected</logic:equal>> ${creamProject.name} </option>
                </logic:iterate>
            </select>	<font color=red>&nbsp;*</font>
        </eln:td>
        <eln:td formfieldAttributesMap='${formfieldAttributesMap}' attribute='trainMode' pageType='0'>
            <select name="trainMode" id="trainMode" class="selectStyle" style="width: 148px;">
                <option value="-1" > 请选择 </option>
                <logic:iterate id="trainMode" name="trainModeList"
                               type="cn.kepu.elearning.bean.pub.Ddictionary">
                    <option value="${trainMode.code}" <logic:equal name="trainMode" property="code" value="${train.trainMode}">selected</logic:equal>> ${trainMode.name} </option>
                </logic:iterate>
            </select>	<font color=red>&nbsp;*</font>
        </eln:td>--%>

    </form>
  </div>
</div>
  <div class="clr"></div>
</div>

  </div>
</div>
</div>
<div class="bottombody"></div>
<script type="text/javascript" src="../js/jquery-latest.js"></script>
<script type="text/javascript" src="../js/nav/amenu.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/JSCommonTools.js"></script>
<script type="text/javascript" src="../js/aTool.js"></script>

<script type="text/javascript" src="../js/common/tools.js"></script>
<script type="text/javascript" src="../js/common/home.js"></script>
<script type="text/javascript" src="../js/jquery-json.js"></script>
<script type="text/javascript" src="../js/jquery.blockUI.js"></script>
<script type="text/javascript" src="../js/dojojs/dojo.js"></script>

<script language="javascript" type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<!-- InstanceBeginEditable name="head" -->
<script type="text/javascript" src="../js/widget/window.js"></script>
<script type="text/javascript" src="../js/widget/classify.js"></script>
<script language="javascript" type="text/javascript" src="../js/CheckFunction.js"></script>
<script type="text/javascript" src="../js/ueditor-new/ueditor.config.js"></script>
<script type="text/javascript" src="../js/ueditor-new/ueditor.all.js"></script>
<link href="../css/pop_window.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/UI/jquery.alerts.js"></script>
<script type="text/javascript" src="../js/verify.js"></script>
<script type="text/javascript" src="../js/onlineTraining/trainInfoEdit.js"></script>
<script type="text/javascript">

//给培训公开范围添加选择
$("input[name='openScope']:first").attr('checked', 'checked');

//给备注添加编辑器
$(function(){
    var option = {
        initialFrameWidth:480,
        initialFrameHeight:250,
        initialStyle: 'p{line-height:20px;font-size:12px;font-family:宋体};span{font-size:12px;font-family:宋体;line-height:20px}'
    };
    editor = new UE.ui.Editor(option);
    editor.addInputRule(function(root){
        root.traversal(function (node) {
            if (node.type == 'element') {
                switch (node.tagName) {
                    case 'span':
                        node.setStyle({'font-size':'12px',"font-family":"宋体","line-height":"20px"});
                    case 'p':
                        node.setStyle({'font-size':'12px',"font-family":"宋体","line-height":"20px"});
                    case 'div':
                        node.setStyle({'font-size':'12px',"font-family":"宋体"});
                    case 'li':
                        node.setStyle({'font-size':'12px',"font-family":"宋体"});
                    case 'table':
                        node.setStyle({'font-size':'12px',"font-family":"宋体"});
                }
            }
        })
    });
    editor.render('comment');
});

//给培训公开范围添加方法
function register(openScope){
    if(openScope==2201){
        //不公开
        document.getElementById('register').style.display="none";
        document.getElementById('programrow').style.display="none";
        noEnroll();
    }else{
        document.getElementById('register').style.display="";
        var is_allow=document.getElementsByName("isEnrolled")[0].checked;
        if(is_allow) {
            //允许报名
            document.getElementById('isNeedCheck').style.display="block";
            document.getElementById('programrow').style.display="block";
            document.getElementById('isNeeds').style.display="block";
        } else{
            //不允许报名
            noEnroll();
        }
    }
}

//给是否允许报名选项 添加点击事件
function isEnroll() {
    document.getElementById('isNeedCheck').style.display="block";
    document.getElementById('programrow').style.display="";
    document.getElementById('isNeeds').style.display="block";
}

//给是否允许报名选项 添加点击事件
function noEnroll(tear) {
    if(tear==0) {
        document.getElementById('programrow').style.display="none";
    } else{
        document.getElementById('isNeedCheck').style.display="none";
        document.getElementById('programrow').style.display="none";
        document.getElementById('isNeeds').style.display="none";
    }
}



</script>
</body>
</html>
