var LoginProcess=function(){
	
}


//系统登录(引用此页面不需要配合引用css/login/login.css)
//请在本js使用前，以任意方式确保JSCommonTools已经加载完毕
var LoginOptions={
	LoginMaskClass:['popmask','fadeIn','animated'],
	originPart:['.pobbox-close','#J-username','#J-password',"#J-submit"],
	unOriginPart:['#loginUserInfo'],
	params:{},
	nextActionUrl:"",
	submited:false,
	qrTimer:null,
	process:new LoginProcess(),
	staticServerPath:'.'
}



//显示登录框背景遮罩
function loadLoginMask(){
	var maskStr="<div id='J_popmask' class='' ></div>";
	$("body").append(maskStr);
	for(var i in LoginOptions.LoginMaskClass){
		$("body #J_popmask").addClass(LoginOptions.LoginMaskClass[i]);
	}
}

//显示登录项界面
function loadLoginBase(){
	//var loginInfo=menuLoadingOptions.loginInfo;
	//$.loadStructureHTML("htmlStructure/login.html","J-login",bindActionForLogin);

	$.loadStructureHTMLByJS("/htmlStructure/loginStructure.js","getLoginScript()","J-login",bindActionForLogin);
}

function bindActionForLogin($loginInfo){
	$("body").append($loginInfo);
	var loginProcess=LoginOptions.process;
	//为登录窗口绑定关闭函数
	$("body").on("click","#J-login .pobbox-close",function(){
		clearTimeout(LoginOptions.qrTimer);
		loginProcess.closeLoginWin();
	})
	
	//为登录按钮绑定校验函数
	$("body").on("click","#J-login #J-login-btn",function(){
		loginProcess.verifyLogin();
	})
	
	
	$("body").on("keydown","#J-login",function(ev){
		if(ev.keyCode==13){ 
			loginProcess.verifyLogin();
		 }
	})
	
	$("body").on("click","#verifyCodeImg",function(){
		loginProcess.refreshVerifyCode();
	})
	
	
	$("body").on("click",".changeLoginStatus",function(){
		$("#loginWay").click();
	})
	
	$("body").on("click","#loginWay",function(){
		var $JLogin=$("#J-login");
		if($JLogin.hasClass("passwordLoginStatus")){
			loginProcess.refreshQRCode();
			$(this).attr("title","返回密码登录");
			$JLogin.removeClass("passwordLoginStatus").addClass("qrLoginStatus");
		}
		else{
			clearTimeout(LoginOptions.qrTimer);
			$(this).attr("title","扫描登录");
			$JLogin.removeClass("qrLoginStatus").addClass("passwordLoginStatus");
		}
	})
	
	
	$("body").on("click",".refresh",function(){
		loginProcess.refreshQRCode();
	})

	$("body").on("click",".logonAction",function(){
		loginProcess.showLoginWin();
	})
	
	$("body").on("click",".escienceLogin",function(){
		window.location.href="https://passport.escience.cn/oauth2/authorize?response_type=code&redirect_uri=http://www.casmooc.cn/eosoperatorAction.do?method=ESCallback&client_id=72348&theme=full";
	})
}

LoginProcess.prototype.refreshVerifyCode=function(){
	
	$("#verifyCodeImg").hide().attr("src","/image/banner-load1.gif");
	setTimeout(function(){
		$("#verifyCodeImg").attr("src","/pub/verifyCode.jsp?version="+Math.random()).show();
	}, 500);
}


LoginProcess.prototype.checkQRCode=function(randNum){
	var $process=this;
	var params={"loginType":"qrLogin","randNum":randNum};
	clearTimeout(LoginOptions.qrTimer);
	$.ajax({
		url:'/login/validate',
		type:"POST",
		dataType:"json",
		data:params,
		success:function(data){
			if(data.result=="true"){
				if(data.ischecked=="true"){
					//已通过扫码流程
					$process.actionAfterValidation(data,params);
				}
				else{
					if($.trim(data.message)!=""){
						$("body #J-login #loginUserInfo .login_info").empty().html(data.message);
					}	
					else{
						//未通过扫码流程
						//二维码已失效
						LoginOptions.qrTimer=setTimeout(function(){$process.checkQRCode(randNum)},2000);
					}
				}
			}
			else{
				//二维码已失效
				$(".qrcode-mod").addClass("needRefresh");
			}
		},
		error:function(data){
			//二维码是否有效获取失败
		}
	})
}


