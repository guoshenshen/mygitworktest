jQuery.cookie=function(name,value,options){if(typeof value!='undefined'){options=options||{};if(value===null){value='';options.expires=-1;}
var expires='';if(options.expires&&(typeof options.expires=='number'||options.expires.toUTCString)){var date;if(typeof options.expires=='number'){date=new Date();date.setTime(date.getTime()+(options.expires*24*60*60*1000));}else{date=options.expires;}
expires='; expires='+date.toUTCString();}
var path=options.path?'; path='+(options.path):'';var domain=options.domain?'; domain='+(options.domain):'';var secure=options.secure?'; secure':'';document.cookie=[name,'=',encodeURIComponent(value),expires,path,domain,secure].join('');}else{var cookieValue=null;if(document.cookie&&document.cookie!=''){var cookies=document.cookie.split(';');for(var i=0;i<cookies.length;i++){var cookie=jQuery.trim(cookies[i]);if(cookie.substring(0,name.length+1)==(name+'=')){cookieValue=decodeURIComponent(cookie.substring(name.length+1));break;}}}
return cookieValue;}};


var trainClassMenuName=[
	'培训首页',
	'必修课程',
//	'线下日程',
	'培训作业',
	'班级成员',
	'班级相册',
	'培训总结',
	'新闻动态',
//	'自测考试',
//	'培训调查',
	'培训考试'
//	'讨论交流'
];

var $left=$(window).width();
var $top=$(window).height();

//定义培训班菜单url数组
var trainClassMenuUrl=[
	'../onlineTraining/viewTrain4Admin.do?trainId=tid',
	'../mtMixTrainSchedule/listScheduleItem.do?trainId=tid',
//	'mtMixTrainScheduleAction.do?method=listScheduleItem2&trainId=tid',
	'../assignment/showAssignmentListForStudent.do?trainId=tid',
	'../mtMixTrainUserTrainInfo/execute.do?train_id=tid',
	'../mtMixTrainPhoto/readList.do?trainId=tid',
	'../trainSummary/getTrainingSummary.do?train_id=tid',
	'../mtTrainNews/queryForNews.do?trainId=tid',
//	'myExam.do?method=searchSelfTestExam&testExamFlag=train&classifyId=tid',
//	'tnNeedsAction.do?method=myTnNeeds&trainId=tid',
	'myExam.do?method=toMyExamHome&trainId=tid'
//	'mtMixTrainingDiscussAction.do?method=list&bookId=tid'
];


//显示培训项目对应的模板图片
function showTitleBandPic(){
	 
 	var trainId=$('#_trainId').val();
 	
 	var defaultTrainPicMap={
 				'2120':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2120.jpg",
 				'2121':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2121.jpg",
 				'2170':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2170.jpg",
 				'2180':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2180.jpg",
 				'2192':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2192.jpg",
 				'2193':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2193.jpg",
 				'2197':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2197.jpg",
 				'2160':"http://www.casmooc.cn/uploadFile/picturebase/trainType_2160.jpg",
 				'2263':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2263.jpg",
 				'2264':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2264.jpg",
 				'2265':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2265.jpg",
 				'2241':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2241.jpg",
 				'2242':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2242.jpg",
 				'2243':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2243.jpg",
 //				'2244':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2244.jpg",
 				'2255':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2255.jpg",
 				'2256':"http://www.casmooc.cn/uploadFile/picturebase/trainMode_2256.jpg" 			
 	}
 	
 	var trainCookieInfo=$.cookie("trainId_"+trainId);
 	if(trainCookieInfo==null){
 		//获取培训班标题大图
 		$.ajax({
            url:'../onlineTraining/getTrainTopBand.do',
            type:'post',
            data:{trainId:trainId},
            dataType:"json",
            success:function(data){
                var picUrl = data.data.picUrl;
                var needWords = data.data.needWords;
                var trainTypeId = data.data.trainTypeId;
                var trainTenantId = data.data.tenantId;
                var trainMode = data.data.trainMode;
                if(picUrl==null || $.trim(picUrl).length==0){
                    //使用默认背景图片
                    if(trainTenantId=="1" && trainTypeId=="2197"){
                        $("#headerbody").addClass(""+"_trainMode_"+trainMode);
                        $("#backgroundImg img").attr("src",defaultTrainPicMap[trainMode]);
                        $.cookie("trainId_"+trainId,JSON.stringify({"trainMode":trainMode}));
                    } else{
                        $("#headerbody").addClass(""+"_trainType_"+trainTypeId);
                        $("#backgroundImg img").attr("src",defaultTrainPicMap[trainTypeId]);
                        $.cookie("trainId_"+trainId,JSON.stringify({"trainTypeId":trainTypeId}));
                    }
                } else{
                    $("#headerbody").css("background-image","url("+picUrl+")");
                    $("#backgroundImg img").attr("src",picUrl);
                    $.cookie("trainId_"+trainId,JSON.stringify({"picUrl":picUrl}));
                    if(needWords=="0"){
                        $("#head-content").hide();
                    }
                }
                changeBackgroundStyle();
            }
 		})
 	} else{
 		trainCookieInfo=$.parseJSON(trainCookieInfo);
 		if(trainCookieInfo.picUrl != null){
 			$("#headerbody").css("background-image","url("+trainCookieInfo.picUrl+")");
			$("#backgroundImg img").attr("src",trainCookieInfo.picUrl);
 		} else if(trainCookieInfo.trainMode != null){
 			$("#headerbody").addClass(""+"_trainMode_"+trainCookieInfo.trainMode);
			$("#backgroundImg img").attr("src",defaultTrainPicMap[trainCookieInfo.trainMode]);
 		} else{
 			$("#headerbody").addClass(""+"_trainType_"+trainCookieInfo.trainTypeId);
			$("#backgroundImg img").attr("src",defaultTrainPicMap[trainCookieInfo.trainTypeId]);
 		}
 		changeBackgroundStyle();
 	}
 }

