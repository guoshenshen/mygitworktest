/**
*动态获取所有一级菜单和二级菜单;
*@editor zq@cnic.cn 
*@version 2017.06.01 
*/
var iconLocation="";  //全局变量
var $left=$(window).width();
var $top=$(window).height();
var exitOptions={
		
}

var operatorInfoOptions={
	logon:false	
}

//页面模板控制器
//edited by zq@cnic.cn
var menuLoadingOptions={
	needLoad:true,
	$currentPage:null,
	$scriptInfo:null,
	$scriptPortalFooter:null,
	$scriptPortalNavi:null,
	$scriptSimpleNavi:null,
	$scriptSimpleFooter:null,
	$scriptAside:null,
	url:"/htmlStructure/skinStructure.js",
	loginInfo:{
		needLoad:true,
		tenantName:"",
		//驻留站点tenantId
		tenantId:null,
		//用户隶属租户tenantId
		userTenant:null,
		userForm:null,
		showVersionChange:false
	}
}


menuLoadingOptions.initializeInfo=function(){
	var $currentPage=$("#portalStyle");
	if($currentPage.length==0){
		$currentPage=$("#newStyle");
	}
	menuLoadingOptions.$currentPage=$currentPage;
}

menuLoadingOptions.loadScript=function(actionAfterLoading,url){
	menuLoadingOptions.ifHasLogined(function(){
		var loginInfo=menuLoadingOptions.loginInfo;
		if(loginInfo.tenantId==2001){
			url="http://resource.casmooc.cn/htmlStructure/skinStructure_united_front_wd.js";
		}
		if(url==null){
			url=menuLoadingOptions.url;
		}
		if(menuLoadingOptions.$scriptInfo==null){
			if(menuLoadingOptions.needLoad){
				menuLoadingOptions.needLoad=false;
				$.getScript(
					url,
			        function(){
						var $scriptInfo=$($.parseHTML(structure()));
						menuLoadingOptions.$scriptPortalFooter=$scriptInfo.find("#footer_bg");
						menuLoadingOptions.$scriptSimpleFooter=$scriptInfo.find("#link");
						menuLoadingOptions.$scriptPortalNavi=$scriptInfo.find(".nav_in");
						menuLoadingOptions.$scriptSimpleNavi=$scriptInfo.find(".nav-login-top");
						menuLoadingOptions.$scriptAside=$scriptInfo.find("#asid_share");
						menuLoadingOptions.$scriptInfo=$scriptInfo;
						actionAfterLoading();
			        }   
			   )
			} else{
				var loadInterval=setInterval(function(){
					if(menuLoadingOptions.$scriptInfo!=null){
						clearInterval(loadInterval);
						actionAfterLoading();
					}
				},1);
			}
			
		}else{
			actionAfterLoading();	
		}	
	})  
}

//判断当前用户是否已经登录,并根据具体登录情况执行后续动作
menuLoadingOptions.ifHasLogined = function (functionAfterLoginInfoLoaded) {
    var loginInfo = menuLoadingOptions.loginInfo;
    if (loginInfo.tenantId == null) {
        if (loginInfo.needLoad) {
            loginInfo.needLoad = false;
            $.ajax({
                url: "../eosOperator/hasLogined.do",
                type: "post",
                dataType: "json",
                success: function (data) {
                    if(data.data.userForm!=null){
                        loginInfo.userForm=data.data.userForm;
                    }
                    if(data.data.showVersionChange!=null){
                        loginInfo.showVersionChange=data.data.showVersionChange;
                    }
                    if(data.data.userTenant!=null){
                        loginInfo.userTenant=data.data.userTenant;
                    }
                    loginInfo.tenantName=data.data.tenantName;
                    loginInfo.tenantId=data.data.tenantId;
                    functionAfterLoginInfoLoaded();
                }
            });
            //loginInfo.needLoad = false;
        } else {
            var loadInterval = setInterval(function () {
                if (loginInfo.tenantId != null) {
                    clearInterval(loadInterval);
                    functionAfterLoginInfoLoaded();
                }
            }, 1);
        };
    } else {
        functionAfterLoginInfoLoaded();
    }
    ;
};

//加载企事业单位版权所有标识
menuLoadingOptions.loadAuthentication=function($target){
	$target.append(unescape("%3Cspan id='_ideConac' %3E%3C/span%3E%3Cscript src='http://dcs.conac.cn/js/33/000/0000/60451603/CA330000000604516030012.js' type='text/javascript'%3E%3C/script%3E"));
}

//加载门户页脚
menuLoadingOptions.loadFooter=function(){	
	menuLoadingOptions.loadScript(function(){
		var $foot=$("#foot",menuLoadingOptions.$currentPage);
		if($foot.length!=0){
			$foot.append(menuLoadingOptions.$scriptPortalFooter);
			menuLoadingOptions.loadAuthentication($(".dw",$foot));
		}
	});
}

//加载简易页脚
menuLoadingOptions.loadSimpleFooter=function(){
	menuLoadingOptions.loadScript(function(){
		$("#simplefoot").append(menuLoadingOptions.$scriptSimpleFooter);
	})
}

