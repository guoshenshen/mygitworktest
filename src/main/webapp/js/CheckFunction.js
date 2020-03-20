
//检查是否是编码字符（只允许字母、数字和“-_”两个字符）
/*
field 被限制的控件
crit = 1
msg出错反馈信息
*/
function deleteChar(values,lit){
	
	var iA = values.split(lit);
	var str = "";
	for(var i=0;i<iA.length;i++){
		str = str+iA[i];
	}
	return (str);
}

//
function isCodeCharacter(field,crit,msg){
	var	sValue = Trim(field.value);
	if(field.value.length != sValue.length){
		field.value = sValue;
	}
	if( sValue.length == 0 ){
		doCritCode(field,crit,msg);
		return false;
	}
	var iCharacter=0;
	for( i = 0; i < sValue.length; i ++ ){
		iCharacter = sValue.charAt(i).charCodeAt(0);
		if(iCharacter >=48 && iCharacter<= 57){//数字
		}else if(iCharacter >=65 && iCharacter<= 90){//大写字母
		}else if(iCharacter >=97 && iCharacter<= 122){//小写字母
		}else if(iCharacter == 45){//-
		}else if(iCharacter == 95){//_
		}else if(iCharacter<0||iCharacter>255){//判断文字	
		}else{
			doCritCode(field,crit,msg);
			return false;
		}
	}
	/*var sFormat = "0123456789-_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	for( i = 0; i < sValue.length; i++ ){
		var cCharacter = sValue.charAt( i );
		if( sFormat.indexOf( cCharacter, 0 ) < 0 ){
			doCritCode(field,crit,msg);
			return false;
		}
	}*/
	return true;
}

//判断长度是否符合要求
/*
field 被限制的控件
len 长度要求
msg出错反馈信息
*/
function isValidFieldLength(field,len,msg)
{
	if(field.value.length > len)
	{	var msgalert="";
		msgalert=msg+"最多允许输入"+len+"个字，请修改！";
		doCritCode(field,1,msgalert);  
		return false;
	}
	else return true;
}

//判断长度是否符合要求
/*
field 被限制的控件
len 长度要求
msg出错反馈信息
*/
function isValidLength(field,len,msg)
{
	if(field.value.length > len)
	{
		doCritCode(field,1,msg);  
		return false;
	}
	else return true;
}

// 判断是否是IP地址 true: 是 ； false:不是
//增加的
function isIP(field,crit,msg)
{
	if(is_null(field,crit,msg) == true){
		return false;
	};
	var iIndex1 = field.value.indexOf(".",0);
	var iIndex2 = field.value.indexOf(".",iIndex1+1);
	var iIndex3 = field.value.indexOf(".",iIndex2+1);
	if(iIndex1 <= 0 || iIndex2 <= 0 || iIndex3 <= 0 || iIndex1 >= iIndex2-1 || iIndex2 >= iIndex3-1){
		doCritCode(field,crit,msg);  
		return false;
	};
	var str1 = field.value.substring(0,iIndex1);
	var str2 = field.value.substring(iIndex1+1,iIndex2);
	var str3 = field.value.substring(iIndex2+1,iIndex3);
	var str4 = field.value.substring(iIndex3+1);
	if(isDigital(str1) == false||isDigital(str2) == false||isDigital(str3) == false||isDigital(str4) == false){
		doCritCode(field,crit,msg);  
		return false;
	}
	if(parseInt(str1) > 255 || parseInt(str1) < 0||
		parseInt(str2) > 255 || parseInt(str2) < 0||
		parseInt(str3) > 255 || parseInt(str3) < 0||
		parseInt(str4) > 255 || parseInt(str4) < 0){
		doCritCode(field,crit,msg);  
		return false;
	}
	else return true;
}
// 判断是否为实数
//增加的
function is_real(field,crit,msg)
{
	var Ret = true;
	var NumStr=".-0123456789";
	var decUsed=false;
	var chr;
	var iA = field.value.split(".");
	if(iA.length>=3){
		Ret=false;
	}
	iA = field.value.split("-");
	if(iA.length>=3){
		Ret=false;
	}else if(iA.length==2){
		if(iA[0] != ""){
			Ret=false;
		}
	}
	if (Ret){
		for (i=0;i<field.value.length;++i){
			chr=field.value.charAt(i);
			if (NumStr.indexOf(chr)==-1){
				Ret=false;
			}
		}
	}
	if (!Ret)
		doCritCode(field,crit,msg);
	else return(Ret);
} 
//判断是否小于某个实数
function isNotLess(field,crit,msg,num){
	{
		if(!/^\d+\.?\d*$/.test(field.value)||parseFloat(field.value)<=num){
			//alert(msg);
			jAlert(msg,"填写提示");
			field.focus();
			return false;
		}
		else return true;
	}
}
// 判断是否大于0的实数
function is_greaterZero(field,crit,msg)
{
	if(!/^\d+\.?\d*$/.test(field.value)||parseFloat(field.value)<=0){
		//alert(msg);
		jAlert(msg,"填写提示");
		field.focus();
		return false;
	}
	else return true;
}
//判断是否为大于某个数的整数
function is_greaterInt(field,crit,msg,num)
{

	if(is_int(field, crit, msg) == false){
		return false;
	}
	if (field.value <= num){
		
		doCritCode(field,crit,msg);
		return false;
	}
	else{
		return true;
	}
}


