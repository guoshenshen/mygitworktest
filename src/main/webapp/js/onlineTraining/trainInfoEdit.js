var alertInfoOption={
		alertTitle:'项目信息填报提示',
		confirmTitle:'',
		dialogStyle:'blueStyle'
}

var feeLevelMap={
		0:550,
        1:650,
        2:760,
        currentValue:550
}

//统计文本区域字数
function gbcount(target, superior_limit) {
	var max=400;
	if(superior_limit!=null){
		max=superior_limit;
	}
	if(target.val().length> max) {
		jAlert("内容不能超过 "+max+"个字!",alertInfoOption.alertTitle,function(){
			target.val(target.val().substring(0,max));
		}); 
	} 		
} 

function enrollTrain(){
	 $("#trainBasicInfoForm  .needForEnroll").css("display","");
}

function unEnrollTrain(){
	$("#trainBasicInfoForm  .needForEnroll").css("display","none");
	$("#trainBasicInfoForm  input[name=isNeedCheck]").each(function(){
		if($(this).val()=="0"){
			$(this).attr("checked","checked");
		}
	})
	$("#trainBasicInfoForm  #programStartTime").val($("#trainBasicInfoForm  #startTime").val());
	$("#trainBasicInfoForm  #programEndTime").val($("#trainBasicInfoForm  #endTime").val());
}

//根据培训对象级别获取预算经费上限
function changeFeeStandard(){
	function getFeeLevel(){
		var feeLevel=$("input[name=feeLevel]:checked").val();
		var feeStandard=feeLevelMap[$.trim(feeLevel)];
		feeLevelMap.currentValue=feeStandard;
		return feeStandard;
	};
	$("#_feeLevel").html(" &nbsp;*人均每日不超过"+getFeeLevel()+"元");
	$("input[name=feeLevel]").change(function(){
		var feeStandard=getFeeLevel();
		$("#_feeLevel").html(" &nbsp;*人均每日不超过"+feeStandard+"元");
	});
}