//加载侧边悬挂栏
menuLoadingOptions.loadAside=function(){
	menuLoadingOptions.loadScript(function(){
		var $asideContainer=$(".asideContainer");
		if($asideContainer.length>0){
			$asideContainer.append(menuLoadingOptions.$scriptAside);
		}
		$.getScript("/js/nav/jQuery.hhShare.min.js",function(){
			$('#asid_share').hhShare({
				cenBox     : 'asid_share_box',  //里边的小层
				icon       : 'adid_icon',
				addClass   : 'red_bag',
				titleClass : 'asid_title',
				triangle   : 'asid_share_triangle', //鼠标划过显示图层，边上的小三角
				showBox    : 'asid_sha_layer' //鼠标划过显示图层
			});
		});

	})
}

//加载简易导航栏
menuLoadingOptions.loadSimpleNavi=function(){
	menuLoadingOptions.loadScript(function(){
		var $simpleNavBar=$("#simpleNavBar");
		if($simpleNavBar.length!=0){
			$simpleNavBar.html(menuLoadingOptions.$scriptSimpleNavi);
			//加载角色切换链接
			menuLoadingOptions.loadTopMenu();
			//加载登录链接及学时提醒
			menuLoadingOptions.loadLoginNav();
			$simpleNavBar.on("mouseenter",".userAndRoles .headPic",function(){
				var $roleLink=$(".roleLink","#simpleNavBar .userAndRoles");
				if($roleLink.find("a").length>0){
					//只有在已经加载链接的情况下才显示链接框
					$(".roleLink","#simpleNavBar .userAndRoles").show();
				}
	    	}).on("mouseleave",".userAndRoles",function(){
	    		$(this).find(".roleLink").hide();
	    	})
		}
	});
	
}

//加载详细导航栏
menuLoadingOptions.loadDetailNavi=function(){
	menuLoadingOptions.loadScript(function(){
		var $detailNavBar=$("#nav",menuLoadingOptions.$currentPage);
		if($detailNavBar.length>0){
			$detailNavBar.append(menuLoadingOptions.$scriptPortalNavi);
			//加载角色切换链接
			menuLoadingOptions.loadTopMenu();
			//加载登录链接及学时提醒
			menuLoadingOptions.loadLoginNav();
		}	
	});
}

function findMenu(){
   var parentmenu="",parentMenuId="",currentmenuchildarea="",childmenu="";
   if(typeof $.cookie!='function'){
	   return
   }
   if($.cookie("parentmenu")!=null){
	   parentmenu=$.cookie("parentmenu");
   }
   var $menuarea=$("#aside ul",$('#oldStyle'));
   var $menuarea1=$("#aside ul",$('#newStyle'));
   if(parentmenu==''){
     $.ajax({
      url:'../findMenu/getParentMenu.do',
      type:"POST",
      async:false,
      dataType:"text",
      success:function(data,evt){   
            var node=$.parseJSON(data);
            var parent_menu="";
            $.each(node,function(i,value){
              parent_menu +="<li ><span class='"+node[i]._parentId+"'></span><a class='menu_"+node[i]._parentId+"' href='javascript:void(0);' onclick='javascript:showBufferMask({\"targetUrl\":\""+node[i].resourceUrl+"\"});writeLocation("+node[i]._parentId+",0);'>"+node[i].resourceName+"</a></li>";
            })
            $.cookie("parentmenu",parent_menu);
            parentmenu=parent_menu;
           }
      })
    }
    $menuarea.html(parentmenu);
    $menuarea1.html(parentmenu);
}
    
/**
*保存一级菜单和二级菜单的名称;
*@author weixiaoyi 
*@version 2011.09.6 
*/
function writeLocation(parentMenuId,childMenuId){
    $.ajax({
    type:"POST",
    url:"../findMenu/setLocation.do",
    dataType:"text",
    data:"cId="+childMenuId+"&pId="+parentMenuId+"",
    success:function(data,evt){}
    });
}

function getLocation(){
    $.ajax({
    type:"POST",
    url:"../findMenu/getLocation.do",
    dataType:'text',
    success:function(data,evt){
      var node=$.parseJSON(data);
      if(!(typeof showBufferMask=== "function")){
    	  $.getScript("/js/JSCommonTools.js",function(response,status){
    	  });
      } 
      var parentMenu="",childMenu="",parentUrl="",childUrl="",pId="";
      $.each(node,function(i,value){
         if(node[i].pName!=null){
           parentMenu=node[i].pName;
           parentUrl=node[i].pUrl;
           pId=node[i].pId;
         }else{
           childMenu=node[i].cName;
           childUrl=node[i].cUrl;
         }
      })
      //alert($("#aside ul").find('.menu_'+pId).html());
      $("#aside ul",$('#oldStyle')).find('.menu_'+pId).css({'background':'url(././image/student/menuon.png) repeat-x','color':'#FFFFFF'});
      $("#aside ul",$('#newStyle')).find('.menu_'+pId).css({'background':'url(././image/student/menuon.png) repeat-x','color':'#FFFFFF'});
      if(parentMenu!="")
          $("#trace",$('#oldStyle')).html("我的学习&nbsp;&nbsp;>&nbsp;&nbsp;"+parentMenu+"");
          $("#trace",$('#newStyle')).html("我的学习&nbsp;&nbsp;>&nbsp;&nbsp;"+parentMenu+"");
    }
    });
}



