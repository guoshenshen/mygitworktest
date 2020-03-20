;(function($){
	
	$("body").on("click",".series-link",function(){
		var enterUrl=$("input[name=url]",$(this)).val();
		window.open(enterUrl);	
	});
	
	$.extend({
		
		/*
		 * 在$targetPanel（必须是ul标签）中显示系列信息
		 * deletePast:是否删除过去的
		 */
		standardSeriesList:function(seriesList,$targetPanel,deletePast){
			var seriesListInfo=$.generateSeriesList(seriesList);
			if($(".col",$targetPanel).length==0){
				$targetPanel.append("<li class='col-left col' rel='loaded'></li>");
				$targetPanel.append("<li class='col-right col' rel='loaded'><li>");
				$targetPanel.append("<div style='clear:both' rel='loaded'></div>");
			}
			if(deletePast){
				$(".col-left",$targetPanel).empty();
				$(".col-right",$targetPanel).empty();
			}
			$(".col-left",$targetPanel).append(seriesListInfo.leftSeries.join(""));
			$(".col-right",$targetPanel).append(seriesListInfo.rightSeries.join(""));
		},
		
		
		//加载系列资源信息
		generateSeriesList:function(seriesList,studySeries){
			var seriesListAllArray=new Array();
			var seriesListLength=seriesList.length;
			for(var i=0;i<seriesListLength;i++){
				var currentSeriesList=null;
				var series=seriesList[i];
				var currentSeries=new Array();
				currentSeries.push("<li class='seriesLi'>");
				currentSeries.push("<div class='section'><a class='series-link'><div class='series-pic'>");
				currentSeries.push("<img src='"+series.mainPicUrl+"'"+"onerror=\"imgError({type:4,target:this})\""+" />");
				currentSeries.push("<input type='hidden' name='url' value='"+series.url+"'></input>");
				if(typeof(studySeries) != 'undefined'){
					if(studySeries.indexOf(series.id.toString())!=-1)
						currentSeries.push("<button type=\"button\" class=\"btn btn-danger studySeries\" value=\""+series.id+"\">退选</button>");
					else
						currentSeries.push("<button type=\"button\" class=\"btn btn-success studySeries\" value=\""+series.id+"\">选学</button>");
						
				}
				currentSeries.push("</div></a>");
				currentSeries.push("<div class='series-desc'>");
				currentSeries.push("<h2 class='title'>"+series.title);
				currentSeries.push("<font>"+series.sponsorInfo+"</font>");
				currentSeries.push("<em></em></h2>");
				currentSeries.push("<div class='description'><span>");
				currentSeries.push(series.detail);
				currentSeries.push("</span></div></div></div>");
				currentSeries.push("</li>");
				seriesListAllArray.push(currentSeries.join(""));
			}
			return seriesListAllArray;
		},
		
		//加载系列资源信息
		generateOldSeriesList:function(seriesList,studySeries){
			var seriesListAllArray=new Array();
			var seriesListLength=seriesList.length;
			for(var i=0;i<seriesListLength;i++){
				var series=seriesList[i];
				var currentSeries=new Array();
				currentSeries.push("<li class='seriesLi'>");
				currentSeries.push("<div class='section'><a class='series-link'><div class='series-pic'>");
				currentSeries.push("<img src='"+series.mainPicUrl+"' />");
				currentSeries.push("<input type='hidden' name='url' value='"+series.url+"'></input>");
				if(typeof(studySeries) != 'undefined'){
					if(studySeries.indexOf(series.id.toString())!=-1){
						currentSeries.push("<button type=\"button\" class=\"btn btn-danger studySeries\" onclick=\"studySeries(this,event)\" value=\""+series.id+"\">退选</button>");
					}
					else{
						currentSeries.push("<button type=\"button\" class=\"btn btn-success studySeries\" onclick=\"studySeries(this,event)\" value=\""+series.id+"\">选学</button>");
					}
				}
				currentSeries.push("</div></a>");
				currentSeries.push("<div class='series-desc'>");
				currentSeries.push("<h2 class='title'>"+series.title);
				currentSeries.push("<font>"+series.sponsorInfo+"</font>");
				currentSeries.push("<em></em></h2>");
				currentSeries.push("<div class='description'><span>");
				currentSeries.push(series.detail);
				currentSeries.push("</span></div></div></div>");
				currentSeries.push("</li>");
				seriesListAllArray.push(currentSeries.join(""));
			}
			return seriesListAllArray;
		},
		
	})
	
	
	
})(jQuery);

function strToArray(str){
	str = str.replace('[','');
	str = str.replace(']','');
	str = str.replace(new RegExp(' ','g'),'');
	var array = str.split(",");
	return array;
}
