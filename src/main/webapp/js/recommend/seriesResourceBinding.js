$(function(){
	var supportH5=$.supportHTML5();
	
	var $openScopeModal=$("[data-remodal-id=weightModal]").remodal($.defaultRemodalOption());
		//专题资源编辑页面
		var $seriesResourceEditor=$("#seriesResourceEditor");
		if($seriesResourceEditor.length>0){
			var paginationConfig=$.getPaginationConfig();
			paginationConfig.actionForClearLoadedData=function(){
				switch(nowTab){
				case "course":
					$("#courseList .infoRow").remove();
				case "train":
					$("#trainList .infoRow").remove();
				case "serise":
					$("#seriesList .infoRow").remove();
				}
			};
            var trainPaginationConfig={
                getUrlForPagination:function(){return "/seriesResourceBinding/getList.do"},
                actionForClearLoadedData:function(){
                    $("#trainList .infoRow").remove();
                },
                actionForSucessLoadingData:function(data){
                    var dataList=data.list;
                    var dataLength=dataList.length;
                    var htmlArray=new Array();
                    for ( var i = 0; i < dataLength; i++) {
                        var currentItem = dataList[i];
                        var trainId = currentItem.itemId;
                        var trainName = currentItem.trainName;
                        var sponsorName = currentItem.sponsorName;
                        if(sponsorName==null||sponsorName=="")
                            sponsorName = "—";
                        var startTimeStr = currentItem.startTime;
                        if(startTimeStr==null||startTimeStr=="")
                            startTimeStr = "—";
                        var endTimeStr = currentItem.endTime;
                        if(endTimeStr==null||endTimeStr=="")
                            endTimeStr = "—";
                        var organizerName = currentItem.organizerName;
                        if(organizerName==null||organizerName=="")
                            organizerName = "—";
                        htmlArray.push("<tr class='infoRow'>");

                        htmlArray.push("<td >" +
                            "<input id='checkbox_"+trainId+"' type='checkbox' value='"+trainId+"' name='selectbox' />" +
                            "<input id='seriesItemId_"+currentItem.id+"' type='hidden' name=\"seriesItemId\" value='"+currentItem.id+"'/>" +
                            "</td>");
                        htmlArray.push("<td align='center'>"
                            + trainName + "</td>");
                        htmlArray.push("<td align='center'>"
                            + sponsorName + "</td>");
                        htmlArray.push("<td align='center' >"
                            + startTimeStr + "</td>");
                        htmlArray.push("<td align='center' >"
                            + endTimeStr + "</td>");
                        htmlArray.push("<td align='center' class = 'coursedisplayYes' >"
                            + organizerName
                            + "</td>");
                        if(currentItem.orderWeight == null||currentItem.orderWeight == 0){
                            htmlArray.push("<td align='center'>"+" "+"<a href=\"javascript:void(0);\" class=\"editWeight\">操作</a>"+"</td>");
                        }else
                            htmlArray.push("<td align='center'>"+currentItem.orderWeight+"&nbsp;<a href=\"javascript:void(0);\" class=\"editWeight\">操作</a>"+"</td>");
                        htmlArray
                            .push("<td>"
                                +"<a href=\"javascript:previewTrain("+trainId+")\">预览</a>"
                                +" | "
                                +"<a href=\"javascript:removeItem("+trainId+","+1+")\">删除</a>"
                                +"</td>");//<%=ResourceType.TOPIC.getTypeCode()%>
                        htmlArray.push("</tr>");
                    }
                    $("#trainList .infoRow").remove();
                    $("#trainList").append(htmlArray.join(""));
                }
			}
            var coursePaginationConfig={
                getUrlForPagination:function(){return "/seriesResourceBinding/getList.do"},
                actionForClearLoadedData:function(){
                    $("#courseList .infoRow").remove();
                },
                actionForSucessLoadingData:function(data){
                	var courseList=data.list;
                    var dataLength=courseList.length;
                    var htmlArray=new Array();
                    var courseHTMLInfo="";
                    for(var i=0;i<dataLength;i++){
                        var courseInfo=courseList[i];
                        courseHTMLInfo+="<tr class=\"infoRow\">";
                        courseHTMLInfo+="<td >" +
                            "<input id='checkbox_"+courseInfo.courseId+"' type='checkbox' value='"+courseInfo.courseId+"' name='selectbox' />" +
                            "<input id='seriesItemId_"+courseInfo.id+"' type='hidden' name=\"seriesItemId\" value='"+courseInfo.id+"'/>" +
                            "</td>";
                        if(typeof(courseInfo.courseName) == "undefined"){
                            courseHTMLInfo+="<td align='center'>"+" — —"+"</td>";
                        }else
                            courseHTMLInfo+="<td align='center'>"+courseInfo.courseName+"</td>";
                        if(typeof(courseInfo.categoryStr) == "undefined"){
                            courseHTMLInfo+="<td align='center'>"+" — —"+"</td>";
                        }else
                            courseHTMLInfo+="<td align='center'>"+courseInfo.categoryStr+"</td>";
                        if(typeof(courseInfo.classHour) == "undefined"){
                            courseHTMLInfo+="<td align='center'>"+" — —"+"</td>";
                        }else
                            courseHTMLInfo+="<td align='center'>"+courseInfo.classHour+"</td>";
                        if(courseInfo.createDate==null){
                            courseHTMLInfo+="<td align='center'>"+" — —"+"</td>";
                        }else
                            courseHTMLInfo+="<td align='center'>"+courseInfo.createDate+"</td>";
                        if(courseInfo.orderWeight == null||courseInfo.orderWeight == 0){
                            courseHTMLInfo+="<td align='center'>"+" "+"<a href=\"javascript:void(0);\" class=\"editWeight\">操作</a>"+"</td>";
                        }else
                            courseHTMLInfo+="<td align='center'>"+courseInfo.orderWeight+"&nbsp;<a href=\"javascript:void(0);\" class=\"editWeight\">操作</a>"+"</td>";
                        //courseHTMLInfo+="<td align='center'>"+courseInfo.orderWeight+"<a href=\"javascript:void(0);\" class=\"editWeight\">操作</a>"+"</td>";
                        courseHTMLInfo+="<td align='center'>"
                            +"<a href=\"javascript:previewCourse("+courseInfo.courseId+")\">预览</a>"
                            +" | "
                            +"<a href=\"javascript:removeItem("+courseInfo.courseId+","+0+")\">删除</a>"
                            +"</td>";//<%=ResourceType.COURSE.getTypeCode()%>
                        courseHTMLInfo+="</tr>";
                        /**
                         * <td >
                         <a href="javascript:void(0);" class="editSeries">编辑</a>
                         <a href="javascript:void(0);" class="previewSeries" >预览</a>
                         </td>
                         */
                    }
                    $("#courseList .infoRow").remove();
                    $("#courseList").append(courseHTMLInfo);
                }
            }
            var seriesPaginationConfig={

                getUrlForPagination:function(){return "/seriesResourceBinding/getList.do"},
                actionForClearLoadedData:function(){
                    $("#seriesList .infoRow").remove();
                },
                actionForSucessLoadingData:function(data){
                    var dataList=data.list;
                    var dataLength=dataList.length;
                    var htmlArray=new Array();
                    for(var i=0;i<dataLength;i++){
                        var currentItem=dataList[i];
                        var currentItem = dataList[i];
                        var detail = currentItem.detail;
                        if(detail==null||detail==""){
                            detail = "—";
                        }

                        htmlArray.push("<tr class='infoRow'>");

                        htmlArray.push("<td >" +
                            "<input id='checkbox_"+currentItem.seriesMainId+"' type='checkbox' value='"+currentItem.seriesMainId+"' name='selectbox' />" +
                            "<input id='seriesItemId_"+currentItem.id+"' type='hidden' name=\"seriesItemId\" value='"+currentItem.id+"'/>" +
                            "</td>");
                        htmlArray.push("<td align='center'>"
                            + currentItem.title + "</td>");
                        htmlArray.push("<td align='center' >"
                            + detail + "</td>");
                        htmlArray.push("<td align='center' >"
                            + currentItem.sponsorInfo + "</td>");
                        htmlArray
                            .push("<td align='center' class = 'coursedisplayYes' >"
                                + currentItem.createTime
                                + "</td>");
                        if(currentItem.orderWeight == null||currentItem.orderWeight == 0){
                            htmlArray.push("<td align='center'>"+" "+"<a href=\"javascript:void(0);\" class=\"editWeight\">操作</a>"+"</td>");
                        }else
                            htmlArray.push("<td align='center'>"+currentItem.orderWeight+"&nbsp;<a href=\"javascript:void(0);\" class=\"editWeight\">操作</a>"+"</td>");
                        htmlArray
                            .push("<td align='center'>"
                                +"<a href=\"javascript:previewSubSeries("+currentItem.seriesMainId+")\">预览</a>"
                                +" | "
                                +"<a href=\"javascript:removeItem("+currentItem.seriesMainId+","+4+")\">删除</a>"
                                +"</td>");//<%=ResourceType.TRAIN.getTypeCode()%>
                        htmlArray.push("</tr>");
                        htmlArray.push("</tr>");
                    }
                    $("#seriesList .infoRow").remove();
                    $("#seriesList").append(htmlArray.join(""));

                }

            }
			$seriesResourceEditor.on("click","input[name=tab]",function(){
				var tabValue=this.value;
				if(tabValue=="4"){
					//相关专题
					seriesTab_();
				}
				else if(tabValue=="0"){
					//相关课程
					courseTab_();
				}
				else if(tabValue=="1"){
					//相关培训
					trainTab_();
				}
				
			}).
			on("click",".editWeight",function(){
				var seriesItemId = $("input[name=seriesItemId]",$(this).parents("tr")).val();
				$("input[name=seriesItemId]").val(seriesItemId);
				$("input[name=weightInput]").val("");
				$("#fristRadio").prop("checked",true);
				$openScopeModal.open();
			});
			
			$("input[name=tab]:first",$seriesResourceEditor).click();
			
			
			
		}
		
		$(".weightModal").on("click",".editSeriesitemWeight",function(){
			var value = $("input[name=weightInput]").val();
			var seriesItemId =$("input[name=seriesItemId]").val();
			var operate = $("input[name=seriesitemWeight]:checked").val();
			$.ajax({
				method:"POST",
				data:{"seriesItemId":seriesItemId,"operate":operate,"value":value},
				url:"/seriesResourceBinding/updateItem.do",
				dataType:"json",
				success:function(data){
					if(data.status){
						$.Ntip({
									'content':"操作成功！"
								});
					}else{
						
					}
					$openScopeModal.close();
				}
				
			});
			
		})		
		
		function courseTab_(){
			paginationConfig.searchInfo.itemType = '0';
			$.extend(paginationConfig,coursePaginationConfig);
			$(".mainContent").removeClass("showSeries").removeClass("showTrain").addClass("showCourse");
			window.seriseTab = "course";
			paginationConfig.searchInfo.seriesId = $("#seriesId").val();
			paginationConfig.searchInfo.count = 10;
			paginationConfig.actionForLoadingPagination();
		}
		function trainTab_(){
			paginationConfig.searchInfo.itemType = '1';
			$.extend(paginationConfig,trainPaginationConfig);
			$(".mainContent").removeClass("showSeries").removeClass("showCourse").addClass("showTrain");
			window.seriseTab = "train";
			paginationConfig.searchInfo.seriesId = $("#seriesId").val();
			paginationConfig.searchInfo.count = 10;
			paginationConfig.actionForLoadingPagination();
		}
		function seriesTab_(){
			paginationConfig.searchInfo.itemType = '4';
			$.extend(paginationConfig,seriesPaginationConfig);
			$(".mainContent").removeClass("showCourse").removeClass("showTrain").addClass("showSeries");
			window.seriseTab = "serise";
			paginationConfig.searchInfo.seriesId = $("#seriesId").val();
			paginationConfig.searchInfo.count = 10;
			paginationConfig.actionForLoadingPagination();
		}

		/**
		 * 点击查询按钮事件
		 */
		function _query(){
			var nowTab = window.seriseTab;
			switch(nowTab){
			case "course":
				paginationConfig.searchInfo.itemType = '0';
				$.extend(paginationConfig,coursePaginationConfig);
				$(".mainContent").removeClass("showSeries").removeClass("showTrain").addClass("showCourse");
				window.seriseTab = "course";
				paginationConfig.searchInfo.seriesId = $("#seriesId").val();
				paginationConfig.searchInfo.count = 10;
				var name = $("#seriesName").val();
				name = name.replace(/^\s+|\s+$/g,"");
				paginationConfig.searchInfo.name = name;
				paginationConfig.actionForLoadingPagination();break;
			case "train":
				
				paginationConfig.searchInfo.itemType = '1';
				$.extend(paginationConfig,trainPaginationConfig);
				$(".mainContent").removeClass("showSeries").removeClass("showCourse").addClass("showTrain");
				window.seriseTab = "train";
				paginationConfig.searchInfo.seriesId = $("#seriesId").val();
				paginationConfig.searchInfo.count = 10;
				var name = $("#seriesName").val();
				name = name.replace(/^\s+|\s+$/g,"");
				paginationConfig.searchInfo.name = name;
				paginationConfig.actionForLoadingPagination();break;
			case "serise":
				paginationConfig.searchInfo.itemType = '4';
				$.extend(paginationConfig,seriesPaginationConfig);
				$(".mainContent").removeClass("showCourse").removeClass("showTrain").addClass("showSeries");
				window.seriseTab = "serise";
				paginationConfig.searchInfo.seriesId = $("#seriesId").val();
				paginationConfig.searchInfo.count = 10;
				var name = $("#seriesName").val();
				name = name.replace(/^\s+|\s+$/g,"");
				paginationConfig.searchInfo.name = name;
				paginationConfig.actionForLoadingPagination();break;
			}
			
		}
		
		window.seriesQuery = _query;
})
//监听modal关闭事件，分页补丁
$(document).on("closing", ".remodal", function() {
	$(".condition .pagination-admin").html("");
	if(window.seriseTab == "course"){
		$("input[name=tab]:eq(0)",$("#seriesResourceEditor")).click();
	}
	else if(window.seriseTab == "train"){
		$("input[name=tab]:eq(1)",$("#seriesResourceEditor")).click();
	}
	else{
		$("input[name=tab]:eq(2)",$("#seriesResourceEditor")).click();
	}
	
});
function removeItem(itemId,key){
	$.Nconfirm({
    	'confirmQuestion':"确定要删除吗？",
    	'onConfirm':function(){
    		$.ajax({
				url:"/seriesResourceBinding/removeItem.do",
				type:"POST",
			 	data:{seriesId:$("#seriesId").val(),itemId:itemId,key:key},
			 	dataType:"json",
			 	success:function(data){
				if(data.status){
					$.Ntip({
						'content':"删除成功！"
					});
					if(window.seriseTab == "course"){
						$("input[name=tab]:eq(0)",$("#seriesResourceEditor")).click();
					}
					else if(window.seriseTab == "train"){
						$("input[name=tab]:eq(1)",$("#seriesResourceEditor")).click();
					}
					else{
						$("input[name=tab]:eq(2)",$("#seriesResourceEditor")).click();
					}
				}
			 	 }
				})
    	},
		'onDeny':function(){}
   		})
}
//批量删除
function removeItemByPatch(){
	$.Nconfirm({
    	'confirmQuestion':"确定要删除吗？",
    	'onConfirm':function(){
			var currentTable = undefined;//(window.seriseTab=="serise"?"series":"series")+"List";
			var key = undefined;
			switch(window.seriseTab){
				case 'course':
					key='0';
					currentTable = window.seriseTab+"List";
					break;
				case 'serise':
					key='4';
					currentTable = "seriesList";
					break;
				case 'train':
					key='1';
					currentTable = window.seriseTab+"List";
					break;
			
			}
			var itemIdArray = new Array();
			$("#"+currentTable+" tr.infoRow").each(function(){
				var $checkbox=$("input[type=checkbox]",$(this));
				if($checkbox.is(':checked')){
					itemIdArray.push($checkbox.val());
				}
			});
			if(itemIdArray.length==0){
				$.Ntip({
					'content':"请选择要删除的信息"
				});
				return;
			}
			if(itemIdArray.length > 0){
	    	$.ajax({
				url:"/seriesResourceBinding/removeItem.do",
				type:"POST",
				traditional:true,
			 	data:{seriesId:$("#seriesId").val(),itemId:itemIdArray,key:key},
			 	dataType:"json",
			 	success:function(data){
				if(data.status){
					$.Ntip({
						'content':"删除成功！"
					});
					if(window.seriseTab == "course"){
						$("input[name=tab]:eq(0)",$("#seriesResourceEditor")).click();
						$("input[type=checkbox]").each(function(){
							this.checked = false;
						});
					}
					else if(window.seriseTab == "train"){
						$("input[name=tab]:eq(1)",$("#seriesResourceEditor")).click();
						$("input[type=checkbox]").each(function(){
							this.checked = false;
						});
					}
					else{
						$("input[name=tab]:eq(2)",$("#seriesResourceEditor")).click();
						$("input[type=checkbox]").each(function(){
							this.checked = false;
						});
					}
					
				}
			 	 }
				});
			}
			else{ 		  
				jAlert("请选择要删除的信息!",'提示');		
			}	     	 
		
    	},
		'onDeny':function(){}
   		})
}

function previewSubSeries(seriesId){
	window.open("/recommendSeries/intoSeriesItemFrame.do?seriesId="+seriesId,"_blank");
}
function previewCourse(courseId){
	window.open("/onlineCourse/detail.do?bookId="+courseId+"&info=admin");
}
function previewTrain(trainId){
	window.open("/onlineTraining/viewTrain4Admin.do?trainId="+trainId);
}