$(document).ready(function(){
	
	  //初始化信息
	  menuLoadingOptions.initializeInfo();
	
	  //加载用户导航栏
	  menuLoadingOptions.loadDetailNavi();
	  
	  //加载简易导航栏信息
	  menuLoadingOptions.loadSimpleNavi();
	 
	  //加载页脚
	  menuLoadingOptions.loadFooter();
	  
	  //加载简易页脚
	  menuLoadingOptions.loadSimpleFooter();
	  
	  //加载侧边悬浮窗
	  menuLoadingOptions.loadAside();
	  
	  var LoginBinding=function(){
		  if(!(typeof showLoginWin=== "function")){
			  $.getScript("/js/login/login.js",function(response,status){
			  });
		  }  
	  };
	  if(typeof(JSCommonToolsArea)=="undefined"){
		  $.getScript("/js/JSCommonTools.js",function(){
			  LoginBinding();
		  });
	  } else{
		  LoginBinding();
	  }
	  $.getScript("/js/UI/jquery.alerts.js",function(){
		  $.alerts.dialogClass="blueStyle";
	  });

      findMenu();      //加载系统菜单
      getLocation();
      
      if(typeof $.cookie=="function"){
    	  if($.cookie("myStudyFlag")==1){
    		  $("#nav #menu_home>li:eq(2)",$('#newStyle')).find("a").css("color","#f38f00");
    	  } 
      }
     
      $("._exitSystemCss").attr("href","#");
      $("._exitSystemCss").on('click',function(){
    	  $.cookie("parentmenu",null);
      	  logonOutSystem();
      }
      );
      //$('.selectpicker').selectpicker({'selectedText': 'cat'});
 
      
      $.ajax({
		  url:'../findMenu/findStudentMenuForRole.do',
	      type:"POST",
	      async:false,
	      dataType:"text",
	      success:function(data,evt){   
	            var node=$.parseJSON(data);
	            if(node.name != null && node.name != ""){
	            	operatorInfoOptions.logon=true;
	            	//$.cookie("myStudyFlag","1");
	            }else{
	            	operatorInfoOptions.logon=false;
	            }
	            	
	      }
	  })
      

});
/*
* load student interface top menu 
* by ffr
* 2012-09-13
*/

function logonOutSystem(){
	//先清空cookie
	$.cookie("parentmenu","");
	$.cookie("myStudyFlag","0");
	$.cookie('i',"0");   //培训班cookie
	//清空map及session
	
	$.ajax({
		url:'exitSystem.do?method=logOut',
		type:"POST",
		dataType:"json",
		traditional: true,  
		success:function(data){
			if(data.result=="false"){
				jAlert("退出系统出现异常！",'错误提示');
			}
			var bufferCss={"width":"200px","height":"130px"};
			if(!(typeof showBufferMask=== "function")){
		    	  $.getScript("/js/JSCommonTools.js",function(response,status){
		    		  showBufferMask({"bufferCss":bufferCss,"targetUrl":data.url,"background_img":"url(./image/buffer/goodBye.png)"});
		    	});
		    } 
			else{
				showBufferMask({"bufferCss":bufferCss,"targetUrl":data.url,"background_img":"url(./image/buffer/goodBye.png)"});
			}
		},
		error:function(data){
			jAlert("退出系统出现异常！",'错误提示');
		}
	})
  
}




/*
function loadTopMenu(){
	$.ajax({
	  url:'findMenuAction.do?method=findStudentMenuForRole',
      type:"POST",
      async:false,
      dataType:"text",
      success:function(data,evt){   
            var node=$.parseJSON(data);
            var str = "";
            if(node.name != null && node.name != ""){
            	str += "您好，<span>"+node.name+"</span>！欢迎登录中国科学院继续教育门户!【<a href='javascript:void(0);' onclick='javascript:logonOutSystem();' style='color:#333'>注销</a>】";
            }else{
            	//未登录
            	str += "&nbsp;&nbsp;";
            	if(node.isVirOrg==1||node.tenantId==1000){
              	}	
            }
            if(node.admin != null && node.admin != ""){
            	str += "【<a href='javascript:void(0);' onclick='javascript:changeRoleAndOpen(\""+node.admin+"\","+node.adminRoleId+");return false;' style='color:#333'>我的管理</a>】";
            }
            if(node.stu != null && node.stu != ""){
            	str += "【<a href='javascript:void(0);' onclick='javascript:showBufferMask();openUrl(\""+node.stu+"\");return false;' style='color:#333'>我的学习</a>】";
            }
            $("#top-bar","#newStyle").html(str);
            $("#portalStyle #top-bar").html(str);
       }
    })
}
*/

