
var defaultPic={
	"-1":[
	      '../../image/headPic/male1.jpg'
	      ],
	//课程
	"0":[
	     '../../image/defaultPic/course1.jpg',
	     '../../image/defaultPic/course2.jpg',
	     '../../image/defaultPic/course3.jpg',
	     '../../image/defaultPic/course4.jpg',
	     '../../image/defaultPic/course5.jpg',
	     '../../image/defaultPic/course6.jpg',
	     '../../image/defaultPic/course7.jpg',
	     '../../image/defaultPic/course8.jpg',
	     '../../image/defaultPic/course9.jpg',
	     '../../image/defaultPic/course10.jpg',
	     '../../image/defaultPic/course11.jpg',
	     '../../image/defaultPic/course12.jpg',
	     '../../image/defaultPic/course13.jpg',
	     '../../image/defaultPic/course14.jpg',
	     '../../image/defaultPic/course15.jpg',
	     '../../image/defaultPic/course16.jpg',
	     '../../image/defaultPic/course17.jpg',
	     '../../image/defaultPic/course18.jpg',
	     '../../image/defaultPic/course19.jpg',
	     '../../image/defaultPic/course20.jpg',
	     '../../image/defaultPic/course21.jpg',
	     '../../image/defaultPic/course22.jpg',
	     '../../image/defaultPic/course23.jpg',
	     '../../image/defaultPic/course24.jpg',
	     '../../image/defaultPic/course25.jpg',
	     '../../image/defaultPic/course26.jpg',
	     '../../image/defaultPic/course27.jpg'
	     
	],
	//培训
	"1":[
	     '../../image/defaultPic/train1.png',
	     '../../image/defaultPic/train2.png',
	     '../../image/defaultPic/train3.png',
	     '../../image/defaultPic/train4.png',
	     '../../image/defaultPic/train5.png'
	],
	//新闻
	"2":[
	     '../../image/defaultPic/news1.png'
	     
	],
	
	//专题
	"4":[
	     '../../image/defaultPic/series1.png',
	     '../../image/defaultPic/series2.png',
	     '../../image/defaultPic/series3.png',
	     '../../image/defaultPic/series4.png',
	     '../../image/defaultPic/series5.png'
	],
	//部门
	"7":[
	     '../../image/headPic/defaultOrg.png'
	],
	//教师
	"8":[
	     '../../image/defaultPic/teacher1.png',
	     '../../image/defaultPic/teacher2.png',
	     '../../image/defaultPic/teacher3.png',
	     '../../image/defaultPic/teacher4.png',
	     '../../image/defaultPic/teacher5.png',
	     '../../image/defaultPic/teacher6.png'
	]
		
}





function imgError(params){
	var type=params.type;
	var target=params.target;
	var imgArray=defaultPic[type];
	var length=imgArray.length;
	var index=Math.floor(Math.random()*length);
	target.src=imgArray[index];
}


