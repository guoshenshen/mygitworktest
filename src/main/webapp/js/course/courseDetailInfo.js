var courseDetailArea={
	canclick:true
}

var courseId=$("#courseDetailInfo input[name=courseId]").val();

var loginData={
	nextActionUrl:"/onlineCourse/detail.do?bookId="+courseId
};
var currentOperatorInfo={};

var courseStudyInfo={};

var $courseSelectWindow=null;


var courseCarryInfo = {};

var firstCourseInfo = {};

var msg = "当前课程只能试看3分钟，请您登陆选学之后观看课程完整内容";

//加载课程基本信息
function loadCourseDetailInfo(){

	if($("#currentOperatorInfo input[type='hidden']").length>0){
		$("#currentOperatorInfo input[type='hidden']").each(function(index,that){
			currentOperatorInfo[that.name]=that.value;
		});
	}
	
	
	if($("#courseDetailInfo input[type='hidden']").length>0){
		$("#courseDetailInfo input[type='hidden']").each(function(index,that){
			courseStudyInfo[that.name]=that.value;
		});
	}
	
	if($("#courseCarryInfo input[type='hidden']").length>0){
		$("#courseCarryInfo input[type='hidden']").each(function(index,that){
			courseCarryInfo[that.name]=that.value;
		});
	}
	
	if($("#firsetCourseInfo input[type='hidden']").length>0){
		$("#firsetCourseInfo input[type='hidden']").each(function(index,that){
			firstCourseInfo[that.name]=that.value;
		});
	}
	
}


//加载界面UI以及初始化学员对课程的选学情况
function initializeCourseUITools(){
	$("#commentMainBody .partTitle a").click(function(){
		var $currentTab=$("#commentMainBody .partTitle a.currentTab");
		if($currentTab.attr("id")==this.id);
		else{
			$currentTab.removeClass("currentTab");
			$(this).addClass("currentTab");
			var currentTabId=this.id;
			$("#commentMainBody .currentContainer").removeClass("currentContainer");
			if(currentTabId=="discussTab"){
				$("#commentMainBody .commentContainer").addClass("currentContainer");
				$(window).scroll();
			}
			else if(currentTabId=="contentTab"){
				$("#commentMainBody .courseContent").addClass("currentContainer");
			}
			else if(currentTabId=="selfExamTab"){
				$("#commentMainBody .courseSelfExam").addClass("currentContainer");
				$(window).scroll();
			}
		}
	});
	
	$("#commentMainBody .partTitle a:first").click();
	
	/*if($.trim(courseStudyInfo.enterUrl).length==0){
	}
	else{*/
	var info = $("#courseInfo").val();
	if(info == "admin"){
		var $studyButton=$("#contentInfo .learnButton .Button");
		$("#progressInfo").hide();
		$studyButton.addClass("greenButton").removeClass("notShow").html("试&nbsp;&nbsp;看");
		$studyButton.click(function(){
			if(firstCourseInfo.courseId == ""){
				openLink("/courseStudy/previewStudy.do",{"courseID":courseStudyInfo.courseID,"info":"admin"},{'target':'courseStudyWindow'});
			}else{
				openLink("/courseStudy/previewStudy.do",{"courseID":firstCourseInfo.courseId,"info":"admin"},{'target':'courseStudyWindow'});
			}
			
				
		})
	}else{
		var $studyButton=$("#contentInfo .learnButton .Button");
		var $userLearnCourseStatus = $("#userLearnCourseStatus");
		if(courseStudyInfo.userID=="0"||$.trim(courseStudyInfo.userID).length==0||courseStudyInfo.userID==null){
			$studyButton.addClass("greenButton").removeClass("notShow").html("选&nbsp;&nbsp;学");
		}
		else{
			if(courseStudyInfo.courseStudyFlag=="1"){
				$studyButton.addClass("blueButton").removeClass("notShow").html("开始学习");
				msg = "当前课程只能试看3分钟，请您点击'开始学习'按钮观看课程完整内容"; //已选学"开始学习"按钮
			}
			else if(courseStudyInfo.courseStudyFlag=="2"){
				$studyButton.addClass("orangeButton").html("继续学习");
				msg = "当前课程只能试看3分钟，请您点击'继续学习'按钮观看课程完整内容"; //已选学"继续学习"按钮
			}
			else if(courseStudyInfo.courseStudyFlag=="3"){
				$studyButton.addClass("orangeButton").removeClass("notShow").html("已选课程")
			}
			else{
				$studyButton.addClass("greenButton").removeClass("notShow").html("选&nbsp;&nbsp;学");
				msg = "当前课程只能试看3分钟，请您点击'选学'按钮—>'开始学习'按钮观看课程完整内容";//已登陆，“选学”按钮
			}
		}
		$("#previewTimeUpReminder").html(msg);
		
		
		$studyButton.click(function(){
			if(courseStudyInfo.userID=="0"||$.trim(courseStudyInfo.userID).length==0||courseStudyInfo.userID==null){
				showLoginWin();
			}
			else if(courseStudyInfo.courseStudyFlag=="3"||courseStudyInfo.courseStudyFlag=="2"||courseStudyInfo.courseStudyFlag=="1"){
				var isCoursePackage = $("#isCoursePackage").val();
				if(isCoursePackage != 1){
					openLink("/courseStudy/scormStudy.do",courseStudyInfo,{'target':'courseStudyWindow'});
				}else{
					openLink("/courseStudy/scormStudy.do",firstCourseInfo,{'target':'courseStudyWindow'});
				}
				//openLink("courseStudy.do?method=scormStudy",courseStudyInfo,{'target':'courseStudyWindow'});
				if(courseStudyInfo.courseStudyFlag=="1"){
					setTimeout(" window.location.reload();",50000);
				}	
			}
			else{
				chooseCourse();
			}
			
			
			
		});
	}
	/*};*/
	
	$("#progressInfo .Button").click(function(){
		var isCoursePackage = $("#isCoursePackage").val();
		if(isCoursePackage != 1){
			openLink("/courseStudy/scormStudy.do",courseStudyInfo,{'target':'courseStudyWindow'});
		}else{
			openLink("/courseStudy/scormStudy.do",courseCarryInfo,{'target':'courseStudyWindow'});
		}
		
		
		
		
		
		
	});
	
	$(".courseSelectFrame .Button").click(function(){
		confirmApplyCourse(courseStudyInfo);
	})
}