// 判断是否大于0的整数
function is_greaterZeroInt(field,crit,msg)
{
	if(is_int(field, crit, msg) == false){
		return false;
	}
	if (field.value <= 0){
		doCritCode(field,crit,msg);
		return false;
	}
	else return true;
}
// 将一个变量转换为对象
function var_to_obj(val)
{
	this.value=val;
}
// 判断是否大于某个数
function is_greater(field,crit,limit)
{
	var Ret = (is_numeric(field,-1) ) ? (field.value > limit )  : false;
	if (!Ret)
		doCritCode(field,crit,"Value must be greater than "+limit);
	else return(Ret);
}
//判断是否小于等于某个数
function is_nogreater(field,crit,limit,msg)
{
	var Ret = (is_numeric(field,-1) ) ? (field.value <= limit )  : false;
	if (!Ret)
		doCritCode(field,crit,msg);
	else return(Ret);
}
// 判断是否小于某个数
function is_less(field,crit,limit)
{
	var Ret = (is_numeric(field,-1) ) ? (field.value < limit )  : false;
	if (!Ret)
		doCritCode(field,crit,"Value must be less than "+limit);
	else return(Ret);
}

//比较两个日期的大小，Num1>Num2 return:true;Num1<=Num2 return:false
function compare_date(Num1,Num2)
{
		var pos1,pos2,end;
		var para1,para2,para3,para4,para5,para6;

		//para1:年
		//para2:月
		//para3:日
		end=Num1.length;
		pos1=Num1.indexOf("-",0);
		pos2=Num1.indexOf("-",pos1+1);
		para1=Num1.substring(0,pos1);
		para2=Num1.substring(pos1+1,pos2);
		para3=Num1.substring(pos2+1,end);
		para1=parseFloat(para1);
		para2=parseFloat(para2);
		para3=parseFloat(para3);		
		end=Num2.length;
		pos1=Num2.indexOf("-",0);
		pos2=Num2.indexOf("-",pos1+1);
		para4=Num2.substring(0,pos1);
		para5=Num2.substring(pos1+1,pos2);
		para6=Num2.substring(pos2+1,end);
		para4=parseFloat(para4);
		para5=parseFloat(para5);
		para6=parseFloat(para6);		
		if(para1>para4)
		{
			return true;
		}
		else if(para1==para4)
		{
			if(para2>para5)
			{
				return true;
			}
			else if(para2==para5)
			{
				if(para3>para6)
				{
					return true;
				}
			}
		}
		else return false;
	
}
//比较两个日期的大小，Num1>=Num2 return:true;Num1<Num2 return:false
function compare_today(Num1,Num2)
{
		var pos1,pos2,end;
		var para1,para2,para3,para4,para5,para6;

		//para1:年
		//para2:月
		//para3:日
		end=Num1.length;
		pos1=Num1.indexOf("-",0);
		pos2=Num1.indexOf("-",pos1+1);
		para1=Num1.substring(0,pos1);
		para2=Num1.substring(pos1+1,pos2);
		para3=Num1.substring(pos2+1,end);
		para1=parseFloat(para1);
		para2=parseFloat(para2);
		para3=parseFloat(para3);
		end=Num2.length;
		pos1=Num2.indexOf("-",0);
		pos2=Num2.indexOf("-",pos1+1);
		para4=Num2.substring(0,pos1);
		para5=Num2.substring(pos1+1,pos2);
		para6=Num2.substring(pos2+1,end);
		para4=parseFloat(para4);
		para5=parseFloat(para5);
		para6=parseFloat(para6);
		if(para1>para4)
		{
			return true;
		}
		else if(para1==para4)
		{
			if(para2>para5)
			{
				return true;
			}
			else if(para2==para5)
			{
				if(para3>para6)
				{
					return true;
				}
				else if(para3==para6)
				{
					return true;
				}
			}
		}
		else return false;
	
}
//判断身份证的合法性
function compare_birthday(birthday,card)
{
		var pos1,pos2,end;
		var para1,para2,para3,para4;

		//para1:年
		//para2:月
		//para3:日
		end=birthday.length;
		pos1=birthday.indexOf("-",0);
		end=card.length;
		if (end==18)
		{
			para1=birthday.substring(0,pos1);
			para4=card.substring(6,14);
		}
		else {
			para1=birthday.substring(2,pos1);
			para4=card.substring(6,12);
		}
		pos2=birthday.indexOf("-",pos1+1);
		para2=birthday.substring(pos1+1,pos2);
		para3=birthday.substring(pos2+1,end);
		para1= para1 + para2 + para3;
		if(para1==para4)
		{
			return true;
		}
		else {
			return false;
		}	
}
//format float data as:*****.**
//decplaces:小数位数
function FloatFormat(expr,decplaces)
{
        var str = "" + Math.round(eval(expr)*Math.pow(10,decplaces));
        while(str.length <= decplaces)
        {
                str = "0" + str;
        }

        var decpoint = str.length - decplaces;
        return str.substring(0,decpoint) + "." + str.substring(decpoint,str.length);
}
function is_numeric(field,crit,msg)
{
	var Ret = true;
	var NumStr="0123456789";
	var decUsed=false;
	var chr;
	for (i=0;i<field.value.length;++i)
	{
		chr=field.value.charAt(i);
		if (NumStr.indexOf(chr,0)==-1)
		{
			if ( (!decUsed) && chr==".")
			{
				decUsed=true;
			}
			else
			{
				Ret=false;
			}
		}
	}
	if (!Ret)
		doCritCode(field,crit,msg);
	else return(Ret);
} 

 // 判断是否是价格
