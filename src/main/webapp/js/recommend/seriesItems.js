var seriesItemsArea={
	rootPath:"",
	templateClass:"",
	bannerClass:"",
	//存储轮播图片及该图片对应信息
	bannerRotateInfo:{
		index:-1,
		bannerArray:[],
		rotateInterval:null
	},
	itemsInfo:{
		currentItemType:-1,
		itemsArray:[]
	},
	paginationInfo:{
		startIndex:-1,
		length:19
	}
}


seriesItemsArea.loadStructure=function(url,templateId,functionAfterStructureLoaded){
	var structureUrl="";
	if($.trim(seriesItemsArea.rootPath)==""){
		structureUrl="."+url;
	}
	else{
		structureUrl=seriesItemsArea.rootPath+url;
	}
	$.ajax({
        url:structureUrl,
        dataType:'text',
        success:function(data){
			var $structureInfo=$($.parseHTML(data));
			var $templateUsed=$structureInfo.find("#"+templateId);
			$templateUsed.insertBefore("#hiddenInfo");
			functionAfterStructureLoaded();
        },
        complete:function(ev){
        }
    })
}

function lazyLoadPic(){
	$("img.lazy").lazyload({
		threshold : 10,
    	effect : "fadeIn"
	});
	
};

//滚动（无翻页）加载更多资源
function scrollLoadResourceInfo(){
	var data={};
	var itemsInfo=seriesItemsArea.itemsInfo;
	var paginationInfo=seriesItemsArea.paginationInfo;
	var resourceType=ConstantsArea.resourceType;
	var seriesId=$("#seriesInfo input[name=seriesId]").val();
	$("#mainbody .standardList").scrollLoadInfo({
		url:"/recommendSeries/getItemsOfSeries.do",
		dataType:"json",
		params:data,
		heightOffset:40,
		beforeLoad:function(){
			$("img.lazy").removeClass("lazy");
			data.seriesId=seriesId;
			data.itemType=itemsInfo.currentItemType;
			data.startIndex=paginationInfo.startIndex;
			data.length=paginationInfo.length;
		},
		loader:function(resultData){
			if(resultData.status){
				if(resultData.data.stopScroll){
					$("#mainbody .standardList").stopScrollPagination();
				}
				var itemsHtml=null;
				if(data.itemType==resourceType.course){
					itemsHtml=$.loadVisibleCourseList(resultData.data.data.list);
				}
				else if(data.itemType==resourceType.train){
					itemsHtml=$.loadVisualTrainList(resultData.data.data.list);
				}
				else if(data.itemType==resourceType.topic){
					itemsHtml=$.generateOldSeriesList(resultData.data.data.list,undefined);
				}
				if(itemsHtml!=null && itemsHtml.indexOf("empty")< 0 ){
					$("#mainbody .topicContent ul").append(itemsHtml);
				}
				
				
			}
		},
		afterLoaded:function(resultDataInfo){
			paginationInfo.startIndex=$("#mainbody .topicContent ul li").length;
			lazyLoadPic();
		}
	});
}



