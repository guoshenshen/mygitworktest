/****本页面封装了用户空间页面的js,本js页面的方法均为非公共方法,除学员空间页面外请勿调用***/

//按姓名检索粉丝/关注人员
function searchFollowsOrFansByName(){
	$(".follow-modal-user-box-wrapper").removeClass("notShow");
	$(".follow-modal-user-box-wrapper").removeClass("show");
	var infoName=$("#search-info").val();
	if(infoName==null||$.trim(infoName)==""){
		// do nothing
		return 
	}
	else{
		infoName=$.trim(infoName);
		//$("#userName_"+infoName).parents(".follow-modal-user-box-wrapper").addClass("show");
		//$(".follow-modal-user-box-wrapper").addClass("notShow");
		$("#search-info").val(infoName);
		
		$(".follow-modal-user-box-wrapper").each(function(index,that){
			var memberName=$(".nickname",$(that)).html();
			if(memberName.indexOf(infoName)<0){
				$(that).removeClass("show").addClass("notShow");
			}
			else{
				$(that).removeClass("notShow").addClass("show");
			}
		})
	}
}

//重置关注人员、粉丝、关注机构数目
function resetMemberNum(param){
	if(param!=null&&param.memberType!=null){
		var memberType=""+param.memberType;
		var memberLength=param.length;
		if(memberLength==null){
			memberLength=$(".follow-modal-body-wrapper .scrollable-wrapper .box").length-$(".follow-modal-body-wrapper .scrollable-wrapper .removed").length;
		}
		if(memberType=="0"){
			$("font.focusPeopleCount").html(memberLength);
		}
		else if(memberType=="1"){
			$("font.fansCount").html(memberLength);
		}
		else if(memberType=="2"){
			$("font.focusOrgCount").html(memberLength);
		}
	}
}


//显示当前空间主人关注的人员、粉丝列表
//param:{
//	memberType:0：关注人员 ;1：粉丝;2：关注机构
//}
function showMemberList(param){
	$("#search-info").val("");
	if(userZoneFrameArea.canClick){
		userZoneFrameArea.canClick=false;
	}
	else{
		return;
	}
	if(param.memberType=="0"){
		$(".concernAndFans").removeClass("showFans").removeClass("showConcernOrgs").addClass("showConcern");
	}
	else if(param.memberType=="1"){
		$(".concernAndFans").removeClass("showConcern").removeClass("showConcernOrgs").addClass("showFans");
	}
	else {
		$(".concernAndFans").removeClass("showConcern").removeClass("showFans").addClass("showConcernOrgs");		
	}
	$.ajax({
		url : "interactionAction.do?method=listFansOrFollows",
		method : "post",
		data : param,
		dataType : "json",
		success : function(data){
			if (data.result == "true") {
				var memberList = data.memberList;
				var member = null;
				var userinfoArray = new Array();
				var memberLength=memberList.length;
				resetMemberNum({"memberType":param.memberType,"length":memberLength})
				$(".follow-modal-body-wrapper .scrollable-wrapper").empty();
				var memberListLength=memberList.length;
				for (var i=0;i<memberListLength;i++) {
					member = memberList[i];
					userinfoArray.push("<div class='follow-modal-user-box-wrapper'><div class='box'><a href='javascript:void(0);' class='zone-headPic u-userCardTrigger'>");
					//关注的机构(获取机构orgId,机构orgName)
					if (data.listType=="0") {
						userinfoArray.push("<input type='hidden' class='infobox_userId' value='"+member.orgId+"'></input> ");
			        	userinfoArray.push("<input type='hidden' class='infobox_userName' value='"+member.orgName+"'></input> ");
			        	userinfoArray.push("<input type='hidden' class='infobox_itemType' value='0'></input> ");
						userinfoArray.push("<input type='hidden' id='userName_"+member.orgName+"' />");
					}
					//关注的人、粉丝（获取人员姓名,operatorId）
					else {
						userinfoArray.push("<input type='hidden' class='infobox_userId' value='"+member.operatorId+"'></input> ");
			   		    userinfoArray.push("<input type='hidden' class='infobox_userName' value='"+member.operatorName+"'></input> ");
						userinfoArray.push("<input type='hidden' id='userName_"+member.operatorName+"' />");
						userinfoArray.push("<input type='hidden' class='infobox_itemType' value='1'></input> ");
					}
					var headPic=null;
					if (data.listType=="0") {
						headPic=member.logo;
						if($.trim(headPic)==""){
							headPic="/image/headPic/defaultOrg.png";
						}
						userinfoArray.push("<img src='" + headPic + "' class = 'headPic orgPic infobox_headPic' /></a>");										
					}
					else{
						headPic=member.address;
						if($.trim(headPic)==""){
							if(member.gender=="2"){
								headPic="/image/headPic/female1.jpg";
							}
							else{
								headPic="/image/headPic/male1.jpg";
							}
							
						}
						userinfoArray.push("<img src='" + headPic + "' class = 'headPic infobox_headPic' /></a>");						
					}
					
					userinfoArray.push("<div class='personal-info'><div class='nickname'>");
					if (data.listType=="0") {
						userinfoArray.push(" " + member.orgName + "</div>");
						userinfoArray.push("<div class='info'>");
			        	userinfoArray.push("<span class='member-type'>" + member.parentOrgName + "&nbsp;&nbsp;</span>");				
					}
					else {
						userinfoArray.push("" + member.operatorName + "</div>");
						userinfoArray.push("<div class='info'>");
			        	userinfoArray.push("<span class='member-type'>" + member.orgName + "&nbsp;&nbsp;</span>");				
					}
			        userinfoArray.push("</div>");
			        userinfoArray.push("<div class='remove'><div class='removeButton'><input type='hidden' name='memberType' value='"+param.memberType+"'></div></div><div class='mask'></div>");
			        userinfoArray.push("</div></div></div>");
			        userinfoArray.push();
			        if ((i + 1) % 60 == 0 || i == memberLength - 1) {
						$(".follow-modal-body-wrapper .scrollable-wrapper").append(userinfoArray.join(""));
						userinfoArray = new Array();
			        }
				}
				$("img.headPic").one("error",function(){    	
			    	if($(this).hasClass("orgPic")){
						$(this).attr('src',"/image/headPic/defaultOrg.png");
					}
					else{
						$(this).attr('src',"/image/headPic/male1.jpg");
					}
			    });	
			}
			else {
				
			}
			userZoneFrameArea.canClick=true;
		},
		error : function(){
			userZoneFrameArea.canClick=true;
		}
	});	
}


