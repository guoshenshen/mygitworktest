var assignmentArea={
	assignInfoMap:{},
	currentAssign:null
}

/*************
 * 基本功能函数
 ************/

//清空作业编辑器
function clearAssignEditor(){
	$(".assignEditor input[type=text]").val("");
	$(".assignEditor input[type=hidden]").val("");
	$(".assignEditor textarea").val("");
}


function loadAssignInfo(assignId,actionAfterLoaded){
	var assignInfo=assignmentArea.assignInfoMap[assignId];
	if(assignInfo==null){
		$.ajax({
			type:"POST",
			data:{
				id:assignId,
			},
			url:"../assignment/getAssignInfo.do",
			dataType:"json",
			success:function(data){
				assignInfo = data.data;
				assignmentArea.assignInfoMap[assignId]=assignInfo;
				if(typeof actionAfterLoaded=="function"){
					actionAfterLoaded(assignInfo);
				}
			},
			error:function(data){
				
			}
		})
	}
	else{
		actionAfterLoaded(assignInfo);
	}
	
}

//加载作业内容到作业编辑器
function loadInfoIntoAssignEditor(assignId){
	loadAssignInfo(assignId,function(assignInfo){
		$(".assignEditor input[name='name']").val(assignInfo.name);
		$(".assignEditor textarea.description").val(assignInfo.description);
		$(".assignEditor input[name=id]").val(assignInfo.id);
		$(".assignEditor textarea.comment").val(assignInfo.comment);	
	})
}

function loadStatisticsTip(){	
	var $ToolWidget=$(".userListFrame .tools");
	var allRow=$(".userListFrame table.currentTable tr").length-1;
	var unselectRow=$(".userListFrame table.currentTable tr.notShow").length
	var selectRow=allRow-unselectRow;
	if(allRow<=0){
		$(".search-info-tip",$ToolWidget).hide();
	} else{
		$(".search-info-tip",$ToolWidget).html(selectRow+"/"+allRow).attr("title","当前列表人数"+selectRow).show();	
	}
}

