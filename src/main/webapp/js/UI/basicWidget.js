//to use this file, files below are needed
//js/calendar.js is needed to generate calendarBar
//

var basicWidget={
	showCalendarBar:calendarBar	
		
};

//
//options.timeType: 日历类型：年度导航(0)/月度导航(1)/日历导航(2)
//options.target: 要插入的元素位置
//options.showAll: 是否显示全部链接（若显示"全部链接"，相当于取消时间段检索设置）
//options.defaultShowYearNum:年度导航栏默认的显示年度数目
//options.selectTimeAction:点击链接触发的动作
//options.date:选择的日期
//
function calendarBar(options){
	//确定生成月历（年历）类型
	//0:年度导航：2014 2015 2016
	//1:月度导航： 2016 1 2 3 4 5 6 7 8 9 10 11 12
	//2:日历导航：2016.01 1 2 3 4 5 6 7......31
	var barType=0;
	//是否显示全部链接（若显示"全部链接"，相当于取消时间段检索设置）
	var showAll=true;
	//控件插入位置
	var target="body";
	var liNum=0;
	var currentYear=getCurrentYear();
	var currentMonth=getCurrentMonth();
	var currentDay=getCurrentDay();
	var paramYear=null;
	if(options.year!=null){
		paramYear=parseInt(options.year);
	}
	var selectedYear=currentYear;
	if(options.year!=null&&options.year!=-1){
		selectedYear=parseInt(options.year);
	}
	if(options.target!=null){
		target=options.target;
	}
	var startYearIndex=null;
	if(options.startYearIndex!=null){
		startYearIndex=parseInt(options.startYearIndex);
	}
	var selectedMonth=currentMonth;
	var selectedTimeInfo="";
	var selectedDay=currentDay;
	var defaultShowYearNum=3;
	var barInfo="<div class='calendarBar'></div>";
	var $calendarBar=null;
	$(target).append(barInfo);
	$calendarBar=$(target).find(".calendarBar");
	
	if(options.timeType){
		barType=options.timeType;
	}
	if(options.showAll!=null){
		showAll=options.showAll;
	}
	if(options.target!=null){
		target=options.target;
	}
	if(options.defaultShowYearNum!=null){
		defaultShowYearNum=options.defaultShowYearNum;
	}
	if(startYearIndex==null){
		startYearIndex=selectedYear-defaultShowYearNum+1;
	}
	

	//生成年历/日历/月历条基本信息
	function generateBarInfo(startYearIndex){
		var cycle=0;
		var daysArray=[];
		var barInfoArray=[];
		var barInfoStr="";
		$calendarBar.empty();
		barInfoArray.push("<a class='arrowleft arrow'><<</a>");
		barInfoArray.push("<a class='arrowright arrow'>>></a>");
		var currentValue=null;
		if(barType==0){
			//显示年度导航栏
			barInfoArray.push("<ul>");
			for(var i=startYearIndex;i<(startYearIndex+defaultShowYearNum);i++){
				barInfoArray.push("<li class='index'><input type='hidden' name='arcValue' value='"+i+"' /><a>"+i+"</a></li>");
			}
			currentValue=selectedYear;
			$calendarBar.addClass("yearCalendarBar");
		}
		else if(barType==1){
			//显示月度导航栏
			selectedTimeInfo=selectedYear+"年";
			barInfoArray.push("<div class='yearTxt'><span>"+selectedTimeInfo+"</span></div>");
			barInfoArray.push("<ul>");
			for(var i=1;i<=12;i++){
				barInfoArray.push("<ul>");
				barInfoArray.push("<li class='index'><input type='hidden' name='arcValue' value='"+i+"' /><a>"+i+"</a></li>");
			}
			currentValue=selectedMonth;
			$calendarBar.addClass("monthCalendarBar");

		}
		else if(barType==2){
			//显示日历导航栏
			if(selectedMonth<10){
				selectedTimeInfo=selectedYear+".0"+selectedMonth;
			}
			else{
				selectedTimeInfo=selectedYear+"."+selectedMonth;
			}
			daysArray=getDaysOfMonth(selectedYear,selectedMonth);
			cycle=daysArray.length;			
			barInfoArray.push("<div class='yearTxt'><span>"+selectedTimeInfo+"</span></div>");
			barInfoArray.push("<ul>");
			for(var i=0;i<cycle;i++){
				barInfoArray.push("<li class='index'><input type='hidden' name='arcValue' value='"+i+"' /><a >"+daysArray[i]+"</a></li>")
			}
			currentValue=selectedDay;
			$calendarBar.addClass("dayCalendarBar");

		}
		if(showAll){
			barInfoArray.push("<li class='all'><input type='hidden' name='arcValue' value='-1' /><a>全部</a><li>");
		}
		
		barInfoArray.push("</ul>");
		barInfoStr=barInfoArray.join("");
		$calendarBar.append(barInfoStr);
		
		$calendarBar.find(".arrowleft").click(function(){
			showPrevArc();
		});
		$calendarBar.find(".arrowright").click(function(){
			showNextArc();
		});
		
		$calendarBar.find("li").each(function(index,that){
			if($.trim($(that).find("input[name=arcValue]").val())==paramYear){
				$(that).find("a").addClass("currentIndex");
			}
		})
		
		$("li.index a").click(function(){
			var selectValue=$.trim($(this).parents("li").find("input[name=arcValue]").val());
			if(barType==0){
				selectedYear=selectValue;
			}
			else if(barType==1){
				selectedMonth=selectValue;
			}
			else if(barType==2){
				selectedDay=selectValue;
			}
			$calendarBar.find("li a.currentIndex").removeClass("currentIndex");
			$(this).addClass("currentIndex");	
			selectTime({year:selectedYear,month:selectedMonth,day:selectedDay});
		});
		
		$("li.all a").click(function(){
			$calendarBar.find("li a.currentIndex").removeClass("currentIndex");
			$(this).addClass("currentIndex");	
			$calendarBar.find("div.yearTxt span").html("");
			selectTime({year:-1,month:-1,day:-1});
			
		});
		
	};
	
	//选定某个月历条某个函数触发的事件
	function selectTime(timeArg){
		var timeRequestInfo={
				
		}
		timeRequestInfo.startYearIndex=startYearIndex;
		if(barType==0){
			timeRequestInfo.year=timeArg.year;
		}
		else if(barType==1){
			timeRequestInfo.year=timeArg.selectedYear;
			timeRequestInfo.month=timeArg.month;
			
		}
		else if(barType==2){
			timeRequestInfo.year=timeArg.selectedYear;
			timeRequestInfo.month=timeArg.selectedMonth;
			timeRequestInfo.day=timeArg.day;
		}
		if(options.selectTimeAction!=null){
			options.selectTimeAction(timeRequestInfo);
		}
		
	}
	
	
	function showPrevArc(){
		if(barType==0){
			startYearIndex-=1
			generateBarInfo(startYearIndex);
		}
		else if(barType==1){
			selectedYear=selectedYear-1;
			generateBarInfo();
		}
		else if(barType==2){
			if(selectedMonth==1){
				selectedYear-=1;
				selectedMonth=12;
			}
			else{
				selectedMonth-=1;
			}
			generateBarInfo();
		}
	}
	
	function showNextArc(){
		if(barType==0){
			startYearIndex+=1;
			generateBarInfo(startYearIndex);
		}
		else if(barType==1){
			selectedYear=selectedYear+1;
			generateBarInfo();
		}
		else if(barType==2){
			if(selectedMonth==12){
				selectedYear+=1;
				selectedMonth=1;
			}
			else{
				selectedMonth+=1;
			}
			generateBarInfo();
		}		
	}
	
	generateBarInfo(startYearIndex);
}

