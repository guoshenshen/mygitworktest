/*
 * generate basic template module for platform
 * created by zq@cnic.cn
 * created on 2017-04-26
 * last edited on 2017-04-26
 * 
 */
var menuLoadingOptions={
		rootPath:""
		
};



menuLoadingOptions.ajaxHTML=function(url,actionAfterLoading){
    $.ajax({
        url:menuLoadingOptions.rootPath+url,
        dataType:'text',
        success:function(data){
    		actionAfterLoading(data);
        },
        complete:function(ev){
        }
    })
}

//加载门户页脚
menuLoadingOptions.loadFooter=function(){
	menuLoadingOptions.ajaxHTML("htmlStructure/footer.html", function(footerHtml){
		$("#portalStyle #foot").html(footerHtml);
	});
}

menuLoadingOptions.loadSimpleFooter=function(){
	menuLoadingOptions.ajaxHTML("htmlStructure/simplefooter.html", function(simpleFooterHtml){
		$("#bottombody").html(simpleFooterHtml);
	})
}



//加载简易导航栏
menuLoadingOptions.loadSimpleNavi=function(){
	menuLoadingOptions.ajaxHTML("htmlStructure/simpleNavigater.html", function(simpleNavigaterHtml){
	});
	
}


//加载详细导航栏
menuLoadingOptions.loadDetailNavi=function(){
	menuLoadingOptions.ajaxHTML("htmlStructure/navigaterDetail.html", function(detailNavigaterHtml){
		$("#portalStyle #nav").html(detailNavigaterHtml);
	});
}


//加载课程辅助导航
menuLoadingOptions.loadCourseToolBox=function(){
	menuLoadingOptions.ajaxHTML("htmlStructure/courseToolBox.html", function(courseToolBoxHtml){
		$(".courseToolPanelParent").append(courseToolBoxHtml);
		$(".courseToolPanelParent .informationPanel").mCustomScrollbar();
		$("#j-hideRightBtn").click(function(){
			$("body").removeClass("showBoxStatus").addClass("hideBoxStatus");
			$("#courseToolBox").animate({right:"-335px"},500,function(){	
			});
		});
		$("#j-showRightBtn").click(function(){
			$("#courseToolBox").animate({right:"0"},500,function(){
				$("body").removeClass("hideBoxStatus").addClass("showBoxStatus");
			});
		});
	});
}

