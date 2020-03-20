/***********************************
 * 
 * edited by zq@cnic.cn on 2017-02-23
 * 
 * This js file can be used as a tool
 * to import person-list from addressbook
 * to target file.
 * 
 * to show concise effect :css/public/basicStyle.css is recommended
 * to invoke meanwhile
 * 
 * 
 * in this file, functions below can be overwritten:
 * setSuccessAction
 * setFailAction
 * setSuccessAddrImportAction: prior to setSuccessAction
 * setFailAddrImportAction: prior to setFailAction
 * setSuccessOrgUserImportAction: prior to setSuccessAction
 * setSuccessOrgUserImportAction: prior to setFailAction
 * closeUsersAddPanel
 * useStaff(important)
 * 
 * functions below are core:
 * showAddressBook(idTag,nameTag), 
 * to use this method, you need to ensure the file
 * you invoke this js file contains a tag such as
 * <input type='hidden'> with id=idTag and a tag 
 * with id=nameTag 
 * 
 * showUserAddChannelWin(idTag,nameTag),
 * severa ways of choosing people are listed here
 * 
 * 
 * setSuccessAction(important)
 * setFailAction(important)
 * 
 * *********************************/



//config info
var addUsersArea={
		idTag:null,
		nameTag:null,
		successImportAction:function(){
			try{
				jAlert("添加人员成功,若不再添加其他人员,请关闭窗口","提示信息",function(){
					$("#addressBookList")[0].contentWindow.addressBookInfoArea=false;
				});
			}catch(e){
				$("#addressBookList")[0].contentWindow.addressBookInfoArea=false;
			}
		},
		failImportAction: function(){
				try{
					jAlert("添加人员失败","提示信息",function(){
						$("addressBookList")[0].contentWindow.addressBookInfoArea=false;
					});
				}
				catch(e){
					alert("添加人员失败","提示信息");
					$("addressBookList")[0].contentWindow.addressBookInfoArea=false;
				}
		},
		successAddrImportAction:null,
		failAddrImportAction:null,
		successOrgUserImportAction:null,
		failOrgUserImportAction:null
}

//reset the action you want to show to users when adding person-list success
function setSuccessAction(successFunction){
	addUsersArea.successImportAction=successFunction;
}

//reset the action you want to show to users when adding person-list fail
function setFailAction(failFunction){
	addUsersArea.failImportAction=failFunction;
}


//reset the action you want to show to users when adding person-list success  by addrbook-list
function setSuccessAddrImportAction(successFunction){
	addUsersArea.successAddrImportAction=successFunction;
}

//reset the action you want to show to users when adding person-list fail  by addrbook-list
function setFailAddrImportAction(failFunction){
	addUsersArea.failAddrImportAction=failFunction;
}

//reset the action you want to show to users when adding person-list success by orgnization-list
function setSuccessOrgUserImportAction(successFunction){
	addUsersArea.successOrgUserImportAction=successFunction;
}

//reset the action you want to show to users when adding person-list fail  by orgnization-list
function setFailOrgUserImportAction(failFunction){
	addUsersArea.failOrgUserImportAction=failFunction;
}


//************************弹出人员选择界面************************************//
//show ways of selecting users to specified area
function showUserAddChannelWin(idTag,nameTag){
	if(idTag!=null&&$.trim(idTag).length>0){
		addUsersArea.idTag="#"+idTag;
	}
	else{
		addUsersArea.idTag=null;
	}
	if(nameTag!=null&&$.trim(nameTag).length>0){
		addUsersArea.nameTag="#"+nameTag;
	}
	else{
		addUsersArea.nameTag=null;
	}
	var channelWin="<div id='userAddChannel'><div id='_pop_win'><h2><a href='javascript:void(0);' class='pop_close_btn'>X</a></h2><br/><div class='buttonGroup'></div></div></div>";
	var pop=$(channelWin);
	pop.find(".buttonGroup").append("<div ><a href='javascript:void(0);' id='selectFromAddrBook' class='simpleButton'>从通讯录中添加</a></div>").
	append("<div ><a href='javascript:void(0);' id='selectFromOrg' class='simpleButton'>从部门添加</a></div>");
	pop.find(".buttonGroup #selectFromAddrBook").click(function(){
		showAddrBookStaff(idTag,nameTag);
	}) 
	pop.find(".buttonGroup #selectFromOrg").click(function(){
		selectusers(idTag,nameTag);
	});
	$.blockUI({message:pop,
        css:{width:"280px",height:"200px",cursor:"default",left:($left-280)/2-10,top:($top-200)/2-10}
	}); 
	
	//设置关闭操作
	pop.find("a.pop_close_btn").click(function(){
		$.unblockUI();
	});
}