//加载系列专题下某一选项卡下的资源信息
function loadItemsByType(params){
	var itemType=params.itemType;
	var seriesId=$("#seriesInfo input[name=seriesId]").val();
	var itemsInfo=seriesItemsArea.itemsInfo;
	var paginationInfo=seriesItemsArea.paginationInfo;
	var itemsArray=itemsInfo.itemsArray;
	if(itemsInfo.currentItemType!=itemType){
		if(itemsArray[itemType]!=null){
			//已经加载过当前选项卡信息
			$("#mainbody .topicContent ul").html(itemsArray[itemType]);
			itemsInfo.currentItemType=itemType;
			lazyLoadPic();
			paginationInfo.startIndex=$("#mainbody .topicContent ul li").length;
			scrollLoadResourceInfo();
			return;
		}
	}
	params.seriesId=seriesId;
	var itemsHtml="";
	var resourceType=ConstantsArea.resourceType;
	$.ajax({
		url:"/recommendSeries/getItemsOfSeries.do",
		method:"post",
		dataType:"json",
		data:params,
		success:function(data){
			if(data.status){
				if(itemType==resourceType.course){
					itemsHtml=$.loadVisibleCourseList(data.data.data.list);
				}
				else if(itemType==resourceType.train){
					itemsHtml=$.loadVisualTrainList(data.data.data.list);
				}
				else if(itemType==resourceType.topic){
					
					itemsHtml=$.generateOldSeriesList(data.data.data.list,undefined);
					//itemsHtml=$.generateSeriesList(data.resultList,data.studySeries);
				}
			}
			else{
				
			}
			if(itemsInfo.currentItemType!=itemType){
				$("#mainbody .topicContent ul").html(itemsHtml);
			}
			else{
				$("#mainbody .topicContent ul").append(itemsHtml);
			}
			itemsInfo.currentItemType=itemType;
			if(itemsArray[itemType]==null){
				itemsArray[itemType]=itemsHtml;
			}
			else{
				itemsArray[itemType]+=itemsHtml;
			}
			lazyLoadPic();
			paginationInfo.startIndex=$("#mainbody .topicContent ul li").length;
			if(data.data.data.list.length>0){
				scrollLoadResourceInfo();
			}
		},
		complete:function(){
			
		}
	})
}


function picRotate(){
	var bannerRotateInfo=seriesItemsArea.bannerRotateInfo;
	var bannerArray=bannerRotateInfo.bannerArray;
	var currentIndex=(bannerRotateInfo.index+1)%bannerArray.length;
	$("#background-image").fadeOut(300);
	$("#bannerPic").animate({opacity:"0"},300,function(){
		$("#bannerDescription").css("display","none");
		
		$("#bannerPic").attr("src",bannerArray[currentIndex].bannerPic);
	});
}
/**
 * 当前专题是否被学员选学
 * @param seriesId
 */
function isSeriesLearned(seriesId){
	var check = new Boolean(false);
	$.ajax({
		url:"recommendSeriesAction.do?method=getSeriesLearnedStatus",
		method:"post",
		dataType:"json",
		data:{"seriesId":seriesId},
		async:false,
		success:function(data){
			if(data.result){
				check = data.isLearned;
				return check;
			}
		},
		complete:function(){
			
		}
	});
	return check;
	
}
/**
 * 前往系列专题展示页面
 * 
 * @param seriesId
 */
