
function searchInfo(params){
	$("#infoSearch").val(params.searchInfo).click();
}


;(function($){
	
	var seriesListArea={
		queryData:{
			startIndex:0,
			length:19
		}	
	};
	
	$.extend({
		loadSeriesList:function(queryData){
			$("#mainbody #seriesList").stopScrollPagination();
			$.ajax({
				url:"/recommendSeries/getRecommendSeriesList.do",
				method:"post",
				dataType:"json",
				data:queryData,
				traditional:true,
				success:function(resultInfo){
					var resultList=resultInfo.data.data.list;
					var studySeries=new Array();
					/*if(typeof(resultInfo.studySeries) != "undefined"){
						studySeries = resultInfo.studySeries;
					}*/
					if(queryData.startIndex=="0"){
						$("#seriesList").html($.generateSeriesList(resultList,undefined));
					}
					else{
						$("#seriesList").append($.generateSeriesList(resultList,undefined));
					}
					
					var queryPagination=seriesListArea.queryData;
					queryPagination.startIndex=resultList.length;
					if(resultInfo.stopScroll=="true"){
						
					}
					else{
						queryData.startIndex=queryPagination.startIndex;
						queryData.length=queryPagination.length;
						$.scrollLoadSeriesInfo(queryData);
					}
				}
			})
		},
		scrollLoadSeriesInfo:function(queryData){
			$("#mainbody #seriesList").scrollLoadInfo({
				url:"/recommendSeries/getRecommendSeriesList.do",
				dataType:"json",
				params:queryData,
				heightOffset:40,
				beforeLoad:function(){
				},
				loader:function(resultInfo){
					var resultList=resultInfo.data.data.list;
					var studySeries=new Array();
					/*if(typeof(resultInfo.studySeries) != "undefined"){
						studySeries = resultInfo.studySeries;
					}*/
					$("#seriesList").append($.generateSeriesList(resultList,undefined));
					
					//var queryPagination=seriesListArea.queryData;
					//queryPagination.startIndex=resultList.length;
					if(resultInfo.stopScroll=="true"){
						$("#mainbody #seriesList").stopScrollPagination();
					}
				},
				afterLoaded:function(resultDataInfo){
					var queryPagination=seriesListArea.queryData;
					queryPagination.startIndex=$("#mainbody #seriesList li").length;
				}
				
			});
		},
		triggerLoadingSeriesList:function(additionData){
			if(additionData==null){
				additionData={};
			}
			var $orgSEQ=$("#loadingInfo input[name=orgSEQ]");
			var $tenantOrgSEQ=$("#loadingInfo input[name=tenantOrgSEQ]");
			var queryData=seriesListArea.queryData;
			$.extend(queryData,{
				orgSEQ:$orgSEQ.val(),
				tenantOrgSEQ:$tenantOrgSEQ.val(),
			},additionData);
			if($.trim(queryData.comeToEnd)=="true"){
				//无需再次加载
			}
			else{
				$.loadSeriesList(queryData);
			}
		},
		loadTopicTemplate:function(){
			
			
		}
		
	});
})(jQuery);