//显示通讯录
function showAddrBookStaff(){
	try{
		$.unblockUI();
	}
	catch(e){
		console.log(e);
	}
	
	var pop_url="AddrBookAction.do?method=showAddressBookLists";
    var pop=$("<div id='_pop_win'><h2>通讯录信息"
				+"<a href='#;' class='pop_close_btn'>X</a></h2>"
				+"<iframe name='addressBookList' id='addressBookList' height='580' width='730' scrolling='no' class='pop_iframe' src='"+pop_url+"'></iframe></div>"
				+"<div style='clear:both'></div>"
				);
	$.blockUI({ message: pop,
				     css: {width:"730px",height:"620px",cursor:"default",left:($left - 730) /2-10 + 'px',top:($top-620) /2 -10+ 'px'}
		});		
		
	pop.find("a.pop_close_btn").click(function(){
		$.unblockUI();
	});
}

//显示通讯录
function getReceiverList(idTag,nameTag){
	var pop_url="AddrBookAction.do?method=selectAddrBook&idTag="+idTag+"&nameTag="+nameTag;
	/*pop window外观定义来自css/pop_window.css*/
	var pop=$("<div id='_pop_win'><h2>选择通讯录人员"
				+"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
				+"<iframe height='380' width='400' scrolling='no' class='pop_iframe' style='margin-right:50px;' src='"+pop_url+"'> </iframe></div>"
				+"<div style='clear:both'></div>"
				);
	pop.find("a.pop_close_btn").click(function(){
		$.unblockUI();
	});
	$.blockUI({ message: pop,
			    css: {width:"400px",height:"400px",cursor:"default",left:($left - 400) /2-10 + 'px',top:($top-400) /2 -10+ 'px'}
	 });
}



//显示组织机构下的成员
function selectusers(idTag,nameTag) {
	$.unblockUI();
	var pop_url="systemAction.do?method=selectUsers";
	if(idTag!=null&&$.trim(idTag).length>0){
		pop_url+="&idTag="+$.trim(idTag);
	}
	if(nameTag!=null&&$.trim(nameTag).length>0){
		pop_url+="&nameTag="+$.trim(nameTag);
	}
	var pop=$("<div id='_pop_win'><h2>选择人员"
				+"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
				+"<iframe height='580' width='700' scrolling='auto' class='pop_iframe' src='"+pop_url+"'> </iframe></div>"
				+"<div style='clear:both'></div>"
				);
	pop.find("a.pop_close_btn").click(function(){
		$.unblockUI();
	});
	$.blockUI({ message: pop,
			     css: {width:"700px",height:"625px",cursor:"default",left:($left - 700) /2-10 + 'px',top:($top-625) /2 -10+ 'px'}
	});		
	return;
}






//the action you want to trigger when users click 'goback' button in addresslist panel(can be overwritten)
function gobackPanel(){
	$.unblockUI();
}

//the action you want to trigger when users close usersAddPanel (can be overwritten) 
function closeUsersAddPanel(){
	$.unblockUI();
}





//将电话通讯录返回的信息添加至人员区域textare及隐藏域中(can be overwritten),后执行特定功能（如完成添加人员到通讯录、参会名单等持久化操作）
function useStaff(staffArray){
	if(addUsersArea.idTag!=null&&addUsersArea.nameTag!=null){
		addStaffToTargetBox(staffArray);
		if(addUsersArea.successAddrImportAction!=null){
			addUsersArea.successAddrImportAction();
		}
		else{
			addUsersArea.successImportAction();
		}	
	}
	else{
		if(addUsersArea.failAddrImportAction!=null){
			addUsersArea.failAddrImportAction();
		}
		else{
			addUsersArea.failImportAction();	
		}	
	}
}



//将组织机构筛选得到的人员添加至人员区域textare及隐藏域中(can be overwritten),后执行特定功能（如完成添加人员到通讯录、参会名单等持久化操作）
function afterCloseSelectUsersAction(){
	if(addUsersArea.idTag!=null&&addUsersArea.nameTag!=null){
		if(addUsersArea.successOrgUserImportAction!=null){
			addUsersArea.successOrgUserImportAction();
		}
		else{
			addUsersArea.successImportAction();
		}
	}
	else{
		if(addUsersArea.failOrgUserImportAction!=null){
			addUsersArea.failOrgUserImportAction;
		}
		else{
			addUsersArea.failImportAction();		
		}
		
	}
}




//添加电话通讯录人员operatorId至指定位置(can be overwritten)
function addStaffToTargetBox(staffArray){
	var idStr=$.trim($(addUsersArea.idTag).val());
	var staffIdArray=new Array();
	var staffNameArray=new Array();
	for(var i in staffArray){
		if(idStr.indexOf(";"+staffArray[i].operatorId+";")<0&&idStr.indexOf(staffArray[i].operatorId+";")!=0){
			staffIdArray.push(staffArray[i].operatorId);
			staffNameArray.push(staffArray[i].operatorName);
		}
	}
	
	var operatorIdListStr="";
	var operatorNameList="";
	if(staffIdArray.length>0){
		operatorIdListStr=staffIdArray.join(";")+";"
	}
	if(staffNameArray.length>0){
		operatorNameList=staffNameArray.join(";")+";";
	}
	if($(addUsersArea.idTag).length>0){
		$(addUsersArea.idTag).val($(addUsersArea.idTag).val()+operatorIdListStr);
	}
	if($(addUsersArea.nameTag).length>0){
		$(addUsersArea.nameTag).val($(addUsersArea.nameTag).val()+operatorNameList);
	}
	
}