function is_price(field,crit,msg)
{
	var Ret = true;
	var NumStr="0123456789";
	var decUsed=false;
	var chr;
	for (i=0;i<field.value.length;++i)
	{
		chr=field.value.charAt(i);
		if (NumStr.indexOf(chr,0)==-1)
		{
			if ( (!decUsed) && chr==".")
			{
				decUsed=true;
			}
			else
			{
				Ret=false;
			}
		}
	}
	if(Ret)
	{
		if(decUsed&&(field.value.length-field.value.indexOf('.')<4))
		;
		else if(decUsed)
			Ret=false;
	}
	if (!Ret)
		doCritCode(field,crit,msg);
	return(Ret);
} 
 // 判断是否是空true:空false:非空
function is_null(field,crit,msg)
{
	Text=""+Trim(field.value);
	
	if(Text.length)
	{
		for(var i=0;i<Text.length;i++)
		if(Text.charAt(i)!=" ")
		break;
		if(i>=Text.length)
			Ret=true;
		else
			Ret=false;
	}
	else
		Ret=true;
	if (Ret)
		doCritCode(field,crit,msg);  
	return(Ret);
}


 
function doCritCode(field,crit,msg)
{
	if ( (-1!=crit) )
	{
		//alert(msg)
		jAlert(msg,"填写提示");
		if (crit==1)
		{
			field.focus();  // focus does not work on certain netscape versions
			field.select();
		}
	}
			return false;
}
// 判断是否是整数true:是整数，false:不是整数
function is_int(field,crit,msg){
	var Ret = true;
	var NumStr="0123456789";
	var chr;

	for (i=0;i<field.value.length;i++)
	{
		chr=field.value.charAt(i);
		if (NumStr.indexOf(chr,0)==-1)
		{
			Ret=false;
		}
	}
	if (!Ret)
		doCritCode(field,crit,msg);
	return(Ret);
}