function goStudySeries(seriesId){
	$.Nconfirm({
    	'confirmQuestion':"您未选学专题,是否选学?",
    	'onConfirm':function(){
    		$.ajax({
				method:"POST",
				data:{"seriesId":seriesId,"isStudy":1},
				url:"recommendSeriesAction.do?method=studySeries",
				dataType:"json",
				success:function(data){
					if(data.status){
						$.Ntip({
		 					'content':"选学成功"
		 				});
					}else{
						$.Ntip({
		 					'content':"操作失败"
		 				});	
					}
				}
			});
    	},
		'onDeny':function(){
			
		}
   	});
}
function fillDataInfoForStructure(){
	var $tabBar=$(".contentContainer .topicTab");
	if($tabBar.length>0){
		var tabHtmlArray=new Array();
		$("#hiddenInfo #tabInfo input[type=hidden]").each(function(index,that){
			tabHtmlArray.push("<div class='tab'>"+that.name+"<input type='hidden' name='tabType' value='"+that.value+"'><div class='bar'></div></div>");
		})
		$tabBar.html(tabHtmlArray.join(""));
		
		$tabBar.on("click",".tab",function(){
			var typeId=$(this).find("input[name=tabType]").val();
			if($(this).hasClass("currentTab"));
			else{
				$(".currentTab",$tabBar).removeClass("currentTab");
				$(this).addClass("currentTab");
				loadItemsByType(
						{
							"itemType":typeId,
							"startIndex":0,
							"length":19,
						}
					);
			}
		});
		
		$tabBar.find(".tab:first").click();
		
		$("#mainbody .topicContent").on("click",".coursePic",function(){
			var courseId=$("input[name=courseId]",$(this)).val();
			/**
			 * 判断课程所属专题是否被当前学员选学，如未选学，则弹出框提示：您未选学该课程所属专题，是否去选学？
			 * go  no
			 * 
			 * 如已选学，则window.open();
			 */
			/*if(resourceAccess()==true){
				var seriesId = $("#seriesInfo input[name=seriesId]").val();
				var isLearned = isSeriesLearned(seriesId);
				if(isLearned == true){
					window.open("onlineCourseAction.do?method=detail&bookId="+courseId);
				}
				else{
					goStudySeries(seriesId);
				}
			}*/
            window.open("/onlineCourse/detail.do?bookId="+courseId);
		});
		
		$("#mainbody .topicContent").on("click",".trainPic",function(){
			var trainId=$("input[name=trainId]",$(this)).val();
			/**
			 * 判断培训所属专题是否被当前学员选学，如未选学，则弹出框提示：您未选学该培训所属专题，是否去选学？
			 * go  no
			 * 
			 * 如已选学，则window.open();
			 */
			/*if(resourceAccess()==true){
				var seriesId = $("#seriesInfo input[name=seriesId]").val();
				var isLearned = isSeriesLearned(seriesId);
				if(isLearned == true){
					window.open("onlineTraining.do?method=viewTrain4Admin&trainId="+trainId);
				}
				else{
					goStudySeries(seriesId);
				}
			}*/
            window.open("/onlineTraining/viewTrain4Admin.do?trainId="+trainId);
		});
	}
	var $bannerInfo=$("#bannerInfo .topicBannerInfo");
	
	$bannerPic=$("#bannerPic");
	$bannerPic.load(function(){
		var bannerRotateInfo=seriesItemsArea.bannerRotateInfo;
		var bannerArray=bannerRotateInfo.bannerArray;
		var index=(bannerRotateInfo.index+1)%bannerArray.length;
		bannerRotateInfo.index=index;
		var currentBanner=bannerArray[index];
		var templateClass = currentBanner.templateClass;
		var templateClassArr = templateClass.replace(/,/g,' ');
			$("body").attr("class","student "+templateClassArr);
		$("#mainbody .bannerContainer").attr("class","bannerContainer "+currentBanner.bannerClass);
		$("#background-image").attr("src",$bannerPic.attr("src")).fadeIn(1000);
		$("#bannerPic").animate({opacity:"1",left:"0px"},800,function(){
			$bannerPic.fadeIn("normal",function(){
				if($.trim(currentBanner.description).length>0){
					$("#bannerDescription p").html(currentBanner.description);
					$("#bannerDescription").fadeIn(1000);
				}
			});
		});
	})
	
	if($bannerInfo.length>0){
		var bannerRotateInfo=seriesItemsArea.bannerRotateInfo;
		$("#bannerPic").load(function(){
			var bannerArray=bannerRotateInfo.bannerArray;
			var currentIndex=(bannerRotateInfo.index+1)%bannerArray.length;
			console.log(bannerArray[currentIndex].bannerPic);
		});
		if($bannerInfo.length>1){
			bannerRotateInfo.roateInterval=setInterval(function(){picRotate();},10000);
			$("#banner").hover(function(){
				clearInterval(bannerRotateInfo.roateInterval);
			},function(){
				bannerRotateInfo.roateInterval=setInterval(function(){picRotate();},10000);
			})
		}
		$bannerInfo.each(function(index,that){
			var newBanner={};
			var $bannerPic=$("input[name=picUrl]",$(that));
			var $bannerClass=$("input[name=bannerClass]",$(that));
			var $description=$("input[name=description]",$(that));
			var $templateClass=$("input[name=templateClass]",$(that));
			var bannerArray=seriesItemsArea.bannerRotateInfo.bannerArray;
			if($bannerPic.length>0){
				bannerArray.push(newBanner);
				newBanner.bannerPic=$bannerPic.val();
				newBanner.bannerClass=$bannerClass.val();
				newBanner.description=$description.val();
				newBanner.templateClass=$templateClass.val();
			}
		})
		picRotate();
	}
}