//加载登录用户的“我的学习”、“我的管理”链接
menuLoadingOptions.loadTopMenu=function(){
	menuLoadingOptions.ifHasLogined(function(){
		var loginInfo=menuLoadingOptions.loginInfo;
		var loginUserInfo=loginInfo.userForm;
		if(loginUserInfo!=null){
			//当前用户已经登录
			$.ajax({
				  url:'../findMenu/findStudentMenuForRole.do',
			      type:"POST",
			      async:false,
			      dataType:"text",
			      success:function(data,evt){   
			            var node=$.parseJSON(data);
			            if($("#top-bar",menuLoadingOptions.$currentPage).length>0){
			            	
			            	var infoArray=new Array();
			            	var greetingInfo="中国科协智能学习平台！";
			            	if(loginUserInfo.tenantId==3000){
			            		greetingInfo="中国科协智能学习平台！";
			            	}
			            	if(loginUserInfo.tenantId==997){
			            		greetingInfo="\"一带一路\"科技教育平台！";
			            	}
			            	else if(loginUserInfo.tenantId==2001){
			            		greetingInfo="中共中央统一战线工作部在线学习平台！";
			            	}else if(loginUserInfo.tenantId==1006){
			            		greetingInfo="上海科技大学在线学习平台！";
			            	}else{
			            		
			            	}
			            	$("#top-bar",menuLoadingOptions.$currentPage).on("click","#toAdmin",function(){
				            		$(this).changeRole();
				            })
			            	
			            	infoArray.push("<div id='customizeLink'><a></a></div>");
			            	infoArray.push("您好，<span  class='intoZone' title='点击进入学员空间'>"+node.name+"</span>！欢迎登录");
			            	infoArray.push(greetingInfo);
			            	infoArray.push("【<a href='javascript:void(0);' onclick='javascript:logonOutSystem();' style='color:#333'>注销</a>】");
			                if(node.admin != null && node.admin != ""){
			                	infoArray.push("【<a href='javascript:void(0);' style='color:#333' id='toAdmin'><input type='hidden' name='roleId'  value='"+node.adminRoleId+"'>我的管理</a>】");
			                }
			                if(node.stu != null && node.stu != ""){
			                	infoArray.push("【<a href='javascript:void(0);' onclick='javascript:showBufferMask();openUrl(\""+node.stu+"\");return false;' style='color:#333'>我的学习</a>】");
			                }
			                $("#top-bar",menuLoadingOptions.$currentPage).html(infoArray.join(""));
			                $("#head").on("click","#top-bar .intoZone",function(){
			            		openLink("interactionAction.do?method=intoUserZone",{"operatorId":loginUserInfo.operatorId,"itemType":1},{"target":"_blank"});
			            		
			            	});
			               
			                
			                /*
			                 * 
			                 * 
			                 * 
			                 * 
			                if($("#top-wrap").hasClass("showCustomize")){
			                	 $("#top-wrap").addClass("logined");
			                	 if($("#top-wrap").hasClass("customizeTopWrap")){
					                	//当前处于专业版
					                	$("#top-bar #customizeLink a").attr("href","stuLogonAction.do?customize=0").attr("title","返回普适版").html("返回普适版").show();  	
					                }
					                else{
					                	//当前处于普适版
					                	$.ajax({
											url:"stuLogonInfoAction.do?method=getCustomizeVersionInfo",
											dataType:"json",
											method:"post",
											success:function(data){
												if(data.result=="true"){
													$("#top-bar #customizeLink a").attr("href",data.url).attr("title","前往专业版").html("前往专业版").show();
												}
												else{
													$("#top-bar #customizeLink").css("display","none");
												}
											},	
											error:function(){
												$("#top-bar #customizeLink").css("display","none");
											}
										});
					                }  
			                }
			                */
			                if(loginInfo.showVersionChange=="true"){
			                	 $("#top-wrap").addClass("logined");
			                	 var $versionLink= $("#top-bar #customizeLink a");
			                	 if(loginInfo.userTenant==loginInfo.tenantId){
			                		 //当前处于专业版
			                		 $versionLink.attr("title","返回全院").html("返回全院").show();  	
			                	 }
			                	 else{
			                		//当前处于普适版
			                		 $versionLink.attr("title","前往本单位").html("前往本单位").show();  
			                	 }
			                	 $versionLink.click(function(){
			                		 window.location.href="stuLogonInfoAction.do?method=getCustomizeVersionInfo";
			                	 })
			                }
			                else{
			                	  	
			                }  
			            }
			            else{
			            	$("#simpleNavBar .userAndRoles").removeClass("unlogonStatus");
			            	var headPicAddr=loginUserInfo.address;
			            	var gender=loginUserInfo.gender;
			            	if(headPicAddr==null||$.trim(headPicAddr)==""){	
			            		if(gender=="2"){
			            			headPicAddr="/image/headPic/female1.jpg";
			            		} else{
			            			headPicAddr="/image/headPic/male1.jpg";
			            		}
			            	}	
			            	$("#simpleNavBar .userAndRoles .headPic img").attr("src",headPicAddr);
			            	$("#simpleNavBar .userAndRoles .operatorName").html(loginUserInfo.operatorName);
			            	var myStudyInfo=new Array();
			            	myStudyInfo.push("<a href='javascript:void(0);' onclick='javascript:showBufferMask();openLink(\""+node.stu+"\");return false;'>我的学习</a>");
			            	myStudyInfo.push("<input type='hidden' id='currentOperatorId' value='"+loginUserInfo.operatorId+"'/>");
			            	$("#simpleNavBar .myStudy").html(myStudyInfo.join(""));
			            	$("#simpleNavBar .userAndRoles").on("click",".headPic",function(){
			            		openLink("interactionAction.do?method=intoUserZone",{"operatorId":loginUserInfo.operatorId,"itemType":1});
			            	});
			            	var $roleLink=$("#simpleNavBar .userAndRoles").find(".roleLink");
			            	var linksArray=new Array();
			            	linksArray.push("<div class='bufferMask'></div>");
			            	linksArray.push("<a href='javascript:void(0);' onclick='javascript:openLink(\""+node.stu+"\")'>我的学习</a>");
			            	if(node.admin != null && node.admin != ""){
			                	linksArray.push("<a href='javascript:void(0);' onclick='javascript:changeRoleAndOpen(\""+node.admin+"\",\""+node.adminRoleId+"\")'>我的管理</a>");
			            	}
			            	linksArray.push("<a href='javascript:void(0);' onclick='javascript:logonOutSystem();'>注销</a>");
			            	$roleLink.html(linksArray.join(""));
			            }

			       }
			    })
			
		}
		else{
			if($("#top-bar",menuLoadingOptions.$currentPage).length>0){
				 $("#top-bar",menuLoadingOptions.$currentPage).html("&nbsp;&nbsp;");
			}
			else{
				
			}
			
		}
	});
	
}


