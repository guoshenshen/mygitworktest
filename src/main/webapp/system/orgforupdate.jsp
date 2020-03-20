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
<!-- InstanceBeginEditable name="doctitle" -->
		<title><%=Constants.systemName%></title>
		<!-- InstanceEndEditable -->
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
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>


<script language="javascript" src="/js/jquery.cookie-min.js"></script>
<link href="/css/jquery-UI/jquery.alerts.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/UI/jquery.alerts.js"></script>
<link href="/css/jquery-UI/jquery.alerts-extend.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function insertOrUpdate(){
	//if(document.getElementById("orgCode").value == "")
	//{
		//alert("单位编号不能为空!");
		//jAlert("单位编号不能为空!",'提示');
		//document.getElementById("orgCode").focus();
		//return ;
	//}
	if(document.getElementById("vorgName").value == "")
	{
		//alert("单位名称不能为空!");
		jAlert("单位名称不能为空!",'提示',function(){
			document.getElementById("vorgName").focus();
		});
		//document.getElementById("vorgName").focus();
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
	var length = document.getElementsByName("isOrg").length;
	var isOrg_flag = 0;
	for(var i=0;i<length;i++){
		if(document.getElementsByName("isOrg")[i].checked==true)
		isOrg_flag =1;
	}
	if(isOrg_flag == 0)
	{
		//alert("请选择是否独立组织培训机构!");
		jAlert("请选择是否独立组织培训机构!",'提示');
		return;
	}	
	var length = document.getElementsByName("isVirOrg").length;
	var isVirOrg_flag = 0;
	for(var i=0;i<length;i++){
		if(document.getElementsByName("isVirOrg")[i].checked==true)
		isVirOrg_flag =1;
	}
	if(isVirOrg_flag == 0)
	{
		alert("请选择是否实体组织机构!");
		return;
	}    	
	return document.getElementById("form1").submit();
	
}
function selectdepts() {
		 top.selectdepts();//至orgMain.jsp
}
function cleardepts()
{	
	dojo.byId("orgName").value = "";
	dojo.byId("orgId").value = "";		
}
function selectEntity(){
				document.getElementById('attach').style.display="none";
//	$("#attach").hide();
	 cleardepts();
}
function selectVirtual(){
				document.getElementById('attach').style.display="";
	//$("#attach").show();
}


$(function(){
	$('#orgName1').focus();
})
</script>
  </head>
  
  <body >
<div class="location"></div><div id="imainbody" class="valuediv">
  <div id="contentbody">
 <div id="content_01">
               	<div class="headtitle" style="width:240px;"><img hspace="4" align="absMiddle" src="/image/house.png"/>修改组织机构信息</div>
         </div>
   <div id="content_02">
  <form id='form1' action="/eosorgTOrganization/update.do" method=post>
  
       <table class="table1" width="75%" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <th align="left" width="30%">单位名称:</th>
          <td width="70%">
  			<input type="hidden" name="vorgId" id="vorgId"  value="${orginfo.orgId}" />        
          <input type="text" name="vorgName" id="vorgName" value="${orginfo.orgName}" size="20" /><font color=red>*</font></td>

        </tr>
        
        <tr>
          <th align="left">单位编号:</th>
          <td ><input type="text" name="orgCode" id="orgCode" value="${orginfo.orgCode}" size="20" /><font color=red>*</font></td>
          </tr>
          <tr>
          <th align="left">上级单位名称:</th>
          <td >${orginfo.parentOrgName}</td>
        </tr>
         <tr>
          <th align="left" >是否有效:</th>
          <td >
			<input type="radio" name="status" value="1"    <c:if test="${orginfo.status==1}">checked</c:if>  ></input>
           		是&nbsp;&nbsp;&nbsp;
           	<input type="radio" name="status" value="0"    <c:if test="${orginfo.status==0}">checked</c:if>  ></input>
           		否<font color=red>&nbsp;*</font>
          </td>
          </tr>
         <tr>
          <th align="left" >序号:</th>
          <td >
			<input type="text" name="sortId" value="${orginfo.sortId}"/>
          </td>
        </tr> 
          <tr>
          <th align="left" ><nobr>是否独立组织培训机构:</nobr></th>
          <td >
				<input type="radio" name="isOrg" value="1"   <c:if test="${orginfo.isOrg==1}">checked</c:if>></input>
            		是&nbsp;&nbsp;&nbsp;
            	<input type="radio" name="isOrg" value="0"   <c:if test="${orginfo.isOrg==0}">checked</c:if>></input>
            		否<font color=red>&nbsp;*</font>
          </td>
          </tr>
          <tr>
          <th  align="left" width="25%">是否虚拟组织机构:</th>
          <td width="25%">
				<input type="radio" name="isVirOrg" value="1"   <c:if test="${orginfo.isVirOrg==1}">checked</c:if> onclick="selectVirtual()"  ></input>
            		是&nbsp;&nbsp;&nbsp;
            	<input type="radio" name="isVirOrg" value="0"  <c:if test="${orginfo.isVirOrg==0}">checked</c:if>  onclick="selectEntity()"></input>
            		否<font color=red>&nbsp;*</font>
          </td>
        </tr>  
        <tr id="attach" <c:if test="${orginfo.isVirOrg==0}">style='display:none'</c:if>>
          <th align="left">挂靠实体研究所:</th>
          <td ><input type=text name=attachedOrgName id='orgName' value='${orginfo.attachedOrgName}' size="20" readonly style="float:left;margin-top: 5px;margin-right: 5px;"/>
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
          <td colspan=2 align="center">
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
 </div><!-- content_02 -->  
 </div>
 </div>
  </body>
</html>