// 判断是否是整数true:是整数，false:不是整数
function check_int(field,crit,msg){
	var Ret = true;
	var NumStr="-0123456789";
	var chr;

	for (i=0;i<field.value.length;i++)
	{
		chr=field.value.charAt(i);
		if (NumStr.indexOf(chr,0)==-1)
		{
			Ret=false;
		}
	}
	if (!Ret)
		doCritCode(field,crit,msg);
	return(Ret);
}



// 判断是否是日期
function is_date(field,crit,msg){
	var Ret = false;
	var mark1;
	var mark2;
	var days

	if(field.value=="")
		return true;
	cd=new Date();

	if ( (mark1 = field.value.indexOf('-'))==-1)
		mark1=field.value.indexOf('-')
	if (mark1>-1)
	{
		if ( (mark2 = field.value.indexOf('-',mark1+1)) ==-1)
			mark2=field.value.indexOf('-',mark1+1);
		if ((mark2>-1)&&(mark2+1<field.value.length) )
		{
			year = new var_to_obj(field.value.substring(0,mark1));
			month = new var_to_obj(field.value.substring(mark1+1,mark2));
			day = new var_to_obj(field.value.substring(mark2+1,field.value.length));
			days = getDaysInMonth(month.value,year.value) + 1
			
			if (
				(is_greater(day,-1,0))&&(is_less(day,-1,days))&&
				(is_greater(month,-1,0))&&(is_less(month,-1,13))&&
				(is_greater(year,-1,1900))&&(is_less(year,-1,2500))
				)
				Ret=true;
		}
	}
	if (!Ret) doCritCode(field,crit,msg);

	return(Ret);
}

function is_date2(tmpy,tmpm,tmpd)
{  
	year=new String (tmpy);
	month=new String (tmpm);
	day=new String (tmpd)
	if ((tmpy.length!=4) || (tmpm.length>2) || (tmpd.length>2))
	{
		return false;
	}
	if (!((1<=month) && (12>=month) && (31>=day) && (1<=day)) )
	{
		return false;
	}
	if (!( ((year % 4)==0) && ((year % 400)==0) ) && (month==2) && (day==29))
	{
		return false;
	}
	if ((month<=7) && ((month % 2)==0) && (day>=31))
	{
		return false;
	
	}
	if ((month>=8) && ((month % 2)==1) && (day>=31))
	{
		return false;
	}
	if ((month==2) && (day==30))
	{
		return false;
	}
	
	return true;
}


function doCrit(field,crit,msg)
{
	if ( (-1!=crit) )
	{
		//alert(msg);
		jAlert(msg,"填写提示");
		if (crit==1)
		{
			field.focus();  // focus does not work on certain netscape versions
		}
	}
}
// 判断是否有有效数据被选中
function is_selected(field,crit,msg)
{
	value=""+field.options[field.selectedIndex].value;
	if(value=="0")
		Ret=false;
	else
		Ret=true;
	if (!Ret)
		doCrit(field,crit,msg);  
	return(Ret);
}



// 检查是否是字符
// cCharacter：输入值
function isCharacter( cCharacter )
{
	var sFormat = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	if( sFormat.indexOf( cCharacter, 0 ) == -1 )
	{
		return false;
	}
	
	return true;
}

// 检查是否是其它可以作名称的字符
// cCharacter：输入值
function isOtherNameCharacter( cCharacter )
{
	var sFormat = "_-";
	
	if( sFormat.indexOf( cCharacter, 0 ) == -1 )
	{
		return false;
	}
	
	return true;
}

