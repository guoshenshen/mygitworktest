var $authLoginTips=$('[data-remodal-id=authLoginTips]').remodal({
	hashTracking: false,
	closeOnOutsideClick: false
});
var $authLoginConfirm=$('[data-remodal-id=authLoginConfirm]').remodal({
	hashTracking: false,
	closeOnOutsideClick: false
});


var authLoginArea={
		condition:0 //0:认证信息异常;1:认证成功，但未在系统中检索到任何匹配账户;2:认证成功，但在系统中检索到多个匹配账户
}



$(function(){
	var $authLoginTipsDIV=$(".authLoginTips");
	var $authLoginConfirmDIV=$(".authLoginConfirm");
	var $abnormalInfo=$("#abnormalInfo");
	var actionAfterValidationParam={};
	var replaceUserInfoByAccount=function(){
		var email=$("input[name=email]",$abnormalInfo).val();
		var operatorName=$("input[name=operatorName]",$abnormalInfo).val();
		var params={};
		if($.trim(email)!=""){
			params.email=email;
		}
		if($.trim(operatorName)!=""){
			params.operatorName=operatorName;
		}
		$.ajax({
			type:"POST",
			data:params,
			dataType:"json",
			url:"authAction.do?method=replaceUserInfoByAccount",
			dataType:"json",
			success:function(response){
				$("button",$authLoginConfirmDIV).css("display","none");
				$(".tipInfo",$authLoginConfirmDIV).append("<p class='sucessTip'>信息替换成功!</p>");
			},
			error:function(respones){},
			complete:function(){
				setTimeout(function(){
					var data=actionAfterValidationParam.data;
					var param=actionAfterValidationParam.param;
					LoginProcess.prototype.actionAfterValidation(data,param);
				},500);
			}
		})
	}
	var AuthProcess=function(){};
	AuthProcess.prototype=new LoginProcess();
	AuthProcess.prototype.actionAfterValidation=function(data,param){
		var $selectedUser=$(".userinfo .box.selected");
		var authenId=$("input[name=authenId]",$abnormalInfo).val();
		var type=$("input[name=type]",$abnormalInfo).val();
		var key=data.key;
		if(key!=null){
			param.key=key;
		}
		var operatorId=$("input[name=operatorId]",$selectedUser).val();
		$.ajax({
			type:"POST",
			data:{"authenId":authenId,"type":type,"operatorId":operatorId},
			url:"authAction.do?method=accountBinding",
			dataType:"json",
			success:function(data1){
				if(data1.result=="true"){
					actionAfterValidationParam.data=data;
					actionAfterValidationParam.param=param;
					$authLoginConfirm.open();
				}
				else{
					LoginProcess.prototype.actionAfterValidation(data,param);
				}
			},
			error:function(data1){
				LoginProcess.prototype.actionAfterValidation(data,param);
			}
		})
	};
	LoginOptions.process=new AuthProcess();
	
	
	if($abnormalInfo.length>0){
		var authenId=$("input[name=authenId]",$abnormalInfo).val();
		var contentArray=new Array();
		if($.trim(authenId)!=""){
			$(".authLoginTips #abnormalInfo").css("display","block");
			if($("li.userinfo",$abnormalInfo).length>0){
				authLoginArea.condition=2;
				$(".tipInfo p",$authLoginTipsDIV).html("以下账户与验证通过的中国科技云账号匹配，请点击头像完成账户绑定");
				$(".remodal-confirm",$authLoginTipsDIV).html("认证绑定").css("display","inline-block");
				$(".remodal-cancel",$authLoginTipsDIV).html("取消").css("display","inline-block");
			}
			else{
				authLoginArea.condition=1;
				$(".tipInfo p",$authLoginTipsDIV).html("未发现CASMOOC账户与您的中国科技云账号匹配，请点击按钮完成账户绑定");
				$(".remodal-confirm",$authLoginTipsDIV).html("认证绑定").css("display","inline-block");
				$(".remodal-cancel",$authLoginTipsDIV).html("取消").css("display","inline-block");
			}
			
			LoginOptions.nextActionUrl="eosoperatorAction.do?method=logonByAuth";
			
		}
		else{
			authLoginArea.condition=0;
			var message=$("input[name=message]",$abnormalInfo).val();
			$(".tipInfo p",$authLoginTipsDIV).html(message);
			$(".remodal-confirm",$authLoginTipsDIV).html("登录").css("display","inline-block");
		}
		$authLoginTips.open();
	}
	
	$(".userinfo .box",$authLoginTipsDIV).click(function(){
		var $selectedUser=$(this);
		var type=$("input[name=type]",$abnormalInfo).val();
		var operatorId=$("input[name=operatorId]",$selectedUser).val();
		$.ajax({
			url:"authAction.do?method=ifAccountBinded",
			data:{"type":type,"operatorId":operatorId},
			type:"POST",
			dataType:"json",
			success:function(data){
				if(data.result=="true"){
					$(".box.selected",$authLoginTipsDIV).removeClass("selected");
					$selectedUser.addClass("selected");	
				}
				else{
					$(".tipInfo p",$authLoginTipsDIV).html(data.message);
				}
			}
		})
	})
	
	$(document).on('confirmation', '[data-remodal-id=authLoginTips]', function (){
		if(authLoginArea.condition==2){
			var $selectedUser=$(".userinfo .box.selected");
			if($selectedUser.length>0){
				var userId=$("input[name=userId]",$selectedUser).val();
				$("#J-login #userId").val(userId).attr("readonly","true");
				$("#J-login #password").val("");
				$authLoginTips.close();
			}
			else{
				$(".tipInfo p",$authLoginTipsDIV).html("您尚未指定要绑定的CASMOOC账户！");
			}
		}
		else{
			$authLoginTips.close();
		}
	});
	$(document).on('cancellation', '[data-remodal-id=authLoginTips]', function (){
		LoginOptions.process.actionAfterValidation=LoginProcess.prototype.actionAfterValidation;
	});
	$(".remodal-confirm",".authLoginConfirm").click(function(){
		replaceUserInfoByAccount();
	});
	$(document).on('cancellation', '[data-remodal-id=authLoginConfirm]', function (){
		var data=actionAfterValidationParam.data;
		var param=actionAfterValidationParam.param;
		LoginProcess.prototype.actionAfterValidation(data,param);
	});
	
})