//生成登录二维码
LoginProcess.prototype.refreshQRCode=function(){
	var $process=this;
	$.ajax({
		url:'/login/generateQR',
		type:"POST",
		dataType:"json",
		success:function(data){
			if(data.result=="true"){
				var randNum=data.randNum;
				var params={"contentInfo":"http://www.casmooc.cn?type=login&randNum="+randNum};
				$("#J_QRCodeImg img").qrCodeImg(params);
				$(".needRefresh").removeClass("needRefresh");
				$process.checkQRCode(randNum);
			}
			else{
				
			}
		},
		error:function(){
			
		}
		
	})
}



//登录基本信息校验
LoginProcess.prototype.verifyLogin=function(){
	var $process=this;
	var $userId=$("body #J-login #userId");
    var userId=$userId.val();
	var $password=$("body #J-login #password");
	var password=$password.val();
	var $verifyCode=$("body #J-login #verifyCode");
	var verifyCode=$verifyCode.val();
	var result=true;
	var resultInfo="";
	if($.trim(userId).length==0){
		result=false;
		$userId.focus();
		resultInfo="请输入登录账号";
	}
	else if($.trim(password).length==0){
		result=false;
		$password.focus();
		resultInfo="请输入登录密码";
	}
	else if($.trim(verifyCode).length==0){
		result=false;
		$verifyCode.focus();
		resultInfo="请输入验证码";
	}
	else{
		$.ajax({
	        url:'/pub/randCheck.jsp',
	        type:'post',
	        data:{rand:$.trim(verifyCode)},	
	        dataType:'text',
	        async:false,
	        success:function(data,evt){
	        	var $dataDOM= $($.parseHTML(data));
	        	var verifyResult = $dataDOM.find("#_randTips").val();
	        	if(verifyResult.length == 0)
	            {
	        	   resultInfo="请输入验证码";
	        	   $verifyCode.focus();
	               result=false;
	            }else if(verifyResult=="false"){
	               resultInfo="验证码填写有误，请重新输入！";
	               $process.refreshVerifyCode();
	               $verifyCode.focus();
	               result=false;
	            } 
	            else{
	            	resultInfo="登录验证中,请您稍后...";
	            }
	        },
	        complete:function(){
	        	$("body #J-login .login_info").empty().html(resultInfo).css("display","block");
	    		if(result==false){
	    			return result;
	    		}
	    		else{
	    			$process.submitLogin($.trim(userId),password,verifyCode);
	    		}
	        }
	     })
	}
	$("body #J-login .login_info").empty().html(resultInfo).css("display","block");
	return result;
}