//gss使用
$(function(){

	//为提示框设置显示样式
	$.alerts.dialogClass=alertInfoOption.dialogStyle;
	
	//根据培训对象级别获取预算经费上限
	changeFeeStandard();
	
	$("#trainBasicInfoForm #backButton").click(function(){
		showBufferMask();
		window.location.href="../onlineTraining/searchTrain.do";
	});
	
	var submitVerify=insertOrUpdate();
	
	var $days=$("#trainBasicInfoForm #days");
	var $attendantCount=$("#trainBasicInfoForm #attendantCount");
	var $startTime=$("#trainBasicInfoForm #startTime");
	var $trainId=$("input[name=trainID]");
	var $fee=$("#trainBasicInfoForm  #fee");
	
	// 新增暂存
	$("#trainBasicInfoForm #addtempStorage").click(function(){
		submitVerify.tempSave(function(){
			$("#isReported").val("0");
			var $parentOrgId=$("input[name=parentOrgId]");
			if($parentOrgId.length>0){
				var params={
						"fee":$fee.val(),
						"days":$days.val(),
						"attendantCount":$attendantCount.val(),
						"trainId":$trainId.val(),
						"startTime":$startTime.val()
				}
				var $currentForm=$("#trainBasicInfoForm");
				$("input[name=executePlan]",$currentForm).val("false");
				$.updateTrainForm();
			}
			else{
				var $currentForm=$("#trainBasicInfoForm");
				$("input[name=executePlan]",$currentForm).val("false");
				$.updateTrainForm();

			}
			
		});
	});
	
	//gss使用--新增提交
	$("#trainBasicInfoForm #addsubmitStorage").click(function(){
		submitVerify.submitSave(function(){
			submitVerify.tempSave(function(){
				$("#isReported").val("1");
				var $parentOrgId=$("input[name=parentOrgId]");
				if($parentOrgId.length>0){
					var params={
							"fee":$fee.val(),
							"days":$days.val(),
							"attendantCount":$attendantCount.val(),
							"trainId":$trainId.val(),
							"startTime":$startTime.val()
					}
					var $currentForm=$("#trainBasicInfoForm");
					$("input[name=executePlan]",$currentForm).val("false");
					$.updateTrainForm();
				} else{
					var $currentForm=$("#trainBasicInfoForm");
					$("input[name=executePlan]",$currentForm).val("false");
					$.updateTrainForm();
	
				}
				
			});
		});
	});
	
	//修改提交
	$("#trainBasicInfoForm #submitStorage").click(function(){
		submitVerify.submitSave(function(){
			submitVerify.tempSave(function(){
				$("#isReported").val("1");
				var $parentOrgId=$("input[name=parentOrgId]");
				if($parentOrgId.length>0){
					var params={
							"fee":$fee.val(),
							"days":$days.val(),
							"attendantCount":$attendantCount.val(),
							"trainId":$trainId.val(),
							"startTime":$startTime.val()
					}
					var $currentForm=$("#trainBasicInfoForm");
					$("input[name=executePlan]",$currentForm).val("false");
					$.updateTrainForm();
				} else{
					var $currentForm=$("#trainBasicInfoForm");
					$("input[name=executePlan]",$currentForm).val("false");
					$.updateTrainForm();
	
				}
				
			});
		});
	});
	
	
	$("#trainBasicInfoForm #tempStorage").click(function(){
		submitVerify.tempSave(function(){
			var $parentOrgId=$("input[name=parentOrgId]");
			if($parentOrgId.length>0){
				var params={
						"fee":$fee.val(),
						"days":$days.val(),
						"attendantCount":$attendantCount.val(),
						"trainId":$trainId.val(),
						"startTime":$startTime.val()
				}
				var $currentForm=$("#trainBasicInfoForm");
				$("input[name=executePlan]",$currentForm).val("false");
				$.updateTrainForm();
			}
			else{

				var $currentForm=$("#trainBasicInfoForm");
				$("input[name=executePlan]",$currentForm).val("false");
				$.updateTrainForm();

			}
			
		});
	});
	
	$("#trainBasicInfoForm #submitTrainInfo").click(function(){
		submitVerify.submitSave(function(){
			submitVerify.tempSave(function(){
				var $parentOrgId=$("input[name=parentOrgId]");
				if($parentOrgId.length>0){
					var params={
							"fee":$fee.val(),
							"days":$days.val(),
							"attendantCount":$attendantCount.val(),
							"trainId":$trainId.val(),
							"startTime":$startTime.val()
					}
					$.upReportTrain(params,function(){
						var $currentForm=$("#trainBasicInfoForm");
						$("input[name=executePlan]",$currentForm).val("true");
						$.updateTrainForm();
					});
				} else{
					var $currentForm=$("#trainBasicInfoForm");
					$("input[name=executePlan]",$currentForm).val("true");
					$.updateTrainForm();
				}
			})
			
		});
	});
	
	$("#trainBasicInfoForm .areaInput").each(function(){
		$(this).keyup(function(){
			gbcount($(this),300);
		});
	})
	
	$("#trainBasicInfoForm #keyWordsTag").click(function(){
		$(this).keyup(function(){
			gbcount($(this),50);
		});
	})
            
	$("input[name=classHour]").blur(function(){
		var time=$(this).val();
    	if(time<1){
    	//培训学时不得少于1个学时
    		jAlert("学时不得少于1小时",alertInfoOption.alertTitle,function(){
    			$(this).val(4.0);
    		});
    	}
	});
    $("input[name=ifBJ]").click(function(){
    	if($(this).val()=="1"){
    		//选择外埠，具体地址为必填项

    		$("#_location").css({"display":"none"});
    	}
    	else{
    		//选择本地的，具体地址不是 必填项
    		$("#_location").css({"display":"inline"});
    	}
    });
    $("input[name=casSupport]").click(function(){
    	if($(this).val()=="0"){
    		$("input[name=isNoted]:first").prop("checked",true);
    		$("#_isNoted").css({"display":"none"});
    	}
    	else{
    		$("input[name=isNoted]:last").prop("checked",true);
    		$("#_isNoted").css({"display":"inline"});
    	}
    });
    
     $("input[name=isNoted]").click(function(){
    	if($(this).val()=="0"){
    		$("input[name=casSupport]:first").prop("checked",true);
    		$("#_isNoted").css({"display":"none"});
    	}
    	else{
    		$("input[name=casSupport]:last").prop("checked",true);
    		$("#_isNoted").css({"display":"inline"});
    	}
    });

    //绑定事件到是否允许报名按钮
    var currentEnrollStatus= $("#trainBasicInfoForm input[name=isEnrolled]:checked").val();
    if(currentEnrollStatus=="0"){
    	unEnrollTrain();
    } 
    else{
    	enrollTrain();
    }
    
    $("#trainBasicInfoForm input[name=isEnrolled]").each(function(){
    	if($(this).val()==1){
    		$(this).click(function(){
    			enrollTrain();
    		});
    	}
    	else{
    		$(this).click(function(){
    			unEnrollTrain();
    		})
    	}
    })
    
    
    
    
if($(".trainEditor").length>0){
		$.extend({
			getUpreportOrg:function(params){
				var alreadyUpreportArray=new Array();
				$(".trainEditor input[name=alreadyUpreport]").each(function(index,that){
					alreadyUpreportArray.push(parseInt(that.value));
				});

				$.ajax({
					url:"../orgPlan/getUpreportOrgForTrain.do",
					type:"post",
					data:params,
					dataType:"json",
					success:function(data){
						if(data.status){
							var mustUp=data.data.mustUp;
							var chooseUp=data.data.chooseUp;
							var mustUpLength=mustUp.length;
							var chooseUpLength=chooseUp.length;
							var upArray=new Array();
							var recommendArray=new Array();
							if(mustUpLength>0){
								for(var i=0;i<mustUpLength;i++){
									var org=mustUp[i];
									upArray.push("<input type='checkbox' checked disabled name='parentOrgId' value='"+org.orgId+"' /><input type='hidden' name='parentOrgId' value='"+org.orgId+"' />"+org.orgName);
								}
							}
							if(chooseUpLength>0){
								for(var j=0;j<chooseUpLength;j++){
									var org=chooseUp[j];
									for(var i=0;i<chooseUpLength;i++){
										var org=chooseUp[i];
										upArray.push("<input type='checkbox' name='parentOrgId' ")
										if($.inArray(org.orgId,alreadyUpreportArray)>=0){
											upArray.push(" checked ");
										}
										upArray.push(" value='"+org.orgId+"' />"+org.orgName);
									}
								}
							}
							if(upArray.length!=0){
								$("#trainBasicInfoForm .upReportOrgInfo").html(upArray.join(""));
								$("#trainBasicInfoForm .upReportOrgInfoContainer").css("display","block");
							}
						}
					}

				});

			},

			upReportTrain:function(params,actionAfterSuccessDealed){
				/*
					$.ajax({
						url:"resourceApproveAction.do?method=needApproveForTrain",
						type:"POST",
						data:params,
						dataType:"json",
						success:function(data){
							if(data.needReport){
								var tips=data.tips;
								var tipsLength=tips.length;
								var tipArray=new Array();
								tipArray.push("<p style='font-size:10px;'>");
								for(var i=0;i<tipsLength;i++){
									if(i!=0){
										tipArray.push(",");
									}
									tipArray.push(tips[i]);
									tipArray.push("<br/><br/>以上调增信息需经由上级管理员审核通过才可继续实施计划,是否提交培训修改信息？</p>");
								}

								$.Nconfirm({
									'content':tipArray.join(""),
									'onConfirm':function(){
										$.generateApproveFormForTrain(actionAfterSuccessDealed);
									},
									'onDeny':function(){

									}
								})
							}
							else{
								if(typeof actionAfterSuccessDealed=="function"){
									actionAfterSuccessDealed();
								}
							}
						}

					})
				*/
				$.generateApproveFormForTrain(actionAfterSuccessDealed);
			},
				
		
			updateTrainForm:function(){
				var $currentForm=$("#trainBasicInfoForm");
				if($("#trainId").val()!=null) {
					$currentForm.attr("action","../onlineTraining/update.do");
				}
				showBufferMask();
				$currentForm.submit();
			},

			generateApproveFormForTrain:function(actionAfterApproveSaved){

				var $parentOrgId=$("input[name=parentOrgId]");
				var $trainId=$("input[name=trainID]");

				$.ajax({
					url:"resourceApproveAction.do?method=generateApproveFormForTrain",
					type:"POST",
					data:{
						"parentOrgId":$parentOrgId.val(),
						"trainId":$trainId.val()
					},
					dataType:"json",
					success:function(data){
						if(data.status){
							if(typeof actionAfterApproveSaved=="function"){
								actionAfterApproveSaved();
							}
						}
						else{
							$.Ntip({
								"content":data.cause
							});
						}
					},
					error:function(){
						$.Ntip({
							"content":"培训审核报表生成异常！"
						});
					}

				})
			}
		});

		$.getUpreportOrg({
			//"planType":$("#planType").val()
		});
	}

});