//根据培训大图调整培训项目页面的背景样式
function changeBackgroundStyle(){
	var headImage=$("#headerbody").css("background-image");
	$(".animate").fadeIn(1500);
	/*
  	var browserTypeAndVersion=searchBrowserTypeAndVersion();
  	$("body").addClass("background-skin");
  	if(browserTypeAndVersion.type=="IE"&&browserTypeAndVersion.version<9){
  		$("#backgroundImg").show();
  	}
  	else{
  		$("body").css("background-image",headImage);	
  	}
  	var resetHeight=$(window).height()>$("body").height()?$(window).height():$("body").height();
  	$("body").css("height",resetHeight);
  	$("#backgroundImg").css("height",resetHeight);
  	$("body #bufferMask").css("height",resetHeight);
  	*/
  }

//加载培训项目的导航栏
function showMenu(joined){
	var menuArr=new Array();
	
	for(var i=0;i<trainClassMenuName.length;i++){
		menuArr[i]="<li><a href='javascript:void(0);' class='menu'>"+trainClassMenuName[i]+"</a></li>";
	}
	
	var className=$("#trainClassMenu").attr("class");
	
	var $menu=$('#trainClassMenu ul');
	
	var openMenuArray=new Array(0,6);   //17年zxy需求，非班级成员只能看到新闻和首页
	//[2014.3.13需求：只有培训日程，培训考试，调查，相片是外部人员不能看到的，其他都是普通人就可以看到的](作废)
	
	var openMenuLink = new Array();
	
	var trainId=$('#_trainId').val();
	
	for(var i=0;i<menuArr.length;i++){
    	var singleMenu=menuArr[i];
    	if(joined == -1){
    		$menu.append(singleMenu);
    		openMenuLink.push(trainClassMenuUrl[i]);
    		break;
    	}else if(joined == 0){
    		if($.inArray(i,openMenuArray)>=0){
    			$menu.append(singleMenu);
    			openMenuLink.push(trainClassMenuUrl[i]);
    		}
    	}else if(joined != -1 && joined != 0){
    	    $menu.append(singleMenu);
    	    openMenuLink.push(trainClassMenuUrl[i]);
    	}
    }
	var menuLi = $menu.find('li');
	var n = $.cookie('currentTrainMenu');
	if(className == "enterTrain"){
		menuLi.eq(0).addClass("currentMenu");
	} else{
		menuLi.eq(n).addClass("currentMenu");
	}
	menuLi.each(function(index,that){
			$(that).click(function(){
				var openMenuIndex = index;
				$.ajax({
					url:'../eosOperator/ifOnline.do',
					type:'post',
				    dataType:"json",
				    success:function(data){
						if(data.data.operatorId == -1){
							$.cookie("lastUser",data.data.operatorId);
							openMenuIndex = 0;
							$.cookie('currentTrainMenu',0);
						} else if(($.cookie("lastUser")!=null) && $.cookie("lastUser") != data.data.operatorId){
							$.cookie("lastUser",data.data.operatorId);
							openMenuIndex = 0;
							$.cookie('currentTrainMenu',0);
						} else{
							$.cookie('currentTrainMenu',openMenuIndex);
							$menu.find(".currentMenu").removeClass("currentMenu");
							$(that).addClass("currentMenu");
						}
						window.location.href=openMenuLink[openMenuIndex].replace("tid",trainId);
					},
					error:function(data){
						$.cookie("lastUser",-1);
						$.cookie('currentTrainMenu',0);
						openMenuIndex = 0;
						window.location.href=openMenuLink[openMenuIndex].replace("tid",trainId);
					}
				})
			})
		}
	)
}