//认证通过后,根据后台反馈的json格式信息进行页面跳转操作
LoginProcess.prototype.actionAfterValidation=function(info,param){
	$("#J-login .pobbox-close").hide();
	$("body #J-login #loginUserInfo .login_info").empty().css("display","block").html(info.msg);
	var url="";
	var data=info.data;
	if(data.openUrl!=null&&$.trim(data.openUrl).length>0){
		url=data.openUrl+"/"+data.basicUrl;
	}
	else{
		url=data.basicUrl;
	} 
	if($.trim(LoginOptions.nextActionUrl)!=""){
		param.nextAction=LoginOptions.nextActionUrl;
	}
	
	if(data.headPic!=null&&$.trim(data.headPic).length>0){
		var headPic="<img src='"+data.headPic+"' />"; 
		$("#J-login #loginUserInfo .userHead").append(headPic);
		$("#J-login #loginUserInfo .userHead img").error(function(){
			if(data.gender=="1"){
				$(this).attr('src', '/image/headPic/male1.jpg');
			} else{
				$(this).attr('src', '/image/headPic/female1.jpg');
			} 
		});
	}
	else{
		
		if(!$.support.leadingWhitespace){
				$("#J-login #loginUserInfo .userHead").css("background","url(/image/banner-load.gif) no-repeat").css("filter","alpha(opacity=100)").css("background-position-x","50%").css("background-position-y","50%");
		}
		else{
			try{
				$("#J-login #loginUserInfo .userHead").shCircleLoader({
				    color: '#f00'
				});
			}
			catch(e){
				$.getScript("/js/UI/jquery.shCircleLoader.js",function(response,status){
					try{
						$("#J-login #loginUserInfo .userHead").shCircleLoader({
						    color: '#f00'
						});
					}	
					catch(e){
						$("#J-login #loginUserInfo .userHead").css("background","url(/image/banner-load.gif) no-repeat").css("filter","alpha(opacity=100)").css("background-position-x","50%").css("background-position-y","50%");
					}
				})
				
			}						
		}
	}
	$("#J-login .noNeedAfterValidate").css("display","none");
	$("#J-login #loginUserInfo").fadeIn();
	if(data.userName!=null&&$.trim(data.userName).length>0){
		var greeting="您好";
		var currentHour=new Date().getHours();
		if(currentHour>=6&&currentHour<9){
			greeting="早上好";
		}
		else if(currentHour>=9&&currentHour<12){
			greeting="上午好";
		}
		else if(currentHour>=12&&currentHour<14){
			greeting="中午好";
		}
		else if(currentHour>=14&&currentHour<18){
			greeting="下午好";
		}
		else if(currentHour>=18&&currentHour<24){
			greeting="晚上好";
		}
		else{}
		$("#J-login #loginUserInfo .userInfo").empty().html(greeting+"！"+data.userName);
	}
	setTimeout(function(){
			openLink(url,param);
		}, 1500);
}

LoginProcess.prototype.submitLogin=function(userId,password,verifyCode){
	var $process=this;
	if(LoginOptions.submited);
	else{
		LoginOptions.submited=true;
		var param={'userId':userId,"password":password,'verifyCode':verifyCode};
		var options={
			type:"POST",
			data:param,
			url:"/login/validate",
			dataType:"json",
			success:function(data){
				if(data.success){
					$process.actionAfterValidation(data,param);
				}
				else{
					LoginOptions.submited=false;
					$("body #J-login .login_info").empty().html(data.msg);
				}
			}
		}
		$.ajax(options);
	}	
}


LoginProcess.prototype.showLoginWin=function(){
	LoginOptions.submited=false;
	$("#verifyCodeImg").attr("src","/pub/verifyCode.jsp?version="+Math.random());
	$("body #J-login").fadeIn(500);
	$("body #J_popmask").css("display","block");
	$("body #J-login .origin").show();
	$("body #J-login .unOrigin").hide();
	if(typeof loginData=="undefined");
	else{
		$.extend(LoginOptions,loginData);
	}

}

function showLoginWin(){
	LoginProcess.prototype.showLoginWin();
}

LoginProcess.prototype.closeLoginWin=function(){
	var $process=this;
	$("body #J-login").fadeOut(500,function(){
		$("#J-login").attr("title","扫描登录").removeClass("qrLoginStatus").addClass("passwordLoginStatus");
		$(".needRefresh").removeClass("needRefresh");
	});
	$("body #J_popmask").css("display","none");
	$("body #J-login .login_info").empty();
	$process.resetTargetUrlAfterLogon("");
}

function resetTargetUrlAfterLogon(url,params){
	LoginProcess.prototype.resetTargetUrlAfterLogon(url,params);
}


LoginProcess.prototype.resetTargetUrlAfterLogon=function(url,params){
	LoginOptions.nextActionUrl=url;
	LoginOptions.params=params;
}

function initializeLogonInfo(){
	if($("#J-login").length==0){
		$("head").append('<link href="/css/login/login.css" rel="stylesheet" type="text/css" />');//'+LoginOptions.staticServerPath+'
		loadLoginMask();
		loadLoginBase();
	}
	
	if(!(typeof $.fn.qrcode=="function")){
		$.getScript("/js/jquery.qrcode.min.js",function(){
			
		});
	}
	if(typeof loginData=="undefined");
	else{
		$.extend(LoginOptions,loginData);
	}
}

$(function(){	
	initializeLogonInfo();
})


