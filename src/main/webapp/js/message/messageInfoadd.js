var messageInfoaddArea={
		idTag:null,
		nameTag:null
}


var alertInfoOption={
		alertTitle:'提示信息',
		confirmTitle:'提示信息',
		dialogStyle:'blueStyle'
}


$(function(){
	$.alerts.dialogClass=alertInfoOption.dialogStyle;
})


//清空某区域已选人员
function clearusers(idList,nameList)
{
	var idListQuery="#"+idList;
	var nameListQuery="#"+nameList;
	$(idListQuery).val("");
	$(nameListQuery).val("");
}


//从通讯录中选择人员
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

//显示通讯录
function showAddressBook(idTag,nameTag){
	messageInfoaddArea.idTag="#"+idTag;
	messageInfoaddArea.nameTag="#"+nameTag;
	showAddrBookStaff();
}


//添加人员到通知对象中(确保同一区域人员不重复)
function addStaffToTargetBox(staffArray){
	var idStr=$.trim($(messageInfoaddArea.idTag).val());
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
	
	$(messageInfoaddArea.idTag).val($(messageInfoaddArea.idTag).val()+operatorIdListStr);
	$(messageInfoaddArea.nameTag).val($(messageInfoaddArea.nameTag).val()+operatorNameList);

}


function gobackPanel(){
	$.unblockUI();
}



//将电话通讯录返回的信息添加至人员区域textare及隐藏域中
function useStaff(staffArray){
	if(messageInfoaddArea.idTag!=null&&messageInfoaddArea.nameTag!=null){
		addStaffToTargetBox(staffArray);
		jAlert("通知对象添加成功,如无需从通讯录中添加其他学员,请点击关闭按钮",alertInfoOption.alertTitle,function(){
			$("#addressBookList")[0].contentWindow.addressBookInfoArea=false;
		});
	}
	else{
		jAlert("未能成功将通讯录中人员添加至通知列表中",alertInfoOption.alertTitle);
		$("#addressBookList")[0].contentWindow.addressBookInfoArea=false;
	}
}