//对未登录学员,加载登录链接;对已登录学员加载当前学时提示
menuLoadingOptions.loadLoginNav=function(){
	menuLoadingOptions.ifHasLogined(function(){
		if($(".logo",menuLoadingOptions.$currentPage).length>0){
			var TenantId=menuLoadingOptions.loginInfo.tenantId;
			var logoimg = "";
			logoimg = '<div style="height:80px;width:400px;overflow:hidden;position:absolute;z-index:2;"><a style="display:block;float:left;cursor:pointer;text-decoration:none" target="_blank"><img src="./image/caskj_2_1.png" alt="logo"></a></div>';
			var logonContent = "";
			var logonContentInfoArray=new Array();
			if(menuLoadingOptions.loginInfo.userForm!=null){
				//当前用户已经登录
				$.ajax({
					url:'trainingHourReminderAction.do?method=getTrainingHour',
					dataType:"text",
					type:"POST",
					success:function(data){
						var node = $.parseJSON(data);
						var year=node.year;
						var remainingTrainingTime=0;
						var remainingTrainingHours=0;
						var totalTime=100;
						if(node.remainingTrainingTime != null && node.remainingTrainingTime !=""){
							remainingTrainingTime  = node.remainingTrainingTime;
						}
						if(node.remainingTrainingHours != null && node.remainingTrainingHours !=""){
							remainingTrainingHours  = node.remainingTrainingHours;
						}
						if(node.totalTime != null && node.totalTime != ""){
							totalTime = node.totalTime;
						}
						
						logonContentInfoArray.push('<div class="rightfloat">');
						logonContentInfoArray.push(node.year);
						logonContentInfoArray.push("年度您已完成");
						logonContentInfoArray.push(totalTime);
						if($.trim(node.noNeed)=="true"){
							logonContentInfoArray.push("</div>");
						}
						else{
							if($.trim(remainingTrainingTime)=="0小时"){
								logonContentInfoArray.push("，恭喜完成学习目标！</div>");
							}
							else{
								logonContentInfoArray.push("，再学");
								logonContentInfoArray.push(remainingTrainingTime);
								logonContentInfoArray.push("将达到100学时的目标！</div>");
							}	
						}
						logonContent=logonContentInfoArray.join("");
						$(".logo",menuLoadingOptions.$currentPage).html(logoimg+logonContent);
					}
					
				});
			}
			else{
					
					logonContentInfoArray.push("<div class='leftfloat' style='font-size:14px;position:absolute;z-index:1;'>");
					var unitName="";
					if(TenantId==1000){
						
					}
					else if(TenantId==1||TenantId==2||TenantId==78||TenantId==162||TenantId==997||TenantId==998||TenantId==1003){
						unitName=menuLoadingOptions.loginInfo.tenantName;
					}
					else if(TenantId==2001   ||  TenantId==1006 ){
						logonContentInfoArray.push("");
						unitName="";
					}
					logonContentInfoArray.push(unitName);
					logonContentInfoArray.push("</div>");
					logonContentInfoArray.push("<div class='rightfloat'>您好，欢迎来到");

		           if(TenantId==162){
		              	logonContentInfoArray.push("中国科学院党校");	
		           }
		            else if(TenantId==998){
		             	logonContentInfoArray.push("信息素质教育平台");
		           }
		            else if(TenantId==2000){
		                	logonContentInfoArray.push("科住物业培训平台");
		              }
		            else if(TenantId==1001){
		              	 $("#top-bar","#newStyle").html("&nbsp;&nbsp;");
		                	logonContentInfoArray.push("中关村医院教育平台");
		              }
		            else if(TenantId==997){
		              	logonContentInfoArray.push("\"一带一路\"科技教育平台");	
		           }
		            else if(TenantId==2001){
	                	logonContentInfoArray.push("中共中央统一战线工作部在线学习平台");
	              }
		            else if(TenantId==1006){
	                	logonContentInfoArray.push("上海科技大学在线学习平台");
	              }
		            else{
		            	logonContentInfoArray.push("中国科学院继续教育门户");
		            }
					logonContentInfoArray.push("!&nbsp;&nbsp;<a href='javascript:void(0);' class='logonAction'>登录</a><a href='javascript:void(0);' class='escienceLogin'><img src='./image/escienceLogin.png' /></a></div>");
					logonContent=logonContentInfoArray.join("");
					$(".logo",menuLoadingOptions.$currentPage).html(logoimg+logonContent);
				}
				
			}	
	});
}

