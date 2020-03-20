var calendarInfo=(function(){
	var currentDate=new Date();
	var currentYear=currentDate.getFullYear();
	var currentMonth=currentDate.getMonth()+1;
	var currentDay=currentDate.getDate();
	return {
		getYear:function(){return currentYear},
		getMonth:function(param){
			var month=currentMonth;
			var offset=param.offset;
			month=Math.abs((month+offset)%12);
			if(month<10){
				month="0"+month
			}
			return month
		},
		
		getDay:function(){return currentDay},
		getDayDetail:function(){
			return currentYear+"."+currentMonth+"."+currentDay;
		}
	}
})();

function getCurrentYear(){
	return calendarInfo.getYear();
}

function getCurrentMonth(){
	return getOffsetMonthFromCurrent();
}

function getOffsetMonthFromCurrent(param){
	var defaultParam={"offset":0};
	if(param==null){
		param=defaultParam;
	}
	return calendarInfo.getMonth(param);
}

function getCurrentDay(){
	return calendarInfo.getDay();
}

function getCurrentHour(){
	return new Date().getHours();
}


function getCurrentDayDetail(){
	return calendarInfo.getDayDetail();
}


//对Date的扩展，将 Date 转化为指定格式的String   
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
//例子：   
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.format = function(fmt)   
{   //author: meizz   
	var o = {   
	 "M+" : this.getMonth()+1,                 //月份   
	 "d+" : this.getDate(),                    //日   
	 "h+" : this.getHours(),                   //小时   
	 "m+" : this.getMinutes(),                 //分   
	 "s+" : this.getSeconds(),                 //秒   
	 "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	 "S"  : this.getMilliseconds()             //毫秒   
	};   
	if(/(y+)/.test(fmt))   
	 fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	for(var k in o)   
	 if(new RegExp("("+ k +")").test(fmt))   
	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	return fmt;   
}  

function getDateInfo(date){
	var dateInfo={};
	if(date instanceof Date){
		dateInfo.year=date.getFullYear();
		dateInfo.month=date.getMonth()+1;
		dateInfo.day=date.getDate();
	}
	return dateInfo;
}

//获取某年某月的最后一天日期：28/29/30/31
function getLastDay(year,month){
	var searchYear=year;
	var searchMonth=month;
	if(searchYear==null){
		searchYear=currentYear;
	}
	if(searchMonth==null){
		searchMonth=currentMonth;
	}
	var  day = new Date(searchYear,searchMonth,0); 
	return day.getDate();
}

//获取某年某月的所有日期：1,2,3,4,5,6,7.....30/31
function getDaysOfMonth(year,month){
	var lastDay=getLastDay(year,month);
	var dayArray=new Array();
	for(var i=1;i<=lastDay;i=i+1){
		dayArray.push(i);
	}
	return dayArray;
}

function getDetailInfoDaysOfMonth(year,month){
	var lastDay=getLastDay(year,month);
	var dayArrayNums=getDaysOfMonth(year,month);
	var dayArray=new Array();
	for(var i in dayArrayNums){
		dayArray.push(new Date(year,month-1,dayArrayNums[i]));
	}
	return dayArray;
}


function castStrToDate(dateStrArg){
	var dateStr=dateStrArg.replace(/-/g, "/");
	return new Date(dateStr);
}

function compareDateStr(dateStr1,dateStr2){
	var date1=castStrToDate(dateStr1);
	var date2=castStrToDate(dateStr2);
	return compareDate(date1,date2);
	
}


//比较日期，若第一个大于第二个返回1，若第一个小于第二个返回-1,相等返回0
function compareDate(date1,date2){
	if(date1>date2){
		return 1;
	}
	else if(date1<date2){
		return -1;
	}
	else{
		return 0;
	}
}
