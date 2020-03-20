//created by zq@cnic.cn at 2017-02-15 
//维护培训班的参会人员

var alertInfoOption={
			alertTitle:'提示信息',
			confirmTitle:'',
			dialogStyle:'blueStyle'
	}


$(function(){
	$.alerts.dialogClass=alertInfoOption.dialogStyle;
})



//显示培训班参会人员添加渠道窗口
function showUserAddChannelWin(){
	var pop=$("#userAddChannel");
	 $.blockUI({message:pop,
         css:{width:"280px",height:"200px",cursor:"default",left:($left-280)/2-10,top:($top-200)/2-10}
	}); 
	
	//设置关闭操作
	pop.find("a.pop_close_btn").click(function(){
		$.unblockUI();
	});
}

function gobackPanel(){
	$.unblockUI();
	showUserAddChannelWin();
}

//显示通讯录中的人员名单
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

function useStaff(staffArray){
	var staffIdArray=new Array();
	for(var i in staffArray){
		staffIdArray.push(staffArray[i].operatorId);
	}
	var param={"operatorIds":staffIdArray};
	if($("#addressBookList").length!=0){
		//var bufferMask=showBufferMask({element:"#addressBookList"});
	}
	$.ajax({
		url: 'mtMixtrainUserTrainInfoAction.do?method=addUsersIntoTrain',
		   type:'POST',
		   dataType:"json",
		   data: param,
		   traditional: true,
		   success: function(data){
		 	// bufferMask.remove();
			 if(data.result=="true"){
				 jAlert("人员信息添加成功!",alertInfoOption.alertTitle,function(){
						window.location='mtMixtrainUserTrainInfoAction.do?method=browseTrainUserInfo';
					}); 
			 }
			 else{
				 var reason=new Array();
				 for(var i in data.reason){
					 reason.push(data.reason[i].info+"<br/>");
				 }
				 jAlert("以下人员信息插入失败:<br/>"+reason.join(""),alertInfoOption.alertTitle,function(){
					 if(window.frames["addressBookList"].contentWindow.addressBookInfoArea!=null){
						 window.location='mtMixtrainUserTrainInfoAction.do?method=browseTrainUserInfo';
					   }
					});  
			 }	
		   },
		   error:function(data){
			  // bufferMask.remove();
			   jAlert("人员信息插入失败:<br/>",alertInfoOption.alertTitle,function(){
				   if(window.frames["addressBookList"].contentWindow.addressBookInfoArea!=null){
					   window.frames["addressBookList"].contentWindow.addressBookInfoArea=false;
				   }
			   });
		   }
	})
	
}