// 检查是否是可以作名称的字符
// sValue：输入值
function isNameCharacter(field,crit,msg)
{
	var	sValue = Trim(field.value);
	if( sValue.length == 0 )
	{
		doCritCode(field,crit,msg);
		return false;
	}
	if( sValue.length < 4 ) 
	{
		doCritCode(field,crit,msg);
		return false;
	}
	else if ( sValue.length > 50)
	{
		doCritCode(field,crit,msg);
		return false;
	}

	var iCharacter=0;
	
	for( i = 0; i < sValue.length; i ++ )
	{
		iCharacter = sValue.charAt(i).charCodeAt(0);
		if(iCharacter >=48 && iCharacter<= 57){//数字
		}else if(iCharacter >=65 && iCharacter<= 90){//大写字母
		}else if(iCharacter >=97 && iCharacter<= 122){//小写字母
		}else if(iCharacter == 45){//-
		}else if(iCharacter == 95){//_
		}else{
		//var cCharacter = sValue.charAt( i );
		//if( isDigital( cCharacter ) == false && isCharacter( cCharacter ) == false && isOtherNameCharacter( cCharacter ) == false )
		//{
			doCritCode(field,crit,msg);
			return false;
		}
	}
	
	return true;
}

function isName(sValue)
{
	if( sValue.length == 0 )
	{
		return false;
	}
	if( sValue.length < 4 ) 
	{
		return false;
	}
	else if ( sValue.length > 50)
	{
		return false;
	}
	
	for( i = 0; i < sValue.length; i ++ )
	{
		var cCharacter = sValue.charAt( i );
		if( isDigital( cCharacter ) == false && isCharacter( cCharacter ) == false && isOtherNameCharacter( cCharacter ) == false )
		{
			return false;
		}
	}
	
	return true;
}
function isStr(sValue)
{
	if( sValue.length == 0 )
	{
		return false;
	}	
	for( i = 0; i < sValue.length; i ++ )
	{
		var cCharacter = sValue.charAt( i );
	//	alert(cCharacter+isCharacter( cCharacter ));
		if( isCharacter( cCharacter ) == false  )
		{
			return false;
		}
	}
	
	return true;
}
// 检查是否是Email
// sValue：输入值，合法格式为a@b.c.d此类形式
function is_Email( sValue )
{
	var iFirstIndex = 0;
	var iSecondIndex = sValue.indexOf( '@' );
	if( iSecondIndex == -1 )
	{
		return false;
	}
	var sTemp = sValue.substring( iFirstIndex, iSecondIndex );
	/*if( isName( sTemp ) == false )
	{
		return false;
	}*/
	iSecondIndex = sValue.indexOf( '.' );
	if( iSecondIndex == -1 || sValue.substring( sValue.length-1, sValue.length ) == '.' )
	{
		return false;
	}
	/*
	else if(  sTemp.length == sValue.length - 2 )	// The last two characters are '@' and '.'
	{
		return false;
	}
	else
	{
		var sTempValue = sValue;
		iSecondIndex = sValue.indexOf( '@' );		
		while( iSecondIndex != -1 )
		{
			iFirstIndex = iSecondIndex + 1;
			sTempValue = sTempValue.substring( iFirstIndex, sTempValue.length );	// The right section of value
			iSecondIndex = sTempValue.indexOf( '.' );
			alert(sTempValue);
			//document.write( "sTempValue=" + sTempValue + "<br>" );
			sTemp = sTempValue.substring( 0, iSecondIndex );
			sTempValue = sTempValue.substring(iSecondIndex);
			alert(sTemp);
			//document.write( "sTemp=" + sTemp + "<br>" );
			if( isName( sTemp ) == false )
			{
				return false;
			}
		}
		
		if( isName( sTempValue ) == false )
		{
			return false;
		}
	}
	*/
	return true;
} 

// 检查是否是邮编
// sValue：输入值，合法格式为六位整数
function is_zipcode( sValue )
{
	if( sValue == null )
	{
		return false;
	}
	
	if( sValue.length != 6 )
	{
		return false;
	}
	else
	{
		for( i = 0; i < 6; i ++ )
		{
			if( isDigital( sValue.charAt( i ) ) == false )
			{
				return false;
			}
		}
	}
	
	return true;
} 

// 检查是否是数字字符串
// sValue：输入值
function isDigitalString( sValue )
{
	if( sValue == null )
	{
		return false;
	}

	for( i = 0; i < sValue.length; i ++ )
	{
		if( isDigital( sValue.charAt( i ) ) == false )
		{
			return false;
		}
	}
}