$(function(){
	
	var $assignmentEditor=null;
	var $topicEditor=null;
	var $assignUserEditor=null;
	var $dispatchEmailEditor=null;
	var $answerEditor=null;
	
	var attendedUserList=new Array();

	function loadAttendedUserList(){
		var $resourceId=$("#assignContainer .assignHiddenInfo input[name=resourceId]");
		$.ajax({
			type:"post",
			data:{"trainId":$resourceId.val()},
			url:"../mtMixTrainUserTrainInfo/getAttendedList.do",
			dataType:"json",
			success:function(data){
				if(data.status){
					var userList = data.data.userList;
					var length = userList.length;
					var userListArray = new Array();
					attendedUserList.length=0;
					for(var i=0;i<length;i++){
						var currentUser=userList[i];
						attendedUserList.push(currentUser);
						if(i%2==0){
							userListArray.push("<tr class='even'>");
						} else{
							userListArray.push("<tr class='odd'>");
						}
						userListArray.push("<td><input type='checkbox' name='operatorId' value='"+currentUser.operatorId+"'></td>");
						userListArray.push("<td>"+currentUser.operatorName+"</td>");
						userListArray.push("<td>"+currentUser.orgName+"</td>");
						userListArray.push("<td>"+currentUser.email+"</td>");
						userListArray.push("</tr>");
					}
					$("#attendedUsers tbody").empty().append(userListArray.join(""));
					loadStatisticsTip();
				}
			}
		})
	}

	//加载作业回答情况
	function loadAssignUsers(params){
		var userList=new Array();
		$.ajax({
			data:params,
			url:"../assignment/loadAssignUsers.do",
			dataType:"json",
			type:"post",
			success:function(data){
				var ifAdd = true;
				var passList=data.data.passList;
				var passListLength=passList.length;
				var submitList=data.data.submitList;
				var submitListLength=submitList.length;
				var resetList=data.data.resetList;
				var resetListLength=resetList.length;
				var userListArray=passList.concat(submitList).concat(resetList);
				var attendedLength=attendedUserList.length;
				for(var i=0;i<attendedLength;i++){
					for(var j=0;j<userListArray.length;j++){
						if(attendedUserList[i].operatorId==userListArray[j].operatorId){
							ifAdd = false;
							break;
						}
					}
					if(ifAdd){
						userListArray.push(attendedUserList[i]);
					}
					var ifAdd = true;
				}
				var length=userListArray.length;
				for(var i=0;i<length;i++){
					var currentUser=userListArray[i];
					if(i%2==0){
						userListArray.push("<tr class='even'>");
					} else{
						userListArray.push("<tr class='odd'>");
					}
					userListArray.push("<td><input type='hidden' name='assignId' value='"+params.assignId+"'>");
					userListArray.push("<input type='checkbox' name='operatorId' value='"+currentUser.operatorId+"'></td>");
					userListArray.push("<td>"+currentUser.operatorName+"</td>");
					userListArray.push("<td>"+currentUser.orgName+"</td>");
					userListArray.push("<td>"+currentUser.email+"</td>");
					if(i<submitListLength+passListLength){
						userListArray.push("<td title='点击查看用户作业'><a class='link seeAnswers'>");
						if(i<passListLength){
							userListArray.push("已通过</a></td>");
						} else{
							userListArray.push("已提交</a></td>");
						}
					} else{
						if(i<submitListLength+passListLength+resetListLength){
							userListArray.push("<td>已驳回</td>");
						} else{
							userListArray.push("<td>未提交</td>");
						}
					}
					userListArray.push("</tr>");
				}

				$("#usersReply tbody").empty().append(userListArray.join(""));
				loadStatisticsTip();
			},
			error:function(){
				$.tips("作业查看加载失败，请稍后重试","系统提示");
			},
			complete:function(){
			}
		})
	};

	//作业编辑器
	$assignmentEditor=$('[data-remodal-id=assignEditor]').remodal();
	//作业题目编辑器
	$topicEditor=$("[data-remodal-id=assignTopicEditor]").remodal();
	//人员查看编辑器
	$assignUserEditor=$("[data-remodal-id=assignUserEditor]").remodal();
	//邮件派送资源编辑器
	$dispatchEmailEditor=$("[data-remodal-id=dispatchEmailEditor]").remodal();
	//学员提交作业查看器
	$answerEditor=$("[data-remodal-id=answerEditor]").remodal();
	
	$("#assignUserContainer").on("click",".search-icon",function(){
		$(this).parents(".userListFrame").searchInfo(loadStatisticsTip);
	}).on("keydown","#search-info",function(event){
		if(event.keyCode == "13"){
			$(this).parents(".search").find(".search-icon").click();
		}
	});
	
	var getSelectedUsers=function(){
		var selectedUsers=new Array();
		$("#assignUserContainer .currentTable input[type=checkbox]:checked").each(function(index,that){
			selectedUsers.push(that.value);
		})
		return selectedUsers;
	}
	
	var seeUsersList=function(){
		var $currentRadio=$("#assignUserContainer .tabContainer input[name=userTab]:checked");
		if($currentRadio.val()=="0"){
			$("#assignUserContainer").removeClass("userAnswerStatus").addClass("userSelectStatus");
			$("#attendedUsers").addClass("currentTable");
			$("#usersReply").removeClass("currentTable");
			loadAttendedUserList();
		}
		else{
			$("#assignUserContainer ").removeClass("userSelectStatus").addClass("userAnswerStatus");
			$("#usersReply").addClass("currentTable");
			$("#attendedUsers").removeClass("currentTable");
			loadAssignUsers({"assignId":assignmentArea.currentAssign});
		}
	};
	
	$(document).on('opening', '[data-remodal-id=assignUserEditor]', function () {
		seeUsersList();
	});
	
	
	$(document).on('closing', '[data-remodal-id=answerEditor]', function () {
		$assignUserEditor.open();
	});
	
	
	$("#assignUserContainer .tabContainer input[name=userTab]").change(function(){
		seeUsersList();
	})
	
	
	$("#assignUserContainer table").on("click","input[type=checkbox]",function(event){
		 event.stopPropagation();
	});
	
	
	$("#assignUserContainer").on("click","tbody tr",function(){
		var $checkbox=$("input[name=operatorId]",$(this));
		$checkbox.prop("checked",!$checkbox.prop("checked"));
	});
	
	
	$("#usersReply").on("click",".seeAnswers",function(){
		$currentLine=$(this).parents("tr");
		var operatorId=$("input[name=operatorId]",$currentLine).val();
		var assignId=$("input[name=assignId]",$currentLine).val();
		$.ajax({
			type:"post",
			data:{"assignId":assignId,"operatorId":operatorId},
			url:"assignAction.do?method=seeUserAnswers",
			dataType:"json",
			success:function(data){
				if(data.result=="true"){
					var assignTopicList=data.assignTopicList;
					var contentInfoArray=new Array();
					var topicLength=assignTopicList.length;
					for(var i=0;i<topicLength;i++){
						var topicForm=assignTopicList[i];
						contentInfoArray.push("<li class='answerItem'>");
						contentInfoArray.push("<div class='question-name'>");
						contentInfoArray.push((i+1)+"、");
						contentInfoArray.push(topicForm.topicContent);
						contentInfoArray.push("</div>");
						contentInfoArray.push("<div class='answer'>");
						if(topicForm.form=="1"){
							//附件
							contentInfoArray.push("<a href=\"javascript:void(0);\" class=\"simpleButton\" onclick=\"javascript:window.open('"+topicForm.content+"')\">下载</a>");
						}
						else{
							contentInfoArray.push("<p class='answerContent'>"+topicForm.content+"</p>");
						}
						contentInfoArray.push("</div></li>");
					}
					$(".answerEditor ul.answerList").html(contentInfoArray.join(""));
					$("#feedbackForAnswer input[name='operatorId']").val(operatorId);
					$("#feedbackForAnswer input[name='assignId']").val(assignId);
				}
				else{
					$.tips("作业查看失败，请稍后尝试","系统提示");
				}
			}
		})
		$answerEditor.open();
	})
	
	$("#assignUserContainer .search-icon").click(function(){
		$("#assignUserContainer").searchInfo();
	});
	
	$("#assignUserContainer .selectAll input[type=checkbox]").click(function(){
		var checkStatus=$(this).prop("checked");
		$("#assignUserContainer .currentTable tr").each(function(index,that){
			if($(that).hasClass("notShow"));
			else{
				$("input[type=checkbox]",$(that)).prop("checked",checkStatus);
			}
		})
	})
	
	$("#deleteAnswer").click(function(){
		var selectedUsers = getSelectedUsers();
		if(selectedUsers.length==0){
			$.tips("您尚未指定作业派送对象","系统提示");
		} else{
			$.ajax({
				traditional:true,
				data:{"operatorId":selectedUsers,"assignId":assignmentArea.currentAssign},
				url:"../assignment/freezeUserDistribute.do",
				dataType:"json",
				type:"post",
				success:function(){
					$(".currentTable input[type=checkbox]:checked").each(function(index,that){
						$(that).parents("tr").remove();
					})
				},
				error:function(){
					$.tips("学员答题情况删除失败！");
				},
				complete:function(){
				}
			})
		}
	})
	
	//绑定动作到派送作业给学员
	$("#dispatch").click(function(){
		var selectedUsers=getSelectedUsers();
		
		if(selectedUsers.length==0){
			$.tips("您尚未指定作业派送对象","系统提示");
		} else{
			var $bufferMask=showBufferMask({"element":".assignUserEditor"});
			$.ajax({
				traditional:true,
				data:{"operatorId":selectedUsers,"assignId":assignmentArea.currentAssign},
				url:"assignAction.do?method=distributeAssign",
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.result=="true"){
						$.tips("作业派送成功，学员登录后可在'我的作业中'查看该作业","系统提示");
					} else{
						$.tips("作业派送失败，请稍后重试","系统提示");
					}
				},
				error:function(){
					$.tips("作业派送失败，请稍后重试","系统提示");
				},
				complete:function(){
					$bufferMask.remove();
				}
			})
		}
	});	
	
	//绑定动作到邮件派送作业给学员
	$("#mailDispatch").click(function(){
		loadAssignInfo(assignmentArea.currentAssign,function(assignInfo){
			$(".dispatchEmailEditor input[name=mailTitle]").val(assignInfo.name+"作业提交通知");
			$(".dispatchEmailEditor textarea[name='mailContent']").val("系统提示！请您及时提交作业"+assignInfo.name);
		})
		
		$dispatchEmailEditor.open();
	});
	
	//绑定动作到邮件派送内容编辑
	$(document).on('confirmation', '[data-remodal-id=dispatchEmailEditor]', function (){
		var selectedUsers=getSelectedUsers();

		if(selectedUsers.length==0){
			$.tips("您尚未指定作业派送对象","系统提示");
		} else{
			var $bufferMask=showBufferMask({"element":".assignUserEditor"});
			var mailContent=$(".dispatchEmailEditor textarea[name='mailContent']").val();
			var mailTitle=$(".dispatchEmailEditor input[name=mailTitle]").val();
			$.ajax({
				traditional:true,
				data:{"operatorId":selectedUsers,"assignId":assignmentArea.currentAssign,"mailContent":mailContent,"mailTitle":mailTitle},
				url:"../assignment/distributeAssignByMail.do",
				dataType:"json",
				type:"post",
				success:function(data){
					if(data.status){
						$.tips("作业邮件派送成功","系统提示",function(){
							$assignUserEditor.open();
						});
					} else{
						$.tips("作业邮件派送失败，请重新操作","系统提示",function(){
							$assignUserEditor.open();
						});
					}
				},
				error:function(){
					$.tips("作业派送失败，请稍后重试","系统提示",function(){
						$assignUserEditor.open();
					});
				},
				complete:function(){
					$bufferMask.remove();
				}
			})
		}
	});
	
	$("#addAssignment").click(function(){
		$assignmentEditor.open();
	});

	
	$(document).on("closed","[data-remodal-id=assignEditor]",function(){
		clearAssignEditor();
		$(".currentAssign").removeClass("currentAssign");
 	});
	
	$(document).on("closed","[data-remodal-id=assignTopicEditor]",function(){
		
		$("#topicItemList .topicItem").each(function(index,that){
			if(index==0){
				$(that).removeClass("opened");
			} else{
				$(that).remove();
			}
		})
 	});
	
	
	$(document).on("closed","[data-remodal-id=assignUserEditor]",function(){
		$("#assignUserContainer tbody").empty();
 	});
		
	$("#addTopicLine").click(function(){
		var $firstTopicItem=$(".topicItem:first");
		if($firstTopicItem.hasClass("opened")){
			$firstTopicItem.removeClass("opened");
			$firstTopicItem.fadeOut(400);
		} else{
			$firstTopicItem.addClass("opened");
			$firstTopicItem.fadeIn(400);
		}
	})
	
	$("#assignmentList ").on("click",".assignName",function(){
		loadInfoIntoAssignEditor($("input[name=assignId]",$(this)).val());
		$(this).addClass("currentAssign");
		$assignmentEditor.open();
	})
	
	
	$("#assignmentList").on("click",".replyStatistics",function(){
		var $assignItem=$(this).parents(".assignmentItem");
		var id=$("input[name=assignId]",$assignItem).val();
		assignmentArea.currentAssign=id;
		$assignUserEditor.open();
	})
	
	$("#assignmentList").on("click",".editTopic",function(){
		
		var $assignItem=$(this).parents(".assignmentItem");
		var id=$("input[name=assignId]",$assignItem).val();
		assignmentArea.currentAssign=id;
		var topicListArray=new Array();

		$.ajax({
			type:"POST",
			data:{"assignId":id},
			url:"../assignment/showTopicsOfAssign.do",
			dataType:"json",
			success:function(data){
				if(data.status){
					var canChange = data.data.canChange;
					var topicList = data.data.topicList;
					var topicLength=topicList.length;
					if(topicLength>0){
						$("#topicItemList .topicItem:first").css("display","none");
						for(var i=0;i<topicLength;i++){
							var topic=topicList[i];
							topicListArray.push("<div class='topicItem'><form>");
							topicListArray.push("<input type='hidden' name='id' value='"+topic.id+"'/>");
							topicListArray.push("<p class='topicAttribute'><span class='attributeName contentAttribute'>内容</span>");
							topicListArray.push("<textarea class='content'>"+topic.content+"</textarea></p>");
							if(topic.form=="0"){
								topicListArray.push("<p class='topicAttribute'><span class='attributeName '>提交形式</span><input type='radio' name='form' value='0' checked />文本输入<input type='radio' name='form' value='1' />附件上传</p>");
							} else{
								topicListArray.push("<p class='topicAttribute'><span class='attributeName '>提交形式</span><input type='radio' name='form' value='0' />文本输入<input type='radio' value='1' name='form' checked />附件上传</p>");
							}
							if(topic.required=="0"){
								//选填
								topicListArray.push("<p class='topicAttribute'><span class='attributeName '>是否必填</span><input type='radio' name='required' value='1'  />是<input type='radio' name='required' value='0' checked />否</p>");
							} else{
								//必填
								topicListArray.push("<p class='topicAttribute'><span class='attributeName '>是否必填</span><input type='radio' name='required' value='1' checked />是<input type='radio' name='required' value='0' />否</p>");
							}
							topicListArray.push("<div class='actionButtonGroup showForChange'><span class='simpleButton blueSimpleButton save' style='margin-bottom:10px;'>确定</span><span class='simpleButton clear'>清除</span></div></form>");
							topicListArray.push("<div class='orderButtonGroup showForChange'><div class='uparrow'></div><div class='downarrow'></div></div></div>");
						}
						$("#topicItemList").append(topicListArray.join(""));
						if(canChange=="false"){
							$("#assignTopicContainer .showForChange").css("display","none");
							$.tips("因该问卷已有作答,如需启动编辑请删除学员作答情况","系统提示");
						}
					} else{
						if(canChange=="true"){
							$("#addTopicLine").click();
						}
					}	
				}
			}
		});
		$topicEditor.open();
	});
	
	$("#topicItemList").on("click",".replyStatistics",function(){
		
	});
	
	$("#assignmentList").on("click",".deleteAssign",function(){
		var $assignItem = $(this).parents(".assignmentItem");
		var id = $("input[name=assignId]",$assignItem).val();
		$.ajax({
			type:"POST",
			data:{"assignId":id},
			url:"../assignment/deleteAssignment.do",
			dataType:"json",
			success:function(data){
				if(data.status){
					$assignItem.fadeOut();
				} else{
					if($.trim(data.msg) != ""){
						$.tips(data.msg,"系统提示");
					} else{
						$.tips("作业删除失败,请稍后尝试！","系统提示");
					}
				}
			}
		})
	});
	
	//绑定动作到作业题目暂存操作
	$("#assignTopicContainer").on("click",".actionButtonGroup .save",function(){
		var $topicItem=$(this).parents(".topicItem");
		if($topicItem.hasClass("firstItem")){
			var $newTopic=$topicItem.clone();
			$newTopic.appendTo($("#topicItemList"));
			$newTopic.append("<div class='orderButtonGroup'><div class='uparrow'></div><div class='downarrow'></div></div>").removeClass("firstItem").removeClass("opened");
			$(".clear",$topicItem).click();
		}
	})
	
	//绑定动作到作业题目删除操作
	$("#assignTopicContainer").on("click",".actionButtonGroup .clear",function(){
		var $topicItem=$(this).parents(".topicItem");
		var assignId=assignmentArea.currentAssign;
		if($topicItem.hasClass("firstItem")){
			$(".content",$topicItem).val("");
			$("input[name='form']:first",$topicItem).prop("checked","true");
			$("input[name='required']:first",$topicItem).prop("checked","true");
		} else{
			var id=$("input[name=id]",$topicItem).val();
			if($.trim(id)==""){
				$topicItem.remove();
			} else{
				$.ajax({
					type:"POST",
					data:{"id":id,"assignId":assignId},
					url:"../assignment/deleteTopicByBatch.do",
					dataType:"json",
					success:function(data){
						if(data.status){
							$topicItem.fadeOut();
						} else{
							if($.trim(data.msg)!=""){
								$.tips(data.msg,"系统提示");
							} else{
								$.tips("作业删除失败,请稍后尝试！","系统提示");
							}
						}
					}
				})
			}
		}
	})
	
	
	
	//绑定动作到作业题目创建保存操作
	$(document).on('confirmation', '[data-remodal-id=assignTopicEditor]', function (){
		var topicIdArray = new Array();
		var contentArray = new Array();
		var formArray = new Array();
		var requiredArray = new Array();
		$("#topicItemList .topicItem").each(function(index,that){
			if(index>0){
				var topicId = $("input[name=id]",that).val();
				var content = $(".content",that).val();
				var form = $("input[name=form]:checked",that).val();
				var required = $("input[name=required]:checked",that).val();
				topicIdArray.push(topicId);
				contentArray.push(content);
				formArray.push(form);
				requiredArray.push(required);
			}
		})
		$.ajax({
			type:"post",
			url:"../assignment/updateTopic.do",
			traditional:true,
			dataType:"json",
			data:{
				required:requiredArray,
				form:formArray,
				id:topicIdArray,
				content:contentArray,
				assignId:assignmentArea.currentAssign
			},
			success:function(){
				$.tips("作业题目保存成功！","系统提示",function(){
					$topicEditor.close();
				});
			},
			error:function(){
				$.tips("作业题目保存失败，请稍后重试！","系统提示",function(){});
			},
			complele:function(){
				
			}
		})
	});
	
	//绑定动作到调整题目顺序
	$(document).on("click",".topicItem .uparrow",function(){
		var $currentTopicItem = $(this).parents(".topicItem");
		var $prev = $currentTopicItem.prev();
		if($prev.length>0){
			var currentHTML = $currentTopicItem.html();
			var prevHTML = $prev.html();
			$currentTopicItem.html(prevHTML);
			$prev.html(currentHTML);
		}
	});
	
	$(document).on("click",".topicItem .downarrow",function(){
		var $currentTopicItem=$(this).parents(".topicItem");
		var $next=$currentTopicItem.next();
		if($next.length>0){
			var currentHTML=$currentTopicItem.html();
			var nextHTML=$next.html();
			$currentTopicItem.html(nextHTML);
			$next.html(currentHTML);
		}
	})
	
	//绑定动作到作业基本信息确认更改操作
	$(document).on('confirmation', '[data-remodal-id=assignEditor]', function (){
		
		var $resourceId = $("#assignContainer .assignHiddenInfo input[name=resourceId]");
		var $type = $("#assignContainer .assignHiddenInfo input[name=type]");
		var $name = $(".assignEditor input[name='name']");
	    var $description = $(".assignEditor textarea.description");
		var $id = $(".assignEditor input[name=id]");
		var $comment = $(".assignEditor textarea.comment");
		if($.trim($name.val())==""){
			$.tips("请输入作业名称后点击保存","系统提示",function(){
				$name.focus();
			});
		} else{
			$.ajax({
				type:"POST",
				data:{
					resourceId:$resourceId.val(),
					type:$type.val(),
					name:$.trim($name.val()),
					description:$.trim($description.val()),
					id:$id.val(),
					comment:$.trim($comment.val())
				},
				url:"../assignment/updateAssignInfo.do",
				dataType:"json",
				success:function(data){
					if(data.status){
						var assignInfo = assignmentArea.assignInfoMap[$id.val()];
						var assigInfoLater = data.data;
						if(assignInfo == null){
							var appendInfoArray = new Array();
	       					appendInfoArray.push('<li class="assignmentItem moreAssignment"><div class="assignName"><font>');
							appendInfoArray.push(assigInfoLater.name);
							appendInfoArray.push('</font><input type="hidden" name="assignId"  value="'+assigInfoLater.id+'"  /></div><div class="assignOptions"><span class="editTopic">编辑题目</span><span class="replyStatistics">答题统计</span><span class="deleteAssign">删除</span></div><div class="createTime">');
							//appendInfoArray.push('<fmt:formatDate value="'+assigInfoLater.createDateStr+'" pattern="yyyy-MM-dd"/>');
							appendInfoArray.push(assigInfoLater.createDateStr);
							appendInfoArray.push('</div><div style="clear:both"></div></li>');
							$(appendInfoArray.join("")).insertAfter("#assignmentList .firstAssignment");
						} else{
							$(".currentAssign font").html(assigInfoLater.name);
							$(".currentAssign .editor").each(function(index,that){
								that.value=assigInfoLater[that.name];
							})
						}
						assignmentArea.assignInfoMap[assigInfoLater.id]=data.data;
					} else{
						$.tips("作业设置失败,请稍后尝试！","系统提示");
					}
				},
				error:function(data){
					$.tips("作业设置失败,请稍后尝试！","系统提示");
				}
			})
		}
	}); 
	
	
	$(".answerEditor #feedbackForAnswer").submit(function(){
		$(this).ajaxSubmit({
			 type: 'post',
			 dataType:'json',
			 url: 'assignAction.do?method=updateAnswersStatus',
			 success: function(data){
				if(data.result=="true"){
					$.tips("作业评价成功！","系统提示",function(){
						$answerEditor.close();
					});
				}
				else{
					$.tips("作业评价失败","系统提示",function(){
						$answerEditor.close();
					});
				}
			 },
			 complete:function(){
				 
			 }
		});
		return false;
	});
	
	
	$(".answerEditor").on("click","#feedbackForAnswer .simpleButton",function(){
		$(this).parents("#feedbackForAnswer").submit();
	})
	
})