function openUrl(url){
	$.cookie("parentmenu","");
	writeLocation(8005,0);  //2:resource表的id ——我的培训班
	window.location.replace(url);
}


function openNavClickInTagetWindow(num,target){
	var openTarget=null;
	if(target==null){
		showBufferMask();
	}
	else{
		openTarget={"target":target};
	}
	var TenantId=menuLoadingOptions.loginInfo.tenantId;
	var url="";
	var inner_outter_flag = 0;
	if(TenantId != 1000 && TenantId != 2001 ) //研究所
		inner_outter_flag = 1;
	if(num==1){
		url="stuLogonAction.do";
		var obj={};
		openLink(url,obj,openTarget);
		return false;
	}
	else if(num==2){
		url="mtMixTrainNewsAction.do";
		var obj={
				method:"queryAllNews",
				newsType:"0",
				inner_outter_flag:inner_outter_flag
		}
		openLink(url,obj,openTarget);
		return false;
	}
	else if(num==3){
		url="msgMessageInfoAction.do";
		var obj={
				method:"queryNotice",
				inner_outter_flag:inner_outter_flag
		}
		openLink(url,obj,openTarget);
		return false;
	}
	else if(num==4){
		url="mtMixTrainNewsAction.do";
		var obj={
				method:"queryAllNews",
				policyAndProcess:"true",
				inner_outter_flag:inner_outter_flag
		}
		openLink(url,obj,openTarget);
		return false;
	}
	else if(num==5){
		url="mtMixTrainScheduleListAction.do";
		var obj={}
		openLink(url,obj,openTarget);
		return false;
	}
	else if(num==6){
		var CurrentMonth=new Date().getMonth()+1;
		url="selectTrainingListAction.do?method=intoFrame";
		var obj={
			month:CurrentMonth
		}
		openLink(url,obj,openTarget);
		return false;
	}
	else if(num==7){
		url="onlineStudy.do?method=intoFrame";
		var obj={}
		openLink(url,obj,openTarget);
		return false;
	}
	else if(num==8){
		url="teacherFrame.do?method=intoFrame";
		var obj={}
		openLink(url,obj,openTarget);
		return false;
	}
	else if(num==9){
		url="frmThreadListAction.do";
		var obj={}
		openLink(url,obj,openTarget);
		return false;
	}
	else if(num==11){
		url="recommendSeriesAction.do?method=intoSeriesFrame";
		var obj={}
		openLink(url,obj,openTarget);
		return false;
	}
	else if(num==10)
		url="#";
	window.location.replace(url);
	
}

function openNavClick(num){
	openNavClickInTagetWindow(num,null);
}  


