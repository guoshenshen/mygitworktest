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
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- InstanceBegin template="/Templates/admin4.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="培训;在线培训;网络培训" name="keywords" />
<title>部门管理</title>
<link id="styleId" href="/css/skinCss/style.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" type="image/x-icon" href="<%=Constants.FAVICON_ICO_URL%>" media="screen" />
<link id="style_td_Id" href="/css/skinCss/style_td.css" rel="stylesheet" type="text/css" />
<link id="style_gl_Id" href="/css/skinCss/style_gl.css" rel="stylesheet" type="text/css" />
<link href="/css/simpleModel.css" rel="stylesheet" type="text/css" />

<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="/js/common/home.js"></script>
<script type="text/javascript" src="/js/common/tools.js"></script>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script language="javascript" src="/js/jquery.simplemodal.1.4.4.min.js"></script>
<script type="text/javascript" src="/js/dojojs/dojo.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/jquery.uploadifive.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.js"></script>
<style type="text/css">
.usebg {
	float: left;
	width: 190px;
	height: 20px;
}

.usebg .uploadifive-button {
	height: 30px;
	line-height: 20px;
	overflow: hidden;
	position: relative;
	text-align: center;
	width: 190px;
}

.table1 td img {
    height: 70px;
    width: 70px;
}
</style>
</head>

<body>
	<div class="heightdiv"></div>
	<div id="imainbody" class="valuediv">
		<div id="contentbody">
			<div id="content_01">
				<div class="headtitle">
					<img hspace="4" align="absMiddle" src="/image/house.png" />组织机构管理
				</div>
			</div>
			<div id="content_02">
				<form action="eosorgTOrganizationAction.do" style="position:relative;margin:0 auto;width:720px;box-shadow: #c7c7c7 0px 0px 2px;padding: 20px;border: 1px solid #e5e5e5;">
					<table class="table1" width="75%" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<th align="left"><bean:message key="orginfo.orgName" />单位名称:</th>
							<td>${orginfo.orgName} <input type='hidden' name='orgName' value='${orginfo.orgName}' /> <input type='hidden' id="orgId" name='orgId' value='${orginfo.orgId}' /> <input type='hidden'
								name='parentOrgId' value='${orginfo.parentOrgId}' /></td>
						</tr>

						<tr>
							<th align="left" width="30%"><bean:message key="orginfo.orgCode" />单位编号:</th>
							<td width="70%">${orginfo.orgCode}</td>
						</tr>

						<tr>
							<th align="left">上级单位名称:</th>
							<td>${orginfo.parentOrgName}</td>
						</tr>

						<tr>
							<th align="left">是否有效:</th>
							<td>
								<c:if test="${orginfo.status ==1}">是</c:if>
								<c:if test="${orginfo.status ==0}">否</c:if>
							</td>
						</tr>

						<tr>
							<th align="left">序号:</th>
							<td>${orginfo.sortId}</td>
						</tr>

						<tr>
							<th align="left"><nobr>是否独立组织培训机构:</nobr>
							</th>
							<td>
								<c:if test="${orginfo.isOrg ==1}">是</c:if>
								<c:if test="${orginfo.isOrg ==0}">否</c:if>
							</td>
						</tr>

						<tr>
							<th align="left">是否虚拟组织机构:</th>
							<td>
								<c:if test="${orginfo.isVirOrg ==1}">是</c:if>
								<c:if test="${orginfo.isVirOrg ==0}">否</c:if>
							</td>
						</tr>
						<c:if test="${orginfo.isVirOrg ==1}">
							<tr>
								<th align="left">挂靠实体研究所:</th>
								<td>${orginfo.attachedOrgName}</td>
							</tr>
						</c:if>

						<c:if test="${orginfo.logo !=null}">
							<tr>
								<th align="left">徽标(Logo):</th>
								<td><label><img src="${orginfo.logo}" />
								</label></td>
							</tr>
						</c:if>

						<c:if test="${not empty message}">
							<tr>
								<td colspan=2 align=center bgcolor='yellow'><font color='red'> ${message} </font></td>
							</tr>
						</c:if>
					</table>
					
					
					<div style="text-align:center;">
						<c:if test="${empty message}">
							
							<div class="form-group " style="text-align:left;padding-top: 10px;padding-left: 50px;" >
								<button class="btn btn-primary" onclick="foradd()" type="button">增加子机构</button>
								<button class="btn btn-primary" onclick="forImport(${orginfo.orgId})" type="button">导入</button>
								<button class="btn btn-primary" onclick="exportExcel()" type="button">导出</button>
								<div id="download" style="display:none"></div>
								<button class="btn btn-primary" onclick="forupdate()" type="button">修改</button>
								
								<c:if test="${not empty orginfo.parentOrgId}">
									<button class="btn btn-danger" onclick="fordelete()" type="button">删除</button>
								</c:if>
								
								<span class="urer_ico btn btn-primary"> 
									<span class="usebg"> 
										<input type="file" name="file_upload" id="file_upload" /> 
										<input type="hidden" name="bannerPicUrl" /> 
									</span>
									<div class="col-sm-6" style="margin-left:-200px">
										<p id="uploadInfo" style="text-align: left;line-height:2em;"></p>
										<div id="fileQueue"></div>
									</div>
								</span>
		
								<button class='btn btn-success' id='downloadButton' type="button" style="display:none;">徽标上传</button>
							</div>

						</c:if>
					</div>
				</form>
			</div>

		</div>
	</div>
	<div id="showBuffer" class="filling-container"></div>

	<script type="text/javascript">
	var supportH5=$.supportHTML5();
	$(function(){

		var canclick=true; 
		
		if(window.parent&&typeof window.parent.iframeResize=="function"){
			window.parent.iframeResize();
		}
			
		var errorTip=function(){
			$.tips("无法设置封面图片，请确保您选择的是图片文件、大小不超过1M","系统提示",function(){
			    $(" #uploadInfo").html("仅支持图片上传"); 
			    $(" #fileQueue").empty();   	
			}); 	
		};
		
		var dialogClose=function(queueData){
			if(queueData.filesErrored>0){
            	uploadAction.errorTip();
            }   
		};
		
		var onUploadStart=function(file){
			$("#uploadInfo").html("");        
		};	
		var uploadSuccess = function(file, data, response){
			var resultData=$.parseJSON(data);
			var result=resultData.result;
			var uploadResultInfo="";
			var orgIdVal = $("#orgId").val();
			if(result=="true"){
				uploadResultInfo="";
				$.ajax({
					data:{"image":resultData.savePath,"orgId":orgIdVal},
					url:"eosorgTOrganizationAction.do?method=uploadImage",
					method:"post",
					dataType:"json",
					success:function(data){
						if(data.status){
							uploadResultInfo="您的图片更新成功";
							$("#fileQueue").hide();
							window.location.reload();
						}
						else{
							uploadResultInfo="图片更新失败";
						}
						$.Ntip({
									'content':uploadResultInfo
								})
					},
					error:function(){
						uploadResultInfo="图片更新失败";
						$.Ntip({
							'content':uploadResultInfo
						})
						 
					}
					
				})
	        }
	        else{
	        	$.tips("无法设置封面图片，请确保您选择的是图片文件、大小不超过1M","系统提示",function(){
				    $(" #fileQueue").empty();
				}); 
		        <%-- 
	        	if($.trim(data.cause)!=""){
	        		uploadResultInfo=data.cause;
	        	}
		        --%>
	        }
		};

		 /* 
	    'fileType' : 'image/*',
	    'fileType' : 'image/jpeg',
	     */
		
		if(supportH5){

			$('#file_upload').uploadifive({
		        'auto' : true,
		        'uploadScript' : 'filesToolAction.do?method=uploadPic&uploadType=10&picWidth=70&picHeight=70',
		        'buttonText' : '徽标上传',
		        'queueID' : 'fileQueue',
		        'fileType' : 'image/jpeg',
		        'multi' : false,
		        'fileSizeLimit' : "1MB",
		        'onError':errorTip,
		        'onUploadComplete' : uploadSuccess
		    });
			
		}else{
			//绑定动作到相片上传操作
			$("#file_upload").uploadify({
				 'overrideEvents' : ['onDialogClose'], 
			      'method'         :'get',
				  'swf'            : '/js/uploadify.swf',
			      'fileTypeExts'   :'*.jpg;*.png;*.bmp;*.jpeg',
			      'uploader'       : 'filesToolAction.do?method=uploadPic&uploadType=10&picWidth=70&picHeight=70',
			      /* 'cancelImg'      : 'image/cancel.png', */
			      'fileSizeLimit'  : "1MB", 
			      'auto'           : true,
			      'multi'          : false,
			      "formData":{'picWidth':70,'picHeight':70},
			      'buttonText'     : '徽标上传',
			      /* 'onUploadStart'  :onUploadStart,  */
			      'queueID'        : 'imgQueue',
			      'successTimeout' :6000,
			      /* 'onDialogClose' : '', */
			      'onUploadSuccess':uploadSuccess 
			});
		}

		$(".uploadifive-button").css("width","190px");
		$(".uploadifive-button").css("line-height","15px");
	})