;(function($){
	
	$.fn.extend({
		//显示好评度分布(打分分布情况)
		//ratingContent:{"1":"xxxx","2":"yyyy","3":"zzzz".....,ratingRange:[1,2,3,4,5]};
		showRatingDistribution:function(ratingInfoContent){
			if(ratingInfoContent==null){
				ratingInfoContent={};
			}
			var $target=this;
			var ratingHTML=new Array();
			var ratingInfo=[5,4,3,2,1];
			if(ratingInfoContent.ratingRange!=null&&ratingInfoContent.ratingRange.length>0){
				ratingInfo=ratingInfoContent.ratingRange;
			}
			var ratingLength=ratingInfo.length;
			for(var i in ratingInfo){
				var rating=ratingInfo[i];
				ratingHTML.push("<div class='u-rating'>");
				var j=0;
				for(j=0;j<rating;j++){
					ratingHTML.push("<div class='star on'></div>");
				}
				for(j=rating;j<ratingLength;j++){
					ratingHTML.push("<div class='star'></div>");
				}
				var ratingContent=ratingInfoContent[rating+""];
				ratingHTML.push("<span class='rating-tip'>"+$.trim(ratingContent)+"</span>");
				ratingHTML.push("<div style='clear:both'></div>");
				ratingHTML.push("</div>");
			}
			ratingHTML.push("<div style='clear:both'></div>");
			$target.html(ratingHTML.join(""));
		},
		
		//params
		//target:控制内容长度的元素选择器
		//len:控制长度
		//tip:显示提示信息的元素选择器
		controllTextLength:function(params){
			if(params.len==null){
				params.len=500;
			}
			var limitedLength=params.len;
			var $currentElement=this;
			var controller=function(target){
				var word=target.val();
		     	 var length=word.length;
		     	 if (length >= limitedLength) 
				 { 
					word = word.substring(0,limitedLength); 
					target.val(word);
				 } 
		     	 length=word.length;
				 var left=limitedLength-length;
				 $(params.tip,$currentElement).html("可输入"+left+"个字符");		
			}
			$currentElement.on("change",params.target,function(){
				controller($(this));
			}).on("keydown",params.target,function(){
				controller($(this));
			}).on("keyup",params.target,function(){
				controller($(this));
			});
		}
		
		
	
		
	
	});
})(jQuery);