;(function($){	
	
	$.fn.extend({
		searchInfo:function(actionAfterSearched){
			var searchTxt=this.find("#search-info").val();
			if($.trim(searchTxt)==""){
				//显示全部
				$(".highlight",this).removeClass("highlight");
				this.find("table.currentTable tr.notShow").removeClass("notShow");
			}
			else{
				var rowLength=0;
				searchTxt=$.trim(searchTxt);
				this.find("table.currentTable tr").each(function(index,that){
					if(!$(that).hasClass("title")){
						var infoContained=false;
						$(that).find("td").each(function(index1,that1){
							var text=$(that1).text();
							if(text!=null&&text.indexOf(searchTxt)!=-1){
								//包含
								infoContained=true;
								$(that1).addClass("highlight");
							}
							else{
								$(that1).removeClass("highlight");
							}
						});	
						if(infoContained==false){
							$(that).addClass("notShow");
						}
						else{
							$(that).removeClass("notShow");
						}
					}
				})
			}
			if(typeof actionAfterSearched=="function"){
				actionAfterSearched();
			}
		},
		
		
		//显示学员列表信息
		showMemberList:function(memberList){
			var $currentWidget = this;
			var userListArray = new Array();
			var length = memberList.length;
			userListArray.push('<tr class="tableTh" id="train_tableTh"><th><span class="pagebodybufferMask"></span><span>姓名</span></th><th><span class="pagebodybufferMask"></span><span>组织机构</span></th><th><span class="pagebodybufferMask"></span><span>邮箱</span></th></tr>');
			for(var i=0;i<length;i++){
				var currentUser = memberList[i];
				if(i%2==0){
					userListArray.push("<tr class='even'>");
				} else{
					userListArray.push("<tr class='odd'>");
				}
				
				userListArray.push("<td align='center'>"+currentUser.operatorName+"</td>");
				userListArray.push("<td align='center'>"+currentUser.orgName+"</td>");
				userListArray.push("<td align='center'>"+currentUser.email+"</td>");
				userListArray.push("</tr>");
			}
			$currentWidget.empty().append(userListArray.join(""));
			return $currentWidget;
		},

		//显示学员名片列表
		showMemberCardList:function(memberList){
			var $currentWidget = this;
			$currentWidget.addClass("follow-modal-body-wrapper").empty();
			var memberListLength = memberList.length;
			var userinfoArray = new Array();
			for (var i=0;i<memberListLength;i++) {
				member = memberList[i];
				userinfoArray.push("<div class='follow-modal-user-box-wrapper'><div class='box'><a href='javascript:void(0);' class='zone-headPic u-userCardTrigger'>");
				userinfoArray.push("<input type='hidden' class='infobox_userId' value='"+member.operatorId+"'/> ");
	   		    userinfoArray.push("<input type='hidden' class='infobox_userName' value='"+member.operatorName+"'/> ");
				userinfoArray.push("<input type='hidden' id='userName_"+member.operatorName+"' />");
				userinfoArray.push("<input type='hidden' class='infobox_itemType' value='1'/> ");
				var headPic = null;
				headPic = member.headPic;
				if($.trim(headPic)==""){
					if(member.gender=="2"){
						headPic="/image/headPic/female1.jpg";
					} else{
						headPic="/image/headPic/male1.jpg";
					}
				}
				userinfoArray.push("<img src='" + headPic + "' class = 'headPic infobox_headPic' /></a>");						
				userinfoArray.push("<div class='personal-info'><div class='nickname'>");
				userinfoArray.push("" + member.operatorName + "</div>");
				userinfoArray.push("<div class='info'>");
	        	userinfoArray.push("<span class='member-type'>" + member.orgName + "&nbsp;&nbsp;</span>");				
		        userinfoArray.push("</div>");
		        userinfoArray.push("<div class='remove'><div class='removeButton'><input type='hidden' name='memberType' value='1'></div></div><div class='mask'></div>");
		        userinfoArray.push("</div></div></div>");
		        userinfoArray.push();
		        if ((i + 1) % 60 == 0 || i == memberListLength - 1) {
					$currentWidget.append(userinfoArray.join(""));
					userinfoArray = new Array();
		        }
			}
			$("img.headPic").one("error",function(){    	
		    	if($(this).hasClass("orgPic")){
					$(this).attr('src',"/image/headPic/defaultOrg.png");
				} else{
					$(this).attr('src',"/image/headPic/male1.jpg");
				}
		    });	
			return $currentWidget;
		}
		
		
		
		
	})
})(jQuery);





//*****************jquery.infobox.js is needed for functions below*******************************
//parameter if exists, it should contain such element: operatorId, itemType
function intoUserZone(parameter){
	var param=parameter;
	var $form=openLink("../interaction/intoUserZone.do",param,{"target":"_blank"});
	$form.remove();
	$.hideUserCard();
}


//关注学员
function focusUser(actionAfterFocused){
	var param={};
	$("#linkInfo input").each(function(){
		param[this.name]=this.value;
	});
	$.ajax({
		url:"interactionAction.do?method=addFollows",
		method:"post",
		data:param,
		dataType:"json",
		success:function(data){
			if(typeof actionAfterFocused=="function"){
				actionAfterFocused(data);
			}
		}	
	})
}

//取消学员关注
function unfocusUser(actionAfterUnFocused){
	var param={};
	$("#linkInfo input").each(function(){
		param[this.name]=this.value;
	});
	$.ajax({
		url:"interactionAction.do?method=unfollows",
		method:"post",
		data:param,
		dataType:"json",
		success:function(data){
			if(typeof actionAfterUnFocused=="function"){
				actionAfterUnFocused(data);
			}
		}	
	})
}

//************************************************************************************************

//remodal.min.js is needed here
//显示用户关注人员及粉丝页面
function bindUserConcernFansFrame(param){
	
	window.REMODAL_GLOBALS = {
			  NAMESPACE: 'modal',
			  DEFAULTS: {
			    hashTracking: false
			  }
			};
	var popWin = $('[data-remodal-id=modal]').remodal();
	
	$('[data-remodal-id=modal]').remodal(param);	
	$(document).on('opening', '.remodal', function (param) {
	    var modal = $(this);
	    $.ajax({
			url:'mtMixTrainNewsAction.do?method=getNewsDetailInfo',
			type:'post',
			data:{'newsId':120545},
		    dataType:"json",
			success:function(data){
				if(data.result=="true"){

				}
				else{
					
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