//点击二级菜单
function openNavClick_Sub(num){
	var TenantId=menuLoadingOptions.loginInfo.tenantId;
	if(operatorInfoOptions.logon==false&&(num==22||num==41||num==42));
	else{
		showBufferMask();	
	}
	
	var url="";
	var inner_outter_flag = 0;
	if(TenantId != 1000  && TenantId != 2001 ) //研究所
		inner_outter_flag = 1;
	//if(num==1)
   //url="stuLogonAction.do";
	//新闻通知
	if(num==21){  
		//新闻
		url="mtMixTrainNewsAction.do";
		var obj={
				method:"queryAllNews",
				newsType:"0",
				inner_outter_flag:inner_outter_flag
		}
		openLink(url,obj);
		return false;
		
	}
	else if(num==22){
		//通知
		if(operatorInfoOptions.logon==false){
			jAlert("&nbsp;&nbsp;&nbsp;&nbsp;请您先登录学习平台！",'友情提示',function(){
				resetTargetUrlAfterLogon("msgMessageInfoAction.do?method=queryNotice&inner_outter_flag="+inner_outter_flag);
				showLoginWin();
	    	});
		}
		else{
			url="msgMessageInfoAction.do"
			var obj={
					method:"queryNotice",
					inner_outter_flag:inner_outter_flag
			}
			openLink(url,obj);
		}
		return false;
	}
	//学习资源
	else if(num==91){
		url = "onlineStudy.do?method=intoFrame";
		var obj={
				resourceType:"31"
		}
		openLink(url,obj);
		return false;
	}
	else if(num==92){
		url = "onlineStudy.do?method=intoFrame";
		var obj={
				resourceType:"32"
		}
		openLink(url,obj);
		return false;
	}
	else if(num==93){
		url = "onlineStudy.do?method=intoFrame";
		var obj={
				resourceType:"33"
		}
		openLink(url,obj);
		return false;
	}
	else if(num==94){
		url = "onlineStudy.do?method=intoFrame";
		var obj={
				resourceType:"34"
		}
		openLink(url,obj);
		return false;
	}
	else if(num==95){
		url = "onlineStudy.do?method=intoFrame";
		var obj={
				resourceType:"35"
		}
		openLink(url,obj);
		return false;
	}
	/*
	else if(num==71){//微课程
		url = "onlineStudy.do?method=intoFrame";
		var obj={
				conditionString:"-r4001"
		}
		openLink(url,obj);
		return false;
	}else if(num==72){//精品课程
		url = "onlineStudy.do?method=intoFrame";
		var obj={
				conditionString:"-r4002"
		}
		openLink(url,obj);
		return false;
	}else if(num==73){//知识地图
		url = "onlineStudy.do?method=intoFrame";
		var obj={
				conditionString:"-r4003"
		}
		openLink(url,obj);
		return false;
	}else if(num==74){//电子阅读
		url = "onlineStudy.do?method=intoFrame";
		var obj={
				conditionString:"-r4004"
		}
		openLink(url,obj);
		return false;
	}else if(num==75){//开放课件
		url = "onlineStudy.do?method=intoFrame";
		var obj={
				conditionString:"-r4005"
		}
		openLink(url,obj);
		return false;
	}else if(num==76){//开放课件
		url = "onlineStudy.do?method=intoFrame";
		var obj={
				conditionString:"-r4006"
		}
		openLink(url,obj);
		return false;
	}
	*/
	//教师资源
	else if(num==81){
		//内聘
		url = "teacherFrame.do?method=intoFrame";
		var obj={
				conditionString:"-t0"
			}
		openLink(url,obj);
		return false;
	}
	else if(num==82){
		//外聘
		url = "teacherFrame.do?method=intoFrame";
		var obj={
				conditionString:"-t1"
			}
		openLink(url,obj);
		return false;
	}
	//培训项目
	else if(num==61){
		//培训项目
		var CurrentMonth=new Date().getMonth()+1;
		url="selectTrainingListAction.do";
		var obj={
			month:CurrentMonth,
			cad_report:5001
		}
		openLink(url,obj);
		return false;
	}
	else if(num==62){
		//学术报告
		var CurrentMonth=new Date().getMonth()+1;
		url="selectTrainingListAction.do";
		var obj={
			month:CurrentMonth,
			cad_report:5002
		}
		openLink(url,obj);
		return false;
	}
	//政策与工作流程
	else if(num==41){
		//政策
		if(operatorInfoOptions.logon==false){
			jAlert("&nbsp;&nbsp;&nbsp;&nbsp;请您先登录学习平台！",'友情提示',function(){
				resetTargetUrlAfterLogon("mtMixTrainNewsAction.do?method=queryAllNews&newsType=1&inner_outter_flag="+inner_outter_flag);
				showLoginWin();
	    	});
		}
		else{
			//url="mtMixTrainNewsAction.do?method=queryAllNews&newsType=1&inner_outter_flag="+inner_outter_flag;
			url="mtMixTrainNewsAction.do";
			var obj={
				method:"queryAllNews",
				newsType:"1",
				inner_outter_flag:inner_outter_flag
			}
			openLink(url,obj);
		}
		return false;
	}
	else if(num==42){
		
		//工作流程
		if(operatorInfoOptions.logon==false){
			jAlert("&nbsp;&nbsp;&nbsp;&nbsp;请您先登录学习平台！",'友情提示',function(){
				resetTargetUrlAfterLogon("mtMixTrainNewsAction.do?method=queryAllNews&newsType=2&inner_outter_flag="+inner_outter_flag);
				showLoginWin();
	    	});
		}
		else{
			//url="mtMixTrainNewsAction.do?method=queryAllNews&newsType=2&inner_outter_flag="+inner_outter_flag;
			url="mtMixTrainNewsAction.do";
			var obj={
					method:"queryAllNews",
					newsType:"2",
					inner_outter_flag:inner_outter_flag
			}
			openLink(url,obj);
		}
		
		return false;
	}
	window.location.replace(url);
}   


function menuimgError(imgObj){
	imgObj.src="/image/headPic/male1.jpg";
}


function clickMyStudy(){
	$.ajax({
		  url:'../findMenu/findStudentMenuForRole.do',
	      type:"POST",
	      async:false,
	      dataType:"text",
	      success:function(data,evt){   
	            var node=$.parseJSON(data);
	            if(node.name != null && node.name != ""){
	            	var url=node.stu;
	            	openUrl(url);
	            	$.cookie("myStudyFlag","1");
	            }else{
	            	jAlert("请您先登录学习平台！",'友情提示',function(){

	            	});
				}
	      }
	})
}

