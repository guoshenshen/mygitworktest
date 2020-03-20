var structureInfo="";

function structure(){
	if(structureInfo!=""){		
		alert('123');
	}
	else{
		var structureArray=new Array();
		structureArray.push('<div>');
structureArray.push('<!-- 用户详细导航栏 -->');
structureArray.push('<div class="nav_in"><div id="nav_left"></div>');
structureArray.push('<ul class="leftfloat" id="menu_home">');
   structureArray.push(' <li class="menuName leftfloat firstmenu1" >');
    	structureArray.push('<a href="javascript:void(0);" onclick="javascript:openNavClick(1);" class="blue head" style="position:static">首&nbsp;&nbsp;页</a>');
    structureArray.push('</li>');
    structureArray.push('<li class="menuSep leftfloat"></li>');
    structureArray.push('<li class="menuName leftfloat firstmenu2" >');
    	structureArray.push('<a href="javascript:void(0);" onclick="javascript:openNavClick(2);" class="blue head">新闻报道</a>');
   		structureArray.push('<ul class="secondmenu2">');
   			structureArray.push('<li onclick="javascript:openNavClick_Sub(21)"><a  href="javascript:void(0);">新闻</a></li>');
   			structureArray.push('<li class="menuLine"></li>');
   			structureArray.push('<li onclick="javascript:openNavClick_Sub(22)"><a  href="javascript:void(0);">通知</a></li>');
   		structureArray.push('</ul>');
    structureArray.push('</li>');
    structureArray.push('<li class="menuSep leftfloat"></li>');
    structureArray.push('<li class="menuName leftfloat firstmenu2" >');
    	structureArray.push('<a href="javascript:void(0);" onclick="javascript:openNavClick(7);" class="blue head">学习资源</a>');
    	structureArray.push('<ul class="secondmenu2" >');
    		structureArray.push('<li onclick="javascript:openNavClick_Sub(91)"><a  href="javascript:void(0);">政治理论</a></li>');
    		structureArray.push('<li class="menuLine"></li>');
    		structureArray.push('<li onclick="javascript:openNavClick_Sub(92)"><a  href="javascript:void(0);">形势政策</a></li>');
    		structureArray.push('<li class="menuLine"></li>');
    		structureArray.push('<li onclick="javascript:openNavClick_Sub(93)"><a  href="javascript:void(0);">科协事业</a></li>');
    		structureArray.push('<li class="menuLine"></li>');
    		structureArray.push('<li onclick="javascript:openNavClick_Sub(94)"><a  href="javascript:void(0);">工作能力</a></li>');
    		structureArray.push('<li class="menuLine"></li>');
    		structureArray.push('<li onclick="javascript:openNavClick_Sub(95)"><a  href="javascript:void(0);">通识素养</a></li>');
    		structureArray.push('<li class="menuLine"></li>');
    		structureArray.push('<li onclick="javascript:openNavClick(11)"><a  href="javascript:void(0);">系列专题</a></li>');
    	structureArray.push('</ul>');
	structureArray.push('</li>');
    structureArray.push('<li class="menuSep leftfloat"></li>');
    structureArray.push('<li class="menuName leftfloat firstmenu2" >');
    	structureArray.push('<a href="javascript:void(0);" onclick="javascript:openNavClick(8);" class="blue head">教师资源</a>');
    	structureArray.push('<ul class="secondmenu2" >');
    		structureArray.push('<li onclick="javascript:openNavClick_Sub(81)"><a  href="javascript:void(0);">内聘教师</a></li>');
    		structureArray.push('<li class="menuLine"></li>');
    		structureArray.push('<li onclick="javascript:openNavClick_Sub(82)"><a  href="javascript:void(0);">外聘教师</a></li>');
    	structureArray.push('</ul>');
    structureArray.push('</li>');
    structureArray.push('<li class="menuSep leftfloat"></li>');
    structureArray.push('<li class="menuName leftfloat firstmenu2" >');
    	structureArray.push('<a href="javascript:void(0);" onclick="javascript:openNavClick(6);" class="blue  head">培训项目</a>');
    	structureArray.push('<ul class="secondmenu2" >');
    		structureArray.push('<li onclick="javascript:openNavClick_Sub(61)"><a  href="javascript:void(0);">培训项目</a></li>');
    		structureArray.push('<li class="menuLine"></li>');
    		structureArray.push('<li onclick="javascript:openNavClick_Sub(62)"><a  href="javascript:void(0);">学术报告</a></li>');
    	structureArray.push('</ul>');
    structureArray.push('</li>');
    structureArray.push('<li class="menuSep leftfloat"></li>');
    structureArray.push('<li class="menuName_1 leftfloat firstmenu3" >');
    	structureArray.push('<a href="javascript:void(0);" onclick="javascript:checkLogon(4);" class="blue head">政策与工作流程</a>');
    	structureArray.push('<ul class="secondmenu3" >');
    		structureArray.push('<li onclick="javascript:openNavClick_Sub(41)"><a  href="javascript:void(0);">政策</a></li>');
    		structureArray.push('<li class="menuLine"></li>');
    		structureArray.push('<li onclick="javascript:openNavClick_Sub(42)"><a  href="javascript:void(0);">工作流程</a></li>');
    	structureArray.push('</ul>');
    structureArray.push('</li>');
    structureArray.push('<li class="menuSep leftfloat"></li>');
    structureArray.push('<li class="menuName leftfloat firstmenu1" onclick="javascript:/*openNavClick(10);">');
    	structureArray.push('<a href="javascript:void(0);"  class="blue">关&nbsp;&nbsp;于</a>');
    structureArray.push('</li>');
structureArray.push('</ul>');
structureArray.push('<div class="search">');
structureArray.push('<input id="search-txt" class="form-control" name="search" value="" style="float:left;height:12px;margin-top:15px;width:115px;margin-left:25px;" type="text" readonly onfocus="this.removeAttribute(\'readonly\');" onkeydown="if(event.keyCode==13){searchAndFind1();}">');
structureArray.push('<a href="javascript:void(0);" onclick="javascript:searchAndFind1();"><img src="/image/search00.png" alt="搜索" style="margin-top:18px;"></a>');

//原有的，不带参数
structureArray.push('<div id="advanced_search"><a href="advancedSearchAction.do?method=forAdvancedSearch" class="head">高级检索</a></div></div><div id="nav_right"></div>');

//升级之后，可以设置为此种方式
//structureArray.push('<div id="advanced_search"><a href="javascript:void(0);" class="head" onclick="javascript:advancedSearch();">高级检索</a></div></div><div id="nav_right"></div>');

structureArray.push('</div>');



structureArray.push('<!-- 用户端简易导航栏 -->');
structureArray.push('<div class="nav-login-top">');
		structureArray.push('<div class="nav-menu-container">');
		structureArray.push('<a href="javascript:void(0);" class="logo-container" onclick="javascript:openNavClick(1);"></a>');
	    structureArray.push('<ul class="nav-bin">');
	    	structureArray.push('<li>');
	        	structureArray.push('<a href="javascript:void(0);"  onclick="javascript:openNavClick(11);">系列专题</a>');
	        structureArray.push('</li>');
	        structureArray.push('<li class="current js-dropdown-btn has-child">');
	        	structureArray.push('<a href="javascript:void(0);" onclick="javascript:openNavClick(7);">学习资源</a>');
				structureArray.push('<ul class="course-drop-down js-dropdown-body" style="display: none;">');
	                structureArray.push('<li class="current">');
						structureArray.push('<a href="http://x.patsnap.cn/kecheng">微课程');
	                        structureArray.push('<em id="v-course-num">289</em>');
	                    structureArray.push('</a>');
	                    structureArray.push('<div class="line-top"></div>');
					structureArray.push('</li>');
					structureArray.push('<li>');
					structureArray.push('<a href="http://x.patsnap.cn/zhuti">精品课程');
	                    structureArray.push('<em id="v-theme-num">7</em>');
	                structureArray.push('</a>');
					structureArray.push('</li>');
					structureArray.push('<li>');
					structureArray.push('<a href="http://x.patsnap.cn/zhuanlan">知识地图');
	                    structureArray.push('<em id="v-column-num">8</em>');
	                structureArray.push('</a>');
					structureArray.push('</li>');
	                structureArray.push('<li>');
	                structureArray.push('<a href="http://x.patsnap.cn/zhiye">电子阅读');
	                    structureArray.push('<em id="v-profession-num">6</em>');
	                structureArray.push('</a>');
	                structureArray.push('</li>');
				structureArray.push('</ul>');
	        structureArray.push('</li>');
	
	        structureArray.push('<li>');
		        structureArray.push('<a href="javascript:void(0);" onclick="javascript:openNavClick(6);">培训项目</a>');
				structureArray.push('<ul class="course-drop-down js-dropdown-body" style="display: none;">');
	                structureArray.push('<li class="current">');
						structureArray.push('<a href="http://x.patsnap.cn/kecheng">学术报告');
	                        structureArray.push('<em id="v-course-num">289</em>');
	                    structureArray.push('</a>');
	                    structureArray.push('<div class="line-top"></div>');
					structureArray.push('</li>');
	                structureArray.push('<li>');
		                structureArray.push('<a href="http://x.patsnap.cn/zhiye">培训项目');
		                    structureArray.push('<em id="v-profession-num">6</em>');
		                structureArray.push('</a>');
	                structureArray.push('</li>');
				structureArray.push('</ul>');
	        structureArray.push('</li>');
	        structureArray.push('<li>');
	        	structureArray.push('<a  href="javascript:void(0);" onclick="javascript:openNavClick(2);">新闻报道</a>');
	        	structureArray.push('<ul class="course-drop-down js-dropdown-body" style="display: none;">');
	                structureArray.push('<li class="current">');
						structureArray.push('<a href="http://x.patsnap.cn/kecheng">新闻');
	                        structureArray.push('<em id="v-course-num">289</em>');
	                    structureArray.push('</a>');
	                    structureArray.push('<div class="line-top"></div>');
					structureArray.push('</li>');
	                structureArray.push('<li>');
		                structureArray.push('<a href="http://x.patsnap.cn/zhiye">通知');
		                    structureArray.push('<em id="v-profession-num">6</em>');
		                structureArray.push('</a>');
	                structureArray.push('</li>');
				structureArray.push('</ul>');
	        structureArray.push('</li>');
		structureArray.push('</ul>');
		structureArray.push('<div class="search">');
			structureArray.push('<input id="search-txt" class="form-control" name="search" value=""  type="text" onkeydown="if(event.keyCode==13){searchAndFind1();}" >');
			structureArray.push('<a href="javascript:void(0);" onclick="javascript:searchAndFind1();" class="search-icon"><img src="/image/search00.png" alt="搜索"></a>');
		structureArray.push('</div>');
		structureArray.push('<div class="userAndRoles unlogonStatus">');
			structureArray.push('<a href="javascript:void(0);" class="logonAction">登录</a>');
			structureArray.push('<a class="headPic"><img src="" onerror="menuimgError(this);"></img></a>');
			structureArray.push('<span class="operatorName"></span>');
			structureArray.push('<div class="myStudy"></div>');
			structureArray.push('<div class="roleLink"></div>');
		structureArray.push('</div>');
	structureArray.push('</div>');
structureArray.push('</div>');

structureArray.push('<!-- 用户详细页脚 -->');
structureArray.push('<div id="footer_bg">');
 structureArray.push('<div id="footer_1">');
 		structureArray.push('<div class="dw_up">');
 			structureArray.push('<div class="up_div"><a href="http://www.cast.org.cn" class="hb" target="_blank">中国科学技术协会</a></div>');
        structureArray.push('</div> ');
 structureArray.push('</div>');
 structureArray.push('<div class="dw_up_line">  </div>');
 structureArray.push('<div id="footer_2"> ');
  structureArray.push('<div class="dw">');
    	structureArray.push('<div class="dw_wz">');
        	structureArray.push('<div class="wz_div"><a href="http://www.cast.org.cn/" class="hb" target="_blank">中国科学技术协会</a></div>');
            structureArray.push('<div class="wz_div last"><a href="http://www.kepu.net.cn" class="hb" target="_blank">中国科普博览</a></div>');
        structureArray.push('</div>'); 	
  structureArray.push('</div>');
  structureArray.push('<div class="d_bq">中国科学技术协会培训和人才服务中心&nbsp;&nbsp;&nbsp;&nbsp;版权所有&nbsp;&nbsp;&nbsp;&nbsp;    京ICP备09112257号-95&nbsp;&nbsp;&nbsp;&nbsp; 京公网安备11010802017084 <br />');
  structureArray.push('技术支持：中国科学院计算机网络信息中心&nbsp;&nbsp;&nbsp;&nbsp;       技术：010-588137(04/07/10)&nbsp;&nbsp;&nbsp;&nbsp;      邮箱：train@cnic.cn');
  structureArray.push('</div>'); 
structureArray.push('</div> ');


structureArray.push('</div> ');


structureArray.push('<!-- 用户简易页脚 -->');
structureArray.push('<div id="link">');
	structureArray.push('<p>中国科学技术协会培训和人才服务中心      版权所有 京ICP备09112257号-95</p>');
	structureArray.push('<p>技术支持：中国科学院计算机网络信息中心    技术：010-588137(07/10/15)   邮箱：train@cnic.cn</p>');
structureArray.push('</div>');


structureArray.push('<!-- 悬挂栏 -->');
structureArray.push('<div class="asid_share" id="asid_share">');
	structureArray.push('<div class="asid_share_box relative version" style="display:none">');
		structureArray.push('<a href="stuLogonAction.do?customize=0"><img alt="返回普适版" title="返回普适版" class="adid_icon" src="/image/hhShare/icon_say.png"></a>');
	structureArray.push('</div>');
	structureArray.push('<div class="asid_share_box relative">');
		structureArray.push('<a href="javascript:void(0);"><img alt="新版帮助" title="新版帮助" class="adid_icon" src="/image/hhShare/icon_help.png"></a>');
		structureArray.push('<div class="asid_share_triangle" style="display:none;">');
			structureArray.push('<em class="border_sj">&#9670;</em>');
	    structureArray.push('<span class="con_sj">&#9670;</span>');
    structureArray.push('</div>');
	structureArray.push('<div class="asid_sha_layer top" style="display:none;">');
		structureArray.push('<ul class="asid_help_list" style="padding: 10px 20px;">');
			structureArray.push('<li><a href="javascript:void(0);">客服：010-58813704/07/10</a></li>');
			structureArray.push('<li><a href="javascript:void(0);">邮箱：train@cnic.cn</a></li>');
		structureArray.push('</ul>');
	structureArray.push('</div>');
	structureArray.push('</div>');
	structureArray.push('<div class="asid_share_box relative">');
		structureArray.push('<a href="javascript:void(0);"><img alt="扫二微码" title="扫二微码" class="adid_icon" src="/image/hhShare/icon_sweep.png"></a>');
		structureArray.push('<div class="asid_share_triangle" style="display:none;">');
			structureArray.push('<em class="border_sj">&#9670;</em>');
	    structureArray.push('<span class="con_sj">&#9670;</span>');
    structureArray.push('</div>');
	structureArray.push('<div class="asid_sha_layer bottom" style="width:250px;display:none;">');
		structureArray.push('<p class="sweep_img"><img src="/image/hhShare/weixin.png" width="220"></p>');
		structureArray.push('<p class="pb6"><b>扫一扫上面的二维码图案，下载继续教育APP</b></p>');
	structureArray.push('</div>');
	structureArray.push('</div>');
	structureArray.push('<div class="asid_share_box relative" style="display:none;background-color:#3564D0">');
		structureArray.push('<a href="javascript:void(0);"><img alt="返回顶部" title="返回顶部" class="adid_icon" src="/image/hhShare/icon_back.png"></a>');
	structureArray.push('</div>');
structureArray.push('</div>');
structureArray.push('</div>');
structureInfo=structureArray.join("");
	}
	return structureInfo;
	
}