//Trim函数去掉一字符串两边的空格
function Trim(his)
{
   //找到字符串开始位置
   Pos_Start = -1;
   for(var i=0;i<his.length;i++)
   {
     if(his.charAt(i)!=" ")
      {
         Pos_Start = i;
         break; 
      }
   }
   //找到字符串结束位置
   Pos_End = -1;
   for(var i=his.length-1;i>=0;i--)
   {
     if(his.charAt(i)!=" ")
      {
         Pos_End = i; 
         break; 
      }
   }
   //返回的字符串
   Str_Return = "";
   if(Pos_Start!=-1 && Pos_End!=-1){    
   		Str_Return=his.substring(Pos_Start,Pos_End+1);
		/*for(var i=Pos_Start;i<=Pos_End;i++)
		{
			   Str_Return = Str_Return + his.charAt(i); 
		}*/
   }
   return Str_Return;
}  
//IsDigital函数判断一个字符串是否由数字(int or long)组成 
function isDigital(str)   
{
  for(ilen=0;ilen<str.length;ilen++)
  {
    if(str.charAt(ilen) < '0' || str.charAt(ilen) > '9' )
    {
       return false;
    }   
  }
  return true;
}
//IsFloat函数判断一个字符串是否由数字(int or long or float)组成 
function is_float(str)   
{
  flag_Dec = 0
  for(ilen=0;ilen<str.length;ilen++)
  {
    if(str.charAt(ilen) == '.')
    {
       flag_Dec++;
	   if(flag_Dec > 1)
          return false;
       else
          continue;
    }         
    if(str.charAt(ilen) < '0' || str.charAt(ilen) > '9' )
    {
       return false;
    }   
  }
  return true;
}
//IsTelephone函数判断一个字符串是否由数字或'-','*','()'组成 
function is_telephone(str)   
{
  for(ilen=0;ilen<str.length;ilen++)
  {
    if(str.charAt(ilen) < '0' || str.charAt(ilen) > '9' )
    {
		if((str.charAt(ilen)!='-')&&(str.charAt(ilen)!='*')&&(str.charAt(ilen)!='(')&&(str.charAt(ilen)!=')'))
        return false;
    }   
  }
  return true;
}
//日期格式转化2/18/2000 ----2000-2-18
	function dateTransfer(strdate)
	{

		var pos1,pos2,end;
		var para1,para2,para3;
		var newdate;
		newdate="";
		//para1:月
		//para2:日
		//para3:年
		if(Trim(strdate)=="")
		{
			return(newdate);
		}
		end=strdate.length;
		pos1=strdate.indexOf("/",0);
		pos2=strdate.indexOf("/",pos1+1);
		para1=strdate.substring(0,pos1);
		para2=strdate.substring(pos1+1,pos2);
		para3=strdate.substring(pos2+1,end);
		newdate=para3+"-"+para1+"-"+para2;		
		return(newdate);
	}
//转化日期2000-10-20 ---->10/20/2000
function transferDate(str)
{
  var m=4;
  var strlen=str.length
  var n=strlen-1;
  if(Trim(str)=="")
  {
		return(str);
  }
  while (n>=strlen-2)
  {
   if(str.charAt(n)=="-")
    {
      break;
    }
   n=n-1
  }
  trimstr=str.substring(m+1,n)+"/"+ str.substring(n+1,strlen)+"/"+str.substring(0,m) 
  return(trimstr);
}
   
//检查是否是密码
function is_password( sValue )
{
	if( sValue == null )
	{
		return false;
	}
	
	for( i = 0; i < sValue.length; i ++ )
	{
		var cCharacter = sValue.charAt( i );
		if( isDigital( cCharacter ) == false && isCharacter( cCharacter ) == false )
		{
			return false;
		}
	}
	
	return true;
}

//判断是否为润年的函数
//参数说明：Year--年份
//          返回值:如果是润年，返回true；否则返回false.

function isLeapYear (Year) {
if (((Year % 4)==0) && ((Year % 100)!=0) || ((Year % 400)==0)) {
return (true);
} else { return (false); }
}

