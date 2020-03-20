/*
 * creator zq@cnic.cn
 * created on 2017-04-25
 * last edited on 2017-04-26
 * 
 * 
 * 
 * 
 */


var jquery_progressBarOptions={
		
		//百分比进度条
		percent_progressBarOptions:{
			
		},
		
		
		//控制分步提交操作
		step_progressBarOptions:{
			defaultStepNode:{
				stepName:'下一步',
				actionBeforeForward:function(){return true},
				actionForForward:function(){return true},
				actionBeforeBack:function(){return true},
				actionBeforeFinish:function(){return true},
				actionForBack:function(){return true},
				backable:true,
				forwardable:true,
				isCurrentStep:false
			},
			stepNodes:[
			    //{stepName:xxx,actionBeforeForward:function,actionBeforeBack:function,backable:true,forwardable:true,isCurrentStep:false}
			]
		},
		currentStepInfo:{
			lastStepIndex:0,
		    currentStepIndex:0
		},
		targetObject:{}
			
}

jquery_progressBarOptions.createStepNode=function(stepNodeInfo){
	var reviseddNodeInfo={};
	$.extend(reviseddNodeInfo,jquery_progressBarOptions.step_progressBarOptions.defaultStepNode,stepNodeInfo);
	return reviseddNodeInfo;
}


jquery_progressBarOptions.resetCurrentStep=function(currentIndex){
	jquery_progressBarOptions.currentStepInfo.currentStepIndex=currentIndex;
	jquery_progressBarOptions.setStepProgress();
}


jquery_progressBarOptions.asycBackStep=function(){
	var currentStep=stepNodesArray[currentStepInfo.currentStepIndex];
	var currentStepInfo=jquery_progressBarOptions.currentStepInfo;
	var stepNodesArray=jquery_progressBarOptions.step_progressBarOptions.stepNodes;
	currentStep.actionForBack();
	currentStepInfo.currentStepIndex-=1;
	jquery_progressBarOptions.setStepProgress();
}


//进行下一个环节,包括当前环节跳转到后面环节前需要完成的动作,进度条变化
jquery_progressBarOptions.backStep=function(){
	var currentStepInfo=jquery_progressBarOptions.currentStepInfo;
	var stepNodesArray=jquery_progressBarOptions.step_progressBarOptions.stepNodes;
	if(currentStepInfo.currentStepIndex==0){
		//已经到达起始节点,不得back
	}
	else{
		var currentStep=stepNodesArray[currentStepInfo.currentStepIndex];
		if(currentStep.backable){
			var result=true;
			result=currentStep.actionBeforeBack();
			if(result){
				currentStep.actionForBack();
				currentStepInfo.currentStepIndex-=1;
				jquery_progressBarOptions.setStepProgress();
			}
		}
	}
}


jquery_progressBarOptions.asycforwardStep=function(){
	var currentStepInfo=jquery_progressBarOptions.currentStepInfo;
	var stepNodesArray=jquery_progressBarOptions.step_progressBarOptions.stepNodes;
	var currentStep=stepNodesArray[currentStepInfo.currentStepIndex];
	currentStep.actionForForward();
	currentStepInfo.currentStepIndex+=1;
	jquery_progressBarOptions.setStepProgress();
}


//返回上一个环节,包括当前环节跳转到后面环节前需要完成的动作,进度条变化
jquery_progressBarOptions.forwardStep=function(){
	var currentStepInfo=jquery_progressBarOptions.currentStepInfo;
	var stepNodesArray=jquery_progressBarOptions.step_progressBarOptions.stepNodes;
	if(currentStepInfo.currentStepIndex==stepNodesArray.length-1){
		//已经到达最终节点,不得forward,直接执行finish操作
		var currentStep=stepNodesArray[currentStepInfo.currentStepIndex];
		currentStep.actionBeforeFinish();
	}
	else{
		var currentStep=stepNodesArray[currentStepInfo.currentStepIndex];
		if(currentStep.forwardable){
			var result=true;
			result=currentStep.actionBeforeForward();
			if(result){
				currentStep.actionForForward();
				currentStepInfo.currentStepIndex+=1;
				jquery_progressBarOptions.setStepProgress();
			}
		}
	}
}

