////以小窗口形式显示某个通讯录的基本信息

var addressBookWindow={
	operatorIdArray:[],
	iframeSrc:"",
	flag:""
};

var $addressBookEditorModal=null;

$(function(){
	if(!$.isScriptIncluded("remodal.min.js")){
		$.loadScript("/js/UI/remodal.min.js",function(){
			$addressBookEditorModal=$("[data-remodal-id=addressBookEditor]").remodal($.defaultRemodalOption());
		});
	}
	else{
		$addressBookEditorModal=$("[data-remodal-id=addressBookEditor]").remodal($.defaultRemodalOption());
	}
})

$(document).on('closed', '.addressBookEditor', function (e) {
	$(".addressBookEditor .bookInfo").attr("src","");
});

$.extend({
	showAddressBookEditor:function(params){
		$(".addressBookEditor .bookInfo").attr("src",params.src);
		$addressBookEditorModal.open();
	}
});

function showAddressBookWin(options){
	 var pop_url=addressBookWindow.iframeSrc;
     var originalWidth=730;
     var originalHeight=620;
     if(options!=null){
    	 if(options.width!=null){
    		 originalWidth=options.width;
    	 }
    	 if(options.height!=null){
    		 originalHeight=options.height;
    	 }
     }
     
     var pop=$("<div id='_pop_win' style='position:relative;'><h2>通讯录信息"
				+"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
				+"<iframe height='" +originalHeight+
						"' width='" +originalWidth+
						"' scrolling='auto' class='pop_iframe' src='"+pop_url+"'></iframe></div>"
				+"<div style='clear:both'></div>"
				);
	$.blockUI({ message: pop,
				     css: {width:originalWidth+"px",height:(originalHeight+35)+"px",cursor:"default",left:($left - originalWidth) /2-10 + 'px',top:($top-originalHeight+35) /2 -10+ 'px'}
		});
	
	pop.find("a.pop_close_btn").click(function(){
		$.unblockUI();
	});
}

//通讯录创建（已经指定了通讯录人员范围）,显示通讯录预览页面
function forAddAddrBook(param){
	 var $check=$(".forAddrBook:checked");
	 if($check.length>0){
		 addressBookWindow.operatorIdArray=new Array();
		 var paramArray=new Array();
		 for(var i in param){
			 if(i=="trainId"){
			 	paramArray.push("?");
			 }else{
			 	paramArray.push("&");
			 }
			 paramArray.push(i);
			 paramArray.push("=");
			 paramArray.push(param[i]);
			 
			 if(i=="flag"){
				 addressBookWindow.flag = param[i];
				 console.log(addressBookWindow.flag);
			 }
		 }
	     addressBookWindow.iframeSrc="../addrBook/showPrevAddressBook.do"+paramArray.join("");
	     if($check.length>0){
	     	$.each($check,function(i,item){
	     		addressBookWindow.operatorIdArray.push(item.getAttribute("value"));
	     	})
	     }
	     showAddressBookWin();
	 } else{
		 try{
			 jAlert('请您先勾选人员','提示信息');
		 }catch(e){
			 alert('请您先勾选人员');
		 }
	 }
}

//通讯录创建、编辑（尚未指定通讯录人员范围）,显示通讯录创建编辑预览页面
function forEditAddrBook(options){
	var params=new Array();
	for(var i in options){
		params.push(i+"="+options[i]);
	}
	if(params.length>0){
		//addressBookWindow.iframeSrc="AddrBookAction.do?method=forUpdateAddrBook&"+params.join("&");
		$.showAddressBookEditor({"src":"../addrBook/forUpdateAddrBook.do?"+params.join("&")});
	}
	else{
		//addressBookWindow.iframeSrc="AddrBookAction.do?method=forUpdateAddrBook";
		$.showAddressBookEditor({"src":"../addrBook/forUpdateAddrBook.do"});
	}	
}
function showAddrBook(addrbookId){
     //addressBookWindow.iframeSrc="AddrBookAction.do?method=showAddrBook&addrbookId="+addrbookId;
     //showAddressBookWin();
	$.showAddressBookEditor({"src":"../addrBook/showAddrBook.do?addrbookId="+addrbookId});
}