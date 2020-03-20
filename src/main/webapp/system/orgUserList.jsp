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
<html xmlns="http://www.w3.org/1999/xhtml"><!-- InstanceBegin template="/Templates/xx1.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="培训;在线培训;网络培训" name="keywords" />
<title></title>
<link href="/css/skinCss/Normalize.css" rel="stylesheet" type="text/css" />
<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="/css/skinCss/administratorStyle.css" rel="stylesheet" type="text/css" />
<link href="/css/fonts/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
</head>
<body class="admin iframeAdmin">
<div id="userListFrame">
	<div class="mainContent">
		<div class="condition">
			<input type="hidden" name="orgseq" value="${orgseq}" />
			<div class="radio">
			  <label>
			    <input name="status" type="radio"  value="1" aria-label="..."   <c:if test="${status!=0}">checked="checked"</c:if>  />人员管理
			  </label>
			   <label>
			    <input name="status" type="radio"  value="0" aria-label="..."  <c:if test="${status==0}">checked="checked"</c:if>  />已删除人员
			  </label>
			</div>
			<input id="defaultPageSize" type="hidden" value="${defaultPageSize}" />
			<div id="form1" name="form1" class="form-inline" style="text-align: right;">
				<div class="form-group">
					<input class="form-control" type="text" name="operatorName" id="operatorName"  placeholder="用户姓名…"	value="${operatorName}" />
					<button id="searchUser" class="btn btn-primary" type="button">查询</button>
				</div>
			</div>
		</div>
					<div class="cls"></div>
					<form id="form2" name="form2"
						action="eosorgTEmployeeAction.do?method=delete" method="post">
						<input type="hidden" name="operatorName_q" value="${operatorName}" />
						<table  id="userList" class="table table-striped table-bordered batchOperation" rules="cols" width="100%" cellspacing="0" cellpadding="0">
							<tr class="tableTh">
								<c:if test="${isVirOrg==0}">
									<th width="5%">
										<input type="checkbox" id="selectAll" name="selectAll" />
									</th>
								</c:if>
								<th width="8%">编号</th>
								<th width="10%" >姓名</th>
								<th width="15%">部门</th>
								<th width="12%">是否有学时要求</th>
								<th width="10%">密码重置</th>
								<th width="10%">状态</th>
								<c:if test="${status==1}">
									<th width="10%">操作</th>
								</c:if>
							</tr>
						</table>
					</form>
				<!-- content_02 -->
				<form name='form4' id='form4'
					action='userVorganizationAction.do?method=updateDuty' method='post'>
					<input type='hidden' name='oldOrgId' id='oldOrgId' value='' />
					<input type='hidden' name='operatorId' id='operatorId' value='' />
					<input type='hidden' name='orgId' id='orgId' value='' />
				</form>
				<div id="message"></div>
				<div class="condtion">
					<ul class="pagination-admin" style="float:right"></ul>
					<div style="clear:both"></div>
				</div>
				<div class="condition" style="margin-top:20px;">	
			        <button  id="addUser" class="btn btn-info">新增</button>
			        <button  id="bacthOperateUser" class="btn btn-info">批量操作</button>
			        <button  id="importUser" class="btn btn-danger">导入</button>
			        <button  id="exportUser" class="btn btn-primary">导出全部</button>
			        <button  id="downloadUser" class="btn btn-success" style="display:none">下载</button>
			 	 </div>
				</div>
			<div id="showBuffer" class="filling-container">
			</div>
	</div>
</div>
<div class="notShow">
	<input type="hidden" id="currentOrgId" name="currentOrgId" value="${orgId}" />