jquery_progressBarOptions.setStepProgress=function(){
	var currentStepInfo=jquery_progressBarOptions.currentStepInfo;
	var stepNodesArray=jquery_progressBarOptions.step_progressBarOptions.stepNodes;
	var currentStepIndex=currentStepInfo.currentStepIndex;
	var lastStepIndex=currentStepInfo.lastStepIndex;
	var stepCount=stepNodesArray.length;
	var progress=(100.0*(currentStepIndex+1))/stepCount+"%";
	var targetObject=jquery_progressBarOptions.targetObject;
	if(currentStepIndex==0){
		//不显示上一步按钮
		$(targetObject.backButton).css("display","none");
	}
	else{
		$(targetObject.backButton).css("display","block");
	}
	if(currentStepIndex==(stepCount-1)){
		//不显示下一步按钮
		$(targetObject.forwardButton).css("display","none");
		$(targetObject.finishButton).css("display","block");
	}
	else{
		$(targetObject.forwardButton).css("display","block");
		$(targetObject.finishButton).css("display","none");
	}
	
	if(currentStepIndex>=lastStepIndex){
		$(".step-bar-active").animate({width:progress},500,function(){
			for(var i=0;i<=currentStepIndex;i++){
				$($(".stepBarList li")[i]).addClass("step-active");
			}
		});	
	}
	else{
		$($(".stepBarList li")[currentStepIndex+1]).removeClass("step-active");
		$(".step-bar-active").animate({width:progress},500);
	}
	currentStepInfo.lastStepIndex=currentStepIndex;
}


jquery_progressBarOptions.setStepInfoConfig=function(stepInfoArray){
	for(var i in stepInfoArray){
		stepInfoArray[i]=jquery_progressBarOptions.createStepNode(stepInfoArray[i]);
	}
	jquery_progressBarOptions.step_progressBarOptions.stepNodes=stepInfoArray;
}


/*
 * targetObject:{
 * 	  fillArea://要填充分步操作条的位置
 * 	  backButton:上一步按钮
 *    forwardButton:下一步按钮	
 * }
 */
jquery_progressBarOptions.loadStepProgressBar=function(targetObject){
	jquery_progressBarOptions.targetObject=targetObject;
	var stepProgressBarInfoArray=new Array();
	var stepNodes=jquery_progressBarOptions.step_progressBarOptions.stepNodes;
	var currentStepIndex=0;
	if(stepNodes!=null&&stepNodes.length>0){
		stepProgressBarInfoArray.push("<div class='step-body'><div class='step-bar'><div class='step-bar-active'></div></div>");
		stepProgressBarInfoArray.push("<ul class='stepBarList'>");
		var stepLength=(100.0/stepNodes.length)+"%";
		$.each(stepNodes,function(index,element){
			stepProgressBarInfoArray.push("<li style='width:");
			stepProgressBarInfoArray.push(stepLength);
			stepProgressBarInfoArray.push("' ><p>");
			stepProgressBarInfoArray.push(element.stepName);
			stepProgressBarInfoArray.push("</p><span>");
			stepProgressBarInfoArray.push((index+1));
			stepProgressBarInfoArray.push("</span></li>");
			if(element.isCurrentStep){
				currentStepIndex=index;
			}
		});
		stepProgressBarInfoArray.push("</ul></div><div style='clear:both'></div>");
		$(targetObject.fillArea).append(stepProgressBarInfoArray.join(""));
		jquery_progressBarOptions.resetCurrentStep(currentStepIndex);
		jquery_progressBarOptions.setStepProgress();
		$(targetObject.backButton).click(function(){
			jquery_progressBarOptions.backStep();
		});
		$(targetObject.forwardButton).click(function(){
			jquery_progressBarOptions.forwardStep();
		});
		$(targetObject.finishButton).click(function(){
			jquery_progressBarOptions.forwardStep();
		});
	}
	
}


function loadPercentProgressBar(){
	
}