//判断当前用户是否参加了培训班并加载导航栏
function hasJoinedTrain(trainId){
	
	$.ajax({
		url:'../onlineTraining/getIfUserJoinTrain.do',
		type:'post',
		data:{"trainId":trainId},
	    dataType:"json",
	    success:function(data){
			showMenu(data.data.joined);
			$.cookie("train_"+trainId+"_joined",data.data.joined);
		},
		error:function(data){
			showMenu(-1);
		}
	})
}

//初始化培训班菜单
$(document).ready(function(){

	showTitleBandPic();
	
	loadSectionHeaderEffect();
	
	var trainId=$('#_trainId').val();
	
	var userId=$('#_userId').val();
	
	var joined=-1;
	
	var className=$("#trainClassMenu").attr("class");
	if(className=="enterTrain"){
		$.cookie('currentTrainMenu',0);
	}
	
	var currentMenuIndex=$.cookie('currentTrainMenu');
	
	if(userId==null || ($.trim(userId)!=-1 && $.trim(userId)!=0)){
		var cookieUser=$.cookie("lastUser");
		var joinStatus=$.cookie("train_"+trainId+"_joined");
		if(joinStatus==null || (userId!=null&&cookieUser!=userId) || currentMenuIndex==null || currentMenuIndex==0){
			$.cookie("lastUser",userId);
			hasJoinedTrain(trainId);
		} else{
			showMenu(joinStatus);
		}
	} else{
		showMenu(joined);
	}
})


//显示新闻通知的具体信息
// css/jquery-UI/remodal-default-theme.css, css/jquery-UI/remodal.css are needed 
// js/UI/remodal.min.js is needed
// options.newsId is necessary
function showTrainNewsDetailInfoWin(options){
	window.REMODAL_GLOBALS = {
	  NAMESPACE: 'modal',
	  DEFAULTS: {
	    hashTracking: false
	  }
	};

	$('[data-remodal-id=modal]').remodal(options);	
	$(document).on('opening', '.remodal', function () {
	    var modal = $(this);
	    $.ajax({
			url:'../mtTrainNews/getNewsDetailInfo.do',
			type:'post',
			data:{'newsId':options.newsId},
		    dataType:"json",
			success:function(data){
				if(data.status){
					$("#notice-content .notice-title").html(data.data.newsTitle);
					$("#notice-content .notice-body").html(data.data.newsContent);
					$("#notice-content .notice-time").html(data.data.createTime);
				} else{
					
				}
			}
	    });
	});

	$(document).on('opened', '.remodal', function () {
	    var modal = $(this);
	});
	 
	$(document).on('closing', '.remodal', function () {
	    var modal = $(this);
	});
	 
	$(document).on('closed', '.remodal', function () {
	    var modal = $(this);
	});
	 
	$(document).on('confirm', '.remodal', function () {
	    var modal = $(this);
	});
	 
	$(document).on('cancel', '.remodal', function () {
	    var modal = $(this);
	});
}

function notShowNews(){
	var inst = $('[data-remodal-id=modal]').remodal();
	inst.close();
}

//控制内容尺寸
function changeNoticeContent(cid){
     var choice = cid;
     switch(choice){
			case 1 : $("#noticeContent").css("font-size","15px");break;
			case 2 : $("#noticeContent").css("font-size","12px");break;
			case 3 : $("#noticeContent").css("font-size","9px");break;
			default : alert("请选择文字大小!");
		}
}

//加载选项卡切换样式
function loadSectionHeaderEffect(){
	$(".sectionHeader .section-header-title").hover(
		function(){
			$(this).addClass("hoverHeader");
			$(".sectionHeader .section-header-title").each(function(index,that){
				if($(that).hasClass("hoverHeader")){

				} else{
					$(that).addClass("unhoverHeader");
				}
			})
		},
		function(){
			$(this).removeClass("hoverHeader");
			$(".unhoverHeader").removeClass("unhoverHeader");
		}
	)
}
