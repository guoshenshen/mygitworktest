var alertInfoOption={
	alertTitle:'计划填报提示',
	confirmTitle:'',
	dialogStyle:'blueStyle'
}

var optionForUeditor = {
   initialStyle: 'p{line-height:1.5em;font-size:14px;font-family:宋体};span{font-size:14px;font-family:宋体}'
};

function AddUedit(){
	 var editor = new UE.ui.Editor(optionForUeditor);
	 editor.render('mailContent');

	 editor.addInputRule(function(root){
     root.traversal(function (node) {
          if (node.type == 'element') {
              switch (node.tagName) {
                  case 'span':
                  	 node.setStyle({'font-size':'14px',"font-family":"宋体","line-height":"1.5em"});
                  case 'p':
                       node.setStyle({'font-size':'14px',"font-family":"宋体","line-height":"1.5em"});
                  case 'div':
                       node.setStyle({'font-size':'14px',"font-family":"宋体"});
                  case 'li':
                  	 node.setStyle({'font-size':'14px',"font-family":"宋体"});
                  case 'table':
                       node.setStyle({'font-size':'14px',"font-family":"宋体"});
              }
          }
      })
  });	 
}


function showUrgePanel($panel,actionForUserSelected){
	var $mailContentPanel=$("#mailContentMain");
	$("input[name=operatorId]",$mailContentPanel).remove();
	var userArray=actionForUserSelected();
	var inputUserArray=new Array();
	var userArrayLength=userArray.length;
	for(var i=0;i<userArrayLength;i++){
		inputUserArray.push("<input type='hidden' name='operatorId' value='"+userArray[i]+"' />");
	}	
	$mailContentPanel.append(inputUserArray.join(""));
	$panel.open();
	
}

function showPreview(actionForUserSelected){
	var pop=$("#_pop_win");
	pop.find("a.pop_close_btn").click(function(){		
		$.unblockUI();
		
	});
	var $mailContentPanel=$("#mailContentMain");
	$("input[name=operatorId]",$mailContentPanel).remove();
	var userArray=actionForUserSelected();
	var inputUserArray=new Array();
	var userArrayLength=userArray.length;
	for(var i=0;i<userArrayLength;i++){
		inputUserArray.push("<input type='hidden' name='operatorId' value='"+userArray[i]+"' />");
	}	
	$mailContentPanel.append(inputUserArray.join(""));
	$.blockUI({ message: pop,
			    css: {width:"700px",height:"630px",cursor:"default",left:"30%",top:"18%"}
	});
}

function forSendUrgeContent(){
	var param={};
	var ue = UE.getEditor('mailContent');
	var $mailContentPanel=$("#mailContentMain");
	param.surveyTitle=$mailContentPanel.find("input[name=surveyTitle]").val();
	param.urgeTitle=$mailContentPanel.find("input[name=urgeTitle]").val();
	param.mailContent=ue.getContent();
	param.surveyId=$mailContentPanel.find("input[name=surveyId]").val();
	var userArray=new Array();
	$mailContentPanel.find("input[name=operatorId]").each(function(index,that){
		userArray.push(that.value);
	});
	param.userList=userArray;
	sendUrgeInfo(param);
}



//发送催促信息
function sendUrgeInfo(param){
	$.ajax({
		url:"infoSendMessage.do?method=sendmailContentByBatch",
		type:"post",
		dataType:"json",
		data:param,
		success:function(data){
			if(data.result=="true"){
				jAlert("催促信息发送完成",alertInfoOption.alertTitle);
			}
			else{
				var messageInfo=new Array();
				for(var i in data.cause){
					messageInfo.push(data.cause[i]);
				}
				jAlert(messageInfo.join(""),alertInfoOption.alertTitle);
			}
		},
		error:function(){
			
		},
		traditional:true
	});
}

$(function(){
	//为提示框设置显示样式
	$.alerts.dialogClass=alertInfoOption.dialogStyle;
	
})