function checkLogon(type){
	var TenantId=menuLoadingOptions.loginInfo.tenantId;
	if(operatorInfoOptions.logon==false){
		jAlert("&nbsp;&nbsp;&nbsp;&nbsp;请您先登录学习平台！",'友情提示',function(){
			var inner_outter_flag = 0;
			if(TenantId != 1000  && TenantId != 2001 ) //研究所
				inner_outter_flag = 1;
			resetTargetUrlAfterLogon("mtMixTrainNewsAction.do?method=queryAllNews&policyAndProcess=true&inner_outter_flag="+inner_outter_flag);
			showLoginWin();
    	});
	}
	else{
		openNavClick(type);
	}
}

function changeRoleAndOpen(url,adminRoleId)
{
   showBufferMask();
   $.ajax({
      type:"POST",
      url:"findMenuAction.do?method=findMenuForRole",
      async:false,
      dataType:"text",
      data:{roleId:adminRoleId},
      success:function(data,evt){ 
      	 openUrl(url);
      }
   });
}

/**
 * 搜索课件、培训班、新闻等
 * */
function searchAndFind1(){
 	var hasSelect = false;
 	var choice = "";
 	var searchTxt = document.getElementById("search-txt");
 	if(searchTxt.value == null || searchTxt.value == ""){
 		jAlert("请输入搜索内容",'友情提示');
        return false;
 	}else{
 		showBufferMask({"targetUrl":encodeURI(encodeURI("advancedSearchListAction.do?keywords_="+searchTxt.value+"&source_category=1,2,3"))});
 	}
 }

/**
 * 高级搜索
 * */
function advancedSearch(){
	var searchTxt = document.getElementById("search-txt");
	var url = "advancedSearchAction.do?method=forAdvancedSearch";
 	if(searchTxt.value != null && searchTxt.value != ""){
 		url = url + "&keyWords=" + searchTxt.value;
 	}
 	url=encodeURI(encodeURI(url));
 	window.open(url);
}

/*
 *搜索课件、书籍或培训，搜索后记录到cookie中
 */
 function searchAndFind(){
 	var hasSelect = false;
 	var choice = "";
 	var searchTxt = document.getElementById("search-txt");
 	var conditions = document.getElementsByName("condition");
 	for(var i = 0; i < conditions.length; i++){
 		if(conditions[i].checked){
 			hasSelect = true;
 			choice = conditions[i].value;
 		}
 	}
 	if(hasSelect==false){
 		hasSelect=true;
 		choice=$("#selectType").val();
 	}
 	if(searchTxt.value == null || $.trim(searchTxt.value).length == 0){
 		jAlert("请输入搜索内容",'友情提示');
 		return false;
 	}
 	/*
 		如果选中的某一个，
 		首先将form表单的action填写，将form的input的name属性和value属性填写，并且提交查询表单
 		其次，将查询选项和查询内容写入到cookie
 	*/
 	if(hasSelect && choice != ""){
		switch(choice){
			case "1" : 
				$("#studentSearchForm").attr("action","selectTrainingListAction.do");
				$("#studentSearchFormInput").attr("name","trainName").attr("value",searchTxt.value);
				$("#studentSearchForm").submit();
				break;
			case "2" : 
				$("#studentSearchForm").attr("action","selectCourseListAction.do");
				$("#studentSearchFormInput").attr("name","courseName").attr("value",searchTxt.value);
				$("#studentSearchForm").submit();
				break;
			case "3" : 
				$("#studentSearchForm").attr("action","rsmRcmbookReadListAction.do");
				$("#studentSearchFormInput").attr("name","bookName").attr("value",searchTxt.value);
				$("#studentSearchForm").submit();
				break;
			default : jAlert("没有这个选项",'友情提示');
		}
 	}else{
 		jAlert("请选择搜索类别",'友情提示');
 	}
 }
 
 
 function mouseoutFontColor(obj,currentSkinId){
		if(currentSkinId==5)
			obj.color='#3a80c1';
		else
			obj.color='white';
}
 

;(function($){
	$.extend({
		"loadSimpleNavi":function(){
			menuLoadingOptions.loadSimpleNavi();
		},
		"reloadLoginInfo":function(){
			//加载角色切换链接
			  menuLoadingOptions.loadTopMenu();
			  
			  //加载登录链接及学时提醒
			  menuLoadingOptions.loadLoginNav();
		}
	
	});
	
	$.fn.extend({
		changeRole:function(actionAfterChanged){
			var $role=this;
			var params={};
			var roleId=$("input[name=roleId]",$role).val();
			params.roleId=roleId;
			$.ajax({
				type:"POST",
			    url:"findMenuAction.do?method=changeRole",
			    dataType:"json",
			    data:params,
				success:function(data){
					if(data.status){
						if(typeof actionAfterChanged=="function"){
							actionAfterChanged();
						}
						else{
							window.location.href=data.link;
						}
					}
				}
			})
		}
	})
})(jQuery); 

 