
  
var jquery_navigationOptions={
		naviMenuData:[
		   
		],
		defaultLiClass:["green","red","orange","blue","purple","grey"],
		naviType:""
}

function removeNavigator(target){
	$(".vertical-menu",$(target)).remove();
}

function loadNavigator(target){
	var navigatorArray=new Array();
	navigatorArray.push("<ul class='vertical-menu'>");
	var defaultMenuOptions={"span_class":"",menuInfo:""};
	var length=jquery_navigationOptions.naviMenuData.length;
	if(length>0){
		var stepWidth=100.0/length+"%";
		for(index=0;index<length;index++){
			defaultMenuOptions={span_class:"",menuInfo:""};
			defaultMenuOptions.li_class=jquery_navigationOptions.defaultLiClass[index%jquery_navigationOptions.defaultLiClass.length];
			$.extend(defaultMenuOptions,jquery_navigationOptions.naviMenuData[index]);
			navigatorArray.push("<li class='");
			navigatorArray.push(defaultMenuOptions.li_class);
			navigatorArray.push("' title='");
			navigatorArray.push(defaultMenuOptions.menuInfo);
			navigatorArray.push("' width='");
			navigatorArray.push(stepWidth);
			navigatorArray.push("'><span class='");
			navigatorArray.push(defaultMenuOptions.span_class);
			navigatorArray.push("'></span>");
			navigatorArray.push("<a href='javascript:void(0);' ><span>");
			navigatorArray.push(defaultMenuOptions.menuInfo);
			navigatorArray.push("</span></a>");
		}
	}
	navigatorArray.push("</ul>");
	$(".vertical-menu",$(target)).remove();
	$(target).append(navigatorArray.join(""));
	var $verticalMenu=$(".vertical-menu",$(target));
	if(jquery_navigationOptions.naviType!=null){
		$verticalMenu.addClass(jquery_navigationOptions.naviType);
	}
	$(".vertical-menu li",$(target)).each(function(index,that){
		$(that).click(function(){
			if(typeof jquery_navigationOptions.naviMenuData[index].action=="function"){
				jquery_navigationOptions.naviMenuData[index].action();
			}
		})
	})
	return $verticalMenu;
}