//已撤销课程提示
function offlineCourse(msg){
	var $courseOffLineWin=$("#courseOffLineWin").remodal();
	var message="抱歉，因内容品质、版权等原因，系统已对当前课程下架，请您浏览其他课程";
	if($.trim(msg)!=""){
		message=$.trim(msg);
	}
	$(document).on('closing', '#courseOffLineWin', function (e) {
		  window.location.href="shareCourseForSelfStudyAction.do?method=shareCourseList";
	});
	$("#courseOffLineWin .info").html(message);
	$courseOffLineWin.open();
}

//选学课程
function chooseCourse(){
	if($courseSelectWindow==null){
		$couresSelectWindow=$("#courseSelectWindow").remodal({});
	}
	$couresSelectWindow.open();
	

}


//确认选学课程
function confirmApplyCourse(courseStudyInfo){
	if(courseDetailArea.canclick){
		 courseDetailArea.canclick=false;
		$.ajax({
		   url: '/selectCourse/selectSingleCourse.do',
		   type:'POST',
		   dataType:"json",
		   data: {courseId:courseStudyInfo.courseId},
		   success: function(data){
			   if(data.status){
				    courseStudyInfo.courseStudyFlag="1";
				    $("#courseStudyFlag").val("1");
				    $("#contentInfo .learnButton .Button").addClass("blueButton").removeClass("notShow").html("开始学习");
				    msg = "当前课程只能试看3分钟，请您点击'开始学习'按钮观看课程完整内容";
				    $("#previewTimeUpReminder").html(msg);
				    $couresSelectWindow.close();  
				    if($("#isCanGenerateSelfPaper").val() ==1){
				    	$("#selfExamTab").show();
				    }
			   }
			   else{
				   $(".courseSelectFrame .tips").html("课程选学失败，请稍后重试....");
			   }
		   },
		   error:function(){
		   		$(".courseSelectFrame .tips").html("课程选学失败，请稍后重试....");
		   },
		   complete:function(data){
		   	  courseDetailArea.canclick=true;
		   }
		}); 
	}
	else{
		return;
	}
	
}

//加载学习进度条
function loadStudyProgress(){
	var currentProgress=$("#progressInfo input[name=studyProgress]").val();
	var lastProgress=0;
	if(currentProgress>50){
		lastProgress=currentProgress-50;
		$("#progressInfo .barFinished").css("width",lastProgress+"%");
	}
	var increase=currentProgress-lastProgress;
	
	$("#progressInfo .barFinished")
	setTimeout(function(){
		$("#progressInfo .barFinished").animate({width:currentProgress+"%"},1000);
		
		var $progressTip=$("#progressInfo .tip");
		var j=1;
		$progressTip.html(parseInt(lastProgress)+"%");
		var increaseFunc=function(){
			var progress=j+lastProgress;
			if(j<parseInt(increase)){
				$progressTip.html(parseInt(progress)+"%");
			}
			else{
				$progressTip.addClass("lastPart").html(progress+"%");
			}
			j++;
		}
		for(var i=1;i<=increase;i++){
			setTimeout(function(){
				increaseFunc();
			},1000/increase*i);
		}
	},500);
}

