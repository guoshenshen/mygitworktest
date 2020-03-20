var loginScript="";
function getLoginScript(){
	
	if(loginScript==""){
		var loginScriptArray=new Array();
		loginScriptArray.push('<div>');
loginScriptArray.push('<div id="J-login" class="login login-modern loginbox passwordLoginStatus" >');
	loginScriptArray.push('<div id="loginMask"></div>');
	loginScriptArray.push('<a href="javascript:void(0);" class="pobbox-close origin" smarttracker="on">x</a>');
	loginScriptArray.push('<a href="javascript:void(0);" class="loginWay noNeedAfterValidate" id="loginWay" title="扫码登录"></a>');
	loginScriptArray.push('<div id="loginUserInfo" class="unOrigin" style="display: none;">');
		loginScriptArray.push('<h2 id="J-login-title" class="ui-form-title">欢迎登录科协系统智能学习平台</h2>');
		loginScriptArray.push('<div class="userHead"></div>');
		loginScriptArray.push('<div class="userInfo"></div>');
		loginScriptArray.push('<div class="login_info" class="textlink" >账号或密码错误</div>');
	loginScriptArray.push('</div>');
	loginScriptArray.push('<div class="ui-form noNeedAfterValidate" action="#" id="login" name="loginForm" novalidate="novalidate" >');
		loginScriptArray.push('<fieldset>');
			loginScriptArray.push('<h2 id="J-login-title" class="ui-form-title">欢迎登录科协系统智能学习平台</h2>');
			loginScriptArray.push('<form>');
			loginScriptArray.push('<div id="J-username" class="ui-form-item ui-form-item-focus  origin">');
				loginScriptArray.push('<label id="J-label-user" class="ui-label">');
					loginScriptArray.push('<span class="ui-icon ui-icon-userDEF">账号</span>');
				loginScriptArray.push('</label>');
				loginScriptArray.push('<span class="alieditContainer">');
					loginScriptArray.push('<input id="userId" name="userId" class="ui-input ui-input-normal" type="text" placeholder="登录账号" maxlength="64"  value="" tabindex="1" data-explain="" />');
				loginScriptArray.push('</span>');
			loginScriptArray.push('</div>');
			loginScriptArray.push('<div id="J-password" class="ui-form-item   origin">');
				loginScriptArray.push('<label data-desc="登录密码" class="ui-label" id="J-label-editer">');
					loginScriptArray.push('<span id="safeSignCheck" class="ui-icon ui-icon-securityON">密码</span>');
				loginScriptArray.push('</label>');
				loginScriptArray.push('<span id="password_container" class="alieditContainer">');
					loginScriptArray.push('<input id="password" name="password" type="password" value=""  maxlength="64" oncontextmenu="return false" class="ui-input i-text" tabindex="2" />');
				loginScriptArray.push('</span>');
			loginScriptArray.push('</div>');
			loginScriptArray.push('<div id="J-verify" class="ui-form-item   origin">');
				loginScriptArray.push('<label data-desc="验证码" class="ui-label" id="J-label-editer">');
					loginScriptArray.push('<span id="safeSignCheck" class="ui-icon ui-icon-securityON">验证码</span>');
				loginScriptArray.push('</label>');
				loginScriptArray.push('<span id="verifyCode_container" class="alieditContainer">');
					loginScriptArray.push('<input id="verifyCode" name="verifyCode" type="text" value="" autocomplete="off" maxlength="64" oncontextmenu="return false" class="ui-input i-text" tabindex="3"><img src="../pub/verifyCode.jsp?version=0.13892570417374372" id="verifyCodeImg" title="点击刷新验证码" />');
				loginScriptArray.push('</span>');
			loginScriptArray.push('</div>');
			loginScriptArray.push('</form>');
			loginScriptArray.push('<div id="aLine" class="ui-form-item-20pd  ">');
				loginScriptArray.push('<p>');
					loginScriptArray.push('<a tabindex="8" title="忘记密码" class="textlink textlink-ml20" href="forgetPwd.jsp" target="_blank">忘记登录密码？</a>');
				loginScriptArray.push('</p>');
			loginScriptArray.push('</div>');
			loginScriptArray.push('<div id="J-submit" class="ui-form-item ui-form-item-30pd ">');
				loginScriptArray.push('<input type="submit" tabindex="4" id="J-login-btn" class="ui-button   origin" value="登 录" />');
				loginScriptArray.push('<p class="ui-form-other">');
					loginScriptArray.push('<a  tabindex="6" class="textlink login_info" href="javascript:void(0);">账户或密码错误</a>');
					loginScriptArray.push('<a style="display:none" tabindex="7" title="账户激活" class="textlink textlink-ml20" href="javascript:void(0);">账户激活</a>');
					loginScriptArray.push('<a tabindex="6" title="免费注册" style="display:none" href="javascript:void(0);" class="register">免费注册</a>');
				loginScriptArray.push('</p>');
			loginScriptArray.push('</div>');
			loginScriptArray.push('<fieldset>');
			loginScriptArray.push('</fieldset>');
		loginScriptArray.push('</fieldset>');
	loginScriptArray.push('</div>');
	loginScriptArray.push('<div class="quick-form noNeedAfterValidate">');
		loginScriptArray.push('<div class="qrcode-login qrcode-login-error" id="J_QRCodeLogin" style="display: block;">');
		   loginScriptArray.push('<div class="ui-form-title">手机扫码，安全登录</div>');
		   loginScriptArray.push('<div class="qrcode-mod">');
		        loginScriptArray.push('<div class="qrcode-main">');
		            loginScriptArray.push('<div class="qrcode-img" id="J_QRCodeImg" ><img src="" /></div>');
				    loginScriptArray.push('<div class="qrcode-help"></div>');
				    loginScriptArray.push('<div class="msg-err">');
						loginScriptArray.push('<h6>二维码已失效</h6>');
						loginScriptArray.push('<a href="javascript:void(0);" class="refresh J_QRCodeRefresh">请点击刷新</a>');
				    loginScriptArray.push('</div>');
		        loginScriptArray.push('</div>');
				loginScriptArray.push('<div class="qrcode-desc">');
		            loginScriptArray.push('<p>');
		            	loginScriptArray.push('打开');
		            	loginScriptArray.push('<a href="http://159.226.28.50/elearning2/install.htm" target="_blank" class="light-link">继续教育app</a>'); 
		                loginScriptArray.push('<span class="ft-gray">扫一扫登录</span>');
		            loginScriptArray.push('</p> ');  
		        loginScriptArray.push('</div>');
		        
		     loginScriptArray.push('</div>');
		      loginScriptArray.push('<div class="qrcode-msg" style="display:none">');
				loginScriptArray.push('<div class="msg-ok">');
						loginScriptArray.push('<div class="msg-icon">');
							loginScriptArray.push('<i class="iconfont icon-ok"></i>');
							loginScriptArray.push('<i class="iconfont icon-phone"></i>');
						loginScriptArray.push('</div>');
						loginScriptArray.push('<h6>扫描成功！</h6>');
						loginScriptArray.push('<p>请在手机上确认登录</p>');
						loginScriptArray.push('<div class="link"><a href="#" class="light-link J_QRCodeRefresh">返回二维码登录</a></div>');
				loginScriptArray.push('</div>');
			loginScriptArray.push('</div>');
			loginScriptArray.push('<div class="login-links" >');
		        loginScriptArray.push('<a href="javascript:void(0);" class="changeLoginStatus" >密码登录</a>');
		   loginScriptArray.push('</div>');
		loginScriptArray.push('</div>');
	loginScriptArray.push('</div>');
loginScriptArray.push('</div>');
loginScriptArray.push('</div>');
		loginScript=loginScriptArray.join("");
	}
	else{
		
	}
	return loginScript;
	
	
	
}
