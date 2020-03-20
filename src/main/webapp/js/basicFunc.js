///////////created by zq@cnic.cn/////////////////////////
/////////// this js file contains frequently used methods specified for such system//////////////////


//显示人员选择窗口
/**
 * edited by zq@cnic.cn at 2017-02-21
 * @param options
 * options.idTag
 * options.nameTag
 * @return
 */
function selectusers(options){
	
	var pop_url="systemAction.do?method=selectUsers" ;
	if(options.url!=null){
		pop_url=options.url;
	}
	if(options.idTag!=null){
		pop_url+="&idTag="+options.idTag;
	}
	if(options.nameTag!=null){
		pop_url+="&nameTag="+options.nameTag;
	}
	
	var winTag="选择人员";
	if(options.tag!=null){
		winTag=options.tag;
	}
	
	var iframeHeight=540;
	var iframeWidth=700;
	
	if(options.width!=null){
		iframeWidth=options.width;
	}
	if(options.height!=null){
		iframeHeight=options.height;
	}
	
	var popTop=($top-iframeHeight-40)/2-10;
	var popLeft=($left-iframeWidth)/2-10;
	
	var popHeight=iframeHeight+35
	
	/*pop window外观定义来自css/pop_window.css*/
	var pop=$("<div id='_pop_win'><h2>"+winTag+"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
				+"<iframe id='pop_selectUser' height='"+iframeHeight+"' width='"+iframeWidth+"' scrolling='auto' class='pop_iframe' style='margin-right:50px;' src='"+pop_url+"'> </iframe></div>"
				+"<div style='clear:both'></div>"
				);
	focusForm = "#form";//定义弹出窗关闭后，光标的定位form，解决弹出窗关闭后不能输入的问题
	pop.find("a.pop_close_btn").click(function(){
		$.unblockUI();
	});
	$.blockUI({ message: pop,
			    css: {width:iframeWidth+"px",height:popHeight+"px",cursor:"default",left:popLeft + 'px',top:popTop+ 'px'}
			 });
}





function userDetail(operatorId) {
		var pop_url="eosorgTEmployeeAction.do?method=detail&userid="+operatorId;
		var pop=$("<div id='_pop_win'><h2>人员详细信息"
				+"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
				+"<iframe height='450' scrolling='no' width='100%' class='pop_iframe' src='"+pop_url+"'> </iframe></div>"
				+"<div style='clear:both'></div>"
				);
		pop.find("a.pop_close_btn").click(function(){
			$.unblockUI();
		});
		$.blockUI({ message: pop,
				    css: {width:"500px",height:"480px",cursor:"default",left:"30%",top:"10%"}
			 });
}	

function selectdepts() {
  	var pop_url="pub/radioOrgTree.html?parent=orgUserMain";
			var pop=$("<div id='_pop_win'><h2>选择机构名称"
					+"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
					+"<iframe height='500' scrolling='yes' width='100%' class='pop_iframe' src='"+pop_url+"'> </iframe></div>"
					+"<div style='clear:both'></div>"
					);
			pop.find("a.pop_close_btn").click(function(){
				$.unblockUI();
			});
			$.blockUI({ message: pop,
					    css: {width:"400px",height:"520px",cursor:"default",left:"30%",top:"5%"}
				 });
}


function showUserDocumentInfo(operatorId,yearInfo){
	var year=yearInfo;
	if(operatorId!=null){
		if(year==null){
			year=getCurrentYear();
		}
		
		
		var pop_url="adminArchivesAction.do?from=2&year="+year+"&operatorId="+operatorId;
		var pop=$("<div id='_pop_win'><h2>人员档案信息"
				+"<a href='javascript:void(0);' class='pop_close_btn'>X</a></h2>"
				+"<iframe height='800' scrolling='auto' width='100%' class='pop_iframe' src='"+pop_url+"'> </iframe></div>"
				+"<div style='clear:both'></div>"
				);
		pop.find("a.pop_close_btn").click(function(){
			$.unblockUI();
		});
		$.blockUI({ message: pop,
				    css: {width:"800px",height:"800px",cursor:"default",left:"30%",top:"10%"}
			 });
		
	}
	
}