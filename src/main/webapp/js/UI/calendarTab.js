
var calendarTabOptions={
		daySelected:new selectedDay(getCurrentYear(),getCurrentMonth(),getCurrentDay()),
		actionAfterBarLoaded:null,
		actionAfterCalendarLoaded:null
}


function selectedDay(year,month,day){
	this.year=year;
	this.month=month;
	this.day=day;
}


selectedDay.prototype.info=function(){
	return this.year+"."+this.month+"."+this.day;
}



//获取上个月的日历
function getDaysOfLastMonth(){
	if(calendarTabOptions.daySelected.month==1){
		calendarTabOptions.daySelected.month=12;
		calendarTabOptions.daySelected.year=calendarTabOptions.daySelected.year-1;
	}
	else{
		calendarTabOptions.daySelected.month=calendarTabOptions.daySelected.month-1;	
	}
	return getDaysOfMonth(calendarTabOptions.daySelected.year,calendarTabOptions.daySelected.month);
	
}
//获取下一个月的日历
function getDaysOfNextMonth(){
	if(calendarTabOptions.daySelected.month==12){
		calendarTabOptions.daySelected.month=1;
		calendarTabOptions.daySelected.year=calendarTabOptions.daySelected.year+1;
	}
	else{
		calendarTabOptions.daySelected.month=calendarTabOptions.daySelected.month+1;	
	}
	return getDaysOfMonth(calendarTabOptions.daySelected.year,calendarTabOptions.daySelected.month);
}

//获取上一个月
function loadLastMonth(){
	if(calendarTabOptions.daySelected.month==1){
		calendarTabOptions.daySelected.month=12;
		calendarTabOptions.daySelected.year=calendarTabOptions.daySelected.year-1;
	}
	else{
		calendarTabOptions.daySelected.month=calendarTabOptions.daySelected.month-1;
	}
	loadCalendarBar();
}

//获取下一个月
function loadNextMonth(){
	if(calendarTabOptions.daySelected.month==12){
		calendarTabOptions.daySelected.month=1;
		calendarTabOptions.daySelected.year=calendarTabOptions.daySelected.year+1;
	}
	else{
		calendarTabOptions.daySelected.month=calendarTabOptions.daySelected.month+1;
	}
	loadCalendarBar();
}

//选择特定月份
function chooseMonth(month){
	calendarTabOptions.daySelected.month=month;
	loadCalendarBar();
}

//加载月历条
function loadCalendarBar(){
	$("#calendarYear").html(calendarTabOptions.daySelected.year+"年");
	var monthOfYear="";
	for(var j=1;j<=12;j++){
		if(j==calendarTabOptions.daySelected.month){
			monthOfYear+="<li class='selectedMonth'><a onclick='javascript:chooseMonth("+j+")'>"+j+"</a></li>"
		}
		else{
			monthOfYear+="<li><a onclick='javascript:chooseMonth("+j+")'>"+j+"</a></li>"
		}
	}
	$("#calendarMonth").html(monthOfYear);
	if(calendarTabOptions.actionAfterBarLoaded!=null&&(typeof calendarTabOptions.actionAfterBarLoaded == "function")){
		calendarTabOptions.actionAfterBarLoaded();
	}
}


//加载日历
function loadCalendar(){
	var currentDaysArray=getDetailInfoDaysOfMonth(calendarTabOptions.daySelected.year,calendarTabOptions.daySelected.month);
	var daysOfMonthStr="<li class='week_head'><span>星期日</span></li><li class='week_head'><span>星期一</span></li><li class='week_head'><span>星期二</span></li><li class='week_head'><span>星期三</span></li><li class='week_head'><span>星期四</span></li><li class='week_head'><span>星期五</span></li><li class='week_head'><span>星期六</span></li>";
	var spaceNum=0;
	var firstDayOfMonth=0;//某月第一天的星期：0：星期日
	for(var i in currentDaysArray){
		if(i==0){
			while(currentDaysArray[i].getDay()!=firstDayOfMonth){
				daysOfMonthStr+="<li><a onclick='#'></a></li>";
				spaceNum++;
				firstDayOfMonth++;
			}
			daysOfMonthStr+="<li id='day_"+currentDaysArray[i].getDate();
			daysOfMonthStr+="'><a onclick='javascript:void(0);'>"+currentDaysArray[i].getDate()+"</a><div class='trace'></div></li>";
		}
		else{
			spaceNum++;
			daysOfMonthStr+="<li id='day_"+currentDaysArray[i].getDate();
			daysOfMonthStr+="'><a onclick='javascript:void(0);'>"+currentDaysArray[i].getDate()+"</a><div class='trace'></div></li>";
		}
		
	}
	while((spaceNum+1)%7!=0){
		daysOfMonthStr+="<li><a onclick='javascript:void(0);'></a></li>";
		spaceNum++;
	}
	daysOfMonthStr+="<div style='clear:both'></div>";
	$("#thismonth ul").html(daysOfMonthStr);
	if(calendarTabOptions.actionAfterCalendarLoaded!=null&&(typeof calendarTabOptions.actionAfterCalendarLoaded=="function")){
		calendarTabOptions.actionAfterCalendarLoaded(calendarTabOptions.daySelected);
	}
	
}


//添加月历条到UI
function initializeCalendarBar(){
	loadCalendarBar();
	$("#calendar_month #calendarMonthpre").click(function(){
		loadLastMonth();
	});
	$("#calendar_month #calendarMonthnext").click(function(){
		loadNextMonth();
	});
}

//添加日历信息到UI
function initializeCalendar(){
	calendarTabOptions.actionAfterBarLoaded=loadCalendar;
	initializeCalendarBar();
}