function insertOrUpdate(){
	var $trainName=$("#trainBasicInfoForm #trainName");
	var $year=$("#trainBasicInfoForm #year");
	var $trainTypeId=$("#trainBasicInfoForm #trainTypeId");
	var $classHour=$("#trainBasicInfoForm #classHour");
	var $days=$("#trainBasicInfoForm #days");
	var $attendantCount=$("#trainBasicInfoForm #attendantCount");
	var $researchPostNum=$("#trainBasicInfoForm #researchPostNum");
	var $managePostNum=$("#trainBasicInfoForm #managePostNum");
	var $supportPostNum=$("#trainBasicInfoForm #supportPostNum");
	var $outSideNum=$("#trainBasicInfoForm #outSideNum");
	var $workerNum=$("#trainBasicInfoForm #workerNum");
	var $ifBJ=null;
	var $location=$("input[name=location]");
	var $startTime=$("#trainBasicInfoForm #startTime");
	var $endTime=$("#trainBasicInfoForm #endTime");
	var $feeChannel=$("#trainBasicInfoForm #feeChannel");
	var $fee=$("#trainBasicInfoForm  #fee");
	var $stationId=null;
	var $attendants=$("#trainBasicInfoForm textarea[name=attendants]");
	var $trainGoal=$("#trainBasicInfoForm textarea[name=trainGoal]");
	var $trainingContent=$("#trainBasicInfoForm textarea[name=trainingContent]");
	var $keyWordsTag=$("#trainBasicInfoForm #keyWordsTag");
	var $_orgsname=$("#trainBasicInfoForm #_orgsname");
	var $organizerName=$("#trainBasicInfoForm #organizerName");
	var $isEnrolled=null;
	var $isNeedCheck=null;
	var $telephone=$("#trainBasicInfoForm #telephone");
	var $organizerEmail=$("#trainBasicInfoForm #organizerEmail");
	
	return {
		"tempSave":function(actionAfterVerify){
			$isEnrolled=$("#trainBasicInfoForm input[name=isEnrolled]:checked");
			$isNeedCheck=$("#trainBasicInfoForm input[name=isNeedCheck]:checked");
			$ifBJ=$("#trainBasicInfoForm input[name=ifBJ]:checked");
			$stationId=$("#trainBasicInfoForm input[name=stationId]:checked");
			var classHour=$.trim($classHour.val());
			var days=$.trim($days.val());
			var attendantCount=$.trim($attendantCount.val());
			var researchPostNum=$.trim($researchPostNum.val());
			var managePostNum=$.trim($managePostNum.val());
			var supportPostNum=$.trim($supportPostNum.val());
			var outSideNum=$.trim($outSideNum.val());
			var workerNum=$.trim($workerNum.val());
			var fee=$.trim($fee.val());
			var attendants=$.trim($attendants.val());
			var trainGoal=$.trim($trainGoal.val());
			var trainingContent=$.trim($trainingContent.val());
			var telephone=$.trim($telephone.val());
			var oemail=$organizerEmail.val();
			if($.trim($trainName.val())==""){
				jAlert("培训名称不能为空!",alertInfoOption.alertTitle,function(){
					$trainName.focus();
				});
				return;
			}
			if($.trim($year.val())== "") {
				jAlert("培训年度不能为空!",alertInfoOption.alertTitle,function(){
					$year.focus();
				});
				return;
			}
			if(classHour!= ""&&!isPositiveNum(classHour)){
				jAlert("您的培训学时数目填写有误,请重新填写!",alertInfoOption.alertTitle,function(){
					$classHour.focus();	
				});
				return;
			}
			if(days!=""&&!isPositiveNum(days)){
		   		jAlert("您的培训天数填写有误,请重新填写!",alertInfoOption.alertTitle,function(){
					$days.focus();
				});
		   		return;
		   	}
			
			if(attendantCount!=""&&!isPositiveIntegerNum(attendantCount)){
				jAlert("参加人数填写有误,请重新填写!",alertInfoOption.alertTitle,function(){
					$attendantCount.focus();	
				});
				return;
			}
			/*if(researchPostNum!=""&&!isNonNegativeInteger(researchPostNum)){
				jAlert("科研岗人数填写有误,请重新填写!",alertInfoOption.alertTitle,function(){
					$researchPostNum.focus();
				});
				return;
			}
			if(managePostNum!=""&&!isNonNegativeInteger(managePostNum)){
				jAlert("管理岗人数填写有误,请重新填写!",alertInfoOption.alertTitle,function(){
					$managePostNum.focus();	
				});
				return;
			}
			if(supportPostNum!=""&&!isNonNegativeInteger(supportPostNum)){
				jAlert("支撑岗人数填写有误,请重新填写!",alertInfoOption.alertTitle,function(){
					$supportPostNum.focus();	
				});
				return;
			}
			if(outSideNum!=""&&!isNonNegativeInteger(outSideNum)){
				jAlert("院外人数填写有误,请重新填写!",alertInfoOption.alertTitle,function(){
					$outSideNum.focus();		
				});
				return;
			}*/
			if(workerNum!=""&&!isNonNegativeInteger(workerNum)){
				jAlert("工作人数填写有误,请重新填写!",alertInfoOption.alertTitle,function(){
					$workerNum.focus();		
				});
				return;
			}
			if($.trim($startTime.val())!=""&&$.trim($endTime.val())!=""&&compare_date($startTime.val(),$endTime.val())){
		    	jAlert("培训结束时间应当晚于培训开始时间!",alertInfoOption.alertTitle,function(){
		    		$endTime.focus();
		    	});
		    	return;
		    }
			if(fee!=""&&!isNonNegativeNum(fee)){
				jAlert("经费开支不得小于0!",alertInfoOption.alertTitle,function(){
					$fee.focus();
				});  
				return;
			}
			if(attendants!=""&&(attendants.length==0||attendants.length>300)){
				jAlert("请填写培训对象,字数不超过300",alertInfoOption.alertTitle,function(){
					$attendants.focus();
				});
				return;
			}
			if(trainGoal!=""&&(trainGoal.length<10||trainGoal.length>300)){
				jAlert("请填写培训目的,字数10-300",alertInfoOption.alertTitle,function(){
					$trainGoal.focus();
				});
				return;
			}
			if(trainingContent!=""&&(trainingContent<10||trainingContent.length>300)){
				jAlert("请填写培训内容,字数10-300",alertInfoOption.alertTitle,function(){
					$trainingContent.focus();	
				});
				return;	
			}
			if(oemail!=""&&!legalEmail(oemail)){
				jAlert("请输入正确的邮箱地址!", alertInfoOption.alertTitle,function(){
					$organizerEmail.val("")
					$organizerEmail.focus();
				});
				return;
			} 
			if(telephone!=""&&(!checkTel(telephone)&&!checkMobile(telephone))){
		    	jAlert("请输入正确的手机号码或座机号码,座机请加区号如：021-!", alertInfoOption.alertTitle,function(){
		    		$telephone.focus();
		    		
		    	});	
		    	return;
			}	
			//如果允许报名
			if($isEnrolled.length>0&&$isEnrolled.val()==1){
				var $programStartTime=$("#trainBasicInfoForm #programStartTime");
				var $programEndTime=$("#trainBasicInfoForm #programEndTime");
		   		if($.trim($programStartTime.val()) == "") {
		   			jAlert("培训报名开始时间不能为空!",alertInfoOption.alertTitle,function(){
		   				$programStartTime.focus();
		   			});
		   			return;
		   		}
		   		if($.trim($programEndTime.val()) == "") {
		   			jAlert("培训报名开始时间不能为空!",alertInfoOption.alertTitle,function(){
		   				$programEndTime.focus();
		   			});
		   			return;
		   		}	
		   		if(compare_date($programStartTime.val(),$programEndTime.val())||$programStartTime.val()==$programEndTime.val()){
		    		jAlert("培训报名开始时间不能等于或晚于培训报名结束时间!",alertInfoOption.alertTitle,function(){
		   				$programEndTime.focus();
		   			});
		    		return;
		    	}
		    	if(compare_date($programEndTime.val(),$endTime.val())){
		    		jAlert("培训报名结束时间不得晚于培训结束时间",alertInfoOption.alertTitle,function(){
		   				$programEndTime.focus();
		   			});
		    		return;
		    	}
		    	if($isNeedCheck.length==0){
		    		jAlert("请确定报名是否需要审批!",alertInfoOption.alertTitle,function(){
		    		});
		    		return;
		    	}
			}  
			if(typeof actionAfterVerify=="function"){
				actionAfterVerify();
			}
		},
		"submitSave":function(actionAfterVerify){
			$isEnrolled=$("#trainBasicInfoForm input[name=isEnrolled]:checked");
			$isNeedCheck=$("#trainBasicInfoForm input[name=isNeedCheck]:checked");
			$ifBJ=$("#trainBasicInfoForm input[name=ifBJ]:checked");
			$stationId=$("#trainBasicInfoForm input[name=stationId]:checked");
			trainWay=$("#trainBasicInfoForm input[name=trainWay]:checked");
			var classHour=$.trim($classHour.val());
			var days=$.trim($days.val());
			var attendantCount=$.trim($attendantCount.val());
			var researchPostNum=$.trim($researchPostNum.val());
			var managePostNum=$.trim($managePostNum.val());
			var supportPostNum=$.trim($supportPostNum.val());
			var outSideNum=$.trim($outSideNum.val());
			var workerNum=$.trim($workerNum.val());
			var fee=$.trim($fee.val());
			var attendants=$.trim($attendants.val());
			var trainGoal=$.trim($trainGoal.val());
			var trainingContent=$.trim($trainingContent.val());
			var telephone=$.trim($telephone.val());
			var oemail=$organizerEmail.val();
			if($.trim($trainTypeId.val())=="-1"){
				jAlert("请选择培训类别!",alertInfoOption.alertTitle,function(){});
				return;
			}
			if(classHour== "") {
				jAlert("学时不能为空!",alertInfoOption.alertTitle,function(){
					$classHour.focus();		
				});
				return;
			}
			if(days=="") {
				jAlert("培训天数不能为空!",alertInfoOption.alertTitle,function(){
					$days.focus();
				});
				return;
			}
			if($.trim($startTime.val())== "") {
				jAlert("培训开始时间不能为空!",alertInfoOption.alertTitle,function(){
					$startTime.focus();		
				});
				return;
			}
			
			if($.trim($endTime.val())== "") {
				jAlert("培训结束时间不能为空!",alertInfoOption.alertTitle,function(){
					$endTime.focus();
				});
				return;
			}
			var minHour=null;
			var maxHour=null;
			if(days<=1){
				minHour=0;
				maxHour=days*8;
			}
			if(days>1&&days<3){
				minHour=(days-1)*8;
				maxHour=days*8;
			} else if(days>=3){
				minHour=(days-2)*8;
				maxHour=days*8;
			}
			if(trainWay.val()!=0){
				if(classHour<minHour||classHour>maxHour){
					jAlert("请确保每日学时控制在8小时内，培训天数在1-2天的,除一天报到撤离时间外，剩余时间每天学时需达到8小时;培训天数为3天及以上的，除两天报道撤离时间外，剩余时间每天学时需达到8小时", alertInfoOption.alertTitle);
					return false;
				}
			}	
			if(attendantCount== "") {
				jAlert("参加人数不能为空!",alertInfoOption.alertTitle,function(){
					$attendantCount.focus();
				});
				return;
			}
			/*if(researchPostNum== ""){
				jAlert("科研岗人数不能为空!",alertInfoOption.alertTitle,function(){
					$researchPostNum.focus();		
				});
				return;
			}
			if(managePostNum== ""){
				jAlert("管理岗人数不能为空!",alertInfoOption.alertTitle,function(){
					$managePostNum.focus();
					
				});
				return;
			}
			if(supportPostNum == ""){
				jAlert("支撑岗人数不能为空!",alertInfoOption.alertTitle,function(){
					$supportPostNum.focus();	
				});
				return;
			}
			if(outSideNum== ""){
				jAlert("院外人员数不能为空!",alertInfoOption.alertTitle,function(){
					$outSideNum.focus();		
				});
				return;
			}*/
			/*if(workerNum == ""){
				jAlert("工作人数不能为空!",alertInfoOption.alertTitle,function(){
					$workerNum.focus();		
				});
				return;
			}*/
			if($workerNum.val()>10||$workerNum.val()>$attendantCount.val()/10){
				jAlert("工作人员数不能超过10人，且不能超过学员计划参加人数的10%!",alertInfoOption.alertTitle,function(){
					$workerNum.focus();		
				});	
				return;
			}
			/*if(attendantCount<parseInt(researchPostNum)+parseInt(managePostNum)+parseInt(supportPostNum)){
				jAlert("科研岗人数、管理岗人数、支撑岗人数之和已超过计划参加人数,请重新填写计划参加人数",alertInfoOption,function(){
					$attendantCount.focus();
				});
				return;
			}*/
			if($ifBJ.length==0){
				jAlert("请选择培训地点为本地或者外埠!",alertInfoOption.alertTitle,function(){		
				});
				return;
			}
			if($ifBJ.val()=="0"&&$location.val()==""){
				jAlert("选择外埠的,具体地点不得为空!",alertInfoOption.alertTitle,function(){
					$location.focus();	
				});
				return;
			}
			if($.trim($feeChannel.val()) == "") {
				jAlert("列支渠道不能为空!",alertInfoOption.alertTitle,function(){
					$feeChannel.focus();
				});
				return;
			}
			if(fee== "") {
				jAlert("所需经费不能为空!",alertInfoOption.alertTitle,function(){
					$fee.focus();	
				});
				return;
			}
			var feeStandard=feeLevelMap.currentValue;
			if(parseInt(attendantCount)*(0.0001+feeStandard)*parseFloat($days.val())<parseFloat(fee)*10000){
				jAlert("超出人均每日最高培训经费"+feeStandard+"元标准!",alertInfoOption.alertTitle,function(){
					$fee.focus();	
				});  
				return;
			}
			if($stationId.length<1){
				jAlert("请选择适用岗位",alertInfoOption.alertTitle,function(){
					
				});
				return;
			}	
			if(attendants==""){
				jAlert("请填写培训对象,字数不超过300",alertInfoOption.alertTitle,function(){
					$attendants.focus();
				});
				return;
			}
			if(trainGoal==""){
				jAlert("请填写培训目的,字数10-300",alertInfoOption.alertTitle,function(){
					$trainGoal.focus();
				});
				return;
			}
			if(trainingContent==""){
				jAlert("请填写培训内容,字数10-300",alertInfoOption.alertTitle,function(){
					$trainingContent.focus();	
				});
				return;	
			}
			if($.trim($_orgsname.val())== "") {
				jAlert("主办单位不能为空!",alertInfoOption.alertTitle,function(){
					$_orgsname.focus();
					
				});
				return;
			}	
			if($.trim($organizerName.val())== "") {
				jAlert("联系人不能为空!",alertInfoOption.alertTitle,function(){
					$organizerName.focus();
					
				});
				return;
			}		
			if(oemail==""){
				jAlert("联系人Email不能为空!", alertInfoOption.alertTitle,function(){
					$organizerEmail.focus();
					
				});
				return;
			}
			if(telephone=="") {
				jAlert("请输入正确的电话号码z!", alertInfoOption.alertTitle,function(){
					$telephone.focus();
				});
				return;
			}	
			if($isEnrolled.length==0){
				jAlert("请选择是否允许报名!",alertInfoOption.alertTitle,function(){
					
				});
				return;
			}
			if(typeof actionAfterVerify=="function"){
				actionAfterVerify();
			}
		}
	};
	
	
	
	
}

