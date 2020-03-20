var editAddressBookArea={
		alreadyUsers:new Array(),
		newUsers:new Array(),
		deleteUsers:new Array(),
		canSubmit:true,
		alertInfoOption:{
				alertTitle:'通讯录管理提示',
				confirmTitle:'',
				dialogStyle:'blueStyle'
		}
}

$(function(){
	$.alerts.dialogClass=editAddressBookArea.alertInfoOption.dialogStyle;
})

//通过单位组织机构人员列表将学员添加至通讯录中
function selectUsersToAddressBook(){
	var selectedUserIdArray=new Array();
	var $bufferMask=showBufferMask();
	$selectedUserId=$("#selectedUserId input[name=selectedUserId]").each(function(index,that){
		selectedUserIdArray.push(that.value);
	});
	var param={'operatorIds':selectedUserIdArray};
	$.ajax({
		url: '../eosorgTEmployee/JSONDetails.do',
		   type:'POST',
		   dataType:"json",
		   data: param,
		   traditional: true,
		   success: function(data){
				addUsersToAddressBook(data.data);
				$bufferMask.remove();
		   },
		   error:function(){
			   $bufferMask.remove();
		   }
		   
	})
}

//调用通过组织机构人员列表窗口选择学员必须
function afterCloseSelectUsersAction(){
	selectUsersToAddressBook();

}

//将人员信息添加至电话簿编辑界面
function addUsersToAddressBook(dataList){
	var dataInfoArray=new Array();
	var personListLength=$("#personnelInfoForAdd tr").length;
	var attentionInfo="";
	for(var i=0;i<dataList.length;i++){
		if($.inArray(dataList[i].operatorId,editAddressBookArea.alreadyUsers)>=0||$.inArray(dataList[i].operatorId,editAddressBookArea.newUsers)>=0){
			//attentionInfo+=dataList[i].operatorName+"已经添加,不必重复导入<br/>";
		} else{
			var index = personListLength+i;
			var dataInfo="<tr><td>"+index+"<input type='hidden' class='index' value='"+index+"'></td><td>"
				+dataList[i].operatorName+"<input type='hidden' class='operatorId' id='operatorId_"+dataList[i].operatorId+"' value='"+dataList[i].operatorId+"'></td><td title='"
				+dataList[i].orgName+"'>"+dataList[i].orgName+"</td><td title='"
				+dataList[i].oemail+"'>"+dataList[i].oemail+"</td><td title='"
				+dataList[i].otel1+"'>"+dataList[i].otel1+"</td><td><a href='javascript:void(0);' onclick='javascript:removeUsersFromAddressBook("+dataList[i].operatorId+")'>删除</a></td></tr>"
			dataInfoArray.push(dataInfo);
			editAddressBookArea.newUsers.push(dataList[i].operatorId);
		}
	}
	var inputStr=dataInfoArray.join("");
	$("#personnelInfoForAdd").append(inputStr);	
	if($.trim(attentionInfo)!=""){
		jAlert(attentionInfo,editAddressBookArea.alertInfoOption.alertTitle);
	}
}

//从通讯录中移除某成员
function removeUsersFromAddressBook(operatorId){
	operatorId=parseInt(operatorId);
	var queryStr="#operatorId_"+operatorId;
	var $removeTr=$(queryStr).parents("tr");
	var index=$removeTr.find("td:first .index").val();
	$removeTr.remove();
	var position=-1;
	position=$.inArray(operatorId,editAddressBookArea.newUsers);
	if(position>=0){
		editAddressBookArea.newUsers.splice(position,1);
	}
	else{
		position=$.inArray(operatorId,editAddressBookArea.alreadyUsers);
		if(position>=0){
			editAddressBookArea.alreadyUsers.splice(position,1);
			editAddressBookArea.deleteUsers.push(operatorId);
		}
	}

	$("#personnelInfoForAdd tr").each(function(index,thus){
		if(index>0&&index>=position){
			$(this).find("td:first").html(index+"<input type='hidden' class='index' value='"+index+"'>");
		}
	})
}

//持久化更新通讯录中现有的人员(包含添加、删除)
//options只需包含通讯录的基本信息,不需要包含通讯录的人员信息
function updateUsersFromAddressBook(options){
	if(editAddressBookArea.canSubmit==true){
		var $bufferMask=showBufferMask();
		editAddressBookArea.canSubmit=false;
		var submitUrl='';
		if(options.addrbook==null){
			submitUrl='../addrBook/generateTrainBook.do';
			options.importPersonId=editAddressBookArea.newUsers;
		}
		else{
			submitUrl='../addrBook/updateTrainBook.do';
			options.deletePersonId=editAddressBookArea.deleteUsers;
			options.importPersonId=editAddressBookArea.newUsers;
		}
		$.ajax({
			url:submitUrl,
			dataType:'json',
			type:'post',
			data: options,
			traditional: true,
			success:function(data){
				var resultInfo="";
				var ifSuccess=false;
				resultInfo += data.msg+"<br/>";
				if(data.status){
					ifSuccess=true;
				}

				$bufferMask.remove();	
				try{
					jAlert(resultInfo,editAddressBookArea.alertInfoOption.alertTitle,function(){
						if(ifSuccess){
							window.parent.closePop();	
							try{
								window.parent.addressBookHomeArea.reloadWindow();
								//window.parent.location.reload();
							}catch(e){
								//window.parent.location="AddrBookAction.do?method=queryTrainBook";
							}
						} else{
							editAddressBookArea.canSubmit=true;
						}
					});
				}catch(e){
					alert(resultInfo);
					if(ifSuccess){
						window.parent.closePop();
						try{
							window.parent.addressBookHomeArea.reloadWindow();
							//window.parent.location.reload();
						}catch(e){
							//window.parent.location="AddrBookAction.do?method=queryTrainBook";
						}
					} else{
						editAddressBookArea.canSubmit=true;
					}
				}
				
			},
			error:function(data){
				$bufferMask.remove();
				try{
					jAlert("通讯录创建失败",editAddressBookArea.alertInfoOption.confirmTitle,function(){
						editAddressBookArea.canSubmit=true;
					});
				}catch(e){
					alert("通讯录创建失败");
					editAddressBookArea.canSubmit=true;
				}	
			}
		})
	}
}

function loadExtraOptions(){
	return {
		forUsers:{
			"#selectedUserId":{
				actionAfterUserselect:function(){
					$.Nconfirm({
						'confirmBtnText':'继续添加',
						'denyBtnText':'添加完毕',
						"confirmQuestion":"人员添加完毕，是否继续添加其他人员？",
						"onConfirm":function(){},
						"onDeny":function(){
							afterCloseSelectUsersAction();
							orgAndUsersSelectArea.$currentWindow.close();
						}
					});
				}
			}
		}
		
	}
}
