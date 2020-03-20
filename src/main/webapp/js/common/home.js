// JavaScript Document

//查询结果选择控制

function _setSelected(id,type, formname){
	var chechTemp = id.checked;
	with(document.getElementById(formname)){
		var form1 = document.getElementById(formname);
		var eles = form1.elements;
		for ( var j=0; j<eles.length; j++ ){
			if ( eles[j].type == "checkbox" && eles[j].name == type) {
				if(chechTemp&& eles[j].style.display!="none"){
					eles[j].checked = true;
				}else{
					eles[j].checked = false;
				}
			}
		}
   }
}

function _setSel(bool,type , formname) {
	var inputname = 'selectAll';
	if(arguments[3] != undefined) inputname=arguments[3];
	
	document.getElementById(inputname).checked = bool;
	_setSelected(document.getElementById(inputname),type,formname);
}

function _setReverse(type, formname) {
	var inputname = 'selectAll';
	if(arguments[2] != undefined) inputname=arguments[2];
	
	document.getElementById(inputname).checked = false;
	with(document.getElementById(formname)){
		var form1 = document.getElementById(formname);
		var eles = form1.elements;
		for ( var j=0; j<eles.length; j++ ){
			if ( eles[j].type == "checkbox" && eles[j].name == type) {
				if(!eles[j].checked){
					eles[j].checked = true;
				}else{
					eles[j].checked = false;
				}
			}
		}
   }
}

function _clearDate(startTime,endTime){
  
  var start=document.getElementById(startTime);
  if(start!=null){
  document.getElementsByName("startTime")[0].value="";
}

  var end=document.getElementById(endTime);
  if(end!=null){
  document.getElementsByName("endTime")[0].value="";
}

}

function isSelected(formname){
	var result = false;
	var formElement = formname;
	var checkElements = formElement.elements;
	
	for(var i=0;i<checkElements.length;i++)
	{
		if("checkbox"==checkElements[i].type && checkElements[i].checked==true && checkElements[i].name=="selectbox"){
			result = true;
			break;
		}
	}
	
	if(!result){alert('请选择要删除的选项！');return;}else{formElement.submit();}
}