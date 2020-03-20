//正则表达式
var pattern={
	//正数
	positiveNumPattern:	/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/,
	//正整数
	positiveIntegerPattern:  /^[0-9]*[1-9][0-9]*$/,
	//非负数
	nonNegativeNumPattern:/^\d+(\.\d+)?$/,
	//非负整数
	nonNegativeIntegerPattern: /^(0|[1-9]\d*)$/,
	//年份
	yearPattern:/^\d{4}$/
}

//校验年度是否有效
function verifyYear(arguments){
	var result=false;
	if(arguments==null);
	else{
		result=pattern.yearPattern.test(arguments);
	}
	return result;
}


//校验正数
function isPositiveNum(arguments) {
	var result=false;
	if(arguments==null);
	else{
		result= pattern.positiveNumPattern.test(arguments);
	}
	return result;
}

//校验正整数
function isPositiveIntegerNum(arguments) {
	var result=false;
	if(arguments==null);
	else{
		result= pattern.positiveIntegerPattern.test(arguments);
	}
	return result;
}

//校验非负数
function isNonNegativeNum(arguments) {
	var result=false;
	if(arguments==null);
	else{
		result= pattern.nonNegativeNumPattern.test(arguments);
	}
	return result;
}

//校验非负整数
function isNonNegativeInteger(arguments) {
	var result=false;
	if(arguments==null);
	else{
		result= pattern.nonNegativeIntegerPattern.test(arguments);
	}
	return result;
}



//邮箱校验 
function legalEmail(emailStr){
	if(emailStr==null||$.trim(emailStr)==""){
		return false;
	}
	else{
		var pattern =/^[a-zA-Z0-9_\-\.]{1,}@[a-zA-Z0-9_\-]{1,}\.[a-zA-Z0-9_\-.]{1,}$/; 
		return pattern.test(emailStr);
	}
}

//手机号码校验
function checkMobile(telNum){
	if(telNum==null||$.trim(telNum)==""){
		return false;
	}
	var mobilePattern=/^1[3|4|5|7|8]\d{9,10,11}$/;
	return mobilePattern.test(telNum);
}


//固定电话校验
function checkTel(telNum){
	if(telNum==null||$.trim(telNum)==""){
		return false;
	}
	else{
		var telpattern = /(^[0-9]{3,4}\-[0-9]{7,8}$)|^1[34578]\d{9,10}$/;
		return telpattern.test(telNum);
	}
}