//加载课程学习评分
function loadCourseEstimation(){
	$.ajax({
		url:"/rsmRcmBookDiscuss/getScoreDistribute.do",
		method:"post",
		data:{"bookId":courseStudyInfo.courseId,"discussType":1703},
		dataType:"json",
		success:function(data){
			if(data.status ){
				var datas = data.data;

                var distributeInfo={};
                for(var i in datas){
                    distributeInfo[i]="("+datas[i]+"人)";
                }
                if(datas.averageScore!=null){
                    distributeInfo.averageScore=datas.averageScore;
                }
                var $overallEvaluation=$("#score .overallEvaluation");
                if($overallEvaluation.length>0){
                    $overallEvaluation.html("平均分<font>"+datas.averageScore+"</font>&nbsp;&nbsp;&nbsp;&nbsp;好评度<font>"+datas.goodRate+"</font>");
                }
                $("#score .ratingContainer").showRatingDistribution(distributeInfo);
			}

		},
		error:function(){
			$("#score .ratingContainer").showRatingDistribution();
		}
	})
}
function getManualRelatedCourse(){
    var dtd = $.Deferred();
    $.ajax({
        url:"/addLessonInfo/getCourseRelevantCourse.do",
        method:"post",
        dataType:"json",
        data:{"originalCourseId":courseStudyInfo.courseId},
        success:function(data){
            if(data.status && data.data.length > 0){
                dtd.resolve(data);
            }
            else{
                dtd.reject({"msg":"未获取手工关联的课程信息"});
            }
        },
        error:function(){
            dtd.reject({"msg":"未获取手工关联的其他课程信息"});
        }
    });
    return dtd;
}
function getRelatedCourseFromSameSeries(){
    var dtd = $.Deferred();
    $.ajax({
        url:"/recommendSeries/getCourseListUnderSameSeries.do",
        method:"post",
        dataType:"json",
        data:{"courseId":courseStudyInfo.courseId},
        success:function(data){
            if(data.status &&data.data.length>0){
                dtd.resolve(data);
            }
            else{
                dtd.reject({"msg":"未获取同一专题下的其他课程信息"});
            }
        },
        error:function(){
            dtd.reject({"msg":"未获取同一专题下的其他课程信息"});
        }
    });
    return dtd;
}

function getHotCourses(){
    var dtd = $.Deferred();
    $.ajax({
        url:"/onlineCourse/getHotCourseList.do",
        method:"post",
        dataType:"json",
        success:function(data){
            if(data.status &&data.data.length>0){
                dtd.resolve(data);
            }
            else{
                dtd.reject({"msg":"未获取热门课程信息"});
            }
        },
        error:function(){
            dtd.reject({"msg":"未获取热门课程信息"});
        }
    });
    return dtd;
}


