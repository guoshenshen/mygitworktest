//全选、反选、不选

function _setSelected(id,type){
    var chechTemp = id.checked;
    with(document.form1){
        for ( var j=0; j<elements.length; j++ ){
            if ( elements[j].type == "checkbox" && elements[j].name == type) {
                if(chechTemp){
                    document.form1.elements[j].checked = true;
                }else{
                    document.form1.elements[j].checked = false;
                }
            }
        }
   }
}

function _setSel(bool) {
	document.getElementById('selectAll').checked = bool;
	_setSelected(document.getElementById('selectAll'),'selectbox');
}

function _setReverse() {
	var type = 'selectbox';
	document.getElementById('selectAll').checked = false;
    with(document.form1){
        for ( var j=0; j<elements.length; j++ ){
            if ( elements[j].type == "checkbox" && elements[j].name == type) {
                if(!document.form1.elements[j].checked){
                    document.form1.elements[j].checked = true;
                }else{
                    document.form1.elements[j].checked = false;
                }
            }
        }
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