</script>
</body>

<script type="text/javascript">
var $model=null;
function foradd(){
	document.forms[0].action = "/eosorgTOrganization/foradd.do";
		document.forms[0].submit();	
}

function forImport(orgid){
    document.forms[0].action="eosorgTOrganizationAction.do?method=importOrgExcel&orgid="+orgid;
    document.forms[0].submit();
}

function forupdate(){
	document.forms[0].action = "/eosorgTOrganization/forupdate.do";
		document.forms[0].submit();	
}

function fordelete(){
	document.forms[0].action = "eosorgTOrganizationAction.do?method=delete"; 
		document.forms[0].submit();	
}

function closeSimpleModel(){
	if($model!=null){
		$model.close();
	}
}

function exportExcel(){
	$model=$("#showBuffer").modal({	
			"opacity":60,
			"overlayClose":false,
			"containerId":"window-frame-base-01",
			"close":false
	});
  dojo.io.queueBind({
		   url: 'eosorgTOrganizationFileAction.do?method=exportExcel',
		   method:'POST',
		   content:{"orgId":dojo.byId("orgId").value},
		   load: function(type,data,evt){
		   		dojo.byId("download").style.display="block";	
		   		if(data!=""){		   		
		   			dojo.byId("download").innerHTML = "<a href=\""+data+"\" class=\"btn-orange-l\"><span class=\"btn-orange-r\">下载</span></a>";
		   		}
		   		
		   		setTimeout("closeSimpleModel()",3000);	
		   	}
		});	

}


</script>

</html>