function loadRelatedCourses(){
    getManualRelatedCourse().then(
        function(data){
            $("#relatedCourseList").show();
            var courseArray=data.data;
            var courseHtml=new Array();
            var length=courseArray.length>5?5:courseArray.length;
            for(var i=0;i<length;i++){
                var course=courseArray[i];
                if(course.courseId==courseStudyInfo.courseId){
                    continue;
                }
                courseHtml.push('<li class="carousel-item" style = "width :265px">');
                courseHtml.push('<a href = "/onlineCourse/detail.do?bookId='+course.courseId+'" target="_blank" title="相关课件——'+course.courseName+'" title><img src="'+course.pictureUrl+'"  onerror="imgError({type:0,target:this})"/>');
                courseHtml.push('</a>');
                courseHtml.push('<span class="titleMask"></span>');
                courseHtml.push('<span class="title" title="'+course.courseName+'">'+course.courseName+'</span>');
                courseHtml.push('</li>');
            }
            $("#relatedCourseList .carousel-inner").html(courseHtml.join(""));
            $("#relatedCourseList  h2").html("相关课件");
            $("#relatedCourseList .ft-carousel").FtCarousel({
            });
        },
        function(){
            getRelatedCourseFromSameSeries().then(
                function(data){
                    $("#relatedCourseList").show();
                    var courseArray=data.data;
                    var courseHtml=new Array();
                    var length=courseArray.length>5?5:courseArray.length;
                    for(var i=0;i<length;i++){
                        var course=courseArray[i];
                        if(course.courseId==courseStudyInfo.courseId){
                            continue;
                        }
                        courseHtml.push('<li class="carousel-item" style = "width :265px">');
                        courseHtml.push('<a href = "/onlineCourse/detail.do?bookId='+course.courseId+'" target="_blank" title="相关课件——'+course.courseName+'" title><img src="'+course.pictureUrl+'"  onerror="imgError({type:0,target:this})"/>');
                        courseHtml.push('</a>');
                        courseHtml.push('<span class="titleMask"></span>');
                        courseHtml.push('<span class="title" title="'+course.courseName+'">'+course.courseName+'</span>');
                        courseHtml.push('</li>');
                    }
                    $("#relatedCourseList .carousel-inner").html(courseHtml.join(""));
                    $("#relatedCourseList  h2").html("相关课件");
                    $("#relatedCourseList .ft-carousel").FtCarousel({
                    });
                },
                function(){
                    getHotCourses().then(function(data){
                        $("#relatedCourseList").show();
                        var courseArray=data.data;
                        var courseHtml=new Array();
                        var length=courseArray.length>5?5:courseArray.length;
                        for(var i=0;i<length;i++){
                            var course=courseArray[i];
                            if(course.courseId==courseStudyInfo.courseId){
                                continue;
                            }
                            courseHtml.push('<li class="carousel-item" style = "width :265px">');
                            courseHtml.push('<a href = "/onlineCourse/detail.do?bookId='+course.courseId+'" target="_blank" title="热门课件——'+course.courseName+'" title><img src="'+course.pictureUrl+'"  onerror="imgError({type:0,target:this})"/>');
                            courseHtml.push('</a>');
                            courseHtml.push('<span class="titleMask"></span>');
                            courseHtml.push('<span class="title" title="'+course.courseName+'">'+course.courseName+'</span>');
                            courseHtml.push('</li>');
                        }
                        $("#relatedCourseList  h2").html("热门课件");
                        $("#relatedCourseList .carousel-inner").html(courseHtml.join(""));
                        $("#relatedCourseList .ft-carousel").FtCarousel({
                        });
                    },function(){

                    })
                }
            )
        });
}
//加载选学该课程的学员列表
function loadUserList(){
	$.ajax({
		url:"/onlineCourse/getCourseSelectedUserInfo.do",
		method:"post",
		data:{"courseId":courseStudyInfo.courseId,"rowCount":16},
		dataType:"json",
		success:function(data){
			var userList=data.data.userList;
			var userListArray=new Array();
			var userListLength=userList.length;
			for(var i=0;i<userListLength;i++){
				var user=userList[i];
				userListArray.push('<a class="userItem"><div class="userInfo u-userCardTrigger">');
				var headPic=user.address;
				if(user.address!=null&&$.trim(user.address)!=""){
					userListArray.push('<img class="headPic" src="'+user.address+'" />');
				}
				else{
					var surName=user.operatorName.substr(0, 1);
					userListArray.push('<div class="headPic">'+surName+'</div>');
				}
				if($.trim(headPic)==""){
					if($.trim(user.gender)=="2"){
						headPic="/image/headPic/female1.jpg";
					}
					else{
						headPic="/image/headPic/male1.jpg";
					}
				}
				userListArray.push('<em class="userName" title="'+user.operatorName+'">'+user.operatorName+'</em>');
				userListArray.push('<input type="hidden" class="infobox_userId" value="'+user.operatorId+'"></input>');
				userListArray.push('<input type="hidden" class="infobox_userName" value="'+user.operatorName+'"></input>');
				userListArray.push('<input type="hidden" class="infobox_itemType" value="1"></input> ');
				userListArray.push('<img src="'+headPic+'" class="notShow infobox_headPic">');
				userListArray.push("</div></a>");
			}
			

			var $userHeadList=$("#studentOfCourse .userHeadList");
			if(!$.support.leadingWhitespace){
				$userHeadList.css("filter","alpha(opacity=0)");
			}
			else{
				$userHeadList.css("opacity","0");
			}
			$userHeadList.html(userListArray.join(""));
			$("img.headPic",$userHeadList).one("error", function(e){
			     $(this).attr("src", "/image/headPic/male1.jpg");
			});
			var selectedTimes=0;
			if(data.selectedTimes!=null&&($.trim(data.selectedTimes)!="null")){
				selectedTimes=data.selectedTimes;
			}
			$("#studentOfCourse .userCount").html("已被选学<font>"+data.data.selectedTimes+"</font>次");
			if(!$.support.leadingWhitespace){
				$userHeadList.animate({"filter":"alpha(opacity=100)"},800);
			}
			else{
				$userHeadList.animate({"opacity":"1"},800);
			}
		

			var $currentOperatorId=$("#currentOperatorInfo #currentOperatorId");
				if($currentOperatorId.length>0){
					$userHeadList.on("click",".userItem",function(){
					var param={};
					param.itemType=$(".infobox_itemType",$(this)).val();
					param.operatorId=$(".infobox_userId",$(this)).val();
					intoUserZone(param);
				});
			}	
		},
		error:function(data){
			$userHeadList.html("<div class='errorLoading'>加载异常...</div>");
		}
	});
}