</div>
<script type="text/javascript" src="/js/jquery-latest.js"></script>
<script type="text/javascript" src="/js/nav/amenu.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/JSCommonTools.js"></script>
<script type="text/javascript" src="/js/UI/remodal.min.js"></script>
<script type="text/javascript" src="/js/aTool.js"></script>
<script type="text/javascript">
$(function(){
	window.top.$.loadScript("/js/systemManage/userList.js",function(){
        window.top.initialUserListTool();
	});

	var canclick=true;

	var currentOrgId=$("#currentOrgId").val();

	var paginationConfig=$.getPaginationConfig();
	var userListPaginationCofig={
        getUrlForPagination:function(){return "/eosorgTEmployee/listUsers.do"},
        actionForClearLoadedData:function(){
            $("#userList .infoRow").remove();
        },
		actionForErrorLoadingData:function(){
            if(window.parent&&typeof window.parent.iframeResize=="function"){
                window.parent.iframeResize();
            }
            $.Ntip({
                'content':"人员信息加载失败"
            });
		},
        actionForSucessLoadingData:function(data){

                var dataList=data.list;
                var dataLength=dataList.length;
                var htmlArray=new Array();
                $("#downloadUser").hide();

                $(".batchOperation th #selectAll").prop("checked",false);

                var htmlArray=new Array();
                for(var i=0;i<dataLength;i++){
                    var currentItem=dataList[i];
                    htmlArray.push("<tr class='infoRow'>");
                    htmlArray.push("<td>"+"<input type='checkbox' value='"+currentItem.operatorID+"' name='operatorId' class='operatorChosen'>"+"</td>");
                    htmlArray.push("<td>"+currentItem.empCode+"</td>");
                    htmlArray.push("<td><a href='javascript:void(0);' class='operatorName'>"+currentItem.operatorName+"</a></td>");
                    htmlArray.push("<td>"+currentItem.orgName+"</td>");
                    if(currentItem.isCtnEduRequire=="1"){
                        htmlArray.push("<td><a href='javascript:void(0)' class='changeCtnRequire toggleStatus'>是</a></td>");
                    }
                    else{
                        htmlArray.push("<td><a href='javascript:void(0)' class='changeCtnRequire toggleStatus'>否</a></td>");
                    }
                    htmlArray.push("<td><a href='javascript:void(0)' class='resetPassword toggleStatus'>密码重置</a></td>");
                    if(currentItem.status=="1"){
                        htmlArray.push("<td><a href='javascript:void(0)' class='changeStatus toggleStatus'>有效</a></td>");
                    }
                    else{
                        htmlArray.push("<td><a href='javascript:void(0)' class='changeStatus toggleStatus'>无效</a></td>");
                    }
                    htmlArray.push("<td><a href='javascript:void(0)' class='operation'>操作</a></td>");
                    htmlArray.push("</tr>");
                }
                $("#userList .infoRow").remove();
                $("#userList").append(htmlArray.join(""));
                if(window.parent&&typeof window.parent.iframeResize=="function"){
                    window.parent.iframeResize();
                }
		}
	};
    $.extend(paginationConfig,userListPaginationCofig);
    paginationConfig.resetSearchInfo();
	paginationConfig.searchInfo.status=$("input[name=status][checked]").val();
    paginationConfig.searchInfo.orgseq=$("input[name=orgseq]").val();
	paginationConfig.actionForLoadingPagination();

	$("input[name=status]").change(function(){
	    var status=$(this).val();
        paginationConfig.resetSearchInfo();
        paginationConfig.searchInfo.orgseq=$("input[name=orgseq]").val();
        paginationConfig.searchInfo.status=status;
		paginationConfig.actionForLoadingPagination();
	});


	$("#userListFrame")
	.on("click","#searchUser",function(){
        paginationConfig.resetSearchInfo();
        paginationConfig.searchInfo.orgseq=$("input[name=orgseq]").val();
		var $form=$(this).parents("#form1");
		var operatorName=$("#operatorName",$form).val();
		paginationConfig.searchInfo.operatorName=operatorName;
		paginationConfig.actionForLoadingPagination();
	}).on("keydown","#operatorName",function(event){
		if(event.keyCode==13){
			$(this).siblings("#searchUser").click();	
			event.stopPropagation();	
		}	
	}).on("click",".operatorName",function(){
		var operatorId=$("input[name=operatorId]" ,$(this).parents("tr")).val();
		window.top.$.showUserDetail({"operatorId":operatorId});
	}).on("click",".operation",function(){
		var operatorId=$("input[name=operatorId]" ,$(this).parents("tr")).val();
		window.location.href="/eosorgTEmployee/forAddOrUpdateUser.do?operatorId="+operatorId+"&orgId="+currentOrgId;
	}).on("click",".toggleStatus",function(){
        var $currentLink=$(this);
        canclick=false;
       	var params={
       	    operatorId:$("input[name=operatorId]" ,$(this).parents("tr")).val()
		}
        if($(this).hasClass("changeCtnRequire")){
			params.isCtnEduRequire=true;
		}
		else if($(this).hasClass("changeStatus")){
       	    params.status=true;
		}
		else if($(this).hasClass("resetPassword")){
       	    params.password=true;
		}
        $.ajax({
            url:"/eosorgTEmployee/toggleProp.do",
            type:"POST",
            dataType:"json",
            data:params,
            success:function(data){
                var message="修改成功";
                if(data.status){
                    message=data.data;
                }
                else{
                    message=data.msg;
                }
                $.Ntip({
                    'content':message,
                    'onClose':function(){
                        if(params.isCtnEduRequire){
                            if($($currentLink).html()=="是"){
                                $currentLink.html("否");
                            }
                            else{
                                $currentLink.html("是");
                            }
						}
						if(params.status){
                            paginationConfig.actionForLoadingPagination();
						}
                    }
                });
                canclick=true;
            },
            error:function(){
                canclick=false;
            }

        })

	})
	/*on("click",".changeCtnRequire",function(){
		var $currentLink=$(this);
		canclick=false;
		var operatorId=$("input[name=operatorId]" ,$(this).parents("tr")).val();
		$.ajax({
			url:"eosorgTEmployeeAction.do?method=modCtnEduRequire",
			type:"POST",
			dataType:"json",
			data:{"userId":operatorId},
			success:function(data){
				var message="修改成功";
				if(data.status){

				}
				else{
					message=data.cause;
				}
				$.Ntip({
					'content':message,
					'onClose':function(){
						if($($currentLink).html()=="是"){
							$currentLink.html("否");
						}
						else{
							$currentLink.html("是");
						}
					}
				});
				canclick=true;
			},
			error:function(){
				canclick=false;
			}

		})

	}).on("click",".changeStatus",function(){
		canclick=false;
		var operatorId=$("input[name=operatorId]" ,$(this).parents("tr")).val();
		$.ajax({
			url:"eosorgTEmployeeAction.do?method=modStatus",
			type:"POST",
			dataType:"json",
			data:{"userId":operatorId},
			success:function(data){
				var message="修改状态成功";
				if(data.status){

				}
				else{
					message=data.cause;
				}
				$.Ntip({
					'content':message,
					'onClose':function(){
						paginationConfig.actionForLoadingPagination();
					}
				});
				canclick=true;
			},
			error:function(){
				canclick=false;
			}

		})

	}).on("click",".resetPassword",function(){
		canclick=false;
		var operatorId=$("input[name=operatorId]" ,$(this).parents("tr")).val();
		$.ajax({
			url:"/eosorgTEmployee/modPassword.do",
			type:"POST",
			dataType:"json",
			data:{"userId":operatorId},
			success:function(data){
				var message="用户密码重置成功";
				if(data.status){

				}
				else{
					message=data.cause;
				}
				$.Ntip({
					'content':message
				});
				canclick=true;
			},
			error:function(){
				canclick=false;
			}

		})
	});
	*/
	.on("click","#bacthOperateUser",function(){
        var operatorArray=new Array();
        $(".operatorChosen:checked").each(function(){
            operatorArray.push($(this).val());
        })

        if(operatorArray.length==0){
            $.Ntip({
                'content':"请先批量选择学员,再点击此按钮"
            });
        }
        else{
            top.batchUserList=operatorArray;
            top.generateBatchOperationWin();
        }
	})
	.on("click","#addUser",function(){
        window.location.href="/eosorgTEmployee/forAddOrUpdateUser.do?orgId="+currentOrgId;
	})
	.on("click","#importUser",function(){
		window.location.href="eosorgTEmployeeImportFileAction.do?method=forupload&orgid="+currentOrgId;
	})
	.on("click","#exportUser",function(){
        if(canclick){
            $("#downloadUser").hide();
            canclick=false;
            $.ajax({
                url:'eosorgTEmployeeExportFileAction.do?method=export',
                type:"POST",
                dataType:"json",
                success:function(data){
                    if(data.status){
                        $("#downloadUser").show().on("click",function(){
                            window.open(data.filePath);
                        })
                    }
                    canclick=true;
                },
                error:function(){
                    canclick=true;
                }
            });
        }
	})
})
</script>
</body>
</html>
