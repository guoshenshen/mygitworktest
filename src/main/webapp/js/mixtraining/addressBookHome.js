/////通讯录模块主页面对应js

var addressBookHomeArea={

		alertInfoOption:{
				alertTitle:'通讯录管理提示',
				confirmTitle:'通讯录管理提示',
				dialogStyle:'blueStyle'
		},
		reloadWindow:function(){
			
		}
}

$(function(){
	
	$.alerts.dialogClass=addressBookHomeArea.alertInfoOption.dialogStyle;
	
	/*
	var trainId=$("#trainId").val();
	
	if(trainId=="-1"){
		$("#tabs li:last").addClass("selected");
		$(".homezoneall").addClass("otherTrainAddrBookShow");
	}
	else{
		$("#tabs li:first").addClass("selected");	
		$(".homezoneall").addClass("currentTrainAddrBookShow");
	}
	*/
	
	//绑定动作到选项卡切换
	/*
	$("#tabs li:first").click(function(){
		$("#trainId").val("");
		reloadWindow();
	})
	
	$("#tabs li:last").click(function(){
		$("#trainId").val("-1");
		reloadWindow();
	})
	*/
});

function closePop(){
	$.unblockUI();
}

function copyAddrbook(param) {
	 $.ajax({
		url:'../addrBook/quoteAddrBook.do',
		type:'post',
		data:param,
		dataType:'json',
		success:function(data){
			if(data.status){
				jAlert(data.msg,addressBookHomeArea.alertInfoOption.confirmTitle,function(){
					//通讯录复制成功
					addressBookHomeArea.reloadWindow(param);
				})
			} else{
				//通讯录复制失败
				jAlert(data.msg,addressBookHomeArea.alertInfoOption.alertTitle);
			}
		},
		error:function(data){
			//通讯录引用失败
			jAlert("通讯录引用失败",addressBookHomeArea.alertInfoOption.confirmTitle);
		}
	 })
}

function deleteAddrbook(param){
	jConfirm("通讯录删除后将无法恢复，请确定是否删除该通讯录",addressBookHomeArea.alertInfoOption.confirmTitle,function(response){
		if(response==true){
		    $.ajax({
		        url:'../addrBook/removeAddrBook.do',
		        type:'post',
		        data:param,	
		        dataType:'json',
		        success:function(data){
		        	if(data.status){
		        		jAlert(data.msg,addressBookHomeArea.alertInfoOption.confirmTitle,function(){
		        			//通讯录删除成功
		        			addressBookHomeArea.reloadWindow(param);
		        		})
		        	} else{
		        		//通讯录删除失败
		        		jAlert(data.msg,addressBookHomeArea.alertInfoOption.alertTitle);
		        	}
		        },
		        error:function(data){
		        	//通讯录删除失败
	        		jAlert("通讯录删除失败",addressBookHomeArea.alertInfoOption.confirmTitle);
		        }
		     })
		}
		else{
			//不删除
		}
	})
  }