//删除粉丝/关注对象
function removeFansOrConcern(){
	//获取当前操作人员的operatorid
	var currentOperatorId="";
	//获取当前页面隶属学员operatorId
	var currentZoneHost="";
	//判断当前操作人员与当前空间隶属人员是否相同,不相同情况下，操作人员无权进行删除操作
	if($(".zone-userInfo input[name=currentOperatorId]").length>0){
		currentOperatorId=$.trim($(".zone-userInfo input[name=currentOperatorId]").val());
	}
	if($(".zone-userInfo input[name=operatorId]").length>0){
		currentZoneHost=$.trim($(".zone-userInfo input[name=operatorId]").val());
	}
	if(currentOperatorId!=""&&currentZoneHost==currentOperatorId){
		$(".follow-modal-body-wrapper .scrollable-wrapper").addClass("ableRemove");
		$(".follow-modal-body-wrapper .scrollable-wrapper").on("click",".removeButton",function(){
			if(!userZoneFrameArea.canClick){
				return;
			}
			else{
				userZoneFrameArea.canClick=false;
				var $box=$(this).parents(".box");
				var userId=$box.find(".infobox_userId").val();
				var memberType=$("input[name='memberType']",$(this)).val();
				$.ajax({
						url: "interactionAction.do?method=deleteFansAndConcern",
						dataType: "json",
						method: "post",
						data: {"userId":userId,"memberType":memberType},
						success: function(data){
							if(data.result=="true"){
								$box.addClass("removed");
								resetMemberNum({"memberType":memberType});
							}
							userZoneFrameArea.canClick=true;
						},
						error:function(){
							userZoneFrameArea.canClick=true;
						}
					})
			}
		})
	}		
}

//加载某选项卡信息
//param{
//	id:选项卡id
//  info:显示内容
//}
function loadTabDetailInfo(param){
	if(userZoneFrameArea.currentTab==null){
		userZoneFrameArea.currentTab={};
	}
	var currentTab=userZoneFrameArea.currentTab;
	var currentTabId=null;
	if(currentTab!=null){
		currentTabId=currentTab.id;
	}
	if(param.id==currentTabId){
		//被点击的选项卡即为当前选项卡
		//不执行任何操作
	}
	else{
		currentTab.id=param.id;
		currentTab.info=param.info;
		$("#mainbody .j-infoBox").html(currentTab.info);
		$(".currentTab","#mainbody").removeClass("currentTab");
		$("#"+currentTab.id,"#mainbody").addClass("currentTab");
	}
	$("img.lazy",$("#mainbody .j-infoBox")).lazyload({
		threshold : 10,
    	effect : "fadeIn"
	})
}

