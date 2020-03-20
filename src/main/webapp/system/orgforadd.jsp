<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.elearning.pojo.pub.EosOperator,com.elearning.common.Constants,java.util.regex.Pattern,java.util.regex.Matcher"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/admin4.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="培训;在线培训;网络培训" name="keywords" />
<title>新增组织机构</title>
<link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css" />

<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />  
<link id="style_td_Id"   href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css" />
<link id="style_gl_Id"  href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/jquery-json.js"></script>
<script type="text/javascript" src="/js/nav/menu.js"></script>
<script type="text/javascript" src="/js/nav/roll.js"></script>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<script language="javascript" src="/js/pagination.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

function selectdepts() {
		 top.selectdepts();//至orgMain.jsp
}
function cleardepts()
{	
	document.getElementById("orgName").value = "";
	document.getElementById("orgId").value = "";		
}
function insertOrUpdate(){
	//if(document.getElementById("orgCode").value == "")
	//{
		//alert("单位编号不能为空!");
		//jAlert("单位编号不能为空!",'提示');
		//document.getElementById("orgCode").focus();
	//	return ;
	//}
	
	if(document.getElementById("orgName1").value == "")
	{
		//alert("单位名称不能为空!");
		jAlert("单位名称不能为空!",'提示',function(){
			document.getElementById("orgName1").focus();
		});
		//$(document).getElementById("orgName1").focus();
		return ;
	}	
	if(document.getElementById("orgCode").value == "")
	{
		//alert("单位编号不能为空!");
		jAlert("单位编号不能为空!",'提示',function(){
			document.getElementById("orgCode").focus();
		});
		//document.getElementById("orgCode").focus();
		return ;
	}
	return document.getElementById("form1").submit();
	
}
function selectEntity(){
	$("#attach").hide();
	cleardepts();
}
function selectVirtual(){
	$("#attach").show();
}


$(function(){
	$('#orgName1').focus();
	

	if(window.parent&&typeof window.parent.iframeResize=="function"){
		window.parent.iframeResize();
	}

})
</script>
  </head>
  
  <body >