//取得每月天数的函数
//参数说明：month--月;year--年
//          返回值：days--天数
function getDaysInMonth(month,year)  {
var days;
if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)  days=31;
else if (month==4 || month==6 || month==9 || month==11) days=30;
else if (month==2)  {
if (isLeapYear(year)) { days=29; }
else { days=28; }
}
return (days);
}
function writeRegard()
{
	var d = new Date();
	var hour = parseFloat(d.getHours());
	if (hour>6 && hour<12)
		document.write("上午好");
	else if (hour>12 && hour<18)
		document.write("下午好");
	else
		document.write("晚上好");
}


  function ratifyTime(str){
          
		  var Ret = false;
		  //小时分割符
		
          var str1=str.value.substring(3,2);
		  //分钟分割符
		  var str2=str.value.substring(6,5);
			var phour;
		  var pmin;
		  var psec;

          if(str1!=":"||str2!=":")
		  {
			//alert("时间的格式应为：01:01:01");
			jAlert("时间的格式应为：01:01:01","填写提示");
		    str.focus();
			return(false);
		  };
		  

		  phour=parseFloat(str.value.substring(2,0));//小时
		  pmin=parseFloat(str.value.substring(5,3));//分钟
		  psec=parseFloat(str.value.substring(8,6));//秒
		 
		  if(phour>23||phour<0){
			//alert("时间的小时位不能超过23，不能小于0");
			  jAlert("时间的小时位不能超过23，不能小于0","填写提示");
			str.focus();
			return(false);
		  };
          if(pmin>59||pmin<0){
			//alert("时间的分钟位不能超过59，不能小于0");
        	  jAlert("时间的分钟位不能超过59，不能小于0","填写提示");
			str.focus();
			return(false);
		  };
		  if(psec>59||psec<0){
			//alert("时间的秒位不能超过59，不能小于0");
			  jAlert("时间的秒位不能超过59，不能小于0","填写提示");
			str.focus();
			return(false);
		  };


	      return(true);
		}


		
		//时间比较大小，如果相等返回0，大于返回1，小于返回2
		function compareTime(str1,str2){
		  var phour1;
		  var pmin1;
		  var psec1;

		  var phour2;
		  var pmin2;
		  var psec2;

		  phour1=parseFloat(str1.value.substring(2,0));//小时
		  pmin1=parseFloat(str1.value.substring(5,3));//分钟
		  psec1=parseFloat(str1.value.substring(8,6));//秒


		  phour2=parseFloat(str2.value.substring(2,0));//小时
		  pmin2=parseFloat(str2.value.substring(5,3));//分钟
		  psec2=parseFloat(str2.value.substring(8,6));//秒
			
		  if(phour1==phour2)
		  {
		    if(pmin1==pmin2)
			{
			  if(psec1==psec2)
			  {
			    return(0);
			  }
			  else
			  {
			    if(psec1>psec2)
				{
			      return(1);
                }
				else
				{
				  return(2);
				};
			  };
			}
			else
			{
			  if(pmin1>pmin2)
			  {
			    return(1);
			  }
			  else
			  {
			    return(2);
			  };
			};
		  }
		  else
		  {
		    if(phour1>phour2)
			{
			  return(1);
			}
			else
			{
			  return(2);
			};
		  };
		}	
		//时间比较大小，如果相等返回0，大于返回1，小于返回2
		function compareTime1(str1,str2){
		  var phour1;
		  var pmin1;
		  var psec1;

		  var phour2;
		  var pmin2;
		  var psec2;

		  phour1=parseInt(str1.substring(2,0));//小时
		  pmin1=parseInt(str1.substring(5,3));//分钟
		  psec1=parseInt(str1.substring(8,6));//秒


		  phour2=parseInt(str2.substring(2,0));//小时
		  pmin2=parseInt(str2.substring(5,3));//分钟
		  psec2=parseInt(str2.substring(8,6));//秒
			
		  if(phour1==phour2)
		  {
		    if(pmin1==pmin2)
			{
			  if(psec1==psec2)
			  {
			    return(0);
			  }
			  else
			  {
			    if(psec1>psec2)
				{
			      return(1);
                }
				else
				{
				  return(2);
				};
			  };
			}
			else
			{
			  if(pmin1>pmin2)
			  {
			    return(1);
			  }
			  else
			  {
			    return(2);
			  }
			}
		  }
		  else
		  {
		    if(phour1>phour2)
			{
			  return(1);
			}
			else
			{
			  return(2);
			};
		  };
		}	