function applyTestExam(courseId){
  	//window.location.href="myExam.do?method=searchSelfTestExam&classifyId="+courseId+"&testExamFlag=course";   
  	window.open("myExam.do?method=searchSelfTestExam&classifyId="+courseId+"&testExamFlag=course");   
 }



$(function(){

	loadCourseDetailInfo();

	initializeCourseUITools();
	
	
	$("img.orgPic").one("error",function(){	
		$(this).attr('src',"./image/headPic/defaultOrg.png");
    });
	
	//如果学员当年已对该课程具有学习记录,则加载该学员的当前学习进度条,加载学员的评论编辑器
	if(courseStudyInfo.courseStudyFlag=="2"){
		loadStudyProgress();
		var paramsForDiscuss=$.extend({"discussType":1703,"bookId":courseStudyInfo.courseId,"isReply":0,"tip":"请对课程"+courseStudyInfo.courseName+"进行评价..."},currentOperatorInfo);
		$("#commentMainBody").loadDiscussEditor(paramsForDiscuss);
		$(".discussReplyContainer",$discussContainer).addClass("noEmpty");
	}
	
	//加载课程评价信息
	loadCourseEstimation();
	
	var pubstatus=courseStudyInfo.pubStatus;
	var info = $("#courseInfo").val();
	if(pubstatus=="未发布" && info != "admin" ){
		offlineCourse();
	}
	else{
		//绑定评论长度控制
		$("#commentMainBody .discussEditorContainer").controllTextLength({"target":"textarea","tip":".size-leave","len":500});
		//绑定评论回复长度控制
		$("#commentMainBody .discussReplyContainer").controllTextLength({"target":"textarea","tip":".size-leave","len":500});
		
		//设置动态加载评论列表/选学学员列表规则
		//当当前位置不在屏幕顶端or鼠标向下移动超过5px or学员当年不具有该课程的学习记录时，加载评论列表/选学学员列表
		var userListLoaded=false;
		var userReplyLoaded=false;
		var paramsForDiscussList={"bookId":courseStudyInfo.courseId,"discussType":1703,"startIndex":0,"length":10};
		var scrollTop=$(window).scrollTop();
		var $discussContainer=$("#commentMainBody .commentContainer");
		if(courseStudyInfo.courseStudyFlag!="2"||(scrollTop>5&&!userListLoaded)){
			userListLoaded=true;
            loadRelatedCourses();
			loadUserList();
		}
		if($discussContainer.hasClass("currentContainer")&&(courseStudyInfo.courseStudyFlag!="2"||(scrollTop>5&&!userListLoaded))){
			userReplyLoaded=true;
			$("#commentMainBody").loadDiscussItemList(paramsForDiscussList);
			
		}
		$(window).scroll(function(){
			var scrollTop = $(this).scrollTop();
			if(scrollTop>5&&!userListLoaded){
				userListLoaded=true;
                loadRelatedCourses();
				loadUserList();
			}
			if((courseStudyInfo.courseStudyFlag!="2"||scrollTop>5)&&!userReplyLoaded){
				userReplyLoaded=true;
				$("#commentMainBody").loadDiscussItemList(paramsForDiscussList);
			}
			if(userReplyLoaded&&userListLoaded){
				$(window).off("scroll");
			}
		});
		//绑定动作到评论回复
		$(".commentContainer").on("click",".discussEditorContainer .replybtn",function(){
			$(this).submitDiscussItem({"success":loadCourseEstimation()});
		}).on("click",".btn-reply .J_Reply",function(){
			$(this).showReplyOtherDiscussEditor();
		}).on("click",".discussReplyContainer .replybtn",function(){
			$(this).replyOtherReply({"success":loadCourseEstimation()});
		}).on("click",".discussReplyContainer .cancelbtn",function(){
			$(this).cancleReply();
		});
		
		//绑定头像名片显示
		$.hoverUserCard();
	}
})