<div class="location"></div><div id="imainbody" class="valuediv">
  <div id="contentbody">
 <div id="content_01">
               	<div class="headtitle" style="width:240px;">&nbsp;<img hspace="4" align="absMiddle" src="/image/house.png"/>添加子机构信息</div>
         </div>
  <form id='form1' action="/eosorgTOrganization/add.do" method=post>
  
      <table class="table1" width="75%" cellspacing="0" cellpadding="0" align="center" >
        <tr>
          <th align="left" width="30%">单位名称:</th>
          <td width="70%">
			<input type=hidden name=parentOrgId value='${orginfo.parentOrgId}' />
			<input type=hidden name=orgId />
          <input type='text' name='orgName'  id='orgName1' value='${orginfo.orgName}' size="20"/><font color=red>*</font></td>

        </tr>
        
        <tr>
          <th align="left">单位编号:</th>
          <td ><input type=text name=orgCode id=orgCode value='${orginfo.orgCode}' size="20"/><font color=red>*</font></td>
        </tr>
        <tr>
          <th  align="left" >上级单位名称:</th>
          <td >${orginfo.parentOrgName}</td>
        </tr>
        <tr>
          <th align="left" >是否有效:</th>
          <td >
			<input type="radio" name="status" value="1"   <c:if test="${orginfo.status==1}">checked</c:if>     ></input>
           		是&nbsp;&nbsp;&nbsp;
           	<input type="radio" name="status" value="0"   <c:if test="${orginfo.status==0}">checked</c:if>          ></input>
           		否<font color=red>&nbsp;*</font>
          </td>
          </tr>
          <tr>
          <th align="left" >序号:</th>
          <td >
			<input type="text" name="sortId" />
          </td>
        </tr> 
          <tr>
          <th align="left" >是否独立组织培训机构:</th>

              <td >
						<input type="radio" name="isOrg" value="1"   <c:if test="${orginfo.isOrg==1}">checked</c:if>     ></input>
		            		是&nbsp;&nbsp;&nbsp;
		            	<input type="radio" name="isOrg" value="0"   <c:if test="${orginfo.isOrg==0}">checked</c:if>     ></input>
		            		否<font color=red>&nbsp;*</font>
          </td>
          </tr>
          <tr>
          <th  align="left" width="25%">是否虚拟组织机构:</th>
          <td width="25%">
                <c:if test="${orginfo.isVirOrg==0}">
					<input type="radio" name="isVirOrg" value="1"   <c:if test="${orginfo.isVirOrg==1}">checked</c:if>     onclick="selectVirtual()"  ></input>
	            		是&nbsp;&nbsp;&nbsp;
	            	<input type="radio" name="isVirOrg" value="0"   <c:if test="${orginfo.isVirOrg==0}">checked</c:if>          onclick="selectEntity()"  ></input>
	            		否<font color=red>&nbsp;*</font>
                </c:if>
                <c:if test="${orginfo.isVirOrg==1}">
           		是<input type="hidden" name="isVirOrg" value="${orginfo.isVirOrg}"/>
                </c:if>
            	<input type=hidden name=orgTypeId value='${orginfo.orgTypeId}'/>
            	<input type=hidden name=orgscope value='${orginfo.orgscope}'/>
            	<input type=hidden name=orgAddress value=${orginfo.orgAddress} />
            	<input type=hidden name=zipCode value='${orginfo.zipCode}'/>
            	<input type=hidden name=managerid value='${orginfo.managerid}'/>
            	<input type=hidden name=linkman value='${orginfo.linkman}'/>
            	<input type=hidden name=email value='${orginfo.email}'/>
            	<input type=hidden name=linkmantel value=${orginfo.linkmantel} />
            	<input type=hidden name=sortAll value='${orginfo.sortAll}' />
          <input type=hidden name=extParam1 value='${orginfo.extParam1}' />
           <input type=hidden name=extParam2 value='${orginfo.extParam2}' />
          <input type=hidden name=orglevel value='${orginfo.orglevel}' />
          <input type=hidden name=webUrl value='${orginfo.webUrl}'/>
          <input type=hidden name=addresscode value='${orginfo.addresscode}'/>
          <input type=hidden name=positionId value='${orginfo.positionId}'/>
            <input type=hidden name=memo value='${orginfo.memo}'/>	
            <input type=hidden name=recDatachgNum value='${orginfo.recDatachgNum}' />
            <input type=hidden name=sortId value='${orginfo.sortId}'/>
            <input type=hidden name=orgClass value='${orginfo.orgClass}'/>
            <input type=hidden name=archiveNum value=${orginfo.archiveNum}/>
            <input type=hidden name=userGroupId value='${orginfo.userGroupId}'/>
            <input type=hidden name=datachgNum value='${orginfo.datachgNum}'/>
            <input type=hidden name=sendDatachgNum value='${orginfo.sendDatachgNum}'/>
          </td>
        </tr>  
        <tr id="attach" style='display:none'>
            <th align="left" <c:if test="${orginfo.isVirOrg==1}">style='display:none'</c:if> >挂靠实体研究所:</th>
          <td ><input type=text name=attachedOrgName id=orgName value='${orginfo.attachedOrgName}' size="20" readonly style="float:left;margin-top: 5px;margin-right: 5px;"/>
        <input type="hidden" name="attachedOrgId" id="orgId" value='${orginfo.attachedOrgId}'/>  
		<a href="javascript:selectdepts();" class="btn-blue-l"><span class="btn-blue-r">选&nbsp;&nbsp;择</span></a>
	    <a href="javascript:cleardepts();" class="btn-orange-l"><span class="btn-orange-r">清&nbsp;&nbsp;空</span></a>
          </td>
        </tr>
        		<c:if test="${not empty message}">
			      <tr>
				      <td colspan=2 align=center bgcolor='yellow'>
					      <font color='red'>
					      ${message}
					      </font>
				      </td>
			      </tr>
               </c:if>
        <tr>
          <td colspan=4 align="center">
         <div class="btnContainer">
         <div class="clearfix">
         <a href="javascript:history.back();" class="btn-blue-l"><span class="btn-blue-r">返&nbsp;&nbsp;回</span></a>
	     <a href="javascript:insertOrUpdate();" class="btn-orange-l"><span class="btn-orange-r">保&nbsp;&nbsp;存</span></a>        
         </div>
       </div>
          </td>
        </tr>
      </table>
 </form>  
 </div>
 </div>
  </body>
</html>
