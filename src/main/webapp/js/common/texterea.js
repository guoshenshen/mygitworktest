function gbcount(message,maxlength) 
{ 
	var max; 
	max = maxlength; 
	if (message.value.length > max) 
	{ 
		message.value = message.value.substring(0,max); 
		alert("内容不能超过 "+maxlength+" 个字!"); 
	} 

}