//绑定动作到课程选学、培训参与、课程发布、培训发布页面
function bindActionForTab(){
	var itemType=$(".zone-userInfo input[name=itemType]").val();
	$("#mainbody .j-userTab").on("click",".Tab",function(){
		if(userZoneFrameArea.canClick){
			userZoneFrameArea.canClick=false;
		}
		else{
			return;
		}
		var currentZoneHost=-1;
		if($(".zone-userInfo input[name=operatorId]").length>0){
			currentZoneHost=$.trim($(".zone-userInfo input[name=operatorId]").val());
		}
		var targetTab=userZoneFrameArea[this.id];
		if(targetTab==null){
			//尚未加载
			targetTab={};
			userZoneFrameArea[this.id]=targetTab;
			var options={
				method:"post",
				dataType:"json"
			}
			options.complete=function(){
				userZoneFrameArea.canClick=true;
			}
			options.error=function(){
				var clickedTab=this;
				targetTab.id=clickedTab.id;
				targetTab.info="<div class='empty'><div class='emptyImg'></div></div>";
				loadTabDetailInfo(targetTab);
			}
			switch(this.id){
				case "courseJoined":
					var clickedTab=this;
					options.data={"operatorId":currentZoneHost};
					options.url="../interaction/listCoursesOfSpecifiedUser.do";
					options.success=function(data){
						if(data.status) {
							targetTab.id=clickedTab.id;
							targetTab.info=$.loadVisualCourseList(data.data);
							loadTabDetailInfo(targetTab);
						}
						else{
							targetTab.id=clickedTab.id;
							targetTab.info="<div class='empty'><div class='emptyImg'></div></div>";
							loadTabDetailInfo(targetTab);
						}
					};
					$.ajax(options);
					break;
				case "trainJoined":
					var clickedTab=this;
					options.data={"operatorId":currentZoneHost};
					options.url="../interaction/listTrainsOfSpecifiedUser.do";
					options.success=function(data){
						if(data.status) {
							targetTab.id=clickedTab.id;
							targetTab.info=$.loadVisualTrainList(data.data);
							loadTabDetailInfo(targetTab);
						}
						else{
							targetTab.id=clickedTab.id;
							targetTab.info="<div class='empty'><div class='emptyImg'></div></div>";
							loadTabDetailInfo(targetTab);
						}
					};
					$.ajax(options);
					break;
				case "coursePublish":
					var clickedTab=this;
					options.data={"orgId":currentZoneHost};
					options.url="interactionAction.do?method=listReleasedCoursesOfSpecifiedOrg";
					options.success=function(data){
						if(data.result=="true") {
							targetTab.id=clickedTab.id;
							targetTab.info=$.loadVisualCourseList(data.courseList);
							loadTabDetailInfo(targetTab);
						}
						else{
							targetTab.id=clickedTab.id;
							targetTab.info="<div class='empty'><div class='emptyImg'></div></div>";
							loadTabDetailInfo(targetTab);
						}
					};
					$.ajax(options);
					break;
				case "trainPublish":
					var clickedTab=this;
					options.data={"orgId":currentZoneHost};
					options.url="interactionAction.do?method=listReleasedTrainsOfSpecifiedOrg";
					options.success=function(data){
						if(data.result=="true") {
							targetTab.id=clickedTab.id;
							targetTab.info=$.loadVisualTrainList(data.trainList);
							loadTabDetailInfo(targetTab);
						}
					};
					$.ajax(options);
					break;
				case "userBasicInfo":
					var clickedTab=this;
					options.data={"operatorId":currentZoneHost};
					options.url="../eosorgTEmployee/JSONFullDetail.do";
					options.success=function(result){
						
						if(result.status){
							var data = result.data;
							targetTab.id = clickedTab.id;
							var defaultInfo = "还没有填写";
							var infoArray = new Array();
							var orgName = $.trim(data.orgName);
							var job = $.trim(data.job);
							var position = $.trim(data.position);
							var regDate = $.trim(data.regDate);
							var cssArray = new Array();
							if(orgName==""){
								orgName=defaultInfo;
								cssArray.push("grey");
							} else{
								cssArray.push("");
							}
							if(job==""){
								job=defaultInfo
								cssArray.push("grey");
							} else{
								cssArray.push("");
							}
							if(position==""){
								position=defaultInfo;
								cssArray.push("grey");
							} else{
								cssArray.push("");
							}
							if(regDate==""){
								position=defaultInfo;
								cssArray.push("grey");
							} else{
								cssArray.push("");
							}
							var cssArrayIndex=0;
							infoArray.push("<ul id='userInfo'>");
							infoArray.push("<li><div class='title'>所属单位：</div><div class='content "+cssArray[cssArrayIndex++]+"'>"+orgName+"</div></li>");
							infoArray.push("<li><div class='title'>职务：</div><div class='content "+cssArray[cssArrayIndex++]+"'>"+job+"</div></li>");
							infoArray.push("<li><div class='title'>岗位：</div><div class='content "+cssArray[cssArrayIndex++]+"'>"+position+"</div></li>");
							infoArray.push("<li><div class='title'>入职时间：</div><div class='content "+cssArray[cssArrayIndex++]+"'>"+regDate+"</div></li>");
							infoArray.push("</ul>");
							targetTab.info=infoArray.join("");
							loadTabDetailInfo(targetTab);
						}
					};
					$.ajax(options);
					break;
			}
			
		}
		else{
			loadTabDetailInfo(targetTab);
			userZoneFrameArea.canClick=true;
		}	
	});
	//默认触发人员基本信息加载
	$("#mainbody .j-userTab .Tab")[0].click();
}